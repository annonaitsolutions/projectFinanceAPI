package annona.services;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import annona.domain.PurchaseOrder;



public class ListService {
	
	/**
	 * Method to remove duplicates from PO list based on transaction id
	 * @param list
	 * @return
	 */
	public static List<PurchaseOrder> removeDuplicatesForMultipplePayment(List<PurchaseOrder> list) {

		Set<PurchaseOrder> set = new TreeSet<PurchaseOrder>(
				new Comparator<PurchaseOrder>() {
					@Override
					public int compare(PurchaseOrder o1, PurchaseOrder o2) {

						if (o1.getTransactionId().equals(o2.getTransactionId())) {
							return 0;
						} else {
							return 1;
						}
					}
				});
		
		set.addAll(list);	
		list.clear();
		list.addAll(set);  
		
		return list;
	}
}
