package annona.dao;

import java.util.Collection;

import javax.persistence.TypedQuery;



import annona.domain.InvoiceUpload;
import annona.domain.PurchaseOrder;





public interface InvoiceUploadDAO {

	public void createUser(	InvoiceUpload invoiceupload);
	
	public TypedQuery<InvoiceUpload> findByName(String name);

	public Collection<InvoiceUpload> getList();

	public InvoiceUpload findId(Long id);

	public void update(	InvoiceUpload invoiceupload);

   public TypedQuery<InvoiceUpload> getByPending();
   
   public TypedQuery<InvoiceUpload> findPoKey(String poKey);



}

