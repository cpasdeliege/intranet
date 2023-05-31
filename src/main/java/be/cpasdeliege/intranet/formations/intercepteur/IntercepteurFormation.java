package be.cpasdeliege.intranet.formations.intercepteur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import be.cpasdeliege.intranet.informatique.model.PrivilegeInformatique;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class IntercepteurFormation implements HandlerInterceptor {

	DomainInterface metier = null;

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {
		// metier.testBD();
		PrivilegeInformatique privilege = (PrivilegeInformatique) request.getSession()
				.getAttribute("privilegeInformatique");
		if (privilege == null) {
			request.getSession().setAttribute("retour", request.getRequestURL() + "?" + request.getQueryString());
			response.sendRedirect("accesRefuse.htm");
			return false;
		} else if (privilege.isAdministrateur() || privilege.isFormations()) {
			return true;
		} else {
			response.sendRedirect("accesRefuse.htm");
			return false;
		}
	}

//	private String getRetour(HttpServletRequest request) {
//		String retour = (String)request.getSession().getAttribute("retour");
//		if(retour == null) {
//			retour = "index.htm";
//		}
//		return retour;
//	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
