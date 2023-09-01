<%@include file="taglib_includes.jsp" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <div class="container">
    <div class="row">
        <div class="col-md-12">
             <h3 style="font-weight:400;font-size:22px;text-align:center;color:#2E9AFE;"><spring:message code="label.bankCustomerSubsidiaryNotificationDetails"/></h3> 
            <form:form action="bankCustomerSubsidiaryNotificationMail" method="post" commandName="customerSubsidiaryForm">  
          	<div class="col-sm-5">
          		
                  <div class="col-sm-3"><spring:message code="label.id"/></div>
										 <div class="col-sm-7"><form:input path="id" readonly="true" style="width:100%;"></form:input></div>
				
                  <div class="col-sm-3"><spring:message code="label.name"/></div>
										 <div class="col-sm-7"><form:input path="name" readonly="true" style="width:100%;"></form:input></div>
					
										 
									
				  <div class="col-sm-3"><spring:message code="label.contactNumber"/></div>
										 <div class="col-sm-7"><form:input path="contactNum" readonly="true" style="width:100%;"></form:input></div>
			
                  <div class="col-sm-3"><spring:message code="label.address"/></div>
										 <div class="col-sm-7"><form:input path="address" readonly="true" style="width:100%;"></form:input></div>
                  <div class="col-sm-3"><spring:message code="label.email"/></div>
										 <div class="col-sm-7"><form:input path="email" readonly="true" style="width:100%;"></form:input></div>
				
			
			</div>	
			 <div class="col-sm-11 spacer-5"></div>
			  <div class="col-sm-11">
           		 <table align="center">
					<tr>
						 <td><input type="submit" size="3" value="Send Email Notification" class="btn btn-primary"></td>
						 <td><button type="button" name="Back"  onclick="javascript:window.location='/annona/bnkEmp/bankCustomerSubsidiaryNotification';" class="btn btn-success">Back</button></td>
					</tr>
				</table>
    	  	 </div>     
            </form:form>
         
        </div>
    </div>
</div>

<script>
    $(document).ready( function () {
        $('#bootstrap-table').bdt();
    });
</script>

</body>