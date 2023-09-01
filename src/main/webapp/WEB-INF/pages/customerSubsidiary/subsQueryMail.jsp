<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
     "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
<body>
    <center>
        <h1>Sending Query Mail </h1>
        <form method="post" action="subsMailSender" name="myForm" onsubmit="return validateForm();">
            <table border="0" width="80%">
                 <tr>
                    <td>To:</td>
                    <td><input type="text" name="recipient" id="recipient" size="65" /></td>
                </tr>  
                <tr>
                    <td>Subject:</td>
                    <td><input type="text" name="subject" id="subject" size="65" /></td>
                </tr>
                <tr>
                    <td>Message:</td>
                    <td><textarea cols="50" rows="10" name="message"></textarea></td>
                </tr>               
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Send E-mail" />
                    </td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html>