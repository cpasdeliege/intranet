<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="be.cpasdeliege.intranet.DemServInfo.model.*"%>
<%@page import="be.cpasdeliege.intranet.informatique.model.*"%>
<%@page import="org.apache.commons.lang3.StringEscapeUtils"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet CPAS de Liège</title>
<link rel="stylesheet" href="site.css" type="text/css">
<script src="http://code.jquery.com/jquery-latest.js" type="text/javascript"></script>
<script language="JavaScript" src="javascript/calendar_db.js"></script>
<link rel="stylesheet" href="calendar.css">
<script type="text/javascript">
function refresh(url, id) {
	$.ajax({ 
	url: url,
	type: "GET",
	success:
	function(retour){
	$(id).html(retour);
	},
	error: function (xhr, ajaxOptions, thrownError) {
	alert(xhr.status);
	alert(thrownError);
	}
	});
	}

function afficheChoix2() {
	var choix1 = $('#choix1 option:selected').val();
	refresh("ajax.dsi?choix1=" + choix1, "#TRchoix2" );
	refresh("ajax.dsi?action=vide", "#TRchoix3" );
	
}

function afficheChoix3() {
	var choix1 = $('#choix1 option:selected').val();
	var choix2 = $('#choix2 option:selected').val();
	refresh("ajax.dsi?choix2=" + choix1 + "-" + choix2, "#TRchoix3" );
}
</script>
</head>

<%
	//List<DemServInf> listeDsi = (List<DemServInf>)request.getAttribute("listeDsi");
PrivilegeInformatique privilege = (PrivilegeInformatique)request.getSession().getAttribute("privilegeInformatique");
List<Service> listeServices = (List<Service> ) request.getAttribute("listeServices");
List<Chef> listeChefs = (List<Chef> ) request.getAttribute("listeChefs");
String chef = (String)request.getAttribute("chef");
String typeDemande1 = (String)request.getAttribute("typeDemande1");
String typeDemande2 = (String)request.getAttribute("typeDemande2");
String typeDemande3 = (String)request.getAttribute("typeDemande3");
String service = (String)request.getAttribute("service");
Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
List<TypeDemandeDsi> listeType = (List<TypeDemandeDsi>)request.getAttribute("listeType");
List<String> listeTypeChoix1 = (List<String>)request.getAttribute("listeTypeChoix1");
List<String> listeTypeChoix2 = (List<String>)request.getAttribute("listeTypeChoix2");
List<String> listeTypeChoix3 = (List<String>)request.getAttribute("listeTypeChoix3");
%>

<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.dsi">demande service informatique</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">ajouter une demande</a>
<hr>
</div>
<p align="center" style="color: red">
${erreur}
</p>
<table cellpadding="0" cellspacing="0"  class="tableau_container">
	<tr height="25">
		<td></td>
		<td>
			<table>
				<tr>
					<td class="titre_tableau">Ajouter une demande</td>
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
			<form method="post" action="addDemande.dsi" name="formulaire" enctype="multipart/form-data" >
			<table>
				<tr>
					<td align="right">type de demande : </td>
					<td>
						<select onchange="afficheChoix2()" id="choix1" name="typeDemande1" size="1" />
								<option>-----> choisir un type de demande</option>
						<%
							for(int i = 0; i < listeTypeChoix1.size(); i++) {
								if(listeTypeChoix1.get(i).equals(typeDemande1)) {
						%>		
								<option selected value="<%= StringEscapeUtils.escapeHtml4(listeTypeChoix1.get(i)) %>"><%= StringEscapeUtils.escapeHtml4(listeTypeChoix1.get(i)) %></option>
						<%
								} else {
						%>
								<option value="<%= StringEscapeUtils.escapeHtml4(listeTypeChoix1.get(i)) %>"><%= StringEscapeUtils.escapeHtml4(listeTypeChoix1.get(i)) %></option>
						<%
								}
							}
						%>
						</select>
					</td>
				</tr>
				
				
				
				
				
				
				
				
				
				<tr id="TRchoix2">
					<%
					if(typeDemande2 != null) {
				%>
				
				
					<td align="right"></td>
					<td id="TDChoix2">
						<select id="choix2" onchange="afficheChoix3()" name="typeDemande2" size="1" >
								<option>-----></option>
						<%
							for(int i = 0; i < listeTypeChoix2.size(); i++) {
								if(listeTypeChoix2.get(i).equals(typeDemande2)) {
						%>		
								<option selected value="<%= listeTypeChoix2.get(i) %>"><%= listeTypeChoix2.get(i) %></option>
						<%
								} else {
						%>
								<option value="<%= listeTypeChoix2.get(i) %>"><%= listeTypeChoix2.get(i) %></option>
						<%
								}
							}
						%>
						</select>
						
					</td>
					
				<%	
					}
				%>
				</tr>
				<tr id="TRchoix3">
					<%
					if(typeDemande3 != null && !(listeTypeChoix3.size() == 1 && listeTypeChoix3.get(0).equals(""))) {
				%>
				
					<td align="right"></td>
					<td id="TDChoix3">
						<select id="choix3" name="typeDemande3" size="1" >
								<option>-----></option>
						<%
							for(int i = 0; i < listeTypeChoix3.size(); i++) {
								if(listeTypeChoix3.get(i).equals(typeDemande3)) {
						%>		
								<option selected value="<%= listeTypeChoix3.get(i) %>"><%= listeTypeChoix3.get(i) %></option>
						<%
								} else {
						%>
								<option value="<%= listeTypeChoix3.get(i) %>"><%= listeTypeChoix3.get(i) %></option>
						<%
								}
							}
						%>
						</select>
					</td>				
					
				<%	
					} else {
				%>
						<input type="hidden" name="typeDemande3" value="" />
				<%
					}
				%>
				</tr>
				
				
				<input type="hidden" name="chef" value="<%= utilisateur.getNom() + " " + utilisateur.getPrenom() %>"/>
				<%-- <tr>
					<td align="right">responsable : </td>
					<td>
						<select name="chef" size="1" />
							<option >choisir un responsable</option>
						<%
							for(int i = 0; i < listeChefs.size(); i++) {
								if((listeChefs.get(i).getNom() + " " + listeChefs.get(i).getPrenom()).equals(chef)) {
						%>		
								<option selected><%= listeChefs.get(i).getNom() + " " + listeChefs.get(i).getPrenom() %></option>
						<%
								} else {
						%>
								<option ><%= listeChefs.get(i).getNom() + " " + listeChefs.get(i).getPrenom() %></option>
						<%
								}
							}
						%>
						</select>
					</td>
				</tr> --%>
				<tr>
					<td align="right">service concerné : </td>
					<td>
						<select name="service" size="1" />
								<option >choisir un service</option>
						<%
							for(int i = 0; i < listeServices.size(); i++) {
								if(listeServices.get(i).getNom().equals(service)) {
						%>		
								<option selected><%= listeServices.get(i).getNom() %></option>
						<%
								} else {
						%>
								<option ><%= listeServices.get(i).getNom() %></option>
						<%
								}
							}
						%>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">échéance souhaitée: </td>
					<td>
						<input type="text" name="echeanceSouhaitee" value="${echeanceSouhaitee}"/>
						<script language="JavaScript">
						new tcal ({
							'formname': 'formulaire',
							'controlname': 'echeanceSouhaitee'
						});
						</script>

					</td>
				</tr>
				<tr>
					<td align="right">Titre : </td>
					<td><input type="text" size="80" maxlength="200" name="titre" value="${titre}"/></td>
				</tr>
				<tr>
					<td align="right">description : </td>
					<td><textarea cols="80" rows="20" name="description"/>${description}</textarea></td>
				</tr>
				<tr>
					<td align="right">remarques sur échéance : </td>
					<td><textarea cols="80" rows="5" name="remarqueEcheance"/>${remarqueEcheance}</textarea></td>
				</tr>
				
				<tr>
					<td align="right">annexe 1 : </td>
					<td><input type="file" name="annexe1" /></td>
				</tr>
				<tr>
					<td align="right">annexe 2 : </td>
					<td><input type="file" name="annexe2" /></td>
				</tr>
				<tr>
					<td align="right">annexe 3 : </td>
					<td><input type="file" name="annexe3" /></td>
				</tr>
				
				<tr>
					<td align="right"></td>
					<td></td>
				</tr>
				
				<tr>
					<td><a href="${retour}"><img alt="retour" src="images/administration/retour.jpg" border="0"></a></td>
					<td align="right"><input type="submit" value="envoyer"/></td>
				</tr>
			</table>
			<input type="hidden" name="_employe"/>
			<input type="hidden" name="_fonction"/>
			<input type="hidden" name="_telephone"/>
			</form>
		</td>
		<td background="images/administration/cadre/area_right.gif"></td>
	</tr>
	<tr height="25">
		<td background="images/administration/cadre/area_bottom_left.gif"></td>
		<td background="images/administration/cadre/area_bottom.gif"></td>
		<td background="images/administration/cadre/area_bottom_right.gif"></td>
	</tr>
</table>


<jsp:include page="../basDePage.jsp" ></jsp:include>
</body>
</html>