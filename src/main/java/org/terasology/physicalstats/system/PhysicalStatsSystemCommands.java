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
import org.terasology.entitySystem.entity.EntityManager;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.logic.console.commandSystem.annotations.Command;
import org.terasology.logic.console.commandSystem.annotations.CommandParam;
import org.terasology.logic.players.LocalPlayer;
import org.terasology.physicalstats.component.PhysicalStatsComponent;
import org.terasology.physicalstats.event.OnAgilityChangedEvent;
import org.terasology.physicalstats.event.OnConstitutionChangedEvent;
import org.terasology.physicalstats.event.OnPhysicalStatChangedEvent;
import org.terasology.physicalstats.event.OnStrengthChangedEvent;
import org.terasology.registry.CoreRegistry;
import org.terasology.registry.In;

@RegisterSystem
public class PhysicalStatsSystemCommands extends BaseComponentSystem {
    private static final Logger logger = LoggerFactory.getLogger(PhysicalStatsSystemCommands.class);

    @In
    private EntityManager entityManager;

    @Command(shortDescription = "Show all stats of the local player", runOnServer = true)
    public void getPlayerStats() {
        if (CoreRegistry.get(LocalPlayer.class).getCharacterEntity().hasComponent(PhysicalStatsComponent.class)) {
            PhysicalStatsComponent p = CoreRegistry.get(LocalPlayer.class).getCharacterEntity().
                    getComponent(PhysicalStatsComponent.class);
            logger.info("STR: " + p.strength + " DEX: " + p.dexterity + " END: " + p.endurance + " CON: "
                    + p.constitution + " AGI: " + p.agility + " CHA: " + p.charisma + " LUK: " + p.luck);
        }
    }

    @Command(shortDescription = "Set physical STR stat.", runOnServer = true)
    public void setSTR(@CommandParam("amount") int amount) {
        if (CoreRegistry.get(LocalPlayer.class).getCharacterEntity().hasComponent(PhysicalStatsComponent.class)) {
            EntityRef player = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();

            int oldValue = player.getComponent(PhysicalStatsComponent.class).strength;
            player.getComponent(PhysicalStatsComponent.class).strength = amount;

            player.send(new OnStrengthChangedEvent(player, player, oldValue, amount));
            player.send(new OnPhysicalStatChangedEvent(player, player));
        }
    }

    @Command(shortDescription = "Set physical DEX stat.", runOnServer = true)
    public void setDEX(@CommandParam("amount") int amount) {
        if (CoreRegistry.get(LocalPlayer.class).getCharacterEntity().hasComponent(PhysicalStatsComponent.class)) {
            EntityRef player = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();

            CoreRegistry.get(LocalPlayer.class).getCharacterEntity().getComponent
                    (PhysicalStatsComponent.class).dexterity = amount;
            player.send(new OnPhysicalStatChangedEvent(player, player));
        }
    }

    @Command(shortDescription = "Set physical CON stat.", runOnServer = true)
    public void setCON(@CommandParam("amount") int amount) {
        if (CoreRegistry.get(LocalPlayer.class).getCharacterEntity().hasComponent(PhysicalStatsComponent.class)) {
            EntityRef player = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();

            int oldValue = player.getComponent(PhysicalStatsComponent.class).constitution;
            player.getComponent(PhysicalStatsComponent.class).constitution = amount;

            player.send(new OnConstitutionChangedEvent(player, player, oldValue, amount));
            player.send(new OnPhysicalStatChangedEvent(player, player));
        }
    }

    @Command(shortDescription = "Set physical AGI stat.", runOnServer = true)
    public void setAGI(@CommandParam("amount") int amount) {
        if (CoreRegistry.get(LocalPlayer.class).getCharacterEntity().hasComponent(PhysicalStatsComponent.class)) {
            EntityRef player = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();

            int oldValue = player.getComponent(PhysicalStatsComponent.class).agility;
            player.getComponent(PhysicalStatsComponent.class).agility = amount;

            player.send(new OnAgilityChangedEvent(player, player, oldValue, amount));
            player.send(new OnPhysicalStatChangedEvent(player, player));
        }
    }

    @Command(shortDescription = "Set physical END stat.", runOnServer = true)
    public void setEND(@CommandParam("amount") int amount) {
        if (CoreRegistry.get(LocalPlayer.class).getCharacterEntity().hasComponent(PhysicalStatsComponent.class)) {
            EntityRef player = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();

            CoreRegistry.get(LocalPlayer.class).getCharacterEntity().getComponent
                    (PhysicalStatsComponent.class).endurance = amount;

            player.send(new OnPhysicalStatChangedEvent(player, player));
        }
    }

    @Command(shortDescription = "Set physical CHA stat.", runOnServer = true)
    public void setCHA(@CommandParam("amount") int amount) {
        if (CoreRegistry.get(LocalPlayer.class).getCharacterEntity().hasComponent(PhysicalStatsComponent.class)) {
            EntityRef player = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();

            CoreRegistry.get(LocalPlayer.class).getCharacterEntity().getComponent
                    (PhysicalStatsComponent.class).charisma = amount;

            player.send(new OnPhysicalStatChangedEvent(player, player));
        }
    }

    @Command(shortDescription = "Set physical LUK stat.", runOnServer = true)
    public void setLUK(@CommandParam("amount") int amount) {
        if (CoreRegistry.get(LocalPlayer.class).getCharacterEntity().hasComponent(PhysicalStatsComponent.class)) {
            EntityRef player = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();

            CoreRegistry.get(LocalPlayer.class).getCharacterEntity().getComponent
                    (PhysicalStatsComponent.class).luck = amount;

            player.send(new OnPhysicalStatChangedEvent(player, player));
        }
    }
}
