package net.fabricmc.orienteering.item;

import net.fabricmc.orienteering.block.entity.ControlBoxBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AbstractSportIdentItem extends Item {

    public AbstractSportIdentItem(Color color, Settings settings) {
        super(settings);
    }

    public static enum Color {
        RED,
        ORANGE,
        YELLOW,
        GREEN,
        BLUE,
        BLACK,
        LIGHT_BLUE,
        LIGHT_GREEN,
        WHITE,
    }

    public static void punch(ItemStack sportIdent, ControlBoxBlockEntity control) {
        System.out.println("PUNCHED CONTROL " + control.getControlCode());
        // TODO: check type of control
    }

}
