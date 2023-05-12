package be.cpasdeliege.intranet.informatique.controler.formulaire;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import be.cpasdeliege.intranet.informatique.model.PrivilegeInformatique;
import be.cpasdeliege.intranet.informatique.model.Utilisateur;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class FormulaireModifierPrivilegeInformatique extends SimpleFormController {

	DomainInterface metier = null;

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object formulaire, BindException arg3) throws Exception {
//		metier.getLogger().log(new LogRecordIntranet(Level.INFO, ((PrivilegeInformatique)formulaire).getLogin(), request));
		metier.modifierPrivilègeInformatique((PrivilegeInformatique)formulaire);
		response.sendRedirect((String)request.getSession().getAttribute("retour") + "#accesIntranet");
		return super.onSubmit(request, response, formulaire, arg3);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		PrivilegeInformatique formulairePrivilegeInformatique = metier.getPrivilegeInformatique(request.getParameter("login"));
		return formulairePrivilegeInformatique;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map data = new HashMap();
		Utilisateur utilisateur = metier.getUtilisateur(request.getParameter("login"));
		data.put("personnel", metier.getPersonnel(utilisateur.getNom(), utilisateur.getPrenom()));
		return data;
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}
}
