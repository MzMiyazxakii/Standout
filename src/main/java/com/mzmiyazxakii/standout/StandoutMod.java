package com.mzmiyazxakii.standout;
import com.mzmiyazxakii.standout.network.ModNetwork;
import com.mzmiyazxakii.standout.registry.ModEntities;
import com.mzmiyazxakii.standout.registry.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
@Mod(StandoutMod.MOD_ID)
public class StandoutMod {
 public static final String MOD_ID="standout";
 public StandoutMod(){ IEventBus bus=FMLJavaModLoadingContext.get().getModEventBus(); ModItems.ITEMS.register(bus); ModEntities.ENTITIES.register(bus); ModNetwork.register(); }
}
