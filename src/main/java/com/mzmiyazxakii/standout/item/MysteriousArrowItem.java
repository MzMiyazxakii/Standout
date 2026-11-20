package com.mzmiyazxakii.standout.item;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
public class MysteriousArrowItem extends Item {
 public MysteriousArrowItem(Properties properties){super(properties);}
 @Override public InteractionResultHolder<ItemStack> use(Level level,Player player,InteractionHand hand){
  ItemStack stack=player.getItemInHand(hand);
  player.startUsingItem(hand);
  return InteractionResultHolder.consume(stack);
 }
 @Override public int getUseDuration(ItemStack stack){return 60;}
 @Override public UseAnim getUseAnimation(ItemStack stack){return UseAnim.BOW;}
 @Override public void onUseTick(Level level,LivingEntity entity,ItemStack stack,int remaining){
  if((remaining&1)!=0)return;
  double progress=(60-remaining)/60.0D;
  double radius=0.65D+progress*1.1D;
  for(int i=0;i<10;i++){
   double angle=(60-remaining)*0.23D+i*Math.PI*2.0D/10.0D;
   double x=entity.getX()+Math.cos(angle)*radius;
   double z=entity.getZ()+Math.sin(angle)*radius;
   double y=entity.getY()+0.25D+((i%3)*0.48D)+Math.sin(angle*2.0D)*0.18D;
   if(level instanceof ServerLevel server){
    server.sendParticles(ParticleTypes.PORTAL,x,y,z,2,0.08D,0.12D,0.08D,0.02D);
    server.sendParticles(ParticleTypes.ENCHANT,x,y,z,1,0.04D,0.08D,0.04D,0.2D);
    if(remaining<24)server.sendParticles(ParticleTypes.END_ROD,x,y,z,1,0.04D,0.08D,0.04D,0.01D);
   }else{
    level.addParticle(ParticleTypes.PORTAL,x,y,z,0.0D,0.04D,0.0D);
    level.addParticle(ParticleTypes.ENCHANT,x,y,z,0.0D,0.08D,0.0D);
    if(remaining<24)level.addParticle(ParticleTypes.END_ROD,x,y,z,0.0D,0.05D,0.0D);
   }
  }
  if(remaining%10==0){
   if(level instanceof ServerLevel server)server.sendParticles(ParticleTypes.WITCH,entity.getX(),entity.getY()+1.0D,entity.getZ(),18,0.55D,0.75D,0.55D,0.08D);
   else for(int i=0;i<8;i++)level.addParticle(ParticleTypes.WITCH,entity.getX()+(entity.getRandom().nextDouble()-0.5D),entity.getY()+entity.getRandom().nextDouble()*1.8D,entity.getZ()+(entity.getRandom().nextDouble()-0.5D),0.0D,0.04D,0.0D);
  }
 }
 @Override public ItemStack finishUsingItem(ItemStack stack,Level level,LivingEntity entity){
  if(entity instanceof Player player){
   if(!level.isClientSide){
    player.getPersistentData().putBoolean("standout_has_verity",true);
    if(!player.getAbilities().instabuild){
     stack.shrink(1);
     ItemStack stick=new ItemStack(Items.STICK,1);
     if(!player.getInventory().add(stick))player.drop(stick,false);
    }
   }
  }
  return stack;
 }
}