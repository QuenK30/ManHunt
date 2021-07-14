package fr.quenk.manhunt.event;

import fr.quenk.manhunt.MHMain;
import fr.quenk.manhunt.tasks.MHStart;
import fr.quenk.manhunt.utils.ChatUtils;
import fr.quenk.manhunt.utils.MHState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

/*
 *QuenK_ 04/07/2021 | 11:18 |ManHunt
 */
public class MHPListener implements Listener {
    private final MHMain main;
    public MHPListener(MHMain main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(main.isState(MHState.WAITING)) {
            event.setJoinMessage(ChatUtils.PREFIX.getMessage() + player.getName() + ChatUtils.PLUS.getMessage());
            main.getSpecplayer().add(player.getName());

            player.getInventory().clear();
            player.setHealth(20);
            player.setFoodLevel(20);
            player.setGameMode(GameMode.ADVENTURE);
            player.setLevel(0);
            player.getActivePotionEffects().clear();
            player.setAllowFlight(true);

        }

        if(main.isState(MHState.WAITING) && main.getSpecplayer().size() == 2){
            MHStart mhStart = new MHStart(main);
            mhStart.runTaskTimer(main,0,20);
            System.out.println("assez de joueur");
            System.out.println(main.getSpecplayer().size());
        }

        if(!main.isState(MHState.WAITING)){
            if(main.getHuntplayer().contains(player.getName()) || main.getPreyplayer().contains(player.getName())){
                Bukkit.broadcastMessage(ChatUtils.RECONNECTED.getMessage()+ player.getName());
            }else{
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(ChatUtils.PREFIX.getMessage()+" This game as already begin !");
                System.out.println("trop de joueur");
                event.setJoinMessage(null);
            }

        }
        player.setPlayerListHeader(ChatColor.GRAY+"Man"+ChatColor.GREEN+"Hunt");
        player.setPlayerListFooter(ChatUtils.BGAUTHOR.getMessage());
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        Block block = event.getBlock();

        //iron
        ItemStack iron = new ItemStack(Material.IRON_INGOT);
        iron.setAmount(2);
        //deepiron
        ItemStack irondeep = new ItemStack(Material.DEEPSLATE_IRON_ORE);
        irondeep.setAmount(2);
        //deepiron
        ItemStack golddeep = new ItemStack(Material.DEEPSLATE_GOLD_ORE);
        golddeep.setAmount(4);
        //gold
        ItemStack gold = new ItemStack(Material.GOLD_INGOT);
        gold.setAmount(4);

        if(main.getPreyplayer().contains(player.getName())){
            if(block.getType() == Material.IRON_ORE){
                player.getInventory().addItem(iron);
                event.getBlock().setType(Material.AIR);
            }

            if(block.getType() == Material.DEEPSLATE_IRON_ORE){
                player.getInventory().addItem(irondeep);
                event.getBlock().setType(Material.AIR);
            }
            if(block.getType() == Material.GOLD_ORE){
                player.getInventory().addItem(gold);
                event.getBlock().setType(Material.AIR);
            }
            if(block.getType() == Material.DEEPSLATE_GOLD_ORE){
                player.getInventory().addItem(golddeep);
                event.getBlock().setType(Material.AIR);
            }

        }
    }
}
