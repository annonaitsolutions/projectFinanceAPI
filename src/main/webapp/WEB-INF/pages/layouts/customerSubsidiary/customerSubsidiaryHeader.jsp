<%-- <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<style>
img#popup_window {
    width: 35px;
    height: 35px;
    border-radius: 50%;
}
</style>
<head>
<meta charset="utf-8">
<title>ANNONA</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
<!-- Link styles -->
<link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/font-awesome.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/font-awesome.css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/resources/css/custom.css" rel="stylesheet">
<link rel="shortcut icon" href="img/fav.ico">
<link href="<%=request.getContextPath()%>/resources/css/c.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.3.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/calendar.js"></script>
<link href="<%=request.getContextPath()%>/resources/css/ionicons.css" rel="stylesheet"/>
<script src="<%=request.getContextPath()%>/resources/js/customer.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/countries.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.sortelements.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.bdt.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.bdt.js"></script>
<link href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/jquery.bdt.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/jquery.bdt.css" rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/popup.css"
	type="text/css">
	
	<link href="<%=request.getContextPath()%>/resources/css/jquery.popup.css"
	rel="stylesheet">
<style>
	.accordion .item .heading {
	height: 40px;
    line-height: 50px;
    font-size: 17px;
    cursor: pointer;
    color: #FFFFFF;
    padding-left: 12px;
    background:#676060 url('img/arrow.png') no-repeat;
    background-position: right 20px top -95px;
    border-bottom: 1px solid #FFFFFF;
    box-sizing: border-box;
    font-size: 1.1em;
    font-family: serif;
}
</style>
<style>
	
	th{
	background-color: rgba(65, 183, 278, 2.27);
	}
</style>

<script>
$(function() {
	
	var temp_lng = $('#lng_slctd').val();
	if(temp_lng == "en"){
		$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle').html("<i class='flags flag6'></i>English");
	} else if(temp_lng == "de"){
		$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle').html("<i class='flags flag3'></i>German");
	} else if(temp_lng == "fr"){
		$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle').html("<i class='flags flag4'></i>French");
	} else if(temp_lng == "it"){
		$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle').html("<i class='flags flag5'></i>Italian");
	} else if(temp_lng == "ar"){
		$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle').html("<i class='flags flag7'></i>Arabic");
	} else if(temp_lng == "pr"){
		$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle').html("<i class='flags flag8'></i>Portugese");
	} else if(temp_lng == "sp"){
		$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle').html("<i class='flags flag2'></i>Spanish");
	} else if(temp_lng == "ch"){
		$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle').html("<i class='flags flag9'></i>Chinese");
	} else if(temp_lng == "jp"){
		$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle').html("<i class='flags flag10'></i>Japnese");
	}else if(temp_lng == "rs"){
		$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle').html("<i class='flags flag11'></i>Russian");
	}
});
	
</script>


</head>

<body>
	<div class="top-bar">
		<div class="row">
			<div class="col-sm-7">
				<a class="navbar-brand" href="#"><img src="<%=request.getContextPath()%>/resources/images/Logo.png" class="logo" alt="logo"></a>
			</div>
			<div class="col-sm-2 welcome">
					<p><strong><a href="editCustomerSubsidiaryProfile?id=${model.user.id}"><img
						src="<%=request.getContextPath()%>/resources/images/Leena.jpg"
						id="popup_window" data-popup-target="#example-popup"></a> Welcome ${model.user.displayName}</strong></p>
			</div>
			<div class="col-sm-2">
				<div class="setting">
				 <a href='<c:url value="/j_spring_security_logout"/>' class="button-default setlog setlog"> <i class="fa fa-unlock">&nbsp;Logout</i></a> 
				</div>
			</div>
		</div>
	</div>




<div class="navbar">
	<div class="navbar-inner PL0 PT20">
			<div class="pull-left">
				<a href="#"><i class="fa fa-home fa-3x home1"></i></a>
			</div>
		<div class="nav-collapse collapse navbar-responsive-collapse">
			<ul class="nav navbar-nav">
				
						
							<li class="dropdown-submenu"><a tabindex="-1" href="#">Add New</a>
								<ul class="dropdown-menu">
								<li><a href="addBuyerCusSubsidiary">Add New Buyer</a></li>
								<li><a href="supplierForCustomerSub">Add New Supplier</a></li>
									<li><a href="fileUploadForm">Add Documents Upload</a></li>
									<li><a href="selectcustomerSubsidiarydocumentApprovalList">List of Uploaded Documents</a></li>
									<li><a href="InventoryPoListForSub">Add Invetory Management</a></li>
									<li><a href="inventoryPoListForSub">List Of Invetory Management</a></li>
								<li><a href="subsBuyerPoList">List of Buyer PO</a></li>
								</ul>
							</li>
							<li class="dropdown-submenu"> <a tabindex="-1" href="#">Details</a>
								<ul class="dropdown-menu">
									<li><a href="customerSubsidiaryBuyerList">List Of Buyer</a></li>
									<li><a href="customerSubsidiarySupplierList">List Of Supplier</a></li>
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
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">Requests</a>
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
					<ul class="nav pull-right" id="lng_slct" style="padding-top: 12px;">
                <li class="dropdown pull-right">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="label.language"/></a>
				  <input type="hidden" id="lng_slctd" value="${model.user.prefferedLanguage}" />
                  <ul class="dropdown-menu">
                    <li><a href="getLocaleLang?lang=en" <c:if test="${model.user.prefferedLanguage == 'en'}"> selected="selected"</c:if>><i class="flags flag6"></i>English</a></li>
                   <!-- <li><a href="getLocaleLang?lang=en" <c:if test="${model.user.prefferedLanguage == 'en'}"> selected="selected"</c:if>><i class="flags flag1"></i>English(UK)</a></li>-->
                    <li><a href="getLocaleLang?lang=sp" <c:if test="${model.user.prefferedLanguage == 'sp'}"> selected="selected"</c:if>><i class="flags flag2"></i>Spanish</a></li>
                    <li><a href="getLocaleLang?lang=de" <c:if test="${model.user.prefferedLanguage == 'de'}"> selected="selected"</c:if>><i class="flags flag3"></i>German</a></li>
                   <li><a href="getLocaleLang?lang=fr" <c:if test="${model.user.prefferedLanguage == 'fr'}"> selected="selected"</c:if>><i class="flags flag4"></i>French</a></li>
                   <li><a href="getLocaleLang?lang=it" <c:if test="${model.user.prefferedLanguage == 'it'}"> selected="selected"</c:if>><i class="flags flag5"></i>Italian</a></li> 
                  <li><a href="getLocaleLang?lang=ar" <c:if test="${model.user.prefferedLanguage == 'ar'}"> selected="selected"</c:if>><i class="flags flag7"></i>Arabic</a></li> 
                  <li><a href="getLocaleLang?lang=pr" <c:if test="${model.user.prefferedLanguage == 'pr'}"> selected="selected"</c:if>><i class="flags flag8"></i>Portugese</a></li> 
                   <li><a href="getLocaleLang?lang=ch" <c:if test="${model.user.prefferedLanguage == 'ch'}"> selected="selected"</c:if>><i class="flags flag9"></i>Chinese</a></li>
                   <li><a href="getLocaleLang?lang=jp" <c:if test="${model.user.prefferedLanguage == 'jp'}"> selected="selected"</c:if>><i class="flags flag10"></i>Japnese</a></li>
                 <li><a href="getLocaleLang?lang=rs" <c:if test="${model.user.prefferedLanguage == 'rs'}"> selected="selected"</c:if>><i class="flags flag11"></i>Russian</a></li>
                  </ul>
                </li>
            </ul>
            	<ul class="nav navbar-nav pull-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">Themes</a>
							<ul class="dropdown-menu">
								<li><a href="themeChange?theme=themeBlue">Blue</a></li>
								<li><a href="themeChange?theme=themeOrange">Orange</a></li>
								<li><a href="themeChange?theme=themeRed">Red</a></li>
								<li><a href="themeChange?theme=themeGreen">Green</a></li>
								
								
							</ul>
						</li>
				</ul>
		</div>
		<!-- /.nav-collapse --> 
	</div>
<!-- /navbar-inner --> 
</div>
<!-- /navbar --> 
 </body>
 </html>   
   
    
    --%>
    
    
    
    
    
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>ANNONA</title>
<meta name="viewport" content="widtr=device-widtr, initial-scale=1.0">
<link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
	
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/bootstrap-glyphicons.css" rel="stylesheet">
<!-- Le styles -->
<link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/font-awesome.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.3.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<link href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-2.0.3.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css" rel="stylesheet">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#menuwrapper ul li").mouseover(function(e) {$(this).addClass(" iehover ");});
		$("#menuwrapper ul li").mouseout(function(e) {$(this).removeClass(" iehover ");});
	});
</script>

<link href="<%=request.getContextPath()%>/resources/css/verticalmenu.css" type="text/css" rel="Stylesheet" />
<script>
	$(function() {

		var temp_lng = $('#lng_slctd').val();
		if (temp_lng == "en") {
			$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle')
					.html("<i class='flags flag6'></i>English");
		} else if (temp_lng == "de") {
			$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle')
					.html("<i class='flags flag3'></i>German");
		} else if (temp_lng == "fr") {
			$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle')
					.html("<i class='flags flag4'></i>French");
		} else if (temp_lng == "it") {
			$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle')
					.html("<i class='flags flag5'></i>Italian");
		} else if (temp_lng == "ar") {
			$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle')
					.html("<i class='flags flag7'></i>Arabic");
		} else if (temp_lng == "pr") {
			$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle')
					.html("<i class='flags flag8'></i>Portugese");
		} else if (temp_lng == "sp") {
			$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle')
					.html("<i class='flags flag2'></i>Spanish");
		} else if (temp_lng == "ch") {
			$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle')
					.html("<i class='flags flag9'></i>Chinese");
		} else if (temp_lng == "jp") {
			$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle')
					.html("<i class='flags flag10'></i>Japnese");
		} else if (temp_lng == "rs") {
			$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle')
					.html("<i class='flags flag11'></i>Russian");
		}
	});

	function simple() {
		var p, r, n, i;
		if (document.interest.principle.value == " ") {
			alert("PLEASE ENTER PRINCIPLE AMOUNT");
			document.interest.principle.alert();
		} else if (document.interest.rate.value == "") {
			alert("ENTER RATE OF INTEREST");
			document.interest.rate.blur();

		} else if (document.interest.year.value == "") {
			alert("ENTER NUMBER OF YEAR");
			document.interest.year.prompt();
		} else if (document.interest.rate.value == "0") {
			p = parseInt(document.interest.principle.value);
			r = parseFloat(document.interest.plrrateamnt.value);
			n = parseInt(document.interest.year.value);
			i = ((p * r / 100) / 365) * n;

			document.interest.answer.value = "&#8377 :" + i;

			document.interest.another.value = i;
			document.interest.another1.value = i;

		}

		else {
			p = parseInt(document.interest.principle.value);

			r = parseInt(document.interest.rate.value);

			n = parseInt(document.interest.year.value);

			i = ((p * r / 100) / 365) * n;

			document.interest.answer.value = "&#8377 :" + i;

			document.interest.another.value = i;
			document.interest.another1.value = i;
		}
	}
	function amount() {
		var p, f, per, i, amnt, a, b, c, d, flt, pt, process, doc, late, tax, amount;

		if (document.interest.principle.value == " ") {
			alert("PLEASE ENTER PRINCIPLE AMOUNT");
			document.interest.principle.alert();
		} else if (document.interest.another.value == "") {
			alert("Interest null");
			document.interest.another.prompt();
		}

		else {
			p = parseInt(document.interest.principle.value);

			i = parseFloat(document.interest.another.value);

			f = parseInt(document.interest.flat.value);

			per = parseInt(document.interest.percentamt.value);

			a = parseInt(document.interest.pf.value);
			b = parseInt(document.interest.bf.value);
			c = parseInt(document.interest.lf.value);
			d = parseFloat(document.interest.taxamt.value);

			flt = parseInt(document.interest.flat.value);
			pt = parseInt(document.interest.percentage.value);
			process = parseInt(document.interest.pf.value);
			doc = parseInt(document.interest.bf.value);
			late = parseInt(document.interest.lf.value);
			tax = parseInt(document.interest.taxpercentage.value);

			amnt = p + f + i + per + a + b + c + d;

			document.interest.amnt.value = amnt;
			document.interest.flat1.value = flt;
			document.interest.percentage1.value = pt;
			document.interest.pf1.value = process;
			document.interest.bf1.value = doc;
			document.interest.lf1.value = late;
			document.interest.taxpercentage1.value = tax;
			document.interest.amnt1.value = amnt;
		}
	}

	function basicpts() {
		var p, i;
		p = parseInt(document.interest.basicpoints.value);
		i = p / 100;
		document.interest.basicamt.value = i;
	}

	function plr() {
		var p, i, amt;
		p = parseFloat(document.interest.plrrate.value);
		i = parseFloat(document.interest.basicamt.value);

		amt = p + i;
		document.interest.plrrateamt.value = amt;

		document.interest.plrrateamnt.value = amt;

	}
	function percent() {
		var p, i, amt;
		p = parseInt(document.interest.principle.value);
		i = parseInt(document.interest.percentage.value);

		amt = p * i / 100;
		document.interest.percentamt.value = amt;
	}
	function taxamnt() {
		var p, i, amt;
		p = parseInt(document.interest.principle.value);
		i = parseFloat(document.interest.taxpercentage.value);

		amt = p * i / 100;
		document.interest.taxamt.value = amt;
	}

	function simple2() {
		var p, q, t;

		p = parseFloat(document.wallet.buyerAmount.value);
		q = parseFloat(document.wallet.wCost.value);
		t = p + q;

		document.wallet.answer.value = t;

		//document.interest.another.value=i;

	}

	function simple1() {
		var r, i, a, b, c, d, e, f, g, h, j, k, l, m, n;

		r = parseFloat(document.wallet.buyerInterest.value);

		n = parseFloat(document.wallet.sellerInterest.value);

		a = parseFloat(document.wallet.sellerFlat.value);

		b = parseFloat(document.wallet.sellerPercentageAmount.value);

		c = parseFloat(document.wallet.buyerFlat.value);

		d = parseFloat(document.wallet.buyerPercentageAmount.value);

		e = parseFloat(document.wallet.sellerPFees.value);
		f = parseFloat(document.wallet.sellerBFees.value);
		g = parseFloat(document.wallet.sellerLFees.value);

		h = parseFloat(document.wallet.buyerPFees.value);
		j = parseFloat(document.wallet.buyerBFees.value);
		k = parseFloat(document.wallet.buyerLFees.value);
		l = parseFloat(document.wallet.buyerTaxAmount.value);
		m = parseFloat(document.wallet.sellerTaxAmount.value);
		n = parseFloat(document.wallet.WcInterestAmount.value);

		i = r + n + a + b + c + d + e + f + g + h + j + k + l + m + n;

		document.wallet.profit.value = i;

	}

	function simpleBR() {
		var p, r, n, i, a, b, c, d, e, f, g;

		if (document.wallet.sellerAmount.value == ""
				|| document.wallet.sellerAmount.value == null) {
			p = 0;
		} else {
			p = parseFloat(document.wallet.sellerAmount.value);
		}
		if (document.wallet.buyerTotalAmount.value == ""
				|| document.wallet.buyerTotalAmount.value == null) {
			r = 0;
		} else {
			r = parseFloat(document.wallet.buyerTotalAmount.value);
		}
		if (document.wallet.sellerInterest.value == " "
				|| document.wallet.sellerInterest.value == null) {
			n = 0;
		} else {
			n = parseFloat(document.wallet.sellerInterest.value);
		}
		if (document.wallet.sellerFlat.value == " "
				|| document.wallet.sellerFlat.value == null) {
			a = 0;
		} else {
			a = parseFloat(document.wallet.sellerFlat.value);
		}
		if (document.wallet.sellerPercentageAmount.value == ""
				|| document.wallet.sellerPercentageAmount.value == null) {
			b = 0;
		} else {
			b = parseFloat(document.wallet.sellerPercentageAmount.value);
		}
		if (document.wallet.sellerPFees.value == ""
				|| document.wallet.sellerPFees.value == null) {
			c = 0;
		} else {
			c = parseFloat(document.wallet.sellerPFees.value);
		}
		if (document.wallet.sellerBFees.value == ""
				|| document.wallet.sellerBFees.value == null) {
			d = 0;
		} else {
			d = parseFloat(document.wallet.sellerBFees.value);
		}
		if (document.wallet.sellerLFees.value == ""
				|| document.wallet.sellerLFees.value == null) {
			e = 0;
		} else {
			e = parseFloat(document.wallet.sellerLFees.value);
		}
		if (document.wallet.sellerTaxAmount.value == ""
				|| document.wallet.sellerTaxAmount.value == null) {
			f = 0;
		} else {
			f = parseFloat(document.wallet.sellerTaxAmount.value);
		}
		if (document.wallet.wCost.value == ""
				|| document.wallet.wCost.value == null) {
			g = 0;
		} else {
			g = parseFloat(document.wallet.wCost.value);
		}
		i = p - r - n - a - b - c - d - e - f - g;

		document.wallet.answer.value = i;

		//document.interest.another.value=i;

	}

	function simpleBR1() {
		var p, r, n, i, a, b, c, d, e, f, g, h, j, k, l, m, n;

		if (document.wallet.buyerInterest.value == ""
				|| document.wallet.buyerInterest.value == null) {
			r = 0;
		} else {
			r = parseFloat(document.wallet.buyerInterest.value);
		}
		if (document.wallet.sellerInterest.value == ""
				|| document.wallet.sellerInterest.value == null) {
			n = 0;
		} else {
			n = parseFloat(document.wallet.sellerInterest.value);
		}
		if (document.wallet.sellerFlat.value == ""
				|| document.wallet.sellerFlat.value == null) {
			a = 0;
		} else {
			a = parseFloat(document.wallet.sellerFlat.value);
		}
		if (document.wallet.sellerPercentageAmount.value == ""
				|| document.wallet.sellerPercentageAmount.value == null) {
			b = 0;
		} else {
			b = parseFloat(document.wallet.sellerPercentageAmount.value);
		}
		if (document.wallet.buyerFlat.value == ""
				|| document.wallet.buyerFlat.value == null) {
			c = 0;
		} else {
			c = parseFloat(document.wallet.buyerFlat.value);
		}
		if (document.wallet.buyerPercentageAmount.value == ""
				|| document.wallet.buyerPercentageAmount.value == null) {
			d = 0;
		} else {
			d = parseFloat(document.wallet.buyerPercentageAmount.value);
		}
		if (document.wallet.sellerPFees.value == ""
				|| document.wallet.sellerPFees.value == null) {
			e = 0;
		} else {
			e = parseFloat(document.wallet.sellerPFees.value);
		}
		if (document.wallet.sellerBFees.value == ""
				|| document.wallet.sellerBFees.value == null) {
			f = 0;
		} else {
			f = parseFloat(document.wallet.sellerBFees.value);
		}
		if (document.wallet.sellerLFees.value == ""
				|| document.wallet.sellerLFees.value == null) {
			g = 0;
		} else {
			g = parseFloat(document.wallet.sellerLFees.value);
		}
		if (document.wallet.buyerPFees.value == ""
				|| document.wallet.buyerPFees.value == null) {
			h = 0;
		} else {
			h = parseFloat(document.wallet.buyerPFees.value);
		}
		if (document.wallet.buyerBFees.value == ""
				|| document.wallet.buyerBFees.value == null) {
			j = 0;
		} else {
			j = parseFloat(document.wallet.buyerBFees.value);
		}
		if (document.wallet.buyerLFees.value == ""
				|| document.wallet.buyerLFees.value == null) {
			k = 0;
		} else {
			k = parseFloat(document.wallet.buyerLFees.value);
		}
		if (document.wallet.buyerTaxAmount.value == ""
				|| document.wallet.buyerTaxAmount.value == null) {
			l = 0;
		} else {
			l = parseFloat(document.wallet.buyerTaxAmount.value);
		}
		if (document.wallet.sellerTaxAmount.value == ""
				|| document.wallet.sellerTaxAmount.value == null) {
			m = 0;
		} else {
			m = parseFloat(document.wallet.sellerTaxAmount.value);
		}
		if (document.wallet.WcInterestAmount.value == ""
				|| document.wallet.WcInterestAmount.value == null) {
			n = 0;
		} else {
			n = parseFloat(document.wallet.WcInterestAmount.value);
		}
		i = r + n + a + b + c + d + e + f + g + h + j + k + l + m + n;

		document.wallet.profit.value = i;

		//document.interest.another.value=i;

	}

	function amt() {
		var p, t, r, total;
		p = parseFloat(document.myForm.wcSancAmount.value);

		t = parseInt(document.myForm.WcTenure.value);

		r = parseFloat(document.myForm.wcSancInterest.value);

		total = ((p * r / 100) / 365) * t;

		document.myForm.wcTotalInterest.value = total;

	}
	function totalamt() {
		var p, r, total;
		p = parseFloat(document.myForm.wcSancAmount.value);

		r = parseFloat(document.myForm.wcTotalInterest.value);

		total = (p + r);

		document.myForm.wcTotalAmount.value = total;

	}
</script>

</head>

<%-- <body>
	<div class="top-bar">
		<div class="row">
			<div class="col-sm-5">
				<a class="navbar-brand" href="bankEmp"><img
					src="<%=request.getContextPath()%>/resources/images/Logo.png"
					class="logo" alt="logo"></a>
			</div>
			<div class="col-sm-4 pull-left welcome">
				<p>
					<strong><a href="editBankProfile?id=${model.user.id}"><img
							src="<%=request.getContextPath()%>/resources/images/Leena.jpg"
							id="popup_window" data-popup-target="#example-popup"></a>
						Welcome ${model.user.displayName}</strong>
				</p>
			</div>
			<div class="col-sm-2">
				<div class="setting">
					<a href='<c:url value="/j_spring_security_logout"/>'
						class="button-default setlog setlog"><i class="fa fa-unlock">&nbsp;Logout</i></a>
				</div>
			</div>
		</div>
	</div>



	<div class="navbar">
		<div class="navbar-inner PL0 PT20" style="padding-left: 5px;">
			<div class="pull-left">
				<a href="bankEmp"><i class="fa fa-home fa-3x home1"></i></a>
			</div>
			<div class="nav-collapse collapse navbar-responsive-collapse">
				<ul class="nav navbar-nav" style="float: none;">

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Create Customer&nbsp;<i
							class="fa fa-angle-down"></i></a>
						<ul class="dropdown-menu">
							<li><a href="createCustomerHead">Customer Head</a></li>
							<li><a href="bankCustomerHeadList">List Of Customer Head</a></li>
							<li><a href="bankCustomerBranchList">List Of Customer
									Branch</a></li>
							<li><a href="bankCustomerBranchList">List Of Customer
									Subsidiary</a></li>
							<li><a href="createCustomerBranch">Customer
									Branch/Subsidiary</a></li>
						</ul></li>
						<ul class="nav navbar-nav" style="float: none;">

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">MasterPlan&nbsp;<i
							class="fa fa-angle-down"></i></a>
						<ul class="dropdown-menu">
							<li><a href="bankMasterPlanPendingDetails">Master Plan For Approval</a></li>
							<li><a href="bankMasterPlanDetails">Credit/WorkingCapital Assignment</a></li>
						    <li><a href="masterPlanFullList">List Of all Master Plans</a></li>
						</ul></li>
				
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Notification&nbsp;<i
							class="fa fa-angle-down"></i></a>
						<ul class="dropdown-menu">
							<li><a href="bMasterPlanList">Master Plan</a></li>
							<li><a href="approvalStatusDetails">Customer
									Notification</a></li>
									
							<li><a href="bankCustomerHeadNotification">Customer Head</a></li>
							<li><a href="bankCustomerBranchNotification">Customer Branch</a></li>
							<li><a href="bankCustomerSubsidiaryNotification">Customer Subsidiary</a></li>
							<li><a href="poApprovalList">Purchase Order</a></li>
							<li><a href="invoiceApprovalList">Invoice</a></li>
							<li><a href="lbApprovalList">Limit Burst</a></li>
							<li><a href="disputeApprovalList">Dispute</a></li>
							<li><a href="disputeStatusApprovalList">Dispute Status</a></li>
						</ul></li>


                    <li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="label.details"/>&nbsp;<i class="fa fa-angle-down"></i></a>
						<ul class="dropdown-menu">
							<li><a href="bankCustomerHeadList"><spring:message code="label.listCustomerHead"/></a></li>
							<li><a href="bankCustomerBranchList"><spring:message code="label.listCustomerBranch"/></a></li>
							<li><a href="bankCustomerSubsidiaryList"><spring:message code="label.listCustomerSubsidiary"/></a></li>
							<li><a href="buyerPageList"><spring:message code="label.listBuyer"/></a></li>
							<li><a href="buyerDocumentList"><spring:message code="label.buyerDocument"/></a></li>
							<li><a href="supplierPageList"><spring:message code="label.listSupplier"/></a></li>
						<li><a href="banlFullPoList">List Of Purchase Order</a></li>
						<li><a href="BankFullInvoiceList">List Of Invoice</a></li>
						<li><a href="inventoryBankList">List Of Inventory</a></li>
						</ul>
					</li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Transaction&nbsp;<i
							class="fa fa-angle-down"></i></a>
						<ul class="dropdown-menu">
							<li><a href="newTransaction">Start New Transaction</a></li>
							<li><a href="checkBalance">Balance And Records</a></li>
							<li><a href="generalLedger">General Ledger</a></li>
							<li><a href="status">Status Table</a></li>
							<!-- <li><a href="addRow">Add Row</a></li> -->
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Buying Event Templates<i
							class="icon-angle-down"></i></a>
						<ul class="dropdown-menu">

							<li class="dropdown-submenu"><a tabindex="-1" href="#">Same
									Bank</a>
								<ul class="dropdown-menu">
									<li><a href="newEvent">Create Event</a></li>
									<li><a href="viewAllEvents">Update Event Details</a></li>
									<li><a href="viewAllEvents1">Send For Approve</a></li>
									<li><a href="viewAllEvents2">Send To Customer</a></li>
									<li><a href="viewAllEvents3">Check Status</a></li>
									<li><a href="graphBuyerSamebankCost">Cost Exposure
											Graph</a></li>
									<li><a href="graphBuyerSamebankDate">Time Exposure
											Graph</a></li>
								</ul></li>
							<li class="dropdown-submenu"><a tabindex="-1" href="#">Different
									Bank</a>
								<ul class="dropdown-menu">
									<li><a href="buyerDiffNewEvent">Create Event</a></li>
									<li><a href="buyerDiffViewAllEvents">Update Event
											Details</a></li>
									<li><a href="buyerDiffViewAllEvents1">Send For Approve</a></li>
									<li><a href="buyerDiffViewAllEvents2">Send To Customer</a></li>
									<li><a href="buyerDiffViewAllEvents3">Check Status</a></li>
									<li><a href="graphBuyerDiffbankCost">Cost Exposure
											Graph</a></li>
									<li><a href="buyerDiffbankDategraph">Time Exposure
											Graph</a></li>
								</ul>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Selling Event Templates<i
							class="icon-angle-down"></i></a>
						<ul class="dropdown-menu">

							<li class="dropdown-submenu"><a tabindex="-1" href="#">Same
									Bank</a>
								<ul class="dropdown-menu">
									<li><a href="sellerNewEvent">Create Event</a></li>
									<li><a href="sellerSameViewAllEvents">Update Event
											Details</a></li>
									<li><a href="sellerSameViewAllEvents1">Send For
											Approve</a></li>
									<li><a href="sellerSameViewAllEvents2">Send To
											Customer</a></li>
									<li><a href="sellerSameViewAllEvents3">Check Status</a></li>
									<li><a href="graphSellerSamebankCost">Cost Exposure
											Graph</a></li>
									<li><a href="graphsellersamebankDate">Time Exposure
											Graph</a></li>
								</ul></li>
							<li class="dropdown-submenu"><a tabindex="-1" href="#">Different
									Bank</a>
								<ul class="dropdown-menu">
									<li><a href="sellerDiffNewEvent">Create Event</a></li>
									<li><a href="sellerDiffViewAllEvents">Update Event
											Details</a></li>
									<li><a href="sellerDiffViewAllEvents1">Send For
											Approve</a></li>
									<li><a href="sellerDiffViewAllEvents2">Send To
											Customer</a></li>
									<li><a href="sellerDiffViewAllEvents3">Check Status</a></li>
									<li><a href="graphSellerDiffbankCost">Cost Exposure
											Graph</a></li>
									<li><a href="sellerDiffbankDategraph">Time Exposure
											Graph</a></li>
								</ul>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Payments&nbsp;<i
							class="fa fa-angle-down"></i></a>
						<ul class="dropdown-menu">
							<li><a href="brApprovalStatusDetails">Buyer Costing
									Approval</a></li>
							<li><a href="srApprovalStatusDetails">Seller Costing
									Approval</a></li>
						</ul></li>
					<li><a href="comparisionCustomer">Comparison</a></li>
					<li><a href="loadCountry">Reports</a></li>

				</ul>
				<ul class="nav" id="lng_slct">
					<li class="dropdown pull-right"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"><spring:message
								code="label.language" /></a> <input type="hidden" id="lng_slctd"
						value="${model.user.prefferedLanguage}" />
						<ul class="dropdown-menu">
							<li><a href="getLocaleLang?lang=en"
								<c:if test="${model.user.prefferedLanguage == 'en'}"> selected="selected"</c:if>><i
									class="flags flag6"></i>English</a></li>
							<!-- <li><a href="getLocaleLang?lang=en" <c:if test="${model.user.prefferedLanguage == 'en'}"> selected="selected"</c:if>><i class="flags flag1"></i>English(UK)</a></li>-->
							<li><a href="getLocaleLang?lang=sp"
								<c:if test="${model.user.prefferedLanguage == 'sp'}"> selected="selected"</c:if>><i
									class="flags flag2"></i>Spanish</a></li>
							<li><a href="getLocaleLang?lang=de"
								<c:if test="${model.user.prefferedLanguage == 'de'}"> selected="selected"</c:if>><i
									class="flags flag3"></i>German</a></li>
							<li><a href="getLocaleLang?lang=fr"
								<c:if test="${model.user.prefferedLanguage == 'fr'}"> selected="selected"</c:if>><i
									class="flags flag4"></i>French</a></li>
							<li><a href="getLocaleLang?lang=it"
								<c:if test="${model.user.prefferedLanguage == 'it'}"> selected="selected"</c:if>><i
									class="flags flag5"></i>Italian</a></li>
							<li><a href="getLocaleLang?lang=ar"
								<c:if test="${model.user.prefferedLanguage == 'ar'}"> selected="selected"</c:if>><i
									class="flags flag7"></i>Arabic</a></li>
							<li><a href="getLocaleLang?lang=pr"
								<c:if test="${model.user.prefferedLanguage == 'pr'}"> selected="selected"</c:if>><i
									class="flags flag8"></i>Portugese</a></li>
							<li><a href="getLocaleLang?lang=ch"
								<c:if test="${model.user.prefferedLanguage == 'ch'}"> selected="selected"</c:if>><i
									class="flags flag9"></i>Chinese</a></li>
							<li><a href="getLocaleLang?lang=jp"
								<c:if test="${model.user.prefferedLanguage == 'jp'}"> selected="selected"</c:if>><i
									class="flags flag10"></i>Japnese</a></li>
							<li><a href="getLocaleLang?lang=rs"
								<c:if test="${model.user.prefferedLanguage == 'rs'}"> selected="selected"</c:if>><i
									class="flags flag11"></i>Russian</a></li>
						</ul></li>
				</ul>
				<ul class="nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Themes</a>
						<ul class="dropdown-menu">
							<li><a href="themeChange?theme=themeBlue">Blue</a></li>
							<li><a href="themeChange?theme=themeOrange">Orange</a></li>
							<li><a href="themeChange?theme=themeRed">Red</a></li>
							<li><a href="themeChange?theme=themeGreen">Green</a></li>
						</ul></li>

				</ul>

			</div>
			<!-- /.nav-collapse -->
		</div>
		<!-- /navbar-inner -->
	</div>
 --%>	<!-- /navbar -->
<body>
<script src="<%=request.getContextPath()%>/resources/js/countries.js"></script>	
<nav class="navbar navbar1">
	<div class="container-fluid">
		<div class="navbar-header logo_1 pull-left">
			<a href="userSubsidiary" class="main_logo_link"><img src="<%=request.getContextPath()%>/resources/images/logopng44.png" alt="ANNONA IT SOLUTIONS" class="a_logo" title="ANNONA IT SOLUTIONS PVT.LTD."></a>
			<p class="annona_title">ANNONA IT SOLUTIONS PVT.LTD.</p>
		</div>
		<div class="img_chain1" align="center">
			<img src="<%=request.getContextPath()%>/resources/images/Tagline_final_transparent.png" class="img_chain_trading img-responsive">
		</div>
	</div>
</nav>
<nav class="navbar  navbar2">
	<div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="userSubsidiary"><i class="glyphicon glyphicon-home"></i></a>
	    </div>
		<div class="pull-left">
				<div class="prof_img"><a href="editCustomerSubsidiaryProfile?id=${model.user.id}"><span><img src="<%=request.getContextPath()%>/resources/images/Leena.jpg" class="profile_img"></span><span class="customer_name">Welcome ${model.user.displayName}</span></a></div>
		</div>
	    <div id="myNavbar">
			<ul class="nav navbar-nav navbar-right">
					<%-- <!--<li class="welcome_name"><a href="editBankProfile?id=${model.user.id"><span><img src="<%=request.getContextPath()%>/resources/images/tom.jpg" class="profile_img"></span> Welcome ${model.user.displayName}</a></li>--> --%>
					
					<li class="dropdown langauges">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-globe"></i>&nbsp;<spring:message
								code="label.language" />&nbsp;<i class="glyphicon glyphicon-chevron-down"></i></a>
								<ul class="dropdown-menu">
									<li><a href="getLocaleLang?lang=en"
								<c:if test="${model.user.prefferedLanguage == 'en'}"> selected="selected"</c:if>><i
									class="flags flag6"></i>English</a></li>
							<!-- <li><a href="getLocaleLang?lang=en" <c:if test="${model.user.prefferedLanguage == 'en'}"> selected="selected"</c:if>><i class="flags flag1"></i>English(UK)</a></li>-->
							<li><a href="getLocaleLang?lang=sp"
								<c:if test="${model.user.prefferedLanguage == 'sp'}"> selected="selected"</c:if>><i
									class="flags flag2"></i>Spanish</a></li>
							<li><a href="getLocaleLang?lang=de"
								<c:if test="${model.user.prefferedLanguage == 'de'}"> selected="selected"</c:if>><i
									class="flags flag3"></i>German</a></li>
							<li><a href="getLocaleLang?lang=fr"
								<c:if test="${model.user.prefferedLanguage == 'fr'}"> selected="selected"</c:if>><i
									class="flags flag4"></i>French</a></li>
							<li><a href="getLocaleLang?lang=it"
								<c:if test="${model.user.prefferedLanguage == 'it'}"> selected="selected"</c:if>><i
									class="flags flag5"></i>Italian</a></li>
							<li><a href="getLocaleLang?lang=ar"
								<c:if test="${model.user.prefferedLanguage == 'ar'}"> selected="selected"</c:if>><i
									class="flags flag7"></i>Arabic</a></li>
							<li><a href="getLocaleLang?lang=pr"
								<c:if test="${model.user.prefferedLanguage == 'pr'}"> selected="selected"</c:if>><i
									class="flags flag8"></i>Portuguese</a></li>
							<li><a href="getLocaleLang?lang=ch"
								<c:if test="${model.user.prefferedLanguage == 'ch'}"> selected="selected"</c:if>><i
									class="flags flag9"></i>Chinese</a></li>
							<li><a href="getLocaleLang?lang=jp"
								<c:if test="${model.user.prefferedLanguage == 'jp'}"> selected="selected"</c:if>><i
									class="flags flag10"></i>Japanese</a></li>
							<li><a href="getLocaleLang?lang=rs"
								<c:if test="${model.user.prefferedLanguage == 'rs'}"> selected="selected"</c:if>><i
									class="flags flag11"></i>Russian</a></li>
								</ul>
					</li>
					<li class="log_out"><a href='<c:url value="/j_spring_security_logout"/>'><i class="glyphicon glyphicon-log-out"></i>&nbsp;<strong>Log-Out</strong></a></li>
				</ul>
	    </div>
  </div>
</nav>
    