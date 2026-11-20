package com.mzmiyazxakii.standout.item;

import com.mzmiyazxakii.standout.entity.VerityStandEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class SaintHeartItem extends Item {
    public SaintHeartItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!player.getPersistentData().getBoolean("standout_has_verity")) {
            return InteractionResultHolder.fail(stack);
        }

        if (!level.isClientSide) {
            // Store the evolution on the owner, not only on the currently spawned entity.
            player.getPersistentData().putBoolean("standout_verity_act2", true);

            for (VerityStandEntity stand : level.getEntitiesOfClass(
                    VerityStandEntity.class,
                    new AABB(player.blockPosition()).inflate(32.0D),
                    entity -> player.getUUID().equals(entity.getOwnerId()))) {
                stand.setActTwo();
            }

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (!(target instanceof VerityStandEntity stand)) {
            return InteractionResult.PASS;
        }
        if (!player.getUUID().equals(stand.getOwnerId()) || stand.isActTwo()) {
            return InteractionResult.PASS;
        }
        if (player.level().isClientSide) {
            return InteractionResult.SUCCESS;
        }

        player.getPersistentData().putBoolean("standout_verity_act2", true);
        stand.setActTwo();
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        return InteractionResult.CONSUME;
    }
}
