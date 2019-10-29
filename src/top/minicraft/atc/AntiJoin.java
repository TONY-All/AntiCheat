package top.minicraft.atc;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class AntiJoin extends BukkitRunnable {
    Player player;
    int times = 0;
    public AntiJoin(Player p) {
        player = p;
    }

    @Override
    public void run() {
        times++;
        if(times ==10){
            JoinEvent.getPlayerCheckers().remove(player);
            JoinEvent.getMessageSender().remove(player);
            JoinEvent.getUnCheckedPlayers().remove(player);
            player.kickPlayer(org.bukkit.ChatColor.RED+"????你好慢啊");
            Bukkit.getScheduler().cancelTask(this.getTaskId());
        }
        TextComponent tc = new TextComponent("[反作弊]点我进入游戏");
        TextComponent tch = new TextComponent("点击通过反作弊");
        tch.setColor(net.md_5.bungee.api.ChatColor.GOLD);
        tc.setColor(ChatColor.GREEN);
        HoverEvent he = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{tch});
        String s = (String) JoinEvent.getPlayerCheckers().get(player);
        ClickEvent ce = new ClickEvent(ClickEvent.Action.RUN_COMMAND,".say "+s);
        PotionEffect pe = new PotionEffect(PotionEffectType.BLINDNESS,60,100);
        player.addPotionEffect(pe);
        tc.setClickEvent(ce);
        tc.setHoverEvent(he);
        player.spigot().sendMessage(tc);
    }
}
