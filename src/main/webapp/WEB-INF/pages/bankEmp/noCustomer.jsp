<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="successMsg" style="text-align: center; color: green; font-size: 18px;">${success}</div>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
	
			<h3
				style="font-size: 22px; font-weight: 400; color: #2E9AFE; text-transform: uppercase; text-align: center">
			<spring:message code="label.noPurchaseOrderRecordFound"/>
				</h3>
	 </div>
</div>
