package net.fabricmc.orienteering.block.entity;

import net.fabricmc.orienteering.Orienteering;
import net.fabricmc.orienteering.block.ControlBlock;
import net.fabricmc.orienteering.item.AbstractSportIdentItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ControlBlockBlockEntity extends BlockEntity {

    private int controlCode = 30;
    private static final int airRange = 2;
    private Type controlType = Type.CONTROL;

    public ControlBlockBlockEntity(BlockPos pos, BlockState state) {
        super(Orienteering.CONTROL_BLOCK_ENTITY_TYPE, pos, state);
    }

    public int getControlCode() {
        return this.controlCode;
    }

    public void setControlCode(int controlCode) {
        this.controlCode = controlCode;
        this.markDirty();
    }

    public Type getControlType() {
        return this.controlType;
    }

    public void setControlType(Type controlType) {
        this.controlType = controlType;
        this.markDirty();
    }

    public static void tick(World world, BlockPos pos, BlockState state, ControlBlockBlockEntity blockEntity) {
        PlayerEntity closestPlayer = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), airRange,
                ControlBlock.isHoldingSportIdentAir);
        if (closestPlayer != null) {
            Item activeItem = closestPlayer.getStackInHand(closestPlayer.getActiveHand()).getItem();
            if (activeItem instanceof AbstractSportIdentItem) {
                ((AbstractSportIdentItem) activeItem).punch(blockEntity);
            }
        }
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.putInt("code", controlCode);
        nbt.putString("type", controlType.name());

        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        this.controlCode = nbt.getInt("code");
        this.controlType = Type.valueOf(nbt.getString("type"));
    };

    public static enum Type {
        CONTROL,
        START,
        FINISH,
        CLEAR,
        CHECK
    }

}
