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

import dao.Service.CheckoutDao;

public class CheckoutAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	private static final long serialVersionUID = 6637145684067005756L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;

	private CheckoutDao outHostel;
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String execute() throws Exception{

		System.out.println("This is Checkout Action");
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		PrintWriter out = response.getWriter();
		
		outHostel = (CheckoutDao) ctx.getBean("checkoutImpl");
		
		String hostelIDStr = String.valueOf(request.getSession(false).getAttribute("ID")); 
		int hostelID = Integer.parseInt(hostelIDStr);
		String roomIDStr = request.getParameter("roomID");
		int roomID = Integer.parseInt(roomIDStr);
		//System.out.println(hostelID+"   "+roomID);
		
		Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String checkoutDate = formatter.format(currentTime);
		//System.out.println(checkoutDate);
	    
		outHostel.checkout(hostelID, roomID, checkoutDate);
		
		out.write("success");
		out.close();
		
		return "success";
	}

}
