package annona.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.Invoice;
import annona.domain.SalesOrder;
import annona.domain.SalesOrderDAO;
import annona.form.SalesOrderForm;
@Repository
public class SalesOrderDAOImpl implements SalesOrderDAO {

	@PersistenceContext
	private EntityManager em;
	
	
	@Transactional
	public void insertSalesOrder (SalesOrder  salesOrder ) {

		em.persist(salesOrder);

	}
	
	public TypedQuery<SalesOrder> getSalesOrderByStatus() {
		TypedQuery<SalesOrder> q = em.createQuery(
				"SELECT o FROM SalesOrder o where o.status='Pending'",
				SalesOrder.class);

		return q;
	}
	
	@Override
	public SalesOrder getBySalesOrderId(Long id) {
		if (id == null)
			return null;
		return em.find(SalesOrder.class, id);
	}
	
	@Transactional
	public void updateSalesOrder(SalesOrder salesOrder) {

		em.merge(salesOrder);
		em.flush();

	}
	
	@Override
	public TypedQuery<SalesOrder> getSalesOrderByCustomer(String buyerName){
		{
			if (buyerName == null || buyerName.length() == 0)
				throw new IllegalArgumentException("The username argument is required");

			TypedQuery<SalesOrder> q = em.createQuery(
					"SELECT o FROM SalesOrder AS o WHERE o.buyerName = :buyerName and o.status='Approved' and o.goodsStatus='Send' ", SalesOrder.class);
			q.setParameter("buyerName", buyerName);
			return q;
		}
	}

	@Override
	public TypedQuery<SalesOrder> getSalesOrderByCustomerHeadName(String customerHead){
		{
			if (customerHead == null || customerHead.length() == 0)
				throw new IllegalArgumentException("The customerHead argument is required");

			TypedQuery<SalesOrder> q = em.createQuery(
					"SELECT o FROM SalesOrder AS o WHERE o.customerHeadName = :customerHead and o.status='Approved' and o.goodsStatus='Send' ", SalesOrder.class);
			q.setParameter("customerHead", customerHead);
			return q;
		}
	}
	
	
	@Override
public List<SalesOrderForm>  getSalesOrderByCustomerAndMasterKey(String customerHeadName ) {
		
		Query query = em
				.createQuery("SELECT T1.id, T1.customerName, T1.goodsCategory, T1.goods, T1.quantity, T1.amount, T1.tenure,T2.masterKey,T2.product FROM SalesOrder T1, MasterPlan T2  WHERE T1.masterKey= T2.masterKey and T1.customerHeadName =:customerHeadName and T1.status='Approved' and T1.goodsStatus='Send'" );
		query.setParameter("customerHeadName", customerHeadName);
		List<Object[]> salesOrderList = query.getResultList();
		List<SalesOrderForm> autoSalesOrder = new ArrayList<SalesOrderForm>();
		
		if (salesOrderList != null) {
			for (int i = 0; i < salesOrderList.size(); i++) {
				SalesOrderForm salesOrderForm = new SalesOrderForm();
				salesOrderForm.setId(Long.parseLong(salesOrderList.get(i)[0].toString()));
				salesOrderForm.setCustomerName(salesOrderList.get(i)[1].toString());
				salesOrderForm.setGoodsCategory(salesOrderList.get(i)[2].toString());
				salesOrderForm.setGoods((salesOrderList.get(i)[3].toString()));
				salesOrderForm.setQuantity(salesOrderList.get(i)[4].toString());
				salesOrderForm.setAmount(Float.valueOf(salesOrderList.get(i)[5].toString()));
				salesOrderForm.setTenure(Integer.parseInt(salesOrderList.get(i)[6].toString()));
				salesOrderForm.setMasterKey(salesOrderList.get(i)[7].toString());
				salesOrderForm.setProduct(salesOrderList.get(i)[8].toString());
				autoSalesOrder.add(salesOrderForm);
			}
			return autoSalesOrder;
		} else {
			return null;
		}
	}
	
	@Override
	public TypedQuery<SalesOrder> getList() {

		TypedQuery<SalesOrder> q = em.createQuery(
				"SELECT o FROM SalesOrder o ", SalesOrder.class);
		return q;

	}
}
