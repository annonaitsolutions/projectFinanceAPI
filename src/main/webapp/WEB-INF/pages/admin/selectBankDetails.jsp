<%@include file="taglib_includes.jsp"%>
 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

			 <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.updateBankDetails"/></h3>
			</div>
			<form:form action="updateBDetails" method="post" commandName="bankDetailsForm" onsubmit="return val();">

				<form:hidden path="transactionId" value="" />

				<table align="center">

					<tr>
						<td class="col-sm-6"><b><spring:message code="label.id"/></b></td>
						<td class="col-sm-6"><form:input path="id" id="id" readonly="true" />
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.bankName"/></b></td>
						<td class="col-sm-6"><form:input path="bankName" id="bankName" readonly="true" />
					</tr>

					<tr>
						<td class="col-sm-6"><b><spring:message code="label.location"/></b></td>
						<td class="col-sm-6"><form:input path="location" id="location" readonly="true"/></td>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.branch"/></b></td>
						<td class="col-sm-6"><form:input path="branch" id="branch" />
					<div id="branchError" style="display:none;color:red;"><spring:message code="label.validation"/></div></td>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.branchCode"/></b></td>
						<td class="col-sm-6"><form:input path="branchCode" id="branchCode" />
					</tr>

					<tr>
						<td class="col-sm-6"><b><spring:message code="label.contactNumber"/></b></td>
						<td class="col-sm-6"><form:input path="contactNo" id="contactNum" />
					<div id="contactNum1Error" style="display:none;color:red;"><spring:message code="label.validation"/></div></td>
					</tr>

					<tr>
						<td class="col-sm-6"><b><spring:message code="label.email"/></b></td>
						<td class="col-sm-6"><form:input path="email" id="email" />
				<div id="emailError" style="display:none;color:red;"><spring:message code="label.validation"/></div></td>
					</tr>

				</table>
				<div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
				<table align="center">
					<tr>
						<td><input type="submit" size="3" value="<spring:message code="label.confirm"/>"
							class="btn btn-primary"></td>
						<td><a href="bankDetails" class="btn btn-success">
								<spring:message code="label.back"/>
							</a>
						</td>
					</tr>
				</table>


			</form:form>

		</div>
<script>
	$(document).ready(function() {
		$('#bootstrap-table').bdt();
	});

	function radio_check() {
		alert("hi");
		if (document.updateReg.regulation.value == 'Banned') {
			document.getElementById("l1").disabled = true;

		} else {
			document.getElementById("l2").disabled = true;
		}

	}
</script>
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
				if (document.getElementById('branch').value == '') {
					document.getElementById('branchError').style.display = 'block';
					canSubmit = false;
				} else {
					document.getElementById('branchError').style.display = 'none';
				}
				
				if(canSubmit == false){
					return false;
				}
			}
				
		</script>