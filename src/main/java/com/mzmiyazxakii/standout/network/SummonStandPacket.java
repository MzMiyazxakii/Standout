package com.mzmiyazxakii.standout.network;
import com.mzmiyazxakii.standout.entity.VerityStandEntity;
import com.mzmiyazxakii.standout.registry.ModEntities;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;
public class SummonStandPacket {
 public static void handle(SummonStandPacket message,Supplier<NetworkEvent.Context> supplier){
  NetworkEvent.Context context=supplier.get();
  context.enqueueWork(()->{
   ServerPlayer player=context.getSender();
   if(player==null||!player.getPersistentData().getBoolean("standout_has_verity"))return;
   var existing=player.level().getEntitiesOfClass(VerityStandEntity.class,new AABB(player.blockPosition()).inflate(24),stand->player.getUUID().equals(stand.getOwnerId()));
   if(!existing.isEmpty())existing.forEach(VerityStandEntity::discard);
   else {VerityStandEntity stand=ModEntities.VERITY_STAND.get().create(player.level());if(stand!=null){stand.setOwner(player);stand.moveTo(player.getX(),player.getY()+1.0D,player.getZ(),player.getYRot(),0.0F);player.level().addFreshEntity(stand);}}
  });
  context.setPacketHandled(true);
 }
}
