package me.gotha.rbac.minigames;

import org.bukkit.Bukkit;

public class SkywarsMinigame implements Minigame {


    public SkywarsMinigame() {
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
        Bukkit.broadcastMessage("Esperando jogadores para começar 1/8");
    }
}
