package net.sashannel.pablmod.items.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;

public class GithubItem extends Item {
    public GithubItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        Player player = context.getPlayer();

        if (!world.isClientSide) {
            Vec3 lookVec = player.getLookAngle();
            Vec3 startVec = player.getEyePosition();
            Vec3 endVec = startVec.add(lookVec.scale(50)); // Adjust range as needed

            HitResult hitResult = world.clip(new ClipContext(startVec, endVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BlockPos hitPos = ((BlockHitResult) hitResult).getBlockPos();
                explode(world, hitPos);
            } else if (hitResult.getType() == HitResult.Type.ENTITY) {
                BlockPos hitPos = ((EntityHitResult) hitResult).getEntity().blockPosition();
                explode(world, hitPos);
            }
        }

        context.getItemInHand().hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(context.getHand()));
        return InteractionResult.SUCCESS;
    }

    private void explode(Level world, BlockPos pos) {
        float explosionStrength = 9.0F; // Adjust this value for a bigger explosion (default is 4.0F)
        world.explode(null, pos.getX(), pos.getY(), pos.getZ(), explosionStrength, Explosion.BlockInteraction.BREAK);
        world.playSound(null, pos, SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 4.0F, (1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.2F) * 0.7F);
        world.addParticle(ParticleTypes.EXPLOSION, pos.getX(), pos.getY(), pos.getZ(), 1.0D, 0.0D, 0.0D);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }
}
