package be.cpasdeliege.intranet.DemServInfo.controler;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.DemServInfo.dao.DaoDemServInf;

public class Transmettre implements Controller {

	DaoDemServInf daoDsi = null;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HashMap modele = new HashMap();
		modele.put("dsi", daoDsi.getDemande(request.getParameter("idDemande")));
		return new ModelAndView("transmettre", modele);
	}

	public DaoDemServInf getDaoDsi() {
		return daoDsi;
	}

	public void setDaoDsi(DaoDemServInf daoDsi) {
		this.daoDsi = daoDsi;
	}

}
