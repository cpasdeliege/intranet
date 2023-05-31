package be.cpasdeliege.intranet.informatique.controler.formulaire;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import be.cpasdeliege.intranet.informatique.model.dao.DaoPersonnelService;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class FormulairePersonnelServiceModifierControler extends SimpleFormController {
	DomainInterface metier = null;

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object formulaire,
			BindException arg3) throws Exception {
		// request.getSession().setAttribute("formulairePersonnelAssigner", formulaire);
//		metier.getLogger().log(new LogRecordIntranet(Level.INFO, ((FormulaireServicePersonnelAssigner)formulaire).getService() + ", " + ((FormulaireServicePersonnelAssigner)formulaire).getEmploye(), request));
		metier.modifierPersonnelService((FormulaireServicePersonnelAssigner) formulaire);
		request.getSession().setAttribute("formulairePersonnelServiceModifier", null);
		response.sendRedirect((String) request.getSession().getAttribute("retour") + "#listePersonnel");
		return super.onSubmit(request, response, formulaire, arg3);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		FormulaireServicePersonnelAssigner formulaire = new FormulaireServicePersonnelAssigner();
		List personnel = metier.getListePersonnelService(request.getParameter("service"), request.getParameter("nom"),
				request.getParameter("prenom"));
		if (personnel.size() == 1) {
			DaoPersonnelService pers = (DaoPersonnelService) personnel.get(0);
			formulaire.setEmploye(pers.getNom() + ", " + pers.getPrenom());
			formulaire.setFonction(pers.getFonction());
			formulaire.setService(pers.getService());
			formulaire.setTelephone(pers.getTelephone());
			formulaire.setRem(pers.getRem());
			formulaire.setExtension(pers.getExtension());
		}
		return formulaire;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map data = new HashMap();
		data.put("nom", request.getParameter("nom"));
		data.put("prenom", request.getParameter("prenom"));
		data.put("ser", metier.getService(request.getParameter("service")));
		data.put("typeFonction", metier.getTypeFonction());
		return data;
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
