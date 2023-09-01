package annona.services;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class DelayAdvDaysTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer days;

	private int advDays;

	private int delayDays;

	public void setDays(Integer days) {
		this.days = days;
	}

	public int getAdvDays() {
		return advDays;
	}

	public void setAdvDays(int advDays) {
		this.advDays = advDays;
	}

	public int getDelayDays() {
		return delayDays;
	}

	public void setDelayDays(int delayDays) {
		this.delayDays = delayDays;
	}

	public int doStartTag() throws JspException {
		try {
			if(days == null) {
				pageContext.setAttribute("advDays", "");
				pageContext.setAttribute("delayDays", "");
			}else {
				if(days > 0) {
					pageContext.setAttribute("advDays", days);
					pageContext.setAttribute("delayDays", 0);
				}else if(days < 0) {
					days = Math.abs(days);
					pageContext.setAttribute("advDays", 0);
					pageContext.setAttribute("delayDays", days);
				}else {
					pageContext.setAttribute("advDays", "On Time");
					pageContext.setAttribute("delayDays", "On Time");
				}
			}
		}catch(Exception ioe) {
			throw new JspException("Error: " + ioe.getMessage());
		}  
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE ;
	}



}
