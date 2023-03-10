package me.sjnez.renosense.mixin;

import java.util.Map;
import me.sjnez.renosense.RenoSense;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

public class RenoSenseLoader
implements IFMLLoadingPlugin {
    private static boolean isObfuscatedEnvironment = false;

    public RenoSenseLoader() {
        RenoSense.LOGGER.info("\n\nLoading mixins by Sjnez");
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.renosense.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
        RenoSense.LOGGER.info(MixinEnvironment.getDefaultEnvironment().getObfuscationContext());
    }

    public String[] getASMTransformerClass() {
        return new String[0];
    }

    public String getModContainerClass() {
        return null;
    }

    public String getSetupClass() {
        return null;
    }

    public void injectData(Map<String, Object> data) {
        isObfuscatedEnvironment = (Boolean)data.get("runtimeDeobfuscationEnabled");
    }

    public String getAccessTransformerClass() {
        return null;
    }
}
