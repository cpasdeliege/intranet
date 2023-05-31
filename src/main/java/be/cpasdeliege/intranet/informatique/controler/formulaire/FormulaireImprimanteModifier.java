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

public class FormulaireImprimanteModifier extends SimpleFormController {

	DomainInterface metier = null;

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object formulaire,
			BindException exception) throws Exception {
		request.getSession().setAttribute("formulaireImprimanteModifier", formulaire);
		try {
//			metier.getLogger().log(new LogRecordIntranet(Level.INFO, ((Imprimante)formulaire).getNumeroSerie(), request));
			metier.modifierImprimante((Imprimante) formulaire);
			response.sendRedirect((String) request.getSession().getAttribute("retour") + "#listeImprimantes");
		} finally {
			request.getSession().setAttribute("formulaireImprimanteModifier", null);
		}
		return super.onSubmit(request, response, formulaire, exception);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		Imprimante formulaire = (Imprimante) request.getSession().getAttribute("formulaireImprimanteModifier");
		if (formulaire == null) {
			formulaire = metier.getImprimante(request.getParameter("numeroS"));
		}
		return formulaire;
	}

	@SuppressWarnings("unchecked")
	protected Map referenceData(HttpServletRequest arg0) throws Exception {
		Map data = new HashMap();
		data.put("listeOrdinateur", metier.getListeOrdinateurs());
		return data;
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
