package com.mzmiyazxakii.standout.network;
import com.mzmiyazxakii.standout.entity.VerityStandEntity;
import com.mzmiyazxakii.standout.registry.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.NetworkEvent;
import java.util.List;
import java.util.function.Supplier;
public class AdminTabletActionPacket {
 public final String action,target;
 public AdminTabletActionPacket(String action,String target){this.action=action;this.target=target;}
 public static void handle(AdminTabletActionPacket message,Supplier<NetworkEvent.Context> supplier){
  NetworkEvent.Context context=supplier.get();
  context.enqueueWork(()->{
   ServerPlayer sender=context.getSender();
   if(sender==null)return;
   if(!sender.hasPermissions(2)){sender.displayClientMessage(Component.translatable("gui.standout.tablet.denied"),true);return;}
   String name=message.target==null?"":message.target.trim();
   ServerPlayer target=name.isEmpty()?sender:sender.getServer().getPlayerList().getPlayerByName(name);
   if(target==null){sender.displayClientMessage(Component.literal("Игрок не найден: "+name),true);return;}
   switch(message.action){
    case "give_stand" -> {target.getPersistentData().putBoolean("standout_has_verity",true);target.displayClientMessage(Component.translatable("message.standout.verity_obtained"),true);notify(sender,target,"Верити выдан");}
    case "give_arrow" -> give(sender,target,new ItemStack(ModItems.MYSTERIOUS_ARROW.get()),"Стрела выдана");
    case "give_rokaka" -> give(sender,target,new ItemStack(ModItems.ROKAKA.get()),"Фрукт Рокака выдан");
    case "give_tablet" -> give(sender,target,new ItemStack(ModItems.ADMIN_TABLET.get()),"Планшет выдан");
    case "reset_cooldowns" -> {
     target.getPersistentData().putLong("standout_verity_atk1_ready",0L);
     target.getPersistentData().putLong("standout_verity_atk_ready",0L);
     target.getPersistentData().putLong("standout_verity_atk2_ready",0L);
     notify(sender,target,"Откаты сброшены");
    }
    case "remove_stand" -> {
     target.getPersistentData().putBoolean("standout_has_verity",false);
     List<VerityStandEntity> stands=target.level().getEntitiesOfClass(VerityStandEntity.class,new AABB(target.blockPosition()).inflate(64.0D),s->target.getUUID().equals(s.getOwnerId()));
     stands.forEach(VerityStandEntity::discard);notify(sender,target,"Стенд забран");
    }
   }
  });
  context.setPacketHandled(true);
 }
 private static void give(ServerPlayer sender,ServerPlayer target,ItemStack stack,String msg){
  if(!target.getInventory().add(stack))target.drop(stack,false);notify(sender,target,msg);
 }
 private static void notify(ServerPlayer sender,ServerPlayer target,String msg){
  sender.displayClientMessage(Component.literal(msg+" у "+target.getGameProfile().getName()),true);
 }
}