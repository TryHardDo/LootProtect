package dev.tryharddo.lootprotect.commands;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.jetbrains.annotations.NotNull;

public class Utils
{
    public static void logStartup(@NotNull Plugin plugin)
    {
        PluginDescriptionFile descriptionFile = plugin.getDescription();

        String[] logMessages = new String[]{
                "",
                "     §aLoot§2Protect §e■ §f" + descriptionFile.getVersion(),
                "     §eDeveloped by: §fTryHardDo",
                "     §aPlugin is ready to use!",
                "     §aCurrent API version: §7§o" + Bukkit.getVersion(),
                ""
        };

        Bukkit.getConsoleSender().sendMessage(logMessages);
    }
}
