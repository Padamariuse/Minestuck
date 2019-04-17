package com.mraof.minestuck.block;

import com.mraof.minestuck.item.TabMinestuck;
import net.minecraft.block.BlockCake;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class BlockCustomCake extends BlockCake
{
	public BlockCustomCake()
	{
		setHardness(0.5F);
		setSoundType(SoundType.CLOTH);
		disableStats();
		setCreativeTab(TabMinestuck.getInstance(TabMinestuck.EnumMinestuckTab.lands));
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, this.damageDropped(state));
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (!worldIn.isRemote)
		{
			return this.eatCake(worldIn, pos, state, playerIn);
		}
		else
		{
			ItemStack itemstack = playerIn.getHeldItem(hand);
			return this.eatCake(worldIn, pos, state, playerIn) || itemstack.isEmpty();
		}
	}
	
	public boolean eatCake(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		if (!player.canEat(false))
		{
			return false;
		}
		else
		{
			player.addStat(StatList.CAKE_SLICES_EATEN);
			applyEffects(worldIn, pos, state, player);
			int i = state.getValue(BITES);
			
			if (i < 6)
			{
				worldIn.setBlockState(pos, state.withProperty(BITES, i + 1), 3);
			}
			else
			{
				worldIn.setBlockToAir(pos);
			}
			
			return true;
		}
	}
	
	protected abstract void applyEffects(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player);
}
