<%@include file="taglib_includes.jsp"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	function validateForm() {

		var masterKey = document.getElementById('goods');

		var canSubmit = true;

		if (document.getElementById('goods').value == '') {
			document.getElementById('goodsError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('goodsError').style.display = 'none';
		}
		
		if (document.getElementById('txtSupplierBank').value == '') {
			document.getElementById('supplierBankError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('supplierBankError').style.display = 'none';
		}

		if (canSubmit == false) {
			return false;
		}

	}
</script>
 <div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
	<form:form action="buyerDiffBankEvent" method="post"
		commandName="newEventForm" onsubmit="return validateForm()">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 style=" text-align: center">Events For Buyer(Same Bank)</h3>
			</div>
			
<form:hidden path="userName" value=""></form:hidden>
<form:hidden path="customerBank" value=""></form:hidden>

				<table align="center">
				
				<tr>
						<td colspan="1">Master Key :</td>
						<td><form:select path="masterKey">
								<form:options items="${newEventForm.masterPlanList}"
									itemValue="masterKey" itemLabel="masterKey" />
							</form:select></td>
					</tr>
					<tr>
						<td colspan="1">Supplier Name :</td>
						<td><form:select path="supplierName">
								<form:options items="${newEventForm.supplierList}"
									itemValue="supplierName" itemLabel="supplierName" />
							</form:select></td>
					</tr>

					<tr>
						<td colspan="1">Supplier Bank :</td>
						<td>
						
						<form:select path="supplierBank"  id="txtSupplierBank">
							            <form:option value=""> select</form:option>
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
									<%-- <form:option value="IDRBT">IDRBT</form:option>
									<form:option value="INDIAN BANK">Indian Bank</form:option>
									<form:option value="INDIAN OVERSEAS BANK">Indian Overseas Bank</form:option>
									<form:option value="INDUSIND BANK LTD">Indusind Bank ltd</form:option>
									<form:option value="INDUSTRIAL AND COMMERCIAL BANK OF CHINA LIMITED">Industrial and Commercial Bank of China limited</form:option>
									<form:option value="JALGAON JANATA SAHKARI BANK LTD">Jalgaon Janata Sahkari bank ltd</form:option>
									<form:option value="JANAKALYAN SAHAKARI BANK LTD">Janakalyan Sahakari Bank ltd</form:option>
									<form:option value="JANASEVA SAHAKARI BANK (BORIVLI) LTD">Janaseva Sahakari Bank (Borivli) ltd</form:option>
									<form:option value="JANASEVA SAHAKARI BANK LTD. PUNE">Janaseva Sahakari Bank ltd. Pune</form:option>
									<form:option value="JANATA SAHAKARI BANK LTD (PUNE)">Janata Sahakari Bank ltd (Pune)</form:option>
									<form:option value="JPMORGAN CHASE BANK N.A">JPMORGAN Chase Bank N.a</form:option>
									<form:option value="KALLAPPANNA AWADE ICH JANATA S BANK">Kallappanna Awade Ich Janata S Bank</form:option>
									<form:option value="KAPOL CO OP BANK">Kapol Co op Bank</form:option>
									<form:option value="KARNATAKA BANK LTD">Karnataka Bank ltd</form:option>
									<form:option value="KARNATAKA VIKAS GRAMEENA BANK">Karnataka Vikas Grameena Bank</form:option>
									<form:option value="KARUR VYSYA BANK">Karur Vysya Bank</form:option>
									<form:option value="KOTAK MAHINDRA BANK">Kotak Mahindra Bank</form:option>
									<form:option value="KURMANCHAL NAGAR SAHKARI BANK LTD">Kurmanchal Nagar Sahkari Bank ltd</form:option>
									<form:option value="MAHANAGAR CO-OP BANK LTD">Mahanagar Co-op Bank ltd</form:option>
									<form:option value="MAHARASHTRA STATE CO OPERATIVE BANK">Maharashtra State Co Operative Bank</form:option>
									<form:option value="MASHREQBANK PSC">MashreqBank PSC</form:option>
									<form:option value="MIZUHO CORPORATE BANK LTD">Mizuho Corporate Bank ltd</form:option>
									<form:option value="MUMBAI DISTRICT CENTRAL CO-OP. BANK LTD.">Mumbai District Central Co-op. Bank ltd.</form:option>
									<form:option value="NAGPUR NAGRIK SAHAKARI BANK LTD">Nagpur Nagrik Sahakari Bank ltd</form:option>
									<form:option value="NATIONAL AUSTRALIA BANK">National Australia Bank</form:option>
									<form:option value="NEW INDIA CO-OPERATIVE BANK LTD.">New India Co-operative Bank ltd.</form:option>
									<form:option value="NKGSB CO-OP BANK LTD">NKGSB Co-op Bank ltd</form:option>
									<form:option value="NORTH MALABAR GRAMIN BANK">North Malabar Gramin Bank</form:option>
									<form:option value="NUTAN NAGARIK SAHAKARI BANK LTD">Nutan Nagarik Sahakari Bank ltd</form:option>
						            <form:option value="OMAN INTERNATIONAL BANK SAOG">Oman International Bank Saog</form:option>
									<form:option value="ORIENTAL BANK OF COMMERCE">Oriental Bank of Commerce</form:option>
									<form:option value="PARSIK JANATA SAHAKARI BANK LTD">Parsik Janata Sahakari Bank ltd</form:option>
									<form:option value="PRATHAMA BANK">Prathama Bank</form:option>
									<form:option value="PRIME CO OPERATIVE BANK LTD">Prime Co operative Bank ltd</form:option>
									<form:option value="PUNJAB AND MAHARASHTRA CO-OP BANK LTD.">Punjab and Maharashtra Co-op Bank ltd.</form:option>
									<form:option value="PUNJAB NATIONAL BANK">Punjab National Bank</form:option>
									<form:option value="RABOBANK INTERNATIONAL (CCRB)">Rabobank International (CCRB)</form:option>
								    <form:option value="RAJGURUNAGAR SAHAKARI BANK LTD.">Rajgurunagar Sahakari Bank ltd.</form:option>
									<form:option value="RAJKOT NAGARIK SAHAKARI BANK LTD">Rajkot Nagarik Sahakari Bank ltd</form:option>
									<form:option value="RESERVE BANK OF INDIA">Reserve Bank of India</form:option>
									<form:option value="SBERBANK">SBERBANK</form:option>
									<form:option value="SHINHAN BANK">Shinhan Bank</form:option>
									<form:option value="SHRI CHHATRAPATI RAJARSHI SHAHU URBAN CO-OP ">Shri Chhatrapati Rajarshi Shahu urban Co-op </form:option>
									<form:option value="BANK LTD">BANK LTD</form:option>
                                    <form:option value="SOCIETE GENERALE">Societe Generale</form:option>
									<form:option value="SOLAPUR JANATA SAHKARI BANK LTD.SOLAPUR">Solapur Janata Sahkari Bank ltd.Solapur</form:option>--%>
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
								<div id="supplierBankError" style="display: none; color: red;">
									Supplier Bank is Mandatory</div>
							<div id="supplierBankError" style="display: none; color: red;">
							Supplier Bank is Mandatory</div>
						</td>
					</tr> 
					
					<tr>
						<td colspan="1">Goods :</td>
						<td><form:input path="goods" id="goods"></form:input>
						<div id="goodsError" style="display: none; color: red;">
					Goods is Mandatory</div>
				<div id="goodsError" style="display: none; color: red;">
					Goods is Mandatory</div>
						
						</td>
					</tr>
					


				</table>
				<table align="center">
					<tr>
						<td><input type="submit" size="3" value="Continue"
							class="btn btn-primary"></td>
						<td><button type="button" name="Back"
								onclick="javascript:window.location='/annona/bnkEmp/newDiffEvent';"
								class="btn btn-success">Back</button></td>
					</tr>
				</table>





	</form:form>
</div>
