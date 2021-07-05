package fr.quenk.manhunt.tasks;

import fr.quenk.manhunt.MHMain;
import fr.quenk.manhunt.utils.ChatUtils;
import fr.quenk.manhunt.utils.MHState;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/*
 *QuenK_ 04/07/2021 | 11:37 |ManHunt
 */
public class MHStart extends BukkitRunnable {
    private MHMain main;
    public MHStart(MHMain main){
        this.main = main;
    }

    private int timer = 30;
    @Override
    public void run() {
        for(Player pls : Bukkit.getOnlinePlayers()){
            pls.setLevel(timer);
        }

        //0 0
        World world = Bukkit.getWorld("world");
        Location location = new Location(world, 0, 0, 0);
        location.setX( location.getX() + Math.random() * 10 * 2 - 10);
        location.setZ( location.getZ() + Math.random() * 10 * 2 - 10);

        Location location2 = new Location(world, 0, 0, 0);
        location2.setX( location2.getX() + Math.random() * 240 * 2 - 10);
        location2.setZ( location2.getZ() + Math.random() * 240 * 2 - 10);

        location.setY( world.getHighestBlockAt(location.getBlockX(), location.getBlockZ() ).getY()+1);
        location2.setY( world.getHighestBlockAt(location2.getBlockX(), location2.getBlockZ() ).getY()+1);


        if(timer == 30 || timer == 20 || timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage() + ChatColor.GRAY+"The game will start in "+ChatColor.RED+timer+ChatColor.GRAY+" seconds !");
            for(Player player : Bukkit.getOnlinePlayers()){
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,20,20);
            }
        }
        if(timer == 0){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage() + ChatColor.GRAY+"May luck be with the prey!");

            Random random = new Random();
            Player randomPlayer = Bukkit.getPlayer(main.getSpecplayer().get(random.nextInt(main.getSpecplayer().size())));
            main.getPreyplayer().add(randomPlayer.getName());
            main.getSpecplayer().remove(randomPlayer.getName());

            System.out.println("prey "+randomPlayer.getName());

            Player randomPlayer2 = Bukkit.getPlayer(main.getSpecplayer().get(random.nextInt(main.getSpecplayer().size())));
            main.getHuntplayer().add(randomPlayer2.getName());
            main.getSpecplayer().remove(randomPlayer2.getName());

            System.out.println("hunt "+randomPlayer2.getName());

            for(Player pls : Bukkit.getOnlinePlayers()){
                pls.playSound(pls.getLocation(), Sound.EVENT_RAID_HORN,20,20);
                if(main.getHuntplayer().contains(pls.getName())){
                    pls.teleport(location);
                }
                if(main.getPreyplayer().contains(pls.getName())){
                    pls.teleport(location2);
                }

                pls.getInventory().clear();
                pls.setGameMode(GameMode.SURVIVAL);
                pls.setFoodLevel(20);
                pls.setHealth(20);
                pls.setAllowFlight(false);
                pls.setLevel(0);
                pls.setExp(0);
            }
            WorldBorder worldBorder = world.getWorldBorder();
            worldBorder.setCenter(0,0);
            worldBorder.setSize(1000);
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+"Border set (500x500)");

            MHGameCycle mhGameCycle = new MHGameCycle(main);
            mhGameCycle.runTaskTimer(main, 0,20);
            cancel();
        }
        timer --;
    }
}
