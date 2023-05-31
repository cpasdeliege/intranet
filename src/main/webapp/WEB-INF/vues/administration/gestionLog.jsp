<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="cpas.css" type="text/css">
<title>Intranet CPAS de Liège - administration - rapports &amp; statistiques</title>
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">rapports &amp; statistiques</a>
<hr>
</div>
<br>
<table align="center" width="90%">
	<tr>
		<td valign="top" width="50%">
			<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">
				<tr height="25">
					<td></td>
					<td>
						<table width="100%">
							<tr>
								<td width="60px"><img src="images/administration/console.png" border="0" width="50px"></td>
								<td class="titre_tableau">Rapports Utilisateurs</td>
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
						<table  border="0">
							<tr>
								<td width="60px" align="center"><img src="images/telephone.png" border="0" width="40px"></td>
								<td><a class="texte_menu_statistiques" href="rapports.admin?requete=logAnnuaire">logs annuaire</a></td>
							</tr>
							<tr>
								<td width="60px" align="center"><img src="images/agent.png" border="0" width="40px"></td>
								<td><a class="texte_menu_statistiques" href="rapports.admin?requete=logRues">logs répartition et code des rues de Liège</a></td>
							</tr>
							<tr>
								<td width="60px" align="center"><img src="images/formation.png" border="0" width="40px"></td>
								<td><a class="texte_menu_statistiques" href="rapports.admin?requete=logFormations">logs formations</a></td>
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
		</td>
		<td valign="top" width="50%" rowspan="2">
			<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">
				<tr height="25">
					<td></td>
					<td>
						<table width="100%">
							<tr>
								<td width="60px"><img src="images/administration/statistiques.png" border="0" width="50px"></td>
								<td class="titre_tableau">Statistiques</td>
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
						<table  border="0">
							<tr>
								<td width="60px" align="center"><img src="images/telephone.png" border="0" width="40px"></td>
								<td><a class="texte_menu_statistiques" href="rapports.admin?requete=statsAnnuaire">annuaire téléphonique</a></td>
							</tr>
							<tr>
								<td width="60px" align="center"><img src="images/agent.png" border="0" width="40px"></td>
								<td><a class="texte_menu_statistiques" href="rapports.admin?requete=statsRues">répartition et code des rues de Liège</a></td>
							</tr>
							<tr>
								<td width="60px" align="center"><img src="images/formation.png" border="0" width="40px"></td>
								<td><a class="texte_menu_statistiques" href="rapports.admin?requete=statsFormations">zone formations</a></td>
							</tr>
							<tr>
								<td width="60px" align="center"><img src="images/intranet.png" border="0" width="40px"></td>
								<td><a class="texte_menu_statistiques" href="rapports.admin?requete=statsIntranet">intranet</a></td>
							</tr>
							<tr>
								<td width="60px" align="center"><img src="images/administration/service.png" border="0" width="40px"></td>
								<td><a class="texte_menu_statistiques" href="rapports.admin?requete=statsCPAS">cpas</a></td>
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
		</td>
	</tr>
	
	
	<tr>
		<td valign="top" width="50%">
			<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">
				<tr height="25">
					<td></td>
					<td>
						<table width="100%">
							<tr>
								<td width="60px"><img src="images/administration/console.png" border="0" width="50px"></td>
								<td class="titre_tableau">Rapports Administration</td>
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
						<table  border="0">
							<tr>
								<td width="60px" align="center"><img src="images/formation.png" border="0" width="40px"></td>
								<td><a class="texte_menu_statistiques" href="rapports.admin?requete=logFormationsAdministration">logs formations</a></td>
							</tr>
							<tr>
								<td width="60px" align="center"><img src="images/administration/admin.png" border="0" width="40px"></td>
								<td><a class="texte_menu_statistiques" href="rapports.admin?requete=logAdmin">logs informatique &amp; téléphonie</a></td>
							</tr>
							<tr>
								<td width="60px" align="center"><img src="images/administration/stop.png" border="0" width="40px"></td>
								<td><a class="texte_menu_statistiques" href="rapports.admin?requete=logMessage">warnings</a></td>
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
		</td>
		<td valign="top" width="50%">
			
		</td>
	</tr>
	
	
	
</table>

<br>

<jsp:include page="../basDePage.jsp" ></jsp:include>
</body>
</html>