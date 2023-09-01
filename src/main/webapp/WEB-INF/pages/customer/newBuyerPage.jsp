<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script>
	/* function fun(){

        var co = document.getElementById('companyName');
    } */
	$(function() {
		$("#datepicker").datepicker({
			format : 'dd/mm/yyyy'
		});
	});
	$(document).ready(function() {
		var companyName = document.getElementById('companyName');
		if(companyName!=''){
			document.getElementById("companyName").readOnly = true;
		}
		$(':input','#newBuyerForm')
				.not(':button, :submit, :reset, :hidden')
				.val('')
	});
	function val() {

		var buyerName = document.getElementById('buyerName');
		var companyName = document.getElementById('companyName');
		var bank = document.getElementById('bank');
		var bankEmail = document.getElementById('bankEmail');
		var branch = document.getElementById('branch');
		var address = document.getElementById("address");
		var country = document.getElementById('country');
		var state = document.getElementById("state");
		var ifsc = document.getElementById("ifsc");
		var pinCode = document.getElementById('pinCode');
		var city = document.getElementById('city');
		var contactNum = document.getElementById('contactNum');
		var altcontactNum = document.getElementById('altcontactNum');
		var altEmail = document.getElementById('altEmail');
		var email = document.getElementById("email");
		var currencydeal = document.getElementById("currencydeal");
		var phoneNum = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
		var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		var nameval             = /^[A-Za-z][A-Za-z0-9]*$/;
		var accExpiryDate = document.getElementById('datepicker').value;
		var rate = document.getElementById('rate').value;
		var canSubmit = true;

		if(buyerName.value.match(/^[A-Za-z][A-Za-z0-9]*$/) && !(document.getElementById('buyerName').value == '' )) {

			document.getElementById('buyerNameError').style.display='none';
		}
		else {

			document.getElementById('buyerNameError').style.display='block';
			canSubmit = false;
		}


		if (document.getElementById('companyName').value == '') {
			document.getElementById('companyNameError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('companyNameError').style.display = 'none';
		}
		if (document.getElementById('bank').value == '') {
			document.getElementById('bankError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('bankError').style.display = 'none';
		}

		if (bankEmail.value
						.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/)
				&& !(document.getElementById('bankEmail').value == '')) {

			document.getElementById('emailError2').style.display = 'none';

		} else {
			document.getElementById('emailError2').style.display = 'block';
			canSubmit = false;
		}
		if (document.getElementById('branch').value == '') {
			document.getElementById('branchError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('branchError').style.display = 'none';
		}
		if (document.getElementById('ifsc').value == '') {
			document.getElementById('ifscError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('ifscError').style.display = 'none';
		}

		if (document.getElementById('address').value == '') {
			document.getElementById('addressError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('addressError').style.display = 'none';
		}
		if (document.getElementById('country').value == '-1') {
			document.getElementById('countryError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('countryError').style.display = 'none';
		}

		if (document.getElementById('state').value == '-1'
				|| document.getElementById('state').value == '') {
			document.getElementById('stateError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('stateError').style.display = 'none';
		}

		if (document.getElementById('pinCode').value == '') {
			document.getElementById('pinCodeError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('pinCodeError').style.display = 'none';
		}

		if (document.getElementById('city').value == '') {
			document.getElementById('cityError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('cityError').style.display = 'none';
		}

		if (contactNum.value
						.match(/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/)
				&& !(document.getElementById('contactNum').value == '')) {

			document.getElementById('contactNum1Error').style.display = 'none';
		} else {

			document.getElementById('contactNum1Error').style.display = 'block';
			canSubmit = false;
		}

		if (email.value
						.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/)
				&& !(document.getElementById('email').value == '')) {

			document.getElementById('emailError1').style.display = 'none';

		} else {
			document.getElementById('emailError1').style.display = 'block';
			canSubmit = false;
		}

		if (altcontactNum.value
						.match(/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/)
				|| (document.getElementById('altcontactNum').value == '')) {

			document.getElementById('altcontactNumError').style.display = 'none';
		} else {

			document.getElementById('altcontactNumError').style.display = 'block';
			canSubmit = false;
		}

		if (altEmail.value
						.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/)
				|| (document.getElementById('altEmail').value == '')) {

			document.getElementById('altemailError').style.display = 'none';

		} else {
			document.getElementById('altemailError').style.display = 'block';
			canSubmit = false;
		}
		if (document.getElementById('currencydeal').value == '') {
			document.getElementById('currencydealError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('currencydealError').style.display = 'none';
		}

		if (accExpiryDate == '') {
			document.getElementById('expiryDateError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('expiryDateError').style.display = 'none';
		}

		if (rate == 0) {
			document.getElementById('rateError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('rateError').style.display = 'none';
		}

		if (canSubmit == false) {
			return false;
		}

		$(document).ready(
				function() {
					$(':input', '#newBuyerForm').not(
							':button, :submit, :reset, :hidden').val('')
				});
	}
</script>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
	<div class="col-sm-12 col-md-12 col-lg-12">
		<div class="successMsg"
			 style="text-align: center; color: green; font-size: 18px;">${success}</div>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3 align="center">
			<spring:message code="label.newBuyer" />
		</h3>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12 newcustomer_border">

		<form:form action="updatenewBuyerPage" method="post"
				   commandName="newBuyerForm" id="newBuyer" onsubmit="return val();">

			<div class="col-sm-12 col-md-6 col-lg-6">
				<table>


					<tr>
						<td class="col-sm-6"><b><spring:message
								code="label.customerName" /> </b><span style="color: red">*</span></td>
						<td class="col-sm-6"><form:input path="name"
														 value="${model.newBuyerForm.name}" id="name" readonly="true" />
						</td>
					</tr>

					<tr>
						<td class="col-sm-6"><b><spring:message
								code="label.buyerName" /></b><span style="color: red">*</span></td>
						<td class="col-sm-6"><form:input path="buyerName"
														 id="buyerName" placeholder="Enter Buyer Name" />
							<div id="buyerNameError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message
								code="label.buyerCompanyName" /></b><span style="color: red">*</span></td>
						<td class="col-sm-6"><form:input path="companyName"
														 id="companyName" placeholder="Enter Company Name" />
							<div id="companyNameError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message
								code="label.address" /></b><span style="color: red">*</span></td>
						<td class="col-sm-6"><form:input path="address" id="address"
														 placeholder="Enter Address" />
							<div id="addressError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.city" /></b><span
								style="color: red">*</span></td>
						<td class="col-sm-6"><form:input path="city" id="city"
														 placeholder="Enter City" />
							<div id="cityError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message
								code="label.state" /></b><span style="color: red">*</span></td>
						<td class="col-sm-6"><form:select path="state" id="state"
														  value="" style="width:205px;" /> <script>
							populateCountries("country", "state");

						</script>
							<div id="stateError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message
								code="label.country" /></b><span style="color: red">*</span></td>
						<td class="col-sm-6"><form:select path="country" id="country"
														  value="" style="width:205px;" />
							<div id="countryError"  style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message
								code="label.pincode" /></b><span style="color: red">*</span></td>
						<td class="col-sm-6"><form:input path="pinCode" id="pinCode"
														 placeholder="Enter Pincode" />
							<div id="pinCodeError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message
								code="label.contactNumber" /></b><span style="color: red">*</span></td>
						<td class="col-sm-6"><form:input path="contactNum"
														 id="contactNum" placeholder="Enter Contact Number" />
							<div id="contactNum1Error" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message
								code="label.altContactNo" /></b><span style="color: red"></span></td>
						<td class="col-sm-6"><form:input path="altcontactNum"
														 id="altcontactNum" placeholder="Enter Alternate Contact Number" />
							<div id="altcontactNumError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
						</td>

					</tr>

				</table>
			</div>
			<div class="col-sm-12 col-md-6 col-lg-6">
				<table>
					<tr>
						<td class="col-sm-6"><b><spring:message
								code="label.email" /></b><span style="color: red">*</span></td>
						<td class="col-sm-6"><form:input path="email" id="email"
														 placeholder="Enter Email" />
							<div id="emailError1" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message
								code="label.altEmail" /></b></td>
						<td class="col-sm-6"><form:input path="altEmail"
														 id="altEmail" placeholder="Enter Alternate Email" />
							<div id="altemailError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
							<div id="altemailError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div></td>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message
								code="label.buyerBank" /></b><span style="color: red">*</span></td>
						<td class="col-sm-6"><%-- <form:input path="bank" id="bank"
								placeholder="Enter Buyer Bank" /> --%>
							<form:select path="bank"  id="bank">
							<form:option value=""> select a Value</form:option>
							<form:option value="ABHYUDAYA CO-OP BANK LTD"> Abhyudaya Co-op Bank Ltd</form:option>
							<form:option value="ABU DHABI COMMERCIAL BANK">Abu Dhabi Commercial Bank</form:option>
							<form:option value="AKOLA DISTRICT CENTRAL CO-OPERATIVE BANK">Akola District Central Co-Operative Bank</form:option>
							<form:option value="AKOLA JANATA COMMERCIAL COOPERATIVE BANK">  Akola Janata Commercial Cooperative Bank	 </form:option>
							<form:option value="ALLAHABAD BANK">	Allahabad Bank</form:option>
							<form:option value="ALMORA URBAN CO-OPERATIVE BANK LTD.">Almora Urban Co-Operative Bank Ltd</form:option>
							<form:option value="ANDHRA BANK"> Andhra Bank </form:option>
							<form:option value="ANDHRA PRAGATHI GRAMEENA BANK">Andhra Pragathi Grameena Bank</form:option>
							<form:option value="APNA SAHAKARI BANK LTD">Apna Sahakari Bank Ltd</form:option>
							<form:option value="AUSTRALIA AND NEW ZEALAND BANKING GROUP LIMITED.">Australia and New Zealand Banking Group Limited</form:option>
							<form:option value="AXIS BANK">Axis Bank</form:option>
							<form:option value="BANK INTERNASIONAL INDONESIA">Bank Internasional Indonesia</form:option>
							<form:option value="BANK OF AMERICA">Bank of America</form:option>
							<form:option value="BANK OF BAHRAIN AND KUWAIT">	Bank of Bahrain and Kuwait</form:option>
							<form:option value="BANK OF BARODA">	Bank of Baroda </form:option>
							<form:option value="HDFC BANK LTD">	HDFC Bank Pvt Ltd </form:option>
							<form:option value="BANK OF CEYLON">Bank of Ceylon</form:option>
							<form:option value="BANK OF INDIA">Bank of India</form:option>
							<form:option value="BANK OF MAHARASHTRA">Bank of Maharashtra</form:option>
							<form:option value="BANK OF TOKYO-MITSUBISHI UFJ LTD.">Bank of Tokyo-Mitsubishi UFJ Ltd</form:option>
							<form:option value="BARCLAYS BANK PLC">Barclays Bank PLC</form:option>
							<form:option value="BASSEIN CATHOLIC CO-OP BANK LTD">Bassein Catholic Co-Op Bank Ltd	</form:option>
							<form:option value="BHARATIYA MAHILA BANK LIMITED">Bharatiya Mahila Bank Limited</form:option>
							<form:option value="BNP PARIBAS">BNP Paribas</form:option>
							<form:option value="CALYON BANK">Calyon Bank</form:option>
							<form:option value="CANARA BANK">Canara Bank</form:option>
							<form:option value="CAPITAL LOCAL AREA BANK LTD.">Capital Local Area Bank Ltd</form:option>
							<form:option value="CATHOLIC SYRIAN BANK LTD.">Catholic Syrian Bank Ltd</form:option>
							<form:option value="CENTRAL BANK OF INDIA">Central Bank Of Indiav</form:option>
							<form:option value="CHINATRUST COMMERCIAL BANK">Chinatrust Commercial Bank</form:option>
							<form:option value="CITIBANK NA">Citibank NA</form:option>
							<form:option value="CITIZENCREDIT CO-OPERATIVE BANK LTD">Citizencredit Co-operative Bank Ltd</form:option>
							<form:option value="CITY UNION BANK LTD">City Union Bank Ltd</form:option>
							<form:option value="COMMONWEALTH BANK OF AUSTRALIA">Commonwealth Bank of Australia</form:option>
							<form:option value="CORPORATION BANK">Corporation Bank</form:option>
							<form:option value="CREDIT SUISSE AG">Credit Suisse AG</form:option>
							<form:option value="DBS BANK LTD">Dbs Bank Ltd</form:option>
							<form:option value="DENA BANK">Dena Bank</form:option>
							<form:option value="DEUTSCHE BANK">Deutsche Bank</form:option>
							<form:option value="DEUTSCHE SECURITIES INDIA PRIVATE LIMITED">Deutsche Securities India Private limited</form:option>
							<form:option value="DEVELOPMENT CREDIT BANK LIMITED">Development Credit Bank limited</form:option>

							<form:option value="DHANLAXMI BANK LTD">Dhanlaxmi Bank ltd</form:option>
							<form:option value="DICGC">DICGC</form:option>
							<form:option value="DOMBIVLI NAGARI SAHAKARI BANK LIMITED">Dombivli Nagari Sahakari Bank limited</form:option>
							<form:option value="FIRSTRAND BANK LIMITED">Firstrand Bank limited</form:option>
							<form:option value="GOPINATH PATIL PARSIK JANATA SAHAKARI BANK LTD">Gopinath Patil Parsik Janata Sahakari Bank ltd</form:option>
							<form:option value="HSBC">HSBC</form:option>
							<form:option value="ICICI BANK LTD">ICICI BANK LTD</form:option>
							<form:option value="IDBI BANK LTD">IDBI BANK LTD</form:option>
							<form:option value="SOUTH INDIAN BANK">South Indian Bank</form:option>
							<form:option value="STANDARD CHARTERED BANK">Standard Chartered Bank</form:option>
							<form:option value="STATE BANK OF BIKANER AND JAIPUR">State Bank of Bikaner and Jaipur</form:option>
							<form:option value="STATE BANK OF HYDERABAD">State Bank of Hyderabad</form:option>
							<form:option value="STATE BANK OF INDIA">State Bank of India</form:option>
							<form:option value="STATE BANK OF MAURITIUS LTD">State Bank of Mauritius ltd</form:option>
							<form:option value="STATE BANK OF PATIALA">State Bank of Patiala</form:option>
							<form:option value="STATE BANK OF TRAVANCORE">State Bank of Travancore</form:option>
							<form:option value="SUMITOMO MITSUI BANKING CORPORATION">Sumitomo Mitsui Banking Corporation</form:option>
							<form:option value="SYNDICATE BANK">SYNDICATE BANK</form:option>
							<form:option value="TAMILNAD MERCANTILE BANK LTD">Tamilnad Mercantile Bank ltd</form:option>
							<form:option value="THANE BHARAT SAHAKARI BANK LTD">Thane Bharat Sahakari Bank ltd</form:option>
							<form:option value="THE A.P. MAHESH CO-OP URBAN BANK LTD.">The A.P. Mahesh Co-op Urban Bank ltd.</form:option>
							<form:option value="THE AHMEDABAD MERCANTILE CO-OPERATIVE BANK LTD.">The Ahmedabad Mercantile Co-operative Bank ltd.</form:option>
								<%-- <form:option value="THE ANDHRA PRADESH STATE COOP BANK LTD">The Andhra Pradesh State Coop Bank ltd</form:option>
                                <form:option value="THE BANK OF NOVA SCOTIA">The Bank of Nova Scotia</form:option>
                                <form:option value="THE BANK OF RAJASTHAN LTD">The Bank of Rajasthan ltd</form:option>
                                <form:option value="THE BHARAT CO-OPERATIVE BANK (MUMBAI) LTD">The Bharat Co-operative Bank (Mumbai) ltd</form:option>
                                <form:option value="THE COSMOS CO-OPERATIVE BANK LTD.">The Cosmos Co-operative Bank ltd.</form:option>
                                <form:option value="THE DELHI STATE COOPERATIVE BANK LTD.">The Delhi State Cooperative Bank ltd.</form:option>
                                <form:option value="THE FEDERAL BANK LTD">The Federal Bank ltd</form:option>
                                <form:option value="THE GADCHIROLI DISTRICT CENTRAL COOPERATIVE BANK LTD">The Gadchiroli District Central Cooperative Bank ltd</form:option>
                                <form:option value="THE GREATER BOMBAY CO-OP. BANK LTD">The Greater Bombay Co-op. Bank ltd</form:option>
                                <form:option value="THE GUJARAT STATE CO-OPERATIVE BANK LTD">The Gujarat State Co-operative Bank ltd</form:option>
                                <form:option value="THE JALGAON PEOPLES CO-OP BANK">The Jalgaon Peoples co-op Bank</form:option>
                                <form:option value="THE JAMMU AND KASHMIR BANK LTD">The Jammu and Kashmir Bank ltd</form:option>
                                <form:option value="THE KALUPUR COMMERCIAL CO. OP. BANK LTD.">The Kalupur Commercial Co. op. Bank ltd.</form:option>
                                <form:option value="THE KALYAN JANATA SAHAKARI BANK LTD.">The Kalyan Janata Sahakari Bank ltd.</form:option>
                                <form:option value="THE KANGRA CENTRAL CO-OPERATIVE BANK LTD">The Kangra Central Co-operative Bank ltd</form:option>
                                <form:option value="THE KANGRA COOPERATIVE BANK LTD">The Kangra Cooperative Bank ltd</form:option> --%>
							<form:option value="THE KARAD URBAN CO-OP BANK LTD">The Karad Urban Co-op Bank ltd</form:option>
							<form:option value="THE KARNATAKA STATE APEX COOP. BANK LTD.">The Karnataka State Apex Coop. Bank ltd.</form:option>
							<form:option value="THE LAKSHMI VILAS BANK LTD">The Lakshmi Vilas Bank ltd</form:option>
							<form:option value="THE MEHSANA URBAN COOPERATIVE BANK LTD">The Mehsana Urban Cooperative Bank ltd</form:option>
							<form:option value="THE MUNICIPAL CO OPERATIVE BANK LTD MUMBAI">The Municipal Co operative Bank ltd Mumbai</form:option>
							<form:option value="THE NAINITAL BANK LIMITED">The Nainital Bank limited</form:option>
							<form:option value="THE NASIK MERCHANTS CO-OP BANK LTD., NASHIK">The Nasik Merchants Co-op Bank ltd., Nashik</form:option>
							<form:option value="THE RAJASTHAN STATE COOPERATIVE BANK LTD.">The Rajasthan State Cooperative Bank ltd.</form:option>
							<form:option value="THE RATNAKAR BANK LTD">The Ratnakar Bank ltd</form:option>
							<form:option value="THE ROYAL BANK OF SCOTLAND N.V">The Royal Bank of Scotland N.V</form:option>
							<form:option value="THE SAHEBRAO DESHMUKH CO-OP. BANK LTD.">The Sahebrao Deshmukh Co-op. Bank lt</form:option>
							<form:option value="THE SARASWAT CO-OPERATIVE BANK LTD">The Saraswat Co-operative Bank ltd</form:option>
							<form:option value="THE SEVA VIKAS CO-OPERATIVE BANK LTD (SVB)">The Seva Vikas Co-operative Bank ltd (SVB)</form:option>
							<form:option value="THE SHAMRAO VITHAL CO-OPERATIVE BANK LTD">The Shamrao Vithal Co-operative Bank ltd</form:option>
							<form:option value="THE SURAT DISTRICT CO OPERATIVE BANK LTD.">The Surat District Co operative Bank ltd.</form:option>
							<form:option value="THE SURAT PEOPLES CO-OP BANK LTD">The Surat Peoples Co-op Bank ltd</form:option>
							<form:option value="THE SUTEX CO.OP. BANK LTD.">The Sutex Co.op. Bank ltd.</form:option>
							<form:option value="THE TAMILNADU STATE APEX COOPERATIVE BANK LIMITED">The Tamilnadu State Apex Cooperative Bank limited</form:option>
							<form:option value="THE THANE DISTRICT CENTRAL CO-OP BANK LTD">The Thane District Central Co-op Bank ltd</form:option>
							<form:option value="THE THANE JANATA SAHAKARI BANK LTD">The Thane Janata Sahakari Bank ltd</form:option>
							<form:option value="THE VARACHHA CO-OP. BANK LTD.">The Varachha Co-op. Bank ltd.</form:option>
							<form:option value="THE VISHWESHWAR SAHAKARI BANK LTD.,PUNE">The Vishweshwar Sahakari Bank ltd.,Pune</form:option>
							<form:option value="THE WEST BENGAL STATE COOPERATIVE BANK LTD">The West Bengal State Cooperative Bank ltd</form:option>
							<form:option value="TJSB SAHAKARI BANK LTD.">TJSB Sahakari Bank ltd.</form:option>
							<form:option value="TUMKUR GRAIN MERCHANTS COOPERATIVE BANK LTD.">Tumkur Grain Merchants Cooperative Bank ltd.</form:option>
							<form:option value="UBS AG">UBS AG</form:option>
							<form:option value="UCO BANK">UCO bank</form:option>
							<form:option value="UNION BANK OF INDIA">Union Bank of India</form:option>
							<form:option value="UNITED BANK OF INDIA">United Bank of India</form:option>
							<form:option value="UNITED OVERSEAS BANK">United Overseas Bank</form:option>
							<form:option value="VASAI VIKAS SAHAKARI BANK LTD.">Vasai Vikas Sahakari Bank ltd.</form:option>
							<form:option value="VIJAYA BANK">VIJAYA Bank</form:option>
							<form:option value="WEST BENGAL STATE COOPERATIVE BANK">West Bengal State Cooperative Bank</form:option>
							<form:option value="WESTPAC BANKING CORPORATION">Westpac Banking Corporation</form:option>
							<form:option value="WOORI BANK">Woori Bank</form:option>
							<form:option value="YES BANK LTD">YES Bank ltd</form:option>
							<form:option value="ZILA SAHKARI BANK LTD GHAZIABAD">Zila Sahkari Bank ltd Ghaziabad</form:option>

							</form:select>
							<div id="bankError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>

					<tr>
						<td class="col-sm-6"><b><spring:message
								code="label.buyerBankEmail" /></b><span style="color: red">*</span></td>
						<td class="col-sm-6"><form:input path="bankEmail"
														 id="bankEmail" placeholder="Enter Bank Email" />
							<div id="emailError2" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message
								code="label.buyerBankBranch" /></b><span style="color: red">*</span></td>
						<td class="col-sm-6"><form:input path="branch" id="branch"
														 placeholder="Enter Buyer Bank Branch" />
							<div id="branchError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message
								code="label.ifscOrSwift" /></b><span style="color: red">*</span></td>
						<td class="col-sm-6"><form:input path="ifsc" id="ifsc"
														 placeholder="Enter Ifsc or Swift Code" />
							<div id="ifscError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>



					<tr>
						<td class="col-sm-6" hidden="true"><b><spring:message
								code="label.state" /></b><span style="color: red">*</span></td>
						<td class="col-sm-6" hidden="true"><form:select path="state" id="state"
														  value="" style="width:205px;" /> <script>
							populateCountries("country", "state");

						</script>
							<div id="stateError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message
								code="label.accountExpiryDate" /></b><span style="color: red">*</span></td>
						<td id="e1" class="col-sm-6"><form:input path="accExpiryDate"
																 class="form-control" placeholder="Select date"
																 style="width: 206px;" readonly="true" id="datepicker" /><i
								class="fa fa-calendar"></i> <span id="expiryDateError"
																  class="error" style="display: none;"><font color="red"><spring:message
								code="label.validation" />*</font></span></td>

					</tr>



					<tr>
						<td class="col-sm-6"><b><spring:message
								code="label.currencyDealt" /></b><span style="color: red">*</span></td>
						<td class="col-sm-6"><form:select path="currencydeal"
														  id="currencydeal" style="width:205px;">
							<form:option value="">
								<spring:message code="label.selectValue" />
							</form:option>
							<form:option value="Rupee">
								<spring:message code="label.rupee" />
							</form:option>
							<form:option value="Dollar">
								<spring:message code="label.dollar" />
							</form:option>
						</form:select>
							<div id="currencydealError" class="error" style="display: none">
								<spring:message code="label.pleaseSelectAValue" />
							</div></td>
					</tr>

					<tr>
						<form:hidden path="rate" id="rate" value="0" />
						<form:hidden path="companyId" id="companyId" />
						<td class="col-sm-6"><b>Rating</b><span style="color: red">*</span></td>
						<td class="col-sm-6"><div class="basic" data-average="5"
												  data-id="1"></div>
							<div id="rateError" style="display: none; color: red;">Rating
								is mandatory</div></td>
					</tr>


				</table>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<table align="center">
					<tr>
						<td class="col-sm-8"><input type="submit"
													value="<spring:message code="label.confirm"/>"
													class="btn btn-primary"></td>
						<td><a href="user" class="btn btn-success"><spring:message
								code="label.back" /></a></td>
					</tr>
				</table>
			</div>
		</form:form>

		<div class="col-sm-12 col-md-12 col-lg-12">
			<div class="col-sm-12 col-md-12 col-lg-12">
				<input type="text" id="kwd_search" value=""
					   placeholder="Search Here..." style="float: right;" />
			</div>
			<table class="table data jqtable example" id="my-table">

				<caption>
					<spring:message code="label.buyerList" />
				</caption>

				<thead>
				<tr>
					<th><spring:message code="label.selectValue" /></th>
					<th><spring:message code="label.buyerName" /></th>
					<th><spring:message code="label.transactionId" /></th>
					<th><spring:message code="label.contactNumber" /></th>
					<th><spring:message code="label.address" /></th>
					<th><spring:message code="label.buyerBankBranch" /></th>
					<th><spring:message code="label.status" /></th>
					<th><spring:message code="label.action" /></th>
				</tr>
				</thead>
				<tbody>
				<c:if test="${! empty newbuyeList}">
					<c:forEach items="${newbuyeList}" var="buyer">
						<tr>
							<td><c:out value="${buyer.id}"></c:out></td>
							<td><c:out value="${buyer.buyerName}"></c:out>
							<td><c:out value="${buyer.transactionId}"></c:out>
							<td><c:out value="${buyer.contactNum}"></c:out></td>
							<td><c:out value="${buyer.address}"></c:out></td>
							<td><c:out value="${buyer.branch}"></c:out></td>
							<td><c:out value="${buyer.status}"></c:out></td>
							<td><a href="selectBuyerUpdate?id=${buyer.id}"
								   class="btn btn-primary"><spring:message code="label.edit" /></a></td>
						</tr>
					</c:forEach>
				</c:if>

				</tbody>
			</table>
		</div>
	</div>
</div>
<script>
	$(document).ready(
			function() {
				$(".basic").jRating({
					onClick : function(element, rate) {
						$('#rate').val(rate);
					}
				});
				document.getElementById("country").value = "India";
				populateStates("country", "state");
			});


</script>
