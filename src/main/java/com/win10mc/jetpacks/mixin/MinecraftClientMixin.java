package com.win10mc.jetpacks.mixin;

import com.win10mc.jetpacks.client.JetpacksClient;
import com.win10mc.jetpacks.item.JetpackItem;
import com.win10mc.jetpacks.item.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
	@Inject(method = "tick", at = @At("HEAD"))
	private void onTick(CallbackInfo info) {
		if (JetpacksClient.toggleJetpack.wasPressed()) {
			PlayerEntity player = MinecraftClient.getInstance().player;
			if (player != null) {
				JetpackItem jetpack = JetpackItem.getEquippedJetpack(player);
				if (jetpack != null) {
					jetpack.toggle();
				}
			}
		}
	}
}
