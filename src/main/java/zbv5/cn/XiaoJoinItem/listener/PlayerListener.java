package zbv5.cn.XiaoJoinItem.listener;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityLevelChangeEvent;
import cn.nukkit.event.player.PlayerDropItemEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerRespawnEvent;
import cn.nukkit.item.Item;
import cn.nukkit.player.Player;
import cn.nukkit.utils.ConfigSection;
import cn.nukkit.utils.Identifier;
import zbv5.cn.XiaoJoinItem.Main;
import zbv5.cn.XiaoJoinItem.lang.Lang;
import zbv5.cn.XiaoJoinItem.util.FileUtil;
import zbv5.cn.XiaoJoinItem.util.ItemUtil;
import zbv5.cn.XiaoJoinItem.util.JoinUtil;
import zbv5.cn.XiaoJoinItem.util.PrintUtil;

import java.util.HashMap;

public class PlayerListener implements Listener
{
    public static HashMap<String, Long> CD = new HashMap<String, Long>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        final Player p = e.getPlayer();
        Main.getInstance().getServer().getScheduler().scheduleDelayedTask(Main.getInstance(), new Runnable()
        {
            public void run()
            {
                if(p.isOnline())
                {
                    if(Main.Clear)
                    {
                        Item i = Item.get(Identifier.fromString("AIR"));
                        p.getInventory().clearAll();
                        p.getInventory().setHelmet(i);
                        p.getInventory().setBoots(i);
                        p.getInventory().setChestplate(i);
                        p.getInventory().setLeggings(i);
                    }
                    JoinUtil.JoinRun(p);
                }
            }
        }, Main.DelayTime * 20);
    }
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e)
    {

        if(e.isCancelled()) return;
        Player p = e.getPlayer();
        Item i = e.getItem();
        PrintUtil.PrintCommandSender(p,i.getId().getName());
        if((i != null)  &&(i.hasCustomName()))
        {
            String CustomName = i.getCustomName();
            for(String name: FileUtil.names.keySet())
            {
                if(CustomName.equals(PrintUtil.cc(FileUtil.names.get(name))))
                {
                    ConfigSection c = FileUtil.list.get(name);
                    if(!c.getBoolean("AllowDrop"))
                    {
                        e.setCancelled(true);
                        PrintUtil.PrintCommandSender(p,Lang.NoDrop);
                    }
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e)
    {
      //  if(e.isCancelled()) return;
        final Player p = e.getPlayer();
        Main.getInstance().getServer().getScheduler().scheduleDelayedTask(Main.getInstance(), new Runnable()
        {
            public void run()
            {
                if(p.isOnline())
                {
                    JoinUtil.CheckRun(p,"Respawn");
                }
            }
        }, 1);
    }

    @EventHandler
    public void onPlayerChangeWorld(EntityLevelChangeEvent e)
    {
        if(e.isCancelled()) return;
        if(e.getEntity() instanceof  Player)
        {
            final Player p = ((Player) e.getEntity()).getPlayer();
            Main.getInstance().getServer().getScheduler().scheduleDelayedTask(Main.getInstance(), new Runnable()
            {
                public void run()
                {
                    if(p.isOnline())
                    {
                        JoinUtil.CheckRun(p,"ChangeWorld");
                    }
                }
            }, 1);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();
        if((e.getAction() == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) || (e.getAction() == PlayerInteractEvent.Action.RIGHT_CLICK_AIR))
        {
            Item i = e.getItem();
            if((i != null) &&(i.hasCustomName()))
            {
                String CustomName = i.getCustomName();
                for(String name: FileUtil.names.keySet())
                {
                    if(CustomName.equals(PrintUtil.cc(FileUtil.names.get(name))))
                    {
                        e.setCancelled(true);
                        ConfigSection c = FileUtil.list.get(name);
                        int cd = c.getInt("CD");
                        if(cd > 0)
                        {
                            Long l = System.currentTimeMillis();
                            if(CD.containsKey(p.getName()) && l - (Long) CD.get(p.getName()) < (long)(cd * 1000))
                            {
                                PrintUtil.PrintCommandSender(p, Lang.CD.replace("{cd}",String.valueOf(((long)cd - (l - (Long) CD.get(p.getName())) / 1000L))));
                                return;
                            }
                            CD.put(p.getName(), l);
                        }
                        if(!p.hasPermission(c.getString("Permission")))
                        {
                            PrintUtil.PrintCommandSender(p,Lang.NoPermission);
                            return;
                        }
                        if(c.getStringList("BanWorlds").contains(p.getLevel().getName()))
                        {
                            PrintUtil.PrintCommandSender(p,Lang.BanWorld);
                            return;
                        }
                        PrintUtil.Run(p,c.getStringList("Run"));
                        break;
                    }
                }
            }
        }
    }

}
