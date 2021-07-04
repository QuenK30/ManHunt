package fr.quenk.manhunt.tasks;

import fr.quenk.manhunt.MHMain;
import fr.quenk.manhunt.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
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
        if(timer == 900){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+"Final fight in"+ ChatColor.RED+" 15"+ChatColor.GRAY+" minutes.");
            for(Player pls : Bukkit.getOnlinePlayers()){
                if(main.getPreyplayer().contains(pls.getName())){
                    pls.sendMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+"You are prey!");
                    PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 18000, 2);
                    PotionEffect haste = new PotionEffect(PotionEffectType.FAST_DIGGING, 18000, 1);
                    pls.addPotionEffect(speed);
                    pls.addPotionEffect(haste);
                }else{
                    pls.sendMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+"You are hunter");
                }
            }
        }
        if(timer == 600){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+"Final fight in"+ ChatColor.RED+" 10"+ChatColor.GRAY+" minutes.");
        }
        if(timer == 300){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+"Final fight in"+ ChatColor.RED+" 5"+ChatColor.GRAY+" minutes.");
        }
        if(timer == 60 || timer == 30 || timer == 20 ||timer == 15){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+"Final fight in"+ ChatColor.RED+timer+ChatColor.GRAY+" minutes.");
        }
        if(timer == 10 ||timer == 9 || timer == 8 ||timer == 7||timer == 6||timer == 5||timer == 4||timer == 3||timer == 2||timer == 1){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+"Final fight in"+ ChatColor.RED+" 10"+ChatColor.GRAY+" minutes.");
            for(Player pls : Bukkit.getOnlinePlayers()){
                pls.playSound(pls.getLocation(), Sound.ENTITY_BAT_HURT,20,20);
            }
            cancel();
        }
        timer--;
    }
}
