package be.cpasdeliege.intranet.regTravail.controler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.cadastre.dao.DaoCadastre;
import be.cpasdeliege.intranet.cadastre.model.Annexe;
import be.cpasdeliege.intranet.cadastre.model.Formulaire;
import be.cpasdeliege.intranet.informatique.model.Config;

public class IndexControler implements Controller {

	Config config = null;
	DaoCadastre daoCadastre = null;

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String param = request.getParameter("param");
		if(param == null) {
			response.sendRedirect((String)request.getSession().getAttribute("retour"));
		} else {
			if(param.equals("normal")) {
				InputStream is = new FileInputStream(config.getPdfReglementTravail());
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("lots")) {
				InputStream is = new FileInputStream(config.getPdfReglementTravailLots());
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("rtcite")) {
				request.getSession().setAttribute("retour", "index.htm");
				HashMap modele = new HashMap();
				return new ModelAndView("IndexNouveauRT", modele);
			} else if(param.equals("etat")) {
				InputStream is = new FileInputStream(config.getPdf_PS_etatDesLieux());
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("tableau")) {
				InputStream is = new FileInputStream(config.getPdf_PS_tableauDeBord());
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("presentation")) {
				InputStream is = new FileInputStream(config.getPdf_PS_presentation());
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("certificat")) {
				InputStream is;
				File tmp = new File("c:\\fop");
		        if(!tmp.exists()) {
		        	is = new FileInputStream("/opt/fop/certificat_medical.pdf");
		        } else {
		        	is = new FileInputStream("c:\\fop\\certificat_medical.pdf");
		        }
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename=Certificat_médical.pdf");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("ethias")) {
				InputStream is;
				File tmp = new File("c:\\fop");
		        if(!tmp.exists()) {
		        	is = new FileInputStream("/opt/fop/2020 Avantages Ethias Liège CPAS.pdf");
		        } else {
		        	is = new FileInputStream("c:\\opt\\fop\\2020 Avantages Ethias Liège CPAS.pdf");
		        }
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("ethiasPromo")) {
				InputStream is;
				File tmp = new File("c:\\fop");
		        if(!tmp.exists()) {
		        	//is = new FileInputStream("/opt/fop/Promo fin 2022 Pouvoir dachat Liège CPAS.pdf");
		        	is = new FileInputStream(config.getPdfPromoEthias());
		        } else {
		        	//is = new FileInputStream("c:\\fop\\Promo fin 2022 Pouvoir dachat Liège CPAS.pdf");
		        	is = new FileInputStream(config.getPdfPromoEthias());
		        }
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("vmMado")) {
				InputStream is = new FileInputStream(config.getPdf_vmMado());
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename=Vade-Mecum_Maintien_à_Domicile.pdf");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("vmIAS")) {
				InputStream is = new FileInputStream(config.getPdf_vmIAS());
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename=Vade-Mecum_Action_Sociale.pdf");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("vmAideSociale")) {
				InputStream is;
				File tmp = new File("c:\\fop");
		        if(!tmp.exists()) {
		        	is = new FileInputStream("/opt/fop/VM_aide_sociale.pdf");
		        } else {
		        	is = new FileInputStream("c:\\fop\\VM_aide_sociale.pdf");
		        }
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename=Vade-Mecum_Aide_Sociale.pdf");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("DSI")) {
				InputStream is = new FileInputStream(config.getPdf_DSI());
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("annexeCadastre")) {
				String id_annexe = request.getParameter("id_annexe");
				Annexe annexe = daoCadastre.getAnnexe(id_annexe);
				
				InputStream is;
				File tmp = new File("c:\\fop");
		        if(!tmp.exists()) {
		        	is = new FileInputStream("/opt/fop/cadastre/" + annexe.getId_marche_public() + "/" + annexe.getPath());
		        } else {
		        	is = new FileInputStream("c:\\fop\\cadastre\\" + annexe.getId_marche_public() + "\\" + annexe.getPath());
		        }
		        response.setHeader("Content-Disposition", "attachment; filename=\"" + annexe.getNom() + "\"");
				OutputStream os = response.getOutputStream();
//					response.setContentType("application/pdf");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
					
			}else if(param.equals("formulaireCadastre")) {
				String id_formulaire = request.getParameter("id_formulaire");
				Formulaire formulaire = daoCadastre.getFormulaire(id_formulaire);
				
				InputStream is;
				File tmp = new File("c:\\fop");
		        if(!tmp.exists()) {
		        	is = new FileInputStream("/opt/fop/cadastre/formulaires/" + formulaire.getPath());
		        } else {
		        	is = new FileInputStream("c:\\fop\\cadastre\\formulaires\\" + formulaire.getPath());
		        }
		        response.setHeader("Content-Disposition", "attachment; filename=\"" + formulaire.getNom() + "\"");
				OutputStream os = response.getOutputStream();
//					response.setContentType("application/pdf");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
					
			} else if(param.equals("DSI_annexe")) {
				String fichier = request.getParameter("fichier");
				String idDemande = request.getParameter("idDemande");
				if(fichier != null && idDemande != null) {
					InputStream is;
					File tmp = new File("c:\\fop");
			        if(!tmp.exists()) {
			        	is = new FileInputStream("/opt/fop/DSI/" + idDemande + "/" + fichier);
			        } else {
			        	is = new FileInputStream("c:\\fop\\DSI\\" + idDemande + "\\" + fichier);
			        }
			        response.setHeader("Content-Disposition", "attachment; filename=\"" + fichier + "\"");
					OutputStream os = response.getOutputStream();
					int count;
					byte buf[] = new byte[4096];
					while((count = is.read(buf))>1) {
						os.write(buf,0, count);
					}
					is.close();
					os.close();
				}
			} else if(param.equals("GTI_annexe")) {
				String fichier = request.getParameter("fichier");
				String idPlanning = request.getParameter("idPlanning");
				
				if(fichier != null && idPlanning != null) {
					InputStream is;
					File tmp = new File("c:\\fop");
			        if(!tmp.exists()) {
			        	is = new FileInputStream("/opt/fop/GTI/" + idPlanning + "/" + fichier);
			        } else {
			        	is = new FileInputStream("c:\\fop\\GTI\\" + idPlanning + "\\" + fichier);
			        }
			        response.setHeader("Content-Disposition", "attachment; filename=\"" + fichier + "\"");
					OutputStream os = response.getOutputStream();
					int count;
					byte buf[] = new byte[4096];
					while((count = is.read(buf))>1) {
						os.write(buf,0, count);
					}
					is.close();
					os.close();
				}
			} else if(param.equals("sscEH")) {
				InputStream is;
				File tmp = new File("c:\\fop");
		        if(!tmp.exists()) {
		        	is = new FileInputStream("/opt/fop/Intervention en faveur d'un enfant handicapé.pdf");
		        } else {
		        	is = new FileInputStream("c:\\fop\\Intervention en faveur d'un enfant handicapé.pdf");
		        }
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename=\"Intervention en faveur d'un enfant handicapé.pdf\"");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("sscPMC")) {
				InputStream is;
				File tmp = new File("c:\\fop");
		        if(!tmp.exists()) {
		        	is = new FileInputStream("/opt/fop/Prime de mariage et de cohabitation.pdf");
		        } else {
		        	is = new FileInputStream("c:\\fop\\Prime de mariage et de cohabitation.pdf");
		        }
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename=\"Prime de mariage et de cohabitation.pdf\"");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("sscPP")) {
				InputStream is;
				File tmp = new File("c:\\fop");
		        if(!tmp.exists()) {
		        	is = new FileInputStream("/opt/fop/Prime de mise à la pension.pdf");
		        } else {
		        	is = new FileInputStream("c:\\fop\\Prime de mise à la pension.pdf");
		        }
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename=\"Prime de mise à la pension.pdf\"");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("sscPNA")) {
				InputStream is;
				File tmp = new File("c:\\fop");
		        if(!tmp.exists()) {
		        	is = new FileInputStream("/opt/fop/Prime de naissance et adoption.pdf");
		        } else {
		        	is = new FileInputStream("c:\\fop\\Prime de naissance et adoption.pdf");
		        }
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename=\"Prime de naissance et adoption.pdf\"");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("rgpdPVDCP")) {
				InputStream is;
				File tmp = new File("c:\\fop");
		        if(!tmp.exists()) {
		        	is = new FileInputStream("/opt/fop/ProcédureViolationDCP - 1er déclarant.pdf");
		        } else {
		        	is = new FileInputStream("c:\\fop\\ProcédureViolationDCP - 1er déclarant.pdf");
		        }
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename=\"ProcédureViolationDCP - 1er déclarant.pdf\"");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("rgpdPPDCP")) {
				InputStream is;
				File tmp = new File("c:\\fop");
		        if(!tmp.exists()) {
		        	is = new FileInputStream("/opt/fop/PolitiqueProtectionDCP.pdf");
		        } else {
		        	is = new FileInputStream("c:\\fop\\PolitiqueProtectionDCP.pdf");
		        }
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename=\"PolitiqueProtectionDCP.pdf\"");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("rgpdFED")) {
				InputStream is;
				File tmp = new File("c:\\fop");
		        if(!tmp.exists()) {
		        	is = new FileInputStream("/opt/fop/FormulaireExerciceDroitsRGPD.pdf");
		        } else {
		        	is = new FileInputStream("c:\\fop\\FormulaireExerciceDroitsRGPD.pdf");
		        }
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename=\"FormulaireExerciceDroitsRGPD.pdf\"");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("sscPSSC")) {
				InputStream is;
				File tmp = new File("c:\\fop");
		        if(!tmp.exists()) {
		        	is = new FileInputStream("/opt/fop/2020- flyer_primes_FR-1.pdf");
		        } else {
		        	is = new FileInputStream("c:\\fop\\2020- flyer_primes_FR-1.pdf");
		        }
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename=\"2020- flyer_primes_FR-1.pdf\"");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("sscPAR")) {
				InputStream is;
				File tmp = new File("c:\\fop");
		        if(!tmp.exists()) {
		        	is = new FileInputStream("/opt/fop/flyer_monSSC_FR_def-1.pdf");
		        } else {
		        	is = new FileInputStream("c:\\fop\\flyer_monSSC_FR_def-1.pdf");
		        }
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename=\"flyer_monSSC_FR_def-1.pdf\"");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("sscNSC")) {
				// changement de personne de contact
				// 
				InputStream is;
				File tmp = new File("c:\\fop");
		        if(!tmp.exists()) {
		        	is = new FileInputStream("/opt/fop/N°900NvellePersonneDeContactSSC.pdf");
		        } else {
		        	is = new FileInputStream("c:\\fop\\N°900NvellePersonneDeContactSSC.pdf");
		        }
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename=\"N°990NvellePersonneDeContactSSC.pdf\"");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			} else if(param.equals("memoCite")) {
				// changement de personne de contact
				// 
				InputStream is;
				File tmp = new File("c:\\fop");
		        if(!tmp.exists()) {
		        	is = new FileInputStream("/opt/fop/memo_cite_administrative.pdf");
		        } else {
		        	is = new FileInputStream("c:\\fop\\memo_cite_administrative.pdf");
		        }
				OutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename=\"Memo_Cite_Administrative.pdf\"");
				int count;
				byte buf[] = new byte[4096];
				while((count = is.read(buf))>1) {
					os.write(buf,0, count);
				}
				is.close();
				os.close();
			}
		}
		return null;
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
