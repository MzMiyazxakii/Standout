package com.mzmiyazxakii.standout.item;
import com.mzmiyazxakii.standout.client.AdminTabletScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
public class AdminTabletItem extends Item {
 public AdminTabletItem(Properties properties){super(properties);}
 @Override public InteractionResultHolder<ItemStack> use(Level level,Player player,InteractionHand hand){
  ItemStack stack=player.getItemInHand(hand);
  if(level.isClientSide)Minecraft.getInstance().setScreen(new AdminTabletScreen());
  return InteractionResultHolder.sidedSuccess(stack,level.isClientSide);
 }
}