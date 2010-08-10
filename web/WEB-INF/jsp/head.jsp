<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%
String userId = (String)session.getAttribute("userId");
if(userId == null || userId.equals("")){
%>
<script language="javascript">
  alert("Please login!!!.");
  window.close();
</script>
<%
}
%>
<html>
<head>
<link rel="stylesheet" href="<spring:theme code='tss'/>" type="text/css" />
<script language="javascript">

function closeTTS()
{
  window.close();
}
</script>
</head>

