package be.cpasdeliege.intranet.controler;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.WifiVisiteurs;
import be.cpasdeliege.intranet.informatique.model.dao.DaoMySQL;

public class Index implements Controller {
	DaoMySQL dao = null;

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String rep = request.getParameter("rep");
		String action = request.getParameter("action");
		
		if(rep == null) {
			if(action == null) {
				
			} else if(action.equals("modifierSSID")) {
				WifiVisiteurs wv = dao.getWifiVisiteurs();
				wv.setSsid(request.getParameter("SSID"));
				dao.updateWifiVisiteurs(wv);
			} else if(action.equals("modifierMDP")) {
				WifiVisiteurs wv = dao.getWifiVisiteurs();
				wv.setMdp(request.getParameter("MDP"));
				dao.updateWifiVisiteurs(wv);
			}
			request.getSession().setAttribute("retour", "index.htm");
			HashMap modele = new HashMap();
			modele.put("wifiVisiteurs", dao.getWifiVisiteurs());
			return new ModelAndView("index", modele);
		} else if(rep.equals("personnelCPAS")) {
			request.getSession().setAttribute("retour", "index.htm");
			HashMap modele = new HashMap();
			return new ModelAndView("indexPersonnelCpas", modele);
		} else if(rep.equals("rtcite")){
			request.getSession().setAttribute("retour", "index.htm");
			HashMap modele = new HashMap();
			return new ModelAndView("IndexNouveauRT", modele);
		}
		else {
			request.getSession().setAttribute("retour", "index.htm");
			HashMap modele = new HashMap();
			return new ModelAndView("index", modele);
		}
	}

	public DaoMySQL getDao() {
		return dao;
	}

	public void setDao(DaoMySQL dao) {
		this.dao = dao;
	}
}