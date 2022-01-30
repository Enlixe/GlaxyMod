package io.github.enlixe.glaxymod.commands;


import io.github.enlixe.glaxymod.Glaxy;
import io.github.enlixe.glaxymod.handlers.ConfigHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.List;

public class ToggleCommand extends CommandBase implements ICommand {
    public static boolean coordsToggled;
    public static boolean chatMaddoxToggled;

    public static boolean slayerCountTotal;
    public static boolean rngesusAlerts;
    public static boolean splitFishing;

    public static boolean outlineTextToggled;

    public static boolean autoSkillTrackerToggled;

    @Override
    public String getCommandName() {
        return "toggle";
    }

    @Override
    public String getCommandUsage(ICommandSender arg0) {
        return "/" + getCommandName() + " <coords/chatmaddox/rngesusalerts/slayercount/rngesusalerts/splitfishing/outlinetext/list>";
    }

    public static String usage(ICommandSender arg0) {
        return new ToggleCommand().getCommandUsage(arg0);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "coords", "chatmaddox", "rngesusalerts","slayercount","rngesusalerts", "splitfishing","outlinetext", "autoskilltracker", "list");
        }
        return null;
    }

    @Override
    public void processCommand(ICommandSender arg0, String[] arg1) throws CommandException {
        final EntityPlayer player = (EntityPlayer)arg0;

        if (arg1.length == 0) {
            player.addChatMessage(new ChatComponentText(Glaxy.ERROR_COLOUR + "Usage: " + getCommandUsage(arg0)));
            return;
        }

        switch (arg1[0].toLowerCase()) {
            case "coords":
                coordsToggled = !coordsToggled;
                ConfigHandler.writeBooleanConfig("toggles", "Coords", coordsToggled);
                player.addChatMessage(new ChatComponentText(Glaxy.MAIN_COLOUR + "Coord/Angle display has been set to " + Glaxy.SECONDARY_COLOUR + coordsToggled + Glaxy.MAIN_COLOUR + "."));
                break;
            case "chatmaddox":
                chatMaddoxToggled = !chatMaddoxToggled;
                ConfigHandler.writeBooleanConfig("toggles", "ChatMaddox", chatMaddoxToggled);
                player.addChatMessage(new ChatComponentText(Glaxy.MAIN_COLOUR + "Click screen to open Maddox menu has been set to " + Glaxy.SECONDARY_COLOUR + chatMaddoxToggled + Glaxy.MAIN_COLOUR + "."));
                break;
            case "rngesusalerts":
                rngesusAlerts = !rngesusAlerts;
                ConfigHandler.writeBooleanConfig("toggles", "RNGesusAlerts", rngesusAlerts);
                player.addChatMessage(new ChatComponentText(Glaxy.MAIN_COLOUR + "Slayer RNGesus alerts has been set to " + Glaxy.SECONDARY_COLOUR + rngesusAlerts + Glaxy.MAIN_COLOUR + "."));
                break;
            case "slayercount":
                slayerCountTotal = !slayerCountTotal;
                ConfigHandler.writeBooleanConfig("toggles", "SlayerCount", slayerCountTotal);
                player.addChatMessage(new ChatComponentText(Glaxy.MAIN_COLOUR + "Counting total 20% slayer drops has been set to " + Glaxy.SECONDARY_COLOUR + slayerCountTotal + Glaxy.MAIN_COLOUR + "."));
                break;
            case "splitfishing":
                splitFishing = !splitFishing;
                ConfigHandler.writeBooleanConfig("toggles", "SplitFishing", splitFishing);
                player.addChatMessage(new ChatComponentText(Glaxy.MAIN_COLOUR + "Split fishing display has been set to " + Glaxy.SECONDARY_COLOUR + splitFishing + Glaxy.MAIN_COLOUR + "."));
                break;
            case "outlinetext":
                outlineTextToggled = !outlineTextToggled;
                ConfigHandler.writeBooleanConfig("toggles", "OutlineText", outlineTextToggled);
                player.addChatMessage(new ChatComponentText(Glaxy.MAIN_COLOUR + "Outline displayed text has been set to " + Glaxy.SECONDARY_COLOUR + outlineTextToggled + Glaxy.MAIN_COLOUR + "."));
                break;
            case "autoskilltracker":
                autoSkillTrackerToggled = !autoSkillTrackerToggled;
                ConfigHandler.writeBooleanConfig("toggles", "AutoSkillTracker", autoSkillTrackerToggled);
                player.addChatMessage(new ChatComponentText(Glaxy.MAIN_COLOUR + "Auto start/stop skill tracker has been set to " + Glaxy.SECONDARY_COLOUR + autoSkillTrackerToggled + Glaxy.MAIN_COLOUR + "."));
                break;
            case "list":
                player.addChatMessage(new ChatComponentText(
                        Glaxy.TYPE_COLOUR + " Coord/Angle display: " + Glaxy.VALUE_COLOUR + coordsToggled + "\n" +
                        Glaxy.TYPE_COLOUR + " Chat Maddox menu: " + Glaxy.VALUE_COLOUR + chatMaddoxToggled + "\n" +
                        Glaxy.TYPE_COLOUR + " Slayer RNGesus alerts: " + Glaxy.VALUE_COLOUR + rngesusAlerts + "\n" +
                        Glaxy.TYPE_COLOUR + " Slayer RNGesus alerts: " + Glaxy.VALUE_COLOUR + rngesusAlerts + "\n" +
                        Glaxy.TYPE_COLOUR + " Outline displayed text: " + Glaxy.VALUE_COLOUR + outlineTextToggled + "\n" +
                        Glaxy.TYPE_COLOUR + " Auto start/stop skill tracker: " + Glaxy.VALUE_COLOUR + autoSkillTrackerToggled + "\n"
                ));
                break;
            default:
                player.addChatMessage(new ChatComponentText(Glaxy.ERROR_COLOUR + "Usage: " + getCommandUsage(arg0)));
        }
    }
}