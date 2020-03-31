package zbv5.cn.XiaoJoinItem.util;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import zbv5.cn.XiaoJoinItem.Main;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

public class FileUtil
{
    public static Config lang;
    public static Config config;
    public static Config items;

    public static HashMap<String, ConfigSection> list = new HashMap<String, ConfigSection>();
    public static HashMap<String, String> names = new HashMap<String, String>();

    public static void LoadFile()
    {
        try
        {
            File Config_Yml = new File(Main.getInstance().getDataFolder(), "config.yml");
            if (!Config_Yml.exists())
            {
                Main.getInstance().saveResource("config.yml", false);
            }
            config = new Config(new File(Main.getInstance().getDataFolder() + "/config.yml"));

            Main.DelayTime = config.getInt("DelayTime");
            Main.Clear = config.getBoolean("Clear");

            File Lang_Yml = new File(Main.getInstance().getDataFolder(), "lang.yml");
            if (!Lang_Yml.exists())
            {
                Main.getInstance().saveResource("lang.yml", false);
            }
            lang = new Config(new File(Main.getInstance().getDataFolder() + "/lang.yml"));

            File Items_Yml = new File(Main.getInstance().getDataFolder(), "items.yml");
            if (!Items_Yml.exists())
            {
                Main.getInstance().saveResource("items.yml", false);
            }
            items = new Config(new File(Main.getInstance().getDataFolder() + "/items.yml"));

            if(!list.isEmpty())
            {
                list.clear();
                names.clear();
            }

            Set<String> keys = FileUtil.items.getKeys(false);
            for(String name:keys)
            {
                ConfigSection c = items.getSection(name);
                if((c != null) && (c.getBoolean("Enable")))
                {
                    list.put(name,c);
                    names.put(name,c.getString("CustomName"));
                    PrintUtil.PrintConsole("&e> &a加载 &6"+name);
                }
            }
            PrintUtil.PrintConsole("&e> &b共加载 &a"+list.size() +"种 &6物品");
            PrintUtil.PrintConsole("&a&l√ &a配置文件加载完成.");
        }
        catch (Exception e)
        {
            PrintUtil.PrintConsole("&c&l× &4加载配置文件出现问题,请检查服务器.");
            e.printStackTrace();
        }
    }
}
