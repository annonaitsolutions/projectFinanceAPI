package annona.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.TypedQuery;

import annona.domain.ClientAppMng;
import annona.form.ClientAppMngForm;

public interface ClientAppMngDAO {
	
	public TypedQuery<ClientAppMng> getList() ;
	
	public TypedQuery<ClientAppMng> getByPending();
	
	public TypedQuery<ClientAppMng> getClientAppMngList(String username);
	
	public List<ClientAppMngForm> getClientAppMngByIdAndStatus();
	
	public List<ClientAppMngForm> getCustAppMngByIdAndAStatusComp(Long companyId);
	
	public List<ClientAppMngForm> getCustAppMngByStatusCompIDAndIsForTrading(Long companyId, Boolean isForTrading);
	
	public List<ClientAppMngForm> findAllClientAppMngs() ;
	
	public ClientAppMng getByClientAppMngId(Long id);
	
	public void updateUser(ClientAppMng client);
	
	public void insertCustomer(ClientAppMng client);

}
