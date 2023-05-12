package be.cpasdeliege.intranet.informatique.controler.formulaire;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class FormulairePersonnelAjouter extends SimpleFormController {

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object formulaire,
			BindException arg3) throws Exception {
		// request.setCharacterEncoding("UTF-8");
		request.getSession().setAttribute("formulairePersonnelAjouter", formulaire);
		/*
		 * ((FormulairePersonnel)formulaire).setNom(new
		 * String(((FormulairePersonnel)formulaire).getNom().getBytes(),"UTF-8"));
		 * ((FormulairePersonnel)formulaire).setPrenom(new
		 * String(((FormulairePersonnel)formulaire).getPrenom().getBytes(),"UTF-8"));
		 */
		System.out.println("submit ajout formulaire nom=" + ((FormulairePersonnel) formulaire).getNom());
		System.out.println("submit ajout formulaire pr�nom=" + ((FormulairePersonnel) formulaire).getPrenom());
		return super.onSubmit(request, response, formulaire, arg3);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		Object formulaire = request.getSession().getAttribute("formulairePersonnelAjouter");
		if (formulaire == null) {
			formulaire = new FormulairePersonnel();
		}
		return formulaire;
	}

}
