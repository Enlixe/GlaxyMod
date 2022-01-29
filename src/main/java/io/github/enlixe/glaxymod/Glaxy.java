/*
 * Glaxy - Hypixel Skyblock Mod
 * Copyright (C) 2022 Enlixe
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.enlixe.glaxymod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.github.enlixe.glaxymod.commands.*;
import io.github.enlixe.glaxymod.events.GuiChestBackgroundDrawnEvent;
import io.github.enlixe.glaxymod.events.RenderOverlay;
import io.github.enlixe.glaxymod.features.NoF3Coords;
import io.github.enlixe.glaxymod.features.SkillTracker;
import io.github.enlixe.glaxymod.features.loot.LootDisplay;
import io.github.enlixe.glaxymod.features.loot.LootTracker;
import io.github.enlixe.glaxymod.gui.*;
import io.github.enlixe.glaxymod.handlers.ConfigHandler;
import io.github.enlixe.glaxymod.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.ICommand;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Mod(
    modid = Glaxy.MODID,
    name = Glaxy.NAME,
    version = Glaxy.VERSION,
    acceptedMinecraftVersions = "[1.8.9]",
    clientSideOnly = true
)
public class Glaxy {
    public static final String
        MODID = "glaxy",
        NAME = "Glaxy",
        VERSION = "0.1.0",
        VERSION_ID = "010";

    public static final Logger logger = Logger.getLogger(Glaxy.MODID);

    public static int titleTimer = -1;
    public static boolean showTitle = false;
    public static String titleText = "";
    public static int tickAmount = 1;
    public static KeyBinding[] keyBindings = new KeyBinding[3];
    public static boolean usingLabymod = false;
    public static boolean usingOAM = false;
    static boolean OAMWarning = false;
    public static String guiToOpen = null;
    public static boolean firstLaunch = false;
    public static String configDirectory;

    public static String MAIN_COLOUR;
    public static String SECONDARY_COLOUR;
    public static String ERROR_COLOUR;
    public static String DELIMITER_COLOUR;
    public static String TYPE_COLOUR;
    public static String VALUE_COLOUR;
    public static String SKILL_AVERAGE_COLOUR;
    public static String ANSWER_COLOUR;

    @Mod.EventHandler
    private void preInit(FMLPreInitializationEvent event) {
        logger.info("Pre-initializing Glaxy");

        ClientCommandHandler.instance.registerCommand(new GlaxyGuiCommand());
        ClientCommandHandler.instance.registerCommand(new GHelpCommand());

        ClientCommandHandler.instance.registerCommand(new DisplayCommand());
        ClientCommandHandler.instance.registerCommand(new ToggleCommand());
        ClientCommandHandler.instance.registerCommand(new LootCommand());
        ClientCommandHandler.instance.registerCommand(new SlayerCommand());

        configDirectory = event.getModConfigurationDirectory().toString();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info("= = = = = = = =");
        logger.info("Initializing Glaxy");
        logger.info("Hello " + Minecraft.getMinecraft().getSession().getUsername());
        logger.info("Minecraft version: " + Minecraft.getMinecraft().getVersion());
        logger.info("= = = = = = = =");

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new LootDisplay());
        MinecraftForge.EVENT_BUS.register(new LootTracker());
        MinecraftForge.EVENT_BUS.register(new NoF3Coords());
        MinecraftForge.EVENT_BUS.register(new SkillTracker());

        ConfigHandler.reloadConfig();

        keyBindings[0] = new KeyBinding("Open Maddox Menu", Keyboard.KEY_M, "Danker's Skyblock Mod");
        keyBindings[1] = new KeyBinding("Regular Ability", Keyboard.KEY_NUMPAD4, "Danker's Skyblock Mod");
        keyBindings[2] = new KeyBinding("Start/Stop Skill Tracker", Keyboard.KEY_NUMPAD5, "Danker's Skyblock Mod");

        for (KeyBinding keyBinding : keyBindings) {
            ClientRegistry.registerKeyBinding(keyBinding);
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Package[] packages = Package.getPackages();
        for (Package p : packages){
            if (p.getName().startsWith("com.spiderfrog.gadgets") || p.getName().startsWith("com.spiderfrog.oldanimations")){
                usingOAM = true;
                break;
            }
        }
        System.out.println("OAM detection: " + usingOAM);

        usingLabymod = Loader.isModLoaded("labymod");
        System.out.println("LabyMod detection: " + usingLabymod);

        if (!ClientCommandHandler.instance.getCommands().containsKey("reparty")) {
//            ClientCommandHandler.instance.registerCommand(new RepartyCommand());
        } else if (ConfigHandler.getBoolean("commands", "reparty")) {
            for (Map.Entry<String, ICommand> entry : ClientCommandHandler.instance.getCommands().entrySet()) {
                if (entry.getKey().equals("reparty") || entry.getKey().equals("rp")) {
//                    entry.setValue(new RepartyCommand());
                }
            }
        }
    }



    @SubscribeEvent
    public void onGuiOpenEvent(GuiOpenEvent event) {
        if (event.gui instanceof GuiMainMenu && usingOAM && !OAMWarning) {
            event.gui = new WarningGuiRedirect(new WarningGui());
            OAMWarning = true;
        }
    }

    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent event) {
        if (firstLaunch) {
            firstLaunch = false;
            ConfigHandler.writeBooleanConfig("misc", "firstLaunch", false);

            IChatComponent chatComponent = new ChatComponentText(
                    EnumChatFormatting.GOLD + "Thank you for downloading Glaxy Skyblock Mod.\n" +
                            "To get started, run the command " + EnumChatFormatting.GOLD + "/glaxy" + EnumChatFormatting.RESET + " to view all the mod features."
            );
            chatComponent.setChatStyle(chatComponent.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click to open the Glaxy menu."))).setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/glaxy")));

            new Thread(() -> {
                while (true) {
                    if (Minecraft.getMinecraft().thePlayer == null) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Minecraft.getMinecraft().thePlayer.addChatMessage(chatComponent);
                    break;
                }
            }).start();
        }
    }

    @SubscribeEvent
    public void renderPlayerInfo(final RenderGameOverlayEvent.Post event) {
        if (usingLabymod && !(Minecraft.getMinecraft().ingameGUI instanceof GuiIngameForge)) return;
        if (event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE && event.type != RenderGameOverlayEvent.ElementType.JUMPBAR)
            return;
        if (Minecraft.getMinecraft().currentScreen instanceof EditLocationsGui) return;
        MinecraftForge.EVENT_BUS.post(new RenderOverlay());
    }

    // LabyMod Support
    @SubscribeEvent
    public void renderPlayerInfoLabyMod(final RenderGameOverlayEvent event) {
        if (!usingLabymod) return;
        if (event.type != null) return;
        if (Minecraft.getMinecraft().currentScreen instanceof EditLocationsGui) return;
        MinecraftForge.EVENT_BUS.post(new RenderOverlay());
    }

    @SubscribeEvent
    public void renderPlayerInfo(RenderOverlay event) {
        if (showTitle) {
            Utils.drawTitle(titleText);
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;

        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;

        tickAmount++;
        if (tickAmount % 20 == 0) {
            if (player != null) {
                Utils.checkForSkyblock();
                Utils.checkForDungeons();
            }

            tickAmount = 0;
        }

        if (titleTimer >= 0) {
            if (titleTimer == 0) {
                showTitle = false;
            }
            titleTimer--;
        }
    }

    // Delay GUI by 1 tick
    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (guiToOpen != null) {
            Minecraft mc = Minecraft.getMinecraft();
            if (guiToOpen.startsWith("glaxygui")) {
                int page = Character.getNumericValue(guiToOpen.charAt(guiToOpen.length() - 1));
                mc.displayGuiScreen(new GlaxyGui(page, ""));
            } else {
                switch (guiToOpen) {
                    case "displaygui":
                        mc.displayGuiScreen(new DisplayGui());
                        break;
                    case "editlocations":
                        mc.displayGuiScreen(new EditLocationsGui());
                        break;
//                    case "puzzlesolvers":
//                        mc.displayGuiScreen(new PuzzleSolversGui(1));
//                        break;
//                    case "experimentsolvers":
//                        mc.displayGuiScreen(new ExperimentsGui());
//                        break;
//                    case "skilltracker":
//                        mc.displayGuiScreen(new SkillTrackerGui());
//                        break;
//                    case "custommusic":
//                        mc.displayGuiScreen(new CustomMusicGui());
//                        break;
                }
            }
            guiToOpen = null;
        }
    }

    @SubscribeEvent
    public void onGuiRender(GuiScreenEvent.BackgroundDrawnEvent event) {
        if (!Utils.inSkyblock) return;
        if (event.gui instanceof GuiChest) {
            GuiChest inventory = (GuiChest) event.gui;
            Container containerChest = inventory.inventorySlots;
            if (containerChest instanceof ContainerChest) {
                List<Slot> invSlots = inventory.inventorySlots.inventorySlots;
                String displayName = ((ContainerChest) containerChest).getLowerChestInventory().getDisplayName().getUnformattedText().trim();
                int chestSize = inventory.inventorySlots.inventorySlots.size();

                MinecraftForge.EVENT_BUS.post(new GuiChestBackgroundDrawnEvent(inventory, displayName, chestSize, invSlots));
            }
        }
    }
}