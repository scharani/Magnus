<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

   <head>
      <title>Spring MVC Form Handling</title>
      <style>
      .error { 
        color: red; font-weight: bold; 
    }
   </style>
   </head>

   <body>
      <h2>Group Information</h2>
      <form:form method = "POST" action = "/MagnusProject/groupdet" commandName="groupdet">
         <table>
            <tr>
              <td><form:label path = "gname">Group Name</form:label></td>
                <td><form:input path = "gname" /></td>
                <td align="left"><form:errors path="gname" cssClass="error"/></td>
                     </tr>
            <tr>
               <td><form:label path = "vdate">Volunteer Date</form:label></td>
                    <td><form:input path = "vdate" /></td>
                <td align="left"><form:errors path="vdate" cssClass="error"/></td>
                                </tr>
             <tr>
               <td colspan = "2">
                <h2>New Volunteer</h2>
               </td>
            </tr>
             <tr>
               <td><form:label path = "fname">First Name:</form:label></td>
               <td><form:input path = "fname" /></td>
               
            </tr>
             <tr>
               <td><form:label path = "lname">Last Name:</form:label></td>
               <td><form:input path = "lname" /></td>
            </tr>
             <tr>
               <td><form:label path = "address">Address:</form:label></td>
               <td><form:input path = "address" /></td>
            </tr>
             <tr>
               <td><form:label path = "city">City:</form:label></td>
               <td><form:input path = "city" /></td>
            </tr>
              <tr>
               <td><form:label path = "state">State:</form:label></td>
               <td><form:input path = "state" /></td>
            </tr>
              <tr>
               <td><form:label path = "zip">Zip:</form:label></td>
               <td><form:input path = "zip" /></td>
            </tr>
            <tr>
               <td colspan = "2">
                  <input type = "submit" value = "Add To List"/>
               </td>
            </tr>                 
            </table>     
             </form:form>   
      <hr> 
      Volunteer List
      <br/> 
      <br/>
      <br/>
      <br/>   
      <form:form method = "POST" action = "/MagnusProject/submitgroup">   
         <c:if test="${not empty vlist}">
		<ul>
			<c:forEach var="listValue" items="${vlist}">
				<li> -${listValue.fname} ${listValue.lname} - ${listValue.address} ${listValue.city}- ${listValue.state} ${listValue.zip}</li>
			</c:forEach>
		</ul>
		<br> 
		 <input type = "submit" value = "Submit Group" align = "Right">
	</c:if>   
	  <c:if test="${empty vlist}"> 
       <input type = "submit" value = "Submit Group" disabled="disabled"/>
		  	</c:if> 
		  	    </form:form>  
               </body>              
               
</html>  