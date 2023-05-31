package be.cpasdeliege.intranet.informatique.controler.formulaire;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import be.cpasdeliege.intranet.informatique.model.PrivilegeInformatique;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class FormulaireServiceAjouter extends SimpleFormController {

	DomainInterface metier = null;

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object formulaire,
			BindException arg3) throws Exception {
		request.getSession().setAttribute("formulaireServiceAjouter", formulaire);
		/*
		 * ((FormulaireService)formulaire).setNom(new
		 * String(((FormulaireService)formulaire).getNom().getBytes(),"UTF-8"));
		 * ((FormulaireService)formulaire).setAdresse(new
		 * String(((FormulaireService)formulaire).getAdresse().getBytes(),"UTF-8"));
		 * ((FormulaireService)formulaire).setCodePostal(new
		 * String(((FormulaireService)formulaire).getCodePostal().getBytes(),"UTF-8"));
		 * ((FormulaireService)formulaire).setFax(new
		 * String(((FormulaireService)formulaire).getFax().getBytes(),"UTF-8"));
		 * ((FormulaireService)formulaire).setEmailService(new
		 * String(((FormulaireService)formulaire).getEmailService().getBytes(),"UTF-8"))
		 * ; ((FormulaireService)formulaire).setLocalisation(new
		 * String(((FormulaireService)formulaire).getLocalisation().getBytes(),"UTF-8"))
		 * ; ((FormulaireService)formulaire).setLocalite(new
		 * String(((FormulaireService)formulaire).getLocalite().getBytes(),"UTF-8"));
		 * ((FormulaireService)formulaire).setNumero(new
		 * String(((FormulaireService)formulaire).getNumero().getBytes(),"UTF-8"));
		 * ((FormulaireService)formulaire).setRemarque(new
		 * String(((FormulaireService)formulaire).getRemarque().getBytes(),"UTF-8"));
		 */
		return super.onSubmit(request, response, formulaire, arg3);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		FormulaireService formulaire = (FormulaireService) request.getSession()
				.getAttribute("formulaireServiceAjouter");
		// formulaire est null
		if (formulaire == null) {
			formulaire = new FormulaireService();
		}
		return formulaire;
	}

}
