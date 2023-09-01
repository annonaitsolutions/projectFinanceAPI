package annona.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import annona.domain.LimitBurst;

public interface LimitBurstDAO {

	public TypedQuery<LimitBurst> getList();
	
	public LimitBurst getByLimitBurstId(Long id);
	
	public void updateLimitBurst(LimitBurst limit);
	
	public void insertLimitBurst(LimitBurst limit);
	
	public TypedQuery<LimitBurst> getByAppMngStatus();
	
	public TypedQuery<LimitBurst> getByBankStatus();
	
	public TypedQuery<LimitBurst> getFullList();
	
	public TypedQuery<LimitBurst> getByName(String customer);
	
	/**
	 * Method to get LimitBurst based on poKey
	 * @param poKey
	 * @return
	 */
	List<LimitBurst> getLimitBurstByPOKey(String poKey);
	
	

}
