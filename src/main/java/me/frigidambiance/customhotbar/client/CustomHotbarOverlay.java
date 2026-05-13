package me.frigidambiance.customhotbar.client;

import java.util.Objects;

import me.frigidambiance.customhotbar.Config;
import me.frigidambiance.customhotbar.CustomHotbar;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = CustomHotbar.MOD_ID,
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public final class CustomHotbarOverlay {
    private static final ResourceLocation DEFAULT_OVERLAY =
          modTexture("textures/gui/overlay.png");

    private static final ResourceLocation DEFAULT_UNDERLAY =
            modTexture("textures/gui/underlay.png");

    private CustomHotbarOverlay() {}

    private static ResourceLocation modTexture(String path) {
      return Objects.requireNonNull(
          ResourceLocation.tryBuild(CustomHotbar.MOD_ID, path),
          "Invalid resource location: " + CustomHotbar.MOD_ID + ":" + path
          );
    }

    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerBelow(
                VanillaGuiOverlay.HOTBAR.id(),
                "custom_hotbar_underlay",
                CustomHotbarOverlay::renderUnderlay
        );

        event.registerAbove(
                VanillaGuiOverlay.HOTBAR.id(),
                "custom_hotbar_overlay",
                CustomHotbarOverlay::renderOverlay
        );
    }

    private static void renderUnderlay(
            ForgeGui gui,
            GuiGraphics graphics,
            float partialTick,
            int screenWidth,
            int screenHeight
    ) {
        if (!Config.ENABLED.get()) {
            return;
        }

        int x = (screenWidth / 2) - 91 - 37 + Config.X_OFFSET.get();
        int y = screenHeight - 54 + Config.Y_OFFSET.get();

        int width = Config.WIDTH.get();
        int height = Config.HEIGHT.get();

        ResourceLocation underlay = resolveTexture(
                Config.UNDERLAY_PATH.get(),
                DEFAULT_UNDERLAY
        );

        graphics.pose().pushPose();
        graphics.blit(underlay, x, y, 0, 0, width, height, width, height);
        graphics.pose().popPose();
    }

    private static void renderOverlay(
            ForgeGui gui,
            GuiGraphics graphics,
            float partialTick,
            int screenWidth,
            int screenHeight
    ) {
        if (!Config.ENABLED.get()) {
            return;
        }

        int x = (screenWidth / 2) - 91 - 37 + Config.X_OFFSET.get();
        int y = screenHeight - 54 + Config.Y_OFFSET.get();

        int width = Config.WIDTH.get();
        int height = Config.HEIGHT.get();

        ResourceLocation overlay = resolveTexture(
                Config.OVERLAY_PATH.get(),
                DEFAULT_OVERLAY
        );

        graphics.pose().pushPose();
        graphics.blit(overlay, x, y, 0, 0, width, height, width, height);
        graphics.pose().popPose();
    }

    private static ResourceLocation resolveTexture(String configuredPath, ResourceLocation fallback) {
        if (configuredPath == null || configuredPath.trim().isEmpty()) {
            return fallback;
        }

        String normalizedPath = configuredPath.trim().replace('\\', '/');

        ResourceLocation configuredLocation = ResourceLocation.tryParse(normalizedPath);

        if (configuredLocation == null) {
            return fallback;
        }

        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();

        if (resourceManager.getResource(configuredLocation).isEmpty()) {
            return fallback;
        }

        return configuredLocation;
    }
}
