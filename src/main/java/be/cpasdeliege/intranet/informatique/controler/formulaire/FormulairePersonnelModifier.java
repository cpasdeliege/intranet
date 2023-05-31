package be.cpasdeliege.intranet.informatique.controler.formulaire;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

import java.net.URLEncoder;

public class FormulairePersonnelModifier extends SimpleFormController {

	DomainInterface metier = null;

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object formulaire,
			BindException arg3) throws Exception {
//		metier.getLogger().log(new LogRecordIntranet(Level.INFO, ((FormulairePersonnel)formulaire).getNom() + ", " + ((FormulairePersonnel)formulaire).getPrenom(), request));
		/*
		 * ((FormulairePersonnel)formulaire).setNom(new
		 * String(((FormulairePersonnel)formulaire).getNom().getBytes(),"UTF-8"));
		 * ((FormulairePersonnel)formulaire).setPrenom(new
		 * String(((FormulairePersonnel)formulaire).getPrenom().getBytes(),"UTF-8"));
		 * ((FormulairePersonnel)formulaire).setCodeAS(new
		 * String(((FormulairePersonnel)formulaire).getCodeAS().getBytes(),"UTF-8"));
		 * ((FormulairePersonnel)formulaire).setEmail(new
		 * String(((FormulairePersonnel)formulaire).getEmail().getBytes(),"UTF-8"));
		 * ((FormulairePersonnel)formulaire).setEmailAlias(new
		 * String(((FormulairePersonnel)formulaire).getEmailAlias().getBytes(),"UTF-8"))
		 * ; ((FormulairePersonnel)formulaire).setLoginAS400(new
		 * String(((FormulairePersonnel)formulaire).getLoginAS400().getBytes(),"UTF-8"))
		 * ; ((FormulairePersonnel)formulaire).setLoginCPAS2000(new
		 * String(((FormulairePersonnel)formulaire).getLoginCPAS2000().getBytes(),
		 * "UTF-8")); ((FormulairePersonnel)formulaire).setLoginGRH(new
		 * String(((FormulairePersonnel)formulaire).getLoginGRH().getBytes(),"UTF-8"));
		 * ((FormulairePersonnel)formulaire).setLoginNCC(new
		 * String(((FormulairePersonnel)formulaire).getLoginNCC().getBytes(),"UTF-8"));
		 * ((FormulairePersonnel)formulaire).setLoginPublilink(new
		 * String(((FormulairePersonnel)formulaire).getLoginPublilink().getBytes(),
		 * "UTF-8")); ((FormulairePersonnel)formulaire).setLoginWindows(new
		 * String(((FormulairePersonnel)formulaire).getLoginWindows().getBytes(),"UTF-8"
		 * )); ((FormulairePersonnel)formulaire).setMdpEmail(new
		 * String(((FormulairePersonnel)formulaire).getMdpEmail().getBytes(),"UTF-8"));
		 * ((FormulairePersonnel)formulaire).setMdpPublilink(new
		 * String(((FormulairePersonnel)formulaire).getMdpPublilink().getBytes(),"UTF-8"
		 * )); ((FormulairePersonnel)formulaire).setPathPhoto(new
		 * String(((FormulairePersonnel)formulaire).getPathPhoto().getBytes(),"UTF-8"));
		 * ((FormulairePersonnel)formulaire).setWifi(new
		 * String(((FormulairePersonnel)formulaire).getWifi().getBytes(),"UTF-8"));
		 */
		metier.modifierPersonnel((FormulairePersonnel) formulaire);
		request.getSession().setAttribute("formulairePersonnelModifier", null);
		response.sendRedirect((String) request.getSession().getAttribute("retour"));
		return super.onSubmit(request, response, formulaire, arg3);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		Object formulaire = request.getSession().getAttribute("formulairePersonnelModifier");
		if (formulaire == null
				|| (request.getParameter("nomPersonnel") != null && request.getParameter("prenomPersonnel") != null)) {
			// formulaire = metier.getFormulairePersonnel(new
			// String(request.getParameter("nomPersonnel").getBytes(),"UTF-8"), new
			// String(request.getParameter("prenomPersonnel").getBytes(),"UTF-8"));
			formulaire = metier.getFormulairePersonnel(request.getParameter("nomPersonnel"),
					request.getParameter("prenomPersonnel"));
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