package com.mzmiyazxakii.standout.registry;
import com.mzmiyazxakii.standout.entity.VerityStandEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid="standout",bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModAttributes {
 @SubscribeEvent public static void registerAttributes(EntityAttributeCreationEvent event){event.put(ModEntities.VERITY_STAND.get(),VerityStandEntity.createAttributes().build());}
}
