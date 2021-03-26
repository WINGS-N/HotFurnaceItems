package io.WINGS.HotFurnaceItems;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import io.WINGS.HotFurnaceItems.storage.SS;
import io.WINGS.calc.Chance;

public class Main extends JavaPlugin {

	Logger log = this.getLogger();
	FileConfiguration cfg = this.getConfig();
	String os = System.getProperty("os.name");
	
	public void onEnable() {
		if(os.contains("Windows")) {
			log.info(SS.WinLoading);
			log.info(SS.Windows);
		} else {
			log.info(SS.Loading);
		}
		this.saveDefaultConfig();
		cfg.addDefault("chance", 35);
		cfg.addDefault("damage", 3);
		cfg.addDefault("damageeffect", "BLINDNESS");
		cfg.addDefault("damageeffecttime", 60);
		cfg.addDefault("damageeffectamp", 255);
		cfg.addDefault("damagesound", "BLOCK_STONE_BREAK");
		cfg.addDefault("damagesoundpitch", 1);
		cfg.options().copyDefaults(true);
		this.saveConfig();
		if(os.contains("Windows")) {
			log.info(SS.WinBy);
		} else {
			log.info(SS.by);
		}
		if(Chance.go(0.6)) log.info(SS.Spotify);
		if(Chance.go(0.3)) log.info(SS.TWT);
		Bukkit.getPluginManager().registerEvents(new FurnaceListener(), this);
		//Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
	}
	
	public void onDisable() {
		log.info(SS.Unloading);
	}
}
