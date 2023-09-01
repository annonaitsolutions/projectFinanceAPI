<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
       <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
             <h3><spring:message code="label.bankEmployeeDetails"/></h3>
        </div> 
            <form:form action="bankCustomerBranchNotificationMail" method="post" commandName="customerBranchForm">  
          	<table align="center">
          	<tr>	
                  <td class="col-sm-6"><spring:message code="label.id"/></td>
				  <td class="col-sm-6"><form:input path="id" readonly="true"></form:input></td>
			</tr>	
			<tr>
                  <td class="col-sm-6"><spring:message code="label.name"/></td>
				  <td class="col-sm-6"><form:input path="name" readonly="true"></form:input></td>
			</tr>	
										 
			<tr>						
				  <td class="col-sm-6"><spring:message code="label.contactNumber"/></td>
				  <td class="col-sm-6"><form:input path="contactNum" readonly="true"></form:input></td>
			</tr>
			<tr>
                  <td class="col-sm-6"><spring:message code="label.address"/></td>
				  <td class="col-sm-6"><form:input path="address" readonly="true"></form:input></td>
			</tr>	  
				<tr>
                  <td class="col-sm-6"><spring:message code="label.email"/></td>
				  <td class="col-sm-6"><form:input path="email" readonly="true"></form:input></td>
				</tr>
			
				
			</table>	
			 <div class="col-sm-11 spacer-5"></div>
			  <div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" size="3" value="<spring:message code="label.sendEmailNotification"/>" class="btn btn-primary"></td>
							<td><a href="bankCustomerBranchNotification" class="btn btn-success"><spring:message code="label.back"/></a></td>
							
					
						</tr>
					</table>
		        </div>    
            </form:form>
         
        </div>
    </div>
</div>



</body>