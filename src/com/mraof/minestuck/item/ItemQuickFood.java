package com.mraof.minestuck.item;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemQuickFood extends ItemFood {
    public ItemQuickFood(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        this.setCreativeTab(TabMinestuck.getInstance());
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 16;
    }
}
