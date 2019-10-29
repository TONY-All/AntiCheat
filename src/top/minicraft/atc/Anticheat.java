package top.minicraft.atc;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Anticheat extends JavaPlugin {
    @Getter
    private static Anticheat instance;
    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new JoinEvent(),this);
        Bukkit.getPluginCommand("anticheatClient").setExecutor(new CommandExcuter());
        Bukkit.getPluginCommand("anticheatClient").setTabCompleter(new TabExcuter());
    }
}