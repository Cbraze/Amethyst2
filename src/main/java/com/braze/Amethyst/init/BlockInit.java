package com.braze.Amethyst.init;

import com.braze.Amethyst.Amethyst;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Amethyst.MOD_ID)
@Mod.EventBusSubscriber(modid = Amethyst.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit {

    public static final Block amethyst_ore = null;
    public static final Block amethyst_block = null;

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new Block(Block.Properties.create(Material.IRON)
                .hardnessAndResistance(5, 6)
                .sound(SoundType.STONE)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(3)).setRegistryName("amethyst_ore"));

        event.getRegistry().register(new Block(Block.Properties.create(Material.IRON)
                .hardnessAndResistance(5, 6)
                .sound(SoundType.METAL)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(3)).setRegistryName("amethyst_block"));

    }

    @SubscribeEvent
    public static void registerBlockItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new BlockItem(amethyst_ore, new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_ore"));
        event.getRegistry().register(new BlockItem(amethyst_block, new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_block"));

    }
}
