<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@page import="be.cpasdeliege.intranet.DemServInfo.model.*"%>
<%@page import="be.cpasdeliege.intranet.informatique.model.*"%>
<%
List<String> listeTypeChoix2 = (List<String>)request.getAttribute("listeTypeChoix2");
String typeDemande = "";
%>


<td align="right"></td>
<td id="TDChoix2">
	<select id="choix2" onchange="afficheChoix3()" name="typeDemande2" size="1" >
			<option>-----></option>
	<%
		for(int i = 0; i < listeTypeChoix2.size(); i++) {
			if(listeTypeChoix2.get(i).equals(typeDemande)) {
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

	
