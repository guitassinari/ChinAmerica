package database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Order;

public class OrderDAO extends DAO {

	public void addOrder(Order Order) {
        Transaction trns = null;
        Session session = initSession();
        try {
            trns = session.beginTransaction();
            session.save(Order);
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

    public void deleteOrder(Integer id) {
        Transaction trns = null;
        Session session = initSession();
        try {
            trns = session.beginTransaction();
            Order Order = (Order) session.load(Order.class, (id));
            session.delete(Order);
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

    public void updateOrder(Order Order) {
        Transaction trns = null;
        Session session = initSession();
        try {
            trns = session.beginTransaction();
            session.update(Order);
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

    public List<Order> getAllOrders() {
        List<Order> products = new ArrayList<Order>();
        Transaction trns = null;
        Session session = initSession();
        try {
            trns = session.beginTransaction();
            products = session.createQuery("from Order").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            endSession();
        }
        return products;
    }

    public Order getOrderById(int Orderid) {
        Order order = null;
        Transaction trns = null;
        Session session = initSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from Order where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", Orderid);
            order = (Order) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            endSession();
        }
        return order;
    }
    	
}
