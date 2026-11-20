package com.mzmiyazxakii.standout.client;

import com.mzmiyazxakii.standout.StandoutMod;
import com.mzmiyazxakii.standout.entity.VerityStandEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class VerityAct2Model extends HierarchicalModel<VerityStandEntity> {
 public static final ModelLayerLocation LAYER_LOCATION=new ModelLayerLocation(new ResourceLocation(StandoutMod.MOD_ID,"verity_act2"),"main");
 private final ModelPart root;
 public VerityAct2Model(ModelPart root){this.root=root;}
 public static LayerDefinition createBodyLayer(){
  MeshDefinition mesh=new MeshDefinition();PartDefinition root=mesh.getRoot();
  PartDefinition stand=root.addOrReplaceChild("Verityact2",CubeListBuilder.create(),PartPose.offset(0.0F,7.0F,0.0F));
  PartDefinition headV=stand.addOrReplaceChild("headV",CubeListBuilder.create().texOffs(0,0).addBox(-4,-8,-4,8,8,8,new CubeDeformation(0)).texOffs(0,19).addBox(-4,-8.75F,-4.25F,8,8,0,new CubeDeformation(0)),PartPose.offsetAndRotation(0,-5,0,0,0,-0.0873F));
  PartDefinition torso=stand.addOrReplaceChild("TorsV",CubeListBuilder.create().texOffs(0,16).addBox(-5,0,-1,10,1,2,new CubeDeformation(0)).texOffs(12,31).addBox(-2.5F,1,-0.5F,5,5,1,new CubeDeformation(0)).texOffs(12,29).addBox(-6,0,-0.5F,1,1,1,new CubeDeformation(0)).texOffs(12,27).addBox(5,0,-0.5F,1,1,1,new CubeDeformation(0)),PartPose.offsetAndRotation(0,-3.75F,0,0,0,-0.1309F));
  torso.addOrReplaceChild("cube_r1",CubeListBuilder.create().texOffs(32,24).addBox(-1,0,-1,2,2,2,new CubeDeformation(0)),PartPose.offsetAndRotation(1.25F,1.5F,-0.5F,0,0,0.6545F));
  torso.addOrReplaceChild("cube_r2",CubeListBuilder.create().texOffs(16,19).addBox(-1,-2,-1,2,10,2,new CubeDeformation(0)),PartPose.offsetAndRotation(2.7965F,2.8904F,0,0,0,0.3491F));
  torso.addOrReplaceChild("cube_r3",CubeListBuilder.create().texOffs(16,19).addBox(-1,-2,-1,2,10,2,new CubeDeformation(0)),PartPose.offsetAndRotation(-2.7965F,2.8904F,-0.025F,0,0,-0.3491F));
  PartDefinition left=stand.addOrReplaceChild("LeftArmV",CubeListBuilder.create(),PartPose.offsetAndRotation(6,-3,0,-0.3491F,0,0));
  left.addOrReplaceChild("cube_r4",CubeListBuilder.create().texOffs(0,27).addBox(-1.0716F,2.8657F,-1.625F,3,3,3,new CubeDeformation(0)).texOffs(32,8).addBox(-1.0716F,0.8657F,-1.625F,3,1,3,new CubeDeformation(0)).texOffs(0,33).addBox(-0.0716F,-0.1343F,-0.625F,1,4,1,new CubeDeformation(0)),PartPose.offsetAndRotation(1.5F,0,-3,-1.0472F,0,-0.3491F));
  left.addOrReplaceChild("cube_r5",CubeListBuilder.create().texOffs(0,33).addBox(-0.5F,-0.25F,-0.5F,1,4,1,new CubeDeformation(0)).texOffs(32,8).addBox(-1.5F,2.75F,-1.5F,3,1,3,new CubeDeformation(0)).texOffs(32,8).addBox(-1.5F,0.75F,-1.5F,3,1,3,new CubeDeformation(0)),PartPose.offsetAndRotation(0,0,0,-1.1683F,0.1979F,-1.8619F));
  PartDefinition right=stand.addOrReplaceChild("RightArmV",CubeListBuilder.create(),PartPose.offsetAndRotation(-6,-3,0,-0.7854F,0,0));
  right.addOrReplaceChild("cube_r6",CubeListBuilder.create().texOffs(32,8).addBox(-2.8681F,-1.0088F,-1.5F,3,1,3,new CubeDeformation(0)).texOffs(0,33).addBox(-1.8681F,-4.0088F,-0.5F,1,4,1,new CubeDeformation(0)).texOffs(32,8).addBox(-2.8681F,-3.0088F,-1.5F,3,1,3,new CubeDeformation(0)),PartPose.offsetAndRotation(-0.25F,4,0,0,0,0.3927F));
  right.addOrReplaceChild("cube_r7",CubeListBuilder.create().texOffs(0,27).addBox(-1.9284F,2.6492F,-1.5F,3,3,3,new CubeDeformation(0)).texOffs(32,8).addBox(-1.9284F,0.6492F,-1.5F,3,1,3,new CubeDeformation(0)).texOffs(0,33).addBox(-0.9284F,-0.3508F,-0.5F,1,4,1,new CubeDeformation(0)),PartPose.offsetAndRotation(-1.25F,4,0,-0.5672F,0,0.3491F));
  return LayerDefinition.create(mesh,64,64);
 }
 @Override public ModelPart root(){return root;}
 @Override public void setupAnim(VerityStandEntity entity,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
  root.getAllParts().forEach(ModelPart::resetPose);
  animate(entity.summonAnimationState,VerityAct2Animation.SUMMON,ageInTicks);
  animate(entity.idleAnimationState,VerityAct2Animation.IDLE,ageInTicks);
  animate(entity.attackAnimationState,entity.isSecondAttacking()?VerityAct2Animation.SPELL:VerityAct2Animation.BLOOD_SHOT,ageInTicks);
 }
}
