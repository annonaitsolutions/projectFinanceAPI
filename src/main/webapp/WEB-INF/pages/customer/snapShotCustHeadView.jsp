<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
	<div class="col-sm-12 col-md-12 col-lg-12">
	 	<div  class="successMsg"><b><font color="green">${success}</font></b></div>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3><spring:message code="label.masterPlanDetails"/></h3>
	</div>
	<div class="col-sm-12 col-md-6 col-lg-6">
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 style="font-size:19px; font-weight:400; color:#2E9AFE;"><spring:message code="label.masterPlanMainDetails"/></h3>
		</div>
		<table>
						<tr>
					<td class="heading_text"><b><spring:message code="label.masterKey"/>:</b></td>
						<td>${model.masterList.masterKey}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.transaction"/>:</b></td>
						<td>${model.masterList.transactionId}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.customer"/>:</b></td>
						<td>${model.masterList.customer}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.currencyType"/>:</b></td>
						<td>${model.masterList.currencySymbol}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.bankEmployeeStatus"/>:</b></td>
						<td>${model.masterList.status}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.comment"/>:</b></td>
						<td>${model.masterList.comment}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.managerStatus"/>:</b></td>
						<td>${model.masterList.managerStatus}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.comment"/>:</b></td>
						<td>${model.masterList.managerComment}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.managerStatus"/>:</b></td>
						<td>${model.masterList.managerStatus}</td>
						</tr>
		</table>
		</div>
		<div class="col-sm-12 col-md-6 col-lg-6">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
						<h3 style="font-size:19px; font-weight:400; color:#2E9AFE;"><spring:message code="label.productInfo"/></h3>
				</div>
			<table>
						<tr>
						<td class="heading_text"><b><spring:message code="label.category"/>:</b></td>
						<td>${model.masterList.category}</td>
						</tr>
					<tr>
					<td class="heading_text"><b><spring:message code="label.product"/>:</b></td>
						<td>${model.masterList.product}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.description"/>:</b></td>
						<td>${model.masterList.description}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.license"/>:</b></td>
						<td>${model.masterList.licence}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.weight"/>:</b></td>
						<td>${model.masterList.weight}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.quantity"/>:</b></td>
						<td>${model.masterList.quantity}</td>
						</tr>
			</table>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
						<h3 style="font-size:19px; font-weight:400; color:#2E9AFE;"><spring:message code="label.costInfo"/></h3>
				</div>
				<table>
						<tr>
					<td class="heading_text"><b><spring:message code="label.buyingCost"/>:</b></td>
						<td>${model.masterList.buyingCost}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.tenure"/>:</b></td>
						<td>${model.masterList.tenure}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.sanctionedAmount"/>:</b></td>
						<td>${model.masterList.buyingCostSanc}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.workingCapital"/>:</b></td>
						<td>${model.masterList.workingCapital}</td>
						</tr>
					  <tr>
					<td class="heading_text"><b><spring:message code="label.tenure"/>:</b></td>
						<td>${model.masterList.wcTenure}</td>
						</tr> 
						  <tr>
					<td class="heading_text"><b><spring:message code="label.sanctionedAmount"/>:</b></td>
						<td>${model.masterList.wcTotalAmount}</td>
						</tr> 
						 <tr>
					<td class="heading_text"><b><spring:message code="label.interest"/>:</b></td>
						<td>${model.masterList.wcTotalInterest}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.sellingCost"/>:</b></td>
						<td>${model.masterList.sellingCost}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.tenure"/>:</b></td>
						<td>${model.masterList.sellingTenure}</td>
						</tr>
			</table>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
						<h3><spring:message code="label.buyingCostDetails"/></h3>
				</div>
						<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.material"/></th>
						<th><spring:message code="label.country"/></th>
						<th><spring:message code="label.quantity"/></th>
						<th><spring:message code="label.cost"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty buyingList}">
						<c:forEach items="${buyingList}" var="buyingList">
							<tr>
								<td><c:out value="${buyingList.material}"></c:out></td>
								<td><c:out value="${buyingList.country}"></c:out></td>
								<td><c:out value="${buyingList.quantity}"></c:out></td>
								<td><c:out value="${buyingList.cost}"></c:out></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
				</table>
			</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
						<h3><spring:message code="label.sellerBuyingCostDetails"/></h3>
					</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.buyerName"/></th>
						<th><spring:message code="label.product"/></th>
						<th><spring:message code="label.quantity"/></th>
						<th><spring:message code="label.amount"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty sellerList}">
						<c:forEach items="${sellerList}" var="buyingList">
							<tr>
								<td><c:out value="${buyingList.buyerName}"></c:out></td>
								<td><c:out value="${buyingList.finalPro}"></c:out></td>
								<td><c:out value="${buyingList.quantity}"></c:out></td>
								<td><c:out value="${buyingList.amt}"></c:out></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
				</table>
					<div class="col-sm-12 col-md-12 col-lg-12">	
						<h3 style="font-size:19px; font-weight:400; color:#2E9AFE;"><spring:message code="label.workingCapitalDetails"/></h3>
					</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.reason"/></th>
						<th><spring:message code="label.amount"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty wclist}">
						<c:forEach items="${wclist}" var="buyingList">
							<tr>
								<td><c:out value="${buyingList.reason}"></c:out></td>
								<td><c:out value="${buyingList.amount}"></c:out></td>
								
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
				</table>
				
				
			</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
						<h3><spring:message code="label.collateralDetails"/></h3>
					</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.propertyType"/></th>
						<th><spring:message code="label.address"/></th>
						<th><spring:message code="label.area"/></th>
						<th><spring:message code="label.costPerArea"/></th>
						<th><spring:message code="label.purchaseValue"/></th>
						<th><spring:message code="label.currentMarketvalue"/></th>
						<th><spring:message code="label.existingCharge"/></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty collateralList}">
						<c:forEach items="${collateralList}" var="collateralList">
							<tr>
								<td><c:out value="${collateralList.propertyType}"></c:out></td>
								<td><c:out value="${collateralList.propertyAddress}"></c:out></td>
								<td><c:out value="${collateralList.propertyArea}"></c:out></td>
								<td><c:out value="${collateralList.pCostPerArea}"></c:out></td>
							    <td><c:out value="${collateralList.propertyPurchaseValue}"></c:out></td>
							    <td><c:out value="${collateralList.propertyCurrValue}"></c:out></td>
							    <td><c:out value="${collateralList.pExisCharge}"></c:out></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
				<thead>
					<tr>
						<th><spring:message code="label.vehicle"/></th>
						<th><spring:message code="label.type"/></th>
						<th><spring:message code="label.purchaseValue"/></th>
						<th><spring:message code="label.currentMarketCost"/></th>
						<th><spring:message code="label.details"/></th>
						<th><spring:message code="label.insurance"/></th>
						<th><spring:message code="label.startDate"/></th>
						<th><spring:message code="label.endDate"/></th>
						<th><spring:message code="label.existingCharge"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty collateralList}">
						<c:forEach items="${collateralList}" var="collateralList">
							<tr>
								<td><c:out value="${collateralList.vehicle}"></c:out></td>
								<td><c:out value="${collateralList.vType}"></c:out></td>
								<td><c:out value="${collateralList.vPurchaseValue}"></c:out></td>
								<td><c:out value="${collateralList.vcurrValue}"></c:out></td>
							    <td><c:out value="${collateralList.vDeatils}"></c:out></td>
							    <td><c:out value="${collateralList.vInsurence}"></c:out></td>
							    <td><c:out value="${collateralList.vInsuranceStart}"></c:out></td>
							    <td><c:out value="${collateralList.vInsuranceEnd}"></c:out></td>
							    <td><c:out value="${collateralList.vExistingCharge}"></c:out></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
					<thead>
					<tr>
						<th><spring:message code="label.cashFixedDeposit"/></th>
						<th><spring:message code="label.amount"/></th>
						<th><spring:message code="label.financialInstitution"/></th>
						<th><spring:message code="label.branch"/></th>
						<th><spring:message code="label.location"/></th>
						<th><spring:message code="label.fdStartDate"/></th>
						<th><spring:message code="label.fdEndDate"/></th>
						<th><spring:message code="label.fdInterestRate"/></th>
						<th><spring:message code="label.existingCharge"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty collateralList}">
						<c:forEach items="${collateralList}" var="collateralList">
							<tr>
								<td><c:out value="${collateralList.cash}"></c:out></td>
								<td><c:out value="${collateralList.amount}"></c:out></td>
								<td><c:out value="${collateralList.finInstitution}"></c:out></td> 
								<td><c:out value="${collateralList.branch}"></c:out></td>
							    <td><c:out value="${collateralList.location}"></c:out></td>
							    <td><c:out value="${collateralList.fDStartDate}"></c:out></td>
							    <td><c:out value="${collateralList.fDEndDate}"></c:out></td>
							    <td><c:out value="${collateralList.fDInterestRate}"></c:out></td>
							    <td><c:out value="${collateralList.cExistimgCharge}"></c:out></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
					<thead>
					<tr>
						<th><spring:message code="label.insurancePolicy"/></th>
						<th><spring:message code="label.beneficiaryName"/></th>
						<th><spring:message code="label.nominee"/></th>
						<th><spring:message code="label.policyStartDate"/></th>
						<th><spring:message code="label.policyEndDate"/></th>
						<th><spring:message code="label.premiumPayment"/></th>
						<th><spring:message code="label.valueOfPolicy"/></th>
						<th><spring:message code="label.existingCharge"/></th>
						<th></th>
						</tr>
				</thead>
				<tbody>
					<c:if test="${! empty collateralList}">
						<c:forEach items="${collateralList}" var="collateralList">
							<tr>
								<td><c:out value="${collateralList.insurancePolicy}"></c:out></td>
								<td><c:out value="${collateralList.benficiaryName}"></c:out></td>
								<td><c:out value="${collateralList.nominee}"></c:out></td>
								<td><c:out value="${collateralList.policyStartDate}"></c:out></td>
							    <td><c:out value="${collateralList.policyEndDate}"></c:out></td>
							    <td><c:out value="${collateralList.premiumPayment}"></c:out></td>
							    <td><c:out value="${collateralList.valueOfPolicy}"></c:out></td>
							    <td><c:out value="${collateralList.policyExisCharge}"></c:out></td>
							 	</tr>
						</c:forEach>
					</c:if>

				</tbody>
				<thead>
					<tr>
						<th><spring:message code="label.shares"/></th>
						<th><spring:message code="label.sharesExistingPrice"/></th>
						<th><spring:message code="label.companyDetails"/></th>
						<th><spring:message code="label.turnOver"/></th>
						<th><spring:message code="label.profit"/></th>
						<th><spring:message code="label.lineOfActivities"/></th>
						<th><spring:message code="label.existingCharge"/></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty collateralList}">
						<c:forEach items="${collateralList}" var="collateralList">
							<tr>
								<td><c:out value="${collateralList.shares}"></c:out></td>
								<td><c:out value="${collateralList.sharesExisPrice}"></c:out></td>
								<td><c:out value="${collateralList.details}"></c:out></td>
								<td><c:out value="${collateralList.turnOver}"></c:out></td>
							    <td><c:out value="${collateralList.profit}"></c:out></td>
							    <td><c:out value="${collateralList.lineOfActivity}"></c:out></td>
							    <td><c:out value="${collateralList.sharesExisCharge}"></c:out></td>
							   	</tr>
						</c:forEach>
					</c:if>

				</tbody>
				<thead>
					<tr>
						<th><spring:message code="label.plantMachinery"/></th>
						<th><spring:message code="label.location"/></th>
						<th><spring:message code="label.purchaseValue"/></th>
						<th><spring:message code="label.currentMarketvalue"/></th>
						<th><spring:message code="label.details"/></th>
						<th><spring:message code="label.registrationNumber"/></th>
						<th><spring:message code="label.existingCharge"/></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty collateralList}">
						<c:forEach items="${collateralList}" var="collateralList">
							<tr>
								<td><c:out value="${collateralList.machinery}"></c:out></td>
								<td><c:out value="${collateralList.machineryLocation}"></c:out></td>
								<td><c:out value="${collateralList.machineryPurchaseValue}"></c:out></td>
								<td><c:out value="${collateralList.machineryMarketValue}"></c:out></td>
							    <td><c:out value="${collateralList.machineryDetails}"></c:out></td>
							    <td><c:out value="${collateralList.registrationNum}"></c:out></td>
							    <td><c:out value="${collateralList.machineryExisCharge}"></c:out></td>
							   	</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
						<h3><spring:message code="label.purchaseOrder"/></h3>
				</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
			<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.supplierName"/></th>
						<th><spring:message code="label.poKey"/></th>
						<th><spring:message code="label.goods"/></th>
						<th><spring:message code="label.quantity"/></th>
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.transactionResult"/></th>
						
					</tr>
				</thead>
					<tbody>
					<c:if test="${! empty purchaseList}">
						<c:forEach items="${purchaseList}" var="master">
							<tr>
								<td><c:out value="${master.id}"></c:out></td>
								<td><c:out value="${master.customerName}"></c:out>
								<td><c:out value="${master.supplierName}"></c:out>
								<td><c:out value="${master.poKey}"></c:out>
								<td><c:out value="${master.goods}"></c:out>
								<td><c:out value="${master.quantity}"></c:out>
								<td><c:out value="${master.status}"></c:out>
								<td><c:out value="${master.transResult}"></c:out>
								
								
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
				</table>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3><spring:message code="label.invoice"/></h3>
				</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
					<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.buyerName"/></th>
						<th><spring:message code="label.invoiceKey"/></th>
						<th><spring:message code="label.goods"/></th>
						<th><spring:message code="label.quantity"/></th>
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.requestMoney"/></th>
					
					</tr>
				</thead>
				
			
					<tbody>
					<c:if test="${! empty invoiceList}">
						<c:forEach items="${invoiceList}" var="master">
							<tr>
								<td><c:out value="${master.id}"></c:out></td>
								<td><c:out value="${master.customerName}"></c:out>
								<td><c:out value="${master.buyerName}"></c:out>
								<td><c:out value="${master.poKey}"></c:out>
								<td><c:out value="${master.goods}"></c:out>
								<td><c:out value="${master.quantity}"></c:out>
								<td><c:out value="${master.status}"></c:out>
								<td><c:out value="${master.requestMoney}"></c:out>
								
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table>

</div>
<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td><a href="snapShotCustHead" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
</div>
