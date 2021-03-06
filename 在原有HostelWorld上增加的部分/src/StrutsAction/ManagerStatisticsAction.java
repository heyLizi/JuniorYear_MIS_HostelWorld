package StrutsAction;

import java.util.List;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

import VOModel.hostelInVO;
import VOModel.memberVO;
import dao.Service.ManagerStatisticsDao;

public class ManagerStatisticsAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 7007844577917835246L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private ManagerStatisticsDao managerStatistics;
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String execute() throws Exception{

		System.out.println("This is ManagerStatistics Action");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		PrintWriter out = response.getWriter();
		
		managerStatistics = (ManagerStatisticsDao) ctx.getBean("managerStatisticsImpl");
		
		double[] sale = managerStatistics.getSaleMoney();
		
		JSONObject result = new JSONObject();
		
		result.put("saleData", sale);
		
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
