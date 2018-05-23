package StrutsAction;

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

import PersistenceModel.OrdinaryPlanBean;
import dao.Service.GetOrdinaryPlanDao;

public class GetOrdinaryPlanAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 6631719712926415424L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private GetOrdinaryPlanDao getOrdinaryPlan;
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String execute() throws Exception{

		System.out.println("This is GetOrdinaryPlan Action");
		
		response.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		PrintWriter out = response.getWriter();
		
		getOrdinaryPlan = (GetOrdinaryPlanDao) ctx.getBean("getOrdinaryPlanImpl");
		
		int defaultHostelID = 1;
		OrdinaryPlanBean opb = getOrdinaryPlan.getOrdinaryPlan(defaultHostelID);
		
		JSONArray array = new JSONArray();
		JSONObject temp = new JSONObject();
		
		temp.put("singleFee", opb.getSingleRoomFee());
		temp.put("standardFee", opb.getStandardRoomFee());
		temp.put("suiteFee", opb.getSuiteRoomFee());
		array.put(temp);
		
		out.write(array.toString());
		out.close();
		
		return "success";
	}

}
