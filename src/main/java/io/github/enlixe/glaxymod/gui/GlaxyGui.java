package io.github.enlixe.glaxymod.gui;

import io.github.enlixe.glaxymod.Glaxy;
import io.github.enlixe.glaxymod.commands.ToggleCommand;
import io.github.enlixe.glaxymod.handlers.ConfigHandler;
import io.github.enlixe.glaxymod.handlers.TextRenderer;
import io.github.enlixe.glaxymod.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.StringUtils;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class GlaxyGui extends GuiScreen {

    private int page;
    private List<GuiButton> allButtons = new ArrayList<>();
    private List<GuiButton> foundButtons = new ArrayList<>();
    String initSearchText;

    private GuiButton closeGUI;
    private GuiButton backPage;
    private GuiButton nextPage;
    private GuiButton githubLink;
    private GuiButton discordLink;
    private GuiButton editLocations;
    private GuiTextField search;

    private GuiButton changeDisplay;
    private GuiButton puzzleSolvers;
    private GuiButton experimentationTableSolvers;
    private GuiButton skillTracker;
    private GuiButton customMusic;
    // Toggles
    private GuiButton gparty;
    private GuiButton coords;
    private GuiButton goldenEnch;
    private GuiButton slayerCount;
    private GuiButton rngesusAlert;
    private GuiButton splitFishing;
    private GuiButton chatMaddox;
    private GuiButton spiritBearAlert;
    private GuiButton petColours;
    private GuiButton golemAlerts;
    private GuiButton expertiseLore;
    private GuiButton skill50Display;
    private GuiButton outlineText;
    private GuiButton cakeTimer;
    private GuiButton pickBlock;
    private GuiButton notifySlayerSlain;
    private GuiButton melodyTooltips;
    private GuiButton autoSkillTracker;
    private GuiButton highlightArachne;
    private GuiButton highlightSlayer;
    // Chat Messages
    private GuiButton sceptreMessages;
    private GuiButton midasStaffMessages;
    private GuiButton implosionMessages;
    private GuiButton healMessages;
    private GuiButton cooldownMessages;
    private GuiButton manaMessages;
    private GuiButton killComboMessages;
    // Dungeons
    private GuiButton dungeonTimer;
    private GuiButton lowHealthNotify;
    private GuiButton lividSolver;
    private GuiButton stopSalvageStarred;
    private GuiButton watcherReadyMessage;
    private GuiButton necronNotifications;
    private GuiButton bonzoTimer;

    public GlaxyGui(int page, String searchText) {
        this.page = page;
        initSearchText = searchText;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {
        super.initGui();

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int height = sr.getScaledHeight();
        int width = sr.getScaledWidth();

        // Default button size is 200, 20
        closeGUI = new GuiButton(0, width / 2 - 100, (int) (height * 0.9), "Close");
        backPage = new GuiButton(0, width / 2 - 100, (int) (height * 0.8), 80, 20, "< Back");
        nextPage = new GuiButton(0, width / 2 + 20, (int) (height * 0.8), 80, 20, "Next >");
        githubLink = new GuiButton(0, 2, height - 50, 80, 20, "GitHub");
        discordLink = new GuiButton(0, 2, height - 30, 80, 20, "Discord");
        editLocations = new GuiButton(0, 2, 5, 100, 20, "Edit Locations");
        search = new GuiTextField(0, this.fontRendererObj, width - 202, 5, 200, 20);

        changeDisplay = new GuiButton(0, 0, 0, "Change Display Settings");
//        puzzleSolvers = new GuiButton(0, 0, 0, "Toggle Dungeons Puzzle Solvers");
//        experimentationTableSolvers = new GuiButton(0, 0, 0, "Toggle Experimentation Table Solvers");
        skillTracker = new GuiButton(0, 0, 0, "Toggle Skill XP/Hour Tracking");
//        customMusic = new GuiButton(0, 0, 0, "Custom Music");
        outlineText = new GuiButton(0, 0, 0, "Outline Displayed Text: " + Utils.getColouredBoolean(ToggleCommand.outlineTextToggled));
        coords = new GuiButton(0, 0, 0, "Coordinate/Angle Display: " + Utils.getColouredBoolean(ToggleCommand.coordsToggled));
        chatMaddox = new GuiButton(0, 0, 0, "Click On-Screen to Open Maddox: " + Utils.getColouredBoolean(ToggleCommand.chatMaddoxToggled));
        rngesusAlert = new GuiButton(0, 0, 0, "RNGesus Alerts: " + Utils.getColouredBoolean(ToggleCommand.rngesusAlerts));

        allButtons.add(changeDisplay);
//        allButtons.add(puzzleSolvers);
//        allButtons.add(experimentationTableSolvers);
        allButtons.add(skillTracker);
//        allButtons.add(customMusic);
        allButtons.add(outlineText);
        allButtons.add(coords);
        allButtons.add(chatMaddox);
        allButtons.add(rngesusAlert);

        search.setText(initSearchText);
        search.setVisible(true);
        search.setEnabled(true);

        reInit();
    }

    public void reInit() {
        this.buttonList.clear();
        foundButtons.clear();

        for (GuiButton button : allButtons) {
            if (search.getText().length() != 0) {
                String buttonName = StringUtils.stripControlCodes(button.displayString.toLowerCase());
                if (buttonName.contains(search.getText().toLowerCase())) {
                    foundButtons.add(button);
                }
            } else {
                foundButtons.add(button);
            }
        }

        for (int i = (page - 1) * 7, iteration = 0; iteration < 7 && i < foundButtons.size(); i++, iteration++) {
            GuiButton button = foundButtons.get(i);
            button.xPosition = width / 2 - 100;
            button.yPosition = (int) (height * (0.1 * (iteration + 1)));
            this.buttonList.add(button);
        }

        if (page > 1) this.buttonList.add(backPage);
        if (page < Math.ceil(foundButtons.size() / 7D)) this.buttonList.add(nextPage);

        this.buttonList.add(githubLink);
        this.buttonList.add(discordLink);
        this.buttonList.add(closeGUI);
        this.buttonList.add(editLocations);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);

        String pageText = "Page: " + page + "/" + (int) Math.ceil(foundButtons.size() / 7D);
        int pageWidth = mc.fontRendererObj.getStringWidth(pageText);
        new TextRenderer(mc, pageText, width / 2 - pageWidth / 2, 10, 1D);

        search.drawTextBox();
    }

    @Override
    public void actionPerformed(GuiButton button) {
        if (button == closeGUI) {
            Minecraft.getMinecraft().thePlayer.closeScreen();
        } else if (button == nextPage) {
            mc.displayGuiScreen(new GlaxyGui(page + 1, search.getText()));
        } else if (button == backPage) {
            mc.displayGuiScreen(new GlaxyGui(page - 1, search.getText()));
        } else if (button == editLocations) {
            Glaxy.guiToOpen = "editlocations";
        } else if (button == githubLink) {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/Enlixe/GlaxyMod"));
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        } else if (button == discordLink) {
            try {
                Desktop.getDesktop().browse(new URI("https://discord.gg/Jt66GWh"));
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        } else if (button == changeDisplay) {
            Glaxy.guiToOpen = "displaygui";
        } else if (button == skillTracker) {
            Glaxy.guiToOpen = "skilltracker";
        } else if (button == chatMaddox) {
            ToggleCommand.chatMaddoxToggled = !ToggleCommand.chatMaddoxToggled;
            ConfigHandler.writeBooleanConfig("toggles", "ChatMaddox", ToggleCommand.chatMaddoxToggled);
            chatMaddox.displayString = "Click On-Screen to Open Maddox: " + Utils.getColouredBoolean(ToggleCommand.chatMaddoxToggled);
        } else if (button == outlineText) {
            ToggleCommand.outlineTextToggled = !ToggleCommand.outlineTextToggled;
            ConfigHandler.writeBooleanConfig("toggles", "OutlineText", ToggleCommand.outlineTextToggled);
            outlineText.displayString = "Outline Displayed Text: " + Utils.getColouredBoolean(ToggleCommand.outlineTextToggled);
        } else if (button == coords) {
            ToggleCommand.coordsToggled = !ToggleCommand.coordsToggled;
            ConfigHandler.writeBooleanConfig("toggles", "Coords", ToggleCommand.coordsToggled);
            coords.displayString = "Coordinate/Angle Display: " + Utils.getColouredBoolean(ToggleCommand.coordsToggled);
        } else if (button == rngesusAlert) {
            ToggleCommand.rngesusAlerts = !ToggleCommand.rngesusAlerts;
            ConfigHandler.writeBooleanConfig("toggles", "RNGesusAlerts", ToggleCommand.rngesusAlerts);
            rngesusAlert.displayString = "RNGesus Alerts: " + Utils.getColouredBoolean(ToggleCommand.rngesusAlerts);
        }

    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        search.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        search.textboxKeyTyped(typedChar, keyCode);
        reInit();
    }

}