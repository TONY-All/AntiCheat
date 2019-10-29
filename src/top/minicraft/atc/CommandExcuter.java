package top.minicraft.atc;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandExcuter implements CommandExecutor {
    private Anticheat instance = Anticheat.getInstance();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equals("atc")) {
            if(args.length==0){
                sender.sendMessage(ChatColor.GOLD+"[AntiHackClient]This server is using antihackclient");
                sender.sendMessage(ChatColor.GOLD+"[AntiHackClient]version:0.1.0");
                sender.sendMessage(ChatColor.GOLD+"[AntiHackClient]patch:TONY_All");
            }
            if(args.length==1){
                if(args[0].equals("reload")){
                    sender.sendMessage("[AntiHackClient]插件已重载");
                    instance.reloadConfig();
                }
            }
            if (sender instanceof Player) {
                Player p = (Player) sender;

            }
            return true;
        }
        return false;
    }
}
