package com.mraof.minestuck.item;

import com.mraof.minestuck.block.MinestuckBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabMinestuck extends CreativeTabs
{
	private ItemStack icon;
	
	public enum EnumMinestuckTab
	{
		main,
		weapons,
		lands
	}
	
	/**
	 * Deprecated. Use "getInstance()" instead.
	 */
	@Deprecated public static final TabMinestuck instance = new TabMinestuck("tabMinestuck");
	private static final TabMinestuck weapons = new TabMinestuck("tabMinestuck", new ItemStack(MinestuckItems.zillyhooHammer));
	private static final TabMinestuck lands = new TabMinestuck("tabMinestuck", new ItemStack(MinestuckBlocks.glowingMushroom));
	
	public static CreativeTabs getInstance(EnumMinestuckTab type) {
		CreativeTabs out;
		switch(type)
		{
			case main:
				out = instance;
				break;
			case weapons:
				out = weapons;
				break;
			case lands:
				out = lands;
				break;
			default:
				out = instance;
				break;
		}
		return out;
	}
	
	public static CreativeTabs getInstance() {
		return instance;
	}
	
	private TabMinestuck(String label)
	{
		this(label, new ItemStack(MinestuckItems.disk));
	}
	
	private TabMinestuck(String label, ItemStack iconItem)
	{
		super(label);
		icon = iconItem.copy();
	}
	
	@Override
	public ItemStack getTabIconItem()
	{
		return icon;
	}
}
