package net.fabricmc.orienteering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.orienteering.block.ControlBoxBlock;
import net.fabricmc.orienteering.block.ControlStakeBlock;
import net.fabricmc.orienteering.block.StartBeeperBlock;
import net.fabricmc.orienteering.block.entity.ControlBoxBlockEntity;
import net.fabricmc.orienteering.block.entity.StartBeeperBlockEntity;
import net.fabricmc.orienteering.item.AbstractSportIdentItem;
import net.fabricmc.orienteering.item.SportIdentAirItem;
import net.fabricmc.orienteering.item.SportIdentItem;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Orienteering implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("orienteering");

	// control box block
	public static final Block CONTROL_BOX_BLOCK = new ControlBoxBlock(
			FabricBlockSettings.of(Material.METAL).strength(4.0f));
	public static final BlockEntityType<ControlBoxBlockEntity> CONTROL_BOX_BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder
			.create(ControlBoxBlockEntity::new, CONTROL_BOX_BLOCK).build(null);

	// control stake block
	public static final Block CONTROL_STAKE_BLOCK = new ControlStakeBlock(
			FabricBlockSettings.of(Material.METAL).strength(4.0f));
	public static final BlockEntityType<ControlBoxBlockEntity> CCONTROL_STAKE_BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder
			.create(ControlBoxBlockEntity::new, CONTROL_STAKE_BLOCK).build(null);

	// start beeper block
	public static final Block START_BEEPER_BLOCK = new StartBeeperBlock(
			FabricBlockSettings.of(Material.METAL).strength(4.0f));
	public static final BlockEntityType<StartBeeperBlockEntity> START_BEEPER_BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder
			.create(StartBeeperBlockEntity::new, START_BEEPER_BLOCK).build();

	// sportident items
	public static final Item BLACK_SPORT_IDENT_ITEM = new SportIdentItem(AbstractSportIdentItem.Color.BLACK,
			new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item RED_SPORT_IDENT_ITEM = new SportIdentItem(AbstractSportIdentItem.Color.RED,
			new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item BLUE_SPORT_IDENT_AIR_ITEM = new SportIdentAirItem(AbstractSportIdentItem.Color.BLUE,
			new FabricItemSettings().group(ItemGroup.MISC));

	@Override
	public void onInitialize() {
		// control box block
		Registry.register(Registry.ITEM, new Identifier("orienteering", "control_box"),
				new BlockItem(CONTROL_BOX_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier("orienteering", "control_box"), CONTROL_BOX_BLOCK);
		Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("orienteering", "control_box"),
				CONTROL_BOX_BLOCK_ENTITY_TYPE);

		// control stake block
		Registry.register(Registry.ITEM, new Identifier("orienteering", "control_stake"),
				new BlockItem(CONTROL_STAKE_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier("orienteering", "control_stake"), CONTROL_STAKE_BLOCK);
		Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("orienteering", "control_stake"),
				CCONTROL_STAKE_BLOCK_ENTITY_TYPE);

		// start beeper block
		Registry.register(Registry.ITEM, new Identifier("orienteering", "start_beeper"),
				new BlockItem(START_BEEPER_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, new Identifier("orienteering", "start_beeper"), START_BEEPER_BLOCK);
		Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("orienteering", "start_beeper"),
				START_BEEPER_BLOCK_ENTITY_TYPE);

		// sportident items
		Registry.register(Registry.ITEM, new Identifier("orienteering", "black_sport_ident"), BLACK_SPORT_IDENT_ITEM);
		Registry.register(Registry.ITEM, new Identifier("orienteering", "red_sport_ident"), RED_SPORT_IDENT_ITEM);
		Registry.register(Registry.ITEM, new Identifier("orienteering", "blue_sport_ident_air"),
				BLUE_SPORT_IDENT_AIR_ITEM);

		LOGGER.info("ORIENTEERING loaded successfully.");
	}
}
