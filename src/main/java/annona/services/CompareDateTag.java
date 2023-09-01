package annona.services;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class CompareDateTag extends TagSupport{

	private Date date;


	public void setDate(Date date) {
		this.date = date;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 
	 */
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			Date today = DateService.getCurrentDate();
			
			if(date == null) {
				out.print("black");
			}else {
				Date givenDate = DateService.getDate(date);
				if(givenDate.compareTo(today) <= 0) {
					out.print("green");
				}else if(givenDate.compareTo(today) > 0) {
					out.print("red");
				}else {
					out.print("black");
				}
			}

		} catch(IOException ioe) {
			throw new JspException("Error: " + ioe.getMessage());
		}       
		return SKIP_BODY;
	}


	public int doEndTag() throws JspException {
		return EVAL_PAGE ;
	}

}
