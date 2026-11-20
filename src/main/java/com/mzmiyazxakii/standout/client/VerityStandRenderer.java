package com.mzmiyazxakii.standout.client;
import com.mzmiyazxakii.standout.StandoutMod;
import com.mzmiyazxakii.standout.entity.VerityStandEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
public class VerityStandRenderer extends MobRenderer<VerityStandEntity,VerityAct1Model> {
 private static final ResourceLocation TEXTURE=new ResourceLocation(StandoutMod.MOD_ID,"textures/entity/verity_act1.png");
 private static final ResourceLocation ATTACK_TEXTURE=new ResourceLocation(StandoutMod.MOD_ID,"textures/entity/verity_act1_atk1.png");
 private static final ResourceLocation ATTACK_TWO_TEXTURE=new ResourceLocation(StandoutMod.MOD_ID,"textures/entity/verity_act1_atk2.png");
 public VerityStandRenderer(EntityRendererProvider.Context context){super(context,new VerityAct1Model(context.bakeLayer(VerityAct1Model.LAYER_LOCATION)),0.0F);}
 @Override public ResourceLocation getTextureLocation(VerityStandEntity entity){return entity.isSecondAttacking()?ATTACK_TWO_TEXTURE:(entity.isAttacking()?ATTACK_TEXTURE:TEXTURE);}
 @Override protected void scale(VerityStandEntity entity,com.mojang.blaze3d.vertex.PoseStack poseStack,float partialTick){poseStack.scale(0.65F,0.65F,0.65F);}
 @Override public boolean shouldRender(VerityStandEntity entity,net.minecraft.client.renderer.culling.Frustum frustum,double x,double y,double z){return true;}
}