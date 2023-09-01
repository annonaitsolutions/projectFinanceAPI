<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
function validateForm() {
    var x = document.forms["myForm"]["recipient"].value;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
        alert("Enter A Email address");
        return false;
    }
    var y = document.forms["myForm"]["subject"].value;
    if(y==null&&y==''){
    	 alert("Subject name must be filled out");
    	  return false;
    }
    
    
}
</script>
    <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
        <h3><spring:message code="label.sendQueryMail"/></h3>
     </div>
        <form method="post" action="mailSender" name="myForm" onsubmit="return validateForm();">
            <table align="center">
                 <tr>
                    <td class="col-sm-6"><spring:message code="label.to"/>:</td>
                    <td class="col-sm-6"><input type="text" name="recipient"  placeholder="Enter a Mail" id="recipient" size="65" /></td>
                </tr>  
                <tr>
                    <td class="col-sm-6"><spring:message code="label.subject"/>:</td>
                    <td class="col-sm-6"><input type="text" name="subject" placeholder="Enter a Subject" id="subject" size="65" /></td>
                </tr>
                <tr>
                    <td class="col-sm-6"><spring:message code="label.mesg"/>:</td>
                    <td class="col-sm-6"><textarea cols="40" rows="10" name="message" placeholder="Enter a Message" style="width:210%;"></textarea></td>
                </tr>               
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="<spring:message code="label.sendEmailNotification"/>" class="btn btn-primary" />
                    </td>
                </tr>
            </table>
        </form>
    </div>