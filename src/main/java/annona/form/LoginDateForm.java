package annona.form;


import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LoginDateForm {
    private Date loginDate;

    private String loginDateString;

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginDateString() {
        return loginDateString;
    }

    public void setLoginDateString(String loginDateString) {
        this.loginDateString = loginDateString;
    }
}
