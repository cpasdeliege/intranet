package be.cpasdeliege.intranet.informatique.controler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.dao.DaoPersonnel;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class GestionPersonnels implements Controller {

	DomainInterface metier = null;

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		HashMap modele = new HashMap();
		modele.put("statistiques", metier.getStatistiques());
		//List<DaoPersonnel> listEmployes=metier.getListePersonnel();
		//for(DaoPersonnel p:listEmployes) {
		//	p.setNom(new String(p.getNom().getBytes(),"UTF-8"));
		//	p.setPrenom(new String(p.getPrenom().getBytes(),"UTF-8"));
		//}
		modele.put("listeEmployes", metier.getListePersonnel());
		//modele.put("listeEmployes", listEmployes);
		arg0.getSession().setAttribute("retour", "gestionPersonnels.admin");
		return new ModelAndView("gestionPersonnels", modele);
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

	/* **************************************************** */
	public static String urlEncode(String value, String charset) throws UnsupportedEncodingException {
		return URLEncoder.encode(value, charset);
	}
	/* **************************************************** */

}
