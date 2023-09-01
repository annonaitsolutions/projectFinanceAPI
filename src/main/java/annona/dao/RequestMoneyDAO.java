package annona.dao;

import javax.persistence.TypedQuery;

import annona.domain.RequestMoney;

public interface RequestMoneyDAO {
	
	public void insertRequest(RequestMoney money);
	
	public RequestMoney getRequestMoneyId(Long id);
	
	public TypedQuery<RequestMoney> getRequestSentList(String customerName);
	
	public TypedQuery<RequestMoney> getRequestRecievedList(String customerName);

}
