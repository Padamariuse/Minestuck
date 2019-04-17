package com.mraof.minestuck.item.weapon;

import com.mraof.minestuck.item.MinestuckItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class ItemBaguetteWeapon extends ItemConsumableWeapon
{
    public ItemBaguetteWeapon(int maxUses, double damageVsEntity, double weaponSpeed, int enchantability, String name, int amount, float saturation, int damageTaken) {
        super(maxUses, damageVsEntity, weaponSpeed, enchantability, name, amount, saturation, damageTaken);
    }
    
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase player) {
        ItemStack crumbs = new ItemStack(MinestuckItems.breadcrumbs, 1);
        if(!target.world.isRemote) {
            EntityItem item = new EntityItem(target.world, target.posX, target.posY, target.posZ, crumbs);
            target.world.spawnEntity(item);
        }
        return super.hitEntity(stack, target, player);
    }
    
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        Random rand = new Random();
        int num = rand.nextInt(10);
        ItemStack crumbs = new ItemStack(MinestuckItems.breadcrumbs, num);
        if(entityLiving instanceof EntityPlayer && !entityLiving.world.isRemote) {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            entityplayer.addItemStackToInventory(crumbs);
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
