package com.win10mc.jetpacks;

import com.win10mc.jetpacks.item.ModItems;
import net.fabricmc.api.ModInitializer;

public class Jetpacks implements ModInitializer {

	public static String MOD_ID = "jetpacks";

	@Override
	public void onInitialize() {
		ModItems.registerItems();
	}
}
