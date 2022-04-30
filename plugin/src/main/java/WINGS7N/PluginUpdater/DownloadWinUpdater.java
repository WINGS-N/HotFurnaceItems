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

public class DownloadWinUpdater {

    public Boolean updateInProgress = false;
    Plugin pl = Bukkit.getPluginManager().getPlugin(SS.pl);
    FileConfiguration config = Objects.requireNonNull(pl).getConfig();

    public DownloadWinUpdater(CommandSender s) {
        if (updateInProgress) {
            s.sendMessage(ChatColor.RED + "Update already in progress");
            return;
        }

        s.sendMessage(UpdateData.prefix + ChatColor.RED + "Downloading Windows Updater for " + UpdateData.UpdatePlugin + "...");

        updateInProgress = true;

        try {
            Method getFile = JavaPlugin.class.getDeclaredMethod("getFile");
            getFile.setAccessible(true);
            File dest = new File("plugins/" + UpdateData.UpdatePlugin + "_WinUPD" + UpdateData.ext);

            //Connect
            URL url =
                    new URL(UpdateData.URL +
                            UpdateData.UpdatePlugin + "_WinUPD" +
                            UpdateData.ext);

            // Creating con
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "WINGS7N/HotFurnaceItems-SelfUpdater-Windows");

            // Get input stream
            try (InputStream input = con.getInputStream()) {
                Files.copy(input, dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            s.sendMessage(UpdateData.prefix + ChatColor.RED + "Download updater success!");
        } catch (Exception ex) {
            ex.printStackTrace();
            s.sendMessage(UpdateData.prefix + ChatColor.RED + "Download updater failed, " + ex.getMessage());
        } finally {
            updateInProgress = false;
        }
    }
}
