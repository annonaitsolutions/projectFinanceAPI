package annona.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import annona.dao.EndUserDAO;
import annona.domain.EndUser;
import annona.form.EndUserForm;
import annona.form.LoginForm;
import annona.services.DateService;

@Controller
@RequestMapping("/auth")
public class LoginLogoutController {

	@Autowired
	EndUserDAO endUserDAOImpl;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	DateService dateService;

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	protected static Logger logger = Logger.getLogger("controller");

	@RequestMapping("/login")
	public String login(
			@RequestParam(value = "error", defaultValue = "0") Short error,
			ModelMap model, HttpServletRequest request,
			@ModelAttribute LoginForm loginForm, RedirectAttributes attributes) {
		logger.debug("Received request to show login page");
	
		if (error == 1) {
			model.put("error",
					"You have entered an invalid username or password!");
		}else if(error == 2) {
			model.put("error",
					"Your account is blocked!!! Please contact Admin");
		}else if(error == 3) {
			model.put("error","Your account is expired!!! Please contact Admin");
		}

		return "loginPage";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String getlogout() {
		logger.debug("Received request to show denied page");

		// This will resolve to /WEB-INF/jsp/deniedpage.jsp
		return "loginPage";
	}

	@RequestMapping(value = "/denied")
	public String denied() {
		logger.debug("Received request to show Denied page");
		return "access/denied";
	}

	@RequestMapping(value = "/logout")
	public String logoutSuccess() {

		return "loginPage";
	}

	@RequestMapping(value = "/login-alert", method = RequestMethod.GET)
	public String getLoginALertPage(ModelMap model) {

		logger.debug("Received request to show login alert page");
		model.put("error",
				"You can only log-in once! Either you wait for you session to expire "
						+ "or clear your browser's cache "
						+ "or manually log-out!");

		return "login-alert-page";
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public ModelAndView forgotPassword(ModelMap model,
			@ModelAttribute EndUserForm endUserForm) {

		model.put("endUserForm", endUserForm);

		return new ModelAndView("forgotPassword", "model", model);

	}

	@RequestMapping(value = "/checkForgotPassword", method = RequestMethod.POST)
	public ModelAndView checkForgotPassword(
			@ModelAttribute EndUserForm endUserForm,
			RedirectAttributes attributes, ModelMap model) {

		ModelAndView mav = new ModelAndView();

		List<EndUser> endUser = endUserDAOImpl.findByUsernameAndEmail(
				endUserForm.getUserName(), endUserForm.getEmail())
				.getResultList();

		if (endUser.size() == 0)

		{

			attributes
					.addFlashAttribute("success", "Incorrect details entered");

			mav = new ModelAndView("redirect:forgotPassword");

		} else {

			endUserForm.setId(endUser.get(0).getId());

			endUserForm.setTransactionId(endUser.get(0).getTransactionId());

			model.put("endUserForm", endUserForm);

			mav = new ModelAndView("newPassword", "model", model);

		}

		return mav;
	}

	@RequestMapping(value = "/newForgotPassword", method = RequestMethod.POST)
	public ModelAndView newForgotPassword(ModelMap model,
			@ModelAttribute EndUserForm endUserForm, BindingResult result,
			RedirectAttributes attributes) {

		EndUser endUser = endUserDAOImpl.findId(endUserForm.getId());

		endUser.setId(endUserForm.getId());

		endUser.setPassword(endUserForm.getNewPassword());
		endUser.setTransactionId(endUserForm.getTransactionId());

		endUserDAOImpl.update(endUser);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("paswwordChanged", "model", model);

	}

	/**
	 * Method to display Change Password screen
	 * @param model
	 * @param endUserForm
	 * @return
	 */
	@RequestMapping(value = "/loginChangePassword", method = RequestMethod.GET)
	public ModelAndView getLoginChangePassword(ModelMap model,
											   @RequestParam Long id,
			                                   @ModelAttribute EndUserForm endUserForm) {		
		endUserForm.setId(id);
		model.put("endUserForm", endUserForm);
		return new ModelAndView("loginChangePassword", "model", model);
	}
	
	/**
	 * Method to update EndUser table with Password expiry date and New password
	 * @param model
	 * @param endUserForm
	 * @param result
	 * @param attributes
	 * @return redirect to Login Page
	 */
	@RequestMapping(value = "/updateLoginChangePwd", method = RequestMethod.POST)
	public String updateLoginChangePwd(ModelMap model,
			@ModelAttribute EndUserForm endUserForm, BindingResult result,
			RedirectAttributes attributes) {

		EndUser endUser = endUserDAOImpl.findId(endUserForm.getId());		
		if(!endUser.getPassword().equals(endUserForm.getPassword())) {
			attributes.addFlashAttribute("error","Old Password Incorrect");
			return "redirect:/auth/loginChangePassword?id="+endUser.getId();
		}else if(endUser.getPassword().equals(endUserForm.getNewPassword())) {
			attributes.addFlashAttribute("error","Old Password and New Password cannot be same");
			return "redirect:/auth/loginChangePassword?id="+endUser.getId();
		}
		endUser.setPassword(endUserForm.getNewPassword());		
		endUser.setPwdExpiryDate(dateService.generatePasswordExpiryDate());
		endUser.setPasswordFlag(1);
		
		endUserDAOImpl.update(endUser);	

		return "redirect:login";

	}

}
