package maikascarlet.frozenpizza;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FrozenPizza implements ModInitializer {
	public static final String MOD_ID = "frozen-pizza";

	public static final Item FROZEN_PIZZA = registerItem("frozen_pizza", new Item.Properties()
			.food(new FoodProperties.Builder().nutrition(8).saturationModifier(0.8f).build()));
	public static final Item TOMATO_SAUCE = registerItem("tomato_sauce", new Item.Properties());

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(output -> {
			output.accept(FROZEN_PIZZA);
			output.accept(TOMATO_SAUCE);
		});

		LOGGER.info("Frozen Pizza items initialized.");
	}

	private static Item registerItem(String path, Item.Properties properties) {
		Identifier identifier = id(path);
		Item item = new Item(properties.setId(ResourceKey.create(Registries.ITEM, identifier)));
		return Registry.register(BuiltInRegistries.ITEM, identifier, item);
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
