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
package org.terasology.physicalstats.event;

import org.terasology.engine.entitySystem.entity.EntityRef;
import org.terasology.engine.entitySystem.event.Event;
import org.terasology.physicalstats.component.PhysicalStatsModifierComponent;

/**
 * This event is sent to an entity when a physical stats modifier has been removed from it.
 */
public class OnPhysicalStatsModifierRemovedEvent implements Event {
    /** A reference to the entity who removed the physical stats modifier. */
    private EntityRef instigator;

    /** A reference to the entity that had the physical stats modifier removed from it. */
    private EntityRef character;

    /** The physical stats modifier that was removed. */
    private PhysicalStatsModifierComponent pStatsModifier;

    /**
     * Create an instance of this event with the given instigator.
     *
     * @param instigator    Entity who removed the modifier.
     */
    public OnPhysicalStatsModifierRemovedEvent(EntityRef instigator) {
        this.instigator = instigator;
    }

    /**
     * Create an instance of this event with the given instigator amd affected entity.
     *
     * @param instigator    Entity who removed the modifier.
     * @param character     Entity that was affected.
     */
    public OnPhysicalStatsModifierRemovedEvent(EntityRef instigator, EntityRef character) {
        this.instigator = instigator;
        this.character = character;
    }

    /**
     * Create an instance of this event with the given instigator, affected entity, and the physical stats modifier.
     *
     * @param instigator        Entity who added the modifier.
     * @param character         Entity that was affected.
     * @param pStatsModifier    Physical stats modifier that was removed.
     */
    public OnPhysicalStatsModifierRemovedEvent(EntityRef instigator, EntityRef character,
                                               PhysicalStatsModifierComponent pStatsModifier) {
        this.instigator = instigator;
        this.character = character;
        this.pStatsModifier = pStatsModifier;
    }

    /**
     * Get the entity who removed the modifier from the character entity.
     *
     * @return  A reference to the instigator entity.
     */
    public EntityRef getInstigator() {
        return instigator;
    }

    /**
     * Get the entity that had the modifier deleted from it.
     *
     * @return  A reference to the character entity.
     */
    public EntityRef getCharacter() {
        return character;
    }

    /**
     * Get the physical stats modifier that was removed from the character entity.
     *
     * @return  The physical stats modifier.
     */
    public PhysicalStatsModifierComponent getPModifier() {
        return pStatsModifier;
    }
}
