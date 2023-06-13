<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="be.cpasdeliege.intranet.informatique.model.PrivilegeInformatique"%>
<%@page import="be.cpasdeliege.intranet.cadastre.model.*"%>
<%@page import="be.cpasdeliege.intranet.utils.*"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet CPAS de Liège</title>
<link rel="stylesheet" href="site.css" type="text/css">
<style type="text/css">
#gauche {
	float: left; /* Le cadre sort du flux */
	/* border: 2px solid #DAA520; */
	width: 595px;
}

#droite {
	float: right; /* ou margin-left:202px;  */
	/* border: 2px solid gray; */
	width: 595px;
}

#principal {
	width: 1000px;
	/* border: 2px solid gray; */
	margin-left: auto;
	margin-right: auto;
}

.tabIndex {
	width: 95%;
}
</style>
<script type="text/javascript">
<!-- 

function supprimerLien(id_lien, reference) {
	var mdp = confirm('Confirmer la suppression du lien ?\n\n ' + reference);
   if(mdp) {
	   document.location.href='index.cad?action=suppLien&id_lien=' + id_lien;
   }
}

function supprimerAnnexe(id_annexe, reference) {
	var mdp = confirm('Confirmer la suppression de l\'annexe ?\n\n ' + reference);
   if(mdp) {
	   document.location.href='index.cad?action=suppAnnexe&id_annexe=' + id_annexe;
   }
}





//-->
</script>
</head>
<%
List<Annexe> listeAnnexes = (List<Annexe>)request.getAttribute("listeAnnexes");
List<Lien> listeLiens = (List<Lien>)request.getAttribute("listeLiens");
Marche marche  = (Marche)request.getAttribute("marche");

PrivilegeInformatique privileges = (PrivilegeInformatique)request.getSession().getAttribute("privilegeInformatique");
if(privileges == null) {
	privileges = new PrivilegeInformatique();
}
%>
<body class="body" style="font-family:  arial">
	<jsp:include page="../entete.jsp"></jsp:include>
	<div>
		<hr>
		&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.cad">Cadastre des Marchés Publics</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">Afficher Marché Public</a>
		<hr>
	</div>
<div id="principal">
<br>
<table cellpadding="0" cellspacing="0"  class="tableau_container" width="100%">
	<tr height="25">
		<td></td>
		<td>
			<table>
				<tr>
					<td class="titre_tableau" style="font-size: xx-large;">${marche.intitule}</td>
					<td>
					</td>
				</tr>
			</table>
		</td>
		<td></td>
	</tr>
	<tr height="25">
		<td width="22" background="images/administration/cadre/area_top_left.gif"></td>
		<td background="images/administration/cadre/area_top.gif"></td>
		<td width="22" background="images/administration/cadre/area_top_right.gif"></td>
	</tr>
	<tr>
		<td background="images/administration/cadre/area_left.gif"></td>
		<td>
			
			<table width="100%">
				<tr>
					<td style="font-weight: bold;" align="right" width="160px">type : </td>
					<td>${marche.typeMarche}</td>
				</tr>
				<tr>
					<td style="font-weight: bold;" align="right">Référence : </td>
					<td>${marche.reference}</td>
				</tr>
				<tr>
					<td style="font-weight: bold;" align="right">Adjudicataire : </td>
					<td>${marche.adjudicataire}</td>
				</tr>
				<tr>
					<td style="font-weight: bold;" align="right">Adresse : </td>
					<td>${marche.adresse}</td>
				</tr>
				<tr>
					<td style="font-weight: bold;" align="right">Date BP/CAS : </td>
					<td>
						${marche.date_BPFormat}
					</td>
				</tr>
				<tr>
					<td style="font-weight: bold;" align="right">Pouvoir Adjudicateur : </td>
					<td>${marche.adjudicateur}</td>
				</tr>
				<tr>
					<td style="font-weight: bold;" align="right" valign="top">Description : </td>
					<td>${marche.descriptionHtml}</td>
				</tr>
				<tr>
					<td align="right"></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td style="font-weight: bold;" align="right">Date de début : </td>
					<td>
						${marche.date_debutFormat}
					</td>
				</tr>
				<tr>
					<td style="font-weight: bold;" align="right">Date de fin : </td>
					<td>
						${marche.date_finFormat}
					</td>
				</tr>
				<tr>
					<td align="right"></td>
					<td>&nbsp;</td>
				</tr><tr>
					<td align="right"></td>
					<td>&nbsp;</td>
				</tr>

				
				</tr>
				
				
				
				
				
				
				
				
				
				
				
				<tr>
					<td style="font-weight: bold;" align="right" valign="top">Liens : </td>
					<td>
						<table>
						<%
							for(Lien lien : listeLiens) {
						%>		
							<tr>
								<td>
									<a target="_blank" href="<%= lien.getLien() %>" style="color: black;"><%= lien.getNom() %></a>
									<% if(privileges.isCadastreMP()) { %>
									<!-- &nbsp;&nbsp;&nbsp;<a href="index.cad?action=suppLien&amp;id_lien=<%= lien.getId_lien() %>" style="color: black;"><img src="images/refuse.png" border="0" width="20"></a> -->
									&nbsp;&nbsp;&nbsp;<a onclick="supprimerLien('<%= lien.getId_lien() %>', '<%= lien.getNom().replace("'","\'") %>')" style="color: black;"><img src="images/refuse.png" border="0" width="20"></a>
									<% } %>
								</td>
							</tr>	
						<%		
							}
						%>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>
									<% if(privileges.isCadastreMP()) { %>
									<h4>Ajouter un lien</h4>
									<form method="post" enctype="multipart/form-data" action="utilsTache.admin">
										<input type="hidden" name="action" value="ajouterLienCadastre">
										<input type="hidden" name="id_marche_public" value="${marche.id_marche_public}">
										<input type="hidden" name="retour" value="${type}">
										nom à afficher : 
										<input type="text" name="nom"><br>
										lien web : 
										<input type="text" name="lien" size="100"/>
										<input type="submit" value="Ajouter"/>
									</form>
									<% } %>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				
				
				
				<tr>
					<td align="right"></td>
					<td>&nbsp;</td>
				</tr><tr>
					<td align="right"></td>
					<td>&nbsp;</td>
				</tr>
				
				
				
				
				
				
				<tr>
					<td style="font-weight: bold;" align="right" valign="top">Documents : </td>
					<td>
						<table>
						<%
							for(Annexe annexe : listeAnnexes) {
						%>		
							<tr>
								<td>
									<a href="index.regTrav?param=annexeCadastre&amp;id_annexe=<%= annexe.getId_annexe() %>" style="color: black;"><img src="<%= Util.getIcone(annexe.getNom()) %>" border="0" height="20">&nbsp;<%= annexe.getNom() %></a>
									<% if(privileges.isCadastreMP()) { %>
									<!-- &nbsp;&nbsp;&nbsp;<a href="index.cad?action=suppAnnexe&amp;id_annexe=<%= annexe.getId_annexe() %>" style="color: black;"><img src="images/refuse.png" border="0" width="20"></a> -->
									&nbsp;&nbsp;&nbsp;<a onclick="supprimerAnnexe('<%= annexe.getId_annexe() %>', '<%= annexe.getNom() %>')" style="color: black;"><img src="images/refuse.png" border="0" width="20"></a>
									<% } %>
								</td>
							</tr>	
						<%		
							}
						%>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>
									<% if(privileges.isCadastreMP()) { %>
									<h4>Ajouter un fichier</h4>
									<form method="post" enctype="multipart/form-data" action="utilsTache.admin">
										<input type="hidden" name="action" value="ajouterAnnexeCadastre">
										<input type="hidden" name="id_marche_public" value="${marche.id_marche_public}">
										<input type="hidden" name="retour" value="${type}">
										nom à afficher : 
										<input type="text" name="nom"><br>
										<input type="file" name="annexe" />
										<input type="submit" value="Ajouter"/>
									</form>
									<% } %>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<% if(marche.getFlag().equals("0")) { %>
						<td><a href="${retour}"><img alt="retour" src="images/administration/retour.jpg" border="0"></a></td>
					<% } else { %>
						<td><a href="index.cad?action=listeMP&amp;type=supp"><img alt="retour" src="images/administration/retour.jpg" border="0"></a></td>
					<% } %>
					<td align="right"></td>
				</tr>
			</table>
		</td>
		<td background="images/administration/cadre/area_right.gif"></td>
	</tr>
	<tr height="25">
		<td background="images/administration/cadre/area_bottom_left.gif"></td>
		<td background="images/administration/cadre/area_bottom.gif"></td>
		<td background="images/administration/cadre/area_bottom_right.gif"></td>
	</tr>
</table>
	
	
</div>





	<jsp:include page="../basDePage.jsp"></jsp:include>
</body>
</html>