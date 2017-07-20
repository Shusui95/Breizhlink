import beans.User;
import dao.UrlDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class Url
 */
@WebServlet("/url")
public class Url extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Url() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// CHeck if user is connected
		if(session.getAttribute("user") == null || session.getAttribute("user").equals("")){
			response.sendRedirect("http://localhost:8080/Breizhlink/connexion");
		}else{
			UrlDaoImpl urlDao = new UrlDaoImpl();
			User userSend = (User) session.getAttribute("user");
			ArrayList<beans.Url> urlsList = urlDao.getUrlsByUser(userSend);
			if (urlsList.size() > 0){
				session.setAttribute("urls", urlsList);
			}else{
				session.setAttribute("urls", null);
			}
			RequestDispatcher view = request.getRequestDispatcher("/views/url.jsp");
	        view.forward(request, response);
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
