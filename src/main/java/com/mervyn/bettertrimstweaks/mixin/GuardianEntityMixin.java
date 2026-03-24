package com.mervyn.bettertrimstweaks.mixin;


import com.bawnorton.bettertrims.config.ConfigManager;
import com.bawnorton.bettertrims.effect.ArmorTrimEffects;
import com.bawnorton.bettertrims.extend.LivingEntityExtender;
import com.bawnorton.bettertrims.util.NumberWrapper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ElderGuardianEntity;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Fixes the prismarine shard trim effect not making guardians ignore players.
 */
@Mixin(MobEntity.class)
public abstract class GuardianEntityMixin {

    @Inject(method = "setTarget", at = @At("HEAD"), cancellable = true)
    private void betterTrimsTweaks$preventTargetingTrimmedPlayers(LivingEntity target, CallbackInfo ci) {
        if (!((Object)this instanceof GuardianEntity))
            return;
        if ((Object)this instanceof ElderGuardianEntity)
            return;

        if (!ConfigManager.getConfig().prismarineShardEffects.guardiansIgnore)
            return;

        if (target instanceof PlayerEntity && target instanceof LivingEntityExtender extender) {
            NumberWrapper trimCount = NumberWrapper.zero();
            ArmorTrimEffects.PRISMARINE_SHARD.apply(
                    extender.betterTrims$getTrimmables(),
                    () -> trimCount.increment(1));

            if (trimCount.getInt() >= ConfigManager.getConfig().prismarineShardEffects.piecesForGuardiansIgnore) {
                ci.cancel();
            }
        }
    }
}
