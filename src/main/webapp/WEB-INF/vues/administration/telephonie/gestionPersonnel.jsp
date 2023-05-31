<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="be.cpasdeliege.intranet.informatique.model.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="cpas.css" type="text/css">
<script type="text/javascript">
<!-- 

function reinitialiserMotDePasse(login) {
   var mdp = confirm('Confirmer la ré-initialisation du mot de passe ?');
   if(mdp) {
   	document.location.href='reinitialiserMotDePasse.admin?login=' + login;
   }
}

function supprimerUtilisateur(login) {
   var prout = confirm('Confirmer la suppression de l\'utilisateur ?');
   if(prout) {
   	document.location.href='supprimerUtilisateurIntranet.admin?login=' + login;
   }
}

function supprimerPhoto(nom, prenom) {
	   var test = confirm('Confirmer la suppression de la photo ?');
	   if(test) {
	   	document.location.href='utilsTache.admin?action=supprimerPhoto&nom=' + nom + '&prenom=' + prenom;
	   }
	}
	
function modifierCodeAS(nom, prenom, codeAS) {
	var code = prompt("Entrer le code Agent : ", codeAS);
	if (code != null) {
		window.location = 'utilsTache.admin?action=modifierCodeAS&nom=' + nom + '&prenom=' + prenom + '&codeAS=' + code;
	}
}

//-->
</script>
<title>Intranet CPAS de Liège - administration - gestion des services</title>
</head>
<body class="body">
<jsp:include page="../../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="gestionPersonnels.admin">gestion du personnel</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" >${personnel.nom } ${personnel.prenom }</a>
<hr>
</div>
<br>
<%
List listeSer = (List)request.getAttribute("listeService");
Personnel pers = (Personnel)request.getAttribute("personnel");

%>
<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="45px"><img src="images/administration/personnel.png" border="0" width="35px"></td>
					<td class="titre_tableau">${personnel.nom } ${personnel.prenom }</td>
					<td align="right">
						
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
			<table class="tableau_liste" cellspacing="0">
				<tr>
					<% if (pers.getPathPhoto().equals("")) {%>
					<td align="right">
						Sélectionner une photo : 
					</td>
					<td align="left">
						<form method="post" enctype="multipart/form-data" action="utilsTache.admin">
							<input type="hidden" name="action" value="ajouterPhoto">
							<input type="hidden" name="nom" value="${personnel.nom }">
							<input type="hidden" name="prenom" value="${personnel.prenom }">
							<input type="file" name="photo" />
							<input type="submit" value="Ajouter Photo"/>
						</form>
					</td>
					<% } %>
					<% if (!pers.getPathPhoto().equals("")) {%>
					<td align="left">
						<img src="http://10.210.117.21/photos/${personnel.pathPhoto }" border="0" width="120px">
						<a onclick="supprimerPhoto('${personnel.nom}', '${personnel.prenom}')"><img src="images/administration/supprimer.gif" border="0"></a>
						
					</td>
					<% } %>
					<td align="right">
						<table>
							<tr>
								<td>
									<%	if(listeSer.size() > 1) { %>														
											<%= listeSer.size() %> services
									<%	} else { %>
											<%= listeSer.size() %> service
									<%	} %>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>
				<tr>
					<td>
						Code Agent : ${personnel.codeAS}
					</td>
					<td>
						<button onclick="modifierCodeAS('${personnel.nom}', '${personnel.prenom}', '${personnel.codeAS}')" >Modifier Code Agent</button>
					</td>
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
	<tr height="25">
		<td></td>
		<td>
			
		</td>
		<td></td>
	</tr>
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td class="titre_tableau"><a name="listeServices">Liste des services</a></td>
					<td align="right">
						<form method="get" action="formulairePersonnelServiceAssigner.admin">
							<input type="submit" value="assigner à un service"/>
							<input type="hidden" name="nom" value="${personnel.nom }"/>
							<input type="hidden" name="prenom" value="${personnel.prenom }"/>
						</form>
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
			<table class="tableau_liste" cellspacing="0">
				<tr class="titre_colonne_tableau_liste">
					<th>Service</th>
					<th>Fonction</th>
					<th></th>
					<th>Téléphone</th>
				</tr>
				<% int i = 0; %>
				<c:forEach items="${listeService}" var="employe">
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td><a class="lien_tableau" href="gestionService.admin?service=${ fn:escapeXml(employe.service) }">${employe.service}</a></td>
						<td>${employe.fonction}</td>
						<td>${employe.rem}</td>
						<td>${employe.telephone}</td>
						<td width="30px" align="right"><a href="formulairePersonnelServiceModifier.admin?service=${employe.service}&amp;nom=${employe.nom}&amp;prenom=${employe.prenom}&amp;ancre=listeServices"><img src="images/administration/modifier.png" border="0" alt="modifier"></a></td>
						<td width="30px" align="right"><a href="desassignerServicePersonnel.admin?service=${employe.service}&amp;nom=${employe.nom}&amp;prenom=${employe.prenom}&amp;ancre=listeServices"><img src="images/administration/supprimer.gif" border="0"></a></td>
					</tr>
				</c:forEach>
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
<jsp:include page="../../basDePage.jsp" ></jsp:include>
</body>
</html>