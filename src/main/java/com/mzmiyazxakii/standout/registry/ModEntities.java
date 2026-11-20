package com.mzmiyazxakii.standout.registry;
import com.mzmiyazxakii.standout.StandoutMod;
import com.mzmiyazxakii.standout.entity.VerityStandEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
public class ModEntities {
 public static final DeferredRegister<EntityType<?>> ENTITIES=DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,StandoutMod.MOD_ID);
 public static final RegistryObject<EntityType<VerityStandEntity>> VERITY_STAND=ENTITIES.register("verity_stand",()->EntityType.Builder.<VerityStandEntity>of(VerityStandEntity::new,MobCategory.MISC).sized(0.1F,0.1F).clientTrackingRange(10).updateInterval(1).build("verity_stand"));
}
