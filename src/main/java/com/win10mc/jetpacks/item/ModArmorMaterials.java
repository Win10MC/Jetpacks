package com.win10mc.jetpacks.item;

import com.win10mc.jetpacks.Jetpacks;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {

	public static final RegistryEntry<ArmorMaterial> IRON_JETPACK_ARMOR_MATERIAL = registerArmorMaterial("iron_jetpack",
			() -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
				map.put(ArmorItem.Type.HELMET, 2);
				map.put(ArmorItem.Type.CHESTPLATE, 6);
				map.put(ArmorItem.Type.LEGGINGS, 5);
				map.put(ArmorItem.Type.BOOTS, 2);
				map.put(ArmorItem.Type.BODY, 5);
			}), 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, () -> Ingredient.ofItems(Items.IRON_INGOT),
					List.of(new ArmorMaterial.Layer(Identifier.of(Jetpacks.MOD_ID, "iron_jetpack"))), 0, 0));

	public static final RegistryEntry<ArmorMaterial> GOLD_JETPACK_ARMOR_MATERIAL = registerArmorMaterial("gold_jetpack",
			() -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
				map.put(ArmorItem.Type.HELMET, 2);
				map.put(ArmorItem.Type.CHESTPLATE, 5);
				map.put(ArmorItem.Type.LEGGINGS, 3);
				map.put(ArmorItem.Type.BOOTS, 1);
				map.put(ArmorItem.Type.BODY, 7);
			}), 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, () -> Ingredient.ofItems(Items.GOLD_INGOT),
					List.of(new ArmorMaterial.Layer(Identifier.of(Jetpacks.MOD_ID, "gold_jetpack"))), 0, 0));

	public static final RegistryEntry<ArmorMaterial> DIAMOND_JETPACK_ARMOR_MATERIAL = registerArmorMaterial("diamond_jetpack",
			() -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
				map.put(ArmorItem.Type.HELMET, 3);
				map.put(ArmorItem.Type.CHESTPLATE, 8);
				map.put(ArmorItem.Type.LEGGINGS, 6);
				map.put(ArmorItem.Type.BOOTS, 3);
				map.put(ArmorItem.Type.BODY, 11);
			}), 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, () -> Ingredient.ofItems(Items.DIAMOND),
					List.of(new ArmorMaterial.Layer(Identifier.of(Jetpacks.MOD_ID, "diamond_jetpack"))), 2, 0));

	public static final RegistryEntry<ArmorMaterial> NETHERITE_JETPACK_ARMOR_MATERIAL = registerArmorMaterial("netherite_jetpack",
			() -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
				map.put(ArmorItem.Type.HELMET, 3);
				map.put(ArmorItem.Type.CHESTPLATE, 8);
				map.put(ArmorItem.Type.LEGGINGS, 6);
				map.put(ArmorItem.Type.BOOTS, 3);
				map.put(ArmorItem.Type.BODY, 11);
			}), 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, () -> Ingredient.ofItems(Items.NETHERITE_INGOT),
					List.of(new ArmorMaterial.Layer(Identifier.of(Jetpacks.MOD_ID, "netherite_jetpack"))), 3, 0.1F));

	public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String name, Supplier<ArmorMaterial> material) {
		return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(Jetpacks.MOD_ID, name), material.get());
	}

}
