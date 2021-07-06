package fr.quenk.manhunt.tasks;

import fr.quenk.manhunt.MHMain;
import fr.quenk.manhunt.utils.ChatUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/*
 *QuenK_ 04/07/2021 | 20:06 |ManHunt
 */
public class MHGameCycle extends BukkitRunnable {
    private MHMain main;
    public MHGameCycle(MHMain main){
        this.main = main;
    }

    private int timer = 900;
    @Override
    public void run() {

        World world = Bukkit.getWorld("world");
        Location location = new Location(world, 0, 0, 0);

        Location location2 = new Location(world, 0, 0, 0);
        location2.setX( location2.getX() + Math.random() * 40 * 2 - 10);
        location2.setZ( location2.getZ() + Math.random() * 40 * 2 - 10);

        location.setY( world.getHighestBlockAt(location.getBlockX(), location.getBlockZ() ).getY()+3);
        location2.setY( world.getHighestBlockAt(location2.getBlockX(), location2.getBlockZ() ).getY()+3);

        WorldBorder worldBorder = world.getWorldBorder();
        worldBorder.setCenter(0,0);
        if(timer == 900){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+"Final fight in"+ ChatColor.RED+" 15"+ChatColor.GRAY+" minutes.");
            for(Player pls : Bukkit.getOnlinePlayers()){
                if(main.getPreyplayer().contains(pls.getName())){
                    pls.sendMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+"You are prey!");
                    PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 6000, 2);
                    PotionEffect haste = new PotionEffect(PotionEffectType.FAST_DIGGING, 6000, 2);
                    pls.addPotionEffect(speed);
                    pls.addPotionEffect(haste);

                    //stuff
                    ItemStack axe = new ItemStack(Material.STONE_AXE);
                    ItemStack pickaxe = new ItemStack(Material.STONE_PICKAXE);
                    ItemStack shovel = new ItemStack(Material.STONE_SHOVEL);

                    pls.getInventory().addItem(axe);
                    pls.getInventory().addItem(pickaxe);
                    pls.getInventory().addItem(shovel);
                    pls.setPlayerListName(ChatColor.GRAY+"["+ChatColor.GREEN+"Prey"+ChatColor.GRAY+"]"+" "+pls.getName());
                }else{
                    pls.sendMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+"You are hunter");

                    //stuff
                    ItemStack sword = new ItemStack(Material.IRON_SWORD);
                    ItemStack helmet = new ItemStack(Material.IRON_HELMET);
                    ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
                    ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
                    ItemStack boots = new ItemStack(Material.IRON_BOOTS);

                    pls.getInventory().addItem(sword);
                    pls.getInventory().addItem(helmet);
                    pls.getInventory().addItem(chestplate);
                    pls.getInventory().addItem(leggings);
                    pls.getInventory().addItem(boots);
                    pls.setPlayerListName(ChatColor.GRAY+"["+ChatColor.RED+"Hunter"+ChatColor.GRAY+"]"+" "+pls.getName());
                }
            }
        }
        if(timer == 600){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+"Final fight in"+ ChatColor.RED+" 10"+ChatColor.GRAY+" minutes.");
            worldBorder.setSize(600);
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+"Border set (300x300)");
        }
        if(timer == 300){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+"Final fight in"+ ChatColor.RED+" 5"+ChatColor.GRAY+" minutes.");
            worldBorder.setSize(200);
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+"Border set (100x100)");
        }
        if(timer == 60 || timer == 30 || timer == 20 ||timer == 15){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+"Final fight in"+ ChatColor.RED+timer+ChatColor.GRAY+" seconds.");
        }
        if(timer == 10 ||timer == 9 || timer == 8 ||timer == 7||timer == 6||timer == 5||timer == 4||timer == 3||timer == 2||timer == 1){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+"Final fight in "+ ChatColor.RED+timer+ChatColor.GRAY+" seconds.");
        }
        if(timer == 0){
            worldBorder.setSize(100);
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+"Border set (50x50)");
            for(Player pls : Bukkit.getOnlinePlayers()){
                pls.playSound(pls.getLocation(), Sound.ENTITY_GOAT_DEATH,20,20);
                if(main.getHuntplayer().contains(pls.getName())){
                    pls.teleport(location);
                }
                if(main.getPreyplayer().contains(pls.getName())){
                    pls.teleport(location2);
                }
            }
            cancel();
        }
        timer--;
    }
}
