package io.github.enlixe.glaxymod.commands;


import io.github.enlixe.glaxymod.Glaxy;
import io.github.enlixe.glaxymod.handlers.ConfigHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.List;

public class ScaleCommand extends CommandBase {

    public static double coordsScale;
    public static double displayScale;
    public static double skillTrackerScale;

    @Override
    public String getCommandName() {
        return "scale";
    }

    @Override
    public String getCommandUsage(ICommandSender arg0) {
        return "/" + getCommandName() + " <coords/display/dungeontimer/skill50/lividhp/caketimer/skilltracker/wateranswer/bonzotimer/golemtimer> <size (0.1 - 10)>";
    }

    public static String usage(ICommandSender arg0) {
        return new ScaleCommand().getCommandUsage(arg0);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "coords", "display", "dungeontimer", "skill50", "lividhp", "caketimer", "skilltracker", "wateranswer", "bonzotimer", "golemtimer");
        }
        return null;
    }

    @Override
    public void processCommand(ICommandSender arg0, String[] arg1) throws CommandException {
        final EntityPlayer player = (EntityPlayer) arg0;

        if (arg1.length < 2) {
            player.addChatMessage(new ChatComponentText(Glaxy.ERROR_COLOUR + "Usage: " + getCommandUsage(arg0)));
            return;
        }

        double scaleAmount = Math.floor(Double.parseDouble(arg1[1]) * 100.0) / 100.0;
        if (scaleAmount < 0.1 || scaleAmount > 10.0) {
            player.addChatMessage(new ChatComponentText(Glaxy.ERROR_COLOUR + "Scale multipler can only be between 0.1x and 10x."));
            return;
        }

        switch (arg1[0].toLowerCase()) {
            case "coords":
                coordsScale = scaleAmount;
                ConfigHandler.writeDoubleConfig("scales", "coordsScale", coordsScale);
                player.addChatMessage(new ChatComponentText(Glaxy.MAIN_COLOUR + "Coords have been scaled to " + Glaxy.SECONDARY_COLOUR + coordsScale + "x"));
                break;
            case "display":
                displayScale = scaleAmount;
                ConfigHandler.writeDoubleConfig("scales", "displayScale", displayScale);
                player.addChatMessage(new ChatComponentText(Glaxy.MAIN_COLOUR + "Display has been scaled to " + Glaxy.SECONDARY_COLOUR + displayScale + "x"));
                break;
            case "skilltracker":
                skillTrackerScale = scaleAmount;
                ConfigHandler.writeDoubleConfig("scales", "skillTrackerScale", skillTrackerScale);
                player.addChatMessage(new ChatComponentText(Glaxy.MAIN_COLOUR + "Skill tracker has been scaled to " + Glaxy.SECONDARY_COLOUR + skillTrackerScale + "x"));
                break;
            default:
                player.addChatMessage(new ChatComponentText(Glaxy.ERROR_COLOUR + "Usage: " + getCommandUsage(arg0)));
        }
    }

}