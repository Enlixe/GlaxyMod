package io.github.enlixe.glaxymod.commands;


import io.github.enlixe.glaxymod.Glaxy;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class GHelpCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "ghelp";
    }

    @Override
    public String getCommandUsage(ICommandSender arg0) {
        return "/" + getCommandName();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender arg0, String[] arg1) throws CommandException {
        EntityPlayer player = (EntityPlayer) arg0;

        player.addChatMessage(new ChatComponentText("\n" + EnumChatFormatting.GOLD + " " + Glaxy.MODID + " Version " + Glaxy.VERSION + "\n" +
                EnumChatFormatting.AQUA + " <> = Mandatory parameter. [] = Optional parameter.\n" +
                EnumChatFormatting.GOLD + " Commands, " + EnumChatFormatting.GREEN + " Keybinds.\n" +
                EnumChatFormatting.GOLD + getCommandUsage(arg0) + EnumChatFormatting.AQUA + " - Returns this message.\n" +
                EnumChatFormatting.GOLD + GlaxyGuiCommand.usage(arg0) + EnumChatFormatting.AQUA + " - Opens the GUI for Danker's Skyblock Mod.\n" +
                EnumChatFormatting.GOLD + ToggleCommand.usage(arg0) + EnumChatFormatting.AQUA + " - Toggles features. /toggle list returns values of every toggle.\n" +
                EnumChatFormatting.GOLD + LootCommand.usage(arg0) + EnumChatFormatting.AQUA + " - Returns loot received from slayer quests or fishing stats. /loot fishing winter returns winter sea creatures instead.\n" +
                EnumChatFormatting.GOLD + DisplayCommand.usage(arg0) + EnumChatFormatting.AQUA + " - Text display for trackers. /display fishing winter displays winter sea creatures instead. /display auto automatically displays the loot for the slayer quest you have active.\n" +
                EnumChatFormatting.GOLD + MoveCommand.usage(arg0) + EnumChatFormatting.AQUA + " - Moves text display to specified X and Y coordinates.\n" +
                EnumChatFormatting.GOLD + ScaleCommand.usage(arg0) + EnumChatFormatting.AQUA + " - Scales text display to a specified multipler between 0.1x and 10x.\n" +
                EnumChatFormatting.GREEN + " Open Maddox Menu" + EnumChatFormatting.AQUA + " - M by default.\n" +
                EnumChatFormatting.GREEN + " Start/Stop Skill Tracker" + EnumChatFormatting.AQUA + " - Numpad 5 by default.\n"));
    }

}