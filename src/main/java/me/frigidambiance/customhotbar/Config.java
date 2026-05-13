package me.frigidambiance.customhotbar;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue ENABLED;
    public static final ForgeConfigSpec.IntValue X_OFFSET;
    public static final ForgeConfigSpec.IntValue Y_OFFSET;
    public static final ForgeConfigSpec.IntValue WIDTH;
    public static final ForgeConfigSpec.IntValue HEIGHT;

    public static final ForgeConfigSpec.ConfigValue<String> UNDERLAY_PATH;
    public static final ForgeConfigSpec.ConfigValue<String> OVERLAY_PATH;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("hotbar");

        ENABLED = builder
                .comment("Enable CustomHotbar overlay rendering.")
                .define("enabled", true);

        X_OFFSET = builder
                .comment("Horizontal offset from centered hotbar position.")
                .defineInRange("x_offset", 0, -500, 500);

        Y_OFFSET = builder
                .comment("Vertical offset from vanilla hotbar position.")
                .defineInRange("y_offset", 0, -500, 500);

        WIDTH = builder
                .comment("Overlay texture width.")
                .defineInRange("width", 256, 1, 1024);

        HEIGHT = builder
                .comment("Overlay texture height.")
                .defineInRange("height", 55, 1, 1024);

        UNDERLAY_PATH = builder
                .comment(
                        "Resource location for the underlay texture.",
                        "Leave blank to use the bundled texture:",
                        "customhotbar:textures/gui/underlay.png",
                        "Example KubeJS asset:",
                        "ce:textures/image/hotbar/underlay.png"
                )
                .define("underlay_path", "");

        OVERLAY_PATH = builder
                .comment(
                        "Resource location for the overlay texture.",
                        "Leave blank to use the bundled texture:",
                        "customhotbar:textures/gui/overlay.png",
                        "Example KubeJS asset:",
                        "ce:textures/image/hotbar/overlay.png"
                )
                .define("overlay_path", "");

        builder.pop();

        SPEC = builder.build();
    }
}
