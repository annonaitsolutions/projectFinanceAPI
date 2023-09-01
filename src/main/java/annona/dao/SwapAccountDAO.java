package annona.dao;

import java.util.List;

import annona.domain.SwapAccount;

public interface SwapAccountDAO {
	
	/**
	 * Method to create swap account
	 */
	void createSwapAccount(SwapAccount swapAccount);
	
	/**
	 * Method to display swap accounts based on Status
	 */
	List<SwapAccount> getAllSwapAccountsByStatus(String status);
	
	/**
	 * Method to find Swap Account based on id
	 */
	SwapAccount findById(Long id);
	
	/**
	 * Method to Update Swap Account
	 */
	void updateSwapAccount(SwapAccount swapAccount);
	
	/**
	 * Method to get all Swap Accounts
	 */
	List<SwapAccount> getAllSwapAccounts();
	
	/**
	 * Method to find Swap account by User id
	 */
	SwapAccount findByEndUserId(Long id);
}

