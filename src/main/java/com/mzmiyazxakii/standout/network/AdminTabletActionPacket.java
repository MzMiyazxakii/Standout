package com.mzmiyazxakii.standout.network;
import com.mzmiyazxakii.standout.entity.VerityStandEntity;
import com.mzmiyazxakii.standout.registry.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.NetworkEvent;
import java.util.List;
import java.util.function.Supplier;
public class AdminTabletActionPacket {
 public final String action;
 public final String target;
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
    case "give_stand" -> {
     target.getPersistentData().putBoolean("standout_has_verity",true);
     target.displayClientMessage(Component.translatable("message.standout.verity_obtained"),true);
     sender.displayClientMessage(Component.literal("Верити выдан игроку "+target.getGameProfile().getName()),true);
    }
    case "give_arrow" -> {
     ItemStack arrow=new ItemStack(ModItems.MYSTERIOUS_ARROW.get());
     if(!target.getInventory().add(arrow))target.drop(arrow,false);
     sender.displayClientMessage(Component.literal("Стрела выдана игроку "+target.getGameProfile().getName()),true);
    }
    case "reset_cooldowns" -> {
     target.getPersistentData().putLong("standout_verity_atk1_ready",0L);
     target.getPersistentData().putLong("standout_verity_atk2_ready",0L);
     sender.displayClientMessage(Component.literal("Откаты сброшены: "+target.getGameProfile().getName()),true);
    }
    case "remove_stand" -> {
     target.getPersistentData().putBoolean("standout_has_verity",false);
     List<VerityStandEntity> stands=target.level().getEntitiesOfClass(VerityStandEntity.class,new AABB(target.blockPosition()).inflate(64.0D),s->target.getUUID().equals(s.getOwnerId()));
     stands.forEach(VerityStandEntity::discard);
     sender.displayClientMessage(Component.literal("Стенд удалён у "+target.getGameProfile().getName()),true);
    }
   }
  });
  context.setPacketHandled(true);
 }
}