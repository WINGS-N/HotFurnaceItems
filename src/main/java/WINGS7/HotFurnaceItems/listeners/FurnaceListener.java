package WINGS7.HotFurnaceItems.listeners;

import WINGS7.HotFurnaceItems.Damage;
import WINGS7.HotFurnaceItems.storage.SS;
import WINGS7.calc.Chance;
import WINGS7.calc.ChanceFormat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;


public class FurnaceListener implements Listener {
    Plugin pl = Objects.requireNonNull(Bukkit.getPluginManager().getPlugin(SS.pl));
    FileConfiguration config = pl.getConfig();

    @SuppressWarnings("deprecation")
    @EventHandler
    public void Furnace(FurnaceExtractEvent e) {
        Player p = e.getPlayer();
        //Damage player if potholder not in hand & chance success
        if (Chance.go(ChanceFormat.go(config.getInt("damage.chance"))) && !(p.getItemInHand().getType() == Material.valueOf(config.getString("potholder.material")))) {
            new Damage().go(p);
        }

        //Clear potholder if chance success (break chance)
        if (Chance.go(ChanceFormat.go(config.getInt("potholder.break_chance"))) && (p.getItemInHand().getType() == Material.valueOf(config.getString("potholder.material")))) {
            int ph_amount = p.getItemInHand().getAmount();
            p.getItemInHand().setAmount(ph_amount - 1);
            p.playSound(p.getLocation(), Sound.valueOf(config.getString("potholder.break_sound")), 100, config.getInt("potholder.break_sound_pitch"));
        }
    }
}
