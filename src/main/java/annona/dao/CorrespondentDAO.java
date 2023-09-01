package annona.dao;

import javax.persistence.TypedQuery;

import annona.domain.Correspondent;

public interface CorrespondentDAO {
	
	public TypedQuery<Correspondent> getList();
	
	public void updateCorrespondent(Correspondent correspondent);
	
	public  Correspondent getByCorrespondentId(Long id);
	
	public void createCorrespondent(Correspondent correspondent);
	
	public TypedQuery<Correspondent> getCorrespondenttByPoKey(String poKey) ;

}
