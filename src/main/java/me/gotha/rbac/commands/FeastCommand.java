package me.gotha.rbac.commands;

import me.gotha.rbac.utils.MinecraftPosition;
import me.gotha.rbac.utils.Util;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeastCommand implements CommandExecutor {

    public final String commandName = Util.capitalize("feast");

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        World world = player.getWorld();

        MinecraftPosition minecraftPosition = new MinecraftPosition(10.00, 7.00, 6.514);

        double x = minecraftPosition.getX();
        double y = minecraftPosition.getY();
        double z = minecraftPosition.getZ();


        Location location = new Location(world, x, y, z);
        player.teleport(location);

        return false;
    }
}
