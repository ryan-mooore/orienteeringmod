package net.fabricmc.orienteering.util.mixin;

import net.fabricmc.orienteering.block.entity.ControlBoxBlockEntity;

public interface OpenControlBlockScreenAccessor {
    public void openControlBlockScreen(ControlBoxBlockEntity controlBlock);
}