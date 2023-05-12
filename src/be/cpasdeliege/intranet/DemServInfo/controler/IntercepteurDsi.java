package be.cpasdeliege.intranet.DemServInfo.controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import be.cpasdeliege.intranet.DemServInfo.dao.DaoDemServInf;
import be.cpasdeliege.intranet.informatique.model.PrivilegeInformatique;

public class IntercepteurDsi implements HandlerInterceptor {

	DaoDemServInf daoDsi = null;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		daoDsi.testBD();
		PrivilegeInformatique privilege = (PrivilegeInformatique) request.getSession()
				.getAttribute("privilegeInformatique");
		if (privilege == null) {
			request.getSession().setAttribute("retour", request.getRequestURL() + "?" + request.getQueryString());
			response.sendRedirect("accesRefuse.htm");
			return false;
		} else if (privilege.isDsiDirection() || privilege.isDsiInfo() || privilege.isDsiUser()
				|| privilege.isDsiChef()) {
			return true;
		} else {
			response.sendRedirect("accesRefuse.htm");
			return false;
		}
	}

	public DaoDemServInf getDaoDsi() {
		return daoDsi;
	}

	public void setDaoDsi(DaoDemServInf daoDsi) {
		this.daoDsi = daoDsi;
	}

}
