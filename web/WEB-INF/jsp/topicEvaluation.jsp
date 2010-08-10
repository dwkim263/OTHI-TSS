<%@ include file="/WEB-INF/jsp/include.jsp" %>
<jsp:include page="head.jsp"/>
<jsp:include page="menu.jsp"/>

    <table>
      <tr>
          <td class="title"><strong><font size="6">Edit Topic Evaluation</font></strong></td>
      </tr> 
    </table>  
    <table class="subtitle">
      <tr>
          <td class="subtitle"><strong><font size="2">
              Course:&nbsp;<c:out value="${courseId}"/>&nbsp;<c:out value="${courseTitle}"/>
              </font></strong></td>
      </tr> 
      <tr>
          <td class="subtitle"><strong><font size="2">
              Topic:&nbsp;<c:out value="${topicTitle}"/>
              </font></strong></td>
      </tr> 
      <tr> 		
        <td class="subtitle"><strong><font size="2">Topic Question</font></strong></td>
      </tr>
      <tr>            
        <td class="subtitle"><font size="2"><c:out value="${topicQuestion}"/></font>
        </td> 
      </tr>       
    </table>
    <form:form method="post" commandName="topicEvaluationSave" onreset="closeTTS()">        
        <table class="form">
            <form:hidden path="courseId"/>         
            <form:hidden path="topicTitle"/>     
           <tr> 		
            <td class="field"><strong><font size="2">Sample Answer</font></strong></td>
	   </tr>           
           <tr>		   
            <td class="input"><font size="2">
            <form:textarea path="topicSampleAnswer" cols="75" rows="6"/>           
              </font>
            </td>
            <td class="error"><form:errors path="topicSampleAnswer" cssClass="error"/>
            </td>                    
	   </tr>           
           <tr> 		
            <td class="field"><strong><font size="2">Conclusion</font></strong></td>
           </tr>			
            <tr>			
            <td class="input"><font size="2">
 		 <form:textarea path="topicConclusion" cols="75" rows="6"/>
              </font></td>
            <td class="error"><form:errors path="topicConclusion" cssClass="error"/>
            </td>     
            </tr>
           <tr>
            <td class="field"><strong><font size="2">Treasure Clue</font></strong></td>
           </tr>
            <tr>
            <td class="input"><font size="2">
 		 <form:textarea path="treasureClue" cols="75" rows="6"/>
              </font></td>
            <td class="error"><form:errors path="treasureClue" cssClass="error"/>
            </td>
            </tr>
        </table>
            <p align="center">
             <input type=submit value="Save">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
             <input type=reset value="Cancel">
            </p>		
        </form:form>
<jsp:include page="foot.jsp"/>
