package com.win10mc.jetpacks.network;

import com.win10mc.jetpacks.Jetpacks;
import com.win10mc.jetpacks.item.JetpackItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.util.math.BlockPos;

public record EnableJetpackPacket() implements CustomPayload {
	public static final EnableJetpackPacket instance = new EnableJetpackPacket();

	public static final CustomPayload.Id<EnableJetpackPacket> ID = new CustomPayload.Id<>(Identifier.of(Jetpacks.MOD_ID, "jetpack_enable"));
	public static final PacketCodec<Object, EnableJetpackPacket> CODEC = PacketCodec.unit(instance);

	@Override
	public CustomPayload.Id<? extends CustomPayload> getId() {
		return ID;
	}
}