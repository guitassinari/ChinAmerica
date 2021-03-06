package database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.User;
import database.HibernateUtil;

public class UserDAO extends DAO{

    public void addUser(User user) {
        Transaction trns = null;
        Session session = initSession();
        try {
            trns = session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            endSession();
        }
    }

    public void deleteUser(String userCpf) {
        Transaction trns = null;
        Session session = initSession();
        try {
            trns = session.beginTransaction();
            User user = (User) session.load(User.class, (userCpf));
            session.delete(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
        	endSession();
        }
    }

    public void updateUser(User user) {
        Transaction trns = null;
        Session session = initSession();
        try {
            trns = session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            endSession();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        Transaction trns = null;
        Session session = initSession();
        try {
            trns = session.beginTransaction();
            users = session.createQuery("from User").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            endSession();
        }
        return users;
    }
    
    public User getUserByCpf(String userCpf) {
        User user = null;
        Transaction trns = null;
        Session session = initSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from User where cpf = :cpf";
            Query query = session.createQuery(queryString);
            query.setString("cpf", userCpf);
            user = (User) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
        	endSession();
        }
        return user;
    }
    
}