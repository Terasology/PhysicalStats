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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.engine.entitySystem.entity.EntityManager;
import org.terasology.engine.entitySystem.entity.EntityRef;
import org.terasology.engine.entitySystem.systems.BaseComponentSystem;
import org.terasology.engine.entitySystem.systems.RegisterSystem;
import org.terasology.engine.logic.console.commandSystem.annotations.Command;
import org.terasology.engine.logic.console.commandSystem.annotations.CommandParam;
import org.terasology.engine.logic.permission.PermissionManager;
import org.terasology.engine.logic.players.LocalPlayer;
import org.terasology.engine.registry.CoreRegistry;
import org.terasology.engine.registry.In;
import org.terasology.physicalstats.component.PhysicalStatsComponent;
import org.terasology.physicalstats.event.OnAgilityChangedEvent;
import org.terasology.physicalstats.event.OnConstitutionChangedEvent;
import org.terasology.physicalstats.event.OnPhysicalStatChangedEvent;
import org.terasology.physicalstats.event.OnStrengthChangedEvent;

/**
 * This system handles cheat or debug commands related to the physical stats system.
 */
@RegisterSystem
public class PhysicalStatsSystemCommands extends BaseComponentSystem {
    /**
     * Define a logger for logging debug information about this system.
     */
    private static final Logger logger = LoggerFactory.getLogger(PhysicalStatsSystemCommands.class);

    @In
    private EntityManager entityManager;

    /**
     * Print all of the current base physical stats of the local player to the console window.
     */
    @Command(shortDescription = "Show all stats of the local player", requiredPermission = PermissionManager.CHEAT_PERMISSION)
    public void getPlayerStats() {
        // Get the local player's character entity, and get the PhysicalStatsComponent from it to print the base stat
        // attributes.
        if (CoreRegistry.get(LocalPlayer.class).getCharacterEntity().hasComponent(PhysicalStatsComponent.class)) {
            PhysicalStatsComponent p = CoreRegistry.get(LocalPlayer.class).getCharacterEntity().
                    getComponent(PhysicalStatsComponent.class);

            logger.info("STR: " + p.strength + " DEX: " + p.dexterity + " END: " + p.endurance + " CON: "
                    + p.constitution + " AGI: " + p.agility + " CHA: " + p.charisma + " LUK: " + p.luck);
        }
    }

    /**
     * Set the local player's base strength attribute to the given amount.
     *
     * @param amount    The new strength value.
     */
    @Command(shortDescription = "Set physical STR stat.", requiredPermission = PermissionManager.CHEAT_PERMISSION)
    public void setSTR(@CommandParam("amount") int amount) {
        // Get the local player's character entity, and check to see whether it has physical stats. If so, continue in
        // this if-block.
        if (CoreRegistry.get(LocalPlayer.class).getCharacterEntity().hasComponent(PhysicalStatsComponent.class)) {
            EntityRef player = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();

            // Store the old strength value, and replace it with the new one.
            int oldValue = player.getComponent(PhysicalStatsComponent.class).strength;
            PhysicalStatsComponent physicalStatsComponent = player.getComponent(PhysicalStatsComponent.class);
            physicalStatsComponent.strength = amount;
            player.saveComponent(physicalStatsComponent);
            // Print the change to the console.
            logger.info("STR changed from " + oldValue + " to " + amount);

            // Send two events to the player entity. The first for indicating that the strength has been changed.
            // The second for indicating that a physical stat has been changed.
            player.send(new OnStrengthChangedEvent(player, player, oldValue, amount));
            player.send(new OnPhysicalStatChangedEvent(player, player));
        }
    }

    /**
     * Set the local player's base dexterity attribute to the given amount.
     *
     * @param amount    The new dexterity value.
     */
    @Command(shortDescription = "Set physical DEX stat.", requiredPermission = PermissionManager.CHEAT_PERMISSION)
    public void setDEX(@CommandParam("amount") int amount) {
        // Get the local player's character entity, and check to see whether it has physical stats. If so, continue in
        // this if-block.
        if (CoreRegistry.get(LocalPlayer.class).getCharacterEntity().hasComponent(PhysicalStatsComponent.class)) {
            EntityRef player = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();

            // Store the old dexterity value, and replace it with the new one.
            int oldValue = player.getComponent(PhysicalStatsComponent.class).constitution;
            PhysicalStatsComponent physicalStatsComponent = player.getComponent(PhysicalStatsComponent.class);
            physicalStatsComponent.dexterity = amount;
            player.saveComponent(physicalStatsComponent);

            // Print the change to the console.
            logger.info("DEX changed from " + oldValue + " to " + amount);

            // Send an event to the player entity indicating that a physical stat has been changed.
            player.send(new OnPhysicalStatChangedEvent(player, player));
        }
    }

    /**
     * Set the local player's base constitution attribute to the given amount.
     *
     * @param amount    The new constitution value.
     */
    @Command(shortDescription = "Set physical CON stat.", requiredPermission = PermissionManager.CHEAT_PERMISSION)
    public void setCON(@CommandParam("amount") int amount) {
        // Get the local player's character entity, and check to see whether it has physical stats. If so, continue in
        // this if-block.
        if (CoreRegistry.get(LocalPlayer.class).getCharacterEntity().hasComponent(PhysicalStatsComponent.class)) {
            EntityRef player = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();

            // Store the old constitution value, and replace it with the new one.
            int oldValue = player.getComponent(PhysicalStatsComponent.class).constitution;
            PhysicalStatsComponent physicalStatsComponent = player.getComponent(PhysicalStatsComponent.class);
            physicalStatsComponent.constitution = amount;
            player.saveComponent(physicalStatsComponent);

            // Print the change to the console.
            logger.info("CON changed from " + oldValue + " to " + amount);

            // Send two events to the player entity. The first for indicating that the constitution has been changed.
            // The second for indicating that a physical stat has been changed.
            player.send(new OnConstitutionChangedEvent(player, player, oldValue, amount));
            player.send(new OnPhysicalStatChangedEvent(player, player));
        }
    }

    /**
     * Set the local player's base agility attribute to the given amount.
     *
     * @param amount    The new agility value.
     */
    @Command(shortDescription = "Set physical AGI stat.", requiredPermission = PermissionManager.CHEAT_PERMISSION)
    public void setAGI(@CommandParam("amount") int amount) {
        // Get the local player's character entity, and check to see whether it has physical stats. If so, continue in
        // this if-block.
        if (CoreRegistry.get(LocalPlayer.class).getCharacterEntity().hasComponent(PhysicalStatsComponent.class)) {
            EntityRef player = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();

            // Store the old agility value, and replace it with the new one.
            int oldValue = player.getComponent(PhysicalStatsComponent.class).agility;
            PhysicalStatsComponent physicalStatsComponent = player.getComponent(PhysicalStatsComponent.class);
            physicalStatsComponent.agility = amount;
            player.saveComponent(physicalStatsComponent);

            // Print the change to the console.
            logger.info("AGI changed from " + oldValue + " to " + amount);

            // Send two events to the player entity. The first for indicating that the agility has been changed.
            // The second for indicating that a physical stat has been changed.
            player.send(new OnAgilityChangedEvent(player, player, oldValue, amount));
            player.send(new OnPhysicalStatChangedEvent(player, player));
        }
    }

    /**
     * Set the local player's base endurance attribute to the given amount.
     *
     * @param amount    The new endurance value.
     */
    @Command(shortDescription = "Set physical END stat.", requiredPermission = PermissionManager.CHEAT_PERMISSION)
    public void setEND(@CommandParam("amount") int amount) {
        // Get the local player's character entity, and check to see whether it has physical stats. If so, continue in
        // this if-block.
        if (CoreRegistry.get(LocalPlayer.class).getCharacterEntity().hasComponent(PhysicalStatsComponent.class)) {
            EntityRef player = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();

            // Store the old endurance value, and replace it with the new one.
            int oldValue = player.getComponent(PhysicalStatsComponent.class).endurance;
            PhysicalStatsComponent physicalStatsComponent = player.getComponent(PhysicalStatsComponent.class);
            physicalStatsComponent.endurance = amount;
            player.saveComponent(physicalStatsComponent);

            // Print the change to the console.
            logger.info("END changed from " + oldValue + " to " + amount);

            // Send an event to the player entity indicating that a physical stat has been changed.
            player.send(new OnPhysicalStatChangedEvent(player, player));
        }
    }

    /**
     * Set the local player's base charisma attribute to the given amount.
     *
     * @param amount    The new charisma value.
     */
    @Command(shortDescription = "Set physical CHA stat.", requiredPermission = PermissionManager.CHEAT_PERMISSION)
    public void setCHA(@CommandParam("amount") int amount) {
        // Get the local player's character entity, and check to see whether it has physical stats. If so, continue in
        // this if-block.
        if (CoreRegistry.get(LocalPlayer.class).getCharacterEntity().hasComponent(PhysicalStatsComponent.class)) {
            EntityRef player = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();

            // Store the old charisma value, and replace it with the new one.
            int oldValue = player.getComponent(PhysicalStatsComponent.class).charisma;
            PhysicalStatsComponent physicalStatsComponent = player.getComponent(PhysicalStatsComponent.class);
            physicalStatsComponent.charisma = amount;
            player.saveComponent(physicalStatsComponent);

            // Print the change to the console.
            logger.info("CHA changed from " + oldValue + " to " + amount);

            // Send an event to the player entity indicating that a physical stat has been changed.
            player.send(new OnPhysicalStatChangedEvent(player, player));
        }
    }

    /**
     * Set the local player's base luck attribute to the given amount.
     *
     * @param amount    The new luck value.
     */
    @Command(shortDescription = "Set physical LUK stat.", requiredPermission = PermissionManager.CHEAT_PERMISSION)
    public void setLUK(@CommandParam("amount") int amount) {
        // Get the local player's character entity, and check to see whether it has physical stats. If so, continue in
        // this if-block.
        if (CoreRegistry.get(LocalPlayer.class).getCharacterEntity().hasComponent(PhysicalStatsComponent.class)) {
            EntityRef player = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();

            // Store the old luck value, and replace it with the new one.
            int oldValue = player.getComponent(PhysicalStatsComponent.class).luck;
            PhysicalStatsComponent physicalStatsComponent = player.getComponent(PhysicalStatsComponent.class);
            physicalStatsComponent.luck = amount;
            player.saveComponent(physicalStatsComponent);

            // Print the change to the console.
            logger.info("LUK changed from " + oldValue + " to " + amount);

            // Send an event to the player entity indicating that a physical stat has been changed.
            player.send(new OnPhysicalStatChangedEvent(player, player));
        }
    }
}
