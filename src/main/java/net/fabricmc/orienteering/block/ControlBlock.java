package net.fabricmc.orienteering.block;

import java.util.function.Predicate;

import net.fabricmc.orienteering.Orienteering;
import net.fabricmc.orienteering.block.entity.ControlBlockBlockEntity;
import net.fabricmc.orienteering.item.AbstractSportIdentItem;
import net.fabricmc.orienteering.util.mixin.OpenControlBlockScreenAccessor;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ControlBlock extends BlockWithEntity {

    public ControlBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        // With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need
        // to change that!
        return BlockRenderType.MODEL;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state,
            BlockEntityType<T> type) {
        return checkType(type, Orienteering.CONTROL_BLOCK_ENTITY_TYPE,
                (world1, pos, state1, be) -> ControlBlockBlockEntity.tick(world1, pos, state1, be));
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
        if (world.isClient()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (isHoldingSportIdent.test(player) || isHoldingSportIdentAir.test(player)) {
                ((AbstractSportIdentItem) player.getStackInHand(player.getActiveHand()).getItem())
                        .punch((ControlBlockBlockEntity) blockEntity);
                return ActionResult.SUCCESS;
            }

            ((OpenControlBlockScreenAccessor) player).openControlBlockScreen((ControlBlockBlockEntity) blockEntity);
            return ActionResult.CONSUME_PARTIAL;
        }

        return ActionResult.PASS;

    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        ControlBlockBlockEntity controlBlockBlockEntity = new ControlBlockBlockEntity(pos, state);
        return controlBlockBlockEntity;
    }
}
