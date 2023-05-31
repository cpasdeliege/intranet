package be.cpasdeliege.intranet.informatique.controler.formulaire;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class FormulaireScannerModifier extends SimpleFormController {

	DomainInterface metier = null;

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object formulaire,
			BindException exception) throws Exception {

		return super.onSubmit(request, response, formulaire, exception);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		return super.formBackingObject(request);
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}
}
