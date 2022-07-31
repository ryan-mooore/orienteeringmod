package net.fabricmc.orienteering.block.entity;

import net.fabricmc.orienteering.Orienteering;
import net.fabricmc.orienteering.block.ControlBoxBlock;
import net.fabricmc.orienteering.item.AbstractSportIdentItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ControlBoxBlockEntity extends BlockEntity {

    private int controlCode = 30;
    private static final int airRange = 2;
    private Type controlType = Type.CONTROL;

    public ControlBoxBlockEntity(BlockPos pos, BlockState state) {
        super(Orienteering.CONTROL_BOX_BLOCK_ENTITY_TYPE, pos, state);
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

    public static void tick(World world, BlockPos pos, BlockState state, ControlBoxBlockEntity blockEntity) {
        PlayerEntity closestPlayer = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), airRange,
                ControlBoxBlock.isHoldingSportIdentAir);
        if (closestPlayer != null) {
            ItemStack activeItemStack = closestPlayer.getStackInHand(closestPlayer.getActiveHand());
            if (activeItemStack.getItem() instanceof AbstractSportIdentItem) {
                AbstractSportIdentItem.punch(activeItemStack, blockEntity);
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
