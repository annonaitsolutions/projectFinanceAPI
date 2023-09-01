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
<link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/font-awesome.css" rel="stylesheet">


 <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.css">
 <script src="<%=request.getContextPath()%>/resources/js/jquery-1.9.1.min.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<link href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/resources/js/countries.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/search.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#menuwrapper ul li").mouseover(function(e) {$(this).addClass(" iehover ");});
		$("#menuwrapper ul li").mouseout(function(e) {$(this).removeClass(" iehover ");});
	});
</script>
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Lato:300,400,700">
		<link rel="stylesheet" href="http://weloveiconfonts.com/api/?family=fontawesome">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
<script src="<%=request.getContextPath()%>/resources/js/simplecalendar.js" type="text/javascript"></script>


<link href="<%=request.getContextPath()%>/resources/css/verticalmenu.css" type="text/css" rel="Stylesheet" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/datepicker.css">
 <script src="<%=request.getContextPath()%>/resources/js/bootstrap-datepicker.js"></script>
<!-- Added for Download and Print -->
<script	src="<%=request.getContextPath()%>/resources/js/tableExport.js"></script>
<script	src="<%=request.getContextPath()%>/resources/js/jquery.base64.js"></script>
<script	src="<%=request.getContextPath()%>/resources/js/sprintf.js"></script>
<script	src="<%=request.getContextPath()%>/resources/js/jspdf.js"></script>
<script	src="<%=request.getContextPath()%>/resources/js/base64.js"></script>
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
		}else if (temp_lng == "hn") {
			$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle')
			.html("<i class='flags flag12'></i>Hindi");
         }
		else if (temp_lng == "bu") {
			$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle')
			.html("<i class='flags flag13'></i>Bulgerian");
         }
		else if (temp_lng == "hu") {
			$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle')
			.html("<i class='flags flag14'></i>Hungerian");
         }
		else if (temp_lng == "cz") {
			$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle')
			.html("<i class='flags flag15'></i>Czhec");
         }
		else if (temp_lng == "uk") {
			$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle')
			.html("<i class='flags flag16'></i>Ukrenian");
         }
		else if (temp_lng == "cr") {
			$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle')
			.html("<i class='flags flag17'></i>Croatian");
         }
		else if (temp_lng == "se") {
			$('#lng_slct li a').parents('.dropdown').find('.dropdown-toggle')
			.html("<i class='flags flag18'></i>Serbian");
         }
	});

	function simple() {
		var p, r, n, i;
		if (document.interest.buyingCostSanc.value == "") {
			alert("Please enter Sanction Amount");
			document.interest.buyingCostSanc.alert();
		} else if (document.interest.rateOfInt.value == "") {
			alert("ENTER RATE OF INTEREST");
			document.interest.rateOfInt.blur();

		} else if (document.interest.tenure.value == "") {
			alert("ENTER NUMBER OF Days");
			document.interest.tenure.prompt();
		// } else if (document.interest.rateOfInt.value == "0") {
		} else if (document.interest.interestType.value == "variable") {
			
			p = parseFloat(document.interest.buyingCostSanc.value);
			r = parseFloat(document.interest.calPlrRate.value);
			n = parseFloat(document.interest.tenure.value);
			
			i = ((p * r / 100) / 365) * n;	
			
			
			document.interest.total1.value = parseFloat(i).toFixed(2);			

			document.interest.rateOfInt1.value = parseFloat(i).toFixed(2);

		}

		else {
			
			p = parseFloat(document.interest.buyingCostSanc.value);

			r = parseFloat(document.interest.rateOfInt.value);

			n = parseFloat(document.interest.tenure.value);

			i = ((p * r / 100) / 365) * n;			

			document.interest.total1.value = parseFloat(i).toFixed(2);

			document.interest.rateOfInt1.value = parseFloat(i).toFixed(2);

		}
	}
	function Amount() {
		var p, f, per, i, amnt, a, b, c, d,f,g, flt, pt, process, doc, late, tax,tax1, amount;

			
			p = parseFloat(document.interest.buyingCostSanc.value);

			i = parseFloat(document.interest.rateOfInt1.value);

			f = parseFloat(document.interest.flatCharges.value);

			// per = parseFloat(document.interest.percentAmt.value);

			a = parseFloat(document.interest.procFee.value);
			b = parseFloat(document.interest.docFee.value);
			c = parseFloat(document.interest.lateFee.value);
			d = parseFloat(document.interest.taxAmt.value);
			f = parseFloat(document.interest.taxAmt1.value);
			g = parseFloat(document.interest.taxAmt2.value);

			flt = parseFloat(document.interest.flatCharges.value);
			// pt = parseInt(document.interest.percentage.value);
			// process = parseInt(document.interest.procFee.value);
			// doc = parseInt(document.interest.docFee.value);
			// late = parseInt(document.interest.lateFee.value);
			// tax = parseInt(document.interest.taxPercentage.value);
			// tax1 = parseInt(document.interest.taxPercentage1.value);
			tax2 = parseInt(document.interest.taxPercentage2.value);

			amnt = p + f + i + a + b + c + d + g + flt;

			document.interest.funalAmt.value = amnt.toFixed(2);
		
	}

	function basicpts() {
		var p, i;
		p = parseInt(document.interest.basicPoints.value);
		i = p / 100;
		document.interest.basicAmt.value = i;
	}

	function plr() {
		var p, i, amt;
		p = parseFloat(document.interest.plrRate.value);
		i = parseFloat(document.interest.basicAmt.value);

		amt = p + i;
		document.interest.calPlrRate.value = amt.toFixed(2);

	}
	
	
	function percent() {
		var p, i, amt;
		p = parseInt(document.interest.buyingCostSanc.value);
		i = parseInt(document.interest.percentage.value);

		amt = p * i / 100;
		document.interest.percentAmt.value = amt.toFixed(2);
	}
	function taxamnt() {
		var p, i, amt;
		p = parseInt(document.interest.buyingCostSanc.value);
		i = parseFloat(document.interest.taxPercentage.value);

		amt = p * i / 100;
		document.interest.taxAmt.value = amt.toFixed(2);
	}
	
	function taxamnt1() {
		var p, i, amt;
		p = parseInt(document.interest.buyingCostSanc.value);
		i = parseFloat(document.interest.taxPercentage1.value);

		amt = p * i / 100;
		document.interest.taxAmt1.value = amt.toFixed(2);
	}
	
	function taxamnt2() {
		var p, i, amt;
		p = parseInt(document.interest.buyingCostSanc.value);
		i = parseFloat(document.interest.taxPercentage2.value);

		amt = p * i / 100;
		document.interest.taxAmt2.value = amt.toFixed(2);
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
	
	function calcInvoice() {
		var p, t, r, total;
		p = parseFloat(document.myForm.amount.value);

		t = parseInt(document.myForm.tenure.value);

		r = parseFloat(document.myForm.percentage.value);

		
		d = ((p * r / 100) / 365) * t;
		
		total= p-d;

		document.myForm.funalAmt.value = total.toFixed(2);

	}
	
	function calcAmt() {
		var p, t, r, total;
		p = parseFloat(document.myForm.amount.value);

		t = parseInt(document.myForm.payDate.value);

		r = parseFloat(document.myForm.intRate.value);

		
		d = ((p * r / 100) / 365) * t;
		
		total= p+d;

		document.myForm.totalAmount.value = total.toFixed(2);

	}
	
	function calcAmt1() {
		var p, t, r, total;
		p = parseFloat(document.myForm.amount1.value);

		t = parseInt(document.myForm.payDate1.value);

		r = parseFloat(document.myForm.intRate1.value);

		
		d = ((p * r / 100) / 365) * t;
		
		total= p+d;

		document.myForm.totalAmount1.value = total.toFixed(2);

	}
	
	function calcAmt2() {
		var p, t, r, total;
		p = parseFloat(document.myForm.amount2.value);

		t = parseInt(document.myForm.payDat2.value);

		r = parseFloat(document.myForm.intRate2.value);

		
		d = ((p * r / 100) / 365) * t;
		
		total= p+d;

		document.myForm.totalAmount2.value = total.toFixed(2);

	}
	
	function calcAmt3() {
		var p, t, r, total;
		p = parseFloat(document.myForm.amount3.value);

		t = parseInt(document.myForm.payDate3.value);

		r = parseFloat(document.myForm.intRate3.value);

		
		d = ((p * r / 100) / 365) * t;
		
		total= p+d;

		document.myForm.totalAmount3.value = total.toFixed(2);

	}


	function amt() {
		var p, t, r, total;
		p = parseFloat(document.myForm.wcSancAmount.value);
		if (isNaN(p)) {
			// sancAmtError
			document.getElementById('sancAmtError').style.display = 'block';
			return;
		} else {
			document.getElementById('sancAmtError').style.display = 'none';
		}

		t = parseInt(document.myForm.WcTenure.value);

		if (isNaN(t)) {
			// sancAmtError
			document.getElementById('wcError').style.display = 'block';
			return;
		} else {
			document.getElementById('wcError').style.display = 'none';
		}

		r = parseFloat(document.myForm.wcSancInterest.value);

		if (isNaN(r)) {
			// sancAmtError
			document.getElementById('wcSancInterestError').style.display = 'block';
			return;
		} else {
			document.getElementById('wcSancInterestError').style.display = 'none';
		}

		total = ((p * r / 100) / 365) * t;

		document.myForm.wcTotalInterest.value = total.toFixed(2);

	}
	function totalamt() {
		var p, r, total;
		p = parseFloat(document.myForm.wcSancAmount.value);

		r = parseFloat(document.myForm.wcTotalInterest.value);

		total = (p + r);

		document.myForm.wcTotalAmount.value = total.toFixed(2);

	}
	function amt1() {
		var p, t, r, total;
		p = parseFloat(document.myForm.reqAmt.value);

		t = parseInt(document.myForm.tenure.value);

		r = parseFloat(document.myForm.intRate.value);

		total = ((p * r / 100) / 365) * t;

		document.myForm.totalRate.value = total.toFixed(2);

	}
	
	function totalamt1() {
		var p, r, total;
		p = parseFloat(document.myForm.reqAmt.value);

		r = parseFloat(document.myForm.totalRate.value);

		total = (p + r);

		document.myForm.finalAmt.value = total.toFixed(2);

	}
</script>

</head>

<body>

<nav class="navbar navbar1">
	<div class="container-fluid">
		<div class="navbar-header col-sm-2 col-xs-12 logo_1">
			<a href="user" class="main_logo_link"><img src="<%=request.getContextPath()%>/resources/images/logopng44.png" alt="ANNONA IT SOLUTIONS" class="a_logo img-responsive" title="ANNONA IT SOLUTIONS PVT.LTD."></a>
			<a href="#" style="color: #2E9AFE;">ANNONA IT SOLUTIONS</a>
		</div>
		<div class="img_chain1 col-sm-6 col-xs-12" align="center">
			<img src="<%=request.getContextPath()%>/resources/images/Tagline_final_transparent.png" class="img_chain_trading img-responsive">
		</div>
		<div class="col-sm-4 hidden-xs mascot-new" align="center">
			<img src="<%=request.getContextPath()%>/resources/images/mascot.gif" class="mascot img-responsive">
		</div>
	</div>
</nav>
<nav class="navbar  navbar2">
	<div class="container-fluid">
	    <div class="navbar-header">
<%--	      <a class="navbar-brand" href="bankEmpCommon"><i class="glyphicon glyphicon-home"></i></a>--%>
			  <a class="navbar-brand" href="bankEmp"><i class="glyphicon glyphicon-home"></i></a>
	        &nbsp;&nbsp;<a href="#" style="color: #005C79;">DHANAPURTHI&nbsp;<sup>TM</sup></a>
	    </div>
		<div class="pull-left">

				<div class="prof_img"><a href="editBankProfile?id=${requestCurrentUser.id}"><span><img src="${requestCurrentUser.imageName}" class="profile_img"></span><span class="customer_name"><spring:message code="label.welcome" /> ${requestCurrentUser.displayName}</span></a></div>

		</div>
	    <div id="myNavbar">
			<ul class="nav navbar-nav navbar-right">
			<li class="log_out"><a href="bankHelp"><i class="glyphicon glyphicon-modal-window"></i>&nbsp;<strong><spring:message code="label.menuHelp"/></strong></a></li>
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
										<li><a href="getLocaleLang?lang=hn"
								<c:if test="${model.user.prefferedLanguage == 'hn'}"> selected="selected"</c:if>><i
									class="flags flag12"></i>Hindi</a></li>
									<li><a href="getLocaleLang?lang=bu"
								<c:if test="${model.user.prefferedLanguage == 'bu'}"> selected="selected"</c:if>><i
									class="flags flag13"></i>Bulgarian</a></li>
									<li><a href="getLocaleLang?lang=hu"
								<c:if test="${model.user.prefferedLanguage == 'hu'}"> selected="selected"</c:if>><i
									class="flags flag14"></i>Hungarian</a></li>
									<li><a href="getLocaleLang?lang=cz"
								<c:if test="${model.user.prefferedLanguage == 'cz'}"> selected="selected"</c:if>><i
									class="flags flag15"></i>Czech</a></li>
									<li><a href="getLocaleLang?lang=uk"
								<c:if test="${model.user.prefferedLanguage == 'uk'}"> selected="selected"</c:if>><i
									class="flags flag16"></i>Ukrainian</a></li>
									<li><a href="getLocaleLang?lang=cr"
								<c:if test="${model.user.prefferedLanguage == 'cr'}"> selected="selected"</c:if>><i
									class="flags flag17"></i>Croatian</a></li>
									<li><a href="getLocaleLang?lang=se"
								<c:if test="${model.user.prefferedLanguage == 'se'}"> selected="selected"</c:if>><i
									class="flags flag18"></i>Serbian</a></li>
								</ul>
					</li>
					<li class="log_out"><a href='<c:url value="/j_spring_security_logout"/>'><i class="glyphicon glyphicon-log-out"></i>&nbsp;<strong><spring:message code="label.logout"/></strong></a></li>
				</ul>
	    </div>
  </div>
</nav>


