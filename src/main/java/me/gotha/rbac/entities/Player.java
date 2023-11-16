package me.gotha.rbac.entities;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "players")
@NamedQuery(
        name = "Player.findAll",
        query = "SELECT P FROM Player P"
)
public class Player {
    @Id
    private int id;

    private String name;

    private boolean active;

    private Date createdAt;

    private Date updatedAt;
}
