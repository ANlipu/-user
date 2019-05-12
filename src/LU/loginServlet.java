package LU;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class loginServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		 
		 Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("成功");
				con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "q1w2e3");
				System.out.println("connect成功");

				Statement sta = (Statement) con.createStatement();
				String user=request.getParameter("username");
				String pwd=request.getParameter("pwd");
				
				ResultSet rs = (ResultSet) sta
						.executeQuery("select*from userss where user='"+user+"' and pwd='"+pwd+"' ");
				if(rs.next()){
					String ss =request.getParameter("username");
					String pp =request.getParameter("pwd");
					if(ss.equals(user)&& pp.equals(pwd)){
						HttpSession session= request.getSession();
						session.setAttribute("usernam", user);
						session.setAttribute("pwd", pwd);
						session.setMaxInactiveInterval(10);
						System.out.println("已存在");
						       
						    request.getRequestDispatcher("/home").forward(request, response);
						}
					}else{
					    response.sendRedirect("/RenWu/register.html");  
					}
				
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("失败");
			} catch (SQLException sqle) {
				System.out.println("连接失败");
			}
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
			out.println("  <BODY>");
			out.print("    This is ");
			out.print(this.getClass());
			out.println(", using the GET method");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
	}

}
