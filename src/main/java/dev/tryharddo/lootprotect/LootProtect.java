package dev.tryharddo.lootprotect;

import dev.tryharddo.lootprotect.commands.BaseCommand;
import dev.tryharddo.lootprotect.commands.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class LootProtect extends JavaPlugin
{
    private static LootProtect instance;
    private static Logger pluginLogger;

    public static LootProtect getInstance()
    {
        return instance;
    }

    public static Logger getPluginLogger()
    {
        return pluginLogger;
    }

    @Override
    public void onEnable()
    {
        // MAIN SETUP - START -
        instance = this;
        pluginLogger = this.getLogger();
        // MAIN SETUP - END -

        getPluginLogger().info("Setting up the plugin.");

        Bukkit.getPluginManager().registerEvents(new ListenerClass(), this);
        Objects.requireNonNull(this.getCommand("lootprotect")).setExecutor(new BaseCommand());

        Utils.logStartup(this);
    }

    @Override
    public void onDisable()
    {
        // If there is a remaining task it should be stopped properly.
        Bukkit.getScheduler().cancelTasks(this);

        getPluginLogger().info("Plugin is now disabled.");
    }
}
