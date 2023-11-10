package be.cpasdeliege.intranet.regTravail.controler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.cadastre.dao.DaoCadastre;
import be.cpasdeliege.intranet.informatique.model.Config;

public class IndexRtController implements Controller {

	Config config = null;
	DaoCadastre daoCadastre = null;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String param = request.getParameter("param");
		switch (param) {
		case "rt":
			ReadDocument(config.getPdfNouveauReglement(), response);
			break;
		case "annexe01":
			ReadDocument(config.getPdfAnnexe01(), response);
			break;
		case "annexe02":
			ReadDocument(config.getPdfAnnexe02(), response);
			break;
		case "annexe03":
			ReadDocument(config.getPdfAnnexe03(), response);
			break;
		case "annexe04":
			ReadDocument(config.getPdfAnnexe04(), response);
			break;
		case "annexe05":
			ReadDocument(config.getPdfAnnexe05(), response);
			break;
		case "annexe06":
			ReadDocument(config.getPdfAnnexe06(), response);
			break;
		case "annexe07":
			ReadDocument(config.getPdfAnnexe07(), response);
			break;
		case "annexe08":
			ReadDocument(config.getPdfAnnexe08(), response);
			break;
		case "annexe09":
			ReadDocument(config.getPdfAnnexe09(), response);
			break;
		case "annexe10":
			ReadDocument(config.getPdfAnnexe10(), response);
			break;
		case "annexe11":
			ReadDocument(config.getPdfAnnexe11(), response);
			break;
		case "annexe12":
			ReadDocument(config.getPdfAnnexe12(), response);
			break;
		case "annexe13":
			ReadDocument(config.getPdfAnnexe13(), response);
			break;
		case "annexe14":
			ReadDocument(config.getPdfAnnexe14(), response);
			break;
		case "annexe14b":
			ReadDocument(config.getPdfAnnexe14b(), response);
			break;
		case "annexe15":
			ReadDocument(config.getPdfAnnexe15(), response);
			break;
		case "annexe16":
			ReadDocument(config.getPdfAnnexe16(), response);
			break;
		case "annexe17":
			ReadDocument(config.getPdfAnnexe17(), response);
			break;
		case "annexe18":
			ReadDocument(config.getPdfAnnexe18(), response);
			break;
		case "statutadm":
			ReadDocument(config.getPdfStatutAdm(), response);
			break;
		case "statutpec":
			ReadDocument(config.getPdfStatutPec(), response);
			break;
		}
		return null;
	}

	private void ReadDocument(String path, HttpServletResponse response) throws Exception {
		InputStream is = new FileInputStream(path);
		OutputStream os = response.getOutputStream();
		response.setContentType("application/pdf");
		int count;
		byte buf[] = new byte[4096];
		while ((count = is.read(buf)) > 1) {
			os.write(buf, 0, count);
		}
		is.close();
		os.close();
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public DaoCadastre getDaoCadastre() {
		return daoCadastre;
	}

	public void setDaoCadastre(DaoCadastre daoCadastre) {
		this.daoCadastre = daoCadastre;
	}

}
