package com.win10mc.jetpacks.item;

import com.win10mc.jetpacks.component.ModDataComponentTypes;
import com.win10mc.jetpacks.mixin.AccessorLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

public class JetpackItem extends ArmorItem {

	private boolean enabled = false;
	private boolean airborne = true;
	private double currentSpeed = 0;

	private double regularSpeed;
	private double sprintSpeed;

	private double ascentSpeed;
	private double descentSpeed;

	private boolean hover;
	private double sinkSpeed;

	private static final int DAMAGE_INTERVAL = 200;

	public JetpackItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings, double regularSpeed, double sprintSpeed, double ascentSpeed, double descentSpeed, boolean hover) {
		super(material, type, settings);
		this.regularSpeed = regularSpeed;
		this.sprintSpeed = sprintSpeed;
		this.ascentSpeed = ascentSpeed;
		this.descentSpeed = descentSpeed;
		this.hover = hover;
		this.sinkSpeed = descentSpeed / 2;
	}

	public void toggle() {
		enabled = !enabled;
	}

	public boolean engineStatus() {
		return enabled;
	}

	public static JetpackItem getEquippedJetpack(LivingEntity entity) {
		ItemStack chestStack = entity.getEquippedStack(EquipmentSlot.CHEST);
		if (chestStack.getItem() instanceof JetpackItem) {
			return (JetpackItem) chestStack.getItem();
		}
		return null;
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if (entity instanceof LivingEntity player) {
			if (slot == EquipmentSlot.CHEST.getEntitySlotId() && enabled) {
				boolean jumping = ((AccessorLivingEntity) player).getJumping();

				if (player.isOnGround()) {
					airborne = false;
				}

				if (airborne) {
					if (stack.get(ModDataComponentTypes.FLYINGTIME) != null) {
						int flightTime = stack.get(ModDataComponentTypes.FLYINGTIME);
						stack.set(ModDataComponentTypes.FLYINGTIME, flightTime + 1);
						if (flightTime >= DAMAGE_INTERVAL) {
							stack.damage(1, player, EquipmentSlot.CHEST);
							stack.set(ModDataComponentTypes.FLYINGTIME, 0);
						}
					} else {
						stack.set(ModDataComponentTypes.FLYINGTIME, 0);
					}
				}

				if (jumping) {
					player.setVelocity(player.getVelocity().x, ascentSpeed, player.getVelocity().z);
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
					player.setVelocity(player.getVelocity().x, -descentSpeed, player.getVelocity().z);
				}

				if (!player.isSneaking() && !jumping && airborne) {
					if (hover) {
						player.setVelocity(player.getVelocity().x, 0, player.getVelocity().z);
					} else {
						player.setVelocity(player.getVelocity().x, -sinkSpeed, player.getVelocity().z);
					}
				}
				player.fallDistance = 0;
			}
		}
	}
}