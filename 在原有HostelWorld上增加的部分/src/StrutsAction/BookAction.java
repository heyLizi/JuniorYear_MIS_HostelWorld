package StrutsAction;

import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

import dao.Service.BookRoomDao;

public class BookAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = -883869086167874111L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private BookRoomDao bookRoom;
	
	private static String[] roomCategory = {"singleRoom","standardRoom","suiteRoom"};
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String execute() throws Exception{

		System.out.println("This is Book Action");
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		PrintWriter out = response.getWriter();
		
		bookRoom = (BookRoomDao) ctx.getBean("bookRoomImpl");
		
		String memberIDStr = String.valueOf(request.getSession(false).getAttribute("ID")); 
		int memberID = Integer.parseInt(memberIDStr);
		String planID = request.getParameter("planID");
		int hostelID = Integer.parseInt(planID.substring(3));
		String categoryCount = request.getParameter("category");
		String roomCategoryStr = roomCategory[Integer.parseInt(categoryCount)];
		String start = request.getParameter("startDate");
		String end = request.getParameter("endDate");
		//System.out.println(memberID+"   "+planID+"   "+hostelID+"   "+categoryCount+"   "+roomCategoryStr);
		
		DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = new Date(dateFormate.parse(start).getTime()); 
		Date endDate = new Date(dateFormate.parse(end).getTime()); 
		Date nowDate = new Date(System.currentTimeMillis());
		String startDateStr = dateFormate.format(startDate);
		String endDateStr = dateFormate.format(endDate);
		String nowDateStr = dateFormate.format(nowDate);
		//System.out.println(startDateStr+"   "+endDateStr+"   "+nowDateStr);
		
		// startDate和endDate的具体时间都是00:00:00，而nowDate的具体时间不是00:00:00
		// 所以endDate-startDate可以完全整除，而startDate-advance不能完全整除，余数被浪费，所以额外加1
		int period =  (int)((endDate.getTime()-startDate.getTime())/ (3600*24*1000));
		int advance = (int)((startDate.getTime()-nowDate.getTime())/ (3600*24*1000)+1);
		//System.out.println(period+"   "+advance);
		
		boolean isPromotion;
		if(planID.startsWith("ord")) {
			isPromotion = false;
		}
		else{
			isPromotion = true;
		}
		
		bookRoom.bookRoom(hostelID, memberID, roomCategoryStr, nowDateStr, startDateStr, endDateStr, period, isPromotion, advance);
		
		out.write("success");
		out.close();
		
		return "success";
	}
	
	public Date DateAdd(Date date){
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		java.util.Date temp = calendar.getTime();
		java.sql.Date result = new Date(temp.getTime());
		
		return result;
	}

}
