package com.mraof.minestuck.world.lands.decorator;

import java.util.Random;

import com.mraof.minestuck.block.MinestuckBlocks;
import com.mraof.minestuck.world.lands.gen.ChunkProviderLands;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LightningRodDecorator extends SingleBlockDecorator {
	public IBlockState block;
	public LightningRodDecorator(Block block) {
		this.block = block.getDefaultState();
	}
	
	@Override
	public IBlockState pickBlock(Random random) {
		return block;
	}

	@Override
	public boolean canPlace(BlockPos pos, World world, ChunkProviderLands provider) {
		return pos == provider.getGroundBlock();
	}
	
	@Override
	public int getCount(Random random) {
		return 1;
	}

}
