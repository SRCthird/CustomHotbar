package me.frigidambiance.customhotbar;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CustomHotbar.MOD_ID)
public class CustomHotbar {
    public static final String MOD_ID = "customhotbar";

    public CustomHotbar(FMLJavaModLoadingContext context) {
        context.registerConfig(
                ModConfig.Type.CLIENT,
                Config.SPEC
        );
    }
}
