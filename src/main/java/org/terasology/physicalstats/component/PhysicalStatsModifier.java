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

import org.terasology.network.Replicate;
import org.terasology.reflection.MappedContainer;

@MappedContainer
public class PhysicalStatsModifier {
    @Replicate
    public String id;

    @Replicate
    public int strength;

    @Replicate
    public int dexterity;

    @Replicate
    public int constitution;

    @Replicate
    public int agility;

    @Replicate
    public int endurance;

    @Replicate
    public int charisma;

    @Replicate
    public int luck;

    public PhysicalStatsModifier() {

    }

    public PhysicalStatsModifier(String id, int strength, int dexterity, int constitution, int agility, int endurance,
                                 int charisma, int luck) {
        this.id = id;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.agility = agility;
        this.endurance = endurance;
        this.charisma = charisma;
        this.luck = luck;
    }
}
