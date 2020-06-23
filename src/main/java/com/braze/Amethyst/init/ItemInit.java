package com.braze.Amethyst.init;


import com.braze.Amethyst.Amethyst;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.Foods;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShootableItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.UseAction;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.LazyValue;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.Predicate;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid= Amethyst.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Amethyst.MOD_ID)
public class ItemInit {

    public static final Item amethyst_ingot = null;
    public static final Item amethyst_sword = null;
    public static final Item amethyst_pickaxe = null;
    public static final Item special_item = null;
    public static final Item amethyst_helmet = null;
    public static final Item amethyst_chestplate = null;
    public static final Item amethyst_leggings = null;
    public static final Item amethyst_boots = null;
    public static final Item amethyst_bow = null;
    public static Object AmethystBow = amethyst_bow;


    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        //event.getRegistry().register(new SpecialItem(new Item.Properties().group(Tutorial.TutorialItemGroup.instance)).setRegistryName("special_item"));
        event.getRegistry().register(new Item(new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_ingot"));
        event.getRegistry().register(new SwordItem(ModItemTier.TOOL, 7, 4.0f, new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_sword"));
        event.getRegistry().register(new PickaxeItem(ModItemTier.TOOL, 4, 4.0f, new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_pickaxe"));
        event.getRegistry().register(new AxeItem(ModItemTier.TOOL, 4, 4.0f, new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_axe"));
        event.getRegistry().register(new ShovelItem(ModItemTier.TOOL, 4, 4.0f, new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_shovel"));
        event.getRegistry().register(new HoeItem(ModItemTier.TOOL, 4.0f, new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_hoe"));

        event.getRegistry().register(new ArmorItem(ModArmorMaterial.ARMOR, EquipmentSlotType.HEAD, new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_helmet"));
        event.getRegistry().register(new ArmorItem(ModArmorMaterial.ARMOR, EquipmentSlotType.CHEST, new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_chestplate"));
        event.getRegistry().register(new ArmorItem(ModArmorMaterial.ARMOR, EquipmentSlotType.LEGS, new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_leggings"));
        event.getRegistry().register(new ArmorItem(ModArmorMaterial.ARMOR, EquipmentSlotType.FEET, new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_boots"));

        event.getRegistry().register(new AmethystBow(ModWeaponTier.BOW, new Item.Properties().group(Amethyst.AmethystItemGroup.instance)).setRegistryName("amethyst_bow"));


    }

    public enum ModWeaponTier implements IItemTier {
        BOW( 3000, 9.0f, 35, () -> {
            return Ingredient.fromItems(ItemInit.amethyst_ingot);
        });

        private final int maxUses;
        private final float attackDamage;
        private final int enchantability;
        private final LazyValue<Ingredient> repairMateriel;

        private ModWeaponTier( int maxUses, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
            this.maxUses = maxUses;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairMateriel = new LazyValue<>(repairMaterial);
        }

        @Override
        public int getMaxUses() {
            return this.maxUses;
        }

        @Override
        public float getEfficiency() {
            return 0;
        }

        @Override
        public float getAttackDamage() {
            return this.attackDamage;
        }

        @Override
        public int getHarvestLevel() {
            return 0;
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

    public enum ModItemTier implements IItemTier {
        TOOL(5, 3000, 15, 7.0f, 35, () -> {
            return Ingredient.fromItems(ItemInit.amethyst_ingot);
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
        ARMOR(Amethyst.MOD_ID + ":amethyst", 60, new int[]{7, 9, 11, 7}, 35, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 6.9f, () -> {
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

    public static class AmethystBow extends ShootableItem {
        public AmethystBow(ModWeaponTier BOW, Properties builder) {
            super(builder);
            this.addPropertyOverride(new ResourceLocation("pull"), (p_210310_0_, p_210310_1_, p_210310_2_) -> {
                if (p_210310_2_ == null) {
                    return 0.0F;
                } else {
                    return !(p_210310_2_.getActiveItemStack().getItem() instanceof net.minecraft.item.BowItem) ? 0.0F : (float) (p_210310_0_.getUseDuration() - p_210310_2_.getItemInUseCount()) / 20.0F;
                }
            });
            this.addPropertyOverride(new ResourceLocation("pulling"), (p_210309_0_, p_210309_1_, p_210309_2_) -> {
                return p_210309_2_ != null && p_210309_2_.isHandActive() && p_210309_2_.getActiveItemStack() == p_210309_0_ ? 1.0F : 0.0F;
            });
        }

        /**
         * Called when the player stops using an Item (stops holding the right mouse button).
         */
        public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
            if (entityLiving instanceof PlayerEntity) {
                PlayerEntity playerentity = (PlayerEntity) entityLiving;
                boolean flag = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
                ItemStack itemstack = playerentity.findAmmo(stack);

                int i = this.getUseDuration(stack) - timeLeft;
                i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, !itemstack.isEmpty() || flag);
                if (i < 0) return;

                if (!itemstack.isEmpty() || flag) {
                    if (itemstack.isEmpty()) {
                        itemstack = new ItemStack(Items.ARROW);
                    }

                    float f = getArrowVelocity(i);
                    if (!((double) f < 0.1D)) {
                        boolean flag1 = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem) itemstack.getItem()).isInfinite(itemstack, stack, playerentity));
                        if (!worldIn.isRemote) {
                            ArrowItem arrowitem = (ArrowItem) (itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                            AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerentity);
                            abstractarrowentity = customeArrow(abstractarrowentity);
                            abstractarrowentity.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F);
                            if (f == 1.0F) {
                                abstractarrowentity.setIsCritical(true);
                            }

                            int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                            if (j > 0) {
                                abstractarrowentity.setDamage(abstractarrowentity.getDamage() + (double) j * 0.5D + 0.5D);
                            }

                            int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
                            if (k > 0) {
                                abstractarrowentity.setKnockbackStrength(k);
                            }

                            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                                abstractarrowentity.setFire(100);
                            }

                            stack.damageItem(1, playerentity, (p_220009_1_) -> {
                                p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
                            });
                            if (flag1 || playerentity.abilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)) {
                                abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                            }

                            worldIn.addEntity(abstractarrowentity);
                        }

                        worldIn.playSound((PlayerEntity) null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                        if (!flag1 && !playerentity.abilities.isCreativeMode) {
                            itemstack.shrink(1);
                            if (itemstack.isEmpty()) {
                                playerentity.inventory.deleteStack(itemstack);
                            }
                        }

                        playerentity.addStat(Stats.ITEM_USED.get(this));
                    }
                }
            }
        }

        /**
         * Gets the velocity of the arrow entity from the bow's charge
         */
        public static float getArrowVelocity(int charge) {
            float f = (float) charge / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;
            if (f > 1.0F) {
                f = 1.0F;
            }

            return f;
        }

        /**
         * How long it takes to use or consume an item
         */
        public int getUseDuration(ItemStack stack) {
            return 72000;
        }

        /**
         * returns the action that specifies what animation to play when the items is being used
         */
        public UseAction getUseAction(ItemStack stack) {
            return UseAction.BOW;
        }

        /**
         * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
         * {@link #onItemUse}.
         */
        public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
            ItemStack itemstack = playerIn.getHeldItem(handIn);
            boolean flag = !playerIn.findAmmo(itemstack).isEmpty();

            ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
            if (ret != null) return ret;

            if (!playerIn.abilities.isCreativeMode && !flag) {
                return ActionResult.resultFail(itemstack);
            } else {
                playerIn.setActiveHand(handIn);
                return ActionResult.resultConsume(itemstack);
            }
        }

        /**
         * Get the predicate to match ammunition when searching the player's inventory, not their main/offhand
         */
        public Predicate<ItemStack> getInventoryAmmoPredicate() {
            return ARROWS;
        }

        public AbstractArrowEntity customeArrow(AbstractArrowEntity arrow) {
            return arrow;
        }
    }
}
