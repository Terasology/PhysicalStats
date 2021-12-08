// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.physicalstats.event;

import org.terasology.engine.entitySystem.entity.EntityRef;
import org.terasology.gestalt.entitysystem.event.Event;

/**
 * This event is sent to an entity when there's a change in its constitution attribute.
 */
public class OnConstitutionChangedEvent implements Event {
    /** A reference to the entity who caused the constitution attribute change. */
    private final EntityRef instigator;

    /** A reference to the entity whose constitution attribute has been affected. */
    private final EntityRef character;

    /** The old constitution attribute value before the change. */
    private int originalValue;

    /** The new constitution attribute value following the change. */
    private int newValue;

    /**
     * Create an instance of this event with the given instigator and the affected entity.
     *
     * @param instigator    Entity who caused the constitution attribute changes in the character.
     * @param character     Entity whose constitution attribute was modified.
     */
    public OnConstitutionChangedEvent(EntityRef instigator, EntityRef character) {
        this.instigator = instigator;
        this.character = character;
    }

    /**
     * Create an instance of this event with the given instigator and the affected entity, as well as the old and new
     * constitution attribute values.
     *
     * @param instigator     Entity who caused the constitution attribute changes in the character.
     * @param character      Entity whose constitution attribute was modified.
     * @param originalValue  The original constitution attribute value before the modification.
     * @param newValue       The new constitution attribute value after the modification.
     */
    public OnConstitutionChangedEvent(EntityRef instigator, EntityRef character, int originalValue, int newValue) {
        this.instigator = instigator;
        this.character = character;
        this.originalValue = originalValue;
        this.newValue = newValue;
    }

    /**
     * Get the entity who instigated this constitution attribute changed event.
     *
     * @return  A reference to the instigator entity.
     */
    public EntityRef getInstigator() {
        return instigator;
    }

    /**
     * Get the entity who had its constitution changed.
     *
     * @return  A reference to the character entity.
     */
    public EntityRef getCharacter() {
        return character;
    }

    /**
     * Get the (character) entity's original constitution value.
     *
     * @return  The old constitution attribute.
     */
    public int getOriginalValue() {
        return originalValue;
    }

    /**
     * Get the (character) entity's new constitution value.
     *
     * @return  The new constitution attribute.
     */
    public int getNewValue() {
        return newValue;
    }
}
