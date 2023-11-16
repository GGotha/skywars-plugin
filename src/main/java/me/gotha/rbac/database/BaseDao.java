package me.gotha.rbac.database;

import me.gotha.rbac.Rbac;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public abstract class BaseDao<T> {

    //to tell the class actually which type of class you use.
    protected abstract Class<T> getClassType();

    //to create data in your database
    public void create(T data) {
        Rbac main = new Rbac();
        SessionFactory sessionFactory = main.getSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            session.clear();
            session.beginTransaction();
            session.persist(data);
            session.getTransaction().commit();
        } catch (final Exception e) {
            //makes sure no invalid data get saved.
            if (session.getTransaction() != null && session.getTransaction().isActive())
                session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //to update data in your database (be carefully with that, there are different ways using the CriteriaBuilder)
    public void update(T data) {
        Rbac main = new Rbac();
        SessionFactory sessionFactory = main.getSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            session.clear();
            session.beginTransaction();
            session.merge(data);
            session.flush();
            session.getTransaction().commit();
        } catch (final Exception e) {
            if (session.getTransaction() != null && session.getTransaction().isActive())
                session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(T data) {
        Rbac main = new Rbac();
        SessionFactory sessionFactory = main.getSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            session.clear();
            session.beginTransaction();
            session.remove(data);
            session.getTransaction().commit();
        } catch (final Exception e) {
            if (session.getTransaction() != null && session.getTransaction().isActive())
                session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Query<T> createNamedQuery(final String queryName) {
        System.out.println("Pegando o session factory");
        SessionFactory sessionFactory = Rbac.getSessionFactory();
        System.out.println("Terminou o sf");
        Session session = sessionFactory.openSession();
        System.out.println("Abriu a  sessão");
        return session.createNamedQuery(queryName, this.getClassType());
    }
}