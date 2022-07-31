package net.fabricmc.orienteering.item;

import net.fabricmc.orienteering.block.entity.ControlBlockBlockEntity;
import net.minecraft.item.Item;

public class AbstractSportIdentItem extends Item {

    public AbstractSportIdentItem(Settings settings) {
        super(settings);
    }

    public void punch(ControlBlockBlockEntity control) {
        System.out.println("PUNCHED CONTROL " + control.getControlCode());
    }

}
