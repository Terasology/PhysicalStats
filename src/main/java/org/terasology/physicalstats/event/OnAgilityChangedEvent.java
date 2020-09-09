// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.physicalstats.event;

import org.terasology.engine.entitySystem.entity.EntityRef;
import org.terasology.engine.entitySystem.event.Event;

/**
 * This event is sent to an entity when there's a change in its agility attribute.
 */
public class OnAgilityChangedEvent implements Event {
    /**
     * A reference to the entity who caused the agility attribute change.
     */
    private final EntityRef instigator;

    /**
     * A reference to the entity whose agility attribute has been affected.
     */
    private final EntityRef character;

    /**
     * The old agility attribute value before the change.
     */
    private int originalValue;

    /**
     * The new agility attribute value following the change.
     */
    private int newValue;

    /**
     * Create an instance of this event with the given instigator and the affected entity.
     *
     * @param instigator Entity who caused the agility attribute changes in the character.
     * @param character Entity whose agility attribute was modified.
     */
    public OnAgilityChangedEvent(EntityRef instigator, EntityRef character) {
        this.instigator = instigator;
        this.character = character;
    }

    /**
     * Create an instance of this event with the given instigator and the affected entity, as well as the old and new
     * agility attribute values.
     *
     * @param instigator Entity who caused the agility attribute changes in the character.
     * @param character Entity whose agility attribute was modified.
     * @param originalValue The original agility attribute value before the modification.
     * @param newValue The new agility attribute value after the modification.
     */
    public OnAgilityChangedEvent(EntityRef instigator, EntityRef character, int originalValue, int newValue) {
        this.instigator = instigator;
        this.character = character;
        this.originalValue = originalValue;
        this.newValue = newValue;
    }

    /**
     * Get the entity who instigated this agility attribute changed event.
     *
     * @return A reference to the instigator entity.
     */
    public EntityRef getInstigator() {
        return instigator;
    }

    /**
     * Get the entity who had its agility changed.
     *
     * @return A reference to the character entity.
     */
    public EntityRef getCharacter() {
        return character;
    }

    /**
     * Get the (character) entity's original agility value.
     *
     * @return The old agility attribute.
     */
    public int getOriginalValue() {
        return originalValue;
    }

    /**
     * Get the (character) entity's new agility value.
     *
     * @return The new agility attribute.
     */
    public int getNewValue() {
        return newValue;
    }
}
