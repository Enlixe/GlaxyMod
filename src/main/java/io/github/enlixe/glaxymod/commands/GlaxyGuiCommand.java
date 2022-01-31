package io.github.enlixe.glaxymod.commands;


import io.github.enlixe.glaxymod.Glaxy;
import io.github.enlixe.glaxymod.features.SkillTracker;
import io.github.enlixe.glaxymod.features.loot.LootDisplay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StringUtils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class GlaxyGuiCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "glaxy";
    }

    @Override
    public String getCommandUsage(ICommandSender arg0) {
        return "/" + getCommandName();
    }

    public static String usage(ICommandSender arg0) {
        return new GlaxyGuiCommand().getCommandUsage(arg0);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender arg0, String[] arg1) throws CommandException {
        if (arg1.length > 0 && arg1[0].equalsIgnoreCase("debug")) {
            StringBuilder debug = new StringBuilder();
            debug.append("```md\n");
            debug.append("# Toggles\n");
            debug.append("[coords][").append(ToggleCommand.coordsToggled).append("]\n");
//            debug.append("[slayercount][").append(ToggleCommand.slayerCountTotal).append("]\n");
            debug.append("[rngesusalerts][").append(ToggleCommand.rngesusAlerts).append("]\n");
//            debug.append("[splitfishing][").append(ToggleCommand.splitFishing).append("]\n");
            debug.append("[outlinetext][").append(ToggleCommand.outlineTextToggled).append("]\n");
            debug.append("[autoskilltracker][").append(ToggleCommand.autoSkillTrackerToggled).append("]\n");
            debug.append("# Locations\n");
            debug.append("[coords][").append(MoveCommand.coordsXY[0]).append(", ").append(MoveCommand.coordsXY[1]).append("]\n");
            debug.append("[display][").append(MoveCommand.displayXY[0]).append(", ").append(MoveCommand.displayXY[1]).append("]\n");
             debug.append("# Other Settings\n");
            debug.append("[Current Display][").append(LootDisplay.display).append("]\n");
            debug.append("[Auto Display][").append(LootDisplay.auto).append("]\n");
            debug.append("[Skill Tracker Visible][").append(SkillTracker.showSkillTracker).append("]\n");
            debug.append("# Problematic Mods\n");
            debug.append("[LabyMod][").append(Glaxy.usingLabymod).append("]\n");
            debug.append("[OAM][").append(Glaxy.usingOAM).append("]\n");
            debug.append("# Resource Packs\n");
            if (Minecraft.getMinecraft().getResourcePackRepository().getRepositoryEntries().size() == 0) {
                debug.append("<None>\n");
            } else {
                for (ResourcePackRepository.Entry resource : Minecraft.getMinecraft().getResourcePackRepository().getRepositoryEntries()) {
                    debug.append("<").append(StringUtils.stripControlCodes(resource.getResourcePackName())).append(">\n");
                }
            }
            debug.append("```");
            StringSelection clipboard = new StringSelection(debug.toString());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(clipboard, clipboard);
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Glaxy.MAIN_COLOUR + "Debug stats copied to clipboard."));
            return;
        }

        Glaxy.guiToOpen = "glaxygui1";
    }

}