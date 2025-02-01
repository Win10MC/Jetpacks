package com.win10mc.jetpacks;

import com.win10mc.jetpacks.component.ModDataComponentTypes;
import com.win10mc.jetpacks.item.ModItems;
import net.fabricmc.api.ModInitializer;
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
	}
}
