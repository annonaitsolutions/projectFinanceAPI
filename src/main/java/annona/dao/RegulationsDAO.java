package annona.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.TypedQuery;

import annona.domain.Regulation;
import annona.domain.Transaction;

public interface RegulationsDAO {

	public void createUser(Regulation regulations);

	public void insertTransaction(Transaction transaction);

	public TypedQuery<Regulation> findByCountry(String country);

	public Collection<Regulation> getList();

	public Regulation findId(Long id);

	public void update(Regulation regulation);
	
	/**
	 * Method to get Regulations list based on country and category
	 */
	List<Regulation> getRegulationsByCountryNCategory(String country, String category);

}
