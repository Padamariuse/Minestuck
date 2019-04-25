package com.mraof.minestuck.world.lands.decorator.structure;

import com.mraof.minestuck.entity.underling.EntityLich;
import com.mraof.minestuck.entity.underling.EntityOgre;
import com.mraof.minestuck.world.lands.gen.ChunkProviderLands;
import com.mraof.minestuck.world.lands.structure.blocks.StructureBlockUtil;
import com.mraof.minestuck.world.storage.loot.MinestuckLoot;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

import java.util.Map;
import java.util.Random;

public class MushroomDungeonDecorator extends SimpleStructureDecorator
{
    
    public MushroomDungeonDecorator(Biome... biomes)
    {
        super(biomes);
    }
    
    @Override
    public float getPriority()
    {
        return 1.0F;
    }
    
    protected static Rotation getRotation(EnumFacing facing)
    {
        switch(facing)
        {
            case NORTH: return Rotation.CLOCKWISE_180;
            case WEST: return Rotation.CLOCKWISE_90;
            case EAST: return Rotation.COUNTERCLOCKWISE_90;
            default: return Rotation.NONE;
        }
    }
    
    @Override
    protected BlockPos generateStructure(World world, Random random, BlockPos pos, ChunkProviderLands provider)
    {
        
        int rotation = random.nextInt(3);
        
        final Template template = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), new ResourceLocation("minestuck:mushroom_minidungeon"));
        final PlacementSettings settings = new PlacementSettings().setRotation(getRotation(EnumFacing.getHorizontal(rotation)));
    
        pos = world.getHeight(pos);
        
        Map<BlockPos, String> datablocks = template.getDataBlocks(pos, settings);
        
        for (Map.Entry<BlockPos, String> entry : datablocks.entrySet())
        {
            BlockPos blockpos = entry.getKey();
            if ("underling".equals(entry.getValue()))
            {
                world.setBlockToAir(blockpos);
                System.out.println("" + pos);
                EntityLich lich = new EntityLich(world);
                lich.setPositionAndRotation(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ(), random.nextFloat()*360F, 0);
                lich.onInitialSpawn(null, null);
                lich.setHomePosAndDistance(blockpos, 2);
                world.spawnEntity(lich);
            }
        
            if ("lootchest".equals(entry.getValue()))
            {
                world.setBlockToAir(blockpos);
                StructureBlockUtil.placeLootChest(blockpos, world, null, EnumFacing.getHorizontal(rotation).getOpposite(), MinestuckLoot.BASIC_MEDIUM_CHEST, random);
            }
        }
        
        template.addBlocksToWorld(world, pos, settings);
        
        
        return pos;
    }
    
    @Override
    public int getCount(Random random)
    {
        return random.nextFloat() < 0.003F ? 1 : 0;
    }
    
}
