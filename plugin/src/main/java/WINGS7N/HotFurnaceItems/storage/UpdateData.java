package WINGS7N.HotFurnaceItems.storage;

import net.md_5.bungee.api.ChatColor;

public interface UpdateData {

    //Plugin
    String UpdatePlugin = SS.pl;
    String prefix = ChatColor.BLACK +
            "[" +
            ChatColor.LIGHT_PURPLE +
            SS.pl +
            ChatColor.BLACK +
            "]" +
            ChatColor.RESET +
            " ";

    //File ext
    String ext = ".jar";

    //GetJAR server (GitHub)
    String URL = String.format("https://github.com/WINGS7N/%s/releases/latest/download/", UpdatePlugin);
    String deprecatedURL = String.format("https://github.com/WINGS07/%s/releases/latest/download/", UpdatePlugin);
}
