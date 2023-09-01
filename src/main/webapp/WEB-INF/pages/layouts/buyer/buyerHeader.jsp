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


 <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.css">
 <script src="<%=request.getContextPath()%>/resources/js/jquery-1.9.1.min.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/search.js"></script>
<link href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/resources/js/countries.js"></script>
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
<body>

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
</script>
	

<nav class="navbar navbar1">
	<div class="container-fluid">
		<div class="navbar-header col-sm-2 col-xs-12 logo_1">
			<a href="user" class="main_logo_link"><img src="<%=request.getContextPath()%>/resources/images/logopng44.png" alt="ANNONA IT SOLUTIONS" class="a_logo img-responsive" title="ANNONA IT SOLUTIONS PVT.LTD."></a>
			<a href="#" style="color: #2E9AFE;">ANNONA IT SOLUTIONS</a>
		 &nbsp;&nbsp;<a href="#" style="color: #005C79;">DHANAPURTHI&nbsp;<sup>TM</sup></a>
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
	      <a class="navbar-brand" href="buyers"><i class="glyphicon glyphicon-home"></i></a>
	         &nbsp;&nbsp;<a href="#" style="color: #005C79;">DHANAPURTHI&nbsp;<sup>TM</sup></a>
	    </div>
		<div class="pull-left">

				<div class="prof_img"><a href="editBuyerProfile?id=${requestCurrentUser.id}"><span><img src="${requestCurrentUser.imageName}" class="profile_img"></span><span class="customer_name"><spring:message code="label.welcome" /> ${requestCurrentUser.displayName}</span></a></div>

		</div>
	    <div id="myNavbar">
			<ul class="nav navbar-nav navbar-right">
			<li class="log_out"><a href="buyerHelp"><i class="glyphicon glyphicon-modal-window"></i>&nbsp;<strong><spring:message code="label.menuHelp"/></strong></a></li>
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

	
	
	
