// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.physicalstats.component;

import org.terasology.engine.context.Context;
import org.terasology.engine.core.Time;
import org.terasology.engine.entitySystem.entity.EntityRef;
import org.terasology.engine.logic.delay.DelayManager;
import org.terasology.physicalstats.event.OnPhysicalStatsModifierAddedEvent;
import org.terasology.physicalstats.event.OnPhysicalStatsModifierRemovedEvent;

/**
 * Class that handles the application and removal of permanent or temporary physical stats modifiers to/from an entity.
 */
public class PhysicalStatsModifyEffect implements IPhysicalStatsModifyEffect {
    /**  Used for getting the current time. */
    private final Time time;

    /** Reference to the delay manager for adding temporary modifiers. */
    private final DelayManager delayManager;

    /**
     * Create an instance of this class using the passed in Context to get the Time and DelayManager instances.
     *
     * @param context   Reference to the current context that this object is running in.
     */
    public PhysicalStatsModifyEffect(Context context) {
        this.time = context.get(Time.class);
        this.delayManager = context.get(DelayManager.class);
    }

    @Override
    public void applyMod(EntityRef instigator, EntityRef entity, PhysicalStatsModifierComponent modifier, long duration) {
        // If the duration is less than or equal to 0, throw an exception as this is an invalid value for duration.
        if (duration <= 0) {
            throw(new IllegalArgumentException("The inputted duration of " + duration + " falls outside the permitted boundaries."));
        }

        // Get the list of physical stats modifiers on this entity.
        PhysicalStatsModifiersListComponent modifiersList =
                entity.getComponent(PhysicalStatsModifiersListComponent.class);

        // If the list is null, create a new list and add the list to the entity.
        if (modifiersList == null) {
            modifiersList = new PhysicalStatsModifiersListComponent();
            entity.addComponent(modifiersList);
        }

        // If a modifier with this ID doesn't already exist in the list, add the modifier to the list. oOtherwise,
        // replace the old modifier with this new one.
        if (modifiersList.modifiers.get(modifier.id) == null) {
            modifiersList.modifiers.put(modifier.id, modifier);
        } else {
            modifiersList.modifiers.replace(modifier.id, modifier);
        }

        // If the duration is greater than or equal to 0, add this to the DelayManager. This indicates that the effect
        // is temporary. Otherwise, the effect will be treated as permanent unless another entity manually removes the
        // effect.
        if (duration >= 0) {
            delayManager.addDelayedAction(entity, "" + modifier.id, duration);
        }

        // Add/save the modifiers list
        entity.addOrSaveComponent(modifiersList);
        entity.saveComponent(modifiersList);

        // Send an event to the affected entity alerting that a physical stats modifier has been added to it.
        entity.send(new OnPhysicalStatsModifierAddedEvent(instigator, entity, modifier));
    }

    @Override
    public void applyMod(EntityRef instigator, EntityRef entity, PhysicalStatsModifierComponent modifier) {
        // Get the list of physical stats modifiers on this entity.
        PhysicalStatsModifiersListComponent modifiersList =
                entity.getComponent(PhysicalStatsModifiersListComponent.class);

        // If the list is null, create a new list and add the list to the entity.
        if (modifiersList == null) {
            modifiersList = new PhysicalStatsModifiersListComponent();
            entity.addComponent(modifiersList);
        }

        // If a modifier with this ID doesn't already exist in the list, add the modifier to the list. oOtherwise,
        // replace the old modifier with this new one.
        if (modifiersList.modifiers.get(modifier.id) == null) {
            modifiersList.modifiers.put(modifier.id, modifier);
        } else {
            modifiersList.modifiers.replace(modifier.id, modifier);
        }

        // Add/save the modifiers list
        entity.addOrSaveComponent(modifiersList);
        entity.saveComponent(modifiersList);

        // Send an event to the affected entity alerting that a physical stats modifier has been added to it.
        entity.send(new OnPhysicalStatsModifierAddedEvent(instigator, entity, modifier));
    }

    /**
     * Remove a PhysicalStatsModifier from the entity. If the modifier doesn't exist (can't find the ID), nothing will
     * be removed.
     *
     * @param instigator    Entity that is removing this modifier.
     * @param entity        Entity that the modifier is being removed from.
     * @param id            ID of the entity to be removed.
     */
    public static void removeMod(EntityRef instigator, EntityRef entity, String id) {
        // Get the list of physical stats modifiers on this entity.
        PhysicalStatsModifiersListComponent modifiersList =
                entity.getComponent(PhysicalStatsModifiersListComponent.class);

        // If there's no modifier on this entity, or the provided modifier ID doesn't exist, return.
        if (modifiersList == null || modifiersList.modifiers.get(id) == null) {
            return;
        }

        // Get the modifier from the list, and then remove it from the list.
        PhysicalStatsModifierComponent temp = modifiersList.modifiers.get(id);
        modifiersList.modifiers.remove(id);

        // Send an event to the affected entity alerting that a physical stats modifier has been removed from it.
        entity.send(new OnPhysicalStatsModifierRemovedEvent(instigator, entity, temp));
    }
}
