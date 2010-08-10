<%@ include file="/WEB-INF/jsp/include.jsp" %>
<jsp:include page="head.jsp"/>
<jsp:include page="menu.jsp"/>

 <table>
    <tr>
      <td class="title"><strong><font size="6">Edit Topic Orientation</font></strong></td>
    </tr>
</table>
<table class="subtitle">
  <tr>    
  <td class="subtitle"><strong><font size="2">
      Course:&nbsp;<c:out value="${courseId}"/>&nbsp;<c:out value="${courseTitle}"/>
      </font></strong></td>
 </tr>
</table>
    <form:form method="post" commandName="topicOrientationSave" onreset="closeTTS()">        
        <table class="form" >
            <form:hidden path="courseId"/>               
           <tr> 		
            <td class="field"><strong><font size="2">Topic Title</font></strong></td>
	   </tr>
           <tr>		   
            <td class="input"><font size="2">
            <form:input path="topicTitle" size="70"/>           
              </font>
            </td>
            <td class="error"><form:errors path="topicTitle" cssClass="error"/>
            </td>                    
	   </tr>
           <tr> 		
            <td class="field"><strong><font size="2">Topic Introduction</font></strong></td>
	   </tr>              
           <tr>		   
            <td class="input"><font size="2">
            <form:textarea path="topicIntroduction" cols="75" rows="6"/>           
              </font>
            </td>
            <td class="error"><form:errors path="topicIntroduction" cssClass="error"/>
            </td>                    
	   </tr>           
           <tr> 		
            <td class="field"><strong><font size="2">Topic Question</font></strong></td>
           </tr>			
            <tr>			
            <td class="input"><font size="2">
 		 <form:textarea path="topicQuestion" cols="75" rows="6"/>
              </font></td>
            <td class="error"><form:errors path="topicQuestion" cssClass="error"/>
            </td>                 
            </tr>					
        </table>
            <p align="center">
             <input type=submit value="Save">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
             <input type=reset value="Cancel">
            </p>		
        </form:form>
<jsp:include page="foot.jsp"/>
