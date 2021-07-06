package fr.quenk.manhunt.event;

import fr.quenk.manhunt.MHMain;
import fr.quenk.manhunt.utils.ChatUtils;
import fr.quenk.manhunt.utils.MHState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

/*
 *QuenK_ 06/07/2021 | 11:52 |ManHunt
 */
public class MHPDamage implements Listener {
    private final MHMain main;

    public MHPDamage(MHMain main) {
        this.main = main;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (main.isState(MHState.WAITING) || main.isState(MHState.STARTING)) {
                System.out.println("waiting");
                event.setCancelled(true);
                player.sendMessage(ChatUtils.PREFIX.getMessage() + ChatColor.GRAY + " The game has not started!");

            }
        }
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        Player player = event.getEntity().getPlayer();
        Player killer = event.getEntity().getKiller();
        if (event.getDeathMessage().contains("hit the ground too hard")) {
            event.setDeathMessage(ChatUtils.PREFIX.getMessage() + ChatColor.RED + player.getName() + ChatColor.GRAY + " didn't see where he was going and fell off a cliff !");
            return;
        }
        if (event.getDeathMessage().contains("was shot by Skeleton")) {
            event.setDeathMessage(ChatUtils.PREFIX.getMessage() + ChatColor.RED + player.getName() + ChatColor.GRAY + " looks like a Hedgehog !");
            return;
        }
        if (event.getDeathMessage().contains("was slain by Spider")) {
            event.setDeathMessage(ChatUtils.PREFIX.getMessage() + ChatColor.RED + player.getName() + ChatColor.GRAY + " wanted to become a Spider-Man ! (It didn't work well)");
            return;
        }
        if (event.getDeathMessage().contains("was blown up by Creeper")) {
            event.setDeathMessage(ChatUtils.PREFIX.getMessage() + ChatColor.RED + player.getName() + ChatColor.GRAY + " made friends with a Creeper !");
            return;
        }
        if (event.getDeathMessage().contains("was slain by Husk")) {
            event.setDeathMessage(ChatUtils.PREFIX.getMessage() + ChatColor.RED + player.getName() + ChatColor.GRAY + " it's chained by a Husk !");
            return;
        }
        if (event.getDeathMessage().contains("was slain by Zombie")) {
            event.setDeathMessage(ChatUtils.PREFIX.getMessage() + ChatColor.RED + player.getName() + ChatColor.GRAY + " wanted to hug a Zombie !");
            return;
        }
        if (event.getDeathMessage().contains("was slain by Slime")) {
            event.setDeathMessage(ChatUtils.PREFIX.getMessage() + ChatColor.RED + player.getName() + ChatColor.GRAY + " is now slimy !");
            return;
        }
        if (event.getDeathMessage().contains("was slain by")) {
            event.setDeathMessage(ChatUtils.PREFIX.getMessage() + ChatColor.RED + player.getName() + ChatColor.GRAY + " was eliminated by " + ChatColor.RED + killer.getName());
            player.kickPlayer("You are dead");
            if(Bukkit.getOnlinePlayers().size() == 1){
                killer.kickPlayer("CONGRATULATIONS YOU WIN!");
            }
            Bukkit.shutdown();
            return;
        }
        event.setDeathMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+player.getName()+" died miserably !");
    }
}
