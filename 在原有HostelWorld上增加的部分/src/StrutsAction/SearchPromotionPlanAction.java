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

import PersistenceModel.HostelBean;
import PersistenceModel.PromotionPlanBean;
import dao.Service.GetHostelInfoDao;
import dao.Service.GetPromotionPlanDao;

public class SearchPromotionPlanAction extends ActionSupport implements ServletResponseAware,ServletRequestAware{

	private static final long serialVersionUID = -8217881198777556375L;
	
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	private GetPromotionPlanDao getPromotionPlan;
	private GetHostelInfoDao getHostelInfo;
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public String execute() throws Exception{

		System.out.println("This is SearchPromotionPlan Action");
		
		response.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		PrintWriter out = response.getWriter();

		getPromotionPlan = (GetPromotionPlanDao) ctx.getBean("getPromotionPlanImpl");
		getHostelInfo = (GetHostelInfoDao) ctx.getBean("getHostelInfoImpl");
		
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String date = request.getParameter("start");
		//System.out.println(province+"   "+city+"   "+date);
		
		//DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
		//Date startDate = new Date(dateFormate.parse(date).getTime()); 
		
		List<PromotionPlanBean> list = getPromotionPlan.getPromotionPlanByAreaAndTime(province, city, date);
		
		JSONArray array = new JSONArray();
		
		if(list.size()==0){
			out.write("无促销信息");
			out.close();
			return "success";
		}
		
		for(int i=0; i<list.size(); i++){
			JSONObject temp = new JSONObject();
			
			int hostelID = list.get(i).getHostelID();
			String hostelIDStr = String.valueOf(hostelID);
			HostelBean hostel =  getHostelInfo.getHostelInfo(hostelID);
			String hostelName = hostel.getHostelName();
			
			temp.put("promotionID", list.get(i).getPromotionID());
			temp.put("promotionName", list.get(i).getPromotionName());
			temp.put("hostelID",hostelIDStr);
			temp.put("hostelName", hostelName);
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
	
	
	public String getStringID(String id){
		String result = id;
		for(int i=id.length(); i<7; i++){
			result = "0"+result;
		}
		return result;
	}

}
