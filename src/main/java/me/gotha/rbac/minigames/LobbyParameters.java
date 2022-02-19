package me.gotha.rbac.minigames;

public class LobbyParameters {
    public int idLobby;
    public int idPlayer;


    public int getIdLobby() {
        return idLobby;
    }


    public int getIdPlayer() {
        return idPlayer;
    }


    public LobbyParameters(int idLobby, int idPlayer) {
        this.idLobby = idLobby;
        this.idPlayer = idPlayer;

    }


}
