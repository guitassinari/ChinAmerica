package database;

import org.hibernate.Session;

public abstract class DAO {
	
	private Session session;
	
	/**
	 * IMplementar a preparação da session, rollback, transaction, fechar a session
	 */
	
	/**
	 * 
	 * Before e after em todas as DAOs???
	 * 
	 * begin Transaction
	 * 
	 * end transaction
	 * Rollback
	 * catch
	 * stackTrace
	 * 
	 */
	
	public Session getSession(){
		if(session == null){
			session = HibernateUtil.getSessionFactory().openSession();
		}
		
		return session;
	}
	
	public void endSession(){
        session.flush();
        session.close();
	}
}
