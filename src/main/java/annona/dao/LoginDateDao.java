package annona.dao;

import annona.domain.BankDetails;
import annona.domain.LoginDate;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.Collection;

public interface LoginDateDao {


    @Transactional
    void updateLoginDate(LoginDate loginDate);

    @Transactional
    void insertLoginDate(LoginDate loginDate);

    LoginDate getLoginDate();
}
