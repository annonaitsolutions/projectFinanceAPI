package annona.trade.dao;

import java.util.Collection;

import javax.persistence.TypedQuery;

import annona.domain.UploadedFile;
import annona.trade.domain.TUploadedFile;



public interface TUploadDAO {

	public void createUser(TUploadedFile uploadFile);
	
	public TypedQuery<TUploadedFile> findByName(String name);

	public Collection<TUploadedFile> getList();

	public UploadedFile findId(Long id);

	public void update(TUploadedFile uploadFile);

   public TypedQuery<TUploadedFile> getByPending();



}

