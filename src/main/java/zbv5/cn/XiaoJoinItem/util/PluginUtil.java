package zbv5.cn.XiaoJoinItem.util;

import zbv5.cn.XiaoJoinItem.Main;
import zbv5.cn.XiaoJoinItem.command.MainCommand;
import zbv5.cn.XiaoJoinItem.lang.Lang;
import zbv5.cn.XiaoJoinItem.listener.PlayerListener;

public class PluginUtil
{
    public static void LoadPlugin()
    {
        PrintUtil.PrintConsole("&e======== &bXiaoJoinItem &e> &d开始加载 &e========");
        FileUtil.LoadFile();
        Lang.LoadLang();
        Main.getInstance().getServer().getPluginManager().registerEvents(new PlayerListener(), Main.getInstance());
        Main.getInstance().getServer().getCommandMap().register(Main.getInstance(),new MainCommand(Main.getInstance()),"XiaoJoinItem");
        PrintUtil.PrintConsole("&e======== &bXiaoJoinItem &e> &a加载成功 &e========");
    }

    public static void DisablePlugin()
    {
        PrintUtil.PrintConsole("&e======== &bXiaoJoinItem &e> &d开始卸载 &e========");
        PrintUtil.PrintConsole("&e> 感谢您的使用,期待下次运行~");
        PrintUtil.PrintConsole("&e======== &bXiaoJoinItem &e> &c卸载完成 &e========");
    }

    public static void reloadLoadPlugin()
    {
        PrintUtil.PrintConsole("&e======== &bXiaoJoinItem &e> &d开始重载 &e========");
        FileUtil.LoadFile();
        Lang.LoadLang();
        PrintUtil.PrintConsole("&e======== &bXiaoJoinItem &e> &a重载成功 &e========");
    }
}
