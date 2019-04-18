package com.mraof.minestuck.item.weapon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public class ItemNoisyWeapon extends ItemWeapon
{
    private final SoundEvent sound;
    
    public ItemNoisyWeapon(int maxUses, double damageVsEntity, double weaponSpeed, int enchantability, String name, SoundEvent noise) {
        super(maxUses, damageVsEntity, weaponSpeed, enchantability, name);
        this.sound = noise;
    }
    
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase player) {
        target.playSound(sound, 1.0F, 1.0F);
        return super.hitEntity(stack, target, player);
    }
}
