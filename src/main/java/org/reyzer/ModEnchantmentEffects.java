package org.reyzer;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import org.reyzer.enchantments.effects.BlazedEnchantmetEffect;
import org.reyzer.enchantments.effects.FastMiningEnchantmentEffect;
import org.reyzer.enchantments.effects.LightningEnchantmentEffect;
import org.reyzer.enchantments.effects.PoisonedEnchantmentEffect;

public class ModEnchantmentEffects {
    public static final ResourceKey<Enchantment> THUNDERING = of("thundering");
    public static final ResourceKey<Enchantment> BLAZING = of("blazing");
    public static final ResourceKey<Enchantment> FAST_MINIG = of("fastmining");
    public static final ResourceKey<Enchantment> POISONED = of("poisoned");

    public static MapCodec<LightningEnchantmentEffect> LIGHTNING_EFFECT = register("lightning_effect", LightningEnchantmentEffect.CODEC);
    public static MapCodec<BlazedEnchantmetEffect> BLAZING_EFFECT = register("blazing_effect", BlazedEnchantmetEffect.CODEC);
    public static MapCodec<FastMiningEnchantmentEffect> FAST_MINING_EFFECT = register("fastmining_effect", FastMiningEnchantmentEffect.CODEC);
    public static MapCodec<PoisonedEnchantmentEffect> POISONED_EFFECT = register("poisoned_effect", PoisonedEnchantmentEffect.CODEC);

    private static ResourceKey<Enchantment> of(String path) {
        Identifier id = Identifier.fromNamespaceAndPath(MultiPickaxes.MOD_ID, path);
        return ResourceKey.create(Registries.ENCHANTMENT, id);
    }

    private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String id, MapCodec<T> codec) {
        return Registry.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.fromNamespaceAndPath(MultiPickaxes.MOD_ID, id), codec);
    }

    public static void registerModEnchantmentEffects() {
        MultiPickaxes.LOGGER.info("Registering EnchantmentEffects for" + MultiPickaxes.MOD_ID);
    }
}
