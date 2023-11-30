<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="be.cpasdeliege.intranet.informatique.model.*" %>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>
<%
PrivilegeInformatique privilege = (PrivilegeInformatique)request.getSession().getAttribute("privilegeInformatique");
if(privilege == null) {
	privilege = new PrivilegeInformatique();
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
<script language="JavaScript" src="javascript/calendar_db.js"></script>
<link rel="stylesheet" href="calendar.css">
<link rel="stylesheet" href="cpas.css" type="text/css">
<script type="text/javascript">

function goToService() {
	var service = document.formulaire.service.value;
   	document.location.href='gestionService.admin?service=' + service + '#listeTache';
}

function goToEmploye() {
	var employe = document.formulaire.personnel.value.split(", ");
	var nom = employe[0];
    var prenom = employe[1];
   	document.location.href='gestionPersonnel.admin?nom=' + nom + '&prenom=' + prenom;
}

function goToEmployeInfo() {
	var employe = document.formulaire.personnelInfo.value.split(", ");
	var nom = employe[0];
    var prenom = employe[1];
   	document.location.href='gestionPersonnel.admin?nom=' + nom + '&prenom=' + prenom;
}

function goToOrdi() {
	var ordi = document.formulaire.ordinateur.value;
	document.location.href='http://10.210.117.21:8080/gpi/#/ordinateur/'+ordi;
}
function ajoutLien() {
	document.getElementById('zonedetexte').value = document.getElementById('zonedetexte').value+' <a href=""></a>';
}
function ajoutLienDSI() {
	document.getElementById('zonedetexte').value = document.getElementById('zonedetexte').value+' <a href="afficherDemande.dsi?idDemandes=">DSI</a>';
}
function ajoutLienTHI() {
	document.getElementById('zonedetexte').value = document.getElementById('zonedetexte').value+' <a href="gestionTache.admin?idPlanning=">TI</a>';
}

</script>
<title>THI ${formulaireTacheModifier.idPlanning } - Intranet CPAS de Liège - administration - gestion du planning</title>
</head>


<%
	List listePersInfo = (List)request.getAttribute("listePersInfo");
%>


<body class="body">
<balise id="hautdepage"></balise>
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="gestionPlanning.admin">gestion du planning</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" >tâche n° ${formulaireTacheModifier.idPlanning }</a>
<hr>
</div>
<br>
<p align="center" style="color: red">
${erreurFormulaireTacheModifier}
</p>
<table cellpadding="0" cellspacing="0"  class="tableau_container">
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td class="titre_tableau">Tâche n° ${formulaireTacheModifier.idPlanning } - ${formulaireTacheModifier.titre }</td>
					<td align="right">
						<a class="lien_tableau" href="index.dsi"> - DSI - </a>
					</td>
				</tr>
			</table>
		</td>
		<td></td>
		<td rowspan="6" width="350px" valign="top">
			
				
			
				<table align="right">
					<tr>
						<td>&nbsp;</td>
						<td><a href="#basdepage"><img width="50px" alt="retour" src="images/2dowarrow.png" border="0"></a></td>
						<td></td>
					</tr>
					<tr>
						
						<td colspan="3">
							<form  method="get" action="utilsTache.admin" target="_blank">
								Notifier à &nbsp; <select name="pers" size="1" />
								<option>-</option>
								<c:forEach items="${listePersInfo}" var="pers">
									<option value="${pers.nom},${pers.prenom}" >${pers.nom}</option>
								</c:forEach>
							</select>
							<input type="submit" value="Envoyer"/>
							<input type="hidden" name="idPlanning" value="${formulaireTacheModifier.idPlanning }"/>
							<input type="hidden" name="action" value="envoiemail">
						</form>
						</td>
						
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td></td>
						<td></td>
					</tr>
					<form method="post" enctype="multipart/form-data" action="utilsTache.admin">
				<input type="hidden" name="action" value="ajouterAnnexe">
				<input type="hidden" name="idPlanning" value="${formulaireTacheModifier.idPlanning}">
					<tr>
						<td colspan="3"><h3>Annexes</h3></td>
					</tr>
					<tr>
						<td colspan="3">
							<%
							String[] annexes = (String[])request.getAttribute("annexes");
							
							if(annexes == null || annexes.length == 0) {
							%>
							
								Pas d'annexe pour cette demande
								
							
							<% } else { 
								for (int i = 0; i < annexes.length; i++) { %>
								<a href="index.regTrav?param=GTI_annexe&fichier=<%= annexes[i] %>&idPlanning=${formulaireTacheModifier.idPlanning}"><%= i + 1 %>) <%= annexes[i] %></a>
								&nbsp;
								<a href="utilsTache.admin?idPlanning=${formulaireTacheModifier.idPlanning}&action=supprimerAnnexe&numAnnexe=<%= i %>">
									<img width="15px" alt="supprimer" src="images/refuse.png" border="0">
								</a>	
								<br>
							<% 	}
							} %>
							
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>ajouter une annexe :</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td><input type="file" name="annexe" /></td>
						<td><input type="submit" value="Ajouter"/></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</form>
		</td>
	</tr>
	<tr>
		<td></td>
		<td align="center">
		
			<!--<table width="80%">
				<tr>
					 <td>
						<form method="get" action="utilsTache.admin">
							<input type="submit" value="notif à Juan"/>
							<input type="hidden" name="mail" value="juan"/>
							<input type="hidden" name="idPlanning" value="${formulaireTacheModifier.idPlanning }"/>
							<input type="hidden" name="action" value="envoiemail">
							
						</form>
					</td>
					<td width="100%">
					</td>
					<td>
					<%
						Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
						if(utilisateur.getNom().equals("Hernandez")) {
					%>		
						<form method="get" action="utilsTache.admin"  target="_blank">
					<%		
						} else {
					%>		
						<form method="get" action="utilsTache.admin">
					<%		
						}
					%>
							<input type="submit" value="notif à ${formulaireTacheModifier.personnelInfo}"/>
							<input type="hidden" name="mail" value="assign"/>
							<input type="hidden" name="idPlanning" value="${formulaireTacheModifier.idPlanning }"/>
							<input type="hidden" name="action" value="envoiemail">
							
						</form>
					</td>   
					<td align="left">
						<form  method="get" action="utilsTache.admin">
							Notifié à &nbsp;
							<select name="pers" size="1" />
							<option>-</option>
							<c:forEach items="${listePersInfo}" var="pers">
								<c:choose>
									<c:when test="${formulaireTacheModifier.service == service.nom}">
										<option selected>${pers.nom}</option>
									</c:when>
									<c:otherwise>
										<option>${pers.nom}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							</select>
							<input type="submit" value="Envoyer"/>
						</form>
					</td>
				</tr>
			</table>
			
			-->
		
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
			<form method="post" name="formulaire">
			<table width="100%">
			<% 
				Tache tache = (Tache)request.getAttribute("formulaireTacheModifier");
			%>
			
				<% if(tache.getIncident().equals("1")) { %>
				<tr>
					<td colspan="3" align="center">
						<p style="font-size: 16; font-weight: bold;color: red">/!\ /!\ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;INCIDENT survenu à <%= tache.getHeureIncident() %>H<%= tache.getMinuteIncident() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/!\ /!\</p>
					</td>
				</tr>
				<% } %>
				
				<tr>
					<td align="right">service : </td>
					<td>
						<select name="service" size="1" />
							<option>-</option>
							<c:forEach items="${listeService}" var="service">
								<c:choose>
									<c:when test="${formulaireTacheModifier.service == service.nom}">
										<option selected>${service.nom}</option>
									</c:when>
									<c:otherwise>
										<option>${service.nom}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<a class="lien_tableau" style="cursor: pointer" onclick="goToService()">go</a>
					</td>
					<td>
						en attente : 
							<%
								Tache t = (Tache)request.getAttribute("formulaireTacheModifier");
								if(t.isEnAttente()) {
							%>
								<input type="checkbox" name="enAttente" checked/>
							<%
								} else {
							%>	
								<input type="checkbox" name="enAttente" />
							<%		
								}
							%>
					</td>
				</tr>
				<tr>
					<td align="right">employé : </td>
					<td>
						<select name="personnel" size="1" />
							<option>-</option>
							<c:forEach items="${listeEmploye}" var="employe">
								<c:choose>
									<c:when test="${formulaireTacheModifier.personnel == employe}">
										<option selected>${employe}</option>
									</c:when>
									<c:otherwise>
										<option>${employe}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<a class="lien_tableau" style="cursor: pointer" onclick="goToEmploye()">go</a>
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td align="right">ordinateur : </td>
					<td>
						<select name="ordinateur" size="1" />
							<option>-</option>
							<c:forEach items="${listeOrdinateur}" var="ordi">
								<c:choose>
									<c:when test="${formulaireTacheModifier.ordinateur == ordi.nom}">
										<option selected>${ordi.nom}</option>
									</c:when>
									<c:otherwise>
										<option>${ordi.nom}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<a class="lien_tableau" style="cursor: pointer" onclick="goToOrdi()">go</a>
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td align="right">échéance : </td>
					<td>
							<input type="text" name="echeance" value="${ formulaireTacheModifier.echeance }"/>
							<script language="JavaScript">
							new tcal ({
								'formname': 'formulaire',
								'controlname': 'echeance'
							});
							</script>

					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td align="right">date fin : </td>
					<td>
							<input type="text" name="dateFin" value="${ formulaireTacheModifier.dateFin }"/>
							<script language="JavaScript">
							new tcal ({
								'formname': 'formulaire',
								'controlname': 'dateFin'
							});
							</script>

					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td align="right">type : </td>
					<td>
						<select name="type" size="1" />
							<option>-</option>
							<c:forEach items="${listeType}" var="type">
								<c:choose>
									<c:when test="${formulaireTacheModifier.type == type}">
										<option selected>${type}</option>
									</c:when>
									<c:otherwise>
										<option>${type}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">coordonné par : </td>
					<td>
						<select name="personnelInfo" size="1" />
							<option>-</option>
							<c:forEach items="${listePersonnelInfo}" var="employe">
								<c:choose>
									<c:when test="${formulaireTacheModifier.personnelInfo == employe}">
										<option selected>${employe}</option>
									</c:when>
									<c:otherwise>
										<option>${employe}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<a class="lien_tableau" style="cursor: pointer" onclick="goToEmployeInfo()">go</a>
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td align="right">titre : </td>
					<td>
							<input type="text" size="60" name="titre" value="${ StringEscapeUtils.escapeHtml4(formulaireTacheModifier.titre) }"/>
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td></td>
					<td><br><input type="submit" value="modifier"/></td>
					<td></td>
				</tr>
				<%
					List liste = (List)request.getAttribute("listeTicketItem");
					for(int i = 0; i < liste.size(); i++) {
						TicketItem item = (TicketItem)liste.get(i);
						if(!item.getTexte().equals("")) {
				%>		
					<tr>
						<td colspan="3">
							<table width="100%">
								<tr>
									<td align="right" style="font-size: 10pt">
									<%
										if(i == liste.size()-1) {
									%>
									<a name="fin">ajouté</a>
									<%
										} else {
									%>
									ajouté
									<%											
										}
									%>
										 le <%= item.getDateAffichage() %> par <%= item.getUser() %>
									</td>
								</tr>
								<tr>
									<td>
										<table width="100%" cellpadding="0" cellspacing="0" style="position:relative;">
											<tr height="25">
												<td width="22" background="images/cadre/area_top_left.gif"></td>
												<td background="images/cadre/area_top.gif"></td>
												<td width="22" background="images/cadre/area_top_right.gif"></td>
											</tr>
											<tr>
												<td background="images/cadre/area_left.gif"></td>
												<td>
													<%= item.getTexte().replaceAll("\r\n", "<br>") %>
												</td>
												<td background="images/cadre/area_right.gif"></td>
											</tr>
											
											<tr height="25">
												<td background="images/cadre/area_bottom_left.gif"></td>
												<td background="images/cadre/area_bottom.gif"></td>
												<td background="images/cadre/area_bottom_right.gif"></td>
											</tr>
											
											<% if(privilege.canDestroyTicket()) { %>
											<td width="15px" style="position:absolute; right: 15px; top:15px;"><a href="supprimerTicketItemConfirmation.admin?ticket=<%= item.getIdTicketItem() %>"><img alt="surpprimer" title="Supprimer" src="images/administration/supprimer.gif" border="0"></a></td>
											<% } %>
											
											<% if(privilege.canEditTicket()) { %>
											<% String rightValue = privilege.canDestroyTicket() ? "40" : "15"; %>
											<td width="15px" style="position:absolute; right: <%= rightValue %>px; top:15px;"><a href="modifierTicketItem.admin?ticket=<%= item.getIdTicketItem() %>"><img alt="éditer" title ="Editer" src="images/administration/editer.png" border="0"></a></td>
											<% } %>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				<%		
						}
					}
				%>
								
						
				<tr>
					<td></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>
						<a href="${retour}#${formulaireTacheModifier.idPlanning }"><img alt="retour" src="images/administration/retour.jpg" border="0"></a>
					</td>
				</tr>
			</table>
			
			<input type="hidden" name="_ordinateur"/>
			<input type="hidden" name="_employe"/>
			<input type="hidden" name="_service"/>
			<input type="hidden" name="_remarque"/>
			<input type="hidden" name="_travail"/>
			<input type="hidden" name="_echeance"/>
			<input type="hidden" name="_titre"/>
			<input type="hidden" name="_type"/>
			</form>
		</td>
		<td background="images/administration/cadre/area_right.gif"></td>
	</tr>
	<tr height="25">
		<td background="images/administration/cadre/area_bottom_left.gif"></td>
		<td background="images/administration/cadre/area_bottom.gif"></td>
		<td background="images/administration/cadre/area_bottom_right.gif"></td>
	</tr>
	
	<tr>
		<td></td>
		<td>
			<form method="get" action="ajouterTicketItem.admin">
				<textarea id="zonedetexte" cols="60" rows="10" name="texte"/></textarea>
				<input type="submit" value="ajouter ticket"/>
				<input type="hidden" name="idPlanning" value="${formulaireTacheModifier.idPlanning }"/>
			</form>
			<input onclick="ajoutLien()" type="button" value="ajouter lien"/>
			<input onclick="ajoutLienDSI()" type="button" value="ajouter lien DSI"/>
			<input onclick="ajoutLienTHI()" type="button" value="ajouter lien TI"/>
			
		</td>
		<td></td>
	</tr>
	
	
	<tr>
		<td colspan="4" align="right">
			<a href="#hautdepage"><img width="50px" alt="retour" src="images/up.png" border="0"></a>
		</td>
	</tr>

	
</table>
<balise id="basdepage"></balise>
<jsp:include page="../basDePage.jsp" ></jsp:include>
</body>
</html>