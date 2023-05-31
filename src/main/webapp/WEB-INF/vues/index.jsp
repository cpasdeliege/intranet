<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	<%@page import="be.cpasdeliege.intranet.informatique.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
PrivilegeInformatique privilege = (PrivilegeInformatique)request.getSession().getAttribute("privilegeInformatique");
if(privilege == null) {
	privilege = new PrivilegeInformatique();
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	background-color: green;
}

.tabIndex {
	width: 95%;
}
</style>
<script language="JavaScript">

function modifierSSID() {
	var ssid = prompt("Entrer le nouvel SSID : ", "${wifiVisiteurs.ssid}");
	if (ssid != null) {
		window.location = "index.htm?action=modifierSSID&SSID="+ssid;
	}
}
function modifierMDP() {
	var mdp = prompt("Entrer le nouveau mot de passe : ", "${wifiVisiteurs.mdp}");
	if (mdp != null) {
		window.location = "index.htm?action=modifierMDP&MDP="+mdp;
	}
}
</script>
</head>
<body class="body">
	<jsp:include page="entete.jsp"></jsp:include>
	<div>
		<hr>
		&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0"
			alt=" - "><a class="menu_contextuel">accueil</a>
		<hr>
	</div>
	<div id="principal">

		<div id="gauche">
			<table class="tabIndex" align="center" cellpadding="0"
				cellspacing="0">
				<tr height="25">
					<td></td>
					<td class="titre_tableau">Zone Libre d'Accès</td>
					<td></td>
				</tr>
				<tr height="25">
					<td width="22" background="images/cadre/area_top_left.gif"></td>
					<td background="images/cadre/area_top.gif"></td>
					<td width="22" background="images/cadre/area_top_right.gif"></td>
				</tr>
				<tr>
					<td background="images/cadre/area_left.gif"></td>
					<td>
						<table width="100%">

							<tr valign="top">
								<td align="right" valign="center" width="100px"><a
									href="index.annuaire"><img src="images/telephone.png"
										border="0" width="50px" >
								</a></td>
								<td align="left" valign="middle" ><a
									class="texte_menu_principal" href="index.annuaire"><nobr>Annuaire
											Interne</nobr>
								</a></td>
								<td align="right" valign="bottom" colspan="2"><a
									href="index.admin" class="lien_administratif_menu_principal">admin</a>
								</td>
							</tr>
							
							
						</table>
					</td>
					<td background="images/cadre/area_right.gif"></td>
				</tr>
			


				<tr>
					<td background="images/cadre/area_left.gif"></td>
					<td>
						<table width="100%">
							<tr>
								<td align="right" valign="center" width="100px" ><a
									href="index.rues"><img src="images/agent.png" border="0"
										width="60px">
								</a></td>
								<td><a class="texte_menu_principal" href="index.rues">Répartition
										et code des rues de Liège</a></td>

								<td align="right" valign="bottom" colspan="2">&nbsp;
								</td>
							</tr>
						</table>
					</td>
					<td background="images/cadre/area_right.gif"></td>
				</tr>

			

				<tr>
					<td background="images/cadre/area_left.gif"></td>
					<td>
						<table width="100%">
							<tr>
								<td align="right" valign="center" width="100px" ><a
									href="index.htm?rep=personnelCPAS"><img src="images/indexPersonnel.png" border="0"
										width="60px">
								</a></td>
								<td><a class="texte_menu_principal" href="index.htm?rep=personnelCPAS">Personnel du CPAS</a></td>


							</tr>
							
						</table>
					</td>
					<td background="images/cadre/area_right.gif"></td>
				</tr>
				 
				
				<tr>
					<td background="images/cadre/area_left.gif"></td>
					<td>
						<table width="100%">
							<tr>
								<td align="right" valign="center" width="100px" ><a
									href="index.htm?rep=personnelCPAS"><img src="images/cadastre.png" border="0"
										width="60px">
								</a></td>
								<td><a class="texte_menu_principal" href="index.cad">Cadastre des Marchés Publics</a></td>


							</tr>
							
						</table>
					</td>
					<td background="images/cadre/area_right.gif"></td>
				</tr>
				 

				<tr height="25">
					<td background="images/cadre/area_bottom_left.gif"></td>
					<td background="images/cadre/area_bottom.gif"></td>
					<td background="images/cadre/area_bottom_right.gif"></td>
				</tr>
			</table>
			<br>
		</div>
		<div id="droite">

			<table align="center" class="tabIndex" cellpadding="0"
				cellspacing="0">
				<tr height="25">
					<td></td>
					<td class="titre_tableau">Zone à Accès Réservé</td>
					<td></td>
				</tr>
				<tr height="25">
					<td width="22" background="images/cadre/area_top_left.gif"></td>
					<td background="images/cadre/area_top.gif"></td>
					<td width="22" background="images/cadre/area_top_right.gif"></td>
				</tr>
				<tr>
					<td background="images/cadre/area_left.gif"></td>
					<td>
						<table width="100%">
							<tr>
								<td width="60px"><a href="index.dsi"><img
										src="images/dsi.png" border="0" width="60px"> </a></td>
								<td><a class="texte_menu_principal" href="index.dsi">Introduction
										de DSI</a></td>
								<td>
									<p>
										- <img src="./images/pdf.png" width="20px"></img><a
											style="color: black;" href="index.regTrav?param=DSI"
											target="_blank">Guide Utilisateur</a>
									</p>
								</td>
								<td align="right" class="lien_administratif_menu_principal"></td>
							</tr>
						</table>
					</td>
					<td background="images/cadre/area_right.gif"></td>
				</tr>
				
				
				<tr>
					<td background="images/cadre/area_left.gif"></td>
					<td>
						<table width="100%">
							<tr>
								<td width="60px"><a href="index.admin"><img
										src="images/informatique.png" border="0" width="60px"> </a>
								</td>
								<td><a class="texte_menu_principal" href="index.admin">Département Informatique</a></td>
								<td><p class="texte_informatif_menu_principal"></p></td>
								<td align="right" class="lien_administratif_menu_principal"></td>
							</tr>
						</table>
					</td>
					<td background="images/cadre/area_right.gif"></td>
				</tr>

				<tr height="25">
					<td background="images/cadre/area_bottom_left.gif"></td>
					<td background="images/cadre/area_bottom.gif"></td>
					<td background="images/cadre/area_bottom_right.gif"></td>
				</tr>
			</table>
			
			
			
			
			
			
			
			
			
			
			<br>
			<table align="center" class="tabIndex" cellpadding="0"
				cellspacing="0">
				<tr height="25">
					<td></td>
					<td class="titre_tableau">Wifi Visiteurs</td>
					<td></td>
				</tr>
				<tr height="25">
					<td width="22" background="images/cadre/area_top_left.gif"></td>
					<td background="images/cadre/area_top.gif"></td>
					<td width="22" background="images/cadre/area_top_right.gif"></td>
				</tr>
				<tr>
					<td background="images/cadre/area_left.gif"></td>
					<td>
						<table width="100%">
							<tr>
								<td width="60px" rowspan="3">
									<img src="images/wifi.jpg" border="0" width="60px">
								</td>
								<td style="font-size: large;" align="right" width="120px">
								SSID : 
								</td>
								<td style="font-size: x-large;" align="center" >
								<span <% if(privilege.isWifiAdmin()) { %>onclick="modifierSSID()" style="cursor: pointer;"<% } %>>&nbsp;&nbsp;&nbsp; ${wifiVisiteurs.ssid} &nbsp;&nbsp;&nbsp;</span>
								</td>
								<td>
								
								</td>
							</tr>
							<tr>
								<td style="font-size: large;" align="right">
								Mot de passe : 
								</td>
								<td style="font-size: x-large;" align="center" >
								<span <% if(privilege.isWifiAdmin()) { %>onclick="modifierMDP()" style="cursor: pointer;" <% } %> >&nbsp;&nbsp;&nbsp; ${wifiVisiteurs.mdp} &nbsp;&nbsp;&nbsp;</span>
								</td>
								<td>
								
								</td>
							</tr>
							
						</table>
					</td>
					<td background="images/cadre/area_right.gif"></td>
				</tr>
				<tr height="25">
					<td background="images/cadre/area_bottom_left.gif"></td>
					<td background="images/cadre/area_bottom.gif"></td>
					<td background="images/cadre/area_bottom_right.gif"></td>
				</tr>
			</table>
			<br>
			
			
			
			
			
			
			
			
			
		</div>
		

	</div>

	<jsp:include page="basDePage.jsp"></jsp:include>
</body>
</html>