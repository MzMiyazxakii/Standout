package com.mzmiyazxakii.standout.client;
import com.mzmiyazxakii.standout.network.AbilityPacket;
import com.mzmiyazxakii.standout.network.AbilityTwoPacket;
import com.mzmiyazxakii.standout.network.ModNetwork;
import com.mzmiyazxakii.standout.network.SummonStandPacket;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
@Mod.EventBusSubscriber(modid="standout",value=Dist.CLIENT,bus=Mod.EventBusSubscriber.Bus.MOD)
public class ClientForgeEvents {
 public static KeyMapping SUMMON_KEY,ABILITY_KEY,ABILITY_TWO_KEY;
 @SubscribeEvent public static void registerKeys(RegisterKeyMappingsEvent event){
  SUMMON_KEY=new KeyMapping("key.standout.summon",GLFW.GLFW_KEY_U,"key.categories.standout");
  ABILITY_KEY=new KeyMapping("key.standout.verity_ability",GLFW.GLFW_KEY_Z,"key.categories.standout");
  ABILITY_TWO_KEY=new KeyMapping("key.standout.verity_ability_two",GLFW.GLFW_KEY_X,"key.categories.standout");
  event.register(SUMMON_KEY);event.register(ABILITY_KEY);event.register(ABILITY_TWO_KEY);
 }
 @Mod.EventBusSubscriber(modid="standout",value=Dist.CLIENT,bus=Mod.EventBusSubscriber.Bus.FORGE)
 public static class ClientTicks {
  private static long abilityOneReadyAt=0L,abilityTwoReadyAt=0L;
  @SubscribeEvent public static void onClientTick(TickEvent.ClientTickEvent event){
   if(event.phase!=TickEvent.Phase.END)return;
   Minecraft mc=Minecraft.getInstance();
   if(mc.player==null||mc.level==null){abilityOneReadyAt=0L;abilityTwoReadyAt=0L;return;}
   if(SUMMON_KEY!=null)while(SUMMON_KEY.consumeClick())ModNetwork.CHANNEL.sendToServer(new SummonStandPacket());
   if(ABILITY_KEY!=null)while(ABILITY_KEY.consumeClick()){
    if(mc.level.getGameTime()>=abilityOneReadyAt){ModNetwork.CHANNEL.sendToServer(new AbilityPacket());abilityOneReadyAt=mc.level.getGameTime()+200L;}
   }
   if(ABILITY_TWO_KEY!=null)while(ABILITY_TWO_KEY.consumeClick()){
    if(mc.level.getGameTime()>=abilityTwoReadyAt){ModNetwork.CHANNEL.sendToServer(new AbilityTwoPacket());abilityTwoReadyAt=mc.level.getGameTime()+300L;}
   }
  }
  @SubscribeEvent public static void drawCooldowns(RenderGuiOverlayEvent.Post event){
   Minecraft mc=Minecraft.getInstance();if(mc.player==null||mc.level==null)return;
   long now=mc.level.getGameTime();String first=remaining(now,abilityOneReadyAt),second=remaining(now,abilityTwoReadyAt);
   var font=mc.font;var gui=event.getGuiGraphics();
   int x=event.getWindow().getGuiScaledWidth()-Math.max(font.width("Z: "+first),font.width("X: "+second))-8;
   int y=event.getWindow().getGuiScaledHeight()-font.lineHeight*2-8;
   gui.drawString(font,Component.literal("Z: "+first),x,y,0xFFFFFF,true);
   gui.drawString(font,Component.literal("X: "+second),x,y+font.lineHeight,0xFFFFFF,true);
  }
  private static String remaining(long now,long until){if(until<=now)return "Готово";return String.format(java.util.Locale.ROOT,"%.1fс",(until-now)/20.0D);}
 }
}