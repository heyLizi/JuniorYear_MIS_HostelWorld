package StrutsAction;

import java.io.PrintWriter;
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

import PersistenceModel.PromotionPlanBean;
import dao.Service.GetPromotionPlanDao;

public class GetPromotionPlanAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 6631719712926415424L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private GetPromotionPlanDao getPromotionPlan;
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String execute() throws Exception{

		System.out.println("This is GetPromotionPlan Action");
		
		response.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		PrintWriter out = response.getWriter();
		
		getPromotionPlan = (GetPromotionPlanDao) ctx.getBean("getPromotionPlanImpl");
		
		String hostelId = String.valueOf(request.getSession(false).getAttribute("ID"));
		//System.out.println(hostelId);
		
		List<PromotionPlanBean> list = getPromotionPlan.getPromotionPlan(Integer.parseInt(hostelId));
		
		JSONArray array = new JSONArray();
		
		for(int i=0; i<list.size(); i++){
			JSONObject temp = new JSONObject();
			temp.put("promotionID", list.get(i).getPromotionID());
			temp.put("promotionName", list.get(i).getPromotionName());
			temp.put("singleFee", list.get(i).getSingleRoomFee());
			temp.put("standardFee", list.get(i).getStandardRoomFee());
			temp.put("suiteFee", list.get(i).getSuiteRoomFee());
			temp.put("startDate", list.get(i).getStartDate().toString());
			temp.put("endDate", list.get(i).getEndDate().toString());
			array.put(temp);
		}
		
		out.write(array.toString());
		out.close();
		
		return "success";
	}

}
