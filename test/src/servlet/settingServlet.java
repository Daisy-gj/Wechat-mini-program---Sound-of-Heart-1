package servlet;

import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.Writer;


public class settingServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
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
            String birth=request.getParameter("birth");
            String sign=request.getParameter("sign");
            String status=request.getParameter("status");
            System.out.println(birth);
            System.out.println(sign);
            System.out.println(status);
            Class.forName("com.mysql.jdbc.Driver"); // ����MYSQL						
        	Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/tsh?user=root&password=pop966100");
    		PreparedStatement ps = connect.prepareStatement(
    				"update users set ubirth=?,usign=?");
			    		ps.setString(1,birth);
			    		ps.setString(2,sign);
    		    int  result = ps.executeUpdate();
    			ps.close();
    			if (result>0) {
    				System.out.println("����1��Ϣ�ɹ���");
    				//����ֵ��΢��С����
    		        Writer out = response.getWriter();
    		        out.write("��̨���յ�");
    		        out.flush();
    		       }
    		PreparedStatement ps1 = connect.prepareStatement(
        				"update moment set private=?");
    			    		ps.setString(1,status);
        		    int  result1 = ps1.executeUpdate();
        			ps1.close();
        			if (result1>0) {
        				System.out.println("����2��Ϣ�ɹ���");
        				//����ֵ��΢��С����
        		        Writer out = response.getWriter();
        		        out.write("��̨���յ���");
        		        out.flush();
        		       }
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
	public void init() throws ServletException {
	}
}
