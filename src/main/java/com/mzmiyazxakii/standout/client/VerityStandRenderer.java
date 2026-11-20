package com.mzmiyazxakii.standout.client;

import com.mzmiyazxakii.standout.StandoutMod;
import com.mzmiyazxakii.standout.entity.VerityStandEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class VerityStandRenderer extends MobRenderer<VerityStandEntity,EntityModel<VerityStandEntity>> {
 private static final ResourceLocation ACT1_TEXTURE=new ResourceLocation(StandoutMod.MOD_ID,"textures/entity/verity_act1.png");
 private static final ResourceLocation ACT1_ATTACK=new ResourceLocation(StandoutMod.MOD_ID,"textures/entity/verity_act1_atk1.png");
 private static final ResourceLocation ACT1_ATTACK_TWO=new ResourceLocation(StandoutMod.MOD_ID,"textures/entity/verity_act1_atk2.png");
 private static final ResourceLocation ACT2_TEXTURE=new ResourceLocation(StandoutMod.MOD_ID,"textures/entity/verity_act2_smile.png");
 private final VerityAct1Model act1;
 private final VerityAct2Model act2;
 public VerityStandRenderer(EntityRendererProvider.Context context){
  super(context,new VerityAct1Model(context.bakeLayer(VerityAct1Model.LAYER_LOCATION)),0.0F);
  this.act1=(VerityAct1Model)this.model;
  this.act2=new VerityAct2Model(context.bakeLayer(VerityAct2Model.LAYER_LOCATION));
 }
 @Override public ResourceLocation getTextureLocation(VerityStandEntity entity){
  if(entity.isActTwo())return ACT2_TEXTURE;
  return entity.isSecondAttacking()?ACT1_ATTACK_TWO:(entity.isAttacking()?ACT1_ATTACK:ACT1_TEXTURE);
 }
 @Override public void render(VerityStandEntity entity,float yaw,float partialTick,com.mojang.blaze3d.vertex.PoseStack poseStack,net.minecraft.client.renderer.MultiBufferSource buffer,int packedLight){
  this.model=entity.isActTwo()?act2:act1;
  super.render(entity,yaw,partialTick,poseStack,buffer,packedLight);
 }
 @Override protected void scale(VerityStandEntity entity,com.mojang.blaze3d.vertex.PoseStack poseStack,float partialTick){poseStack.scale(0.65F,0.65F,0.65F);}
 @Override public boolean shouldRender(VerityStandEntity entity,net.minecraft.client.renderer.culling.Frustum frustum,double x,double y,double z){return true;}
}