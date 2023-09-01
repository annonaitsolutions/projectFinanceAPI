package annona.dao;

import javax.persistence.TypedQuery;

import annona.domain.LetterOfCredit;

public interface LetterOfCreditDAO {
	
	public TypedQuery<LetterOfCredit> getList();
	
	public void updateLetterOfCredit(LetterOfCredit credit);
	
	public  LetterOfCredit getByLetterOfCreditId(Long id);
	
	public void createLetterOfCredit(LetterOfCredit credit);
	
	public TypedQuery<LetterOfCredit> getLCListByPoKey(String poKey);

}
