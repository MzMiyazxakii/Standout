package com.mzmiyazxakii.standout.network;
import com.mzmiyazxakii.standout.StandoutMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
public class ModNetwork {
 private static final String VERSION="1";
 public static final SimpleChannel CHANNEL=NetworkRegistry.newSimpleChannel(new ResourceLocation(StandoutMod.MOD_ID,"main"),()->VERSION,VERSION::equals,VERSION::equals);
 public static void register(){CHANNEL.messageBuilder(SummonStandPacket.class,0).encoder((msg,buf)->{}).decoder(buf->new SummonStandPacket()).consumerMainThread(SummonStandPacket::handle).add();}
}
