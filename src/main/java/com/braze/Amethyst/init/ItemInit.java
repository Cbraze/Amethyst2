package com.braze.Amethyst.init;


import com.braze.Amethyst.Amethyst;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid= Amethyst.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Amethyst.MOD_ID)
public class ItemInit {

    public static final Item amethyst_ingot = null;
    public static final Item amethyst_sword = null;
    public static final Item amethyst_pickaxe = null;
    //public static final Item special_item = null;
    public static final Item amethyst_helmet = null;
    public static final Item amethyst_chestplate = null;
    public static final Item amethyst_leggings = null;
    public static final Item amethyst_boots = null;



    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        //event.getRegistry().register(new SpecialItem(new Item.Properties().group(Tutorial.TutorialItemGroup.instance)).setRegistryName("special_item"));

        event.getRegistry().register(new Item(new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_ingot"));
        event.getRegistry().register(new SwordItem(ModItemTier.EXAMPLE, 7, 4.0f, new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_sword"));
        event.getRegistry().register(new PickaxeItem(ModItemTier.EXAMPLE, 4, 4.0f, new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_pickaxe"));
        event.getRegistry().register(new AxeItem(ModItemTier.EXAMPLE, 4, 4.0f, new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_axe"));
        event.getRegistry().register(new ShovelItem(ModItemTier.EXAMPLE, 4, 4.0f, new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_shovel"));
        event.getRegistry().register(new HoeItem(ModItemTier.EXAMPLE, 4.0f, new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_hoe"));

        event.getRegistry().register(new ArmorItem(ModArmorMaterial.AMETHYST, EquipmentSlotType.HEAD, new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_helmet.json"));
        event.getRegistry().register(new ArmorItem(ModArmorMaterial.AMETHYST, EquipmentSlotType.CHEST, new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_chestplate.json"));
        event.getRegistry().register(new ArmorItem(ModArmorMaterial.AMETHYST, EquipmentSlotType.LEGS, new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_leggings.json"));
        event.getRegistry().register(new ArmorItem(ModArmorMaterial.AMETHYST, EquipmentSlotType.FEET, new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_boots.json"));

    }


    public enum ModItemTier implements IItemTier {
        EXAMPLE(5, 3000, 15, 7.0f, 300, () -> {
            return Ingredient.fromItems(ItemInit.amethyst_sword);
        });
        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final LazyValue<Ingredient> repairMateriel;

        private ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairMateriel = new LazyValue<> (repairMaterial);
        }

        @Override
        public int getMaxUses() {
            return this.maxUses;
        }

        @Override
        public float getEfficiency() {
            return this.efficiency;
        }

        @Override
        public float getAttackDamage() {
            return this.attackDamage;
        }

        @Override
        public int getHarvestLevel() {
            return this.harvestLevel;
        }

        @Override
        public int getEnchantability() {
            return this.enchantability;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return this.repairMateriel.getValue();
        }
    }
    public enum ModArmorMaterial implements IArmorMaterial {
        AMETHYST(Amethyst.MOD_ID + ":amethyst", 60, new int[]{7, 9, 11, 7}, 420, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 6.9f, () -> {
            return Ingredient.fromItems(ItemInit.amethyst_ingot);
        });

        private static final int[] MAX_DAMAGE_ARRAY = new int[]{16, 16, 16, 16};
        private final String name;
        private final int maxDamageFactor;
        private final int[] damageReductionAmountArray;
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final float toughness;
        private final LazyValue<Ingredient> repairMaterial;

        private ModArmorMaterial(String nameIn, int MaxDamageFactorIn, int[] damageReductionAmountIn, int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn, Supplier<Ingredient> repairMaterialIn ) {
            this.name = nameIn;
            this.maxDamageFactor = MaxDamageFactorIn;
            this.damageReductionAmountArray = damageReductionAmountIn;
            this.enchantability = enchantabilityIn;
            this.soundEvent = soundEventIn;
            this.toughness = toughnessIn;
            this.repairMaterial = new LazyValue<>(repairMaterialIn);
        }

        @Override
        public int getDurability(EquipmentSlotType slotIn) {
            return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
        }

        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotIn) {
            return this.damageReductionAmountArray[slotIn.getIndex()];
        }

        @Override
        public int getEnchantability() {
            return this.enchantability;
        }

        @Override
        public SoundEvent getSoundEvent() {
            return this.soundEvent;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return this.repairMaterial.getValue();
        }

        @OnlyIn(Dist.CLIENT)
        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public float getToughness() {
            return this.toughness;
        }
    }
}
