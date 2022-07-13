package net.fabricmc.orienteering.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.fabricmc.orienteering.block.entity.ControlBlockBlockEntity;
import net.fabricmc.orienteering.client.gui.screen.ControlBlockScreen;
import net.fabricmc.orienteering.util.mixin.OpenControlBlockScreenAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

@Mixin(ClientPlayerEntity.class)
public abstract class MixinClientPlayerEntity implements OpenControlBlockScreenAccessor {
    @Shadow
    private MinecraftClient client;

    public void openControlBlockScreen(ControlBlockBlockEntity controlBlock) {
        this.client.setScreen(new ControlBlockScreen(controlBlock));
    }
}
