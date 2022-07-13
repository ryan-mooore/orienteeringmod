package net.fabricmc.orienteering.block.entity;

import net.fabricmc.orienteering.Orienteering;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class ControlBlockBlockEntity extends BlockEntity {

    private int controlCode = 30;
    private Type controlType = Type.CONTROL;
    private Boolean airIsOn = true;

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

    public void setAirIsOn(Boolean value) {
        this.airIsOn = value;
        this.markDirty();
    }

    public Boolean getIsAirOn() {
        return this.airIsOn;
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
