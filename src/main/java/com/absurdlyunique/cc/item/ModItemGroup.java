package com.absurdlyunique.cc.item;

import com.absurdlyunique.cc.CC;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {

    public static final ItemGroup CC_Group = Registry.register(Registries.ITEM_GROUP, new Identifier(CC.MOD_ID, "cc"), FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ccgroup")).icon(() -> new ItemStack(ModItems.GLASS_SHARDS)).entries((displayContext, entries) -> {
        entries.add(new ItemStack(ModItems.GLASS_SHARDS));
        entries.add(new ItemStack(ModItems.METAL_DETECTOR));
    }).build());

    public static final ItemGroup Shards_Group = Registry.register(Registries.ITEM_GROUP, new Identifier(CC.MOD_ID, "shards"), FabricItemGroup.builder().displayName(Text.translatable("itemgroup.shards")).icon(() -> new ItemStack(ModItems.GLASS_SHARDS)).entries((displayContext, entries) -> {
        entries.add(new ItemStack(ModItems.GLASS_SHARDS));
    }).build());

    public static void registerModItemGroups() {
        // CC.LOGGER.info("Registering Mod Item Group for " + CC.MOD_ID);

    }
}
