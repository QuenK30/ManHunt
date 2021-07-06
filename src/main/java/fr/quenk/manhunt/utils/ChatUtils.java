package fr.quenk.manhunt.utils;

import org.bukkit.ChatColor;

/*
 *QuenK_ 03/07/2021 | 23:42 |ManHunt
 */
public enum ChatUtils {
    PREFIX(ChatColor.WHITE+"﴾"+ ChatColor.GRAY+" Man"+ChatColor.DARK_GREEN+"Hunt "+ ChatColor.WHITE+"﴿ "),
    AUTHOR("QuenK"),
    BGAUTHOR(ChatColor.WHITE+"﴾"+ ChatColor.GRAY+" By QuenK "+ChatColor.WHITE+"﴿ "),
    PLUS(ChatColor.GRAY+" ["+ChatColor.GREEN+"+"+ChatColor.GRAY+"] "),
    MOIN(ChatColor.GRAY+" ["+ChatColor.GREEN+"-"+ChatColor.GRAY+"] "),
    RECONNECTED(ChatColor.GRAY+" ["+ChatColor.GREEN+"RECONNECTED"+ChatColor.GRAY+"] ");

    private final String message;

    ChatUtils(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
