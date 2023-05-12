package be.cpasdeliege.intranet.informatique.controler.formulaire;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class FormulairePersonnelOrdinateurAssignerControler extends SimpleFormController {

	DomainInterface metier = null;

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object formulaire,
			BindException arg3) throws Exception {
		// request.getSession().setAttribute("formulairePersonnelAssigner", formulaire);
//		metier.getLogger().log(new LogRecordIntranet(Level.INFO, ((FormulaireOrdinateurPersonnelAssigner)formulaire).getEmploye() + ", " + ((FormulaireOrdinateurPersonnelAssigner)formulaire).getOrdinateur(), request));
		metier.assignerOrdinateurPersonnel((FormulaireOrdinateurPersonnelAssigner) formulaire);
		request.getSession().setAttribute("formulairePersonnelOrdinateurAssigner", null);
		response.sendRedirect((String) request.getSession().getAttribute("retour") + "#listeOrdinateurs");
		return super.onSubmit(request, response, formulaire, arg3);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		Object formulaire = request.getSession().getAttribute("formulairePersonnelOrdinateurAssigner");
		if (formulaire == null) {
			formulaire = new FormulaireOrdinateurPersonnelAssigner();
		}
		return formulaire;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map data = new HashMap();
		data.put("personnel", metier.getPersonnel(request.getParameter("nom"), request.getParameter("prenom")));
		data.put("listeOrdinateur", metier.getListeOrdinateurPersonnelAssigner(request.getParameter("nom"),
				request.getParameter("prenom")));
		return data;
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}
}
