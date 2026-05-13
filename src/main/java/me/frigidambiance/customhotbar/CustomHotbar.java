package me.frigidambiance.customhotbar;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(CustomHotbar.MOD_ID)
public class CustomHotbar {
    public static final String MOD_ID = "customhotbar";

    public CustomHotbar() {
        ModLoadingContext.get().registerConfig(
                ModConfig.Type.CLIENT,
                Config.SPEC
        );
    }
}
