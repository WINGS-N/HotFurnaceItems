package io.WINGS.HotFurnaceItems;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.plugin.Plugin;

import io.WINGS.HotFurnaceItems.storage.SS;
import io.WINGS.calc.Chance;
import io.WINGS.calc.ChanceFormat;

public class FurnaceListener implements Listener {

	Plugin pl = Bukkit.getPluginManager().getPlugin(SS.pl);
	Logger log = pl.getLogger();
	FileConfiguration config = pl.getConfig();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void Furnace(FurnaceExtractEvent e) {
		if(e.getPlayer() != null && e.getItemType() != null) {
			Player p = e.getPlayer();
			if(Chance.go(ChanceFormat.go(config.getInt("chance")))) {
				if(p.getItemInHand().getType() != null && p.getItemInHand().getType() == Material.LEATHER) {
					return;
				} else {
					new Damage(p);
				}
			} else {
				return;
			}
		}
	}
}
