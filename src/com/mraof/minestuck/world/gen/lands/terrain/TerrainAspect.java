package com.mraof.minestuck.world.gen.lands.terrain;

import java.util.ArrayList;
import java.util.List;

import com.mraof.minestuck.world.gen.lands.ILandAspect;
import com.mraof.minestuck.world.gen.lands.decorator.ILandDecorator;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;

public abstract class TerrainAspect implements ILandAspect
{
		/**
		 * Returns the blocks that can possibly be use in the land's underground blocks.
		 * @return
		 */
		public abstract IBlockState[] getSurfaceBlocks();
		
		/**
		 * Returns the blocks that can possibly be use in the land's topmost layer of blocks.
		 * @return
		 */
		public abstract IBlockState[] getUpperBlocks();
		
		/**
		 * Unused currently. Will be used to generate the land's terrain.
		 * @return
		 */
		public abstract double[] generateTerrainMap();
		
		/**
		 * Returns the block that is a part of the land's ocean.
		 * @return
		 */
		public IBlockState getOceanBlock()
		{
			return Blocks.water.getDefaultState();
		}
		
		public IBlockState getRiverBlock()
		{
			return getOceanBlock();
		}
		
		/**
		 * Returns a list of possible worldgen structures a land can use.
		 * @return
		 */
		public abstract List<ILandDecorator> getOptionalDecorators();
		
		public abstract List<ILandDecorator> getRequiredDecorators();
		
		/**
		 *  Returns a integer representing how they land's day cycle will proceed.
		 *  
		 *  0 = Normal day cycle;
		 *  1 = Always day;
		 *  2 = Always night.
		 */
		public abstract int getDayCycleMode();

		public abstract Vec3 getFogColor();
	
	public IBlockState[] getStructureBlocks()
	{
		return getUpperBlocks();
	}
	
	public int getWeatherType()
	{
		return -1;
	}
	
	@Override
	public List<ILandAspect> getVariations()
	{
		ArrayList<ILandAspect> list = new ArrayList<ILandAspect>();
		list.add(this);
		return list;
	}
	
	@Override
	public ILandAspect getPrimaryVariant()
	{
		return this;
	}
	
}
