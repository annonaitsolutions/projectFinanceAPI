<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
  
 
<div class="col-sm-3 col-md-3 vertical_menu_fixed" style="padding-left: 0px;">
<div id="menuwrapper">
<ul class="nav navbar-nav">
				
						
							<li class="dropdown-submenu"><a tabindex="-1" href="#">Add New</a>
								<ul class="dropdown-menu">
								<li><a href="addBuyerCusSubsidiary">Add New Buyer</a></li>
								<li><a href="supplierForCustomerSub">Add New Supplier</a></li>
									<li><a href="fileUploadForm">Add Documents Upload</a></li>
									<li><a href="selectcustomerSubsidiarydocumentApprovalList">List of Uploaded Documents</a></li>
									<li><a href="InventoryPoListForSub">PO Inventory Management</a></li>
									<li><a href="inventoryPoListForSub">List Of PO Inventory Management</a></li>
									<li><a href="InventoryInvoiceListForSub">Invoice Inventory Management</a></li>
									<li><a href="inventoryInvoiceListForSub">List Of Invoice Inventory Management</a></li>
								<li><a href="subsBuyerPoList">List of Buyer PO</a></li></ul>
							</li>
							<li class="dropdown-submenu"> <a tabindex="-1" href="#">Details</a>
								<ul class="dropdown-menu">
									<li><a href="customerSubsidiaryBuyerList">List Of Buyer</a></li>
									<li><a href="customerSubsidiarySupplierList">List Of Supplier</a></li>
									<li><a href="customerSubsidiaryPODcumentList">List Of PO Document</a></li>
								</ul>
							</li>
							<li class="dropdown-submenu"> <a tabindex="-1" href="#">PO Payment</a>
								<ul class="dropdown-menu">
									<li><a href="poPaymentSubsList">PO For Payment</a></li>
								<li><a href="poPaymentSubsFullList">PO Payment Full List</a></li>
								</ul>
							</li>
							<li class="dropdown-submenu"> <a tabindex="-1" href="#">Business Plan</a>
								<ul class="dropdown-menu">
									<li><a href="subsBusinessPlan">Create Business Plan</a></li>
									<li><a href="customerSubsPoList">List Of Purchase Order</a></li>
									<li><a href="customerSubsInvoiceList">List Of Invoice</a></li>
								</ul>
							</li>
							<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">Dispute</a>
						<ul class="dropdown-menu">
							<li><a href="disputeSubsList">Create Dispute</a></li>
							<li><a href="disputeSubsUpdateList">Update Dispute</a></li>
						</ul>
					</li>
								<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">Cancel/Close</a>
						<ul class="dropdown-menu">
							<li><a href="cancelPoSubsList">Cancel PO</a></li>
							<li><a href="closePoSubsList">Close PO</a></li>
							<li><a href="closeinvoiceSubsList">Close Invoice</a></li>
						
						</ul>
					</li>
							<li class="dropdown-submenu"> <a tabindex="-1" href="#">Requests</a>
								<ul class="dropdown-menu">
									<li><a href="SubsFundsRequest">Request For Funds</a></li>
							<li><a href="viewSubsRequestSent">List Of Request sent</a></li>
							<li><a href="viewSubsRequestRecieved">List Of Request Received</a></li>
							<li><a href="subsFundsTransfer">Funds Transfer</a></li>
							<li><a href="fullSubsFundsDistributeStatement">Full Statement</a></li>
								</ul>
							</li>
							<li class="dropdown-submenu"> <a tabindex="-1" href="#">User Settings</a>
								<ul class="dropdown-menu">
						<li><a href="subsShowMail">Query Mail</a></li>
									<li><a href="subsShowCurrency">Rate Of Currency</a></li>
									<li><a href="subsShowcurrencyConversion">Currency Conversion</a></li>
								</ul>
							</li>
							
						</ul>
						
						</div>
						</div>