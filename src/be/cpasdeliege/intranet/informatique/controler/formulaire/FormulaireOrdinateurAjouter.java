package be.cpasdeliege.intranet.informatique.controler.formulaire;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import be.cpasdeliege.intranet.informatique.model.TypeOS;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class FormulaireOrdinateurAjouter extends SimpleFormController {

	DomainInterface metier = null;

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object formulaire,
			BindException arg3) throws Exception {
		request.getSession().setAttribute("formulaireOrdinateurAjouter", formulaire);
		return super.onSubmit(request, response, formulaire, arg3);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		FormulaireOrdinateur formulaire = (FormulaireOrdinateur) request.getSession()
				.getAttribute("formulaireOrdinateurAjouter");
		if (formulaire == null) {
			formulaire = new FormulaireOrdinateur();
			if (request.getParameter("serviceDefault") != null) {
				formulaire.setService(request.getParameter("serviceDefault"));
			}
			List listeSystemeExploitation = metier.getTypeOS();
			int i = 0;
			while (((TypeOS) listeSystemeExploitation.get(i)).getDef().equals("0")) {
				i++;
			}
			formulaire.setSystemeExploitation(((TypeOS) listeSystemeExploitation.get(i)).getNom());
		}
		return formulaire;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Map referenceData(HttpServletRequest arg0) throws Exception {
		Map data = new HashMap();
		data.put("listeService", metier.getListeServices());
		data.put("typeOrdinateur", metier.getTypeOrdinateur());
		data.put("typeOS", metier.getTypeOS());
		data.put("typeEcran", metier.getTypeEcran());
		data.put("typeGraveur", metier.getTypeGraveur());
		return data;
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
