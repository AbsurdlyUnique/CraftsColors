package com.absurdlyunique.cc.item;

import com.absurdlyunique.cc.CC;
import com.absurdlyunique.cc.block.Modblocks;
import com.absurdlyunique.cc.item.custom.MetalDetectorrItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item GLASS_SHARDS = registerItem("glass_shards",
            new Item(new Item.Settings()));
    public static final Item METAL_DETECTOR = registerItem("metal_detector",
            new MetalDetectorrItem(new Item.Settings().maxDamage(256).maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(CC.MOD_ID, name), item);
    }

    private static void itemGroupIngredients(FabricItemGroupEntries entries) {
        entries.add(GLASS_SHARDS);

        entries.add(Modblocks.PINK_GARNET_BLOCK);
        entries.add(Modblocks.RAW_PINK_GARNET_BLOCK);
    }

    public static void registerModItems() {
        CC.LOGGER.info("Registering Mod Items for " + CC.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::itemGroupIngredients);
    }
}
