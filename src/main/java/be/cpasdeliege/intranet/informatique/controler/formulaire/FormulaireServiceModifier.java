package be.cpasdeliege.intranet.informatique.controler.formulaire;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class FormulaireServiceModifier extends SimpleFormController {

	DomainInterface metier = null;

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object formulaire,
			BindException arg3) throws Exception {
		metier.modifierService((FormulaireService) formulaire);
		request.getSession().setAttribute("formulaireServiceModifier", null);
		response.sendRedirect((String) request.getSession().getAttribute("retour"));
		return super.onSubmit(request, response, formulaire, arg3);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		Object formulaire = request.getSession().getAttribute("formulaireServiceModifier");
		if (formulaire == null || request.getParameter("service") != null) {
			// formulaire = metier.getFormulaireService(new
			// String(request.getParameter("service").getBytes(),"UTF-8"));
			formulaire = metier.getFormulaireService(request.getParameter("service"));
		}
		return formulaire;
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
