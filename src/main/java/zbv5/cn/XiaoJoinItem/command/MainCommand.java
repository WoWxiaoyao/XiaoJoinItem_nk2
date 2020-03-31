package zbv5.cn.XiaoJoinItem.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginIdentifiableCommand;
import cn.nukkit.player.Player;
import cn.nukkit.plugin.Plugin;
import zbv5.cn.XiaoJoinItem.Main;
import zbv5.cn.XiaoJoinItem.lang.Lang;
import zbv5.cn.XiaoJoinItem.util.JoinUtil;
import zbv5.cn.XiaoJoinItem.util.PluginUtil;
import zbv5.cn.XiaoJoinItem.util.PrintUtil;

public class MainCommand extends Command implements PluginIdentifiableCommand
{

    private final Main plugin;

    public MainCommand(Main plugin)
    {
        super("XiaoJoinItem", "XiaoJoinItem 插件指令.", "/XiaoJoinItem <help|check|reload>", new String[]{"xji"});
        this.setPermission("XiaoJoinItem.help");
        this.plugin = plugin;
    }

    public Plugin getPlugin()
    {
        return this.plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args)
    {
        if (!this.plugin.isEnabled() || !this.testPermission(sender)) {
            return false;
        }
        if ((args.length == 0) ||(args[0].equalsIgnoreCase("help"))||(args[0].equalsIgnoreCase("?")))
        {
            PrintUtil.PrintCommandSender(sender,"&6========= [&bXiaoJoinItem&6] &6=========");
            PrintUtil.PrintCommandSender(sender,"&6/"+label+"&a help&f[?] &7- &b查看命令帮助");
            PrintUtil.PrintCommandSender(sender,"&6/"+label+"&a Check &7- &b检查自身物品");
            PrintUtil.PrintCommandSender(sender,"&6/"+label+"&a Check &e<玩家> &7- &b检查玩家物品");
            PrintUtil.PrintCommandSender(sender,"&6/"+label+"&e reload &7- &c重载插件");
            return true;
        }
        if(args[0].equalsIgnoreCase("Check"))
        {
            if(args.length == 1)
            {
                if(!(sender instanceof Player))
                {
                    PrintUtil.PrintCommandSender(sender, Lang.NeedPlayer);
                    return false;
                }
                if( (sender.hasPermission("XiaoJoinItem.Check")) || (sender.hasPermission("XiaoJoinItem.CheckOther")) || (sender.hasPermission("XiaoJoinItem.admin")))
                {
                    Player p = (Player)sender;
                    JoinUtil.JoinRun(p);
                    PrintUtil.PrintCommandSender(sender,Lang.Executed);
                } else {
                    PrintUtil.PrintCommandSender(sender,Lang.NoPermission);
                }
                return true;
            }
            if(args.length == 2)
            {
                if((sender.hasPermission("XiaoJoinItem.CheckOther")) || (sender.hasPermission("XiaoJoinItem.admin")))
                {
                    String PlayerName = args[1];
                    if((Main.getInstance().getServer().getPlayer(PlayerName) != null) && (Main.getInstance().getServer().getPlayer(PlayerName).isOnline()))
                    {
                        Player p = Main.getInstance().getServer().getPlayer(PlayerName);
                        JoinUtil.JoinRun(p);
                        PrintUtil.PrintCommandSender(sender,Lang.Executed);
                    } else {
                        PrintUtil.PrintCommandSender(sender,Lang.NullPlayer.replace("{player}",PlayerName));
                    }
                } else {
                    PrintUtil.PrintCommandSender(sender,Lang.NoPermission);
                }
                return true;
            }
            PrintUtil.PrintCommandSender(sender,"{prefix}&c正确方法:/"+label+" check <玩家(可选)>");
            return false;
        }
        if(args[0].equalsIgnoreCase("reload"))
        {
            if(!sender.hasPermission("XiaoJoinItem.admin"))
            {
                PrintUtil.PrintCommandSender(sender,Lang.NoPermission);
                return false;
            }
            try
            {
                PluginUtil.reloadLoadPlugin();
                PrintUtil.PrintCommandSender(sender, Lang.SuccessReload);
                return true;
            } catch (Exception e)
            {
                PrintUtil.PrintCommandSender(sender,Lang.FailReload);
                e.printStackTrace();
            }
            return false;
        }
        PrintUtil.PrintCommandSender(sender,Lang.NullCommand);
        return false;
    }
}
