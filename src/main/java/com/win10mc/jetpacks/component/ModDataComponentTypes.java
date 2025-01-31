package com.win10mc.jetpacks.component;

import com.mojang.serialization.Codec;
import com.win10mc.jetpacks.Jetpacks;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
	public static final ComponentType<Integer> FLYINGTIME =
			register("flyingtime", builder -> builder.codec(Codec.INT));

	public static final ComponentType<Boolean> ENABLED =
			register("enabled", builder -> builder.codec(Codec.BOOL));


	private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
		return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Jetpacks.MOD_ID, name),
				builderOperator.apply(ComponentType.builder()).build());
	}

	public static void registerDataComponentTypes() {
		Jetpacks.LOGGER.info("Registering Data Component Types for " + Jetpacks.MOD_ID);
	}
}