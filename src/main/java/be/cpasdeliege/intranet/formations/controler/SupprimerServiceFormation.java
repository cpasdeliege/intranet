package be.cpasdeliege.intranet.formations.controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.formations.model.dao.DaoFormations;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class SupprimerServiceFormation implements Controller {

	DomainInterface metier = null;
	DaoFormations daoFormations = null;

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			daoFormations.supprimerFormationService(request.getParameter("idFormation"),
					request.getParameter("service"));
		} catch (Exception e) {
		}
		response.sendRedirect((String) request.getSession().getAttribute("retour") + "#listeService");
		return null;
	}

	public DaoFormations getDaoFormations() {
		return daoFormations;
	}

	public void setDaoFormations(DaoFormations daoFormations) {
		this.daoFormations = daoFormations;
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}
}
