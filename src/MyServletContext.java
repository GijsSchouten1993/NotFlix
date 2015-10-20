import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import model.Model;

@WebListener
public class MyServletContext implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		Model mod = (Model) arg0.getServletContext().getAttribute("model");
		String text = mod.GetTokenTextForFile();
		try {
			String relativeWebPath = "/WEB-INF/tokens.txt";
			String absoluteDiskPath = arg0.getServletContext().getRealPath(relativeWebPath);
			File file = new File(absoluteDiskPath);
			
			FileWriter fw;
			
			fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(text);
			bw.close();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		// Haal de tekst uit het token bestand op
		String filename = "/WEB-INF/tokens.txt";

		ServletContext context = arg0.getServletContext();
		String text = "";
		InputStream is = context.getResourceAsStream(filename);
		if (is != null) {
			
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(isr);
			try {
				StringBuilder everything = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					everything.append(line + "/n");
				}
				text = everything.toString();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Model model = new Model();
		model.is =is; 
		model.SetTokenFromFile(text);
		arg0.getServletContext().setAttribute("model", model);
	}

}