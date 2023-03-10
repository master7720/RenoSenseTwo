package me.sjnez.renosense.manager;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import me.sjnez.renosense.RenoSense;
import me.sjnez.renosense.event.events.Render2DEvent;
import me.sjnez.renosense.event.events.Render3DEvent;
import me.sjnez.renosense.features.Feature;
import me.sjnez.renosense.features.gui.RenoSenseGui;
import me.sjnez.renosense.features.modules.Module;
import me.sjnez.renosense.features.modules.client.ClickGui;
import me.sjnez.renosense.features.modules.client.FontMod;
import me.sjnez.renosense.features.modules.client.HUD;
import me.sjnez.renosense.features.modules.client.Logo;
import me.sjnez.renosense.features.modules.client.ModuleTools;
import me.sjnez.renosense.features.modules.client.NickHider;
import me.sjnez.renosense.features.modules.combat.AutoArmor;
import me.sjnez.renosense.features.modules.combat.AutoCrystal;
import me.sjnez.renosense.features.modules.combat.AutoTrap;
import me.sjnez.renosense.features.modules.combat.AutoWeb;
import me.sjnez.renosense.features.modules.combat.Burrow;
import me.sjnez.renosense.features.modules.combat.ChorusPredict;
import me.sjnez.renosense.features.modules.combat.Criticals;
import me.sjnez.renosense.features.modules.combat.FakeKick;
import me.sjnez.renosense.features.modules.combat.HoleFiller;
import me.sjnez.renosense.features.modules.combat.Killaura;
import me.sjnez.renosense.features.modules.combat.Offhand;
import me.sjnez.renosense.features.modules.combat.Selftrap;
import me.sjnez.renosense.features.modules.combat.Surround;
import me.sjnez.renosense.features.modules.misc.ChatModifier;
import me.sjnez.renosense.features.modules.misc.CoordNotifier;
import me.sjnez.renosense.features.modules.misc.FriendSettings;
import me.sjnez.renosense.features.modules.misc.NoHandShake;
import me.sjnez.renosense.features.modules.misc.PearlNotify;
import me.sjnez.renosense.features.modules.misc.PopCounter;
import me.sjnez.renosense.features.modules.misc.RPC;
import me.sjnez.renosense.features.modules.misc.ToolTips;
import me.sjnez.renosense.features.modules.misc.Tracker;
import me.sjnez.renosense.features.modules.movement.AntiVoid;
import me.sjnez.renosense.features.modules.movement.AntiWeb;
import me.sjnez.renosense.features.modules.movement.NoSlow;
import me.sjnez.renosense.features.modules.movement.Step;
import me.sjnez.renosense.features.modules.player.FakePlayer;
import me.sjnez.renosense.features.modules.player.FastPlace;
import me.sjnez.renosense.features.modules.player.MiddleClick;
import me.sjnez.renosense.features.modules.player.NoEntityTrace;
import me.sjnez.renosense.features.modules.player.NoGlitchBlocks;
import me.sjnez.renosense.features.modules.player.PacketMine;
import me.sjnez.renosense.features.modules.player.Replenish;
import me.sjnez.renosense.features.modules.render.Aspect;
import me.sjnez.renosense.features.modules.render.BlockHighlight;
import me.sjnez.renosense.features.modules.render.BurrowESP;
import me.sjnez.renosense.features.modules.render.Chams;
import me.sjnez.renosense.features.modules.render.Fullbright;
import me.sjnez.renosense.features.modules.render.HoleESP;
import me.sjnez.renosense.features.modules.render.KillEffects;
import me.sjnez.renosense.features.modules.render.Nametags;
import me.sjnez.renosense.features.modules.render.NoRender;
import me.sjnez.renosense.features.modules.render.SkyColor;
import me.sjnez.renosense.features.modules.render.Swing;
import me.sjnez.renosense.features.modules.render.Trails;
import me.sjnez.renosense.features.modules.render.ViewModel;
import me.sjnez.renosense.util.Util;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;

public class ModuleManager
extends Feature {
    public ArrayList<Module> modules = new ArrayList();
    public List<Module> sortedModules = new ArrayList<Module>();
    public List<String> sortedModulesABC = new ArrayList<String>();
    public Animation animationThread;

    public void init() {
        this.modules.add(new Trails());
        this.modules.add(new Swing());
        this.modules.add(new KillEffects());
        this.modules.add(new CoordNotifier());
        this.modules.add(new FakePlayer());
        this.modules.add(new NoHandShake());
        this.modules.add(new NoEntityTrace());
        this.modules.add(new ModuleTools());
        this.modules.add(new BurrowESP());
        this.modules.add(new ChorusPredict());
        this.modules.add(new FriendSettings());
        this.modules.add(new Step());
        this.modules.add(new Replenish());
        this.modules.add(new Fullbright());
        this.modules.add(new NoSlow());
        this.modules.add(new Chams());
        this.modules.add(new Nametags());
        this.modules.add(new ChatModifier());
        this.modules.add(new NoRender());
        this.modules.add(new FakeKick());
        this.modules.add(new NickHider());
        this.modules.add(new SkyColor());
        this.modules.add(new ViewModel());
        this.modules.add(new Aspect());
        this.modules.add(new Burrow());
        this.modules.add(new RPC());
        this.modules.add(new AntiWeb());
        this.modules.add(new Logo());
        this.modules.add(new ClickGui());
        this.modules.add(new FontMod());
        this.modules.add(new HUD());
        this.modules.add(new BlockHighlight());
        this.modules.add(new HoleESP());
        this.modules.add(new MiddleClick());
        this.modules.add(new PacketMine());
        this.modules.add(new AntiVoid());
        this.modules.add(new PearlNotify());
        this.modules.add(new ToolTips());
        this.modules.add(new Tracker());
        this.modules.add(new PopCounter());
        this.modules.add(new Offhand());
        this.modules.add(new Surround());
        this.modules.add(new AutoTrap());
        this.modules.add(new AutoWeb());
        this.modules.add(new AutoCrystal());
        this.modules.add(new Killaura());
        this.modules.add(new Criticals());
        this.modules.add(new HoleFiller());
        this.modules.add(new AutoArmor());
        this.modules.add(new FastPlace());
        this.modules.add(new Selftrap());
        this.modules.add(new NoGlitchBlocks());
    }

    public Module getModuleByName(String name) {
        for (Module module : this.modules) {
            if (!module.getName().equalsIgnoreCase(name)) continue;
            return module;
        }
        return null;
    }

    public <T extends Module> T getModuleByClass(Class<T> clazz) {
        for (Module module : this.modules) {
            if (!clazz.isInstance(module)) continue;
            return (T)module;
        }
        return null;
    }

    public void enableModule(Class<Module> clazz) {
        Module module = this.getModuleByClass(clazz);
        if (module != null) {
            module.enable();
        }
    }

    public void disableModule(Class<Module> clazz) {
        Module module = this.getModuleByClass(clazz);
        if (module != null) {
            module.disable();
        }
    }

    public void enableModule(String name) {
        Module module = this.getModuleByName(name);
        if (module != null) {
            module.enable();
        }
    }

    public void disableModule(String name) {
        Module module = this.getModuleByName(name);
        if (module != null) {
            module.disable();
        }
    }

    public boolean isModuleEnabled(String name) {
        Module module = this.getModuleByName(name);
        return module != null && module.isOn();
    }

    public boolean isModuleEnabled(Class<Module> clazz) {
        Module module = this.getModuleByClass(clazz);
        return module != null && module.isOn();
    }

    public Module getModuleByDisplayName(String displayName) {
        for (Module module : this.modules) {
            if (!module.getDisplayName().equalsIgnoreCase(displayName)) continue;
            return module;
        }
        return null;
    }

    public ArrayList<Module> getEnabledModules() {
        ArrayList<Module> enabledModules = new ArrayList<Module>();
        for (Module module : this.modules) {
            if (!module.isEnabled()) continue;
            enabledModules.add(module);
        }
        return enabledModules;
    }

    public ArrayList<String> getEnabledModulesName() {
        ArrayList<String> enabledModules = new ArrayList<String>();
        for (Module module : this.modules) {
            if (!module.isEnabled() || !module.isDrawn()) continue;
            enabledModules.add(module.getFullArrayString());
        }
        return enabledModules;
    }

    public ArrayList<Module> getModulesByCategory(Module.Category category) {
        ArrayList<Module> modulesCategory = new ArrayList<Module>();
        this.modules.forEach(module -> {
            if (module.getCategory() == category) {
                modulesCategory.add((Module)module);
            }
        });
        return modulesCategory;
    }

    public List<Module.Category> getCategories() {
        return Arrays.asList(Module.Category.values());
    }

    public void onLoad() {
        this.modules.stream().filter(Module::listening).forEach(MinecraftForge.EVENT_BUS::register);
        this.modules.forEach(Module::onLoad);
    }

    public void onUpdate() {
        this.modules.stream().filter(Feature::isEnabled).forEach(Module::onUpdate);
    }

    public void onTick() {
        this.modules.stream().filter(Feature::isEnabled).forEach(Module::onTick);
    }

    public void onRender2D(Render2DEvent event) {
        this.modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender2D(event));
    }

    public void onRender3D(Render3DEvent event) {
        this.modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender3D(event));
    }

    public void sortModules(boolean reverse) {
        this.sortedModules = this.getEnabledModules().stream().filter(Module::isDrawn).sorted(Comparator.comparing(module -> this.renderer.getStringWidth(module.getFullArrayString()) * (reverse ? -1 : 1))).collect(Collectors.toList());
    }

    public void sortModulesABC() {
        this.sortedModulesABC = new ArrayList<String>(this.getEnabledModulesName());
        this.sortedModulesABC.sort(String.CASE_INSENSITIVE_ORDER);
    }

    public void onLogout() {
        this.modules.forEach(Module::onLogout);
    }

    public void onLogin() {
        this.modules.forEach(Module::onLogin);
    }

    public void onUnload() {
        this.modules.forEach(MinecraftForge.EVENT_BUS::unregister);
        this.modules.forEach(Module::onUnload);
    }

    public void onUnloadPost() {
        for (Module module : this.modules) {
            module.enabled.setValue(false);
        }
    }

    public void onKeyPressed(int eventKey) {
        if (eventKey == 0 || !Keyboard.getEventKeyState() || ModuleManager.mc.currentScreen instanceof RenoSenseGui) {
            return;
        }
        this.modules.forEach(module -> {
            if (module.getBind().getKey() == eventKey) {
                module.toggle();
            }
        });
    }

    private class Animation
    extends Thread {
        public Module module;
        public float offset;
        public float vOffset;
        ScheduledExecutorService service;

        public Animation() {
            super("Animation");
            this.service = Executors.newSingleThreadScheduledExecutor();
        }

        @Override
        public void run() {
            if (HUD.getInstance().renderingMode.getValue() == HUD.RenderingMode.Length) {
                for (Module module : ModuleManager.this.sortedModules) {
                    String text = module.getDisplayName() + ChatFormatting.GRAY + (module.getDisplayInfo() != null ? " [" + ChatFormatting.WHITE + module.getDisplayInfo() + ChatFormatting.GRAY + "]" : "");
                    module.offset = (float)ModuleManager.this.renderer.getStringWidth(text) / HUD.getInstance().animationHorizontalTime.getValue().floatValue();
                    module.vOffset = (float)ModuleManager.this.renderer.getFontHeight() / HUD.getInstance().animationVerticalTime.getValue().floatValue();
                    if (module.isEnabled() && HUD.getInstance().animationHorizontalTime.getValue() != 1) {
                        if (!(module.arrayListOffset > module.offset) || Util.mc.world == null) continue;
                        module.arrayListOffset -= module.offset;
                        module.sliding = true;
                        continue;
                    }
                    if (!module.isDisabled() || HUD.getInstance().animationHorizontalTime.getValue() == 1) continue;
                    if (module.arrayListOffset < (float)ModuleManager.this.renderer.getStringWidth(text) && Util.mc.world != null) {
                        module.arrayListOffset += module.offset;
                        module.sliding = true;
                        continue;
                    }
                    module.sliding = false;
                }
            } else {
                for (String e : ModuleManager.this.sortedModulesABC) {
                    Module module = RenoSense.moduleManager.getModuleByName(e);
                    String text = module.getDisplayName() + ChatFormatting.GRAY + (module.getDisplayInfo() != null ? " [" + ChatFormatting.WHITE + module.getDisplayInfo() + ChatFormatting.GRAY + "]" : "");
                    module.offset = (float)ModuleManager.this.renderer.getStringWidth(text) / HUD.getInstance().animationHorizontalTime.getValue().floatValue();
                    module.vOffset = (float)ModuleManager.this.renderer.getFontHeight() / HUD.getInstance().animationVerticalTime.getValue().floatValue();
                    if (module.isEnabled() && HUD.getInstance().animationHorizontalTime.getValue() != 1) {
                        if (!(module.arrayListOffset > module.offset) || Util.mc.world == null) continue;
                        module.arrayListOffset -= module.offset;
                        module.sliding = true;
                        continue;
                    }
                    if (!module.isDisabled() || HUD.getInstance().animationHorizontalTime.getValue() == 1) continue;
                    if (module.arrayListOffset < (float)ModuleManager.this.renderer.getStringWidth(text) && Util.mc.world != null) {
                        module.arrayListOffset += module.offset;
                        module.sliding = true;
                        continue;
                    }
                    module.sliding = false;
                }
            }
        }

        @Override
        public void start() {
            System.out.println("Starting animation thread.");
            this.service.scheduleAtFixedRate(this, 0L, 1L, TimeUnit.MILLISECONDS);
        }
    }
}
