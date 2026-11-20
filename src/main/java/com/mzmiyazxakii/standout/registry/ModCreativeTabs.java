package com.mzmiyazxakii.standout.registry;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid="standout",bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTabs {
 @SubscribeEvent public static void addItems(BuildCreativeModeTabContentsEvent event){
  if(event.getTabKey()==CreativeModeTabs.TOOLS_AND_UTILITIES){event.accept(ModItems.MYSTERIOUS_ARROW);event.accept(ModItems.ADMIN_TABLET);event.accept(ModItems.SAINT_HEART);}
 }
}