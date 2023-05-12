package be.cpasdeliege.intranet.informatique.model.domain;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Document;

import be.cpasdeliege.intranet.informatique.controler.formulaire.FormulaireImprimanteOrdinateurAssigner;
import be.cpasdeliege.intranet.informatique.controler.formulaire.FormulaireOrdinateur;
import be.cpasdeliege.intranet.informatique.controler.formulaire.FormulaireOrdinateurPersonnelAssigner;
import be.cpasdeliege.intranet.informatique.controler.formulaire.FormulairePersonnel;
import be.cpasdeliege.intranet.informatique.controler.formulaire.FormulaireService;
import be.cpasdeliege.intranet.informatique.controler.formulaire.FormulaireServicePersonnelAssigner;
import be.cpasdeliege.intranet.informatique.model.Departement;
import be.cpasdeliege.intranet.informatique.model.EmailGenerique;
import be.cpasdeliege.intranet.informatique.model.Imprimante;
import be.cpasdeliege.intranet.informatique.model.Ordinateur;
import be.cpasdeliege.intranet.informatique.model.Personnel;
import be.cpasdeliege.intranet.informatique.model.Pole;
import be.cpasdeliege.intranet.informatique.model.PrivilegeInformatique;
import be.cpasdeliege.intranet.informatique.model.Service;
import be.cpasdeliege.intranet.informatique.model.Statistiques;
import be.cpasdeliege.intranet.informatique.model.StatistiquesAnnuaire;
import be.cpasdeliege.intranet.informatique.model.StatistiquesFormations;
import be.cpasdeliege.intranet.informatique.model.Tache;
import be.cpasdeliege.intranet.informatique.model.TicketItem;
import be.cpasdeliege.intranet.informatique.model.Utilisateur;
import be.cpasdeliege.intranet.informatique.model.dao.DaoInterface;
import be.cpasdeliege.intranet.informatique.model.dao.DaoPersonnel;

public interface DomainInterface {

	public abstract DaoInterface getDao();

	public abstract void setDao(DaoInterface dao);

	public abstract void testBD();

	public abstract void addService(FormulaireService service);

	public abstract void supprimerService(String service);

	public abstract void modifierService(FormulaireService service);

	public abstract List<Service> getListeServices();

	public abstract Service getService(String service);

	public abstract FormulaireService getFormulaireService(String service);

	public abstract void addPersonnel(FormulairePersonnel employe);

	public abstract FormulairePersonnel getFormulairePersonnel(String nom, String prenom);

	public abstract List<DaoPersonnel> getListePersonnel();

	public abstract List<String> getListePersonnelTexte();

	public abstract List<String> getListePersonnelInfoTexte();

	public abstract Personnel getPersonnel(String nom, String prenom);

	public abstract void modifierPersonnel(FormulairePersonnel employe);

	public abstract void supprimerPersonnel(String nom, String prenom);

	public abstract List getListePersonnelService();

	public abstract List getListePersonnelGestionService(String service);

	public abstract List getListePersonnelServiceAlphabetique(String lettre);

	public abstract List getListePersonnelServiceRequete(String requete);

	public abstract List getListePersonnelService(int id_service, String nom, String prenom);

	public abstract List getListePersonnelDepartement(int id_departement, String nom, String prenom);

	public abstract List getListePersonnelService(String service, String nom, String prenom);

	public abstract List getListePersonnelService(String service, String nom, String prenom, String orderBy);

	public abstract List getListePersonnelServiceAssigner(String service);

	public abstract List getListeServicePersonnelAssigner(String nom, String prenom);

	public abstract void assignerPersonnelService(FormulaireServicePersonnelAssigner formulaire);

	public abstract void modifierPersonnelService(FormulaireServicePersonnelAssigner formulaire);

	public abstract void desassignerPersonnelService(String service, String nom, String prenom);

	public abstract void addOrdinateur(FormulaireOrdinateur formulaireOrdinateur);

	public abstract FormulaireOrdinateur getFormulaireOrdinateur(String ordinateur);

	public abstract List<Ordinateur> getListeOrdinateurs();

	public List<Ordinateur> getListeOrdinateurService(String service);

	public abstract Ordinateur getOrdinateur(String ordinateur);

	public abstract void modifierOrdinateur(FormulaireOrdinateur formulaireOrdinateur);

	public abstract void supprimerOrdinateur(String ordinateur);

	public abstract List getListeOrdinateurPersonnel(String ordinateur, String nom, String prenom);

	public abstract List getListePersonnelOrdinateurAssigner(String ordinateur);

	public abstract List getListeOrdinateurPersonnelAssigner(String nom, String prenom);

	public abstract void assignerOrdinateurPersonnel(FormulaireOrdinateurPersonnelAssigner formulaire);

	public abstract void desassignerOrdinateurPersonnel(String ordinateur, String nom, String prenom);

	public abstract List getTypeOrdinateur();

	public abstract List getTypeOS();

	public abstract List getTypeEcran();

	public abstract List getTypeGraveur();

	public abstract List getTypeFonction();

	public abstract List getTypeImprimante();

	public abstract void ajouterFonction(String fonction);

	public abstract void supprimerFonction(String fonction);

	public abstract void addUtilisateur(String nom, String prenom);

	public abstract void supprimerUtilisateur(String login);

	public abstract void supprimerUtilisateur(String nom, String prenom);

	public abstract Utilisateur getUtilisateur(String nom, String prenom);

	public abstract Utilisateur getUtilisateur(String login);

	public abstract Utilisateur authentification(String login, String mdp);

	public abstract void reinitialiserMotDePasseUtilisateur(String login);

	public abstract void changerMotDePasseUtilisateur(String login, String mdp);

	public abstract void changerAccesIntranetUtilisateur(String login, String acces);

	public abstract void addPrivilègeInformatique(PrivilegeInformatique privilege);

	public abstract void supprimerPrivilègeInformatique(String login);

	public abstract void modifierPrivilègeInformatique(PrivilegeInformatique privilege);

	public abstract PrivilegeInformatique getPrivilegeInformatique(String login);

	public abstract void addImprimante(Imprimante imprimante);

	public abstract void supprimerImprimante(String numeroSerie);

	public abstract void modifierImprimante(Imprimante imprimante);

	public abstract List getListeImprimante(String numeroSerie, String ordinateur);

	public abstract List getListeImprimanteService(String service);

	public abstract Imprimante getImprimante(String numeroSerie);

	public abstract List getListeOrdinateursImprimlanteAssigner(String numeroSerie);

	public abstract List getListeImprimlanteOrdinateursAssigner(String ordinateur);

	public abstract List getListeImprimanteOrdinateur(String ordinateur, String imprimante);

	public abstract List getListeImprimanteReseau(String ordinateur);

	public abstract void assignerImprimante(FormulaireImprimanteOrdinateurAssigner imprimanteReseau);

	public abstract void desassignerImprimante(String ordinateur, String imprimante);

//	public abstract Logger getLogger();

	public abstract Statistiques getStatistiques();

	public abstract StatistiquesAnnuaire getStatistiquesAnnuaire();

	public abstract StatistiquesAnnuaire getStatistiquesRues();

	public abstract StatistiquesFormations getStatistiquesFormations();

	public abstract StatistiquesAnnuaire getStatistiquesIntranet();

	public abstract void log(HttpServletRequest request, HttpServletResponse response);

	public abstract void log(HttpServletRequest request, HttpServletResponse response, String message);

	public abstract List getLog(String request);

	public abstract List getLogFormationUtilisateur();

	public abstract List getLogFormationAdministration();

	public abstract List getWarnings();

	public abstract int addTache(Tache tache);

	public abstract Tache getTache(String idPlanning);

	public abstract void updateTache(Tache tache);

	public abstract List getListeTacheEnCours(String service, String nom, String prenom, String ordinateur,
			String order);

	public abstract List getListeTacheEnAttente(String service, String nom, String prenom, String ordinateur,
			String order);

	public abstract List getListeTacheFinie(String service, String nom, String prenom, String ordinateur, String order);

	public abstract List getListeTache();

	public abstract List getListeTicketItem(int idPlanning);

	public abstract void addTicketItem(TicketItem ticket);

	public abstract void addEmailGenerique(EmailGenerique email);

	public abstract List getListeEmailGenerique();

	public abstract void supprimerEmailGenerique(String email);

	public abstract List<String> getListePathPhoto(List pers);

	public abstract List<String> getListeCodeAS(List pers);

	public abstract Document getAnnuaireServiceXML();

	public abstract Document getAnnuaireAlphabetiqueXML();

	public abstract List<DaoPersonnel> getListeAccesInternet();

	public abstract List<String> getListeEmail(List pers);

	public abstract List<DaoPersonnel> getPersonnelDsiUser();

	public abstract List<DaoPersonnel> getPersonnelDsichef();

	public abstract List<DaoPersonnel> getPersonnelDsiDirection();

	public abstract List<DaoPersonnel> getPersonnelDsiInfo();

	public abstract List getListPole();

	public abstract Pole getPoleById(long id_pole);

	public abstract Pole getPoleOfService(long id_service);

	public abstract Departement getDepartementOfService(long id_service);

	public abstract Pole getPoleByName(String nom);

	public abstract List getListDepartement();

	public abstract Departement getDepartementById(int id_departement);

	public abstract Departement getDepartementByName(String nom);

	public abstract List getListDepartementByPole(int id_pole);

	public abstract List getListDepartementByPole(String nom);

	public abstract List getListService();

	public abstract List getListServiceByDepartement(int id_departement);

	public abstract List getListServiceByDepartement(String nom);

}