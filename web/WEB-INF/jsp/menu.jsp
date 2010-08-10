<%@ include file="/WEB-INF/jsp/include.jsp" %>
<body>
<table class="menu">      
  <tr>
    <td><a href="<c:url value="courseIntroduction.html"/>">EDIT COURSE INTRODUCTION</a></td>
    <td><a href="<c:url value="selectingCourse.html"/>">EDIT TOPIC ORIENTATION</a></td>
    <td><a href="<c:url value="selectingTopic.html?doc=eval"/>">EDIT TOPIC EVALUATION</a></td>
    <td><a href="<c:url value="selectingTopic.html?doc=sub"/>">ADD SUB-TOPIC</a></td>
    <td><a href="<c:url value="buildingEnvironment.html"/>">BUILD ENVIRONMENT</a></td>
  </tr>  
</table>