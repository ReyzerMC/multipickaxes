package org.reyzer;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import org.reyzer.enchantments.effects.BlazedEnchantmetEffect;
import org.reyzer.enchantments.effects.FastMiningEnchantmentEffect;
import org.reyzer.enchantments.effects.LightningEnchantmentEffect;
import org.reyzer.enchantments.effects.PoisonedEnchantmentEffect;

import java.util.concurrent.CompletableFuture;

public class MultipickaxesEnchantmentGenerator extends FabricDynamicRegistryProvider {
    public MultipickaxesEnchantmentGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
        System.out.println("REGISTERING ENCHANTS");
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        // Our new enchantment, "Blazing."
        register(entries, ModEnchantmentEffects.BLAZING, Enchantment.enchantment(
                                Enchantment.definition(
                                        registries.lookupOrThrow(Registries.ITEM).getOrThrow(ItemTags.MINING_ENCHANTABLE),
                                        // this is the "weight" or probability of our enchantment showing up in the table
                                        4,
                                        // the maximum level of the enchantment
                                        1,
                                        // base cost for level 1 of the enchantment, and min levels required for something higher
                                        Enchantment.constantCost(5),
                                        // same fields as above but for max cost
                                        Enchantment.constantCost(8),
                                        // anvil cost
                                        4,
                                        // valid slots
                                        EquipmentSlotGroup.HAND
                                )
                        )
                        .withEffect(
                                // enchantment occurs POST_ATTACK
                                EnchantmentEffectComponents.TICK,
                                new BlazedEnchantmetEffect(LevelBasedValue.perLevel(0.4f, 0.2f)) // scale the enchantment linearly.
                        )
        );

        register(entries, ModEnchantmentEffects.FAST_MINIG, Enchantment.enchantment(
                                Enchantment.definition(
                                        registries.lookupOrThrow(Registries.ITEM).getOrThrow(ItemTags.MINING_ENCHANTABLE),
                                        2,
                                        1,
                                        Enchantment.constantCost(5),
                                        Enchantment.constantCost(7),
                                        5,
                                        EquipmentSlotGroup.HAND
                                )
                        )
                        .withEffect(
                            EnchantmentEffectComponents.TICK,
                            new FastMiningEnchantmentEffect(LevelBasedValue.perLevel(0.4f, 0.2f))
                        )
        );

        register(entries, ModEnchantmentEffects.THUNDERING, Enchantment.enchantment(
                                Enchantment.definition(
                                        registries.lookupOrThrow(Registries.ITEM).getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                        // this is the "weight" or probability of our enchantment showing up in the table
                                        3,
                                        // the maximum level of the enchantment
                                        3,
                                        // base cost for level 1 of the enchantment, and min levels required for something higher
                                        Enchantment.dynamicCost(1, 10),
                                        // same fields as above but for max cost
                                        Enchantment.dynamicCost(1, 15),
                                        // anvil cost
                                        5,
                                        // valid slots
                                        EquipmentSlotGroup.HAND
                                )
                        )
                        .withEffect(
                                // enchantment occurs POST_ATTACK
                                EnchantmentEffectComponents.POST_ATTACK,
                                EnchantmentTarget.ATTACKER,
                                EnchantmentTarget.VICTIM,
                                new LightningEnchantmentEffect(LevelBasedValue.perLevel(0.4f, 0.2f)) // scale the enchantment linearly.
                        )
        );

        register(entries, ModEnchantmentEffects.POISONED, Enchantment.enchantment(
                                Enchantment.definition(
                                        registries.lookupOrThrow(Registries.ITEM).getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                        // this is the "weight" or probability of our enchantment showing up in the table
                                        5,
                                        // the maximum level of the enchantment
                                        1,
                                        // base cost for level 1 of the enchantment, and min levels required for something higher
                                        Enchantment.constantCost(3),
                                        // same fields as above but for max cost
                                        Enchantment.constantCost(6),
                                        // anvil cost
                                        5,
                                        // valid slots
                                        EquipmentSlotGroup.HAND
                                )
                        )
                        .withEffect(
                                // enchantment occurs POST_ATTACK
                                EnchantmentEffectComponents.POST_ATTACK,
                                EnchantmentTarget.ATTACKER,
                                EnchantmentTarget.VICTIM,
                                new PoisonedEnchantmentEffect(LevelBasedValue.perLevel(0.4f, 0.2f)) // scale the enchantment linearly.
                        )
        );
    }

    private void register(Entries entries, ResourceKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition... resourceConditions) {
        entries.add(key, builder.build(key.identifier()), resourceConditions);
    }

    @Override
    public String getName() {
        return "MultipickaxesEnchantmentGenerator";
    }
}
