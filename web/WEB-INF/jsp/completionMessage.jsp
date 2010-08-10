<%@ include file="/WEB-INF/jsp/include.jsp" %>
<jsp:include page="head.jsp"/>
<jsp:include page="menu.jsp"/>

<table>  
  <tr>
      <td class="complete">&nbsp;
      </td>
  </tr>
  <tr>  
  <tr>
      <td class="complete">
        <c:out value="${completionMessage}"/>
      </td>
  </tr>
  <tr>
      <td class="complete">&nbsp;
      </td>
  </tr>  
</table>  
<table class="navigator_30">
<tr>
    <td><a href="<c:url value="tss.html"/>">TSS</a></td>
    <td class="width_30"></td>
    <td><a href="javascript:closeTTS()">CLOSE</a></td>  
</tr>
</table>
<jsp:include page="foot.jsp"/>
