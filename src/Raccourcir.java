

import beans.Url;
import beans.User;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class Raccourcir
 */
@WebServlet("/raccourcir")
public class Raccourcir extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Raccourcir() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null || session.getAttribute("user").equals("")) {
            response.sendRedirect("http://localhost:8080/Breizhlink/connexion");
        } else {
            RequestDispatcher view = request.getRequestDispatcher("/views/raccourcir.jsp");
            view.forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Url urlSave = new Url();
        User user = (User) session.getAttribute("user");
        session.setAttribute("urlSave", null);
        // Check if user is connected
        if (user != null && user.getId() > 0 && user.getEmail() != null) {
            urlSave.setUser_id(user.getId());

            // Check options submit
            String urlLong = request.getParameter("url");
            urlSave.setUrl_long(urlLong);
            String captcha = request.getParameter("captchaInput");

            if ("on".equals(captcha)) {
                urlSave.setCaptcha(1);
            }

            String email = request.getParameter("email");
            if ("on".equals(email)) {
                urlSave.setEmail(user.getEmail());
            }
            String checkboxPassword = request.getParameter("checkboxPassword");
            String password = request.getParameter("password");
            if ("on".equals(checkboxPassword) && password != null) {
                urlSave.setPassword(password);
            }

            String checkboxMaxUse = request.getParameter("checkboxMaxUse");
            String numberString = request.getParameter("numberInput");
            if (!"".equals(numberString) && numberString != null) {
                int numberInput = Integer.parseInt(numberString);
                if ("on".equals(checkboxMaxUse)) {
                    urlSave.setMax_use(numberInput);
                }
            }

            String radioChoice = request.getParameter("radioChoice");
            if (radioChoice != null) {
                if (radioChoice.equals("period")) {
                    String startInput = request.getParameter("startInput");
                    String endInput = request.getParameter("endInput");
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date start = formatter.parse(startInput);
                        Date end = formatter.parse(endInput);
                        urlSave.setPeriod_start(start);
                        urlSave.setDate_expiration(end);
                        java.sql.Date startSql = new java.sql.Date(urlSave.getPeriod_start().getTime());
                        urlSave.setPeriod_start(startSql);
                        java.sql.Date endSql = new java.sql.Date(urlSave.getDate_expiration().getTime());
                        urlSave.setDate_expiration(endSql);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (radioChoice.equals("dateLimit")) {
                    String validityInput = request.getParameter("validityInput");
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    try {

                        Date end = formatter.parse(validityInput);

                        urlSave.setDate_expiration(end);
                        java.sql.Date date_expiration = new java.sql.Date(urlSave.getDate_expiration().getTime());
                        urlSave.setDate_expiration(date_expiration);
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }

            UrlDaoImpl urlDao = new UrlDaoImpl();
            Utils utils = new Utils();

            String shortUrl = utils.generateShortUrl(urlDao.getLastId());
            urlSave.setUrl_short(shortUrl);
            urlDao.addUrlRac(urlSave);
            session.setAttribute("urlSave", urlSave);

        } else {
            session.setAttribute("user", null);
            session.setAttribute("urlSave", null);
            response.sendRedirect("http://localhost:8080/Breizhlink/connexion");
        }
        doGet(request, response);
    }

}
