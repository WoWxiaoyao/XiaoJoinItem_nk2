package zbv5.cn.XiaoJoinItem;

import cn.nukkit.plugin.PluginBase;
import zbv5.cn.XiaoJoinItem.util.PluginUtil;

public class Main extends PluginBase
{
    private static Main instance;

    public static Main getInstance()
    {
        return instance;
    }

    public static int DelayTime = 2;
    public static boolean Clear = false;
    @Override
    public void onEnable()
    {
        instance = this;
        PluginUtil.LoadPlugin();
    }

    @Override
    public void onDisable()
    {
        PluginUtil.DisablePlugin();
    }

}
