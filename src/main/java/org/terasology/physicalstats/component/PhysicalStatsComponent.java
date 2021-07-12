// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.physicalstats.component;

import org.terasology.engine.network.Replicate;
import org.terasology.gestalt.entitysystem.component.Component;

/**
 * This component is used for storing information about an entity's base physical stats or attributes.
 * It's up to other modules to define how exactly each stat is used - the descriptions given here are guidelines.
 */
public class PhysicalStatsComponent implements Component<PhysicalStatsComponent> {
    /** The strength stat affects how much physical damage an entity does upon striking a target. */
    @Replicate
    public int strength;

    /** The dexterity stat will affect weapon accuracy and item use speed in the future. */
    @Replicate
    public int dexterity;

    /** The constitution stat affects player health. */
    @Replicate
    public int constitution;

    /** The agility stat affects player movement speed. */
    @Replicate
    public int agility;

    /** The endurance stat will affect something in the future. */
    @Replicate
    public int endurance;

    /** The charisma stat will affect NPC interactions and shopping in the future. */
    @Replicate
    public int charisma;

    /** The luck stat will provide benefits to many different actions in the future. */
    @Replicate
    public int luck;

    @Override
    public void copy(PhysicalStatsComponent other) {
        this.strength = other.strength;
        this.dexterity = other.dexterity;
        this.constitution = other.constitution;
        this.agility = other.agility;
        this.endurance = other.endurance;
        this.charisma = other.charisma;
        this.luck = other.luck;
    }
}
