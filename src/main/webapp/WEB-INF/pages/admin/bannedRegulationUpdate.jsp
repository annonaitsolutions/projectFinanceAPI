<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

			 <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<div class="successMsg"
				style="text-align: center; color: green; font-size: 18px;">${success}</div>
		
				<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
				<form:form name="regulation" action="bannedRegulationUpdate"
					commandName="regulationsForm" onsubmit="return validateForm()">
					<table align="center">

						<tr>
							<td class="heading_text"><b><spring:message code="label.id"/></b></td>
							<td><form:input path="id" id="id"
									value="${model.regulationsForm.id}" readonly="true" />
						</tr>



						<tr>
							<td class="heading_text"><b><spring:message code="label.country"/></b></td>
							<td><form:input path="countryName" id="countryName"
									value="${model.regulationsForm.countryName}" readonly="true" />
						</tr>

						<tr>
							<td><b><spring:message code="label.regulationStatus"/></b></td>
							<td><form:input path="regulation"
									value="${model.regulationsForm.regulation}" readonly="true" />
							</td>
						</tr>
						<tr>
							<td><b><spring:message code="label.exportableGoods"/></b></td>
							<td><form:input path="exportedGoods"
									value="${model.regulationsForm.exportedGoods}" readonly="true" />

							</td>
							<form:hidden path="transactionId" value="" />
						</tr>

					</table>

					<div class="col-sm-11">
						<table align="center">
							<tr>
								<td class="col-sm-8"><input type="submit" size="3"
									value="<spring:message code="label.update"/>" class="btn btn-primary"></td>
								<td><a href="regulationPage" class="btn btn-success" ><spring:message code="label.back"/></a></td>

							</tr>
						</table>
					</div>

				</form:form>
			</div>
		</div>
