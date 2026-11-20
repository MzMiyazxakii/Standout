package com.mzmiyazxakii.standout.network;
import com.mzmiyazxakii.standout.StandoutMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
public class ModNetwork {
 private static final String VERSION="1";
 public static final SimpleChannel CHANNEL=NetworkRegistry.newSimpleChannel(new ResourceLocation(StandoutMod.MOD_ID,"main"),()->VERSION,VERSION::equals,VERSION::equals);
 public static void register(){
  CHANNEL.messageBuilder(SummonStandPacket.class,0).encoder((msg,buf)->{}).decoder(buf->new SummonStandPacket()).consumerMainThread(SummonStandPacket::handle).add();
  CHANNEL.messageBuilder(AbilityPacket.class,1).encoder((msg,buf)->{}).decoder(buf->new AbilityPacket()).consumerMainThread(AbilityPacket::handle).add();
  CHANNEL.messageBuilder(AbilityTwoPacket.class,2).encoder((msg,buf)->{}).decoder(buf->new AbilityTwoPacket()).consumerMainThread(AbilityTwoPacket::handle).add();
  CHANNEL.messageBuilder(AdminTabletActionPacket.class,3).encoder((msg,buf)->{buf.writeUtf(msg.action);buf.writeUtf(msg.target);}).decoder(buf->new AdminTabletActionPacket(buf.readUtf(32),buf.readUtf(64))).consumerMainThread(AdminTabletActionPacket::handle).add();
  CHANNEL.messageBuilder(ResetCooldownsPacket.class,4).encoder((msg,buf)->{}).decoder(buf->new ResetCooldownsPacket()).consumerMainThread(ResetCooldownsPacket::handle).add();
 }
}