package net.fabricmc.orienteering.block;

import net.fabricmc.orienteering.block.entity.ControlBlockBlockEntity;
import net.fabricmc.orienteering.util.mixin.OpenControlBlockScreenAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ControlBlock extends Block implements BlockEntityProvider {

    public ControlBlock(
            Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState());
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof ControlBlockBlockEntity) {
            ((OpenControlBlockScreenAccessor) player).openControlBlockScreen((ControlBlockBlockEntity) blockEntity);
            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        ControlBlockBlockEntity controlBlockBlockEntity = new ControlBlockBlockEntity(pos, state);
        return controlBlockBlockEntity;
    }
}
