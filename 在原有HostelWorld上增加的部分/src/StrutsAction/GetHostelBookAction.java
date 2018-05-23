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

import PersistenceModel.BookBean;
import dao.Service.GetHostelBookDao;

public class GetHostelBookAction extends ActionSupport implements ServletResponseAware,ServletRequestAware{

	private static final long serialVersionUID = 456487578979107682L;
	
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	private GetHostelBookDao getHostelBook;
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public String execute() throws Exception{

		System.out.println("This is GetHostelBook Action");
		
		response.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		PrintWriter out = response.getWriter();
		
		getHostelBook = (GetHostelBookDao) ctx.getBean("getHostelBookImpl");
		
		String hostelID = String.valueOf(request.getSession(false).getAttribute("ID"));
		String memberID = request.getParameter("ID");
		//System.out.println(hostelID+"   "+memberID);
		
		List<BookBean> list = getHostelBook.getHostelBook(Integer.parseInt(hostelID), Integer.parseInt(memberID));
		
		if(list.size() == 0){
			out.write("fail");
			out.close();
			return "success";
		}
		
		JSONArray array = new JSONArray();
		
		for(int i=0; i<list.size(); i++){
			JSONObject temp = new JSONObject();
			
			temp.put("bookID", list.get(i).getBookID());
			String hostelIDStr = String.valueOf(list.get(i).getHostelID());
			temp.put("hostelID",getStringID(hostelIDStr));
			temp.put("memberID", list.get(i).getMemberID());
			temp.put("category", list.get(i).getRoomCategory());
			temp.put("bookTime", list.get(i).getBookTime());
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
