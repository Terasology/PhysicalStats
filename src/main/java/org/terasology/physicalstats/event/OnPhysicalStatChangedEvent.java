// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.physicalstats.event;

import org.terasology.engine.entitySystem.entity.EntityRef;
import org.terasology.gestalt.entitysystem.event.Event;

/**
 * This event is sent to an entity when there's a change in one or more of its physical stats.
 */
public class OnPhysicalStatChangedEvent implements Event {
    /** A reference to the entity who caused the change in physical stats. */
    private EntityRef instigator;

    /** A reference to the entity whose stats have been altered. */
    private EntityRef character;

    /** Default constructor. */
    public OnPhysicalStatChangedEvent() {
    }

    /**
     * Create an instance of this event with the given instigator and the affected entity.
     *
     * @param instigator    Entity who caused the physical stat changes in the character.
     * @param character     Entity whose physical stats have been modified.
     */
    public OnPhysicalStatChangedEvent(EntityRef instigator, EntityRef character) {
        this.instigator = instigator;
        this.character = character;
    }

    /**
     * Get the entity who instigated this stat(s) changed event.
     *
     * @return  A reference to the instigator entity.
     */
    public EntityRef getInstigator() {
        return instigator;
    }

    /**
     * Get the entity who had its stat(s) changed.
     *
     * @return  A reference to the character entity.
     */
    public EntityRef getCharacter() {
        return character;
    }
}
