package com.mzmiyazxakii.standout.network;
import com.mzmiyazxakii.standout.entity.VerityStandEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;
import java.util.List;
import java.util.function.Supplier;
public class AbilityTwoPacket {
 public static void handle(AbilityTwoPacket message,Supplier<NetworkEvent.Context> supplier){
  NetworkEvent.Context context=supplier.get();
  context.enqueueWork(()->{
   ServerPlayer player=context.getSender();
   if(player==null||!player.getPersistentData().getBoolean("standout_has_verity"))return;
   long now=player.level().getGameTime();
   if(now<player.getPersistentData().getLong("standout_verity_atk2_ready"))return;
   List<VerityStandEntity> stands=player.level().getEntitiesOfClass(VerityStandEntity.class,new AABB(player.blockPosition()).inflate(32.0D),s->player.getUUID().equals(s.getOwnerId()));
   if(stands.isEmpty())return;
   VerityStandEntity stand=stands.get(0);
   if(stand.isActTwo()){
    stand.startAttackTwo();
    AABB area=player.getBoundingBox().inflate(6.0D,4.0D,6.0D);
    for(LivingEntity target:player.level().getEntitiesOfClass(LivingEntity.class,area,e->e!=player&&e.isAlive())){
     if(target.distanceToSqr(player)>36.0D)continue;
     target.hurt(player.damageSources().magic(),5.0F);
     Vec3 push=target.position().subtract(player.position()).normalize().scale(0.8D).add(0,0.25D,0);
     target.setDeltaMovement(target.getDeltaMovement().add(push));target.hurtMarked=true;
    }
    if(player.level() instanceof ServerLevel server){
     server.sendParticles(ParticleTypes.SOUL_FIRE_FLAME,player.getX(),player.getY()+1.0D,player.getZ(),45,2.0D,0.8D,2.0D,0.04D);
     server.sendParticles(ParticleTypes.ENCHANT,player.getX(),player.getY()+1.0D,player.getZ(),35,2.0D,0.8D,2.0D,0.3D);
    }
   }else{
    stand.startAttackTwo();
   }
   player.getPersistentData().putLong("standout_verity_atk2_ready",now+300L);
  });
  context.setPacketHandled(true);
 }
}
