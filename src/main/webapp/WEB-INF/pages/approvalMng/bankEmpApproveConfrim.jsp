<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function validateForm() {
		/* var d = new Date().getTime();
		var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
			var r = (d + Math.random() * 16) % 16 | 0;
			d = Math.floor(d / 16);
			return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
		});
		document.bankempupdate.transactionId.value = uuid; */
	}
</script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		 <h3><spring:message code="label.confirmationScreen"/></h3>
	</div>
				<form:form name="bankempupdate" action="updateEmpStatus" method="post"
					commandName="endUserForm" onsubmit="return validateForm();">
					<table align="center">
					
					<tr>
					<td class="col-sm-6"><b><spring:message code="label.id"/></b><span style="color:red">*</span></td>
					<td class="col-sm-6">
					<form:input path="id" id="id" 	value="${model.endUserForm.id}" readonly="true"/>
					</td>
				</tr>
						 <tr>
							<td class="col-sm-6"><b><spring:message code="label.userName"/></b></td>
							<td class="col-sm-6"><form:input path="userName" id="userName"
									value="${model.endUserForm.userName}" readonly="true" />
						</tr> 

						<%-- <tr>
							<td><b>Display Name :</b></td>
							<td><form:input path="displayName"
									value="${model.endUserForm.displayName}" readonly="true" /></td>
						</tr> --%>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.email"/></b></td>
							<td class="col-sm-6"><form:input path="email"
									value="${model.endUserForm.email}" readonly="true" /></td>

						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.contactNumber"/></b></td>
							<td class="col-sm-6"><form:input path="contactNo"
									value="${model.endUserForm.contactNo}" readonly="true" /></td>
						</tr>


                   	<tr>
							<td class="col-sm-6"><b><spring:message code="label.status"/></b></td>
							<td class="col-sm-6"><form:input path="status"
									value="${model.endUserForm.status}" readonly="true" /></td>
								<form:hidden path="transactionId"  value=""/>	
						</tr>
	                     <tr>
							<td class="col-sm-6"><b><spring:message code="label.comment"/></b></td>
							<td class="col-sm-6"><form:textarea path="comment" id="comment" placeholder="Enter Comment" style="height:120px;" readonly="true"></form:textarea></td>
						</tr>


					</table>

					<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="bankEmpApprov" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>

				</form:form>
			</div>
		</div>
</section>
