package com.win10mc.jetpacks;

import com.win10mc.jetpacks.item.JetpackItem;
import com.win10mc.jetpacks.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModCategories {
	public static final ItemGroup ITEMS_GROUP = Registry.register(
			Registries.ITEM_GROUP, Identifier.of(Jetpacks.MOD_ID, "items"),
			FabricItemGroup.builder()
					.icon(() -> new ItemStack(ModItems.IRON_JETPACK))
					.displayName(Text.translatable("itemgroup.jetpacks.items"))
					.entries(((displayContext, entries) -> {
						entries.add(new ItemStack(ModItems.IRON_JETPACK));
						entries.add(new ItemStack(ModItems.GOLD_JETPACK));
						entries.add(new ItemStack(ModItems.DIAMOND_JETPACK));
						entries.add(new ItemStack(ModItems.NETHERITE_JETPACK));
					}))
					.build()
	);

	public static void register() {
		Jetpacks.LOGGER.info("Registering Item Groups for " + Jetpacks.MOD_ID);
	}
}