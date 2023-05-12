package be.cpasdeliege.intranet.informatique.controler;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.PrivilegeInformatique;
import be.cpasdeliege.intranet.informatique.model.dao.DaoMySQL;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class Index_derlee implements Controller {

	DomainInterface metier = null;
	DaoMySQL dao = null;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.getSession().setAttribute("retour", "index.admin");
		HashMap modele = new HashMap();
		modele.put("statistiques", metier.getStatistiques());
		if(((PrivilegeInformatique)request.getSession().getAttribute("privilegeInformatique")).isAdministrateur()) {
			return new ModelAndView("index", modele);
		} else {
			return new ModelAndView("telephonie/index", modele);
		}
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
