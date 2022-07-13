package net.fabricmc.orienteering.util.mixin;

import net.fabricmc.orienteering.block.entity.ControlBlockBlockEntity;

public interface OpenControlBlockScreenAccessor {
    public void openControlBlockScreen(ControlBlockBlockEntity controlBlock);
}