<%@ include file="/WEB-INF/jsp/include.jsp" %>
<jsp:include page="head.jsp"/>
<jsp:include page="menu.jsp"/>

<form:form method="post" commandName="selectingTopic" onreset="closeTTS()">
    <table  width="50%" border="0" cellspacing="1" cellpadding="1">	
    <c:if test="${param.courseId == null}">    
   <tr>
      <td  height="100" ><strong><font size="6">
          Select Course</font></strong></td>
    </tr>

    <tr>                
    <td  height="50"><font size="4">      
    <spring:bind path="courseId"> 
      <select name="${status.expression}">
        <c:forEach var="course" items="${courses}">
          <option value="<c:out value="${course.key}"/>"
          <c:if test="${course.key == status.value}"> selected="selected"</c:if>>
            <c:out value="${course.key}:${course.value.courseTitle}"/>
          </option>
        </c:forEach>
      </select>
    </spring:bind>         
    </font></td>
    </tr>	
    </c:if>
     <c:if test="${param.courseId != null}">
   <tr>
      <td  height="100" ><strong><font size="6">
          Select Topic</font></strong></td>
    </tr>
    <tr>                
    <td  height="50"><font size="4">
    <spring:bind path="topic">     
      <select name="${status.expression}">
        <c:forEach var="topic" items="${topics}">
          <c:if test="${topic.value.courseId == param.courseId}">            
          <option value="<c:out value="${topic.key}"/>"
            <c:if test="${topic.key == status.value}"> selected="selected"</c:if>> 
            <c:out value="${topic.value.topicTitle}"/>   
          </option>
          </c:if>          
        </c:forEach>
      </select>  
    </spring:bind>      
    </font></td>
    </tr>    
     </c:if>    
    <tr>
    <td  height="200">
     <input type=submit value="Ok">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
     <input type=reset value="Cancel"></td>
    </tr>
    </table>
</form:form>        
<jsp:include page="foot.jsp"/>
