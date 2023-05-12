package be.cpasdeliege.intranet.controler.gestionUtilisateur;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class AccesRefuse implements Controller {

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HashMap modele = new HashMap();
		return new ModelAndView("accesRefuse", modele);
	}

}