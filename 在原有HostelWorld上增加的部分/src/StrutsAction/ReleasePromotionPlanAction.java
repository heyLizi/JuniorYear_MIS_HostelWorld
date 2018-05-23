package StrutsAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

import PersistenceModel.HostelBean;
import dao.Service.GetHostelInfoDao;
import dao.Service.ReleasePromotionPlanDao;

public class ReleasePromotionPlanAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 312666520144380976L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private ReleasePromotionPlanDao releasePromotionPlan;
	private GetHostelInfoDao getHostelInfo;
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String execute() throws Exception{

		System.out.println("This is ReleasePromotionPlan Action");
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		PrintWriter out = response.getWriter();
		
		releasePromotionPlan = (ReleasePromotionPlanDao) ctx.getBean("releasePromotionPlanImpl");
		getHostelInfo = (GetHostelInfoDao) ctx.getBean("getHostelInfoImpl");
		
		String hostelID =String.valueOf(request.getSession(false).getAttribute("ID"));
		
		String planName = request.getParameter("planName");
		String single = request.getParameter("single");
		String standard = request.getParameter("standard");
		String suite = request.getParameter("suite");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		//System.out.println(planName+"   "+single+"  "+standard+"   "+suite+"  "+start+"   "+end);
		
		HostelBean hostel = getHostelInfo.getHostelInfo(Integer.parseInt(hostelID));
		
		//DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
		//Date startDate = new Date(dateFormate.parse(start).getTime()); 
		//Date endDate = new Date(dateFormate.parse(end).getTime()); 
		
		if(releasePromotionPlan.isConflict(start, Integer.parseInt(hostelID))){
			out.write("fail");
			out.close();
			return "success";
		}
		else{
			releasePromotionPlan.releasePlan(planName, hostel.getHostelID(), Double.valueOf(single), Double.valueOf(standard), Double.valueOf(suite), start, end);
			out.write("success");
			out.close();
			return "success";
		}
	}

}
