package dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import PersistenceModel.BookBean;
import dao.Service.BookRoomDao;

public class BookRoomImpl implements BookRoomDao{

	private SessionFactory sessionFactory;
	
	@Override
	public void bookRoom(int hostelID, int memberID, String roomCategory, String bookTime, 
            String startDate, String endDate, int periodSpan, boolean isPromotion, int advanceTime) {

		// 增加一条Book信息
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		
		BookBean book = new BookBean(); 
		
		book.setHostelID(hostelID);
		book.setMemberID(memberID);
		book.setRoomCategory(roomCategory);
		book.setBookTime(bookTime);
		book.setStartDate(startDate);
		book.setEndDate(endDate);
		book.setPeriodSpan(periodSpan);
		book.setIsPromotion(isPromotion);
		book.setAdvanceTime(advanceTime);
		
		sess.save(book);
		tx.commit();
		sess.close();
		
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
