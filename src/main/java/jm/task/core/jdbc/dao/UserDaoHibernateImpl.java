package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final SessionFactory GET_SESSION_FACT = Util.getSessionFactory();
    private Transaction transaction;
    private static final String CREATE_TBL = "CREATE TABLE IF NOT EXISTS Users " +
            "(id BIGSERIAL PRIMARY KEY, name TEXT, lastName TEXT, age INTEGER)";

    private static final String DROP_US_TBL = "DROP TABLE IF EXISTS Users";
    private static final String GET_ALL_US = "FROM User Users";
    private static final String CLEAN_US_TBL = "TRUNCATE TABLE Users";

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = GET_SESSION_FACT.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(CREATE_TBL).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.format("Hibernate State: %s\n%s", e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = GET_SESSION_FACT.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(DROP_US_TBL).executeUpdate();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.format("Hibernate State: %s\n%s", e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = GET_SESSION_FACT.openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.format("Hibernate State: %s\n%s", e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = GET_SESSION_FACT.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.format("Hibernate State: %s\n%s", e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        try(Session session = GET_SESSION_FACT.openSession()) {
            List<User> users = session.createQuery(GET_ALL_US, User.class).getResultList();
         return users;
        }
        catch(Exception e) {
            System.err.format("Hibernate State: %s\n%s", e.getMessage());
        }
        return List.of();
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = GET_SESSION_FACT.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(CLEAN_US_TBL).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.format("Hibernate State: %s\n%s", e.getMessage());
        }
    }
}
