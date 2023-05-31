package be.cpasdeliege.intranet.controler.gestionUtilisateur;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.informatique.model.Utilisateur;
import be.cpasdeliege.intranet.informatique.model.domain.DomainInterface;

public class Authentification implements Controller {

	DomainInterface metier = null;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Utilisateur utilisateur = metier.authentification(request.getParameter("login"), request.getParameter("mdp"));
		if(utilisateur == null) {
			metier.log(request, response, "BAD LOGIN ! (mdp=" + request.getParameter("mdp") + ")");
			logBadLogin(request.getParameter("login"), request.getRemoteAddr());
						
			
			response.sendRedirect((String)request.getSession().getAttribute("retour"));
		} else if (utilisateur.isActif()){
//			String date = new SimpleDateFormat("yyyyMMdd").format(new GregorianCalendar().getTime());
//			String tmp = date + "psswrd";
////			System.out.println(tmp);
//			 byte[] hash;
//		        try {
//		            hash = MessageDigest.getInstance("MD5").digest(tmp.getBytes("UTF-8"));
//		        } catch (NoSuchAlgorithmException e) {
//		            throw new RuntimeException("Huh, MD5 should be supported?", e);
//		        } catch (UnsupportedEncodingException e) {
//		            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
//		        }
//		        StringBuilder hex = new StringBuilder(hash.length * 2);
//		        for (byte b : hash) {
//		            int i = (b & 0xFF);
//		            if (i < 0x10) hex.append('0');
//		            hex.append(Integer.toHexString(i));
//		        }
//            
//			Cookie cookie = new Cookie("prt", hex.toString());
//			cookie.setDomain("10.104.87.242");
//			cookie.setPath("/imprimantes/");
//			cookie.setMaxAge(3600);
//			response.addCookie(cookie);
			
			request.getSession().setAttribute("utilisateur", utilisateur);
			request.getSession().setAttribute("privilegeInformatique", metier.getPrivilegeInformatique(utilisateur.getLogin()));
			request.getSession().setMaxInactiveInterval(60 * 60);
			if(!request.getParameter("mdp").equals("cpas")) {
				response.sendRedirect((String)request.getSession().getAttribute("retour"));
				//response.sendRedirect("index.htm");
			} else {
				response.sendRedirect("changerMotDePasseParDefaut.htm");
			}
		} else {
			response.sendRedirect((String)request.getSession().getAttribute("retour"));
		}

		return null;
	}

	public void logBadLogin(String user, String IP) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String filename = "/opt/log/intranet.log";
//		filename = (new File("c:\\fop").exists()) ? "C:\\TEMP\\intranet.log" : "/opt/log/intranet.log";
		File tmp = new File(filename);

		BufferedWriter bufWriter = null;
		FileWriter fileWriter = null;
		try {
			if (!tmp.exists()) {
				tmp.createNewFile();
			}
			fileWriter = new FileWriter(filename, true);
			bufWriter = new BufferedWriter(fileWriter);
			bufWriter.write(sdf.format(new java.util.Date()) + " USER=" + user + " BAD LOGIN FROM " + IP);
			bufWriter.newLine();
			bufWriter.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				bufWriter.close();
				fileWriter.close();
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public DomainInterface getMetier() {
		return metier;
	}

	public void setMetier(DomainInterface metier) {
		this.metier = metier;
	}

}
