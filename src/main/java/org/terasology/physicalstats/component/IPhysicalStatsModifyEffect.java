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

import org.terasology.entitySystem.entity.EntityRef;

/**
 * Interface for the application of physical stat modifiers over a period of time.
 */
public interface IPhysicalStatsModifyEffect {
    /**
     * Apply a PhysicalStatsModifier on the entity for the given duration.
     *
     * @param instigator    The instigator who is applying this modifier on the entity. It can be another entity, block,
     *                      item, etc.
     * @param entity        The entity who the physical stats modifier is being applied on.
     * @param magnitude     The physical stats modifier that's going to be applied to the entity.
     * @param duration      The duration of the effect in milliseconds. If this is less than 0, the effect will be
     *                      treated as permanent unless explicitly removed by another entity or system. For example, an
     *                      item with stat boosts. Otherwise, the effect will run until it's cancelled by the
     *                      DelayManager.
     */
    void applyMod(EntityRef instigator, EntityRef entity, PhysicalStatsModifierComponent magnitude, long duration);
}
