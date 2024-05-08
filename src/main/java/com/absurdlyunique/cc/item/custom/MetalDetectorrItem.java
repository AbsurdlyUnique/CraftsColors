package com.absurdlyunique.cc.item.custom;

import com.absurdlyunique.cc.utils.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class MetalDetectorrItem extends Item {
    public MetalDetectorrItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient) {
            // on the server
            BlockPos position_Clicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i <= position_Clicked.getY() + 64; i++) {
                BlockState blockState = context.getWorld().getBlockState(position_Clicked.down(i));
                Block block = blockState.getBlock();
                if(isValueableBlock(blockState)) {
                    outputValueableCoordiantes(position_Clicked.down(i), player);
                    outputIfLavaIsPresent(player, position_Clicked.down(i));
                    foundBlock = true;

                    break;
                }
            }

            if(!foundBlock) {
                player.sendMessage(Text.translatable("item.cc.metal_detector.no_valueables"), false);
            }
            context.getStack().damage(1, player, EquipmentSlot.MAINHAND);

        } else {
            // on the client
        }

        return super.useOnBlock(context);
    }

    private void outputValueableCoordiantes(BlockPos blockPos, PlayerEntity player) {
        player.sendMessage(Text.literal("Found a valuable block at: " + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ()), false);
    }

    private void outputIfLavaIsPresent(PlayerEntity player, BlockPos down) {
        if(isLavaBeneath(player.getWorld().getBlockState(down.down()))) {
            player.sendMessage(Text.literal("Lava is beneath the valuable block"), false);
        }
    }

    private boolean isValueableBlock(BlockState blockstate) {
        return blockstate.isIn(ModTags.Blocks.METAL_DETECTOR_DETECTABLE_BLOCKS);
    }


    // create a function which determines if there is lava anywhere beneath
    private boolean isLavaBeneath(BlockState blockState) {
        return blockState.getBlock() == Blocks.LAVA;
    }
}
