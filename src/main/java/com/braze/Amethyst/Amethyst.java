package com.braze.Amethyst;


import com.braze.Amethyst.init.BlockInit;
import com.braze.Amethyst.init.ItemInit;
import com.braze.Amethyst.worldgen.AmethystOreGen;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("amethyst")
@Mod.EventBusSubscriber(modid = Amethyst.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Amethyst
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "amethyst";
    public static Amethyst instance;
    public Amethyst() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the setup method for modloading
        modEventBus.addListener(this::setup);
        // Register the doClientStuff method for modloading
        modEventBus.addListener(this::doClientStuff);

        instance = this;
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts

    }

    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
       AmethystOreGen.generateOre();
   }

    public static class AmethystItemGroup extends ItemGroup {
        public static final AmethystItemGroup instance = new AmethystItemGroup(ItemGroup.GROUPS.length, "amethysttab");
        private AmethystItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemInit.amethyst_ingot);
        }
    }

}
