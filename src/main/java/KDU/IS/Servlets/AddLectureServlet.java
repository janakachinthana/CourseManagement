package KDU.IS.Servlets;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import KDU.IS.Models.Lecture;
import KDU.IS.Services.ILectureService;
import KDU.IS.Services.LectureServiceImpl;
/**
 * Servlet implementation class AddLectureServlet
 */
@WebServlet("/AddLectureServlet")
public class AddLectureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLectureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
			Lecture lecture = new Lecture();
			try {
				List<FileItem> multifile = sf.parseRequest(request);
				
				for(FileItem item : multifile) {
					try {
						item.write(new File("/Users/J.dissanayake/eclipse-workspace/KDU.IS.Self_Defense_Learning/src/main/webapp/assets/Files/"+item.getName()));

					
						
						lecture.setLectureName(request.getParameter("lectureName"));
						lecture.setDescription(request.getParameter("description"));
						lecture.setCourseID(request.getParameter("courseID"));
						lecture.setFileUrl("/Users/J.dissanayake/eclipse-workspace/KDU.IS.Self_Defense_Learning/src/main/webapp/assets/Files/"+item.getName());
						lecture.setStatus("Active");
						
						System.out.println(request.getParameter("courseID ")+ "hfhsdf");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				ILectureService lectureService = new LectureServiceImpl();
				lectureService.addLecture(lecture);
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/signUp.html");
				dispatcher.forward(request, response);
			}
	}

}
