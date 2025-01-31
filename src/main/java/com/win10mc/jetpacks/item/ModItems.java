package com.win10mc.jetpacks.item;

import com.win10mc.jetpacks.Jetpacks;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item.Settings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

	public static final JetpackItem JETPACK = new JetpackItem(ArmorMaterials.IRON, ArmorItem.Type.CHESTPLATE,
			new Settings().maxCount(1).maxDamage(100),
			0.08, 0.12, 0.6, 0.4, true);

	public static void registerItems() {
		Registry.register(Registries.ITEM, Identifier.of(Jetpacks.MOD_ID, "jetpack"), JETPACK);
	}
}
