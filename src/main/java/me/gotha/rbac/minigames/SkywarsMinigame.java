package me.gotha.rbac.minigames;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.sql.SQLException;
import java.sql.Statement;

public class SkywarsMinigame extends Skywars implements Minigame {

    public int idLobby;
    public int idPlayer;
    public Statement statement;
    public InventoryClickEvent event;

    public SkywarsMinigame(LobbyParameters lobbyParameters) {
        super();
        Bukkit.broadcastMessage("INSTANCIADO");
        this.idLobby = lobbyParameters.idLobby;
        this.idPlayer = lobbyParameters.idPlayer;
        this.statement = lobbyParameters.statement;
        this.event = lobbyParameters.event;
        this.onLobby();

    }


    @Override
    public void onStart() {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onLobby() {
        Player player = (Player) this.event.getWhoClicked();

        try {
            this.sendInitialMessages(player);
            this.createScoreboard(player);

            this.movePlayerToMap(this.event, this.statement, this.idLobby, this.idPlayer);

            this.movePlayerToInitialPosition(player);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
