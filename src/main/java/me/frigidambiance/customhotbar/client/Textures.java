package me.frigidambiance.customhotbar.client;

import java.util.Objects;

import me.frigidambiance.customhotbar.Config;
import me.frigidambiance.customhotbar.CustomHotbar;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

public final class Textures {
    private static final ResourceLocation DEFAULT_OVERLAY =
            modTexture("textures/gui/overlay.png");

    private static final ResourceLocation DEFAULT_UNDERLAY =
            modTexture("textures/gui/underlay.png");

    private static ResourceLocation overlay;
    private static ResourceLocation underlay;

    private Textures() {}

    public static ResourceLocation overlay() {
        if (overlay == null) {
            refresh(Minecraft.getInstance().getResourceManager());
        }

        return overlay;
    }

    public static ResourceLocation underlay() {
        if (underlay == null) {
            refresh(Minecraft.getInstance().getResourceManager());
        }

        return underlay;
    }

    public static void invalidate() {
        overlay = null;
        underlay = null;
    }

    public static void refresh(ResourceManager resourceManager) {
        overlay = resolveTexture(
                Config.OVERLAY_PATH.get(),
                DEFAULT_OVERLAY,
                resourceManager
        );

        underlay = resolveTexture(
                Config.UNDERLAY_PATH.get(),
                DEFAULT_UNDERLAY,
                resourceManager
        );
    }

    private static ResourceLocation resolveTexture(
            String configuredPath,
            ResourceLocation fallback,
            ResourceManager resourceManager
    ) {
        if (configuredPath == null || configuredPath.trim().isEmpty()) {
            return fallback;
        }

        String normalizedPath = configuredPath.trim().replace('\\', '/');

        ResourceLocation configuredLocation = ResourceLocation.tryParse(normalizedPath);

        if (configuredLocation == null) {
            return fallback;
        }

        if (resourceManager.getResource(configuredLocation).isEmpty()) {
            return fallback;
        }

        return configuredLocation;
    }

    private static ResourceLocation modTexture(String path) {
        return Objects.requireNonNull(
                ResourceLocation.tryBuild(CustomHotbar.MOD_ID, path),
                "Invalid resource location: " + CustomHotbar.MOD_ID + ":" + path
        );
    }
}
