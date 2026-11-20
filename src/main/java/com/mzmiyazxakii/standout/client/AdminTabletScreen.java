package com.mzmiyazxakii.standout.client;

import com.mzmiyazxakii.standout.network.AdminTabletActionPacket;
import com.mzmiyazxakii.standout.network.ModNetwork;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.multiplayer.PlayerInfo;
import java.util.ArrayList;
import java.util.List;

public class AdminTabletScreen extends Screen {
 private String target="";
 private List<String> players=List.of();
 private int selectedPlayer=0;
 private String page="main";
 public AdminTabletScreen(){super(Component.translatable("gui.standout.tablet.title"));}
 @Override protected void init(){
  refreshPlayers();
  int left=(width-260)/2,top=(height-170)/2;
  if(!players.isEmpty()) target=players.get(Math.min(selectedPlayer,players.size()-1));
  if(page.equals("main")){
   addRenderableWidget(Button.builder(Component.literal("Игрок: "+(target.isEmpty()?"никого":target)+"  ‹ ›"),b->cyclePlayer()).bounds(left,top+24,260,22).build());
   addRenderableWidget(Button.builder(Component.literal("Выдать стенд"),b->{page="stands";rebuildWidgets();}).bounds(left,top+54,260,22).build());
   addRenderableWidget(Button.builder(Component.literal("Забрать стенд"),b->send("remove_stand")).bounds(left,top+82,260,22).build());
   addRenderableWidget(Button.builder(Component.literal("Сбросить откаты"),b->send("reset_cooldowns")).bounds(left,top+110,260,22).build());
   addRenderableWidget(Button.builder(Component.literal("Выдать предмет"),b->{page="items";rebuildWidgets();}).bounds(left,top+138,260,22).build());
  }else if(page.equals("stands")){
   addRenderableWidget(Button.builder(Component.literal("Верити"),b->send("give_stand")).bounds(left,top+40,260,24).build());
   addRenderableWidget(Button.builder(Component.literal("‹ Назад"),b->{page="main";rebuildWidgets();}).bounds(left,top+74,260,22).build());
  }else{
   addRenderableWidget(Button.builder(Component.literal("Загадочная стрела"),b->send("give_arrow")).bounds(left,top+34,260,22).build());
   addRenderableWidget(Button.builder(Component.literal("Фрукт Рокака"),b->send("give_rokaka")).bounds(left,top+60,260,22).build());
   addRenderableWidget(Button.builder(Component.literal("Планшет"),b->send("give_tablet")).bounds(left,top+86,260,22).build());
   addRenderableWidget(Button.builder(Component.literal("‹ Назад"),b->{page="main";rebuildWidgets();}).bounds(left,top+116,260,22).build());
  }
 }
 private void refreshPlayers(){
  if(Minecraft.getInstance().getConnection()!=null){
   List<String> names=new ArrayList<>();
   for(PlayerInfo info:Minecraft.getInstance().getConnection().getOnlinePlayers()) names.add(info.getProfile().getName());
   players=names;
   if(!players.isEmpty()&&target.isEmpty()) target=players.get(0);
  }
 }
 private void cyclePlayer(){
  refreshPlayers();
  if(!players.isEmpty()){selectedPlayer=(selectedPlayer+1)%players.size();target=players.get(selectedPlayer);}
  rebuildWidgets();
 }
 private void send(String action){
  ModNetwork.CHANNEL.sendToServer(new AdminTabletActionPacket(action,target));
  if(action.equals("give_stand")||action.equals("give_arrow")||action.equals("give_rokaka")||action.equals("give_tablet")){page="main";rebuildWidgets();}
 }
 @Override public void render(GuiGraphics g,int mx,int my,float pt){
  renderBackground(g);
  int left=(width-280)/2,top=(height-190)/2;
  g.fill(left,top,left+280,top+190,0xEE101A2A);
  g.fill(left+2,top+2,left+278,top+4,0xFF21D9F2);
  g.drawCenteredString(font,title,width/2,top+8,0xFFB8F7FF);
  g.drawCenteredString(font,Component.literal(page.equals("main")?"Выбери игрока и действие":page.equals("stands")?"Выбор стенда":"Выбор предмета"),width/2,top+topOffset(),0xFF9BB7C8);
  super.render(g,mx,my,pt);
 }
 private int topOffset(){return 28;}
 @Override public boolean isPauseScreen(){return false;}
}