package com.win10mc.jetpacks.datagen;

import com.win10mc.jetpacks.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
	public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
		super(output, completableFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
		getOrCreateTagBuilder(ItemTags.ARMOR_ENCHANTABLE)
				.add(ModItems.IRON_JETPACK)
				.add(ModItems.GOLD_JETPACK)
				.add(ModItems.DIAMOND_JETPACK)
				.add(ModItems.NETHERITE_JETPACK);
	}
}
