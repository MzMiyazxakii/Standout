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
 private UUID ownerId; private int attackTicks;
 public final AnimationState summonAnimationState=new AnimationState(),idleAnimationState=new AnimationState(),attackAnimationState=new AnimationState();
 private boolean animationInitialized; private int secondTearsFired,nextSecondTearTick;
 public VerityStandEntity(EntityType<? extends Mob> type,Level level){super(type,level);setNoGravity(true);setNoAi(true);}
 public static AttributeSupplier.Builder createAttributes(){return Mob.createMobAttributes().add(Attributes.MAX_HEALTH,20.0D);}
 @Override protected void defineSynchedData(){super.defineSynchedData();entityData.define(ATTACKING,false);entityData.define(ATTACKING_TWO,false);entityData.define(OWNER_ID,Optional.empty());}
 public void setOwner(Player player){ownerId=player.getUUID();entityData.set(OWNER_ID,Optional.of(ownerId));}
 public UUID getOwnerId(){return entityData.get(OWNER_ID).orElse(ownerId);}
 public boolean isAttacking(){return entityData.get(ATTACKING);} public boolean isSecondAttacking(){return entityData.get(ATTACKING_TWO);}
 public void startAttack(){if(!level().isClientSide){attackTicks=30;entityData.set(ATTACKING,true);entityData.set(ATTACKING_TWO,false);}}
 public void startAttackTwo(){if(!level().isClientSide){attackTicks=52;secondTearsFired=0;nextSecondTearTick=42;entityData.set(ATTACKING,true);entityData.set(ATTACKING_TWO,true);}}
 @Override public void tick(){
  super.tick();
  if(level().isClientSide){
   if(!animationInitialized){animationInitialized=true;summonAnimationState.start(tickCount);}
   if(isAttacking()){if(!attackAnimationState.isStarted()){idleAnimationState.stop();attackAnimationState.start(tickCount);}}
   else {if(attackAnimationState.isStarted()){attackAnimationState.stop();if(tickCount>=7)idleAnimationState.start(tickCount);}if(tickCount>=7&&!idleAnimationState.isStarted()){summonAnimationState.stop();idleAnimationState.start(tickCount);}}
  }
  if(!level().isClientSide&&isAttacking()&&--attackTicks<=0){entityData.set(ATTACKING,false);entityData.set(ATTACKING_TWO,false);}
  UUID id=getOwnerId();if(id==null){if(!level().isClientSide)discard();return;}
  Player owner=level().getPlayerByUUID(id);if(owner==null||!owner.isAlive()){if(!level().isClientSide)discard();return;}
  if(isSecondAttacking()){Vec3 f=owner.getLookAngle().normalize();setPos(owner.getX()+f.x*1.35D,owner.getY()+0.55D,owner.getZ()+f.z*1.35D);}
  else {float yaw=owner.getYRot()*((float)Math.PI/180F);setPos(owner.getX()+Math.cos(yaw)*0.42D+Math.sin(yaw)*0.28D,owner.getY()+1.15D,owner.getZ()+Math.sin(yaw)*0.42D-Math.cos(yaw)*0.28D);}
  setYRot(owner.getYRot());yRotO=getYRot();setYHeadRot(owner.getYHeadRot());yHeadRotO=getYHeadRot();setXRot(owner.getXRot());xRotO=getXRot();setDeltaMovement(Vec3.ZERO);
  if(!level().isClientSide){
   long auraUntil=owner.getPersistentData().getLong("standout_verity_aura_until");
   if(level() instanceof ServerLevel server&&level().getGameTime()<=auraUntil)server.sendParticles(new DustParticleOptions(new Vector3f(1.0F,0.91F,0.38F),0.75F),owner.getX(),owner.getY()+0.9D,owner.getZ(),5,0.38D,0.65D,0.38D,0.005D);
   if(isAttacking()&&!isSecondAttacking()&&level() instanceof ServerLevel server){
    Vec3 aim=owner.getLookAngle().normalize();Vec3 front=position().add(aim.scale(0.48D)).add(0.0D,0.35D,0.0D);
    server.sendParticles(net.minecraft.core.particles.ParticleTypes.CLOUD,front.x,front.y,front.z,5,0.10D,0.08D,0.10D,0.015D);
   }
   if(isSecondAttacking()&&secondTearsFired<4&&attackTicks<=nextSecondTearTick){
    Vec3 look=owner.getLookAngle().normalize();Vec3 origin=position().add(0.0D,0.15D,0.0D).add(look.scale(0.62D));
    TearProjectile tear=new TearProjectile(level(),owner);tear.setPos(origin.x,origin.y,origin.z);tear.shoot(look.x,look.y,look.z,0.62F,0.0F);level().addFreshEntity(tear);
    secondTearsFired++;nextSecondTearTick-=5;
   }
  }
 }
 @Override protected void registerGoals(){}
 @Override public void addAdditionalSaveData(CompoundTag tag){super.addAdditionalSaveData(tag);if(ownerId!=null)tag.putUUID("Owner",ownerId);}
 @Override public void readAdditionalSaveData(CompoundTag tag){super.readAdditionalSaveData(tag);if(tag.hasUUID("Owner")){ownerId=tag.getUUID("Owner");entityData.set(OWNER_ID,Optional.of(ownerId));}}
 @Override public boolean isPushable(){return false;} @Override public boolean hurt(net.minecraft.world.damagesource.DamageSource source,float amount){return false;}
}