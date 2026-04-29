package com.elthisboy.noexplosions.mixin;

import com.elthisboy.noexplosions.NoExplosions;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Este mixin intercepta el método collectBlocksAndDamageEntities() de Explosion,
 * que es el punto central donde TODA explosión calcula su daño y destrucción de bloques.
 * Esto aplica a: TNT vanilla, creepers, cargas de fuego, wither, end crystal,
 * y cualquier explosión de mods externos que use la clase Explosion de Minecraft.
 */
@Mixin(Explosion.class)
public class ExplosionMixin {

    @Inject(
        at = @At("HEAD"),
        method = "collectBlocksAndDamageEntities",
        cancellable = true
    )
    private void noExplosions_blockExplosion(CallbackInfo ci) {
        if (NoExplosions.explosionsBlocked) {
            // Cancelar completamente: sin destrucción de bloques, sin daño a entidades
            ci.cancel();
        }
    }
}
