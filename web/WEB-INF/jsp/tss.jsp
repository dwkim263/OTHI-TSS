<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%
    String userId = (String)session.getAttribute("userId");
    if(userId == null || userId.equals("")){
        userId = request.getParameter("userid");
        if(userId == null || userId.equals("")){
%>
<script type="text/javascript">
  alert("Please login!!!.");
  window.close();
</script>
<%
        } else {
            session.setAttribute( "userId", userId );
        }
    }     
%>
<html>
<head>
<link rel="stylesheet" href="<spring:theme code='tss'/>" type="text/css" />
</head>
<body>
<table>  
  <tr>
      <td class="title">TEACHING SUPPORT SUBSYSTEM (TSS)</td>
  </tr>
  <tr>
  <td class="item"><a href="<c:url value='courseIntroduction.html'/>">
          EDIT COURSE INTRODUCTION!!!
  </a></td>
  </tr>
  <tr>
  <td class="item"><a href="<c:url value='selectingCourse.html'/>">
          EDIT TOPIC ORIENTATION!!!
  </a></td>          
  </tr>  
  <tr>
  <td class="item"><a href="<c:url value='selectingTopic.html?doc=eval'/>">
          EDIT TOPIC EVALUATION!!!
  </a></td>          
  </tr>    
  <tr>
  <td class="item"><a href="<c:url value='selectingTopic.html?doc=sub'/>">
          ADD SUB-TOPIC!!!
  </a></td>          
  </tr>    
  <tr>
  <td class="item"><a href="<c:url value='buildingEnvironment.html'/>">
          BUILD LEARNING ENVIRONMENT!!!
  </a></td>
  </tr>  
</table>  
<table class="navigator_30">
<tr>
    <td><a href="javascript:closeTTS()">CLOSE</a></td>
</tr>
</table>
<jsp:include page="foot.jsp"/>