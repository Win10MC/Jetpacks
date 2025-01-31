package com.win10mc.jetpacks.client;

import com.win10mc.jetpacks.item.JetpackItem;
import com.win10mc.jetpacks.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import org.lwjgl.glfw.GLFW;

public class JetpacksClient implements ClientModInitializer {

	public static KeyBinding toggleJetpack;

	@Override
	public void onInitializeClient() {

		MinecraftClient client = MinecraftClient.getInstance();

		toggleJetpack = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.jetpacks.toggle_jetpack",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_R,
				"category.jetpacks.jetpack"
		));

		HudRenderCallback.EVENT.register(((drawContext, renderTickCounter) -> {
			PlayerEntity player = MinecraftClient.getInstance().player;
			if (player != null) {
				JetpackItem jetpack = JetpackItem.getEquippedJetpack(player);
				if (jetpack != null) {
					int screenWidth = client.getWindow().getScaledWidth();
					int screenHeight = client.getWindow().getScaledHeight();

					double[] jetpackEnabledText = {5, screenHeight / 2};
					drawContext.drawText(MinecraftClient.getInstance().textRenderer, "Jetpack Engine: " + jetpack.engineStatus(player.getEquippedStack(EquipmentSlot.CHEST)), ((int) jetpackEnabledText[0]), ((int) jetpackEnabledText[1]), 0xFFFFFF, false);
				}
			}
		}));
	}
}
