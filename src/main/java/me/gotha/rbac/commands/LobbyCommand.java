package me.gotha.rbac.commands;

import me.gotha.rbac.minigames.LobbyParameters;
import me.gotha.rbac.minigames.Levels;
import me.gotha.rbac.minigames.SkywarsMinigame;
import me.gotha.rbac.repositories.PlayerRepository;
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
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LobbyCommand implements CommandExecutor, Listener {

    private Session session;
    private Integer playersOnLobby = 0;
    public final String commandName = Util.capitalize("lobby");
    private ItemMeta skywarsItemMeta;

    public LobbyCommand(Session session) {
        this.session = session;
    }


    public void setPlayersOnLobby(Integer playersOnLobby) {
        this.playersOnLobby = playersOnLobby;
    }


    public void setSkywarsItemMeta(ItemMeta skywarsItemMeta) {
        this.skywarsItemMeta = skywarsItemMeta;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }

            System.out.println("teste");

//            String getPlayersOnLobby = String.format(
//                    "SELECT COUNT(l) AS players_on_lobby " +
//                            "FROM lobbies l " +
//                            "INNER JOIN lobby_players lp ON l.id = lp.id_lobby " +
//                            "WHERE l.id = %s AND lp.active=true and l.active = true;", 1
//            );

//            String hql = "SELECT COUNT(l) AS players_on_lobby " +
//                    "FROM Lobby l " +
//                    "INNER JOIN LobbyPlayer lp ON Lobby.id = LobbyPlayer.id " +
//                    "WHERE l.id = :id_lobby AND lp.active=true and l.active = true";

        PlayerRepository playerRepository = new PlayerRepository();

        List<me.gotha.rbac.entities.Player> players = playerRepository.findAll();


        System.out.println(players);
//                Query getPlayersOnLobby = this.session.createNamedQuery("getPlayersOnLobby");

//                ResultSet getPlayersOnLobbyResultSet = this.session.addNamedQuery("getPlayersOnLobby", lista).getResultSet();

//                if (!getPlayersOnLobbyResultSet.next()) {
//                    Bukkit.broadcastMessage(ChatColor.RED + "Não foi encontrado nenhuma pessoa nesse lobby!");
//                }
//
//                setPlayersOnLobby(getPlayersOnLobbyResultSet.getInt("players_on_lobby"));
//
//
//                Player player = (Player) commandSender;
//
//                Inventory inventory = Bukkit.createInventory(player, 9, ChatColor.AQUA + this.commandName);
//
//                Material diamondSword = Material.DIAMOND_SWORD;
//
//                ItemStack itemStackSkywarsDS = new ItemStack(diamondSword, 1);
//                ItemMeta itemStackMetaSkywarsDS = itemStackSkywarsDS.getItemMeta();
//
//                itemStackMetaSkywarsDS.setDisplayName(ChatColor.AQUA + "Skywars");
//                itemStackMetaSkywarsDS.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//
//                List<String> itemDescription = new ArrayList();
//                String stringLobbyAmount = String.format("Lobby %s/8", this.playersOnLobby);
//                itemDescription.add(stringLobbyAmount);
//
//                itemStackMetaSkywarsDS.setLore(itemDescription);
//
//                setSkywarsItemMeta(itemStackMetaSkywarsDS);
//
//                itemStackSkywarsDS.setItemMeta(itemStackMetaSkywarsDS);
//
//                inventory.addItem(itemStackSkywarsDS);

//                player.openInventory(inventory);

            return true;


    }

//    @EventHandler
//    public void onPlayerDisconnect(PlayerQuitEvent event) throws SQLException {
//        Player player = event.getPlayer();
//        String playerName = player.getName();
//
//        String getPlayer = String.format("SELECT * FROM players WHERE name = '%s';", playerName);
//        ResultSet getPlayerResultSet = this.statement.executeQuery(getPlayer);
//
//        int idPlayer;
//
//        if (getPlayerResultSet.next()) {
//            idPlayer = getPlayerResultSet.getInt("id");
//
//            String updateActivePlayer = String.format("UPDATE lobby_players SET active = false WHERE id_player = %s and active = true;", idPlayer);
//            this.statement.executeUpdate(updateActivePlayer);
//        }
//    }

//    @EventHandler
//    public void onPlayerDeath(PlayerDeathEvent event) throws SQLException {
//        Player player = event.getEntity().getPlayer();
//        String playerName = player.getName();
//
//        String getPlayer = String.format("SELECT * FROM players WHERE name = '%s';", playerName);
//        ResultSet getPlayerResultSet = this.statement.executeQuery(getPlayer);
//
//        int idPlayer;
//
//        if (getPlayerResultSet.next()) {
//            idPlayer = getPlayerResultSet.getInt("id");
//
//            String updateActivePlayer = String.format("UPDATE lobby_players SET active = false WHERE id_player = %s and active = true;", idPlayer);
//            this.statement.executeUpdate(updateActivePlayer);
//
//            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
//        }
//    }

//    @EventHandler
//    public void onInventoryClick(InventoryClickEvent event) throws SQLException {
//        final boolean isInventoryTitleTheSameOfCommandName = event.getView().getTitle().equalsIgnoreCase(ChatColor.AQUA + this.commandName);
//        String itemNameClickedOnInventory = event.getCurrentItem().getItemMeta().getDisplayName().toLowerCase();
//        String skywarsItemName = this.skywarsItemMeta.getDisplayName().toLowerCase();
//
//        final boolean isSkywarsItem = itemNameClickedOnInventory.equals(skywarsItemName);
//
//        if (isInventoryTitleTheSameOfCommandName) {
//            event.setCancelled(true);
//        }
//
//        Player player = (Player) event.getWhoClicked();
//        String playerName = player.getName();
//
//        boolean isValidLobby = false;
//        boolean playerExists = false;
//        boolean playerIsOnLobby = false;
//        int id_player = 0;
//        int id_lobby = 0;
//
//
//        String getActiveLobbies = "SELECT id FROM lobbies WHERE active=true LIMIT 1;";
//        ResultSet getActiveLobbiesResultSet = this.statement.executeQuery(getActiveLobbies);
//
//
//        if (getActiveLobbiesResultSet.next()) {
//            id_lobby = getActiveLobbiesResultSet.getInt("id");
//
//
//            String getPlayersOnLobby = String.format(
//                    "SELECT COUNT(*) AS players_on_lobby FROM lobbies l INNER JOIN lobby_players lp ON l.id = lp.id_lobby WHERE l.id = %s and lp.active = true and l.active = true;", id_lobby);
//            ResultSet getPlayersOnLobbyResultSet = this.statement.executeQuery(getPlayersOnLobby);
//
//            if (getPlayersOnLobbyResultSet.next()) {
//                int players_on_lobby = getPlayersOnLobbyResultSet.getInt("players_on_lobby");
//
//                if (players_on_lobby < 8) {
//                    isValidLobby = true;
//                }
//            }
//        }
//
//        String lobbyLevel = Levels.selectRandomlyALevel();
//
//        Bukkit.broadcastMessage("CHEGA AQUI::" + lobbyLevel);
//
//
//        if (!isValidLobby) {
//            String insertLobbyQuery = String.format(Queries.createLobby, lobbyLevel);
//            this.statement.executeUpdate(insertLobbyQuery);
//
//            ResultSet result = this.statement.getGeneratedKeys();
//
//            if (result.next()) {
//                Object object = result.getObject(1);
//
//                id_lobby = (int) object;
//            }
//        }
//
//
//        String getPlayerWithTheSameName = String.format("SELECT * FROM players WHERE name = '%s';", playerName);
//        ResultSet getPlayerWithTheSameNameResultSet = this.statement.executeQuery(getPlayerWithTheSameName);
//
//        if (getPlayerWithTheSameNameResultSet.next()) {
//            id_player = getPlayerWithTheSameNameResultSet.getInt("id");
//            playerExists = true;
//        }
//
//
//        if (!playerExists) {
//            String createPlayer = String.format(Queries.createPlayer, playerName);
//            this.statement.executeUpdate(createPlayer);
//
//            ResultSet result = this.statement.getGeneratedKeys();
//
//            if (result.next()) {
//                Object object = result.getObject(1);
//
//                id_player = (int) object;
//            }
//
//        }
//
//
//        String getPlayerOnLobby = String.format("SELECT * FROM lobby_players WHERE id_player = %s and id_lobby = %d and active = true;", id_player, id_lobby);
//        ResultSet getPlayerOnLobbyResultSet = this.statement.executeQuery(getPlayerOnLobby);
//
//
//        if (getPlayerOnLobbyResultSet.next()) {
//            playerIsOnLobby = true;
//        }
//
//        if (!playerIsOnLobby && isInventoryTitleTheSameOfCommandName && isSkywarsItem) {
//            String insertPlayerOnLobby = String.format(
//                    "INSERT INTO lobby_players (id_lobby, id_player, active, created_at, updated_at) VALUES (%s, %d, true, '2021-05-25', '2021-05-25');", id_lobby, id_player);
//
//            this.statement.executeUpdate(insertPlayerOnLobby);
//
////            new LobbyParameters(id_lobby, id_player);
//            new SkywarsMinigame(new LobbyParameters(event, statement, id_lobby, id_player));
//        } else {
//            Bukkit.broadcastMessage(ChatColor.RED + "Você já está nesse lobby!");
//        }
//
//
//        event.setCancelled(true);
//
//
//    }
}
