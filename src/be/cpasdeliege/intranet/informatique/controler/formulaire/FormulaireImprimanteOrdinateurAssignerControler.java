package be.cpasdeliege.intranet.informatique.controler.formulaire;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class FormulaireImprimanteOrdinateurAssignerControler extends SimpleFormController {
	DomainInterface metier = null;

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object formulaire,
			BindException arg3) throws Exception {
//		metier.getLogger().log(new LogRecordIntranet(Level.INFO, ((FormulaireImprimanteOrdinateurAssigner)formulaire).getOrdinateur() + ", " + ((FormulaireImprimanteOrdinateurAssigner)formulaire).getNumeroSerie(), request));
		metier.assignerImprimante((FormulaireImprimanteOrdinateurAssigner) formulaire);
		request.getSession().setAttribute("FormulaireImprimanteOrdinateurAssigner", null);
		response.sendRedirect((String) request.getSession().getAttribute("retour") + "#listeOrdinateur");
		return super.onSubmit(request, response, formulaire, arg3);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		Object formulaire = request.getSession().getAttribute("FormulaireImprimanteOrdinateurAssigner");
		if (formulaire == null) {
			formulaire = new FormulaireImprimanteOrdinateurAssigner();
		}
		return formulaire;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map data = new HashMap();
		data.put("listeOrdinateur", metier.getListeOrdinateursImprimlanteAssigner(request.getParameter("numeroS")));
		data.put("imprimante", metier.getImprimante(request.getParameter("numeroS")));
		return data;
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}
}
