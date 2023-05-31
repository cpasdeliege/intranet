package be.cpasdeliege.intranet.controler.intercepteur;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class IntercepteurChrono implements HandlerInterceptor {

	long time = 0;
	DomainInterface metier = null;

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		GregorianCalendar date = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMMM yyyy 'à' HH:mm");
		time = new GregorianCalendar().getTimeInMillis() - time;
		DecimalFormat df = new DecimalFormat("0.000");
		double temp = time;
		temp = temp / 1000;
		request.setAttribute("chrono", df.format(temp));
		request.setAttribute("date", sdf.format(date.getTime()));
	}

	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		time = new GregorianCalendar().getTimeInMillis();
		metier.testBD();
		return true;
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
