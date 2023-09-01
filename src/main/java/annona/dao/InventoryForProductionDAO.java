package annona.dao;

import annona.domain.Inventory;
import annona.domain.InventoryForProduction;

import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

public interface InventoryForProductionDAO {
	
	public void insertInventoryForProduction(InventoryForProduction inventoryForProduction);
	
	public InventoryForProduction getByInventoryForProductionId(Long id);
	
	public void updateInventoryForProduction(InventoryForProduction inventoryForProduction);

	public Collection<InventoryForProduction> getList();

}
