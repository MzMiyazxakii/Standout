package com.mzmiyazxakii.standout.registry;
import com.mzmiyazxakii.standout.StandoutMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
public class ModSounds {
 public static final DeferredRegister<SoundEvent> SOUNDS=DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,StandoutMod.MOD_ID);
 public static final RegistryObject<SoundEvent> VERITY_SUMMON=SOUNDS.register("verity_summon",()->SoundEvent.createVariableRangeEvent(new ResourceLocation(StandoutMod.MOD_ID,"verity_summon")));
}