package annona.dao;

import javax.persistence.TypedQuery;

import annona.domain.ConsortiumBank;

public interface ConsortiumDAO {
	
	public ConsortiumBank createConsortium(ConsortiumBank bank);
	
	public  ConsortiumBank getByConsortiumId(Long id);
	
	public void updateConsortium(ConsortiumBank bank);
	
	public TypedQuery<ConsortiumBank> getConsortiumByMasterKey(String masterKey);

}
