package annona.dao;

import java.util.Collection;

import javax.persistence.TypedQuery;

import annona.domain.VendorUploadedFile;

public interface VendorUploadDAO {

	public void createUser(VendorUploadedFile vendorUploadedFile);
	
	public TypedQuery<VendorUploadedFile> findByName(String name);

	public Collection<VendorUploadedFile> getList();

	public VendorUploadedFile findId(Long id);

	public void update(VendorUploadedFile vendorUploadedFile);

   public TypedQuery<VendorUploadedFile> getByPending();

   public TypedQuery<VendorUploadedFile> findPokey(String poKey) ;

}

