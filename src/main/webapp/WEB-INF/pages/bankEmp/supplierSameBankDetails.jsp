<%@include file="taglib_includes.jsp"%>

<script>
	function validateForm() {

		var masterKey = document.getElementById('goods');

		var canSubmit = true;

		if (document.getElementById('goods').value == '') {
			document.getElementById('goodsError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('goodsError').style.display = 'none';
		}

		if (canSubmit == false) {
			return false;
		}

	}
</script>
<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
	<form:form action="buyerSameBankEvent" method="post" commandName="newEventForm" onsubmit="return validateForm()">
		<div class="col-sm-12 col-md-12 header_customer">	
			<h3 style="text-align: center"><spring:message code="label.eventsForBuyerSameBank"/></h3>
		</div>
			<form:hidden path="userName" value=""></form:hidden>
			<form:hidden path="customerBank" value=""></form:hidden>
		<div class="col-sm-12 col-md-12">	
				<table align="center">
				<tr>
						<td colspan="1"><spring:message code="label.masterKey"/></td>
						<td><form:select path="masterKey">
								<form:options items="${newEventForm.masterPlanList}"
									itemValue="masterKey" itemLabel="masterKey" />
							</form:select></td>
					</tr>
					<tr>
						<td colspan="1"><spring:message code="label.supplierName"/></td>
						<td><form:select path="supplierName">
								<form:options items="${newEventForm.supplierList}"
									itemValue="supplierName" itemLabel="supplierName" />
							</form:select></td>
					</tr>

					<tr>
						<form:hidden path="supplierBank" value="${model.customerBankName}"
								readonly="true"></form:hidden>
					</tr> 
					
					<tr>
						<td colspan="1"><spring:message code="label.goods"/></td>
						<td><form:input path="goods" id="goods"></form:input>
						<div id="goodsError" style="display: none; color: red;">
					<spring:message code="label.validation"/></div>
				<div id="goodsError" style="display: none; color: red;">
					<spring:message code="label.validation"/></div>
						
						</td>
					</tr>
					
				</table>
			</div>
				<div class="col-sm-12 col-md-12">	
					<table align="center">
						<tr>
							<td><input type="submit" size="3" value="Continue"
								class="btn btn-primary"></td>
							<td><button type="button" name="Back"
									onclick="javascript:window.location='/annona/bnkEmp/newEvent';"
									class="btn btn-success"><spring:message code="label.back"/></button></td>
						</tr>
					</table>
				</div>


	</form:form>
</div>
