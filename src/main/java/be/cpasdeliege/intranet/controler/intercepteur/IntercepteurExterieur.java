package be.cpasdeliege.intranet.controler.intercepteur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import be.cpasdeliege.intranet.informatique.model.PrivilegeInformatique;
import be.cpasdeliege.intranet.informatique.model.dao.DaoMySQL;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class IntercepteurExterieur implements HandlerInterceptor {

	DomainInterface metier = null;
	DaoMySQL dao = null;

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {
		System.out.println("getServerName : " + request.getServerName());
		PrivilegeInformatique privilege = (PrivilegeInformatique) request.getSession()
				.getAttribute("privilegeInformatique");
		if (privilege == null && request.getServerName().equals("intranet.cpasdeliege.be")) {
			request.getSession().setAttribute("retour", "index.htm");
			response.sendRedirect("accesRefuse.htm");
			return false;
		} else {
			return true;
		}
	}

	private boolean isIpAutorized(String ip) {
		return false;
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

	public DaoMySQL getDao() {
		return dao;
	}

	public void setDao(DaoMySQL dao) {
		this.dao = dao;
	}

}