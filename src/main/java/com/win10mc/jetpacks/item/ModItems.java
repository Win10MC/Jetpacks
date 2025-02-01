package com.win10mc.jetpacks.item;

import com.win10mc.jetpacks.Jetpacks;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

	public static final JetpackItem IRON_JETPACK = new JetpackItem(ArmorMaterials.IRON, ArmorItem.Type.CHESTPLATE,
			new Settings().maxCount(1).maxDamage(50),
			0.04, 0.06, 0.6, 0.4, false);

	public static final JetpackItem GOLD_JETPACK = new JetpackItem(ArmorMaterials.GOLD, ArmorItem.Type.CHESTPLATE,
			new Settings().maxCount(1).maxDamage(35),
			0.06, 0.08, 0.6, 0.4, false);

	public static final JetpackItem DIAMOND_JETPACK = new JetpackItem(ArmorMaterials.DIAMOND, ArmorItem.Type.CHESTPLATE,
			new Settings().maxCount(1).maxDamage(100),
			0.08, 0.12, 0.6, 0.4, true);

	public static final JetpackItem NETHERITE_JETPACK = new JetpackItem(ArmorMaterials.NETHERITE, ArmorItem.Type.CHESTPLATE,
			new Settings().maxCount(1).maxDamage(200),
			0.12, 0.14, 0.6, 0.4, true);

	public static void registerItems() {
		Registry.register(Registries.ITEM, Identifier.of(Jetpacks.MOD_ID, "iron_jetpack"), IRON_JETPACK);
		Registry.register(Registries.ITEM, Identifier.of(Jetpacks.MOD_ID, "gold_jetpack"), GOLD_JETPACK);
		Registry.register(Registries.ITEM, Identifier.of(Jetpacks.MOD_ID, "diamond_jetpack"), DIAMOND_JETPACK);
		Registry.register(Registries.ITEM, Identifier.of(Jetpacks.MOD_ID, "netherite_jetpack"), NETHERITE_JETPACK);
	}
}
