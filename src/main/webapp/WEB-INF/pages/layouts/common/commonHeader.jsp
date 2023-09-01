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
<!-- Le styles -->
<link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/font-awesome.css" rel="stylesheet">


 <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.css">
 <script src="<%=request.getContextPath()%>/resources/js/jquery-1.9.1.min.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/search.js"></script>
<link href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css" rel="stylesheet">

<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Lato:300,400,700">
		<link rel="stylesheet" href="http://weloveiconfonts.com/api/?family=fontawesome">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
<script src="<%=request.getContextPath()%>/resources/js/simplecalendar.js" type="text/javascript"></script>

<link href="<%=request.getContextPath()%>/resources/css/verticalmenu.css" type="text/css" rel="Stylesheet" />
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!--[if IE 7]>
      <link href="//nethna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome-ie7.css" rel="stylesheet">
<![endif]-->
	<div class="top-bar">
			<div class="row">
				<div class="col-sm-3 col-lg-3 col-md-3">
					<a class="main_logo_link" href="index.html"><img src="<%=request.getContextPath()%>/resources/images/logopng44.png" class="a_logo" alt="logo"></a>
					<p class="annona_title">ANNONA IT SOLUTIONS PVT.LTD.</p>
				</div>
				
				<div class="col-sm-7 col-lg-7 col-md-7">
					<div class="img_chain1" style="float:left;">
						<img src="<%=request.getContextPath()%>/resources/images/Tagline_final_transparent.png" class="img_chain_trading img-responsive">
					</div>
				</div>
				<div class="col-sm-2 col-lg-2 col-md-2">
					<div class="setting">
						<a href='<c:url value="/j_spring_security_logout"/>' class="button-default setlog setlog"><i class="fa fa-unlock">&nbsp;Logout</i></a>
					</div>
				</div>
			</div>
	</div>
<style>

	input.btn.btn-primary {
        width: 106%;
		height: 40px;
		font-size: 30px;
		margin-left: -15px;
}
	.trading{
		cursor:pointer;
	}
	.masterplan{
		cursor:pointer;
		border:1px solid rgba(222, 218, 218, 0.84);
		border-radius:5px;
	}
	
	.masterplan:hover{
		background-color:#DCE9F1;
	}
</style>

