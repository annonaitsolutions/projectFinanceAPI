<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>



  <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
         	   	<h3 align="center"><spring:message code="label.updateRole"/></h3>
         	   </div> 
            <form:form name="updateRole1" action="updateRole" method="post" commandName="endUserForm" onsubmit="return val();">  
            
            <form:hidden path="transactionId"  value=""/>	
		<div class="col-sm-12 col-md-12 col-lg-12">
		<table align="center">
		
					<tr>
						  <td class="col-sm-5"><b><spring:message code="label.id"/></b></td>
						  <td class="col-sm-7"><form:input path="id" id="id"  readonly="true"/>  </td>
					</tr>
	 	  
									
					<tr>
					    <td class="col-sm-5"><b><spring:message code="label.roleId"/></b></td>
						<td class="col-sm-7"><form:input path="role" id="role"  readonly="true"/></td>
					
    				</tr>
					<tr>
						 <td class="col-sm-5"><b><spring:message code="label.contactNumber"/></b></td>
						<td class="col-sm-7"><form:input path="contactNo" placeholder="Enter contact Number" value="" id="contactNum"/>
					 <div id="contactNum1Error" style="display:none;color:red;"><spring:message code="label.validation"/></div>
						</td>	
																				
					</tr>	
					
					<tr>
						 <td class="col-sm-5"><b><spring:message code="label.email"/></b></td>
						<td class="col-sm-7"><form:input path="email" placeholder="Enter Email" value="" id="email"/>
						<div id="emailError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
						</td>	
																			
					</tr>	
					
					<tr>
						 <td class="col-sm-5"><b><spring:message code="label.userName"/></b></td>
						<td class="col-sm-7"><form:input path="userName"  placeholder="Enter UserName" value="" id="userName"/>
						<div id="userNameError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
						</td>	
					</tr>	
									
									
					

		</table>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12">	
		<table align="center">
			<tr>
				<td><input type="submit" size="3" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
				<td><a href="createRole" class="btn btn-success"><spring:message code="label.back"/></a></td>
				
			</tr>
		</table>
		
        </div>    
            </form:form>
         
        </div>


</body>

<script>
		function val(){
				
			
		    	var phoneNum            = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/; 
				var reg                 = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	
				var contactNum  = document.getElementById('contactNum');
				var email  = document.getElementById('email');
			    var userName =document.getElementById('userName');
				var canSubmit = true;


		
				 if(contactNum.value.match(/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/)&&!(document.getElementById('contactNum').value == '' )) {
			    	 
			    	  document.getElementById('contactNum1Error').style.display='none';
			      }
			      else {
			       
			        document.getElementById('contactNum1Error').style.display='block';
			        canSubmit = false;
			      }
				
				if(email.value.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/) &&!(document.getElementById('email').value == ''))
				  	{
				  		
				  		document.getElementById('emailError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('emailError').style.display='block';
				  	     canSubmit = false;
				  	}
				if (document.getElementById('userName').value == '') {
					document.getElementById('userNameError').style.display = 'block';
					canSubmit = false;
				} else {
					document.getElementById('userNameError').style.display = 'none';
				}
				
				if(canSubmit == false){
					return false;
				}
			}
				
		</script>