package WINGS7N.HotFurnaceItems.WinUPD;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    Logger log = this.getLogger();
    String os = System.getProperty("os.name");
    boolean win = false;

    public void onEnable() {
        if (os.contains("Windows")) {
            win = true;
            log.info("Running on Windows");
            log.info(String.format("AutoUpdate %s on server stop enabled", UpdateData.UpdatePlugin));
            log.info("By " + UpdateData.author);
        } else {
            log.warning(String.format("Running not on Windows OS, you can delete %s.jar", UpdateData.UpdatePlugin));
        }
    }

    public void onDisable() {
        if (win) {
            new UnloadPlugin(UpdateData.UpdatePlugin);

            //UPDATE
            log.info(UpdateData.prefix + ChatColor.RED + "Updating " + UpdateData.UpdatePlugin + "...");

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
                con.setRequestProperty("User-Agent", String.format("WINGS07/%s-SelfUpdater-Windows", UpdateData.UpdatePlugin));

                // Get input stream
                try (InputStream input = con.getInputStream()) {
                    Files.copy(input, dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }

                log.info(UpdateData.prefix + ChatColor.RED + "Update success!");
            } catch (Exception ex) {
                ex.printStackTrace();
                log.severe(UpdateData.prefix + ChatColor.RED + "Update failed, " + ex.getMessage());
            }
        } else {
            log.warning(String.format("Running not on Windows OS, you can delete %s_WinUPD.jar", UpdateData.UpdatePlugin));
        }
    }
}
