package be.cpasdeliege.intranet.informatique.controler.formulaire;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class FormulaireServicePersonnelAssignerControler extends SimpleFormController {

	DomainInterface metier = null;

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object formulaire,
			BindException arg3) throws Exception {
		// request.getSession().setAttribute("formulairePersonnelAssigner", formulaire);
//		metier.getLogger().log(new LogRecordIntranet(Level.INFO, ((FormulaireServicePersonnelAssigner)formulaire).getService() + ", " + ((FormulaireServicePersonnelAssigner)formulaire).getEmploye(), request));
		metier.assignerPersonnelService((FormulaireServicePersonnelAssigner) formulaire);
		request.getSession().setAttribute("formulaireServicePersonnelAssigner", null);
		response.sendRedirect((String) request.getSession().getAttribute("retour") + "#listePersonnel");
		return super.onSubmit(request, response, formulaire, arg3);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		Object formulaire = request.getSession().getAttribute("formulaireServicePersonnelAssigner");
		if (formulaire == null) {
			formulaire = new FormulaireServicePersonnelAssigner();
		}
		return formulaire;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map data = new HashMap();
		data.put("ser", metier.getService(request.getParameter("service")));
		data.put("typeFonction", metier.getTypeFonction());
		data.put("listePersonnel", metier.getListePersonnelServiceAssigner(request.getParameter("service")));
		return data;
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}
}
