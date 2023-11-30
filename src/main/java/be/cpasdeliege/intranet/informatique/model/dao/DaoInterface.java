package be.cpasdeliege.intranet.informatique.model.dao;

import java.util.List;

import be.cpasdeliege.intranet.informatique.controler.formulaire.FormulaireImprimanteOrdinateurAssigner;
import be.cpasdeliege.intranet.informatique.model.Departement;
import be.cpasdeliege.intranet.informatique.model.EmailGenerique;
import be.cpasdeliege.intranet.informatique.model.Imprimante;
import be.cpasdeliege.intranet.informatique.model.IntranetLog;
import be.cpasdeliege.intranet.informatique.model.Pole;
import be.cpasdeliege.intranet.informatique.model.PrivilegeInformatique;
import be.cpasdeliege.intranet.informatique.model.Statistiques;
import be.cpasdeliege.intranet.informatique.model.StatistiquesAnnuaire;
import be.cpasdeliege.intranet.informatique.model.StatistiquesFormations;
import be.cpasdeliege.intranet.informatique.model.Tache;
import be.cpasdeliege.intranet.informatique.model.TicketItem;
import be.cpasdeliege.intranet.informatique.model.Utilisateur;

public interface DaoInterface {

	public abstract String getAliasDB();

	public abstract void setAliasDB(String aliasDB);

	public abstract String getDriver();

	public abstract void setDriver(String driver);

	public abstract String getPassword();

	public abstract void setPassword(String password);

	public abstract String getUser();

	public abstract void setUser(String user);

	public abstract void connect();

	public abstract void addService(DaoService service);

	public abstract void updateService(DaoService service);

	public abstract void supprimerService(String service);

	public abstract List getListeServices(String param);

	public abstract void addPersonnel(DaoPersonnel employe);

	public abstract void supprimerPersonnel(String nom, String prenom);

	public abstract List getListePersonnels(String nom, String prenom);

	public abstract void updatePersonnel(DaoPersonnel personnel);

	public abstract void addOrdinateur(DaoOrdinateur ordinateur);

	public abstract void modifierOrdinateur(DaoOrdinateur ordinateur);

	public abstract void supprimerOrdinateur(String ordinateur);

	public abstract List getListeOrdinateurs(String ordinateur, String service);

	public abstract List getListePersonnelService(String service, String nom, String prenom);

	public abstract List getListePersonnelService(int id_service, String nom, String prenom);

	public abstract List getListePersonnelDepartement(int id_departement, String nom, String prenom);

	public abstract List getListePersonnelServiceRequete(String requete);

	public abstract List getListePersonnelService(String service, String nom, String prenom, String orderBy);

	public abstract List getListePersonnelService(String service, String nom, String prenom, String fonction,
			String orderBy);

	public abstract List getListPole();

	public abstract DaoPole getPoleById(long id_pole);

	public abstract DaoPole getPoleByName(String nom);

	public abstract List getListDepartement();

	public abstract DaoDepartement getDepartementById(int id_departement);

	public abstract DaoDepartement getDepartementByName(String nom);

	public abstract List getListDepartementByPole(int id_pole);

	public abstract List getListDepartementByPole(String nom);

	public abstract List getListService();

	public abstract List getListServiceByDepartement(int id_departement);

	public abstract List getListServiceByDepartement(String nom);

	public abstract DaoPole getPoleOfService(long id_service);

	public abstract DaoDepartement getDepartementOfService(long id_service);

	public abstract void assignerPersonnel(DaoPersonnelService employe);

	public abstract void desassignerPersonnel(String service, String nom, String prenom);

	public abstract List getListeImprimanteOrdinateur(String ordinateur, String imprimante);

	public abstract void assignerImprimante(FormulaireImprimanteOrdinateurAssigner imprimanteReseau);

	public abstract void desassignerImprimante(String ordinateur, String imprimante);

	public abstract void addImprimante(Imprimante imprimante);

	public abstract void supprimerImprimante(String numeroSerie);

	public abstract List getListeImprimante(String numeroSerie, String ordinateur);

	public abstract List getListeImprimante(String numeroSerie, String ordinateur, String orderBy);

	public abstract List getListeImprimanteService(String service);
	// public abstract List getListeOrdinateursImprimlanteAssigner(String
	// imprimante);

	public abstract List getListeOrdinateurPersonnel(String ordinateur, String nom, String prenom);

	public abstract void assignerOrdinateur(DaoOrdinateurPersonnel employeOrdinateur);

	public abstract void desassignerOrdinateur(String ordinateur, String nom, String prenom);

	public abstract List getTypeOrdinateur();

	public abstract List getTypeOS();

	public abstract List getTypeEcran();

	public abstract List getTypeGraveur();

	public abstract List getTypeFonction();

	public abstract List getTypeImprimante();

	public abstract void ajouterFonction(String fonction);

	public abstract void supprimerFonction(String fonction);

	public abstract void addUtilisateur(Utilisateur utilisateur);

	public abstract List getUtilisateur(String login, String nom, String prenom);

	public abstract void supprimerUtilisateur(String nom, String prenom);

	public abstract void supprimerUtilisateur(String login);

	public abstract void addPrivilègeInformatique(PrivilegeInformatique privilege);

	public abstract void supprimerPrivilègeInformatique(String login);

	public abstract PrivilegeInformatique getPrivilegeInformatique(String login);

	public abstract List getFonctionService(String service);

	public abstract Statistiques getStatistiques();

	public abstract StatistiquesAnnuaire getStatistiquesAnnuaire();

	public abstract StatistiquesAnnuaire getStatistiquesRues();

	public abstract StatistiquesFormations getStatistiquesFormations();

	public abstract StatistiquesAnnuaire getStatistiquesIntranet();

	public abstract int addTache(Tache tache);

	public abstract List getListeTache();

	public abstract void updateTache(Tache tache);

	public abstract Tache getTache(String idPlanning);

	public abstract List getListeTacheEnCours(String service, String nom, String prenom, String ordinateur,
			String order);

	public abstract List getListeTacheEnAttente(String service, String nom, String prenom, String ordinateur,
			String order);

	public abstract List getListeTacheFinie(String service, String nom, String prenom, String ordinateur, String order);

	public abstract TicketItem getTicketItem(int idTicketItem);

	public abstract List getListeTicketItem(int idPlanning);

	public abstract void addTicketItem(TicketItem ticket);

	public abstract void supprimerTicketItem(int idTicketItem, String login);
	
	public abstract void updateTicketItem(int idTicket, String texte);

	public abstract void addEmailGenerique(EmailGenerique email);

	public abstract List getListeEmailGenerique();

	public abstract void supprimerEmailGenerique(String email);

	public abstract void addLog(IntranetLog log);

	public abstract List getLog(String request);

	public abstract List getLogFormationUtilisateur();

	public abstract List getLogFormationAdministration();

	public abstract List getWarnings();

	public abstract List<DaoPersonnel> getPersonnelDsiUser();

	public abstract List<DaoPersonnel> getPersonnelDsichef();

	public abstract List<DaoPersonnel> getPersonnelDsiDirection();

	public abstract List<DaoPersonnel> getPersonnelDsiInfo();

}