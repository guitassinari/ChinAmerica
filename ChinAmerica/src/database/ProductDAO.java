package database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Product;

public class ProductDAO extends DAO {
	
	public void addProduct(Product Product) {
        Transaction trns = null;
        Session session = initSession();
        try {
            trns = session.beginTransaction();
            session.save(Product);
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

    public void deleteProduct(String ProductCpf) {
        Transaction trns = null;
        Session session = initSession();
        try {
            trns = session.beginTransaction();
            Product Product = (Product) session.load(Product.class, (ProductCpf));
            session.delete(Product);
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

    public void updateProduct(Product Product) {
        Transaction trns = null;
        Session session = initSession();
        try {
            trns = session.beginTransaction();
            session.update(Product);
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

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<Product>();
        Transaction trns = null;
        Session session = initSession();
        try {
            trns = session.beginTransaction();
            products = session.createQuery("from Product").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            endSession();
        }
        return products;
    }

    public Product getProductById(int Productid) {
        Product product = null;
        Transaction trns = null;
        Session session = initSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from Product where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", Productid);
            product = (Product) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            endSession();
        }
        return product;
    }

}
