package annona.web;

import annona.dao.*;
import annona.domain.*;
import annona.form.*;
import annona.services.ColumnGraphService;
import annona.services.DateService;
import annona.services.ImageService;
import annona.trade.dao.TradeNotificationDAO;
import annona.trade.domain.TradeNotification;
import annona.utility.Constants;
import annona.utility.KeyGenerator;

import com.google.gson.Gson;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/appMng")
public class ApprovalMngController {

	@PersistenceContext(name = "PersistentUnitName")
	private EntityManager entityManager;

	@Autowired
	EndUserDAO endUserDAOImpl;

	@Autowired
	InvoiceInventoryDAO invoiceInventoryDAO;

	@Autowired
	ClientAdminDAO clientAdminDAO;

	@Autowired
	WareHouseDAO wareHouseDAO;

	@Autowired
	RepaymentForm repaymentForm;

	@Autowired
	PoUploadDAO poUploadDAO;

	@Autowired
	InvoiceUploadDAO invoiceUploadDAO;

	@Autowired
	RepaymentDAO repaymenyDAO;

	@Autowired
	DisputeDAO disputeDAO;

	@Autowired
	ClientAdminForm clientAdminForm;

	@Autowired
	ClientAppMngDAO clientAppMngDAO;

	@Autowired
	ClientAppMngForm clientAppMngForm;

	@Autowired
	EndUserForm endUserForm;

	@Autowired
	NewBuyerDAO newBuyerDAOImpl;

	@Autowired
	MasterPlanDAO masterPlanDAO;

	@Autowired
	BuyingCostDAO buyingCostDAO;

	@Autowired
	CollateralDAO collateralDAO;

	@Autowired
	MasterPlanForm masterPlanForm;

	@Autowired
	PurchaseOrderForm purchaseOrderForm;

	@Autowired
	PurchaseOrderDAO purchaseOrderDAO;

	@Autowired
	FundsDistributeDAO fundsDistributeDAO;

	@Autowired
	InvoiceForm invoiceForm;

	@Autowired
	InvoiceDAO invoiceDAO;

	@Autowired
	InventoryDAO invenrotyDAO;

	@Autowired
	NewBuyerForm newBuyerForm;
	@Autowired
	CustomerDAO customerDAO;
	@Autowired
	TransactionDAO transactionDAO;
	@Autowired
	CustomerHeadForm customerHeadForm;

	@Autowired
	CustomerBranchForm customerBranchForm;

	@Autowired
	CustomerSubsidiaryForm customerSubsidiaryForm;

	@Autowired
	TransactionDAO transcationDAOImpl;
	@Autowired
	SupplierDAO supplierservice;

	@Autowired
	SellerBuyingCostDAO sellerBuyingCostDAO;

	@Autowired
	SupplierForm supplierForm;
	@Autowired
	UploadedFileForm uploadedFileForm;

	@Autowired
	LetterOfCreditDAO letterOfCreditDAO;

	@Autowired
	LetterOfCreditForm letterOfCreditForm;

	@Autowired
	CorrespondentDAO correspondentDAO;

	@Autowired
	NotificationDAO notificationDAO;

	@Autowired
	BuyerSameBankEventDAO buyersamebankservice;

	@Autowired
	BuyerDiffBankEventDAO buyerdiffrentbankService;

	@Autowired
	SellerSameBankEventDAO sellersameBankService;

	@Autowired
	WorkingCapitalDAO workingCapitalDAO;

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
	UploadDAO uploadDaoImpl;

	@Autowired
	LimitBurstDAO limitBurstDAO;

	@Autowired
	LimitBurstForm limitBurstForm;

	@Autowired
	QuarterlyDAO quarterlyDAO;

	@Autowired
	QuarterlyForm quarterlyForm;

	@Autowired
	FullAmountDAO fullAmountDAO;

	@Autowired
	FullAmountForm fullAmountForm;

	@Autowired
	HalfYearlyForm halfYearlyForm;

	@Autowired
	HalfYearlyDAO halfYearlyDAO;

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	SwapAccountDAO swapAccountDAO;

	@Autowired
	SwapAccountForm swapAccountForm;

	@Autowired
	PoStockDAO poStockDAO;

	@Autowired
	InvoiceStockDAO invoiceStockDAO;

	@Autowired
	DisputeReportsDAO disputeReportsDAO;

	@Autowired
	DisputeReportsForm disputeReportsForm;

	@Autowired
	InvoiceReportsDAO invoiceReportsDAO;

	@Autowired
	TradeNotificationDAO tradeNotificationDAO;

	@Autowired
	RestrictedLicenseForm restrictedLicenseForm;

	@Autowired
	RestrictedLicenseDAO restrictedLicenseDAO;

	@Autowired
	ConsortiumDAO consortiumDAO;

	@Autowired
	ConsortiumForm consortiumForm;

	@Autowired
	WareHouseMngDAO wareHouseMngDAO;

	@Autowired
	WareHouseMngForm wareHouseMngForm;

	@Autowired
	CompanyDAO companyDAO;

	@Autowired
	CompanyForm companyForm;

	@Autowired
	CustomerBankDetailsDAO customerBankDetailsDAO;

	CookieThemeResolver themeResolver = new CookieThemeResolver();

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

	private EndUser getCurrentLoggedUserDetails() {

		EndUser endUser = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		return endUser;

	}

	@RequestMapping(value = "/editAMProfile", method = RequestMethod.GET)
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

		return new ModelAndView("editAMProfile", "model", model);

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
		return "redirect:editAMProfile?id=" + endUserForm.getId();
	}

	@RequestMapping(value = "/confirmEditAMProfile", method = RequestMethod.POST)
	public ModelAndView confirmEditAdminProfile(ModelMap model, @ModelAttribute EndUserForm endUserForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("confirmEditAMProfile", "model", model);

	}

	@RequestMapping(value = "/updateAMDetails", method = RequestMethod.POST)
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

		return new ModelAndView("updateAMSuccess", "model", model);

	}

	@RequestMapping(value = "/editAMPWD", method = RequestMethod.GET)
	public ModelAndView editAdminPWD(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser userProfile = endUserDAOImpl.findId(id);

		endUserForm.setId(userProfile.getId());

		endUserForm.setTransactionId(userProfile.getTransactionId());

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("editAMPWD", "model", model);

	}

	@RequestMapping(value = "/updateEditAMPWD", method = RequestMethod.POST)
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

		return new ModelAndView("updateAMSuccess", "model", model);

	}

	@RequestMapping(value = "/themeChange", method = RequestMethod.GET)
	public String getThemeChange(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		log.info("Received request for theme change");

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

		return "redirect:apprMng";
	}

	@RequestMapping(value = "/getLocaleLang", method = RequestMethod.GET)
	public String getLocaleLang(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		log.info("Received request for locale change");
		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		LocaleResolver localeResolver = new CookieLocaleResolver();
		Locale locale = new Locale(request.getParameter("lang"));
		localeResolver.setLocale(request, response, locale);
		user.setPrefferedLanguage(request.getParameter("lang"));

		endUserDAOImpl.mergeUser(user);
		model.put("user", user);
		return "redirect:apprMng";
	}

	@RequestMapping(value = "/apprMngCommon", method = RequestMethod.GET)
	public ModelAndView showApprMngCommonDashBoard(ModelMap model, HttpServletRequest request,
												   HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

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
		model.put("user", user);
		return new ModelAndView("apprMngCommonPage", "model", model);

	}

	@RequestMapping(value = "/apprMng", method = RequestMethod.GET)
	public ModelAndView showApprMngDashBoard(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		List<Repayment> repay = repaymenyDAO.getList().getResultList();

		List<ColumnGraphForm> columnGraphList = new ArrayList<ColumnGraphForm>();
		List<String> categoryList = new ArrayList<>();
		// Populate Graphs for BuyerSameBankEvent
		if (repay != null && repay.size() > 0) {

			float[] sanctionedData = new float[repay.size()];
			float[] utilizedData = new float[repay.size()];
			float[] balanceData = new float[repay.size()];
			for (int i = 0; i < repay.size(); i++) {
				categoryList.add(repay.get(i).getCustomer());
				if (repay.get(i).getBuyingCostSanc() == null) {
					repay.get(i).setBuyingCostSanc(0f);
				}
				if (repay.get(i).getAmountPaid() == null) {
					repay.get(i).setAmountPaid(0f);
				}
				if (repay.get(i).getFunalAmt() == null) {
					repay.get(i).setFunalAmt(0f);
				}
				sanctionedData[i] = repay.get(i).getBuyingCostSanc();
				utilizedData[i] = repay.get(i).getAmountPaid();
				balanceData[i] = repay.get(i).getFunalAmt();
			}
			columnGraphList.add(ColumnGraphService.generateObject("Sanctioned Amount", sanctionedData));
			columnGraphList.add(ColumnGraphService.generateObject("Amount Paid", utilizedData));
			columnGraphList.add(ColumnGraphService.generateObject("Total Amount", balanceData));
		}

		String[] category = categoryList.toArray(new String[categoryList.size()]);
		Gson gson = new Gson();
		String values = gson.toJson(columnGraphList);
		String categories = gson.toJson(category);

		model.put("values", values);
		model.put("categories", categories);

		model.put("user", user);

		return new ModelAndView("apprMngPage", "model", model);

	}

	@RequestMapping(value = "/apprCustomerHeadList", method = RequestMethod.GET)
	public ModelAndView customerHeadList(ModelMap model) {

		List<CustomerHead> customerList = customerDAO.getAllCustomerHead().getResultList();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		if (customerList != null && customerList.size() > 0) {
			model.put("customerList", customerList);

			return new ModelAndView("apprCustomerHeadList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/customerHeadApprovalList", method = RequestMethod.GET)
	public ModelAndView customerHeadApproval(ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<CustomerHeadForm> customerList = customerDAO.getCustByIdAndStatus();

		if (customerList != null && customerList.size() > 0) {

			model.put("customerList", customerList);
			model.put("customerHeadForm", customerHeadForm);
			return new ModelAndView("customerHeadApprovalList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	// Bank Employee Approval
	@RequestMapping(value = "/bankEmpApprov", method = RequestMethod.GET)
	public ModelAndView bankempApp(ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<EndUser> userbankList = endUserDAOImpl.getByRole().getResultList();
		if (userbankList != null && userbankList.size() > 0) {
			model.put("userbankList", userbankList);
			model.put("endUserForm", endUserForm);
			return new ModelAndView("bankEmpApprov", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/approveBankEmp", method = RequestMethod.GET)
	public ModelAndView approvBankEmp(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		EndUser userList = endUserDAOImpl.findId(id);

		endUserForm.setId(userList.getId());
		endUserForm.setUserName(userList.getUserName());
		endUserForm.setContactNo(userList.getContactNo());
		endUserForm.setEmail(userList.getEmail());
		endUserForm.setDisplayName(userList.getDisplayName());

		model.put("user", user);
		model.put("endUserForm", endUserForm);

		return new ModelAndView("approveBankEmp", "model", model);
	}

	@RequestMapping(value = "/approveBankEmpConfrim", method = RequestMethod.POST)
	public ModelAndView approveBankEmpConfrim(@ModelAttribute EndUserForm endUserForm, ModelMap model,
											  RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("endUserForm", endUserForm);

		return new ModelAndView("approveBankEmpConfrim", "model", model);
	}

	@RequestMapping(value = "/updateEmpStatus", method = RequestMethod.POST)
	public ModelAndView updateEmpStatus(@ModelAttribute EndUserForm endUserForm, ModelMap model,
										RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		
		endUserForm.setTransactionId(KeyGenerator.generateTransactionKey());

		EndUser userList = endUserDAOImpl.findId(endUserForm.getId());
		Transaction transaction = new Transaction();
		userList.setId(endUserForm.getId());
		userList.setUserName(endUserForm.getUserName());
		userList.setContactNo(endUserForm.getContactNo());
		userList.setEmail(endUserForm.getEmail());
		userList.setDisplayName(endUserForm.getDisplayName());
		userList.setStatus(endUserForm.getStatus());
		userList.setTransactionId(endUserForm.getTransactionId());
		transaction.setTransactionId(endUserForm.getTransactionId());
		transaction.setTransactionType("Bank Employee Deatils Update Status");
		transaction.setTransactionStatus("Bank Employee Deatils  status saved successfully");
		transcationDAOImpl.insertTransaction(transaction);
		endUserDAOImpl.mergeUser(userList);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("approvalbankEmployeeSuccess", "model", model);
	}

	@RequestMapping(value = "/bankEmpApprovList", method = RequestMethod.GET)
	public ModelAndView bankEmpApprovList(ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		List<EndUser> userList = endUserDAOImpl.getByRoleList().getResultList();
		ModelAndView mav = new ModelAndView();
		if (userList.size() == 0) {
			model.put("user", user);
			mav = new ModelAndView("noDataFound1", "model", model);
		} else {

			model.put("userList", userList);
			model.put("user", user);
			mav = new ModelAndView("bankEmpApprovList", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/approveCustomerHead", method = RequestMethod.GET)
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
		customerHeadForm.setAccExpiryDate(customer.getAccExpiryDate());
		customerHeadForm.setAltContactNum(customer.getAltContactNum());
		customerHeadForm.setAltEmail(customer.getAltEmail());

		model.put("customerHeadForm", customerHeadForm);
		return new ModelAndView("approveCustomerHead", "model", model);
	}

	@RequestMapping(value = "/approveCustomerHeadConfirm", method = RequestMethod.POST)
	public ModelAndView approveCustomerHeadConfirm(@ModelAttribute CustomerHeadForm customerHeadForm, ModelMap model,
												   RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		customerHeadForm.setId(customerHeadForm.getId());
		customerHeadForm.setName(customerHeadForm.getName());
		customerHeadForm.setCompanyName(customerHeadForm.getCompanyName());
		customerHeadForm.setCustomerPrefix(customerHeadForm.getCustomerPrefix());
		customerHeadForm.setAddress(customerHeadForm.getAddress());
		customerHeadForm.setStatus(customerHeadForm.getStatus());
		customerHeadForm.setComment(customerHeadForm.getComment());
		customerHeadForm.setCustomerHeadKey(customerHeadForm.getCustomerHeadKey());
		customerHeadForm.setContactNum(customerHeadForm.getContactNum());
		customerHeadForm.setEmail(customerHeadForm.getEmail());
		customerHeadForm.setAccExpiryDate(customerHeadForm.getAccExpiryDate());
		customerHeadForm.setAltContactNum(customerHeadForm.getAltContactNum());
		customerHeadForm.setAltEmail(customerHeadForm.getAltEmail());
		model.put("customerHeadForm", customerHeadForm);

		return new ModelAndView("approveCustomerHeadConfirm", "model", model);
	}

	@RequestMapping(value = "/approveCustomerHeadSave", method = RequestMethod.POST)
	public ModelAndView approveCustomerHeadSave(@ModelAttribute CustomerHeadForm customerHeadForm, ModelMap model,
												RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		
		customerHeadForm.setTransactionId(KeyGenerator.generateTransactionKey());
		customerHeadForm.setCustomerHeadKey(KeyGenerator.generateTransactionKey());

		model.put("user", user);

		CustomerHead customer = customerDAO.getByCustomerId(customerHeadForm.getId());
		Company company = companyDAO.getByCompanyId(customer.getCompanyId());

		String bankName = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0).getBankName();

		customer.setName(customerHeadForm.getName());
		//customer.setCompanyName(customerHeadForm.getCompanyName());
		customer.setCustomerPrefix(customerHeadForm.getCustomerPrefix());
		//customer.setAddress(customerHeadForm.getAddress());
		customer.setCompanyId(company.getId());
		customer.setStatus(customerHeadForm.getStatus());
		customer.setComment(customerHeadForm.getComment());
		customer.setAltContactNum(customerHeadForm.getAltContactNum());
		customer.setAltEmail(customerHeadForm.getAltEmail());
		Date date = DateService.loginDate;
		customer.setApproveeDate(date);
		customer.setCustomerHeadKey(customerHeadForm.getCustomerHeadKey());
		customer.setPassword(customer.getContactNum());

		customerDAO.updateUser(customer);

		String status = customerHeadForm.getStatus();

		if (status != null && status.equalsIgnoreCase("Approved")) {
			EndUser endUser = new EndUser();

			endUser.setUserName(customerHeadForm.getName());
			endUser.setPassword(customerHeadForm.getContactNum());
			endUser.setContactNo(customerHeadForm.getContactNum());
			endUser.setAltContactNo(customerHeadForm.getAltContactNum());
			endUser.setAltEmail(customerHeadForm.getAltEmail());
			endUser.setCompanyId(company.getId());
			endUser.setCurrentRole("ROLE_USER");
			endUser.setRole(4);
			endUser.setPrefferedLanguage("en");
			endUser.setTheme("themeBlue");
			endUser.setEmail(customerHeadForm.getEmail());
			endUser.setDisplayName(customerHeadForm.getName());
			endUser.setStatus(customerHeadForm.getStatus());
			endUser.setTransactionId(customerHeadForm.getTransactionId());
			endUser.setPasswordFlag(0);
			endUser.setAccExpiryDate(customerHeadForm.getAccExpiryDate());
			endUserDAOImpl.createUser(endUser);

		}

		Transaction trans = new Transaction();
		trans.setTransactionId(customerHeadForm.getTransactionId());
		trans.setTransactionStatus("Customer Head Approval");
		trans.setTransactionType("Approved Successfully");

		transactionDAO.insertTransaction(trans);

		String stat = customerHeadForm.getStatus();
		String username = customerHeadForm.getName();
		String email = customerHeadForm.getEmail();
		String password = customer.getContactNum();
		String currentrole = "ROLE_USER";
		try {
			if (stat.equals("Approved")) {
				String tex = "Customer Head  Details Notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + username + ",\n\n Your Account has been Created. "
						+ ",\n\n Your Credentials Details are as follows. " + "\n" + "\n"

						+ "\n\nUserName:" + username + "\n\nPassword:" + password + "\n\nCurrent Role:" + currentrole +

						"\n\n\nRegards,\n Bank");
				System.out.println("" + email + username);
				mailSender.send(emails);
				SimpleMailMessage emails1 = new SimpleMailMessage();
				emails1.setTo(email);

			} else if (status.equals("Rejected")) {
				String tex = "Customer Head  Details  notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + username + ",\n\n Your Account has been Rejected  "

						+ "\n" + "\n" + "\n\n\nRegards,\n"+bankName);
				mailSender.send(emails);
			}

			model.put("customerHeadForm", customerHeadForm);

		} catch (Exception e) {
			System.out.println(e.getMessage() + e);

		}
		return new ModelAndView("customerHeadAppTransaction", "model", model);
	}

	@RequestMapping(value = "/customerHeadAppTransaction", method = RequestMethod.GET)
	public ModelAndView customerHeadTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("customerHeadForm", customerHeadForm);

		return new ModelAndView("customerHeadAppTransaction", "model", model);

	}

	@RequestMapping(value = "/customerBranchApprovalList", method = RequestMethod.GET)
	public ModelAndView customerBranchApproval(ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<CustomerBranch> customerList = customerDAO.getCustBranchByIdAndStatus().getResultList();

		if (customerList != null && customerList.size() > 0) {

			model.put("customerList", customerList);
			model.put("customerBranchForm", customerBranchForm);
			return new ModelAndView("customerBranchApprovalList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/approveCustomerBranch", method = RequestMethod.GET)
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
		customerBranchForm.setRoleType(customer.getRoleType());
		customerBranchForm.setEmail(customer.getEmail());
		customerBranchForm.setAccExpiryDate(customer.getAccExpiryDate());

		model.put("customerBranchForm", customerBranchForm);
		return new ModelAndView("approveCustomerBranch", "model", model);
	}

	@RequestMapping(value = "/approveCustomerBranchConfirm", method = RequestMethod.POST)
	public ModelAndView approveCustomerBranchConfirm(@ModelAttribute CustomerBranchForm customerBranchForm,
													 ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		customerBranchForm.setName(customerBranchForm.getName());
		customerBranchForm.setCustomerHeadName(customerBranchForm.getCustomerHeadName());
		customerBranchForm.setAddress(customerBranchForm.getAddress());
		customerBranchForm.setStatus(customerBranchForm.getStatus());
		customerBranchForm.setComment(customerBranchForm.getComment());
		customerBranchForm.setCustomerHeadKey(customerBranchForm.getCustomerHeadKey());
		customerBranchForm.setRoleType(customerBranchForm.getRoleType());
		customerBranchForm.setContactNum(customerBranchForm.getContactNum());
		customerBranchForm.setEmail(customerBranchForm.getEmail());

		model.put("customerBranchForm", customerBranchForm);

		return new ModelAndView("approveCustomerBranchConfirm", "model", model);
	}

	@RequestMapping(value = "/approveCustomerBranchSave", method = RequestMethod.POST)
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
		customer.setStatus(customerBranchForm.getStatus());
		customer.setComment(customerBranchForm.getComment());
		Date date = DateService.loginDate;
		customer.setApproveeDate(date);
		customer.setPassword(customerBranchForm.getContactNum());
		customer.setCustomerBranchKey(customerBranchForm.getCustomerBranchKey());
		customer.setTransactionId(customerBranchForm.getTransactionId());
		customerDAO.updateBranch(customer);

		String status = customerBranchForm.getStatus();

		if (status != null && status.equalsIgnoreCase("Approved")) {
			EndUser endUser = new EndUser();
			endUser.setUserName(customerBranchForm.getName());
			endUser.setPassword(customerBranchForm.getContactNum());
			endUser.setContactNo(customerBranchForm.getContactNum());
			endUser.setRoleType(customerBranchForm.getRoleType());
			endUser.setCurrentRole("ROLE_USERBRANCH");
			endUser.setRole(5);
			endUser.setPrefferedLanguage("en");
			endUser.setTheme("themeBlue");
			endUser.setEmail(customerBranchForm.getEmail());
			endUser.setDisplayName(customerBranchForm.getName());
			endUser.setStatus(customerBranchForm.getStatus());
			endUser.setCustomerHeadKey(customerBranchForm.getCustomerHeadKey());
			endUser.setCustomerHeadName(customerBranchForm.getCustomerHeadName());
			endUser.setTransactionId(customerBranchForm.getTransactionId());
			endUser.setPasswordFlag(0);
			endUser.setAccExpiryDate(customerBranchForm.getAccExpiryDate());
			endUserDAOImpl.createUser(endUser);

		}
		Transaction trans = new Transaction();
		trans.setTransactionId(customerBranchForm.getTransactionId());
		trans.setTransactionStatus("Customer Branch Approval");
		trans.setTransactionType("Approved Successfully");

		transactionDAO.insertTransaction(trans);

		String stat = customerBranchForm.getStatus();
		String username = customerBranchForm.getName();
		String email = customerBranchForm.getEmail();
		String password = customerBranchForm.getContactNum();
		String roleType = customerBranchForm.getRoleType();

		try {
			if (stat.equals("Approved")) {
				String tex = "Customer Branch  Details Notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + username + ",\n\n Your Account has been Created. "
						+ ",\n\n Your Credentials Details are as follows. " + "\n" + "\n"

						+ "\n\nUserName:" + username + "\n\nPassword:" + password + "\n\nCurrent Role:" + roleType +

						"\n\n\nRegards,\nBank");
				System.out.println("" + email + username);
				mailSender.send(emails);
				SimpleMailMessage emails1 = new SimpleMailMessage();
				emails1.setTo(email);

			} else if (status.equals("Rejected")) {
				String tex = "Customer Branch Details Notification ";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + username + ",\n\n Your Account has been Rejected  "

						+ "\n" + "\n" + "\n\n\nRegards,\nBank");
				mailSender.send(emails);

			}

			model.put("customerBranchForm", customerBranchForm);

		} catch (Exception e) {
			System.out.println("message" + e.getMessage());
		}
		return new ModelAndView("customerBranchAppTransaction", "model", model);
	}

	@RequestMapping(value = "/customerBranchAppTransaction", method = RequestMethod.GET)
	public ModelAndView customerBranchAppTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("customerBranchForm", customerBranchForm);

		return new ModelAndView("customerBranchAppTransaction", "model", model);

	}

	@RequestMapping(value = "/apprCustomerBranchList", method = RequestMethod.GET)
	public ModelAndView customerBranchList(ModelMap model) {

		Collection<CustomerBranch> customerList = customerDAO.findAllCustomerBranch();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		if (customerList != null && customerList.size() > 0) {
			model.put("customerList", customerList);

			return new ModelAndView("apprCustomerBranchList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	/* ojhsfg */

	@RequestMapping(value = "/customerSubsidiaryApprovalList", method = RequestMethod.GET)
	public ModelAndView customerSubsidiaryApprovalList(ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		List<CustomerSubsidiary> customerList = customerDAO.getCustSubsByIdAndStatus().getResultList();

		if (customerList != null && customerList.size() > 0) {

			model.put("customerList", customerList);
			model.put("customerSubsidiaryForm", customerSubsidiaryForm);
			return new ModelAndView("customerSubsidiaryApprovalList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}
	}

	@RequestMapping(value = "/approveCustomerSubsidiary", method = RequestMethod.GET)
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
		return new ModelAndView("approveCustomerSubsidiary", "model", model);
	}

	@RequestMapping(value = "/approveCustomerSubsidiaryConfirm", method = RequestMethod.POST)
	public ModelAndView approveCustomerBranchConfirm(@ModelAttribute CustomerSubsidiaryForm customerSubsidiaryForm,
													 ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		customerSubsidiaryForm.setName(customerSubsidiaryForm.getName());
		customerSubsidiaryForm.setCustomerHeadName(customerSubsidiaryForm.getCustomerHeadName());
		customerSubsidiaryForm.setAddress(customerSubsidiaryForm.getAddress());
		customerSubsidiaryForm.setStatus(customerSubsidiaryForm.getStatus());
		customerSubsidiaryForm.setComment(customerSubsidiaryForm.getComment());
		customerSubsidiaryForm.setCustomerHeadKey(customerSubsidiaryForm.getCustomerHeadKey());
		customerSubsidiaryForm.setContactNum(customerSubsidiaryForm.getContactNum());
		customerSubsidiaryForm.setEmail(customerSubsidiaryForm.getEmail());

		model.put("customerSubsidiaryForm", customerSubsidiaryForm);

		return new ModelAndView("approveCustomerSubsidiaryConfirm", "model", model);
	}

	@RequestMapping(value = "/approveCustomerSubsidiarySave", method = RequestMethod.POST)
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
		customer.setStatus(customerSubsidiaryForm.getStatus());
		customer.setComment(customerSubsidiaryForm.getComment());
		Date date = DateService.loginDate;
		customer.setApproveeDate(date);
		customer.setPassword(customerSubsidiaryForm.getContactNum());
		customer.setCustomerSubsidiaryKey(customerSubsidiaryForm.getCustomerSubsidiaryKey());
		customer.setTransactionId(customerSubsidiaryForm.getTransactionId());
		customerDAO.updateSubsidiary(customer);

		String status = customerSubsidiaryForm.getStatus();

		if (status != null && status.equalsIgnoreCase("Approved")) {
			EndUser endUser = new EndUser();
			endUser.setUserName(customerSubsidiaryForm.getName());
			endUser.setPassword(customerSubsidiaryForm.getEmail());
			endUser.setContactNo(customerSubsidiaryForm.getContactNum());
			endUser.setCurrentRole("ROLE_USERSUBSIDIARY");
			endUser.setRole(6);
			endUser.setPrefferedLanguage("en");
			endUser.setTheme("themeBlue");
			endUser.setEmail(customerSubsidiaryForm.getEmail());
			endUser.setDisplayName(customerSubsidiaryForm.getName());
			endUser.setCustomerHeadKey(customerSubsidiaryForm.getCustomerHeadKey());
			endUser.setCustomerHeadName(customerSubsidiaryForm.getCustomerHeadName());
			endUser.setStatus(customerSubsidiaryForm.getStatus());
			endUser.setTransactionId(customerSubsidiaryForm.getTransactionId());
			endUser.setPasswordFlag(0);
			endUserDAOImpl.createUser(endUser);

		}
		Transaction trans = new Transaction();
		trans.setTransactionId(customerSubsidiaryForm.getTransactionId());
		trans.setTransactionStatus("Customer Subsidiary Approval");
		trans.setTransactionType("Updated Successfully");

		transactionDAO.insertTransaction(trans);

		String stat = customerSubsidiaryForm.getStatus();
		String username = customerSubsidiaryForm.getName();
		String email = customerSubsidiaryForm.getEmail();
		String password = customerSubsidiaryForm.getContactNum();
		String currentrole = "ROLE_USERSUBSIDIARY";
		try {
			if (stat.equals("Approved")) {
				String tex = "Customer Subsidiary  Details Notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + username + ",\n\n Your Account has been Created. "
						+ ",\n\n Your Credentials Details are as follows. " + "\n" + "\n"

						+ "\n\nUserName:" + username + "\n\nPassword:" + password + "\n\nCurrent Role:" + currentrole +

						"\n\n\nRegards,\nBank");
				System.out.println("" + email + username);
				mailSender.send(emails);
				SimpleMailMessage emails1 = new SimpleMailMessage();
				emails1.setTo(email);

			} else if (status.equals("Rejected")) {
				String tex = "Customer Subsidiary Details Notification ";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + username + ",\n\n Your Account has been Rejected  "

						+ "\n" + "\n" + "\n\n\nRegards,\nBank");
				mailSender.send(emails);

			}

			model.put("customerSubsidiaryForm", customerSubsidiaryForm);

		} catch (Exception e) {
			System.out.println(e.getMessage() + e);
		}
		return new ModelAndView("customerSubsidiaryAppTransaction", "model", model);
	}

	@RequestMapping(value = "/customerSubsidiaryAppTransaction", method = RequestMethod.GET)
	public ModelAndView customerSubsidiaryAppTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("customerSubsidiaryForm", customerSubsidiaryForm);

		return new ModelAndView("customerSubsidiaryAppTransaction", "model", model);

	}

	@RequestMapping(value = "/apprCustomerSubsidiaryList", method = RequestMethod.GET)
	public ModelAndView customerSubsidiaryList(ModelMap model) {

		Collection<CustomerSubsidiary> customerList = customerDAO.findAllCustomerSubsidiary();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		if (customerList != null && customerList.size() > 0) {
			model.put("customerList", customerList);

			return new ModelAndView("apprCustomerSubsidiaryList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	/* Transaction List */

	@RequestMapping(value = "/transcationList", method = RequestMethod.GET)
	public ModelAndView getALLTransactionList(ModelMap model, HttpServletRequest request,
											  HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		Collection<Transaction> transactionList = transactionDAO.getList();

		model.put("user", user);
		if (transactionList != null && transactionList.size() > 0) {
			model.put("transactionList", transactionList);
			return new ModelAndView("transcationList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	/* Buyer Details */

	@RequestMapping(value = "/appBuyerPageList", method = RequestMethod.GET)
	public ModelAndView getAllBuyerList(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		Collection<NewBuyer> buyerList = newBuyerDAOImpl.getList();

		model.put("user", user);
		if (buyerList != null && buyerList.size() > 0) {
			model.put("buyerList", buyerList);
			return new ModelAndView("appBuyerPageList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/buyerPage", method = RequestMethod.GET)
	public ModelAndView getAllBuyer(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<NewBuyer> buyerList = newBuyerDAOImpl.getByBuyer().getResultList();

		model.put("user", user);
		if (buyerList != null && buyerList.size() > 0) {
			model.put("buyerList", buyerList);
			return new ModelAndView("buyerPage", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/buyerPageShow", method = RequestMethod.GET)
	public ModelAndView buyerPageShow(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		NewBuyer buyer = newBuyerDAOImpl.findId(id);
		Company company = companyDAO.getByCompanyId(buyer.getCompanyId());

		newBuyerForm.setId(buyer.getId());
		newBuyerForm.setBuyerName(buyer.getBuyerName());
		newBuyerForm.setName(buyer.getName());
		newBuyerForm.setBank(buyer.getBank());
		newBuyerForm.setBranch(buyer.getBranch());
		newBuyerForm.setBankEmail(buyer.getBankEmail());
		newBuyerForm.setUniqueKey(buyer.getUniqueKey());
		newBuyerForm.setCompanyName(company.getCompanyName());
		newBuyerForm.setContactNum(buyer.getContactNum());
		newBuyerForm.setEmail(buyer.getEmail());
		newBuyerForm.setAccExpiryDate(buyer.getAccExpiryDate());

		model.put("user", user);

		model.put("user", user);

		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("buyerPageShow", "model", model);
	}

	@RequestMapping(value = "/buyerPageShowConfirm", method = RequestMethod.POST)
	public ModelAndView approveBankEmpConfrim(@ModelAttribute NewBuyerForm newBuyerForm, ModelMap model,
											  RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("buyerPageShowConfirm", "model", model);
	}

	@RequestMapping(value = "/updatebuyerPageShowConfrim", method = RequestMethod.POST)
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
		buyer.setContactNum(newBuyerForm.getContactNum());
		buyer.setUniqueKey(newBuyerForm.getUniqueKey());
		buyer.setEmail(newBuyerForm.getEmail());
		buyer.setStatus(newBuyerForm.getStatus());
		buyer.setComment(newBuyerForm.getComment());
		Date approvDate = DateService.loginDate;
		buyer.setApproveDate(approvDate);
		buyer.setTransactionId(newBuyerForm.getTransactionId());
		transaction.setTransactionId(newBuyerForm.getTransactionId());
		transaction.setTransactionType("Buyer Details Update Status");
		transaction.setTransactionStatus("Buyer  Deatils  status saved successfully");
		transcationDAOImpl.insertTransaction(transaction);

		newBuyerDAOImpl.update(buyer);

		Notification not = new Notification();
		not.setCustomerName(newBuyerForm.getName());
		Date notDate = DateService.loginDate;
		not.setNotificationDate(notDate);
		not.setNotificationType("New Buyer Approval");
		not.setNotificationDescription(newBuyerForm.getStatus());
		not.setNotificationAcc("main");

		notificationDAO.insertNotification(not);
		String status = "";
		if (!customerBankDetailsDAO.getList().isEmpty()) {
			String bankName = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0).getBankName();
			try {
				status = newBuyerForm.getStatus();
				String buyername = newBuyerForm.getBuyerName();
				String email = newBuyerForm.getEmail();
				String password = newBuyerForm.getContactNum();
				String comment = newBuyerForm.getComment();

				if (status.equals("Approved")) {
					String tex = "Buyer Approved  Details Notification";
					SimpleMailMessage emails = new SimpleMailMessage();
					emails.setTo(email);
					emails.setSubject(tex);
					emails.setText("Hello " + buyername + ",\n\n Your Account has been Created. "
							+ ",\n\n Your Credentials Details are as follows. " + "\n" + "\n"

							+ "\n\nUserName:" + buyername + "\n\nPassword:" + password +

							"\n\n\nRegards,\n" + bankName);
					System.out.println("" + email + buyername);
					mailSender.send(emails);
					SimpleMailMessage emails1 = new SimpleMailMessage();
					emails1.setTo(email);

				} else if (status.equals("Rejected")) {
					String tex = "Buyer Account Create Rejection notification";
					SimpleMailMessage emails = new SimpleMailMessage();
					emails.setTo(email);
					emails.setSubject(tex);
					emails.setText("Hello " + buyername + ",\n\n Your Account has been Rejected  " + "\n\n Comment:"
							+ comment + "\n" + "\n" + "\n\n\nRegards,\n" + bankName);
					mailSender.send(emails);
				} else {
					String tex = "Buyer Pending notification";
					SimpleMailMessage emails = new SimpleMailMessage();
					emails.setTo(email);
					emails.setSubject(tex);
					emails.setText("Hello " + buyername + ",\n\n  Buyer Details has been Pending " + "\n" + "\n"
							+ "\n\n\nRegards,\n" + bankName);
					mailSender.send(emails);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage() + e);
			}
		}else {
			model.put("error", "Email Notification not sent because Customer Bank Details are not present");
		}

		if (status != null && status.equalsIgnoreCase("Approved")) {
			EndUser endUser = new EndUser();
			endUser.setUserName(newBuyerForm.getBuyerName());
			endUser.setPassword(newBuyerForm.getContactNum());
			endUser.setContactNo(newBuyerForm.getContactNum());
			endUser.setCurrentRole("ROLE_BUYER");
			endUser.setRole(8);
			endUser.setPrefferedLanguage("en");
			endUser.setTheme("themeBlue");
			endUser.setEmail(newBuyerForm.getEmail());
			endUser.setDisplayName(newBuyerForm.getBuyerName());
			endUser.setCustomerHeadName(newBuyerForm.getName());
			endUser.setStatus(newBuyerForm.getStatus());
			endUser.setTransactionId(newBuyerForm.getTransactionId());
			endUser.setPasswordFlag(0);
			endUser.setAccExpiryDate(newBuyerForm.getAccExpiryDate());
			endUser.setIsForTrading(Boolean.FALSE);
			endUserDAOImpl.createUser(endUser);

		}

		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("approvalbuyerPageSuccess", "model", model);
	}

	@RequestMapping(value = "/appSupplierPageList", method = RequestMethod.GET)
	public ModelAndView getAllSellerList(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		Collection<Supplier> SupplierList = supplierservice.getList();
		model.put("user", user);

		if (SupplierList != null && SupplierList.size() > 0) {
			model.put("SupplierList", SupplierList);

			return new ModelAndView("appSupplierPageList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/supplierPage", method = RequestMethod.GET)
	public ModelAndView getAllSeller(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		List<Supplier> SupplierList = supplierservice.getBySupplier().getResultList();
		model.put("user", user);
		if (SupplierList != null && SupplierList.size() > 0) {

			model.put("SupplierList", SupplierList);

			return new ModelAndView("supplierPage", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/supplierPageShow", method = RequestMethod.GET)
	public ModelAndView supplierPageShow(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		Supplier supplier = supplierservice.findId(id);

		supplierForm.setId(supplier.getId());
		supplierForm.setContactNum(supplier.getContactNum());
		supplierForm.setBank(supplier.getBank());
		supplierForm.setBranch(supplier.getBranch());
		supplierForm.setBankEmail(supplier.getBankEmail());
		supplierForm.setCompanyName(supplier.getCompanyName());
		supplierForm.setEmail(supplier.getEmail());
		supplierForm.setName(supplier.getName());
		supplierForm.setSupplierName(supplier.getSupplierName());
		supplierForm.setComment(supplier.getComment());
		supplierForm.setCustomerPrefix(supplier.getCustomerPrefix());
		supplierForm.setStatus(supplier.getStatus());
		supplierForm.setUniquekey(supplier.getUniquekey());
		supplierForm.setAccExpiryDate(supplier.getAccExpiryDate());
		supplierForm.setCompanyId(supplier.getCompanyId());
		model.put("user", user);
		model.put("supplierForm", supplierForm);

		return new ModelAndView("supplierPageShow", "model", model);
	}

	@RequestMapping(value = "/supplierPageShowConfirm", method = RequestMethod.POST)
	public ModelAndView approvesupplierPageShowConfirm(@ModelAttribute SupplierForm supplierForm, ModelMap model,
													   RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("supplierPageShowConfirm", "model", model);
	}

	@RequestMapping(value = "/updatesupplierPageShowConfrim", method = RequestMethod.POST)
	public ModelAndView updatesupplierPageShowConfrim(@ModelAttribute SupplierForm supplierForm, ModelMap model,
													  RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		
		supplierForm.setTransactionId(KeyGenerator.generateTransactionKey());
		supplierForm.setUniquekey(KeyGenerator.generateTransactionKey());
		
		model.put("user", user);

		Supplier supplier = supplierservice.findId(supplierForm.getId());
		Company company = companyDAO.getByCompanyId(supplier.getCompanyId());
		Transaction transaction = new Transaction();
		supplier.setId(supplierForm.getId());
		supplier.setSupplierName(supplierForm.getSupplierName());
		supplier.setName(supplierForm.getName());
		supplier.setUniquekey(supplierForm.getUniquekey());
		supplier.setSupplierName(supplierForm.getSupplierName());
		supplier.setContactNum(supplierForm.getContactNum());
		supplier.setEmail(supplierForm.getEmail());
		supplier.setComment(supplierForm.getComment());
		supplier.setStatus(supplierForm.getStatus());
		Date appDate = DateService.loginDate;
		supplier.setApproveDate(appDate);
		supplier.setTransactionId(supplierForm.getTransactionId());
		transaction.setTransactionId(supplierForm.getTransactionId());
		transaction.setTransactionType("Supplier Deatils Update Status");
		transaction.setTransactionStatus("Supplier Deatils  status saved successfully");
		transcationDAOImpl.insertTransaction(transaction);
		supplierservice.update(supplier);

		Notification not = new Notification();
		not.setCustomerName(supplierForm.getName());
		Date notDate = DateService.loginDate;
		not.setNotificationDate(notDate);
		not.setNotificationType("New Supplier Approval");
		not.setNotificationDescription(supplierForm.getStatus());
		not.setNotificationAcc("main");
		notificationDAO.insertNotification(not);
		String status = "";
		if (!customerBankDetailsDAO.getList().isEmpty()) {
			String bankName = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0).getBankName();
			try {
				status = supplierForm.getStatus();
				String suppliername = supplierForm.getSupplierName();
				String email = supplierForm.getEmail();
				String password = supplierForm.getContactNum();
				String comment = supplierForm.getComment();

				if (status.equals("Approved")) {
					String tex = "Supplier Approved  Details Notification";
					SimpleMailMessage emails = new SimpleMailMessage();
					emails.setTo(email);
					emails.setSubject(tex);
					emails.setText("Hello " + suppliername + ",\n\n Your Account has been Created. "
							+ ",\n\n Your Credentials Details are as follows. " + "\n" + "\n"

							+ "\n\nUserName:" + suppliername + "\n\nPassword:" + password +

							"\n\n\nRegards,\n" + bankName);
					System.out.println("" + email + suppliername);
					mailSender.send(emails);
					SimpleMailMessage emails1 = new SimpleMailMessage();
					emails1.setTo(email);

				} else if (status.equals("Rejected")) {
					String tex = "Supplier Account Create Rejection notification";
					SimpleMailMessage emails = new SimpleMailMessage();
					emails.setTo(email);
					emails.setSubject(tex);
					emails.setText("Hello " + suppliername + ",\n\n Your Account has been Rejected  " + "\n\n Comment:"
							+ comment + "\n" + "\n" + "\n\n\nRegards,\n" + bankName);
					mailSender.send(emails);
				} else {
					String tex = "Supplier Pending notification";
					SimpleMailMessage emails = new SimpleMailMessage();
					emails.setTo(email);
					emails.setSubject(tex);
					emails.setText("Hello " + suppliername + ",\n\n  Supplier Details has been Pending " + "\n" + "\n"
							+ "\n\n\nRegards,\n" + bankName);
					mailSender.send(emails);
				}
				model.put("supplierForm", supplierForm);

			} catch (Exception e) {
				System.out.println(e.getMessage() + e);
			}
		} else {
			model.put("error", "Email Notification not sent because Customer Bank Details are not present");
		}

		if (status != null && status.equalsIgnoreCase("Approved")) {
			EndUser endUser = new EndUser();
			endUser.setUserName(supplierForm.getSupplierName());
			endUser.setPassword(supplierForm.getContactNum());
			endUser.setContactNo(supplierForm.getContactNum());
			endUser.setCurrentRole("ROLE_VENDOR");
			endUser.setRole(7);
			endUser.setPrefferedLanguage("en");
			endUser.setTheme("themeBlue");
			endUser.setEmail(supplierForm.getEmail());
			endUser.setDisplayName(supplierForm.getSupplierName());
			endUser.setCustomerHeadName(supplierForm.getName());
			endUser.setStatus(supplierForm.getStatus());
			endUser.setTransactionId(supplierForm.getTransactionId());
			endUser.setPasswordFlag(0);
			endUser.setAccExpiryDate(supplierForm.getAccExpiryDate());
			endUser.setCompanyId(company.getId());
			endUser.setIsForTrading(Boolean.FALSE);
			endUserDAOImpl.createUser(endUser);

		}
		model.put("supplierForm", supplierForm);


		return new ModelAndView("approvalsupplierPageSuccess", "model", model);
	}

	@RequestMapping(value = "/appMasterPlanDetails", method = RequestMethod.GET)
	public ModelAndView appMasterPlanDetails(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<MasterPlan> masterList = masterPlanDAO.getMasterPlanByMng().getResultList();

		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);
			return new ModelAndView("appMasterPlanDetails", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/appMasterPlanFullDetails", method = RequestMethod.GET)
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

			if (buyingList != null && buyingList.size() > 0) {
				model.put("buyingList", buyingList);
			}

			List<SellerBuyingCost> sellerList = sellerBuyingCostDAO.getSellerBuyingCostList(masterList.getMasterKey())
					.getResultList();
			if (sellerList != null && sellerList.size() > 0) {
				model.put("sellerList", sellerList);
			}

			List<WorkingCapital> wcList = workingCapitalDAO.getWCBYMAsterKey(masterList.getMasterKey()).getResultList();
			if (wcList != null && wcList.size() > 0) {
				model.put("wcList", wcList);
			}
			List<ConsortiumBank> bankList = consortiumDAO.getConsortiumByMasterKey(masterList.getMasterKey())
					.getResultList();
			if (bankList != null && bankList.size() > 0) {
				model.put("bankList", bankList);
			}

			model.put("masterList", masterList);
		}
		model.put("user", user);

		return new ModelAndView("appMasterPlanFullDetails", "model", model);

	}

	@RequestMapping(value = "/appRiskAssignment", method = RequestMethod.GET)
	public ModelAndView riskAssignment(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("appRiskAssignment", "model", model);

	}

	@RequestMapping(value = "/appMasterPlanCreditAssign", method = RequestMethod.GET)
	public ModelAndView masterPlanCreditAssign(@RequestParam("id") Long id, ModelMap model,
											   RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		
		MasterPlanForm masterPlanForm = new MasterPlanForm();
		Float totalTax = 0f;

		MasterPlan master = masterPlanDAO.getByMasterPlanId(id);

		masterPlanForm.setId(master.getId());
		masterPlanForm.setBuyingCost(master.getBuyingCost());
		masterPlanForm.setTenure(master.getTenure());
		masterPlanForm.setBuyingCostSanc(master.getBuyingCostSanc());
		masterPlanForm.setRateOfInt(master.getRateOfInt());
		masterPlanForm.setRateOfInt1(master.getRateOfInt1());
		masterPlanForm.setFunalAmt(master.getFunalAmt());
		masterPlanForm.setFlatCharges(master.getFlatCharges());
		masterPlanForm.setProcFee(master.getProcFee());
		masterPlanForm.setDocFee(master.getDocFee());
		masterPlanForm.setLateFee(master.getLateFee());
		totalTax =  (master.getTaxAmt()+master.getTaxAmt1()+master.getTaxAmt2());
		masterPlanForm.setTotalTaxAmt(totalTax);

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);
		

		return new ModelAndView("appMasterPlanCreditAssign", "model", model);

	}

	@RequestMapping(value = "/appMasterPlanWcAssign", method = RequestMethod.GET)
	public ModelAndView masterPlanWcAssign(@RequestParam("id") Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		MasterPlan master = masterPlanDAO.getByMasterPlanId(id);

		masterPlanForm.setId(master.getId());
		masterPlanForm.setWcTenure(master.getWcTenure());
		masterPlanForm.setWorkingCapital(master.getWorkingCapital());
		masterPlanForm.setWcSancAmount(master.getWcSancAmount());
		masterPlanForm.setWcSancInterest(master.getWcSancInterest());
		masterPlanForm.setWcTotalInterest(master.getWcTotalInterest());
		masterPlanForm.setWcTotalAmount(master.getWcTotalAmount());

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);
		return new ModelAndView("appMasterPlanWcAssign", "model", model);
	}

	@RequestMapping(value = "/appMasterPlanApproveStatus", method = RequestMethod.GET)
	public ModelAndView bankMasterPlanApproveStatus(@RequestParam("id") Long id, ModelMap model) {

		MasterPlan master = masterPlanDAO.getByMasterPlanId(id);

		masterPlanForm.setId(master.getId());
		masterPlanForm.setCustomer(master.getCustomer());
		masterPlanForm.setMasterKey(master.getMasterKey());
		masterPlanForm.setStatus(master.getStatus());
		masterPlanForm.setTransactionId(master.getTransactionId());
		masterPlanForm.setCustomerEmail(master.getCustomerEmail());

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("appMasterPlanApproveStatus", "model", model);

	}

	@RequestMapping(value = "/appMasterPlanApproveStatusConfirm", method = RequestMethod.POST)
	public ModelAndView bankMasterPlanApproveStatusConfirm(ModelMap model,
														   @ModelAttribute MasterPlanForm masterPlanForm, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		masterPlanForm.setTransactionId(masterPlanForm.getTransactionId());
		masterPlanForm.setCustomer(masterPlanForm.getCustomer());
		masterPlanForm.setManagerStatus(masterPlanForm.getManagerStatus());
		masterPlanForm.setManagerComment(masterPlanForm.getManagerComment());
		masterPlanForm.setCustomerEmail(masterPlanForm.getCustomerEmail());

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("appMasterPlanApproveStatusConfirm", "model", model);

	}

	@RequestMapping(value = "/appMasterPlanApproveStatusPost", method = RequestMethod.POST)
	public ModelAndView draftsMasterPlanInfoConfirm(ModelMap model, @ModelAttribute MasterPlanForm masterPlanForm,
													RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		masterPlanForm.setTransactionId(masterPlanForm.getTransactionId());

		MasterPlan master = masterPlanDAO.getByMasterPlanId(masterPlanForm.getId());

		master.setManagerStatus(masterPlanForm.getManagerStatus());
		master.setManagerComment(masterPlanForm.getManagerComment());

		Date masterDate = DateService.loginDate;
		master.setBuyingCostDate(masterDate);

		if (master.getManagerStatus().equals("Rejected")) {
			master.setBuyingCostSanc(null);
			master.setWcSancAmount(null);

		}
		masterPlanForm.setCustomer(masterPlanForm.getCustomer());
		masterPlanForm.setBuyingCostSanc(master.getBuyingCostSanc());
		masterPlanForm.setWcTotalAmount(master.getWcTotalAmount());
		masterPlanForm.setAmt(master.getAmountPaid());
		masterPlanForm.setWcSancAmount(master.getWcSancAmount());
		masterPlanForm.setWorkingCapital(master.getWorkingCapital());

		Date appdate = DateService.loginDate;
		master.setAppDate(appdate);
		masterPlanDAO.updatePlan(master);

		Transaction trans = new Transaction();
		trans.setTransactionId(masterPlanForm.getTransactionId());
		trans.setTransactionStatus("Master Plan Approval/Reject By Manager");
		trans.setTransactionType("Submitted Successfully");

		transactionDAO.insertTransaction(trans);

		Notification not = new Notification();

		not.setCustomerName(masterPlanForm.getCustomer());
		Date notDate = DateService.loginDate;
		not.setNotificationDate(notDate);
		not.setNotificationType("Master Plan Approval");
		not.setNotificationDescription(masterPlanForm.getManagerStatus());
		not.setNotificationAcc("main");

		notificationDAO.insertNotification(not);

		String email = masterPlanForm.getCustomerEmail();
		String customer = masterPlanForm.getCustomer();

		String masterKey = masterPlanForm.getMasterKey();
		Float workingcapitalcost = masterPlanForm.getWorkingCapital();
		Float workingSancAmount = masterPlanForm.getWcSancAmount();
		Float amtsanctioned = masterPlanForm.getWcSancAmount();
		Float buyingCostSanc = masterPlanForm.getBuyingCostSanc();
		String stat = masterPlanForm.getManagerStatus();
		String bankName = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0).getBankName();
		try {
			if (stat.equals("Approved")) {

				String tex = "Master Plan Details Notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + customer + ",\n\n Your MasterPlan has been approved " + "\n" + "\n"

						+ "\n\nMaterKey:" + masterKey + "\n\nAmount Sanctioned Cost:" + amtsanctioned
						+ "\n\nBuying Sanctioned Cost:" + buyingCostSanc +

						"\n\nWorking Capital Cost:" + workingcapitalcost + "\n\nWorking Capital Sanctioned Cost:"
						+ workingSancAmount + "\n\n Please add warehouse for your masterplan."
						+ "\n\nCheck your Profile for full Details"

						+ "\n\n\nRegards,\n"+bankName);
				System.out.println("" + email + customer);
				mailSender.send(emails);
				SimpleMailMessage emails1 = new SimpleMailMessage();
				emails1.setTo(email);
				emails1.setSubject(tex);
				emails1.setText(
						"Hello " + customer + "\n\n Your MasterPlan has been approved.\n" + "\n\nRegards,\n"+bankName);
			} else if (stat.equals("Rejected")) {
				String tex = "Master Plan Rejection notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + customer + ",\n\n\n Your Masterplan has been Rejected: "

						+ "\n.\n\n\nRegards,\n"+bankName);
				mailSender.send(emails);
			}

			attributes.addFlashAttribute("success", "Mail has been sent successfully.");

			model.put("user", user);
			model.put("masterPlanForm", masterPlanForm);

		} catch (Exception e) {
			System.out.println(e.getMessage() + e);
		}
		return new ModelAndView("appMasterPlanAppTransaction", "model", model);
	}

	@RequestMapping(value = "/appMasterPlanAppTransaction", method = RequestMethod.GET)
	public ModelAndView collateralTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("appMasterPlanAppTransaction", "model", model);

	}

	@RequestMapping(value = "/appMasterPlanFullList", method = RequestMethod.GET)
	public ModelAndView masterPlanFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<MasterPlan> masterList = masterPlanDAO.getAllMasterPlans().getResultList();

		model.put("user", user);
		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);

			return new ModelAndView("appMasterPlanFullList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/uploadDocument", method = RequestMethod.GET)
	public ModelAndView getAllUploadDocuments(ModelMap model, HttpServletRequest request,
											  HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<UploadedFile> uploadList = uploadDaoImpl.getByPending().getResultList();
		if (uploadList != null && uploadList.size() > 0) {
			model.put("uploadList", uploadList);

			return new ModelAndView("uploadDocument", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/uploadPageShow", method = RequestMethod.GET)
	public ModelAndView uploadPageShow(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		UploadedFile upload = uploadDaoImpl.findId(id);
		uploadedFileForm.setId(upload.getId());
		uploadedFileForm.setComment(upload.getComment());
		uploadedFileForm.setCustomerName(upload.getCustomerName());
		uploadedFileForm.setDocument(upload.getDocument());
		uploadedFileForm.setFileName(upload.getFileName());
		uploadedFileForm.setReason(upload.getReason());
		uploadedFileForm.setStatus(upload.getStatus());
		uploadedFileForm.setUploadDate(upload.getUploadDate());

		model.put("user", user);
		model.put("uploadedFileForm", uploadedFileForm);

		return new ModelAndView("uploadPageShow", "model", model);
	}

	@RequestMapping(value = "/uploadPageShowConfirm", method = RequestMethod.POST)
	public ModelAndView uploadPageShowConfirm(@ModelAttribute UploadedFileForm uploadedFileForm, ModelMap model,
											  RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("uploadedFileForm", uploadedFileForm);

		return new ModelAndView("uploadPageShowConfirm", "model", model);
	}

	@RequestMapping(value = "/uploadPageShowConfrim", method = RequestMethod.POST)
	public ModelAndView uploadPageShowConfrim(@ModelAttribute UploadedFileForm uploadedFileForm, ModelMap model,
											  RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		
		masterPlanForm.setTransactionId(KeyGenerator.generateTransactionKey());
		model.put("user", user);

		UploadedFile upload = uploadDaoImpl.findId(uploadedFileForm.getId());
		Transaction transaction = new Transaction();
		upload.setCustomerName(uploadedFileForm.getCustomerName());
		upload.setDocument(uploadedFileForm.getDocument());
		upload.setFileName(uploadedFileForm.getFileName());
		upload.setReason(uploadedFileForm.getReason());
		upload.setStatus(uploadedFileForm.getStatus());
		upload.setComment(uploadedFileForm.getComment());
		upload.setTransactionId(uploadedFileForm.getTransactionId());
		transaction.setTransactionId(uploadedFileForm.getTransactionId());
		transaction.setTransactionType("Upload File  Update Status");
		transaction.setTransactionStatus("Upload File Update status saved successfully");
		transcationDAOImpl.insertTransaction(transaction);
		uploadDaoImpl.update(upload);

		model.put("uploadedFileForm", uploadedFileForm);

		return new ModelAndView("approvaluploadPageSuccess", "model", model);
	}

	@RequestMapping(value = "/uploadDocumentList", method = RequestMethod.GET)
	public ModelAndView getAllUploadDocumentsLists(ModelMap model, HttpServletRequest request,
												   HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		Collection<UploadedFile> uploadList = uploadDaoImpl.getList();
		if (uploadList != null && uploadList.size() > 0) {
			model.put("uploadList", uploadList);

			return new ModelAndView("uploadDocumentList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/poListForApproval", method = RequestMethod.GET)
	public ModelAndView poListForApproval(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		List<PurchaseOrder> purchase = purchaseOrderDAO.getPurchaseOrderByStatus().getResultList();

		if (purchase != null && purchase.size() > 0) {
			model.put("purchase", purchase);
			return new ModelAndView("poListForApproval", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/invoiceListForApproval", method = RequestMethod.GET)
	public ModelAndView invoiceListForApproval(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		List<Invoice> invoice = invoiceDAO.getInvoiceByStatus().getResultList();

		if (invoice != null && invoice.size() > 0) {
			model.put("invoice", invoice);
			return new ModelAndView("invoiceListForApproval", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}
	}

	@RequestMapping(value = "/poForApproval", method = RequestMethod.GET)
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
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("poForApproval", "model", model);

	}

	@RequestMapping(value = "/poForApprovalConfirm", method = RequestMethod.POST)
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

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("poForApprovalConfirm", "model", model);

	}

	@RequestMapping(value = "/poForApprovalPost", method = RequestMethod.POST)
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

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(purchaseOrderForm.getId());

		purchase.setStatus(purchaseOrderForm.getStatus());
		purchase.setComment(purchaseOrderForm.getComment());

		if (purchaseOrderForm.getStatus().equals("Approved")) {
			purchase.setrStatus("Pending");
			purchase.setRequest("No");
		}

		purchaseOrderDAO.updatePo(purchase);

		if (purchaseOrderForm.getFlag().equals(0)) {
			if (purchase.getStatus().equals("Rejected")) {
				MasterPlan plan = masterPlanDAO.getMasterPlanByMasterKey(purchaseOrderForm.getMasterKey())
						.getSingleResult();

				Float amt = purchaseOrderForm.getAmount();
				Float utiliAmt = plan.getUtilizedBusnsAmt();
				Float bal = plan.getBalance();

				Float totalUtilized = utiliAmt - amt;
				Float totalBal = bal + amt;
				plan.setBalance(totalBal);
				plan.setUtilizedBusnsAmt(totalUtilized);

				masterPlanDAO.updatePlan(plan);
			}
		} else {
			if (purchase.getStatus().equals("Rejected")) {
				FundsDistribute funds = fundsDistributeDAO
						.getFundsListByKeyAndName(purchaseOrderForm.getMasterKey(), purchaseOrderForm.getCustomerName())
						.getSingleResult();

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

		Transaction trans = new Transaction();
		trans.setTransactionId(purchaseOrderForm.getTransactionId());
		trans.setTransactionStatus("Purchase Order Approval/Reject By Manager");
		trans.setTransactionType("Submitted Successfully");

		transactionDAO.insertTransaction(trans);

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("poForApprovalTransaction", "model", model);

	}

	@RequestMapping(value = "/poForApprovalTransaction", method = RequestMethod.GET)
	public ModelAndView poForApprovalTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("poForApprovalTransaction", "model", model);

	}

	@RequestMapping(value = "/invoiceForApproval", method = RequestMethod.GET)
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
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("invoiceForApproval", "model", model);

	}

	@RequestMapping(value = "/invoiceForApprovalConfirm", method = RequestMethod.POST)
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

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("invoiceForApprovalConfirm", "model", model);

	}

	@RequestMapping(value = "/invoiceForApprovalPost", method = RequestMethod.POST)
	public ModelAndView invoiceForApprovalPost(ModelMap model, @ModelAttribute InvoiceForm invoiceForm,
											   RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		invoiceForm.setTransactionId(invoiceForm.getTransactionId());
		invoiceForm.setCustomerHeadEmail(invoiceForm.getCustomerHeadEmail());
		invoiceForm.setCustomerBranchEmail(invoiceForm.getCustomerBranchEmail());
		invoiceForm.setBuyerEmail(invoiceForm.getBuyerEmail());

		Invoice invoice = invoiceDAO.getByInvoiceId(invoiceForm.getId());

		invoice.setStatus(invoiceForm.getStatus());
		invoice.setComment(invoiceForm.getComment());

		if (invoiceForm.getStatus().equals("Approved")) {
			invoice.setrStatus("Pending");
			invoice.setRequest("No");
		}

		invoiceDAO.updateInvoice(invoice);

		Transaction trans = new Transaction();
		trans.setTransactionId(invoiceForm.getTransactionId());
		trans.setTransactionStatus("Invoice Approval/Reject By Manager");
		trans.setTransactionType("Submitted Successfully");

		transactionDAO.insertTransaction(trans);

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("invoiceForApprovalTransaction", "model", model);

	}

	@RequestMapping(value = "/invoiceForApprovalTransaction", method = RequestMethod.GET)
	public ModelAndView invoiceForApprovalTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("invoiceForApprovalTransaction", "model", model);

	}

	@RequestMapping(value = "/fullInvoiceList", method = RequestMethod.GET)
	public ModelAndView fullInvoiceList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<Invoice> invoiceList = invoiceDAO.getList().getResultList();

		model.put("user", user);
		if (invoiceList != null && invoiceList.size() > 0) {
			model.put("invoiceList", invoiceList);

			return new ModelAndView("fullInvoiceList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/fullPoList", method = RequestMethod.GET)
	public ModelAndView fullPoList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PurchaseOrder> poList = purchaseOrderDAO.getList().getResultList();

		model.put("user", user);
		if (poList != null && poList.size() > 0) {
			model.put("poList", poList);

			return new ModelAndView("fullPoList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/inventoryAppList", method = RequestMethod.GET)
	public ModelAndView InventoryList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		Collection<Inventory> inventoryList = invenrotyDAO.getList();
		model.put("user", user);
		if (inventoryList != null && inventoryList.size() > 0) {
			model.put("inventoryList", inventoryList);

			return new ModelAndView("inventoryListApproved", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/inventoryInvoiceAppMngList", method = RequestMethod.GET)
	public ModelAndView inventoryInvoiceBankList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		Collection<InvoiceInventory> inventoryInvoiceList = invoiceInventoryDAO.getList();
		model.put("user", user);
		if (inventoryInvoiceList != null && inventoryInvoiceList.size() > 0) {
			model.put("inventoryInvoiceList", inventoryInvoiceList);

			return new ModelAndView("inventoryInvoiceAppMngList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/rateListApproval", method = RequestMethod.GET)
	public ModelAndView rateList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PurchaseOrder> poList = purchaseOrderDAO.getPoListForRatesApproval().getResultList();

		model.put("user", user);
		if (poList != null && poList.size() > 0) {
			model.put("poList", poList);

			return new ModelAndView("rateListApproval", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/rateListForApproval", method = RequestMethod.GET)
	public ModelAndView rateListForApproval(@RequestParam("id") Long id, ModelMap model) {

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(id);

		purchaseOrderForm.setId(purchase.getId());
		purchaseOrderForm.setCustomerName(purchase.getCustomerName());
		purchaseOrderForm.setPoKey(purchase.getPoKey());
		purchaseOrderForm.setFunalAmt(purchase.getFunalAmt());
		purchaseOrderForm.setRateOfInt(purchase.getRateOfInt());
		purchaseOrderForm.setCustomerBranchEmail(purchase.getCustomerBranchEmail());
		purchaseOrderForm.setCustomerHeadEmail(purchase.getCustomerHeadEmail());
		purchaseOrderForm.setTransactionId(purchase.getTransactionId());

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("rateListForApproval", "model", model);

	}

	@RequestMapping(value = "/rateListForApprovalConfirm", method = RequestMethod.POST)
	public ModelAndView rateListForApprovalConfirm(ModelMap model, @ModelAttribute PurchaseOrderForm purchaseOrderForm,
												   RedirectAttributes attributes) {

		purchaseOrderForm.setId(purchaseOrderForm.getId());
		purchaseOrderForm.setCustomerName(purchaseOrderForm.getCustomerName());
		purchaseOrderForm.setFunalAmt(purchaseOrderForm.getFunalAmt());
		purchaseOrderForm.setRateOfInt(purchaseOrderForm.getRateOfInt());
		purchaseOrderForm.setCustomerBranchEmail(purchaseOrderForm.getCustomerBranchEmail());
		purchaseOrderForm.setCustomerHeadEmail(purchaseOrderForm.getCustomerHeadEmail());
		purchaseOrderForm.setrStatus(purchaseOrderForm.getrStatus());
		purchaseOrderForm.setrComment(purchaseOrderForm.getrComment());
		purchaseOrderForm.setTransactionId(purchaseOrderForm.getTransactionId());

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("rateListForApprovalConfirm", "model", model);

	}

	@RequestMapping(value = "/rateListForApprovalPost", method = RequestMethod.POST)
	public ModelAndView rateListForApprovalPost(ModelMap model, @ModelAttribute PurchaseOrderForm purchaseOrderForm,
												RedirectAttributes attributes) {
		purchaseOrderForm.setTransactionId(purchaseOrderForm.getTransactionId());
		purchaseOrderForm.setCustomerBranchEmail(purchaseOrderForm.getCustomerBranchEmail());
		purchaseOrderForm.setCustomerHeadEmail(purchaseOrderForm.getCustomerHeadEmail());

		EndUser user = getCurrentLoggedUserDetails();
		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(purchaseOrderForm.getId());

		purchase.setrStatus(purchaseOrderForm.getrStatus());
		purchase.setrComment(purchaseOrderForm.getrComment());

		if (purchaseOrderForm.getrStatus().equals("Rejected")) {
			purchase.setRequest("No");
		}

		purchaseOrderDAO.updatePo(purchase);

		Transaction trans = new Transaction();
		trans.setTransactionId(purchaseOrderForm.getTransactionId());
		trans.setTransactionStatus("Rates Approval/Reject By Manager");
		trans.setTransactionType("Submitted Successfully");

		transactionDAO.insertTransaction(trans);

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("rateListForApprovalTransaction", "model", model);

	}

	@RequestMapping(value = "/rateListForApprovalTransaction", method = RequestMethod.GET)
	public ModelAndView rateListForApprovalTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("rateListForApprovalTransaction", "model", model);

	}

	@RequestMapping(value = "/rateInvoiceListApproval", method = RequestMethod.GET)
	public ModelAndView rateInvoiceListApproval(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<Invoice> invoiceList = invoiceDAO.getInvoiceListForRatesApproval().getResultList();

		model.put("user", user);
		if (invoiceList != null && invoiceList.size() > 0) {
			model.put("invoiceList", invoiceList);

			return new ModelAndView("rateInvoiceListApproval", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/rateInvoiceListForApproval", method = RequestMethod.GET)
	public ModelAndView rateInvoiceListForApproval(@RequestParam("id") Long id, ModelMap model) {

		Invoice invoice = invoiceDAO.getByInvoiceId(id);

		invoiceForm.setId(invoice.getId());
		invoiceForm.setCustomerName(invoice.getCustomerName());
		invoiceForm.setPoKey(invoice.getPoKey());
		invoiceForm.setFunalAmt(invoice.getFunalAmt());
		invoiceForm.setRateOfInt(invoice.getRateOfInt());
		invoiceForm.setCustomerBranchEmail(invoice.getCustomerBranchEmail());
		invoiceForm.setCustomerHeadEmail(invoice.getCustomerHeadEmail());
		invoiceForm.setTransactionId(invoice.getTransactionId());

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("rateInvoiceListForApproval", "model", model);

	}

	@RequestMapping(value = "/rateInvoiceListForApprovalConfirm", method = RequestMethod.POST)
	public ModelAndView rateInvoiceListForApprovalConfirm(ModelMap model, @ModelAttribute InvoiceForm invoiceForm,
														  RedirectAttributes attributes) {

		invoiceForm.setId(invoiceForm.getId());
		invoiceForm.setCustomerName(invoiceForm.getCustomerName());
		invoiceForm.setFunalAmt(invoiceForm.getFunalAmt());
		invoiceForm.setRateOfInt(invoiceForm.getRateOfInt());
		invoiceForm.setCustomerBranchEmail(invoiceForm.getCustomerBranchEmail());
		invoiceForm.setCustomerHeadEmail(invoiceForm.getCustomerHeadEmail());
		invoiceForm.setrStatus(invoiceForm.getrStatus());
		invoiceForm.setrComment(invoiceForm.getrComment());
		invoiceForm.setTransactionId(invoiceForm.getTransactionId());

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("rateInvoiceListForApprovalConfirm", "model", model);

	}

	@RequestMapping(value = "/rateInvoiceListForApprovalPost", method = RequestMethod.POST)
	public ModelAndView rateInvoiceListForApprovalPost(ModelMap model, @ModelAttribute InvoiceForm invoiceForm,
													   RedirectAttributes attributes) {
		invoiceForm.setTransactionId(invoiceForm.getTransactionId());
		invoiceForm.setCustomerBranchEmail(invoiceForm.getCustomerBranchEmail());
		invoiceForm.setCustomerHeadEmail(invoiceForm.getCustomerHeadEmail());

		EndUser user = getCurrentLoggedUserDetails();
		Invoice invoice = invoiceDAO.getByInvoiceId(invoiceForm.getId());

		invoice.setrStatus(invoiceForm.getrStatus());
		invoice.setrComment(invoiceForm.getrComment());

		if (invoiceForm.getrStatus().equals("Rejected")) {
			invoice.setRequest("No");
		}

		invoiceDAO.updateInvoice(invoice);

		Transaction trans = new Transaction();
		trans.setTransactionId(invoiceForm.getTransactionId());
		trans.setTransactionStatus("Rates Approval/Reject By Manager");
		trans.setTransactionType("Submitted Successfully");

		transactionDAO.insertTransaction(trans);

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("rateInvoiceListForApprovalTransaction", "model", model);

	}

	@RequestMapping(value = "/rateInvoiceListForApprovalTransaction", method = RequestMethod.GET)
	public ModelAndView rateInvoiceListForApprovalTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("rateInvoiceListForApprovalTransaction", "model", model);

	}

	@RequestMapping(value = "/limitBurstRatesApprList", method = RequestMethod.GET)
	public ModelAndView setLimitBurstRatesList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<LimitBurst> limit = limitBurstDAO.getByAppMngStatus().getResultList();

		model.put("user", user);
		if (limit != null && limit.size() > 0) {
			model.put("limit", limit);

			return new ModelAndView("limitBurstRatesApprList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/limitBurstRatesAppr", method = RequestMethod.GET)
	public ModelAndView limitBurstRatesAssign(@RequestParam("id") Long id, ModelMap model,
											  RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		LimitBurst limit = limitBurstDAO.getByLimitBurstId(id);

		limitBurstForm.setId(limit.getId());
		limitBurstForm.setReqAmt(limit.getReqAmt());
		limitBurstForm.setFinalAmt(limit.getFinalAmt());
		limitBurstForm.setTenure(limit.getTenure());
		limitBurstForm.setTransactionId(limit.getTransactionId());
		limitBurstForm.setCustomerEmail(limit.getCustomerEmail());
		limitBurstForm.setCustomerHeadName(limit.getCustomerHeadName());
		limitBurstForm.setMasterKey(limit.getMasterKey());

		model.put("user", user);
		model.put("limitBurstForm", limitBurstForm);
		return new ModelAndView("limitBurstRatesAppr", "model", model);

	}

	@RequestMapping(value = "/limitBurstRatesApprConfirm", method = RequestMethod.POST)
	public ModelAndView limitBurstRatesAssignConfirm(ModelMap model, @ModelAttribute LimitBurstForm limitBurstForm,
													 RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		limitBurstForm.setId(limitBurstForm.getId());
		limitBurstForm.setCustomerHeadName(limitBurstForm.getCustomerHeadName());
		limitBurstForm.setReqAmt(limitBurstForm.getReqAmt());
		limitBurstForm.setFinalAmt(limitBurstForm.getFinalAmt());
		limitBurstForm.setTenure(limitBurstForm.getTenure());
		limitBurstForm.setTransactionId(limitBurstForm.getTransactionId());
		limitBurstForm.setaStatus(limitBurstForm.getaStatus());
		limitBurstForm.setComment(limitBurstForm.getComment());
		limitBurstForm.setCustomerEmail(limitBurstForm.getCustomerEmail());
		limitBurstForm.setMasterKey(limitBurstForm.getMasterKey());

		model.put("user", user);
		model.put("limitBurstForm", limitBurstForm);

		return new ModelAndView("limitBurstRatesApprConfirm", "model", model);

	}

	@RequestMapping(value = "/limitBurstRatesApprPost", method = RequestMethod.POST)
	public ModelAndView limitBurstRatesApprPost(ModelMap model, @ModelAttribute LimitBurstForm limitBurstForm,
												RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		if (limitBurstForm.getaStatus().equals("Approved")) {
			LimitBurst limit = limitBurstDAO.getByLimitBurstId(limitBurstForm.getId());

			limit.setaStatus(limitBurstForm.getaStatus());
			limit.setComment(limitBurstForm.getComment());
			Date limitDate = DateService.loginDate;
			limit.setLimitDate(limitDate);
			limitBurstDAO.updateLimitBurst(limit);

			MasterPlan plan = masterPlanDAO.getMasterPlanByMasterKey(limitBurstForm.getMasterKey()).getSingleResult();

			Float amt = plan.getBalance();
			Float limitAmt = limitBurstForm.getReqAmt();
			Float finalAmt = amt + limitAmt;
			plan.setBalance(finalAmt);
			masterPlanDAO.updatePlan(plan);
		} else {
			LimitBurst limit = limitBurstDAO.getByLimitBurstId(limitBurstForm.getId());

			limit.setaStatus(limitBurstForm.getaStatus());
			limit.setComment(limitBurstForm.getComment());
			limit.setFinalAmt(null);
			limitBurstDAO.updateLimitBurst(limit);

		}

		Transaction trans = new Transaction();
		trans.setTransactionId(limitBurstForm.getTransactionId());
		trans.setTransactionStatus("Approve Or Reject");
		trans.setTransactionType("Submitted Successfully");

		transactionDAO.insertTransaction(trans);
		String bankName = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0).getBankName();

		try {

			String custheademail = limitBurstForm.getCustomerEmail();

			String customerName = limitBurstForm.getCustomerHeadName();

			Float requestamount = limitBurstForm.getReqAmt();
			Float totalamt = limitBurstForm.getFinalAmt();

			String tex = "Limit Burst Approval Notification";
			SimpleMailMessage emails = new SimpleMailMessage();
			emails.setTo(custheademail);
			emails.setSubject(tex);
			emails.setText("Hello " + customerName + ",\n\n  Limit Burst Approval Successfully  " + "\n" + "\n"

					+ "\n\nRequested Amount:" + requestamount + "\n\nTotal Amount Payable with Limit:" + totalamt
					+ "\n\n\nRegards,\n"+bankName);
			System.out.println("" + custheademail + customerName);
			mailSender.send(emails);
			SimpleMailMessage emails1 = new SimpleMailMessage();
			emails1.setTo(custheademail);

			model.put("user", user);
			model.put("limitBurstForm", limitBurstForm);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ModelAndView("limitBurstRatesAppTransaction", "model", model);

	}

	@RequestMapping(value = "/limitBurstRatesAppTransaction", method = RequestMethod.GET)
	public ModelAndView limitBurstRatesAssignTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("limitBurstForm", limitBurstForm);

		return new ModelAndView("limitBurstRatesAppTransaction", "model", model);

	}

	@RequestMapping(value = "/limitBurstAppList", method = RequestMethod.GET)
	public ModelAndView limitBurstAppList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<LimitBurst> limit = limitBurstDAO.getFullList().getResultList();

		model.put("user", user);

		if (limit != null && limit.size() > 0) {
			model.put("limit", limit);

			return new ModelAndView("limitBurstAppList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/apprCustAdminList", method = RequestMethod.GET)
	public ModelAndView apprCustAdminList(ModelMap model) {

		List<ClientAdmin> customerList = clientAdminDAO.getAllClientAdmin().getResultList();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		if (customerList != null && customerList.size() > 0) {
			model.put("customerList", customerList);

			return new ModelAndView("apprCustAdminList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/custAdminApprovalList", method = RequestMethod.GET)
	public ModelAndView custAdminApprovalList(ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		List<ClientAdminForm> customerList = clientAdminDAO.getClientAdminByIdAndAStatusCompAndIsForTrading(Boolean.FALSE);

		if (customerList != null && customerList.size() > 0) {

			model.put("customerList", customerList);
			model.put("clientAdminForm", clientAdminForm);
			return new ModelAndView("custAdminApprovalList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/approveClientAdmin", method = RequestMethod.GET)
	public ModelAndView approveClientAdmin(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		ClientAdmin customer = clientAdminDAO.getByClientAdminId(id);
		Company company = companyDAO.getByCompanyId(customer.getCompanyId());

		clientAdminForm.setId(customer.getId());
		clientAdminForm.setName(customer.getName());
		clientAdminForm.setCompanyName(company.getCompanyName());
		clientAdminForm.setCustomerPrefix(customer.getCustomerPrefix());
		clientAdminForm.setAddress(company.getAddress());
		clientAdminForm.setContactNum(customer.getContactNum());
		clientAdminForm.setEmail(customer.getEmail());
		clientAdminForm.setAccExpiryDate(customer.getAccExpiryDate());

		model.put("clientAdminForm", clientAdminForm);
		return new ModelAndView("approveClientAdmin", "model", model);
	}

	@RequestMapping(value = "/approveClientAdminConfirm", method = RequestMethod.POST)
	public ModelAndView approveClientAdminConfirm(@ModelAttribute ClientAdminForm clientAdminForm, ModelMap model,
												  RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		clientAdminForm.setId(clientAdminForm.getId());
		clientAdminForm.setName(clientAdminForm.getName());
		clientAdminForm.setCompanyName(clientAdminForm.getCompanyName());
		clientAdminForm.setCustomerPrefix(clientAdminForm.getCustomerPrefix());
		clientAdminForm.setAddress(clientAdminForm.getAddress());
		clientAdminForm.setStatus(clientAdminForm.getStatus());
		clientAdminForm.setComment(clientAdminForm.getComment());
		clientAdminForm.setCustomerHeadKey(clientAdminForm.getCustomerHeadKey());
		clientAdminForm.setContactNum(clientAdminForm.getContactNum());
		clientAdminForm.setEmail(clientAdminForm.getEmail());
		clientAdminForm.setAccExpiryDate(clientAdminForm.getAccExpiryDate());

		model.put("clientAdminForm", clientAdminForm);

		return new ModelAndView("approveClientAdminConfirm", "model", model);
	}

	@RequestMapping(value = "/approveClientAdminSave", method = RequestMethod.POST)
	public ModelAndView approveClientAdminSave(@ModelAttribute ClientAdminForm clientAdminForm, ModelMap model,
											   RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		
		clientAdminForm.setTransactionId(KeyGenerator.generateTransactionKey());
		clientAdminForm.setCustomerHeadKey(KeyGenerator.generateTransactionKey());

		model.put("user", user);

		ClientAdmin customer = clientAdminDAO.getByClientAdminId(clientAdminForm.getId());
		Company company = companyDAO.getByCompanyName(clientAdminForm.getCompanyName());

		customer.setName(clientAdminForm.getName());
		//customer.setCompanyName(clientAdminForm.getCompanyName());
		customer.setCompanyId(company.getId());
		customer.setCustomerPrefix(clientAdminForm.getCustomerPrefix());
		//customer.setAddress(clientAdminForm.getAddress());
		customer.setStatus(clientAdminForm.getStatus());
		customer.setComment(clientAdminForm.getComment());
		Date date = DateService.loginDate;
		customer.setApproveeDate(date);
		customer.setCustomerHeadKey(clientAdminForm.getCustomerHeadKey());
		customer.setPassword(customer.getContactNum());

		clientAdminDAO.updateUser(customer);

		String status = clientAdminForm.getStatus();

		if (status != null && status.equalsIgnoreCase("Approved")) {
			EndUser endUser = new EndUser();

			endUser.setUserName(clientAdminForm.getName());
			endUser.setPassword(clientAdminForm.getContactNum());
			endUser.setContactNo(clientAdminForm.getContactNum());
			endUser.setCurrentRole("ROLE_CUSTOMERADMIN");
			endUser.setCompanyId(company.getId());
			endUser.setRole(9);
			endUser.setPrefferedLanguage("en");
			endUser.setTheme("themeBlue");
			endUser.setEmail(clientAdminForm.getEmail());
			endUser.setDisplayName(clientAdminForm.getName());
			endUser.setStatus(clientAdminForm.getStatus());
			endUser.setTransactionId(clientAdminForm.getTransactionId());
			endUser.setPasswordFlag(0);
			endUser.setAccExpiryDate(clientAdminForm.getAccExpiryDate());
			endUser.setIsForTrading(customer.getIsForTrading());
			endUserDAOImpl.createUser(endUser);

		}

		Transaction trans = new Transaction();
		trans.setTransactionId(clientAdminForm.getTransactionId());
		trans.setTransactionStatus("Customer Admin Approval");
		trans.setTransactionType("Approved Successfully");

		transactionDAO.insertTransaction(trans);
		model.put("clientAdminForm", clientAdminForm);

		try {
			String stat = clientAdminForm.getStatus();
			String email = clientAdminForm.getEmail();
			String username = clientAdminForm.getName();
			String currentRole = "ROLE_CUSTOMERADMIN";
			String password = customer.getPassword();
			if (stat.equals("Approved")) {
				String tex = "Customer Admin Details Notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + username
						+ ",\n\n An official customer admin account has been created for you. " + "\n" + "\n"
						+ "\n\n Your Login Credentials are:" + "\n\nUser Name:" + username + "\n\nPassword: " + password
						+ "\n\nCurrent Role: " + currentRole + "\n\n\nRegards,\nBank");
				System.out.println("" + email + username);
				mailSender.send(emails);
				SimpleMailMessage emails1 = new SimpleMailMessage();
				emails1.setTo(email);
				emails1.setSubject(tex);
				emails1.setText(
						"Hello " + username + "\n\n An official customer admin account has been created for you..\n"
								+ "\n\nRegards,\nBank");

			} else if (stat.equals("Rejected")) {
				String tex = "Customer Admin Details Notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + username + ",\n\n\n Your customer admin Account has been Rejected "

						+ "\n\n\nRegards,\nBank");
				mailSender.send(emails);
			} else {
				String tex = "Customer Admin Details Notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + username
						+ ",\n\n\n  Your customer admin Account approval is pending.\n\n\nRegards,\nBank");
				mailSender.send(emails);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage() + e);
		}
		return new ModelAndView("customerAdminAppTransaction", "model", model);
	}

	@RequestMapping(value = "/customerAdminAppTransaction", method = RequestMethod.GET)
	public ModelAndView customerAdminAppTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("clientAdminForm", clientAdminForm);

		return new ModelAndView("customerAdminAppTransaction", "model", model);

	}
	
	@RequestMapping(value = "/viewCompanyList", method = RequestMethod.GET)
	public ModelAndView viewCompanyList(ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Company> companyList = companyDAO.getCompanyList();
		if (companyList != null && companyList.size() > 0) {
			model.put("companyList", companyList);
			model.put("companyForm", companyForm);
			return new ModelAndView("viewCompanyList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}
	}

	@RequestMapping(value = "/companyList", method = RequestMethod.GET)
	public ModelAndView companyList(ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Company> companyList = companyDAO.getCompanyByStatusAndIsForTrading("Pending",Boolean.FALSE);
		if (companyList != null && companyList.size() > 0) {
			model.put("companyList", companyList);
			model.put("companyForm", companyForm);
			return new ModelAndView("companyList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/approveCompany", method = RequestMethod.GET)
	public ModelAndView approveCompany(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		companyForm = new CompanyForm();
		Company company = companyDAO.getByCompanyId(id);
		companyForm.setCompanyId(company.getId());
		companyForm.setCompanyName(company.getCompanyName());
		companyForm.setCompanyPrefix(company.getCompanyPrefix());
		companyForm.setAddress(company.getAddress());
		companyForm.setCity(company.getCity());
		companyForm.setCountry(company.getCountry());
		companyForm.setPincode(company.getPincode());
		companyForm.setState(company.getState());
		companyForm.setStatus(company.getStatus());

		model.put("companyForm", companyForm);
		return new ModelAndView("approveCompany", "model", model);
	}

	@RequestMapping(value = "/approveCompanyConfirm", method = RequestMethod.POST)
	public ModelAndView approveCompanyConfirm(@ModelAttribute CompanyForm companyForm, ModelMap model,
											  RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("companyForm", companyForm);

		return new ModelAndView("approveCompanyConfirm", "model", model);
	}

	@RequestMapping(value = "/approveCompanySave", method = RequestMethod.POST)
	public ModelAndView approveClientAppMngSave(@ModelAttribute CompanyForm companyForm, ModelMap model,
												RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		
		companyForm.setTransactionId(KeyGenerator.generateTransactionKey());
		
		model.put("user", user);

		Company company = companyDAO.getByCompanyId(companyForm.getCompanyId());
		company.setStatus(companyForm.getStatus());
		company.setComment(companyForm.getComment());

		companyDAO.updateCompany(company);

		Transaction trans = new Transaction();
		trans.setTransactionId(companyForm.getTransactionId());
		trans.setTransactionStatus(companyForm.getStatus() + " Successfully");
		trans.setTransactionType("Company Approval");

		transactionDAO.insertTransaction(trans);
		model.put("transaction", trans);
		model.put("companyForm", companyForm);
		model.put("user", user);

		return new ModelAndView("approveCompanySave", "model", model);

	}

	@RequestMapping(value = "/custAppMngApprovalList", method = RequestMethod.GET)
	public ModelAndView custAppMngApprovalList(ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<ClientAppMngForm> customerList = clientAppMngDAO.getClientAppMngByIdAndStatus();

		model.put("user", user);

            if (customerList != null && customerList.size() > 0) {

				model.put("customerList", customerList);
				model.put("clientAppMngForm", clientAppMngForm);
				return new ModelAndView("custAppMngApprovalList", "model", model);
			} else {
				return new ModelAndView("noDataFound1", "model", model);
			}

	}

	@RequestMapping(value = "/approveClientAppMng", method = RequestMethod.GET)
	public ModelAndView approveClientAppMng(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		ClientAppMng customer = clientAppMngDAO.getByClientAppMngId(id);

		Company company = companyDAO.getByCompanyId(customer.getCompanyId());

		clientAppMngForm.setId(customer.getId());
		clientAppMngForm.setName(customer.getName());
		clientAppMngForm.setCompanyName(company.getCompanyName());
		clientAppMngForm.setCustomerPrefix(customer.getCustomerPrefix());
		clientAppMngForm.setAddress(company.getAddress());
		clientAppMngForm.setContactNum(customer.getContactNum());
		clientAppMngForm.setEmail(customer.getEmail());

		model.put("clientAppMngForm", clientAppMngForm);
		return new ModelAndView("approveClientAppMng", "model", model);
	}

	@RequestMapping(value = "/approveClientAppMngConfirm", method = RequestMethod.POST)
	public ModelAndView approveClientAppMngConfirm(@ModelAttribute ClientAppMngForm clientAppMngForm, ModelMap model,
												   RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		clientAppMngForm.setId(clientAppMngForm.getId());
		clientAppMngForm.setName(clientAppMngForm.getName());
		clientAppMngForm.setCompanyName(clientAppMngForm.getCompanyName());
		clientAppMngForm.setCustomerPrefix(clientAppMngForm.getCustomerPrefix());
		clientAppMngForm.setAddress(clientAppMngForm.getAddress());
		clientAppMngForm.setStatus(clientAppMngForm.getStatus());
		clientAppMngForm.setComment(clientAppMngForm.getComment());
		clientAppMngForm.setCustomerHeadKey(clientAppMngForm.getCustomerHeadKey());
		clientAppMngForm.setContactNum(clientAppMngForm.getContactNum());
		clientAppMngForm.setEmail(clientAppMngForm.getEmail());

		model.put("clientAppMngForm", clientAppMngForm);

		return new ModelAndView("approveClientAppMngConfirm", "model", model);
	}

	@RequestMapping(value = "/approveClientAppMngSave", method = RequestMethod.POST)
	public ModelAndView approveClientAppMngSave(@ModelAttribute ClientAppMngForm clientAppMngForm, ModelMap model,
												RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ClientAppMng customer = clientAppMngDAO.getByClientAppMngId(clientAppMngForm.getId());
		Company company = companyDAO.getByCompanyName(clientAppMngForm.getCompanyName());

		customer.setName(clientAppMngForm.getName());
		//customer.setCompanyName(clientAppMngForm.getCompanyName());
		customer.setCustomerPrefix(clientAppMngForm.getCustomerPrefix());
		//customer.setAddress(clientAppMngForm.getAddress());
		customer.setCompanyId(company.getId());
		customer.setStatus(clientAppMngForm.getStatus());
		customer.setComment(clientAppMngForm.getComment());
		Date date = DateService.loginDate;
		customer.setApproveeDate(date);
		customer.setCustomerHeadKey(clientAppMngForm.getCustomerHeadKey());
		customer.setPassword(customer.getContactNum());

		clientAppMngDAO.updateUser(customer);

		String status = clientAppMngForm.getStatus();

		if (status != null && status.equalsIgnoreCase("Approved")) {
			EndUser endUser = new EndUser();

			endUser.setUserName(clientAppMngForm.getName());
			endUser.setCompanyId(company.getId());
			endUser.setPassword(clientAppMngForm.getContactNum());
			endUser.setContactNo(clientAppMngForm.getContactNum());
			endUser.setCurrentRole("ROLE_CUSTOMERAPPMNG");
			endUser.setRole(10);
			endUser.setPrefferedLanguage("en");
			endUser.setTheme("themeBlue");
			endUser.setEmail(clientAppMngForm.getEmail());
			endUser.setDisplayName(clientAppMngForm.getName());
			endUser.setStatus(clientAppMngForm.getStatus());
			endUser.setTransactionId(clientAppMngForm.getTransactionId());
			endUser.setPasswordFlag(0);
			endUser.setAccExpiryDate(clientAppMngForm.getAccExpiryDate());
			endUserDAOImpl.createUser(endUser);

		}

		Transaction trans = new Transaction();
		trans.setTransactionId(clientAdminForm.getTransactionId());
		trans.setTransactionStatus("Customer App Mng Approval");
		trans.setTransactionType("Approved Successfully");

		transactionDAO.insertTransaction(trans);
		String stat = clientAppMngForm.getStatus();
		String email = clientAppMngForm.getEmail();
		String username = clientAppMngForm.getName();
		String currentRole = "ROLE_CUSTOMERAPPMNG";
		String password = customer.getPassword();
		String bankName = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0).getBankName();
		try {
			if (stat.equals("Approved")) {
				String tex = " Customer Approval Managers Approved  Details Notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + username + ",\n\n Your official account has been Created. "
						+ ",\n\n Your Credentials Details are as follows. " + "\n" + "\n"

						+ "\n\nUserName:" + username + "\n\nPassword:" + password + "\n\nCurrent Role:" + currentRole +

						"\n\n\nRegards,\n"+bankName);
				System.out.println("" + email + username);
				mailSender.send(emails);
				SimpleMailMessage emails1 = new SimpleMailMessage();
				emails1.setTo(email);

			} else if (stat.equals("Rejected")) {
				String tex = " Customer Approval Managers Rejection notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + username + ",\n\n Your Account has been Rejected  "

						+ "\n" + "\n" + "\n\n\nRegards,\n"+bankName);
				mailSender.send(emails);

			}
			model.put("clientAppMngForm", clientAppMngForm);

		} catch (Exception e) {

			System.out.println("message" + e.getMessage());
		}
		return new ModelAndView("customerAppMngppTransaction", "model", model);

	}

	@RequestMapping(value = "/customerAppMngppTransaction", method = RequestMethod.GET)
	public ModelAndView customerAppMngppTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("clientAppMngForm", clientAppMngForm);

		return new ModelAndView("customerAppMngppTransaction", "model", model);

	}

	@RequestMapping(value = "/apprCustAppMngList", method = RequestMethod.GET)
	public ModelAndView apprCustAppMngList(ModelMap model) {

		List<ClientAppMng> customerList = clientAppMngDAO.getList().getResultList();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		if (customerList != null && customerList.size() > 0) {
			model.put("customerList", customerList);

			return new ModelAndView("apprCustAppMngList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/masterPlanRePaymentAppMngList", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentBank(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Repayment> repayList = repaymenyDAO.getRepayByAppMngStatus().getResultList();

		if (repayList != null && repayList.size() > 0) {
			model.put("repayList", repayList);
			return new ModelAndView("masterPlanRePaymentAppMngList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/masterPlanRePaymentAppMngAppr", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentAppMngLAppr(@RequestParam Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		Repayment master = repaymenyDAO.getByRepaymentId(id);

		repaymentForm.setId(master.getId());
		repaymentForm.setMasterKey(master.getMasterKey());
		repaymentForm.setCustomer(master.getCustomer());
		repaymentForm.setCustomerEmail(master.getCustomerEmail());
		repaymentForm.setTenure(master.getTenure());
		repaymentForm.setPayOption(master.getPayOption());
		repaymentForm.setAmtType(master.getAmtType());
		repaymentForm.setTransactionId(master.getTransactionId());

		model.put("repaymentForm", repaymentForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentAppMngAppr", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentAppMngApprConfirm", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentDisplayConfirm(ModelMap model, @ModelAttribute RepaymentForm repaymentForm,
														  BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		repaymentForm.setId(repaymentForm.getId());
		repaymentForm.setMasterKey(repaymentForm.getMasterKey());
		repaymentForm.setCustomer(repaymentForm.getCustomer());
		repaymentForm.setCustomerEmail(repaymentForm.getCustomerEmail());
		repaymentForm.setTenure(repaymentForm.getTenure());
		repaymentForm.setPayOption(repaymentForm.getPayOption());
		repaymentForm.setAmtType(repaymentForm.getAmtType());
		repaymentForm.setTransactionId(repaymentForm.getTransactionId());
		repaymentForm.setStatus(repaymentForm.getStatus());
		repaymentForm.setComment(repaymentForm.getComment());

		model.put("repaymentForm", repaymentForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentAppMngApprConfirm", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentAppMngApprSave", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentAppMngApprSave(ModelMap model, @ModelAttribute RepaymentForm repaymentForm,
														  BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		Repayment repay = repaymenyDAO.getByRepaymentId(repaymentForm.getId());
		repay.setStatus(repaymentForm.getStatus());
		repay.setComment(repaymentForm.getComment());
		repay.setAccept("Pending");
		if (repaymentForm.getStatus().equals("Rejected")) {
			repay.setbStatus("Pending");
		}
		repaymenyDAO.updateRepayment(repay);

		List<Quarterly> full = quarterlyDAO.getByTransIdList(repaymentForm.getTransactionId()).getResultList();
		if (full != null && full.size() > 0) {
			full.get(0).setStatus(repaymentForm.getStatus());
			quarterlyDAO.updateQuarterly(full.get(0));
		}
		List<FullAmount> full1 = fullAmountDAO.getByTransIdList(repaymentForm.getTransactionId()).getResultList();
		if (full1 != null && full1.size() > 0) {
			full1.get(0).setStatus(repaymentForm.getStatus());
			fullAmountDAO.updateFullAmount(full1.get(0));
		}
		List<HalfYearly> full2 = halfYearlyDAO.getByTransIdList(repaymentForm.getTransactionId()).getResultList();
		if (full2 != null && full2.size() > 0) {
			full2.get(0).setStatus(repaymentForm.getStatus());
			halfYearlyDAO.updateHalfYearly(full2.get(0));
		}

		Transaction trans = new Transaction();

		trans.setTransactionId(repaymentForm.getTransactionId());
		trans.setTransactionStatus("Repayment Approve/Reject");
		trans.setTransactionType("Sent Successfully");
		transactionDAO.insertTransaction(trans);

		model.put("repaymentForm", repaymentForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentAppMngApprTrans", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentAppMngApprTrans", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("repaymentForm", repaymentForm);

		return new ModelAndView("masterPlanRePaymentAppMngApprTrans", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentAppQuarterly", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentAppQuarterly(@RequestParam Long id, ModelMap model,
														RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Repayment master = repaymenyDAO.getByRepaymentId(id);
		if (master != null) {
			List<Quarterly> full = quarterlyDAO.getByTransIdList(master.getTransactionId()).getResultList();

			if (full != null && full.size() > 0) {
				model.put("full", full);
				model.put("user", user);
			} else {
				model.put("user", user);
				attributes.addFlashAttribute("success", "Check The Type of Payment Type");

				return new ModelAndView("redirect:masterPlanRePaymentAppMngList");

			}
		}
		return new ModelAndView("masterPlanRePaymentAppQuarterly", "model", model);
	}

	@RequestMapping(value = "/masterPlanRePaymentAppFullList", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentAppFullList(@RequestParam Long id, ModelMap model,
													   RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Repayment master = repaymenyDAO.getByRepaymentId(id);
		if (master != null) {
			List<FullAmount> full = fullAmountDAO.getByTransIdList(master.getTransactionId()).getResultList();
			if (full != null && full.size() > 0) {
				model.put("full", full);
				model.put("user", user);
			} else {
				model.put("user", user);
				attributes.addFlashAttribute("success", "Check The Type of Payment Type");

				return new ModelAndView("redirect:masterPlanRePaymentAppMngList");

			}
		}
		return new ModelAndView("masterPlanRePaymentAppFullList", "model", model);
	}

	@RequestMapping(value = "/masterPlanRePaymentAppHalfYearly", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentAppHalfYearly(@RequestParam Long id, ModelMap model,
														 RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Repayment master = repaymenyDAO.getByRepaymentId(id);
		if (master != null) {
			List<HalfYearly> full = halfYearlyDAO.getByTransIdList(master.getTransactionId()).getResultList();

			if (full != null && full.size() > 0) {
				model.put("full", full);
				model.put("user", user);
			} else {
				model.put("user", user);
				attributes.addFlashAttribute("success", "Check The Type of Payment Type");

				return new ModelAndView("redirect:masterPlanRePaymentAppMngList");

			}
		}
		return new ModelAndView("masterPlanRePaymentAppHalfYearly", "model", model);
	}

	@RequestMapping(value = "/masterPlanRePaymentAppMngFullList", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Repayment> repayList = repaymenyDAO.getRepayFullList().getResultList();

		if (repayList != null && repayList.size() > 0) {
			model.put("repayList", repayList);
			return new ModelAndView("masterPlanRePaymentAppMngFullList", "model", model);

		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/poPaymentAppMngList", method = RequestMethod.GET)
	public ModelAndView poPaymentBankList(ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> purchaseList = purchaseOrderDAO.getList().getResultList();
		if (purchaseList != null && purchaseList.size() > 0) {

			model.put("purchaseList", purchaseList);
			return new ModelAndView("poPaymentAppMngList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/poPaymentAppMngClear", method = RequestMethod.GET)
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
			model.put("user", user);
			attributes.addFlashAttribute("success", "Please Check Tpe Of Transfer ");
			return new ModelAndView("redirect:poPaymentAppMngList");
		}
		return new ModelAndView("poPaymentAppMngClear", "model", model);

	}

	@RequestMapping(value = "/appPODocumentList", method = RequestMethod.GET)
	public ModelAndView AppPODocumentList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> poList = purchaseOrderDAO.getList().getResultList();

		if (poList != null && poList.size() > 0) {
			purchaseOrderForm.setId(poList.get(0).getId());

			model.put("poList", poList);
			model.put("purchaseOrderForm", purchaseOrderForm);
			return new ModelAndView("appPODocumentList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/requestedInvoiceAppMng", method = RequestMethod.GET)
	public ModelAndView requestedInvoiceAppMng(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getInvoiceListForAppMng().getResultList();

		if (invoiceList != null && invoiceList.size() > 0) {

			model.put("invoiceList", invoiceList);
			return new ModelAndView("requestedInvoiceAppMng", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/requestedInvoiceAppMngStatus", method = RequestMethod.GET)
	public ModelAndView requestedInvoiceAppMngStatus(@RequestParam Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		Invoice invoice = invoiceDAO.getByInvoiceId(id);

		invoiceForm.setId(invoice.getId());
		invoiceForm.setCustomerName(invoice.getCustomerName());
		invoiceForm.setBuyerName(invoice.getBuyerName());
		invoiceForm.setPoKey(invoice.getPoKey());
		invoiceForm.setTenure(invoice.getTenure());
		invoiceForm.setAmount(invoice.getAmount());
		invoiceForm.setFunalAmt(invoice.getFunalAmt());
		invoiceForm.setTransactionId(invoice.getTransactionId());

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("requestedInvoiceAppMngStatus", "model", model);

	}

	@RequestMapping(value = "/requestedInvoiceAppMngStatusConfirm", method = RequestMethod.POST)
	public ModelAndView requestedInvoiceSetRatePost(ModelMap model, @ModelAttribute InvoiceForm invoiceForm,
													BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		invoiceForm.setId(invoiceForm.getId());
		invoiceForm.setCustomerName(invoiceForm.getCustomerName());
		invoiceForm.setBuyerName(invoiceForm.getBuyerName());
		invoiceForm.setPoKey(invoiceForm.getPoKey());
		invoiceForm.setTenure(invoiceForm.getTenure());
		invoiceForm.setAmount(invoiceForm.getAmount());
		invoiceForm.setFunalAmt(invoiceForm.getFunalAmt());
		invoiceForm.setTransactionId(invoiceForm.getTransactionId());
		invoiceForm.setTransStatus(invoiceForm.getTransStatus());
		invoiceForm.setTransComment(invoiceForm.getTransComment());

		model.put("invoiceForm", invoiceForm);
		model.put("user", user);

		return new ModelAndView("requestedInvoiceAppMngStatusConfirm", "model", model);

	}

	@RequestMapping(value = "/requestedInvoiceAppMngStatusPost", method = RequestMethod.POST)
	public ModelAndView requestedInvoiceAppMngStatusPost(ModelMap model, @ModelAttribute InvoiceForm invoiceForm,
														 BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		Invoice invoice = invoiceDAO.getByInvoiceId(invoiceForm.getId());

		invoiceForm.setTransactionId(invoiceForm.getTransactionId());
		invoice.setTransStatus(invoiceForm.getTransStatus());
		invoice.setTransComment(invoiceForm.getTransComment());
		if (invoiceForm.getTransStatus().equals("Approved")) {
			invoice.setRequestMoney("Approved");
		}
		invoiceDAO.updateInvoice(invoice);

		Transaction trans = new Transaction();

		trans.setTransactionId(purchaseOrderForm.getTransactionId());
		trans.setTransactionStatus("Set Rates Approval/Reject");
		trans.setTransactionType("Submitted Successfully");

		transactionDAO.insertTransaction(trans);

		model.put("invoiceForm", invoiceForm);
		model.put("user", user);

		return new ModelAndView("requestedInvoiceAppMngStatusTrans", "model", model);

	}

	@RequestMapping(value = "/requestedInvoiceAppMngStatusTrans", method = RequestMethod.GET)
	public ModelAndView requestedInvoiceAppMngStatusTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("requestedInvoiceAppMngStatusTrans", "model", model);

	}

	@RequestMapping(value = "/requestMoneyInvoiceAppMngFullList", method = RequestMethod.GET)
	public ModelAndView requestMoneyInvoiceAppMngFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getList().getResultList();

		if (invoiceList != null && invoiceList.size() > 0) {

			model.put("invoiceList", invoiceList);
			return new ModelAndView("requestMoneyInvoiceAppMngFullList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/generalLedgerAppMng", method = RequestMethod.GET)
	public ModelAndView generalLedgerBank(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<MasterPlan> masterList = masterPlanDAO.getAllMasterPlans().getResultList();
		if (masterList.size() == 0 && masterList.size() == 0) {
			return new ModelAndView("noDataFound1", "model", model);
		} else {
			model.put("masterList", masterList);

			return new ModelAndView("generalLedgerAppMng", "model", model);
		}

	}

	@RequestMapping(value = "/generalLedgerAppMngView", method = RequestMethod.GET)
	public ModelAndView generalLedgerBankView(@RequestParam Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		MasterPlan plan = masterPlanDAO.getByMasterPlanId(id);
		masterPlanForm.setMasterKey(plan.getMasterKey());
		masterPlanForm.setCustomer(plan.getCustomer());
		masterPlanForm.setStatus(plan.getStatus());
		masterPlanForm.setManagerStatus(plan.getManagerStatus());
		masterPlanForm.setCategory(plan.getCategory());
		masterPlanForm.setProduct(plan.getProduct());
		masterPlanForm.setLicence(plan.getLicence());
		masterPlanForm.setWeight(plan.getWeight());
		masterPlanForm.setQuantity(plan.getQuantity());
		masterPlanForm.setTenure(plan.getTenure());
		masterPlanForm.setRateOfInt(plan.getRateOfInt());
		masterPlanForm.setPlrRate(plan.getPlrRate());
		masterPlanForm.setRateOfInt1(plan.getRateOfInt1());
		masterPlanForm.setFlatCharges(plan.getFlatCharges());
		masterPlanForm.setPercentAmt(plan.getPercentAmt());
		masterPlanForm.setPercentage(plan.getPercentage());
		masterPlanForm.setProcFee(plan.getProcFee());
		masterPlanForm.setDocFee(plan.getDocFee());
		masterPlanForm.setLateFee(plan.getLateFee());
		masterPlanForm.setTaxPercentage(plan.getTaxPercentage());
		masterPlanForm.setFunalAmt(plan.getFunalAmt());
		masterPlanForm.setAmountPaid(plan.getAmountPaid());
		masterPlanForm.setBalance(plan.getBalance());

		List<PurchaseOrder> poList = purchaseOrderDAO.getPoByMasterKeyList(plan.getMasterKey()).getResultList();
		if (poList != null && poList.size() > 0) {
			Float total = 0.0f;
			for (PurchaseOrder po : poList) {
				total = total + po.getAmount();
			}
			masterPlanForm.setWcTotalAmount(total);
		}

		List<Invoice> inList = invoiceDAO.getInvoiceByMasterKeyList(plan.getMasterKey()).getResultList();
		if (inList != null && inList.size() > 0) {
			Float total1 = 0.0f;
			for (Invoice in : inList) {
				total1 = total1 + in.getAmount();
			}
			masterPlanForm.setCalPlrRate(total1);
		}

		model.put("masterPlanForm", masterPlanForm);
		return new ModelAndView("generalLedgerAppMngView", "model", model);
	}

	@RequestMapping(value = "/snapShotAppMng", method = RequestMethod.GET)
	public ModelAndView snapShotBank(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<MasterPlan> masterList = masterPlanDAO.getAllMasterPlans().getResultList();

		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);
			return new ModelAndView("snapShotAppMng", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/snapShotAppMngView", method = RequestMethod.GET)
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

			if (buyingList != null && buyingList.size() > 0) {
				model.put("buyingList", buyingList);
			}

			List<SellerBuyingCost> sellerList = sellerBuyingCostDAO.getSellerBuyingCostList(masterList.getMasterKey())
					.getResultList();
			if (sellerList != null && sellerList.size() > 0) {
				model.put("sellerList", sellerList);
			}

			List<WorkingCapital> wclist = workingCapitalDAO.getWCBYMAsterKey(masterList.getMasterKey()).getResultList();
			if (wclist != null && wclist.size() > 0) {
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

		return new ModelAndView("snapShotAppMngView", "model", model);

	}

	/**
	 * Method to display list of Pending requests for Block/Unblock
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getPedingBlockUnblockUsers", method = RequestMethod.GET)
	public ModelAndView getPedingBlockUnblockUsers(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<EndUser> userList = endUserDAOImpl.getUsersForBlockUnblockApproval();

		if (userList != null && userList.size() > 0) {
			model.put("userList", userList);
			return new ModelAndView("getPedingBlockUnblockUsers", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/reportsListAppMng", method = RequestMethod.GET)
	public ModelAndView reportsListBank(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("purchaseOrderForm", purchaseOrderForm);
		model.put("user", user);

		return new ModelAndView("reportsListAppMng", "model", model);

	}

	@RequestMapping(value = "/reportsPOAppMngPost", method = RequestMethod.POST)
	public ModelAndView reportsPOBankPost(@ModelAttribute PurchaseOrderForm purchaseOrderForm, ModelMap model,
										  RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> purchaseList = purchaseOrderDAO.getPOByCategoryAndDate(purchaseOrderForm.getGoodsCategory(),
				purchaseOrderForm.getFromDate(), purchaseOrderForm.getToDate());
		/*
		 * if (purchaseList != null && purchaseList.size() > 0) {
		 * purchaseOrderForm.setPurchaseList(purchaseList);
		 * model.put("purchaseOrderForm", purchaseOrderForm); return new
		 * ModelAndView("reportsListAppMngView", "model", model); } else { return new
		 * ModelAndView("noDataFound1", "model", model); }
		 */

		if (purchaseList != null && purchaseList.size() > 0) {
			purchaseOrderForm.setPurchaseList(purchaseList);

			int range = DateService.getDaysBetweenTwoDates(purchaseOrderForm.getFromDate(),
					purchaseOrderForm.getToDate());
			double mean = Math.round(range / purchaseList.size());
			String mode = purchaseOrderDAO.getModeByQuantity(purchaseOrderForm.getGoodsCategory(),
					purchaseOrderForm.getFromDate(), purchaseOrderForm.getToDate());
			double median = 0.0;
			if (purchaseList.size() % 2 == 0) {
				median = Math.round((Double.parseDouble(purchaseList.get(purchaseList.size() / 2).getQuantity())
						+ Double.parseDouble(purchaseList.get(purchaseList.size() / 2 - 1).getQuantity())) / 2);
			} else {
				median = Math.round(Double.parseDouble(purchaseList.get(purchaseList.size() / 2).getQuantity()));
			}

			double variance = Math.round(((range - mean) * (range - mean)) / purchaseList.size());

			double standardDeviation = Math.round(Math.sqrt(variance));

			model.put("range", range);
			model.put("mean", mean);
			model.put("mode", mode);
			model.put("median", median);
			model.put("variance", variance);
			model.put("standardDeviation", standardDeviation);

			model.put("purchaseOrderForm", purchaseOrderForm);
		}

		model.put("user", user);

		return new ModelAndView("reportsListAppMngView", "model", model);

	}

	@RequestMapping(value = "/reportsListAppMngView", method = RequestMethod.GET)
	public ModelAndView reportsListBankView(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("purchaseOrderForm", purchaseOrderForm);
		model.put("user", user);

		return new ModelAndView("reportsListAppMngView", "model", model);

	}

	@RequestMapping(value = "/reportsListAppMngBying", method = RequestMethod.GET)
	public ModelAndView reportsListBankBying(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("invoiceForm", invoiceForm);
		model.put("user", user);

		return new ModelAndView("reportsListAppMngBying", "model", model);

	}

	@RequestMapping(value = "/reportsAppMngBuyingPost", method = RequestMethod.POST)
	public ModelAndView reportsPOBankPost(@ModelAttribute InvoiceForm invoiceForm, ModelMap model,
										  RedirectAttributes attributes) {

		List<Invoice> invoiceList = invoiceDAO.getInvoiceByCategoryAndDate(invoiceForm.getGoodsCategory(),
				invoiceForm.getFromDate(), invoiceForm.getToDate());

		if (invoiceList != null && invoiceList.size() > 0) {
			invoiceForm.setInvoiceList(invoiceList);

			int range = DateService.getDaysBetweenTwoDates(invoiceForm.getFromDate(), invoiceForm.getToDate());
			double mean = Math.round(range / invoiceList.size());
			String mode = invoiceDAO.getModeByQuantity(invoiceForm.getGoodsCategory(), invoiceForm.getFromDate(),
					invoiceForm.getToDate());
			double median = 0.0;
			if (invoiceList.size() % 2 == 0) {
				median = Math.round((Double.parseDouble(invoiceList.get(invoiceList.size() / 2).getQuantity())
						+ Double.parseDouble(invoiceList.get(invoiceList.size() / 2 - 1).getQuantity())) / 2);
			} else {
				median = Math.round(Double.parseDouble(invoiceList.get(invoiceList.size() / 2).getQuantity()));
			}

			double variance = Math.round(((range - mean) * (range - mean)) / invoiceList.size());

			double standardDeviation = Math.round(Math.sqrt(variance));

			model.put("range", range);
			model.put("mean", mean);
			model.put("mode", mode);
			model.put("median", median);
			model.put("variance", variance);
			model.put("standardDeviation", standardDeviation);

			model.put("invoiceForm", invoiceForm);

		}

		return new ModelAndView("reportsListAppMngBuyingView", "model", model);

	}

	@RequestMapping(value = "/reportsListAppMngBuyingView", method = RequestMethod.GET)
	public ModelAndView reportsListBankBuyingView(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("invoiceForm", invoiceForm);
		model.put("user", user);

		return new ModelAndView("reportsListAppMngBuyingView", "model", model);

	}

	@RequestMapping(value = "/disputeAppMngList", method = RequestMethod.GET)
	public ModelAndView disputeBankList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<Dispute> disputeList = disputeDAO.getList().getResultList();

		model.put("disputeList", disputeList);
		if (disputeList != null && disputeList.size() > 0) {
			model.put("user", user);

			return new ModelAndView("disputeAppMngList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	/**
	 * Method to Block/Renew Account
	 *
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getBlockRenewApproval", method = RequestMethod.GET)
	public ModelAndView getBlockRenewApproval(@ModelAttribute EndUserForm endUserForm, ModelMap model,
											  @RequestParam Long id) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser endUser = endUserDAOImpl.findId(id);

		model.put("endUser", endUser);
		model.put("user", user);

		return new ModelAndView("getBlockRenewApproval", "model", model);

	}

	/**
	 * Method to update EndUser with Block/Renew status
	 *
	 * @param endUserForm
	 * @param model
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/updateBlockRenewAccount", method = RequestMethod.POST)
	public ModelAndView updateBlockRenewAccount(@ModelAttribute EndUserForm endUserForm, ModelMap model,
												RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		EndUser endUser = endUserDAOImpl.findId(endUserForm.getId());
		if (!endUserForm.getAccessStatus().equals("")) {
			if (endUserForm.getStatus().equals(Constants.APPROVED)) {
				if (endUser.getAccessStatus().equals(Constants.BLOCK)) {
					endUser.setAccessStatus(Constants.BLOCKED);
				} else {
					endUser.setAccessStatus(Constants.UNBLOCKED);
				}
			} else {
				endUser.setAccessStatus(Constants.REJECTED);
			}
		} else {
			if (endUserForm.getStatus().equals(Constants.APPROVED)) {
				endUser.setAccRenewStatus(Constants.RENEWED);
			} else {
				endUser.setAccRenewStatus(endUserForm.getStatus());
			}

		}
		endUserForm.setTransactionId(endUser.getTransactionId());
		endUserDAOImpl.update(endUser);

		Transaction trans = new Transaction();

		trans.setTransactionId(endUserForm.getTransactionId());
		trans.setTransactionStatus("Account Renew/Block");
		trans.setTransactionType("Account update");

		attributes.addFlashAttribute("success", "Updated successfully");
		model.put("endUserForm", endUserForm);
		return new ModelAndView("updateBlockRenewTrans", "model", model);
	}

	@RequestMapping(value = "/updateBlockRenewTrans", method = RequestMethod.GET)
	public ModelAndView clientAppMngTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("endUserForm", endUserForm);

		return new ModelAndView("updateBlockRenewTrans", "model", model);

	}

	/**
	 * Method to display Pending Swap Accounts for approval
	 */
	@RequestMapping(value = "/swapAccountForApproval", method = RequestMethod.GET)
	public ModelAndView swapAccountForApproval(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		List<SwapAccount> swapAccountList = swapAccountDAO.getAllSwapAccountsByStatus(Constants.PENDING);

		model.put("swapAccountList", swapAccountList);
		return new ModelAndView("swapAccountForApproval", "model", model);
	}

	/**
	 * Method to display approve/reject screen for Swap/Revert Account
	 */
	@RequestMapping(value = "/approveRejectSwapping", method = RequestMethod.GET)
	public ModelAndView approveRejectSwapping(@RequestParam Long id, ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		SwapAccount swapAccount = swapAccountDAO.findById(id);
		swapAccountForm.setOldUser(swapAccount.getOldUser());
		swapAccountForm.setOldEmail(swapAccount.getOldEmail());
		swapAccountForm.setNewUser(swapAccount.getNewUser());
		swapAccountForm.setNewEmail(swapAccount.getNewEmail());
		swapAccountForm.setId(swapAccount.getId());

		model.put("swapAccountForm", swapAccountForm);

		return new ModelAndView("approveRejectSwapping", "model", model);
	}

	/**
	 * Method to update status of Swap/Revert Account
	 */
	@RequestMapping(value = "/updateSwapAccount", method = RequestMethod.POST)
	public String updateSwapAccount(ModelMap model, @ModelAttribute SwapAccountForm swapAccountForm,
									RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		SwapAccount swapAccount = swapAccountDAO.findById(swapAccountForm.getId());

		swapAccount.setStatus(swapAccountForm.getStatus());

		swapAccountDAO.updateSwapAccount(swapAccount);
		if (swapAccountForm.getStatus().equals(Constants.APPROVED)) {
			EndUser endUser = endUserDAOImpl.findByUsername(swapAccount.getOldUser()).getSingleResult();

			endUser.setUserName(swapAccount.getNewUser());
			endUser.setPassword(swapAccount.getNewContactNo());
			endUser.setEmail(swapAccount.getNewEmail());
			endUser.setContactNo(swapAccount.getNewContactNo());
			endUser.setPasswordFlag(0);

			endUserDAOImpl.update(endUser);
		}

		attributes.addFlashAttribute("success", "Updated Successsfully");

		return "redirect:swapAccountForApproval";
	}

	@RequestMapping(value = "/poDocumnetListApproval", method = RequestMethod.GET)
	public ModelAndView PoDocumnetList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		Collection<PoUpload> docList = poUploadDAO.getList();

		model.put("user", user);
		if (docList != null && docList.size() > 0) {
			model.put("docList", docList);

			return new ModelAndView("poDocumnetListApproval", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/invoiceDocumnetListApproval", method = RequestMethod.GET)
	public ModelAndView InvoiceDocumnetList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		Collection<InvoiceUpload> docList = invoiceUploadDAO.getList();

		model.put("user", user);
		if (docList != null && docList.size() > 0) {
			model.put("docList", docList);

			return new ModelAndView("invoiceDocumnetListApproval", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	/**
	 * Method to generate Master Plan Chart
	 */
	@RequestMapping(value = "/generateMasterPlanChart", method = RequestMethod.GET)
	public ModelAndView generateMasterPlanChart(@RequestParam(required = false) Long id, ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		List<MasterPlan> masterPlanList = masterPlanDAO.getApprovedMasterKey().getResultList();

		Map<MasterPlan, TreeChartForm> planChart = new LinkedHashMap<>();
		Map<PurchaseOrder, TreeChartForm> limitBurstNDocumentList = new LinkedHashMap<>();
		TreeChartForm form = new TreeChartForm();
		if (id != null) {
			MasterPlan plan = masterPlanDAO.getByMasterPlanId(id);
			List<PurchaseOrder> poList = purchaseOrderDAO.getPoByMasterKeyList(plan.getMasterKey()).getResultList();
			List<Invoice> invoiceList = invoiceDAO.getInvoiceByMasterKeyList(plan.getMasterKey()).getResultList();
			if (poList != null && poList.size() > 0) {
				for (PurchaseOrder po : poList) {
					List<LimitBurst> limitBurstList = limitBurstDAO.getLimitBurstByPOKey(po.getPoKey());
					List<PoUpload> documentList = poUploadDAO.getPoUploadByPOKey(po.getPoKey());

					TreeChartForm treeChartForm = new TreeChartForm();
					treeChartForm.setLimitBurstList(limitBurstList);
					treeChartForm.setPoUploadList(documentList);

					limitBurstNDocumentList.put(po, treeChartForm);
				}
				form.setPoList(limitBurstNDocumentList);
			}

			if (invoiceList != null && invoiceList.size() > 0) {
				form.setInvoiceList(invoiceList);
			}

			planChart.put(plan, form);
		}

		model.put("user", user);
		model.put("masterPlanList", masterPlanList);
		model.put("planChart", planChart);
		return new ModelAndView("generateMasterPlanChartAppMng", "model", model);
	}

	@RequestMapping(value = "/appMngHelp", method = RequestMethod.GET)
	public ModelAndView adminHelp(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("appMngHelp", "model", model);

	}

	@RequestMapping(value = "/appMngExistingStockInvoiceList", method = RequestMethod.GET)
	public ModelAndView existingStockHeadInvoiceFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<InvoiceStock> poList = invoiceStockDAO.getList().getResultList();

		model.put("user", user);
		if (poList != null && poList.size() > 0) {
			model.put("poList", poList);

			return new ModelAndView("appMngExistingStockInvoiceList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/appMngExistingStockPoList", method = RequestMethod.GET)
	public ModelAndView bankExistingStockPoList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PoStock> poList = poStockDAO.getList().getResultList();

		model.put("user", user);
		if (poList != null && poList.size() > 0) {
			model.put("poList", poList);

			return new ModelAndView("appMngExistingStockPoList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/poInsFullAppList", method = RequestMethod.GET)
	public ModelAndView poInsFullList(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);

		List<DisputeReports> dispute = disputeReportsDAO.getList().getResultList();

		if (dispute != null && dispute.size() > 0) {
			model.put("dispute", dispute);
			return new ModelAndView("poInsFullAppList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/addOrModifyReportAppView", method = RequestMethod.GET)
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

		return new ModelAndView("addOrModifyReportAppView", "model", model);
	}

	@RequestMapping(value = "/addOrModifyReportAppCompare", method = RequestMethod.GET)
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

		return new ModelAndView("addOrModifyReportAppCompare", "model", model);
	}

	@RequestMapping(value = "/addOrModifyReportInvoiceAppFullList", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportVendorFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<InvoiceReports> disputeList = invoiceReportsDAO.getList().getResultList();

		if (disputeList != null && disputeList.size() > 0) {
			model.put("disputeList", disputeList);
			return new ModelAndView("addOrModifyReportInvoiceAppFullList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/addOrModifyReportInvoiceAppView", method = RequestMethod.GET)
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

		return new ModelAndView("addOrModifyReportInvoiceAppView", "model", model);
	}

	@RequestMapping(value = "/addOrModifyReportInvoiceAppCompare", method = RequestMethod.GET)
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

		return new ModelAndView("addOrModifyReportInvoiceAppCompare", "model", model);
	}

	@RequestMapping(value = "/licenseOnRestrictedApp", method = RequestMethod.GET)
	public ModelAndView licenseOnRestricted(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<RestrictedLicense> license = restrictedLicenseDAO.getInsByStatus().getResultList();

		model.put("user", user);
		model.put("license", license);

		return new ModelAndView("licenseOnRestrictedApp", "model", model);

	}

	@RequestMapping(value = "/licenseOnRestrictedAppStatus", method = RequestMethod.GET)
	public ModelAndView licenseOnRestrictedAppStatus(@RequestParam Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		RestrictedLicense license = restrictedLicenseDAO.getByRestrictedLicenseId(id);

		restrictedLicenseForm.setId(license.getId());
		restrictedLicenseForm.setCustomer(license.getCustomer());
		restrictedLicenseForm.setLicenseDetails(license.getLicenseDetails());
		restrictedLicenseForm.setStartDate(license.getStartDate());
		restrictedLicenseForm.setEndDate(license.getEndDate());
		restrictedLicenseForm.setGoodsName(license.getGoodsName());
		restrictedLicenseForm.setQty(license.getQty());
		restrictedLicenseForm.setTransId(license.getTransId());

		model.put("user", user);
		model.put("restrictedLicenseForm", restrictedLicenseForm);

		return new ModelAndView("licenseOnRestrictedAppStatus", "model", model);

	}

	@RequestMapping(value = "/licenseOnRestrictedAppStatusConfirm", method = RequestMethod.POST)
	public ModelAndView licenseOnRestrictedAppStatusConfirm(ModelMap model,
															@ModelAttribute RestrictedLicenseForm restrictedLicenseForm, BindingResult result,
															RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		restrictedLicenseForm.setId(restrictedLicenseForm.getId());
		restrictedLicenseForm.setCustomer(restrictedLicenseForm.getCustomer());
		restrictedLicenseForm.setLicenseDetails(restrictedLicenseForm.getLicenseDetails());
		restrictedLicenseForm.setStartDate(restrictedLicenseForm.getStartDate());
		restrictedLicenseForm.setEndDate(restrictedLicenseForm.getEndDate());
		restrictedLicenseForm.setGoodsName(restrictedLicenseForm.getGoodsName());
		restrictedLicenseForm.setTransId(restrictedLicenseForm.getTransId());
		restrictedLicenseForm.setStatus(restrictedLicenseForm.getStatus());
		restrictedLicenseForm.setComment(restrictedLicenseForm.getComment());

		model.put("user", user);
		model.put("restrictedLicenseForm", restrictedLicenseForm);

		return new ModelAndView("licenseOnRestrictedAppStatusConfirm", "model", model);
	}

	@RequestMapping(value = "/licenseOnRestrictedAppStatusPost", method = RequestMethod.POST)
	public ModelAndView licenseOnRestrictedAppStatusPost(ModelMap model,
														 @ModelAttribute RestrictedLicenseForm restrictedLicenseForm, BindingResult result,
														 RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		RestrictedLicense license = restrictedLicenseDAO.getByRestrictedLicenseId(restrictedLicenseForm.getId());

		license.setStatus(restrictedLicenseForm.getStatus());
		license.setComment(restrictedLicenseForm.getComment());

		restrictedLicenseDAO.updateRestrictedLicense(license);

		model.put("user", user);
		model.put("restrictedLicenseForm", restrictedLicenseForm);

		return new ModelAndView("licenseOnRestrictedAppStatusTrans", "model", model);
	}

	@RequestMapping(value = "/licenseOnRestrictedAppStatusTrans", method = RequestMethod.GET)
	public ModelAndView licenseOnRestrictedTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("restrictedLicenseForm", restrictedLicenseForm);

		return new ModelAndView("licenseOnRestrictedAppStatusTrans", "model", model);

	}

	@RequestMapping(value = "/licenseOnRestrictedAppMngList", method = RequestMethod.GET)
	public ModelAndView licenseOnRestrictedHeadList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<RestrictedLicense> license = restrictedLicenseDAO.getList().getResultList();

		model.put("user", user);
		model.put("license", license);

		return new ModelAndView("licenseOnRestrictedAppMngList", "model", model);

	}

	/**
	 * Pie chart view for General ledger
	 *
	 * @param id
	 * @param model
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/generalLedgerGraphicalView", method = RequestMethod.GET)
	public ModelAndView generalLedgerGraphicalView(@RequestParam Long id, ModelMap model,
												   RedirectAttributes attributes) {

		MasterPlan plan = masterPlanDAO.getByMasterPlanId(id);

		model.put("plan", plan);

		return new ModelAndView("generalLedgerGraphicalViewMng", "model", model);
	}

	@RequestMapping(value = "/whMngBankApprovalList", method = RequestMethod.GET)
	public ModelAndView whMngBankApprovalList(ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<WareHouseMng> customerList = wareHouseMngDAO.getBankAppByIdAndStatus().getResultList();

		if (customerList != null && customerList.size() > 0) {

			model.put("customerList", customerList);
			return new ModelAndView("whMngBankApprovalList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/approveWhMngBank", method = RequestMethod.GET)
	public ModelAndView approveWhMngBank(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
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
		return new ModelAndView("approveWhMngBank", "model", model);
	}

	@RequestMapping(value = "/approveWhMngBankConfirm", method = RequestMethod.POST)
	public ModelAndView approveWhMngBankConfirm(@ModelAttribute WareHouseMngForm wareHouseMngForm, ModelMap model,
												RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		wareHouseMngForm.setId(wareHouseMngForm.getId());
		wareHouseMngForm.setMngName(wareHouseMngForm.getMngName());
		wareHouseMngForm.setCustomerName(wareHouseMngForm.getCustomerName());
		wareHouseMngForm.setCustomerPrefix(wareHouseMngForm.getCustomerPrefix());
		wareHouseMngForm.setAddress(wareHouseMngForm.getAddress());
		wareHouseMngForm.setbStatus(wareHouseMngForm.getbStatus());
		wareHouseMngForm.setbComment(wareHouseMngForm.getbComment());
		wareHouseMngForm.setContactNum(wareHouseMngForm.getContactNum());
		wareHouseMngForm.setEmail(wareHouseMngForm.getEmail());
		wareHouseMngForm.setAccExpiryDate(wareHouseMngForm.getAccExpiryDate());
		wareHouseMngForm.setTransactionId(wareHouseMngForm.getTransactionId());

		model.put("wareHouseMngForm", wareHouseMngForm);

		return new ModelAndView("approveWhMngBankConfirm", "model", model);
	}

	@RequestMapping(value = "/approveWhMngBankSave", method = RequestMethod.POST)
	public ModelAndView approveWhMngBankSave(@ModelAttribute WareHouseMngForm wareHouseMngForm, ModelMap model,
											 RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		WareHouseMng customer = wareHouseMngDAO.getByWareHouseMngId(wareHouseMngForm.getId());

		customer.setMngName(wareHouseMngForm.getMngName());
		customer.setbStatus(wareHouseMngForm.getbStatus());
		customer.setbComment(wareHouseMngForm.getbComment());
		Date date = DateService.loginDate;
		customer.setApproveeDate(date);
		customer.setPassword(wareHouseMngForm.getContactNum());

		wareHouseMngDAO.updateUser(customer);

		String status = wareHouseMngForm.getbStatus();

		if (status != null && status.equalsIgnoreCase("Approved")) {
			EndUser endUser = new EndUser();

			endUser.setUserName(wareHouseMngForm.getMngName());
			endUser.setPassword(wareHouseMngForm.getContactNum());
			endUser.setContactNo(wareHouseMngForm.getContactNum());
			endUser.setCurrentRole("ROLE_WAREHOUSEMNG");
			endUser.setRole(11);
			endUser.setPrefferedLanguage("en");
			endUser.setTheme("themeBlue");
			endUser.setEmail(wareHouseMngForm.getEmail());
			endUser.setDisplayName(wareHouseMngForm.getMngName());
			endUser.setStatus(wareHouseMngForm.getbStatus());
			endUser.setTransactionId(wareHouseMngForm.getTransactionId());
			endUser.setPasswordFlag(0);
			endUser.setAccExpiryDate(wareHouseMngForm.getAccExpiryDate());
			endUserDAOImpl.createUser(endUser);

		}

		Transaction trans = new Transaction();
		trans.setTransactionId(wareHouseMngForm.getTransactionId());
		trans.setTransactionStatus("WareHouse Manager Approval");
		trans.setTransactionType("Approved Successfully");

		transactionDAO.insertTransaction(trans);
		model.put("wareHouseMngForm", wareHouseMngForm);

		try {
			String stat = wareHouseMngForm.getbStatus();
			String email = wareHouseMngForm.getEmail();
			String username = wareHouseMngForm.getMngName();
			String currentRole = "ROLE_WAREHOUSEMNG";
			String password = customer.getPassword();
			if (stat.equals("Approved")) {
				String tex = "WareHouse Manager Details Notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + username
						+ ",\n\n An official WareHouse Manager account has been created for you. " + "\n" + "\n"
						+ "\n\n Your Login Credentials are:" + "\n\nUser Name:" + username + "\n\nPassword: " + password
						+ "\n\nCurrent Role: " + currentRole + "\n\n\nRegards,\nBank");
				System.out.println("" + email + username);
				mailSender.send(emails);
				SimpleMailMessage emails1 = new SimpleMailMessage();
				emails1.setTo(email);
				emails1.setSubject(tex);
				emails1.setText(
						"Hello " + username + "\n\n An official WareHouse Manager account has been created for you..\n"
								+ "\n\nRegards,\nBank");

			} else if (stat.equals("Rejected")) {
				String tex = "WareHouse Manager Details Notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + username + ",\n\n\n Your WareHouse Manager Account has been Rejected "

						+ "\n\n\nRegards,\nBank");
				mailSender.send(emails);
			} else {
				String tex = "WareHouse Manager Details Notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + username
						+ ",\n\n\n  Your WareHouse Manager Account approval is pending.\n\n\nRegards,\nBank");
				mailSender.send(emails);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage() + e);
		}
		return new ModelAndView("whMngBankAppTransaction", "model", model);
	}

	@RequestMapping(value = "/whMngBankAppTransaction", method = RequestMethod.GET)
	public ModelAndView whMngBankAppTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("wareHouseMngForm", wareHouseMngForm);

		return new ModelAndView("whMngBankAppTransaction", "model", model);

	}

	@RequestMapping(value = "/wareHouseMngBankAppFullList", method = RequestMethod.GET)
	public ModelAndView wareHouseMngList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouseMng> mngList = wareHouseMngDAO.getList().getResultList();

		model.put("user", user);
		model.put("mngList", mngList);

		return new ModelAndView("wareHouseMngBankAppFullList", "model", model);

	}
}
