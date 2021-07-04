package fr.quenk.manhunt;

import fr.quenk.manhunt.event.EventManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MHMain extends JavaPlugin {
    private MHMain main;
    @Override
    public void onEnable() {
        new EventManager(getMain()).registerEvents();
        super.onEnable();
    }

    @Override
    public void onDisable() {
       super.onDisable();
    }

    public MHMain getMain() {
        return main;
    }
}
