package com.mraof.minestuck.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemMusicBox extends Item
{
    public ItemMusicBox()
    {
        super();
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        if(playerIn.getHeldItemOffhand() == new ItemStack(MinestuckItems.musicBoxKey)) {
        
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
