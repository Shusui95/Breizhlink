

import beans.User;
import dao.UserDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class Informations
 */
@WebServlet("/informations-personnelles")
public class Informations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Informations() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null || session.getAttribute("user").equals("")){
			response.sendRedirect("http://localhost:8080/Breizhlink/connexion");
		}else{
			UserDaoImpl userDao = new UserDaoImpl();
			User userSend = (User) session.getAttribute("user");
			User userInfos = userDao.getUserById(userSend);
			
			// Check if user exists
			if (userInfos != null && userInfos.getEmail() != null){
				session.setAttribute("userInfos", userInfos);
			}else{
				session.setAttribute("userInfos", null);
			}
			RequestDispatcher view = request.getRequestDispatcher("/views/informations.jsp");
	        view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
