package com.mzmiyazxakii.standout.registry;
import com.mzmiyazxakii.standout.StandoutMod;
import com.mzmiyazxakii.standout.item.MysteriousArrowItem;
import com.mzmiyazxakii.standout.item.RokakaItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
public class ModItems {
 public static final DeferredRegister<Item> ITEMS=DeferredRegister.create(ForgeRegistries.ITEMS,StandoutMod.MOD_ID);
 public static final RegistryObject<Item> MYSTERIOUS_ARROW=ITEMS.register("mysterious_arrow",()->new MysteriousArrowItem(new Item.Properties().stacksTo(16)));
 public static final RegistryObject<Item> ROKAKA=ITEMS.register("rokaka",()->new RokakaItem(new Item.Properties().stacksTo(16)));
}