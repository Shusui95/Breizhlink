

import beans.Url;
import dao.UrlClickDaoImpl;
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
 * Servlet implementation class Stats
 */
@WebServlet({ "/stats", "/stats/", "/stats/*", "/stats/*/" })
public class Stats extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Stats() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String urlRequest = request.getPathInfo();
		
		if(urlRequest != null){
			urlRequest = urlRequest.replace("/", "");
			HttpSession session = request.getSession();
			session.setAttribute("urlRequest", urlRequest);
			
			if(!urlRequest.contains(".") && !urlRequest.contains("-")){
				
				Url urlReq = new Url();
				urlReq.setUrl_short(urlRequest);
				UrlDaoImpl urlDao = new UrlDaoImpl();
				UrlClickDaoImpl urlClickDao = new UrlClickDaoImpl();
				Url urlReturn = urlDao.getUrlByShortUrl(urlReq);

				if(urlReturn != null){

					if(urlReturn.getUser_id() != 0){
						if(session.getAttribute("user") == null || session.getAttribute("user").equals("")){
							session.setAttribute("url", null);
							session.setAttribute("errorUrl", "Veuillez vous connecter pour observer cet stat.");
						}else{
							ArrayList array = urlClickDao.getUrlClickByUrlId(urlReturn.getId());
							
							session.setAttribute("url", urlReturn);
							session.setAttribute("errorUrl", null);
						}
					}else{
						session.setAttribute("url", urlReturn);
						session.setAttribute("errorUrl", null);
					}
					
					
					
					
					
				}else{
					session.setAttribute("url", null);
					session.removeAttribute("url");
				}
			}else{
				//
			}
		}
		RequestDispatcher view = request.getRequestDispatcher("/views/stats.jsp");      
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
