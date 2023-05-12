package be.cpasdeliege.intranet.DemServInfo.controler;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.DemServInfo.dao.DaoDemServInf;
import be.cpasdeliege.intranet.informatique.model.dao.DaoMySQL;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class ListeTypdeDemande implements Controller {

	DomainInterface metier = null;
	DaoDemServInf daoDsi = null;
	DaoMySQL dao = null;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String type = request.getParameter("typeDemande");
		if(type != null) {
			String valeur = request.getParameter("valeur");
			dao.updateTypeDemandeDSI(type, valeur);
		} 
		HashMap modele = new HashMap();
		modele.put("listeTypeDemande", dao.getTypeDemandeDSI());
		return new ModelAndView("listeTypeDemande", modele);
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

	public DaoDemServInf getDaoDsi() {
		return daoDsi;
	}

	public void setDaoDsi(DaoDemServInf daoDsi) {
		this.daoDsi = daoDsi;
	}

	public DaoMySQL getDao() {
		return dao;
	}

	public void setDao(DaoMySQL dao) {
		this.dao = dao;
	}

}
