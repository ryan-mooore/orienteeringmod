package net.fabricmc.orienteering.block;

import net.fabricmc.orienteering.block.entity.StartBeeperBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class StartBeeperBlock extends BlockWithEntity {

    public StartBeeperBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        StartBeeperBlockEntity startBeeperBlockEntity = new StartBeeperBlockEntity(pos, state);
        return startBeeperBlockEntity;
    }

}
