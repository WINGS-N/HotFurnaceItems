package WINGS7.HotFurnaceItems;

import java.util.logging.Logger;

import WINGS7.HotFurnaceItems.listeners.FurnaceListener;
import WINGS7.HotFurnaceItems.storage.SS;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    Logger log = this.getLogger();
    FileConfiguration cfg = this.getConfig();
    String os = System.getProperty("os.name");

    public void onEnable() {
        log.info(SS.loading);


        this.saveDefaultConfig();
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
        cfg.options().copyDefaults(true);
        this.saveConfig();


        if (os.contains("Windows")) {
            log.info(SS.WinBy);
        } else {
            log.info(SS.by);
        }
        Bukkit.getPluginManager().registerEvents(new FurnaceListener(), this);
    }

    public void onDisable() {
        log.info(SS.unloading);
    }
}
