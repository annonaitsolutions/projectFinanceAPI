<%@include file="taglib_includes.jsp"%>

<div class="col-md-9 col-sm-9 col-lg-9 body_fixed">
<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
<h3 style="text-align: center"><spring:message code="label.eventsForSuppliers"/></h3>
</div>
	<form:form action="graphSellerdiffbankDatePage" method="post"
		commandName="masterPlanForm" onsubmit="return validateForm()">
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<div>
				<font color="red">${success}</font>
			</div>
			</div>

		<div class="col-sm-12 col-md-12 col-lg-12">

			<table align="center">
					<tr>
						<td colspan="1"><spring:message code="label.masterKey"/></td>
						<td><form:select id="masterKey" path="masterKey">
						<form:option value="select"></form:option>
								<form:options items="${masterPlanForm.masterPlanList}"
									itemValue="masterKey" itemLabel="masterKey" />
							</form:select>
							<div id="masterKeyError" style="display: none; color: red;"><spring:message code="label.pleaseSelectAMasterKey"/></div>
							</td>
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
	<script>
	function validateForm() {

		var canSubmit = true;

		if (document.getElementById('masterKey').value == 'select') {
			document.getElementById('masterKeyError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('masterKeyError').style.display = 'none';
		}

		if (canSubmit == false) {
			return false;
		}

	}
</script>