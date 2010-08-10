<%@ include file="/WEB-INF/jsp/include.jsp" %>
<jsp:include page="head.jsp"/>
<jsp:include page="menu.jsp"/>

<form:form method="post" commandName="selectingCourse" onreset="closeTTS()">
    <table  width="50%" border="0" cellspacing="1" cellpadding="1">	
   <tr>   
      <td  height="100" ><strong><font size="6">
          Select Course</font></strong></td>
    </tr>
    <tr>                
    <td  height="50"><font size="4">
        <form:select path="course">
        <c:forEach var="course" items="${courses}">
          <option value="<c:out value="${course.key}:${course.value.courseTitle}"/>">
            <c:out value="${course.key}:${course.value.courseTitle}"/>
          </option>
        </c:forEach>
        </form:select>                
    </font></td>
    </tr>		
    <tr>
    <td  height="200">
     <input type=submit value="Ok">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
     <input type=reset value="Cancel"></td>
    </tr>
    </table>
</form:form>        
<jsp:include page="foot.jsp"/>
