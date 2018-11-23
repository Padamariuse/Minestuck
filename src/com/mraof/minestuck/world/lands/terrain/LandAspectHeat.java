package com.mraof.minestuck.world.lands.terrain;

import com.mraof.minestuck.block.BlockMinestuckStone;
import com.mraof.minestuck.block.MinestuckBlocks;
import com.mraof.minestuck.entity.consort.EnumConsort;
import com.mraof.minestuck.world.biome.BiomeMinestuck;
import com.mraof.minestuck.world.lands.LandAspectRegistry;
import com.mraof.minestuck.world.lands.decorator.*;
import com.mraof.minestuck.world.lands.structure.blocks.StructureBlockRegistry;
import com.mraof.minestuck.world.lands.terrain.LandAspectSand.Variant;

import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

public class LandAspectHeat extends TerrainLandAspect 
{
	static Vec3d skyColor = new Vec3d(0.4D, 0.0D, 0.0D);
	private final Variant type;
	private final List<TerrainLandAspect> variations;
	
	public LandAspectHeat()
	{
		this(Variant.HEAT);
	}
	
	public LandAspectHeat(Variant variation)
	{
		variations = new ArrayList<>();
		type = variation;
		
		if(type == Variant.HEAT)
		{
			skyColor = new Vec3d(0.99D, 0.8D, 0.05D);
			
			variations.add(this);
			variations.add(new LandAspectHeat(Variant.HELL));
		} else
		{
			skyColor = new Vec3d(0.99D, 0.6D, 0.05D);
		}
		
	}
	@Override
	public void registerBlocks(StructureBlockRegistry registry)
	{
		if(type == Variant.HEAT) {
			registry.setBlockState("upper", Blocks.COBBLESTONE.getDefaultState());
		} else {
			registry.setBlockState("upper", Blocks.NETHERRACK.getDefaultState());
		}
		registry.setBlockState("ground", Blocks.NETHERRACK.getDefaultState());
		registry.setBlockState("ocean", Blocks.LAVA.getDefaultState());
		registry.setBlockState("structure_primary", Blocks.NETHER_BRICK.getDefaultState());
		registry.setBlockState("structure_primary_stairs", Blocks.NETHER_BRICK_STAIRS.getDefaultState());
		registry.setBlockState("structure_secondary", MinestuckBlocks.stone.getDefaultState().withProperty(BlockMinestuckStone.VARIANT, BlockMinestuckStone.BlockType.CAST_IRON));
		registry.setBlockState("structure_secondary_decorative", MinestuckBlocks.stone.getDefaultState().withProperty(BlockMinestuckStone.VARIANT, BlockMinestuckStone.BlockType.CAST_IRON_CHISELED));
		registry.setBlockState("structure_secondary_stairs", MinestuckBlocks.castIronStairs.getDefaultState());
		registry.setBlockState("fall_fluid", Blocks.WATER.getDefaultState());
		registry.setBlockState("structure_planks", Blocks.BRICK_BLOCK.getDefaultState());
		registry.setBlockState("structure_planks_slab", Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.BRICK));
		if(type == Variant.HEAT) {
			registry.setBlockState("village_path", Blocks.QUARTZ_BLOCK.getDefaultState());
		} else {
			registry.setBlockState("village_path", Blocks.RED_NETHER_BRICK.getDefaultState());
		}
		registry.setBlockState("village_fence", Blocks.NETHER_BRICK_FENCE.getDefaultState());
		registry.setBlockState("structure_wool_1", Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW));
		registry.setBlockState("structure_wool_3", Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.PURPLE));
	}
	
	@Override
	public String getPrimaryName()
	{
		return "heat";
	}

	@Override
	public String[] getNames() {
		if(type == Variant.HEAT) {
			return new String[] {"heat","flame","fire"};
		} else {
			return new String[] {"hell"};
		}
	}
	
	@Override
	public List<ILandDecorator> getDecorators()
	{
		ArrayList<ILandDecorator> list = new ArrayList<>();
		if(type == Variant.HEAT) {
			list.add(new FireFieldDecorator(7, BiomeMinestuck.mediumNormal));
			list.add(new FireFieldDecorator(10, BiomeMinestuck.mediumRough));
		} else {
			list.add(new FireFieldDecorator(15, BiomeMinestuck.mediumNormal));
			list.add(new FireFieldDecorator(25, BiomeMinestuck.mediumRough));
		}
		list.add(new OceanRundown(0.5F, 3));
		if(type == Variant.HEAT) {
			list.add(new SurfaceDecoratorVein(Blocks.SOUL_SAND.getDefaultState(), 15, 32, BiomeMinestuck.mediumRough));
			list.add(new SurfaceDecoratorVein(Blocks.SOUL_SAND.getDefaultState(), 8, 32, BiomeMinestuck.mediumNormal));
		} else {
			list.add(new SurfaceDecoratorVein(Blocks.SOUL_SAND.getDefaultState(), 30, 8, BiomeMinestuck.mediumNormal));
			list.add(new SurfaceDecoratorVein(Blocks.SOUL_SAND.getDefaultState(), 50, 8, BiomeMinestuck.mediumRough));
		}
		list.add(new SurfaceDecoratorVein(Blocks.GLOWSTONE.getDefaultState(), 5, 8, BiomeMinestuck.mediumNormal));
		
		list.add(new UndergroundDecoratorVein(Blocks.GRAVEL.getDefaultState(), 8, 33, 256));
		list.add(new UndergroundDecoratorVein(MinestuckBlocks.coalOreNetherrack.getDefaultState(), 26, 17, 128));
		list.add(new UndergroundDecoratorVein(Blocks.QUARTZ_ORE.getDefaultState(), 13, 8, 64));
		return list;
	}
	
	@Override
	public int getDayCycleMode()
	{
		return 0;
	}
	
	@Override
	public Vec3d getFogColor() 
	{
		return skyColor;
	}
	
	@Override
	public float getTemperature()
	{
		return 2.0F;
	}
	
	@Override
	public float getRainfall()
	{
		return 0.0F;
	}
	
	@Override
	public List<TerrainLandAspect> getVariations()
	{
		return variations;
	}
	
	@Override
	public TerrainLandAspect getPrimaryVariant()
	{
		return LandAspectRegistry.fromNameTerrain("heat");
	}
	
	@Override
	public EnumConsort getConsortType()
	{
		return EnumConsort.NAKAGATOR;
	}
	
	public static enum Variant
	{
		HEAT,
		HELL;
		public String getName()
		{
			return this.toString().toLowerCase();
		}
	}
}
