package me.gotha.rbac.commands;

import me.gotha.rbac.database.Queries;
import me.gotha.rbac.minigames.SkywarsMinigame;
import me.gotha.rbac.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LobbyCommand implements CommandExecutor, Listener {

    private Statement statement;
    private Integer lbyAmountPlayers = 0;
    public final String commandName = Util.capitalize("lobby");
    private ItemMeta skywarsItemMeta;

    public LobbyCommand(Statement statement) {
        this.statement = statement;
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

                String selectAllPlayersInLobbyQuery = Queries.getAmountPlayersOnSkywarsLobby;
                ResultSet resultSetSelectAllPlayersInLobbyQuery = this.statement.executeQuery(selectAllPlayersInLobbyQuery);


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
    public void onPlayerDisconnect(PlayerQuitEvent event) throws SQLException {
        Player player = event.getPlayer();
        String playerName = player.getName();


        String updatePlayerActiveFalseQuery = String.format(Queries.setPlayerActiveToFalse, playerName);
        this.statement.executeUpdate(updatePlayerActiveFalseQuery);


    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) throws SQLException {
        final boolean isInventoryTitleTheSameOfCommandName = event.getView().getTitle().equalsIgnoreCase(ChatColor.AQUA + this.commandName);
        String itemNameClickedOnInventory = event.getCurrentItem().getItemMeta().getDisplayName().toLowerCase();
        String skywarsItemName = this.skywarsItemMeta.getDisplayName().toLowerCase();

        final boolean isSkywarsItem = itemNameClickedOnInventory.equals(skywarsItemName);

        if (isInventoryTitleTheSameOfCommandName) {
            event.setCancelled(true);
        }


        Player player = (Player) event.getWhoClicked();
        String playerName = player.getName();

        String selectAllPlayersInLobbyQuery = Queries.getAmountPlayersOnSkywarsLobby;
        ResultSet resultSetSelectAllPlayersInLobbyQuery = this.statement.executeQuery(selectAllPlayersInLobbyQuery);


        if (!resultSetSelectAllPlayersInLobbyQuery.next()) {
            Bukkit.broadcastMessage(ChatColor.RED + "Não foi encontrado nenhuma pessoa nesse lobby!");
            event.setCancelled(true);
        }

        setLbyAmountPlayers(Integer.parseInt(resultSetSelectAllPlayersInLobbyQuery.getString("lby_amount_players")));


        String selectUserNameWithTheSamePlayerNameAndMustBeActiveQuery = String.format(Queries.getPlayerWithTheSameNameAndActiveTrue, playerName);
        ResultSet resultSetSelectUserNameWithTheSamePlayerNameAndMustBeActiveQuery = this.statement.executeQuery(selectUserNameWithTheSamePlayerNameAndMustBeActiveQuery);


        if (resultSetSelectUserNameWithTheSamePlayerNameAndMustBeActiveQuery.next()) {
            Bukkit.broadcastMessage(ChatColor.RED + "Você já está nesse lobby!");
            event.setCancelled(true);
        } else {
            String selectUserNameWithTheSamePlayerNameQuery = String.format(Queries.getPlayerWithTheSameNameAndActiveFalse, playerName);
            ResultSet resultSetSelectUserNameWithTheSamePlayerNameQuery = this.statement.executeQuery(selectUserNameWithTheSamePlayerNameQuery);

            if (resultSetSelectUserNameWithTheSamePlayerNameQuery.next()) {
                String setActiveTruePlayerQuery = String.format(Queries.updatePlayerWithActiveTrue, playerName);
                this.statement.executeUpdate(setActiveTruePlayerQuery);
                event.setCancelled(true);
            } else {
                String insertPlayerToTableQuery = String.format(Queries.createPlayer, playerName);
                this.statement.executeUpdate(insertPlayerToTableQuery);
            }


            if (isInventoryTitleTheSameOfCommandName && isSkywarsItem) {
                new SkywarsMinigame(event);


                event.setCancelled(true);
            }
        }


    }
}
