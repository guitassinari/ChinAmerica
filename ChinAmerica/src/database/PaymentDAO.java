package database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Payment;

public class PaymentDAO extends DAO {

	public void addPayment(Payment Payment) {
        Transaction trns = null;
        Session session = initSession();
        try {
            trns = session.beginTransaction();
            session.save(Payment);
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

    public void deletePayment(Integer id) {
        Transaction trns = null;
        Session session = initSession();
        try {
            trns = session.beginTransaction();
            Payment Payment = (Payment) session.load(Payment.class, (id));
            session.delete(Payment);
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

    public void updatePayment(Payment Payment) {
        Transaction trns = null;
        Session session = initSession();
        try {
            trns = session.beginTransaction();
            session.update(Payment);
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

    public List<Payment> getAllPayments() {
        List<Payment> products = new ArrayList<Payment>();
        Transaction trns = null;
        Session session = initSession();
        try {
            trns = session.beginTransaction();
            products = session.createQuery("from Payment").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            endSession();
        }
        return products;
    }

    public Payment getPaymentById(int Paymentid) {
        Payment payment = null;
        Transaction trns = null;
        Session session = initSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from Payment where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", Paymentid);
            payment = (Payment) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            endSession();
        }
        return payment;
    }
    
    public Payment getPaymentByOrderId(int orderId) {
        Payment payment = null;
        Transaction trns = null;
        Session session = initSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from Payment where order_id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", orderId);
            payment = (Payment) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            endSession();
        }
        return payment;
    }
	
}
