package be.cpasdeliege.intranet.informatique.controler;

import java.io.ByteArrayOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.Driver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.Config;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class GenererPDF implements Controller {

	DomainInterface metier = null;
	Config config = null;

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String type = request.getParameter("type");
		String saut = request.getParameter("saut");
		if (type == null || saut == null) {
			response.sendRedirect((String) request.getSession().getAttribute("retour"));
		} else {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Driver driver = new Driver();
			driver.setRenderer(Driver.RENDER_PDF);
			driver.setOutputStream(out);
			Result resultat = new SAXResult(driver.getContentHandler());
			Source source;
			Source style;
			if (type.equals("alpha")) {
				if (saut.equals("true")) {
					source = new DOMSource(metier.getAnnuaireAlphabetiqueXML());
//					style = new StreamSource("c:\\fop\\cpas_alpha_saut.xsl");
					style = new StreamSource(config.getCpasAlphaSautXsl());
				} else {
					source = new DOMSource(metier.getAnnuaireAlphabetiqueXML());
//					style = new StreamSource("c:\\fop\\cpas_alpha.xsl");
					style = new StreamSource(config.getCpasAlphaXsl());
				}
			} else {
				if (saut.equals("true")) {
					source = new DOMSource(metier.getAnnuaireServiceXML());
//					style = new StreamSource("c:\\fop\\cpas_service_saut.xsl");
					style = new StreamSource(config.getCpasServiceSautXsl());
				} else {
					source = new DOMSource(metier.getAnnuaireServiceXML());
//					style = new StreamSource("c:\\fop\\cpas_service.xsl");
					style = new StreamSource(config.getCpasServiceXsl());
				}
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer(style);
			transformer.transform(source, resultat);
//			
//			FileOutputStream fw = new FileOutputStream(new File("c:\\prouot.pdf"));
//			fw.write(out.toByteArray());
//			fw.close();

			response.setContentType("application/pdf");
			response.setContentLength(out.size());
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(out.toByteArray());
			outputStream.flush();
//		    response.getWriter().write(out.toString(), 0, out.size());
//		    response.getWriter().flush();
		}
		return null;
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

}
