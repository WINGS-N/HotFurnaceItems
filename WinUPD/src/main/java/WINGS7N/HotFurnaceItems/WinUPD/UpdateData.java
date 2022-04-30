package WINGS7N.HotFurnaceItems.WinUPD;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.util.Objects;

public interface UpdateData {

    //Plugin
    String UpdatePlugin = "HotFurnaceItems";
    String author = Objects.requireNonNull(Bukkit.getPluginManager().getPlugin(UpdateData.UpdatePlugin + "_WinUPD")).getDescription().getAuthors().get(0);
    String prefix = ChatColor.BLACK +
            "[" +
            ChatColor.LIGHT_PURPLE +
            UpdatePlugin +
            ChatColor.BLACK +
            "]" +
            ChatColor.RESET +
            " ";

    //File ext
    String ext = ".jar";

    //GetJAR server (GitHub)
    String URL = String.format("https://github.com/%s/%s/releases/latest/download/", author, UpdatePlugin);
}
