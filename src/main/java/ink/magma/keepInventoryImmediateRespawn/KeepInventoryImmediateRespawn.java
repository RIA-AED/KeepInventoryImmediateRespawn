package ink.magma.keepInventoryImmediateRespawn;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class KeepInventoryImmediateRespawn extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDeath(PlayerDeathEvent event) {
        boolean isKeepInv = event.getKeepInventory();
        if (!isKeepInv) return;

        boolean isWorldImmediateRespawn = Boolean.TRUE.equals(event.getEntity().getWorld().getGameRuleValue(GameRule.DO_IMMEDIATE_RESPAWN));
        if (isWorldImmediateRespawn) return;

        // 1 tick
        Bukkit.getScheduler().runTask(this, () -> event.getEntity().spigot().respawn());
    }
}
