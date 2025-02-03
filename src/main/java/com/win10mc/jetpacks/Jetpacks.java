package com.win10mc.jetpacks;

import com.win10mc.jetpacks.component.ModDataComponentTypes;
import com.win10mc.jetpacks.item.JetpackItem;
import com.win10mc.jetpacks.item.ModItems;
import com.win10mc.jetpacks.network.EnableJetpackPacket;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EquipmentSlot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Jetpacks implements ModInitializer {

	public static String MOD_ID = "jetpacks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerItems();
		ModDataComponentTypes.registerDataComponentTypes();
		ModCategories.register();

		PayloadTypeRegistry.playC2S().register(EnableJetpackPacket.ID, EnableJetpackPacket.CODEC);

		ServerPlayNetworking.registerGlobalReceiver(EnableJetpackPacket.ID, (payload, context) -> {
			context.server().execute(() -> {
				JetpackItem.toggle(context.player().getEquippedStack(EquipmentSlot.CHEST));
			});
		});
	}
}
