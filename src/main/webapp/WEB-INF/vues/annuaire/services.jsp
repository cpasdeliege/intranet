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
<title>Intranet CPAS de Liège</title>
<link rel="stylesheet" href="annuaire.css" type="text/css">
<script type="text/javascript">
//-----------------------
function see_bubble(img_){
var Obj = document.getElementById(img_);
Obj.style.display = "block";
}
//--------------------
function kill_bubble(img_){
var Obj = document.getElementById(img_);
Obj.style.display = "none";
}
//<!--
document.oncontextmenu = new Function("return false");
//-->
  </script>

</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.annuaire">annuaire interne</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">recherche par service</a>
<hr>
</div>
<table align="center" cellpadding="0" cellspacing="0">
	<tr height="25">
		<td width="22" background="images/annuaire/cadre/area_top_left.gif"></td>
		<td background="images/annuaire/cadre/area_top.gif"></td>
		<td width="22" background="images/annuaire/cadre/area_top_right.gif"></td>
	</tr>
	<tr>
		<td background="images/annuaire/cadre/area_left.gif"></td>
		<td align="center"><a class="lien_navigation"  href="recherche.annuaire">recherche par mot-clé</a> - <a class="lien_navigation_actif">service</a> - <a class="lien_navigation" href="ordreAlphabetique.annuaire">ordre alphabétique</a></td>
		<td background="images/annuaire/cadre/area_right.gif"></td>
	</tr>
	<tr height="25">
		<td background="images/annuaire/cadre/area_left.gif"></td>
		<td background="images/annuaire/cadre/area_top.gif"></td>
		<td background="images/annuaire/cadre/area_right.gif"></td>
	</tr>
	<tr>
		<td background="images/annuaire/cadre/area_left.gif"></td>
		<td>
			<table width="100%">
				<tr>
					<td>
						<table>
							<c:forEach items="${colonne1}" var="service">
								<tr>
									<td>
										<a href="services.annuaire?service=${ fn:escapeXml(service.nom) }#debut" class="lien_sous_navigation">${service.nom}</a>
										<c:if test = "${service.voip eq 1}">
										  (*)
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</table>
					</td>
					<td>
						<table>
							<c:forEach items="${colonne2}" var="service">
								<tr>
									<td>
										<a href="services.annuaire?service=${ fn:escapeXml(service.nom) }#debut" class="lien_sous_navigation">${service.nom}</a>
										<c:if test = "${service.voip eq 1}">
										  (*)
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</table>
					</td>
					<td valign="top">
						<table>
							<c:forEach items="${colonne3}" var="service">
								<tr>
									<td>
										<a href="services.annuaire?service=${ fn:escapeXml(service.nom) }#debut" class="lien_sous_navigation">${service.nom}</a>
										<c:if test = "${service.voip eq 1}">
										  (*)
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
				
				<tr>
					<td colspan="3" align="right">(*) service en téléphonie VOIP </td>
				</tr>
			</table>
		</td>
		<td background="images/annuaire/cadre/area_right.gif"></td>
	</tr>
	<tr height="25">
		<td background="images/annuaire/cadre/area_bottom_left.gif"></td>
		<td background="images/annuaire/cadre/area_bottom.gif"></td>
		<td background="images/annuaire/cadre/area_bottom_right.gif"></td>
	</tr>
</table>
<br>
<%@ page import="be.cpasdeliege.intranet.informatique.model.*" %>
<%@ page import="be.cpasdeliege.intranet.informatique.model.dao.*" %>
<%@ page import="java.util.*" %>
<%
Service service = (Service)request.getAttribute("service");

Departement departement = null;
Pole pole = null;

Object o = request.getAttribute("departement");
if(o != null) {
	departement = (Departement)o;
}

o = request.getAttribute("pole");
if(o != null) {
	pole = (Pole)o;
}

List listEmploye = (List)request.getAttribute("listePersonnel");
	if(service != null) {
%>
<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">
	<tr>
		<td></td>
		<td>
			<table width="100%">
				<tr class="titre_tableau" valign="bottom">
					<td width="50px"><img src="images/administration/service.png" border="0" width="40px"></td>
					<td><a name="debut"><u style="font-weight: bold;">${service.nom}</u> <c:if test="${not empty departement}"> / ${departement.nom} </c:if> <c:if test="${not empty pole}"> / ${pole.nom} </c:if></a></td>
					<td align="center" valign="bottom">
						${service.adresse}, ${service.numero} - ${service.codePostal} ${service.localite}	
						- <a target="_blank" href="http://maps.google.fr/maps?f=q&hl=fr&q=${service.adresse}+${service.numero}+${service.codePostal}+${service.localite}"><img  style="border: none;" title="afficher dans Google Maps" width="30px" alt="map" src="images/googleMap.gif">  </a>					
					</td>
					<td align="right">
					<%
						if(!service.getEmailService().equals("")) {
					%>
						<a title="${service.emailService}" style="text-decoration: none; color: black; font-size: large;"><img width="15px" src="images/annuaire/email.gif" border="0"/>&nbsp;${service.emailService}</a>
					<%
						}
					%>
					</td>
				</tr>
				
				<tr>
					<td></td>
					<td></td>
					<td style="font-size: 14px; font-weight: normal" align="center" valign="top">
					<%
						if(!service.getLocalisation().equals("")) {
					%>
							- ${service.localisation} -
					<%	
						}
					%>
					&nbsp;
					</td>
					<td align="right"></td>
				</tr>
				
				<tr>
					<td colspan="4">
						<table width="100%">
							<tr>
								<td align="left"><%= service.getRemarque().replaceAll("\r\n", "<br>") %></td>
								<td style="font-size: small;" align="right" valign="bottom">fax : ${service.fax}</td>
								
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
		<td></td>
	</tr>
	<tr height="25">
		<td width="22" background="images/annuaire/cadre/area_top_left.gif"></td>
		<td background="images/annuaire/cadre/area_top.gif"></td>
		<td width="22" background="images/annuaire/cadre/area_top_right.gif"></td>
	</tr>
	<tr>
		<td background="images/annuaire/cadre/area_left.gif"></td>
		<td>
			<table class="tableau_liste" cellspacing="0" border="0">
				<tr class="titre_colonne_tableau_liste">
					<th width="20px"></th>
					<th width="60px"></th>
					<th><a href="services.annuaire?service=${ fn:escapeXml(service.nom) }&orderBy=nom#debut" class="lien_sous_navigation">Nom</a></th>
					<th>Prénom</th>
					<th>Code Agent</th>
					<th><a href="services.annuaire?service=${ fn:escapeXml(service.nom) }&orderBy=fonction, nom#debut" class="lien_sous_navigation">Fonction</a></th>
					

					<th width="60px" align="center">Extension VOIP</th>
					<th align="right">Numéro Externe</th>
					
				</tr>
				<% int i = 0; 
					List<String> listeEmail = (List<String>)request.getAttribute("listeEmail");
					List<String> listePathPhoto = (List<String>)request.getAttribute("listePathPhoto");
					List<String> listeCodeAS = (List<String>)request.getAttribute("listeCodeAS");
				%>
				<c:forEach items="${listePersonnel}" var="employe">
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr id="trPhoto" class="ligne_paire" onmouseover="see_bubble('<%= listePathPhoto.get(i-1) %>')" onmouseout="kill_bubble('<%= listePathPhoto.get(i-1) %>')">
				<% } else { %>
					<tr id="trPhoto" class="ligne_impaire" onmouseover="see_bubble('<%= listePathPhoto.get(i-1) %>')" onmouseout="kill_bubble('<%= listePathPhoto.get(i-1) %>')" >
				<% } %>
						<td> 
							<% if(!listeEmail.get(i-1).equals("")) { %>
							<a title="<%= listeEmail.get(i-1) %>">
								<img width="15px" src="images/annuaire/email.gif" border="0"/>
							</a>
							<% } %>
						</td>
						<td>
							<img width="60px" src="http://10.210.117.21/photosAnnuaire/<%= listePathPhoto.get(i-1) %>" id="<%= listePathPhoto.get(i-1) %>" style="display: none;" >
						</td>
						<td>
							${employe.nom}
						</td>
						<td>${employe.prenom}</td>
						<td><%= listeCodeAS.get(i-1) %></td>
						<td>${employe.fonction} ${employe.rem}</td>
						
						<%
						String tel = "";
						String prefix = "";
						/** if((((DaoPersonnelService)listEmploye.get(i-1)).getTelephone().startsWith("04/220.") 
								|| ((DaoPersonnelService)listEmploye.get(i-1)).getTelephone().startsWith("04/250.")
								) && !((DaoPersonnelService)listEmploye.get(i-1)).getTelephone().startsWith("04/220.23")) {
							tel = ((DaoPersonnelService)listEmploye.get(i-1)).getTelephone().substring(7);
							prefix = ((DaoPersonnelService)listEmploye.get(i-1)).getTelephone().substring(0,7);
						} else {
							tel = ((DaoPersonnelService)listEmploye.get(i-1)).getTelephone();
						} **/
						tel = ((DaoPersonnelService)listEmploye.get(i-1)).getTelephone();
						%>
						<td align="right">${employe.extension}</td>
						<td align="right"><span style="color: red"><%= prefix %></span><%= tel %></td>
						
					</tr>
				</c:forEach>
			</table>			
		</td>
		<td background="images/annuaire/cadre/area_right.gif"></td>
	</tr>
	<tr height="25">
		<td background="images/annuaire/cadre/area_bottom_left.gif"></td>
		<td background="images/annuaire/cadre/area_bottom.gif"></td>
		<td background="images/annuaire/cadre/area_bottom_right.gif"></td>
	</tr>
</table>
<%
	}
%>
<jsp:include page="../basDePage.jsp" ></jsp:include>
</body>
</html>