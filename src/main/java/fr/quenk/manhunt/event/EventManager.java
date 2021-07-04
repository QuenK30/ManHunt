package fr.quenk.manhunt.event;

import fr.quenk.manhunt.MHMain;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

/*
 *QuenK_ 03/07/2021 | 23:40 |ManHunt
 */
public class EventManager {
    private MHMain main;
    public EventManager(MHMain main) {
        this.main = main;
    }

    public void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new MHPListener(main), main);
    }
}
