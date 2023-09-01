<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div class="col-sm-3 col-md-3 vertical_menu_fixed"
	style="padding-left: 0px;">
	<div id="menuwrapper">
		<ul class="nav navbar-nav">
			<li><a href="loginDate">Login Date</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><i class="fa fa-file-text-o"></i>&nbsp;<spring:message
						code="label.creteNew" /> &nbsp;<i class="fa fa-angle-down"></i></a>
				<ul class="dropdown-menu">
					<li><a href="createClientAppMng"><spring:message
								code="label.creteAppMng" /></a></li>
					<li><a href="createCustomerHead"><spring:message
								code="label.custHeadApp" /></a></li>
					<li><a href="createCustomerBranch"><spring:message
								code="label.custBr/SubsApp" /></a></li>
					<li><a href="clientAppMngFullList"><spring:message
								code="label.listOfCustAppMng" /></a></li>
					<li><a href="bankCustomerHeadList"><spring:message
								code="label.listOfCustHead" /></a></li>
					<li><a href="bankCustomerBranchList"><spring:message
								code="label.listOfCustBr/Subs" /></a></li>
					<li><a href="wareHouseMngCAdminFullList"><spring:message
								code="label.addwhmngList" /></a></li>
					<!-- <li><a href="bankCustomerBranchList">List Of Customer
									Subsidiary</a></li> -->
				</ul></li>

			<li><a href="#"><spring:message code="label.menuDetails" />&nbsp;<i
					class="fa fa-caret-right" style="left: 38px;"></i></a>
				<ul>
					<li><a href="adminExistingStockInvoiceList"><spring:message
								code="label.listOfSellingStock" /></a></li>
					<li><a href="adminExistingStockPoList"><spring:message
								code="label.listOfBuyingStock" /></a></li>
					<li><a href="clientAdminSupplierPageList"><spring:message
								code="label.listOfSupplierApp" /></a></li>
					<li><a href="clientAdminBuyerPageList"><spring:message
								code="label.listOfBuyerApp" /></a></li>
				</ul></li>
				
				<li><a href="custBankDetails"><spring:message code="label.bankDe"/></a></li>
				
			<li><a href="#"><spring:message code="label.menuUs" /></a>
				<ul class="dropdown-menu">
					<li><a href="snapShotCustAdmin"><spring:message
								code="label.menuSnp" /></a></li>
					<li><a href="getUsersForSwapRevert"><spring:message
								code="label.menuSwap&Revert" /></a></li>
				</ul></li>
			<li><a href="#"><spring:message code="label.insDetails" /></a>
				<ul class="dropdown-menu">
					<li><a href="poInsFullCAdminList"><spring:message
								code="label.insList" /></a></li>
					<li><a href="addOrModifyReportInvoiceCAdminFullList"><spring:message
								code="label.insInvoiceList" /></a></li>
				</ul></li>
			<li class="dropdown-submenu"><a tabindex="-1" href="#"><spring:message
						code="label.menuCharts" /></a>
				<ul class="dropdown-menu">
					<li><a href="generateWarehouseChart"><spring:message
								code="label.poInventory" /></a></li>
					<li><a href="generateInvoiceInventoryChartBranch"><spring:message
								code="label.invoiceInventory" /></a></li>
					<li><a href="existingStockPOInventoryChart"><spring:message
								code="label.exPoChart" /></a></li>
					<li><a href="existingStockInvoiceInventoryChart"><spring:message
								code="label.exInvoiceChart" /></a></li>
				</ul></li>

		</ul>
	</div>
</div>