import beans.Url;
import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;
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
import java.util.Date;

/**
 * Servlet implementation class Link
 */
@WebServlet({"/link", "/link/", "/link/*", "/link/*/"})
public class Link extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Link() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
        String urlRequest = request.getPathInfo();
        

        if (urlRequest != null) {
            urlRequest = urlRequest.replace("/", "");
            HttpSession session = request.getSession();
            session.setAttribute("urlRequest", urlRequest);

            if (!urlRequest.contains(".") && !urlRequest.contains("-")) {

                Url urlReq = new Url();
                urlReq.setUrl_short(urlRequest);

                UrlDaoImpl urlDao = new UrlDaoImpl();
                UrlClickDaoImpl urlClickDao = new UrlClickDaoImpl();
                Url urlReturn = urlDao.getUrlByShortUrl(urlReq);

                // Check if url exists
                if (urlReturn != null) {
                	if(session.getAttribute("submitLink") == null){
                		urlReturn.setUtilsation(urlReturn.getUtilsation() + 1);
                        urlClickDao.addUrlClick(urlReturn.getId());
                        urlDao.updateUrlUse(urlReturn);
                	}else{
                		session.setAttribute("submitLink", null);
                	}

                    if(!urlReturn.getUrl_long().equals(session.getAttribute("showUrl"))){
                    	session.setAttribute("showUrl", null);
                    }
                    session.setAttribute("url", urlReturn);
                    
                    session.setAttribute("errorUrl", null);
                    if(session.getAttribute("errorPost") != null){
                    	session.setAttribute("errorUrl", session.getAttribute("errorPost"));
                    	session.setAttribute("showUrl", null);
                    }
                    // Too use
                    if (urlReturn.getMax_use() > 0 && urlReturn.getMax_use() < urlReturn.getUtilsation()) {
                        session.setAttribute("url", null);
                        session.removeAttribute("url");
                        session.setAttribute("showUrl", null);
                    }


                } else {
                    session.setAttribute("url", null);
                    session.removeAttribute("url");
                    session.setAttribute("showUrl", null);
                }

            } else {
                //
            }
        }
        RequestDispatcher view = request.getRequestDispatcher("/views/link.jsp");
        view.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("submitLink", true);
        session.setAttribute("showUrl", null);

        String urlReq = request.getPathInfo();
        if (urlReq != null) {
            urlReq = urlReq.replace("/", "");

            session.setAttribute("urlRequest", urlReq);

            if (!urlReq.contains(".") && !urlReq.contains("-")) {

                Url urlSend = new Url();
                urlSend.setUrl_short(urlReq);

                UrlDaoImpl urlDao = new UrlDaoImpl();
                Url urlRequest = urlDao.getUrlByShortUrl(urlSend);
                String errorPost = "";
                // Check if url exists
                if (urlRequest != null) {

                	// Check url options
                	
                    urlRequest.setShowUrl(1);
                    if (urlRequest.getEmail() != null) {

                        String email = request.getParameter("email");
                        
                        if (!urlRequest.getEmail().equals(email)) {
                            urlRequest.setShowUrl(0);
                            errorPost += "Mauvais email !\n";
                            
                        }
                    }
                    
                    if (urlRequest.getMax_use() > 0 && urlRequest.getMax_use() < urlRequest.getUtilsation()) {
                        urlRequest.setShowUrl(0);
                        errorPost +=  "Limite d'utilisation atteinte !\n";
                    }
                    
                    if (urlRequest.getDate_expiration() != null){
                        Date today = new Date();
                        if(urlRequest.getPeriod_start() != null){
                            if (today.before(urlRequest.getPeriod_start())){
                                urlRequest.setShowUrl(0);
                                
                                errorPost += "La période de disponibilité est plus tard. Revenez ultérieument\n";
                            }
                        }
                        if (today.after(urlRequest.getDate_expiration())){
                            urlRequest.setShowUrl(0);
                            
                            errorPost += "La période de disponibilité est passée.\n";
                        }
                    }
                    
                    if (urlRequest.getPassword() != null){
                        String password = request.getParameter("password");
                        System.out.println("password "+password);
                        if (!urlRequest.getPassword().equals(password)) {
                            urlRequest.setShowUrl(0);
                            errorPost +=  "Mauvais mot de passe !\n";
                        }
                    }
                    
                    if (urlRequest.getCaptcha() == 1){
                        String userCaptchaResponse = request.getParameter("jcaptcha");
                        
                        boolean captchaPassed = SimpleImageCaptchaServlet.validateResponse(request, userCaptchaResponse);
                        if (!captchaPassed) {
                            urlRequest.setShowUrl(0);
                            errorPost += "Mauvais captcha !\n";
                        }
                    }
                    
                    // Check if options submit is valid
                    
                    if (urlRequest.getShowUrl() == 1){
                        System.out.println("url post  win");
                        session.setAttribute("showUrl", urlRequest.getUrl_long());
                        session.setAttribute("errorPost", null);
                    }
                    
                    if(!"".equals(errorPost)){
                    	session.setAttribute("errorPost", errorPost);
                    }else{
                    	session.setAttribute("errorPost", null);
                    }
                    


                } else {
                    session.setAttribute("url", null);
                    session.removeAttribute("url");
                }

            } else {
                //
            }
        }

            response.sendRedirect("http://localhost:8080"+request.getRequestURI());
       
    }
    

}
