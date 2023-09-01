<%@include file="taglib_includes.jsp" %>
 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

			 <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
        			 <h3 style="text-align:center;"><spring:message code="label.bankEmployeeDetails"/></h3>
             </div> 
            <form:form action="selectUpdateBankEmp" method="post" commandName="endUserForm">  
          	<div class="col-sm-12 col-md-12 col-lg-12">
          		<table align="center">
          		<tr>
                  <td class="col-sm-6"><spring:message code="label.id"/></td>
				  <td class="col-sm-6"><form:input path="id" readonly="true"></form:input></td>
				</tr>
				<tr>
                  <td class="col-sm-6"><spring:message code="label.userName"/></td>
				  <td class="col-sm-6"><form:input path="userName" readonly="true"></form:input></td>
				</tr>
				<tr>
                  <td class="col-sm-6"><spring:message code="label.contactNumber"/></td>
				  <td class="col-sm-6"><form:input path="contactNo" readonly="true"></form:input></td>
				</tr>
				<tr>
                  <td class="col-sm-6"><spring:message code="label.currentRole"/></td>
				  <td class="col-sm-6"><form:input path="currentRole" readonly="true"></form:input></td>
				 </tr>
				 <tr>
                  <td class="col-sm-6"><spring:message code="label.email"/></td>
				  <td class="col-sm-6"><form:input path="email" readonly="true"></form:input></td>
				</tr>
				<tr>
				 <td class="col-sm-6"><spring:message code="label.status"/></td>
				 <td class="col-sm-6"><form:input path="status" readonly="true"></form:input></td>
				</tr>
				</table>
			</div>	
			 <div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
			 <div class="col-sm-12 col-md-12 col-lg-12">
           		 <table align="center">
					<tr>
						 <td><input type="submit" size="3" value="<spring:message code="label.sendEmailNotification"/>" class="btn btn-primary"></td>
						 <td><a href="#" class="btn btn-success"  onclick="javascript:window.location='bankEmpList';"><spring:message code="label.back"/></a></td>
						
					</tr>
				</table>
    	  	 </div>     
            </form:form>
         
        </div>
<script>
    $(document).ready( function () {
        $('#bootstrap-table').bdt();
    });
</script>
