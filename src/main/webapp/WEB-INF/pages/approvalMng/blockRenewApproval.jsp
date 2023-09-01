<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-sm-9 col-md-9 body_fixed">
	<form:form action="updateBlockRenewAccount" commandName="endUserForm" onsubmit="return val();">
		<div class="col-sm-12 col-md-12 header_customer">
			<h3 align="center"><spring:message code="label.approval"/></h3>
		</div>
		<div class="col-sm-12 col-md-12">

			<table align="center">
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.id"/></b></td>
					<td class="col-sm-7"><form:input path="id" readonly="true"></form:input>
				</tr>
				<tr>
					<td class="col-sm-5" id="bStatus"><b><spring:message code="label.blockStatus"/></b></td>
					<td class="col-sm-6">
						<form:input path="accessStatus" id="accessStatus" class="form-control"  readonly="true"  
						            value="${endUser.accessStatus}" />
					</td>
				</tr>
				<tr>
					<td class="col-sm-5" id="rStatus"><b><spring:message code="label.renewStatus"/></b></td>
					<td class="col-sm-6">
						<form:input path="accRenewStatus" id="accRenewStatus" class="form-control"  readonly="true" 
						            value="${endUser.accRenewStatus}"/>
					</td>
				</tr>
				<tr>
					<td class="col-sm-5" id="expDate"><b><spring:message code="label.accountExpiryDate"/></b></td>
					<td class="col-sm-6">
					<fmt:formatDate var="formatDate" value="${endUser.accExpiryDate}" pattern="MM/dd/yyyy" />
						<form:input path="accExpiryDate" id="accExpiryDate" class="form-control"  readonly="true"
						            value="${formatDate}" />
					</td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.approveReject"/></b></td>
					<td class="col-sm-6">
						<form:radiobutton path="status" value="Approved"/><spring:message code="label.approve"/>
						<form:radiobutton path="status" value="Rejected"/><spring:message code="label.reject"/>
					</td>
				</tr>
			</table>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<p align="center" style="padding-top: 10px;">
				<input type="submit" class="btn btn-primary" value="Block" id="blockBtn"> 
				<input type="submit" class="btn btn-primary" value="Renew" id="renewBtn"> 
				<a href="getPedingBlockUnblockUsers" class="btn btn-success" onclick="javascript:window.location='#';"><spring:message code="label.back"/></a>
			</p>
		</div>
	</form:form>
</div>

<script>

	$(function() {
		if( "${endUser.accRenewStatus}" === "Pending") {
			$("#accessStatus").hide();
			$("#bStatus").hide();
			$("#blockBtn").hide();
		}else {
			$("#rStatus").hide();
			$("#accRenewStatus").hide();
			$("#expDate").hide();
			$("#accExpiryDate").hide();
			$("#renewBtn").hide();
		}
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

