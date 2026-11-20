package com.mzmiyazxakii.standout.network;
import com.mzmiyazxakii.standout.entity.TearProjectile;
import com.mzmiyazxakii.standout.entity.VerityStandEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;
import java.util.List;
import java.util.function.Supplier;
public class AbilityPacket {
 public static void handle(AbilityPacket message,Supplier<NetworkEvent.Context> supplier){
  NetworkEvent.Context context=supplier.get();
  context.enqueueWork(()->{
   ServerPlayer player=context.getSender();
   if(player==null||!player.getPersistentData().getBoolean("standout_has_verity"))return;
   long now=player.level().getGameTime();
   if(now<player.getPersistentData().getLong("standout_verity_atk1_ready"))return;
   List<VerityStandEntity> stands=player.level().getEntitiesOfClass(VerityStandEntity.class,new AABB(player.blockPosition()).inflate(32.0D),s->player.getUUID().equals(s.getOwnerId()));
   if(stands.isEmpty())return;
   VerityStandEntity stand=stands.get(0);
   if(stand.isActTwo()){
    stand.startAttack();
    Vec3 look=player.getLookAngle().normalize();
    TearProjectile shot=new TearProjectile(player.level(),player);
    Vec3 origin=player.getEyePosition().add(look.scale(0.65D));
    shot.setPos(origin.x,origin.y,origin.z);
    shot.shoot(look.x,look.y,look.z,2.2F,0.0F);
    shot.setNoGravity(true);
    player.level().addFreshEntity(shot);
   }else{
    Vec3 look=player.getLookAngle().normalize();
    AABB search=player.getBoundingBox().inflate(12.0D,8.0D,12.0D);
    for(LivingEntity target:player.level().getEntitiesOfClass(LivingEntity.class,search,e->e!=player&&e.isAlive())){
     Vec3 toward=target.position().subtract(player.getEyePosition());
     if(toward.lengthSqr()>144.0D||toward.lengthSqr()<0.001D)continue;
     if(look.dot(toward.normalize())<0.45D)continue;
     Vec3 pull=player.position().add(0.0D,0.55D,0.0D).subtract(target.position()).normalize();
     target.setDeltaMovement(target.getDeltaMovement().add(pull.scale(1.15D)).add(0.0D,0.16D,0.0D));target.hurtMarked=true;
    }
    stand.startAttack();
   }
   player.getPersistentData().putLong("standout_verity_atk1_ready",now+200L);
  });
  context.setPacketHandled(true);
 }
}
