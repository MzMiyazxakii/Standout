package com.mzmiyazxakii.standout.entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.core.particles.DustParticleOptions;
import org.joml.Vector3f;
import net.minecraft.nbt.CompoundTag;
import java.util.UUID;
import java.util.Optional;
public class VerityStandEntity extends Mob {
 private static final EntityDataAccessor<Boolean> ATTACKING=SynchedEntityData.defineId(VerityStandEntity.class,EntityDataSerializers.BOOLEAN);
 private static final EntityDataAccessor<Boolean> ATTACKING_TWO=SynchedEntityData.defineId(VerityStandEntity.class,EntityDataSerializers.BOOLEAN);
 private static final EntityDataAccessor<Optional<UUID>> OWNER_ID=SynchedEntityData.defineId(VerityStandEntity.class,EntityDataSerializers.OPTIONAL_UUID);
 private UUID ownerId;
 private int attackTicks;
 public final AnimationState summonAnimationState=new AnimationState();
 public final AnimationState idleAnimationState=new AnimationState();
 public final AnimationState attackAnimationState=new AnimationState();
 private boolean animationInitialized;
 public VerityStandEntity(EntityType<? extends Mob> type,Level level){super(type,level);setNoGravity(true);setNoAi(true);}
 public static AttributeSupplier.Builder createAttributes(){return Mob.createMobAttributes().add(Attributes.MAX_HEALTH,20.0D);}
 @Override protected void defineSynchedData(){super.defineSynchedData();entityData.define(ATTACKING,false);entityData.define(ATTACKING_TWO,false);entityData.define(OWNER_ID,Optional.empty());}
 public void setOwner(Player player){ownerId=player.getUUID();entityData.set(OWNER_ID,Optional.of(ownerId));}
 public UUID getOwnerId(){return entityData.get(OWNER_ID).orElse(ownerId);}
 public boolean isAttacking(){return entityData.get(ATTACKING);}
 public boolean isSecondAttacking(){return entityData.get(ATTACKING_TWO);}
 public void startAttack(){if(!level().isClientSide){attackTicks=30;entityData.set(ATTACKING,true);entityData.set(ATTACKING_TWO,false);}}
 public void startAttackTwo(){if(!level().isClientSide){attackTicks=42;entityData.set(ATTACKING,true);entityData.set(ATTACKING_TWO,true);}}
 @Override public void tick(){
  super.tick();
  if(level().isClientSide){
   if(!animationInitialized){animationInitialized=true;summonAnimationState.start(tickCount);}
   if(isAttacking()){
    if(!attackAnimationState.isStarted()){idleAnimationState.stop();attackAnimationState.start(tickCount);}
   }else{
    if(attackAnimationState.isStarted()){attackAnimationState.stop();if(tickCount>=7)idleAnimationState.start(tickCount);}
    if(animationInitialized&&tickCount>=7&&!idleAnimationState.isStarted()){summonAnimationState.stop();idleAnimationState.start(tickCount);}
   }
  }else{
   if(isAttacking()&&--attackTicks<=0){entityData.set(ATTACKING,false);entityData.set(ATTACKING_TWO,false);}
   if(ownerId==null){discard();return;}
   Player owner=level().getPlayerByUUID(ownerId);
   if(owner==null||!owner.isAlive()){discard();return;}
   long auraUntil=owner.getPersistentData().getLong("standout_verity_aura_until");
   if(level() instanceof ServerLevel server&&level().getGameTime()<=auraUntil){
    server.sendParticles(new DustParticleOptions(new Vector3f(1.0F,0.91F,0.38F),0.75F),owner.getX(),owner.getY()+0.9D,owner.getZ(),5,0.38D,0.65D,0.38D,0.005D);
   }
   if(isSecondAttacking()){
    Vec3 forward=owner.getLookAngle().normalize();
    setPos(owner.getX()+forward.x*1.05D,owner.getY()+0.45D,owner.getZ()+forward.z*1.05D);
   }else{
    float yaw=owner.getYRot()*((float)Math.PI/180F);
    double x=owner.getX()+Math.cos(yaw)*0.42D+Math.sin(yaw)*0.28D;
    double z=owner.getZ()+Math.sin(yaw)*0.42D-Math.cos(yaw)*0.28D;
    setPos(x,owner.getY()+1.15D,z);
   }
   setYRot(owner.getYRot());yRotO=getYRot();
   setYHeadRot(owner.getYHeadRot());yHeadRotO=getYHeadRot();
   setXRot(owner.getXRot());xRotO=getXRot();
   setDeltaMovement(Vec3.ZERO);
  }
 }
 @Override protected void registerGoals(){}
 @Override public void addAdditionalSaveData(CompoundTag tag){super.addAdditionalSaveData(tag);if(ownerId!=null)tag.putUUID("Owner",ownerId);}
 @Override public void readAdditionalSaveData(CompoundTag tag){super.readAdditionalSaveData(tag);if(tag.hasUUID("Owner")){ownerId=tag.getUUID("Owner");entityData.set(OWNER_ID,Optional.of(ownerId));}}
 @Override public boolean isPushable(){return false;}
 @Override public boolean hurt(net.minecraft.world.damagesource.DamageSource source,float amount){return false;}
}