<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 body_fixed">
	<form:form action="renewAccount" commandName="endUserForm" onsubmit="return val();">
		<div class="col-sm-12 col-md-12 header_customer">
			<h3 align="center"><spring:message code="label.renewAccount"/></h3>
		</div>
		<div class="col-sm-12 col-md-12">

			<table align="center">
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.id"/></b></td>
					<td class="col-sm-7"><form:input path="id" readonly="true"></form:input>
				</tr>


				<tr>
					<td class="col-sm-5"><b><spring:message code="label.accountExpiryDate"/></b><span style="color: red">*</span></td>
					<td id="e1" class="col-sm-6">
						<form:input path="accExpiryDate" class="form-control" placeholder="Select date"
							style="width: 206px;" readonly="true" id="datepicker" />
						<i class="fa fa-calendar"></i>
						<span id="expiryDateError" class="error" style="display: none;">
							<font color="red">
								<spring:message	code="label.validation" />*
							</font>
						</span>
				    </td>

				</tr>
			</table>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.update"/>" class="btn btn-primary"></td>
							<td><a href="getUsersForBlockUnblock" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>		
	</form:form>
</div>

<script>

	$(function() {
		$("#datepicker").datepicker({format: 'dd/mm/yyyy'});
	});

	function val() {

		var accExpiryDate = document.getElementById('datepicker').value;

		var canSubmit = true;

		if (accExpiryDate == '') {
			document.getElementById('expiryDateError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('expiryDateError').style.display = 'none';
		}

		if (canSubmit == false) {
			return false;
		}
	}
</script>

