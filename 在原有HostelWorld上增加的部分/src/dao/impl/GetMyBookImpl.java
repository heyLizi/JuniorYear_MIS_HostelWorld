package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import PersistenceModel.BookBean;
import dao.Service.GetMyBookDao;

public class GetMyBookImpl implements GetMyBookDao{

	private SessionFactory sessionFactory;
	
	private BookBean book;
	
	@Override
	public List<BookBean> getCurrentBook(int memberID, String currentDate) {

		// 获得会员编号为memberID的会员截止到当前时间未Settle的Book
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		
		List<BookBean> list = sess.createQuery("select b from BookBean b where "+
		"b.memberID = ? and b.startDate >= ? and b.isSettled = false").setParameter(0, memberID).setParameter(1, currentDate).list();
		//System.out.println(list.size());
		
		tx.commit();
		sess.close();
		
		return list;
	}
	
	@Override
	public List<BookBean> getHistoryBook(int memberID, String currentDate) {

		// 获得会员编号为memberID的会员截止到currentDate的所有Book
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		
		List<BookBean> list = sess.createQuery("select b from BookBean b where "+
		"b.memberID = ? and b.endDate <= ?").setParameter(0, memberID).setParameter(1, currentDate).list();
		//System.out.println(list.size());
		
		tx.commit();
		sess.close();
		
		return list;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setBook(BookBean book) {
		this.book = book;
	}
	
}
