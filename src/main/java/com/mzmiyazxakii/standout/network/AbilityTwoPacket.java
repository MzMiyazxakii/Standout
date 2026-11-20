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
   Vec3 right=new Vec3(-look.z,0.0D,look.x).normalize();
   Vec3 origin=player.position().add(0.0D,1.0D,0.0D).add(look.scale(1.6D));
   double[] lanes={-0.27D,-0.09D,0.09D,0.27D};
   double[] spreads={-0.20D,-0.07D,0.07D,0.20D};
   for(int i=0;i<4;i++){
    Vec3 spawn=origin.add(right.scale(lanes[i]));
    Vec3 direction=look.add(right.scale(spreads[i])).normalize();
    TearProjectile tear=new TearProjectile(player.level(),player);
    tear.setPos(spawn.x,spawn.y,spawn.z);
    tear.shoot(direction.x,direction.y,direction.z,1.85F,0.0F);
    player.level().addFreshEntity(tear);
   }
   player.getPersistentData().putLong("standout_verity_atk2_ready",now+300L);
  });
  context.setPacketHandled(true);
 }
}