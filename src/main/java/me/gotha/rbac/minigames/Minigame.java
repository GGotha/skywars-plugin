package me.gotha.rbac.minigames;

import org.bukkit.event.inventory.InventoryClickEvent;

public interface Minigame {

    public void onStart();

    public void onFinish();

    public void onLobby(InventoryClickEvent event);

}
