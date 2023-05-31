package be.cpasdeliege.intranet.informatique.controler.formulaire;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import be.cpasdeliege.intranet.informatique.model.Imprimante;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class FormulaireImprimanteAjouter extends SimpleFormController {

	DomainInterface metier = null;

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object formulaire,
			BindException exception) throws Exception {
		request.getSession().setAttribute("formulaireImprimanteAjouter", formulaire);
		return super.onSubmit(request, response, formulaire, exception);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		Imprimante formulaire = (Imprimante) request.getSession().getAttribute("formulaireImprimanteAjouter");
		if (formulaire == null) {
			formulaire = new Imprimante();
			if (request.getParameter("ordinateurDefault") != null) {
				formulaire.setOrdinateurLocal(request.getParameter("ordinateurDefault"));
			}
		}
		return formulaire;
	}

	@SuppressWarnings("unchecked")
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map data = new HashMap();
		// data.put("listeService", metier.getListeServices());
		if (request.getParameter("serviceDefault") != null) {
			data.put("listeOrdinateur", metier.getListeOrdinateurService(request.getParameter("serviceDefault")));
		} else {
			data.put("listeOrdinateur", metier.getListeOrdinateurs());
		}
		data.put("typeImprimante", metier.getTypeImprimante());
		return data;
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
