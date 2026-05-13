package me.frigidambiance.customhotbar.client;

import com.mojang.blaze3d.systems.RenderSystem;
import me.frigidambiance.customhotbar.Config;
import me.frigidambiance.customhotbar.CustomHotbar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;

public class CustomHotbarOverlay {
    private static final ResourceLocation HOTBAR_OVERLAY =
            new ResourceLocation(
                    CustomHotbar.MOD_ID,
                    "textures/gui/overlay.png"
            );

    public static void render(
            ForgeGui gui,
            GuiGraphics graphics,
            float partialTick,
            int screenWidth,
            int screenHeight
    ) {
        Minecraft mc = Minecraft.getInstance();

        if (!Config.ENABLED.get()) return;
        if (mc.options.hideGui) return;
        if (mc.player == null || mc.gameMode == null) return;

        int width = Config.WIDTH.get();
        int height = Config.HEIGHT.get();

        int x = (screenWidth - width) / 2 + Config.X_OFFSET.get();
        int y = screenHeight - height + Config.Y_OFFSET.get();

        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        graphics.blit(
                HOTBAR_OVERLAY,
                x,
                y,
                0,
                0,
                width,
                height,
                width,
                height
        );

        RenderSystem.disableBlend();
    }
}
