package annona.form;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ComparisonForm {

	
	private String colourI1;
	
	private Date colourI;
	
	private Long colourDelayI;
	
	private Long colourAdvanceI;
	
	private String disputeRaised;
	
    private String colourJ1;
	
	private Date colourJ;
	
	private Long colourDelayJ;
	
	private Long colourAdvanceJ;
	
	private String disputeUpdate;
	
	private Date disputeDate;
	
	private int disputeDays;
	
	private Date statusDate;
	
	private int statusDays;
	
	
	public Long getColourDelayI() {
		return colourDelayI;
	}

	public void setColourDelayI(Long colourDelayI) {
		this.colourDelayI = colourDelayI;
	}

	public Long getColourAdvanceI() {
		return colourAdvanceI;
	}

	public void setColourAdvanceI(Long colourAdvanceI) {
		this.colourAdvanceI = colourAdvanceI;
	}

	public String getDisputeRaised() {
		return disputeRaised;
	}

	public void setDisputeRaised(String disputeRaised) {
		this.disputeRaised = disputeRaised;
	}

	public Date getColourI() {
		return colourI;
	}

	public void setColourI(Date colourI) {
		this.colourI = colourI;
	}

	public String getColourI1() {
		return colourI1;
	}

	public void setColourI1(String colourI1) {
		this.colourI1 = colourI1;
	}

	public String getColourJ1() {
		return colourJ1;
	}

	public void setColourJ1(String colourJ1) {
		this.colourJ1 = colourJ1;
	}

	public Date getColourJ() {
		return colourJ;
	}

	public void setColourJ(Date colourJ) {
		this.colourJ = colourJ;
	}

	public Long getColourDelayJ() {
		return colourDelayJ;
	}

	public void setColourDelayJ(Long colourDelayJ) {
		this.colourDelayJ = colourDelayJ;
	}

	public Long getColourAdvanceJ() {
		return colourAdvanceJ;
	}

	public void setColourAdvanceJ(Long colourAdvanceJ) {
		this.colourAdvanceJ = colourAdvanceJ;
	}

	public String getDisputeUpdate() {
		return disputeUpdate;
	}

	public void setDisputeUpdate(String disputeUpdate) {
		this.disputeUpdate = disputeUpdate;
	}

	public Date getDisputeDate() {
		return disputeDate;
	}

	public void setDisputeDate(Date disputeDate) {
		this.disputeDate = disputeDate;
	}

	public int getDisputeDays() {
		return disputeDays;
	}

	public void setDisputeDays(int disputeDays) {
		this.disputeDays = disputeDays;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public int getStatusDays() {
		return statusDays;
	}

	public void setStatusDays(int statusDays) {
		this.statusDays = statusDays;
	}

	
	
}
