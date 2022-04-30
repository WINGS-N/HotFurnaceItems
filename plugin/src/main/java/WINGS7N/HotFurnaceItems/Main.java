package WINGS7N.HotFurnaceItems;

import java.io.File;
import java.util.logging.Logger;

import WINGS7N.HotFurnaceItems.listeners.FurnaceListener;
import WINGS7N.HotFurnaceItems.storage.BS;
import WINGS7N.HotFurnaceItems.storage.IS;
import WINGS7N.HotFurnaceItems.storage.SS;
import WINGS7N.HotFurnaceItems.storage.UpdateData;
import WINGS7N.PluginUpdater.DownloadWinUpdater;
import WINGS7N.PluginUpdater.SelfUpdate;
import WINGS7N.providers.metrics.Metrics;
import WINGS7N.providers.metrics.MetricsData;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    Logger log = this.getLogger();
    FileConfiguration cfg = this.getConfig();
    String os = System.getProperty("os.name");
    File winupdater = new File("plugins/" + UpdateData.UpdatePlugin + "_WinUPD" + UpdateData.ext);
    public boolean debug = cfg.getBoolean("DEV.DEBUG");

    public void onEnable() {
        log.info(SS.loading);
        log.info(SS.detected + Bukkit.getVersion());

        if (os.contains("Windows")) {
            if (!winupdater.exists()) {
                new DownloadWinUpdater(Bukkit.getConsoleSender());
            } else {
                log.warning(SS.WindowsSelfUpdateNote);
            }
        } else if (BS.dev) {
            debug = true;
            log.warning(SS.DevBuild);
            log.warning(SS.NoDevBuildSelfUpdate);
        } else {
            if (winupdater.exists()) {
                winupdater.delete();
            }
            new SelfUpdate(Bukkit.getConsoleSender());
        }


        this.saveDefaultConfig();
        cfg.addDefault("version", IS.config_version);
        cfg.addDefault("damage.chance", 35);
        cfg.addDefault("damage.count", 3);
        cfg.addDefault("damage.effect", "BLINDNESS");
        cfg.addDefault("damage.effect_time", 60);
        cfg.addDefault("damage.effect_amp", 255);
        cfg.addDefault("damage.sound", "ENTITY_ENDERMAN_SCREAM");
        cfg.addDefault("damage.sound_pitch", 3);
        cfg.addDefault("potholder.material", "LEATHER");
        cfg.addDefault("potholder.break_chance", 1);
        cfg.addDefault("potholder.break_sound", "ENTITY_ITEM_BREAK");
        cfg.addDefault("potholder.break_sound_pitch", 1);
        cfg.addDefault("DEV.DEBUG", false);
        cfg.options().copyDefaults(true);
        this.saveConfig();


        if (os.contains("Windows")) {
            log.info(SS.WinBy);
        } else {
            log.info(SS.by);
        }

        if (!BS.dev) {
            Metrics m = new Metrics(this, MetricsData.id);
            m.getPluginData();
        }

        Bukkit.getPluginManager().registerEvents(new FurnaceListener(), this);
    }

    public void onDisable() {
        if (os.contains("Windows")) {
            if (!winupdater.exists()) {
                new DownloadWinUpdater(Bukkit.getConsoleSender());
            } else {
                log.warning(SS.WindowsSelfUpdateNote);
            }
        } else if (BS.dev) {
            debug = true;
            log.warning(SS.DevBuild);
            log.warning(SS.NoDevBuildSelfUpdate);
        } else {
            if (winupdater.exists()) {
                winupdater.delete();
            }
            new SelfUpdate(Bukkit.getConsoleSender());
        }

        log.info(SS.unloading);
    }
}
