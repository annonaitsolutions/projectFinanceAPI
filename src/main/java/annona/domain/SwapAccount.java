package annona.domain;
/**
 * Class to save details of swap account
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Configurable
public class SwapAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	private String oldUser;
	
	private String oldPassword;
	
	private String oldEmail;
	
	private String oldContactNo;
	
	private String newUser;
	
	private String newPassword;
	
	private String newEmail;
	
	private String newContactNo;	
	
	private String status;
	
	private Long endUserId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getOldUser() {
		return oldUser;
	}

	public void setOldUser(String oldUser) {
		this.oldUser = oldUser;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getOldEmail() {
		return oldEmail;
	}

	public void setOldEmail(String oldEmail) {
		this.oldEmail = oldEmail;
	}
	
	public String getOldContactNo() {
		return oldContactNo;
	}

	public void setOldContactNo(String oldContactNo) {
		this.oldContactNo = oldContactNo;
	}

	public String getNewUser() {
		return newUser;
	}

	public void setNewUser(String newUser) {
		this.newUser = newUser;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	public String getNewContactNo() {
		return newContactNo;
	}

	public void setNewContactNo(String newContactNo) {
		this.newContactNo = newContactNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getEndUserId() {
		return endUserId;
	}

	public void setEndUserId(Long endUserId) {
		this.endUserId = endUserId;
	}
}
