import beans.User;
import dao.UserDaoImpl;
import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class SendConfirmEmail
 */
@WebServlet("/sendConfirmEmail")
public class SendConfirmEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendConfirmEmail() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// Check if user is connected
		if(session.getAttribute("user") == null || session.getAttribute("user").equals("")){
			response.sendRedirect("http://localhost:8080/Breizhlink/connexion");
		}else{
			
			// Ask to send again confirm mail
			UserDaoImpl userDao = new UserDaoImpl();
			Utils utils = new Utils();
			User userSend = (User) session.getAttribute("user");
			try {
				utils.sendMail(userSend.getEmail(), "Breizhlink : Confirmation de compte", "Bonjour, \nvotre adresse email a servi � la cr�ation sur Breizhlink. Pour confirmer ce compte, veuillez vous rendre � l'adresse suivante : http://localhost:8080/Breizhlink/confirmAccount/"+userSend.getConfirmAccount()+"\nCordialement, Breizhlink");
			}  catch (Exception e){
				e.printStackTrace();
			}
			response.sendRedirect("http://localhost:8080/Breizhlink/connexion");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
