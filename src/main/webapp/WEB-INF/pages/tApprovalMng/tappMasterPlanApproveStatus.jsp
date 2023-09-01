<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3>
			<spring:message code="label.masterPlanApproval" />
		</h3>
	</div>
	<form:form action="tappMasterPlanApproveStatusConfirm"
		name="bankMasterPlanApproveStatusPost" method="post"
		commandName="tMasterPlanForm" onsubmit="return val();">
		<div class="col-sm-12 col-md-12 col-lg-12">
			<div class="successMsg">
				<b><font color="green">${success}</font></b>
			</div>
		</div>

		<div class="col-sm-12 col-md-12 col-lg-12">
			<table align="center">
				<form:hidden path="transactionId" value="" />
				<form:hidden path="id" value="" />
				<form:hidden path="customerEmail" value="" />
				<tr>
					<td class="col-sm-6"><b><spring:message
								code="label.customerName" /></b><span style="color: red">&nbsp;*</span>&nbsp;</td>
					<td class="col-sm-6"><form:input path="customer"
							readonly="true" placeholder="Enter name"></form:input>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message
								code="label.masterKey" />:</b></td>

					<td class="col-sm-6"><form:input path="masterKey"
							readonly="true" placeholder="Enter masterKey"></form:input></td>
				</tr>

				<tr>
					<td class="col-sm-6"><b><spring:message
								code="label.status" />:</b><span style="color: red">*</span></td>
					<td class="col-sm-6"><form:select path="managerStatus"
							id="managerStatus" style="width: 207px;">
							<form:option value="">
								<spring:message code="label.selectValue" />
							</form:option>
							<form:option value="Approved">
								<spring:message code="label.approve" />
							</form:option>
							<form:option value="Rejected">
								<spring:message code="label.reject" />
							</form:option>
						</form:select></td>
					<td id="managerStatusError" class="error" style="display: none;"><font
						color="red"><spring:message code="label.validation" /></font></td>
					<td id="managerStatusError" class="error" style="display: none"><spring:message
							code="label.validation" /></td>
				</tr>

				<tr>
					<td class="col-sm-6"><b><spring:message
								code="label.comment" />:</b></td>
					<td class="col-sm-6"><form:textarea path="managerComment"
							placeholder="Enter comment" id="comment"></form:textarea>
					<td id="commentError" class="error" style="display: none;"><font
						color="red"><spring:message code="label.validation" /></font></td>
					<td id="commentError" class="error" style="display: none"><spring:message
							code="label.validation" /></td>


				</tr>
			</table>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<table align="center">
				<tr>
					<td class="col-sm-8"><input type="submit"
						value="<spring:message code="label.confirm"/>"
						class="btn btn-primary"></td>
					<td><a href="tappMasterPlanDetails" class="btn btn-success"><spring:message
								code="label.back" /></a></td>
				</tr>
			</table>
		</div>
	</form:form>
</div>
<script>
	function val() {

		var userName = document.getElementById('comment');

		var canSubmit = true;

		if (document.getElementById('comment').value == '') {
			document.getElementById('commentError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('commentError').style.display = 'none';
		}
		if (document.getElementById('managerStatus').value == '') {
			document.getElementById('managerStatusError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('managerStatusError').style.display = 'none';
		}

		if (canSubmit == false) {
			return false;
		}
	}
</script>
