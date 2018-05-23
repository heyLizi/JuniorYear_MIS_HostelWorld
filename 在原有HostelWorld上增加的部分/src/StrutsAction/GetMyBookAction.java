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
import dao.Service.GetMyBookDao;

public class GetMyBookAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 8283872101682727018L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private GetMyBookDao getMyBook;
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String execute() throws Exception{

		System.out.println("This is GetMyBook Action");
		
		response.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		PrintWriter out = response.getWriter();

		getMyBook = (GetMyBookDao) ctx.getBean("getMyBookImpl");
		
		String sign = request.getParameter("sign");
		String id = String.valueOf(request.getSession(false).getAttribute("ID"));
		//System.out.println(sign+"   "+id);
		
		Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String nowDate = formatter.format(currentTime);
		
		List<BookBean> list;
		if(sign.equals("0")){
			list = getMyBook.getCurrentBook(Integer.parseInt(id), nowDate);
		}
		else{
			list = getMyBook.getHistoryBook(Integer.parseInt(id), nowDate);
		}
		
		if(list.size()==0){
			out.write("fail");
			out.close();
			return "success";
		}
		
		JSONArray array = new JSONArray();
		
		for(int i=0 ; i<list.size(); i++){
			JSONObject temp = new JSONObject();
			temp.put("bookID", list.get(i).getBookID());
			String hostelID = String.valueOf(list.get(i).getHostelID());
			temp.put("hostelID",getStringID(hostelID));
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
