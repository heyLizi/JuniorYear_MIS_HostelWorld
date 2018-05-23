package StrutsAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

import dao.Service.ReleaseOrdinaryPlanDao;

public class ReleaseOrdinaryPlanAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 312666520144380976L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private ReleaseOrdinaryPlanDao releaseOrdinaryPlan;
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String execute() throws Exception{

		System.out.println("This is ReleaseOrdinaryPlan Action");
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		PrintWriter out = response.getWriter();
		
		releaseOrdinaryPlan = (ReleaseOrdinaryPlanDao) ctx.getBean("releaseOrdinaryPlanImpl");
		
		String single = request.getParameter("single");
		String standard = request.getParameter("standard");
		String suite = request.getParameter("suite");
		//System.out.println(single+"   "+standard+"   "+suite);
		
		releaseOrdinaryPlan.releasePlan(Double.valueOf(single), Double.valueOf(standard), Double.valueOf(suite));
		out.write("success");
		out.close();
		return "success";
	}

}
