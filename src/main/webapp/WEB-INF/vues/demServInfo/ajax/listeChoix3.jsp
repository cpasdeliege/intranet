<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="be.cpasdeliege.intranet.DemServInfo.model.*"%>
<%@page import="be.cpasdeliege.intranet.informatique.model.*"%>
<%
List<String> listeTypeChoix3 = (List<String>)request.getAttribute("listeTypeChoix3");
String typeDemande = "";

if(listeTypeChoix3.size() == 1 && listeTypeChoix3.get(0).equals("")) {
%>
<td align="right"></td>
<td id="TDChoix3">
<input type="hidden" name="typeDemande3" value="" />
</td>
<%
} else {
%>

<td align="right"></td>
<td id="TDChoix3">
	<select id="choix3" name="typeDemande3" size="1" >
			<option>-----></option>
	<%
		for(int i = 0; i < listeTypeChoix3.size(); i++) {
			if(listeTypeChoix3.get(i).equals(typeDemande)) {
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
}
%>