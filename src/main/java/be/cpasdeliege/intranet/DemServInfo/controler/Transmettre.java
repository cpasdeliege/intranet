package be.cpasdeliege.intranet.DemServInfo.controler;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.DemServInfo.dao.DaoDemServInf;
import be.cpasdeliege.intranet.DemServInfo.model.DemServInf;
import be.cpasdeliege.intranet.utils.Utils;

public class Transmettre implements Controller {

	DaoDemServInf daoDsi = null;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		

		DemServInf dsi = daoDsi.getDemande(request.getParameter("idDemande"));
		/*
		HashMap modele = new HashMap();
		modele.put("dsi", daoDsi.getDemande(request.getParameter("idDemande")));
		return new ModelAndView("transmettre", modele);
		*/
		
		String subject = dsi.getTitre();
		String dsiUrl = "http://intranet/gestionTache.admin?idPlanning=" + dsi.getIdDemandes();
		String message = "Cher collègue,\n\n" +
		"Nous vous informons qu'une remarque importante vient d'être ajoutée à la DSI dont le descriptif suit.\n\n"+
		"Pourriez-vous donc la consulter dans les plus bref délais en cliquant sur ce lien : " + dsiUrl + "\n\n"+
		"++++++++++++++++++++++++++++++++++++++\n\n"+
		dsi.getDescription();
		
		try {
			response.sendRedirect("https://outlook.office.com/mail/deeplink/compose?body="+
			URLEncoder.encode(message, "UTF8") + "&subject=" + URLEncoder.encode(subject, "UTF8"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ModelAndView();
	}

	public DaoDemServInf getDaoDsi() {
		return daoDsi;
	}

	public void setDaoDsi(DaoDemServInf daoDsi) {
		this.daoDsi = daoDsi;
	}

}
