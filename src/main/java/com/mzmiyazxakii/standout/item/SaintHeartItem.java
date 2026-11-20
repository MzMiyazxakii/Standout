package com.mzmiyazxakii.standout.item;
import com.mzmiyazxakii.standout.entity.VerityStandEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
public class SaintHeartItem extends Item {
 public SaintHeartItem(Properties properties){super(properties);}
 @Override public InteractionResult interactLivingEntity(ItemStack stack,Player player,LivingEntity target,InteractionHand hand){
  if(!(target instanceof VerityStandEntity stand))return InteractionResult.PASS;
  if(player.level().isClientSide)return InteractionResult.SUCCESS;
  if(!player.getUUID().equals(stand.getOwnerId())||stand.isActTwo())return InteractionResult.PASS;
  stand.setActTwo();
  if(!player.getAbilities().instabuild)stack.shrink(1);
  return InteractionResult.CONSUME;
 }
}