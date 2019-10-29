package top.minicraft.atc;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitTask;
import top.minicraft.atc.AntiJoin;
import top.minicraft.atc.Anticheat;

import java.util.*;
import java.util.List;

public class JoinEvent implements Listener {
    public static List<Player> getUnCheckedPlayers() {
        return UnCheckedPlayers;
    }

    public static Map<Player, String> getPlayerCheckers() {
        return PlayerCheckers;
    }

    public static Map<Player, Integer> getMessageSender() {
        return MessageSender;
    }

    private static List<Player> UnCheckedPlayers = new ArrayList<Player>();
    private static Map<Player,String> PlayerCheckers = new HashMap<Player, String>();
    private static Map<Player,Integer> MessageSender = new HashMap<Player,Integer>();

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e){
        String s = getRandomString(20);
        Player p = e.getPlayer();
        UnCheckedPlayers.add(p);
        PlayerCheckers.put(p,s);
        AntiJoin AJ = new AntiJoin(p);
        BukkitTask task = AJ.runTaskTimer(Anticheat.getInstance(),0,20);
        MessageSender.put(p,task.getTaskId());
    }
    private static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerChatEvent(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        if(UnCheckedPlayers.contains(p)){
            e.setCancelled(true);
            String sended = e.getMessage();
            if(sended.equals(PlayerCheckers.get(p))){
                p.sendMessage("QWQ");
                Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(),"您已被反作弊永久封号",null,null);
            }else if(sended.equals(".say "+PlayerCheckers.get(p))){
                p.sendMessage(ChatColor.GREEN+"[反作弊]您已通过验证");
            }else {
                p.sendMessage("未通过反作弊验证，GUN");
                p.kickPlayer("??????");
            }
            Bukkit.getScheduler().cancelTask(MessageSender.get(p));
            MessageSender.remove(p);
            UnCheckedPlayers.remove(p);
            PlayerCheckers.remove(p);
        }
    }
    @EventHandler
    public void AntiMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(UnCheckedPlayers.contains(p)){
            e.setCancelled(true);
        }
    }
}
