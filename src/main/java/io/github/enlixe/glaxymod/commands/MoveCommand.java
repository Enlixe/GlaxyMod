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

public class MoveCommand extends CommandBase {

    public static int[] coordsXY = {0, 0};
    public static int[] displayXY = {0, 0};
    public static int[] dungeonTimerXY = {0, 0};
    public static int[] skill50XY = {0, 0};
    public static int[] lividHpXY = {0, 0};
    public static int[] cakeTimerXY = {0, 0};
    public static int[] skillTrackerXY = {0, 0};
    public static int[] waterAnswerXY = {0, 0};
    public static int[] bonzoTimerXY = {0, 0};
    public static int[] golemTimerXY = {0 ,0};

    @Override
    public String getCommandName() {
        return "move";
    }

    @Override
    public String getCommandUsage(ICommandSender arg0) {
        return "/" + getCommandName() + " <coords/display/dungeontimer/skill50/lividhp/caketimer/skilltracker/wateranswer/bonzotimer/golemtimer> <x> <y>";
    }

    public static String usage(ICommandSender arg0) {
        return new MoveCommand().getCommandUsage(arg0);
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
        final EntityPlayer player = (EntityPlayer)arg0;

        if (arg1.length < 2) {
            player.addChatMessage(new ChatComponentText(Glaxy.ERROR_COLOUR + "Usage: " + getCommandUsage(arg0)));
            return;
        }

        switch (arg1[0].toLowerCase()) {
            case "coords":
                coordsXY[0] = Integer.parseInt(arg1[1]);
                coordsXY[1] = Integer.parseInt(arg1[2]);
                ConfigHandler.writeIntConfig("locations", "coordsX", coordsXY[0]);
                ConfigHandler.writeIntConfig("locations", "coordsY", coordsXY[1]);
                player.addChatMessage(new ChatComponentText(Glaxy.MAIN_COLOUR + "Coords have been moved to " + Glaxy.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
                break;
            case "display":
                displayXY[0] = Integer.parseInt(arg1[1]);
                displayXY[1] = Integer.parseInt(arg1[2]);
                ConfigHandler.writeIntConfig("locations", "displayX", displayXY[0]);
                ConfigHandler.writeIntConfig("locations", "displayY", displayXY[1]);
                player.addChatMessage(new ChatComponentText(Glaxy.MAIN_COLOUR + "Tracker display has been moved to " + Glaxy.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
                break;
            case "skilltracker":
                skillTrackerXY[0] = Integer.parseInt(arg1[1]);
                skillTrackerXY[1] = Integer.parseInt(arg1[2]);
                ConfigHandler.writeIntConfig("locations", "skillTrackerX", skillTrackerXY[0]);
                ConfigHandler.writeIntConfig("locations", "skillTrackerY", skillTrackerXY[1]);
                player.addChatMessage(new ChatComponentText(Glaxy.MAIN_COLOUR + "Skill tracker has been moved to " + Glaxy.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
                break;
            default:
                player.addChatMessage(new ChatComponentText(Glaxy.ERROR_COLOUR + "Usage: " + getCommandUsage(arg0)));
        }
    }

}