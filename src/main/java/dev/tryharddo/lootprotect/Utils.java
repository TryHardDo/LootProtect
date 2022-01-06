package dev.tryharddo.lootprotect;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    public static void addPlayerToEnabledList(@NotNull Player player) throws IllegalArgumentException
    {
        UUID targetPlayer = player.getUniqueId();

        if (LootProtect.getEnabledList().contains(targetPlayer))
        {
            throw new IllegalArgumentException("§cYou have already turned on this feature.");
        }

        LootProtect.getEnabledList().add(targetPlayer);
    }

    public static void removePlayerFromEnabledList(@NotNull Player player) throws IllegalArgumentException
    {
        UUID targetPlayer = player.getUniqueId();

        if (! LootProtect.getEnabledList().contains(targetPlayer))
        {
            throw new IllegalArgumentException("§cYou have already turned off this feature.");
        }

        LootProtect.getEnabledList().remove(targetPlayer);
    }

    public static boolean isLootProtectEnabled(@NotNull Player player)
    {
        return LootProtect.getEnabledList().contains(player.getUniqueId());
    }

    public static void addItemsToCacheByUUID(@NotNull UUID uuid, @NotNull List<Item> items)
    {
        try
        {
            items.forEach(item -> LootProtect.getProtectionCache().put(uuid, item));
        } catch (NullPointerException e)
        {
            LootProtect.getPluginLogger().severe("There was an error while tried to add items to the cache. Details:");
            e.printStackTrace();
        }
    }

    public static boolean isRestrictedItem(@NotNull Item item)
    {
        return LootProtect.getProtectionCache().containsValue(item);
    }

    public static @Nullable UUID getItemOwner(@NotNull Item item)
    {
        for (Map.Entry<UUID, Item> cachePiece : LootProtect.getProtectionCache().entrySet())
        {
            if (item.equals(cachePiece.getValue())) return cachePiece.getKey();
        }

        return null;
    }

    public static void removeItemFromCache(@NotNull Item item)
    {
        for (Map.Entry<UUID, Item> cachePiece : LootProtect.getProtectionCache().entrySet())
        {
            if (item.equals(cachePiece.getValue()))
            {
                LootProtect.getProtectionCache().remove(cachePiece.getKey(), cachePiece.getValue());
                return;
            }
        }
    }
}
