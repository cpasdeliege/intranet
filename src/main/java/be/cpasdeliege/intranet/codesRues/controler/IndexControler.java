package be.cpasdeliege.intranet.codesRues.controler;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.codesRues.model.dao.DaoCodesRues;

public class IndexControler implements Controller {

	DaoCodesRues daoCodesRues = null;

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		session.setAttribute("retour", request.getRequestURL() + "?" + request.getQueryString());
		
		String param = request.getParameter("param");
		HashMap modele = new HashMap();
		if(param == null) {
			modele.put("listeRues", daoCodesRues.getListeRues());
			return new ModelAndView("index", modele);
		} else if(param.equals("addenda")) {
			modele.put("listeRues", daoCodesRues.getAddenda());
			return new ModelAndView("addenda", modele);
		} else {
			modele.put("listeRues", daoCodesRues.getListeRues(param));
			return new ModelAndView("index", modele);
		}
	}

	public DaoCodesRues getDaoCodesRues() {
		return daoCodesRues;
	}

	public void setDaoCodesRues(DaoCodesRues daoCodesRues) {
		this.daoCodesRues = daoCodesRues;
	}

}
