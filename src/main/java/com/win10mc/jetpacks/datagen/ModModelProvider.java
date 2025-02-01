package com.win10mc.jetpacks.datagen;

import com.win10mc.jetpacks.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class ModModelProvider extends FabricModelProvider {
	public ModModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		itemModelGenerator.registerArmor(ModItems.IRON_JETPACK);
		itemModelGenerator.registerArmor(ModItems.GOLD_JETPACK);
		itemModelGenerator.registerArmor(ModItems.DIAMOND_JETPACK);
		itemModelGenerator.registerArmor(ModItems.NETHERITE_JETPACK);
	}
}
