package dao.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import PersistenceModel.CheckinBean;
import PersistenceModel.RoomBean;
import dao.Service.CheckinDao;

public class CheckinImpl implements CheckinDao{

	private SessionFactory sessionFactory;
	
	@Override
	public int checkin(int hostelID, String roomCategory, boolean isBook, int bookID, 
			           boolean isMember, int memberID, String checkinDate) {
		
		// 找一个这家酒店中对应类型的空房间，记录房间编号，更新Room入住状态
		Session sess1 = sessionFactory.openSession();
		Transaction tx1 = sess1.beginTransaction();
		 
		List<RoomBean> roomList = sess1.createQuery("select r from RoomBean r where "+
		"r.hostelID = ? and r.roomCategory = ? and r.isCheckin = 0").setParameter(0, hostelID).setParameter(1, roomCategory).list();
		RoomBean room = roomList.get(0);
		int roomNum = room.getRoomID();
		room.setIsCheckin(true);
		
		sess1.update(room);
		tx1.commit();
		sess1.close();
		
		// 增加一条Checkin信息
		Session sess2 = sessionFactory.openSession();
		Transaction tx2 = sess2.beginTransaction();
		
		CheckinBean checkin = new CheckinBean();
		
		checkin.setHostelID(hostelID);
		checkin.setRoomCategory(roomCategory);
		checkin.setRoomID(roomNum);
		checkin.setIsBook(isBook);
		checkin.setBookID(bookID);
		checkin.setIsMember(isMember);
		checkin.setMemberID(memberID);
		checkin.setCheckinDate(checkinDate);
		
		sess2.save(checkin);
		
		// 得到刚才保存的Checkin的编号
		int checkinID = checkin.getCheckinID();
		System.out.println(checkinID);
		
		tx2.commit();
		sess2.close();
		
		// 将得到的Checkin编号登记到上述房间的信息中，即更新一条Room信息
		Session sess3 = sessionFactory.openSession();
		Transaction tx3 = sess3.beginTransaction();
		
		List<RoomBean> roomList2 = sess3.createQuery("select r from RoomBean r "+
		"where r.roomID=?").setParameter(0, roomNum).list();
		RoomBean thisRoom = roomList2.get(0);
		thisRoom.setLatestCheckinID(checkinID);

		sess3.update(thisRoom);
		tx3.commit();
		sess3.close();
		
		return roomNum;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
