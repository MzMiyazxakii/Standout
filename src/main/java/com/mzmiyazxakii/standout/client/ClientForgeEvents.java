package com.mzmiyazxakii.standout.client;
import com.mzmiyazxakii.standout.network.ModNetwork;
import com.mzmiyazxakii.standout.network.SummonStandPacket;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
@Mod.EventBusSubscriber(modid="standout",value=Dist.CLIENT,bus=Mod.EventBusSubscriber.Bus.MOD)
public class ClientForgeEvents {
 public static KeyMapping SUMMON_KEY;
 @SubscribeEvent public static void registerKeys(RegisterKeyMappingsEvent event){SUMMON_KEY=new KeyMapping("key.standout.summon",GLFW.GLFW_KEY_U,"key.categories.standout");event.register(SUMMON_KEY);}
 @Mod.EventBusSubscriber(modid="standout",value=Dist.CLIENT,bus=Mod.EventBusSubscriber.Bus.FORGE)
 public static class ClientTicks {
  @SubscribeEvent public static void onClientTick(TickEvent.ClientTickEvent event){if(event.phase==TickEvent.Phase.END&&SUMMON_KEY!=null)while(SUMMON_KEY.consumeClick())ModNetwork.CHANNEL.sendToServer(new SummonStandPacket());}
 }
}
