package me.frigidambiance.customhotbar;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue ENABLED;
    public static final ForgeConfigSpec.IntValue X_OFFSET;
    public static final ForgeConfigSpec.IntValue Y_OFFSET;
    public static final ForgeConfigSpec.IntValue WIDTH;
    public static final ForgeConfigSpec.IntValue HEIGHT;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("hotbar");

        ENABLED = builder
                .comment("Enable CustomHotbar overlay rendering.")
                .define("enabled", true);

        X_OFFSET = builder
                .comment("Horizontal offset from centered hotbar position.")
                .defineInRange("xOffset", 0, -500, 500);

        Y_OFFSET = builder
                .comment("Vertical offset from vanilla hotbar position.")
                .defineInRange("yOffset", 0, -500, 500);

        WIDTH = builder
                .comment("Overlay texture width.")
                .defineInRange("width", 182, 1, 1024);

        HEIGHT = builder
                .comment("Overlay texture height.")
                .defineInRange("height", 22, 1, 1024);

        builder.pop();

        SPEC = builder.build();
    }
}
