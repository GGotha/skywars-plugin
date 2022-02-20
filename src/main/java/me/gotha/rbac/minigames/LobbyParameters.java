package me.gotha.rbac.minigames;

import org.bukkit.event.inventory.InventoryClickEvent;

import java.sql.Statement;

public class LobbyParameters {
    public int idLobby;
    public int idPlayer;
    public Statement statement;
    public InventoryClickEvent event;

    public LobbyParameters(InventoryClickEvent event, Statement statement, int id_lobby, int id_player) {
        this.event = event;
        this.statement = statement;
        this.idLobby = id_lobby;
        this.idPlayer = id_player;
    }


}
