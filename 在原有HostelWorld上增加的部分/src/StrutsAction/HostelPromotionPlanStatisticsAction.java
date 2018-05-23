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
import PersistenceModel.PromotionPlanBean;
import dao.Service.HostelMoneyStatisticsDao;
import dao.Service.HostelPromotionPlanStatisticsDao;
import dao.Service.HostelStatisticsDao;

public class HostelPromotionPlanStatisticsAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 6424176270867683265L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private HostelPromotionPlanStatisticsDao hostelPromotionPlanStatistics;
	
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
		
		hostelPromotionPlanStatistics = (HostelPromotionPlanStatisticsDao) ctx.getBean("hostelPromotionPlanStatisticsImpl");
		
		String hostelID = String.valueOf(request.getSession(false).getAttribute("ID"));

		PromotionPlanBean lastPromotionPlan = hostelPromotionPlanStatistics.getLastPromotion(Integer.parseInt(hostelID));
		double[] ppAnalyse = hostelPromotionPlanStatistics.getLastPromotionAnalyse(Integer.parseInt(hostelID));
		
		JSONObject result = new JSONObject();
		
		result.put("lastPromotionPlanData", lastPromotionPlan);
		result.put("PromotionPlanAnalyseData", ppAnalyse);
		
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
