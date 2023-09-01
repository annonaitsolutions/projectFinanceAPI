<%@include file="taglib_includes.jsp"%>

 <script>
				function validateForm(){
					
					
					var userName  = document.getElementById('poKey');
					
					var canSubmit = true; 
					
					if (document.getElementById('poKey').value == ''){
						document.getElementById('poKeyError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('poKeyError').style.display='none';
					}
					
					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>

<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 style="text-align: center"><spring:message code="label.poKey"/></h3>
			</div>
	<form:form action="comparisonData" method="post"
		commandName="newEventForm" onsubmit="return validateForm()">
			<div class="col-sm-12 col-md-12 col-lg-12" style="text-align: center;">
				<font color="red">${success}</font>
			</div>
			

			<div class="col-sm-12 col-md-12">

				<table align="center">
					<tr>
						<td colspan="1"><spring:message code="label.poKey"/></td>
						<td><form:select id="poKey" path="poKey">
						<form:option value="">select</form:option>
								<form:options items="${newEventForm.buyerSameBankEvents}"
									itemValue="poKey" itemLabel="poKey" />
							    <form:options items="${newEventForm.buyerDiffBankEvents}"
									itemValue="poKey" itemLabel="poKey" />
							</form:select>
									</td>
									<td id="poKeyError" class="error" style="display:none;"><font color="red"><spring:message code="label.poValidation"/></font></td>
					</tr>
      			 
				</table>
				<table align="center">
					<tr>
						<td><input type="submit" size="3" value="Go"
							class="btn btn-primary"></td>
						
					</tr>
				</table>

		</div>


	</form:form>
</div>
