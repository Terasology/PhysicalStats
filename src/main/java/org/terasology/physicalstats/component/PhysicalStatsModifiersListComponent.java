// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.physicalstats.component;

import org.terasology.engine.entitySystem.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * This component is used for storing a mapped list of physical stats modifiers that are applied to the entity that this
 * component's attached to. Each map entry is a reference to an item's or effect's physical stat modifiers. This is
 * intended to be attached to entities, not items. Use PhysicalStatsModifierComponent for items.
 * <p>
 * Note: Make sure that the entity you are attaching this to has a PhysicalStatsComponent.
 */
public class PhysicalStatsModifiersListComponent implements Component {
    /**
     * A map of physical stats modifiers being applied to an entity.
     */
    public Map<String, PhysicalStatsModifierComponent> modifiers = new HashMap<String,
            PhysicalStatsModifierComponent>();
}
