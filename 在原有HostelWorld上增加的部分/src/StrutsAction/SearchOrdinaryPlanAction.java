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
import PersistenceModel.OrdinaryPlanBean;
import PersistenceModel.PromotionPlanBean;
import dao.Service.GetHostelInfoDao;
import dao.Service.GetOrdinaryPlanDao;
import dao.Service.GetPromotionPlanDao;

public class SearchOrdinaryPlanAction extends ActionSupport implements ServletResponseAware,ServletRequestAware{

	private static final long serialVersionUID = -8217881198777556375L;
	
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	private GetOrdinaryPlanDao getOrdinaryPlan;
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

		System.out.println("This is SearchOrdinaryPlan Action");
		
		response.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		PrintWriter out = response.getWriter();

		getOrdinaryPlan = (GetOrdinaryPlanDao) ctx.getBean("getOrdinaryPlanImpl");
		getPromotionPlan = (GetPromotionPlanDao) ctx.getBean("getPromotionPlanImpl");
		getHostelInfo = (GetHostelInfoDao) ctx.getBean("getHostelInfoImpl");
		
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String date = request.getParameter("start");
		System.out.println(province+"   "+city+"   "+date);
		
		//DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
		//Date startDate = new Date(dateFormate.parse(date).getTime()); 
		
		List<HostelBean> hostelList = getOrdinaryPlan.getHostelByAreaAndTime(province, city);
		OrdinaryPlanBean ordinaryPlan = getOrdinaryPlan.getOrdinaryPlan(hostelList.get(0).getHostelID());
		List<PromotionPlanBean> promotionList = getPromotionPlan.getPromotionPlanByAreaAndTime(province, city, date);
		
		JSONArray array = new JSONArray();
		
		if(hostelList.size()==0){
			out.write("无符合条件的酒店信息");
			out.close();
			return "success";
		}
		
		int[] promotionHostelID = new int[promotionList.size()];
		for(int i=0; i<promotionList.size(); i++){
			promotionHostelID[i] = promotionList.get(i).getHostelID();
		}
		
		for(int i=0; i<hostelList.size(); i++){
			
			boolean isDuplicate = false;
			int hostelID = hostelList.get(i).getHostelID();
			for(int j=0; j<promotionList.size(); j++){
				if(promotionHostelID[j] == hostelID) {
					isDuplicate = true;
					break;
				}
			}
			
			if(isDuplicate == false) {
				JSONObject temp = new JSONObject();
				
				String hostelIDStr = String.valueOf(hostelID);
				HostelBean hostel =  getHostelInfo.getHostelInfo(hostelID);
				String hostelName = hostel.getHostelName();
				
				temp.put("hostelID",hostelIDStr);
				temp.put("hostelName", hostelName);
				temp.put("singleFee", ordinaryPlan.getSingleRoomFee());
				temp.put("standardFee", ordinaryPlan.getStandardRoomFee());
				temp.put("suiteFee", ordinaryPlan.getSuiteRoomFee());
				array.put(temp);
			}
			
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
