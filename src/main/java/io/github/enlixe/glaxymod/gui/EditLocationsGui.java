package io.github.enlixe.glaxymod.gui;


import io.github.enlixe.glaxymod.Glaxy;
import io.github.enlixe.glaxymod.commands.MoveCommand;
import io.github.enlixe.glaxymod.commands.ScaleCommand;
import io.github.enlixe.glaxymod.features.NoF3Coords;
import io.github.enlixe.glaxymod.features.SkillTracker;
import io.github.enlixe.glaxymod.gui.buttons.LocationButton;
import io.github.enlixe.glaxymod.handlers.ConfigHandler;
import io.github.enlixe.glaxymod.utils.Utils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;

public class EditLocationsGui extends GuiScreen {

    private String moving = null;
    private int lastMouseX = -1;
    private int lastMouseY = -1;

    private LocationButton display;
    private LocationButton dungeonTimer;
    private LocationButton coords;
    private LocationButton skill50;
    private LocationButton lividHP;
    private LocationButton cakeTimer;
    private LocationButton skillTracker;
    private LocationButton waterAnswer;
    private LocationButton bonzoTimer;
    private LocationButton golemTimer;

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {
        super.initGui();

        String displayText = EnumChatFormatting.GOLD + "Svens Killed:\n" +
                EnumChatFormatting.GREEN + "Wolf Teeth:\n" +
                EnumChatFormatting.BLUE + "Hamster Wheels:\n" +
                EnumChatFormatting.AQUA + "Spirit Runes:\n" +
                EnumChatFormatting.WHITE + "Critical VI Books:\n" +
                EnumChatFormatting.DARK_RED + "Red Claw Eggs:\n" +
                EnumChatFormatting.GOLD + "Couture Runes:\n" +
                EnumChatFormatting.AQUA + "Grizzly Baits:\n" +
                EnumChatFormatting.DARK_PURPLE + "Overfluxes:\n" +
                EnumChatFormatting.AQUA + "Time Since RNG:\n" +
                EnumChatFormatting.AQUA + "Bosses Since RNG:";
        String displayNums = EnumChatFormatting.GOLD + "1,024" + "\n" +
                EnumChatFormatting.GREEN + "59,719" + "\n" +
                EnumChatFormatting.BLUE + "36" + "\n" +
                EnumChatFormatting.AQUA + "64" + "\n" +
                EnumChatFormatting.WHITE + "17" + "\n" +
                EnumChatFormatting.DARK_RED + "3" + "\n" +
                EnumChatFormatting.GOLD + "4" + "\n" +
                EnumChatFormatting.AQUA + "0" + "\n" +
                EnumChatFormatting.DARK_PURPLE + "5" + "\n" +
                EnumChatFormatting.AQUA + Utils.getTimeBetween(0, 2678400) + "\n" +
                EnumChatFormatting.AQUA + "5,000";

        String dungeonTimerText = EnumChatFormatting.GRAY + "Wither Doors:\n" +
                EnumChatFormatting.DARK_RED + "Blood Open:\n" +
                EnumChatFormatting.RED + "Watcher Clear:\n" +
                EnumChatFormatting.BLUE + "Boss Clear:\n" +
                EnumChatFormatting.YELLOW + "Deaths:\n" +
                EnumChatFormatting.YELLOW + "Puzzle Fails:";
        String dungeonTimerNums = EnumChatFormatting.GRAY + "" + 5 + "\n" +
                EnumChatFormatting.DARK_RED + Utils.getTimeBetween(0, 33) + "\n" +
                EnumChatFormatting.RED + Utils.getTimeBetween(0, 129) + "\n" +
                EnumChatFormatting.BLUE + Utils.getTimeBetween(0, 169) + "\n" +
                EnumChatFormatting.YELLOW + 2 + "\n" +
                EnumChatFormatting.YELLOW + 1;

        String skillTrackerText = SkillTracker.SKILL_TRACKER_COLOUR + "Farming XP Earned: 462,425.3\n" +
                SkillTracker.SKILL_TRACKER_COLOUR + "Time Elapsed: " + Utils.getTimeBetween(0, 3602) + "\n" +
                SkillTracker.SKILL_TRACKER_COLOUR + "XP Per Hour: 462,168";

        String waterAnswerText = Glaxy.MAIN_COLOUR + "The following levers must be down:\n" +
                EnumChatFormatting.DARK_PURPLE + "Purple: " + EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.GREEN + "Emerald, " + EnumChatFormatting.RED + "Clay\n" +
                EnumChatFormatting.GOLD + "Orange: " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.DARK_GRAY + "Coal\n" +
                EnumChatFormatting.BLUE + "Blue: " + EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.DARK_GRAY + "Coal, " + EnumChatFormatting.GREEN + "Emerald, " + EnumChatFormatting.RED + "Clay\n" +
                EnumChatFormatting.GREEN + "Green: " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.GREEN + "Emerald\n" +
                EnumChatFormatting.RED + "Red: " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.AQUA + "Diamond, " + EnumChatFormatting.GREEN + "Emerald, " + EnumChatFormatting.RED + "Clay";

        display = new LocationButton(0, MoveCommand.displayXY[0], MoveCommand.displayXY[1], 145 * ScaleCommand.displayScale, 102 * ScaleCommand.displayScale, ScaleCommand.displayScale, displayText, displayNums, 110);
        coords = new LocationButton(0, MoveCommand.coordsXY[0], MoveCommand.coordsXY[1], 141 * ScaleCommand.coordsScale, 12 * ScaleCommand.coordsScale, ScaleCommand.coordsScale, NoF3Coords.COORDS_COLOUR + "74 / 14 / -26 (141.1 / 6.7)", null, null);
        skillTracker = new LocationButton(0, MoveCommand.skillTrackerXY[0], MoveCommand.skillTrackerXY[1], 150 * ScaleCommand.skillTrackerScale, 28 * ScaleCommand.skillTrackerScale, ScaleCommand.skillTrackerScale, skillTrackerText, null, null);

        this.buttonList.add(coords);
        this.buttonList.add(skillTracker);
        this.buttonList.add(display);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        mouseMoved(mouseX, mouseY);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void mouseMoved(int mouseX, int mouseY) {
        int xMoved = mouseX - lastMouseX;
        int yMoved = mouseY - lastMouseY;

        if (moving != null) {
            switch (moving) {
                case "display":
                    MoveCommand.displayXY[0] += xMoved;
                    MoveCommand.displayXY[1] += yMoved;
                    display.xPosition = MoveCommand.displayXY[0];
                    display.yPosition = MoveCommand.displayXY[1];
                    break;
                case "coords":
                    MoveCommand.coordsXY[0] += xMoved;
                    MoveCommand.coordsXY[1] += yMoved;
                    coords.xPosition = MoveCommand.coordsXY[0];
                    coords.yPosition = MoveCommand.coordsXY[1];
                    break;
                case "skillTracker":
                    MoveCommand.skillTrackerXY[0] += xMoved;
                    MoveCommand.skillTrackerXY[1] += yMoved;
                    skillTracker.xPosition = MoveCommand.skillTrackerXY[0];
                    skillTracker.yPosition = MoveCommand.skillTrackerXY[1];
                    break;
            }
            this.buttonList.clear();
            initGui();
        }

        lastMouseX = mouseX;
        lastMouseY = mouseY;
    }

    @Override
    public void actionPerformed(GuiButton button) {
        if (button instanceof LocationButton) {
            if (button == display) {
                moving = "display";
            } else if (button == coords) {
                moving = "coords";
            } else if (button == skillTracker) {
                moving = "skillTracker";
            }
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        moving = null;
        ConfigHandler.writeIntConfig("locations", "coordsX", MoveCommand.coordsXY[0]);
        ConfigHandler.writeIntConfig("locations", "coordsY", MoveCommand.coordsXY[1]);
        ConfigHandler.writeIntConfig("locations", "displayX", MoveCommand.displayXY[0]);
        ConfigHandler.writeIntConfig("locations", "displayY", MoveCommand.displayXY[1]);
        ConfigHandler.writeIntConfig("locations", "skillTrackerX", MoveCommand.skillTrackerXY[0]);
        ConfigHandler.writeIntConfig("locations", "skillTrackerY", MoveCommand.skillTrackerXY[1]);
    }

}