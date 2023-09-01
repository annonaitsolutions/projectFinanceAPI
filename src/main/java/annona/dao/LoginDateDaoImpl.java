package annona.dao;

import annona.domain.LoginDate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository
public class LoginDateDaoImpl implements LoginDateDao {
    protected Logger log = Logger.getLogger(LoginDateDaoImpl.class.getName());
    @PersistenceContext
    private EntityManager em;

    public EntityManager entityManager() {
        EntityManager em = new LoginDateDaoImpl().em;
        if (em == null)
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    @Override
    @Transactional
    public void updateLoginDate(LoginDate loginDate) {

        em.merge(loginDate);
        em.flush();

    }

    @Override
    @Transactional
    public void insertLoginDate(LoginDate loginDate) {

        em.persist(loginDate);

    }

    @Override
    public LoginDate getLoginDate() {
        try {
            TypedQuery<LoginDate> q = em.createQuery("SELECT o FROM LoginDate o ORDER BY o.id DESC", LoginDate.class);
            List<LoginDate> loginDates=q.getResultList();
            return loginDates.size()>0?loginDates.get(0):null;
        } catch (NoResultException e) {
            log.info("login date not found");
            e.printStackTrace();
        }
        return null;
    }

}
