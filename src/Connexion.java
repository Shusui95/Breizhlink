
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
 * Servlet implementation class Connexion
 */
@WebServlet("/connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        super();
    }

	/**
	 * Connexion form
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null || session.getAttribute("user").equals("")){
			session.setAttribute("confirm", null);
		}
		RequestDispatcher view = request.getRequestDispatcher("/views/connexion.jsp");      
        view.forward(request, response);
	}

	/**
	 * Connexion submit
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserDaoImpl userDao = new UserDaoImpl();
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		User userConnect = new User();
		userConnect.setLogin(login);
		userConnect.setPassword(userDao.encryptSHA(password));
		userConnect = userDao.getUserByLoginAndPassword(userConnect);
		// Check if user exist
		if (userConnect != null && userConnect.getId() > 0 && userConnect.getEmail() != null && userConnect.getStatut() != null){
			
			// Check if account is confirmed
			if (userConnect.getActivated() == 1){
				session.setAttribute("user", userConnect);
				System.out.println("conex "+userConnect.toString());
				session.setAttribute("errorConnect", null);
				session.setAttribute("confirm", null);
				response.sendRedirect("http://localhost:8080/Breizhlink/raccourcir");
			}else{
				session.setAttribute("user", userConnect);
				session.setAttribute("errorConnect", null);
				session.setAttribute("confirm", userConnect.getConfirmAccount());
				response.sendRedirect("http://localhost:8080/Breizhlink/connexion");
			}

		}else{
			session.setAttribute("user", null);
			session.setAttribute("confirm", null);
			session.setAttribute("errorConnect", "Login ou mot de passe incorrect !");
			response.sendRedirect("http://localhost:8080/Breizhlink/connexion");
		}
	}

}
