package com.mzmiyazxakii.standout.entity;
import com.mzmiyazxakii.standout.registry.ModEntities;
import com.mzmiyazxakii.standout.registry.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
public class TearProjectile extends ThrowableItemProjectile {
 public TearProjectile(EntityType<? extends TearProjectile> type,Level level){super(type,level);}
 public TearProjectile(Level level,LivingEntity owner){super(ModEntities.TEAR_PROJECTILE.get(),owner,level);}
 @Override protected Item getDefaultItem(){return ModItems.TEAR.get();}
 @Override protected void onHitEntity(EntityHitResult result){
  super.onHitEntity(result);
  if(!level().isClientSide&&result.getEntity() instanceof LivingEntity target){
   target.hurt(level().damageSources().thrown(this,getOwner()),2.0F);
  }
  if(!level().isClientSide)discard();
 }
 @Override protected void onHit(net.minecraft.world.phys.HitResult result){super.onHit(result);if(!level().isClientSide)discard();}
}