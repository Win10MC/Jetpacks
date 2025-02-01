package com.win10mc.jetpacks.client;

import com.win10mc.jetpacks.item.JetpackItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
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

		HudRenderCallback.EVENT.register((drawContext, renderTickCounter) -> {
			PlayerEntity player = MinecraftClient.getInstance().player;

			if (player != null) {
				JetpackItem jetpack = JetpackItem.getEquippedJetpack(player);

				if (jetpack != null && !player.isInCreativeMode()) {
					int screenWidth = client.getWindow().getScaledWidth();
					int screenHeight = client.getWindow().getScaledHeight();
					Text enabledText;
					double[] jetpackEnabledText = {5, screenHeight / 2};

					if (jetpack.engineStatus(player.getEquippedStack(EquipmentSlot.CHEST))) {
						enabledText = Text.literal("Enabled").setStyle(Style.EMPTY.withColor(0x00FF00));
					} else {
						enabledText = Text.literal("Disabled").setStyle(Style.EMPTY.withColor(0xFF0000));
					}

					Text combinedText = Text.literal("Jetpack Engine (" + toggleJetpack.getBoundKeyLocalizedText().getString() + "): ")
							.append(enabledText);

					drawContext.drawText(client.textRenderer, combinedText, (int) jetpackEnabledText[0], (int) jetpackEnabledText[1], 0xFFFFFF, false);
					drawContext.drawText(client.textRenderer, "Jetpack Durability: " + (player.getEquippedStack(EquipmentSlot.CHEST).getMaxDamage() - player.getEquippedStack(EquipmentSlot.CHEST).getDamage()) + "/" + player.getEquippedStack(EquipmentSlot.CHEST).getMaxDamage(), (int) jetpackEnabledText[0], (int) jetpackEnabledText[1] + 10, 0xFFFFFF, false);
				}
			}
		});
	}
}
