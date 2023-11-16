package me.gotha.rbac.repositories;

import me.gotha.rbac.database.BaseDao;
import me.gotha.rbac.entities.Player;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.List;

public class PlayerRepository extends BaseDao<Player> {
    @Override
    protected Class<Player> getClassType() { return Player.class; }

    public List<Player> findAll() {
        System.out.println("PlayerRepository.findAll()");
        Query<Player> query = this.createNamedQuery("Player.findAll");

        System.out.println("DEPOIS DA QUERY");
        try {
            return query.getResultList();
        } catch (final NoResultException exc) {
            System.out.println("ERRO");
            return Collections.emptyList();
        }
    }
}
