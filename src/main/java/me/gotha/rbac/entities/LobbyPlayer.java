package me.gotha.rbac.entities;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "lobby_players")
public class LobbyPlayer {
    @Id
    private int id;

    @ManyToOne(targetEntity = Lobby.class)
    private int id_lobby;

    @ManyToOne(targetEntity = Player.class)
    private int id_player;

    private boolean active;

    private Date createdAt;

    private Date updatedAt;
}
