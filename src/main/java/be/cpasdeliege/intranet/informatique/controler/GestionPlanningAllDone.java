package be.cpasdeliege.intranet.informatique.controler;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//import sun.nio.cs.StandardCharsets;

//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import be.cpasdeliege.intranet.informatique.model.TacheListe;
import be.cpasdeliege.intranet.informatique.model.Utilisateur;
import be.cpasdeliege.intranet.informatique.model.dao.DaoMySQL;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;
import be.cpasdeliege.intranet.utils.Utils;

public class GestionPlanningAllDone implements Controller {

	DomainInterface metier = null;
	DaoMySQL dao = null;

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		String action = arg0.getParameter("action");

		HashMap modele = new HashMap();
		
		String triEnFini = arg0.getParameter("triEnFini");
		
		if(triEnFini == null) {
			triEnFini = "dateFin";
		}
		
		modele.put("listeTachesFinies", metier.getListeTacheFinie("%", "%", "%", "%", triEnFini));
		
		arg0.getSession().setAttribute("retour", "gestionPlanningAllDone.admin");
		
		return new ModelAndView("gestionPlanningAllDone", modele);
		
		
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

	public DaoMySQL getDao() {
		return dao;
	}

	public void setDao(DaoMySQL dao) {
		this.dao = dao;
	}

}
