package net.fabricmc.orienteering.block.entity;

import net.fabricmc.orienteering.Orienteering;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class StartBeeperBlockEntity extends BlockEntity {

    public StartBeeperBlockEntity(BlockPos pos, BlockState state) {
        super(Orienteering.START_BEEPER_BLOCK_ENTITY_TYPE, pos, state);
    }

}
