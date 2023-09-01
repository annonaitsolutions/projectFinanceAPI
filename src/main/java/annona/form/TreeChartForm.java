package annona.form;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import annona.domain.Invoice;
import annona.domain.LimitBurst;
import annona.domain.PoUpload;
import annona.domain.PurchaseOrder;
/**
 * Class used to populate and display Tree chart
 * @author goutame
 *
 */
@Component
public class TreeChartForm {
	
	private Map<PurchaseOrder, TreeChartForm> poList;
	
	private List<LimitBurst> limitBurstList;
	
	private List<PoUpload> poUploadList;
	
	private List<Invoice> invoiceList;

	public Map<PurchaseOrder, TreeChartForm> getPoList() {
		return poList;
	}

	public void setPoList(Map<PurchaseOrder, TreeChartForm> poList) {
		this.poList = poList;
	}

	public List<LimitBurst> getLimitBurstList() {
		return limitBurstList;
	}

	public void setLimitBurstList(List<LimitBurst> limitBurstList) {
		this.limitBurstList = limitBurstList;
	}

	public List<PoUpload> getPoUploadList() {
		return poUploadList;
	}

	public void setPoUploadList(List<PoUpload> poUploadList) {
		this.poUploadList = poUploadList;
	}

	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}	
}
