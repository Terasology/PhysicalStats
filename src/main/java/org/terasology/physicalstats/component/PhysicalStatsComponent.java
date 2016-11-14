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
package org.terasology.physicalstats.component;

import org.terasology.entitySystem.Component;
import org.terasology.network.Replicate;

/**
 * This component is used for storing information about an entity's base physical stats or attributes.
 * It's up to other modules to define how exactly each stat is used - the descriptions given here are guidelines.
 */
public class PhysicalStatsComponent implements Component {
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
}