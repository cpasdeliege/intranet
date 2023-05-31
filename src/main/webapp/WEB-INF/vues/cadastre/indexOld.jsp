<%@page import="be.cpasdeliege.intranet.informatique.model.PrivilegeInformatique"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="be.cpasdeliege.*"%>
<%@page import="be.cpasdeliege.intranet.cadastre.model.*"%>
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
	width: 1200px;
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

function supprimerMail(id_mail, adresse) {
   var mdp = confirm('Confirmer la suppression de l\'adresse mail ?\n\n ' + adresse);
   if(mdp) {
   	document.location.href='index.cad?action=suppMail&id_mail=' + id_mail;
   }
}

function supprimerDenomination(id_denomination_reference, denomination) {
   var mdp = confirm('Confirmer la suppression de la dénomination ?\n\n ' + denomination );
   if(mdp) {
   	document.location.href='index.cad?action=suppDenomination&id_denomination_reference=' + id_denomination_reference;
   }
}

function supprimerFormulaire(id_formulaire, nom) {
   var mdp = confirm('Confirmer la suppression du formulaire ?\n\n ' + nom );
   if(mdp) {
   	document.location.href='index.cad?action=suppFormulaire&id_formulaire=' + id_formulaire;
   }
}

function supprimerTypeMP(id_type_marche, nom) {
   var mdp = confirm('Confirmer la suppression du type de marché public ?\n\n ' + nom );
   if(mdp) {
   	document.location.href='index.cad?action=suppType&id_type_marche=' + id_type_marche;
   }
}


//-->
</script>
</head>
<%
List<AdresseMail> listeMail = (List<AdresseMail>)request.getAttribute("listeMail");
List<TypeMarche> listeTypeMarche = (List<TypeMarche>)request.getAttribute("listeTypeMarche");
List<Formulaire> listeFormulaire = (List<Formulaire>)request.getAttribute("listeFormulaire");
List<DenominationReference> listeDenomination = (List<DenominationReference>)request.getAttribute("listeDenomination");
PrivilegeInformatique privileges = (PrivilegeInformatique)request.getSession().getAttribute("privilegeInformatique");
if(privileges == null) {
	privileges = new PrivilegeInformatique();
}
%>
<body class="bodyCadastre">
	<jsp:include page="../entete.jsp"></jsp:include>
	<div>
		<hr>
		&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">Cadastre des Marchés Publics</a>
		<hr>
	</div>
<div id="principal">
	<p align="center" style="color: red">
	${erreur}
	</p>
	
	
	
	<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">
		<tr height="25">
			<td></td>
			<td>
				<table width="100%">
					<tr>
						<td width="45px"><img src="images/listing_cadastre.jpg" border="0" width="40"></td>
						<td class="titre_tableau">Listing des Marchés Publics</td>
						<td align="right">
						<% if(privileges.isCadastreMP()) { %>
							<form method="post" action="index.cad?action=formAjouterMP">
								<input type="submit" value="Ajouter un Marché Public"/>
							</form>
							<form method="post" action="index.cad?action=formAjouterType">
								<input type="submit" value="Ajouter un type de Marché Public"/>
							</form>
						<% } %>
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
				<ul style="list-style-type:square;font-size: large;">
				<li><a href="index.cad?action=listeMP&amp;type=tous" style="text-decoration: none;color: black;">Tous</a></li>
				<li style="list-style-type: none;">&nbsp;</li>
				<c:forEach items="${listeTypeMarche}" var="type">
					<li>
						<a href="index.cad?action=listeMP&amp;type=${type.id_type_marche}" style="text-decoration: none;color: black;"><img src="images/cadastre/${type.pathImage}" border="0" width="20">&nbsp;${type.type_marche}</a>&nbsp;&nbsp;
						<% if(privileges.isCadastreMP()) { %>
						<a href="index.cad?action=formModifierType&amp;id_type_marche=${type.id_type_marche}" style="color: black;"><img src="images/edit.png" border="0" width="20"></a>&nbsp;&nbsp;
						<!-- <a href="index.cad?action=suppType&amp;id_type_marche=${type.id_type_marche}" style="color: black;"><img src="images/refuse.png" border="0" width="20"></a>  -->
						<a onclick="supprimerTypeMP('${type.id_type_marche}', '${type.type_marche}')" style="color: black;"><img src="images/refuse.png" border="0" width="20"></a>
						<% } %>
					</li>
				</c:forEach>
				<% if(privileges.isCadastreMP()) { %>
				<li style="list-style-type: none;">&nbsp;</li>
				<li><a href="index.cad?action=listeMP&amp;type=supp" style="text-decoration: none;color: black;">Archives</a></li>
				<% } %>
				</ul>
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
						<td width="45px"><img src="images/cadastre_telech.png" border="0" width="40"></td>
						<td class="titre_tableau">Télécharger les formulaires de commande</td>
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
				<ul style="list-style-type:square;font-size: large;">
			<%
				for(Formulaire formulaire : listeFormulaire) {
			%>		
				<li>
					<a href="index.regTrav?param=formulaireCadastre&amp;id_formulaire=<%= formulaire.getId_formulaire() %>" style="color: black;"><img src="<%= Util.getIcone(formulaire.getNom()) %>" border="0" height="25">&nbsp;<%= formulaire.getNom() %></a>
					<% if(privileges.isCadastreMP()) { %>
					<!--  &nbsp;&nbsp;&nbsp;<a href="index.cad?action=suppFormulaire&amp;id_formulaire=<%= formulaire.getId_formulaire() %>" style="color: black;"><img src="images/refuse.png" border="0" width="20"></a> -->
					&nbsp;&nbsp;&nbsp;<a onclick="supprimerFormulaire('<%= formulaire.getId_formulaire() %>', '<%= formulaire.getNom() %>')" style="color: black;"><img src="images/refuse.png" border="0" width="20"></a>
					<% } %>
				</li>
			<%		
				}
			%>
			
			
				</ul>
				<% if(privileges.isCadastreMP()) { %>
				<div align="right">
				<h4>Ajouter un fichier</h4>
				<form method="post" enctype="multipart/form-data" action="utilsTache.admin">
					<input type="hidden" name="action" value="ajouterFormulaireCadastre">
					nom à afficher : 
					<input type="text" name="nom"><br>
					<input type="file" name="annexe" />
					<input type="submit" value="Ajouter"/>
				</form>
				</div>
				<% } %>
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
		
		 
		<% if(privileges.isCadastreMP()) { %>
		<tr height="25">
			<td></td>
			<td>
				<table width="100%">
					<tr>
						<td width="45px"><img src="images/administration/fonction.png" border="0" width="40"></td>
						<td class="titre_tableau">Liste des dénominations pour les références</td>
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
				<ul style="list-style-type:square">
			<%
				for(DenominationReference denomination : listeDenomination) {
			%>		
				<li>
					<%= denomination.getDenomination() %>
					<!--  &nbsp;&nbsp;&nbsp;<a href="index.cad?action=suppDenomination&amp;id_denomination_reference=<%= denomination.getId_denomination_reference() %>" style="color: black;"><img src="images/refuse.png" border="0" width="15"></a>   -->
					&nbsp;&nbsp;&nbsp;<a onclick="supprimerDenomination('<%= denomination.getId_denomination_reference() %>', '<%= denomination.getDenomination() %>')" style="color: black;"><img src="images/refuse.png" border="0" width="15"></a>
					
				</li>
			<%		
				}
			%>
				</ul>
				<div align="right">
				<h4>Ajouter une dénomination</h4>
				<form method="post" action="index.cad">
					<input type="hidden" name="action" value="ajouterDenomination">
					dénomination : 
					<input type="text" name="denomination"><br>
					<input type="submit" value="Ajouter"/>
				</form>
				</div>
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
						<td width="45px"><img src="images/mail.png" border="0" width="40"></td>
						<td class="titre_tableau">Adresses mail à notifier en cas de fin proche d'un marché</td>
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
				<ul style="list-style-type:square">
			<%
				for(AdresseMail adresseMail : listeMail) {
			%>		
				<li>
					<%= adresseMail.getAdresse() %>
					<!--   &nbsp;&nbsp;&nbsp;<a href="index.cad?action=suppMail&amp;id_mail=<%= adresseMail.getId_mail() %>" style="color: black;"><img src="images/refuse.png" border="0" width="15"></a> -->
					&nbsp;&nbsp;&nbsp;<a onclick="supprimerMail('<%= adresseMail.getId_mail() %>', '<%= adresseMail.getAdresse() %>')" style="color: black;"><img src="images/refuse.png" border="0" width="15"></a>
				</li>
			<%		
				}    
			%>
				</ul>
				<div align="right">
				<h4>Ajouter une adresse mail</h4>
				<form method="post" action="index.cad">
					<input type="hidden" name="action" value="ajouterMail">
					adresse : 
					<input type="text" name="adresse"><br>
					<input type="submit" value="Ajouter"/>
				</form>
				</div>
			</td>
			<td background="images/administration/cadre/area_right.gif"></td>
		</tr>
		<tr height="25">
			<td background="images/administration/cadre/area_bottom_left.gif"></td>
			<td background="images/administration/cadre/area_bottom.gif"></td>
			<td background="images/administration/cadre/area_bottom_right.gif"></td>
		</tr>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		<% } %>
		
		
		
		<tr height="25">
			<td></td>
			<td>
				
			</td>
			<td></td>
		</tr>
	</table>

</div>
	<jsp:include page="../basDePage.jsp"></jsp:include>
</body>
</html>