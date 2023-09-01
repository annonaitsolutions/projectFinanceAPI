package annona.dao;

import javax.persistence.TypedQuery;

import annona.domain.PurchaseDoc;

public interface PurchaseDocDAO {

	  public PurchaseDoc createDoc(PurchaseDoc purchaseDoc);
	  
	  public TypedQuery<PurchaseDoc> findPoKey(String poKey);
}
