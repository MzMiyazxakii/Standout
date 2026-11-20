package com.mzmiyazxakii.standout.client;
import com.mzmiyazxakii.standout.network.AdminTabletActionPacket;
import com.mzmiyazxakii.standout.network.ModNetwork;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
public class AdminTabletScreen extends Screen {
 private EditBox target;
 public AdminTabletScreen(){super(Component.translatable("gui.standout.tablet.title"));}
 @Override protected void init(){
  int left=(width-260)/2,top=(height-150)/2;
  target=new EditBox(font,left,top+24,260,20,Component.translatable("gui.standout.tablet.target"));
  target.setMaxLength(64);
  target.setHint(Component.translatable("gui.standout.tablet.target"));
  addRenderableWidget(target);
  addRenderableWidget(Button.builder(Component.translatable("gui.standout.tablet.give"),b->send("give_stand")).bounds(left,top+55,126,22).build());
  addRenderableWidget(Button.builder(Component.translatable("gui.standout.tablet.arrow"),b->send("give_arrow")).bounds(left+134,top+55,126,22).build());
  addRenderableWidget(Button.builder(Component.translatable("gui.standout.tablet.reset"),b->send("reset_cooldowns")).bounds(left,top+83,126,22).build());
  addRenderableWidget(Button.builder(Component.translatable("gui.standout.tablet.remove"),b->send("remove_stand")).bounds(left+134,top+83,126,22).build());
 }
 private void send(String action){ModNetwork.CHANNEL.sendToServer(new AdminTabletActionPacket(action,target==null?"":target.getValue()));}
 @Override public void render(GuiGraphics graphics,int mouseX,int mouseY,float partialTick){
  renderBackground(graphics);
  int left=(width-280)/2,top=(height-160)/2;
  graphics.fill(left,top,left+280,top+160,0xEE101A2A);
  graphics.fill(left+2,top+2,left+278,top+4,0xFF21D9F2);
  graphics.drawCenteredString(font,title,width/2,top+8,0xFFB8F7FF);
  graphics.drawCenteredString(font,Component.literal("Оставь поле пустым, чтобы выбрать себя"),width/2,top+132,0xFF9BB7C8);
  super.render(graphics,mouseX,mouseY,partialTick);
 }
 @Override public boolean isPauseScreen(){return false;}
}