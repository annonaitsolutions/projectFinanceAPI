package annona.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.TypedQuery;

import annona.domain.PoUpload;





public interface PoUploadDAO {

	public void createUser(	PoUpload poupload);
	
	public TypedQuery<PoUpload> findByName(String name);

	public Collection<PoUpload> getList();

	public PoUpload findId(Long id);

	public void update(	PoUpload poupload);

   public TypedQuery<PoUpload> getByPending();
   
   /**
	 * Method to get PoUpload based on poKey
	 * @param poKey
	 * @return
	 */
	List<PoUpload> getPoUploadByPOKey(String poKey);



}

