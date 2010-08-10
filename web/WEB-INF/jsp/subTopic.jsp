<%@ include file="/WEB-INF/jsp/include.jsp" %>
<jsp:include page="head.jsp"/>
<jsp:include page="menu.jsp"/>

    <table>
      <tr>
          <td class="title"><strong><font size="6">Add Sub-Topic</font></strong></td>
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
    </table>
    <form:form method="post" commandName="subTopicSave" onreset="closeTTS()">        
        <table class="form">            
           <form:hidden path="courseId"/>     
           <form:hidden path="topicTitle"/>          
           <tr> 	            
            <td class="field"><strong><font size="2">Sub-Topic Title</font></strong></td>
	   </tr>
           <tr>		   
            <td class="input"><font size="2">
            <form:input path="subTopicTitle" size="70"/>         
              </font>
            </td>
            <td class="error"><form:errors path="subTopicTitle" cssClass="error"/>
            </td>                    
	   </tr>            
           <tr> 		
            <td class="field"><strong><font size="2">Description</font></strong></td>
	   </tr>           
           <tr>		   
            <td class="input"><font size="2">
            <form:textarea path="subTopicDescription" cols="75" rows="6"/>           
              </font>
            </td>
            <td class="error"><form:errors path="subTopicDescription" cssClass="error"/>
            </td>                    
	   </tr>           
           <tr> 		
            <td class="field"><strong><font size="2">Clue</font></strong></td>
           </tr>			
            <tr>			
            <td class="input"><font size="2">
 		 <form:textarea path="subTopicClue" cols="75" rows="6"/>
              </font></td>
            <td class="error"><form:errors path="subTopicClue" cssClass="error"/>
            </td>     
            </tr>
        </table>
            <p align="center">
             <input type=submit value="Save">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
             <input type=reset value="Cancel">
            </p>		
        </form:form>
<jsp:include page="foot.jsp"/>
