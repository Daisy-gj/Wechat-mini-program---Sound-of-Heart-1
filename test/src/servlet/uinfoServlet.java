package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class uinfoServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //�����������
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        /* ������Ӧͷ����ajax������� */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* �Ǻű�ʾ���е��������󶼿��Խ��ܣ� */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        //��ȡ΢��С����get�Ĳ���ֵ����ӡ
        try{
            String uid=request.getParameter("Id");
            System.out.println(uid);
        	String driverName = "com.mysql.jdbc.Driver";  
        	String url = "jdbc:mysql://localhost/tsh?user=root&password=pop966100";  
        	Class.forName(driverName);  	
        	Connection conn = DriverManager.getConnection(url);     
        	Statement stmt = conn.createStatement();  
    		String ssql="select ubirth,usign from users where uid="+uid; 
    		ResultSet rs = stmt.executeQuery(ssql);
    		rs.last();
    		rs.beforeFirst();
    		JsonArray array = new JsonArray();
    		while(rs.next())
    		{
    			JsonObject obj = new JsonObject();
    			String birth=rs.getString("ubirth");
    			String sign=rs.getString("usign");

    			obj.addProperty("birth",birth);
    			obj.addProperty("sign",sign);  			
    			array.add(obj);
    		}
    		PrintWriter out = null;
    		out = response.getWriter();
    		out.append(array.toString());  
	        out.close();
        }
        catch (Exception e) 
        {
        	System.out.println(e);  
            e.printStackTrace();
       	}
        //result.put("data", user);
        //result.put("msg", "��̨���յ�");
        //ʹ��Gson����Ҫ����gson-2.8.0.jar
        //String json = new Gson().toJson(result);

        //����ֵ��΢��С����
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

