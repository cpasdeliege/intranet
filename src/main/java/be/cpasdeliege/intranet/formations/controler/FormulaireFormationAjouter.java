package be.cpasdeliege.intranet.formations.controler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import be.cpasdeliege.intranet.formations.model.Formation;
import be.cpasdeliege.intranet.formations.model.dao.DaoFormations;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class FormulaireFormationAjouter extends SimpleFormController {

	DomainInterface metier = null;
	DaoFormations daoFormations = null;

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		daoFormations.addFormation((Formation) command);
		response.sendRedirect((String) request.getSession().getAttribute("retour"));
		return super.onSubmit(request, response, command, errors);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		Object formulaire = request.getSession().getAttribute("formulaireFormationAjouter");
		if (formulaire == null) {
			formulaire = new Formation();
		}
		return formulaire;
	}

	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {

		Map data = new HashMap();
		// data.put("listeServices", metier.getService("%"));
		data.put("listeTypeFormation", daoFormations.getTypeFormation());
		return data;
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
