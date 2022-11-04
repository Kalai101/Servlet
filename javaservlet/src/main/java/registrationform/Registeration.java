package registrationform;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/registeration")
public class Registeration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Registeration() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmpassword= request.getParameter("confirmpassword");
		String DateOfBirth = request.getParameter("dob");
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			
				con = Database.getConnection();
				stmt = con.createStatement();
				rs = stmt.executeQuery("select  NAME from LOGIN");
	        String usernameCounter;
	         while(rs.next()) 
	         {
	           usernameCounter =  rs.getString("name");
	           
			if(usernameCounter.equals(name)) {
	               out.println("It already exists");
	               RequestDispatcher rd = request.getRequestDispatcher("homepage.html");
					rd.include(request, response);
					return;
	           }
	         }
			if (password.equals(confirmpassword)) {
				Connection con1 = Database.getConnection();
				PreparedStatement pstmt1 = con1.prepareStatement("Insert into LOGIN(name, MAIL, dob,PASSWORD) values(?,?,?,?)");
				
				pstmt1.setString(1, name);
				pstmt1.setString(2, email);
				pstmt1.setString(3, password);
				pstmt1.setString(4, DateOfBirth);
				
				int x = pstmt1.executeUpdate();
				if(x>=0) {
					RequestDispatcher rd = request.getRequestDispatcher("success.html");
					rd.forward(request, response);
				}else {
					out.println("<h2>Data not saved</h2>");
				}
				
			} else {
				out.print("Sorry username or password error");
				RequestDispatcher rd = request.getRequestDispatcher("register.html");
				rd.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}