package annona.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.theme.CookieThemeResolver;

import annona.dao.DisputeDAO;
import annona.dao.EndUserDAO;
import annona.dao.InvoiceDAO;
import annona.dao.TransactionDAO;
import annona.domain.Dispute;
import annona.domain.EndUser;
import annona.domain.Invoice;
import annona.domain.PurchaseOrder;
import annona.domain.Transaction;
import annona.form.DisputeForm;
import annona.form.EndUserForm;
import annona.form.InvoiceForm;
import annona.services.DateService;
import annona.services.ImageService;
import annona.services.UploadService;

@Controller
@RequestMapping("/wareHouseMng")
public class WareHouseMngController {
	
	@Autowired
	EndUserDAO endUserDAOImpl;
	
	@Autowired
	EndUserForm endUserForm;
	
	@Autowired
	InvoiceDAO invoiceDAO;
	
	@Autowired
	UploadService uploadService;

	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	TransactionDAO transcationDAOImpl;
	
	@Autowired
	DisputeDAO disputeDAO;
	
	@Autowired
	DisputeForm disputeForm;
	
	
	CookieThemeResolver themeResolver = new CookieThemeResolver();

	private ServletContext servletContext;

	// Used to display log messages
	protected Logger log = Logger.getLogger(AdminController.class.getName());

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	private String getCurrentLoggedUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();

	}

	@ModelAttribute("requestCurrentUser")
	public EndUser getUserDetails() {
		EndUser enUser = getCurrentLoggedUserDetails();
		if (enUser.getImageName() != null) {
			String type = ImageService.getImageType(enUser.getImageName());

			String url = "data:image/" + type + ";base64,"
					+ Base64.encodeBase64String(enUser.getImage());
			enUser.setImageName(url);
		}

		return enUser;
	}

	private EndUser getCurrentLoggedUserDetails() {

		EndUser endUser = endUserDAOImpl.findByUsername(
				getCurrentLoggedUserName()).getSingleResult();

		return endUser;

	}

	@RequestMapping(value = "/themeChange", method = RequestMethod.GET)
	public String getThemeChange(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		log.info("Received request for theme change");

		EndUser endUser = endUserDAOImpl.findByUsername(
				getCurrentLoggedUserName()).getSingleResult();
		if (endUser.getTheme() == null)
			themeResolver.setThemeName(request, response, "themeBlue");
		if (!endUser.getTheme().equalsIgnoreCase(request.getParameter("theme"))) {

			if (request.getParameter("theme").equalsIgnoreCase("themeBlue")) {
				themeResolver.setThemeName(request, response, "themeBlue");
			} else if (request.getParameter("theme").equalsIgnoreCase(
					"themeGray")) {
				themeResolver.setThemeName(request, response, "themeGray");
			} else if (request.getParameter("theme").equalsIgnoreCase(
					"themeOrange")) {
				themeResolver.setThemeName(request, response, "themeOrange");
			} else if (request.getParameter("theme").equalsIgnoreCase(
					"themeRed")) {
				themeResolver.setThemeName(request, response, "themeRed");
			}

			endUser.setTheme(request.getParameter("theme"));
			endUserDAOImpl.mergeUser(endUser);
		} else
			themeResolver.setThemeName(request, response, endUser.getTheme());
		return "redirect:wareHouseMngPage";
	}

	@RequestMapping(value = "/getLocaleLang", method = RequestMethod.GET)
	public String getLocaleLang(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		log.info("Received request for locale change");
		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		LocaleResolver localeResolver = new CookieLocaleResolver();
		Locale locale = new Locale(request.getParameter("lang"));
		localeResolver.setLocale(request, response, locale);
		user.setPrefferedLanguage(request.getParameter("lang"));

		endUserDAOImpl.mergeUser(user);

		model.put("user", user);

		return "redirect:wareHouseMngPage";
	}

	@RequestMapping(value = "/wareHouseMngPage", method = RequestMethod.GET)
	public ModelAndView showVendorDashBoard(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("wareHouseMngPage", "model", model);

	}

	@RequestMapping(value = "/editWareHouseMngProfile", method = RequestMethod.GET)
	public ModelAndView editAdminProfile(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser userProfile = endUserDAOImpl.findId(id);
		if (userProfile.getImageName() != null) {
			String type = ImageService.getImageType(userProfile.getImageName());

			String url = "data:image/" + type + ";base64,"
					+ Base64.encodeBase64String(userProfile.getImage());
			userProfile.setImageName(url);

			endUserForm.setImageName(url);
		}
		endUserForm.setId(userProfile.getId());
		endUserForm.setDisplayName(userProfile.getDisplayName());
		endUserForm.setUserName(userProfile.getUserName());
		endUserForm.setAltContactNo(userProfile.getAltContactNo());
		endUserForm.setAltEmail(userProfile.getAltEmail());
		endUserForm.setContactNo(userProfile.getContactNo());
		endUserForm.setEmail(userProfile.getEmail());

		endUserForm.setPassword(userProfile.getPassword());
		endUserForm.setTransactionId(userProfile.getTransactionId());

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("editWareHouseMngProfile", "model", model);

	}

	@RequestMapping(value = "/updateImageForWareHouseMngProfile", method = RequestMethod.POST)
	public String updateImageForProfile(ModelMap model,
			@ModelAttribute EndUserForm endUserForm) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		try {
			EndUser userProfile = endUserDAOImpl.findId(endUserForm.getId());
			userProfile.setImage(endUserForm.getFile().getBytes());
			userProfile.setImageName(endUserForm.getFile()
					.getOriginalFilename());
			endUserDAOImpl.update(userProfile);

		} catch (Exception e) {
			e.getMessage();
		}
		return "redirect:editWareHouseMngProfile?id=" + endUserForm.getId();
	}

	@RequestMapping(value = "/confirmEditWareHouseMngProfile", method = RequestMethod.POST)
	public ModelAndView confirmEditAdminProfile(ModelMap model,
			@ModelAttribute EndUserForm endUserForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("confirmEditWareHouseMngProfile", "model", model);

	}

	@RequestMapping(value = "/updateWareHouseMngDetails", method = RequestMethod.POST)
	public ModelAndView updateAdminDetails(ModelMap model,
			@ModelAttribute EndUserForm endUserForm, BindingResult result,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		EndUser endUser = endUserDAOImpl.findId(endUserForm.getId());

		endUser.setId(endUserForm.getId());

		endUser.setDisplayName(endUserForm.getDisplayName());
		endUser.setContactNo(endUserForm.getContactNo());
		endUser.setAltContactNo(endUserForm.getAltContactNo());
		endUser.setEmail(endUserForm.getEmail());
		endUser.setAltEmail(endUserForm.getAltEmail());
		endUser.setUserName(endUserForm.getUserName());
		endUser.setTransactionId(endUserForm.getTransactionId());

		endUserDAOImpl.update(endUser);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("updateWareHouseMngSuccess", "model", model);

	}

	@RequestMapping(value = "/editWareHouseMngPWD", method = RequestMethod.GET)
	public ModelAndView editAdminPWD(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser userProfile = endUserDAOImpl.findId(id);

		endUserForm.setId(userProfile.getId());

		endUserForm.setTransactionId(userProfile.getTransactionId());

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("editWareHouseMngPWD", "model", model);

	}

	@RequestMapping(value = "/updateEditWareHouseMngPWD", method = RequestMethod.POST)
	public ModelAndView updateEditAdminPWD(ModelMap model,
			@ModelAttribute EndUserForm endUserForm, BindingResult result,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		EndUser endUser = endUserDAOImpl.findId(endUserForm.getId());

		endUser.setId(endUserForm.getId());

		endUser.setPassword(endUserForm.getNewPassword());
		endUser.setTransactionId(endUserForm.getTransactionId());

		endUserDAOImpl.update(endUser);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("updateWareHouseMngSuccess", "model", model);

	}
	
	@RequestMapping(value = "/showWhMngMail", method = RequestMethod.GET)
	public ModelAndView doSendEmail1(@ModelAttribute ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("queryWhMngMail", "model", model);
	}
	
	@RequestMapping(value = "/mailSender", method = RequestMethod.POST)
	public ModelAndView doSendEmail(@ModelAttribute ModelMap model,
			HttpServletRequest request) {
		EndUser user = getCurrentLoggedUserDetails();

		String recipientAddress = request.getParameter("recipient");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");

		System.out.println("To: " + recipientAddress);
		System.out.println("Subject: " + subject);
		System.out.println("Message: " + message);

		// creates a simple e-mail object
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject(subject);
		email.setText(message);
		// sends the e-mail
		mailSender.send(email);

		model.put("user", user);

		// forwards to the view named "Result"
		return new ModelAndView("whMngresult", "model", model);
	}
	
	@RequestMapping(value = "/disputeMngList", method = RequestMethod.GET)
	public ModelAndView disputeHeadList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Dispute> disList = disputeDAO.getDisputeMngList(user.getUserName()).getResultList();

		if (disList != null && disList.size() > 0) {
			model.put("disList", disList);
			return new ModelAndView("disputeMngList", "model", model);
		}
		else
		{
			return new ModelAndView("noDataFoundInWhMng", "model", model);
		}

	}
	
	@RequestMapping(value = "/disputeMng", method = RequestMethod.GET)
	public ModelAndView disputeHead(@RequestParam Long id, ModelMap model,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Dispute dis = disputeDAO.getByDisputeId(id);

		disputeForm.setId(dis.getId());
		disputeForm.setCustomerName(dis.getCustomerName());
		disputeForm.setMasterKey(dis.getMasterKey());
		disputeForm.setPokey(dis.getPoKey());
		disputeForm.setSupplierName(dis.getSupplierName());
		disputeForm.setGoods(dis.getGoods());
		disputeForm.setTransactionId(dis.getTransactionId());
		disputeForm.setMissingGoodsCost(dis.getMissingGoodsCost());
		disputeForm.setMissingGoodsQty(dis.getMissingGoodsQty());
		disputeForm.setFullyDamagedCost(dis.getFullyDamagedCost());
		disputeForm.setFullyDamagedQty(dis.getFullyDamagedQty());
		disputeForm.setPartiallyDamagedCost(dis.getPartiallyDamagedCost());
		disputeForm.setPartiallyDamagedQty(dis.getPartiallyDamagedQty());
		disputeForm.setNoOfDefect(dis.getNoOfDefect());
		disputeForm.setAnswer2(dis.getAnswer2());

		attributes.addFlashAttribute("success", "Closed Successfully");
		model.put("user", user);
		model.put("disputeForm", disputeForm);

		return new ModelAndView("disputeMng", "model", model);
	}
	
	@RequestMapping(value = "/disputeMngConfirm", method = RequestMethod.POST)
	public ModelAndView disputeMngConfirm(ModelMap model,
			@ModelAttribute DisputeForm disputeForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		disputeForm.setId(disputeForm.getId());
		disputeForm.setCustomerName(disputeForm.getCustomerName());
		disputeForm.setMasterKey(disputeForm.getMasterKey());
		disputeForm.setPokey(disputeForm.getPokey());
		disputeForm.setSupplierName(disputeForm.getSupplierName());
		disputeForm.setGoods(disputeForm.getGoods());
		disputeForm.setTransactionId(disputeForm.getTransactionId());
		disputeForm.setMissingGoodsCost(disputeForm.getMissingGoodsCost());
		disputeForm.setMissingGoodsQty(disputeForm.getMissingGoodsQty());
		disputeForm.setFullyDamagedCost(disputeForm.getFullyDamagedCost());
		disputeForm.setFullyDamagedQty(disputeForm.getFullyDamagedQty());
		disputeForm.setPartiallyDamagedCost(disputeForm.getPartiallyDamagedCost());
		disputeForm.setPartiallyDamagedQty(disputeForm.getPartiallyDamagedQty());
		disputeForm.setNoOfDefect(disputeForm.getNoOfDefect());
		disputeForm.setAnswer2(disputeForm.getAnswer2());
		disputeForm.setStatus(disputeForm.getStatus());
		disputeForm.setComment(disputeForm.getComment());

		model.put("disputeForm", disputeForm);
		model.put("user", user);

		return new ModelAndView("disputeMngConfirm", "model", model);

	}
	
	@RequestMapping(value = "/disputeHeadPost", method = RequestMethod.POST)
	public ModelAndView disputeHeadPost(ModelMap model,
			@ModelAttribute DisputeForm disputeForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		Dispute dis = disputeDAO.getByDisputeId(disputeForm.getId());

		dis.setStatus(disputeForm.getStatus());
		dis.setComment(disputeForm.getComment());
		
		disputeDAO.updateDispute(dis);

		Transaction trans = new Transaction();
		trans.setTransactionId(disputeForm.getTransactionId());
		trans.setTransactionStatus("Dispute Approval");
		trans.setTransactionType("Submitted Successfully");

		model.put("disputeForm", disputeForm);
		model.put("user", user);

		return new ModelAndView("disputeMngTrans", "model", model);

	}
	
	@RequestMapping(value = "/disputeMngTrans", method = RequestMethod.GET)
	public ModelAndView disputeHeadTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("disputeForm", disputeForm);

		return new ModelAndView("disputeMngTrans", "model", model);

	}
	
	@RequestMapping(value = "/disputeMngFullList", method = RequestMethod.GET)
	public ModelAndView disputeMngFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Dispute> disList = disputeDAO.getDisputeMngFullList(user.getUserName()).getResultList();

		if (disList != null && disList.size() > 0) {
			model.put("disList", disList);
			return new ModelAndView("disputeMngFullList", "model", model);
		}
		else
		{
			return new ModelAndView("noDataFoundInWhMng", "model", model);
		}

	}
	
	@RequestMapping(value = "/whMngHelp", method = RequestMethod.GET)
	public ModelAndView whMngHelp(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("whMngHelp", "model", model);

	}
	@RequestMapping(value = "/custWareHouseInvoiceList", method = RequestMethod.GET)
	public ModelAndView custWareHouseInvoiceList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getInvoiceListForGoods(user.getUserName()).getResultList();

		if (invoiceList != null && invoiceList.size() > 0) {

			model.put("invoiceList", invoiceList);
			return new ModelAndView("custWareHouseInvoiceList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInWhMng", "model", model);
		}
	}

@RequestMapping(value = "/custWareHouseInvoiceSend", method = RequestMethod.GET)
	public ModelAndView custWareHouseInvoiceSend(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		
		InvoiceForm  invoiceForm  = new InvoiceForm ();

		Invoice invoice = invoiceDAO.getByInvoiceId(id);

		invoice.setGoodsStatus("Sent");
		
		invoiceForm.setCustomerHeadName(invoice.getCustomerHeadName());
		invoiceForm.setBuyerName(invoice.getBuyerName());
		invoiceForm.setMasterKey(invoice.getMasterKey());
		invoiceForm.setGoods(invoice.getGoods());
		invoiceForm.setQuantity(invoice.getQuantity());
		invoiceForm.setGoodsStatus("Goods Sent To Buyer");

		Date sent = DateService.loginDate;
		invoice.setSentDate(sent);

		invoiceDAO.updateInvoice(invoice);

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("WareHouseDeliverTrans");

	}

}
