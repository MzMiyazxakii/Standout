package com.mzmiyazxakii.standout.entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.nbt.CompoundTag;
import java.util.UUID;
public class VerityStandEntity extends Mob {
 private UUID ownerId;
 public final AnimationState summonAnimationState=new AnimationState();
 public final AnimationState idleAnimationState=new AnimationState();
 private boolean animationInitialized;
 public VerityStandEntity(EntityType<? extends Mob> type,Level level){super(type,level);setNoGravity(true);setNoAi(true);}
 public static AttributeSupplier.Builder createAttributes(){return Mob.createMobAttributes().add(Attributes.MAX_HEALTH,20.0D);}
 public void setOwner(Player player){ownerId=player.getUUID();}
 public UUID getOwnerId(){return ownerId;}
 @Override public void tick(){
  super.tick();
  if(level().isClientSide&&!animationInitialized){animationInitialized=true;summonAnimationState.start(tickCount);}
  if(level().isClientSide&&animationInitialized&&tickCount>=7&&!idleAnimationState.isStarted()){summonAnimationState.stop();idleAnimationState.start(tickCount);}
  if(!level().isClientSide){
   if(ownerId==null){discard();return;}
   Player owner=level().getPlayerByUUID(ownerId);
   if(owner==null||!owner.isAlive()){discard();return;}
   float yaw=owner.getYRot()*((float)Math.PI/180F);
   double x=owner.getX()+Math.cos(yaw)*0.42D+Math.sin(yaw)*0.28D;
   double z=owner.getZ()+Math.sin(yaw)*0.42D-Math.cos(yaw)*0.28D;
   setPos(x,owner.getY()+1.15D,z);setYRot(owner.getYRot());yRotO=getYRot();setDeltaMovement(Vec3.ZERO);
  }
 }
 @Override protected void registerGoals(){}
 @Override public void addAdditionalSaveData(CompoundTag tag){super.addAdditionalSaveData(tag);if(ownerId!=null)tag.putUUID("Owner",ownerId);}
 @Override public void readAdditionalSaveData(CompoundTag tag){super.readAdditionalSaveData(tag);if(tag.hasUUID("Owner"))ownerId=tag.getUUID("Owner");}
 @Override public boolean isPushable(){return false;}
 @Override public boolean hurt(net.minecraft.world.damagesource.DamageSource source,float amount){return false;}
}
