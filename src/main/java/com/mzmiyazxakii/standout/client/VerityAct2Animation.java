package com.mzmiyazxakii.standout.client;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public final class VerityAct2Animation {
 private VerityAct2Animation() {}
 public static final AnimationDefinition IDLE=AnimationDefinition.Builder.withLength(3.4583F).looping()
  .addAnimation("Verityact2",new AnimationChannel(AnimationChannel.Targets.POSITION,
   new Keyframe(0F,KeyframeAnimations.posVec(0,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(2.5833F,KeyframeAnimations.posVec(0,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(3.4583F,KeyframeAnimations.posVec(0,2,0),AnimationChannel.Interpolations.LINEAR)))
  .addAnimation("headV",new AnimationChannel(AnimationChannel.Targets.ROTATION,
   new Keyframe(0F,KeyframeAnimations.degreeVec(0,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(1.25F,KeyframeAnimations.degreeVec(0,0,7.5F),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(3.4583F,KeyframeAnimations.degreeVec(0,0,0),AnimationChannel.Interpolations.LINEAR)))
  .addAnimation("headV",new AnimationChannel(AnimationChannel.Targets.POSITION,
   new Keyframe(0F,KeyframeAnimations.posVec(0,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(1.25F,KeyframeAnimations.posVec(0,-1,0),AnimationChannel.Interpolations.LINEAR)))
  .addAnimation("TorsV",new AnimationChannel(AnimationChannel.Targets.POSITION,
   new Keyframe(0F,KeyframeAnimations.posVec(0,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(1.9167F,KeyframeAnimations.posVec(0,-1,0),AnimationChannel.Interpolations.LINEAR)))
  .addAnimation("LeftArmV",new AnimationChannel(AnimationChannel.Targets.POSITION,
   new Keyframe(0F,KeyframeAnimations.posVec(0,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(1.9167F,KeyframeAnimations.posVec(0,-1,0),AnimationChannel.Interpolations.LINEAR)))
  .addAnimation("RightArmV",new AnimationChannel(AnimationChannel.Targets.POSITION,
   new Keyframe(0F,KeyframeAnimations.posVec(0,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(1.9167F,KeyframeAnimations.posVec(0,-1,0),AnimationChannel.Interpolations.LINEAR))).build();

 public static final AnimationDefinition SUMMON=AnimationDefinition.Builder.withLength(0.2917F)
  .addAnimation("Verityact2",new AnimationChannel(AnimationChannel.Targets.POSITION,
   new Keyframe(0F,KeyframeAnimations.posVec(-16,-4,-12),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.1667F,KeyframeAnimations.posVec(0,0,0),AnimationChannel.Interpolations.CATMULLROM)))
  .addAnimation("headV",new AnimationChannel(AnimationChannel.Targets.ROTATION,
   new Keyframe(0F,KeyframeAnimations.degreeVec(0,0,7.5F),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.1667F,KeyframeAnimations.degreeVec(0,0,0),AnimationChannel.Interpolations.LINEAR)))
  .addAnimation("TorsV",new AnimationChannel(AnimationChannel.Targets.ROTATION,
   new Keyframe(0F,KeyframeAnimations.degreeVec(0,0,7.5F),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.1667F,KeyframeAnimations.degreeVec(0,0,0),AnimationChannel.Interpolations.LINEAR)))
  .addAnimation("LeftArmV",new AnimationChannel(AnimationChannel.Targets.ROTATION,
   new Keyframe(0F,KeyframeAnimations.degreeVec(87.5F,-62.5F,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.1667F,KeyframeAnimations.degreeVec(0,0,0),AnimationChannel.Interpolations.LINEAR)))
  .addAnimation("RightArmV",new AnimationChannel(AnimationChannel.Targets.ROTATION,
   new Keyframe(0F,KeyframeAnimations.degreeVec(60,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.1667F,KeyframeAnimations.degreeVec(0,0,0),AnimationChannel.Interpolations.LINEAR))).build();

 public static final AnimationDefinition BARRAGE=AnimationDefinition.Builder.withLength(2.0417F)
  .addAnimation("Verityact2",new AnimationChannel(AnimationChannel.Targets.POSITION,
   new Keyframe(0F,KeyframeAnimations.posVec(0,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.25F,KeyframeAnimations.posVec(0,0,-3),AnimationChannel.Interpolations.CATMULLROM),
   new Keyframe(0.2917F,KeyframeAnimations.posVec(0,0,0),AnimationChannel.Interpolations.LINEAR)))
  .addAnimation("LeftArmV",new AnimationChannel(AnimationChannel.Targets.ROTATION,
   new Keyframe(0F,KeyframeAnimations.degreeVec(112.5F,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.25F,KeyframeAnimations.degreeVec(135,0,0),AnimationChannel.Interpolations.CATMULLROM),
   new Keyframe(0.4583F,KeyframeAnimations.degreeVec(-2.5F,0,0),AnimationChannel.Interpolations.CATMULLROM)))
  .addAnimation("RightArmV",new AnimationChannel(AnimationChannel.Targets.ROTATION,
   new Keyframe(0F,KeyframeAnimations.degreeVec(60,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.25F,KeyframeAnimations.degreeVec(112.5F,0,0),AnimationChannel.Interpolations.CATMULLROM),
   new Keyframe(0.4583F,KeyframeAnimations.degreeVec(0,0,0),AnimationChannel.Interpolations.CATMULLROM))).build();

 public static final AnimationDefinition BLOOD_SHOT=AnimationDefinition.Builder.withLength(1.25F)
  .addAnimation("Verityact2",new AnimationChannel(AnimationChannel.Targets.POSITION,
   new Keyframe(0F,KeyframeAnimations.posVec(0,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.3333F,KeyframeAnimations.posVec(-16,0,-22),AnimationChannel.Interpolations.LINEAR)))
  .addAnimation("headV",new AnimationChannel(AnimationChannel.Targets.ROTATION,
   new Keyframe(0F,KeyframeAnimations.degreeVec(0,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.3333F,KeyframeAnimations.degreeVec(5,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.5F,KeyframeAnimations.degreeVec(-20,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.6667F,KeyframeAnimations.degreeVec(10,0,0),AnimationChannel.Interpolations.LINEAR)))
  .addAnimation("headV",new AnimationChannel(AnimationChannel.Targets.SCALE,
   new Keyframe(0F,KeyframeAnimations.scaleVec(1,1,1),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.375F,KeyframeAnimations.scaleVec(1.6552F,1.2552F,1.3F),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.6667F,KeyframeAnimations.scaleVec(1,1,1),AnimationChannel.Interpolations.LINEAR)))
  .addAnimation("LeftArmV",new AnimationChannel(AnimationChannel.Targets.ROTATION,
   new Keyframe(0F,KeyframeAnimations.degreeVec(0,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.3333F,KeyframeAnimations.degreeVec(117.5F,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.5833F,KeyframeAnimations.degreeVec(116.6606F,3.4553F,-27.2988F),AnimationChannel.Interpolations.LINEAR)))
  .addAnimation("RightArmV",new AnimationChannel(AnimationChannel.Targets.ROTATION,
   new Keyframe(0F,KeyframeAnimations.degreeVec(0,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.3333F,KeyframeAnimations.degreeVec(97.5F,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.5833F,KeyframeAnimations.degreeVec(95.7658F,-15.7442F,12.4933F),AnimationChannel.Interpolations.LINEAR))).build();

 public static final AnimationDefinition SPELL=AnimationDefinition.Builder.withLength(0.9167F)
  .addAnimation("headV",new AnimationChannel(AnimationChannel.Targets.ROTATION,
   new Keyframe(0F,KeyframeAnimations.degreeVec(0,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.4583F,KeyframeAnimations.degreeVec(0,0,5),AnimationChannel.Interpolations.LINEAR)))
  .addAnimation("TorsV",new AnimationChannel(AnimationChannel.Targets.ROTATION,
   new Keyframe(0F,KeyframeAnimations.degreeVec(0,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.4583F,KeyframeAnimations.degreeVec(0,0,5),AnimationChannel.Interpolations.LINEAR)))
  .addAnimation("LeftArmV",new AnimationChannel(AnimationChannel.Targets.ROTATION,
   new Keyframe(0F,KeyframeAnimations.degreeVec(0,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.4583F,KeyframeAnimations.degreeVec(0,-55,0),AnimationChannel.Interpolations.LINEAR)))
  .addAnimation("RightArmV",new AnimationChannel(AnimationChannel.Targets.ROTATION,
   new Keyframe(0F,KeyframeAnimations.degreeVec(0,0,0),AnimationChannel.Interpolations.LINEAR),
   new Keyframe(0.4583F,KeyframeAnimations.degreeVec(12.5F,0,42.5F),AnimationChannel.Interpolations.LINEAR))).build();
}
