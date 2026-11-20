package com.mzmiyazxakii.standout.client;
import com.mzmiyazxakii.standout.StandoutMod;
import com.mzmiyazxakii.standout.entity.VerityStandEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
public class VerityAct1Model extends HierarchicalModel<VerityStandEntity> {
 public static final ModelLayerLocation LAYER_LOCATION=new ModelLayerLocation(new ResourceLocation(StandoutMod.MOD_ID,"verity_act1"),"main");
 private final ModelPart root;
 public VerityAct1Model(ModelPart root){this.root=root;}
 public static LayerDefinition createBodyLayer(){
  MeshDefinition mesh=new MeshDefinition();PartDefinition root=mesh.getRoot();
  root.addOrReplaceChild("Verity",CubeListBuilder.create()
   .texOffs(0,0).addBox(-4.0F,-8.0F,-4.0F,8.0F,8.0F,8.0F,new CubeDeformation(0.0F))
   .texOffs(12,24).addBox(-4.0F,-8.75F,-4.25F,8.0F,8.0F,0.0F,new CubeDeformation(0.0F)),PartPose.offset(0.0F,2.0F,0.0F));
  return LayerDefinition.create(mesh,32,32);
 }
 @Override public ModelPart root(){return root;}
 @Override public void setupAnim(VerityStandEntity entity,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){
  root.getAllParts().forEach(ModelPart::resetPose);
  animate(entity.summonAnimationState,VerityAct1Animation.SUMMON,ageInTicks);
  animate(entity.idleAnimationState,VerityAct1Animation.IDLE,ageInTicks);
  animate(entity.attackAnimationState,entity.isSecondAttacking()?VerityAct1Animation.ATK2:VerityAct1Animation.ATK,ageInTicks);
 }
}