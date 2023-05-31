package be.cpasdeliege.intranet.informatique.controler.formulaire;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class FormulaireOrdinateurPersonnelAssignerControler extends SimpleFormController {

	DomainInterface metier = null;

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object formulaire,
			BindException arg3) throws Exception {
//		metier.getLogger().log(new LogRecordIntranet(Level.INFO, ((FormulaireOrdinateurPersonnelAssigner)formulaire).getOrdinateur() + ", " + ((FormulaireOrdinateurPersonnelAssigner)formulaire).getEmploye(), request));
		metier.assignerOrdinateurPersonnel((FormulaireOrdinateurPersonnelAssigner) formulaire);
		request.getSession().setAttribute("formulaireOrdinateurPersonnelAssigner", null);
		response.sendRedirect((String) request.getSession().getAttribute("retour") + "#listePersonnel");
		return super.onSubmit(request, response, formulaire, arg3);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		Object formulaire = request.getSession().getAttribute("formulaireOrdinateurPersonnelAssigner");
		if (formulaire == null) {
			formulaire = new FormulaireOrdinateurPersonnelAssigner();
		}
		return formulaire;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map data = new HashMap();
		data.put("ordi", metier.getOrdinateur(request.getParameter("ordinateur")));
		data.put("listePersonnel", metier.getListePersonnelOrdinateurAssigner(request.getParameter("ordinateur")));
		return data;
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
