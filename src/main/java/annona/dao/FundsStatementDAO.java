package annona.dao;

import javax.persistence.TypedQuery;

import annona.domain.FundsStatement;

public interface FundsStatementDAO {
	
	public FundsStatement getByFundsId(Long id) ;
	
	public void updateStatement(FundsStatement statement);
	
	public void insertStatement(FundsStatement statement);
	
	public TypedQuery<FundsStatement> getStatementList(String username);

}
