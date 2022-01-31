package io.github.enlixe.glaxymod.features;

import io.github.enlixe.glaxymod.commands.ToggleCommand;
import io.github.enlixe.glaxymod.utils.Utils;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FailPuzzleAlert {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChat(ClientChatReceivedEvent event) {
        if (!ToggleCommand.failPuzzleAlerts) return;
        if (!Utils.inDungeons) return;
        if (event.type == 2) return;

        String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
        if (message.contains(":")) return;
        if (message.contains("§lPUZZLE FAIL!") || message.contains("PUZZLE FAIL!")) {
            String failmsg = message.toString();
            Utils.createTitle(EnumChatFormatting.RED + failmsg, 3);
        }
    }
}
