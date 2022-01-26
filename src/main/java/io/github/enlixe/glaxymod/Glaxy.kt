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
package io.github.enlixe.glaxymod

import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import java.io.File
import java.util.logging.Logger

@Mod(modid = Glaxy.MODID, name = Glaxy.NAME, version = Glaxy.VERSION, acceptedMinecraftVersions = "[1.8.9]", clientSideOnly = true)
class Glaxy {
    companion object {
        const val MODID = "glaxy"
        const val NAME = "Glaxy"
        const val VERSION = "0.0.1"

        @JvmStatic
        val mc: Minecraft
            get() = Minecraft.getMinecraft()

        // val config = Config

        @JvmField
        val modDir = File(File(mc.mcDataDir, "config"), "glaxy")

        @JvmField
        var jarFile: File? = null

        @JvmStatic
        val logger: Logger
            get() = Logger.getLogger(MODID)
    }

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        logger.info("Pre-initializing Glaxy")

        if (!modDir.exists()) modDir.mkdirs()
        File(modDir, "test").mkdirs()

        jarFile = event.sourceFile
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        logger.info("Initializing Glaxy")

        // config.init()
    }

    @Mod.EventHandler
    fun onFMLInitialization(event: FMLInitializationEvent?) {
        // Initialization code
        logger.info("= = = = = = = =")
        logger.info("Initializing Glaxy")
        logger.info("Hello " + Minecraft.getMinecraft().session.username)
        logger.info("Minecraft version: " + Minecraft.getMinecraft().version)
        logger.info("= = = = = = = =")
    }
}