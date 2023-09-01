package annona.dao;

import java.util.Collection;

import javax.persistence.TypedQuery;

import annona.domain.UploadedFile;



public interface UploadDAO {

	public void createUser(UploadedFile uploadFile);
	
	public TypedQuery<UploadedFile> findByName(String name);

	public Collection<UploadedFile> getList();

	public UploadedFile findId(Long id);

	public void update(UploadedFile uploadFile);

   public TypedQuery<UploadedFile> getByPending();
   
   public TypedQuery<UploadedFile> findPokey(String poKey);



}

