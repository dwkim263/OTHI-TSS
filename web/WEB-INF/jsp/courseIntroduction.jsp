<%@ include file="/WEB-INF/jsp/include.jsp" %>
<jsp:include page="head.jsp"/>
<jsp:include page="menu.jsp"/>
 <table>
  <tr>   
      <td class="title">Edit Course Introduction</td>
  </tr>
</table>
<form:form method="post" commandName="courseIntroductionSave" onreset="closeTTS()">
    <table class="form">
       <tr> 		
        <td class="field"><strong><font size="2">Course ID</font></strong></td>
       </tr>
       <tr>		   
        <td class="input"><font size="2">
        <form:input path="courseId" size="30"/>
          </font>
        </td>
        <td class="error">
        <form:errors path="courseId" cssClass="error"/>
        </td>                    
       </tr>            
       <tr> 		
        <td class="field"><strong><font size="2">Course Title</font></strong></td>
      </tr>
       <tr>		   
        <td class="input"><font size="2">
        <form:input path="courseTitle" size="70"/>           
          </font>
        </td>
        <td class="error">
        <form:errors path="courseTitle" cssClass="error"/>
        </td>                    
       </tr>
       <tr> 		
        <td class="field"><strong><font size="2">Course Objective</font></strong></td>
       </tr>			
        <tr>			
        <td class="input"><font size="2">
             <form:textarea path="courseObjective" cols="75" rows="6"/>
          </font></td>
        <td class="error">
        <form:errors path="courseObjective" cssClass="error"/>
        </td>                 
        </tr>
        <tr>	
          <td class="field"><strong><font size="2">Course References</font></strong></td>
        </tr>			
        <tr>
        <td class="input"><font size="2">
            <form:textarea path="courseReferences" cols="75" rows="6"/>
          </font></td>
        <td class="error">
        <form:errors path="courseReferences" cssClass="error"/>
        </td>              
        </tr>	     
    </table>
        <p align="center" >
         <input type=submit value="Save">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
         <input type=reset value="Cancel">
        </p>		
    </form:form>
<jsp:include page="foot.jsp"/>
