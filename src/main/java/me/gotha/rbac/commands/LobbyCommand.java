package me.gotha.rbac.commands;

import me.gotha.rbac.utils.MinecraftPosition;
import me.gotha.rbac.minigames.SkywarsMinigame;
import me.gotha.rbac.utils.Util;
import org.apache.commons.lang.StringUtils;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LobbyCommand implements CommandExecutor, Listener {

    private Connection connection;
    private Integer lbyAmountPlayers = 0;
    public final String commandName = Util.capitalize("lobby");
    private ItemMeta skywarsItemMeta;

    public LobbyCommand(Connection connection) {
        this.connection = connection;
    }


    public void setLbyAmountPlayers(Integer lbyAmountPlayers) {
        this.lbyAmountPlayers = lbyAmountPlayers;
    }


    public void setSkywarsItemMeta(ItemMeta skywarsItemMeta) {
        this.skywarsItemMeta = skywarsItemMeta;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            try {
                Statement statement = this.connection.createStatement();


                String selectAllPlayersInLobbyQuery = "SELECT COUNT(*) AS lby_amount_players FROM lby_skywars WHERE active=true";
                ResultSet resultSetSelectAllPlayersInLobbyQuery = statement.executeQuery(selectAllPlayersInLobbyQuery);


                if (!resultSetSelectAllPlayersInLobbyQuery.next()) {
                    Bukkit.broadcastMessage(ChatColor.RED + "Não foi encontrado nenhuma pessoa nesse lobby!");
                }

                setLbyAmountPlayers(Integer.parseInt(resultSetSelectAllPlayersInLobbyQuery.getString("lby_amount_players")));

                Player player = (Player) commandSender;

                Inventory inventory = Bukkit.createInventory(player, 9, ChatColor.AQUA + this.commandName);

                Material diamondSword = Material.DIAMOND_SWORD;

                ItemStack itemStackSkywarsDS = new ItemStack(diamondSword, 1);
                ItemMeta itemStackMetaSkywarsDS = itemStackSkywarsDS.getItemMeta();

                itemStackMetaSkywarsDS.setDisplayName(ChatColor.AQUA + "Skywars");
                itemStackMetaSkywarsDS.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

                List<String> itemDescription = new ArrayList();
                String stringLobbyAmount = String.format("Lobby %s/8", this.lbyAmountPlayers);
                itemDescription.add(stringLobbyAmount);

                itemStackMetaSkywarsDS.setLore(itemDescription);

                setSkywarsItemMeta(itemStackMetaSkywarsDS);

                itemStackSkywarsDS.setItemMeta(itemStackMetaSkywarsDS);

                inventory.addItem(itemStackSkywarsDS);

                player.openInventory(inventory);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) throws SQLException {
        Statement statement = this.connection.createStatement();
        final boolean isInventoryTitleTheSameOfCommandName = event.getView().getTitle().equalsIgnoreCase(ChatColor.AQUA + this.commandName);
        String itemNameClickedOnInventory = event.getCurrentItem().getItemMeta().getDisplayName().toLowerCase();
//        String skywarsItemName = this.getSkywarsItemMeta().getDisplayName().toLowerCase();
        String skywarsItemName = this.skywarsItemMeta.getDisplayName().toLowerCase();

        final boolean isSkywarsItem = itemNameClickedOnInventory.equals(skywarsItemName);

        if (isInventoryTitleTheSameOfCommandName) {
            event.setCancelled(true);
        }


        Player player = (Player) event.getWhoClicked();
        String playerName = player.getName();
        World world = player.getWorld();

        String selectAllPlayersInLobbyQuery = "SELECT COUNT(*) AS lby_amount_players FROM lby_skywars WHERE active = true";
        ResultSet resultSetSelectAllPlayersInLobbyQuery = statement.executeQuery(selectAllPlayersInLobbyQuery);


        if (!resultSetSelectAllPlayersInLobbyQuery.next()) {
            Bukkit.broadcastMessage(ChatColor.RED + "Não foi encontrado nenhuma pessoa nesse lobby!");
            event.setCancelled(true);
        }

        setLbyAmountPlayers(Integer.parseInt(resultSetSelectAllPlayersInLobbyQuery.getString("lby_amount_players")));

        Bukkit.broadcastMessage(resultSetSelectAllPlayersInLobbyQuery.getString("lby_amount_players"));


        String selectUserNameWithTheSamePlayerNameQuery = String.format("SELECT * FROM lby_skywars WHERE user_name = '%s'", playerName);


        String insertPlayerToTableQuery = String.format("INSERT INTO lby_skywars (user_name, active, created_at, updated_at) VALUES ('%s', true, '2021-05-25', '2021-05-25');", playerName);


        ResultSet resultSetSelectUserNameWithTheSamePlayerNameQuery = statement.executeQuery(selectUserNameWithTheSamePlayerNameQuery);


        if (resultSetSelectUserNameWithTheSamePlayerNameQuery.next()) {
            Bukkit.broadcastMessage(ChatColor.RED + "Você já nesse lobby!");
            event.setCancelled(true);
        }


        statement.executeUpdate(insertPlayerToTableQuery);


        if (isInventoryTitleTheSameOfCommandName && isSkywarsItem) {
            Bukkit.broadcastMessage(StringUtils.repeat(" \n", 100));
            Bukkit.broadcastMessage(ChatColor.GREEN + "O jogador " + player.getName() + " entrou no lobby de skywars!");


            ArrayList<MinecraftPosition> minecraftPositions = new ArrayList<MinecraftPosition>();

            minecraftPositions.add(new MinecraftPosition(26.70, 17.20, -9.30));
            minecraftPositions.add(new MinecraftPosition(-24.30, 17.20, 10.70));
            minecraftPositions.add(new MinecraftPosition(-11.30, 17.20, -9.70));
            minecraftPositions.add(new MinecraftPosition(7.30, 17.20, -22.30));
            minecraftPositions.add(new MinecraftPosition(40.30, 17.20, 9.645));
            minecraftPositions.add(new MinecraftPosition(26.30, 17.20, 28.70));
            minecraftPositions.add(new MinecraftPosition(8.70, 17.20, 42.30));
            minecraftPositions.add(new MinecraftPosition(-11.30, 17.20, 28.30));

            Random random = new Random();
            int randomIndex = random.nextInt(8);

            MinecraftPosition selectedPlayerPosition = minecraftPositions.get(randomIndex);

            double x = selectedPlayerPosition.getX();
            double y = selectedPlayerPosition.getY();
            double z = selectedPlayerPosition.getZ();

            Location location = new Location(world, x, y, z);

            player.teleport(location);


            SkywarsMinigame skywarsMinigame = new SkywarsMinigame();
            event.setCancelled(true);
        }


    }
}
