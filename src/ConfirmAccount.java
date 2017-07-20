
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
 * Servlet implementation class ConfirmAccount
 */
@WebServlet({ "/confirmAccount", "/confirmAccount/", "/confirmAccount/*", "/confirmAccount/*/" })
public class ConfirmAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmAccount() {
        super();
    }

	/**
	 * Confirm account ou connexion
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String urlRequest = request.getPathInfo();
		if(urlRequest != null){

			urlRequest = urlRequest.replace("/", "");
			
			session.setAttribute("confirmAccount", null);
			if(!urlRequest.contains(".") && !urlRequest.contains("-")){
				
				// Check User session
				if(session.getAttribute("user") == null || session.getAttribute("user").equals("")){
					response.sendRedirect("http://localhost:8080/Breizhlink/connexion");
				}else{
					UserDaoImpl userDao = new UserDaoImpl();
					User userSend = (User) session.getAttribute("user");
					User userInfos = userDao.getUserById(userSend);
					
					if (userInfos != null && userInfos.getEmail() != null && userInfos.getConfirmAccount() != null){
						// Check confirm account
						if(urlRequest.equals(userInfos.getConfirmAccount())){
							userInfos.setActivated(1);
							userDao.activateUser(userInfos);
							session.setAttribute("confirmAccount", 1);
						}else{
							session.setAttribute("confirmAccount", null);
						}
						session.setAttribute("userInfos", userInfos);
					}else{
						session.setAttribute("userInfos", null);
						session.setAttribute("confirmAccount", null);
					}
					RequestDispatcher view = request.getRequestDispatcher("/views/confirmAccount.jsp");
			        view.forward(request, response);
				}
			}
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
