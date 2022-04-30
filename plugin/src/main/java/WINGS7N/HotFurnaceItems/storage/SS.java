package WINGS7N.HotFurnaceItems.storage;

import net.md_5.bungee.api.ChatColor;

public interface SS {

	String pl = "HotFurnaceItems";
	String loading = "Loading...";
	String unloading = "Unloading...";
	String detected = "Detected ";
	String by = "By WINGS7N " + "\uD83D\uDC9C";
	String WinBy = "By WINGS7N";

	String WindowsSelfUpdateNote = String.format("SelfUpdate on Windows OS still in beta. Plugin %s_WinUPD.jar responsible for SelfUpdate, please, don't rename/remove it.", pl);
	String NoDevBuildSelfUpdate = "Running dev build, auto update cancelled";
	String DevBuild = ChatColor.RED + String.format("______ %s DEV BUILD! ______", pl);
}
