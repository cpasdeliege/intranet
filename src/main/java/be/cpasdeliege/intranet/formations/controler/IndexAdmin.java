package be.cpasdeliege.intranet.formations.controler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.formations.model.Formation;
import be.cpasdeliege.intranet.formations.model.FormationService;
import be.cpasdeliege.intranet.formations.model.dao.DaoFormations;

public class IndexAdmin implements Controller {

	DaoFormations daoFormations = null;

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.getSession().setAttribute("retour", "indexAdmin.formations");
		HashMap modele = new HashMap();
		List<Formation> listeFormations = daoFormations.getFormations();
		List<String> listeService = new ArrayList<String>();
		for (Formation formation : listeFormations) {
			List<FormationService> listeFormationService = daoFormations.getServicesFormation(formation.getIdFormation());
			StringBuffer tmp = new StringBuffer();
			for (FormationService formationService : listeFormationService) {
				tmp.append(formationService.getService() + "\n");
			}
			listeService.add(tmp.toString());
		}
		modele.put("listeFormations", listeFormations);
		modele.put("listeServices", listeService);
		return new ModelAndView("indexAdmin", modele);
	}

	public DaoFormations getDaoFormations() {
		return daoFormations;
	}

	public void setDaoFormations(DaoFormations daoFormations) {
		this.daoFormations = daoFormations;
	}

}