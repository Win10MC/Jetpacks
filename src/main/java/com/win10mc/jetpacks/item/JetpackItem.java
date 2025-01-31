package com.win10mc.jetpacks.item;

import com.win10mc.jetpacks.mixin.AccessorLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

public class JetpackItem extends ArmorItem {

	private boolean airborne = false;
	private double currentSpeed = 0;

	private double regularSpeed;
	private double sprintSpeed;

	public JetpackItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings, double regularSpeed, double sprintSpeed) {
		super(material, type, settings);
		this.regularSpeed = regularSpeed;
		this.sprintSpeed = sprintSpeed;
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if (entity instanceof LivingEntity player) {
			System.out.println("Movement Speed: " + player.getMovementSpeed());
			if (slot == EquipmentSlot.CHEST.getEntitySlotId()) {
				boolean jumping = ((AccessorLivingEntity) player).getJumping();

				if (player.isOnGround()) {
					airborne = false;
				}

				if (jumping) {
					player.setVelocity(player.getVelocity().x, 0.6, player.getVelocity().z);
					airborne = true;
				}

				if (player.isSprinting()) {
					currentSpeed = sprintSpeed;
				} else {
					currentSpeed = regularSpeed;
				}

				if ((player.forwardSpeed != 0 || player.sidewaysSpeed != 0) && airborne) {
					double motionX = (player.getRotationVector().x * player.forwardSpeed) + (player.getRotationVector().z * player.sidewaysSpeed);
					double motionZ = (player.getRotationVector().z * player.forwardSpeed) - (player.getRotationVector().x * player.sidewaysSpeed);

					double length = Math.sqrt(motionX * motionX + motionZ * motionZ);
					if (length > 0) {
						motionX /= length;
						motionZ /= length;
					}
					player.addVelocity(motionX * currentSpeed, 0, motionZ * currentSpeed);
				}

				if (player.isSneaking() && airborne) {
					player.setVelocity(player.getVelocity().x, -0.4, player.getVelocity().z);
				}

				if (!player.isSneaking() && !jumping && airborne) {
					player.setVelocity(player.getVelocity().x, 0, player.getVelocity().z);
				}
				player.fallDistance = 0;
			}
		}
	}
}