package annona.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.TypedQuery;

import annona.domain.ClientAdmin;
import annona.form.ClientAdminForm;

public interface ClientAdminDAO {

	public TypedQuery<ClientAdmin> getList();

	public TypedQuery<ClientAdmin> getClientAdminByIdAndStatus();

	public List<ClientAdminForm> getClientAdminByIdAndAStatusCompAndIsForTrading(Boolean isForTrading);

	public ClientAdmin getByClientAdminId(Long id);

	public void updateUser(ClientAdmin client);

	public void insertCustomer(ClientAdmin client);

	public TypedQuery<ClientAdmin> getByPending();

	public TypedQuery<ClientAdminForm> getCustByIdAndStatusComp(Long id);

	public TypedQuery<ClientAdmin> getClientAdminList(String username);

	public List<ClientAdminForm> findAllClientAdmins();

	public TypedQuery<ClientAdmin> getCustByIdAndStatus();

	public TypedQuery<ClientAdmin> getClientAdminByStatusAndTradeFlag(Boolean tradeFlag);
	
	public TypedQuery<ClientAdmin> getAllClientAdmin();

}
