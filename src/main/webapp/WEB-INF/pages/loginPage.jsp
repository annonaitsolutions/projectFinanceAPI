<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 
 
<body class="homepage">
	
	<section id="bodychain">
		<div class="container">
			<div class="row">
				<div class="col-sm-12 col-md-12">
					<div class="tag_line">
						<marquee>ANNONA IT SOLUTIONS PVT. LTD</marquee>
					</div>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
				
				<c:url value="/j_spring_security_check" var="loginUrl" />
	<form  action="${loginUrl}" autocomplete="off"  method="post" id="loginForm">
					
						<div class="login-block" autocomplete="off">
							<div class="page-icon animated bounceInDown">
								<img class="logo"  src="<%=request.getContextPath()%>/resources/images/logopng44.png">
							</div>
							
							<div><font color="red">${error}</font></div>
							<p> &nbsp;</p>
							<input type="text" value="" class="form-control" placeholder="Username" alt="" id="j_username" name="j_username"/>
							<input type="password" value="" class="form-control" placeholder="Password" id="j_password" name="j_password"/>
							<button class="btn btn-md btn-primary btn-block" type="submit" value="Login">Login</button>							
							<a class="pull-left" href="/annona/auth/forgotPassword">Forgot password?</a>
							
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>