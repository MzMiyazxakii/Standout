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
}
