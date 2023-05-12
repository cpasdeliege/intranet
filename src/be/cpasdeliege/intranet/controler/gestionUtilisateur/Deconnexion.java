package be.cpasdeliege.intranet.controler.gestionUtilisateur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class Deconnexion implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getSession().setAttribute("utilisateur", null);
		request.getSession().invalidate();
		response.sendRedirect("index.htm");
		return null;
	}

}
