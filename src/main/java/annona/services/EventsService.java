package annona.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import annona.dao.BuyerPODAO;
import annona.dao.InvoiceUploadDAO;
import annona.dao.MasterPlanDAO;
import annona.dao.PoUploadDAO;
import annona.dao.UploadDAO;
import annona.domain.BuyerDiffBankEvent;
import annona.domain.BuyerPO;
import annona.domain.BuyerSameBankEvent;
import annona.domain.Dispute;
import annona.domain.Invoice;
import annona.domain.InvoiceUpload;
import annona.domain.MasterPlan;
import annona.domain.PoUpload;
import annona.domain.PurchaseOrder;
import annona.domain.SellerDiffBankEvent;
import annona.domain.SellerSameBankEvent;
import annona.form.BuyerDiffBankEventForm;
import annona.form.BuyerSameBankEventForm;
import annona.form.ComparisonForm;
import annona.form.SellerDiffBankEventForm;
import annona.form.SellerSameBankEventForm;
import annona.utility.Constants;

@Service
public class EventsService {
	
	@Autowired
	MasterPlanDAO masterPlanDAO;
	
	@Autowired
	BuyerSameBankEventForm buyerSameBankEventForm;
	
	@Autowired
	BuyerDiffBankEventForm buyerDiffBankEventForm;
	
	@Autowired
	SellerSameBankEventForm sellerSameBankEventForm;
	
	@Autowired
	BuyerPODAO buyerPODAO;
	
	@Autowired
	UploadDAO uploadDAO;
	
	@Autowired
	SellerDiffBankEventForm sellerDiffBankEventForm;
	
	@Autowired
	PoUploadDAO poUploadDAO;
	
	@Autowired
	InvoiceUploadDAO invoiceUploadDAO;
	/**
	 * Method to populate Comparison form based on dispute for Same bank events
	 * @param disputesList
	 * @param buyerSameBankEvent
	 * @return
	 */
	public  List<ComparisonForm> populateFormBasedOnDisputes(List<Dispute> disputesList, 
			                                            BuyerSameBankEvent buyerSameBankEvent) {
		List<ComparisonForm> comparisonList = new ArrayList<ComparisonForm>();
		
		for (Dispute dispute : disputesList) {
			ComparisonForm comparisonFrm = new ComparisonForm();
			
			if(dispute.getDate() != null ) {
				if(buyerSameBankEvent.getDate11() != null) {
				comparisonFrm.setDisputeDays(DateService.getDaysBetweenTwoDates(
						dispute.getDate(),buyerSameBankEvent.getDate11()));
				}
				comparisonFrm.setDisputeDate(dispute.getDate());
				if(dispute.getGoodsSummarry() == null){
					dispute.setGoodsSummarry("Not Available");
				}
				comparisonFrm.setDisputeRaised("Damage Summary:"
						+ dispute.getGoodsSummarry() +  "\n Cost of Defect:"
						+ dispute.getAnswer2());
			}else {
				comparisonFrm.setDisputeRaised("No Records found");
			}
			
			if(dispute.getStatusDate() != null){
				if(buyerSameBankEvent.getDate11() != null) {
				comparisonFrm.setStatusDays(DateService.getDaysBetweenTwoDates(
						dispute.getStatusDate(),buyerSameBankEvent.getDate11()));
				}
				comparisonFrm.setStatusDate(dispute.getStatusDate());
				comparisonFrm.setDisputeUpdate("Dispute is "
						+ dispute.getDisputeStatus());
			}else {
				comparisonFrm.setDisputeUpdate("No Records found");
			}
			
		
			comparisonList.add(comparisonFrm);
		}
		return comparisonList;
	}
	/**
	 * Method to generate feedback for Same bank events
	 * @param buyerSameBankEventForm
	 * @param buyerSameBankEvent
	 * @param purchaseOrder
	 * @return
	 */
	public  BuyerSameBankEventForm generateFeedback(BuyerSameBankEventForm buyerSameBankEventForm,
			                       BuyerSameBankEvent buyerSameBankEvent, PurchaseOrder purchaseOrder) {
		if( buyerSameBankEvent.getDate2() !=null && buyerSameBankEvent.getDate11() != null) {
			buyerSameBankEventForm.setEstimatedDays(DateService.getDaysBetweenTwoDates(
													buyerSameBankEvent.getDate2(),buyerSameBankEvent.getDate11()));
	
	}else {
		buyerSameBankEventForm.setEstimatedDays(0);
	}
	
	if(purchaseOrder.getPurchaseDate() != null && purchaseOrder.getClosePoDate() != null) {
		buyerSameBankEventForm.setActualDays(DateService.getDaysBetweenTwoDates(
				purchaseOrder.getPurchaseDate(),purchaseOrder.getClosePoDate()));
	}else {
		buyerSameBankEventForm.setActualDays(0);
		buyerSameBankEventForm.setStatus(Constants.PENDING);
		buyerSameBankEventForm.setInterest(0f);
	}
	int delay = buyerSameBankEventForm.getEstimatedDays() - buyerSameBankEventForm.getActualDays();
					
		if (delay < 0) {

			delay = Math.abs(delay);
			
			buyerSameBankEventForm.setStatus("Transaction delayed by " + delay + " days");					


			MasterPlan masterPlan = masterPlanDAO.getMasterPlanByMasterKey(
					purchaseOrder.getMasterKey()).getSingleResult();
			if (masterPlan.getInterestType().equals("fixed")) {
				
				buyerSameBankEventForm.setInterest(((masterPlan.getBuyingCostSanc() * masterPlan.getRateOfInt() / 100) / 365) * delay);
				
			} else {					

				buyerSameBankEventForm.setInterest(((masterPlan.getBuyingCostSanc() * masterPlan.getCalPlrRate() / 100) / 365) * delay);

			}

		}
		else if (delay > 0) {	
			
			buyerSameBankEventForm.setStatus("Transaction advanced by " + delay	+ " days");
			buyerSameBankEventForm.setInterest(0f);
			
		} else {
			buyerSameBankEventForm.setStatus("Transaction done on time");
			buyerSameBankEventForm.setInterest(0f);
		}
		
		return buyerSameBankEventForm;

	}
	
	public BuyerSameBankEventForm populateBuyerSameBankFormBasedOnEvents(BuyerSameBankEvent buyerSameBankEvent, 
			                                                     PurchaseOrder purchaseOrder) {		
		
		if(purchaseOrder.getPurchaseDate() != null && buyerSameBankEvent.getDate2() != null) {
			buyerSameBankEventForm.setPoDays(DateService.getDaysBetweenTwoDates(
					purchaseOrder.getPurchaseDate(),buyerSameBankEvent.getDate2()));
		}
		if(purchaseOrder.getSentDate() != null && buyerSameBankEvent.getDate4() != null) {
			buyerSameBankEventForm.setSentDays(DateService.getDaysBetweenTwoDates(
					purchaseOrder.getSentDate(),buyerSameBankEvent.getDate4()));
		}

		/* Fifth Event */
		List<PoUpload> poUploads = poUploadDAO.getPoUploadByPOKey(buyerSameBankEvent.getPoKey());

		if (poUploads != null && poUploads.size() > 0) {

			PoUpload poUpload = poUploads.get(0);
			
			if(poUpload.getUploadDate() !=null && buyerSameBankEvent.getDate5() != null) {
				buyerSameBankEventForm.setUploadDays(DateService.getDaysBetweenTwoDates(
						poUpload.getUploadDate(),buyerSameBankEvent.getDate5()));
				buyerSameBankEventForm.setSupplierDate(poUpload.getUploadDate());
			}
		}
			
		/* Seventh Event */
		
		if(purchaseOrder.getForwardDate() != null && buyerSameBankEvent.getDate7() != null) {
			buyerSameBankEventForm.setForwardDays(DateService.getDaysBetweenTwoDates(
					purchaseOrder.getForwardDate(),buyerSameBankEvent.getDate7()));
		}


		/* Ninth Event */
		
		if(purchaseOrder.getPoPayDate() != null && buyerSameBankEvent.getDate9() != null) {
			buyerSameBankEventForm.setPoPaymentDays(DateService.getDaysBetweenTwoDates(
					purchaseOrder.getPoPayDate(),buyerSameBankEvent.getDate9()));
		}


		/* Tenth Event */
		
		if(purchaseOrder.getReceiveDate() != null && buyerSameBankEvent.getDate10() != null) {
			buyerSameBankEventForm.setReceiveDays(DateService.getDaysBetweenTwoDates(
					purchaseOrder.getReceiveDate(),buyerSameBankEvent.getDate10()));
		}	
		
		/* Eleventh Event */
		if(purchaseOrder.getClosePoDate() != null && buyerSameBankEvent.getDate11() != null) {
			buyerSameBankEventForm.setPoCloseDays(DateService.getDaysBetweenTwoDates(
					purchaseOrder.getClosePoDate(),buyerSameBankEvent.getDate11()));
		}
		
		return buyerSameBankEventForm;		
		
	}

	/**
	 * Populate Buyer Different bank  events
	 * @param buyerSameBankEvent
	 * @param purchaseOrder
	 * @return
	 */
	public BuyerDiffBankEventForm populateBuyerDiffBankFormBasedOnEvents(BuyerDiffBankEvent buyerDiffBankEvent, 
			PurchaseOrder purchaseOrder) {		

		if(purchaseOrder.getPurchaseDate() != null && buyerDiffBankEvent.getDate2() != null) {
			buyerDiffBankEventForm.setPoDays(DateService.getDaysBetweenTwoDates(
					purchaseOrder.getPurchaseDate(),buyerDiffBankEvent.getDate2()));
		}
		if(purchaseOrder.getSentDate() != null && buyerDiffBankEvent.getDate4() != null) {
			buyerDiffBankEventForm.setSentDays(DateService.getDaysBetweenTwoDates(
					purchaseOrder.getSentDate(),buyerDiffBankEvent.getDate4()));
		}

		/* Fifth Event */
		List<PoUpload> poUploads = poUploadDAO.getPoUploadByPOKey(buyerDiffBankEvent.getPoKey());

		if (poUploads != null && poUploads.size() > 0) {

			PoUpload poUpload = poUploads.get(0);

			if(poUpload.getUploadDate() !=null && buyerDiffBankEvent.getDate5() != null) {
				buyerDiffBankEventForm.setUploadDays(DateService.getDaysBetweenTwoDates(
						poUpload.getUploadDate(),buyerDiffBankEvent.getDate5()));
				buyerDiffBankEventForm.setSupplierDate(poUpload.getUploadDate());
			}
		}		

		/* Eleventh Event */

		if(purchaseOrder.getPoPayDate() != null && buyerDiffBankEvent.getDate11() != null) {
			buyerDiffBankEventForm.setPoPaymentDays(DateService.getDaysBetweenTwoDates(
					purchaseOrder.getPoPayDate(),buyerDiffBankEvent.getDate11()));
		}


		/* Twelfth Event */

		if(purchaseOrder.getReceiveDate() != null && buyerDiffBankEvent.getDate12() != null) {
			buyerDiffBankEventForm.setReceiveDays(DateService.getDaysBetweenTwoDates(
					purchaseOrder.getReceiveDate(),buyerDiffBankEvent.getDate12()));
		}	

		/* Thirteenth Event */
		if(purchaseOrder.getClosePoDate() != null && buyerDiffBankEvent.getDate13() != null) {
			buyerDiffBankEventForm.setPoCloseDays(DateService.getDaysBetweenTwoDates(
					purchaseOrder.getClosePoDate(),buyerDiffBankEvent.getDate13()));
		}

		return buyerDiffBankEventForm;		

	}
	/**
	 * Method to Populate Buyer Different bank form if disputes exist
	 * @param disputesList
	 * @param buyerDiffBankEvent
	 * @return
	 */
	public  List<ComparisonForm> populateDiffBankFormBasedOnDisputes(List<Dispute> disputesList, 
			BuyerDiffBankEvent buyerDiffBankEvent) {
		List<ComparisonForm> comparisonList = new ArrayList<ComparisonForm>();

		for (Dispute dispute : disputesList) {
			ComparisonForm comparisonFrm = new ComparisonForm();

			if(dispute.getDate() != null ) {
				if(buyerDiffBankEvent.getDate11() != null) {
					comparisonFrm.setDisputeDays(DateService.getDaysBetweenTwoDates(
							dispute.getDate(),buyerDiffBankEvent.getDate11()));
				}
				comparisonFrm.setDisputeDate(dispute.getDate());
				if(dispute.getGoodsSummarry() == null){
					dispute.setGoodsSummarry("Not Available");
				}
				comparisonFrm.setDisputeRaised("Damage Summary:"
						+ dispute.getGoodsSummarry() +"\n Cost of Defect:"
						+ dispute.getAnswer2());
			}else {
				comparisonFrm.setDisputeRaised("No Records found");
			}

			if(dispute.getStatusDate() != null){
				if(buyerDiffBankEvent.getDate11() != null) {
					comparisonFrm.setStatusDays(DateService.getDaysBetweenTwoDates(
							dispute.getStatusDate(),buyerDiffBankEvent.getDate11()));
				}
				comparisonFrm.setStatusDate(dispute.getStatusDate());
				comparisonFrm.setDisputeUpdate("Dispute is"
						+ dispute.getDisputeStatus());
			}else {
				comparisonFrm.setDisputeUpdate("No Records found");
			}


			comparisonList.add(comparisonFrm);
		}
		return comparisonList;
	}
	
	/**
	 * Method to generate feedback for Different bank events
	 * @param buyerSameBankEventForm
	 * @param buyerSameBankEvent
	 * @param purchaseOrder
	 * @return
	 */
	public  BuyerDiffBankEventForm generateDiffBankFeedback(BuyerDiffBankEventForm buyerDiffBankEventForm,
			                       BuyerDiffBankEvent buyerDiffBankEvent, PurchaseOrder purchaseOrder) {
		if( buyerDiffBankEvent.getDate2() !=null && buyerDiffBankEvent.getDate13() != null) {
			buyerDiffBankEventForm.setEstimatedDays(DateService.getDaysBetweenTwoDates(
					buyerDiffBankEvent.getDate2(),buyerDiffBankEvent.getDate13()));
	
	}else {
		buyerDiffBankEventForm.setEstimatedDays(0);
	}
	
	if(purchaseOrder.getPurchaseDate() != null && purchaseOrder.getClosePoDate() != null) {
		buyerDiffBankEventForm.setActualDays(DateService.getDaysBetweenTwoDates(
				purchaseOrder.getPurchaseDate(),purchaseOrder.getClosePoDate()));
	}else {
		buyerDiffBankEventForm.setActualDays(0);
		buyerDiffBankEventForm.setStatus(Constants.PENDING);
		buyerDiffBankEventForm.setInterest(0f);
	}
	int delay = buyerDiffBankEventForm.getEstimatedDays() - buyerDiffBankEventForm.getActualDays();
					
		if (delay < 0) {
			delay = Math.abs(delay);
			buyerDiffBankEventForm.setStatus("Transaction delayed by " + Math.abs(delay) + " days");					

			MasterPlan masterPlan = masterPlanDAO.getMasterPlanByMasterKey(
					purchaseOrder.getMasterKey()).getSingleResult();
			if (masterPlan.getInterestType().equals("fixed")) {
				
				buyerDiffBankEventForm.setInterest(((masterPlan.getBuyingCostSanc() * masterPlan.getRateOfInt() / 100) / 365) * delay);
				
			} else {					

				buyerDiffBankEventForm.setInterest(((masterPlan.getBuyingCostSanc() * masterPlan.getCalPlrRate() / 100) / 365) * delay);

			}

		}
		else if (delay > 0) {	
			
			buyerDiffBankEventForm.setStatus("Transaction advanced by " + delay	+ " days");
			buyerDiffBankEventForm.setInterest(0f);
			
		} else {
			buyerDiffBankEventForm.setStatus("Transaction done on time");
			buyerDiffBankEventForm.setInterest(0f);
		}
		
		return buyerDiffBankEventForm;

	}
	/**
	 * Method to populate SellerSameBankEventForm based on events
	 * @param sellerSameBankEvent
	 * @param invoice
	 * @return
	 */
	public SellerSameBankEventForm populateSellerSameBankFormBasedOnEvents(SellerSameBankEvent sellerSameBankEvent, 
			Invoice invoice) {	
		/* Second Event */

		List<BuyerPO> buyerPOs = buyerPODAO.getPoKey(sellerSameBankEvent.getInvoiceKey()).getResultList();
		if (buyerPOs != null && buyerPOs.size() > 0) {
			BuyerPO buyerPO = buyerPOs.get(0);				
			
			if(buyerPO.getPurchaseDate() != null && sellerSameBankEvent.getDate2() != null) {
				sellerSameBankEventForm.setPoDays(DateService.getDaysBetweenTwoDates(
						buyerPO.getPurchaseDate(),sellerSameBankEvent.getDate2()));
				sellerSameBankEventForm.setPurchaseDate(buyerPO.getPurchaseDate());
			}
		}
		
		/* Fourth Event */
		if(invoice.getSentDate() != null && sellerSameBankEvent.getDate4() != null) {
			sellerSameBankEventForm.setSentDays(DateService.getDaysBetweenTwoDates(
					invoice.getSentDate(),sellerSameBankEvent.getDate4()));
		}
		
		/* Fifth Event */

		List<InvoiceUpload> invoiceUploads = invoiceUploadDAO.findPoKey(
				sellerSameBankEvent.getInvoiceKey()).getResultList();

		if (invoiceUploads != null && invoiceUploads.size() > 0) {

			InvoiceUpload invoiceUpload = invoiceUploads.get(0);
			
			if(invoiceUpload.getUploadDate() != null && sellerSameBankEvent.getDate5() != null) {
				sellerSameBankEventForm.setUploadDays(DateService.getDaysBetweenTwoDates(
						invoiceUpload.getUploadDate(),sellerSameBankEvent.getDate5()));
			}
			sellerSameBankEventForm.setInvoiceUpload(invoiceUpload);;

		}
		/* Tenth Event */
		if(invoice.getReceiveDate() != null && sellerSameBankEvent.getDate10() != null) {
			sellerSameBankEventForm.setReceiveDays(DateService.getDaysBetweenTwoDates(
					invoice.getReceiveDate(),sellerSameBankEvent.getDate10()));
		}
		/* Thirteenth Event */
		if(invoice.getCloseDate() != null && sellerSameBankEvent.getDate13() != null) {
			sellerSameBankEventForm.setCloseDays(DateService.getDaysBetweenTwoDates(
					invoice.getCloseDate(),sellerSameBankEvent.getDate13()));
		}
		
		return sellerSameBankEventForm;
	}
	/**
	 * Method to generate feedback form for same bank events
	 * @param sellerSameBankEventForm
	 * @param sellerSameBankEvent
	 * @param invoice
	 * @return
	 */
	public  SellerSameBankEventForm generateSellerSameBankFeedback(SellerSameBankEventForm sellerSameBankEventForm,
			SellerSameBankEvent sellerSameBankEvent, Invoice invoice) {
		if( sellerSameBankEvent.getDate2() !=null && sellerSameBankEvent.getDate13() != null) {
			sellerSameBankEventForm.setEstimatedDays(DateService.getDaysBetweenTwoDates(
					sellerSameBankEvent.getDate2(),sellerSameBankEvent.getDate13()));

		}else {
			sellerSameBankEventForm.setEstimatedDays(0);
		}

		if(sellerSameBankEventForm.getPurchaseDate() != null && invoice.getCloseDate() != null) {
			sellerSameBankEventForm.setActualDays(DateService.getDaysBetweenTwoDates(
					sellerSameBankEventForm.getPurchaseDate(),invoice.getCloseDate()));
		}else {
			sellerSameBankEventForm.setActualDays(0);
			sellerSameBankEventForm.setStatus(Constants.PENDING);
			
		}
		int delay = sellerSameBankEventForm.getEstimatedDays() - sellerSameBankEventForm.getActualDays();

		if (delay < 0) {
			delay = Math.abs(delay);
			sellerSameBankEventForm.setStatus("Transaction delayed by " + Math.abs(delay) + " days");	
		}
		else if (delay > 0) {
			sellerSameBankEventForm.setStatus("Transaction advanced by " + delay	+ " days");			

		} else {
			sellerSameBankEventForm.setStatus("Transaction done on time");			
		}

		return sellerSameBankEventForm;

	}
	/**
	 * Method to populate SellerDiffBank form based on events
	 * @param sellerDiffBankEvent
	 * @param invoice
	 * @return
	 */
	public SellerDiffBankEventForm populateSellerDiffBankFormBasedOnEvents(SellerDiffBankEvent sellerDiffBankEvent, 
			Invoice invoice) {	
		/* Second Event */

		List<BuyerPO> buyerPOs = buyerPODAO.getPoKey(
				sellerDiffBankEvent.getInvoiceKey()).getResultList();
		if (buyerPOs != null && buyerPOs.size() > 0) {
			BuyerPO buyerPO = buyerPOs.get(0);
			
			if(buyerPO.getPurchaseDate() != null && sellerDiffBankEvent.getDate2() != null) {
				sellerDiffBankEventForm.setPoDays(DateService.getDaysBetweenTwoDates(
						buyerPO.getPurchaseDate(),sellerDiffBankEvent.getDate2()));
				sellerDiffBankEventForm.setPurchaseDate(buyerPO.getPurchaseDate());
			}
		}

		/* Fourth Event */
		if(invoice.getSentDate() != null && sellerDiffBankEvent.getDate4() != null) {
			sellerDiffBankEventForm.setSentDays(DateService.getDaysBetweenTwoDates(
					invoice.getSentDate(),sellerDiffBankEvent.getDate4()));
		}
		
		/* Fifth Event */

		List<InvoiceUpload> invoiceUploads = invoiceUploadDAO.findPoKey(
				sellerDiffBankEvent.getInvoiceKey()).getResultList();

		if (invoiceUploads != null && invoiceUploads.size() > 0) {
			InvoiceUpload invoiceUpload = invoiceUploads.get(0);
			
			if(invoiceUpload.getUploadDate() != null && sellerDiffBankEvent.getDate5() != null) {
				sellerDiffBankEventForm.setUploadDays(DateService.getDaysBetweenTwoDates(
						invoiceUpload.getUploadDate(),sellerDiffBankEvent.getDate5()));
			}
			sellerDiffBankEventForm.setInvoiceUpload(invoiceUpload);
			}

		/* Twelfth Event */
		if(invoice.getReceiveDate() != null && sellerDiffBankEvent.getDate12() != null) {
			sellerDiffBankEventForm.setReceiveDays(DateService.getDaysBetweenTwoDates(
					invoice.getReceiveDate(),sellerDiffBankEvent.getDate12()));
		}
		/* Sixteen Event */

		if(invoice.getCloseDate() != null && sellerDiffBankEvent.getDate16() != null) {
			sellerDiffBankEventForm.setCloseDays(DateService.getDaysBetweenTwoDates(
					invoice.getCloseDate(),sellerDiffBankEvent.getDate16()));
		}
		return sellerDiffBankEventForm;
	}
	
	public  SellerDiffBankEventForm generateSellerDiffBankFeedback(SellerDiffBankEventForm sellerDiffBankEventForm,
			SellerDiffBankEvent sellerDiffBankEvent, Invoice invoice) {
		if( sellerDiffBankEvent.getDate2() !=null && sellerDiffBankEvent.getDate16() != null) {
			sellerSameBankEventForm.setEstimatedDays(DateService.getDaysBetweenTwoDates(
					sellerDiffBankEvent.getDate2(),sellerDiffBankEvent.getDate16()));

		}else {
			sellerDiffBankEventForm.setEstimatedDays(0);
		}

		if(sellerDiffBankEventForm.getPurchaseDate() != null && invoice.getCloseDate() != null) {
			sellerDiffBankEventForm.setActualDays(DateService.getDaysBetweenTwoDates(
					sellerDiffBankEventForm.getPurchaseDate(),invoice.getCloseDate()));
		}else {
			sellerDiffBankEventForm.setActualDays(0);
			sellerDiffBankEventForm.setStatus(Constants.PENDING);
			
		}
		int delay = sellerDiffBankEventForm.getEstimatedDays() - sellerDiffBankEventForm.getActualDays();

		if (delay < 0) {
			delay = Math.abs(delay);
			sellerDiffBankEventForm.setStatus("Transaction delayed by " + Math.abs(delay) + " days");	
		}
		else if (delay > 0) {
			sellerDiffBankEventForm.setStatus("Transaction advanced by " + delay	+ " days");			

		} else {
			sellerDiffBankEventForm.setStatus("Transaction done on time");			
		}

		

		return sellerDiffBankEventForm;
	}
}
