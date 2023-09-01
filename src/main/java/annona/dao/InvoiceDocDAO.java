package annona.dao;

import javax.persistence.TypedQuery;

import annona.domain.InvoiceDoc;

public interface InvoiceDocDAO {

	public InvoiceDoc createDoc(InvoiceDoc invoiceDoc);
	  
	  public TypedQuery<InvoiceDoc> findPoKey(String invoiceKey);
}
