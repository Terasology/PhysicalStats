/*
 * Copyright 2016 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.physicalstats.system;

import org.terasology.context.Context;
import org.terasology.entitySystem.entity.EntityManager;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.event.ReceiveEvent;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.logic.characters.GetMaxSpeedEvent;
import org.terasology.logic.health.BeforeDamagedEvent;
import org.terasology.logic.health.HealthComponent;
import org.terasology.logic.players.event.OnPlayerSpawnedEvent;
import org.terasology.physicalstats.component.PhysicalStatsComponent;
import org.terasology.physicalstats.component.PhysicalStatsModifierComponent;
import org.terasology.physicalstats.component.PhysicalStatsModifiersListComponent;
import org.terasology.physicalstats.event.OnConstitutionChangedEvent;
import org.terasology.registry.In;

/**
 * This system handles the initialization of physical stats and the impact they have on certain actions.
 */
@RegisterSystem
public class PhysicalStatsSystem extends BaseComponentSystem {
    /**
     * Define a logger for logging debug information about this system.
     */
    private static final Logger logger = LoggerFactory.getLogger(PhysicalStatsSystem.class);

    /**
     * The value to multiply the constitution stat by for the maximum health. 10 by default.
     */
    public static int constitutionMultiplier = 10;

    @In
    private EntityManager entityManager;

    @In
    private Context context;

    /**
     * For every entity that has the PhysicalStatsComponent, initialize several characterstics that are affected by
     * having this component. This includes the max health for example.
     */
    @Override
    public void initialise() {
        for (EntityRef clientEntity : entityManager.getEntitiesWith(PhysicalStatsComponent.class)) {
            // For every entity that has a health component, set their max health equal to CON * 10.
            if (clientEntity.hasComponent(HealthComponent.class)) {
                HealthComponent h = clientEntity.getComponent(HealthComponent.class);
                PhysicalStatsComponent p = clientEntity.getComponent(PhysicalStatsComponent.class);
                updateHealth(clientEntity, h, p);
            }
        }

        super.initialise();
    }

    /**
     * Sets the health and maximum health values as components of the PhysicalStats constitution stat.
     * @param e The entity (i.e. player) to update
     * @param h The health stats of the player
     * @param p The physical stats of the player
     * @return True if changed, false if unchanged
     */
    public boolean updateHealth(EntityRef e, HealthComponent h, PhysicalStatsComponent p)
    {
        int newMaxHealth = p.constitution * constitutionMultiplier;
        if (h.maxHealth != newMaxHealth) {
            float healthPercentage = (float) h.currentHealth / (float) h.maxHealth;
            h.maxHealth = newMaxHealth;
            h.currentHealth = (int) Math.floor(newMaxHealth * healthPercentage);
            e.saveComponent(h);
            return true;
        }
        return false;
    }

    /**
     * When an entity (with physical stats) has been spawned following world generation or respawned following death,
     * perform some initialization tasks related to their stats.
     *
     * @param event     Event indicating the player has just been spawned.
     * @param player    Reference to the player entity that has been spawned.
     * @param phyStats  The physical stats of the player entity.
     */
    @ReceiveEvent
    public void onPlayerSpawn(OnPlayerSpawnedEvent event, EntityRef player, PhysicalStatsComponent phyStats) {
        // If the player entity has a health component, make sure that the max health is equal to CON * 10, and the
        // current health is equal to the maximum.
        if (player.hasComponent(HealthComponent.class)) {
            updateHealth(player, player.getComponent(HealthComponent.class), phyStats);
        }
    }

    /**
     * When a character entity's (with physical stats) constitution attribute is changed, update the related stats like
     * health.
     *
     * @param event     Event indicating the character's constitution has been altered.
     * @param player    Reference to the character entity that was affected.
     * @param phyStats  The physical stats of the character entity.
     */
    @ReceiveEvent
    public void onCONChanged(OnConstitutionChangedEvent event, EntityRef player, PhysicalStatsComponent phyStats) {
        // If the player entity has a health component, make sure that the max health is equal to CON * 10, and if the
        // current health is above the maximum health, set the current equal to the max.
        if (player.hasComponent(HealthComponent.class)) {
            HealthComponent h = player.getComponent(HealthComponent.class);
            updateHealth(player, h, phyStats);
        }
    }

    /**
     * Before this entity deals damage to another entity, apply the impact that their total strength attribute has on
     * the total or final damage value.
     *
     * @param event         Event with information of the instigator and current base damage (without stat influence).
     * @param damageTarget  Entity that's being targeted for an attack.
     */
    @ReceiveEvent
    public void impactOnPhysicalDamage(BeforeDamagedEvent event, EntityRef damageTarget) {
        // Ensure that the instigator entity actually has physical stats. If not, then STR will be non-existent.
        if (event.getInstigator().hasComponent(PhysicalStatsComponent.class)) {
            PhysicalStatsComponent phy = event.getInstigator().getComponent(PhysicalStatsComponent.class);

            // If the instigator has no physical stats modifiers, then directly add the (base strength / 2) to the
            // total damage value.
            if (!event.getInstigator().hasComponent(PhysicalStatsModifiersListComponent.class)) {
                event.add(phy.strength / 2f);
            } else {
                // Otherwise, get the list of physical stats modifiers applied to this entity.
                PhysicalStatsModifiersListComponent mods =
                        event.getInstigator().getComponent(PhysicalStatsModifiersListComponent.class);

                // Tally up the base strength and all the strength modifiers into the totalStrength variable.
                int totalStrength = phy.strength;
                for (PhysicalStatsModifierComponent mod : mods.modifiers.values()) {
                    totalStrength += mod.strength;
                }

                // Add the total strength to the total damage value.
                event.add(totalStrength / 2f);
            }
        }
    }

    /**
     * Before this entity (with physical stats) moves, apply the impact that their total agility attribute has on their
     * maximum movement speed.
     *
     * @param event     Event with information of the current (and modifiable) movement speed.
     * @param entity    Entity that's intending to move.
     * @param phy       The physical stats of the entity.
     */
    @ReceiveEvent
    public void impactOnSpeed(GetMaxSpeedEvent event, EntityRef entity, PhysicalStatsComponent phy) {
        // If this entity has no physical stats modifiers, then directly add the agility attribute to the max movement
        // speed. -1 is the minimum effect agility can have on the max speed. Every 10 AGI should increase the max
        // movement speed by 100$.
        if (!entity.hasComponent(PhysicalStatsModifiersListComponent.class)) {
            event.add(Math.max(-1, (phy.agility - 10) / 10f));
        } else {
            // Otherwise, get the list of physical stats modifiers applied to this entity.
            PhysicalStatsModifiersListComponent mods = entity.getComponent(PhysicalStatsModifiersListComponent.class);

            // Tally up the base agility and all the agility modifiers into the totalAgility variable.
            int totalAgility = phy.agility;
            for (PhysicalStatsModifierComponent mod : mods.modifiers.values()) {
                totalAgility += mod.agility;
            }

            // Add the total agility to the total damage value.
            event.add(Math.max(-1, (totalAgility - 10) / 10f));
        }
    }
}
