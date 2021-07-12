// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.physicalstats.component;

import org.terasology.engine.network.Replicate;
import org.terasology.gestalt.entitysystem.component.Component;
import org.terasology.reflection.MappedContainer;

/**
 * This component is used for storing information about a particular physical stats modifier. This modifier can be
 * temporary or permanent, and from an entity or item. This is intended to be applied to items or buffs, and not
 * (directly) to character entities like players. For entities like those, use PhysicalStatsModifiersList, and add
 * instances of this to that class.
 *
 * Note: Only one of this kind of modifier should be applied to either items or buffs at a time.
 */
@MappedContainer
public class PhysicalStatsModifierComponent implements Component<PhysicalStatsModifierComponent> {
    /**
     * This stores this particular modifier's ID. Each modifier is normally intended to have a different ID, barring
     * the scenario where certain effects can replace older ones.
     */
    @Replicate
    public String id;

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
    public void copy(PhysicalStatsModifierComponent other) {
        this.id = other.id;
        this.strength = other.strength;
        this.dexterity = other.dexterity;
        this.constitution = other.constitution;
        this.agility = other.agility;
        this.endurance = other.endurance;
        this.charisma = other.charisma;
        this.luck = other.luck;
    }
}
