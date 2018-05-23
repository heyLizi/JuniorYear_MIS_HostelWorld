package StrutsAction;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

import PersistenceModel.BookBean;
import dao.Service.HostelStatisticsDao;

public class HostelStatisticsAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 6424176270867683265L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private HostelStatisticsDao hostelStatistics;
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String execute() throws Exception{

		System.out.println("This is HostelStatistics Action");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		PrintWriter out = response.getWriter();
		
		hostelStatistics = (HostelStatisticsDao) ctx.getBean("hostelStatisticsImpl");
		
		String hostelID = String.valueOf(request.getSession(false).getAttribute("ID"));

		int[] nowCheckin = hostelStatistics.getNowCheckin(Integer.parseInt(hostelID));
		int[] nowBook = hostelStatistics.getNowBook(Integer.parseInt(hostelID));
		
		JSONObject result = new JSONObject();
		
		result.put("checkinData", nowCheckin);
		result.put("bookData", nowBook);
		
		out.write(result.toString());
		out.close();
		
		return "success";
	}
	
	public String getStringID(String id){
		String result = id;
		for(int i=id.length();i<7;i++){
			result = "0"+result;
		}
		return result;
	}

}
