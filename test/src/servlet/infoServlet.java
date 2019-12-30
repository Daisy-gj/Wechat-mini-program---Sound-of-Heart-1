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


public class infoServlet extends HttpServlet {

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
    		String ssql="select useid,cdate,ccontent,mcontent from comment_u where uid="+uid+" order by cdate desc"; 
    		ResultSet rs = stmt.executeQuery(ssql);
    		rs.last();
    		rs.beforeFirst();
    		JsonArray array = new JsonArray();
    		while(rs.next())
    		{
    			JsonObject obj = new JsonObject();
    			String useid=rs.getString("useid");
    			String cdate=rs.getString("cdate");
    			String ccontent=rs.getString("ccontent");
    			String mcontent=rs.getString("mcontent");
 
    			obj.addProperty("useid",useid);
    			obj.addProperty("cdate",cdate);
    			obj.addProperty("ccontent",ccontent);
    			obj.addProperty("mcontent",mcontent);
    			
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
