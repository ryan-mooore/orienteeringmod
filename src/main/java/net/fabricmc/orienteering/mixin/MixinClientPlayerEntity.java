package net.fabricmc.orienteering.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.fabricmc.orienteering.block.entity.ControlBoxBlockEntity;
import net.fabricmc.orienteering.client.gui.screen.ControlBoxBlockScreen;
import net.fabricmc.orienteering.util.mixin.OpenControlBoxBlockScreenAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

@Mixin(ClientPlayerEntity.class)
public abstract class MixinClientPlayerEntity implements OpenControlBoxBlockScreenAccessor {
    @Shadow
    private MinecraftClient client;

    public void openControlBoxBlockScreen(ControlBoxBlockEntity controlBlock) {
        this.client.setScreen(new ControlBoxBlockScreen(controlBlock));
    }
}
