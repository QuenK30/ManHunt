package fr.quenk.manhunt;

import fr.quenk.manhunt.event.EventManager;
import fr.quenk.manhunt.tasks.MHStart;
import fr.quenk.manhunt.utils.MHState;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class MHMain extends JavaPlugin {
    private List<String> specplayer = new ArrayList<>();
    private List<String> huntplayer = new ArrayList<>();
    private List<String> preyplayer = new ArrayList<>();
    private MHState state;
    @Override
    public void onEnable() {
        new EventManager(this).registerEvents();
        setState(MHState.WAITING);
        super.onEnable();
    }

    @Override
    public void onDisable() {
       super.onDisable();
    }

    public List<String> getHuntplayer() {
        return huntplayer;
    }

    public List<String> getPreyplayer() {
        return preyplayer;
    }

    public List<String> getSpecplayer() {
        return specplayer;
    }

    public void setState(MHState state) {
        this.state = state;
    }

    public boolean isState(MHState state) {
        return this.state == state;
    }
}
