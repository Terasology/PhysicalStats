// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.physicalstats.component;

import org.terasology.gestalt.entitysystem.component.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * This component is used for storing a mapped list of physical stats modifiers that are applied to the entity that
 * this component's attached to. Each map entry is a reference to an item's or effect's physical stat modifiers. This is
 * intended to be attached to entities, not items. Use PhysicalStatsModifierComponent for items.
 *
 * Note: Make sure that the entity you are attaching this to has a PhysicalStatsComponent.
 */
public class PhysicalStatsModifiersListComponent implements Component<PhysicalStatsModifiersListComponent> {
    /** A map of physical stats modifiers being applied to an entity. */
    public Map<String, PhysicalStatsModifierComponent> modifiers = new HashMap<String, PhysicalStatsModifierComponent>();
}
