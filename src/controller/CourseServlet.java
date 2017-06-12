package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.CourseDAO;

/**
 * Servlet implementation class CourseServlet
 */
@WebServlet("/Course")
@MultipartConfig(maxFileSize = 16177215) 

public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("cid");
		CourseDAO cd = new CourseDAO();
		try {
			Map<String,Object> map = cd.courseDetail(Integer.parseInt(query));
			request.setAttribute("map", map);
			request.getRequestDispatcher("WEB-INF/CourseDetails.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("type");
		String content= request.getParameter("content");
		String cid= request.getParameter("cid");
		String lid= request.getParameter("lid");
		
		
		
		InputStream inputStream = null; // input stream of the upload file
		String type = null;
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("file");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
           
             type = filePart.getContentType();
             System.out.println(type);
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
        
        
        
        
        
        
		if(content!=null && !content.equals("")){
			CourseDAO cd = new CourseDAO();
			if(filePart!=null)
			cd.update(category, type, content, cid, inputStream, extractFileName(filePart));
			else{
				cd.update(category, null, content, cid, null, null);
				
			}
		}
		String query = request.getParameter("cid");
		CourseDAO cd = new CourseDAO();
		try {
			Map<String,Object> map = cd.courseDetail(Integer.parseInt(query));
			request.setAttribute("map", map);
			request.getRequestDispatcher("WEB-INF/CourseDetails.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String extractFileName(Part part) {
	    String contentDisp = part.getHeader("content-disposition");
	    String[] items = contentDisp.split(";");
	    for (String s : items) {
	        if (s.trim().startsWith("filename")) {
	            return s.substring(s.indexOf("=") + 2, s.length()-1);
	        }
	    }
	    return "";
	}

}
