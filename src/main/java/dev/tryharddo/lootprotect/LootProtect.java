package dev.tryharddo.lootprotect;

import dev.tryharddo.lootprotect.commands.BaseCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.logging.Logger;

public final class LootProtect extends JavaPlugin
{
    private static LootProtect instance;
    private static Logger pluginLogger;
    private static final List<UUID> enabledList = new ArrayList<>();
    private static final HashMap<UUID, Item> protectionCache = new LinkedHashMap<>();

    public static LootProtect getInstance()
    {
        return instance;
    }

    public static Logger getPluginLogger()
    {
        return pluginLogger;
    }

    public static List<UUID> getEnabledList()
    {
        return enabledList;
    }

    public static HashMap<UUID, Item> getProtectionCache()
    {
        return protectionCache;
    }

    @Override
    public void onEnable()
    {
        instance = this;
        pluginLogger = this.getLogger();

        getPluginLogger().info("Setting up the plugin.");

        Bukkit.getPluginManager().registerEvents(new ListenerClass(), this);
        Objects.requireNonNull(this.getCommand("lootprotect")).setExecutor(new BaseCommand());

        Utils.logStartup(this);
    }

    @Override
    public void onDisable()
    {
        Bukkit.getScheduler().cancelTasks(this);

        getPluginLogger().info("Plugin is now disabled.");
    }
}
