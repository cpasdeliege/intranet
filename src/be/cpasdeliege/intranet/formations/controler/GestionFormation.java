package be.cpasdeliege.intranet.formations.controler;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.formations.model.dao.DaoFormations;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class GestionFormation implements Controller {

	DomainInterface metier = null;
	DaoFormations daoFormations = null;

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.getSession().setAttribute("retour", "gestionFormation.formations?idFormation=" + request.getParameter("idFormation"));
		HashMap modele = new HashMap();
		modele.put("formation", daoFormations.getFormation(request.getParameter("idFormation")));
		modele.put("listeServices", metier.getListeServices());
		modele.put("listeServicesFormation", daoFormations.getServicesFormation(request.getParameter("idFormation")));
		return new ModelAndView("gestionFormation", modele);
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

	public DaoFormations getDaoFormations() {
		return daoFormations;
	}

	public void setDaoFormations(DaoFormations daoFormations) {
		this.daoFormations = daoFormations;
	}

}
