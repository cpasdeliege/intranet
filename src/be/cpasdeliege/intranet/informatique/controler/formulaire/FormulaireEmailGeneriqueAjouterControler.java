package be.cpasdeliege.intranet.informatique.controler.formulaire;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import be.cpasdeliege.intranet.informatique.model.EmailGenerique;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class FormulaireEmailGeneriqueAjouterControler extends SimpleFormController {
	DomainInterface metier = null;

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object formulaire,
			BindException exception) throws Exception {
		request.getSession().setAttribute("formulaireEmailGeneriqueAjouter", formulaire);
		if (testFormulaire(request, formulaire)) {
			metier.addEmailGenerique((EmailGenerique) formulaire);
			request.getSession().setAttribute("formulaireEmailGeneriqueAjouter", null);
			response.sendRedirect((String) request.getSession().getAttribute("retour"));
		} else {
			response.sendRedirect("formulaireEmailGeneriqueAjouter.admin");
		}
		return super.onSubmit(request, response, formulaire, exception);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		EmailGenerique email = (EmailGenerique) request.getSession().getAttribute("formulaireEmailGeneriqueAjouter");
		if (email == null) {
			email = new EmailGenerique();
		}
		return email;
	}

	@SuppressWarnings("unchecked")
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map data = new HashMap();

		return data;
	}

	public DomainInterface getMetier() {
		return metier;
	}

	private boolean testFormulaire(HttpServletRequest request, Object formulaire) {
		EmailGenerique email = (EmailGenerique) formulaire;
		if (email.getEmail().trim().equals("")) {
			request.getSession().setAttribute("erreurFormulaireTacheAjouter", "le champs email est vide !");
			return false;
		} else if (email.getMdp().trim().equals("")) {
			request.getSession().setAttribute("erreurFormulaireTacheAjouter", "le champs mot de passe est vide !");
			return false;
		} else {
			request.getSession().setAttribute("erreurFormulaireTacheAjouter", null);
			return true;
		}
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}
}
