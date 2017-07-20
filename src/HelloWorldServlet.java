import beans.*;
import beans.Url;
import dao.UrlClickDaoImpl;
import dao.UrlDaoImpl;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Generate dynamic chart
 */
@WebServlet({ "/helloworld", "/helloworld/", "/helloworld/*", "/helloworld/*/" })
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorldServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/png");
		
		OutputStream outputStream = response.getOutputStream();

		String urlReq = request.getPathInfo();
		if (urlReq != null) {
			urlReq = urlReq.replace("/", "");
			Url url = new Url();
			url.setUrl_short(urlReq);
			JFreeChart chart = getChart( url);

			int width = 800;
			int height = 560;
			ChartUtilities.writeChartAsPNG(outputStream, chart, width, height);
		}
	}
	
	public JFreeChart getChart(beans.Url url) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String categoryAxisLabel = "Utilisation en fonction du temps";
	  
		String series1 = "Utilisation";

		UrlClickDaoImpl urlClickDao = new UrlClickDaoImpl();
		UrlDaoImpl urlDao = new UrlDaoImpl();
		url = urlDao.getUrlByShortUrl(url);
		System.out.println(url.toString());
		ArrayList<Map<String,String>> urlClick = urlClickDao.getUrlClickByUrlId(url.getId());
		ArrayList year = new ArrayList();
		ArrayList month = new ArrayList();
		ArrayList utilisation = new ArrayList();
		for(int i = 0; i < urlClick.size(); i++){
			
			for (Map.Entry<String, String> entry : urlClick.get(i).entrySet())
	        {
	            String key = entry.getKey();
	            String value = entry.getValue();
	            String date = "";
	            if(key.equals("month")){
	            	month.add(value);
	            }
	            if(key.equals("year")){
	            	year.add(value);
	            }
	            if(key.equals("count")){
	            	utilisation.add(value);
	            }
	        } 
		}
		for(int j = 0; j < utilisation.size(); j++){
			dataset.addValue(Integer.parseInt(utilisation.get(j).toString()), series1, month.get(j)+"/"+year.get(j));
		}
		JFreeChart chart = ChartFactory.createLineChart(categoryAxisLabel, "Mois", "Total utilisation", dataset);

		chart.setBorderPaint(Color.GREEN);
		chart.setBorderStroke(new BasicStroke(5.0f));
		chart.setBorderVisible(true);

		return chart;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
