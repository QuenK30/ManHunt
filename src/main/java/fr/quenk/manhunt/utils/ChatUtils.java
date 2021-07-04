package fr.quenk.manhunt.utils;

import org.bukkit.ChatColor;

/*
 *QuenK_ 03/07/2021 | 23:42 |ManHunt
 */
public enum ChatUtils {
    PREFIX(ChatColor.WHITE+"﴾"+ ChatColor.GRAY+" Man"+ChatColor.DARK_GREEN+"Hunt "+ ChatColor.WHITE+"﴿ ");

    private final String message;

    ChatUtils(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
