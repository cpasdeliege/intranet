<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@page import="be.cpasdeliege.intranet.informatique.model.PrivilegeInformatique"%>
<%@page import="be.cpasdeliege.intranet.cadastre.model.*"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Intranet CPAS de Li�ge</title>
<link rel="stylesheet" href="site.css" type="text/css">
<link rel="stylesheet" href="annuaire.css" type="text/css">
<style type="text/css">
#gauche {
	float: left; /* Le cadre sort du flux */
	/* border: 2px solid #DAA520; */ //
	background-color: #DAA520;
	width: 595px;
}

#droite {
	float: right; /* ou margin-left:202px;  */
	/* border: 2px solid gray; */ //
	background-color: gray;
	width: 595px;
}

#principal {
	width: 100%;
	/* border: 2px solid gray; background-color : green; */
	margin-left: auto;
	margin-right: auto;
}

#tableau {
	width: 90%;
	/* border: 2px solid gray; background-color : green; */
	margin-left: auto;
	margin-right: auto;
}

.tabIndex {
	width: 95%;
}
</style>

<script type="text/javascript">
<!-- 

function supprimerMarche(id_marche_public, reference, type_marche) {
	var mdp = confirm('Confirmer la suppression du march� ?\n\n ' + reference);
   if(mdp) {
	   document.location.href='index.cad?action=deleteMarche&id_marche_public=' + id_marche_public + '&retour=' + type_marche;
   }
}

function deflagMarche(id_marche_public, reference, type_marche) {
	var mdp = confirm('Confirmer la publication du march� ?\n\n ' + reference);
   if(mdp) {
	   document.location.href='index.cad?action=deflagMarche&id_marche_public=' + id_marche_public + '&retour=' + type_marche;
   }
}
	
function flagMarche(id_marche_public, reference, type_marche) {
   var mdp = confirm('Confirmer l\'archivage du march� ?\n\n ' + reference);
   if(mdp) {
   	document.location.href='index.cad?action=suppMarche&id_marche_public=' + id_marche_public + '&retour=' + type_marche;
   }
}



//-->
</script>
</head>
<% 
	List<TypeMarche> listeTypeMarche = (List<TypeMarche>)request.getAttribute("listeTypeMarche");
	TypeMarche typeMarche = (TypeMarche)request.getAttribute("typeMarche");
	String info = (String)request.getAttribute("info");
	if(info == null) info = "";
	if(typeMarche == null) {
		typeMarche = new TypeMarche();
		if(info.equals("recherche")) {
			typeMarche.setId_type_marche("recherche");
		} else if(info.equals("supprim�s")) {
			typeMarche.setId_type_marche("supp");
		} else {
			typeMarche.setId_type_marche("tous");
		}
	}
	
	PrivilegeInformatique privileges = (PrivilegeInformatique)request.getSession().getAttribute("privilegeInformatique");
	if(privileges == null) {
		privileges = new PrivilegeInformatique();
	}
	
%>
<body class="bodyCadastre" >
	<jsp:include page="../entete.jsp"></jsp:include>
	<div>
		<hr>
		&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.cad">Cadastre des March�s Publics</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">March�s Publics ${typeMarche.type_marche} <% if(info.equals("supprim�s")) {%>Archives<% } %></a>
		<hr>
	</div>
<div id="principal">
	
	
	<div id="tableau">
	<table width="100%">
	<tr>
		<td align="left"><h1>March�s publics ${typeMarche.type_marche} <% if(info.equals("supprim�s")) {%>Archives<% } %> <% if(info.equals("recherche")) {%> - Recherche : ${motcle}<% } %></h1> </td>
		<td align="right"><a href="index.cad?action=telecharger" style="color: black;"><img src="<%= Util.getIcone("fichier.xls") %>" border="0" height="40">&nbsp;T�l�charger le listing</a></td>
	</tr>
	</table>
		<table width="100%" cellpadding="10">
			<tr height="50px">
				<th align="left"><a href="index.cad?action=listeMP&amp;orderBy=intitule&amp;type=<%= typeMarche.getId_type_marche() %>&amp;motcle=${motcle}" style="text-decoration: none;color: black;">Intitul�</a></th>
				<th align="left"><a href="index.cad?action=listeMP&amp;orderBy=reference&amp;type=<%= typeMarche.getId_type_marche() %>&amp;motcle=${motcle}" style="text-decoration: none;color: black;">R�f�rence</th>
				<th align="left"><a href="index.cad?action=listeMP&amp;orderBy=adjudicataire&amp;type=<%= typeMarche.getId_type_marche() %>&amp;motcle=${motcle}" style="text-decoration: none;color: black;">Adjudicataire</th>
				<th><a href="index.cad?action=listeMP&amp;orderBy=date_BP&amp;type=<%= typeMarche.getId_type_marche() %>&amp;motcle=${motcle}" style="text-decoration: none;color: black;">Date BP/CAS</th>
				<th align="left"><a href="index.cad?action=listeMP&amp;orderBy=adjudicateur&amp;type=<%= typeMarche.getId_type_marche() %>&amp;motcle=${motcle}" style="text-decoration: none;color: black;">P. Adjudicateur</th>
				<th align="left"><a href="index.cad?action=listeMP&amp;orderBy=id_type_marche&amp;type=<%= typeMarche.getId_type_marche() %>&amp;motcle=${motcle}" style="text-decoration: none;color: black;">Type</th>
				<th><a href="index.cad?action=listeMP&amp;orderBy=date_debut&amp;type=<%= typeMarche.getId_type_marche() %>&amp;motcle=${motcle}" style="text-decoration: none;color: black;">Date d�but</th>
				<th><a href="index.cad?action=listeMP&amp;orderBy=date_fin&amp;type=<%= typeMarche.getId_type_marche() %>&amp;motcle=${motcle}" style="text-decoration: none;color: black;">Date fin</th>
				<% if(privileges.isCadastreMP()) { %>
				<% if(!info.equals("supprim�s")) {%>
				<th></th>
				<th></th>
				<% } %>
				<% } %>
			</tr>
			<% int i = 0; %>
			<c:forEach items="${listeMarche}" var="marche">
			<% i++; %>
			<% if(i%2 == 0) {  %>
				<tr class="ligne_paire">
				
			<% } else { %>
				<tr class="ligne_impaire">
				
			<% } %>
				
					<td width="500px"><a style="color: black;font-weight: bold;" href="index.cad?action=afficherMP&amp;id_marche_public=${marche.id_marche_public}&amp;retour=<%= typeMarche.getId_type_marche() %>&amp;motcle=${motcle}">${marche.intitule}</a></td>
					<td>${marche.reference}</td>
					<td>${marche.adjudicataire}</td>
					<td align="center">${marche.date_BPFormat}</td>
					<td>${marche.adjudicateur}</td>
					<td>${marche.typeMarche}</td>
					<td align="center">${marche.date_debutFormat}</td>
					<td align="center">${marche.date_finFormat}</td>
					<% if(privileges.isCadastreMP()) { %>
					<% if(!info.equals("supprim�s")) {%>
					<td><a href="index.cad?action=formModifierMP&amp;id_marche_public=${marche.id_marche_public}&amp;retour=${typeMarche.id_type_marche}" style="color: black;"><img src="images/edit.png" border="0" width="20"></a></td>
					<!-- <td><a href="index.cad?action=suppMarche&amp;id_marche_public=${marche.id_marche_public}&amp;retour=${typeMarche.id_type_marche}" style="color: black;"><img src="images/refuse.png" border="0" width="20"></a></td>  -->
					<td><a onclick="flagMarche('${marche.id_marche_public}', '${marche.reference}', '${typeMarche.id_type_marche}')" style="color: black;"><img src="images/refuse.png" border="0" width="20"></a></td>
					<% } else { %>
					<!-- <td><a href="index.cad?action=deflagMarche&amp;id_marche_public=${marche.id_marche_public}&amp;retour=${typeMarche.id_type_marche}" style="color: black;"><img src="images/ark2.png" border="0" width="20"></a></td>  -->
					<td><a onclick="deflagMarche('${marche.id_marche_public}', '${marche.reference}', '${typeMarche.id_type_marche}')" style="color: black;"><img src="images/ark2.png" border="0" width="20"></a></td>
					<!-- <td><a href="index.cad?action=deleteMarche&amp;id_marche_public=${marche.id_marche_public}&amp;retour=${typeMarche.id_type_marche}" style="color: black;"><img src="images/refuse.png" border="0" width="20"></a></td> -->	
					<td><a onclick="supprimerMarche('${marche.id_marche_public}', '${marche.reference}', '${typeMarche.id_type_marche}')" style="color: black;"><img src="images/refuse.png" border="0" width="20"></a></td>
					<% } %>
					<% } %>
				</tr>
			</c:forEach>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><a href="index.cad"><img alt="retour" src="images/administration/retour.jpg" border="0"></a></td>
			</tr>
		</table>
	
	</div>	
</div>





	<jsp:include page="../basDePage.jsp"></jsp:include>
</body>
</html>