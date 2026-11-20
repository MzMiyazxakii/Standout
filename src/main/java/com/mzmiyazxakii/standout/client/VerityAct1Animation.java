package com.mzmiyazxakii.standout.client;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
public class VerityAct1Animation {
 public static final AnimationDefinition IDLE=AnimationDefinition.Builder.withLength(4.0F).looping()
  .addAnimation("Verity",new AnimationChannel(AnimationChannel.Targets.POSITION,
   new Keyframe(0.0F,KeyframeAnimations.posVec(0.0F,0.0F,0.0F),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(2.0F,KeyframeAnimations.posVec(0.0F,2.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
   new Keyframe(4.0F,KeyframeAnimations.posVec(0.0F,-0.31F,0.0F),AnimationChannel.Interpolations.CATMULLROM))).build();
 public static final AnimationDefinition SUMMON=AnimationDefinition.Builder.withLength(0.2917F)
  .addAnimation("Verity",new AnimationChannel(AnimationChannel.Targets.POSITION,
   new Keyframe(0.0F,KeyframeAnimations.posVec(-14.0F,-10.0F,-11.0F),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.25F,KeyframeAnimations.posVec(1.0F,0.0F,1.0F),AnimationChannel.Interpolations.LINEAR))).build();
 public static final AnimationDefinition ATK=AnimationDefinition.Builder.withLength(1.5F)
  .addAnimation("Verity",new AnimationChannel(AnimationChannel.Targets.ROTATION,
   new Keyframe(0.0F,KeyframeAnimations.degreeVec(0.0F,0.0F,0.0F),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.5F,KeyframeAnimations.degreeVec(-27.5F,0.0F,0.0F),AnimationChannel.Interpolations.LINEAR)))
  .addAnimation("Verity",new AnimationChannel(AnimationChannel.Targets.SCALE,
   new Keyframe(0.0F,KeyframeAnimations.scaleVec(1.0F,1.0F,1.0F),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.9583F,KeyframeAnimations.scaleVec(1.1F,1.1F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
   new Keyframe(1.5F,KeyframeAnimations.scaleVec(1.2F,1.2F,1.1F),AnimationChannel.Interpolations.CATMULLROM))).build();
 public static final AnimationDefinition ATK2=AnimationDefinition.Builder.withLength(2.0F)
  .addAnimation("Verity",new AnimationChannel(AnimationChannel.Targets.POSITION,
   new Keyframe(0.0F,KeyframeAnimations.posVec(0.0F,0.0F,0.0F),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.45F,KeyframeAnimations.posVec(0.0F,0.0F,-4.0F),AnimationChannel.Interpolations.CATMULLROM),
   new Keyframe(1.5F,KeyframeAnimations.posVec(0.0F,0.0F,-4.0F),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(2.0F,KeyframeAnimations.posVec(0.0F,0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM))).build();
}