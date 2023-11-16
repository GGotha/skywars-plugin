package me.gotha.rbac.entities;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "lobbies")
public class Lobby {
    @Id
    private int id;

    private String name;

    private boolean active;

    private Date createdAt;

    private Date updatedAt;
}
