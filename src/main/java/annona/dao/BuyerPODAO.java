package annona.dao;

import javax.persistence.TypedQuery;

import annona.domain.BuyerPO;
import org.springframework.transaction.annotation.Transactional;

public interface BuyerPODAO {
	
	public void createUser(BuyerPO buyerPO);

    @Transactional
    void updatePo(BuyerPO buyerPO);

    public TypedQuery<BuyerPO> getBuyerPOByName(String customerName);
	
	public TypedQuery<BuyerPO> getBuyerPOByHeadName(String customerHeadName);

    TypedQuery<BuyerPO> getBuyerPOByHeadNameAndRemainingInvoices(String customerHeadName);

    public TypedQuery<BuyerPO> getPoKey(String pokey);

    TypedQuery<BuyerPO> getById(Long id);
}
