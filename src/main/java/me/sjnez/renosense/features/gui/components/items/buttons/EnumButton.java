package me.sjnez.renosense.features.gui.components.items.buttons;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.sjnez.renosense.RenoSense;
import me.sjnez.renosense.features.gui.RenoSenseGui;
import me.sjnez.renosense.features.gui.components.items.buttons.Button;
import me.sjnez.renosense.features.modules.client.ClickGui;
import me.sjnez.renosense.features.setting.Setting;
import me.sjnez.renosense.util.RenderUtil;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public class EnumButton
extends Button {
    public Setting setting;

    public EnumButton(Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtil.drawRect(this.x, this.y, this.x + (float)this.width + 7.4f, this.y + (float)this.height - 0.5f, this.getState() ? (!this.isHovering(mouseX, mouseY) ? RenoSense.colorManager.getColorWithAlpha(RenoSense.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue()) : RenoSense.colorManager.getColorWithAlpha(RenoSense.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue())) : (!this.isHovering(mouseX, mouseY) ? 0x11555555 : -2007673515));
        RenoSense.textManager.drawStringWithShadow(this.setting.getName() + " " + ChatFormatting.RED + (this.setting.currentEnumName().equalsIgnoreCase("ABC") ? "ABC" : this.setting.currentEnumName()), this.x + 2.3f, this.y - 1.7f - (float)RenoSenseGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
    }

    @Override
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.isHovering(mouseX, mouseY) && mouseButton == 0) {
            mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord((SoundEvent)SoundEvents.UI_BUTTON_CLICK, (float)1.0f));
        }
    }

    @Override
    public int getHeight() {
        return 14;
    }

    @Override
    public void toggle() {
        this.setting.increaseEnum();
    }

    @Override
    public void toggleR() {
        this.setting.decreaseEnum();
    }

    @Override
    public boolean getState() {
        return true;
    }
}
