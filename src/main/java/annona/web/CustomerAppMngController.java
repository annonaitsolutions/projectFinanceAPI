package annona.web;

import annona.dao.*;
import annona.domain.*;
import annona.form.*;
import annona.services.DateService;
import annona.services.ImageService;
import annona.trade.dao.TradeNotificationDAO;
import annona.trade.domain.TradeNotification;
import annona.utility.KeyGenerator;

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.theme.CookieThemeResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/clientAppMng")
public class CustomerAppMngController {

	@Autowired
	EndUserDAO endUserDAOImpl;

	@Autowired
	EndUserForm endUserForm;

	@Autowired
	CompanyDAO companyDAO;

	@Autowired
	PoUploadDAO poUploadDAO;

	@Autowired
	InvoiceUploadDAO invoiceUploadDAO;

	@Autowired
	WareHouseDAO wareHouseDAO;

	@Autowired
	SalesOrderForm salesOrderForm;

	@Autowired
	WareHouseForm wareHouseForm;

	@Autowired
	RepaymentForm repaymentForm;

	@Autowired
	RepaymentDAO repaymenyDAO;

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	DisputeReportsDAO disputeReportsDAO;

	@Autowired
	InvoiceReportsDAO invoiceReportsDAO;

	@Autowired
	DisputeReportsForm disputeReportsForm;

	@Autowired
	NotificationDAO notificationDAO;

	@Autowired
	WorkingCapitalDAO workingCapitalDAO;

	@Autowired
	SellerBuyingCostDAO sellerBuyingCostDAO;

	@Autowired
	InvoiceForm invoiceForm;

	@Autowired
	InvoiceDAO invoiceDAO;

	@Autowired
	CustomerDAO customerDAO;

	@Autowired
	TransactionDAO transcationDAO;

	@Autowired
	TransactionDAO transactionDAO;

	@Autowired
	MasterPlanDAO masterPlanDAO;

	@Autowired
	MasterPlanForm masterPlanForm;

	@Autowired
	LetterOfCreditDAO letterOfCreditDAO;

	@Autowired
	LetterOfCreditForm letterOfCreditForm;

	@Autowired
	CorrespondentDAO correspondentDAO;

	@Autowired
	CollateralDAO collateralDAO;

	@Autowired
	BuyingCostDAO buyingCostDAO;

	@Autowired
	FundsDistributeDAO fundsDistributeDAO;

	@Autowired
	CustomerHeadForm customerHeadForm;

	@Autowired
	PurchaseOrderDAO purchaseOrderDAO;

	@Autowired
	PurchaseOrderForm purchaseOrderForm;

	@Autowired
	CustomerBranchForm customerBranchForm;

	@Autowired
	CustomerSubsidiaryForm customerSubsidiaryForm;
	@Autowired
	NewBuyerDAO newBuyerDAOImpl;
	@Autowired
	NewBuyerForm newBuyerForm;
	@Autowired
	SupplierDAO supplierDAOImpl;

	@Autowired
	SupplierForm supplierForm;

	@Autowired
	TransactionDAO transcationDAOImpl;

	@Autowired
	BuyerSameBankEventDAO buyersamebankservice;

	@Autowired
	BuyerDiffBankEventDAO buyerdiffrentbankService;

	@Autowired
	SalesOrderDAO salesOrderDAO;

	@Autowired
	SellerSameBankEventDAO sellersameBankService;

	@Autowired
	SellerDiffBankEventDAO sellerDiffBankEventDAO;

	@Autowired
	SellerDiffBankEventForm sellerDiffBankEventForm;

	@Autowired
	BuyerSameBankEventForm buyersameBankForm;

	@Autowired
	BuyerDiffBankEventForm buyerdiffBankForm;

	@Autowired
	SellerSameBankEventForm sellerSameBankEventForm;

	@Autowired
	PurchaseDocDAO purchaseDocDAO;

	@Autowired
	InvoiceDocDAO invoiceDocDAO;

	@Autowired
	InventoryDAO inventoryDAO;

	@Autowired
	InvoiceStockDAO invoiceStockDAO;

	@Autowired
	PoStockDAO poStockDAO;

	@Autowired
	InvoiceInventoryDAO invoiceInventoryDAO;

	@Autowired
	WareHouseMngForm wareHouseMngForm;

	@Autowired
	WareHouseMngDAO wareHouseMngDAO;

	@Autowired
	TradeNotificationDAO tradeNotificationDAO;

	@Autowired
	CustomerBankDetailsDAO customerBankDetailsDAO;

	CookieThemeResolver themeResolver = new CookieThemeResolver();

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	protected Logger log = Logger.getLogger(ApprovalMngController.class.getName());

	private String getCurrentLoggedUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();

	}

	@ModelAttribute("requestCurrentUser")
	public EndUser getUserDetails() {
		EndUser enUser = getCurrentLoggedUserDetails();
		if (enUser.getImageName() != null) {
			String type = ImageService.getImageType(enUser.getImageName());

			String url = "data:image/" + type + ";base64," + Base64.encodeBase64String(enUser.getImage());
			enUser.setImageName(url);
		}

		return enUser;
	}

	private EndUser getCurrentLoggedUserDetails() {

		EndUser endUser = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		return endUser;

	}

	@RequestMapping(value = "/themeChange", method = RequestMethod.GET)
	public String getThemeChange(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		log.info("Received request for theme change");
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		EndUser endUser = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		if (endUser.getTheme() == null)
			themeResolver.setThemeName(request, response, "themeBlue");
		if (!endUser.getTheme().equalsIgnoreCase(request.getParameter("theme"))) {

			if (request.getParameter("theme").equalsIgnoreCase("themeBlue")) {
				themeResolver.setThemeName(request, response, "themeBlue");
			} else if (request.getParameter("theme").equalsIgnoreCase("themeGray")) {
				themeResolver.setThemeName(request, response, "themeGray");
			} else if (request.getParameter("theme").equalsIgnoreCase("themeOrange")) {
				themeResolver.setThemeName(request, response, "themeOrange");
			} else if (request.getParameter("theme").equalsIgnoreCase("themeRed")) {
				themeResolver.setThemeName(request, response, "themeRed");
			}

			endUser.setTheme(request.getParameter("theme"));
			endUserDAOImpl.mergeUser(endUser);
		} else
			themeResolver.setThemeName(request, response, endUser.getTheme());

		return "redirect:clientApprMng";
	}

	@RequestMapping(value = "/getLocaleLang", method = RequestMethod.GET)
	public String getLocaleLang(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		log.info("Received request for locale change");
		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		LocaleResolver localeResolver = new CookieLocaleResolver();
		Locale locale = new Locale(request.getParameter("lang"));
		localeResolver.setLocale(request, response, locale);
		user.setPrefferedLanguage(request.getParameter("lang"));
		model.put("user", user);
		endUserDAOImpl.mergeUser(user);

		return "redirect:clientApprMng";
	}

	@RequestMapping(value = "/clientApprMngCommon", method = RequestMethod.GET)
	public ModelAndView showApprMngCommonDashBoard(ModelMap model, HttpServletRequest request,
												   HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		TradeNotification tradenot = new TradeNotification();
		Collection<TradeNotification> tradenotificationList = tradeNotificationDAO.getList();

		if (tradenotificationList != null && tradenotificationList.size() > 0) {

			model.put("tradenot", tradenot);

			model.put("tradenotificationList", tradenotificationList);

		}
		Notification not = new Notification();
		Collection<Notification> notificationList = notificationDAO.getList();

		if (notificationList != null && notificationList.size() > 0) {

			model.put("not", not);

			model.put("notificationList", notificationList);

		}

		return new ModelAndView("clientApprMngCommonPage", "model", model);

	}

	@RequestMapping(value = "/clientApprMng", method = RequestMethod.GET)
	public ModelAndView showApprMngDashBoard(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("clientApprMngPage", "model", model);

	}

	@RequestMapping(value = "/editCustAppProfile", method = RequestMethod.GET)
	public ModelAndView editAdminProfile(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser userProfile = endUserDAOImpl.findId(id);
		if (userProfile.getImageName() != null) {
			String type = ImageService.getImageType(userProfile.getImageName());

			String url = "data:image/" + type + ";base64," + Base64.encodeBase64String(userProfile.getImage());
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

		return new ModelAndView("editCustAppProfile", "model", model);

	}

	@RequestMapping(value = "/updateImageForProfile", method = RequestMethod.POST)
	public String updateImageForProfile(ModelMap model, @ModelAttribute EndUserForm endUserForm) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		try {
			EndUser userProfile = endUserDAOImpl.findId(endUserForm.getId());
			userProfile.setImage(endUserForm.getFile().getBytes());
			userProfile.setImageName(endUserForm.getFile().getOriginalFilename());
			endUserDAOImpl.update(userProfile);

		} catch (Exception e) {
			e.getMessage();
		}
		return "redirect:editCustAppProfile?id=" + endUserForm.getId();
	}

	@RequestMapping(value = "/confirmEditCustAppProfile", method = RequestMethod.POST)
	public ModelAndView confirmEditAdminProfile(ModelMap model, @ModelAttribute EndUserForm endUserForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("confirmEditCustAppProfile", "model", model);

	}

	@RequestMapping(value = "/updateCustAppDetails", method = RequestMethod.POST)
	public ModelAndView updateAdminDetails(ModelMap model, @ModelAttribute EndUserForm endUserForm,
										   BindingResult result, RedirectAttributes attributes) {

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

		return new ModelAndView("updateCustAppSuccess", "model", model);

	}

	@RequestMapping(value = "/editCustAppPWD", method = RequestMethod.GET)
	public ModelAndView editAdminPWD(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser userProfile = endUserDAOImpl.findId(id);

		endUserForm.setId(userProfile.getId());

		endUserForm.setTransactionId(userProfile.getTransactionId());

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("editCustAppPWD", "model", model);

	}

	@RequestMapping(value = "/updateEditCustAppPWD", method = RequestMethod.POST)
	public ModelAndView updateEditAdminPWD(ModelMap model, @ModelAttribute EndUserForm endUserForm,
										   BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		EndUser endUser = endUserDAOImpl.findId(endUserForm.getId());

		endUser.setId(endUserForm.getId());

		endUser.setPassword(endUserForm.getNewPassword());
		endUser.setTransactionId(endUserForm.getTransactionId());

		endUserDAOImpl.update(endUser);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("updateCustAppSuccess", "model", model);

	}

	@RequestMapping(value = "/capprCustomerHeadList", method = RequestMethod.GET)
	public ModelAndView customerHeadList(ModelMap model) {

		List<CustomerHeadForm> customerList = customerDAO.findAllCustomers();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		if (customerList != null && customerList.size() > 0) {
			model.put("customerList", customerList);

			return new ModelAndView("capprCustomerHeadList", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/customerHeadCApprovalList", method = RequestMethod.GET)
	public ModelAndView customerHeadApproval(ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		List<CustomerHeadForm> customerList = customerDAO.getCustByIdAndAStatusComp(user.getCompanyId());

		model.put("user", user);
		if (customerList != null && customerList.size() > 0) {

			model.put("customerList", customerList);
			model.put("customerHeadForm", customerHeadForm);
			return new ModelAndView("customerHeadCApprovalList", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}
	}

	@RequestMapping(value = "/capproveCustomerHead", method = RequestMethod.GET)
	public ModelAndView approveCustomerHead(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		CustomerHead customer = customerDAO.getByCustomerId(id);

		Company company = companyDAO.getByCompanyId(customer.getCompanyId());

		customerHeadForm.setId(customer.getId());
		customerHeadForm.setName(customer.getName());
		customerHeadForm.setCompanyName(company.getCompanyName());
		customerHeadForm.setCustomerPrefix(customer.getCustomerPrefix());
		customerHeadForm.setAddress(company.getAddress());
		customerHeadForm.setContactNum(customer.getContactNum());
		customerHeadForm.setEmail(customer.getEmail());
		customerHeadForm.setAltContactNum(customer.getAltContactNum());
		customerHeadForm.setAltEmail(customer.getAltEmail());
		model.put("customerHeadForm", customerHeadForm);
		return new ModelAndView("capproveCustomerHead", "model", model);
	}

	@RequestMapping(value = "/capproveCustomerHeadConfirm", method = RequestMethod.POST)
	public ModelAndView approveCustomerHeadConfirm(@ModelAttribute CustomerHeadForm customerHeadForm, ModelMap model,
												   RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		customerHeadForm.setId(customerHeadForm.getId());
		customerHeadForm.setName(customerHeadForm.getName());
		customerHeadForm.setCompanyName(customerHeadForm.getCompanyName());
		customerHeadForm.setCustomerPrefix(customerHeadForm.getCustomerPrefix());
		customerHeadForm.setAddress(customerHeadForm.getAddress());
		customerHeadForm.setaStatus(customerHeadForm.getaStatus());
		customerHeadForm.setComment(customerHeadForm.getComment());
		customerHeadForm.setCustomerHeadKey(customerHeadForm.getCustomerHeadKey());
		customerHeadForm.setContactNum(customerHeadForm.getContactNum());
		customerHeadForm.setEmail(customerHeadForm.getEmail());
		customerHeadForm.setAltContactNum(customerHeadForm.getAltContactNum());
		customerHeadForm.setAltEmail(customerHeadForm.getAltEmail());
		model.put("customerHeadForm", customerHeadForm);

		return new ModelAndView("capproveCustomerHeadConfirm", "model", model);
	}

	@RequestMapping(value = "/capproveCustomerHeadSave", method = RequestMethod.POST)
	public ModelAndView approveCustomerHeadSave(@ModelAttribute CustomerHeadForm customerHeadForm, ModelMap model,
												RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		
		customerHeadForm.setTransactionId(KeyGenerator.generateTransactionKey());
		customerHeadForm.setCustomerHeadKey(KeyGenerator.generateTransactionKey());

		model.put("user", user);

		CustomerHead customer = customerDAO.getByCustomerId(customerHeadForm.getId());

		customer.setName(customerHeadForm.getName());
		// customer.setCompanyName(customerHeadForm.getCompanyName());
		customer.setCustomerPrefix(customerHeadForm.getCustomerPrefix());
		// customer.setAddress(customerHeadForm.getAddress());
		customer.setaStatus(customerHeadForm.getaStatus());
		customer.setComment(customerHeadForm.getComment());
		customer.setAltContactNum(customerHeadForm.getAltContactNum());
		customer.setAltEmail(customerHeadForm.getAltEmail());
		Date date = DateService.loginDate;
		customer.setApproveeDate(date);
		customer.setCustomerHeadKey(customerHeadForm.getCustomerHeadKey());
		customer.setPassword(customer.getContactNum());
		customer.setTransactionId(customerHeadForm.getTransactionId());
		customer.setStatus("Pending");

		customerDAO.updateUser(customer);

		Transaction trans = new Transaction();
		trans.setTransactionId(customerHeadForm.getTransactionId());
		trans.setTransactionStatus("Customer Head Approval");
		trans.setTransactionType("Approved Successfully");

		transactionDAO.insertTransaction(trans);
		model.put("customerHeadForm", customerHeadForm);
		return new ModelAndView("customerHeadcAppTransaction", "model", model);
	}

	@RequestMapping(value = "/customerHeadcAppTransaction", method = RequestMethod.GET)
	public ModelAndView customerHeadTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("customerHeadForm", customerHeadForm);

		return new ModelAndView("customerHeadcAppTransaction", "model", model);

	}

	@RequestMapping(value = "/customerBranchcApprovalList", method = RequestMethod.GET)
	public ModelAndView customerBranchApproval(ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<CustomerBranch> customerList = customerDAO.getCustBranchByIdAndaStatus().getResultList();

		if (customerList != null && customerList.size() > 0) {

			model.put("customerList", customerList);
			model.put("customerBranchForm", customerBranchForm);
			return new ModelAndView("customerBranchcApprovalList", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/capproveCustomerBranch", method = RequestMethod.GET)
	public ModelAndView approveCustomerBranch(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		CustomerBranch customer = customerDAO.getByCustomerBranchId(id);

		customerBranchForm.setId(customer.getId());
		customerBranchForm.setName(customer.getName());
		customerBranchForm.setCustomerHeadName(customer.getCustomerHeadName());
		customerBranchForm.setCustomerHeadKey(customer.getCustomerHeadKey());
		customerBranchForm.setAddress(customer.getAddress());
		customerBranchForm.setContactNum(customer.getContactNum());
		customerBranchForm.setEmail(customer.getEmail());

		model.put("customerBranchForm", customerBranchForm);
		return new ModelAndView("capproveCustomerBranch", "model", model);
	}

	@RequestMapping(value = "/capproveCustomerBranchConfirm", method = RequestMethod.POST)
	public ModelAndView approveCustomerBranchConfirm(@ModelAttribute CustomerBranchForm customerBranchForm,
													 ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		customerBranchForm.setName(customerBranchForm.getName());
		customerBranchForm.setCustomerHeadName(customerBranchForm.getCustomerHeadName());
		customerBranchForm.setAddress(customerBranchForm.getAddress());
		customerBranchForm.setaStatus(customerBranchForm.getaStatus());
		customerBranchForm.setComment(customerBranchForm.getComment());
		customerBranchForm.setCustomerHeadKey(customerBranchForm.getCustomerHeadKey());
		customerBranchForm.setContactNum(customerBranchForm.getContactNum());
		customerBranchForm.setEmail(customerBranchForm.getEmail());

		model.put("customerBranchForm", customerBranchForm);

		return new ModelAndView("capproveCustomerBranchConfirm", "model", model);
	}

	@RequestMapping(value = "/capproveCustomerBranchSave", method = RequestMethod.POST)
	public ModelAndView approveCustomerBranchSave(@ModelAttribute CustomerBranchForm customerBranchForm, ModelMap model,
												  RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		
		customerBranchForm.setTransactionId(KeyGenerator.generateTransactionKey());
		customerBranchForm.setCustomerBranchKey(KeyGenerator.generateTransactionKey());

		model.put("user", user);

		CustomerBranch customer = customerDAO.getByCustomerBranchId(customerBranchForm.getId());

		customer.setName(customerBranchForm.getName());
		customer.setCustomerHeadName(customerBranchForm.getCustomerHeadName());
		customer.setAddress(customerBranchForm.getAddress());
		customer.setaStatus(customerBranchForm.getaStatus());
		customer.setComment(customerBranchForm.getComment());
		Date date = DateService.loginDate;
		customer.setApproveeDate(date);
		customer.setPassword(customerBranchForm.getEmail());
		customer.setCustomerBranchKey(customerBranchForm.getCustomerBranchKey());
		customer.setTransactionId(customerBranchForm.getTransactionId());
		if (customer.getaStatus().equals("Approved")) {
			customer.setStatus("Pending");
		}

		customerDAO.updateBranch(customer);

		Transaction trans = new Transaction();
		trans.setTransactionId(customerBranchForm.getTransactionId());
		trans.setTransactionStatus("Customer Branch Approval");
		trans.setTransactionType("Approved Successfully");

		transactionDAO.insertTransaction(trans);
		model.put("customerBranchForm", customerBranchForm);
		return new ModelAndView("customerBranchcAppTransaction", "model", model);
	}

	@RequestMapping(value = "/customerBranchcAppTransaction", method = RequestMethod.GET)
	public ModelAndView customerBranchAppTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("customerBranchForm", customerBranchForm);

		return new ModelAndView("customerBranchcAppTransaction", "model", model);

	}

	@RequestMapping(value = "/capprCustomerBranchList", method = RequestMethod.GET)
	public ModelAndView customerBranchList(ModelMap model) {

		Collection<CustomerBranch> customerList = customerDAO.findAllCustomerBranch();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		if (customerList != null && customerList.size() > 0) {
			model.put("customerList", customerList);

			return new ModelAndView("capprCustomerBranchList", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/customerSubsidiarycApprovalList", method = RequestMethod.GET)
	public ModelAndView customerSubsidiaryApprovalList(ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<CustomerSubsidiary> customerList = customerDAO.getCustSubsByIdAndaStatus().getResultList();

		if (customerList != null && customerList.size() > 0) {

			model.put("customerList", customerList);
			model.put("customerSubsidiaryForm", customerSubsidiaryForm);
			return new ModelAndView("customerSubsidiarycApprovalList", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}
	}

	@RequestMapping(value = "/capproveCustomerSubsidiary", method = RequestMethod.GET)
	public ModelAndView approveCustomerSubsidiary(@RequestParam Long id, ModelMap model,
												  RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		CustomerSubsidiary customer = customerDAO.getByCustomerSubsidiaryId(id);

		customerSubsidiaryForm.setId(customer.getId());
		customerSubsidiaryForm.setName(customer.getName());
		customerSubsidiaryForm.setCustomerHeadName(customer.getCustomerHeadName());
		customerSubsidiaryForm.setCustomerHeadKey(customer.getCustomerHeadKey());
		customerSubsidiaryForm.setAddress(customer.getAddress());
		customerSubsidiaryForm.setContactNum(customer.getContactNum());
		customerSubsidiaryForm.setEmail(customer.getEmail());

		model.put("customerSubsidiaryForm", customerSubsidiaryForm);
		return new ModelAndView("capproveCustomerSubsidiary", "model", model);
	}

	@RequestMapping(value = "/capproveCustomerSubsidiaryConfirm", method = RequestMethod.POST)
	public ModelAndView approveCustomerBranchConfirm(@ModelAttribute CustomerSubsidiaryForm customerSubsidiaryForm,
													 ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		customerSubsidiaryForm.setName(customerSubsidiaryForm.getName());
		customerSubsidiaryForm.setCustomerHeadName(customerSubsidiaryForm.getCustomerHeadName());
		customerSubsidiaryForm.setAddress(customerSubsidiaryForm.getAddress());
		customerSubsidiaryForm.setaStatus(customerSubsidiaryForm.getaStatus());
		customerSubsidiaryForm.setComment(customerSubsidiaryForm.getComment());
		customerSubsidiaryForm.setCustomerHeadKey(customerSubsidiaryForm.getCustomerHeadKey());
		customerSubsidiaryForm.setContactNum(customerSubsidiaryForm.getContactNum());
		customerSubsidiaryForm.setEmail(customerSubsidiaryForm.getEmail());

		model.put("customerSubsidiaryForm", customerSubsidiaryForm);

		return new ModelAndView("capproveCustomerSubsidiaryConfirm", "model", model);
	}

	@RequestMapping(value = "/capproveCustomerSubsidiarySave", method = RequestMethod.POST)
	public ModelAndView approveCustomerSubsidiarySave(@ModelAttribute CustomerSubsidiaryForm customerSubsidiaryForm,
													  ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		
		customerSubsidiaryForm.setTransactionId(KeyGenerator.generateTransactionKey());
		customerSubsidiaryForm.setCustomerSubsidiaryKey(KeyGenerator.generateTransactionKey());

		model.put("user", user);

		CustomerSubsidiary customer = customerDAO.getByCustomerSubsidiaryId(customerSubsidiaryForm.getId());

		customer.setName(customerSubsidiaryForm.getName());
		customer.setCustomerHeadName(customerSubsidiaryForm.getCustomerHeadName());
		customer.setAddress(customerSubsidiaryForm.getAddress());
		customer.setaStatus(customerSubsidiaryForm.getaStatus());
		customer.setComment(customerSubsidiaryForm.getComment());
		Date date = DateService.loginDate;
		customer.setApproveeDate(date);
		customer.setPassword(customerSubsidiaryForm.getContactNum());
		customer.setCustomerSubsidiaryKey(customerSubsidiaryForm.getCustomerSubsidiaryKey());
		customer.setTransactionId(customerSubsidiaryForm.getTransactionId());
		customer.setStatus("Pending");
		customerDAO.updateSubsidiary(customer);

		Transaction trans = new Transaction();
		trans.setTransactionId(customerSubsidiaryForm.getTransactionId());
		trans.setTransactionStatus("Customer Subsidiary Approval");
		trans.setTransactionType("Updated Successfully");

		transactionDAO.insertTransaction(trans);
		model.put("customerSubsidiaryForm", customerSubsidiaryForm);
		return new ModelAndView("customerSubsidiarycAppTransaction", "model", model);
	}

	@RequestMapping(value = "/customerSubsidiarycAppTransaction", method = RequestMethod.GET)
	public ModelAndView customerSubsidiaryAppTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("customerSubsidiaryForm", customerSubsidiaryForm);

		return new ModelAndView("customerSubsidiarycAppTransaction", "model", model);

	}

	@RequestMapping(value = "/capprCustomerSubsidiaryList", method = RequestMethod.GET)
	public ModelAndView customerSubsidiaryList(ModelMap model) {

		Collection<CustomerSubsidiary> customerList = customerDAO.findAllCustomerSubsidiary();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		if (customerList != null && customerList.size() > 0) {
			model.put("customerList", customerList);

			return new ModelAndView("capprCustomerSubsidiaryList", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}
	}

	@RequestMapping(value = "/clientMasterPlanPendingDetails", method = RequestMethod.GET)
	public ModelAndView bankMasterPlanPendingDetails(ModelMap model) throws ParseException {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<MasterPlan> masterList = masterPlanDAO.getMasterPlanForApprovalByClientPenStatus();

		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);

			return new ModelAndView("clientMasterPlanPendingDetails", "model", model);
		}

		else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/clientMasterPlanFullDetails", method = RequestMethod.GET)
	public ModelAndView bankMasterPlanFullDetails(@RequestParam("id") Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		MasterPlan masterList = masterPlanDAO.getByMasterPlanId(id);

		if (masterList != null && masterList.getMasterKey() != null) {
			List<Collateral> collateralList = collateralDAO.getCollateralBYMAsterKey(masterList.getMasterKey())
					.getResultList();
			if (collateralList != null && collateralList.size() > 0) {
				model.put("collateralList", collateralList);
			}

			List<BuyingCost> buyingList = buyingCostDAO.getBuyingCostBYMAsterKey(masterList.getMasterKey())
					.getResultList();

			if (buyingList != null && buyingList.size() > 0)

			{
				model.put("buyingList", buyingList);
			}

			List<WorkingCapital> wcList = workingCapitalDAO.getWCBYMAsterKey(masterList.getMasterKey()).getResultList();
			if (wcList != null && wcList.size() > 0)

			{
				model.put("wcList", wcList);
			}

			List<SellerBuyingCost> sellerList = sellerBuyingCostDAO.getSellerBuyingCostList(masterList.getMasterKey())
					.getResultList();
			if (sellerList != null && sellerList.size() > 0)

			{
				model.put("sellerList", sellerList);
			}
			model.put("masterList", masterList);
		}
		model.put("user", user);

		return new ModelAndView("clientMasterPlanFullDetails", "model", model);

	}

	@RequestMapping(value = "/clientMasterPlanApproveStatus", method = RequestMethod.GET)
	public ModelAndView bankMasterPlanApproveStatus(@RequestParam("id") Long id, ModelMap model) {

		MasterPlan master = masterPlanDAO.getByMasterPlanId(id);

		masterPlanForm.setId(master.getId());
		masterPlanForm.setCustomer(master.getCustomer());
		masterPlanForm.setMasterKey(master.getMasterKey());
		masterPlanForm.setTransactionId(master.getTransactionId());

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("clientMasterPlanApproveStatus", "model", model);

	}

	@RequestMapping(value = "/clientMasterPlanApproveStatusConfirm", method = RequestMethod.POST)
	public ModelAndView bankMasterPlanApproveStatusConfirm(ModelMap model,
														   @ModelAttribute MasterPlanForm masterPlanForm, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		masterPlanForm.setTransactionId(masterPlanForm.getTransactionId());
		masterPlanForm.setCustomer(masterPlanForm.getCustomer());
		masterPlanForm.setaStatus(masterPlanForm.getaStatus());
		masterPlanForm.setaComment(masterPlanForm.getaComment());

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("clientMasterPlanApproveStatusConfirm", "model", model);

	}

	@RequestMapping(value = "/clientMasterPlanApproveStatusPost", method = RequestMethod.POST)
	public ModelAndView draftsMasterPlanInfoConfirm(ModelMap model, @ModelAttribute MasterPlanForm masterPlanForm,
													RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		masterPlanForm.setTransactionId(masterPlanForm.getTransactionId());

		MasterPlan master = masterPlanDAO.getByMasterPlanId(masterPlanForm.getId());

		master.setaStatus(masterPlanForm.getaStatus());
		master.setaComment(masterPlanForm.getaComment());

		String status = masterPlanForm.getaStatus();

		if (status.equals("Approved")) {
			master.setStatus("pending");
		}

		masterPlanDAO.updatePlan(master);

		Transaction trans = new Transaction();
		trans.setTransactionId(masterPlanForm.getTransactionId());
		trans.setTransactionStatus("Master Plan Approval/Reject By Customer App Mng");
		trans.setTransactionType("Submitted Successfully");

		transactionDAO.insertTransaction(trans);

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("masterPlanClientAppTransaction", "model", model);

	}

	@RequestMapping(value = "/masterPlanClientAppTransaction", method = RequestMethod.GET)
	public ModelAndView collateralTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("masterPlanClientAppTransaction", "model", model);

	}

	@RequestMapping(value = "/masterPlanClientFullList", method = RequestMethod.GET)
	public ModelAndView masterPlanFullList(ModelMap model) throws ParseException {

		EndUser user = getCurrentLoggedUserDetails();

		List<MasterPlan> masterList = masterPlanDAO.getAllMasterPlanWithCompanyName();

		model.put("user", user);
		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);

			return new ModelAndView("masterPlanClientFullList", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/cPoListForApproval", method = RequestMethod.GET)
	public ModelAndView poListForApproval(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);

		List<PurchaseOrder> purchase = purchaseOrderDAO.getPoListForAppRatesApproval().getResultList();

		if (purchase != null && purchase.size() > 0) {
			model.put("purchase", purchase);
			return new ModelAndView("cPoListForApproval", "model", model);
		}

		else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/cPoForApproval", method = RequestMethod.GET)
	public ModelAndView poForApproval(@RequestParam("id") Long id, ModelMap model) {

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(id);

		purchaseOrderForm.setId(purchase.getId());
		purchaseOrderForm.setCustomerName(purchase.getCustomerName());
		purchaseOrderForm.setCustomerHeadName(purchase.getCustomerHeadName());
		purchaseOrderForm.setMasterKey(purchase.getMasterKey());
		purchaseOrderForm.setStatus(purchase.getStatus());
		purchaseOrderForm.setGoods(purchase.getGoods());
		purchaseOrderForm.setQuantity(purchase.getQuantity());
		purchaseOrderForm.setSupplierName(purchase.getSupplierName());
		purchaseOrderForm.setTransactionId(purchase.getTransactionId());
		purchaseOrderForm.setAmount(purchase.getAmount());
		purchaseOrderForm.setFlag(purchase.getFlag());
		purchaseOrderForm.setCustomerHeadEmail(purchase.getCustomerHeadEmail());
		purchaseOrderForm.setCustomerBranchEmail(purchase.getCustomerBranchEmail());
		purchaseOrderForm.setSupplierEmail(purchase.getSupplierEmail());
		purchaseOrderForm.setPoKey(purchase.getPoKey());
		purchaseOrderForm.setInsuranceAmount(purchase.getInsuranceAmount());
		purchaseOrderForm.setInsuranceDetails(purchase.getInsuranceDetails());
		purchaseOrderForm.setInsuranceType(purchase.getInsuranceType());
		purchaseOrderForm.setStartDate(purchase.getStartDate());
		purchaseOrderForm.setEndDate(purchase.getEndDate());
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("cPoForApproval", "model", model);

	}

	@RequestMapping(value = "/cPoForApprovalConfirm", method = RequestMethod.POST)
	public ModelAndView poForApprovalConfirm(ModelMap model, @ModelAttribute PurchaseOrderForm purchaseOrderForm,
											 RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		purchaseOrderForm.setCustomerName(purchaseOrderForm.getCustomerName());
		purchaseOrderForm.setCustomerHeadName(purchaseOrderForm.getCustomerHeadName());
		purchaseOrderForm.setMasterKey(purchaseOrderForm.getMasterKey());
		purchaseOrderForm.setStatus(purchaseOrderForm.getStatus());
		purchaseOrderForm.setGoods(purchaseOrderForm.getGoods());
		purchaseOrderForm.setQuantity(purchaseOrderForm.getQuantity());
		purchaseOrderForm.setSupplierName(purchaseOrderForm.getSupplierName());
		purchaseOrderForm.setComment(purchaseOrderForm.getComment());
		purchaseOrderForm.setTransactionId(purchaseOrderForm.getTransactionId());
		purchaseOrderForm.setAmount(purchaseOrderForm.getAmount());
		purchaseOrderForm.setFlag(purchaseOrderForm.getFlag());
		purchaseOrderForm.setCustomerHeadEmail(purchaseOrderForm.getCustomerHeadEmail());
		purchaseOrderForm.setCustomerBranchEmail(purchaseOrderForm.getCustomerBranchEmail());
		purchaseOrderForm.setSupplierEmail(purchaseOrderForm.getSupplierEmail());
		purchaseOrderForm.setPoKey(purchaseOrderForm.getPoKey());
		purchaseOrderForm.setInsuranceAmount(purchaseOrderForm.getInsuranceAmount());
		purchaseOrderForm.setInsuranceDetails(purchaseOrderForm.getInsuranceDetails());
		purchaseOrderForm.setInsuranceType(purchaseOrderForm.getInsuranceType());

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("cPoForApprovalConfirm", "model", model);

	}

	@RequestMapping(value = "/cPoForApprovalPost", method = RequestMethod.POST)
	public ModelAndView poForApprovalPost(ModelMap model, @ModelAttribute PurchaseOrderForm purchaseOrderForm,
										  RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		purchaseOrderForm.setTransactionId(purchaseOrderForm.getTransactionId());
		purchaseOrderForm.setAmount(purchaseOrderForm.getAmount());
		purchaseOrderForm.setMasterKey(purchaseOrderForm.getMasterKey());
		purchaseOrderForm.setCustomerName(purchaseOrderForm.getCustomerName());
		purchaseOrderForm.setCustomerHeadEmail(purchaseOrderForm.getCustomerHeadEmail());
		purchaseOrderForm.setCustomerBranchEmail(purchaseOrderForm.getCustomerBranchEmail());
		purchaseOrderForm.setSupplierEmail(purchaseOrderForm.getSupplierEmail());
		purchaseOrderForm.setPoKey(purchaseOrderForm.getPoKey());

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(purchaseOrderForm.getId());

		purchase.setStatus(purchaseOrderForm.getStatus());
		purchase.setComment(purchaseOrderForm.getComment());
		purchase.setTransStatus("None");
		purchase.setStatus("Pending");
		purchase.setPoKey(purchaseOrderForm.getPoKey());
		purchaseOrderDAO.updatePo(purchase);

		if (purchaseOrderForm.getFlag().equals(0)) {
			if (purchaseOrderForm.getStatus().equals("Rejected")) {
				MasterPlan plan = masterPlanDAO.getMasterPlanByMasterKey(purchaseOrderForm.getMasterKey())
						.getSingleResult();
				if (plan != null) {
					Float amt = purchaseOrderForm.getAmount();
					Float utiliAmt = plan.getUtilizedBusnsAmt();
					Float bal = plan.getBalance();

					Float totalUtilized = utiliAmt - amt;
					Float totalBal = bal + amt;
					plan.setBalance(totalBal);
					plan.setUtilizedBusnsAmt(totalUtilized);

					masterPlanDAO.updatePlan(plan);
				}
			}
		} else {
			if (purchaseOrderForm.getStatus().equals("Rejected")) {
				FundsDistribute funds = fundsDistributeDAO
						.getFundsListByKeyAndName(purchaseOrderForm.getMasterKey(), purchaseOrderForm.getCustomerName())
						.getSingleResult();
				if (funds != null) {
					Float amt = purchaseOrderForm.getAmount();
					Float utiliAmt = funds.getUtilizedAmount();
					Float bal = funds.getBusBalance();

					Float totalUtilized = utiliAmt - amt;
					Float totalBal = bal + amt;
					funds.setBusBalance(totalBal);
					funds.setUtilizedAmount(totalUtilized);

					fundsDistributeDAO.updateFunds(funds);
				}
			}

		}

		Transaction trans = new Transaction();
		trans.setTransactionId(purchaseOrderForm.getTransactionId());
		trans.setTransactionStatus("Purchase Order Approval/Reject By Vendor");
		trans.setTransactionType("Submitted Successfully");

		transcationDAO.insertTransaction(trans);
		Notification not = new Notification();

		not.setCustomerName(purchaseOrderForm.getCustomerName());
		Date notDate = DateService.loginDate;
		not.setNotificationDate(notDate);
		not.setNotificationType("Purchase Order Approval");
		not.setNotificationDescription(purchaseOrderForm.getStatus());
		not.setNotificationAcc("main");

		notificationDAO.insertNotification(not);
		String stat = purchaseOrderForm.getStatus();
		String email = purchaseOrderForm.getCustomerHeadEmail();
		String customerheadname = purchaseOrderForm.getCustomerHeadName();
		String pokey = purchaseOrderForm.getPoKey();
		String masterkey = purchaseOrderForm.getMasterKey();
		try {
			if (stat.equals("Approved")) {
				String tex = "Purchase Order  Details Notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + customerheadname + ",\n Your purchase order request has been accepted. "
						+ "\n\n Your Pokey Detail is as follows. " + "\n" + "\n" + "\n\nMaterKey:" + masterkey

						+ "\n\nPoKey:" + pokey +

						"\n\n Please upload documents for this purchase order. "
						+ "\n\n Check your profile for further details. " + "\n\n\nRegards,\n Bank");
				System.out.println("" + email + customerheadname);
				mailSender.send(emails);
				SimpleMailMessage emails1 = new SimpleMailMessage();
				emails1.setTo(email);

			} else if (stat.equals("Rejected")) {
				String tex = "Purchase Order  Details Notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + customerheadname + "\n  the purchase order request has been rejected . " +

						",\n\n Your Pokey Detail is as follows. " + "\n" + "\n" + "\n\nPoKey:" + pokey
						+ "\n\n\nRegards,\nBank");
				mailSender.send(emails);
			}

			model.put("user", user);
			model.put("purchaseOrderForm", purchaseOrderForm);

		} catch (Exception e) {
			System.out.println(e.getMessage() + e);
		}
		return new ModelAndView("cPoForApprovalTransaction", "model", model);

	}

	@RequestMapping(value = "/cPoForApprovalTransaction", method = RequestMethod.GET)
	public ModelAndView poForApprovalTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("cPoForApprovalTransaction", "model", model);

	}

	@RequestMapping(value = "/poInsApprovalList", method = RequestMethod.GET)
	public ModelAndView poInsApproval(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);

		List<DisputeReports> dispute = disputeReportsDAO.getInsByStatus().getResultList();

		if (dispute != null && dispute.size() > 0) {
			model.put("dispute", dispute);
			return new ModelAndView("poInsApprovalList", "model", model);
		}

		else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/poInsApproval", method = RequestMethod.GET)
	public ModelAndView poInsApproval(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		DisputeReports dispute = disputeReportsDAO.getByDisputeReportsId(id);

		disputeReportsForm.setId(dispute.getId());
		disputeReportsForm.setCustomerName(dispute.getCustomerName());
		disputeReportsForm.setSupplierName(dispute.getSupplierName());
		disputeReportsForm.setTransportType(dispute.getModeOfTransport());
		disputeReportsForm.setModeOfTransport(dispute.getModeOfTransport());
		disputeReportsForm.setDisputekey(dispute.getDisputekey());
		disputeReportsForm.setTransactionId(dispute.getTransactionId());

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		return new ModelAndView("poInsApproval", "model", model);
	}

	@RequestMapping(value = "/poInsApprovalConfirm", method = RequestMethod.POST)
	public ModelAndView poInsApprovalConfirm(ModelMap model, @ModelAttribute DisputeReportsForm disputeReportsForm,
											 BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		disputeReportsForm.setCustomerName(disputeReportsForm.getCustomerName());
		disputeReportsForm.setSupplierName(disputeReportsForm.getSupplierName());
		disputeReportsForm.setTransportType(disputeReportsForm.getModeOfTransport());
		disputeReportsForm.setModeOfTransport(disputeReportsForm.getModeOfTransport());
		disputeReportsForm.setDisputekey(disputeReportsForm.getDisputekey());
		disputeReportsForm.setStatus(disputeReportsForm.getStatus());
		disputeReportsForm.setComment(disputeReportsForm.getComment());
		disputeReportsForm.setId(disputeReportsForm.getId());
		disputeReportsForm.setTransactionId(disputeReportsForm.getTransactionId());

		model.put("disputeReportsForm", disputeReportsForm);
		model.put("user", user);

		return new ModelAndView("poInsApprovalConfirm", "model", model);

	}

	@RequestMapping(value = "/poInsApprovalPost", method = RequestMethod.POST)
	public ModelAndView addOrModifyReportPost(ModelMap model, @ModelAttribute DisputeReportsForm disputeReportsForm,
											  BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		DisputeReports dispute = disputeReportsDAO.getByDisputeReportsId(disputeReportsForm.getId());

		dispute.setStatus(disputeReportsForm.getStatus());
		dispute.setComment(disputeReportsForm.getComment());

		disputeReportsDAO.updateDisputeReports(dispute);

		Transaction trans = new Transaction();
		trans.setTransactionId(disputeReportsForm.getTransactionId());
		trans.setTransactionStatus("Insurance Reports Approval");
		trans.setTransactionType("Submitted Successfully");

		model.put("disputeReportsForm", disputeReportsForm);
		model.put("user", user);

		return new ModelAndView("poInsApprovalTrans", "model", model);

	}

	@RequestMapping(value = "/poInsApprovalTrans", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		return new ModelAndView("poInsApprovalTrans", "model", model);

	}

	@RequestMapping(value = "/poInsFullList", method = RequestMethod.GET)
	public ModelAndView poInsFullList(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);

		List<DisputeReports> dispute = disputeReportsDAO.getList().getResultList();

		if (dispute != null && dispute.size() > 0) {
			model.put("dispute", dispute);
			return new ModelAndView("poInsFullList", "model", model);
		}

		else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/addOrModifyReportAppMngView", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportView(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		DisputeReports dispute = disputeReportsDAO.getByDisputeReportsId(id);

		disputeReportsForm.setId(dispute.getId());
		disputeReportsForm.setCustomerName(dispute.getCustomerName());
		disputeReportsForm.setPokey(dispute.getPokey());
		disputeReportsForm.setSupplierName(dispute.getSupplierName());
		disputeReportsForm.setGoods(dispute.getGoods());
		disputeReportsForm.setInsDetails(dispute.getInsDetails());
		disputeReportsForm.setCost(dispute.getCost());
		disputeReportsForm.setDisputekey(dispute.getDisputekey());
		disputeReportsForm.setGoodsDetails(dispute.getGoodsDetails());
		disputeReportsForm.setTransactionId(dispute.getTransactionId());
		disputeReportsForm.setShipper(dispute.getShipper());
		disputeReportsForm.setTransportType(dispute.getModeOfTransport());
		disputeReportsForm.setModeOfTransport(dispute.getModeOfTransport());
		disputeReportsForm.setTermsCond(dispute.getTermsCond());
		disputeReportsForm.setInclusion(dispute.getInclusion());
		disputeReportsForm.setExclusion(dispute.getExclusion());
		disputeReportsForm.setSurveyorName(dispute.getSurveyorName());
		disputeReportsForm.setSurveyorCom(dispute.getSurveyorCom());
		disputeReportsForm.setSurveyorAdd(dispute.getSurveyorAdd());
		disputeReportsForm.setSurveyorEmail(dispute.getSurveyorEmail());
		disputeReportsForm.setSurveyorPhone(dispute.getSurveyorPhone());
		disputeReportsForm.setDefectType(dispute.getDefectType());
		disputeReportsForm.setDefectQty(dispute.getDefectQty());
		disputeReportsForm.setDefectCostForGoods(dispute.getDefectCostForGoods());
		disputeReportsForm.setDamageStatus(dispute.getDamageStatus());
		disputeReportsForm.setReplacement(dispute.getReplacement());
		disputeReportsForm.setRepairCost(dispute.getRepairCost());
		disputeReportsForm.setAccept(dispute.getAccept());
		disputeReportsForm.setArbNames(dispute.getArbNames());
		disputeReportsForm.setLoc(dispute.getLoc());
		disputeReportsForm.setJudgement(dispute.getJudgement());
		disputeReportsForm.setJudgDate(dispute.getJudgDate());
		disputeReportsForm.setCompliedDate(dispute.getCompliedDate());
		disputeReportsForm.setArbCost(dispute.getArbCost());
		disputeReportsForm.setArbQty(dispute.getArbQty());
		disputeReportsForm.setArbStartDate(dispute.getArbStartDate());
		disputeReportsForm.setArbEndDate(dispute.getArbEndDate());
		disputeReportsForm.setDocSub(dispute.getDocSub());
		disputeReportsForm.setMoneyPaid(dispute.getMoneyPaid());
		disputeReportsForm.setPayDate(dispute.getPayDate());
		disputeReportsForm.setGoodsReplaced(dispute.getGoodsReplaced());
		disputeReportsForm.setRepacedDate(dispute.getRepacedDate());

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		return new ModelAndView("addOrModifyReportAppMngView", "model", model);
	}

	@RequestMapping(value = "/addOrModifyReportAppMngCompare", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportCompare(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		DisputeReports dispute = disputeReportsDAO.getByDisputeReportsId(id);

		disputeReportsForm.setId(dispute.getId());
		disputeReportsForm.setCustomerName(dispute.getCustomerName());
		disputeReportsForm.setPokey(dispute.getPokey());
		disputeReportsForm.setSupplierName(dispute.getSupplierName());
		disputeReportsForm.setGoods(dispute.getGoods());
		disputeReportsForm.setInsDetails(dispute.getInsDetails());
		disputeReportsForm.setCost(dispute.getCost());
		disputeReportsForm.setDisputekey(dispute.getDisputekey());
		disputeReportsForm.setGoodsDetails(dispute.getGoodsDetails());
		disputeReportsForm.setTransactionId(dispute.getTransactionId());
		disputeReportsForm.setShipper(dispute.getShipper());
		disputeReportsForm.setTransportType(dispute.getModeOfTransport());
		disputeReportsForm.setModeOfTransport(dispute.getModeOfTransport());
		disputeReportsForm.setTermsCond(dispute.getTermsCond());
		disputeReportsForm.setInclusion(dispute.getInclusion());
		disputeReportsForm.setExclusion(dispute.getExclusion());
		disputeReportsForm.setSurveyorName(dispute.getSurveyorName());
		disputeReportsForm.setSurveyorCom(dispute.getSurveyorCom());
		disputeReportsForm.setSurveyorAdd(dispute.getSurveyorAdd());
		disputeReportsForm.setSurveyorEmail(dispute.getSurveyorEmail());
		disputeReportsForm.setSurveyorPhone(dispute.getSurveyorPhone());
		disputeReportsForm.setDefectType(dispute.getDefectType());
		disputeReportsForm.setDefectQty(dispute.getDefectQty());
		disputeReportsForm.setDefectCostForGoods(dispute.getDefectCostForGoods());
		disputeReportsForm.setDamageStatus(dispute.getDamageStatus());
		disputeReportsForm.setReplacement(dispute.getReplacement());
		disputeReportsForm.setRepairCost(dispute.getRepairCost());

		disputeReportsForm.setSuppSurveyorName(dispute.getSuppSurveyorName());
		disputeReportsForm.setSuppSurveyorCom(dispute.getSuppSurveyorCom());
		disputeReportsForm.setSuppSurveyorAdd(dispute.getSuppSurveyorAdd());
		disputeReportsForm.setSuppSurveyorEmail(dispute.getSuppSurveyorEmail());
		disputeReportsForm.setSuppSurveyorPhone(dispute.getSuppSurveyorPhone());
		disputeReportsForm.setSuppDefectType(dispute.getSuppDefectType());
		disputeReportsForm.setSuppDefectQty(dispute.getSuppDefectQty());
		disputeReportsForm.setSuppDefectCostForGoods(dispute.getSuppDefectCostForGoods());
		disputeReportsForm.setSuppDamageStatus(dispute.getSuppDamageStatus());
		disputeReportsForm.setSuppReplacement(dispute.getSuppReplacement());
		disputeReportsForm.setSuppRepairCost(dispute.getSuppRepairCost());
		disputeReportsForm.setAccept(dispute.getAccept());

		Float total = dispute.getDefectQty() - dispute.getSuppDefectQty();
		Float total1 = dispute.getRepairCost() - dispute.getSuppRepairCost();

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);
		model.put("total", total);
		model.put("total1", total1);

		return new ModelAndView("addOrModifyReportAppMngCompare", "model", model);
	}

	@RequestMapping(value = "/appMngPoList", method = RequestMethod.GET)
	public ModelAndView fullPoList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PurchaseOrder> poList = purchaseOrderDAO.getList().getResultList();

		model.put("user", user);
		if (poList != null && poList.size() > 0) {
			model.put("poList", poList);

			return new ModelAndView("appMngPoList", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/buyerClientApprovalList", method = RequestMethod.GET)
	public ModelAndView buyerCustomerHeadBranchList(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		newBuyerForm.setEndUser(user);
		newBuyerForm.setName(user.getCustomerHeadName());
		newBuyerForm.setbName(user.getUserName());

		List<NewBuyer> newbuyerList = newBuyerDAOImpl.getForcApprovalByCompanyId(user.getCompanyId()).getResultList();

		model.put("user", user);
		model.put("newbuyerList", newbuyerList);
		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("buyerClientApprovalList", "model", model);
	}

	@RequestMapping(value = "/buyerClientPageShowApproval", method = RequestMethod.GET)
	public ModelAndView buyerPageShow(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		NewBuyer buyer = newBuyerDAOImpl.findId(id);
		Company company = companyDAO.getByCompanyId(buyer.getCompanyId());

		newBuyerForm.setId(buyer.getId());
		newBuyerForm.setBuyerName(buyer.getBuyerName());
		newBuyerForm.setName(buyer.getName());
		newBuyerForm.setbName(buyer.getbName());
		newBuyerForm.setBank(buyer.getBank());
		newBuyerForm.setBranch(buyer.getBranch());
		newBuyerForm.setBankEmail(buyer.getBankEmail());
		newBuyerForm.setUniqueKey(buyer.getUniqueKey());
		newBuyerForm.setCompanyName(company.getCompanyName());
		newBuyerForm.setContactNum(buyer.getContactNum());
		newBuyerForm.setEmail(buyer.getEmail());
		newBuyerForm.setcStatus(buyer.getcStatus());

		model.put("user", user);

		model.put("user", user);

		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("buyerClientPageShowApproval", "model", model);
	}

	@RequestMapping(value = "/buyerClientPageShowConfirmApproval", method = RequestMethod.POST)
	public ModelAndView approveBankEmpConfrim(@ModelAttribute NewBuyerForm newBuyerForm, ModelMap model,
											  RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("buyerClientPageShowConfirmApproval", "model", model);
	}

	@RequestMapping(value = "/updatebuyerClientPageShowConfrim", method = RequestMethod.POST)
	public ModelAndView updatebuyerPageShowConfrim(@ModelAttribute NewBuyerForm newBuyerForm, ModelMap model,
												   RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		
		newBuyerForm.setTransactionId(KeyGenerator.generateTransactionKey());
		newBuyerForm.setUniqueKey(KeyGenerator.generateTransactionKey());

		model.put("user", user);
		NewBuyer buyer = newBuyerDAOImpl.findId(newBuyerForm.getId());
		Transaction transaction = new Transaction();
		buyer.setId(newBuyerForm.getId());
		buyer.setBuyerName(newBuyerForm.getBuyerName());
		buyer.setName(newBuyerForm.getName());
		buyer.setbName(newBuyerForm.getbName());
		buyer.setContactNum(newBuyerForm.getContactNum());
		buyer.setUniqueKey(newBuyerForm.getUniqueKey());
		buyer.setEmail(newBuyerForm.getEmail());
		buyer.setcStatus(newBuyerForm.getcStatus());
		buyer.setComment(newBuyerForm.getComment());
		buyer.setTransactionId(newBuyerForm.getTransactionId());
		transaction.setTransactionId(newBuyerForm.getTransactionId());
		transaction.setTransactionType("Buyer Details Update Status");
		transaction.setTransactionStatus("Buyer  Deatils  status saved successfully");
		if (newBuyerForm.getcStatus().equals("Approved")) {
			buyer.setStatus("Pending");
		}

		transcationDAOImpl.insertTransaction(transaction);
		newBuyerDAOImpl.update(buyer);

		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("buyerPageSuccessApproval", "model", model);
	}
	// Supplier List

	@RequestMapping(value = "/supplierClientApprovalList", method = RequestMethod.GET)
	public ModelAndView supplierCustomerHeadBranchList(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		supplierForm.setEndUser(user);
		supplierForm.setName(user.getCustomerHeadName());
		supplierForm.setbName(user.getUserName());

		List<Supplier> supplierList = supplierDAOImpl.getForcApprovalByCompany(user.getCompanyId()).getResultList();

		model.put("user", user);
		if (supplierList != null && supplierList.size() > 0) {
			model.put("supplierList", supplierList);
			model.put("supplierForm", supplierForm);

			return new ModelAndView("supplierClientApprovalList", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/supplierClientPageShowConfirmApproval", method = RequestMethod.GET)
	public ModelAndView supplierPageShow(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		Supplier supplier = supplierDAOImpl.findId(id);

		supplierForm.setId(supplier.getId());
		supplierForm.setContactNum(supplier.getContactNum());
		supplierForm.setBank(supplier.getBank());
		supplierForm.setBranch(supplier.getBranch());
		supplierForm.setBankEmail(supplier.getBankEmail());
		supplierForm.setCompanyName(supplier.getCompanyName());
		supplierForm.setEmail(supplier.getEmail());
		supplierForm.setSupplierName(supplier.getSupplierName());
		supplierForm.setbName(supplier.getbName());
		supplierForm.setName(supplier.getName());
		supplierForm.setUniquekey(supplier.getUniquekey());
		supplierForm.setComment(supplier.getComment());
		supplierForm.setcStatus(supplier.getcStatus());
		model.put("user", user);
		model.put("supplierForm", supplierForm);

		return new ModelAndView("supplierClientPageShowConfirmApproval", "model", model);
	}

	@RequestMapping(value = "/supplierClientPageShowApprovalConfirm", method = RequestMethod.POST)
	public ModelAndView approvesupplierPageShowConfirm(@ModelAttribute SupplierForm supplierForm, ModelMap model,
													   RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("supplierClientPageShowApprovalConfirm", "model", model);
	}

	@RequestMapping(value = "/updatesupplierPageShowConfrim", method = RequestMethod.POST)
	public ModelAndView updatesupplierPageShowConfrim(@ModelAttribute SupplierForm supplierForm, ModelMap model,
													  RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		Supplier supplier = supplierDAOImpl.findId(supplierForm.getId());
		Transaction transaction = new Transaction();
		supplier.setId(supplierForm.getId());
		supplier.setName(supplierForm.getName());
		supplier.setbName(supplierForm.getbName());
		supplier.setUniquekey(supplierForm.getUniquekey());
		supplier.setSupplierName(supplierForm.getSupplierName());
		//supplier.setCompanyName(supplierForm.getCompanyName());
		supplier.setContactNum(supplierForm.getContactNum());
		supplier.setEmail(supplierForm.getEmail());
		supplier.setcStatus(supplierForm.getcStatus());
		supplier.setTransactionId(supplierForm.getTransactionId());
		transaction.setTransactionId(supplierForm.getTransactionId());
		transaction.setTransactionType("Supplier Deatils Update Status");
		transaction.setTransactionStatus("Supplier Deatils  status saved successfully");
		transcationDAOImpl.insertTransaction(transaction);

		if (supplierForm.getcStatus().equals("Approved")) {
			supplier.setStatus("Pending");
		}

		supplierDAOImpl.update(supplier);

		model.put("supplierForm", supplierForm);

		return new ModelAndView("supplierPageSuccessApproval", "model", model);
	}

	@RequestMapping(value = "/invoiceListForcApproval", method = RequestMethod.GET)
	public ModelAndView invoiceListForApproval(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		model.put("user", user);

		List<Invoice> invoice = invoiceDAO.getInvoiceByStatus().getResultList();

		if (invoice != null && invoice.size() > 0) {
			model.put("invoice", invoice);
			return new ModelAndView("invoiceListForcApproval", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/invoiceForcApproval", method = RequestMethod.GET)
	public ModelAndView invoiceForApproval(@RequestParam("id") Long id, ModelMap model) {

		Invoice invoice = invoiceDAO.getByInvoiceId(id);

		invoiceForm.setId(invoice.getId());
		invoiceForm.setCustomerName(invoice.getCustomerName());
		invoiceForm.setCustomerHeadName(invoice.getCustomerHeadName());
		invoiceForm.setMasterKey(invoice.getMasterKey());
		invoiceForm.setStatus(invoice.getStatus());
		invoiceForm.setGoods(invoice.getGoods());
		invoiceForm.setQuantity(invoice.getQuantity());
		invoiceForm.setBuyerName(invoice.getBuyerName());
		invoiceForm.setTransactionId(invoice.getTransactionId());
		invoiceForm.setAmount(invoice.getAmount());
		invoiceForm.setCustomerHeadEmail(invoice.getCustomerHeadEmail());
		invoiceForm.setCustomerBranchEmail(invoice.getCustomerBranchEmail());
		invoiceForm.setBuyerEmail(invoice.getBuyerEmail());
		invoiceForm.setInsuranceAmount(invoice.getInsuranceAmount());
		invoiceForm.setInsuranceDetails(invoice.getInsuranceDetails());
		invoiceForm.setInsuranceType(invoice.getInsuranceType());
		invoiceForm.setStartDate(invoice.getStartDate());
		invoiceForm.setEndDate(invoice.getEndDate());
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("invoiceForcApproval", "model", model);

	}

	@RequestMapping(value = "/invoiceForcApprovalConfirm", method = RequestMethod.POST)
	public ModelAndView invoiceForApprovalConfirm(ModelMap model, @ModelAttribute InvoiceForm invoiceForm,
												  RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		invoiceForm.setCustomerName(invoiceForm.getCustomerName());
		invoiceForm.setCustomerHeadName(invoiceForm.getCustomerHeadName());
		invoiceForm.setMasterKey(invoiceForm.getMasterKey());
		invoiceForm.setStatus(invoiceForm.getStatus());
		invoiceForm.setGoods(invoiceForm.getGoods());
		invoiceForm.setQuantity(invoiceForm.getQuantity());
		invoiceForm.setBuyerName(invoiceForm.getBuyerName());
		invoiceForm.setComment(invoiceForm.getComment());
		invoiceForm.setTransactionId(invoiceForm.getTransactionId());
		invoiceForm.setAmount(invoiceForm.getAmount());
		invoiceForm.setCustomerHeadEmail(invoiceForm.getCustomerHeadEmail());
		invoiceForm.setCustomerBranchEmail(invoiceForm.getCustomerBranchEmail());
		invoiceForm.setBuyerEmail(invoiceForm.getBuyerEmail());
		invoiceForm.setInsuranceAmount(invoiceForm.getInsuranceAmount());
		invoiceForm.setInsuranceDetails(invoiceForm.getInsuranceDetails());
		invoiceForm.setInsuranceType(invoiceForm.getInsuranceType());

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("invoiceForcApprovalConfirm", "model", model);

	}

	@RequestMapping(value = "/invoiceForcApprovalPost", method = RequestMethod.POST)
	public ModelAndView invoiceForApprovalPost(ModelMap model, @ModelAttribute InvoiceForm invoiceForm,
											   RedirectAttributes attributes) {

		invoiceForm.setMasterKey(invoiceForm.getMasterKey());
		invoiceForm.setCustomerName(invoiceForm.getCustomerName());
		invoiceForm.setGoods(invoiceForm.getGoods());

		EndUser user = getCurrentLoggedUserDetails();
		invoiceForm.setTransactionId(invoiceForm.getTransactionId());
		invoiceForm.setCustomerHeadEmail(invoiceForm.getCustomerHeadEmail());
		invoiceForm.setCustomerBranchEmail(invoiceForm.getCustomerBranchEmail());
		invoiceForm.setBuyerEmail(invoiceForm.getBuyerEmail());

		Invoice invoice = invoiceDAO.getByInvoiceId(invoiceForm.getId());

		invoice.setStatus(invoiceForm.getStatus());
		invoice.setComment(invoiceForm.getComment());
		invoice.setRequestMoney("No");

		invoiceDAO.updateInvoice(invoice);

		Transaction trans = new Transaction();
		trans.setTransactionId(invoiceForm.getTransactionId());
		trans.setTransactionStatus("Invoice Approval/Reject By Manager");
		trans.setTransactionType("Submitted Successfully");

		transactionDAO.insertTransaction(trans);

		Notification not = new Notification();

		not.setCustomerName(invoiceForm.getCustomerName());
//		Date notDate = new Date();
		Date notDate = DateService.loginDate;
		not.setNotificationDate(notDate);
		not.setNotificationType("Invoice Approval");
		not.setNotificationDescription(invoiceForm.getStatus());
		not.setNotificationAcc("main");

		notificationDAO.insertNotification(not);
		invoiceForm.setQuantity(invoiceForm.getQuantity());
		invoiceForm.setAmount(invoiceForm.getAmount());

		List<EndUser> userList = endUserDAOImpl.getByBankDetails().getResultList();
		String mail = userList.get(0).getEmail();

		List<SellerBuyingCost> byinglist = sellerBuyingCostDAO
				.getSellerBuyingCostbygoodsList(invoiceForm.getMasterKey(), invoiceForm.getGoods()).getResultList();

		String bankName = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0).getBankName();

		if (byinglist != null && byinglist.size() > 0) {
			Float costperitem = byinglist.get(0).getCostperUnit();

			List<MasterPlan> masterlist = masterPlanDAO.getMasterPlanByMasterKey(invoiceForm.getMasterKey())
					.getResultList();

			Float sCost = invoiceForm.getAmount();
			Float sQty = Float.parseFloat(invoiceForm.getQuantity());

			Float sPerItem = sCost / sQty;
			Float costper = costperitem * 0.1f;
			Float upperInvoiceper = costperitem + costper;
			Float lowerInvoiceper = costperitem - costper;

			if (sPerItem >= upperInvoiceper || sPerItem <= lowerInvoiceper) {
				try {
					Float perItem = byinglist.get(0).getCostperUnit();
					String customerName = masterlist.get(0).getCustomer();
					String masterKey = masterlist.get(0).getMasterKey();

					String tex = "Cost Details Notification";
					SimpleMailMessage emails = new SimpleMailMessage();
					emails.setTo(mail);
					emails.setSubject(tex);
					emails.setText("Hello " + "Bank"

							+ "\n\nCustomer Name :" + customerName + "\n\nMatser Key :" + masterKey +

							"\n\n Cost Details :" + "\n" +

							"\n Cost Per Item :" + perItem +

							"\n\n Inovice Details :" + "\n" + "\nSelling Cost :" + sCost + "\nSelling Quantity :" + sQty
							+ "\nCost Per Item :" + sPerItem +

							"\n\n\nRegards,\n"+bankName);
					System.out.println("" + mail + customerName);
					mailSender.send(emails);
					SimpleMailMessage emails1 = new SimpleMailMessage();
					emails1.setTo(mail);
					model.put("user", user);
					model.put("invoiceForm", invoiceForm);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("invoiceForcApprovalTransaction", "model", model);

	}

	@RequestMapping(value = "/invoiceForcApprovalTransaction", method = RequestMethod.GET)
	public ModelAndView invoiceForApprovalTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("invoiceForcApprovalTransaction", "model", model);

	}
       /* ------------------------------------------------------------->*/
	@RequestMapping(value = "/salesOrderList", method = RequestMethod.GET)
	public ModelAndView salesOrderList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<SalesOrder> salesOrderList = salesOrderDAO.getList().getResultList();

		model.put("user", user);
		if (salesOrderList != null && salesOrderList.size() > 0) {
			model.put("salesOrderList", salesOrderList);

			return new ModelAndView("fullSalesOrderList", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/salesOrderListForcApproval", method = RequestMethod.GET)
	public ModelAndView salesOrderListForcApproval(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		model.put("user", user);

		List<SalesOrder> salesOrder = salesOrderDAO.getSalesOrderByStatus().getResultList();

		if (salesOrder != null && salesOrder.size() > 0) {
			model.put("salesOrder", salesOrder);
			return new ModelAndView("salesOrderListForcApproval", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/salesOrderForcApproval", method = RequestMethod.GET)
	public ModelAndView salesOrderForcApproval(@RequestParam("id") Long id, ModelMap model) {

		SalesOrder salesOrder = salesOrderDAO.getBySalesOrderId(id);

		salesOrderForm.setId(salesOrder.getId());
		salesOrderForm.setCustomerName(salesOrder.getCustomerName());
		salesOrderForm.setCustomerHeadName(salesOrder.getCustomerHeadName());
		salesOrderForm.setMasterPlanKey(salesOrder.getMasterPlanKey());
		salesOrderForm.setStatus(salesOrder.getStatus());
		salesOrderForm.setGoods(salesOrder.getGoods());
		salesOrderForm.setQuantity(salesOrder.getQuantity());
		salesOrderForm.setBuyerName(salesOrder.getBuyerName());
		salesOrderForm.setTransactionId(salesOrder.getTransactionId());
		salesOrderForm.setAmount(salesOrder.getAmount());
		salesOrderForm.setCustomerHeadEmail(salesOrder.getCustomerHeadEmail());
		salesOrderForm.setCustomerBranchEmail(salesOrder.getCustomerBranchEmail());
		salesOrderForm.setBuyerEmail(salesOrder.getBuyerEmail());
		salesOrderForm.setInsuranceAmount(salesOrder.getInsuranceAmount());
		salesOrderForm.setInsuranceDetails(salesOrder.getInsuranceDetails());
		salesOrderForm.setInsuranceType(salesOrder.getInsuranceType());
		salesOrderForm.setStartDate(salesOrder.getStartDate());
		salesOrderForm.setEndDate(salesOrder.getEndDate());
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("salesOrderForm", salesOrderForm);

		return new ModelAndView("salesOrderForcApproval", "model", model);

	}

	@RequestMapping(value = "/salesOrderForcApprovalConfirm", method = RequestMethod.POST)
	public ModelAndView salesOrderForcApprovalConfirm(ModelMap model, @ModelAttribute SalesOrderForm salesOrderForm,
													  RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		salesOrderForm.setCustomerName(salesOrderForm.getCustomerName());
		salesOrderForm.setCustomerHeadName(salesOrderForm.getCustomerHeadName());
		salesOrderForm.setMasterPlanKey(salesOrderForm.getMasterPlanKey());
		salesOrderForm.setStatus(salesOrderForm.getStatus());
		salesOrderForm.setGoods(salesOrderForm.getGoods());
		salesOrderForm.setQuantity(salesOrderForm.getQuantity());
		salesOrderForm.setBuyerName(salesOrderForm.getBuyerName());
		salesOrderForm.setComment(salesOrderForm.getComment());
		salesOrderForm.setTransactionId(salesOrderForm.getTransactionId());
		salesOrderForm.setAmount(salesOrderForm.getAmount());
		salesOrderForm.setCustomerHeadEmail(salesOrderForm.getCustomerHeadEmail());
		salesOrderForm.setCustomerBranchEmail(salesOrderForm.getCustomerBranchEmail());
		salesOrderForm.setBuyerEmail(salesOrderForm.getBuyerEmail());
		salesOrderForm.setInsuranceAmount(salesOrderForm.getInsuranceAmount());
		salesOrderForm.setInsuranceDetails(salesOrderForm.getInsuranceDetails());
		salesOrderForm.setInsuranceType(salesOrderForm.getInsuranceType());

		model.put("user", user);
		model.put("salesOrderForm", salesOrderForm);

		return new ModelAndView("salesOrderForcApprovalConfirm", "model", model);

	}

	@RequestMapping(value = "/salesOrderForcApproval", method = RequestMethod.POST)
	public ModelAndView salesOrderForcApproval(ModelMap model, @ModelAttribute SalesOrderForm salesOrderForm,
												   RedirectAttributes attributes) {

		salesOrderForm.setMasterPlanKey(salesOrderForm.getMasterPlanKey());
		salesOrderForm.setCustomerName(salesOrderForm.getCustomerName());
		salesOrderForm.setGoods(salesOrderForm.getGoods());

		EndUser user = getCurrentLoggedUserDetails();
		salesOrderForm.setTransactionId(salesOrderForm.getTransactionId());
		salesOrderForm.setCustomerHeadEmail(salesOrderForm.getCustomerHeadEmail());
		salesOrderForm.setCustomerBranchEmail(salesOrderForm.getCustomerBranchEmail());
		salesOrderForm.setBuyerEmail(salesOrderForm.getBuyerEmail());

		SalesOrder salesOrder = salesOrderDAO.getBySalesOrderId(salesOrderForm.getId());

		salesOrder.setStatus(salesOrderForm.getStatus());
		salesOrder.setComment(salesOrderForm.getComment());
		salesOrder.setRequestMoney("No");

		salesOrderDAO.updateSalesOrder(salesOrder);

		Transaction trans = new Transaction();
		trans.setTransactionId(salesOrderForm.getTransactionId());
		trans.setTransactionStatus("SalesOrder Approval/Reject By Manager");
		trans.setTransactionType("Submitted Successfully");

		transactionDAO.insertTransaction(trans);

		Notification not = new Notification();

		not.setCustomerName(salesOrderForm.getCustomerName());
		Date notDate = DateService.loginDate;
		not.setNotificationDate(notDate);
		not.setNotificationType("SalesOrder Approval");
		not.setNotificationDescription(salesOrderForm.getStatus());
		not.setNotificationAcc("main");

		notificationDAO.insertNotification(not);
		salesOrderForm.setQuantity(salesOrderForm.getQuantity());
		salesOrderForm.setAmount(salesOrderForm.getAmount());

		List<EndUser> userList = endUserDAOImpl.getByBankDetails().getResultList();
		String mail = userList.get(0).getEmail();

		List<SellerBuyingCost> byinglist = sellerBuyingCostDAO
				.getSellerBuyingCostbygoodsList(salesOrderForm.getMasterPlanKey(), salesOrderForm.getGoods()).getResultList();

		String bankName = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0).getBankName();

		if (byinglist != null && byinglist.size() > 0) {
			Float costperitem = byinglist.get(0).getCostperUnit();

			List<MasterPlan> masterlist = masterPlanDAO.getMasterPlanByMasterKey(salesOrderForm.getMasterPlanKey())
					.getResultList();

			Float sCost = salesOrderForm.getAmount();
			Float sQty = Float.parseFloat(salesOrderForm.getQuantity());

			Float sPerItem = sCost / sQty;
			Float costper = costperitem * 0.1f;
			Float upperInvoiceper = costperitem + costper;
			Float lowerInvoiceper = costperitem - costper;

			if (sPerItem >= upperInvoiceper || sPerItem <= lowerInvoiceper) {
				try {
					Float perItem = byinglist.get(0).getCostperUnit();
					String customerName = masterlist.get(0).getCustomer();
					String masterKey = masterlist.get(0).getMasterKey();

					String tex = "Cost Details Notification";
					SimpleMailMessage emails = new SimpleMailMessage();
					emails.setTo(mail);
					emails.setSubject(tex);
					emails.setText("Hello " + "Bank"

							+ "\n\nCustomer Name :" + customerName + "\n\nMatser Key :" + masterKey +

							"\n\n Cost Details :" + "\n" +

							"\n Cost Per Item :" + perItem +

							"\n\n Inovice Details :" + "\n" + "\nSelling Cost :" + sCost + "\nSelling Quantity :" + sQty
							+ "\nCost Per Item :" + sPerItem +

							"\n\n\nRegards,\n"+bankName);
					System.out.println("" + mail + customerName);
					mailSender.send(emails);
					SimpleMailMessage emails1 = new SimpleMailMessage();
					emails1.setTo(mail);
					model.put("user", user);
					model.put("salesOrderForm", salesOrderForm);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		model.put("user", user);
		model.put("salesOrderForm", salesOrderForm);

		return new ModelAndView("salesOrderForcApprovalTransaction", "model", model);

	}

	@RequestMapping(value = "/salesOrderForcApprovalTransaction", method = RequestMethod.GET)
	public ModelAndView salesOrderForApprovalTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("salesOrderForm", salesOrderForm);

		return new ModelAndView("invoiceForcApprovalTransaction", "model", model);

	}

	@RequestMapping(value = "/fullcInvoiceList", method = RequestMethod.GET)
	public ModelAndView fullInvoiceList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<Invoice> invoiceList = invoiceDAO.getList().getResultList();

		model.put("user", user);
		if (invoiceList != null && invoiceList.size() > 0) {
			model.put("invoiceList", invoiceList);

			return new ModelAndView("fullcInvoiceList", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/masterPlanRePaymentAppList", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Repayment> repayList = repaymenyDAO.getRepayByIdAndStatus().getResultList();

		if (repayList != null && repayList.size() > 0) {
			model.put("repayList", repayList);

			return new ModelAndView("masterPlanRePaymentAppList", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/masterPlanRePaymentAppApprove", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentAppApprove(@RequestParam Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		Repayment master = repaymenyDAO.getByRepaymentId(id);

		repaymentForm.setId(master.getId());
		repaymentForm.setMasterKey(master.getMasterKey());
		repaymentForm.setBuyingCostSanc(master.getBuyingCostSanc());
		repaymentForm.setWcSancAmount(master.getWcSancAmount());
		repaymentForm.setCustomer(master.getCustomer());
		repaymentForm.setCustomerEmail(master.getCustomerEmail());
		repaymentForm.setBuyingCostDate(master.getBuyingCostDate());
		repaymentForm.setTenure(master.getTenure());
		repaymentForm.setRateOfInt1(master.getRateOfInt1());
		repaymentForm.setAmtType(master.getAmtType());
		repaymentForm.setPayOption(master.getPayOption());
		repaymentForm.setFunalAmt(master.getFunalAmt());
		repaymentForm.setWcTotalInterest(master.getWcTotalInterest());
		repaymentForm.setTransactionId(master.getTransactionId());

		model.put("repaymentForm", repaymentForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentAppApprove", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentAppApproveConfirm", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentAppApproveConfirm(ModelMap model,
															 @ModelAttribute RepaymentForm repaymentForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		repaymentForm.setId(repaymentForm.getId());
		repaymentForm.setMasterKey(repaymentForm.getMasterKey());
		repaymentForm.setBuyingCostSanc(repaymentForm.getBuyingCostSanc());
		repaymentForm.setWcSancAmount(repaymentForm.getWcSancAmount());
		repaymentForm.setCustomer(repaymentForm.getCustomer());
		repaymentForm.setCustomerEmail(repaymentForm.getCustomerEmail());
		repaymentForm.setBuyingCostDate(repaymentForm.getBuyingCostDate());
		repaymentForm.setTenure(repaymentForm.getTenure());
		repaymentForm.setRateOfInt1(repaymentForm.getRateOfInt1());
		repaymentForm.setPayOption(repaymentForm.getPayOption());
		repaymentForm.setcStatus(repaymentForm.getcStatus());
		repaymentForm.setcComment(repaymentForm.getcComment());
		repaymentForm.setTransactionId(repaymentForm.getTransactionId());
		repaymentForm.setFunalAmt(repaymentForm.getFunalAmt());
		repaymentForm.setWcTotalInterest(repaymentForm.getWcTotalInterest());
		model.put("repaymentForm", repaymentForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentAppApproveConfirm", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentAppApproveSave", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentAppApproveSave(ModelMap model, @ModelAttribute RepaymentForm repaymentForm,
														  BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		Repayment repay = repaymenyDAO.getByRepaymentId(repaymentForm.getId());

		repay.setcStatus(repaymentForm.getcStatus());
		repay.setcComment(repaymentForm.getcComment());
		repay.setbStatus("Pending");
		repaymentForm.setTransactionId(repaymentForm.getTransactionId());
		repaymenyDAO.updateRepayment(repay);

		model.put("repaymentForm", repaymentForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentAppTransaction", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentAppTransaction", method = RequestMethod.GET)
	public ModelAndView clientAdminTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("repaymentForm", repaymentForm);

		return new ModelAndView("masterPlanRePaymentAppTransaction", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentFullList", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Repayment> repayList = repaymenyDAO.getList().getResultList();

		if (repayList != null && repayList.size() > 0) {
			model.put("repayList", repayList);

			return new ModelAndView("masterPlanRePaymentFullList", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/poPaymentCustAppList", method = RequestMethod.GET)
	public ModelAndView poPaymentBankList(ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> purchaseList = purchaseOrderDAO.getList().getResultList();
		if (purchaseList != null && purchaseList.size() > 0) {

			model.put("purchaseList", purchaseList);
			return new ModelAndView("poPaymentCustAppList");
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/poPaymentCustAppClear", method = RequestMethod.GET)
	public ModelAndView poPaymentHead(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(id);

		List<LetterOfCredit> letter = letterOfCreditDAO.getLCListByPoKey(purchase.getPoKey()).getResultList();
		if (letter != null && letter.size() > 0) {

			letterOfCreditForm.setSupplierName(letter.get(0).getSupplierName());
			letterOfCreditForm.setCustomerHeadName(letter.get(0).getCustomerHeadName());
			letterOfCreditForm.setCustomerName(letter.get(0).getCustomerName());
			letterOfCreditForm.setPoKey(letter.get(0).getPoKey());
			letterOfCreditForm.setSupplierEmail(letter.get(0).getSupplierEmail());
			letterOfCreditForm.setCustomerHeadEmail(letter.get(0).getCustomerHeadEmail());
			letterOfCreditForm.setCustomerBranchEmail(letter.get(0).getCustomerBranchEmail());
			letterOfCreditForm.setAmount(letter.get(0).getAmount());
			letterOfCreditForm.setTransactionId(letter.get(0).getTransactionId());
			letterOfCreditForm.setTypeOfLc(letter.get(0).getTypeOfLc());
			letterOfCreditForm.setBankType(letter.get(0).getBankType());
			letterOfCreditForm.setBankName(letter.get(0).getBankName());
			letterOfCreditForm.setBankAddress(letter.get(0).getBankAddress());
			letterOfCreditForm.setBankBranch(letter.get(0).getBankBranch());
			letterOfCreditForm.setSwiftCode(letter.get(0).getSwiftCode());
			letterOfCreditForm.setAccNo(letter.get(0).getAccNo());
			letterOfCreditForm.setContactNum(letter.get(0).getContactNum());

			List<Correspondent> corrList = correspondentDAO.getCorrespondenttByPoKey(purchase.getPoKey())
					.getResultList();

			if (corrList != null && corrList.size() > 0) {
				model.put("corrList", corrList);
			}

			model.put("user", user);
			model.put("letterOfCreditForm", letterOfCreditForm);
		} else {
			attributes.addFlashAttribute("success", "Please Check Tpe Of Transfer ");
			return new ModelAndView("redirect:poPaymentCustAppList");
		}
		return new ModelAndView("poPaymentCustAppClear", "model", model);

	}

	@RequestMapping(value = "/requestMoneyInvoiceCustAppMngFullList", method = RequestMethod.GET)
	public ModelAndView requestMoneyInvoiceCustAppMngFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getList().getResultList();

		if (invoiceList != null && invoiceList.size() > 0) {
			model.put("invoiceList", invoiceList);
			return new ModelAndView("requestMoneyInvoiceCustAppMngFullList", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	/* Same bank Approval side for Buyer Events */
	@RequestMapping(value = "/sameBankEventPage", method = RequestMethod.GET)
	public ModelAndView getAllEvents(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		Collection<BuyerSameBankEvent> buyerSamebankList = buyersamebankservice.findAllupdateEvents();

		ModelAndView mav = new ModelAndView();

		model.put("user", user);

		if (buyerSamebankList != null && buyerSamebankList.size() > 0) {

			model.put("buyerSamebankList", buyerSamebankList);
			mav = new ModelAndView("sameBankEventPage", "model", model);

		} else {

			mav = new ModelAndView("noDataFoundINCusmApp", "model", model);

		}
		return mav;

	}

	@RequestMapping(value = "/updatesameBankEvent", method = RequestMethod.GET)
	public ModelAndView editsameBankEvent(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		BuyerSameBankEvent buyerSameBankEvent = buyersamebankservice.findEvent(id);
		List<PurchaseDoc> purchaseDocs = purchaseDocDAO.findPoKey(buyerSameBankEvent.getPoKey()).getResultList();
		List<PurchaseDocForm> purchaseDocForms = new ArrayList<PurchaseDocForm>();

		for (PurchaseDoc value : purchaseDocs) {

			PurchaseDocForm purchaseDocForm = new PurchaseDocForm();
			purchaseDocForm.setPoKey(value.getPoKey());
			purchaseDocForm.setDocName(value.getDocName());

			purchaseDocForm.setDescription(value.getDescription());

			purchaseDocForms.add(purchaseDocForm);
		}

		model.put("purchaseDocForms", purchaseDocForms);

		buyersameBankForm.setId(buyerSameBankEvent.getId());
		buyersameBankForm.setCustomerName(buyerSameBankEvent.getCustomerName());
		buyersameBankForm.setSupplier(buyerSameBankEvent.getSupplier());
		buyersameBankForm.setSupplierBank(buyerSameBankEvent.getSupplierBank());
		buyersameBankForm.setMasterKey(buyerSameBankEvent.getMasterKey());
		buyersameBankForm.setPoKey(buyerSameBankEvent.getPoKey());
		buyersameBankForm.setGoods(buyerSameBankEvent.getGoods());
		buyersameBankForm.setSanctionedAmount(buyerSameBankEvent.getSanctionedAmount());
		buyersameBankForm.setUtilizedAmount(buyerSameBankEvent.getUtilizedAmount());
		buyersameBankForm.setAvailableCost(buyerSameBankEvent.getAvailableCost());
		buyersameBankForm.setDate1(buyerSameBankEvent.getDate1());
		buyersameBankForm.setEvent1(buyerSameBankEvent.getEvent1());
		buyersameBankForm.setFirst(buyerSameBankEvent.getFirst());
		buyersameBankForm.setDate2(buyerSameBankEvent.getDate2());
		buyersameBankForm.setEvent2(buyerSameBankEvent.getEvent2());
		buyersameBankForm.setSecond(buyerSameBankEvent.getSecond());
		buyersameBankForm.setDate3(buyerSameBankEvent.getDate3());
		buyersameBankForm.setEvent3(buyerSameBankEvent.getEvent3());
		buyersameBankForm.setThird(buyerSameBankEvent.getThird());
		buyersameBankForm.setDate4(buyerSameBankEvent.getDate4());
		buyersameBankForm.setEvent4(buyerSameBankEvent.getEvent4());
		buyersameBankForm.setFourth(buyerSameBankEvent.getFourth());
		buyersameBankForm.setDate5(buyerSameBankEvent.getDate5());
		buyersameBankForm.setEvent5(buyerSameBankEvent.getEvent5());
		buyersameBankForm.setFifth(buyerSameBankEvent.getFifth());
		buyersameBankForm.setDate6(buyerSameBankEvent.getDate6());
		buyersameBankForm.setEvent6(buyerSameBankEvent.getEvent6());
		buyersameBankForm.setSix(buyerSameBankEvent.getSix());
		buyersameBankForm.setDate7(buyerSameBankEvent.getDate7());
		buyersameBankForm.setEvent7(buyerSameBankEvent.getEvent7());
		buyersameBankForm.setSeven(buyerSameBankEvent.getSeven());
		buyersameBankForm.setDate8(buyerSameBankEvent.getDate8());
		buyersameBankForm.setEvent8(buyerSameBankEvent.getEvent8());
		buyersameBankForm.setEight(buyerSameBankEvent.getEight());
		buyersameBankForm.setDate9(buyerSameBankEvent.getDate9());
		buyersameBankForm.setEvent9(buyerSameBankEvent.getEvent9());
		buyersameBankForm.setNine(buyerSameBankEvent.getNine());
		buyersameBankForm.setDate10(buyerSameBankEvent.getDate10());
		buyersameBankForm.setEvent10(buyerSameBankEvent.getEvent10());
		buyersameBankForm.setTen(buyerSameBankEvent.getTen());
		buyersameBankForm.setDate11(buyerSameBankEvent.getDate11());
		buyersameBankForm.setEvent11(buyerSameBankEvent.getEvent11());
		buyersameBankForm.setElven(buyerSameBankEvent.getElven());

		model.put("user", user);
		model.put("buyersameBankForm", buyersameBankForm);
		return new ModelAndView("editsameBankEvent", "model", model);

	}

	@RequestMapping(value = "/confirmEditsameBankEvent", method = RequestMethod.POST)
	public ModelAndView confirmEditsameBankEvent(ModelMap model,
												 @ModelAttribute BuyerSameBankEventForm buyersameBankForm, HttpServletRequest request,
												 RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PurchaseDoc> purchaseDocs = purchaseDocDAO.findPoKey(buyersameBankForm.getPoKey()).getResultList();
		List<PurchaseDocForm> purchaseDocForms = new ArrayList<PurchaseDocForm>();

		for (PurchaseDoc value : purchaseDocs) {

			PurchaseDocForm purchaseDocForm = new PurchaseDocForm();
			purchaseDocForm.setPoKey(value.getPoKey());
			purchaseDocForm.setDocName(value.getDocName());

			purchaseDocForm.setDescription(value.getDescription());

			purchaseDocForms.add(purchaseDocForm);
		}

		model.put("purchaseDocForms", purchaseDocForms);

		model.put("user", user);

		model.put("buyersameBankForm", buyersameBankForm);

		return new ModelAndView("confirmEditsameBankEvent", "model", model);

	}

	@RequestMapping(value = "/updatesameBankEvent", method = RequestMethod.POST)
	public ModelAndView getupdatesameBankEvent(ModelMap model,
											   @ModelAttribute BuyerSameBankEventForm buyersameBankForm) {
		EndUser user = getCurrentLoggedUserDetails();
		
		buyersameBankForm.setTransactionId(KeyGenerator.generateTransactionKey());
		buyersameBankForm.setUniqueId(KeyGenerator.generateTransactionKey());
		
		model.put("user", user);
		BuyerSameBankEvent buyerSameBankEvent = buyersamebankservice.findEvent(buyersameBankForm.getId());

		Transaction transaction = new Transaction();

		buyerSameBankEvent.setId(buyersameBankForm.getId());
		buyerSameBankEvent.setCustomerName(buyersameBankForm.getCustomerName());
		buyerSameBankEvent.setSupplier(buyersameBankForm.getSupplier());
		buyerSameBankEvent.setSupplierBank(buyersameBankForm.getSupplierBank());
		buyerSameBankEvent.setMasterKey(buyersameBankForm.getMasterKey());
		buyerSameBankEvent.setGoods(buyersameBankForm.getGoods());
		buyerSameBankEvent.setDate1(buyersameBankForm.getDate1());
		buyerSameBankEvent.setEvent1(buyersameBankForm.getEvent1());
		buyerSameBankEvent.setFirst(buyersameBankForm.getFirst());
		buyerSameBankEvent.setDate2(buyersameBankForm.getDate2());
		buyerSameBankEvent.setEvent2(buyersameBankForm.getEvent2());
		buyerSameBankEvent.setSecond(buyersameBankForm.getSecond());
		buyerSameBankEvent.setDate3(buyersameBankForm.getDate3());
		buyerSameBankEvent.setEvent3(buyersameBankForm.getEvent3());
		buyerSameBankEvent.setThird(buyersameBankForm.getThird());
		buyerSameBankEvent.setDate4(buyersameBankForm.getDate4());
		buyerSameBankEvent.setEvent4(buyersameBankForm.getEvent4());
		buyerSameBankEvent.setFourth(buyersameBankForm.getFourth());
		buyerSameBankEvent.setDate5(buyersameBankForm.getDate5());
		buyerSameBankEvent.setEvent5(buyersameBankForm.getEvent5());
		buyerSameBankEvent.setFifth(buyersameBankForm.getFifth());
		buyerSameBankEvent.setDate6(buyersameBankForm.getDate6());
		buyerSameBankEvent.setEvent6(buyersameBankForm.getEvent6());
		buyerSameBankEvent.setSix(buyersameBankForm.getSix());
		buyerSameBankEvent.setDate7(buyersameBankForm.getDate7());
		buyerSameBankEvent.setEvent7(buyersameBankForm.getEvent7());
		buyerSameBankEvent.setSeven(buyersameBankForm.getSeven());
		buyerSameBankEvent.setDate8(buyersameBankForm.getDate8());
		buyerSameBankEvent.setEvent8(buyersameBankForm.getEvent8());
		buyerSameBankEvent.setEight(buyersameBankForm.getEight());
		buyerSameBankEvent.setDate9(buyersameBankForm.getDate9());
		buyerSameBankEvent.setEvent9(buyersameBankForm.getEvent9());
		buyerSameBankEvent.setNine(buyersameBankForm.getNine());
		buyerSameBankEvent.setDate10(buyersameBankForm.getDate10());
		buyerSameBankEvent.setEvent10(buyersameBankForm.getEvent10());
		buyerSameBankEvent.setTen(buyersameBankForm.getTen());
		buyerSameBankEvent.setDate11(buyersameBankForm.getDate11());
		buyerSameBankEvent.setEvent11(buyersameBankForm.getEvent11());
		buyerSameBankEvent.setElven(buyersameBankForm.getElven());
		buyerSameBankEvent.setStatus(buyersameBankForm.getStatus());
		buyerSameBankEvent.setComment(buyersameBankForm.getComment());
		buyerSameBankEvent.setTransactionId(buyersameBankForm.getTransactionId());
		buyerSameBankEvent.setUniqueId(buyersameBankForm.getUniqueId());
		transaction.setTransactionId(buyersameBankForm.getTransactionId());

		transaction.setTransactionType("Buyer Same Bank Events");

		transaction.setTransactionStatus("Approval done by Approval manager");

		transactionDAO.insertTransaction(transaction);

		Date date = DateService.loginDate;

		buyerSameBankEvent.setApproveDate(date);

		buyersamebankservice.updateBuyerSameBankEvent(buyerSameBankEvent);

		model.put("buyersameBankForm", buyersameBankForm);

		return new ModelAndView("approveCusmAppSuccess", "model", model);
	}

	/* Diff bank Approval side for Buyer Events */
	@RequestMapping(value = "/buyerDiffrentBankPage", method = RequestMethod.GET)
	public ModelAndView getAllEventsB(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		ModelAndView mav = new ModelAndView();

		model.put("user", user);

		Collection<BuyerDiffBankEvent> buyerDiffBankEventList = buyerdiffrentbankService.findAllupdateEvents();

		if (buyerDiffBankEventList != null && buyerDiffBankEventList.size() > 0) {

			model.put("buyerDiffBankEventList", buyerDiffBankEventList);
			mav = new ModelAndView("buyerDiffrentBankPage", "model", model);

		} else {

			mav = new ModelAndView("noDataFoundINCusmApp", "model", model);

		}

		return mav;

	}

	@RequestMapping(value = "/updatediffrentBankEvent", method = RequestMethod.GET)
	public ModelAndView editdiffrentBankEvent(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		BuyerDiffBankEvent buyerDiffBankEvent = buyerdiffrentbankService.findEvent(id);

		List<PurchaseDoc> purchaseDocs = purchaseDocDAO.findPoKey(buyerDiffBankEvent.getPoKey()).getResultList();
		List<PurchaseDocForm> purchaseDocForms = new ArrayList<PurchaseDocForm>();

		for (PurchaseDoc value : purchaseDocs) {

			PurchaseDocForm purchaseDocForm = new PurchaseDocForm();
			purchaseDocForm.setPoKey(value.getPoKey());
			purchaseDocForm.setDocName(value.getDocName());

			purchaseDocForm.setDescription(value.getDescription());

			purchaseDocForms.add(purchaseDocForm);
		}
		model.put("purchaseDocForms", purchaseDocForms);

		buyerdiffBankForm.setId(buyerDiffBankEvent.getId());
		buyerdiffBankForm.setCustomerName(buyerDiffBankEvent.getCustomerName());
		buyerdiffBankForm.setSupplier(buyerDiffBankEvent.getSupplier());
		buyerdiffBankForm.setSupplierBank(buyerDiffBankEvent.getSupplierBank());
		buyerdiffBankForm.setMasterKey(buyerDiffBankEvent.getMasterKey());
		buyerdiffBankForm.setPoKey(buyerDiffBankEvent.getPoKey());
		buyerdiffBankForm.setGoods(buyerDiffBankEvent.getGoods());
		buyerdiffBankForm.setSanctionedAmount(buyerDiffBankEvent.getSanctionedAmount());
		buyerdiffBankForm.setUtilizedAmount(buyerDiffBankEvent.getUtilizedAmount());
		buyerdiffBankForm.setAvailableCost(buyerDiffBankEvent.getAvailableCost());
		buyerdiffBankForm.setDate1(buyerDiffBankEvent.getDate1());
		buyerdiffBankForm.setEvent1(buyerDiffBankEvent.getEvent1());
		buyerdiffBankForm.setFirst(buyerDiffBankEvent.getFirst());
		buyerdiffBankForm.setDate2(buyerDiffBankEvent.getDate2());
		buyerdiffBankForm.setEvent2(buyerDiffBankEvent.getEvent2());
		buyerdiffBankForm.setSecond(buyerDiffBankEvent.getSecond());
		buyerdiffBankForm.setDate3(buyerDiffBankEvent.getDate3());
		buyerdiffBankForm.setEvent3(buyerDiffBankEvent.getEvent3());
		buyerdiffBankForm.setThird(buyerDiffBankEvent.getThird());
		buyerdiffBankForm.setDate4(buyerDiffBankEvent.getDate4());
		buyerdiffBankForm.setEvent4(buyerDiffBankEvent.getEvent4());
		buyerdiffBankForm.setFourth(buyerDiffBankEvent.getFourth());
		buyerdiffBankForm.setDate5(buyerDiffBankEvent.getDate5());
		buyerdiffBankForm.setEvent5(buyerDiffBankEvent.getEvent5());
		buyerdiffBankForm.setFifth(buyerDiffBankEvent.getFifth());
		buyerdiffBankForm.setDate6(buyerDiffBankEvent.getDate6());
		buyerdiffBankForm.setEvent6(buyerDiffBankEvent.getEvent6());
		buyerdiffBankForm.setSix(buyerDiffBankEvent.getSix());
		buyerdiffBankForm.setDate7(buyerDiffBankEvent.getDate7());
		buyerdiffBankForm.setEvent7(buyerDiffBankEvent.getEvent7());
		buyerdiffBankForm.setSeven(buyerDiffBankEvent.getSeven());
		buyerdiffBankForm.setDate8(buyerDiffBankEvent.getDate8());
		buyerdiffBankForm.setEvent8(buyerDiffBankEvent.getEvent8());
		buyerdiffBankForm.setEight(buyerDiffBankEvent.getEight());
		buyerdiffBankForm.setDate9(buyerDiffBankEvent.getDate9());
		buyerdiffBankForm.setEvent9(buyerDiffBankEvent.getEvent9());
		buyerdiffBankForm.setNine(buyerDiffBankEvent.getNine());
		buyerdiffBankForm.setDate10(buyerDiffBankEvent.getDate10());
		buyerdiffBankForm.setEvent10(buyerDiffBankEvent.getEvent10());
		buyerdiffBankForm.setTen(buyerDiffBankEvent.getTen());
		buyerdiffBankForm.setDate11(buyerDiffBankEvent.getDate11());
		buyerdiffBankForm.setEvent11(buyerDiffBankEvent.getEvent11());
		buyerdiffBankForm.setElven(buyerDiffBankEvent.getElven());
		buyerdiffBankForm.setDate12(buyerDiffBankEvent.getDate12());
		buyerdiffBankForm.setEvent12(buyerDiffBankEvent.getEvent12());
		buyerdiffBankForm.setTwelve(buyerDiffBankEvent.getTwelve());
		buyerdiffBankForm.setDate13(buyerDiffBankEvent.getDate13());
		buyerdiffBankForm.setEvent13(buyerDiffBankEvent.getEvent13());
		buyerdiffBankForm.setThirteen(buyerDiffBankEvent.getThirteen());

		model.put("user", user);
		model.put("buyerdiffBankForm", buyerdiffBankForm);
		return new ModelAndView("editdiffBankEvent", "model", model);

	}

	@RequestMapping(value = "/confirmUpdatediffrentBankEvent", method = RequestMethod.POST)
	public ModelAndView confirmUpdatediffrentBankEvent(ModelMap model,
													   @ModelAttribute BuyerDiffBankEventForm buyerdiffBankForm, HttpServletRequest request,
													   RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PurchaseDoc> purchaseDocs = purchaseDocDAO.findPoKey(buyerdiffBankForm.getPoKey()).getResultList();
		List<PurchaseDocForm> purchaseDocForms = new ArrayList<PurchaseDocForm>();

		for (PurchaseDoc value : purchaseDocs) {

			PurchaseDocForm purchaseDocForm = new PurchaseDocForm();
			purchaseDocForm.setPoKey(value.getPoKey());
			purchaseDocForm.setDocName(value.getDocName());

			purchaseDocForm.setDescription(value.getDescription());

			purchaseDocForms.add(purchaseDocForm);
		}
		model.put("purchaseDocForms", purchaseDocForms);

		model.put("user", user);

		model.put("buyerdiffBankForm", buyerdiffBankForm);

		return new ModelAndView("confirmUpdatediffrentBankEvent", "model", model);

	}

	@RequestMapping(value = "/updatediffrentBankEvent", method = RequestMethod.POST)
	public ModelAndView getupdatediffrentBankEvent(ModelMap model,
												   @ModelAttribute BuyerDiffBankEventForm buyerdiffBankForm) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		
		buyerdiffBankForm.setTransactionId(KeyGenerator.generateTransactionKey());
		buyerdiffBankForm.setUniqueId(KeyGenerator.generateTransactionKey());

		Transaction transaction = new Transaction();

		BuyerDiffBankEvent buyerDiffBankEvent = buyerdiffrentbankService.findEvent(buyerdiffBankForm.getId());
		buyerDiffBankEvent.setId(buyerdiffBankForm.getId());
		buyerDiffBankEvent.setCustomerName(buyerdiffBankForm.getCustomerName());
		buyerDiffBankEvent.setSupplier(buyerdiffBankForm.getSupplier());
		buyerDiffBankEvent.setSupplierBank(buyerdiffBankForm.getSupplierBank());
		buyerDiffBankEvent.setMasterKey(buyerdiffBankForm.getMasterKey());
		buyerDiffBankEvent.setGoods(buyerdiffBankForm.getGoods());
		buyerDiffBankEvent.setDate1(buyerdiffBankForm.getDate1());
		buyerDiffBankEvent.setEvent1(buyerdiffBankForm.getEvent1());
		buyerDiffBankEvent.setFirst(buyerdiffBankForm.getFirst());
		buyerDiffBankEvent.setDate2(buyerdiffBankForm.getDate2());
		buyerDiffBankEvent.setEvent2(buyerdiffBankForm.getEvent2());
		buyerDiffBankEvent.setSecond(buyerdiffBankForm.getSecond());
		buyerDiffBankEvent.setDate3(buyerdiffBankForm.getDate3());
		buyerDiffBankEvent.setEvent3(buyerdiffBankForm.getEvent3());
		buyerDiffBankEvent.setThird(buyerdiffBankForm.getThird());
		buyerDiffBankEvent.setDate4(buyerdiffBankForm.getDate4());
		buyerDiffBankEvent.setEvent4(buyerdiffBankForm.getEvent4());
		buyerDiffBankEvent.setFourth(buyerdiffBankForm.getFourth());
		buyerDiffBankEvent.setDate5(buyerdiffBankForm.getDate5());
		buyerDiffBankEvent.setEvent5(buyerdiffBankForm.getEvent5());
		buyerDiffBankEvent.setFifth(buyerdiffBankForm.getFifth());
		buyerDiffBankEvent.setDate6(buyerdiffBankForm.getDate6());
		buyerDiffBankEvent.setEvent6(buyerdiffBankForm.getEvent6());
		buyerDiffBankEvent.setSix(buyerdiffBankForm.getSix());
		buyerDiffBankEvent.setDate7(buyerdiffBankForm.getDate7());
		buyerDiffBankEvent.setEvent7(buyerdiffBankForm.getEvent7());
		buyerDiffBankEvent.setSeven(buyerdiffBankForm.getSeven());
		buyerDiffBankEvent.setDate8(buyerdiffBankForm.getDate8());
		buyerDiffBankEvent.setEvent8(buyerdiffBankForm.getEvent8());
		buyerDiffBankEvent.setEight(buyerdiffBankForm.getEight());
		buyerDiffBankEvent.setDate9(buyerdiffBankForm.getDate9());
		buyerDiffBankEvent.setEvent9(buyerdiffBankForm.getEvent9());
		buyerDiffBankEvent.setNine(buyerdiffBankForm.getNine());
		buyerDiffBankEvent.setDate10(buyerdiffBankForm.getDate10());
		buyerDiffBankEvent.setEvent10(buyerdiffBankForm.getEvent10());
		buyerDiffBankEvent.setTen(buyerdiffBankForm.getTen());
		buyerDiffBankEvent.setDate11(buyerdiffBankForm.getDate11());
		buyerDiffBankEvent.setEvent11(buyerdiffBankForm.getEvent11());
		buyerDiffBankEvent.setElven(buyerdiffBankForm.getElven());
		buyerDiffBankEvent.setDate12(buyerdiffBankForm.getDate12());
		buyerDiffBankEvent.setEvent12(buyerdiffBankForm.getEvent12());
		buyerDiffBankEvent.setTwelve(buyerdiffBankForm.getTwelve());
		buyerDiffBankEvent.setDate13(buyerdiffBankForm.getDate13());
		buyerDiffBankEvent.setEvent13(buyerdiffBankForm.getEvent13());
		buyerDiffBankEvent.setThirteen(buyerdiffBankForm.getThirteen());
		buyerDiffBankEvent.setUniqueId(buyerdiffBankForm.getUniqueId());
		buyerDiffBankEvent.setTransactionId(buyerdiffBankForm.getTransactionId());
		buyerDiffBankEvent.setStatus(buyerdiffBankForm.getStatus());
		buyerDiffBankEvent.setComment(buyerdiffBankForm.getComment());

		transaction.setTransactionId(buyerdiffBankForm.getTransactionId());

		transaction.setTransactionType("Buyer Different Bank Events");

		transaction.setTransactionStatus("Approval done by Approval manager");

		transactionDAO.insertTransaction(transaction);

		Date date = DateService.loginDate;

		buyerDiffBankEvent.setApproveDate(date);

		buyerdiffrentbankService.updateBuyerDiffBankEvent(buyerDiffBankEvent);

		model.put("buyerdiffBankForm", buyerdiffBankForm);

		return new ModelAndView("approveCusmAppDiffSuccess", "model", model);
	}

//seller same Bank

	@RequestMapping(value = "/sellerBankSameEventPage", method = RequestMethod.GET)
	public ModelAndView getAllSEvents(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		Collection<SellerSameBankEvent> SellerSameBankEventList = sellersameBankService.findAllupdateEvents();
		ModelAndView mav = new ModelAndView();

		model.put("user", user);

		if (SellerSameBankEventList != null && SellerSameBankEventList.size() > 0) {

			model.put("SellerSameBankEventList", SellerSameBankEventList);
			mav = new ModelAndView("sellerBankSameEventPage", "model", model);

		} else {

			mav = new ModelAndView("noDataFoundINCusmApp", "model", model);

		}

		return mav;
	}

	@RequestMapping(value = "/updateSellerSameBankEvent", method = RequestMethod.GET)
	public ModelAndView editsellerBankSameEvent(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		SellerSameBankEvent sellerSameBankEvent = sellersameBankService.findEvent(id);

		List<InvoiceDoc> invoiceDocs = invoiceDocDAO.findPoKey(sellerSameBankEvent.getInvoiceKey()).getResultList();
		List<InvoiceDocForm> invoiceDocForms = new ArrayList<InvoiceDocForm>();

		for (InvoiceDoc value : invoiceDocs) {

			InvoiceDocForm invoiceDocForm = new InvoiceDocForm();
			invoiceDocForm.setInvoiceKey(value.getInvoiceKey());
			invoiceDocForm.setDocName(value.getDocName());

			invoiceDocForm.setDescription(value.getDescription());

			invoiceDocForms.add(invoiceDocForm);
		}

		model.put("invoiceDocForms", invoiceDocForms);

		String status = sellerSameBankEvent.getStatus();
		sellerSameBankEventForm.setId(sellerSameBankEvent.getId());
		sellerSameBankEventForm.setCustomerName(sellerSameBankEvent.getCustomerName());
		sellerSameBankEventForm.setBuyer(sellerSameBankEvent.getBuyer());
		sellerSameBankEventForm.setBuyerBank(sellerSameBankEvent.getBuyerBank());
		sellerSameBankEventForm.setMasterKey(sellerSameBankEvent.getMasterKey());
		sellerSameBankEventForm.setInvoiceKey(sellerSameBankEvent.getInvoiceKey());
		sellerSameBankEventForm.setGoods(sellerSameBankEvent.getGoods());
		sellerSameBankEventForm.setCost(sellerSameBankEvent.getCost());
		sellerSameBankEventForm.setDate1(sellerSameBankEvent.getDate1());
		sellerSameBankEventForm.setEvent1(sellerSameBankEvent.getEvent1());
		sellerSameBankEventForm.setFirst(sellerSameBankEvent.getFirst());
		sellerSameBankEventForm.setDate2(sellerSameBankEvent.getDate2());
		sellerSameBankEventForm.setEvent2(sellerSameBankEvent.getEvent2());
		sellerSameBankEventForm.setSecond(sellerSameBankEvent.getSecond());
		sellerSameBankEventForm.setDate3(sellerSameBankEvent.getDate3());
		sellerSameBankEventForm.setEvent3(sellerSameBankEvent.getEvent3());
		sellerSameBankEventForm.setThird(sellerSameBankEvent.getThird());
		sellerSameBankEventForm.setDate4(sellerSameBankEvent.getDate4());
		sellerSameBankEventForm.setEvent4(sellerSameBankEvent.getEvent4());
		sellerSameBankEventForm.setFourth(sellerSameBankEvent.getFourth());
		sellerSameBankEventForm.setDate5(sellerSameBankEvent.getDate5());
		sellerSameBankEventForm.setEvent5(sellerSameBankEvent.getEvent5());
		sellerSameBankEventForm.setFifth(sellerSameBankEvent.getFifth());
		sellerSameBankEventForm.setDate6(sellerSameBankEvent.getDate6());
		sellerSameBankEventForm.setEvent6(sellerSameBankEvent.getEvent6());
		sellerSameBankEventForm.setSix(sellerSameBankEvent.getSix());
		sellerSameBankEventForm.setDate7(sellerSameBankEvent.getDate7());
		sellerSameBankEventForm.setEvent7(sellerSameBankEvent.getEvent7());
		sellerSameBankEventForm.setSeven(sellerSameBankEvent.getSeven());
		sellerSameBankEventForm.setDate8(sellerSameBankEvent.getDate8());
		sellerSameBankEventForm.setEvent8(sellerSameBankEvent.getEvent8());
		sellerSameBankEventForm.setEight(sellerSameBankEvent.getEight());
		sellerSameBankEventForm.setDate9(sellerSameBankEvent.getDate9());
		sellerSameBankEventForm.setEvent9(sellerSameBankEvent.getEvent9());
		sellerSameBankEventForm.setNine(sellerSameBankEvent.getNine());
		sellerSameBankEventForm.setDate10(sellerSameBankEvent.getDate10());
		sellerSameBankEventForm.setEvent10(sellerSameBankEvent.getEvent10());
		sellerSameBankEventForm.setTen(sellerSameBankEvent.getTen());
		sellerSameBankEventForm.setDate11(sellerSameBankEvent.getDate11());
		sellerSameBankEventForm.setEvent11(sellerSameBankEvent.getEvent11());
		sellerSameBankEventForm.setElven(sellerSameBankEvent.getElven());
		sellerSameBankEventForm.setTwelve(sellerSameBankEvent.getTwelve());
		sellerSameBankEventForm.setDate12(sellerSameBankEvent.getDate12());
		sellerSameBankEventForm.setEvent12(sellerSameBankEvent.getEvent12());
		sellerSameBankEventForm.setThirteen(sellerSameBankEvent.getThirteen());
		sellerSameBankEventForm.setDate13(sellerSameBankEvent.getDate13());
		sellerSameBankEventForm.setEvent13(sellerSameBankEvent.getEvent13());

		model.put("user", user);
		model.put("sellerSameBankEventForm", sellerSameBankEventForm);
		return new ModelAndView("editSellerSameBankEvent", "model", model);

	}

	@RequestMapping(value = "/confirmUpdateSellerSameBankEvent", method = RequestMethod.POST)
	public ModelAndView confirmUpdateSellerSameBankEvent(ModelMap model,
														 @ModelAttribute SellerSameBankEventForm sellerSameBankEventForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<InvoiceDoc> invoiceDocs = invoiceDocDAO.findPoKey(sellerSameBankEventForm.getInvoiceKey()).getResultList();
		List<InvoiceDocForm> invoiceDocForms = new ArrayList<InvoiceDocForm>();

		for (InvoiceDoc value : invoiceDocs) {

			InvoiceDocForm invoiceDocForm = new InvoiceDocForm();
			invoiceDocForm.setInvoiceKey(value.getInvoiceKey());
			invoiceDocForm.setDocName(value.getDocName());

			invoiceDocForm.setDescription(value.getDescription());

			invoiceDocForms.add(invoiceDocForm);
		}

		model.put("invoiceDocForms", invoiceDocForms);

		model.put("sellerSameBankEventForm", sellerSameBankEventForm);

		return new ModelAndView("confirmUpdateSellerSameBankEvent", "model", model);

	}

	@RequestMapping(value = "/updateSellerSameBankEvent", method = RequestMethod.POST)
	public ModelAndView getupdateSellerSameBankEvent(ModelMap model,
													 @ModelAttribute SellerSameBankEventForm sellerSameBankEventForm) {
		EndUser user = getCurrentLoggedUserDetails();
		
		sellerSameBankEventForm.setTransactionId(KeyGenerator.generateTransactionKey());
		
		model.put("user", user);
		SellerSameBankEvent sellerSameBankEvent = sellersameBankService.findEvent(sellerSameBankEventForm.getId());

		Transaction transaction = new Transaction();

		sellerSameBankEvent.setId(sellerSameBankEventForm.getId());
		sellerSameBankEvent.setCustomerName(sellerSameBankEventForm.getCustomerName());
		sellerSameBankEvent.setBuyer(sellerSameBankEventForm.getBuyer());
		sellerSameBankEvent.setBuyerBank(sellerSameBankEventForm.getBuyerBank());
		sellerSameBankEvent.setMasterKey(sellerSameBankEventForm.getMasterKey());
		sellerSameBankEvent.setGoods(sellerSameBankEventForm.getGoods());
		sellerSameBankEvent.setCost(sellerSameBankEventForm.getCost());
		sellerSameBankEvent.setDate1(sellerSameBankEventForm.getDate1());
		sellerSameBankEvent.setEvent1(sellerSameBankEventForm.getEvent1());
		sellerSameBankEvent.setFirst(sellerSameBankEventForm.getFirst());
		sellerSameBankEvent.setDate2(sellerSameBankEventForm.getDate2());
		sellerSameBankEvent.setEvent2(sellerSameBankEventForm.getEvent2());
		sellerSameBankEvent.setSecond(sellerSameBankEventForm.getSecond());
		sellerSameBankEvent.setDate3(sellerSameBankEventForm.getDate3());
		sellerSameBankEvent.setEvent3(sellerSameBankEventForm.getEvent3());
		sellerSameBankEvent.setThird(sellerSameBankEventForm.getThird());
		sellerSameBankEvent.setDate4(sellerSameBankEventForm.getDate4());
		sellerSameBankEvent.setEvent4(sellerSameBankEventForm.getEvent4());
		sellerSameBankEvent.setFourth(sellerSameBankEventForm.getFourth());
		sellerSameBankEvent.setDate5(sellerSameBankEventForm.getDate5());
		sellerSameBankEvent.setEvent5(sellerSameBankEventForm.getEvent5());
		sellerSameBankEvent.setFifth(sellerSameBankEventForm.getFifth());
		sellerSameBankEvent.setDate6(sellerSameBankEventForm.getDate6());
		sellerSameBankEvent.setEvent6(sellerSameBankEventForm.getEvent6());
		sellerSameBankEvent.setSix(sellerSameBankEventForm.getSix());
		sellerSameBankEvent.setDate7(sellerSameBankEventForm.getDate7());
		sellerSameBankEvent.setEvent7(sellerSameBankEventForm.getEvent7());
		sellerSameBankEvent.setSeven(sellerSameBankEventForm.getSeven());
		sellerSameBankEvent.setDate8(sellerSameBankEventForm.getDate8());
		sellerSameBankEvent.setEvent8(sellerSameBankEventForm.getEvent8());
		sellerSameBankEvent.setEight(sellerSameBankEventForm.getEight());
		sellerSameBankEvent.setDate9(sellerSameBankEventForm.getDate9());
		sellerSameBankEvent.setEvent9(sellerSameBankEventForm.getEvent9());
		sellerSameBankEvent.setNine(sellerSameBankEventForm.getNine());
		sellerSameBankEvent.setDate10(sellerSameBankEventForm.getDate10());
		sellerSameBankEvent.setEvent10(sellerSameBankEventForm.getEvent10());
		sellerSameBankEvent.setTen(sellerSameBankEventForm.getTen());
		sellerSameBankEvent.setDate11(sellerSameBankEventForm.getDate11());
		sellerSameBankEvent.setEvent11(sellerSameBankEventForm.getEvent11());
		sellerSameBankEvent.setElven(sellerSameBankEventForm.getElven());

		sellerSameBankEvent.setTwelve(sellerSameBankEventForm.getTwelve());
		sellerSameBankEvent.setDate12(sellerSameBankEventForm.getDate12());
		sellerSameBankEvent.setEvent12(sellerSameBankEventForm.getEvent12());
		sellerSameBankEvent.setThirteen(sellerSameBankEventForm.getThirteen());
		sellerSameBankEvent.setDate13(sellerSameBankEventForm.getDate13());
		sellerSameBankEvent.setEvent13(sellerSameBankEventForm.getEvent13());
		sellerSameBankEvent.setStatus(sellerSameBankEventForm.getStatus());
		sellerSameBankEvent.setComment(sellerSameBankEventForm.getComment());
		sellerSameBankEvent.setUniqueId(sellerSameBankEventForm.getUniqueId());
		sellerSameBankEvent.setTransactionId(sellerSameBankEventForm.getTransactionId());

		transaction.setTransactionId(sellerSameBankEventForm.getTransactionId());

		transaction.setTransactionType("Seller Same Bank Events");

		transaction.setTransactionStatus("Approval done by Approval manager");

		transactionDAO.insertTransaction(transaction);

		Date date = DateService.loginDate;

		sellerSameBankEvent.setApproveDate(date);

		sellersameBankService.updateSellerSameBankEvent(sellerSameBankEvent);
		model.put("buyerdiffBankForm", buyerdiffBankForm);

		return new ModelAndView("approveSellerSameSuccess", "model", model);
	}

	/* Diff bank Approval side for Seller Events */
	@RequestMapping(value = "/sellerBankDiffrentEventPage", method = RequestMethod.GET)
	public ModelAndView getAllSDEvents(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		Collection<SellerDiffBankEvent> sellerDiffBankEventList = sellerDiffBankEventDAO.findAllupdateEvents();
		ModelAndView mav = new ModelAndView();

		model.put("user", user);

		if (sellerDiffBankEventList != null && sellerDiffBankEventList.size() > 0) {

			model.put("sellerDiffBankEventList", sellerDiffBankEventList);
			mav = new ModelAndView("sellerBankDiffrentEventPage", "model", model);

		} else {

			mav = new ModelAndView("noDataFoundINCusmApp", "model", model);

		}

		return mav;
	}

	@RequestMapping(value = "/updatesellerBankDiffrentEvent", method = RequestMethod.GET)
	public ModelAndView editsellerBankDifferentEvent(@RequestParam Long id, ModelMap model,
													 RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		SellerDiffBankEvent sellerDiffBankEvent = sellerDiffBankEventDAO.findEvent(id);

		List<InvoiceDoc> invoiceDocs = invoiceDocDAO.findPoKey(sellerDiffBankEvent.getInvoiceKey()).getResultList();
		List<InvoiceDocForm> invoiceDocForms = new ArrayList<InvoiceDocForm>();

		for (InvoiceDoc value : invoiceDocs) {

			InvoiceDocForm invoiceDocForm = new InvoiceDocForm();
			invoiceDocForm.setInvoiceKey(value.getInvoiceKey());
			invoiceDocForm.setDocName(value.getDocName());

			invoiceDocForm.setDescription(value.getDescription());

			invoiceDocForms.add(invoiceDocForm);
		}

		model.put("invoiceDocForms", invoiceDocForms);

		String status = sellerDiffBankEvent.getStatus();

		sellerDiffBankEventForm.setId(sellerDiffBankEvent.getId());

		sellerDiffBankEventForm.setCustomerName(sellerDiffBankEvent.getCustomerName());
		sellerDiffBankEventForm.setBuyer(sellerDiffBankEvent.getBuyer());
		sellerDiffBankEventForm.setBuyerBank(sellerDiffBankEvent.getBuyerBank());
		sellerDiffBankEventForm.setMasterKey(sellerDiffBankEvent.getMasterKey());
		sellerDiffBankEventForm.setInvoiceKey(sellerDiffBankEvent.getInvoiceKey());
		sellerDiffBankEventForm.setGoods(sellerDiffBankEvent.getGoods());
		sellerDiffBankEventForm.setCost(sellerDiffBankEvent.getCost());
		sellerDiffBankEventForm.setDate1(sellerDiffBankEvent.getDate1());
		sellerDiffBankEventForm.setEvent1(sellerDiffBankEvent.getEvent1());
		sellerDiffBankEventForm.setFirst(sellerDiffBankEvent.getFirst());
		sellerDiffBankEventForm.setDate2(sellerDiffBankEvent.getDate2());
		sellerDiffBankEventForm.setEvent2(sellerDiffBankEvent.getEvent2());
		sellerDiffBankEventForm.setSecond(sellerDiffBankEvent.getSecond());
		sellerDiffBankEventForm.setDate3(sellerDiffBankEvent.getDate3());
		sellerDiffBankEventForm.setEvent3(sellerDiffBankEvent.getEvent3());
		sellerDiffBankEventForm.setThird(sellerDiffBankEvent.getThird());
		sellerDiffBankEventForm.setDate4(sellerDiffBankEvent.getDate4());
		sellerDiffBankEventForm.setEvent4(sellerDiffBankEvent.getEvent4());
		sellerDiffBankEventForm.setFourth(sellerDiffBankEvent.getFourth());
		sellerDiffBankEventForm.setDate5(sellerDiffBankEvent.getDate5());
		sellerDiffBankEventForm.setEvent5(sellerDiffBankEvent.getEvent5());
		sellerDiffBankEventForm.setFifth(sellerDiffBankEvent.getFifth());
		sellerDiffBankEventForm.setDate6(sellerDiffBankEvent.getDate6());
		sellerDiffBankEventForm.setEvent6(sellerDiffBankEvent.getEvent6());
		sellerDiffBankEventForm.setSix(sellerDiffBankEvent.getSix());
		sellerDiffBankEventForm.setDate7(sellerDiffBankEvent.getDate7());
		sellerDiffBankEventForm.setEvent7(sellerDiffBankEvent.getEvent7());
		sellerDiffBankEventForm.setSeven(sellerDiffBankEvent.getSeven());
		sellerDiffBankEventForm.setDate8(sellerDiffBankEvent.getDate8());
		sellerDiffBankEventForm.setEvent8(sellerDiffBankEvent.getEvent8());
		sellerDiffBankEventForm.setEight(sellerDiffBankEvent.getEight());
		sellerDiffBankEventForm.setDate9(sellerDiffBankEvent.getDate9());
		sellerDiffBankEventForm.setEvent9(sellerDiffBankEvent.getEvent9());
		sellerDiffBankEventForm.setNine(sellerDiffBankEvent.getNine());
		sellerDiffBankEventForm.setDate10(sellerDiffBankEvent.getDate10());
		sellerDiffBankEventForm.setEvent10(sellerDiffBankEvent.getEvent10());
		sellerDiffBankEventForm.setTen(sellerDiffBankEvent.getTen());
		sellerDiffBankEventForm.setDate11(sellerDiffBankEvent.getDate11());
		sellerDiffBankEventForm.setEvent11(sellerDiffBankEvent.getEvent11());
		sellerDiffBankEventForm.setElven(sellerDiffBankEvent.getElven());
		sellerDiffBankEventForm.setTwelve(sellerDiffBankEvent.getTwelve());
		sellerDiffBankEventForm.setDate12(sellerDiffBankEvent.getDate12());
		sellerDiffBankEventForm.setEvent12(sellerDiffBankEvent.getEvent12());
		sellerDiffBankEventForm.setThirteen(sellerDiffBankEvent.getThirteen());
		sellerDiffBankEventForm.setDate13(sellerDiffBankEvent.getDate13());
		sellerDiffBankEventForm.setEvent13(sellerDiffBankEvent.getEvent13());

		sellerDiffBankEventForm.setFourteen(sellerDiffBankEvent.getFourteen());
		sellerDiffBankEventForm.setDate14(sellerDiffBankEvent.getDate14());
		sellerDiffBankEventForm.setEvent14(sellerDiffBankEvent.getEvent14());
		sellerDiffBankEventForm.setFifteen(sellerDiffBankEvent.getFifteen());
		sellerDiffBankEventForm.setDate15(sellerDiffBankEvent.getDate15());
		sellerDiffBankEventForm.setEvent15(sellerDiffBankEvent.getEvent15());
		sellerDiffBankEventForm.setSixteen(sellerDiffBankEvent.getSixteen());
		sellerDiffBankEventForm.setDate16(sellerDiffBankEvent.getDate16());
		sellerDiffBankEventForm.setEvent16(sellerDiffBankEvent.getEvent16());

		model.put("user", user);
		model.put("sellerDiffBankEventForm", sellerDiffBankEventForm);
		return new ModelAndView("editsellerDiffBankEvent", "model", model);

	}

	@RequestMapping(value = "/confirmUpdatesellerBankDiffrentEvent", method = RequestMethod.POST)
	public ModelAndView confirmUpdatesellerBankDiffrentEvent(ModelMap model,
															 @ModelAttribute SellerDiffBankEventForm sellerDiffBankEventForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<InvoiceDoc> invoiceDocs = invoiceDocDAO.findPoKey(sellerDiffBankEventForm.getInvoiceKey()).getResultList();
		List<InvoiceDocForm> invoiceDocForms = new ArrayList<InvoiceDocForm>();

		for (InvoiceDoc value : invoiceDocs) {

			InvoiceDocForm invoiceDocForm = new InvoiceDocForm();
			invoiceDocForm.setInvoiceKey(value.getInvoiceKey());
			invoiceDocForm.setDocName(value.getDocName());

			invoiceDocForm.setDescription(value.getDescription());

			invoiceDocForms.add(invoiceDocForm);
		}

		model.put("invoiceDocForms", invoiceDocForms);

		model.put("sellerDiffBankEventForm", sellerDiffBankEventForm);

		return new ModelAndView("confirmUpdatesellerBankDiffrentEvent", "model", model);

	}

	@RequestMapping(value = "/updatesellerBankDiffrentEvent", method = RequestMethod.POST)
	public ModelAndView getupdatesellerBankDiffrentEvent(ModelMap model,
														 @ModelAttribute SellerDiffBankEventForm sellerDiffBankEventForm) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		
		sellerDiffBankEventForm.setTransactionId(KeyGenerator.generateTransactionKey());
		sellerDiffBankEventForm.setUniqueId(KeyGenerator.generateTransactionKey());
		
		SellerDiffBankEvent sellerDiffBankEvent = sellerDiffBankEventDAO.findEvent(sellerDiffBankEventForm.getId());

		Transaction transaction = new Transaction();

		sellerDiffBankEvent.setId(sellerDiffBankEventForm.getId());
		sellerDiffBankEvent.setCustomerName(sellerDiffBankEventForm.getCustomerName());
		sellerDiffBankEvent.setBuyer(sellerDiffBankEventForm.getBuyer());
		sellerDiffBankEvent.setBuyerBank(sellerDiffBankEventForm.getBuyerBank());
		sellerDiffBankEvent.setMasterKey(sellerDiffBankEventForm.getMasterKey());
		sellerDiffBankEvent.setGoods(sellerDiffBankEventForm.getGoods());
		sellerDiffBankEvent.setCost(sellerDiffBankEventForm.getCost());
		sellerDiffBankEvent.setDate1(sellerDiffBankEventForm.getDate1());
		sellerDiffBankEvent.setEvent1(sellerDiffBankEventForm.getEvent1());
		sellerDiffBankEvent.setFirst(sellerDiffBankEventForm.getFirst());
		sellerDiffBankEvent.setDate2(sellerDiffBankEventForm.getDate2());
		sellerDiffBankEvent.setEvent2(sellerDiffBankEventForm.getEvent2());
		sellerDiffBankEvent.setSecond(sellerDiffBankEventForm.getSecond());
		sellerDiffBankEvent.setDate3(sellerDiffBankEventForm.getDate3());
		sellerDiffBankEvent.setEvent3(sellerDiffBankEventForm.getEvent3());
		sellerDiffBankEvent.setThird(sellerDiffBankEventForm.getThird());
		sellerDiffBankEvent.setDate4(sellerDiffBankEventForm.getDate4());
		sellerDiffBankEvent.setEvent4(sellerDiffBankEventForm.getEvent4());
		sellerDiffBankEvent.setFourth(sellerDiffBankEventForm.getFourth());
		sellerDiffBankEvent.setDate5(sellerDiffBankEventForm.getDate5());
		sellerDiffBankEvent.setEvent5(sellerDiffBankEventForm.getEvent5());
		sellerDiffBankEvent.setFifth(sellerDiffBankEventForm.getFifth());
		sellerDiffBankEvent.setDate6(sellerDiffBankEventForm.getDate6());
		sellerDiffBankEvent.setEvent6(sellerDiffBankEventForm.getEvent6());
		sellerDiffBankEvent.setSix(sellerDiffBankEventForm.getSix());
		sellerDiffBankEvent.setDate7(sellerDiffBankEventForm.getDate7());
		sellerDiffBankEvent.setEvent7(sellerDiffBankEventForm.getEvent7());
		sellerDiffBankEvent.setSeven(sellerDiffBankEventForm.getSeven());
		sellerDiffBankEvent.setDate8(sellerDiffBankEventForm.getDate8());
		sellerDiffBankEvent.setEvent8(sellerDiffBankEventForm.getEvent8());
		sellerDiffBankEvent.setEight(sellerDiffBankEventForm.getEight());
		sellerDiffBankEvent.setDate9(sellerDiffBankEventForm.getDate9());
		sellerDiffBankEvent.setEvent9(sellerDiffBankEventForm.getEvent9());
		sellerDiffBankEvent.setNine(sellerDiffBankEventForm.getNine());
		sellerDiffBankEvent.setDate10(sellerDiffBankEventForm.getDate10());
		sellerDiffBankEvent.setEvent10(sellerDiffBankEventForm.getEvent10());
		sellerDiffBankEvent.setTen(sellerDiffBankEventForm.getTen());
		sellerDiffBankEvent.setDate11(sellerDiffBankEventForm.getDate11());
		sellerDiffBankEvent.setEvent11(sellerDiffBankEventForm.getEvent11());
		sellerDiffBankEvent.setElven(sellerDiffBankEventForm.getElven());

		sellerDiffBankEvent.setTwelve(sellerDiffBankEventForm.getTwelve());
		sellerDiffBankEvent.setDate12(sellerDiffBankEventForm.getDate12());
		sellerDiffBankEvent.setEvent12(sellerDiffBankEventForm.getEvent12());
		sellerDiffBankEvent.setThirteen(sellerDiffBankEventForm.getThirteen());
		sellerDiffBankEvent.setDate13(sellerDiffBankEventForm.getDate13());
		sellerDiffBankEvent.setEvent13(sellerDiffBankEventForm.getEvent13());

		sellerDiffBankEvent.setFourteen(sellerDiffBankEventForm.getFourteen());
		sellerDiffBankEvent.setDate14(sellerDiffBankEventForm.getDate14());
		sellerDiffBankEvent.setEvent14(sellerDiffBankEventForm.getEvent14());
		sellerDiffBankEvent.setFifteen(sellerDiffBankEventForm.getFifteen());
		sellerDiffBankEvent.setDate15(sellerDiffBankEventForm.getDate15());
		sellerDiffBankEvent.setEvent15(sellerDiffBankEventForm.getEvent15());
		sellerDiffBankEvent.setSixteen(sellerDiffBankEventForm.getSixteen());
		sellerDiffBankEvent.setDate16(sellerDiffBankEventForm.getDate16());
		sellerDiffBankEvent.setEvent16(sellerDiffBankEventForm.getEvent16());
		sellerDiffBankEvent.setTransactionId(sellerDiffBankEventForm.getTransactionId());
		sellerDiffBankEvent.setUniqueId(sellerDiffBankEventForm.getUniqueId());
		sellerDiffBankEvent.setStatus(sellerDiffBankEventForm.getStatus());
		sellerDiffBankEvent.setComment(sellerDiffBankEventForm.getComment());

		transaction.setTransactionId(sellerDiffBankEventForm.getTransactionId());

		transaction.setTransactionType("Seller Different Bank Events");

		transaction.setTransactionStatus("Approval done by Approval manager");

		transactionDAO.insertTransaction(transaction);

		Date date = DateService.loginDate;

		sellerDiffBankEvent.setApproveDate(date);

		sellerDiffBankEventDAO.updateSellerDiffBankEvent(sellerDiffBankEvent);

		model.put("sellerDiffBankEventForm", sellerDiffBankEventForm);

		return new ModelAndView("approveSellerDiffSuccesss", "model", model);
	}

	@RequestMapping(value = "/snapShotCustAppMng", method = RequestMethod.GET)
	public ModelAndView snapShotBank(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<MasterPlan> masterList = masterPlanDAO.getAllMasterPlans().getResultList();

		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);
			return new ModelAndView("snapShotCustAppMng", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/snapShotCustAppMngView", method = RequestMethod.GET)
	public ModelAndView snapShotBankView(@RequestParam("id") Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		MasterPlan masterList = masterPlanDAO.getByMasterPlanId(id);

		if (masterList != null && masterList.getMasterKey() != null) {
			List<Collateral> collateralList = collateralDAO.getCollateralBYMAsterKey(masterList.getMasterKey())
					.getResultList();
			if (collateralList != null && collateralList.size() > 0) {
				model.put("collateralList", collateralList);
			}

			List<BuyingCost> buyingList = buyingCostDAO.getBuyingCostBYMAsterKey(masterList.getMasterKey())
					.getResultList();

			if (buyingList != null && buyingList.size() > 0)

			{
				model.put("buyingList", buyingList);
			}

			List<SellerBuyingCost> sellerList = sellerBuyingCostDAO.getSellerBuyingCostList(masterList.getMasterKey())
					.getResultList();
			if (sellerList != null && sellerList.size() > 0)

			{
				model.put("sellerList", sellerList);
			}

			List<WorkingCapital> wclist = workingCapitalDAO.getWCBYMAsterKey(masterList.getMasterKey()).getResultList();
			if (wclist != null && wclist.size() > 0)

			{
				model.put("wclist", wclist);
			}

			List<Invoice> invoiceList = invoiceDAO.getInvoiceByMasterKeyList(masterList.getMasterKey()).getResultList();

			if (invoiceList != null && invoiceList.size() > 0) {
				model.put("invoiceList", invoiceList);
			}
			List<PurchaseOrder> purchaseList = purchaseOrderDAO.getPoByMasterKeyList(masterList.getMasterKey())
					.getResultList();

			if (purchaseList != null && purchaseList.size() > 0) {
				model.put("purchaseList", purchaseList);

			}

			model.put("masterList", masterList);
		}
		model.put("user", user);

		return new ModelAndView("snapShotCustAppMngView", "model", model);

	}

	@RequestMapping(value = "/cancelPoAppList", method = RequestMethod.GET)
	public ModelAndView cancelPoList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> purchaseList = purchaseOrderDAO.getPoCancel().getResultList();

		if (purchaseList != null && purchaseList.size() > 0) {
			model.put("purchaseList", purchaseList);

			return new ModelAndView("cancelPoAppList", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/cancelPoApp", method = RequestMethod.GET)
	public ModelAndView cancelPoApp(@RequestParam("id") Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(id);

		purchaseOrderForm.setId(purchase.getId());
		purchaseOrderForm.setCustomerName(purchase.getCustomerName());
		purchaseOrderForm.setCustomerHeadName(purchase.getCustomerHeadName());
		purchaseOrderForm.setSupplierName(purchase.getSupplierName());
		purchaseOrderForm.setMasterKey(purchase.getMasterKey());
		purchaseOrderForm.setTransactionId(purchase.getTransactionId());
		purchaseOrderForm.setvStatus(purchase.getvStatus());
		purchaseOrderForm.setCancelComment(purchase.getCancelComment());
		purchaseOrderForm.setCustomerHeadEmail(purchase.getCustomerHeadEmail());
		purchaseOrderForm.setCustomerBranchEmail(purchase.getCustomerBranchEmail());
		purchaseOrderForm.setSupplierEmail(purchase.getSupplierEmail());

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("cancelPoApp", "model", model);

	}

	@RequestMapping(value = "/cancelPoAppConfirm", method = RequestMethod.POST)
	public ModelAndView cancelPoAppConfirm(ModelMap model, @ModelAttribute PurchaseOrderForm purchaseOrderForm,
										   RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		purchaseOrderForm.setId(purchaseOrderForm.getId());
		purchaseOrderForm.setCustomerName(purchaseOrderForm.getCustomerName());
		purchaseOrderForm.setCustomerHeadName(purchaseOrderForm.getCustomerHeadName());
		purchaseOrderForm.setMasterKey(purchaseOrderForm.getMasterKey());
		purchaseOrderForm.setSupplierName(purchaseOrderForm.getSupplierName());
		purchaseOrderForm.setTransactionId(purchaseOrderForm.getTransactionId());
		purchaseOrderForm.setvStatus(purchaseOrderForm.getvStatus());
		purchaseOrderForm.setCancelComment(purchaseOrderForm.getCancelComment());
		purchaseOrderForm.setCustomerHeadEmail(purchaseOrderForm.getCustomerHeadEmail());
		purchaseOrderForm.setCustomerBranchEmail(purchaseOrderForm.getCustomerBranchEmail());
		purchaseOrderForm.setSupplierEmail(purchaseOrderForm.getSupplierEmail());

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("cancelPoAppConfirm", "model", model);

	}

	@RequestMapping(value = "/cancelPoAppPost", method = RequestMethod.POST)
	public ModelAndView cancelPoAppPost(ModelMap model, @ModelAttribute PurchaseOrderForm purchaseOrderForm,
										RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		purchaseOrderForm.setTransactionId(purchaseOrderForm.getTransactionId());
		purchaseOrderForm.setMasterKey(purchaseOrderForm.getMasterKey());
		purchaseOrderForm.setCustomerName(purchaseOrderForm.getCustomerName());
		purchaseOrderForm.setCustomerHeadEmail(purchaseOrderForm.getCustomerHeadEmail());
		purchaseOrderForm.setCustomerBranchEmail(purchaseOrderForm.getCustomerBranchEmail());
		purchaseOrderForm.setSupplierEmail(purchaseOrderForm.getSupplierEmail());

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(purchaseOrderForm.getId());

		purchase.setvStatus(purchaseOrderForm.getvStatus());
		purchase.setCancelComment(purchaseOrderForm.getCancelComment());

		if (purchaseOrderForm.getStatus().equals("Cancelled")) {
			purchase.setStatus("Cancelled");
			purchase.setrStatus("Cancelled");
			purchase.setTransStatus("Cancelled");
			purchase.setTransResult("Cancelled");
		}

		purchaseOrderDAO.updatePo(purchase);

		purchaseOrderForm.setFlag(purchase.getFlag());
		purchaseOrderForm.setMasterKey(purchase.getMasterKey());
		purchaseOrderForm.setCustomerName(purchase.getCustomerName());
		purchaseOrderForm.setAmount(purchase.getAmount());

		if (purchaseOrderForm.getFlag().equals(0)) {
			if (purchaseOrderForm.getStatus().equals("Cancelled")) {
				MasterPlan plan = masterPlanDAO.getMasterPlanByMasterKey(purchaseOrderForm.getMasterKey())
						.getSingleResult();
				if (plan != null) {
					Float amt = purchaseOrderForm.getAmount();
					Float utiliAmt = plan.getUtilizedBusnsAmt();
					Float bal = plan.getBalance();

					Float totalUtilized = utiliAmt - amt;
					Float totalBal = bal + amt;
					plan.setBalance(totalBal);
					plan.setUtilizedBusnsAmt(totalUtilized);

					masterPlanDAO.updatePlan(plan);
				}
			}
		} else {
			if (purchaseOrderForm.getStatus().equals("Cancelled")) {
				FundsDistribute funds = fundsDistributeDAO
						.getFundsListByKeyAndName(purchaseOrderForm.getMasterKey(), purchaseOrderForm.getCustomerName())
						.getSingleResult();
				if (funds != null) {
					Float amt = purchaseOrderForm.getAmount();
					Float utiliAmt = funds.getUtilizedAmount();
					Float bal = funds.getBusBalance();

					Float totalUtilized = utiliAmt - amt;
					Float totalBal = bal + amt;
					funds.setBusBalance(totalBal);
					funds.setUtilizedAmount(totalUtilized);

					fundsDistributeDAO.updateFunds(funds);
				}
			}

		}

		Transaction trans = new Transaction();
		trans.setTransactionId(purchaseOrderForm.getTransactionId());
		trans.setTransactionStatus("Purchase Order Cancel/Reject By Manager");
		trans.setTransactionType("Submitted Successfully");

		transactionDAO.insertTransaction(trans);

		attributes.addFlashAttribute("success", "Cancelled Successfully");

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("cancelPoAppTransaction", "model", model);

	}

	@RequestMapping(value = "/cancelPoAppTransaction", method = RequestMethod.GET)
	public ModelAndView cancelPoAppTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("cancelPoAppTransaction", "model", model);

	}

	@RequestMapping(value = "/custWareHouseAppList", method = RequestMethod.GET)
	public ModelAndView createClientAdmin(ModelMap model) {

		List<WareHouse> whList = wareHouseDAO.getByAppPending().getResultList();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		if (whList != null && whList.size() > 0) {
			model.put("whList", whList);

			return new ModelAndView("custWareHouseAppList", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/custWareHouseApproval", method = RequestMethod.GET)
	public ModelAndView CustWareHouseApproval(@RequestParam("id") Long id, ModelMap model) {

		WareHouse wh = wareHouseDAO.getByWareHouseId(id);

		wareHouseForm.setId(wh.getId());
		wareHouseForm.setCustomerName(wh.getCustomerName());
		wareHouseForm.setWareHouseName(wh.getWareHouseName());
		wareHouseForm.setTransactionId(wh.getTransactionId());

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("wareHouseForm", wareHouseForm);

		return new ModelAndView("custWareHouseApproval", "model", model);

	}

	@RequestMapping(value = "/custWareHouseApprovalConfirm", method = RequestMethod.POST)
	public ModelAndView CustWareHouseApprovalConfirm(ModelMap model, @ModelAttribute WareHouseForm wareHouseForm,
													 RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		wareHouseForm.setId(wareHouseForm.getId());
		wareHouseForm.setCustomerName(wareHouseForm.getCustomerName());
		wareHouseForm.setWareHouseName(wareHouseForm.getWareHouseName());
		wareHouseForm.setTransactionId(wareHouseForm.getTransactionId());
		wareHouseForm.setStatus(wareHouseForm.getStatus());
		wareHouseForm.setComment(wareHouseForm.getComment());

		model.put("user", user);
		model.put("wareHouseForm", wareHouseForm);

		return new ModelAndView("custWareHouseApprovalConfirm", "model", model);

	}

	@RequestMapping(value = "/custWareHouseApprovalPost", method = RequestMethod.POST)
	public ModelAndView custWareHouseApprovalPost(ModelMap model, @ModelAttribute WareHouseForm wareHouseForm,
												  RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		WareHouse wh = wareHouseDAO.getByWareHouseId(wareHouseForm.getId());

		wh.setStatus(wareHouseForm.getStatus());
		wh.setComment(wareHouseForm.getComment());

		wareHouseDAO.updateWareHouse(wh);

		Transaction trans = new Transaction();
		trans.setTransactionId(wareHouseForm.getTransactionId());
		trans.setTransactionStatus("WareHouse Approval/Reject By Manager");
		trans.setTransactionType("Submitted Successfully");

		transactionDAO.insertTransaction(trans);

		model.put("user", user);
		model.put("wareHouseForm", wareHouseForm);

		return new ModelAndView("custWareHouseApprovalTransaction", "model", model);

	}

	@RequestMapping(value = "/custWareHouseApprovalTransaction", method = RequestMethod.GET)
	public ModelAndView custWareHouseApprovalTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("wareHouseForm", wareHouseForm);

		return new ModelAndView("custWareHouseApprovalTransaction", "model", model);

	}

	@RequestMapping(value = "/custWareHouseAppMngList", method = RequestMethod.GET)
	public ModelAndView apprCustAppMngList(ModelMap model) {

		List<WareHouse> whList = wareHouseDAO.getList().getResultList();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		if (whList != null && whList.size() > 0) {
			model.put("whList", whList);

			return new ModelAndView("custWareHouseAppMngList", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/poDocumnetListCustomerAppMng", method = RequestMethod.GET)
	public ModelAndView PoDocumnetList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		Collection<PoUpload> docList = poUploadDAO.getList();

		model.put("user", user);
		if (docList != null && docList.size() > 0) {
			model.put("docList", docList);

			return new ModelAndView("poDocumnetListCustomerAppMng", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/invoiceDocumnetListCustomerAppMng", method = RequestMethod.GET)
	public ModelAndView InvoiceDocumnetList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		Collection<InvoiceUpload> docList = invoiceUploadDAO.getList();

		model.put("user", user);
		if (docList != null && docList.size() > 0) {
			model.put("docList", docList);

			return new ModelAndView("invoiceDocumnetListCustomerAppMng", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	/**
	 * Method to generate Warehouse Chart
	 */
	@RequestMapping(value = "/generateWarehouseChart", method = RequestMethod.GET)
	public ModelAndView generateWarehouseChart(@ModelAttribute WareHouseForm wareHouseForm, ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouse> warehouseList = wareHouseDAO.getList().getResultList();

		model.put("user", user);
		model.put("warehouseList", warehouseList);

		return new ModelAndView("generateWarehouseChartAppMng", "model", model);
	}

	/**
	 * Method to generate Warehouse Chart
	 */
	@RequestMapping(value = "/generateWarehouseChartById", method = RequestMethod.POST)
	public String generateWarehouseChartById(@ModelAttribute WareHouseForm wareHouseForm, ModelMap model,
											 RedirectAttributes attributes) {

		Map<WareHouse, List<Inventory>> warehouseChart = new LinkedHashMap<>();

		if (wareHouseForm.getIds() != null) {
			for (long id : wareHouseForm.getIds()) {
				WareHouse wareHouse = wareHouseDAO.getByWareHouseId(id);
				List<Inventory> inventoryList = inventoryDAO.getInventoryListByWareHouse(wareHouse.getWareHouseName());
				warehouseChart.put(wareHouse, inventoryList);
			}
		}
		attributes.addFlashAttribute("warehouseChart", warehouseChart);

		return "redirect:generateWarehouseChart";
	}

	@RequestMapping(value = "/appExistingStockInvoiceList", method = RequestMethod.GET)
	public ModelAndView existingStockHeadInvoiceFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<InvoiceStock> poList = invoiceStockDAO.getList().getResultList();

		model.put("user", user);
		if (poList != null && poList.size() > 0) {
			model.put("poList", poList);

			return new ModelAndView("appExistingStockInvoiceList", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/appExistingStockPoList", method = RequestMethod.GET)
	public ModelAndView bankExistingStockPoList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PoStock> poList = poStockDAO.getList().getResultList();

		model.put("user", user);
		if (poList != null && poList.size() > 0) {
			model.put("poList", poList);

			return new ModelAndView("appExistingStockPoList", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	/**
	 * Method to generate Existing stock PO Chart
	 */
	@RequestMapping(value = "/existingStockPOInventoryChart", method = RequestMethod.GET)
	public ModelAndView existingStockPOInventoryChart(@ModelAttribute WareHouseForm wareHouseForm, ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouse> warehouseList = wareHouseDAO.getList().getResultList();

		model.put("user", user);
		model.put("warehouseList", warehouseList);

		return new ModelAndView("existingStockPOInventoryChartCltMng", "model", model);
	}

	/**
	 * Method to generate Warehouse Chart
	 */
	@RequestMapping(value = "/existingStockPOInventoryChartById", method = RequestMethod.POST)
	public String existingStockPOInventoryChartById(@ModelAttribute WareHouseForm wareHouseForm, ModelMap model,
													RedirectAttributes attributes) {

		Map<WareHouse, List<PoStock>> warehouseChart = new LinkedHashMap<>();

		if (wareHouseForm.getIds() != null) {
			for (long id : wareHouseForm.getIds()) {
				WareHouse wareHouse = wareHouseDAO.getByWareHouseId(id);
				List<PoStock> poList = poStockDAO.getPoStockByWarehouse(wareHouse.getWareHouseName());

				warehouseChart.put(wareHouse, poList);
			}
		}
		attributes.addFlashAttribute("warehouseChart", warehouseChart);

		return "redirect:existingStockPOInventoryChart";
	}

	/**
	 * Method to generate Existing stock Invoice Chart
	 */
	@RequestMapping(value = "/existingStockInvoiceInventoryChart", method = RequestMethod.GET)
	public ModelAndView existingStockInvoiceInventoryChart(@ModelAttribute WareHouseForm wareHouseForm,
														   ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouse> warehouseList = wareHouseDAO.getList().getResultList();

		model.put("user", user);
		model.put("warehouseList", warehouseList);

		return new ModelAndView("existingStockInvoiceInventoryChartCltMng", "model", model);
	}

	/**
	 * Method to generate Existing stock Invoice Chart
	 */
	@RequestMapping(value = "/existingStockInvoiceInventoryChartById", method = RequestMethod.POST)
	public String existingStockInvoiceInventoryChartById(@ModelAttribute WareHouseForm wareHouseForm, ModelMap model,
														 RedirectAttributes attributes) {

		Map<WareHouse, List<InvoiceStock>> warehouseChart = new LinkedHashMap<>();

		if (wareHouseForm.getIds() != null) {
			for (long id : wareHouseForm.getIds()) {
				WareHouse wareHouse = wareHouseDAO.getByWareHouseId(id);
				List<InvoiceStock> invoiceList = invoiceStockDAO
						.getInvoiceStockByWarehouse(wareHouse.getWareHouseName());

				warehouseChart.put(wareHouse, invoiceList);

			}
		}
		attributes.addFlashAttribute("warehouseChart", warehouseChart);

		return "redirect:existingStockInvoiceInventoryChart";
	}

	/**
	 * Method to generate Invoice inventory chart
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/generateInvoiceInventoryChartBranch", method = RequestMethod.GET)
	public ModelAndView generateInvoiceInventoryChartBranch(@ModelAttribute WareHouseForm wareHouseForm,
															ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouse> warehouseList = wareHouseDAO.getList().getResultList();

		model.put("user", user);
		model.put("warehouseList", warehouseList);

		return new ModelAndView("generateInvoiceInventoryChartCltMng", "model", model);
	}

	/**
	 * Method to generate Invoice inventory Chart
	 */
	@RequestMapping(value = "/generateInvoiceInventoryChartBranchById", method = RequestMethod.POST)
	public String generateInvoiceInventoryChartBranchById(@ModelAttribute WareHouseForm wareHouseForm, ModelMap model,
														  RedirectAttributes attributes) {

		Map<WareHouse, List<InvoiceInventory>> warehouseChart = new LinkedHashMap<>();

		if (wareHouseForm.getIds() != null) {
			for (long id : wareHouseForm.getIds()) {
				WareHouse wareHouse = wareHouseDAO.getByWareHouseId(id);
				List<InvoiceInventory> inventoryList = invoiceInventoryDAO
						.getInvoiceInventoryByWarehouse(wareHouse.getWareHouseName());

				warehouseChart.put(wareHouse, inventoryList);
			}
		}
		attributes.addFlashAttribute("warehouseChart", warehouseChart);

		return "redirect:generateInvoiceInventoryChartBranch";
	}

	@RequestMapping(value = "/custAppHelp", method = RequestMethod.GET)
	public ModelAndView adminHelp(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("custAppHelp", "model", model);

	}

	@RequestMapping(value = "/addOrModifyReportInvoiceAppMngFullList", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportVendorFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<InvoiceReports> disputeList = invoiceReportsDAO.getList().getResultList();

		if (disputeList != null && disputeList.size() > 0) {
			model.put("disputeList", disputeList);
			return new ModelAndView("addOrModifyReportInvoiceAppMngFullList", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/addOrModifyReportInvoiceAppMngView", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportInvoiceView(@RequestParam Long id, ModelMap model,
													 RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		InvoiceReports dispute = invoiceReportsDAO.getByInvoiceReportsId(id);

		disputeReportsForm.setId(dispute.getId());
		disputeReportsForm.setCustomerName(dispute.getCustomerName());
		disputeReportsForm.setPokey(dispute.getPokey());
		disputeReportsForm.setBuyerName(dispute.getBuyerName());
		disputeReportsForm.setGoods(dispute.getGoods());
		disputeReportsForm.setInsDetails(dispute.getInsDetails());
		disputeReportsForm.setCost(dispute.getCost());
		disputeReportsForm.setInvoicekey(dispute.getInvoicekey());
		disputeReportsForm.setGoodsDetails(dispute.getGoodsDetails());
		disputeReportsForm.setTransactionId(dispute.getTransactionId());
		disputeReportsForm.setShipper(dispute.getShipper());
		disputeReportsForm.setTransportType(dispute.getModeOfTransport());
		disputeReportsForm.setModeOfTransport(dispute.getModeOfTransport());
		disputeReportsForm.setTermsCond(dispute.getTermsCond());
		disputeReportsForm.setInclusion(dispute.getInclusion());
		disputeReportsForm.setExclusion(dispute.getExclusion());
		disputeReportsForm.setSuppSurveyorName(dispute.getSuppSurveyorName());
		disputeReportsForm.setSuppSurveyorCom(dispute.getSuppSurveyorCom());
		disputeReportsForm.setSuppSurveyorAdd(dispute.getSuppSurveyorAdd());
		disputeReportsForm.setSuppSurveyorEmail(dispute.getSuppSurveyorEmail());
		disputeReportsForm.setSuppSurveyorPhone(dispute.getSuppSurveyorPhone());
		disputeReportsForm.setSuppDefectType(dispute.getSuppDefectType());
		disputeReportsForm.setSuppDefectQty(dispute.getSuppDefectQty());
		disputeReportsForm.setSuppDefectCostForGoods(dispute.getSuppDefectCostForGoods());
		disputeReportsForm.setSuppDamageStatus(dispute.getSuppDamageStatus());
		disputeReportsForm.setSuppReplacement(dispute.getSuppReplacement());
		disputeReportsForm.setSuppRepairCost(dispute.getSuppRepairCost());
		disputeReportsForm.setAccept(dispute.getAccept());
		disputeReportsForm.setArbNames(dispute.getArbNames());
		disputeReportsForm.setLoc(dispute.getLoc());
		disputeReportsForm.setJudgement(dispute.getJudgement());
		disputeReportsForm.setJudgDate(dispute.getJudgDate());
		disputeReportsForm.setCompliedDate(dispute.getCompliedDate());
		disputeReportsForm.setArbCost(dispute.getArbCost());
		disputeReportsForm.setArbQty(dispute.getArbQty());
		disputeReportsForm.setArbStartDate(dispute.getArbStartDate());
		disputeReportsForm.setArbEndDate(dispute.getArbEndDate());
		disputeReportsForm.setDocSub(dispute.getDocSub());
		disputeReportsForm.setMoneyPaid(dispute.getMoneyPaid());
		disputeReportsForm.setPayDate(dispute.getPayDate());
		disputeReportsForm.setGoodsReplaced(dispute.getGoodsReplaced());
		disputeReportsForm.setRepacedDate(dispute.getRepacedDate());

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		return new ModelAndView("addOrModifyReportInvoiceAppMngView", "model", model);
	}

	@RequestMapping(value = "/addOrModifyReportInvoiceAppMngCompare", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportInvoiceCompare(@RequestParam Long id, ModelMap model,
														RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		InvoiceReports dispute = invoiceReportsDAO.getByInvoiceReportsId(id);

		disputeReportsForm.setId(dispute.getId());
		disputeReportsForm.setCustomerName(dispute.getCustomerName());
		disputeReportsForm.setPokey(dispute.getPokey());
		disputeReportsForm.setBuyerName(dispute.getBuyerName());
		disputeReportsForm.setGoods(dispute.getGoods());
		disputeReportsForm.setInsDetails(dispute.getInsDetails());
		disputeReportsForm.setCost(dispute.getCost());
		disputeReportsForm.setInvoicekey(dispute.getInvoicekey());
		disputeReportsForm.setGoodsDetails(dispute.getGoodsDetails());
		disputeReportsForm.setTransactionId(dispute.getTransactionId());
		disputeReportsForm.setShipper(dispute.getShipper());
		disputeReportsForm.setTransportType(dispute.getModeOfTransport());
		disputeReportsForm.setModeOfTransport(dispute.getModeOfTransport());
		disputeReportsForm.setTermsCond(dispute.getTermsCond());
		disputeReportsForm.setInclusion(dispute.getInclusion());
		disputeReportsForm.setExclusion(dispute.getExclusion());
		disputeReportsForm.setSurveyorName(dispute.getSurveyorName());
		disputeReportsForm.setSurveyorCom(dispute.getSurveyorCom());
		disputeReportsForm.setSurveyorAdd(dispute.getSurveyorAdd());
		disputeReportsForm.setSurveyorEmail(dispute.getSurveyorEmail());
		disputeReportsForm.setSurveyorPhone(dispute.getSurveyorPhone());
		disputeReportsForm.setDefectType(dispute.getDefectType());
		disputeReportsForm.setDefectQty(dispute.getDefectQty());
		disputeReportsForm.setDefectCostForGoods(dispute.getDefectCostForGoods());
		disputeReportsForm.setDamageStatus(dispute.getDamageStatus());
		disputeReportsForm.setReplacement(dispute.getReplacement());
		disputeReportsForm.setRepairCost(dispute.getRepairCost());

		disputeReportsForm.setSuppSurveyorName(dispute.getSuppSurveyorName());
		disputeReportsForm.setSuppSurveyorCom(dispute.getSuppSurveyorCom());
		disputeReportsForm.setSuppSurveyorAdd(dispute.getSuppSurveyorAdd());
		disputeReportsForm.setSuppSurveyorEmail(dispute.getSuppSurveyorEmail());
		disputeReportsForm.setSuppSurveyorPhone(dispute.getSuppSurveyorPhone());
		disputeReportsForm.setSuppDefectType(dispute.getSuppDefectType());
		disputeReportsForm.setSuppDefectQty(dispute.getSuppDefectQty());
		disputeReportsForm.setSuppDefectCostForGoods(dispute.getSuppDefectCostForGoods());
		disputeReportsForm.setSuppDamageStatus(dispute.getSuppDamageStatus());
		disputeReportsForm.setSuppReplacement(dispute.getSuppReplacement());
		disputeReportsForm.setSuppRepairCost(dispute.getSuppRepairCost());
		disputeReportsForm.setAccept(dispute.getAccept());

		Float total = dispute.getDefectQty() - dispute.getSuppDefectQty();
		Float total1 = dispute.getRepairCost() - dispute.getSuppRepairCost();

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);
		model.put("total", total);
		model.put("total1", total1);

		return new ModelAndView("addOrModifyReportInvoiceAppMngCompare", "model", model);
	}

	@RequestMapping(value = "/wareHouseMngApprovalList", method = RequestMethod.GET)
	public ModelAndView custAdminApprovalList(ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<WareHouseMng> customerList = wareHouseMngDAO.getClientAdminByIdAndStatus().getResultList();

		if (customerList != null && customerList.size() > 0) {

			model.put("customerList", customerList);
			return new ModelAndView("wareHouseMngApprovalList", "model", model);
		} else {
			return new ModelAndView("noDataFoundINCusmApp", "model", model);
		}

	}

	@RequestMapping(value = "/approveWareHouseMng", method = RequestMethod.GET)
	public ModelAndView approveClientAdmin(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		WareHouseMng customer = wareHouseMngDAO.getByWareHouseMngId(id);

		wareHouseMngForm.setId(customer.getId());
		wareHouseMngForm.setMngName(customer.getMngName());
		wareHouseMngForm.setCustomerName(customer.getCustomerName());
		wareHouseMngForm.setCompanyName(customer.getCompanyName());
		wareHouseMngForm.setCustomerPrefix(customer.getCustomerPrefix());
		wareHouseMngForm.setAddress(customer.getAddress());
		wareHouseMngForm.setContactNum(customer.getContactNum());
		wareHouseMngForm.setEmail(customer.getEmail());
		wareHouseMngForm.setAccExpiryDate(customer.getAccExpiryDate());
		wareHouseMngForm.setTransactionId(customer.getTransactionId());

		model.put("wareHouseMngForm", wareHouseMngForm);
		return new ModelAndView("approveWareHouseMng", "model", model);
	}

	@RequestMapping(value = "/approveWareHouseMngConfirm", method = RequestMethod.POST)
	public ModelAndView approveClientAdminConfirm(@ModelAttribute WareHouseMngForm wareHouseMngForm, ModelMap model,
												  RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		wareHouseMngForm.setId(wareHouseMngForm.getId());
		wareHouseMngForm.setCustomerName(wareHouseMngForm.getCustomerName());
		wareHouseMngForm.setMngName(wareHouseMngForm.getMngName());
		wareHouseMngForm.setCompanyName(wareHouseMngForm.getCompanyName());
		wareHouseMngForm.setCustomerPrefix(wareHouseMngForm.getCustomerPrefix());
		wareHouseMngForm.setAddress(wareHouseMngForm.getAddress());
		wareHouseMngForm.setStatus(wareHouseMngForm.getStatus());
		wareHouseMngForm.setComment(wareHouseMngForm.getComment());
		wareHouseMngForm.setContactNum(wareHouseMngForm.getContactNum());
		wareHouseMngForm.setEmail(wareHouseMngForm.getEmail());
		wareHouseMngForm.setAccExpiryDate(wareHouseMngForm.getAccExpiryDate());
		wareHouseMngForm.setTransactionId(wareHouseMngForm.getTransactionId());

		model.put("wareHouseMngForm", wareHouseMngForm);

		return new ModelAndView("approveWareHouseMngConfirm", "model", model);
	}

	@RequestMapping(value = "/approveWareHouseMngSave", method = RequestMethod.POST)
	public ModelAndView approveWareHouseMngSave(@ModelAttribute WareHouseMngForm wareHouseMngForm, ModelMap model,
												RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		WareHouseMng customer = wareHouseMngDAO.getByWareHouseMngId(wareHouseMngForm.getId());

		customer.setStatus(wareHouseMngForm.getStatus());
		customer.setComment(wareHouseMngForm.getComment());
		if (wareHouseMngForm.getStatus().equals("Approved")) {
			customer.setbStatus("Pending");
		}

		wareHouseMngDAO.updateUser(customer);

		Transaction trans = new Transaction();
		trans.setTransactionId(wareHouseMngForm.getTransactionId());
		trans.setTransactionStatus("WareHouse Manager Approval");
		trans.setTransactionType("Approved Successfully");

		transactionDAO.insertTransaction(trans);
		model.put("wareHouseMngForm", wareHouseMngForm);

		return new ModelAndView("wareHouseMngAppTransaction", "model", model);
	}

	@RequestMapping(value = "/wareHouseMngAppTransaction", method = RequestMethod.GET)
	public ModelAndView customerAdminAppTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("wareHouseMngForm", wareHouseMngForm);

		return new ModelAndView("wareHouseMngAppTransaction", "model", model);

	}

	@RequestMapping(value = "/wareHouseMngCAppFullList", method = RequestMethod.GET)
	public ModelAndView wareHouseMngList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouseMng> mngList = wareHouseMngDAO.getList().getResultList();

		model.put("user", user);
		model.put("mngList", mngList);

		return new ModelAndView("wareHouseMngCAppFullList", "model", model);

	}

}
