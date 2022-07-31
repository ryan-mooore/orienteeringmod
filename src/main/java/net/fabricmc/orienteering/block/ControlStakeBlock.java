package net.fabricmc.orienteering.block;

import net.minecraft.block.Block;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.shape.VoxelShape;

public class ControlStakeBlock extends Block {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    protected static final VoxelShape STAKE_SHAPE = Block.createCuboidShape(4, 0.0, 4, 12.0, 16.0, 12.0);

    public ControlStakeBlock(Settings settings) {
        super(settings);
        // this.setDefaultState(this.stateManager.getDefaultState().with(FACING,
        // Direction.NORTH));
    }
}
