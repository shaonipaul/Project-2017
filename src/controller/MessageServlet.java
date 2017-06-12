package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.Message;
import model.MessageList;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("to        "+request.getParameter("to")+"     from     "+request.getParameter("from"));
		int a = Integer.parseInt(request.getParameter("to"));
		int b = Integer.parseInt(request.getParameter("from"));
		PrintWriter pw = response.getWriter();
		
		String c ="";
		if(a>b){
			c+=b+""+a;
		}else{
			c+=a+""+b;
		}
		Message m = new Message();
		
		MessageList listm = m.getMessage(c);
		ObjectMapper mapp = new ObjectMapper();
		System.out.println(mapp.writeValueAsString(listm));
		pw.print(mapp.writeValueAsString(listm));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
