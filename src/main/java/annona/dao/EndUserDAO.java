package annona.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.TypedQuery;

import annona.domain.EndUser;
import annona.domain.Transaction;

public interface EndUserDAO {

	public void mergeUser(EndUser endUser);

	public TypedQuery<EndUser> findByUsername(String username);

	public TypedQuery<EndUser> findByUsernameAndEmail(String username,
			String email);
	
	public TypedQuery<EndUser> getAllUsers();

	public TypedQuery<EndUser> getByBankEmp();

	public TypedQuery<EndUser> getByRole();

	public TypedQuery<EndUser> getByRoleList();

	public EndUser createUser(EndUser user);

	public EndUser findId(Long id);

	public EndUser GetUser(String userName);

	public void update(EndUser endUser);

	public TypedQuery<EndUser> getByApprovalMng();

	public Collection<EndUser> getList();

	public EndUser getByApprovalId(Long id);

	public void Transaction(Transaction transcation);
	
	public TypedQuery<EndUser> getByBankDetails();
	
	/**
	 * Method to get Users for Blocking/Unblocking
	 * @return
	 */
	List<EndUser> getUsersForBlockUnblock();
	
	/**
	 * Method to display USers list for Approval Block/Unblock
	 * @return
	 */
	List<EndUser> getUsersForBlockUnblockApproval();
	
	/**
	 * Method to display BankEmp,ApprovalMng to Block/Unblock/Renew
	 * @return
	 */
	List<EndUser> getUsersForBlockUnblockRenew();
	
	/**
	 * Method to get all users except Admin
	 */
	List<EndUser> getUsersExceptAdmin();

}
