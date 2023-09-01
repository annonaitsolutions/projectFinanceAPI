package annona.dao;

import javax.persistence.TypedQuery;

import annona.domain.Dispute;

public interface DisputeDAO {
	
	public TypedQuery<Dispute> getList();
	
	public void updateDispute(Dispute dispute) ;
	
	public  Dispute getByDisputeId(Long id);
	
	public void createDispute(Dispute dispute);
	
	public TypedQuery<Dispute> getDisputeList(String customer);
	
	public TypedQuery<Dispute> getDisputeSupplierList(String customer);
	
	public TypedQuery<Dispute> getPoKey(String poKey);
	
	public TypedQuery<Dispute> getDisputeMngList(String wareHousrMng);
	
	public TypedQuery<Dispute> getDisputeMngFullList(String wareHousrMng);

}
