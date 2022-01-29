package io.github.enlixe.glaxymod.handlers;

import io.github.enlixe.glaxymod.Glaxy;
import io.github.enlixe.glaxymod.commands.MoveCommand;
import io.github.enlixe.glaxymod.commands.ScaleCommand;
import io.github.enlixe.glaxymod.commands.ToggleCommand;
import io.github.enlixe.glaxymod.features.NoF3Coords;
import io.github.enlixe.glaxymod.features.SkillTracker;
import io.github.enlixe.glaxymod.features.loot.LootDisplay;
import io.github.enlixe.glaxymod.features.loot.LootTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {
    public static Configuration config;
    private final static String file = "config/Glaxy/Glaxy.cfg";

    public static void init() {
        config = new Configuration(new File(file));
        try {
            config.load();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
    }

    public static int getInt(String category, String key) {
        config = new Configuration(new File(file));
        try {
            config.load();
            if (config.getCategory(category).containsKey(key)) {
                return config.get(category, key, 0).getInt();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
        return 0;
    }

    public static double getDouble(String category, String key) {
        config = new Configuration(new File(file));
        try {
            config.load();
            if (config.getCategory(category).containsKey(key)) {
                return config.get(category, key, 0D).getDouble();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
        return 0D;
    }

    public static String getString(String category, String key) {
        config = new Configuration(new File(file));
        try {
            config.load();
            if (config.getCategory(category).containsKey(key)) {
                return config.get(category, key, "").getString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
        return "";
    }

    public static boolean getBoolean(String category, String key) {
        config = new Configuration(new File(file));
        try {
            config.load();
            if (config.getCategory(category).containsKey(key)) {
                return config.get(category, key, false).getBoolean();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
        return true;
    }

    public static void writeIntConfig(String category, String key, int value) {
        config = new Configuration(new File(file));
        try {
            config.load();
            int set = config.get(category, key, value).getInt();
            config.getCategory(category).get(key).set(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
    }

    public static void writeDoubleConfig(String category, String key, double value) {
        config = new Configuration(new File(file));
        try {
            config.load();
            double set = config.get(category, key, value).getDouble();
            config.getCategory(category).get(key).set(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
    }

    public static void writeStringConfig(String category, String key, String value) {
        config = new Configuration(new File(file));
        try {
            config.load();
            String set = config.get(category, key, value).getString();
            config.getCategory(category).get(key).set(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
    }

    public static void writeBooleanConfig(String category, String key, boolean value) {
        config = new Configuration(new File(file));
        try {
            config.load();
            boolean set = config.get(category, key, value).getBoolean();
            config.getCategory(category).get(key).set(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
    }

    public static boolean hasKey(String category, String key) {
        config = new Configuration(new File(file));
        try {
            config.load();
            if (!config.hasCategory(category)) return false;
            return config.getCategory(category).containsKey(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
        return false;
    }

    public static void deleteCategory(String category) {
        config = new Configuration(new File(file));
        try {
            config.load();
            if (config.hasCategory(category)) {
                config.removeCategory(new ConfigCategory(category));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
    }

    public static int initInt(String category, String key, int defaultValue) {
        if (!hasKey(category, key)) {
            writeIntConfig(category, key, defaultValue);
            return defaultValue;
        } else {
            return getInt(category, key);
        }
    }

    public static double initDouble(String category, String key, double defaultValue) {
        if (!hasKey(category, key)) {
            writeDoubleConfig(category, key, defaultValue);
            return defaultValue;
        } else {
            return getDouble(category, key);
        }
    }

    public static String initString(String category, String key, String defaultValue) {
        if (!hasKey(category, key)) {
            writeStringConfig(category, key, defaultValue);
            return defaultValue;
        } else {
            return getString(category, key);
        }
    }

    public static boolean initBoolean(String category, String key, boolean defaultValue) {
        if (!hasKey(category, key)) {
            writeBooleanConfig(category, key, defaultValue);
            return defaultValue;
        } else {
            return getBoolean(category, key);
        }
    }

    public static void reloadConfig() {
        // Toggles
        ToggleCommand.coordsToggled = initBoolean("toggles", "Coords", false);
        ToggleCommand.slayerCountTotal = initBoolean("toggles", "SlayerCount", true);
        ToggleCommand.rngesusAlerts = initBoolean("toggles", "RNGesusAlerts", false);
        ToggleCommand.splitFishing = initBoolean("toggles", "SplitFishing", true);
        ToggleCommand.outlineTextToggled = initBoolean("toggles", "OutlineText", false);

        // API
        if (!hasKey("api", "APIKey")) writeStringConfig("api", "APIKey", "");

        // Wolf
        LootTracker.wolfSvens = initInt("wolf", "svens", 0);
        LootTracker.wolfTeeth = initInt("wolf", "teeth", 0);
        LootTracker.wolfWheels = initInt("wolf", "wheel", 0);
        LootTracker.wolfWheelsDrops = initInt("wolf", "wheelDrops", 0);
        LootTracker.wolfSpirits = initInt("wolf", "spirit", 0);
        LootTracker.wolfBooks = initInt("wolf", "book", 0);
        LootTracker.wolfEggs = initInt("wolf", "egg", 0);
        LootTracker.wolfCoutures = initInt("wolf", "couture", 0);
        LootTracker.wolfBaits = initInt("wolf", "bait", 0);
        LootTracker.wolfFluxes = initInt("wolf", "flux", 0);
        LootTracker.wolfTime = initDouble("wolf", "timeRNG", -1);
        LootTracker.wolfBosses = initInt("wolf", "bossRNG", -1);
        // Spider
        LootTracker.spiderTarantulas = initInt("spider", "tarantulas", 0);
        LootTracker.spiderWebs = initInt("spider", "web", 0);
        LootTracker.spiderTAP = initInt("spider", "tap", 0);
        LootTracker.spiderTAPDrops = initInt("spider", "tapDrops", 0);
        LootTracker.spiderBites = initInt("spider", "bite", 0);
        LootTracker.spiderCatalysts = initInt("spider", "catalyst", 0);
        LootTracker.spiderBooks = initInt("spider", "book", 0);
        LootTracker.spiderSwatters = initInt("spider", "swatter", 0);
        LootTracker.spiderTalismans = initInt("spider", "talisman", 0);
        LootTracker.spiderMosquitos = initInt("spider", "mosquito", 0);
        LootTracker.spiderTime = initDouble("spider", "timeRNG", -1);
        LootTracker.spiderBosses = initInt("spider", "bossRNG", -1);
        // Zombie
        LootTracker.zombieRevs = initInt("zombie", "revs", 0);
        LootTracker.zombieRevFlesh = initInt("zombie", "revFlesh", 0);
        LootTracker.zombieFoulFlesh = initInt("zombie", "foulFlesh", 0);
        LootTracker.zombieFoulFleshDrops = initInt("zombie", "foulFleshDrops", 0);
        LootTracker.zombiePestilences = initInt("zombie", "pestilence", 0);
        LootTracker.zombieUndeadCatas = initInt("zombie", "undeadCatalyst", 0);
        LootTracker.zombieBooks = initInt("zombie", "book", 0);
        LootTracker.zombieBeheadeds = initInt("zombie", "beheaded", 0);
        LootTracker.zombieRevCatas = initInt("zombie", "revCatalyst", 0);
        LootTracker.zombieSnakes = initInt("zombie", "snake", 0);
        LootTracker.zombieScythes = initInt("zombie", "scythe", 0);
        LootTracker.zombieShards = initInt("zombie", "shard", 0);
        LootTracker.zombieWardenHearts = initInt("zombie", "heart", 0);
        LootTracker.zombieTime = initDouble("zombie", "timeRNG", -1);
        LootTracker.zombieBosses = initInt("zombie", "bossRNG", -1);

        // Fishing
        LootTracker.seaCreatures = initInt("fishing", "seaCreature", 0);
        LootTracker.goodCatches = initInt("fishing", "goodCatch", 0);
        LootTracker.greatCatches = initInt("fishing", "greatCatch", 0);
        LootTracker.squids = initInt("fishing", "squid", 0);
        LootTracker.seaWalkers = initInt("fishing", "seaWalker", 0);
        LootTracker.nightSquids = initInt("fishing", "nightSquid", 0);
        LootTracker.seaGuardians = initInt("fishing", "seaGuardian", 0);
        LootTracker.seaWitches = initInt("fishing", "seaWitch", 0);
        LootTracker.seaArchers = initInt("fishing", "seaArcher", 0);
        LootTracker.monsterOfTheDeeps = initInt("fishing", "monsterOfDeep", 0);
        LootTracker.catfishes = initInt("fishing", "catfish", 0);
        LootTracker.carrotKings = initInt("fishing", "carrotKing", 0);
        LootTracker.seaLeeches = initInt("fishing", "seaLeech", 0);
        LootTracker.guardianDefenders = initInt("fishing", "guardianDefender", 0);
        LootTracker.deepSeaProtectors = initInt("fishing", "deepSeaProtector", 0);
        LootTracker.hydras = initInt("fishing", "hydra", 0);
        LootTracker.seaEmperors = initInt("fishing", "seaEmperor", 0);
        LootTracker.empTime = initDouble("fishing", "empTime", -1);
        LootTracker.empSCs = initInt("fishing", "empSC", -1);
        LootTracker.fishingMilestone = initInt("fishing", "milestone", 0);
        // Fishing Winter
        LootTracker.frozenSteves = initInt("fishing", "frozenSteve", 0);
        LootTracker.frostyTheSnowmans = initInt("fishing", "snowman", 0);
        LootTracker.grinches = initInt("fishing", "grinch", 0);
        LootTracker.yetis = initInt("fishing", "yeti", 0);
        LootTracker.yetiTime = initDouble("fishing", "yetiTime", -1);
        LootTracker.yetiSCs = initInt("fishing", "yetiSC", -1);
        // Fishing Festival
        LootTracker.nurseSharks = initInt("fishing", "nurseShark", 0);
        LootTracker.blueSharks = initInt("fishing", "blueShark", 0);
        LootTracker.tigerSharks = initInt("fishing", "tigerShark", 0);
        LootTracker.greatWhiteSharks = initInt("fishing", "greatWhiteShark", 0);
        // Spooky Fishing
        LootTracker.scarecrows = initInt("fishing", "scarecrow", 0);
        LootTracker.nightmares = initInt("fishing", "nightmare", 0);
        LootTracker.werewolfs = initInt("fishing", "werewolf", 0);
        LootTracker.phantomFishers = initInt("fishing", "phantomFisher", 0);
        LootTracker.grimReapers = initInt("fishing", "grimReaper", 0);

        // Mythological
        LootTracker.mythCoins = initDouble("mythological", "coins", 0);
        LootTracker.griffinFeathers = initInt("mythological", "griffinFeather", 0);
        LootTracker.crownOfGreeds = initInt("mythological", "crownOfGreed", 0);
        LootTracker.washedUpSouvenirs = initInt("mythological", "washedUpSouvenir", 0);
        LootTracker.minosHunters = initInt("mythological", "minosHunter", 0);
        LootTracker.siameseLynxes = initInt("mythological", "siameseLynx", 0);
        LootTracker.minotaurs = initInt("mythological", "minotaur", 0);
        LootTracker.gaiaConstructs = initInt("mythological", "gaiaConstruct", 0);
        LootTracker.minosChampions = initInt("mythological", "minosChampion", 0);
        LootTracker.minosInquisitors = initInt("mythological", "minosInquisitor", 0);

        // Dungeons
        LootTracker.recombobulators =  initInt("catacombs", "recombobulator", 0);
        LootTracker.fumingPotatoBooks = initInt("catacombs", "fumingBooks", 0);
        // F1
        LootTracker.bonzoStaffs = initInt("catacombs", "bonzoStaff", 0);
        LootTracker.f1CoinsSpent = initDouble("catacombs", "floorOneCoins", 0);
        LootTracker.f1TimeSpent = initDouble("catacombs", "floorOneTime", 0);
        // F2
        LootTracker.scarfStudies = initInt("catacombs", "scarfStudies", 0);
        LootTracker.f2CoinsSpent = initDouble("catacombs", "floorTwoCoins", 0);
        LootTracker.f2TimeSpent = initDouble("catacombs", "floorTwoTime", 0);
        // F3
        LootTracker.adaptiveHelms = initInt("catacombs", "adaptiveHelm", 0);
        LootTracker.adaptiveChests = initInt("catacombs", "adaptiveChest", 0);
        LootTracker.adaptiveLegs = initInt("catacombs", "adaptiveLegging", 0);
        LootTracker.adaptiveBoots = initInt("catacombs", "adaptiveBoot", 0);
        LootTracker.adaptiveSwords = initInt("catacombs", "adaptiveSword", 0);
        LootTracker.f3CoinsSpent = initDouble("catacombs", "floorThreeCoins", 0);
        LootTracker.f3TimeSpent = initDouble("catacombs", "floorThreeTime", 0);
        // F4
        LootTracker.spiritWings = initInt("catacombs", "spiritWing", 0);
        LootTracker.spiritBones = initInt("catacombs", "spiritBone", 0);
        LootTracker.spiritBoots = initInt("catacombs", "spiritBoot", 0);
        LootTracker.spiritSwords = initInt("catacombs", "spiritSword", 0);
        LootTracker.spiritBows = initInt("catacombs", "spiritBow", 0);
        LootTracker.epicSpiritPets = initInt("catacombs", "spiritPetEpic", 0);
        LootTracker.legSpiritPets = initInt("catacombs", "spiritPetLeg", 0);
        LootTracker.f4CoinsSpent = initDouble("catacombs", "floorFourCoins", 0);
        LootTracker.f4TimeSpent = initDouble("catacombs", "floorFourTime", 0);
        // F5
        LootTracker.warpedStones = initInt("catacombs", "warpedStone", 0);
        LootTracker.shadowAssHelms = initInt("catacombs", "shadowAssassinHelm", 0);
        LootTracker.shadowAssChests = initInt("catacombs", "shadowAssassinChest", 0);
        LootTracker.shadowAssLegs = initInt("catacombs", "shadowAssassinLegging", 0);
        LootTracker.shadowAssBoots = initInt("catacombs", "shadowAssassinBoot", 0);
        LootTracker.lastBreaths = initInt("catacombs", "lastBreath", 0);
        LootTracker.lividDaggers = initInt("catacombs", "lividDagger", 0);
        LootTracker.shadowFurys = initInt("catacombs", "shadowFury", 0);
        LootTracker.f5CoinsSpent = initDouble("catacombs", "floorFiveCoins", 0);
        LootTracker.f5TimeSpent = initDouble("catacombs", "floorFiveTime", 0);
        // F6
        LootTracker.ancientRoses = initInt("catacombs", "ancientRose", 0);
        LootTracker.precursorEyes = initInt("catacombs", "precursorEye", 0);
        LootTracker.giantsSwords = initInt("catacombs", "giantsSword", 0);
        LootTracker.necroLordHelms = initInt("catacombs", "necroLordHelm", 0);
        LootTracker.necroLordChests = initInt("catacombs", "necroLordChest", 0);
        LootTracker.necroLordLegs = initInt("catacombs", "necroLordLegging", 0);
        LootTracker.necroLordBoots = initInt("catacombs", "necroLordBoot", 0);
        LootTracker.necroSwords = initInt("catacombs", "necroSword", 0);
        LootTracker.f6CoinsSpent = initDouble("catacombs", "floorSixCoins", 0);
        LootTracker.f6TimeSpent = initDouble("catacombs", "floorSixTime", 0);
        // F7
        LootTracker.witherBloods = initInt("catacombs", "witherBlood", 0);
        LootTracker.witherCloaks = initInt("catacombs", "witherCloak", 0);
        LootTracker.implosions = initInt("catacombs", "implosion", 0);
        LootTracker.witherShields = initInt("catacombs", "witherShield", 0);
        LootTracker.shadowWarps = initInt("catacombs", "shadowWarp", 0);
        LootTracker.necronsHandles = initInt("catacombs", "necronsHandle", 0);
        LootTracker.autoRecombs = initInt("catacombs", "autoRecomb", 0);
        LootTracker.witherHelms = initInt("catacombs", "witherHelm", 0);
        LootTracker.witherChests = initInt("catacombs", "witherChest", 0);
        LootTracker.witherLegs = initInt("catacombs", "witherLegging", 0);
        LootTracker.witherBoots = initInt("catacombs", "witherBoot", 0);
        LootTracker.f7CoinsSpent = initDouble("catacombs", "floorSevenCoins", 0);
        LootTracker.f7TimeSpent = initDouble("catacombs", "floorSevenTime", 0);

        // Ghost
        LootTracker.sorrows = initInt("ghosts", "sorrow", 0);
        LootTracker.voltas = initInt("ghosts", "volta", 0);
        LootTracker.plasmas = initInt("ghosts", "plasma", 0);
        LootTracker.ghostlyBoots = initInt("ghosts", "ghostlyBoots", 0);
        LootTracker.bagOfCashs = initInt("ghosts", "bagOfCash", 0);


        // Misc
        LootDisplay.display = initString("misc", "display", "off");
        LootDisplay.auto = initBoolean("misc", "autoDisplay", false);
        SkillTracker.showSkillTracker = initBoolean("misc", "showSkillTracker", false);
        Glaxy.firstLaunch = initBoolean("misc", "firstLaunch", true);

        // Locations
        ScaledResolution scaled = new ScaledResolution(Minecraft.getMinecraft());
        int height = scaled.getScaledHeight();
        MoveCommand.coordsXY[0] = initInt("locations", "coordsX", 5);
        MoveCommand.coordsXY[1] = initInt("locations", "coordsY", height - 25);
        MoveCommand.displayXY[0] = initInt("locations", "displayX", 80);
        MoveCommand.displayXY[1] = initInt("locations", "displayY", 5);
        MoveCommand.dungeonTimerXY[0] = initInt("locations", "dungeonTimerX", 5);
        MoveCommand.dungeonTimerXY[1] = initInt("locations", "dungeonTimerY", 5);
        MoveCommand.skill50XY[0] = initInt("locations", "skill50X", 40);
        MoveCommand.skill50XY[1] = initInt("locations", "skill50Y", 10);
        MoveCommand.lividHpXY[0] = initInt("locations", "lividHpX", 40);
        MoveCommand.lividHpXY[1] = initInt("locations", "lividHpY", 20);
        MoveCommand.cakeTimerXY[0] = initInt("locations", "cakeTimerX", 40);
        MoveCommand.cakeTimerXY[1] = initInt("locations", "cakeTimerY", 30);
        MoveCommand.skillTrackerXY[0] = initInt("locations", "skillTrackerX", 40);
        MoveCommand.skillTrackerXY[1] = initInt("locations", "skillTrackerY", 50);
        MoveCommand.waterAnswerXY[0] = initInt("locations", "waterAnswerX", 100);
        MoveCommand.waterAnswerXY[1] = initInt("locations", "waterAnswerY", 100);
        MoveCommand.bonzoTimerXY[0] = initInt("locations", "bonzoTimerX", 40);
        MoveCommand.bonzoTimerXY[1] = initInt("locations", "bonzoTimerY", 80);
        MoveCommand.golemTimerXY[0] = initInt("locations", "golemTimerX", 100);
        MoveCommand.golemTimerXY[1] = initInt("locations", "golemTimerY", 30);

        // Scales
        ScaleCommand.coordsScale = initDouble("scales", "coordsScale", 1);
        ScaleCommand.displayScale = initDouble("scales", "displayScale", 1);
        ScaleCommand.skillTrackerScale = initDouble("scales", "skillTrackerScale", 1);

        // Colours
        Glaxy.MAIN_COLOUR = initString("colors", "main", EnumChatFormatting.GREEN.toString());
        Glaxy.SECONDARY_COLOUR = initString("colors", "secondary", EnumChatFormatting.DARK_GREEN.toString());
        Glaxy.DELIMITER_COLOUR = initString("colors", "delimiter", EnumChatFormatting.AQUA.toString() + EnumChatFormatting.STRIKETHROUGH.toString());
        Glaxy.ERROR_COLOUR = initString("colors", "error", EnumChatFormatting.RED.toString());
        Glaxy.TYPE_COLOUR = initString("colors", "type", EnumChatFormatting.GREEN.toString());
        Glaxy.VALUE_COLOUR = initString("colors", "value", EnumChatFormatting.DARK_GREEN.toString());
        Glaxy.SKILL_AVERAGE_COLOUR = initString("colors", "skillAverage", EnumChatFormatting.GOLD.toString());
        Glaxy.ANSWER_COLOUR = initString("colors", "answer", EnumChatFormatting.DARK_GREEN.toString());
        NoF3Coords.COORDS_COLOUR = initString("colors", "coordsDisplay", EnumChatFormatting.WHITE.toString());
        SkillTracker.SKILL_TRACKER_COLOUR = initString("colors", "skillTracker", EnumChatFormatting.AQUA.toString());

        // Commands
        if (!hasKey("commands", "reparty")) writeBooleanConfig("commands", "reparty", false);
    }

}