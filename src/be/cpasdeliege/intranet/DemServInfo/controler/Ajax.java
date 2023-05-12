package be.cpasdeliege.intranet.DemServInfo.controler;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.DemServInfo.dao.DaoDemServInf;
import be.cpasdeliege.intranet.informatique.model.dao.DaoMySQL;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class Ajax implements Controller {

	DomainInterface metier = null;
	DaoDemServInf daoDsi = null;
	DaoMySQL dao = null;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		if(action ==null) {
			String choix1 = request.getParameter("choix1");
			String choix2 = request.getParameter("choix2");
			if(choix2 == null) {
				HashMap modele = new HashMap();
				List tmp = dao.getTypeChoix2DemandeDSI(choix1);
				modele.put("listeTypeChoix2", tmp);
				return new ModelAndView("ajax/listeChoix2", modele);
			} else {
				HashMap modele = new HashMap();
				List tmp = dao.getTypeChoix3DemandeDSI(choix2);
//				if(tmp.size() == 1 && tmp.get(0).equals("")) {
//					return new ModelAndView("ajax/vide", modele);
//				} else {
//					modele.put("listeTypeChoix3", tmp);
//					return new ModelAndView("ajax/listeChoix3", modele);
//				}
				modele.put("listeTypeChoix3", tmp);
				return new ModelAndView("ajax/listeChoix3", modele);
			}
		} else {
			HashMap modele = new HashMap();
			return new ModelAndView("ajax/vide", modele);
		}

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
