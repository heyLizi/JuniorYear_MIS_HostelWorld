package dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import PersistenceModel.BookBean;
import dao.Service.GetHostelBookDao;

public class GetHostelBookImpl implements GetHostelBookDao{

	private SessionFactory sessionFactory;
	
	private BookBean book;
	
	@Override
	public List<BookBean> getHostelBook(int hostelID) {
		
		// 获得编号为hostelID的酒店中的Book信息列表
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		
		Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String nowDate = formatter.format(currentTime);
		
		List<BookBean> list = sess.createQuery("select b from BookBean b where "+
		"b.hostelID = ? and b.startDate >= ?")
		.setParameter(0, hostelID).setParameter(1, nowDate).list();
		
		tx.commit();
		sess.close();
		
		return list;
	}
	
	@Override
	public List<BookBean> getHostelBook(int hostelID, int memberID) {
		
		// 获得会员号为memberID的会员在编号为hostelID的酒店中的Book信息列表
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();

		Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String nowDate = formatter.format(currentTime);
		
		List<BookBean> list = sess.createQuery("select b from BookBean b where "+
		"b.hostelID = ? and b.memberID = ? and b.startDate >= ?")
		.setParameter(0, hostelID).setParameter(1, memberID).setParameter(2, nowDate).list();
		
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
