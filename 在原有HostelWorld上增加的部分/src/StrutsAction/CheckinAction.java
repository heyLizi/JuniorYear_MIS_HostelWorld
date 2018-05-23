package StrutsAction;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

import PersistenceModel.BookBean;
import dao.Service.CheckinDao;
import dao.Service.GetHostelBookDao;

public class CheckinAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = -1886457467487234393L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private CheckinDao checkin;
	private GetHostelBookDao getHostelBook;
	
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String execute() throws Exception{

		System.out.println("This is Checkin Action");
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		PrintWriter out = response.getWriter();
		
		checkin = (CheckinDao) ctx.getBean("checkinImpl");
		getHostelBook = (GetHostelBookDao) ctx.getBean("getHostelBookImpl");
		
		boolean isMember = false;
		String memberIDStr = null;
		int memberID = -1;
		
		boolean isBook = false;
		String bookIDStr = null;
		int bookID = -1;
		
		memberIDStr = request.getParameter("memberID"); // 无提前预订的会员
		if(memberIDStr != null) {
			isMember = true;
			memberID = Integer.parseInt(memberIDStr);
		}
		else {
			memberIDStr = request.getParameter("ID"); // 提前预订的会员
			if(memberIDStr != null) {
				isMember = true;
				memberID = Integer.parseInt(memberIDStr);
				isBook = true;
				bookIDStr = request.getParameter("bookID");
				bookID = Integer.parseInt(bookIDStr);
			}
			else { // 非会员

			}
		}
		//System.out.println(isMember+"   "+memberID+"   "+isBook+"   "+bookID);
		
		String hostelIDStr = String.valueOf(request.getSession(false).getAttribute("ID"));
		int hostelID = Integer.parseInt(hostelIDStr);
		//System.out.println(hostelID);
		
		String roomCategory = "";
		if(isBook == false){ // 如果没有提前预订，那么可以直接获取房间类型的值
			String category = request.getParameter("category");
			
			if(category.equals("0")){
				roomCategory = "singleRoom";
			}
			else if(category.equals("1")){
				roomCategory = "standardRoom";
			}
			else{
				roomCategory = "suiteRoom";
			}
		}
		else{
			BookBean book = getHostelBook.getHostelBook(hostelID, memberID).get(0);
			roomCategory = book.getRoomCategory();
		}
		//System.out.println(roomCategory);
		
		Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String checkinDate = formatter.format(currentTime);
		//System.out.println(checkinDate);
	    
		int roomNum = checkin.checkin(hostelID, roomCategory, isBook, bookID, isMember, memberID, checkinDate);
		
		out.write(String.valueOf(roomNum));
		out.close();
		
		return "success";
	}

}
