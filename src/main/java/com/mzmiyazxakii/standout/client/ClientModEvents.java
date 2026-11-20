package com.mzmiyazxakii.standout.client;
import com.mzmiyazxakii.standout.registry.ModEntities;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid="standout",value=Dist.CLIENT,bus=Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {
 @SubscribeEvent public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
  event.registerLayerDefinition(VerityAct1Model.LAYER_LOCATION,VerityAct1Model::createBodyLayer);
  event.registerLayerDefinition(VerityAct2Model.LAYER_LOCATION,VerityAct2Model::createBodyLayer);
 }
 @SubscribeEvent public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
  event.registerEntityRenderer(ModEntities.VERITY_STAND.get(),VerityStandRenderer::new);
  event.registerEntityRenderer(ModEntities.TEAR_PROJECTILE.get(),context->new ThrownItemRenderer<>(context,1.0F,true));
 }
}