package com.mzmiyazxakii.standout.item;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
public class MysteriousArrowItem extends Item {
 public MysteriousArrowItem(Properties properties){super(properties);}
 @Override public InteractionResultHolder<ItemStack> use(Level level,Player player,InteractionHand hand){
  ItemStack stack=player.getItemInHand(hand);
  if(!level.isClientSide){ player.getPersistentData().putBoolean("standout_has_verity",true); if(!player.getAbilities().instabuild) stack.shrink(1); player.displayClientMessage(Component.translatable("message.standout.verity_obtained"),true); }
  return InteractionResultHolder.sidedSuccess(stack,level.isClientSide);
 }
}
