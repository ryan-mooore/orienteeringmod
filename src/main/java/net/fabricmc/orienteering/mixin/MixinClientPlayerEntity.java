package net.fabricmc.orienteering.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.fabricmc.orienteering.block.entity.ControlBoxBlockEntity;
import net.fabricmc.orienteering.client.gui.screen.ControlBoxBlockScreen;
import net.fabricmc.orienteering.util.mixin.OpenControlBlockScreenAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

@Mixin(ClientPlayerEntity.class)
public abstract class MixinClientPlayerEntity implements OpenControlBlockScreenAccessor {
    @Shadow
    private MinecraftClient client;

    public void openControlBlockScreen(ControlBoxBlockEntity controlBlock) {
        this.client.setScreen(new ControlBoxBlockScreen(controlBlock));
    }
}
