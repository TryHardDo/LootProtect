package dev.tryharddo.lootprotect;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ListenerClass implements Listener
{
    @EventHandler
    public void onDrop(final @NotNull BlockDropItemEvent e)
    {
        if (! Utils.isLootProtectEnabled(e.getPlayer())) return;

        Player p = e.getPlayer();

        Utils.addItemsToCacheByUUID(p.getUniqueId(), e.getItems());
    }

    @EventHandler
    public void onPickup(final @NotNull EntityPickupItemEvent e)
    {
        if (! Utils.isRestrictedItem(e.getItem())) return;

        Item item = e.getItem();
        UUID owner = Utils.getItemOwner(item);

        if (owner == null) return;

        if (! (e.getEntity() instanceof Player))
        {
            e.setCancelled(true);
            return;
        }

        Player picker = ((Player) e.getEntity());

        if (picker.getUniqueId().equals(owner))
        {
            Utils.removeItemFromCache(item);
            return;
        }

        e.setCancelled(true);
    }
}
