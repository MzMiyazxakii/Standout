package com.mzmiyazxakii.standout.client;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

/** Animation set for Verity Act 2. Keyframe values are in seconds. */
public final class VerityAct2Animation {
 private VerityAct2Animation(){}
 public static final AnimationDefinition IDLE=AnimationDefinition.Builder.withLength(3.4583F).looping()
  .addAnimation("Verityact2",new AnimationChannel(AnimationChannel.Targets.POSITION,
   new Keyframe(0.0F,KeyframeAnimations.posVec(0,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(2.5833F,KeyframeAnimations.posVec(0,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(3.4583F,KeyframeAnimations.posVec(0,2,0),AnimationChannel.Interpolations.LINEAR)))
  .addAnimation("headV",new AnimationChannel(AnimationChannel.Targets.ROTATION,
   new Keyframe(0,KeyframeAnimations.degreeVec(0,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(1.25F,KeyframeAnimations.degreeVec(0,0,7.5F),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(3.4583F,KeyframeAnimations.degreeVec(0,0,0),AnimationChannel.Interpolations.LINEAR)))
  .addAnimation("TorsV",new AnimationChannel(AnimationChannel.Targets.POSITION,
   new Keyframe(0,KeyframeAnimations.posVec(0,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(1.9167F,KeyframeAnimations.posVec(0,-1,0),AnimationChannel.Interpolations.LINEAR))).build();
 public static final AnimationDefinition SUMMON=AnimationDefinition.Builder.withLength(0.2917F)
  .addAnimation("Verityact2",new AnimationChannel(AnimationChannel.Targets.POSITION,
   new Keyframe(0,KeyframeAnimations.posVec(-16,-4,-12),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.1667F,KeyframeAnimations.posVec(0,0,0),AnimationChannel.Interpolations.CATMULLROM)))
  .addAnimation("headV",new AnimationChannel(AnimationChannel.Targets.ROTATION,
   new Keyframe(0,KeyframeAnimations.degreeVec(0,0,7.5F),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.1667F,KeyframeAnimations.degreeVec(0,0,0),AnimationChannel.Interpolations.LINEAR))).build();
 public static final AnimationDefinition BARRAGE=AnimationDefinition.Builder.withLength(2.0417F)
  .addAnimation("LeftArmV",new AnimationChannel(AnimationChannel.Targets.ROTATION,
   new Keyframe(0,KeyframeAnimations.degreeVec(112.5F,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.25F,KeyframeAnimations.degreeVec(135,0,0),AnimationChannel.Interpolations.CATMULLROM),
   new Keyframe(0.4583F,KeyframeAnimations.degreeVec(-2.5F,0,0),AnimationChannel.Interpolations.CATMULLROM)))
  .addAnimation("RightArmV",new AnimationChannel(AnimationChannel.Targets.ROTATION,
   new Keyframe(0,KeyframeAnimations.degreeVec(60,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.25F,KeyframeAnimations.degreeVec(112.5F,0,0),AnimationChannel.Interpolations.CATMULLROM),
   new Keyframe(0.4583F,KeyframeAnimations.degreeVec(0,0,0),AnimationChannel.Interpolations.CATMULLROM)))
  .addAnimation("Verityact2",new AnimationChannel(AnimationChannel.Targets.POSITION,
   new Keyframe(0,KeyframeAnimations.posVec(0,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.25F,KeyframeAnimations.posVec(0,0,-3),AnimationChannel.Interpolations.CATMULLROM),
   new Keyframe(0.2917F,KeyframeAnimations.posVec(0,0,0),AnimationChannel.Interpolations.LINEAR))).build();
}