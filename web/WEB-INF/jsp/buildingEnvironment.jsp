<%@ include file="/WEB-INF/jsp/include.jsp" %>
<jsp:include page="head.jsp"/>
<jsp:include page="menu.jsp"/>
<%@ page import="java.util.List, java.util.LinkedList" %>
 <table>
  <tr>   
      <td class="title">Building Learning Environment</td>
  </tr>
</table>
<form:form method="post" commandName="buildingEnvironment" onreset="closeTTS()">
    <table class="form">
    <c:if test="${empty param.courseId}">
       <tr align="left"> 		
        <td class="field"><strong><font size="2">Course</font></strong></td>
       </tr>

	<!--
         Select course
	-->
       <tr>
        <td class="input"><font size="4">
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
    <c:if test="${!empty param.courseId}">
       <tr align="left">
        <td class="field"><strong><font size="2">Course</font></strong></td>
        <td class="field"><strong><font size="2">Topic</font></strong></td>
        <td class="field"><strong><font size="2">Station</font></strong></td>
       </tr>
    <tr>
        <td class="input">
            <c:forEach var="course" items="${courses}">
              <c:if test="${param.courseId == course.key}">
                <input type=hidden name="courseId" value="<c:out value="${course.key}"/>"/>
                <c:out value="${course.key}:${course.value.courseTitle}"/>                
               </c:if>                
            </c:forEach>        
       </td>
        <!--
             Select topic
        -->
        <c:if test="${empty param.topicTitle}">     
        <td  class="input"><font size="4">
        <spring:bind path="topicTitle"> 
          <select name="${status.expression}">
            <c:forEach var="topic" items="${topics}">
              <c:if test="${topic.value.courseId == param.courseId}">            
              <option value="<c:out value="${topic.value.topicTitle}"/>"
                <c:if test="${topic.key == status.value}"> selected="selected"</c:if>> 
                <c:out value="${topic.value.topicTitle}"/>   
              </option>
              </c:if>          
            </c:forEach>
          </select>  
        </spring:bind>      
        </font></td>                
   </c:if>   
    <c:if test="${!empty param.topicTitle}">       
        <td  class="input">
            <input type=hidden name="topicTitle" value="<c:out value="${param.topicTitle}"/>"/>
            <c:out value="${param.topicTitle}"/>
        </td>                
	<!--
         Assign a station
	-->
    <c:if test="${empty param.station}">  
        <td  class="input"><font size="4">
        <spring:bind path="station">     
          <select name="${status.expression}">
           <option value="Orientation"             
                <c:if test="${param.station == 'Orientation'}"> selected="selected"</c:if>>
            Orientation
           </option>                
            <c:forEach var="subTopic" items="${subTopics}">
              <c:if test="${subTopic.value.topicTitle == param.topicTitle}">     
              <option value="<c:out value="${subTopic.value.subTopicTitle}"/>"              
                <c:if test="${param.station == subTopic.value.subTopicTitle}"> selected="selected"</c:if>>
                <c:out value="${subTopic.value.subTopicTitle}"/> 
              </option>
              </c:if>          
            </c:forEach>
           <option value="Evaluation"             
                <c:if test="${param.station == 'Evaluation'}"> selected="selected"</c:if>>
            Evaluation
           </option>                    
        </select>
        </spring:bind>      
        </font></td>        
     </c:if>
     <c:if test="${!empty param.station}">
             <td class="input">
            <input type=hidden name="station" value="<c:out value="${param.station}"/>"/>
            <c:out value="${param.station}"/>
        </td>
     </c:if>
     </c:if>
       </tr>
   </c:if>
     <c:if test="${!empty param.station}">      
	<!--
         Assign a game map for the station
	-->
     <c:if test="${empty param.placeName}">
       <tr> 		
        <td class="field"><strong><font size="2">Game Map</font></strong></td>
        <td class="field"><strong><font size="2">Nick Name</font></strong></td>
       </tr>
        <tr>        
        <td  class="input"><font size="4">
        <spring:bind path="placeName">     
          <select name="${status.expression}">
            <c:forEach var="placeName" items="${places}">
              <option value="<c:out value="${placeName}"/>"
                <c:if test="${placeName== status.value}"> selected="selected"</c:if>>
                <c:out value="${placeName}"/> 
              </option>
            </c:forEach>
          </select>
        </spring:bind>      
        </font></td>  
        <td  class="input"><font size="4"><form:input path="placeNickName"/>            
        </font></td>                     
        </tr>	
        </c:if>
        </c:if>       
        <c:if test="${!empty param.placeName}">
       <tr> 		
        <td class="field"><strong><font size="2">Game Map</font></strong></td>
        <td class="field"><strong><font size="2">Nick Name</font></strong></td>
       </tr>
        <tr>        
        <td  class="input">
            <input type=hidden name="placeName" value="<c:out value="${param.placeName}"/>"/>
                <c:out value="${param.placeName}"/> 
        </td>
        <td  class="input">
            <input type=hidden name="placeNickName" value="<c:out value="${placeNickName}"/>"/>
                <c:out value="${placeNickName}"/>
        </td>
       </tr>
        <!--
             Assign guide
        -->
        <c:if test="${param.station != 'Evaluation'}">
        <c:if test="${empty param.guideName}">
        <tr> 		
            <td class="field"><strong><font size="2">Guide</font></strong></td>
            <td class="field"><strong><font size="2">Nick Name</font></strong></td>
        </tr>
        <tr>
        <td  class="input"><font size="4">
        <spring:bind path="guideName">     
          <select name="${status.expression}">
            <c:forEach var="guideName" items="${gameGuides}">
              <option value="<c:out value="${guideName}"/>"
                <c:if test="${guideName == status.value}"> selected="selected"</c:if>> 
                <c:out value="${guideName}"/> 
              </option>
            </c:forEach>
          </select>
        </spring:bind>
        </font></td>
        <td  class="input"><font size="4"><form:input path="guideNickName"/>
        </font></td>
        </tr>
       </c:if>
       </c:if>
	<!--
         Assign treasure Box
	-->
        <c:if test="${param.station == 'Evaluation'}">
        <c:if test="${empty param.treasureBoxName}">
        <tr>
            <td class="field"><strong><font size="2">Treasure Box</font></strong></td>
        </tr>
        <tr>
        <td  class="input"><font size="4">
        <spring:bind path="treasureBoxName">
          <select name="${status.expression}">
            <c:forEach var="treasureBoxName" items="${treasureBoxes}">
              <option value="<c:out value="${treasureBoxName}"/>"
                <c:if test="${treasureBoxName == status.value}"> selected="selected"</c:if>>
                <c:out value="${treasureBoxName}"/>
              </option>
            </c:forEach>
          </select>
        </spring:bind>
        </font></td>
        </tr>
       </c:if>
       </c:if>
       </c:if>

        <!--
             Input Next Stations
        -->
       <c:if test="${!empty param.guideName}">
       <tr>
        <td class="field"><strong><font size="2">Guide</font></strong></td>
        <td class="field"><strong><font size="2">Nick Name</font></strong></td>
       </tr>
        <tr>
        <td  class="input">
            <input type=hidden name="guideName" value="<c:out value="${param.guideName}"/>"/>
                <c:out value="${param.guideName}"/>
        </td>
        <td  class="input">
            <input type=hidden name="guideNickName" value="<c:out value="${param.guideNickName}"/>"/>
                <c:out value="${param.guideNickName}"/>
        </td>
       </tr>
     <c:if test="${empty param.nextStations}">
        <tr>
            <td class="field"><strong><font size="2">Next Stations</font></strong></td>
        </tr>
        <tr>
        <td  class="input"><font size="4">
        <spring:bind path="nextStations">
          <select name="${status.expression}" multiple="true">
            <c:forEach var="subTopic" items="${subTopics}">
              <c:if test="${subTopic.value.topicTitle == param.topicTitle}">
              <c:if test="${param.station != subTopic.value.subTopicTitle}">
              <option value="<c:out value="${subTopic.value.subTopicTitle}"/>">
                <c:out value="${subTopic.value.subTopicTitle}"/>
              </option>
              </c:if>
              </c:if>
            </c:forEach>
           <option value="Evaluation">
            Evaluation
           </option>
          </select>
        </spring:bind>
        </font></td>
        </tr>
       </c:if>
       </c:if>
       </table>

      <c:if test="${!empty param.nextStations}">
      <table class="form">
       <tr>
        <td class="field"><strong><font size="2">Next Stations</font></strong></td>
       </tr>
    <%
    String[] quests = request.getParameterValues("nextStations");
    List<String> nextStations = new LinkedList<String>();
    for (String s : quests) {
        nextStations.add(s);
    }
    pageContext.setAttribute("quests", nextStations);
    %>
     <tr>
        <td  class="input"><font size="4">
        <spring:bind path="nextStations">
          <select name="${status.expression}" multiple="true" disabled="true">
           <c:forEach var="nextStations" items="${quests}">
              <option value="<c:out value="${nextStations}"/>"  selected="selected">
                <c:out value="${nextStations}"/>
              </option>
           </c:forEach>
          </select>
        </spring:bind>
        </font></td>
       </tr>
        <!--
             Update introduction and question for orientation
        -->
        <c:if test="${param.station == 'Orientation'}">
           <tr>          
            <td class="field"><strong><font size="2">Introduction by guide
            </font></strong></td>
           </tr>
           <tr>             
            <td class="input"><font size="2">        
                 <textarea name="introduction" cols="75" rows="6">
                 <c:out value="${introduction}"/>
                 </textarea>
              </font></td>
            <td class="error">
            <form:errors path="introduction" cssClass="error"/>
            </td>              
            </tr>     
           <tr>          
            <td class="field"><strong><font size="2">Question by guide
            </font></strong></td></tr>
           <tr>             
            <td class="input"><font size="2">        
                 <textarea name="question" cols="75" rows="6">
                 <c:out value="${question}"/>
                 </textarea>
              </font></td>
            <td class="error">
            <form:errors path="question" cssClass="error"/>
            </td>              
            </tr>
            <tr>
            <td  class="input">
                <input type=hidden name="completedStationType" value="Orientation"/>
            </td>
            </tr>
        </c:if>

        <!--
             Update sub-topic description
        -->
        <c:if test="${param.station != 'Orientation'}">            
        <c:forEach var="subTopic" items="${subTopics}">
          <c:if test="${subTopic.value.subTopicTitle == param.station}">   
           <tr>          
            <td class="field"><strong><font size="2"><c:out value="Information by ${subTopic.value.subTopicTitle}"/>
            </font></strong></td></tr>
           <tr>          
            <spring:bind path="description">
            <td class="input"><font size="2">        
                 <textarea name="${status.expression}" cols="75" rows="6">
                 <c:out value="${subTopic.value.subTopicDescription}"/>
                 </textarea>
              </font></td>
            <td class="error">
            <form:errors path="description" cssClass="error"/>
            </td>     
            </spring:bind>            
            </tr>                              
          </c:if>          
        </c:forEach>
        <tr>
        <td  class="input">
            <input type=hidden name="completedStationType" value="SubType"/>
        </td>
        </tr>  
        </c:if>
     </table>
      </c:if>

    <c:if test="${!empty param.treasureBoxName}">
       <table class="form">
       <tr>
        <td class="field"><strong><font size="2">Treasure Box</font></strong></td>
       </tr>
        <tr>
        <td  class="input">
            <input type=hidden name="treasureBoxName" value="<c:out value="${param.treasureBoxName}"/>"/>
                <c:out value="${param.treasureBoxName}"/>
        </td>
       </tr>
    <!--
         Update conclusion
    -->
       <tr>
        <td class="field"><strong><font size="2">Conclusion</font></strong></td>
       </tr>
        <tr>
        <td class="input"><font size="2">
             <textarea name="conclusion" cols="75" rows="6">
             <c:out value="${conclusion}"/>
             </textarea>
          </font></td>
        <td class="error">
        <form:errors path="conclusion" cssClass="error"/>
        </td>
        </tr>
       </table>
        <!--
             define rewards
        -->
       <table class="form">
       <tr>
        <td class="field"><strong><font size="2">Reward</font></strong></td>
       </tr>
       <tr>
           <td>
               <table>
                   <tr>
                       <td width="10%">Golds</td>
                       <td width="10%">Experience Points</td>
                       <td width="10%">Tools</td>
                       <td width="20%">Armors</td>
                       <td width="20%">Weapons</td>
                   </tr>
                   <tr>
                       <td width="10%"><form:input path="golds"/>
                       </td>
                       <td width="10%"><form:input path="experiencePoints"/>
                       </td>
                        <td  width="10%">
                        <spring:bind path="tool">
                          <select name="${status.expression}">
                           <option value="none">
                            None
                           </option>
                            <c:forEach var="tool" items="${tools}">
                              <option value="<c:out value="${tool.value.name}"/>"
                                <c:if test="${tool.value.name == status.value}"> selected="selected"</c:if>>
                                <c:out value="${tool.value.name}"/>
                              </option>
                            </c:forEach>
                          </select>
                        </spring:bind>
                        </td>
                        <td  width="20%">
                        <spring:bind path="armor">
                          <select name="${status.expression}">
                           <option value="none">
                            None
                           </option>
                            <c:forEach var="armor" items="${armors}">
                              <option value="<c:out value="${armor.value.name}"/>"
                                <c:if test="${armor.value.name == status.value}"> selected="selected"</c:if>>
                                <c:out value="${armor.value.name}"/>
                              </option>
                            </c:forEach>
                          </select>
                        </spring:bind>
                        </td>
                        <td  width="20%">
                        <spring:bind path="weapon">
                          <select name="${status.expression}">
                           <option value="none">
                            None
                           </option>
                            <c:forEach var="weapon" items="${weapons}">
                              <option value="<c:out value="${weapon.value.name}"/>"
                                <c:if test="${weapon.value.name == status.value}"> selected="selected"</c:if>>
                                <c:out value="${weapon.value.name}"/>
                              </option>
                            </c:forEach>
                          </select>
                        </spring:bind>
                        </td>
                   </tr>
               </table>
           </td>
       </tr>
        <tr>
        <td  class="input">
            <input type=hidden name="completedStationType" value="Evaluation"/>
        </td>
        </tr>
    </table>
    </c:if>
    <p align="center" >
     <input type=submit value="Save">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
     <input type=reset value="Cancel">
    </p>
    </form:form>
<jsp:include page="foot.jsp"/>