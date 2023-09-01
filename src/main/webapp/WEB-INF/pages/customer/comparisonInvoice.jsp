<%@include file="taglib_includes.jsp"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <script>
				function validateForm(){
					
					
					var userName  = document.getElementById('invoiceKey');
					
					var canSubmit = true; 
					
					if (document.getElementById('invoiceKey').value == ''){
						document.getElementById('invoiceKeyError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('invoiceKeyError').style.display='none';
					}
					
					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>
<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 style="text-align: center"><spring:message code="label.invoiceKey"/></h3>
			</div>
	<form:form action="comparisonInvoiceData" method="post"
		commandName="newEventForm" onsubmit="return validateForm()">
			<div class="col-sm-12 col-md-12 col-lg-12" style="text-align: center;">
				<font color="red">${success}</font>
			</div>
			

			<div class="col-sm-12 col-md-12">

				<table align="center">
					<tr>
						<td colspan="1"><spring:message code="label.invoiceKey"/></td>
						<td><form:select id="invoiceKey" path="invoiceKey">
						<form:option value="">select</form:option>
								<form:options items="${newEventForm.sellerSameBankEvents}"
									itemValue="invoiceKey" itemLabel="invoiceKey" />
							    <form:options items="${newEventForm.sellerDiffBankEvents}"
									itemValue="invoiceKey" itemLabel="invoiceKey" />
							</form:select>
							
							</td><td id="invoiceKeyError" class="error" style="display:none;"><font color="red"><spring:message code="label.invoiceValidation"/></font></td>
					</tr>
      			 
				</table>
				<table align="center">
					<tr>
						<td><input type="submit" size="3" value="<spring:message code="label.go"/>"
							class="btn btn-primary"></td>
						
					</tr>
				</table>

		</div>


	</form:form>
</div>
