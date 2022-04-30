package WINGS7N.PluginUpdater;

import WINGS7N.HotFurnaceItems.storage.SS;
import WINGS7N.HotFurnaceItems.storage.UpdateData;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class SelfUpdate {

    public Boolean updateInProgress = false;
    Plugin pl = Bukkit.getPluginManager().getPlugin(SS.pl);
    FileConfiguration config = Objects.requireNonNull(pl).getConfig();

    public SelfUpdate(CommandSender s) {
        if (updateInProgress) {
            s.sendMessage(ChatColor.RED + "Update already in progress");
            return;
        }

        s.sendMessage(UpdateData.prefix + ChatColor.RED + "Self-Updating " + UpdateData.UpdatePlugin + "...");

        updateInProgress = true;

        try {
            Method getFile = JavaPlugin.class.getDeclaredMethod("getFile");
            getFile.setAccessible(true);
            File dest = new File("plugins/" + UpdateData.UpdatePlugin + UpdateData.ext);

            //Connect
            URL url =
                    new URL(UpdateData.URL +
                            UpdateData.UpdatePlugin +
                            UpdateData.ext);

            // Creating con
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", String.format("WINGS7N/%s-SelfUpdater", UpdateData.UpdatePlugin));

            // Get input stream
            try (InputStream input = con.getInputStream()) {
                Files.copy(input, dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            s.sendMessage(UpdateData.prefix + ChatColor.RED + "Update success!");
        } catch (Exception ex) {
            ex.printStackTrace();
            s.sendMessage(UpdateData.prefix + ChatColor.RED + "Update failed, " + ex.getMessage());
            new SelfUpdateFromBackupServer(s);
        } finally {
            updateInProgress = false;
        }
    }
}
