

import beans.Url;
import dao.UrlDaoImpl;
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
 * Servlet implementation class Index
 */
@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("errorConnect", null);
        session.setAttribute("confirm", null);
        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.setAttribute("urlIndex", null);
		UrlDaoImpl urlDao = new UrlDaoImpl();
		String urlLong = request.getParameter("url");
		beans.Url urlToAdd = new Url();
        int lastId = urlDao.getLastId();
        Utils util = new Utils();
        String shortUrl = util.generateShortUrl(lastId);
		urlToAdd.setUrl_long(urlLong);
		urlToAdd.setUrl_short(shortUrl);
		
		// Check if password option in url submit
		if (request.getParameter("password") != null && !request.getParameter("password").isEmpty()){
			String password = request.getParameter("password");
			urlToAdd.setPassword(password);
			urlDao.addUrlWithPassword(urlToAdd);
		}else{
			urlDao.addUrl(urlToAdd);
		}
		session.setAttribute("urlIndex", urlToAdd);
		response.sendRedirect("http://localhost:8080/Breizhlink/");
	}

}
