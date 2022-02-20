package me.gotha.rbac.commands;

import me.gotha.rbac.minigames.Levels;
import me.gotha.rbac.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class CreateCommand implements CommandExecutor {

    public final String commandName = Util.capitalize("create");

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Bukkit.broadcastMessage(Levels.selectRandomlyALevel());
//        WorldCreator wc = new WorldCreator("test");



//        wc.environment(World.Environment.NORMAL);
//        wc.type(WorldType.NORMAL);
//
//        wc.createWorld();

        return false;
    }
}
