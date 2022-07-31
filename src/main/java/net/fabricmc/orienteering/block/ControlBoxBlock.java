package net.fabricmc.orienteering.block;

import java.util.function.Predicate;

import net.fabricmc.orienteering.Orienteering;
import net.fabricmc.orienteering.block.entity.ControlBoxBlockEntity;
import net.fabricmc.orienteering.item.AbstractSportIdentItem;
import net.fabricmc.orienteering.util.mixin.OpenControlBlockScreenAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.WallMountedBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

public class ControlBoxBlock extends BlockWithEntity {
    public static final DirectionProperty FACING = WallMountedBlock.FACING;
    protected static final VoxelShape BOX_SHAPE = Block.createCuboidShape(6, 4, 4, 11, 12.0, 12.0);

    public ControlBoxBlock(Settings settings) {
        super(settings);
        // this.setDefaultState(this.stateManager.getDefaultState().with(FACING,
        // Direction.NORTH));
    }

    @Override
    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState) state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state,
            BlockEntityType<T> type) {
        return checkType(type, Orienteering.CONTROL_BOX_BLOCK_ENTITY_TYPE,
                (world1, pos, state1, be) -> ControlBoxBlockEntity.tick(world1, pos, state1, be));
    }

    private static boolean sportIdentPredicateBuilder(Entity entity, Item sportIdentItemType) {
        if (entity instanceof PlayerEntity) {
            ItemStack itemStack = ((PlayerEntity) entity).getStackInHand(((PlayerEntity) entity).getActiveHand());
            if (itemStack.isOf(sportIdentItemType)) {
                return true;
            }
        }
        return false;
    }

    public static Predicate<Entity> isHoldingSportIdent = entity -> sportIdentPredicateBuilder(entity,
            Orienteering.SPORT_IDENT_ITEM);
    public static Predicate<Entity> isHoldingSportIdentAir = entity -> sportIdentPredicateBuilder(entity,
            Orienteering.SPORT_IDENT_AIR_ITEM);

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (isHoldingSportIdent.test(player) || isHoldingSportIdentAir.test(player)) {
            ItemStack itemStack = player.getStackInHand(player.getActiveHand());
            AbstractSportIdentItem.punch(itemStack, (ControlBoxBlockEntity) blockEntity);
            return ActionResult.SUCCESS;
        }

        ((OpenControlBlockScreenAccessor) player).openControlBlockScreen((ControlBoxBlockEntity) blockEntity);
        return ActionResult.CONSUME_PARTIAL;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        ControlBoxBlockEntity controlBlockBlockEntity = new ControlBoxBlockEntity(pos, state);
        return controlBlockBlockEntity;
    }
}
