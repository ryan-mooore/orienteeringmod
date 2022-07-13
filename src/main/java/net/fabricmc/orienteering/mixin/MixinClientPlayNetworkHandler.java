package net.fabricmc.orienteering.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class MixinClientPlayNetworkHandler {

}
