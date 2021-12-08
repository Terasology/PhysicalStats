// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.physicalstats.event;

import org.terasology.engine.entitySystem.entity.EntityRef;
import org.terasology.gestalt.entitysystem.event.Event;
import org.terasology.physicalstats.component.PhysicalStatsModifierComponent;

/**
 * This event is sent to an entity when a physical stats modifier has been added to it.
 */
public class OnPhysicalStatsModifierAddedEvent implements Event {
    /** A reference to the entity who added the physical stats modifier. */
    private EntityRef instigator;

    /** A reference to the entity that had the physical stats modifier added to it. */
    private EntityRef character;

    /** The physical stats modifier that was added. */
    private PhysicalStatsModifierComponent pStatsModifier;

    /**
     * Create an instance of this event with the given instigator.
     *
     * @param instigator    Entity who added the modifier.
     */
    public OnPhysicalStatsModifierAddedEvent(EntityRef instigator) {
        this.instigator = instigator;
    }

    /**
     * Create an instance of this event with the given instigator amd affected entity.
     *
     * @param instigator    Entity who added the modifier.
     * @param character     Entity that was affected.
     */
    public OnPhysicalStatsModifierAddedEvent(EntityRef instigator, EntityRef character) {
        this.instigator = instigator;
        this.character = character;
    }

    /**
     * Create an instance of this event with the given instigator, affected entity, and the physical stats modifier.
     *
     * @param instigator        Entity who added the modifier.
     * @param character         Entity that was affected.
     * @param pStatsModifier    Physical stats modifier that was added.
     */
    public OnPhysicalStatsModifierAddedEvent(EntityRef instigator, EntityRef character,
                                             PhysicalStatsModifierComponent pStatsModifier) {
        this.instigator = instigator;
        this.character = character;
        this.pStatsModifier = pStatsModifier;
    }

    /**
     * Get the entity who added the modifier to the character entity.
     *
     * @return  A reference to the instigator entity.
     */
    public EntityRef getInstigator() {
        return instigator;
    }

    /**
     * Get the entity that had the modifier attached to it.
     *
     * @return  A reference to the character entity.
     */
    public EntityRef getCharacter() {
        return character;
    }

    /**
     * Get the physical stats modifier that was attached to the character entity.
     *
     * @return  The physical stats modifier.
     */
    public PhysicalStatsModifierComponent getPModifier() {
        return pStatsModifier;
    }
}
