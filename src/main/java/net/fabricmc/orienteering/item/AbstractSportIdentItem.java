package net.fabricmc.orienteering.item;

import net.fabricmc.orienteering.block.entity.ControlBoxBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AbstractSportIdentItem extends Item {

    public AbstractSportIdentItem(Settings settings) {
        super(settings);
    }

    public static void punch(ItemStack sportIdent, ControlBoxBlockEntity control) {
        System.out.println("PUNCHED CONTROL " + control.getControlCode());
        // TODO: check type of control
    }

}
