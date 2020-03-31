package zbv5.cn.XiaoJoinItem.lang;

import zbv5.cn.XiaoJoinItem.util.FileUtil;
import zbv5.cn.XiaoJoinItem.util.PrintUtil;

public class Lang
{
    public static String Prefix = "&6[&bXiaoJoinItem&6]";
    public static String CD = "{prefix}&c请在{cd}秒后再尝试打开.";
    public static String BanWorld = "{prefix}&c该物品禁止在此世界使用.";
    public static String NoPermission = "{prefix}&c你没有权限这样做";
    public static String SuccessReload = "{prefix}&a重载完成!";
    public static String FailReload = "{prefix}&c重载失败!请前往控制台查看报错.";
    public static String NullCommand = "{prefix}&c未知指令.";
    public static String NullPlayer = "{prefix}&c玩家{player}不在线或不存在.";
    public static String Executed= "{prefix}&a已执行.";
    public static String NeedPlayer = "{prefix}&c只有玩家才能执行该操作.";
    public static String NoDrop = "{prefix}&c你不能丢弃这个物品";
    public static void LoadLang()
    {
        try
        {
            Prefix = FileUtil.lang.getString("Prefix");
            CD = FileUtil.lang.getString("CD");
            BanWorld = FileUtil.lang.getString("BanWorld");
            NoPermission = FileUtil.lang.getString("NoPermission");
            SuccessReload = FileUtil.lang.getString("SuccessReload");
            FailReload = FileUtil.lang.getString("FailReload");
            NullCommand = FileUtil.lang.getString("NullCommand");
            NullPlayer = FileUtil.lang.getString("NullPlayer");
            Executed = FileUtil.lang.getString("Executed");
            NeedPlayer = FileUtil.lang.getString(" NeedPlayer");
            NoDrop = FileUtil.lang.getString("NoDrop");

            PrintUtil.PrintConsole("&a&l√ &a语言文件加载完成.");
        }
        catch (Exception e)
        {
            PrintUtil.PrintConsole("&c&l× &4读取语言文件出现问题,请检查服务器.");
            e.printStackTrace();
        }
    }
}
