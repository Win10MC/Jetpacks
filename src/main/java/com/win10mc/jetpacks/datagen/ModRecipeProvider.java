package com.win10mc.jetpacks.datagen;

import com.win10mc.jetpacks.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
	public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	private void jetpackRecipe(RecipeExporter exporter, ItemConvertible item, ItemConvertible material, ItemConvertible chestplate) {
		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, item)
				.pattern(" M ")
				.pattern("MAM")
				.pattern(" M ")
				.input('M', material)
				.input('A', chestplate)
				.criterion("has_material", conditionsFromItem(material))
				.criterion("has_material", conditionsFromItem(chestplate))
				.offerTo(exporter);
	}

	@Override
	public void generate(RecipeExporter recipeExporter) {
		jetpackRecipe(recipeExporter, ModItems.IRON_JETPACK, Items.ELYTRA, Items.IRON_CHESTPLATE);
		jetpackRecipe(recipeExporter, ModItems.GOLD_JETPACK, Items.ELYTRA, Items.GOLDEN_CHESTPLATE);
		jetpackRecipe(recipeExporter, ModItems.DIAMOND_JETPACK, Items.ELYTRA, Items.DIAMOND_CHESTPLATE);
		jetpackRecipe(recipeExporter, ModItems.NETHERITE_JETPACK, Items.ELYTRA, Items.NETHERITE_CHESTPLATE);
		offerNetheriteUpgradeRecipe(recipeExporter, ModItems.DIAMOND_JETPACK, RecipeCategory.TOOLS, ModItems.NETHERITE_JETPACK);
	}
}
