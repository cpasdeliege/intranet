package be.cpasdeliege.intranet.cadastre.controler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import be.cpasdeliege.intranet.cadastre.dao.DaoCadastre;
import be.cpasdeliege.intranet.cadastre.model.AdresseMail;
import be.cpasdeliege.intranet.cadastre.model.Annexe;
import be.cpasdeliege.intranet.cadastre.model.DenominationReference;
import be.cpasdeliege.intranet.cadastre.model.Lien;
import be.cpasdeliege.intranet.cadastre.model.Marche;
import be.cpasdeliege.intranet.cadastre.model.TypeMarche;
import be.cpasdeliege.intranet.informatique.model.dao.DaoMySQL;

public class Index implements Controller {

	DaoMySQL dao = null;
	DaoCadastre daoCadastre = null;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		if (action == null) {
			return index(request, response);
		} else if (action.equals("listeMP")) {
			return listeMP(request, response);
		} else if (action.equals("ajouterMP")) {
			return ajouterMP(request, response);
		} else if (action.equals("ajouterType")) {
			return ajouterType(request, response);
		} else if (action.equals("formAjouterMP")) {
			return formAjouterMP(request, response);
		} else if (action.equals("formAjouterType")) {
			return formAjouterType(request, response);
		} else if (action.equals("modifierMP")) {
			return modifierMP(request, response);
		} else if (action.equals("modifierType")) {
			return modifierType(request, response);
		} else if (action.equals("formModifierMP")) {
			return formModifierMP(request, response);
		} else if (action.equals("formModifierType")) {
			return formModifierType(request, response);
		} else if (action.equals("afficherMP")) {
			return afficherMP(request, response);
		} else if (action.equals("suppAnnexe")) {
			return suppAnnexe(request, response);
		} else if (action.equals("suppLien")) {
			return suppLien(request, response);
		} else if (action.equals("suppFormulaire")) {
			return suppFormulaire(request, response);
		} else if (action.equals("suppMarche")) {
			return suppMarche(request, response);
		} else if (action.equals("deleteMarche")) {
			return deleteMarche(request, response);
		} else if (action.equals("deflagMarche")) {
			return deflagMarche(request, response);
		} else if (action.equals("suppType")) {
			return suppType(request, response);
		} else if (action.equals("ajouterDenomination")) {
			return ajouterDenomination(request, response);
		} else if (action.equals("suppDenomination")) {
			return suppDenomination(request, response);
		} else if (action.equals("ajouterMail")) {
			return ajouterMail(request, response);
		} else if (action.equals("suppMail")) {
			return suppMail(request, response);
		} else if (action.equals("telecharger")) {
			return telecharger(request, response);
		} else {
			return index(request, response);
		}

	}

	private ModelAndView telecharger(HttpServletRequest request, HttpServletResponse response) {
		try {

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet();

			List<Marche> listeMarche = (List<Marche>) request.getSession().getAttribute("listeMarche");

			if (listeMarche != null) {
				HSSFRow row = sheet.createRow(0);
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell0.setCellValue(new HSSFRichTextString("Intitulé"));

				HSSFCell cell1 = row.createCell(1);
				cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell1.setCellValue(new HSSFRichTextString("Référence"));

				HSSFCell cell2 = row.createCell(2);
				cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell2.setCellValue(new HSSFRichTextString("Adjudicataire"));

				HSSFCell cell3 = row.createCell(3);
				cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell3.setCellValue(new HSSFRichTextString("Date BP/CAS"));

				HSSFCell cell4 = row.createCell(4);
				cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell4.setCellValue(new HSSFRichTextString("P. Adjudicateur"));

				HSSFCell cell5 = row.createCell(5);
				cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell5.setCellValue(new HSSFRichTextString("Type"));

				HSSFCell cell6 = row.createCell(6);
				cell6.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell6.setCellValue(new HSSFRichTextString("Date début"));

				HSSFCell cell7 = row.createCell(7);
				cell7.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell7.setCellValue(new HSSFRichTextString("Date fin"));

//				CellStyle style = row.getRowStyle();
//				HSSFFont font= wb.createFont();
//			    font.setFontHeightInPoints((short)10);
//			    font.setFontName("Arial");
//			    font.setColor(IndexedColors.WHITE.getIndex());
//			    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
//			    font.setItalic(false);
//			    style.setFont(font);
			}
			int i = 1;
			for (Marche marche : listeMarche) {
				HSSFRow row = sheet.createRow(i);
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell0.setCellValue(new HSSFRichTextString(marche.getIntitule()));

				HSSFCell cell1 = row.createCell(1);
				cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell1.setCellValue(new HSSFRichTextString(marche.getReference()));

				HSSFCell cell2 = row.createCell(2);
				cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell2.setCellValue(new HSSFRichTextString(marche.getAdjudicataire()));

				HSSFCell cell3 = row.createCell(3);
				cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell3.setCellValue(new HSSFRichTextString(marche.getDate_BPFormat()));

				HSSFCell cell4 = row.createCell(4);
				cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell4.setCellValue(new HSSFRichTextString(marche.getAdjudicateur()));

				HSSFCell cell5 = row.createCell(5);
				cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell5.setCellValue(new HSSFRichTextString(marche.getTypeMarche()));

				HSSFCell cell6 = row.createCell(6);
				cell6.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell6.setCellValue(new HSSFRichTextString(marche.getDate_debutFormat()));

				HSSFCell cell7 = row.createCell(7);
				cell7.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell7.setCellValue(new HSSFRichTextString(marche.getDate_finFormat()));

				i++;
			}
			for (int columnIndex = 0; columnIndex < 10; columnIndex++) {
				sheet.autoSizeColumn(columnIndex);
			}
			response.setHeader("Content-Disposition", "attachment; filename=\"listing cadastres.xls\"");
			response.setContentType("application/xls");
			wb.write(response.getOutputStream());
			response.getOutputStream().close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private ModelAndView ajouterMail(HttpServletRequest request, HttpServletResponse response) {
		String adresse = request.getParameter("adresse");
		if (adresse != null && !adresse.equals("")) {
			AdresseMail adresseMail = new AdresseMail();
			adresseMail.setAdresse(adresse);
			daoCadastre.addAdresseMail(adresseMail);
		}
		return index(request, response);
	}

	private ModelAndView suppMail(HttpServletRequest request, HttpServletResponse response) {
		String id_mail = request.getParameter("id_mail");
		if (id_mail != null) {
			daoCadastre.supprimerAdresseMail(id_mail);
		}
		return index(request, response);
	}

	private ModelAndView deleteMarche(HttpServletRequest request, HttpServletResponse response) {
		String id_marche_public = request.getParameter("id_marche_public");
		daoCadastre.deleteMarche(id_marche_public);
		List<Marche> listeMarche = null;
		TypeMarche typeMarche = null;
		listeMarche = daoCadastre.getListeMarchesSupp(null, null);
		request.getSession().setAttribute("listeMarche", listeMarche);
		HashMap modele = new HashMap();
		modele.put("info", "supprimés");
		modele.put("listeMarche", listeMarche);
		modele.put("typeMarche", typeMarche);
		modele.put("listeTypeMarche", daoCadastre.getListeTypeMarche());
		return new ModelAndView("listerMarchePublic", modele);
	}

	private ModelAndView deflagMarche(HttpServletRequest request, HttpServletResponse response) {
		String id_marche_public = request.getParameter("id_marche_public");
		daoCadastre.deflagMarche(id_marche_public);
		List<Marche> listeMarche = null;
		TypeMarche typeMarche = null;
		listeMarche = daoCadastre.getListeMarchesSupp(null, null);
		request.getSession().setAttribute("listeMarche", listeMarche);
		HashMap modele = new HashMap();
		modele.put("info", "supprimés");
		modele.put("listeMarche", listeMarche);
		modele.put("typeMarche", typeMarche);
		modele.put("listeTypeMarche", daoCadastre.getListeTypeMarche());
		return new ModelAndView("listerMarchePublic", modele);
	}

	private ModelAndView ajouterDenomination(HttpServletRequest request, HttpServletResponse response) {
		String denomination = request.getParameter("denomination");
		if (denomination != null && !denomination.equals("")) {
			DenominationReference denomitationReference = new DenominationReference();
			denomitationReference.setDenomination(denomination);
			daoCadastre.addDenomitationReference(denomitationReference);
		}
		return index(request, response);
	}

	private ModelAndView suppDenomination(HttpServletRequest request, HttpServletResponse response) {
		String id_denomination_reference = request.getParameter("id_denomination_reference");
		if (id_denomination_reference != null) {
			daoCadastre.supprimerDenominationReference(id_denomination_reference);
		}
		return index(request, response);
	}

	private ModelAndView modifierDenomination(HttpServletRequest request, HttpServletResponse response) {

		return null;
	}

	private ModelAndView suppType(HttpServletRequest request, HttpServletResponse response) {
		String id_type_marche = request.getParameter("id_type_marche");
		List<Marche> listeMarche = daoCadastre.getListeMarchesByType(id_type_marche, null, null);
		HashMap modele = new HashMap();
		if(listeMarche.size() == 0) {
			daoCadastre.supprimerType(id_type_marche);
		} else {
			modele.put("erreur", "Il reste des marchés de type " + daoCadastre.getTypeMarche(id_type_marche).getType_marche() + ", suppression impossible !");
		}
		request.getSession().setAttribute("listeMarche", listeMarche);
		modele.put("listeMail", daoCadastre.getListeAdresseMail());
		modele.put("listeFormulaire", daoCadastre.getListeFormulaire());
		modele.put("listeDenomination", daoCadastre.getListeDenominationReference());
		modele.put("listeTypeMarche", daoCadastre.getListeTypeMarche());
		return new ModelAndView("index", modele);
	}

	private ModelAndView ajouterType(HttpServletRequest request, HttpServletResponse response) {
		if(verifFormulaireTypeMarche(request)) {
			daoCadastre.addTypeMarche(getFormulaireType(request));
			return index(request, response);
		} else {
			HashMap modele = new HashMap();
			modele.put("retour", "index.cad");
			modele.put("typeMarche", getFormulaireType(request));
			return new ModelAndView("formulaireAjouterType", modele);
		}
	}

	private ModelAndView formAjouterType(HttpServletRequest request, HttpServletResponse response) {
		HashMap modele = new HashMap();
		modele.put("retour", "index.cad");
		return new ModelAndView("formulaireAjouterType", modele);
	}

	private ModelAndView formModifierType(HttpServletRequest request, HttpServletResponse response) {
		String id_type_marche = request.getParameter("id_type_marche");
		HashMap modele = new HashMap();
		modele.put("retour", "index.cad");
		modele.put("typeMarche", daoCadastre.getTypeMarche(id_type_marche));
		return new ModelAndView("formulaireModifierType", modele);
	}

	private ModelAndView modifierType(HttpServletRequest request, HttpServletResponse response) {
		if(verifFormulaireTypeMarche(request)) {
			TypeMarche typeMarche = daoCadastre.getTypeMarche(request.getParameter("id_type_marche"));
			typeMarche.setType_marche(request.getParameter("type_marche"));
			daoCadastre.modifierType(typeMarche);
			return index(request, response);
		} else {
			HashMap modele = new HashMap();
			modele.put("retour", "index.cad");
			modele.put("typeMarche", daoCadastre.getTypeMarche(request.getParameter("id_type_marche")));
			return new ModelAndView("formulaireModifierType", modele);
		}
	}

	private ModelAndView formModifierMP(HttpServletRequest request, HttpServletResponse response) {
		String id_marche_public = request.getParameter("id_marche_public");
		String id_type_marche = request.getParameter("retour");
		if(id_type_marche.equals("")) {
			id_type_marche = "tous";
		}
		HashMap modele = new HashMap();
		modele.put("retour", "index.cad?action=listeMP&amp;type=" + id_type_marche);
		modele.put("marche", daoCadastre.getMarche(id_marche_public));
		modele.put("listeTypeMarche", daoCadastre.getListeTypeMarche());
		return new ModelAndView("formulaireModifier", modele);
	}

	private ModelAndView modifierMP(HttpServletRequest request, HttpServletResponse response) {
		if(verifFormulaireMarche(request)) {
			daoCadastre.modifierMarche(getFormulaireMarche(request));
			try {
				response.sendRedirect(request.getParameter("retour"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			HashMap modele = new HashMap();
			modele.put("retour", request.getParameter("retour"));
			modele.put("marche", getFormulaireMarche(request));
			modele.put("listeTypeMarche", daoCadastre.getListeTypeMarche());
			return new ModelAndView("formulaireModifier", modele);
		}
	}

	private ModelAndView suppAnnexe(HttpServletRequest request, HttpServletResponse response) {
		String id_annexe = request.getParameter("id_annexe");
		Annexe annexe = daoCadastre.getAnnexe(id_annexe);
		// delete disque du fichier
		daoCadastre.supprimerAnnexe(id_annexe);
		HashMap modele = new HashMap();
		modele.put("retour", "index.cad?action=listeMP&type=tous");
		modele.put("type", request.getParameter("retour"));
		modele.put("listeAnnexes", daoCadastre.getListeAnnexe(annexe.getId_marche_public()));
		modele.put("listeLiens", daoCadastre.getListeLien(annexe.getId_marche_public()));
		modele.put("marche", daoCadastre.getMarche(annexe.getId_marche_public()));
		return new ModelAndView("afficherMarche", modele);
	}

	private ModelAndView suppLien(HttpServletRequest request, HttpServletResponse response) {
		String id_lien = request.getParameter("id_lien");
		Lien annexe = daoCadastre.getLien(id_lien);
		// delete disque du fichier
		daoCadastre.supprimerLien(id_lien);
		HashMap modele = new HashMap();
		modele.put("retour", "index.cad?action=listeMP&type=tous");
		modele.put("type", request.getParameter("retour"));
		modele.put("listeAnnexes", daoCadastre.getListeAnnexe(annexe.getId_marche_public()));
		modele.put("listeLiens", daoCadastre.getListeLien(annexe.getId_marche_public()));
		modele.put("marche", daoCadastre.getMarche(annexe.getId_marche_public()));
		return new ModelAndView("afficherMarche", modele);
	}

	private ModelAndView suppFormulaire(HttpServletRequest request, HttpServletResponse response) {
		String id_formulaire = request.getParameter("id_formulaire");
		daoCadastre.supprimerFormulaire(id_formulaire);
		return index(request, response);
	}

	private ModelAndView suppMarche(HttpServletRequest request, HttpServletResponse response) {
		String id_marche_public = request.getParameter("id_marche_public");
		String type = request.getParameter("retour");
		daoCadastre.supprimerMarche(id_marche_public);
		List<Marche> listeMarche = null;
		TypeMarche typeMarche = null;
		if(type.equals("")) {
			listeMarche = daoCadastre.getListeMarches(null, null);
		} else {
			listeMarche = daoCadastre.getListeMarchesByType(type, null, null);
			typeMarche = daoCadastre.getTypeMarche(type);
		}
		request.getSession().setAttribute("listeMarche", listeMarche);
		HashMap modele = new HashMap();
		modele.put("listeMarche", listeMarche);
		modele.put("typeMarche", typeMarche);
		modele.put("listeTypeMarche", daoCadastre.getListeTypeMarche());
		return new ModelAndView("listerMarchePublic", modele);
	}

	private ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		HashMap modele = new HashMap();
		modele.put("listeMail", daoCadastre.getListeAdresseMail());
		modele.put("listeFormulaire", daoCadastre.getListeFormulaire());
		modele.put("listeDenomination", daoCadastre.getListeDenominationReference());
		modele.put("listeTypeMarche", daoCadastre.getListeTypeMarche());
		return new ModelAndView("index", modele);
	}

	private ModelAndView listeMP(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		String orderBy = request.getParameter("orderBy");
		String sensOrderBy = null;
		if(orderBy != null) {
			request.getSession().setAttribute("orderBy", orderBy);
			sensOrderBy = (String)request.getSession().getAttribute("sensOrderBy");
			if(sensOrderBy == null) {
				request.getSession().setAttribute("sensOrderBy", "asc");
				sensOrderBy = "asc";
			} else if(sensOrderBy.equals("asc")) {
				request.getSession().setAttribute("sensOrderBy", "desc");
				sensOrderBy = "desc";
			} else if(sensOrderBy.equals("desc")){
				request.getSession().setAttribute("sensOrderBy", "asc");
				sensOrderBy = "asc";
			}
		} else {
			orderBy = (String)request.getSession().getAttribute("orderBy");
			sensOrderBy = (String)request.getSession().getAttribute("sensOrderBy");
		}
		
		List<Marche> listeMarche = null;
		TypeMarche typeMarche = null;
		HashMap modele = new HashMap();
		if(type.equals("tous")) {
			listeMarche = daoCadastre.getListeMarches(orderBy, sensOrderBy);
			modele.put("info", "");
		} else if(type.equals("supp")) {
			listeMarche = daoCadastre.getListeMarchesSupp(orderBy, sensOrderBy);
			modele.put("info", "supprimés");
		} else if(type.equals("recherche")) {
			String motcle = request.getParameter("motcle");
			listeMarche = daoCadastre.getListeMarchesRecherche(motcle, orderBy, sensOrderBy);
			modele.put("info", "recherche");
			modele.put("motcle", motcle);
		} else {
			listeMarche = daoCadastre.getListeMarchesByType(type, orderBy, sensOrderBy);
			typeMarche = daoCadastre.getTypeMarche(type);
		}
		request.getSession().setAttribute("listeMarche", listeMarche);
		modele.put("listeMarche", listeMarche);
		modele.put("typeMarche", typeMarche);
		modele.put("listeTypeMarche", daoCadastre.getListeTypeMarche());
		return new ModelAndView("listerMarchePublic", modele);
	}

	private ModelAndView afficherMP(HttpServletRequest request, HttpServletResponse response) {
		String id_marche_public = request.getParameter("id_marche_public");
		String motcle = request.getParameter("motcle");
		String retour;
		if(request.getParameter("retour").equals("")) {
			retour = "index.cad?action=listeMP&amp;type=tous";
		} else if(request.getParameter("retour").equals("recherche")) {
			retour = "index.cad?action=listeMP&amp;type=recherche&amp;motcle=" + motcle;
		} else {
			retour = "index.cad?action=listeMP&amp;type=" + request.getParameter("retour");
		}
		HashMap modele = new HashMap();
		modele.put("retour", retour);
		modele.put("type", request.getParameter("retour"));
		modele.put("listeAnnexes", daoCadastre.getListeAnnexe(id_marche_public));
		modele.put("listeLiens", daoCadastre.getListeLien(id_marche_public));
		modele.put("marche", daoCadastre.getMarche(id_marche_public));
		return new ModelAndView("afficherMarche", modele);
	}

	private ModelAndView formAjouterMP(HttpServletRequest request, HttpServletResponse response) {
		HashMap modele = new HashMap();
		modele.put("retour", "index.cad");
		modele.put("marche", new Marche());
		modele.put("listeTypeMarche", daoCadastre.getListeTypeMarche());
		modele.put("listeDenomination", daoCadastre.getListeDenominationReference());
		return new ModelAndView("formulaireAjouter", modele);
	}

	private ModelAndView ajouterMP(HttpServletRequest request, HttpServletResponse response) {
		if(verifFormulaireMarche(request)) {
			int id_marche_public = daoCadastre.addMarche(getFormulaireMarche(request));
			Marche tmpMarche = daoCadastre.getMarche(""+id_marche_public);
			int idReference = daoCadastre.getIdReference(tmpMarche.getTypeMarche().substring(0, 1)) + 1;
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMinimumIntegerDigits(3);
			String reference = tmpMarche.getTypeMarche().substring(0, 1) + "-" + tmpMarche.getDate_BP().split("-")[0] + "-0" + nf.format(idReference);
			tmpMarche.setReference(reference);
			daoCadastre.modifierMarche(tmpMarche);
			return index(request, response);
		} else {
			HashMap modele = new HashMap();
			modele.put("retour", "index.cad");
			modele.put("listeDenomination", daoCadastre.getListeDenominationReference());
			modele.put("marche", getFormulaireMarche(request));
			modele.put("listeTypeMarche", daoCadastre.getListeTypeMarche());
			return new ModelAndView("formulaireAjouter", modele);
		}
	}

	private boolean verifFormulaireTypeMarche(HttpServletRequest request) {
		String type_marche = request.getParameter("type_marche");
		if (type_marche.equals("")) {
			request.setAttribute("erreur", "Vous devez indiquer un intitulé");
			return false;
		}
		return true;
	}

	private boolean verifFormulaireMarche(HttpServletRequest request) {
		String type = request.getParameter("type");
		if (type.equals("-----> choisir un type")) {
			request.setAttribute("erreur", "Vous devez choisir un type");
			return false;
		}
		String intitule = request.getParameter("intitule");
		if (intitule.equals("")) {
			request.setAttribute("erreur", "Vous devez indiquer un intitulé");
			return false;
		}
		String adjudicataire = request.getParameter("adjudicataire");
		if (adjudicataire.equals("")) {
			request.setAttribute("erreur", "Vous devez indiquer un adjudicataire");
			return false;
		}
		String date_bp = request.getParameter("date_bp");
		if (date_bp.equals("")) {
			request.setAttribute("erreur", "Vous devez indiquer une date de BP");
			return false;
		}
		String adjudicateur = request.getParameter("adjudicateur");
		if (adjudicateur.equals("")) {
			request.setAttribute("erreur", "Vous devez indiquer un adjudicateur");
			return false;
		}
		String adresse = request.getParameter("adresse");
		if (adresse.equals("")) {
			request.setAttribute("erreur", "Vous devez indiquer une adresse");
			return false;
		}
		String description = request.getParameter("description");
		if (description.equals("")) {
			request.setAttribute("erreur", "Vous devez indiquer une description");
			return false;
		}
		String date_debut = request.getParameter("date_debut");
		if (date_debut.equals("")) {
			request.setAttribute("erreur", "Vous devez indiquer une date de début");
			return false;
		}
		String date_fin = request.getParameter("date_fin");
		if (date_fin.equals("")) {
			request.setAttribute("erreur", "Vous devez indiquer une date de fin");
			return false;
		}
		return true;
	}

	private TypeMarche getFormulaireType(HttpServletRequest request) {
		String type_marche = request.getParameter("type_marche");
		TypeMarche typeMarche = new TypeMarche();
		typeMarche.setType_marche(type_marche);
		return typeMarche;
	}

	private Marche getFormulaireMarche(HttpServletRequest request) {
		Marche marche = new Marche();
		marche.setId_type_marche(request.getParameter("type"));
		marche.setIntitule(request.getParameter("intitule"));
		marche.setAdjudicataire(request.getParameter("adjudicataire"));
		marche.setAdjudicateur(request.getParameter("adjudicateur"));
		marche.setAdresse(request.getParameter("adresse"));
		marche.setDescription(request.getParameter("description"));
		marche.setDate_BP(request.getParameter("date_bp"));
		marche.setDate_debut(request.getParameter("date_debut"));
		marche.setDate_fin(request.getParameter("date_fin"));
		marche.setId_marche_public(request.getParameter("id_marche_public"));
		marche.setReference(request.getParameter("reference"));
		String flag = request.getParameter("flag");
		if (flag == null || flag.equals("")) {
			flag = "0";
		}
		marche.setFlag(flag);
		return marche;
	}

	public DaoMySQL getDao() {
		return dao;
	}

	public void setDao(DaoMySQL dao) {
		this.dao = dao;
	}

	public DaoCadastre getDaoCadastre() {
		return daoCadastre;
	}

	public void setDaoCadastre(DaoCadastre daoCadastre) {
		this.daoCadastre = daoCadastre;
	}
}
