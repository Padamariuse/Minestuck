package com.mraof.minestuck.item.weapon;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;

public class ItemIceWeapon extends ItemPotionWeapon
{
    public ItemIceWeapon(int maxUses, double damageVsEntity, double weaponSpeed, int enchantability, String name, PotionEffect effect) {
        super(maxUses, damageVsEntity, weaponSpeed, enchantability, name, effect);
    }
    
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase player) {
        target.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.5F, 1.0F);
        return super.hitEntity(stack, target, player);
    }
}
