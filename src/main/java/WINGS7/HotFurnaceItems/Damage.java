package WINGS7.HotFurnaceItems;

import WINGS7.HotFurnaceItems.storage.SS;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;


public class Damage {
    Plugin pl = Objects.requireNonNull(Bukkit.getPluginManager().getPlugin(SS.pl));
    FileConfiguration cfg = pl.getConfig();

    public boolean go(Player p) {
        p.damage(cfg.getInt("damage.count"));
        p.addPotionEffect(
                new PotionEffect(Objects.requireNonNull(PotionEffectType.getByName(Objects.requireNonNull(
                        cfg.getString("damage.effect")))),
                        cfg.getInt("damage.effect_time"),
                        cfg.getInt("damage.effect_amp")));
        p.playSound(p.getLocation(), Sound.valueOf(cfg.getString("damage.sound")), 100, cfg.getInt("damage.sound_pitch"));

        return true;
    }
}
