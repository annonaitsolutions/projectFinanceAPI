<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>

<tiles:insertAttribute name="customerSubsidiaryheader" />
<tiles:insertAttribute name="customerSubsidiarymenu" />
<tiles:insertAttribute name="customerSubsidiarybody" />
<tiles:insertAttribute name="customerSubsidiaryfooter" />
<body>
<%-- <link href="<spring:theme code='css'/>" rel="stylesheet"  type="text/css" />  --%>
<link href="<%=request.getContextPath()%>/resources/css/themeBlue.css" rel="stylesheet"  type="text/css"/>
</body>
</html>