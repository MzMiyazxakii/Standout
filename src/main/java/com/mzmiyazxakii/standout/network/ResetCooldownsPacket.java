package com.mzmiyazxakii.standout.network;

import com.mzmiyazxakii.standout.client.ClientForgeEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public class ResetCooldownsPacket {
 public static void handle(ResetCooldownsPacket message,Supplier<NetworkEvent.Context> supplier){
  NetworkEvent.Context context=supplier.get();
  context.enqueueWork(()->DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()->()->ClientForgeEvents.ClientTicks.resetCooldowns()));
  context.setPacketHandled(true);
 }
}