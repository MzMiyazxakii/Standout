package com.mzmiyazxakii.standout.network;
import com.mzmiyazxakii.standout.entity.TearProjectile;
import com.mzmiyazxakii.standout.entity.VerityStandEntity;
import net.minecraft.server.level.ServerPlayer;
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
   VerityStandEntity stand=stands.get(0);stand.startAttackTwo();
   Vec3 look=player.getLookAngle().normalize();
   Vec3 origin=stand.position().add(0.0D,0.55D,0.0D).add(look.scale(0.55D));
   double[] offsets={-0.075D,-0.025D,0.025D,0.075D};
   for(double offset:offsets){
    Vec3 direction=new Vec3(look.x+(-look.z*offset),look.y,look.z+(look.x*offset)).normalize();
    TearProjectile tear=new TearProjectile(player.level(),player);
    tear.setPos(origin.x,origin.y,origin.z);
    tear.shoot(direction.x,direction.y,direction.z,1.85F,0.0F);
    player.level().addFreshEntity(tear);
   }
   player.getPersistentData().putLong("standout_verity_atk2_ready",now+300L);
  });
  context.setPacketHandled(true);
 }
}