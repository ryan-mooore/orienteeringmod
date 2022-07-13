package net.fabricmc.orienteering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.orienteering.block.ControlBlock;
import net.fabricmc.orienteering.block.entity.ControlBlockBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Orienteering implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("orienteering");

	public static final Block CONTROL_BLOCK = new ControlBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f));
	public static final BlockEntityType<ControlBlockBlockEntity> CONTROL_BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder
			.create(ControlBlockBlockEntity::new, CONTROL_BLOCK).build(null);
	public static final Item BLUE_SPORT_IDENT_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		Registry.register(Registry.BLOCK, new Identifier("orienteering", "control"), CONTROL_BLOCK);
		Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("orienteering", "control"),
				CONTROL_BLOCK_ENTITY_TYPE);
		Registry.register(Registry.ITEM, new Identifier("orienteering", "control"),
				new BlockItem(CONTROL_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.ITEM, new Identifier("orienteering", "blue_sport_ident"), BLUE_SPORT_IDENT_ITEM);

		LOGGER.info("Hello Fabric world!");
	}
}
