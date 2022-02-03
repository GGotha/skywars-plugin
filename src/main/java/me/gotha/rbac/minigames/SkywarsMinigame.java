package me.gotha.rbac.minigames;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SkywarsMinigame extends Skywars implements Minigame {


    public SkywarsMinigame(InventoryClickEvent event) {
        super();
        this.onLobby(event);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onLobby(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        this.sendInitialMessages(player);
        this.createScoreboard(player);
        this.movePlayerToInitialPosition(player);
    }

}
