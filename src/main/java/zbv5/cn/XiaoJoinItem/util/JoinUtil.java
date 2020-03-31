package zbv5.cn.XiaoJoinItem.util;


import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.player.Player;
import cn.nukkit.utils.ConfigSection;

public class JoinUtil
{

    public static void JoinRun(Player p)
    {
        for(String name:FileUtil.list.keySet())
        {
            ConfigSection c = FileUtil.list.get(name);
            if((p.hasPermission(c.getString("Permission"))) && (!c.getStringList("BanWorlds").contains(p.getLevel().getName())))
            {
                Item i = ItemUtil.getItem(c);
                if(!ItemUtil.hasItem(p,i))
                {
                    int slot = c.getInt("Slot") - 1;
                    PlayerInventory inv = p.getInventory();
                    if( (inv.getItem(slot) == null) || (inv.getItem(slot).getId().getName().equals(c.getString("ID"))))
                    {
                        inv.setItem(slot,i);
                    } else {
                        inv.addItem(i);
                    }
                }
            }
        }
    }

    public static void CheckRun(Player p,String Check)
    {
        for(String name:FileUtil.list.keySet())
        {
            ConfigSection c = FileUtil.list.get(name);
            if((p.hasPermission(c.getString("Permission"))) && (!c.getStringList("BanWorlds").contains(p.getLevel().getName()))&& (c.getBoolean(Check)))
            {
                Item i = ItemUtil.getItem(c);
                if(!ItemUtil.hasItem(p,i))
                {
                    int slot = c.getInt("Slot") - 1;
                    PlayerInventory inv = p.getInventory();
                    if( (inv.getItem(slot) == null) || (inv.getItem(slot).getId().getName().equals(c.getString("ID"))))
                    {
                        inv.setItem(slot,i);
                    } else {
                        inv.addItem(i);
                    }
                }
            }
        }
    }
}
