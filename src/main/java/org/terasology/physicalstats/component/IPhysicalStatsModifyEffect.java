// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.physicalstats.component;

import org.terasology.engine.entitySystem.entity.EntityRef;

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
     * @param modifier      The physical stats modifier that's going to be applied to the entity.
     * @param duration      The duration of the effect in milliseconds. As long as this is greater than 0, the effect
     *                      will be applied on the entity. For permanent modifiers, see
     *                      {@link #applyMod(EntityRef, EntityRef, PhysicalStatsModifierComponent)}.
     */
    void applyMod(EntityRef instigator, EntityRef entity, PhysicalStatsModifierComponent modifier, long duration);

    /**
     * Apply a PhysicalStatsModifier on the entity for a permanent duration unless explicitly removed.
     *
     * @param instigator    The instigator who is applying this modifier on the entity. It can be another entity, block,
     *                      item, etc.
     * @param entity        The entity who the physical stats modifier is being applied on.
     * @param modifier      The physical stats modifier that's going to be applied to the entity.
     */
    void applyMod(EntityRef instigator, EntityRef entity, PhysicalStatsModifierComponent modifier);
}
