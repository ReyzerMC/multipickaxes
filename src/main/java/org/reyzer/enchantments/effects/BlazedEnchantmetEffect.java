package org.reyzer.enchantments.effects;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record BlazedEnchantmetEffect(LevelBasedValue amount) implements EnchantmentEntityEffect {
    public static final MapCodec<BlazedEnchantmetEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    LevelBasedValue.CODEC.fieldOf("amount").forGetter(BlazedEnchantmetEffect::amount)
            ).apply(instance, BlazedEnchantmetEffect::new)
    );

    @Override
    public void apply(ServerLevel level, int enchantLevel, EnchantedItemInUse context, Entity target, Vec3 pos) {

        if (!(target instanceof LivingEntity living)) return;

        int amplifier = Math.max(0, Mth.floor(amount.calculate(enchantLevel)) - 1);

        living.addEffect(new MobEffectInstance(
                MobEffects.FIRE_RESISTANCE,
                20,               // 1 segundo (20 ticks)
                amplifier,
                true,             // ambient
                false,            // partículas
                true              // icono
        ));
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
