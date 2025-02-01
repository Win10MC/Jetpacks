package com.win10mc.jetpacks.item;

import com.win10mc.jetpacks.component.ModDataComponentTypes;
import com.win10mc.jetpacks.mixin.AccessorLivingEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class JetpackItem extends ArmorItem {

	private boolean airborne = true;
	private double currentSpeed = 0;
	private boolean onGround = false;

	private double regularSpeed;
	private double sprintSpeed;

	private double ascentSpeed;
	private double descentSpeed;

	private boolean hover;
	private double sinkSpeed;

	private float UNBREAKING_LEVEL;
	private float UNBREAKING_BONUS = 1f;
	private float DAMAGE_INTERVAL = 200 * UNBREAKING_BONUS;

	public JetpackItem(RegistryEntry<?> material, Type type, Settings settings, double regularSpeed, double sprintSpeed, double ascentSpeed, double descentSpeed, boolean hover) {
		super((RegistryEntry<ArmorMaterial>) material, type, settings
				.component(ModDataComponentTypes.FLYINGTIME, 0)
				.component(ModDataComponentTypes.ENABLED, false)
		);
		this.regularSpeed = regularSpeed;
		this.sprintSpeed = sprintSpeed;
		this.ascentSpeed = ascentSpeed;
		this.descentSpeed = descentSpeed;
		this.hover = hover;
		this.sinkSpeed = descentSpeed / 2;
	}

	public void toggle(ItemStack stack) {
		boolean enabled = stack.get(ModDataComponentTypes.ENABLED);

		if (enabled) {
			stack.set(ModDataComponentTypes.ENABLED, false);
			airborne = false;
			return;
		}

		if (stack.getDamage() + 1 <= stack.getMaxDamage()) {
			stack.set(ModDataComponentTypes.ENABLED, true);
			airborne = true;
		}
	}



	public boolean engineStatus(ItemStack stack) {
		return stack.get(ModDataComponentTypes.ENABLED);
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
		if (!(entity instanceof LivingEntity player)) return;

		UNBREAKING_LEVEL = EnchantmentHelper.getLevel(world.getRegistryManager().getWrapperOrThrow(RegistryKeys.ENCHANTMENT)
				.getOrThrow(Enchantments.UNBREAKING), stack);
		UNBREAKING_BONUS = Math.max(1, UNBREAKING_LEVEL / 1.5f);
		DAMAGE_INTERVAL = 200 * UNBREAKING_BONUS;

		if (stack.get(ModDataComponentTypes.ENABLED) == null) {
			stack.set(ModDataComponentTypes.ENABLED, false);
		}
		boolean enabled = stack.get(ModDataComponentTypes.ENABLED);

		if (slot == EquipmentSlot.CHEST.getEntitySlotId()) {
			if (enabled && !player.isInCreativeMode()) {
				boolean jumping = ((AccessorLivingEntity) player).getJumping();

				if (player.isOnGround()) {
					onGround = true;
					airborne = false;
				} else {
					onGround = false;
				}

				if (airborne) {
					if (stack.get(ModDataComponentTypes.FLYINGTIME) == null) {
						stack.set(ModDataComponentTypes.FLYINGTIME, 0);
					}
					stack.set(ModDataComponentTypes.FLYINGTIME, stack.get(ModDataComponentTypes.FLYINGTIME) + 1);

					if (stack.get(ModDataComponentTypes.FLYINGTIME) >= DAMAGE_INTERVAL) {
						if (stack.getDamage() + 1 == stack.getMaxDamage()) {
							stack.setDamage(stack.getDamage() + 1);
							toggle(stack);
						} else {
							stack.setDamage(stack.getDamage() + 1);
						}
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
			}
			player.fallDistance = 0;
		} else {
			stack.set(ModDataComponentTypes.ENABLED, false);
		}
	}
}
