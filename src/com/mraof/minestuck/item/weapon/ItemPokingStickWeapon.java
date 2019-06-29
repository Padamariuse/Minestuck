package com.mraof.minestuck.item.weapon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class ItemPokingStickWeapon extends ItemWeapon
{
    public ItemPokingStickWeapon(int maxUses, double damageVsEntity, double weaponSpeed, int enchantability, String name)
    {
        super(maxUses, damageVsEntity, weaponSpeed, enchantability, name);
    }
    
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase player)
    {
        if(!(target instanceof EntityPlayer) && !target.world.isRemote)
        {
            target.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 140, 1));
        }
        return super.hitEntity(stack, target, player);
    }
}
