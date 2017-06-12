package controller;

 
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CourseDAO;
import dao.RegisterDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String form = request.getParameter("form");
		
		if(form.equalsIgnoreCase("login")){
			String username= request.getParameter("username");
			String password= request.getParameter("password");
			RegisterDao reg=new RegisterDao();
			try{
			Map<String,String> m = reg.login(username, password);
			
			if(m != null){
				HttpSession session = request.getSession();
				session.setAttribute("mapvalue",m );
				
				CourseDAO cd = new CourseDAO();
				List<Map<String,String>> list = cd.getCourses(Integer.parseInt(m.get("id")));
				
				session.setAttribute("list", list);
				RequestDispatcher req = request.getRequestDispatcher("WEB-INF/Course.jsp");
				req.forward(request, response);
//				if((m.get("usertype")).equalsIgnoreCase("Faculty")){
//					RequestDispatcher req = request.getRequestDispatcher("WEB-INF/Course.jsp");
//					req.forward(request, response);
//				}else{
//					
//				}
				
			}
			else{
				request.setAttribute("error", "INVALID USERNAME/PASSWORD");
				RequestDispatcher req = request.getRequestDispatcher("Login.jsp");
				req.forward(request, response);
			}
			
			}catch(Exception e){
				e.printStackTrace();
				 request.setAttribute("error", "RETRY");
				 RequestDispatcher req = request.getRequestDispatcher("Login.jsp");
				 req.forward(request, response);
				}
			
			
		}else{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String user = request.getParameter("user");
			String email = request.getParameter("email");
			RegisterDao reg = new RegisterDao();
			try{
			reg.register(username, password, email, user);
			request.setAttribute("msg", "YOU ARE SUCCESSFULLY REGISTERED");
			RequestDispatcher req = request.getRequestDispatcher("Login.jsp");
			req.forward(request, response);
		}catch(Exception e)
			{
			  request.setAttribute("msg", "PLEASE ENTER DIFFERENT USERNAME");
			  RequestDispatcher req = request.getRequestDispatcher("Login.jsp");
			  req.forward(request, response);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
