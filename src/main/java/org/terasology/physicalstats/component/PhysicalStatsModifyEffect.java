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

import org.terasology.context.Context;
import org.terasology.engine.Time;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.logic.delay.DelayManager;
import org.terasology.physicalstats.event.OnPhysicalStatsModifierAddedEvent;
import org.terasology.physicalstats.event.OnPhysicalStatsModifierRemovedEvent;

public class PhysicalStatsModifyEffect implements IPhysicalStatsModifyEffect {
    private final Time time;
    private final DelayManager delayManager;

    public PhysicalStatsModifyEffect(Context context) {
        this.time = context.get(Time.class);
        this.delayManager = context.get(DelayManager.class);
    }

    @Override
    public void applyMod(EntityRef instigator, EntityRef entity, PhysicalStatsModifierComponent magnitude, long duration) {
        PhysicalStatsModifiersListComponent modifier = entity.getComponent(PhysicalStatsModifiersListComponent.class);

        if (modifier == null) {
            modifier = new PhysicalStatsModifiersListComponent();
            entity.addComponent(modifier);
        }

        if (modifier.modifiers.get(magnitude.id) == null) {
            modifier.modifiers.put(magnitude.id, magnitude);
        } else {
            modifier.modifiers.replace(magnitude.id, magnitude);
        }

        if (duration >= 0) {
            delayManager.addDelayedAction(entity, "" + magnitude.id, duration);
        }

        entity.addOrSaveComponent(modifier);
        entity.saveComponent(modifier);

        entity.send(new OnPhysicalStatsModifierAddedEvent(instigator, entity, magnitude));
    }

    public static void removeMod(EntityRef instigator, EntityRef entity, String id) {
        PhysicalStatsModifiersListComponent modifier = entity.getComponent(PhysicalStatsModifiersListComponent.class);

        if (modifier == null || modifier.modifiers.get(id) == null) {
            return;
        }

        PhysicalStatsModifierComponent temp = modifier.modifiers.get(id);
        modifier.modifiers.remove(id);

        entity.send(new OnPhysicalStatsModifierRemovedEvent(instigator, entity, temp));
    }
}
