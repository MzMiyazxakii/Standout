package com.mzmiyazxakii.standout.network;
import com.mzmiyazxakii.standout.entity.VerityStandEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.AABB;
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
   stands.get(0).startAttackTwo();
   player.getPersistentData().putLong("standout_verity_atk2_ready",now+300L);
  });
  context.setPacketHandled(true);
 }
}