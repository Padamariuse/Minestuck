package com.mraof.minestuck.item.weapon;

import com.mraof.minestuck.entity.underling.EntityUnderling;
import com.mraof.minestuck.item.MinestuckItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCandyCaneConsumableWeapon extends ItemConsumableWeapon {
    public ItemCandyCaneConsumableWeapon(int maxUses, double damageVsEntity, double weaponSpeed, int enchantability, String name, int amount, float saturation) {
        super(maxUses, damageVsEntity, weaponSpeed, enchantability, name, amount, saturation);
    }
    
    @Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase player)
    {
        if(target instanceof EntityUnderling)
        {
            ((EntityUnderling) target).dropCandy = true;
        }
        return super.hitEntity(itemStack, target, player);
    }
    
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        stack.damageItem(999, entityLiving);
        if(entityLiving instanceof EntityPlayer) {
            ((EntityPlayer) entityLiving).addItemStackToInventory(new ItemStack(MinestuckItems.sharpCandyCane));
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
