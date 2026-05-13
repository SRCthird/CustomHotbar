package me.frigidambiance.customhotbar.client;

import com.mojang.brigadier.Command;

import me.frigidambiance.customhotbar.CustomHotbar;

import net.minecraft.client.Minecraft;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ConfigTracker;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod.EventBusSubscriber(
        modid = CustomHotbar.MOD_ID,
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public final class ClientCommands {
    private ClientCommands() {}

    @SubscribeEvent
    public static void registerClientCommands(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("customhotbar")
                        .then(Commands.literal("reload")
                                .executes(context -> reloadCustomHotbar()))
        );
    }

    private static int reloadCustomHotbar() {
        Minecraft minecraft = Minecraft.getInstance();

        try {
            ConfigTracker.INSTANCE.loadConfigs(
                    ModConfig.Type.CLIENT,
                    FMLPaths.CONFIGDIR.get()
            );

            Textures.invalidate();

            minecraft.reloadResourcePacks().thenRun(() -> minecraft.execute(() -> {
                Textures.invalidate();

                if (minecraft.player != null) {
                    minecraft.player.sendSystemMessage(
                            Component.literal("CustomHotbar config and resources reloaded.")
                    );
                }
            }));

            return Command.SINGLE_SUCCESS;
        } catch (Exception exception) {
            if (minecraft.player != null) {
                minecraft.player.sendSystemMessage(
                        Component.literal("Failed to reload CustomHotbar config: " + exception.getMessage())
                );
            }

            return 0;
        }
    }
}
