import beans.User;
import dao.UserDaoImpl;
import utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class CreateAccount
 */
@WebServlet("/creer-compte")
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccount() {
        super();
    }

	/**
	 * Create account form
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("errorConnect", null);
		session.setAttribute("confirm", null);
		RequestDispatcher view = request.getRequestDispatcher("/views/createAccount.jsp");      
        view.forward(request, response);
	}

	/**
	 * Create account submit
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserDaoImpl userDao = new UserDaoImpl();
		String emailPost = request.getParameter("email");
		
		// CHeck if email is valid
		if (userDao.validEmail(emailPost)){
			session.setAttribute("emailNotValid", null);
			session.setAttribute("emailCreate", emailPost);
			User userAdd = new User();
			userAdd.setLogin(request.getParameter("login"));
			userAdd.setEmail(emailPost);
			userAdd.setStatut(request.getParameter("status"));
			userAdd.setPassword(request.getParameter("password"));
			Utils utils = new Utils();
			String confirm = utils.generateShortUrl(userDao.getLastId());
			userAdd.setConfirmAccount(confirm);
			userDao.addUser(userAdd);

			// Send mail confirm account
			try {
				utils.sendMail(userAdd.getEmail(), "Breizhlink : Confirmation de compte", "Bonjour, \nvotre adresse email a servi � la cr�ation sur Breizhlink. Pour confirmer ce compte, veuillez vous rendre � l'adresse suivante : http://localhost:8080/Breizhlink/confirmAccount/"+userAdd.getConfirmAccount()+"\nCordialement, Breizhlink");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			session.setAttribute("emailCreate", null);
			session.setAttribute("emailNotValid", "L'adresse email "+emailPost+" n'est pas un email valide.");
		}
		response.sendRedirect("http://localhost:8080/Breizhlink/creer-compte");
	}

}
