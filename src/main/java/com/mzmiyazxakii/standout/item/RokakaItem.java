package com.mzmiyazxakii.standout.item;
import com.mzmiyazxakii.standout.entity.VerityStandEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
public class RokakaItem extends Item {
 public RokakaItem(Properties properties){super(properties);}
 @Override public InteractionResultHolder<ItemStack> use(Level level,Player player,InteractionHand hand){
  ItemStack stack=player.getItemInHand(hand);
  player.startUsingItem(hand);
  return InteractionResultHolder.consume(stack);
 }
 @Override public int getUseDuration(ItemStack stack){return 32;}
 @Override public UseAnim getUseAnimation(ItemStack stack){return UseAnim.EAT;}
 @Override public ItemStack finishUsingItem(ItemStack stack,Level level,LivingEntity entity){
  if(!(entity instanceof Player player))return stack;
  if(!level.isClientSide){
   boolean hadStand=player.getPersistentData().getBoolean("standout_has_verity");
   if(hadStand){
    player.getPersistentData().remove("standout_has_verity");
    level.getEntitiesOfClass(VerityStandEntity.class,new AABB(player.blockPosition()).inflate(256.0D),stand->player.getUUID().equals(stand.getOwnerId())).forEach(VerityStandEntity::discard);
    if(!player.getAbilities().instabuild)stack.shrink(1);
    player.displayClientMessage(Component.translatable("message.standout.rokaka_used"),true);
   }else{
    player.displayClientMessage(Component.translatable("message.standout.no_stand"),true);
   }
  }
  return stack;
 }
}