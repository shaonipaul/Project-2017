package controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourseDAO;

/**
 * Servlet implementation class DownloadFile
 */
@WebServlet("/DownloadFile")
public class DownloadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fid = request.getParameter("fid");
		String category = request.getParameter("type");
		CourseDAO c = new CourseDAO();
		Map<String, Object> m = c.download(Integer.parseInt(fid), category);
		ServletOutputStream output = response.getOutputStream();
		
		response.reset();
		response.setContentType((String)m.get("type"));
		response.setHeader("Content-disposition","attachment; filename=" +(String)m.get("name"));
		byte[] buf = new byte[1024];
		int len;
		ByteArrayInputStream bis = (ByteArrayInputStream)m.get("data");
		while ((len = bis.read(buf)) > 0){
		output.write(buf, 0, len);
		}
		bis.close();
		response.getOutputStream().flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
