package me.sjnez.renosense.features.modules.misc;

import java.util.Objects;
import me.sjnez.renosense.event.events.DeathEvent;
import me.sjnez.renosense.features.command.Command;
import me.sjnez.renosense.features.modules.Module;
import me.sjnez.renosense.features.modules.client.HUD;
import me.sjnez.renosense.util.EntityUtil;
import me.sjnez.renosense.util.TextUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Tracker
extends Module {
    private static Tracker instance;
    private EntityPlayer trackedPlayer;
    private int usedExp = 0;
    private int usedStacks = 0;

    public Tracker() {
        super("DuelTracker", "Tracks players in 1v1s.", Module.Category.MISC, true, false, false);
        instance = this;
    }

    public static Tracker getInstance() {
        if (instance == null) {
            instance = new Tracker();
        }
        return instance;
    }

    @Override
    public void onUpdate() {
        if (this.trackedPlayer == null) {
            this.trackedPlayer = EntityUtil.getClosestEnemy(1000.0);
        } else if (this.usedStacks != this.usedExp / 64) {
            this.usedStacks = this.usedExp / 64;
            Command.sendMessage(TextUtil.coloredString(this.trackedPlayer.getName() + " has used " + this.usedStacks + " stacks of XP!", HUD.getInstance().commandColor.getValue()));
        }
    }

    public void onSpawnEntity(Entity entity) {
        if (entity instanceof EntityExpBottle && Objects.equals(Tracker.mc.world.func_72890_a(entity, 3.0), this.trackedPlayer)) {
            ++this.usedExp;
        }
    }

    @Override
    public void onDisable() {
        this.trackedPlayer = null;
        this.usedExp = 0;
        this.usedStacks = 0;
    }

    @SubscribeEvent
    public void onDeath(DeathEvent event) {
        if (event.player.equals(this.trackedPlayer)) {
            this.usedExp = 0;
            this.usedStacks = 0;
        }
    }

    @Override
    public String getDisplayInfo() {
        if (this.trackedPlayer != null) {
            return this.trackedPlayer.getName();
        }
        return null;
    }
}
