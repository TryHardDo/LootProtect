package dev.tryharddo.lootprotect.commands;

import dev.tryharddo.lootprotect.LootProtect;
import dev.tryharddo.lootprotect.Utils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BaseCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args)
    {
        if (args.length == 0)
        {
            sender.sendMessage("HELP LIST IDE JÖHET MAJD!!!!!"); // Marked as "should be removed later"
        } else
        {
            if (sender instanceof Player)
            {
                Player player = ((Player) sender);

                if (args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("on"))
                {
                    try
                    {
                        Utils.addPlayerToEnabledList(player);
                        player.sendMessage("§aYou have successfully §2§lENABLED§a the loot steal prevent feature.");
                    } catch (IllegalArgumentException e)
                    {
                        player.sendMessage(e.getMessage());
                    }
                } else if (args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("off"))
                {
                    try
                    {
                        Utils.removePlayerFromEnabledList(player);
                        player.sendMessage("§aYou have successfully §c§lDISABLED§a the loot steal prevent feature.");
                    } catch (IllegalArgumentException e)
                    {
                        player.sendMessage(e.getMessage());
                    }
                } else if (args[0].equalsIgnoreCase("count"))
                {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§a§lVédett blokkok: §c§l" + LootProtect.getProtectionCache().size()));
                } else
                {
                    player.sendMessage("§aNo such argument! Use /" + label + " help for more information.");
                }
            }
        }
        return true;
    }
}
