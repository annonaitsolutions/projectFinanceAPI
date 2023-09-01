package annona.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annona.dao.*;
import annona.domain.*;
import annona.form.*;
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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.theme.CookieThemeResolver;

import com.google.gson.Gson;

import annona.services.ColumnGraphService;
import annona.services.DateService;
import annona.services.EventsService;
import annona.services.ImageService;
import annona.services.ListService;
import annona.services.UploadService;
import annona.trade.dao.TradeNotificationDAO;
import annona.trade.domain.TradeNotification;
import annona.utility.Constants;
import annona.utility.KeyGenerator;

@Controller
@RequestMapping("/bnkEmp")
public class BankEmpController implements ServletContextAware {

	@Autowired
	EndUserDAO endUserDAOImpl;

	@Autowired
	CustomerHeadForm customerHeadForm;

	@Autowired
	PoUploadDAO poUploadDAO;

	@Autowired
	WareHouseDAO wareHouseDAO;

	@Autowired
	CollateralDAO collateralDAO;

	@Autowired
	InvoiceUploadDAO invoiceUploadDAO;

	@Autowired
	VendorUploadDAO vendorUploadDAO;

	@Autowired
	UploadDAO uploadDAO;

	@Autowired
	CorrespondentDAO correspondentDAO;

	@Autowired
	BuyingCostDAO buyingCostDAO;

	@Autowired
	SellerBuyingCostDAO SellerBuyingCostDAO;

	@Autowired
	WorkingCapitalDAO workingCapitalDAO;

	@Autowired
	RepaymentForm repaymentForm;

	@Autowired
	SellerBuyingCostDAO sellerBuyingCostDAO;

	@Autowired
	HalfYearlyForm halfYearlyForm;

	@Autowired
	HalfYearlyDAO halfYearlyDAO;

	@Autowired
	LetterOfCreditForm letterOfCreditForm;

	@Autowired
	RepaymentDAO repaymenyDAO;

	@Autowired
	InvoiceInventoryDAO invoiceInventoryDAO;

	@Autowired
	MasterPlanDAO masterPlanDAO;

	@Autowired
	MasterPlanForm masterPlanForm;

	@Autowired
	LetterOfCreditDAO letterOfCreditDAO;

	@Autowired
	NewBuyerDAO newBuyerDAOImpl;

	@Autowired
	NewBuyerForm newBuyerForm;

	@Autowired
	InvoiceForm invoiceForm;

	@Autowired
	EndUserForm endUserForm;

	@Autowired
	NewEventForm newEventForm;

	@Autowired
	NotificationDAO notificationDAO;

	@Autowired
	ClientAdminDAO clientAdminDAO;

	@Autowired
	ClientAdminForm clientAdminForm;

	@Autowired
	CompanyForm companyForm;

	@Autowired
	InventoryDAO invenrotyDAO;

	@Autowired
	CustomerBranchForm customerBranchForm;

	@Autowired
	CustomerSubsidiaryForm customerSubsidiaryForm;

	@Autowired
	PurchaseOrderForm purchaseOrderForm;

	@Autowired
	CustomerDAO customerDAO;

	@Autowired
	PurchaseOrderDAO purchaseOrderDAO;

	@Autowired
	FullAmountDAO fullAmountDAO;

	@Autowired
	FullAmountForm fullAmountForm;

	@Autowired
	InvoiceDAO invoiceDAO;

	@Autowired
	TransactionDAO transactionDAO;

	@Autowired
	BuyerSameBankEventForm buyerSameBankEventForm;

	@Autowired
	BuyerDiffBankEventForm buyerDiffBankEventForm;

	@Autowired
	SellerSameBankEventForm sellerSameBankEventForm;

	@Autowired
	BuyerSameBankEventDAO buyerSameBankeventDAO;

	@Autowired
	BuyerDiffBankEventDAO buyerDiffBankEventDAO;

	@Autowired
	SellerSameBankEventDAO sellerSameBankEventDAO;

	@Autowired
	SellerDiffBankEventForm sellerDiffBankEventForm;

	@Autowired
	SellerDiffBankEventDAO sellerDiffBankEventDAO;

	@Autowired
	SupplierDAO supplierService;

	@Autowired
	NewBuyerDAO buyerDAO;

	@Autowired
	SupplierForm supplierForm;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private JavaMailSender mailSender2;

	@Autowired
	UploadedFileForm uploadedFileForm;

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
	BankDetailsDAO bankDetailsDAO;

	@Autowired
	DisputeDAO disputeDAO;

	@Autowired
	UploadService uploadService;

	@Autowired
	BuyerPODAO buyerPODAO;

	@Autowired
	PurchaseDocDAO purchaseDocDAO;

	@Autowired
	InvoiceDocDAO invoiceDocDAO;

	@Autowired
	InvoiceStockDAO invoiceStockDAO;

	@Autowired
	PoStockDAO poStockDAO;

	@Autowired
	TransactionDAO transcationDAOImpl;

	@Autowired
	TradeNotificationDAO tradeNotificationDAO;

	@Autowired
	DisputeReportsDAO disputeReportsDAO;

	@Autowired
	DisputeReportsForm disputeReportsForm;

	@Autowired
	InvoiceReportsDAO invoiceReportsDAO;

	@Autowired
	EventsService eventsService;

	@Autowired
	ConsortiumDAO consortiumDAO;

	@Autowired
	ConsortiumForm consortiumForm;

	@Autowired
	WareHouseMngDAO wareHouseMngDAO;

	@Autowired
	CompanyDAO companyDAO;

	@Autowired
	CustomerBankDetailsDAO customerBankDetailsDAO;

	@Autowired
	ClientAppMngDAO clientAppMngDAO;

	private ServletContext servletContext;

	CookieThemeResolver themeResolver = new CookieThemeResolver();

	protected Logger log = Logger.getLogger(BankEmpController.class.getName());

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

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public JavaMailSender getMailSender2() {
		return mailSender2;
	}

	public void setMailSender2(JavaMailSender mailSender2) {
		this.mailSender2 = mailSender2;
	}

	private String getCurrentLoggedUserName() {

		return SecurityContextHolder.getContext().getAuthentication().getName();

	}

	private EndUser getCurrentLoggedUserDetails() {

		EndUser endUser = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		return endUser;

	}

	/* Bank employee home page display */

	@RequestMapping(value = "/bankEmpCommon", method = RequestMethod.GET)
	public ModelAndView showBankEmpCommonDashBoard(ModelMap model, HttpServletRequest request,
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

		model.put("not", not);

		model.put("notificationList", notificationList);
		return new ModelAndView("bankEmpCommonPage", "model", model);

	}

	@RequestMapping(value = "/bankEmp", method = RequestMethod.GET)
	public ModelAndView showBankEmpDashBoard(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

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

		return new ModelAndView("bankEmpPage", "model", model);

	}

	@RequestMapping(value = "/editBankProfile", method = RequestMethod.GET)
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

		return new ModelAndView("editBankProfile", "model", model);

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
		return "redirect:editBankProfile?id=" + endUserForm.getId();
	}

	@RequestMapping(value = "/confirmEditBankProfile", method = RequestMethod.POST)
	public ModelAndView confirmEditAdminProfile(ModelMap model, @ModelAttribute EndUserForm endUserForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("confirmEditBankProfile", "model", model);

	}

	@RequestMapping(value = "/updateBankDetails", method = RequestMethod.POST)
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

		return new ModelAndView("updateBankSuccess", "model", model);

	}

	@RequestMapping(value = "/editBankPWD", method = RequestMethod.GET)
	public ModelAndView editAdminPWD(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser userProfile = endUserDAOImpl.findId(id);

		endUserForm.setId(userProfile.getId());

		endUserForm.setTransactionId(userProfile.getTransactionId());

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("editBankPWD", "model", model);

	}

	@RequestMapping(value = "/updateEditBankPWD", method = RequestMethod.POST)
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

		return new ModelAndView("updateBankSuccess", "model", model);

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

		return "redirect:bankEmp";
	}

	@RequestMapping(value = "/getLocaleLang", method = RequestMethod.GET)
	public String getLocaleLang(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		log.info("Received request for locale change");
		EndUser enduser = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		LocaleResolver localeResolver = new CookieLocaleResolver();
		Locale locale = new Locale(request.getParameter("lang"));
		localeResolver.setLocale(request, response, locale);
		enduser.setPrefferedLanguage(request.getParameter("lang"));

		endUserDAOImpl.mergeUser(enduser);

		return "redirect:bankEmp";
	}

	@RequestMapping(value = "/createClientAdmin", method = RequestMethod.GET)
	public ModelAndView createClientAdmin(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		// List<ClientAdminForm> customerList1 =
		// clientAdminDAO.getCustByIdAndStatusComp(user.getId()).getResultList();

		List<ClientAdmin> customerList = clientAdminDAO.getClientAdminByStatusAndTradeFlag(Boolean.FALSE)
				.getResultList();

		List<ClientAdmin> adm = new ArrayList<ClientAdmin>();

		List<Company> companyList = companyDAO.getCompanyByStatusAndIsForTrading("Approved", Boolean.FALSE);

		for (int a = 0; a < customerList.size(); a++) {

			ClientAdmin customer = customerList.get(a);

			for (int i = 0; i < companyList.size(); i++) {

				Company comp = companyList.get(i);

				if (customer.getCompanyId() == comp.getId() && customer.getCompanyId() != null) {

					customer.setAddress(comp.getAddress());
					customer.setCompanyName(comp.getCompanyName());
				}

			}
			adm.add(customer);
		}

		if (adm != null && adm.size() > 0) {

			model.put("adminList", adm);

		}

		if (companyList != null && companyList.size() > 0) {

			model.addAttribute("companyList", companyList);
			// model.put("companyList", companyList);

		}
		model.put("user", user);
		model.put("clientAdminForm", clientAdminForm);
		return new ModelAndView("createClientAdmin", "model", model);

	}

	@RequestMapping(value = "/createCompany", method = RequestMethod.GET)
	public ModelAndView createCompany(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		companyForm = new CompanyForm();
		model.put("companyForm", companyForm);
		return new ModelAndView("createCompany", "model", model);
	}

	@RequestMapping(value = "/createCompanyConfirm", method = RequestMethod.POST)
	public ModelAndView createCompanyConfirm(@ModelAttribute CompanyForm companyForm, ModelMap model,
											 RedirectAttributes attribute) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("companyForm", companyForm);
		model.put("user", user);
		return new ModelAndView("createCompanyConfirm", "model", model);
	}

	@RequestMapping(value = "/createCompanySave", method = RequestMethod.POST)
	public ModelAndView createCompanySave(@ModelAttribute CompanyForm companyForm, ModelMap model,
										  RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		companyForm.setTransactionId(KeyGenerator.generateTransactionKey());

		companyForm.setTransactionId(KeyGenerator.generateTransactionKey());
		Company company = new Company();
		company.setCompanyName(companyForm.getCompanyName());
		company.setCompanyPrefix(companyForm.getCompanyPrefix());
		company.setAddress(companyForm.getAddress());
		company.setCity(companyForm.getCity());
		company.setCountry(companyForm.getCountry());
		company.setPincode(companyForm.getPincode());
		company.setState(companyForm.getState());
		company.setStatus("Pending");
		company.setIsForTrading(Boolean.FALSE);

		companyDAO.insertCompany(company);

		Transaction trans = new Transaction();
		trans.setTransactionId(companyForm.getTransactionId());
		trans.setTransactionStatus("Company Created");
		trans.setTransactionType("Company");

		transactionDAO.insertTransaction(trans);

		model.put("companyForm", companyForm);
		model.put("transaction", trans);
		model.put("user", user);

		return new ModelAndView("companyTransaction", "model", model);

	}

	@RequestMapping(value = "/companyTransaction", method = RequestMethod.GET)
	public ModelAndView companyTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("companyForm", companyForm);

		return new ModelAndView("companyTransaction", "model", model);

	}

	@RequestMapping(value = "/companyList", method = RequestMethod.GET)
	public ModelAndView companyList(ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Company> companyList = companyDAO.getCompanyList();
		if (companyList != null && companyList.size() > 0) {
			model.put("companyList", companyList);
			model.put("companyForm", companyForm);
			return new ModelAndView("empCompanyList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}
	}

	@RequestMapping(value = "/selectClientAdmin", method = RequestMethod.GET)
	public ModelAndView selectClientAdmin(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		ClientAdmin customer = clientAdminDAO.getByClientAdminId(id);

		Company company = companyDAO.getByCompanyId(customer.getCompanyId());

		clientAdminForm.setId(customer.getId());
		clientAdminForm.setName(customer.getName());
		clientAdminForm.setCompanyName(company.getCompanyName());
		clientAdminForm.setCountry(customer.getCountry());
		clientAdminForm.setState(customer.getState());
		clientAdminForm.setCity(customer.getCity());
		clientAdminForm.setAddress(customer.getAddress());
		clientAdminForm.setPincode(customer.getPincode());
		clientAdminForm.setContactNum(customer.getContactNum());
		clientAdminForm.setAltContactNum(customer.getAltContactNum());
		clientAdminForm.setEmail(customer.getEmail());
		clientAdminForm.setAltEmail(customer.getAltEmail());
		clientAdminForm.setCompanyPrefix(customer.getCompanyPrefix());
		clientAdminForm.setPosition(customer.getPosition());
		clientAdminForm.setGender(customer.getGender());
		clientAdminForm.setDateOfBirth(customer.getDateOfBirth());
		clientAdminForm.setCustomerPrefix(customer.getCustomerPrefix());

		model.put("user", user);

		model.put("clientAdminForm", clientAdminForm);

		return new ModelAndView("selectClientAdmin", "model", model);

	}

	@RequestMapping(value = "/updateClientAdminConfirm", method = RequestMethod.POST)
	public ModelAndView updateClientAdminConfirm(@ModelAttribute ClientAdminForm clientAdminForm, ModelMap model,
												 RedirectAttributes attribute) {
		EndUser user = getCurrentLoggedUserDetails();

		clientAdminForm.setId(clientAdminForm.getId());
		clientAdminForm.setName(clientAdminForm.getName());
		clientAdminForm.setCompanyName(clientAdminForm.getCompanyName());
		clientAdminForm.setCountry(clientAdminForm.getCountry());
		clientAdminForm.setState(clientAdminForm.getState());
		clientAdminForm.setCity(clientAdminForm.getCity());
		clientAdminForm.setAddress(clientAdminForm.getAddress());
		clientAdminForm.setPincode(clientAdminForm.getPincode());
		clientAdminForm.setContactNum(clientAdminForm.getContactNum());
		clientAdminForm.setAltContactNum(clientAdminForm.getAltContactNum());
		clientAdminForm.setEmail(clientAdminForm.getEmail());
		clientAdminForm.setAltEmail(clientAdminForm.getAltEmail());
		clientAdminForm.setCompanyPrefix(clientAdminForm.getCompanyPrefix());
		clientAdminForm.setPosition(clientAdminForm.getPosition());
		clientAdminForm.setGender(clientAdminForm.getGender());
		clientAdminForm.setDateOfBirth(clientAdminForm.getDateOfBirth());
		clientAdminForm.setCustomerPrefix(clientAdminForm.getCustomerPrefix());
		clientAdminForm.setManager(clientAdminForm.getManager());
		clientAdminForm.setManagerEmail(clientAdminForm.getManagerEmail());

		model.put("clientAdminForm", clientAdminForm);
		model.put("user", user);

		return new ModelAndView("updateClientAdminConfirm", "model", model);

	}

	@RequestMapping(value = "/updateClientAdmin", method = RequestMethod.POST)
	public ModelAndView updateClientAdmin(ModelMap model, @ModelAttribute ClientAdminForm clientAdminForm,
										  RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		ClientAdmin customer = clientAdminDAO.getByClientAdminId(clientAdminForm.getId());

		customer.setName(clientAdminForm.getName());
		// customer.setCompanyName(clientAdminForm.getCompanyName());
		customer.setCountry(clientAdminForm.getCountry());
		customer.setState(clientAdminForm.getState());
		customer.setCity(clientAdminForm.getCity());
		customer.setAddress(clientAdminForm.getAddress());
		customer.setPincode(clientAdminForm.getPincode());
		customer.setContactNum(clientAdminForm.getContactNum());
		customer.setAltContactNum(clientAdminForm.getAltContactNum());
		customer.setEmail(clientAdminForm.getEmail());
		customer.setAltEmail(clientAdminForm.getAltEmail());
		customer.setCompanyPrefix(clientAdminForm.getCompanyPrefix());
		customer.setPosition(clientAdminForm.getPosition());
		customer.setGender(clientAdminForm.getGender());
		customer.setDateOfBirth(clientAdminForm.getDateOfBirth());
		customer.setCustomerPrefix(clientAdminForm.getCustomerPrefix());
		customer.setManager(clientAdminForm.getManager());
		customer.setManagerEmail(clientAdminForm.getManagerEmail());
		customer.setStatus("Pending");

		clientAdminDAO.updateUser(customer);

		return new ModelAndView("redirect:createClientAdmin");

	}

	@RequestMapping(value = "/clientAdminConfirm", method = RequestMethod.POST)
	public ModelAndView clientAdminConfirm(@ModelAttribute ClientAdminForm clientAdminForm, ModelMap model,
										   RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		List<ClientAdmin> head = clientAdminDAO.getClientAdminList(clientAdminForm.getName()).getResultList();

		List<EndUser> endUser = endUserDAOImpl.findByUsername(clientAdminForm.getName()).getResultList();

		Company company = companyDAO.getByCompanyId(clientAdminForm.getCompanyId());

		if (endUser.size() != 0 || head.size() != 0) {
			model.put("user", user);
			attributes.addFlashAttribute("success", "Customer Name Already Exists");

			return new ModelAndView("redirect:createClientAdmin");
		} else {

			clientAdminForm.setName(clientAdminForm.getName());
			clientAdminForm.setCompanyName(company.getCompanyName());
			clientAdminForm.setCompanyId(clientAdminForm.getCompanyId());
			clientAdminForm.setAddress(company.getAddress());
			clientAdminForm.setContactNum(clientAdminForm.getContactNum());
			clientAdminForm.setAltContactNum(clientAdminForm.getAltContactNum());
			clientAdminForm.setEmail(clientAdminForm.getEmail());
			clientAdminForm.setAltEmail(clientAdminForm.getAltEmail());
			clientAdminForm.setPosition(clientAdminForm.getPosition());
			clientAdminForm.setGender(clientAdminForm.getGender());
			clientAdminForm.setDateOfBirth(clientAdminForm.getDateOfBirth());

			clientAdminForm.setManager(clientAdminForm.getManager());
			clientAdminForm.setManagerEmail(clientAdminForm.getManagerEmail());
			clientAdminForm.setAccExpiryDate(clientAdminForm.getAccExpiryDate());

			model.put("clientAdminForm", clientAdminForm);
			model.put("user", user);

			return new ModelAndView("clientAdminConfirm", "model", model);
		}
	}

	@RequestMapping(value = "/clientAdminSave", method = RequestMethod.POST)
	public ModelAndView clientAdminSave(@ModelAttribute ClientAdminForm clientAdminForm, ModelMap model,
										RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		clientAdminForm.setTransactionId(KeyGenerator.generateTransactionKey());


		clientAdminForm.setTransactionId(KeyGenerator.generateTransactionKey());

		Company company = companyDAO.getByCompanyName(clientAdminForm.getCompanyName());

		ClientAdmin customer = new ClientAdmin();
		customer.setName(clientAdminForm.getName());
		customer.setCompanyId(company.getId());
		customer.setCountry(clientAdminForm.getCountry());
		customer.setState(clientAdminForm.getState());
		customer.setCity(clientAdminForm.getCity());
		customer.setAddress(clientAdminForm.getAddress());
		customer.setPincode(clientAdminForm.getPincode());
		customer.setContactNum(clientAdminForm.getContactNum());
		customer.setAltContactNum(clientAdminForm.getAltContactNum());
		customer.setEmail(clientAdminForm.getEmail());
		customer.setAltEmail(clientAdminForm.getAltEmail());
		customer.setCompanyPrefix(clientAdminForm.getCompanyPrefix());
		customer.setPosition(clientAdminForm.getPosition());
		customer.setGender(clientAdminForm.getGender());
		customer.setDateOfBirth(clientAdminForm.getDateOfBirth());
		customer.setManager(clientAdminForm.getManager());
		customer.setManagerEmail(clientAdminForm.getManagerEmail());
		customer.setNotificationStatus("Pending");
		customer.setStatus("Pending");
		customer.setCustomerPrefix(clientAdminForm.getCustomerPrefix());
		customer.setTransactionId(clientAdminForm.getTransactionId());
		customer.setAccExpiryDate(clientAdminForm.getAccExpiryDate());
		customer.setIsForTrading(Boolean.FALSE);
		clientAdminDAO.insertCustomer(customer);

		Transaction trans = new Transaction();

		trans.setTransactionId(clientAdminForm.getTransactionId());
		trans.setTransactionStatus("Client Admin Saved");
		trans.setTransactionType("Customer Head");

		transactionDAO.insertTransaction(trans);

		model.put("clientAdminForm", clientAdminForm);
		model.put("user", user);

		return new ModelAndView("clientAdminTransaction", "model", model);

	}

	@RequestMapping(value = "/clientAdminTransaction", method = RequestMethod.GET)
	public ModelAndView clientAdminTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("clientAdminForm", clientAdminForm);

		return new ModelAndView("clientAdminTransaction", "model", model);

	}
	
	@RequestMapping(value = "/custAdminList", method = RequestMethod.GET)
	public ModelAndView apprCustAdmList(ModelMap model) {

		List<ClientAdmin> customerList = clientAdminDAO.getAllClientAdmin().getResultList();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		if (customerList != null && customerList.size() > 0) {
			model.put("customerList", customerList);

			return new ModelAndView("custAdminList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/bankCustomerHeadList", method = RequestMethod.GET)
	public ModelAndView customerHeadList(ModelMap model) {

		List<CustomerHead> customerList = customerDAO.getAllCustomerHead().getResultList();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		if (customerList != null && customerList.size() > 0) {
			model.put("customerList", customerList);

			return new ModelAndView("bankCustHeadList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/bankCustomerBranchList", method = RequestMethod.GET)
	public ModelAndView customerBranchList(ModelMap model) {

		Collection<CustomerBranch> customerList = customerDAO.findAllCustomerBranch();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		if (customerList != null && customerList.size() > 0) {
			model.put("customerList", customerList);

			return new ModelAndView("bankCustBranchList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/bankCustomerSubsidiaryList", method = RequestMethod.GET)
	public ModelAndView customerSubsidiaryList(ModelMap model) {

		Collection<CustomerSubsidiary> customerList = customerDAO.findAllCustomerSubsidiary();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		if (customerList != null && customerList.size() > 0) {
			model.put("customerList", customerList);

			return new ModelAndView("bankCustSubsidiaryList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/buyerPageList", method = RequestMethod.GET)
	public ModelAndView getAllBuyerList(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		Collection<NewBuyer> buyerList = newBuyerDAOImpl.getList();

		model.put("user", user);
		if (buyerList != null && buyerList.size() > 0) {
			model.put("buyerList", buyerList);
			return new ModelAndView("buyerPageList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/supplierPageList", method = RequestMethod.GET)
	public ModelAndView getAllSellerList(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		Collection<Supplier> SupplierList = supplierService.getList();
		model.put("user", user);
		if (SupplierList != null && SupplierList.size() > 0) {
			model.put("SupplierList", SupplierList);

			return new ModelAndView("supplierPageList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/bankCustomerHeadNotification", method = RequestMethod.GET)
	public ModelAndView bankCustomerHeadNotification(ModelMap model) {

		List<CustomerHead> customerList = customerDAO.getApproved().getResultList();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		if (customerList != null && customerList.size() > 0) {
			model.put("customerList", customerList);

			return new ModelAndView("bankCustomerHeadNotification", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/selectbankCustomerHeadNotification", method = RequestMethod.GET)
	public ModelAndView selectApproveManager(ModelMap model, @RequestParam("id") Long id) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		CustomerHead customer = customerDAO.findCustomers(id);

		customerHeadForm.setId(customer.getId());
		customerHeadForm.setName(customer.getName());
		customerHeadForm.setPassword(customer.getPassword());
		customerHeadForm.setEmail(customer.getEmail());

		customerHeadForm.setNotificationStatus(customer.getNotificationStatus());

		model.put("customerHeadForm", customerHeadForm);

		model.put("customer", customer);

		return new ModelAndView("selectbankCustomerHeadNotification", "model", model);

	}

	@RequestMapping(value = "/bankCustomerHeadNotificationMail", method = RequestMethod.POST)
	public ModelAndView mailApprovalManagerk(ModelMap model, @ModelAttribute CustomerHeadForm customerHeadForm,
											 BindingResult result, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		CustomerHead customer = customerDAO.findCustomers(customerHeadForm.getId());

		String bankName = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0).getBankName();
		try {
			String email = customer.getEmail();
			String adress = customer.getAddress();
			String name = customer.getName();
			String password = customer.getPassword();

			String tex = "Bank Customer Head Notification Details ";

			customer.setNotificationStatus("Notification Sent");
			customerDAO.updateUser(customer);

			SimpleMailMessage emails = new SimpleMailMessage();
			emails.setTo(email);
			emails.setSubject(tex);
			emails.setText("Hello " + name
					+ ",\n\n An official Bank Customer HeadNotification has been registered against you by " + "\n"
					+ "\n" + "\n\nName:" + name + "\n\nPassword:" + password + "\n\nAddress:" + adress
					+ "\nRegards,\n"+bankName);
			System.out.println("" + email + name);
			mailSender.send(emails);
			SimpleMailMessage emails1 = new SimpleMailMessage();
			emails1.setTo(email);
			emails1.setSubject(tex);
			emails1.setText("Hello " + name
					+ "\n\n An official Bank Customer HeadNotification has been registered approved by the bank.\n"
					+ "\n\nRegards,\n"+bankName);
			customer.setNotificationStatus("Sent");
			customerDAO.updateUser(customer);
			attributes.addFlashAttribute("success", "Mail has been sent successfully.");

		} catch (Exception e) {
			e.getMessage();
		}
		return new ModelAndView("redirect:bankCustomerHeadNotification");
	}

	@RequestMapping(value = "/bankCustomerBranchNotification", method = RequestMethod.GET)
	public ModelAndView bankCustomerBranchNotification(ModelMap model) {

		Collection<CustomerBranch> customerList = customerDAO.findAllCustomerBranchApprovedList();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		if (customerList != null && customerList.size() > 0) {
			model.put("customerList", customerList);

			return new ModelAndView("bankCustomerBranchNotification", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}
	}

	@RequestMapping(value = "/selectbankCustomerBranchNotification", method = RequestMethod.GET)
	public ModelAndView bankCustomerBranchNotification(ModelMap model, @RequestParam("id") Long id) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		CustomerBranch customer = customerDAO.getByCustomerBranchId(id);

		customerBranchForm.setId(customer.getId());
		customerBranchForm.setName(customer.getName());
		customerBranchForm.setAddress(customer.getAddress());

		customerBranchForm.setContactNum(customer.getContactNum());

		customerBranchForm.setEmail(customer.getEmail());
		customerBranchForm.setName(customer.getName());
		customerBranchForm.setPassword(customer.getPassword());

		model.put("customerBranchForm", customerBranchForm);
		model.put("customer", customer);
		return new ModelAndView("selectbankCustomerBranchNotification", "model", model);

	}

	@RequestMapping(value = "/bankCustomerBranchNotificationMail", method = RequestMethod.POST)
	public ModelAndView bankCustomerBranchNotificationMail(ModelMap model,
														   @ModelAttribute CustomerBranchForm customerBranchForm, BindingResult result,
														   RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		String bankName = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0).getBankName();

		CustomerBranch customer = customerDAO.getByCustomerBranchId(customerBranchForm.getId());
		try {
			String email = customer.getEmail();

			String adress = customer.getAddress();
			String name = customer.getName();
			String password = customer.getPassword();

			String tex = "Bank Customer Branch Notification Details ";

			customer.setNotificationStatus("Notification Sent");
			customerDAO.updateBranch(customer);

			SimpleMailMessage emails = new SimpleMailMessage();
			emails.setTo(email);
			emails.setSubject(tex);
			emails.setText("Hello " + name
					+ ",\n\n An official Bank Customer Branch Notification Details has been registered against you by "
					+ "\n" + "\n" + "\n\nName:" + name + "\n\nPassword:" + password + "\n\nAddress:" + adress
					+ "\nRegards,\n"+bankName);
			System.out.println("" + email + name);
			mailSender.send(emails);
			SimpleMailMessage emails1 = new SimpleMailMessage();
			emails1.setTo(email);
			emails1.setSubject(tex);
			emails1.setText("Hello " + name
					+ "\n\n An official Bank Customer Branch Notification Details has been registered approved by the bank.\n"
					+ "\n\nRegards,\n"+bankName);
			customer.setNotificationStatus("Sent");
			customerDAO.updateBranch(customer);
			attributes.addFlashAttribute("success", "Mail has been sent successfully.");

		} catch (Exception e) {
			System.out.println(e.getMessage() + e);
		}
		return new ModelAndView("redirect:bankCustomerBranchNotification");
	}

	@RequestMapping(value = "/bankCustomerSubsidiaryNotification", method = RequestMethod.GET)
	public ModelAndView bankCustomerSubsidiaryNotification(ModelMap model) {

		Collection<CustomerSubsidiary> customerList = customerDAO.findAllCustomerSubsidiary();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		if (customerList != null && customerList.size() > 0) {
			model.put("customerList", customerList);

			return new ModelAndView("bankCustomerSubsidiaryNotification", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/selectbankCustomerSubsidiaryNotification", method = RequestMethod.GET)
	public ModelAndView bankCustomerSubsidiaryNotification(ModelMap model, @RequestParam("id") Long id) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		CustomerSubsidiary customer = customerDAO.getByCustomerSubsidiaryId(id);

		customerSubsidiaryForm.setId(customer.getId());
		customerSubsidiaryForm.setName(customer.getName());

		customerSubsidiaryForm.setAddress(customer.getAddress());

		customerSubsidiaryForm.setContactNum(customer.getContactNum());

		customerSubsidiaryForm.setEmail(customer.getEmail());
		customerSubsidiaryForm.setName(customer.getName());
		customerSubsidiaryForm.setPassword(customer.getPassword());

		model.put("customer", customer);

		model.put("customerSubsidiaryForm", customerSubsidiaryForm);

		return new ModelAndView("selectbankCustomerSubsidiaryNotification", "model", model);

	}

	@RequestMapping(value = "/bankCustomerSubsidiaryNotificationMail", method = RequestMethod.POST)
	public ModelAndView bankCustomerSubsidiaryNotificationMail(ModelMap model,
															   @ModelAttribute CustomerSubsidiaryForm customerSubsidiaryForm, BindingResult result,
															   RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		String bankName = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0).getBankName();

		CustomerSubsidiary customer = customerDAO.getByCustomerSubsidiaryId(customerSubsidiaryForm.getId());
		try {
			String email = customer.getEmail();
			String adress = customer.getAddress();
			String name = customer.getName();
			String password = customer.getPassword();

			String tex = "Bank Customer Subsidiary Notification Details ";

			customer.setNotificationStatus("Notification Sent");
			customerDAO.updateSubsidiary(customer);

			SimpleMailMessage emails = new SimpleMailMessage();
			emails.setTo(email);
			emails.setSubject(tex);
			emails.setText("Hello " + name
					+ ",\n\n An official Bank Customer Subsidiary Notification Details has been registered against you by "
					+ "\n" + "\n" + "\n\nName:" + name + "\n\nPassword:" + password + "\n\nAddress:" + adress
					+ "\nRegards,\n"+bankName);
			System.out.println("" + email + name);
			mailSender.send(emails);
			SimpleMailMessage emails1 = new SimpleMailMessage();
			emails1.setTo(email);
			emails1.setSubject(tex);
			emails1.setText("Hello " + name
					+ "\n\n An official Customer Subsidiary Notification Details has been registered approved by the bank.\n"
					+ "\n\nRegards,\n"+bankName);
			customer.setNotificationStatus("Sent");
			customerDAO.updateSubsidiary(customer);
			attributes.addFlashAttribute("success", "Mail has been sent successfully.");
		} catch (Exception e) {
			System.out.println(e.getMessage() + e);
		}
		return new ModelAndView("redirect:bankCustomerSubsidiaryNotification");
	}

	@RequestMapping(value = "/bankMasterPlanPendingDetails", method = RequestMethod.GET)
	public ModelAndView bankMasterPlanPendingDetails(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<MasterPlan> masterList = masterPlanDAO.getMasterPlanByPenStatus().getResultList();

		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);
			return new ModelAndView("bankMasterPlanPendingDetails", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/bankMasterPlanFullDetails", method = RequestMethod.GET)
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

			List<SellerBuyingCost> sellerList = SellerBuyingCostDAO.getSellerBuyingCostList(masterList.getMasterKey())
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

		return new ModelAndView("bankMasterPlanFullDetails", "model", model);

	}

	@RequestMapping(value = "/riskAssignment", method = RequestMethod.GET)
	public ModelAndView riskAssignment(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("riskAssignment", "model", model);

	}

	@RequestMapping(value = "/bankMasterPlanApproveStatus", method = RequestMethod.GET)
	public ModelAndView bankMasterPlanApproveStatus(@RequestParam("id") Long id, ModelMap model) {

		MasterPlan master = masterPlanDAO.getByMasterPlanId(id);

		masterPlanForm.setId(master.getId());
		masterPlanForm.setCustomer(master.getCustomer());
		masterPlanForm.setMasterKey(master.getMasterKey());
		masterPlanForm.setTransactionId(master.getTransactionId());

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("bankMasterPlanApproveStatus", "model", model);

	}

	@RequestMapping(value = "/bankMasterPlanApproveStatusConfirm", method = RequestMethod.POST)
	public ModelAndView bankMasterPlanApproveStatusConfirm(ModelMap model,
														   @ModelAttribute MasterPlanForm masterPlanForm, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		masterPlanForm.setTransactionId(masterPlanForm.getTransactionId());
		masterPlanForm.setCustomer(masterPlanForm.getCustomer());
		masterPlanForm.setStatus(masterPlanForm.getStatus());
		masterPlanForm.setComment(masterPlanForm.getComment());

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("bankMasterPlanApproveStatusConfirm", "model", model);

	}

	@RequestMapping(value = "/bankMasterPlanApproveStatusPost", method = RequestMethod.POST)
	public ModelAndView draftsMasterPlanInfoConfirm(ModelMap model, @ModelAttribute MasterPlanForm masterPlanForm,
													RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		masterPlanForm.setTransactionId(masterPlanForm.getTransactionId());

		MasterPlan master = masterPlanDAO.getByMasterPlanId(masterPlanForm.getId());
		Date date = DateService.loginDate;
		master.setBankDate(date);
		master.setStatus(masterPlanForm.getStatus());
		master.setComment(masterPlanForm.getComment());

		masterPlanDAO.updatePlan(master);

		Transaction trans = new Transaction();
		trans.setTransactionId(masterPlanForm.getTransactionId());
		trans.setTransactionStatus("Master Plan Approval/Reject By Bank");
		trans.setTransactionType("Submitted Successfully");

		transactionDAO.insertTransaction(trans);

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("masterPlanAppTransaction", "model", model);

	}

	@RequestMapping(value = "/masterPlanAppTransaction", method = RequestMethod.GET)
	public ModelAndView collateralTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("masterPlanAppTransaction", "model", model);

	}

	@RequestMapping(value = "/bankMasterPlanDetails", method = RequestMethod.GET)
	public ModelAndView bankMasterPlanDetails(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<MasterPlan> masterList = masterPlanDAO.getMasterPlanByAppStatus().getResultList();

		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);
			return new ModelAndView("bankMasterPlanDetails", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/masterPlanCreditAssign", method = RequestMethod.GET)
	public ModelAndView masterPlanCreditAssign(@RequestParam("id") Long id, ModelMap model,
											   RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		MasterPlan master = masterPlanDAO.getByMasterPlanId(id);

		masterPlanForm.setId(master.getId());
		masterPlanForm.setBuyingCost(master.getBuyingCost());
		masterPlanForm.setTenure(master.getTenure());
		masterPlanForm.setTransactionId(master.getTransactionId());

		if (master.getBuyingCostSanc() != null) {
			model.put("user", user);
			attributes.addFlashAttribute("success", "Credit Assignment is already Done.");
			return new ModelAndView("redirect:bankMasterPlanDetails");
		} else {

			model.put("user", user);
			model.put("masterPlanForm", masterPlanForm);
			return new ModelAndView("masterPlanCreditAssign", "model", model);
		}
	}

	@RequestMapping(value = "/masterPlanCreditAssignConfirm", method = RequestMethod.POST)
	public ModelAndView masterPlanCreditAssignConfirm(ModelMap model, @ModelAttribute MasterPlanForm masterPlanForm,
													  RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		masterPlanForm.setId(masterPlanForm.getId());
		masterPlanForm.setTransactionId(masterPlanForm.getTransactionId());
		masterPlanForm.setTenure(masterPlanForm.getTenure());
		masterPlanForm.setBuyingCost(masterPlanForm.getBuyingCost());
		masterPlanForm.setBuyingCostSanc(masterPlanForm.getBuyingCostSanc());
		masterPlanForm.setInterestType(masterPlanForm.getInterestType());
		masterPlanForm.setRateOfInt(masterPlanForm.getRateOfInt());
		masterPlanForm.setNoOfDays(masterPlanForm.getNoOfDays());
		masterPlanForm.setPlrRate(masterPlanForm.getPlrRate());
		masterPlanForm.setBasicPoints(masterPlanForm.getBasicPoints());
		masterPlanForm.setBasicAmt(masterPlanForm.getBasicAmt());
		masterPlanForm.setCalPlrRate(masterPlanForm.getCalPlrRate());
		masterPlanForm.setTotal1(masterPlanForm.getTotal1());
		masterPlanForm.setRateOfInt1(masterPlanForm.getRateOfInt1());
		masterPlanForm.setFlatCharges(masterPlanForm.getFlatCharges());
		masterPlanForm.setPercentAmt(masterPlanForm.getPercentAmt());
		masterPlanForm.setPercentage(masterPlanForm.getPercentage());
		masterPlanForm.setProcFee(masterPlanForm.getProcFee());
		masterPlanForm.setDocFee(masterPlanForm.getDocFee());
		masterPlanForm.setLateFee(masterPlanForm.getLateFee());
		masterPlanForm.setTaxName(masterPlanForm.getTaxName());
		masterPlanForm.setTaxPercentage(masterPlanForm.getTaxPercentage());
		masterPlanForm.setTaxAmt(masterPlanForm.getTaxAmt());
		masterPlanForm.setTaxName1(masterPlanForm.getTaxName1());
		masterPlanForm.setTaxPercentage1(masterPlanForm.getTaxPercentage1());
		masterPlanForm.setTaxAmt1(masterPlanForm.getTaxAmt1());
		masterPlanForm.setTaxName2(masterPlanForm.getTaxName2());
		masterPlanForm.setTaxPercentage2(masterPlanForm.getTaxPercentage2());
		masterPlanForm.setTaxAmt2(masterPlanForm.getTaxAmt2());
		masterPlanForm.setFunalAmt(masterPlanForm.getFunalAmt());

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("masterPlanCreditAssignConfirm", "model", model);

	}

	@RequestMapping(value = "/masterPlanCreditAssignPost", method = RequestMethod.POST)
	public ModelAndView masterPlanCreditAssignPost(ModelMap model, @ModelAttribute MasterPlanForm masterPlanForm,
												   RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		masterPlanForm.setTransactionId(masterPlanForm.getTransactionId());

		MasterPlan master = masterPlanDAO.getByMasterPlanId(masterPlanForm.getId());

		master.setBuyingCostSanc(masterPlanForm.getBuyingCostSanc());
		master.setInterestType(masterPlanForm.getInterestType());
		master.setRateOfInt(masterPlanForm.getRateOfInt());
		master.setTenure(masterPlanForm.getTenure());
		master.setNoOfDays(masterPlanForm.getNoOfDays());
		master.setPlrRate(masterPlanForm.getPlrRate());
		master.setBasicPoints(masterPlanForm.getBasicPoints());
		master.setBasicAmt(masterPlanForm.getBasicAmt());
		master.setCalPlrRate(masterPlanForm.getCalPlrRate());
		master.setTotal1(masterPlanForm.getTotal1());
		master.setRateOfInt1(masterPlanForm.getRateOfInt1());
		master.setFlatCharges(masterPlanForm.getFlatCharges());
		master.setPercentAmt(masterPlanForm.getPercentAmt());
		master.setPercentage(masterPlanForm.getPercentage());
		master.setProcFee(masterPlanForm.getProcFee());
		master.setDocFee(masterPlanForm.getDocFee());
		master.setLateFee(masterPlanForm.getLateFee());
		master.setTaxName(masterPlanForm.getTaxName());
		master.setTaxPercentage(masterPlanForm.getTaxPercentage());
		master.setTaxAmt(masterPlanForm.getTaxAmt());
		master.setTaxName1(masterPlanForm.getTaxName1());
		master.setTaxPercentage1(masterPlanForm.getTaxPercentage1());
		master.setTaxAmt1(masterPlanForm.getTaxAmt1());
		master.setTaxName2(masterPlanForm.getTaxName2());
		master.setTaxPercentage2(masterPlanForm.getTaxPercentage2());
		master.setTaxAmt2(masterPlanForm.getTaxAmt2());
		master.setFunalAmt(masterPlanForm.getFunalAmt());
		Date date = DateService.loginDate;
		master.setCreditDate(date);
		masterPlanDAO.updatePlan(master);

		Transaction trans = new Transaction();
		trans.setTransactionId(masterPlanForm.getTransactionId());
		trans.setTransactionStatus("Credit Assignment");
		trans.setTransactionType("Submitted Successfully");

		transactionDAO.insertTransaction(trans);

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("creditAssignmentTransaction", "model", model);

	}

	@RequestMapping(value = "/creditAssignmentTransaction", method = RequestMethod.GET)
	public ModelAndView creditAssignmentTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("creditAssignmentTransaction", "model", model);

	}

	@RequestMapping(value = "/masterPlanConsBank", method = RequestMethod.GET)
	public ModelAndView masterPlanConfirm(@RequestParam("id") Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		MasterPlan master = masterPlanDAO.getByMasterPlanId(id);

		List<ConsortiumBank> consortiumList = consortiumDAO.getConsortiumByMasterKey(master.getMasterKey())
				.getResultList();
		List<PieChartForm> dataList = new ArrayList<>();
		if (consortiumList != null && consortiumList.size() > 0) {
			for (ConsortiumBank consortium : consortiumList) {
				PieChartForm pie = new PieChartForm();
				pie.setName(consortium.getBankName() + "(" + consortium.getBankBranch() + ")");
				pie.setY(Float.valueOf(consortium.getBankLimit()));
				dataList.add(pie);
			}
		}
		Gson gson = new Gson();
		String data = gson.toJson(dataList);
		model.addAttribute("data", data);

		consortiumForm.setMasterKey(master.getMasterKey());
		consortiumForm.setTransactionId(master.getTransactionId());

		model.put("user", user);
		model.put("consortiumForm", consortiumForm);

		return new ModelAndView("masterPlanConsBank", "model", model);
	}

	@RequestMapping(value = "/masterPlanConsBankPost", method = RequestMethod.POST)
	public ModelAndView masterPlanInfoPost(ModelMap model, @ModelAttribute ConsortiumForm consortiumForm,
										   BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		consortiumForm.setTransactionId(KeyGenerator.generateTransactionKey());

		if (consortiumForm.getBankName() != "") {
			String allStatus = consortiumForm.getStatus();
			String[] status = allStatus.split(",");

			String allBank = consortiumForm.getBankName();
			String[] bank = allBank.split(",");

			String allBranches = consortiumForm.getBankBranch();
			String[] branches = allBranches.split(",");

			String allLoc = consortiumForm.getLocation();
			String[] loc = allLoc.split(",");

			String allLimit = consortiumForm.getLimit();
			String[] limit = allLimit.split(",");

			List<ConsortiumForm> masterplanList1 = new ArrayList<ConsortiumForm>();
			for (int count = 0; count < bank.length; count++) {

				ConsortiumForm masterForm1 = new ConsortiumForm();

				masterForm1.setStatus(status[count]);
				masterForm1.setBankName(bank[count]);
				masterForm1.setBankBranch(branches[count]);
				masterForm1.setLocation(loc[count]);
				masterForm1.setLimit(limit[count]);
				masterplanList1.add(masterForm1);
			}
			for (ConsortiumForm value : masterplanList1) {

				ConsortiumBank seller = new ConsortiumBank();
				seller.setMasterKey(consortiumForm.getMasterKey());
				seller.setTransactionId(consortiumForm.getTransactionId());
				seller.setStatus(value.getStatus());
				seller.setBankName(value.getBankName());
				seller.setLocation(value.getLocation());
				seller.setBankBranch(value.getBankBranch());
				seller.setBankLimit(value.getLimit());
				consortiumDAO.createConsortium(seller);
			}
		}

		Transaction trans = new Transaction();

		trans.setTransactionId(consortiumForm.getTransactionId());
		trans.setTransactionStatus("Consortium Of Bank Saved");
		trans.setTransactionType("Consortium Of Bank");

		transcationDAOImpl.insertTransaction(trans);
		// }

		model.put("user", user);
		model.put("consortiumForm", consortiumForm);

		return new ModelAndView("masterPlanConsBankTransaction", "model", model);

	}

	@RequestMapping(value = "/masterPlanConsBankTransaction", method = RequestMethod.GET)
	public ModelAndView masterPlanConsBankTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("consortiumForm", consortiumForm);

		return new ModelAndView("masterPlanConsBankTransaction", "model", model);

	}

	@RequestMapping(value = "/bankMasterPlanSend", method = RequestMethod.GET)
	public ModelAndView bankMasterPlanSend(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		MasterPlanForm  masterPlanForm  = new MasterPlanForm ();

		MasterPlan masterPlan = masterPlanDAO.getByMasterPlanId(id);

		masterPlanForm.setCustomer(masterPlan.getCustomer());
		masterPlanForm.setMasterKey(masterPlan.getMasterKey());
		masterPlanForm.setProduct(masterPlan.getProduct());

		if (masterPlan.getBuyingCostSanc() != null) {
			if (masterPlan != null) {
				masterPlan.setApprovalSent("Yes");
				masterPlan.setManagerStatus("Pending");
				masterPlanDAO.updatePlan(masterPlan);
			}

			/* attributes.addFlashAttribute("success", "Sent for Approval"); */

		} else {

			attributes.addFlashAttribute("success", "Credit  Assignment is Mandatory");
			return  new ModelAndView ("redirect:bankMasterPlanDetails");
		}
		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);
		//return "redirect:bankMasterPlanDetails";
		return new ModelAndView("bankMasterPlanSendTrans");
	}

	@RequestMapping(value = "/masterPlanWcAssign", method = RequestMethod.GET)
	public ModelAndView masterPlanWcAssign(@RequestParam("id") Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		MasterPlan master = masterPlanDAO.getByMasterPlanId(id);

		masterPlanForm.setId(master.getId());
		masterPlanForm.setWorkingCapital(master.getWorkingCapital());
		masterPlanForm.setWcTenure(master.getWcTenure());
		masterPlanForm.setTransactionId(master.getTransactionId());

		if (master.getWcSancAmount() != null) {
			model.put("user", user);
			attributes.addFlashAttribute("success", "Working Capital Assignment is already Done.");
			return new ModelAndView("redirect:bankMasterPlanDetails");
		} else {

			model.put("user", user);
			model.put("masterPlanForm", masterPlanForm);
			return new ModelAndView("masterPlanWcAssign", "model", model);
		}
	}

	@RequestMapping(value = "/masterPlanWcAssignConfirm", method = RequestMethod.POST)
	public ModelAndView masterPlanWcAssignConfirm(ModelMap model, @ModelAttribute MasterPlanForm masterPlanForm,
												  RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		masterPlanForm.setId(masterPlanForm.getId());
		masterPlanForm.setTransactionId(masterPlanForm.getTransactionId());
		masterPlanForm.setWcTenure(masterPlanForm.getWcTenure());
		masterPlanForm.setWorkingCapital(masterPlanForm.getWorkingCapital());
		masterPlanForm.setWcSancAmount(masterPlanForm.getWcSancAmount());
		masterPlanForm.setWcSancInterest(masterPlanForm.getWcSancInterest());
		masterPlanForm.setWcTotalInterest(masterPlanForm.getWcTotalInterest());
		masterPlanForm.setWcTotalAmount(masterPlanForm.getWcTotalAmount());

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("masterPlanWcAssignConfirm", "model", model);

	}

	@RequestMapping(value = "/masterPlanWcAssignPost", method = RequestMethod.POST)
	public ModelAndView masterPlanWcAssignPost(ModelMap model, @ModelAttribute MasterPlanForm masterPlanForm,
											   RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		masterPlanForm.setTransactionId(masterPlanForm.getTransactionId());

		MasterPlan master = masterPlanDAO.getByMasterPlanId(masterPlanForm.getId());

		master.setWcSancAmount(masterPlanForm.getWcSancAmount());
		master.setWcSancInterest(masterPlanForm.getWcSancInterest());
		master.setWcTotalInterest(masterPlanForm.getWcTotalInterest());
		master.setWcTotalAmount(masterPlanForm.getWcTotalAmount());
		master.setWcTenure(masterPlanForm.getWcTenure());
		Date date = DateService.loginDate;
		master.setWcDate(date);
		masterPlanDAO.updatePlan(master);

		Transaction trans = new Transaction();
		trans.setTransactionId(masterPlanForm.getTransactionId());
		trans.setTransactionStatus("Working Capital Assignment");
		trans.setTransactionType("Submitted Successfully");

		transactionDAO.insertTransaction(trans);

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("wcAssignmentTransaction", "model", model);

	}

	@RequestMapping(value = "/wcAssignmentTransaction", method = RequestMethod.GET)
	public ModelAndView wcAssignmentTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("wcAssignmentTransaction", "model", model);

	}

	@RequestMapping(value = "/masterPlanFullList", method = RequestMethod.GET)
	public ModelAndView masterPlanFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<MasterPlan> masterList = masterPlanDAO.getAllMasterPlans().getResultList();

		model.put("user", user);
		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);

			return new ModelAndView("masterPlanFullList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/buyerDocumentList", method = RequestMethod.GET)
	public ModelAndView buyerDocumentList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		Collection<UploadedFile> uploadList = uploadDaoImpl.getList();

		if (uploadList != null && uploadList.size() > 0) {
			model.put("uploadList", uploadList);

			return new ModelAndView("buyerUploadedDocumentList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/BankFullInvoiceList", method = RequestMethod.GET)
	public ModelAndView BankFullInvoiceList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<Invoice> invoiceList = invoiceDAO.getList().getResultList();

		model.put("user", user);
		if (invoiceList != null && invoiceList.size() > 0) {
			model.put("invoiceList", invoiceList);

			return new ModelAndView("BankFullInvoiceList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/banlFullPoList", method = RequestMethod.GET)
	public ModelAndView fullPoList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PurchaseOrder> poList = purchaseOrderDAO.getList().getResultList();

		model.put("user", user);
		if (poList != null && poList.size() > 0) {
			model.put("poList", poList);

			return new ModelAndView("banlFullPoList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/inventoryBankList", method = RequestMethod.GET)
	public ModelAndView InventoryList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		Collection<Inventory> inventoryList = invenrotyDAO.getList();
		model.put("user", user);
		if (inventoryList != null && inventoryList.size() > 0) {
			model.put("inventoryList", inventoryList);

			return new ModelAndView("inventoryBankList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/inventoryInvoiceBankList", method = RequestMethod.GET)
	public ModelAndView inventoryInvoiceBankList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		Collection<InvoiceInventory> inventoryInvoiceList = invoiceInventoryDAO.getList();
		model.put("user", user);

		if (inventoryInvoiceList != null && inventoryInvoiceList.size() > 0) {
			model.put("inventoryInvoiceList", inventoryInvoiceList);

			return new ModelAndView("inventoryInvoiceBankList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/rateList", method = RequestMethod.GET)
	public ModelAndView rateList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PurchaseOrder> poList = purchaseOrderDAO.getPoListForRates().getResultList();

		model.put("user", user);
		if (poList != null && poList.size() > 0) {
			model.put("poList", poList);

			return new ModelAndView("rateList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/getRateList", method = RequestMethod.GET)
	public ModelAndView getRateList(@RequestParam("id") Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(id);

		List<BankDetails> details = bankDetailsDAO.getBankDetails().getResultList();

		purchaseOrderForm.setBanklist(details);
		purchaseOrderForm.setId(purchase.getId());
		purchaseOrderForm.setPoKey(purchase.getPoKey());
		purchaseOrderForm.setCustomerName(purchase.getCustomerName());
		purchaseOrderForm.setCustomerBranchEmail(purchase.getCustomerBranchEmail());
		purchaseOrderForm.setCustomerHeadEmail(purchase.getCustomerHeadEmail());
		purchaseOrderForm.setSupplierName(purchase.getSupplierName());
		purchaseOrderForm.setSupplierEmail(purchase.getSupplierEmail());
		purchaseOrderForm.setGoods(purchase.getGoods());
		purchaseOrderForm.setAmount(purchase.getAmount());
		purchaseOrderForm.setTenure(purchase.getTenure());
		purchaseOrderForm.setTransactionId(purchase.getTransactionId());

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("getRateList", "model", model);

	}

	@RequestMapping(value = "/getRateListConfirm", method = RequestMethod.POST)
	public ModelAndView getRateListConfirm(ModelMap model, @ModelAttribute PurchaseOrderForm purchaseOrderForm,
										   RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		purchaseOrderForm.setBankName(purchaseOrderForm.getBankName());
		purchaseOrderForm.setId(purchaseOrderForm.getId());
		purchaseOrderForm.setPoKey(purchaseOrderForm.getPoKey());
		purchaseOrderForm.setCustomerName(purchaseOrderForm.getCustomerName());
		purchaseOrderForm.setCustomerBranchEmail(purchaseOrderForm.getCustomerBranchEmail());
		purchaseOrderForm.setCustomerHeadEmail(purchaseOrderForm.getCustomerHeadEmail());
		purchaseOrderForm.setSupplierName(purchaseOrderForm.getSupplierName());
		purchaseOrderForm.setSupplierEmail(purchaseOrderForm.getSupplierEmail());
		purchaseOrderForm.setGoods(purchaseOrderForm.getGoods());
		purchaseOrderForm.setAmount(purchaseOrderForm.getAmount());
		purchaseOrderForm.setInterestType(purchaseOrderForm.getInterestType());
		purchaseOrderForm.setRateOfInt(purchaseOrderForm.getRateOfInt());
		purchaseOrderForm.setNoOfDays(purchaseOrderForm.getNoOfDays());
		purchaseOrderForm.setPlrRate(purchaseOrderForm.getPlrRate());
		purchaseOrderForm.setBasicPoints(purchaseOrderForm.getBasicPoints());
		purchaseOrderForm.setBasicAmt(purchaseOrderForm.getBasicAmt());
		purchaseOrderForm.setCalPlrRate(purchaseOrderForm.getCalPlrRate());
		purchaseOrderForm.setTotal1(purchaseOrderForm.getTotal1());
		purchaseOrderForm.setRateOfInt1(purchaseOrderForm.getRateOfInt1());
		purchaseOrderForm.setFlatCharges(purchaseOrderForm.getFlatCharges());
		purchaseOrderForm.setPercentAmt(purchaseOrderForm.getPercentAmt());
		purchaseOrderForm.setPercentage(purchaseOrderForm.getPercentage());
		purchaseOrderForm.setProcFee(purchaseOrderForm.getProcFee());
		purchaseOrderForm.setDocFee(purchaseOrderForm.getDocFee());
		purchaseOrderForm.setLateFee(purchaseOrderForm.getLateFee());
		purchaseOrderForm.setTaxName(purchaseOrderForm.getTaxName());
		purchaseOrderForm.setTaxPercentage(purchaseOrderForm.getTaxPercentage());
		purchaseOrderForm.setTaxAmt(purchaseOrderForm.getTaxAmt());
		purchaseOrderForm.setTaxName1(purchaseOrderForm.getTaxName1());
		purchaseOrderForm.setTaxPercentage1(purchaseOrderForm.getTaxPercentage1());
		purchaseOrderForm.setTaxAmt1(purchaseOrderForm.getTaxAmt1());
		purchaseOrderForm.setTaxPercentage2(purchaseOrderForm.getTaxPercentage2());
		purchaseOrderForm.setTaxAmt2(purchaseOrderForm.getTaxAmt2());
		purchaseOrderForm.setFunalAmt(purchaseOrderForm.getFunalAmt());
		purchaseOrderForm.setTenure(purchaseOrderForm.getTenure());
		purchaseOrderForm.setTransactionId(purchaseOrderForm.getTransactionId());

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("getRateListConfirm", "model", model);

	}

	@RequestMapping(value = "/getRateListPost", method = RequestMethod.POST)
	public ModelAndView getRateListPost(ModelMap model, @ModelAttribute PurchaseOrderForm purchaseOrderForm,
										RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(purchaseOrderForm.getId());

		purchase.setBankName(purchaseOrderForm.getBankName());
		purchase.setPoKey(purchaseOrderForm.getPoKey());
		purchase.setCustomerName(purchaseOrderForm.getCustomerName());
		purchase.setCustomerBranchEmail(purchaseOrderForm.getCustomerBranchEmail());
		purchase.setCustomerHeadEmail(purchaseOrderForm.getCustomerHeadEmail());
		purchase.setSupplierName(purchaseOrderForm.getSupplierName());
		purchase.setSupplierEmail(purchaseOrderForm.getSupplierEmail());
		purchase.setGoods(purchaseOrderForm.getGoods());
		purchase.setAmount(purchaseOrderForm.getAmount());
		purchase.setInterestType(purchaseOrderForm.getInterestType());
		purchase.setRateOfInt(purchaseOrderForm.getRateOfInt());
		purchase.setNoOfDays(purchaseOrderForm.getNoOfDays());
		purchase.setPlrRate(purchaseOrderForm.getPlrRate());
		purchase.setBasicPoints(purchaseOrderForm.getBasicPoints());
		purchase.setBasicAmt(purchaseOrderForm.getBasicAmt());
		purchase.setCalPlrRate(purchaseOrderForm.getCalPlrRate());
		purchase.setTotal1(purchaseOrderForm.getTotal1());
		purchase.setRateOfInt1(purchaseOrderForm.getRateOfInt1());
		purchase.setFlatCharges(purchaseOrderForm.getFlatCharges());
		purchase.setPercentAmt(purchaseOrderForm.getPercentAmt());
		purchase.setPercentage(purchaseOrderForm.getPercentage());
		purchase.setProcFee(purchaseOrderForm.getProcFee());
		purchase.setDocFee(purchaseOrderForm.getDocFee());
		purchase.setLateFee(purchaseOrderForm.getLateFee());
		purchase.setTaxName(purchaseOrderForm.getTaxName());
		purchase.setTaxPercentage(purchaseOrderForm.getTaxPercentage());
		purchase.setTaxAmt(purchaseOrderForm.getTaxAmt());
		purchase.setTaxName1(purchaseOrderForm.getTaxName1());
		purchase.setTaxPercentage1(purchaseOrderForm.getTaxPercentage1());
		purchase.setTaxAmt1(purchaseOrderForm.getTaxAmt1());
		purchase.setTaxPercentage2(purchaseOrderForm.getTaxPercentage2());
		purchase.setTaxAmt2(purchaseOrderForm.getTaxAmt2());
		purchase.setFunalAmt(purchaseOrderForm.getFunalAmt());
		purchase.setTenure(purchaseOrderForm.getTenure());
		purchase.setRequest("Yes");
		purchase.setrStatus("Pending");

		purchaseOrderDAO.updatePo(purchase);
		Transaction transaction = new Transaction();

		transaction.setTransactionId(purchaseOrderForm.getTransactionId());
		transaction.setTransactionType("Role");
		transaction.setTransactionStatus("Rates Submitted successfully");
		transactionDAO.insertTransaction(transaction);

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("getRateListTransaction", "model", model);

	}

	@RequestMapping(value = "/getRateListTransaction", method = RequestMethod.GET)
	public ModelAndView requestMoneyTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("getRateListTransaction", "model", model);

	}

	@RequestMapping(value = "/rateInvoiceList", method = RequestMethod.GET)
	public ModelAndView rateInvoiceList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<Invoice> invoiceList = invoiceDAO.getInvoiceListForRates().getResultList();

		model.put("user", user);

		if (invoiceList != null && invoiceList.size() > 0) {
			model.put("invoiceList", invoiceList);

			return new ModelAndView("rateInvoiceList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/getRateInvoiceList", method = RequestMethod.GET)
	public ModelAndView getRateInvoiceList(@RequestParam("id") Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Invoice invoice = invoiceDAO.getByInvoiceId(id);

		List<BankDetails> details = bankDetailsDAO.getBankDetails().getResultList();

		invoiceForm.setBanklist(details);
		invoiceForm.setId(invoice.getId());
		invoiceForm.setPoKey(invoice.getPoKey());
		invoiceForm.setCustomerName(invoice.getCustomerName());
		invoiceForm.setCustomerBranchEmail(invoice.getCustomerBranchEmail());
		invoiceForm.setCustomerHeadEmail(invoice.getCustomerHeadEmail());
		invoiceForm.setBuyerName(invoice.getBuyerName());
		invoiceForm.setBuyerEmail(invoice.getBuyerEmail());
		invoiceForm.setGoods(invoice.getGoods());
		invoiceForm.setAmount(invoice.getAmount());
		invoiceForm.setTransactionId(invoice.getTransactionId());

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("getRateInvoiceList", "model", model);

	}

	@RequestMapping(value = "/getRateInvoiceListConfirm", method = RequestMethod.POST)
	public ModelAndView getRateInvoiceListConfirm(ModelMap model, @ModelAttribute InvoiceForm invoiceForm,
												  RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		invoiceForm.setBankName(invoiceForm.getBankName());
		invoiceForm.setId(invoiceForm.getId());
		invoiceForm.setPoKey(invoiceForm.getPoKey());
		invoiceForm.setCustomerName(invoiceForm.getCustomerName());
		invoiceForm.setCustomerBranchEmail(invoiceForm.getCustomerBranchEmail());
		invoiceForm.setCustomerHeadEmail(invoiceForm.getCustomerHeadEmail());
		invoiceForm.setBuyerName(invoiceForm.getBuyerName());
		invoiceForm.setBuyerEmail(invoiceForm.getBuyerEmail());
		invoiceForm.setGoods(invoiceForm.getGoods());
		invoiceForm.setAmount(invoiceForm.getAmount());
		invoiceForm.setTransactionId(invoiceForm.getTransactionId());
		invoiceForm.setInterestType(invoiceForm.getInterestType());
		invoiceForm.setRateOfInt(invoiceForm.getRateOfInt());
		invoiceForm.setNoOfDays(invoiceForm.getNoOfDays());
		invoiceForm.setPlrRate(invoiceForm.getPlrRate());
		invoiceForm.setBasicPoints(invoiceForm.getBasicPoints());
		invoiceForm.setBasicAmt(invoiceForm.getBasicAmt());
		invoiceForm.setCalPlrRate(invoiceForm.getCalPlrRate());
		invoiceForm.setTotal1(invoiceForm.getTotal1());
		invoiceForm.setRateOfInt1(invoiceForm.getRateOfInt1());
		invoiceForm.setFlatCharges(invoiceForm.getFlatCharges());
		invoiceForm.setPercentAmt(invoiceForm.getPercentAmt());
		invoiceForm.setPercentage(invoiceForm.getPercentage());
		invoiceForm.setProcFee(invoiceForm.getProcFee());
		invoiceForm.setDocFee(invoiceForm.getDocFee());
		invoiceForm.setLateFee(invoiceForm.getLateFee());
		invoiceForm.setTaxName(invoiceForm.getTaxName());
		invoiceForm.setTaxPercentage(invoiceForm.getTaxPercentage());
		invoiceForm.setTaxAmt(invoiceForm.getTaxAmt());
		invoiceForm.setTaxName1(invoiceForm.getTaxName1());
		invoiceForm.setTaxPercentage1(invoiceForm.getTaxPercentage1());
		invoiceForm.setTaxAmt1(invoiceForm.getTaxAmt1());
		invoiceForm.setTaxPercentage2(invoiceForm.getTaxPercentage2());
		invoiceForm.setTaxAmt2(invoiceForm.getTaxAmt2());
		invoiceForm.setFunalAmt(invoiceForm.getFunalAmt());
		invoiceForm.setTenure(invoiceForm.getTenure());

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("getRateInvoiceListConfirm", "model", model);

	}

	@RequestMapping(value = "/getRateInvoiceListPost", method = RequestMethod.POST)
	public ModelAndView getRateInvoiceListPost(ModelMap model, @ModelAttribute InvoiceForm invoiceForm,
											   RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Invoice invoice = invoiceDAO.getByInvoiceId(invoiceForm.getId());

		invoice.setBankName(invoiceForm.getBankName());
		invoice.setPoKey(invoiceForm.getPoKey());
		invoice.setCustomerName(invoiceForm.getCustomerName());
		invoice.setCustomerBranchEmail(invoiceForm.getCustomerBranchEmail());
		invoice.setCustomerHeadEmail(invoiceForm.getCustomerHeadEmail());
		invoice.setBuyerName(invoiceForm.getBuyerName());
		invoice.setBuyerEmail(invoiceForm.getBuyerEmail());
		invoice.setGoods(invoiceForm.getGoods());
		invoice.setAmount(invoiceForm.getAmount());
		invoice.setTransactionId(invoiceForm.getTransactionId());
		invoice.setInterestType(invoiceForm.getInterestType());
		invoice.setRateOfInt(invoiceForm.getRateOfInt());
		invoice.setNoOfDays(invoiceForm.getNoOfDays());
		invoice.setPlrRate(invoiceForm.getPlrRate());
		invoice.setBasicPoints(invoiceForm.getBasicPoints());
		invoice.setBasicAmt(invoiceForm.getBasicAmt());
		invoice.setCalPlrRate(invoiceForm.getCalPlrRate());
		invoice.setTotal1(invoiceForm.getTotal1());
		invoice.setRateOfInt1(invoiceForm.getRateOfInt1());
		invoice.setFlatCharges(invoiceForm.getFlatCharges());
		invoice.setPercentAmt(invoiceForm.getPercentAmt());
		invoice.setPercentage(invoiceForm.getPercentage());
		invoice.setProcFee(invoiceForm.getProcFee());
		invoice.setDocFee(invoiceForm.getDocFee());
		invoice.setLateFee(invoiceForm.getLateFee());
		invoice.setTaxName(invoiceForm.getTaxName());
		invoice.setTaxPercentage(invoiceForm.getTaxPercentage());
		invoice.setTaxAmt(invoiceForm.getTaxAmt());
		invoice.setTaxName1(invoiceForm.getTaxName1());
		invoice.setTaxPercentage1(invoiceForm.getTaxPercentage1());
		invoice.setTaxAmt1(invoiceForm.getTaxAmt1());
		invoice.setTaxPercentage2(invoiceForm.getTaxPercentage2());
		invoice.setTaxAmt2(invoiceForm.getTaxAmt2());
		invoice.setFunalAmt(invoiceForm.getFunalAmt());
		invoice.setTenure(invoiceForm.getTenure());
		invoice.setRequest("Yes");
		invoice.setrStatus("Pending");

		invoiceDAO.updateInvoice(invoice);
		Transaction transaction = new Transaction();

		transaction.setTransactionId(invoiceForm.getTransactionId());
		transaction.setTransactionType("Rates");
		transaction.setTransactionStatus("Rates Submitted successfully");
		transactionDAO.insertTransaction(transaction);

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("getRateInvoiceListTransaction", "model", model);

	}

	@RequestMapping(value = "/getRateInvoiceListTransaction", method = RequestMethod.GET)
	public ModelAndView getRateInvoiceListTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("getRateInvoiceListTransaction", "model", model);

	}

	/* Transaction List */

	@RequestMapping(value = "/bankTransactionList", method = RequestMethod.GET)
	public ModelAndView getALLTransactionList(ModelMap model, HttpServletRequest request,
											  HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		Collection<Transaction> transactionPageList = transactionDAO.getList();

		model.put("user", user);
		if (transactionPageList != null && transactionPageList.size() > 0) {
			model.put("transactionPageList", transactionPageList);
			return new ModelAndView("bankTransactionList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/setLimitBurstRatesList", method = RequestMethod.GET)
	public ModelAndView setLimitBurstRatesList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<LimitBurst> limit = limitBurstDAO.getByBankStatus().getResultList();

		model.put("user", user);
		if (limit != null && limit.size() > 0) {
			model.put("limit", limit);

			return new ModelAndView("setLimitBurstRatesList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/limitBurstRatesAssign", method = RequestMethod.GET)
	public ModelAndView limitBurstRatesAssign(@RequestParam("id") Long id, ModelMap model,
											  RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		LimitBurst limit = limitBurstDAO.getByLimitBurstId(id);

		limitBurstForm.setId(limit.getId());
		limitBurstForm.setReqAmt(limit.getReqAmt());
		limitBurstForm.setTenure(limit.getTenure());
		limitBurstForm.setTransactionId(limit.getTransactionId());

		if (limit.getFinalAmt() != null) {
			model.put("user", user);
			attributes.addFlashAttribute("success", "Limit Burst Assignment is already Done.");
			return new ModelAndView("redirect:setLimitBurstRatesList");
		} else {

			model.put("user", user);
			model.put("limitBurstForm", limitBurstForm);
			return new ModelAndView("limitBurstRatesAssign", "model", model);
		}
	}

	@RequestMapping(value = "/limitBurstRatesAssignConfirm", method = RequestMethod.POST)
	public ModelAndView limitBurstRatesAssignConfirm(ModelMap model, @ModelAttribute LimitBurstForm limitBurstForm,
													 RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		limitBurstForm.setId(limitBurstForm.getId());
		limitBurstForm.setReqAmt(limitBurstForm.getReqAmt());
		limitBurstForm.setTenure(limitBurstForm.getTenure());
		limitBurstForm.setIntRate(limitBurstForm.getIntRate());
		limitBurstForm.setFinalAmt(limitBurstForm.getFinalAmt());
		limitBurstForm.setTotalRate(limitBurstForm.getTotalRate());
		limitBurstForm.setTransactionId(limitBurstForm.getTransactionId());

		model.put("user", user);
		model.put("limitBurstForm", limitBurstForm);

		return new ModelAndView("limitBurstRatesAssignConfirm", "model", model);

	}

	@RequestMapping(value = "/limitBurstRatesAssignPost", method = RequestMethod.POST)
	public ModelAndView limitBurstRatesAssignPost(ModelMap model, @ModelAttribute LimitBurstForm limitBurstForm,
												  RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		limitBurstForm.setTransactionId(limitBurstForm.getTransactionId());

		LimitBurst limit = limitBurstDAO.getByLimitBurstId(limitBurstForm.getId());

		limit.setFinalAmt(limitBurstForm.getFinalAmt());
		limit.setIntRate(limitBurstForm.getIntRate());
		limit.setTotalRate(limitBurstForm.getTotalRate());

		limitBurstDAO.updateLimitBurst(limit);

		Transaction trans = new Transaction();
		trans.setTransactionId(limitBurstForm.getTransactionId());
		trans.setTransactionStatus("Limit Burst Assignment");
		trans.setTransactionType("Submitted Successfully");

		transactionDAO.insertTransaction(trans);

		model.put("user", user);
		model.put("limitBurstForm", limitBurstForm);

		return new ModelAndView("limitBurstRatesAssignTransaction", "model", model);

	}

	@RequestMapping(value = "/limitBurstRatesAssignTransaction", method = RequestMethod.GET)
	public ModelAndView limitBurstRatesAssignTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("limitBurstForm", limitBurstForm);

		return new ModelAndView("limitBurstRatesAssignTransaction", "model", model);

	}

	@RequestMapping(value = "/limitBurstSend", method = RequestMethod.GET)
	public String limitBurstSend(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		LimitBurst limit = limitBurstDAO.getByLimitBurstId(id);

		if (limit.getFinalAmt() != null) {
			if (limit != null) {
				limit.setaStatus("Pending");
				limit.setbStatus("Approved");
				limitBurstDAO.updateLimitBurst(limit);
			}
			attributes.addFlashAttribute("success", "Sent for Approval");
			model.put("user", user);
		} else {
			attributes.addFlashAttribute("success", "Limit  Assignment is Mandatory");
		}
		model.put("user", user);
		return "redirect:setLimitBurstRatesList";
	}

	@RequestMapping(value = "/limitBurstReject", method = RequestMethod.GET)
	public String limitBurstReject(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		LimitBurst limit = limitBurstDAO.getByLimitBurstId(id);

		if (limit != null) {
			limit.setbStatus("Rejected");
			limitBurstDAO.updateLimitBurst(limit);
		}
		attributes.addFlashAttribute("success", "Rejected Successfully");
		model.put("user", user);

		return "redirect:setLimitBurstRatesList";
	}

	@RequestMapping(value = "/limitBurstBankList", method = RequestMethod.GET)
	public ModelAndView limitBurstBankList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<LimitBurst> limit = limitBurstDAO.getFullList().getResultList();

		model.put("user", user);
		if (limit != null && limit.size() > 0) {
			model.put("limit", limit);

			return new ModelAndView("limitBurstBankList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/masterPlanRePaymentBank", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentBank(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Repayment> repayList = repaymenyDAO.getRepayByIdAndStatusBank().getResultList();

		if (repayList != null && repayList.size() > 0) {
			model.put("repayList", repayList);
			return new ModelAndView("masterPlanRePaymentBank", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/masterPlanRePaymentBankSet", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentAppApprove(@RequestParam Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		Repayment master = repaymenyDAO.getByRepaymentId(id);

		repaymentForm.setId(master.getId());
		repaymentForm.setMasterKey(master.getMasterKey());
		repaymentForm.setBuyingCostSanc(master.getBuyingCostSanc());
		repaymentForm.setWcSancAmount(master.getWcSancAmount());
		repaymentForm.setWcTotalInterest(master.getWcTotalInterest());
		repaymentForm.setCustomer(master.getCustomer());
		repaymentForm.setCustomerEmail(master.getCustomerEmail());
		repaymentForm.setBuyingCostDate(master.getBuyingCostDate());
		repaymentForm.setTenure(master.getTenure());
		repaymentForm.setRateOfInt1(master.getRateOfInt1());
		repaymentForm.setAmtType(master.getAmtType());
		repaymentForm.setPayOption(master.getPayOption());
		repaymentForm.setTransactionId(master.getTransactionId());
		repaymentForm.setFunalAmt(master.getFunalAmt());

		repaymentForm.setWcTenure(master.getWcTenure());
		if(master.getAmtType().equals("Working Capital")){
			repaymentForm.setTotalAmt(master.getWcSancAmount()+(master.getWcTotalInterest()==null?0:master.getWcTotalInterest()));

		}else{
			repaymentForm.setTotalAmt(master.getFunalAmt()+(master.getRateOfInt1()==null?0:master.getRateOfInt1()));
		}
//		repaymentForm.setTotalAmt(master.getFunalAmt()+master.getWcSancAmount()+(master.getWcTotalInterest()==null?0:master.getWcTotalInterest())+(master.getRateOfInt1()==null?0:master.getRateOfInt1()));
		repaymentForm.setOtherCharges(master.getFunalAmt()-master.getBuyingCostSanc()-master.getRateOfInt1());


		model.put("repaymentForm", repaymentForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentBankSet", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentFull", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentSet(@RequestParam Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		Repayment master = repaymenyDAO.getByRepaymentId(id);

		quarterlyForm.setId(master.getId());
		quarterlyForm.setMasterKey(master.getMasterKey());
		quarterlyForm.setCustomer(master.getCustomer());
		quarterlyForm.setCustomerEmail(master.getCustomerEmail());
		quarterlyForm.setTenure(master.getTenure());
		quarterlyForm.setPayOption(master.getPayOption());
		quarterlyForm.setAmtType(master.getAmtType());
		quarterlyForm.setTransactionId(master.getTransactionId());


		MasterPlan masterPlan = masterPlanDAO.getMasterPlanByMasterKey(master.getMasterKey()).getSingleResult();

		Float totalAmount;
		int numberOfEmi = 0;
		ArrayList<String> loanDates = new ArrayList<String>();
		if (master.getAmtType().equals("Working Capital")) {
			totalAmount = (master.getWcSancAmount() + (master.getWcTotalInterest() == null ? 0 : master.getWcTotalInterest()));
			int tempTenure = masterPlan.getWcTenure();

			Date lastDate = master.getWcDate();
			int diff = 0;
			while (tempTenure > 0) {
				numberOfEmi++;
				Date newDate = DateService.addMonths(lastDate, 3);
				diff = DateService.getDaysBetweenTwoDates(lastDate, newDate);
				if (diff <= tempTenure) {
					tempTenure = tempTenure - diff;
					loanDates.add(newDate.toString());
					lastDate = newDate;
				} else {
					Date newDate1 = DateService.addDays(lastDate, tempTenure);
					tempTenure = tempTenure - diff;
					loanDates.add(newDate1.toString());
				}
			}
		} else {
			totalAmount = (master.getFunalAmt() + (master.getRateOfInt1() == null ? 0 : master.getRateOfInt1()));
			int tempTenure = masterPlan.getTenure();

//			ArrayList<String> loanDates = new ArrayList<String>();
			Date lastDate = master.getBuyingCostDate();
			int diff = 0;
			while (tempTenure > 0) {
				numberOfEmi++;
				Date newDate = DateService.addMonths(lastDate, 3);
				diff = DateService.getDaysBetweenTwoDates(lastDate, newDate);
				if (diff <= tempTenure) {
					tempTenure = tempTenure - diff;
					loanDates.add(newDate.toString());
					lastDate = newDate;
				} else {
					Date newDate1 = DateService.addDays(lastDate, tempTenure);
					tempTenure = tempTenure - diff;
					loanDates.add(newDate1.toString());
				}
			}
		}
		quarterlyForm.setTotalAmount(totalAmount);
		quarterlyForm.setAmount(totalAmount / numberOfEmi);
		quarterlyForm.setLoanDates(loanDates);


//		Float totalAmount = master.getFunalAmt() + master.getWcSancAmount() + ((master.getWcTotalInterest() == null) ? 0 : master.getWcTotalInterest());


//Float interest= masterPlan.getin


//		quarterlyForm.setAmount(totalAmount/4);
//		quarterlyForm.setLoanDate(DateService.addMonths(master.getBuyingCostDate(),3));
//		quarterlyForm.setPayDate(DateService.getDaysBetweenTwoDates(master.getBuyingCostDate(),quarterlyForm.getLoanDate()));
//
//		quarterlyForm.setAmount1(totalAmount/4);
//		quarterlyForm.setLoanDate1(DateService.addMonths(master.getBuyingCostDate(),6));
//		quarterlyForm.setPayDate1(DateService.getDaysBetweenTwoDates(quarterlyForm.getLoanDate(),quarterlyForm.getLoanDate1()));
//
//		quarterlyForm.setAmount2(totalAmount/4);
//		quarterlyForm.setLoanDate2(DateService.addMonths(master.getBuyingCostDate(),9));
//		quarterlyForm.setPayDat2(DateService.getDaysBetweenTwoDates(quarterlyForm.getLoanDate1(),quarterlyForm.getLoanDate2()));
//
//		quarterlyForm.setAmount3(totalAmount/4);
//		quarterlyForm.setLoanDate3(DateService.addMonths(master.getBuyingCostDate(),12));
//		quarterlyForm.setPayDate3(DateService.getDaysBetweenTwoDates(quarterlyForm.getLoanDate2(),quarterlyForm.getLoanDate3()));



		model.put("quarterlyForm", quarterlyForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentFull", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentDisplayBankConfirm", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentDisplayConfirm(ModelMap model, @ModelAttribute QuarterlyForm quarterlyForm,
														  BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		quarterlyForm.setId(quarterlyForm.getId());
		quarterlyForm.setMasterKey(quarterlyForm.getMasterKey());
		quarterlyForm.setCustomer(quarterlyForm.getCustomer());
		quarterlyForm.setCustomerEmail(quarterlyForm.getCustomerEmail());
		quarterlyForm.setTenure(quarterlyForm.getTenure());
		quarterlyForm.setPayOption(quarterlyForm.getPayOption());
		quarterlyForm.setAmtType(quarterlyForm.getAmtType());
		quarterlyForm.setTransactionId(quarterlyForm.getTransactionId());
		quarterlyForm.setAmount(quarterlyForm.getAmount());
//		quarterlyForm.setIntRate(quarterlyForm.getIntRate());
//		quarterlyForm.setPayDate(quarterlyForm.getPayDate());
		quarterlyForm.setTotalAmount(quarterlyForm.getTotalAmount());
//		quarterlyForm.setLoanDate(quarterlyForm.getLoanDate());
//		quarterlyForm.setAmount1(quarterlyForm.getAmount1());
//		quarterlyForm.setIntRate1(quarterlyForm.getIntRate1());
//		quarterlyForm.setPayDate1(quarterlyForm.getPayDate1());
//		quarterlyForm.setTotalAmount1(quarterlyForm.getTotalAmount1());
//		quarterlyForm.setLoanDate1(quarterlyForm.getLoanDate1());
//		quarterlyForm.setAmount2(quarterlyForm.getAmount2());
//		quarterlyForm.setIntRate2(quarterlyForm.getIntRate2());
//		quarterlyForm.setPayDat2(quarterlyForm.getPayDat2());
//		quarterlyForm.setTotalAmount2(quarterlyForm.getTotalAmount2());
//		quarterlyForm.setLoanDate2(quarterlyForm.getLoanDate2());
//		quarterlyForm.setAmount3(quarterlyForm.getAmount3());
//		quarterlyForm.setIntRate3(quarterlyForm.getIntRate3());
//		quarterlyForm.setPayDate3(quarterlyForm.getPayDate3());
//		quarterlyForm.setTotalAmount3(quarterlyForm.getTotalAmount3());
//		quarterlyForm.setLoanDate3(quarterlyForm.getLoanDate3());

		model.put("quarterlyForm", quarterlyForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentDisplayBankConfirm", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentSave", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentSave(ModelMap model, @ModelAttribute QuarterlyForm quarterlyForm,
												BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		Repayment master = repaymenyDAO.getByRepaymentId(quarterlyForm.getId());
		master.setStatus("Pending");
		master.setbStatus("Approved");

		repaymenyDAO.updateRepayment(master);

		Quarterly quarter = new Quarterly();
		List<Quarterly> full = quarterlyDAO.getByTransIdList(quarterlyForm.getTransactionId()).getResultList();

		StringBuilder stringBuilder = new StringBuilder();
		for (String s : quarterlyForm.getLoanDates()) {
			if (stringBuilder.length() > 0) {
				stringBuilder.append(", ");
			}
			stringBuilder.append(s);
		}
		String dates = stringBuilder.toString();

		if (full != null && full.size() > 0) {

			full.get(0).setMasterKey(quarterlyForm.getMasterKey());
			full.get(0).setCustomer(quarterlyForm.getCustomer());
			full.get(0).setCustomerEmail(quarterlyForm.getCustomerEmail());
			full.get(0).setTenure(quarterlyForm.getTenure());
			full.get(0).setPayOption(quarterlyForm.getPayOption());
			full.get(0).setTransactionId(quarterlyForm.getTransactionId());
			full.get(0).setAmount(quarterlyForm.getAmount());
//			full.get(0).setIntRate(quarterlyForm.getIntRate());

//			full.get(0).setPayDate(quarterlyForm.getPayDate());
			full.get(0).setTotalAmount(quarterlyForm.getTotalAmount());
//			full.get(0).setLoanDate(quarterlyForm.getLoanDate());
			full.get(0).setLoanDates(dates);
			full.get(0).setTransactionId(quarterlyForm.getTransactionId());
//			full.get(0).setAmount1(quarterlyForm.getAmount1());
//			full.get(0).setIntRate1(quarterlyForm.getIntRate1());
//			full.get(0).setPayDate1(quarterlyForm.getPayDate1());
//			full.get(0).setTotalAmount1(quarterlyForm.getTotalAmount1());
//			full.get(0).setLoanDate1(quarterlyForm.getLoanDate1());
//			full.get(0).setAmount2(quarterlyForm.getAmount2());
//			full.get(0).setIntRate2(quarterlyForm.getIntRate2());
//			full.get(0).setPayDat2(quarterlyForm.getPayDat2());
//			full.get(0).setTotalAmount2(quarterlyForm.getTotalAmount2());
//			full.get(0).setLoanDate2(quarterlyForm.getLoanDate2());
//			full.get(0).setAmount3(quarterlyForm.getAmount3());
//			full.get(0).setIntRate3(quarterlyForm.getIntRate3());
//			full.get(0).setPayDate3(quarterlyForm.getPayDate3());
//			full.get(0).setTotalAmount3(quarterlyForm.getTotalAmount3());
//			full.get(0).setLoanDate3(quarterlyForm.getLoanDate3());
			full.get(0).setStatus("Pending");
			quarterlyDAO.updateQuarterly(full.get(0));
		} else {

			quarter.setMasterKey(quarterlyForm.getMasterKey());
			quarter.setCustomer(quarterlyForm.getCustomer());
			quarter.setCustomerEmail(quarterlyForm.getCustomerEmail());
			quarter.setTenure(quarterlyForm.getTenure());
			quarter.setPayOption(quarterlyForm.getPayOption());
			quarter.setTransactionId(quarterlyForm.getTransactionId());
			quarter.setAmount(quarterlyForm.getAmount());
//			quarter.setIntRate(quarterlyForm.getIntRate());
//			quarter.setPayDate(quarterlyForm.getPayDate());
			quarter.setTotalAmount(quarterlyForm.getTotalAmount());
//			quarter.setLoanDate(quarterlyForm.getLoanDate());
			quarter.setLoanDates(dates);
			quarter.setTransactionId(quarterlyForm.getTransactionId());
//			quarter.setAmount1(quarterlyForm.getAmount1());
//			quarter.setIntRate1(quarterlyForm.getIntRate1());
//			quarter.setPayDate1(quarterlyForm.getPayDate1());
//			quarter.setTotalAmount1(quarterlyForm.getTotalAmount1());
//			quarter.setLoanDate1(quarterlyForm.getLoanDate1());
//			quarter.setAmount2(quarterlyForm.getAmount2());
//			quarter.setIntRate2(quarterlyForm.getIntRate2());
//			quarter.setPayDat2(quarterlyForm.getPayDat2());
//			quarter.setTotalAmount2(quarterlyForm.getTotalAmount2());
//			quarter.setLoanDate2(quarterlyForm.getLoanDate2());
//			quarter.setAmount3(quarterlyForm.getAmount3());
//			quarter.setIntRate3(quarterlyForm.getIntRate3());
//			quarter.setPayDate3(quarterlyForm.getPayDate3());
//			quarter.setTotalAmount3(quarterlyForm.getTotalAmount3());
//			quarter.setLoanDate3(quarterlyForm.getLoanDate3());
			quarter.setStatus("Pending");

			quarterlyDAO.updateQuarterly(quarter);
		}
		Transaction trans = new Transaction();

		trans.setTransactionId(quarterlyForm.getTransactionId());
		trans.setTransactionStatus("Repayment Dates Quarterly");
		trans.setTransactionType("Sent Successfully");

		transactionDAO.insertTransaction(trans);

		model.put("quarterlyForm", quarterlyForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentBankTransaction", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentBankTransaction", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("quarterlyForm", quarterlyForm);

		return new ModelAndView("masterPlanRePaymentBankTransaction", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentQuater", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentQuater(@RequestParam Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		Repayment master = repaymenyDAO.getByRepaymentId(id);

		fullAmountForm.setId(master.getId());
		fullAmountForm.setMasterKey(master.getMasterKey());
		fullAmountForm.setCustomer(master.getCustomer());
		fullAmountForm.setCustomerEmail(master.getCustomerEmail());
		fullAmountForm.setTenure(master.getTenure());
		fullAmountForm.setPayOption(master.getPayOption());
		fullAmountForm.setAmtType(master.getAmtType());
		fullAmountForm.setTransactionId(master.getTransactionId());

		MasterPlan masterPlan = masterPlanDAO.getMasterPlanByMasterKey(master.getMasterKey()).getSingleResult();
		Float totalAmount;

		Date loanDate;
		if (master.getAmtType().equals("Working Capital")) {
			totalAmount = (master.getWcSancAmount() + (master.getWcTotalInterest() == null ? 0 : master.getWcTotalInterest()));
			int tempTenure = masterPlan.getWcTenure();
			Date lastDate = master.getWcDate();
			loanDate = DateService.addDays(lastDate,tempTenure);
		} else {
			totalAmount = (master.getFunalAmt() + (master.getRateOfInt1() == null ? 0 : master.getRateOfInt1()));
			int tempTenure = masterPlan.getTenure();
			Date lastDate = master.getBuyingCostDate();
			loanDate = DateService.addDays(lastDate,tempTenure);
		}
		fullAmountForm.setTotalAmount(totalAmount);
		fullAmountForm.setLoanDate(loanDate);

		model.put("fullAmountForm", fullAmountForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentQuater", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentDisplayFullConfirm", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentDisplayFullConfirm(ModelMap model,
															  @ModelAttribute FullAmountForm fullAmountForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		fullAmountForm.setId(fullAmountForm.getId());
		fullAmountForm.setMasterKey(fullAmountForm.getMasterKey());
		fullAmountForm.setCustomer(fullAmountForm.getCustomer());
		fullAmountForm.setCustomerEmail(fullAmountForm.getCustomerEmail());
		fullAmountForm.setTenure(fullAmountForm.getTenure());
		fullAmountForm.setPayOption(fullAmountForm.getPayOption());
		fullAmountForm.setAmtType(fullAmountForm.getAmtType());
		fullAmountForm.setTransactionId(fullAmountForm.getTransactionId());
		fullAmountForm.setAmount(fullAmountForm.getAmount());
		fullAmountForm.setIntRate(fullAmountForm.getIntRate());
		fullAmountForm.setPayDate(fullAmountForm.getPayDate());
		fullAmountForm.setTotalAmount(fullAmountForm.getTotalAmount());
		fullAmountForm.setLoanDate(fullAmountForm.getLoanDate());

		model.put("fullAmountForm", fullAmountForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentDisplayFullConfirm", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentQuarterSave", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentQuarterSave(ModelMap model, @ModelAttribute FullAmountForm fullAmountForm,
													   BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		Repayment master = repaymenyDAO.getByRepaymentId(fullAmountForm.getId());
		master.setStatus("Pending");
		master.setbStatus("Approved");

		repaymenyDAO.updateRepayment(master);

		FullAmount quarter = new FullAmount();
		List<FullAmount> full1 = fullAmountDAO.getByTransIdList(fullAmountForm.getTransactionId()).getResultList();
		if (full1 != null && full1.size() > 0) {
			full1.get(0).setStatus(repaymentForm.getStatus());
			full1.get(0).setMasterKey(fullAmountForm.getMasterKey());
			full1.get(0).setCustomer(fullAmountForm.getCustomer());
			full1.get(0).setCustomerEmail(fullAmountForm.getCustomerEmail());
			full1.get(0).setTenure(fullAmountForm.getTenure());
			full1.get(0).setPayOption(fullAmountForm.getPayOption());
			full1.get(0).setTransactionId(fullAmountForm.getTransactionId());
			full1.get(0).setAmount(fullAmountForm.getAmount());
			full1.get(0).setIntRate(fullAmountForm.getIntRate());
			full1.get(0).setPayDate(fullAmountForm.getPayDate());
			full1.get(0).setTotalAmount(fullAmountForm.getTotalAmount());
			full1.get(0).setLoanDate(fullAmountForm.getLoanDate());
			full1.get(0).setTransactionId(fullAmountForm.getTransactionId());
			full1.get(0).setStatus("Pending");
			fullAmountDAO.updateFullAmount(full1.get(0));
		} else {
			quarter.setMasterKey(fullAmountForm.getMasterKey());
			quarter.setCustomer(fullAmountForm.getCustomer());
			quarter.setCustomerEmail(fullAmountForm.getCustomerEmail());
			quarter.setTenure(fullAmountForm.getTenure());
			quarter.setPayOption(fullAmountForm.getPayOption());
			quarter.setTransactionId(fullAmountForm.getTransactionId());
			quarter.setAmount(fullAmountForm.getAmount());
			quarter.setIntRate(fullAmountForm.getIntRate());
			quarter.setPayDate(fullAmountForm.getPayDate());
			quarter.setTotalAmount(fullAmountForm.getTotalAmount());
			quarter.setLoanDate(fullAmountForm.getLoanDate());
			quarter.setTransactionId(fullAmountForm.getTransactionId());
			quarter.setStatus("Pending");

			fullAmountDAO.updateFullAmount(quarter);
		}
		Transaction trans = new Transaction();

		trans.setTransactionId(fullAmountForm.getTransactionId());
		trans.setTransactionStatus("Repayment Rates Set");
		trans.setTransactionType("Sent Successfully");

		transactionDAO.insertTransaction(trans);

		model.put("fullAmountForm", fullAmountForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentQuarterTransaction", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentQuarterTransaction", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentQuarterTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("fullAmountForm", fullAmountForm);

		return new ModelAndView("masterPlanRePaymentQuarterTransaction", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentHalf", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentHalf(@RequestParam Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		Repayment master = repaymenyDAO.getByRepaymentId(id);

		halfYearlyForm.setId(master.getId());
		halfYearlyForm.setMasterKey(master.getMasterKey());
		halfYearlyForm.setCustomer(master.getCustomer());
		halfYearlyForm.setCustomerEmail(master.getCustomerEmail());
		halfYearlyForm.setTenure(master.getTenure());
		halfYearlyForm.setPayOption(master.getPayOption());
		halfYearlyForm.setAmtType(master.getAmtType());
		halfYearlyForm.setTransactionId(master.getTransactionId());

		MasterPlan masterPlan = masterPlanDAO.getMasterPlanByMasterKey(master.getMasterKey()).getSingleResult();

		Float totalAmount;
		int numberOfEmi = 0;
		ArrayList<String> loanDates = new ArrayList<String>();
		if (master.getAmtType().equals("Working Capital")) {
			totalAmount = (master.getWcSancAmount() + (master.getWcTotalInterest() == null ? 0 : master.getWcTotalInterest()));
			int tempTenure = masterPlan.getWcTenure();

			Date lastDate = master.getWcDate();
			int diff = 0;
			while (tempTenure > 0) {
				numberOfEmi++;
				Date newDate = DateService.addMonths(lastDate, 6);
				diff = DateService.getDaysBetweenTwoDates(lastDate, newDate);
				if (diff <= tempTenure) {
					tempTenure = tempTenure - diff;
					loanDates.add(newDate.toString());
					lastDate = newDate;
				} else {
					Date newDate1 = DateService.addDays(lastDate, tempTenure);
					tempTenure = tempTenure - diff;
					loanDates.add(newDate1.toString());
				}
			}
		} else {
			totalAmount = (master.getFunalAmt() + (master.getRateOfInt1() == null ? 0 : master.getRateOfInt1()));
			int tempTenure = masterPlan.getTenure();

//			ArrayList<String> loanDates = new ArrayList<String>();
			Date lastDate = master.getBuyingCostDate();
			int diff = 0;
			while (tempTenure > 0) {
				numberOfEmi++;
				Date newDate = DateService.addMonths(lastDate, 6);
				diff = DateService.getDaysBetweenTwoDates(lastDate, newDate);
				if (diff <= tempTenure) {
					tempTenure = tempTenure - diff;
					loanDates.add(newDate.toString());
					lastDate = newDate;
				} else {
					Date newDate1 = DateService.addDays(lastDate, tempTenure);
					tempTenure = tempTenure - diff;
					loanDates.add(newDate1.toString());
				}
			}
		}
		halfYearlyForm.setTotalAmount(totalAmount);
		halfYearlyForm.setAmount(totalAmount / numberOfEmi);
		halfYearlyForm.setLoanDates(loanDates);

		model.put("halfYearlyForm", halfYearlyForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentHalf", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentDisplayHalfConfirm", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentDisplayHalfConfirm(ModelMap model,
															  @ModelAttribute HalfYearlyForm halfYearlyForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		halfYearlyForm.setId(halfYearlyForm.getId());
		halfYearlyForm.setMasterKey(halfYearlyForm.getMasterKey());
		halfYearlyForm.setCustomer(halfYearlyForm.getCustomer());
		halfYearlyForm.setCustomerEmail(halfYearlyForm.getCustomerEmail());
		halfYearlyForm.setTenure(halfYearlyForm.getTenure());
		halfYearlyForm.setPayOption(halfYearlyForm.getPayOption());
		halfYearlyForm.setAmtType(halfYearlyForm.getAmtType());
		halfYearlyForm.setTransactionId(halfYearlyForm.getTransactionId());
		halfYearlyForm.setAmount(halfYearlyForm.getAmount());
		halfYearlyForm.setIntRate(halfYearlyForm.getIntRate());
		halfYearlyForm.setPayDate(halfYearlyForm.getPayDate());
		halfYearlyForm.setTotalAmount(halfYearlyForm.getTotalAmount());
		halfYearlyForm.setLoanDate(halfYearlyForm.getLoanDate());

		model.put("halfYearlyForm", halfYearlyForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentDisplayHalfConfirm", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentHalfSave", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentHalfSave(ModelMap model, @ModelAttribute HalfYearlyForm halfYearlyForm,
													BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		Repayment master = repaymenyDAO.getByRepaymentId(halfYearlyForm.getId());
		master.setStatus("Pending");
		master.setbStatus("Approved");

		repaymenyDAO.updateRepayment(master);

		HalfYearly quarter = new HalfYearly();
		List<HalfYearly> full2 = halfYearlyDAO.getByTransIdList(repaymentForm.getTransactionId()).getResultList();
		StringBuilder stringBuilder = new StringBuilder();
		for (String s : halfYearlyForm.getLoanDates()) {
			if (stringBuilder.length() > 0) {
				stringBuilder.append(", ");
			}
			stringBuilder.append(s);
		}
		String dates = stringBuilder.toString();

		if (full2 != null && full2.size() > 0) {
			full2.get(0).setStatus(repaymentForm.getStatus());
			full2.get(0).setMasterKey(halfYearlyForm.getMasterKey());
			full2.get(0).setCustomer(halfYearlyForm.getCustomer());
			full2.get(0).setCustomerEmail(halfYearlyForm.getCustomerEmail());
			full2.get(0).setTenure(halfYearlyForm.getTenure());
			full2.get(0).setPayOption(halfYearlyForm.getPayOption());
			full2.get(0).setTransactionId(halfYearlyForm.getTransactionId());
			full2.get(0).setAmount(halfYearlyForm.getAmount());
//			full2.get(0).setIntRate(halfYearlyForm.getIntRate());
//			full2.get(0).setPayDate(halfYearlyForm.getPayDate());
			full2.get(0).setTotalAmount(halfYearlyForm.getTotalAmount());
			full2.get(0).setLoanDates(dates);
//			full2.get(0).setLoanDate(halfYearlyForm.getLoanDate());
			full2.get(0).setTransactionId(halfYearlyForm.getTransactionId());
			full2.get(0).setStatus("Pending");
			halfYearlyDAO.updateHalfYearly(full2.get(0));
		} else {
			quarter.setMasterKey(halfYearlyForm.getMasterKey());
			quarter.setCustomer(halfYearlyForm.getCustomer());
			quarter.setCustomerEmail(halfYearlyForm.getCustomerEmail());
			quarter.setTenure(halfYearlyForm.getTenure());
			quarter.setPayOption(halfYearlyForm.getPayOption());
			quarter.setTransactionId(halfYearlyForm.getTransactionId());
			quarter.setAmount(halfYearlyForm.getAmount());
//			quarter.setIntRate(halfYearlyForm.getIntRate());
//			quarter.setPayDate(halfYearlyForm.getPayDate());
			quarter.setTotalAmount(halfYearlyForm.getTotalAmount());
			quarter.setLoanDates(dates);
//			quarter.setLoanDate(halfYearlyForm.getLoanDate());
			quarter.setTransactionId(halfYearlyForm.getTransactionId());
			quarter.setStatus("Pending");

			halfYearlyDAO.updateHalfYearly(quarter);
		}
		Transaction trans = new Transaction();

		trans.setTransactionId(halfYearlyForm.getTransactionId());
		trans.setTransactionStatus("Repayment Dates Set");
		trans.setTransactionType("Sent Successfully");

		transactionDAO.insertTransaction(trans);

		model.put("halfYearlyForm", halfYearlyForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentHalfTransaction", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentHalfTransaction", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentHalfTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("halfYearlyForm", halfYearlyForm);

		return new ModelAndView("masterPlanRePaymentHalfTransaction", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentReject", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentReject(ModelMap model, @RequestParam Long id,
												  RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Repayment repay = repaymenyDAO.getByRepaymentId(id);

		repay.setbStatus("Rejected");
		repaymenyDAO.updateRepayment(repay);

		attributes.addFlashAttribute("success", "Rejected Successfully");

		model.put("user", user);
		model.put("halfYearlyForm", halfYearlyForm);

		return new ModelAndView("redirect:masterPlanRePaymentBank");

	}

	@RequestMapping(value = "/masterPlanRePaymentMadeList", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentMadeList(ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Repayment> repay = repaymenyDAO.getRepayByAccept().getResultList();
		if (repay != null && repay.size() > 0) {
			model.put("repay", repay);
			return new ModelAndView("masterPlanRePaymentMadeList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/masterPlanRePaymentMadeStatus", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentMadeStatus(@RequestParam("id") Long id, ModelMap model) {

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

		return new ModelAndView("masterPlanRePaymentMadeStatus", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentMadeStatusConfirm", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentMadeStatusConfirm(ModelMap model,
															 @ModelAttribute RepaymentForm repaymentForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		repaymentForm.setId(repaymentForm.getId());
		repaymentForm.setMasterKey(repaymentForm.getMasterKey());
		repaymentForm.setCustomer(repaymentForm.getCustomer());
		repaymentForm.setCustomerEmail(repaymentForm.getCustomerEmail());
		repaymentForm.setTenure(repaymentForm.getTenure());
		repaymentForm.setPayOption(repaymentForm.getPayOption());
		repaymentForm.setAmtType(repaymentForm.getAmtType());
		repaymentForm.setTransactionId(repaymentForm.getTransactionId());
		repaymentForm.setAmountPaid(repaymentForm.getAmountPaid());

		model.put("repaymentForm", repaymentForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentMadeStatusConfirm", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentMadeStatusPost", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentMadeStatusPost(ModelMap model, @ModelAttribute RepaymentForm repaymentForm,
														  BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		Repayment repay = repaymenyDAO.getByRepaymentId(repaymentForm.getId());
		repay.setAmountPaid(repaymentForm.getAmountPaid());
		repay.setPayConfirm("Paid");
		repay.setMoneyStatus("Approved");
		repaymenyDAO.updateRepayment(repay);

		MasterPlan plan = masterPlanDAO.getMasterPlanByMasterKey(repaymentForm.getMasterKey()).getSingleResult();
		if (plan != null) {
			Float amt = plan.getAmountPaid();
			Float total = amt + repaymentForm.getAmountPaid();

			plan.setAmountPaid(total);
			masterPlanDAO.updatePlan(plan);
		}

		Transaction trans = new Transaction();

		trans.setTransactionId(repaymentForm.getTransactionId());
		trans.setTransactionStatus("Repayment Made");
		trans.setTransactionType("Sent Successfully");
		transactionDAO.insertTransaction(trans);

		model.put("repaymentForm", repaymentForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentMadeStatusTrans", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentMadeStatusTrans", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentMadeStatusTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("repaymentForm", repaymentForm);

		return new ModelAndView("masterPlanRePaymentMadeStatusTrans", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentBankQuarterly", method = RequestMethod.GET)
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

				return new ModelAndView("redirect:masterPlanRePaymentMadeList");

			}
		}
		model.put("user", user);
		return new ModelAndView("masterPlanRePaymentBankQuarterly", "model", model);
	}

	@RequestMapping(value = "/masterPlanRePaymentBankFullList", method = RequestMethod.GET)
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

				return new ModelAndView("redirect:masterPlanRePaymentMadeList");

			}
		}
		model.put("user", user);
		return new ModelAndView("masterPlanRePaymentBankFullList", "model", model);
	}

	@RequestMapping(value = "/masterPlanRePaymentBankHalfYearly", method = RequestMethod.GET)
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

				return new ModelAndView("redirect:masterPlanRePaymentMadeList");

			}
		}
		model.put("user", user);
		return new ModelAndView("masterPlanRePaymentBankHalfYearly", "model", model);
	}

	@RequestMapping(value = "/masterPlanRePaymentBankList", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Repayment> repayList = repaymenyDAO.getRepayFullList().getResultList();

		if (repayList != null && repayList.size() > 0) {
			model.put("repayList", repayList);
			return new ModelAndView("masterPlanRePaymentBankList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/poPaymentClearList", method = RequestMethod.GET)
	public ModelAndView poPaymentHeadList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> purchaseList = purchaseOrderDAO.getPoPaymentClearence().getResultList();

		if (purchaseList != null && purchaseList.size() > 0) {
			model.put("purchaseList", purchaseList);
			return new ModelAndView("poPaymentClearList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/poPaymentClear", method = RequestMethod.GET)
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
			letterOfCreditForm.setTypeOfTrans(letter.get(0).getTypeOfTrans());
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

			model.put("letterOfCreditForm", letterOfCreditForm);
		} else {
			model.put("user", user);
			attributes.addFlashAttribute("success", "Please Check Type Of Transfer ");
			return new ModelAndView("redirect:poPaymentClearList");
		}
		model.put("user", user);
		return new ModelAndView("poPaymentClear", "model", model);

	}

	@RequestMapping(value = "/poPaymentClearFunds", method = RequestMethod.GET)
	public ModelAndView poPaymentClearFunds(ModelMap model, @RequestParam Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(id);

		purchase.setTransResult("Cleared");
		Date payDate = DateService.loginDate;
		purchase.setPoPayDate(payDate);
		purchaseOrderDAO.updatePo(purchase);

		attributes.addFlashAttribute("success", "Cleared Successfully");

		model.put("user", user);

		return new ModelAndView("redirect:poPaymentClearList");

	}

	@RequestMapping(value = "/poPaymentRejectFunds", method = RequestMethod.GET)
	public ModelAndView poPaymentRejectFunds(ModelMap model, @RequestParam Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(id);

		purchase.setTransResult("NotCleared");
		purchase.setTransStatus("Rejected");
		purchaseOrderDAO.updatePo(purchase);

		attributes.addFlashAttribute("success", "Rejected Successfully");

		model.put("user", user);

		return new ModelAndView("redirect:poPaymentClearList");

	}

	/**
	 * Method displaying multiple payments PO pending
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/poMultiplePaymentList", method = RequestMethod.GET)
	public ModelAndView poMultiplePaymentList(ModelMap model) {

		List<PurchaseOrder> purchaseList = purchaseOrderDAO.getPoPaymentClearence().getResultList();

		if (purchaseList != null && purchaseList.size() > 0) {
			purchaseList = ListService.removeDuplicatesForMultipplePayment(purchaseList);

			model.put("purchaseList", purchaseList);
			return new ModelAndView("poMultiplePaymentList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}
	}

	/**
	 * Method to view all multiple payment PO's based on transaction id selected
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/poMultiplePaymentView", method = RequestMethod.GET)
	public ModelAndView poMultiplePaymentView(@RequestParam String id, ModelMap model) {

		List<PurchaseOrder> purchaseList = purchaseOrderDAO.getPoMultiplePaymentByTransacrionId(id).getResultList();

		if (purchaseList != null && purchaseList.size() > 0) {

			model.put("purchaseList", purchaseList);
			return new ModelAndView("poMultiplePaymentView", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}
	}

	/**
	 * Method to clear funds for multiple PO based on transaction id
	 *
	 * @param model
	 * @param id
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/poMultiplePaymentClearFunds", method = RequestMethod.GET)
	public ModelAndView poMultiplePaymentClearFunds(ModelMap model, @RequestParam String id,
													RedirectAttributes attributes) {

		List<PurchaseOrder> purchaseList = purchaseOrderDAO.getPoMultiplePaymentByTransacrionId(id).getResultList();
		if (purchaseList != null && purchaseList.size() > 0) {
			for (PurchaseOrder purchase : purchaseList) {
				purchase.setTransResult("Cleared");
				purchase.setPoPayDate(DateService.loginDate);

				purchaseOrderDAO.updatePo(purchase);
			}
		}
		attributes.addFlashAttribute("success", "Cleared Successfully");

		return new ModelAndView("redirect:poMultiplePaymentList");
	}

	/**
	 * Method to reject funds for multiple PO based on transaction id
	 *
	 * @param model
	 * @param id
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/poMultiplePaymentRejectFunds", method = RequestMethod.GET)
	public ModelAndView poMultiplePaymentRejectFunds(ModelMap model, @RequestParam String id,
													 RedirectAttributes attributes) {

		List<PurchaseOrder> purchaseList = purchaseOrderDAO.getPoMultiplePaymentByTransacrionId(id).getResultList();
		if (purchaseList != null && purchaseList.size() > 0) {
			for (PurchaseOrder purchase : purchaseList) {
				purchase.setTransResult("NotCleared");
				purchase.setTransStatus("Rejected");

				purchaseOrderDAO.updatePo(purchase);
			}
		}

		attributes.addFlashAttribute("success", "Rejected Successfully");

		return new ModelAndView("redirect:poMultiplePaymentList");
	}

	@RequestMapping(value = "/poPaymentBankList", method = RequestMethod.GET)
	public ModelAndView poPaymentBankList(ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> purchaseList = purchaseOrderDAO.getList().getResultList();
		if (purchaseList != null && purchaseList.size() > 0) {
			model.put("purchaseList", purchaseList);
			return new ModelAndView("poPaymentBankList");
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/PoBankEmpList", method = RequestMethod.GET)
	public ModelAndView VendorPoList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> poList = purchaseOrderDAO.getPurchaseOrderByApproved().getResultList();

		if (poList != null && poList.size() > 0) {
			purchaseOrderForm.setId(poList.get(0).getId());

			model.put("poList", poList);
			model.put("purchaseOrderForm", purchaseOrderForm);
			return new ModelAndView("PoBankEmpList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/bankEmpfileUploadForm", method = RequestMethod.GET)
	public ModelAndView vendorPOFileupload(@RequestParam Long id, ModelMap model,
										   @ModelAttribute PurchaseOrderForm purchaseOrderForm, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchaseOrder = purchaseOrderDAO.getByPurchaseId(id);

		purchaseOrderForm.setPoKey(purchaseOrder.getPoKey());

		model.put("user", user);

		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("bankEmpfileUploadForm", "model", model);
	}

	@RequestMapping("/fileUpload1")
	public ModelAndView fileUploadedForTarding(ModelMap model, RedirectAttributes attribute,
											   @ModelAttribute PurchaseOrderForm purchaseOrderForm, BindingResult result)
			throws RuntimeException, IOException {

		EndUser user = getCurrentLoggedUserDetails();
		PurchaseOrder purchaseorder = new PurchaseOrder();

		purchaseOrderForm.setTransactionId(KeyGenerator.generateTransactionKey());
		/*
		 * List<MultipartFile> files = purchaseOrderForm.getFiles(); for (MultipartFile
		 * multipartFile : files) { String fileName =
		 * multipartFile.getOriginalFilename();
		 *
		 * if (fileName != null && !fileName.equals("")) { log.info("These are the File"
		 * + fileName); uploadService.saveImage(servletContext.getRealPath("/") + "/img"
		 * + "/" + fileName, multipartFile);
		 *
		 * } PurchaseOrder purchaseorder = purchaseOrderDAO.getPoListByPoKey(
		 * purchaseOrderForm.getPoKey()).getSingleResult();
		 * purchaseorder.setFileName(servletContext.getRealPath("/") + "/img" + "/" +
		 * fileName);
		 *
		 * purchaseOrderDAO.updatePo(purchaseorder);
		 *
		 * }
		 */
		try {
			List<MultipartFile> files = purchaseOrderForm.getFiles();
			Set<byte[]> filesList = new HashSet<byte[]>();
			Set<String> fileNameList = new HashSet<String>();
			for (MultipartFile multipartFile : files) {
				filesList.add(multipartFile.getBytes());
				fileNameList.add(multipartFile.getOriginalFilename());
			}
			purchaseorder.setFiles(filesList);
			purchaseorder.setFileNames(fileNameList);

			purchaseorder = purchaseOrderDAO.getPoListByPoKey(purchaseOrderForm.getPoKey()).getSingleResult();
			purchaseorder.setDocument(purchaseOrderForm.getDocument());

			purchaseorder.setCustomerName(purchaseOrderForm.getCustomerName());
			purchaseorder.setForWard("No");
			purchaseorder.setReason(purchaseOrderForm.getReason());
			purchaseorder.setTransactionId(purchaseOrderForm.getTransactionId());
			Date date = DateService.loginDate;
			purchaseorder.setUploadDate(date);
			purchaseOrderDAO.updatePo(purchaseorder);

			Transaction trans = new Transaction();

			trans.setTransactionId(purchaseOrderForm.getTransactionId());
			trans.setTransactionStatus("Upload Document");
			trans.setTransactionType("Uploaded Successfully");

			transactionDAO.insertTransaction(trans);

			model.put("user", user);
			model.put("purchaseOrderForm", purchaseOrderForm);

		} catch (Exception e) {
			System.out.println(e.getMessage() + e);

		}
		attribute.addFlashAttribute("success", "Saved Successfully. ");
		return new ModelAndView("buyerUploadFileSuccess", "model", model);
	}

	@RequestMapping(value = "/bankUploadFileForward", method = RequestMethod.GET)
	public ModelAndView buyerUploadFileForward(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchaseOrder = purchaseOrderDAO.getByPurchaseId(purchaseOrderForm.getId());

		if (purchaseOrder.getFiles() != null) {
			purchaseOrder.setForWard("Yes");
			Date fDate = DateService.loginDate;
			purchaseOrder.setForwardDate(fDate);
			purchaseOrderDAO.updatePo(purchaseOrder);
			model.put("user", user);
			model.put("purchaseOrderForm", purchaseOrderForm);
		} else {
			model.put("user", user);
			attributes.addFlashAttribute("success", "You Have Not Uploaded File. ");
			return new ModelAndView("redirect:PoBankEmpList");
		}

		return new ModelAndView("redirect:PoBankEmpList");
	}

	@RequestMapping(value = "/requestedInvoiceBank", method = RequestMethod.GET)
	public ModelAndView requestedInvoiceBank(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getInvoiceListForSetRates().getResultList();

		if (invoiceList != null && invoiceList.size() > 0) {

			model.put("invoiceList", invoiceList);
			return new ModelAndView("requestedInvoiceBank", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/requestedInvoiceSetRate", method = RequestMethod.GET)
	public ModelAndView requestedInvoiceSetRate(@RequestParam Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		Invoice invoice = invoiceDAO.getByInvoiceId(id);

		invoiceForm.setId(invoice.getId());
		invoiceForm.setCustomerName(invoice.getCustomerName());
		invoiceForm.setBuyerName(invoice.getBuyerName());
		invoiceForm.setPoKey(invoice.getPoKey());
		invoiceForm.setTenure(invoice.getTenure());
		invoiceForm.setAmount(invoice.getAmount());
		invoiceForm.setTransactionId(invoice.getTransactionId());

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("requestedInvoiceSetRate", "model", model);

	}

	@RequestMapping(value = "/requestedInvoiceSetRateConfirm", method = RequestMethod.POST)
	public ModelAndView requestedInvoiceSetRatePost(ModelMap model, @ModelAttribute InvoiceForm invoiceForm,
													BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		invoiceForm.setId(invoiceForm.getId());
		invoiceForm.setCustomerName(invoiceForm.getCustomerName());
		invoiceForm.setBuyerName(invoiceForm.getBuyerName());
		invoiceForm.setPoKey(invoiceForm.getPoKey());
		invoiceForm.setTenure(invoiceForm.getTenure());
		invoiceForm.setAmount(invoiceForm.getAmount());
		invoiceForm.setTransactionId(invoiceForm.getTransactionId());
		invoiceForm.setPercentage(invoiceForm.getPercentage());
		invoiceForm.setFunalAmt(invoiceForm.getFunalAmt());

		model.put("invoiceForm", invoiceForm);
		model.put("user", user);

		return new ModelAndView("requestedInvoiceSetRateConfirm", "model", model);

	}

	@RequestMapping(value = "/requestedInvoiceSetRatePost", method = RequestMethod.POST)
	public ModelAndView requestedInvoiceSetRateConfirm(ModelMap model, @ModelAttribute InvoiceForm invoiceForm,
													   BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		Invoice invoice = invoiceDAO.getByInvoiceId(invoiceForm.getId());

		invoiceForm.setTransactionId(invoiceForm.getTransactionId());
		invoice.setPercentage(invoiceForm.getPercentage());
		invoice.setFunalAmt(invoiceForm.getFunalAmt());
		invoice.setTransStatus("Pending");
		invoice.setRequestMoney("Pending");

		invoiceDAO.updateInvoice(invoice);

		Transaction trans = new Transaction();

		trans.setTransactionId(purchaseOrderForm.getTransactionId());
		trans.setTransactionStatus("Set Rates");
		trans.setTransactionType("Submitted Successfully");

		transactionDAO.insertTransaction(trans);

		model.put("invoiceForm", invoiceForm);
		model.put("user", user);

		return new ModelAndView("requestedInvoiceSetRateTrans", "model", model);

	}

	@RequestMapping(value = "/requestedInvoiceSetRateTrans", method = RequestMethod.GET)
	public ModelAndView requestedInvoiceSetRateTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("requestedInvoiceSetRateTrans", "model", model);

	}

	@RequestMapping(value = "/invoiceMoneyReject", method = RequestMethod.GET)
	public ModelAndView invoiceMoneyReject(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Invoice invoice = invoiceDAO.getByInvoiceId(id);

		invoice.setRequestMoney("Rejected");
		invoice.setTransStatus("Rejected");
		invoiceDAO.updateInvoice(invoice);

		model.put("user", user);

		attributes.addFlashAttribute("success", "Rejected Successfully");
		return new ModelAndView("redirect:requestedInvoiceBank");

	}

	@RequestMapping(value = "/requestMoneyInvoiceBankFullList", method = RequestMethod.GET)
	public ModelAndView requestMoneyInvoiceHeadFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<Invoice> invoiceList = invoiceDAO.getList().getResultList();

		if (invoiceList != null && invoiceList.size() > 0) {

			model.put("invoiceList", invoiceList);
		}
		model.put("user", user);
		return new ModelAndView("requestMoneyInvoiceBankFullList", "model", model);

	}

	@RequestMapping(value = "/requestMoneyCustAccepted", method = RequestMethod.GET)
	public ModelAndView requestMoneyOnInvoice(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getInvoiceListForClearence().getResultList();

		if (invoiceList != null && invoiceList.size() > 0) {

			model.put("invoiceList", invoiceList);
			return new ModelAndView("requestMoneyCustAccepted", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/invoiceMoneyCleared", method = RequestMethod.GET)
	public ModelAndView invoiceMoneyCleared(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Invoice invoice = invoiceDAO.getByInvoiceId(id);

		invoice.setRequestMoney("Cleared");
		invoice.setTransStatus("Cleared");
		invoice.setAmountRec(invoice.getFunalAmt());
		invoiceDAO.updateInvoice(invoice);

		model.put("user", user);

		attributes.addFlashAttribute("success", "Cleared Funds Successfully");
		return new ModelAndView("redirect:requestMoneyCustAccepted");

	}

	@RequestMapping(value = "/supplierRatingList", method = RequestMethod.GET)
	public ModelAndView requestMoneyAccepted(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Supplier> supplierList = supplierService.getForRating().getResultList();

		if (supplierList != null && supplierList.size() > 0) {

			model.put("supplierList", supplierList);
			return new ModelAndView("supplierRatingList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/supplierRating", method = RequestMethod.GET)
	public ModelAndView approveCustomerHead(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		Supplier supplier = supplierService.findId(id);

		supplierForm.setId(supplier.getId());
		supplierForm.setSupplierName(supplier.getSupplierName());

		model.put("supplierForm", supplierForm);
		return new ModelAndView("supplierRating", "model", model);
	}

	@RequestMapping(value = "/supplierRatingConfirm", method = RequestMethod.POST)
	public ModelAndView supplierRatingConfirm(@ModelAttribute SupplierForm supplierForm, ModelMap model,
											  RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		supplierForm.setId(supplierForm.getId());
		supplierForm.setSupplierName(supplierForm.getSupplierName());
		supplierForm.setRating(supplierForm.getRating());
		supplierForm.setLimit(supplierForm.getLimit());

		model.put("supplierForm", supplierForm);

		return new ModelAndView("supplierRatingConfirm", "model", model);
	}

	@RequestMapping(value = "/supplierRatingPost", method = RequestMethod.POST)
	public ModelAndView supplierRatingPost(@ModelAttribute SupplierForm supplierForm, ModelMap model,
										   RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();


		supplierForm.setTransactionId(KeyGenerator.generateTransactionKey());

		model.put("user", user);
		Supplier supplier = supplierService.findId(supplierForm.getId());

		supplier.setId(supplierForm.getId());
		supplier.setRating(supplierForm.getRating());
		supplier.setCustLimit(supplierForm.getLimit());

		supplierService.update(supplier);
		Transaction transaction = new Transaction();
		transaction.setTransactionId(supplierForm.getTransactionId());
		transaction.setTransactionType("Supplier Rating");
		transaction.setTransactionStatus("Supplier Rating saved successfully");

		transcationDAOImpl.insertTransaction(transaction);
		model.put("supplierForm", supplierForm);

		return new ModelAndView("supplierRatingTrans", "model", model);
	}

	@RequestMapping(value = "/supplierRatingTrans", method = RequestMethod.GET)
	public ModelAndView customerHeadTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("supplierForm", supplierForm);

		return new ModelAndView("supplierRatingTrans", "model", model);

	}

	@RequestMapping(value = "/buyerRatingList", method = RequestMethod.GET)
	public ModelAndView buyerRatingList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<NewBuyer> buyerList = newBuyerDAOImpl.getForRating().getResultList();

		if (buyerList != null && buyerList.size() > 0) {

			model.put("buyerList", buyerList);
			return new ModelAndView("buyerRatingList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/buyerRating", method = RequestMethod.GET)
	public ModelAndView buyerRating(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		NewBuyer buyer = newBuyerDAOImpl.findId(id);

		newBuyerForm.setId(buyer.getId());
		newBuyerForm.setBuyerName(buyer.getBuyerName());

		model.put("newBuyerForm", newBuyerForm);
		return new ModelAndView("buyerRating", "model", model);
	}

	@RequestMapping(value = "/buyerRatingConfirm", method = RequestMethod.POST)
	public ModelAndView buyerRatingConfirm(@ModelAttribute NewBuyerForm newBuyerForm, ModelMap model,
										   RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		newBuyerForm.setId(newBuyerForm.getId());
		newBuyerForm.setBuyerName(newBuyerForm.getBuyerName());
		newBuyerForm.setRating(newBuyerForm.getRating());
		newBuyerForm.setCustLimit(newBuyerForm.getCustLimit());

		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("buyerRatingConfirm", "model", model);
	}

	@RequestMapping(value = "/buyerRatingPost", method = RequestMethod.POST)
	public ModelAndView buyerRatingPost(@ModelAttribute NewBuyerForm newBuyerForm, ModelMap model,
										RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		newBuyerForm.setTransactionId(KeyGenerator.generateTransactionKey());

		model.put("user", user);
		NewBuyer buyer = newBuyerDAOImpl.findId(newBuyerForm.getId());

		buyer.setRating(newBuyerForm.getRating());
		buyer.setCustLimit(newBuyerForm.getCustLimit());

		newBuyerDAOImpl.update(buyer);
		Transaction trans = new Transaction();
		trans.setTransactionId(newBuyerForm.getTransactionId());
		trans.setTransactionStatus("Buyer Rating Save Successfully");
		trans.setTransactionType("Buyer Rating");

		transactionDAO.insertTransaction(trans);
		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("buyerRatingTrans", "model", model);
	}

	@RequestMapping(value = "/buyerRatingTrans", method = RequestMethod.GET)
	public ModelAndView buyerRatingTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("buyerRatingTrans", "model", model);

	}

	@RequestMapping(value = "/invoicePaymentForClear", method = RequestMethod.GET)
	public ModelAndView invoicePaymentForClear(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getInvoiceClear().getResultList();

		if (invoiceList != null && invoiceList.size() > 0) {
			model.put("invoiceList", invoiceList);
			return new ModelAndView("invoicePaymentForClear", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/invoiceVendorPayCleared", method = RequestMethod.GET)
	public ModelAndView invoiceVendorPayCleared(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Invoice invoice = invoiceDAO.getByInvoiceId(id);

		invoice.setRequestMoney("PayDone");
		invoice.setTransResult("PayDone");
		invoiceDAO.updateInvoice(invoice);

		model.put("user", user);

		attributes.addFlashAttribute("success", "Cleared Funds Successfully");
		return new ModelAndView("redirect:invoicePaymentForClear");

	}

	@RequestMapping(value = "/generalLedgerBank", method = RequestMethod.GET)
	public ModelAndView generalLedgerBank(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<MasterPlan> masterList = masterPlanDAO.getAllMasterPlans().getResultList();

		if (masterList.size() == 0 && masterList.size() == 0) {
			return new ModelAndView("noDataFound", "model", model);
		} else {
			model.put("masterList", masterList);

			return new ModelAndView("generalLedgerBank", "model", model);
		}

	}

	@RequestMapping(value = "/generalLedgerBankView", method = RequestMethod.GET)
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
		return new ModelAndView("generalLedgerBankView", "model", model);
	}

	@RequestMapping(value = "/snapShotBank", method = RequestMethod.GET)
	public ModelAndView snapShotBank(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<MasterPlan> masterList = masterPlanDAO.getAllMasterPlans().getResultList();

		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);
			return new ModelAndView("snapShotBank", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/snapShotBankView", method = RequestMethod.GET)
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

		return new ModelAndView("snapShotBankView", "model", model);

	}

	@RequestMapping(value = "/reportsListBank", method = RequestMethod.GET)
	public ModelAndView reportsListBank(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("purchaseOrderForm", purchaseOrderForm);
		model.put("user", user);

		return new ModelAndView("reportsListBank", "model", model);

	}

	@RequestMapping(value = "/reportsPOBankPost", method = RequestMethod.POST)
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
		 * ModelAndView("reportsListBankView", "model", model); }
		 *
		 * else { return new ModelAndView("noDataFound", "model", model); }
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

		return new ModelAndView("reportsListBankView", "model", model);

	}

	@RequestMapping(value = "/reportsListBankView", method = RequestMethod.GET)
	public ModelAndView reportsListBankView(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("purchaseOrderForm", purchaseOrderForm);
		model.put("user", user);

		return new ModelAndView("reportsListBankView", "model", model);

	}

	@RequestMapping(value = "/reportsListBankBying", method = RequestMethod.GET)
	public ModelAndView reportsListBankBying(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("invoiceForm", invoiceForm);
		model.put("user", user);

		return new ModelAndView("reportsListBankBying", "model", model);

	}

	@RequestMapping(value = "/reportsBankBuyingPost", method = RequestMethod.POST)
	public ModelAndView reportsPOBankPost(@ModelAttribute InvoiceForm invoiceForm, ModelMap model,
										  RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getInvoiceByCategoryAndDate(invoiceForm.getGoodsCategory(),
				invoiceForm.getFromDate(), invoiceForm.getToDate());

		/*
		 * if (invoiceList != null && invoiceList.size() > 0) {
		 * invoiceForm.setInvoiceList(invoiceList); model.put("invoiceForm",
		 * invoiceForm); return new ModelAndView("reportsListBankBuyingView", "model",
		 * model); } else { return new ModelAndView("noDataFound", "model", model); }
		 */
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
		model.put("user", user);
		return new ModelAndView("reportsListBankBuyingView", "model", model);

	}

	@RequestMapping(value = "/reportsListBankBuyingView", method = RequestMethod.GET)
	public ModelAndView reportsListBankBuyingView(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("invoiceForm", invoiceForm);
		model.put("user", user);

		return new ModelAndView("reportsListBankBuyingView", "model", model);

	}

	/**
	 * Method to display List of Users to Block/Unblock
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getUsersForBlockUnblock", method = RequestMethod.GET)
	public ModelAndView getUsersForBlockUnblock(ModelMap model, @ModelAttribute EndUserForm endUserForm) {
		EndUser user = getCurrentLoggedUserDetails();
		List<EndUser> userList = endUserDAOImpl.getUsersForBlockUnblock();

		model.put("user", user);
		if (userList != null && userList.size() > 0) {
			model.put("userList", userList);
			model.put("endUserForm", endUserForm);
			return new ModelAndView("getUsersForBlockUnblock", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	/**
	 * Method to Block/Unblock user
	 *
	 * @param invoiceForm
	 * @param model
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/blockUnblockUser", method = RequestMethod.POST)
	public String blockUnblockUser(ModelMap model, @ModelAttribute EndUserForm endUserForm,
								   RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		EndUser endUser = endUserDAOImpl.findId(endUserForm.getId());
		endUser.setAccessStatus(endUserForm.getAccessStatus());
		endUser.setReason(endUserForm.getReason());
		endUserDAOImpl.update(endUser);
		attributes.addFlashAttribute("successMsg", endUserForm.getAccessStatus() + " request sent for Approval");

		return "redirect:getUsersForBlockUnblock";
	}

	@RequestMapping(value = "/disputeBankList", method = RequestMethod.GET)
	public ModelAndView disputeBankList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Dispute> disputeList = disputeDAO.getList().getResultList();

		if (disputeList != null && disputeList.size() > 0) {
			model.put("disputeList", disputeList);
			return new ModelAndView("disputeBankList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	/**
	 * Method to renew account by extending the account expiry date
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getRenewAccount", method = RequestMethod.GET)
	public ModelAndView getRenewAccount(@RequestParam Long id, ModelMap model,
										@ModelAttribute EndUserForm endUserForm) {

		EndUser user = getCurrentLoggedUserDetails();
		EndUser renewUser = endUserDAOImpl.findId(id);
		endUserForm.setId(id);
		endUserForm.setAccExpiryDate(renewUser.getAccExpiryDate());

		model.put("user", user);
		model.put("endUserForm", endUserForm);
		return new ModelAndView("getRenewAccount", "model", model);

	}

	/**
	 * Method to renew account
	 *
	 * @return
	 */
	@RequestMapping(value = "/renewAccount", method = RequestMethod.POST)
	public String renewAccount(@ModelAttribute EndUserForm endUserForm, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		EndUser endUser = endUserDAOImpl.findId(endUserForm.getId());

		endUser.setAccExpiryDate(endUserForm.getAccExpiryDate());
		endUser.setAccRenewStatus(Constants.PENDING);
		endUserDAOImpl.update(endUser);

		return "redirect:getUsersForBlockUnblock";

	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@RequestMapping(value = "/viewAllEvents3", method = RequestMethod.GET)
	public ModelAndView checkStatusEvents(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		List<BuyerSameBankEvent> buyerSameBankEvents = buyerSameBankeventDAO.findCheckStatus().getResultList();

		if (buyerSameBankEvents != null && buyerSameBankEvents.size() > 0) {

			model.put("buyerSameBankEvents", buyerSameBankEvents);

			mav = new ModelAndView("bankCheckStatusEvents", "model", model);

		} else {
			mav = new ModelAndView("noDataFound", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/selectEventsForCheckStatus", method = RequestMethod.GET)
	public ModelAndView selectEventsForCheckStatus(ModelMap model, @RequestParam("id") Long id,
												   HttpServletRequest request, RedirectAttributes attributes) throws ParseException {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		BuyerSameBankEvent buyerSameBankEvent = buyerSameBankeventDAO.findEvent(id);

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

		DateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
		Date date1 = buyerSameBankEvent.getDate1();
		Date date2 = buyerSameBankEvent.getDate2();
		Date date3 = buyerSameBankEvent.getDate3();
		Date date4 = buyerSameBankEvent.getDate4();
		Date date5 = buyerSameBankEvent.getDate5();
		Date date6 = buyerSameBankEvent.getDate6();
		Date date7 = buyerSameBankEvent.getDate7();
		Date date8 = buyerSameBankEvent.getDate8();
		Date date9 = buyerSameBankEvent.getDate9();
		Date date10 = buyerSameBankEvent.getDate10();
		Date date11 = buyerSameBankEvent.getDate11();

		if (date2 != null) {

			long tenure2 = DateService.getDaysBetweenTwoDates(date1, date2);
			model.put("tenure2", tenure2);

		} else {
			long tenure2 = 0;

			model.put("tenure2", tenure2);

		}
		if (date3 != null) {
			if (date2 != null) {
				long tenure3 = DateService.getDaysBetweenTwoDates(date2, date3);
				model.put("tenure3", tenure3);
			}

		} else {
			long tenure3 = 0;

			model.put("tenure3", tenure3);
		}
		if (date4 != null) {
			if (date3 != null) {
				long tenure4 = DateService.getDaysBetweenTwoDates(date3, date4);
				model.put("tenure4", tenure4);
			}

		} else {
			long tenure4 = 0;

			model.put("tenure4", tenure4);

		}

		if (date5 != null) {
			if (date4 != null) {
				long tenure5 = DateService.getDaysBetweenTwoDates(date4, date5);
				model.put("tenure5", tenure5);

			}

		} else {
			long tenure5 = 0;

			model.put("tenure5", tenure5);

		}
		if (date6 != null) {
			if (date5 != null) {
				long tenure6 = DateService.getDaysBetweenTwoDates(date5, date6);

				model.put("tenure6", tenure6);
			}
		} else {
			long tenure6 = 0;
			model.put("tenure6", tenure6);
		}
		if (date7 != null) {
			if (date6 != null) {
				long tenure7 = DateService.getDaysBetweenTwoDates(date6, date7);

				model.put("tenure7", tenure7);
			}
		} else {
			long tenure7 = 0;
			model.put("tenure7", tenure7);
		}
		if (date8 != null) {
			if (date7 != null) {
				long tenure8 = DateService.getDaysBetweenTwoDates(date7, date8);

				model.put("tenure8", tenure8);
			}
		} else {
			long tenure8 = 0;
			model.put("tenure8", tenure8);
		}

		if (date9 != null) {
			if (date8 != null) {
				long tenure9 = DateService.getDaysBetweenTwoDates(date8, date9);

				model.put("tenure9", tenure9);
			}
		} else {
			long tenure9 = 0;
			model.put("tenure9", tenure9);
		}
		if (date10 != null) {
			if (date9 != null) {
				long tenure10 = DateService.getDaysBetweenTwoDates(date9, date10);

				model.put("tenure10", tenure10);
			}
		} else {
			long tenure10 = 0;
			model.put("tenure10", tenure10);
		}
		if (date11 != null) {
			if (date10 != null) {
				long tenure11 = DateService.getDaysBetweenTwoDates(date10, date11);

				model.put("tenure11", tenure11);
			}
		} else {
			long tenure11 = 0;
			model.put("tenure11", tenure11);
		}

		int range = DateService.getDaysBetweenTwoDates(date1, date11);

		double mean = Math.round(range / 11);

		double variance = Math.round(((range - mean) * (range - mean)) / 11);

		double standardDeviation = Math.round(Math.sqrt(variance));

		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		Date bDate1;
		Date bDate2;
		arrayList.add(1);
		for (int i = 2; i < 12; i++) {
			bDate1 = DateService.getBuyerSameEventDate(buyerSameBankEvent, i - 1);
			bDate2 = DateService.getBuyerSameEventDate(buyerSameBankEvent, i);
			if (bDate1 != null && bDate2 != null) {
				int noOfDays = DateService.getDaysBetweenTwoDates(bDate1, bDate2);

				arrayList.add(noOfDays);
			}
		}
		Collections.sort(arrayList);

		double median = 0.0;

		if (arrayList.size() % 2 == 0) {
			median = Math
					.round((arrayList.get((arrayList.size() / 2)) + arrayList.get((arrayList.size() / 2 - 1))) / 2);
		} else {
			median = Math.round(arrayList.get((arrayList.size() / 2)));
		}

		int mode = DateService.getModeElement(arrayList);
		model.put("mode", mode);
		model.put("median", median);

		model.put("range", range);
		model.put("mean", mean);
		model.put("variance", variance);
		model.put("standardDeviation", standardDeviation);

		Date date = DateService.loginDate;
		String sysdat = dateFormat.format(date);

		Date end = new SimpleDateFormat("d/M/yyyy").parse(sysdat);

		if (date1 != null) {
			String dat1 = dateFormat.format(date1);
			Date bdate1 = new SimpleDateFormat("d/M/yyyy").parse(dat1);
			if (bdate1.compareTo(end) < 0) {
				String colour1 = "green";
				model.put("colour1", colour1);

			} else {
				String colour1 = "red";
				model.put("colour1", colour1);
			}
		} else {
			String colour1 = "black";
			model.put("colour1", colour1);
		}
		if (date2 != null) {

			String dat2 = dateFormat.format(date2);
			Date bdate2 = new SimpleDateFormat("d/M/yyyy").parse(dat2);

			if (bdate2.compareTo(end) < 0) {
				String colour2 = "green";
				model.put("colour2", colour2);

			} else {
				String colour2 = "red";
				model.put("colour2", colour2);
			}
		} else {
			String colour2 = "black";
			model.put("colour2", colour2);
		}
		if (date3 != null) {
			String dat3 = dateFormat.format(date3);
			Date bdate3 = new SimpleDateFormat("d/M/yyyy").parse(dat3);

			if (bdate3.compareTo(end) < 0) {
				String colour3 = "green";
				model.put("colour3", colour3);

			} else {
				String colour3 = "red";
				model.put("colour3", colour3);
			}
		} else {
			String colour3 = "black";
			model.put("colour3", colour3);
		}
		if (date4 != null) {
			String dat4 = dateFormat.format(date4);
			Date bdate4 = new SimpleDateFormat("d/M/yyyy").parse(dat4);

			if (bdate4.compareTo(end) < 0) {
				String colour4 = "green";
				model.put("colour4", colour4);

			} else {
				String colour4 = "red";
				model.put("colour4", colour4);
			}
		} else {
			String colour4 = "black";
			model.put("colour4", colour4);
		}
		if (date5 != null) {

			String dat5 = dateFormat.format(date5);
			Date bdate5 = new SimpleDateFormat("d/M/yyyy").parse(dat5);
			if (bdate5.compareTo(end) < 0) {
				String colour5 = "green";
				model.put("colour5", colour5);

			} else {
				String colour5 = "red";
				model.put("colour5", colour5);
			}

		} else {
			String colour5 = "black";
			model.put("colour5", colour5);

		}

		if (date6 != null) {
			String dat6 = dateFormat.format(date6);
			Date bdate6 = new SimpleDateFormat("d/M/yyyy").parse(dat6);

			if (bdate6.compareTo(end) < 0) {
				String colour6 = "green";
				model.put("colour6", colour6);

			} else {
				String colour6 = "red";
				model.put("colour6", colour6);
			}
		} else {
			String colour6 = "black";
			model.put("colour6", colour6);
		}
		if (date7 != null) {
			String dat7 = dateFormat.format(date7);
			Date bdate7 = new SimpleDateFormat("d/M/yyyy").parse(dat7);
			if (bdate7.compareTo(end) < 0) {
				String colour7 = "green";
				model.put("colour7", colour7);

			} else {
				String colour7 = "red";
				model.put("colour7", colour7);
			}
		} else {
			String colour7 = "black";
			model.put("colour7", colour7);

		}
		if (date8 != null) {
			String dat8 = dateFormat.format(date8);
			Date bdate8 = new SimpleDateFormat("d/M/yyyy").parse(dat8);
			if (bdate8.compareTo(end) < 0) {
				String colour8 = "green";
				model.put("colour8", colour8);

			} else {
				String colour8 = "red";
				model.put("colour8", colour8);
			}
		} else {
			String colour8 = "black";
			model.put("colour8", colour8);
		}
		if (date9 != null) {
			String dat9 = dateFormat.format(date9);
			Date bdate9 = new SimpleDateFormat("d/M/yyyy").parse(dat9);
			if (bdate9.compareTo(end) < 0) {
				String colour9 = "green";
				model.put("colour9", colour9);

			} else {
				String colour9 = "red";
				model.put("colour9", colour9);
			}
		} else {
			String colour9 = "black";
			model.put("colour9", colour9);
		}
		if (date10 != null) {

			String dat10 = dateFormat.format(date10);
			Date bdate10 = new SimpleDateFormat("d/M/yyyy").parse(dat10);

			if (bdate10.compareTo(end) < 0) {
				String colour10 = "green";
				model.put("colour10", colour10);

			} else {
				String colour10 = "red";
				model.put("colour10", colour10);
			}
		} else {
			String colour10 = "black";
			model.put("colour10", colour10);
		}

		if (date11 != null) {
			String dat11 = dateFormat.format(date11);
			Date bdate11 = new SimpleDateFormat("d/M/yyyy").parse(dat11);

			if (bdate11.compareTo(end) < 0) {
				String colour11 = "green";
				model.put("colour11", colour11);

			} else {
				String colour11 = "red";
				model.put("colour11", colour11);
			}
		} else {
			String colour11 = "black";
			model.put("colour11", colour11);
		}

		buyerSameBankEventForm.setCustomerName(buyerSameBankEvent.getCustomerName());
		buyerSameBankEventForm.setSupplier(buyerSameBankEvent.getSupplier());
		buyerSameBankEventForm.setSupplierBank(buyerSameBankEvent.getSupplierBank());
		buyerSameBankEventForm.setMasterKey(buyerSameBankEvent.getMasterKey());
		buyerSameBankEventForm.setGoods(buyerSameBankEvent.getGoods());
		buyerSameBankEventForm.setSanctionedAmount(buyerSameBankEvent.getSanctionedAmount());
		buyerSameBankEventForm.setUtilizedAmount(buyerSameBankEvent.getUtilizedAmount());
		buyerSameBankEventForm.setAvailableCost(buyerSameBankEvent.getAvailableCost());
		buyerSameBankEventForm.setDate1(buyerSameBankEvent.getDate1());
		buyerSameBankEventForm.setEvent1(buyerSameBankEvent.getEvent1());
		buyerSameBankEventForm.setFirst(buyerSameBankEvent.getFirst());
		buyerSameBankEventForm.setDate2(buyerSameBankEvent.getDate2());
		buyerSameBankEventForm.setEvent2(buyerSameBankEvent.getEvent2());
		buyerSameBankEventForm.setSecond(buyerSameBankEvent.getSecond());
		buyerSameBankEventForm.setDate3(buyerSameBankEvent.getDate3());
		buyerSameBankEventForm.setEvent3(buyerSameBankEvent.getEvent3());
		buyerSameBankEventForm.setThird(buyerSameBankEvent.getThird());
		buyerSameBankEventForm.setDate4(buyerSameBankEvent.getDate4());
		buyerSameBankEventForm.setEvent4(buyerSameBankEvent.getEvent4());
		buyerSameBankEventForm.setFourth(buyerSameBankEvent.getFourth());
		buyerSameBankEventForm.setDate5(buyerSameBankEvent.getDate5());
		buyerSameBankEventForm.setEvent5(buyerSameBankEvent.getEvent5());
		buyerSameBankEventForm.setFifth(buyerSameBankEvent.getFifth());
		buyerSameBankEventForm.setDate6(buyerSameBankEvent.getDate6());
		buyerSameBankEventForm.setEvent6(buyerSameBankEvent.getEvent6());
		buyerSameBankEventForm.setSix(buyerSameBankEvent.getSix());
		buyerSameBankEventForm.setDate7(buyerSameBankEvent.getDate7());
		buyerSameBankEventForm.setEvent7(buyerSameBankEvent.getEvent7());
		buyerSameBankEventForm.setSeven(buyerSameBankEvent.getSeven());
		buyerSameBankEventForm.setDate8(buyerSameBankEvent.getDate8());
		buyerSameBankEventForm.setEvent8(buyerSameBankEvent.getEvent8());
		buyerSameBankEventForm.setEight(buyerSameBankEvent.getEight());
		buyerSameBankEventForm.setDate9(buyerSameBankEvent.getDate9());
		buyerSameBankEventForm.setEvent9(buyerSameBankEvent.getEvent9());
		buyerSameBankEventForm.setNine(buyerSameBankEvent.getNine());
		buyerSameBankEventForm.setDate10(buyerSameBankEvent.getDate10());
		buyerSameBankEventForm.setEvent10(buyerSameBankEvent.getEvent10());
		buyerSameBankEventForm.setTen(buyerSameBankEvent.getTen());
		buyerSameBankEventForm.setDate11(buyerSameBankEvent.getDate11());
		buyerSameBankEventForm.setEvent11(buyerSameBankEvent.getEvent11());
		buyerSameBankEventForm.setElven(buyerSameBankEvent.getElven());

		model.put("buyerSameBankEventForm", buyerSameBankEventForm);

		return new ModelAndView("bankSelectEventsForCheckStatus", "model", model);

	}

	@RequestMapping(value = "/buyerDiffViewAllEvents3", method = RequestMethod.GET)
	public ModelAndView checkStatusDiffEvents(ModelMap model, HttpServletRequest request,
											  HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<BuyerDiffBankEvent> buyerDiffBankEvents = buyerDiffBankEventDAO.findCheckStatus().getResultList();

		ModelAndView mav = new ModelAndView();

		if (buyerDiffBankEvents != null && buyerDiffBankEvents.size() > 0) {

			model.put("buyerDiffBankEvents", buyerDiffBankEvents);

			mav = new ModelAndView("bankCheckStatusDiffEvents", "model", model);

		} else {
			mav = new ModelAndView("noDataFound", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/selectDiffEventsForCheckStatus", method = RequestMethod.GET)
	public ModelAndView selectDiffEventsForCheckStatus(ModelMap model, @RequestParam("id") Long id,
													   HttpServletRequest request, RedirectAttributes attributes) throws ParseException {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		BuyerDiffBankEvent buyerDiffBankEvent = buyerDiffBankEventDAO.findEvent(id);

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

		DateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
		Date date1 = buyerDiffBankEvent.getDate1();
		Date date2 = buyerDiffBankEvent.getDate2();
		Date date3 = buyerDiffBankEvent.getDate3();
		Date date4 = buyerDiffBankEvent.getDate4();
		Date date5 = buyerDiffBankEvent.getDate5();
		Date date6 = buyerDiffBankEvent.getDate6();
		Date date7 = buyerDiffBankEvent.getDate7();
		Date date8 = buyerDiffBankEvent.getDate8();
		Date date9 = buyerDiffBankEvent.getDate9();
		Date date10 = buyerDiffBankEvent.getDate10();
		Date date11 = buyerDiffBankEvent.getDate11();

		Date date12 = buyerDiffBankEvent.getDate12();

		Date date13 = buyerDiffBankEvent.getDate13();

		if (date2 != null) {

			long tenure2 = DateService.getDaysBetweenTwoDates(date1, date2);
			model.put("tenure2", tenure2);

		} else {
			long tenure2 = 0;

			model.put("tenure2", tenure2);

		}
		if (date3 != null) {
			if (date2 != null) {
				long tenure3 = DateService.getDaysBetweenTwoDates(date2, date3);
				model.put("tenure3", tenure3);
			}

		} else {
			long tenure3 = 0;

			model.put("tenure3", tenure3);
		}
		if (date4 != null) {
			if (date3 != null) {
				long tenure4 = DateService.getDaysBetweenTwoDates(date3, date4);
				model.put("tenure4", tenure4);
			}

		} else {
			long tenure4 = 0;

			model.put("tenure4", tenure4);

		}

		if (date5 != null) {
			if (date4 != null) {
				long tenure5 = DateService.getDaysBetweenTwoDates(date4, date5);
				model.put("tenure5", tenure5);

			}

		} else {
			long tenure5 = 0;

			model.put("tenure5", tenure5);

		}
		if (date6 != null) {
			if (date5 != null) {
				long tenure6 = DateService.getDaysBetweenTwoDates(date5, date6);

				model.put("tenure6", tenure6);
			}
		} else {
			long tenure6 = 0;
			model.put("tenure6", tenure6);
		}
		if (date7 != null) {
			if (date6 != null) {
				long tenure7 = DateService.getDaysBetweenTwoDates(date6, date7);

				model.put("tenure7", tenure7);
			}
		} else {
			long tenure7 = 0;
			model.put("tenure7", tenure7);
		}
		if (date8 != null) {
			if (date7 != null) {
				long tenure8 = DateService.getDaysBetweenTwoDates(date7, date8);

				model.put("tenure8", tenure8);
			}
		} else {
			long tenure8 = 0;
			model.put("tenure8", tenure8);
		}

		if (date9 != null) {
			if (date8 != null) {
				long tenure9 = DateService.getDaysBetweenTwoDates(date8, date9);

				model.put("tenure9", tenure9);
			}
		} else {
			long tenure9 = 0;
			model.put("tenure9", tenure9);
		}
		if (date10 != null) {
			if (date9 != null) {
				long tenure10 = DateService.getDaysBetweenTwoDates(date9, date10);

				model.put("tenure10", tenure10);
			}
		} else {
			long tenure10 = 0;
			model.put("tenure10", tenure10);
		}
		if (date11 != null) {
			if (date10 != null) {
				long tenure11 = DateService.getDaysBetweenTwoDates(date10, date11);

				model.put("tenure11", tenure11);
			}
		} else {
			long tenure11 = 0;
			model.put("tenure11", tenure11);
		}
		if (date12 != null) {
			if (date11 != null) {
				long tenure12 = DateService.getDaysBetweenTwoDates(date11, date12);

				model.put("tenure12", tenure12);
			}
		} else {
			long tenure12 = 0;
			model.put("tenure12", tenure12);
		}

		if (date13 != null) {
			if (date12 != null) {
				long tenure13 = DateService.getDaysBetweenTwoDates(date12, date13);
				model.put("tenure13", tenure13);
			}
		} else {
			long tenure13 = 0;
			model.put("tenure13", tenure13);
		}

		int range = DateService.getDaysBetweenTwoDates(date1, date13);

		double mean = Math.round(range / 13);

		double variance = Math.round(((range - mean) * (range - mean)) / 13);

		double standardDeviation = Math.round(Math.sqrt(variance));

		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		Date bDate1;
		Date bDate2;
		arrayList.add(1);
		for (int i = 2; i < 14; i++) {
			bDate1 = DateService.getBuyerDiffEventDate(buyerDiffBankEvent, i - 1);
			bDate2 = DateService.getBuyerDiffEventDate(buyerDiffBankEvent, i);
			if (bDate1 != null && bDate2 != null) {
				int noOfDays = DateService.getDaysBetweenTwoDates(bDate1, bDate2);

				arrayList.add(noOfDays);
			}
		}
		Collections.sort(arrayList);

		double median = 0.0;

		if (arrayList.size() % 2 == 0) {
			median = Math
					.round((arrayList.get((arrayList.size() / 2)) + arrayList.get((arrayList.size() / 2 - 1))) / 2);
		} else {
			median = Math.round(arrayList.get((arrayList.size() / 2)));
		}

		int mode = DateService.getModeElement(arrayList);
		model.put("mode", mode);
		model.put("median", median);
		model.put("range", range);
		model.put("mean", mean);
		model.put("variance", variance);
		model.put("standardDeviation", standardDeviation);

		Date date = DateService.loginDate;
		String sysdat = dateFormat.format(date);

		Date end = new SimpleDateFormat("d/M/yyyy").parse(sysdat);

		if (date12 != null) {
			String dat12 = dateFormat.format(date12);
			Date bdate12 = new SimpleDateFormat("d/M/yyyy").parse(dat12);
			if (bdate12.compareTo(end) < 0) {
				String colour12 = "green";
				model.put("colour12", colour12);

			} else {
				String colour12 = "red";
				model.put("colour12", colour12);
			}
		} else {
			String colour12 = "black";
			model.put("colour12", colour12);
		}
		if (date13 != null) {
			String dat13 = dateFormat.format(date13);
			Date bdate13 = new SimpleDateFormat("d/M/yyyy").parse(dat13);
			if (bdate13.compareTo(end) < 0) {
				String colour13 = "green";
				model.put("colour13", colour13);

			} else {
				String colour13 = "red";
				model.put("colour13", colour13);
			}
		} else {
			String colour13 = "black";
			model.put("colour13", colour13);
		}

		if (date1 != null) {
			String dat1 = dateFormat.format(date1);
			Date bdate1 = new SimpleDateFormat("d/M/yyyy").parse(dat1);
			if (bdate1.compareTo(end) < 0) {
				String colour1 = "green";
				model.put("colour1", colour1);

			} else {
				String colour1 = "red";
				model.put("colour1", colour1);
			}
		} else {
			String colour1 = "black";
			model.put("colour1", colour1);
		}
		if (date2 != null) {

			String dat2 = dateFormat.format(date2);
			Date bdate2 = new SimpleDateFormat("d/M/yyyy").parse(dat2);

			if (bdate2.compareTo(end) < 0) {
				String colour2 = "green";
				model.put("colour2", colour2);

			} else {
				String colour2 = "red";
				model.put("colour2", colour2);
			}
		} else {
			String colour2 = "black";
			model.put("colour2", colour2);
		}
		if (date3 != null) {
			String dat3 = dateFormat.format(date3);
			Date bdate3 = new SimpleDateFormat("d/M/yyyy").parse(dat3);

			if (bdate3.compareTo(end) < 0) {
				String colour3 = "green";
				model.put("colour3", colour3);

			} else {
				String colour3 = "red";
				model.put("colour3", colour3);
			}
		} else {
			String colour3 = "black";
			model.put("colour3", colour3);
		}
		if (date4 != null) {
			String dat4 = dateFormat.format(date4);
			Date bdate4 = new SimpleDateFormat("d/M/yyyy").parse(dat4);

			if (bdate4.compareTo(end) < 0) {
				String colour4 = "green";
				model.put("colour4", colour4);

			} else {
				String colour4 = "red";
				model.put("colour4", colour4);
			}
		} else {
			String colour4 = "black";
			model.put("colour4", colour4);
		}
		if (date5 != null) {

			String dat5 = dateFormat.format(date5);
			Date bdate5 = new SimpleDateFormat("d/M/yyyy").parse(dat5);
			if (bdate5.compareTo(end) < 0) {
				String colour5 = "green";
				model.put("colour5", colour5);

			} else {
				String colour5 = "red";
				model.put("colour5", colour5);
			}

		} else {
			String colour5 = "black";
			model.put("colour5", colour5);

		}

		if (date6 != null) {
			String dat6 = dateFormat.format(date6);
			Date bdate6 = new SimpleDateFormat("d/M/yyyy").parse(dat6);

			if (bdate6.compareTo(end) < 0) {
				String colour6 = "green";
				model.put("colour6", colour6);

			} else {
				String colour6 = "red";
				model.put("colour6", colour6);
			}
		} else {
			String colour6 = "black";
			model.put("colour6", colour6);
		}
		if (date7 != null) {
			String dat7 = dateFormat.format(date7);
			Date bdate7 = new SimpleDateFormat("d/M/yyyy").parse(dat7);
			if (bdate7.compareTo(end) < 0) {
				String colour7 = "green";
				model.put("colour7", colour7);

			} else {
				String colour7 = "red";
				model.put("colour7", colour7);
			}
		} else {
			String colour7 = "black";
			model.put("colour7", colour7);

		}
		if (date8 != null) {
			String dat8 = dateFormat.format(date8);
			Date bdate8 = new SimpleDateFormat("d/M/yyyy").parse(dat8);
			if (bdate8.compareTo(end) < 0) {
				String colour8 = "green";
				model.put("colour8", colour8);

			} else {
				String colour8 = "red";
				model.put("colour8", colour8);
			}
		} else {
			String colour8 = "black";
			model.put("colour8", colour8);
		}
		if (date9 != null) {
			String dat9 = dateFormat.format(date9);
			Date bdate9 = new SimpleDateFormat("d/M/yyyy").parse(dat9);
			if (bdate9.compareTo(end) < 0) {
				String colour9 = "green";
				model.put("colour9", colour9);

			} else {
				String colour9 = "red";
				model.put("colour9", colour9);
			}
		} else {
			String colour9 = "black";
			model.put("colour9", colour9);
		}
		if (date10 != null) {

			String dat10 = dateFormat.format(date10);
			Date bdate10 = new SimpleDateFormat("d/M/yyyy").parse(dat10);

			if (bdate10.compareTo(end) < 0) {
				String colour10 = "green";
				model.put("colour10", colour10);

			} else {
				String colour10 = "red";
				model.put("colour10", colour10);
			}
		} else {
			String colour10 = "black";
			model.put("colour10", colour10);
		}

		if (date11 != null) {
			String dat11 = dateFormat.format(date11);
			Date bdate11 = new SimpleDateFormat("d/M/yyyy").parse(dat11);

			if (bdate11.compareTo(end) < 0) {
				String colour11 = "green";
				model.put("colour11", colour11);

			} else {
				String colour11 = "red";
				model.put("colour11", colour11);
			}
		} else {
			String colour11 = "black";
			model.put("colour11", colour11);
		}

		buyerDiffBankEventForm.setCustomerName(buyerDiffBankEvent.getCustomerName());
		buyerDiffBankEventForm.setSupplier(buyerDiffBankEvent.getSupplier());
		buyerDiffBankEventForm.setSupplierBank(buyerDiffBankEvent.getSupplierBank());
		buyerDiffBankEventForm.setMasterKey(buyerDiffBankEvent.getMasterKey());
		buyerDiffBankEventForm.setGoods(buyerDiffBankEvent.getGoods());
		buyerDiffBankEventForm.setSanctionedAmount(buyerDiffBankEvent.getSanctionedAmount());
		buyerDiffBankEventForm.setUtilizedAmount(buyerDiffBankEvent.getUtilizedAmount());
		buyerDiffBankEventForm.setAvailableCost(buyerDiffBankEvent.getAvailableCost());
		buyerDiffBankEventForm.setDate1(buyerDiffBankEvent.getDate1());
		buyerDiffBankEventForm.setEvent1(buyerDiffBankEvent.getEvent1());
		buyerDiffBankEventForm.setFirst(buyerDiffBankEvent.getFirst());
		buyerDiffBankEventForm.setDate2(buyerDiffBankEvent.getDate2());
		buyerDiffBankEventForm.setEvent2(buyerDiffBankEvent.getEvent2());
		buyerDiffBankEventForm.setSecond(buyerDiffBankEvent.getSecond());
		buyerDiffBankEventForm.setDate3(buyerDiffBankEvent.getDate3());
		buyerDiffBankEventForm.setEvent3(buyerDiffBankEvent.getEvent3());
		buyerDiffBankEventForm.setThird(buyerDiffBankEvent.getThird());
		buyerDiffBankEventForm.setDate4(buyerDiffBankEvent.getDate4());
		buyerDiffBankEventForm.setEvent4(buyerDiffBankEvent.getEvent4());
		buyerDiffBankEventForm.setFourth(buyerDiffBankEvent.getFourth());
		buyerDiffBankEventForm.setDate5(buyerDiffBankEvent.getDate5());
		buyerDiffBankEventForm.setEvent5(buyerDiffBankEvent.getEvent5());
		buyerDiffBankEventForm.setFifth(buyerDiffBankEvent.getFifth());
		buyerDiffBankEventForm.setDate6(buyerDiffBankEvent.getDate6());
		buyerDiffBankEventForm.setEvent6(buyerDiffBankEvent.getEvent6());
		buyerDiffBankEventForm.setSix(buyerDiffBankEvent.getSix());
		buyerDiffBankEventForm.setDate7(buyerDiffBankEvent.getDate7());
		buyerDiffBankEventForm.setEvent7(buyerDiffBankEvent.getEvent7());
		buyerDiffBankEventForm.setSeven(buyerDiffBankEvent.getSeven());
		buyerDiffBankEventForm.setDate8(buyerDiffBankEvent.getDate8());
		buyerDiffBankEventForm.setEvent8(buyerDiffBankEvent.getEvent8());
		buyerDiffBankEventForm.setEight(buyerDiffBankEvent.getEight());
		buyerDiffBankEventForm.setDate9(buyerDiffBankEvent.getDate9());
		buyerDiffBankEventForm.setEvent9(buyerDiffBankEvent.getEvent9());
		buyerDiffBankEventForm.setNine(buyerDiffBankEvent.getNine());
		buyerDiffBankEventForm.setDate10(buyerDiffBankEvent.getDate10());
		buyerDiffBankEventForm.setEvent10(buyerDiffBankEvent.getEvent10());
		buyerDiffBankEventForm.setTen(buyerDiffBankEvent.getTen());
		buyerDiffBankEventForm.setDate11(buyerDiffBankEvent.getDate11());
		buyerDiffBankEventForm.setEvent11(buyerDiffBankEvent.getEvent11());
		buyerDiffBankEventForm.setElven(buyerDiffBankEvent.getElven());
		buyerDiffBankEventForm.setDate12(buyerDiffBankEvent.getDate12());
		buyerDiffBankEventForm.setEvent12(buyerDiffBankEvent.getEvent12());
		buyerDiffBankEventForm.setTwelve(buyerDiffBankEvent.getTwelve());
		buyerDiffBankEventForm.setDate13(buyerDiffBankEvent.getDate13());
		buyerDiffBankEventForm.setEvent13(buyerDiffBankEvent.getEvent13());
		buyerDiffBankEventForm.setThirteen(buyerDiffBankEvent.getThirteen());

		model.put("buyerDiffBankEventForm", buyerDiffBankEventForm);

		mav = new ModelAndView("bankSelectDiffEventsForCheckStatus", "model", model);

		return mav;
	}

	@RequestMapping(value = "/sellerSameViewAllEvents3", method = RequestMethod.GET)
	public ModelAndView checkStatusSellerSameEvents(ModelMap model, HttpServletRequest request,
													HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		List<SellerSameBankEvent> sellerSameBankEvents = sellerSameBankEventDAO.findCheckStatus().getResultList();

		if (sellerSameBankEvents != null && sellerSameBankEvents.size() > 0) {

			model.put("sellerSameBankEvents", sellerSameBankEvents);

			mav = new ModelAndView("bankCheckStatusSellerSameEvents", "model", model);

		} else {
			mav = new ModelAndView("noDataFound", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/selectSellerSameEventsForCheckStatus", method = RequestMethod.GET)
	public ModelAndView selectSellerSameEventsForCheckStatus(ModelMap model, @RequestParam("id") Long id,
															 HttpServletRequest request, RedirectAttributes attributes) throws ParseException {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		SellerSameBankEvent sellerSameBankEvent = sellerSameBankEventDAO.findEvent(id);

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

		DateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
		Date date1 = sellerSameBankEvent.getDate1();
		Date date2 = sellerSameBankEvent.getDate2();
		Date date3 = sellerSameBankEvent.getDate3();
		Date date4 = sellerSameBankEvent.getDate4();
		Date date5 = sellerSameBankEvent.getDate5();
		Date date6 = sellerSameBankEvent.getDate6();
		Date date7 = sellerSameBankEvent.getDate7();
		Date date8 = sellerSameBankEvent.getDate8();
		Date date9 = sellerSameBankEvent.getDate9();
		Date date10 = sellerSameBankEvent.getDate10();
		Date date11 = sellerSameBankEvent.getDate11();

		Date date12 = sellerSameBankEvent.getDate12();

		Date date13 = sellerSameBankEvent.getDate13();

		if (date2 != null) {

			long tenure2 = DateService.getDaysBetweenTwoDates(date1, date2);
			model.put("tenure2", tenure2);

		} else {
			long tenure2 = 0;

			model.put("tenure2", tenure2);

		}
		if (date3 != null) {
			if (date2 != null) {
				long tenure3 = DateService.getDaysBetweenTwoDates(date2, date3);
				model.put("tenure3", tenure3);
			}

		} else {
			long tenure3 = 0;

			model.put("tenure3", tenure3);
		}
		if (date4 != null) {
			if (date3 != null) {
				long tenure4 = DateService.getDaysBetweenTwoDates(date3, date4);
				model.put("tenure4", tenure4);
			}

		} else {
			long tenure4 = 0;

			model.put("tenure4", tenure4);

		}

		if (date5 != null) {
			if (date4 != null) {
				long tenure5 = DateService.getDaysBetweenTwoDates(date4, date5);
				model.put("tenure5", tenure5);

			}

		} else {
			long tenure5 = 0;

			model.put("tenure5", tenure5);

		}
		if (date6 != null) {
			if (date5 != null) {
				long tenure6 = DateService.getDaysBetweenTwoDates(date5, date6);

				model.put("tenure6", tenure6);
			}
		} else {
			long tenure6 = 0;
			model.put("tenure6", tenure6);
		}
		if (date7 != null) {
			if (date6 != null) {
				long tenure7 = DateService.getDaysBetweenTwoDates(date6, date7);

				model.put("tenure7", tenure7);
			}
		} else {
			long tenure7 = 0;
			model.put("tenure7", tenure7);
		}
		if (date8 != null) {
			if (date7 != null) {
				long tenure8 = DateService.getDaysBetweenTwoDates(date7, date8);

				model.put("tenure8", tenure8);
			}
		} else {
			long tenure8 = 0;
			model.put("tenure8", tenure8);
		}

		if (date9 != null) {
			if (date8 != null) {
				long tenure9 = DateService.getDaysBetweenTwoDates(date8, date9);

				model.put("tenure9", tenure9);
			}
		} else {
			long tenure9 = 0;
			model.put("tenure9", tenure9);
		}
		if (date10 != null) {
			if (date9 != null) {
				long tenure10 = DateService.getDaysBetweenTwoDates(date9, date10);

				model.put("tenure10", tenure10);
			}
		} else {
			long tenure10 = 0;
			model.put("tenure10", tenure10);
		}
		if (date11 != null) {
			if (date10 != null) {
				long tenure11 = DateService.getDaysBetweenTwoDates(date10, date11);

				model.put("tenure11", tenure11);
			}
		} else {
			long tenure11 = 0;
			model.put("tenure11", tenure11);
		}
		if (date12 != null) {
			if (date11 != null) {
				long tenure12 = DateService.getDaysBetweenTwoDates(date11, date12);

				model.put("tenure12", tenure12);
			}
		} else {
			long tenure12 = 0;
			model.put("tenure12", tenure12);
		}

		if (date13 != null) {
			if (date12 != null) {
				long tenure13 = DateService.getDaysBetweenTwoDates(date12, date13);
				model.put("tenure13", tenure13);
			}
		} else {
			long tenure13 = 0;
			model.put("tenure13", tenure13);
		}

		int range = DateService.getDaysBetweenTwoDates(date1, date13);

		double mean = Math.round(range / 13);

		double variance = Math.round(((range - mean) * (range - mean)) / 13);

		double standardDeviation = Math.round(Math.sqrt(variance));

		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		Date bDate1;
		Date bDate2;
		arrayList.add(1);
		for (int i = 2; i < 14; i++) {
			bDate1 = DateService.getSellerSameEventDate(sellerSameBankEvent, i - 1);
			bDate2 = DateService.getSellerSameEventDate(sellerSameBankEvent, i);
			if (bDate1 != null && bDate2 != null) {
				int noOfDays = DateService.getDaysBetweenTwoDates(bDate1, bDate2);

				arrayList.add(noOfDays);
			}
		}
		Collections.sort(arrayList);

		double median = 0.0;

		if (arrayList.size() % 2 == 0) {
			median = Math
					.round((arrayList.get((arrayList.size() / 2)) + arrayList.get((arrayList.size() / 2 - 1))) / 2);
		} else {
			median = Math.round(arrayList.get((arrayList.size() / 2)));
		}

		int mode = DateService.getModeElement(arrayList);
		model.put("mode", mode);
		model.put("median", median);

		model.put("range", range);
		model.put("mean", mean);
		model.put("variance", variance);
		model.put("standardDeviation", standardDeviation);

		Date date = DateService.loginDate;
		String sysdat = dateFormat.format(date);

		Date end = new SimpleDateFormat("d/M/yyyy").parse(sysdat);

		if (date12 != null) {
			String dat12 = dateFormat.format(date12);
			Date bdate12 = new SimpleDateFormat("d/M/yyyy").parse(dat12);
			if (bdate12.compareTo(end) < 0) {
				String colour12 = "green";
				model.put("colour12", colour12);

			} else {
				String colour12 = "red";
				model.put("colour12", colour12);
			}
		} else {
			String colour12 = "black";
			model.put("colour12", colour12);
		}
		if (date13 != null) {
			String dat13 = dateFormat.format(date13);
			Date bdate13 = new SimpleDateFormat("d/M/yyyy").parse(dat13);
			if (bdate13.compareTo(end) < 0) {
				String colour13 = "green";
				model.put("colour13", colour13);

			} else {
				String colour13 = "red";
				model.put("colour13", colour13);
			}
		} else {
			String colour13 = "black";
			model.put("colour13", colour13);
		}

		if (date1 != null) {
			String dat1 = dateFormat.format(date1);
			Date bdate1 = new SimpleDateFormat("d/M/yyyy").parse(dat1);
			if (bdate1.compareTo(end) < 0) {
				String colour1 = "green";
				model.put("colour1", colour1);

			} else {
				String colour1 = "red";
				model.put("colour1", colour1);
			}
		} else {
			String colour1 = "black";
			model.put("colour1", colour1);
		}
		if (date2 != null) {

			String dat2 = dateFormat.format(date2);
			Date bdate2 = new SimpleDateFormat("d/M/yyyy").parse(dat2);

			if (bdate2.compareTo(end) < 0) {
				String colour2 = "green";
				model.put("colour2", colour2);

			} else {
				String colour2 = "red";
				model.put("colour2", colour2);
			}
		} else {
			String colour2 = "black";
			model.put("colour2", colour2);
		}
		if (date3 != null) {
			String dat3 = dateFormat.format(date3);
			Date bdate3 = new SimpleDateFormat("d/M/yyyy").parse(dat3);

			if (bdate3.compareTo(end) < 0) {
				String colour3 = "green";
				model.put("colour3", colour3);

			} else {
				String colour3 = "red";
				model.put("colour3", colour3);
			}
		} else {
			String colour3 = "black";
			model.put("colour3", colour3);
		}
		if (date4 != null) {
			String dat4 = dateFormat.format(date4);
			Date bdate4 = new SimpleDateFormat("d/M/yyyy").parse(dat4);

			if (bdate4.compareTo(end) < 0) {
				String colour4 = "green";
				model.put("colour4", colour4);

			} else {
				String colour4 = "red";
				model.put("colour4", colour4);
			}
		} else {
			String colour4 = "black";
			model.put("colour4", colour4);
		}
		if (date5 != null) {

			String dat5 = dateFormat.format(date5);
			Date bdate5 = new SimpleDateFormat("d/M/yyyy").parse(dat5);
			if (bdate5.compareTo(end) < 0) {
				String colour5 = "green";
				model.put("colour5", colour5);

			} else {
				String colour5 = "red";
				model.put("colour5", colour5);
			}

		} else {
			String colour5 = "black";
			model.put("colour5", colour5);

		}

		if (date6 != null) {
			String dat6 = dateFormat.format(date6);
			Date bdate6 = new SimpleDateFormat("d/M/yyyy").parse(dat6);

			if (bdate6.compareTo(end) < 0) {
				String colour6 = "green";
				model.put("colour6", colour6);

			} else {
				String colour6 = "red";
				model.put("colour6", colour6);
			}
		} else {
			String colour6 = "black";
			model.put("colour6", colour6);
		}
		if (date7 != null) {
			String dat7 = dateFormat.format(date7);
			Date bdate7 = new SimpleDateFormat("d/M/yyyy").parse(dat7);
			if (bdate7.compareTo(end) < 0) {
				String colour7 = "green";
				model.put("colour7", colour7);

			} else {
				String colour7 = "red";
				model.put("colour7", colour7);
			}
		} else {
			String colour7 = "black";
			model.put("colour7", colour7);

		}
		if (date8 != null) {
			String dat8 = dateFormat.format(date8);
			Date bdate8 = new SimpleDateFormat("d/M/yyyy").parse(dat8);
			if (bdate8.compareTo(end) < 0) {
				String colour8 = "green";
				model.put("colour8", colour8);

			} else {
				String colour8 = "red";
				model.put("colour8", colour8);
			}
		} else {
			String colour8 = "black";
			model.put("colour8", colour8);
		}
		if (date9 != null) {
			String dat9 = dateFormat.format(date9);
			Date bdate9 = new SimpleDateFormat("d/M/yyyy").parse(dat9);
			if (bdate9.compareTo(end) < 0) {
				String colour9 = "green";
				model.put("colour9", colour9);

			} else {
				String colour9 = "red";
				model.put("colour9", colour9);
			}
		} else {
			String colour9 = "black";
			model.put("colour9", colour9);
		}
		if (date10 != null) {

			String dat10 = dateFormat.format(date10);
			Date bdate10 = new SimpleDateFormat("d/M/yyyy").parse(dat10);

			if (bdate10.compareTo(end) < 0) {
				String colour10 = "green";
				model.put("colour10", colour10);

			} else {
				String colour10 = "red";
				model.put("colour10", colour10);
			}
		} else {
			String colour10 = "black";
			model.put("colour10", colour10);
		}

		if (date11 != null) {
			String dat11 = dateFormat.format(date11);
			Date bdate11 = new SimpleDateFormat("d/M/yyyy").parse(dat11);

			if (bdate11.compareTo(end) < 0) {
				String colour11 = "green";
				model.put("colour11", colour11);

			} else {
				String colour11 = "red";
				model.put("colour11", colour11);
			}
		} else {
			String colour11 = "black";
			model.put("colour11", colour11);
		}

		sellerSameBankEventForm.setCustomerName(sellerSameBankEvent.getCustomerName());
		sellerSameBankEventForm.setBuyer(sellerSameBankEvent.getBuyer());
		sellerSameBankEventForm.setBuyerBank(sellerSameBankEvent.getBuyerBank());
		sellerSameBankEventForm.setMasterKey(sellerSameBankEvent.getMasterKey());
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
		sellerSameBankEventForm.setDate12(sellerSameBankEvent.getDate12());
		sellerSameBankEventForm.setEvent12(sellerSameBankEvent.getEvent12());
		sellerSameBankEventForm.setTwelve(sellerSameBankEvent.getTwelve());
		sellerSameBankEventForm.setDate13(sellerSameBankEvent.getDate13());
		sellerSameBankEventForm.setEvent13(sellerSameBankEvent.getEvent13());
		sellerSameBankEventForm.setThirteen(sellerSameBankEvent.getThirteen());
		model.put("sellerSameBankEventForm", sellerSameBankEventForm);

		return new ModelAndView("bankSelectSellerSameEventsForCheckStatus", "model", model);

	}

	@RequestMapping(value = "/sellerDiffViewAllEvents3", method = RequestMethod.GET)
	public ModelAndView checkStatusSellerDiffEvents(ModelMap model, HttpServletRequest request,
													HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		List<SellerDiffBankEvent> sellerDiffBankEvents = sellerDiffBankEventDAO.findCheckStatus().getResultList();

		if (sellerDiffBankEvents != null && sellerDiffBankEvents.size() > 0) {

			model.put("sellerDiffBankEvents", sellerDiffBankEvents);

			mav = new ModelAndView("bankCheckStatusSellerDiffEvents", "model", model);

		} else {
			mav = new ModelAndView("noDataFound", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/selectSellerDiffEventsForCheckStatus", method = RequestMethod.GET)
	public ModelAndView selectSellerDiffEventsForCheckStatus(ModelMap model, @RequestParam("id") Long id,
															 HttpServletRequest request, RedirectAttributes attributes) throws ParseException {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

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

		DateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
		Date date1 = sellerDiffBankEvent.getDate1();
		Date date2 = sellerDiffBankEvent.getDate2();
		Date date3 = sellerDiffBankEvent.getDate3();
		Date date4 = sellerDiffBankEvent.getDate4();
		Date date5 = sellerDiffBankEvent.getDate5();
		Date date6 = sellerDiffBankEvent.getDate6();
		Date date7 = sellerDiffBankEvent.getDate7();
		Date date8 = sellerDiffBankEvent.getDate8();
		Date date9 = sellerDiffBankEvent.getDate9();
		Date date10 = sellerDiffBankEvent.getDate10();
		Date date11 = sellerDiffBankEvent.getDate11();

		Date date12 = sellerDiffBankEvent.getDate12();

		Date date13 = sellerDiffBankEvent.getDate13();

		Date date14 = sellerDiffBankEvent.getDate14();

		Date date15 = sellerDiffBankEvent.getDate15();

		Date date16 = sellerDiffBankEvent.getDate16();

		if (date2 != null) {

			long tenure2 = DateService.getDaysBetweenTwoDates(date1, date2);
			model.put("tenure2", tenure2);

		} else {
			long tenure2 = 0;

			model.put("tenure2", tenure2);

		}
		if (date3 != null) {
			if (date2 != null) {
				long tenure3 = DateService.getDaysBetweenTwoDates(date2, date3);
				model.put("tenure3", tenure3);
			}

		} else {
			long tenure3 = 0;

			model.put("tenure3", tenure3);
		}
		if (date4 != null) {
			if (date3 != null) {
				long tenure4 = DateService.getDaysBetweenTwoDates(date3, date4);
				model.put("tenure4", tenure4);
			}

		} else {
			long tenure4 = 0;

			model.put("tenure4", tenure4);

		}

		if (date5 != null) {
			if (date4 != null) {
				long tenure5 = DateService.getDaysBetweenTwoDates(date4, date5);
				model.put("tenure5", tenure5);

			}

		} else {
			long tenure5 = 0;

			model.put("tenure5", tenure5);

		}
		if (date6 != null) {
			if (date5 != null) {
				long tenure6 = DateService.getDaysBetweenTwoDates(date5, date6);

				model.put("tenure6", tenure6);
			}
		} else {
			long tenure6 = 0;
			model.put("tenure6", tenure6);
		}
		if (date7 != null) {
			if (date6 != null) {
				long tenure7 = DateService.getDaysBetweenTwoDates(date6, date7);

				model.put("tenure7", tenure7);
			}
		} else {
			long tenure7 = 0;
			model.put("tenure7", tenure7);
		}
		if (date8 != null) {
			if (date7 != null) {
				long tenure8 = DateService.getDaysBetweenTwoDates(date7, date8);

				model.put("tenure8", tenure8);
			}
		} else {
			long tenure8 = 0;
			model.put("tenure8", tenure8);
		}

		if (date9 != null) {
			if (date8 != null) {
				long tenure9 = DateService.getDaysBetweenTwoDates(date8, date9);

				model.put("tenure9", tenure9);
			}
		} else {
			long tenure9 = 0;
			model.put("tenure9", tenure9);
		}
		if (date10 != null) {
			if (date9 != null) {
				long tenure10 = DateService.getDaysBetweenTwoDates(date9, date10);

				model.put("tenure10", tenure10);
			}
		} else {
			long tenure10 = 0;
			model.put("tenure10", tenure10);
		}
		if (date11 != null) {
			if (date10 != null) {
				long tenure11 = DateService.getDaysBetweenTwoDates(date10, date11);

				model.put("tenure11", tenure11);
			}
		} else {
			long tenure11 = 0;
			model.put("tenure11", tenure11);
		}
		if (date12 != null) {
			if (date11 != null) {
				long tenure12 = DateService.getDaysBetweenTwoDates(date11, date12);

				model.put("tenure12", tenure12);
			}
		} else {
			long tenure12 = 0;
			model.put("tenure12", tenure12);
		}

		if (date13 != null) {
			if (date12 != null) {
				long tenure13 = DateService.getDaysBetweenTwoDates(date12, date13);
				model.put("tenure13", tenure13);
			}
		} else {
			long tenure13 = 0;
			model.put("tenure13", tenure13);
		}

		if (date14 != null) {
			if (date13 != null) {
				long tenure14 = DateService.getDaysBetweenTwoDates(date13, date14);
				model.put("tenure14", tenure14);
			}
		} else {
			long tenure14 = 0;
			model.put("tenure14", tenure14);
		}
		if (date15 != null) {
			if (date14 != null) {
				long tenure15 = DateService.getDaysBetweenTwoDates(date14, date15);
				model.put("tenure15", tenure15);
			}
		} else {
			long tenure15 = 0;
			model.put("tenure15", tenure15);
		}
		if (date16 != null) {
			if (date15 != null) {
				long tenure16 = DateService.getDaysBetweenTwoDates(date15, date16);
				model.put("tenure16", tenure16);
			}
		} else {
			long tenure16 = 0;
			model.put("tenure16", tenure16);
		}

		int range = DateService.getDaysBetweenTwoDates(date1, date16);

		double mean = Math.round(range / 16);

		double variance = Math.round(((range - mean) * (range - mean)) / 16);

		double standardDeviation = Math.round(Math.sqrt(variance));

		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		Date bDate1;
		Date bDate2;
		arrayList.add(1);
		for (int i = 2; i < 17; i++) {
			bDate1 = DateService.getSellerDiffEventDate(sellerDiffBankEvent, i - 1);
			bDate2 = DateService.getSellerDiffEventDate(sellerDiffBankEvent, i);
			if (bDate1 != null && bDate2 != null) {
				int noOfDays = DateService.getDaysBetweenTwoDates(bDate1, bDate2);
				arrayList.add(noOfDays);
			}
		}
		Collections.sort(arrayList);

		double median = 0.0;

		if (arrayList.size() % 2 == 0) {
			median = Math
					.round((arrayList.get((arrayList.size() / 2)) + arrayList.get((arrayList.size() / 2 - 1))) / 2);
		} else {
			median = Math.round(arrayList.get((arrayList.size() / 2)));
		}

		int mode = DateService.getModeElement(arrayList);
		model.put("mode", mode);
		model.put("median", median);
		model.put("range", range);
		model.put("mean", mean);
		model.put("variance", variance);
		model.put("standardDeviation", standardDeviation);

		Date date = DateService.loginDate;
		String sysdat = dateFormat.format(date);

		Date end = new SimpleDateFormat("d/M/yyyy").parse(sysdat);

		if (date16 != null) {
			String dat16 = dateFormat.format(date16);
			Date bdate16 = new SimpleDateFormat("d/M/yyyy").parse(dat16);
			if (bdate16.compareTo(end) < 0) {
				String colour16 = "green";
				model.put("colour16", colour16);

			} else {
				String colour16 = "red";
				model.put("colour16", colour16);
			}
		} else {
			String colour16 = "black";
			model.put("colour16", colour16);
		}

		if (date15 != null) {
			String dat15 = dateFormat.format(date15);
			Date bdate15 = new SimpleDateFormat("d/M/yyyy").parse(dat15);
			if (bdate15.compareTo(end) < 0) {
				String colour15 = "green";
				model.put("colour15", colour15);

			} else {
				String colour15 = "red";
				model.put("colour15", colour15);
			}
		} else {
			String colour15 = "black";
			model.put("colour15", colour15);
		}

		if (date14 != null) {
			String dat14 = dateFormat.format(date14);
			Date bdate14 = new SimpleDateFormat("d/M/yyyy").parse(dat14);
			if (bdate14.compareTo(end) < 0) {
				String colour14 = "green";
				model.put("colour14", colour14);

			} else {
				String colour14 = "red";
				model.put("colour14", colour14);
			}
		} else {
			String colour14 = "black";
			model.put("colour14", colour14);
		}

		if (date12 != null) {
			String dat12 = dateFormat.format(date12);
			Date bdate12 = new SimpleDateFormat("d/M/yyyy").parse(dat12);
			if (bdate12.compareTo(end) < 0) {
				String colour12 = "green";
				model.put("colour12", colour12);

			} else {
				String colour12 = "red";
				model.put("colour12", colour12);
			}
		} else {
			String colour12 = "black";
			model.put("colour12", colour12);
		}
		if (date13 != null) {
			String dat13 = dateFormat.format(date13);
			Date bdate13 = new SimpleDateFormat("d/M/yyyy").parse(dat13);
			if (bdate13.compareTo(end) < 0) {
				String colour13 = "green";
				model.put("colour13", colour13);

			} else {
				String colour13 = "red";
				model.put("colour13", colour13);
			}
		} else {
			String colour13 = "black";
			model.put("colour13", colour13);
		}

		if (date1 != null) {
			String dat1 = dateFormat.format(date1);
			Date bdate1 = new SimpleDateFormat("d/M/yyyy").parse(dat1);
			if (bdate1.compareTo(end) < 0) {
				String colour1 = "green";
				model.put("colour1", colour1);

			} else {
				String colour1 = "red";
				model.put("colour1", colour1);
			}
		} else {
			String colour1 = "black";
			model.put("colour1", colour1);
		}
		if (date2 != null) {

			String dat2 = dateFormat.format(date2);
			Date bdate2 = new SimpleDateFormat("d/M/yyyy").parse(dat2);

			if (bdate2.compareTo(end) < 0) {
				String colour2 = "green";
				model.put("colour2", colour2);

			} else {
				String colour2 = "red";
				model.put("colour2", colour2);
			}
		} else {
			String colour2 = "black";
			model.put("colour2", colour2);
		}
		if (date3 != null) {
			String dat3 = dateFormat.format(date3);
			Date bdate3 = new SimpleDateFormat("d/M/yyyy").parse(dat3);

			if (bdate3.compareTo(end) < 0) {
				String colour3 = "green";
				model.put("colour3", colour3);

			} else {
				String colour3 = "red";
				model.put("colour3", colour3);
			}
		} else {
			String colour3 = "black";
			model.put("colour3", colour3);
		}
		if (date4 != null) {
			String dat4 = dateFormat.format(date4);
			Date bdate4 = new SimpleDateFormat("d/M/yyyy").parse(dat4);

			if (bdate4.compareTo(end) < 0) {
				String colour4 = "green";
				model.put("colour4", colour4);

			} else {
				String colour4 = "red";
				model.put("colour4", colour4);
			}
		} else {
			String colour4 = "black";
			model.put("colour4", colour4);
		}
		if (date5 != null) {

			String dat5 = dateFormat.format(date5);
			Date bdate5 = new SimpleDateFormat("d/M/yyyy").parse(dat5);
			if (bdate5.compareTo(end) < 0) {
				String colour5 = "green";
				model.put("colour5", colour5);

			} else {
				String colour5 = "red";
				model.put("colour5", colour5);
			}

		} else {
			String colour5 = "black";
			model.put("colour5", colour5);

		}

		if (date6 != null) {
			String dat6 = dateFormat.format(date6);
			Date bdate6 = new SimpleDateFormat("d/M/yyyy").parse(dat6);

			if (bdate6.compareTo(end) < 0) {
				String colour6 = "green";
				model.put("colour6", colour6);

			} else {
				String colour6 = "red";
				model.put("colour6", colour6);
			}
		} else {
			String colour6 = "black";
			model.put("colour6", colour6);
		}
		if (date7 != null) {
			String dat7 = dateFormat.format(date7);
			Date bdate7 = new SimpleDateFormat("d/M/yyyy").parse(dat7);
			if (bdate7.compareTo(end) < 0) {
				String colour7 = "green";
				model.put("colour7", colour7);

			} else {
				String colour7 = "red";
				model.put("colour7", colour7);
			}
		} else {
			String colour7 = "black";
			model.put("colour7", colour7);

		}
		if (date8 != null) {
			String dat8 = dateFormat.format(date8);
			Date bdate8 = new SimpleDateFormat("d/M/yyyy").parse(dat8);
			if (bdate8.compareTo(end) < 0) {
				String colour8 = "green";
				model.put("colour8", colour8);

			} else {
				String colour8 = "red";
				model.put("colour8", colour8);
			}
		} else {
			String colour8 = "black";
			model.put("colour8", colour8);
		}
		if (date9 != null) {
			String dat9 = dateFormat.format(date9);
			Date bdate9 = new SimpleDateFormat("d/M/yyyy").parse(dat9);
			if (bdate9.compareTo(end) < 0) {
				String colour9 = "green";
				model.put("colour9", colour9);

			} else {
				String colour9 = "red";
				model.put("colour9", colour9);
			}
		} else {
			String colour9 = "black";
			model.put("colour9", colour9);
		}
		if (date10 != null) {

			String dat10 = dateFormat.format(date10);
			Date bdate10 = new SimpleDateFormat("d/M/yyyy").parse(dat10);

			if (bdate10.compareTo(end) < 0) {
				String colour10 = "green";
				model.put("colour10", colour10);

			} else {
				String colour10 = "red";
				model.put("colour10", colour10);
			}
		} else {
			String colour10 = "black";
			model.put("colour10", colour10);
		}

		if (date11 != null) {
			String dat11 = dateFormat.format(date11);
			Date bdate11 = new SimpleDateFormat("d/M/yyyy").parse(dat11);

			if (bdate11.compareTo(end) < 0) {
				String colour11 = "green";
				model.put("colour11", colour11);

			} else {
				String colour11 = "red";
				model.put("colour11", colour11);
			}
		} else {
			String colour11 = "black";
			model.put("colour11", colour11);
		}

		sellerDiffBankEventForm.setCustomerName(sellerDiffBankEvent.getCustomerName());
		sellerDiffBankEventForm.setBuyer(sellerDiffBankEvent.getBuyer());
		sellerDiffBankEventForm.setBuyerBank(sellerDiffBankEvent.getBuyerBank());
		sellerDiffBankEventForm.setMasterKey(sellerDiffBankEvent.getMasterKey());
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
		sellerDiffBankEventForm.setDate12(sellerDiffBankEvent.getDate12());
		sellerDiffBankEventForm.setEvent12(sellerDiffBankEvent.getEvent12());
		sellerDiffBankEventForm.setTwelve(sellerDiffBankEvent.getTwelve());
		sellerDiffBankEventForm.setDate13(sellerDiffBankEvent.getDate13());
		sellerDiffBankEventForm.setEvent13(sellerDiffBankEvent.getEvent13());
		sellerDiffBankEventForm.setThirteen(sellerDiffBankEvent.getThirteen());

		sellerDiffBankEventForm.setDate14(sellerDiffBankEvent.getDate14());
		sellerDiffBankEventForm.setEvent14(sellerDiffBankEvent.getEvent14());
		sellerDiffBankEventForm.setFourteen(sellerDiffBankEvent.getFourteen());

		sellerDiffBankEventForm.setDate15(sellerDiffBankEvent.getDate15());
		sellerDiffBankEventForm.setEvent15(sellerDiffBankEvent.getEvent15());
		sellerDiffBankEventForm.setFifteen(sellerDiffBankEvent.getFifteen());

		sellerDiffBankEventForm.setDate16(sellerDiffBankEvent.getDate16());
		sellerDiffBankEventForm.setEvent16(sellerDiffBankEvent.getEvent16());
		sellerDiffBankEventForm.setSixteen(sellerDiffBankEvent.getSixteen());

		model.put("sellerDiffBankEventForm", sellerDiffBankEventForm);

		mav = new ModelAndView("bankSelectSellerDiffEventsForCheckStatus", "model", model);

		return mav;

	}

	@RequestMapping(value = "/invoiceDocumnetListAll", method = RequestMethod.GET)
	public ModelAndView InvoiceDocumnetList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		Collection<InvoiceUpload> docList = invoiceUploadDAO.getList();

		model.put("user", user);
		if (docList != null && docList.size() > 0) {
			model.put("docList", docList);

			return new ModelAndView("invoiceDocumnetListAll", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/poDocumentListAll", method = RequestMethod.GET)
	public ModelAndView poDocumentListAll(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		Collection<PoUpload> docList = poUploadDAO.getList();

		model.put("user", user);
		if (docList != null && docList.size() > 0) {
			model.put("docList", docList);

			return new ModelAndView("poDocumentListAll", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/masterKeyApprovedList", method = RequestMethod.GET)
	public ModelAndView createSellerNewEvent(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<MasterPlan> masterPlanList = masterPlanDAO.getApprovedMasterKey().getResultList();

		masterPlanForm.setMasterPlanList(masterPlanList);

		if (masterPlanList.size() == 0) {

			mav = new ModelAndView("noDataFound", "model", model);
		} else {

			model.put("masterPlanForm", masterPlanForm);

			mav = new ModelAndView("masterKeyApprovedList", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/graphBuyerSamebankCostPage", method = RequestMethod.POST)
	public ModelAndView eventsForInvoice(ModelMap model, @ModelAttribute MasterPlanForm masterPlanForm,
										 HttpServletRequest request, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<BuyerSameBankEvent> buyerSameBankEventForm = buyerSameBankeventDAO
				.checkMasterKeyBuyerSame(masterPlanForm.getMasterKey()).getResultList();

		List<BuyerDiffBankEvent> buyerDiffBankEventForm = buyerDiffBankEventDAO
				.checkMasterKeyBuyerDiffrent(masterPlanForm.getMasterKey()).getResultList();

		if (buyerSameBankEventForm != null && buyerSameBankEventForm.size() > 0
				|| buyerDiffBankEventForm != null && buyerDiffBankEventForm.size() > 0) {
			List<BuyerSameBankEvent> sameBank = buyerSameBankeventDAO.findAllEvents(masterPlanForm.getMasterKey())
					.getResultList();
			List<BarGraphJson> jsonList = new ArrayList<BarGraphJson>();
			for (BuyerSameBankEvent buyer : sameBank) {
				BarGraphJson json = new BarGraphJson();
				json.setY(buyer.getAvailableCost());
				json.setLabel(buyer.getSupplier());
				jsonList.add(json);
			}

			List<BuyerDiffBankEvent> diffbuyerBankcost = buyerDiffBankEventDAO
					.findAllEvents(masterPlanForm.getMasterKey()).getResultList();
			List<BarGraphJson> diffJsonList = new ArrayList<BarGraphJson>();
			for (BuyerDiffBankEvent buyer : diffbuyerBankcost) {
				BarGraphJson json = new BarGraphJson();
				json.setY(buyer.getAvailableCost());
				json.setLabel(buyer.getSupplier());
				diffJsonList.add(json);
			}

			jsonList.addAll(diffJsonList);
			Gson gson = new Gson();
			String json = gson.toJson(jsonList);

			model.put("user", user);
			model.put("sameBank", sameBank);
			model.put("json", json);

			return new ModelAndView("graphBuyerSamebankCostPage", "model", model);

		} else {
			attributes.addFlashAttribute("success", "Master Key Not Found");

			return new ModelAndView("redirect:masterKeyApprovedList");
		}

	}

	@RequestMapping(value = "/masterKeyApprovedDateList", method = RequestMethod.GET)
	public ModelAndView createmasterKeyApprovedDateList(ModelMap model, HttpServletRequest request,
														HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<MasterPlan> masterPlanList = masterPlanDAO.getApprovedMasterKey().getResultList();

		masterPlanForm.setMasterPlanList(masterPlanList);

		if (masterPlanList.size() == 0) {

			mav = new ModelAndView("noDataFound", "model", model);
		} else {

			model.put("masterPlanForm", masterPlanForm);

			mav = new ModelAndView("masterKeyApprovedDateList", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/graphBuyerSamebankDatePage", method = RequestMethod.POST)
	public ModelAndView graphBuyerSamebankDatePage(ModelMap model, @ModelAttribute MasterPlanForm masterPlanForm,
												   HttpServletRequest request, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		List<BuyerSameBankEvent> buyerSameBankEventForm = buyerSameBankeventDAO
				.checkMasterKeyBuyerSame(masterPlanForm.getMasterKey()).getResultList();

		List<BuyerDiffBankEvent> buyerDiffBankEventForm = buyerDiffBankEventDAO
				.checkMasterKeyBuyerDiffrent(masterPlanForm.getMasterKey()).getResultList();

		if (buyerSameBankEventForm != null && buyerSameBankEventForm.size() > 0
				|| buyerDiffBankEventForm != null && buyerDiffBankEventForm.size() > 0) {
			List<BuyerSameBankEvent> sameBank = buyerSameBankeventDAO.findAllEvents(masterPlanForm.getMasterKey())
					.getResultList();
			List<BarGraphJsonDate> jsonList = new ArrayList<BarGraphJsonDate>();
			for (BuyerSameBankEvent buyer : sameBank) {
				BarGraphJsonDate json = new BarGraphJsonDate();
				json.setY(buyer.getAvailableCost());
				json.setLabel(buyer.getDate9());

				jsonList.add(json);

			}

			List<BuyerDiffBankEvent> diffbuyerBankcost = buyerDiffBankEventDAO
					.findAllEvents(masterPlanForm.getMasterKey()).getResultList();
			List<BarGraphJsonDate> diffjsonList = new ArrayList<BarGraphJsonDate>();
			for (BuyerDiffBankEvent buyer : diffbuyerBankcost) {
				BarGraphJsonDate json = new BarGraphJsonDate();
				json.setY(buyer.getAvailableCost());
				json.setLabel(buyer.getDate9());

				diffjsonList.add(json);
			}

			jsonList.addAll(diffjsonList);
			Gson gson = new Gson();
			String json = gson.toJson(jsonList);

			model.put("user", user);
			model.put("json", json);

			return new ModelAndView("graphBuyerSamebankDatePage", "model", model);

		} else {
			attributes.addFlashAttribute("success", "Master Key Not Found");

			return new ModelAndView("redirect:masterKeyApprovedDateList");
		}
	}

	// seller side cost

	@RequestMapping(value = "/masterKeyApprovedSellercostList", method = RequestMethod.GET)
	public ModelAndView masterKeyApprovedSellercostList(ModelMap model, HttpServletRequest request,
														HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<MasterPlan> masterPlanList = masterPlanDAO.getApprovedMasterKey().getResultList();

		masterPlanForm.setMasterPlanList(masterPlanList);

		if (masterPlanList.size() == 0) {

			mav = new ModelAndView("noDataFound", "model", model);
		} else {

			model.put("masterPlanForm", masterPlanForm);

			mav = new ModelAndView("masterKeyApprovedSellercostList", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/graphSellerdiffbankcostPage", method = RequestMethod.POST)
	public ModelAndView graphSellerdiffbankcostPage(ModelMap model, @ModelAttribute MasterPlanForm masterPlanForm,
													HttpServletRequest request, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<SellerSameBankEvent> sellerSameBankEventtForm = sellerSameBankEventDAO
				.checkMasterKeySellerSame(masterPlanForm.getMasterKey()).getResultList();

		List<SellerDiffBankEvent> sellerDiffBankEventForm = sellerDiffBankEventDAO
				.checkMasterKeySellerDiffrent(masterPlanForm.getMasterKey()).getResultList();

		if (sellerSameBankEventtForm != null && sellerSameBankEventtForm.size() > 0
				|| sellerDiffBankEventForm != null && sellerDiffBankEventForm.size() > 0) {
			List<SellerSameBankEvent> sellercost = sellerSameBankEventDAO.findAllEvents(masterPlanForm.getMasterKey())
					.getResultList();
			List<BarGraphJsonsameBanksellercost> jsonList = new ArrayList<BarGraphJsonsameBanksellercost>();
			for (SellerSameBankEvent buyer : sellercost) {
				BarGraphJsonsameBanksellercost json = new BarGraphJsonsameBanksellercost();
				json.setY(buyer.getCost());
				json.setLabel(buyer.getBuyer());
				jsonList.add(json);

			}

			List<SellerDiffBankEvent> sellerdiffbankcost = sellerDiffBankEventDAO
					.findAllEvents(masterPlanForm.getMasterKey()).getResultList();
			List<BarGraphJsonsameBanksellercost> jsonListseller = new ArrayList<BarGraphJsonsameBanksellercost>();

			for (SellerDiffBankEvent buyer : sellerdiffbankcost) {
				BarGraphJsonsameBanksellercost json = new BarGraphJsonsameBanksellercost();
				json.setY(buyer.getCost());
				json.setLabel(buyer.getBuyer());
				jsonListseller.add(json);
			}

			jsonList.addAll(jsonListseller);
			Gson gson = new Gson();
			String json = gson.toJson(jsonList);

			model.put("user", user);
			model.put("sellercost", sellercost);
			model.put("json", json);

			return new ModelAndView("graphSellerdiffbankcostPage", "model", model);

		} else {
			attributes.addFlashAttribute("success", "Master Key Not Found");

			return new ModelAndView("redirect:masterKeyApprovedSellercostList");
		}

	}

	// seller Date
	@RequestMapping(value = "/masterKeyApprovedSellerList", method = RequestMethod.GET)
	public ModelAndView masterKeyApprovedSellerList(ModelMap model, HttpServletRequest request,
													HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<MasterPlan> masterPlanList = masterPlanDAO.getApprovedMasterKey().getResultList();

		masterPlanForm.setMasterPlanList(masterPlanList);

		if (masterPlanList.size() == 0) {

			mav = new ModelAndView("noDataFound", "model", model);
		} else {

			model.put("masterPlanForm", masterPlanForm);

			mav = new ModelAndView("masterKeyApprovedSellerList", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/graphSellerdiffbankDatePage", method = RequestMethod.POST)
	public ModelAndView graphSellerSamebankDatePage(ModelMap model, @ModelAttribute MasterPlanForm masterPlanForm,
													HttpServletRequest request, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		List<SellerSameBankEvent> sellerSameBankEventtForm = sellerSameBankEventDAO
				.checkMasterKeySellerSame(masterPlanForm.getMasterKey()).getResultList();

		List<SellerDiffBankEvent> sellerDiffBankEventForm = sellerDiffBankEventDAO
				.checkMasterKeySellerDiffrent(masterPlanForm.getMasterKey()).getResultList();

		if (sellerSameBankEventtForm != null && sellerSameBankEventtForm.size() > 0
				|| sellerDiffBankEventForm != null && sellerDiffBankEventForm.size() > 0) {
			List<SellerSameBankEvent> samebankdate = sellerSameBankEventDAO.findAllEvents(masterPlanForm.getMasterKey())
					.getResultList();
			List<BarGraphJson> jsonListdate = new ArrayList<BarGraphJson>();
			for (SellerSameBankEvent sellersamebank : samebankdate) {
				BarGraphJson json = new BarGraphJson();
				json.setY(sellersamebank.getCost());
				json.setLabel(sellersamebank.getBuyer());

				jsonListdate.add(json);

			}

			List<SellerDiffBankEvent> sellerdiffbankdate = sellerDiffBankEventDAO
					.findAllEvents(masterPlanForm.getMasterKey()).getResultList();
			List<BarGraphJson> jsonListdiffdate = new ArrayList<BarGraphJson>();
			for (SellerDiffBankEvent seller : sellerdiffbankdate) {
				BarGraphJson json = new BarGraphJson();
				json.setY(seller.getCost());
				json.setLabel(seller.getBuyer());

				jsonListdiffdate.add(json);
			}

			jsonListdate.addAll(jsonListdiffdate);
			Gson gson = new Gson();
			String json = gson.toJson(jsonListdate);

			model.put("user", user);
			model.put("samebankdate", samebankdate);
			model.put("json", json);

			return new ModelAndView("graphSellerdiffbankDatePage", "model", model);

		} else {
			attributes.addFlashAttribute("success", "Master Key Not Found");

			return new ModelAndView("redirect:masterKeyApprovedSellerList");
		}

	}

	/**
	 * Method to compare PO events
	 *
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/comparisonBankPO", method = RequestMethod.GET)
	public ModelAndView comparisionCustomer(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		List<BuyerSameBankEvent> buyerSameBankEvents = buyerSameBankeventDAO.findCheckStatus().getResultList();
		List<BuyerDiffBankEvent> buyerDiffBankEvents = buyerDiffBankEventDAO.findCheckStatus().getResultList();

		newEventForm.setBuyerSameBankEvents(buyerSameBankEvents);
		newEventForm.setBuyerDiffBankEvents(buyerDiffBankEvents);

		if (buyerSameBankEvents.size() == 0 && buyerDiffBankEvents.size() == 0) {

			mav = new ModelAndView("noDataFound", "model", model);
		} else {

			model.put("user", user);

			model.put("newEventForm", newEventForm);

			mav = new ModelAndView("comparisonBankPO", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/comparisonData", method = RequestMethod.POST)
	public ModelAndView comparisionCustomer(ModelMap model, @ModelAttribute NewEventForm newEventForm,
											RedirectAttributes attributes) throws ParseException {

		String mav = "";

		List<ComparisonForm> comparisonList = new ArrayList<ComparisonForm>();

		PurchaseOrder purchaseOrder = purchaseOrderDAO.getPoListByPoKey(newEventForm.getPoKey()).getSingleResult();

		List<BuyerSameBankEvent> buyerSameBankEvents = buyerSameBankeventDAO.findPoKey(newEventForm.getPoKey())
				.getResultList();

		List<PurchaseDoc> purchaseDocForms = purchaseDocDAO.findPoKey(newEventForm.getPoKey()).getResultList();

		List<PoUpload> poUploadForms = poUploadDAO.getPoUploadByPOKey(newEventForm.getPoKey());

		if (buyerSameBankEvents != null && buyerSameBankEvents.size() > 0) {

			BuyerSameBankEvent buyerSameBankEvent = buyerSameBankEvents.get(0);

			buyerSameBankEventForm = eventsService.populateBuyerSameBankFormBasedOnEvents(buyerSameBankEvent,
					purchaseOrder);

			/* If there are any Disputes */

			List<Dispute> disputesList = disputeDAO.getPoKey(newEventForm.getPoKey()).getResultList();

			if (disputesList != null && disputesList.size() > 0) {
				comparisonList = eventsService.populateFormBasedOnDisputes(disputesList, buyerSameBankEvent);
			}

			/* FeedBack Module */

			buyerSameBankEventForm = eventsService.generateFeedback(buyerSameBankEventForm, buyerSameBankEvent,
					purchaseOrder);

			model.put("buyerSameBankEvent", buyerSameBankEvent);

			model.put("buyerSameBankEventForm", buyerSameBankEventForm);

			mav = "comparisonBankDataForSame";

		} else {
			List<BuyerDiffBankEvent> buyerDiffBankEventList = buyerDiffBankEventDAO.findPoKey(newEventForm.getPoKey())
					.getResultList();
			if (buyerDiffBankEventList != null && buyerDiffBankEventList.size() > 0) {
				BuyerDiffBankEvent buyerDiffBankEvent = buyerDiffBankEventList.get(0);

				buyerDiffBankEventForm = eventsService.populateBuyerDiffBankFormBasedOnEvents(buyerDiffBankEvent,
						purchaseOrder);

				/* If there are any Disputes */

				List<Dispute> disputesList = disputeDAO.getPoKey(newEventForm.getPoKey()).getResultList();

				if (disputesList != null && disputesList.size() > 0) {
					comparisonList = eventsService.populateDiffBankFormBasedOnDisputes(disputesList,
							buyerDiffBankEvent);
				}

				/* FeedBack Module */

				buyerDiffBankEventForm = eventsService.generateDiffBankFeedback(buyerDiffBankEventForm,
						buyerDiffBankEvent, purchaseOrder);

				model.put("buyerDiffBankEvent", buyerDiffBankEvent);

				model.put("buyerDiffBankEventForm", buyerDiffBankEventForm);

			}
			mav = "comparisonBankDataForDiff";
		}

		model.put("poUploadForms", poUploadForms);

		model.put("purchaseDocForms", purchaseDocForms);

		model.put("purchaseOrders", purchaseOrder);

		model.put("comparisonList", comparisonList);

		return new ModelAndView(mav, "model", model);
	}

	/* Comparison For Invoice events */

	@RequestMapping(value = "/comparisonBankInvoice", method = RequestMethod.GET)
	public ModelAndView comparisionInvoiceCustomer(ModelMap model, HttpServletRequest request,
												   HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		List<SellerSameBankEvent> sellerSameBankEvents = sellerSameBankEventDAO.findCheckStatus().getResultList();
		List<SellerDiffBankEvent> sellerDiffBankEvents = sellerDiffBankEventDAO.findCheckStatus().getResultList();

		newEventForm.setSellerSameBankEvents(sellerSameBankEvents);
		newEventForm.setSellerDiffBankEvents(sellerDiffBankEvents);

		if (sellerSameBankEvents.size() == 0 && sellerDiffBankEvents.size() == 0) {

			mav = new ModelAndView("noDataFound", "model", model);
		} else {

			model.put("user", user);

			model.put("newEventForm", newEventForm);

			mav = new ModelAndView("comparisonBankInvoice", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/comparisonInvoiceData", method = RequestMethod.POST)
	public ModelAndView comparisionInvoiceCustomer(ModelMap model, @ModelAttribute NewEventForm newEventForm,
												   RedirectAttributes attributes) throws ParseException {

		String mav = "";

		Invoice invoice = invoiceDAO.getInvoiceData(newEventForm.getInvoiceKey()).getSingleResult();

		List<SellerSameBankEvent> sellerSameBankEvents = sellerSameBankEventDAO
				.findInvoiceKey(newEventForm.getInvoiceKey()).getResultList();

		List<InvoiceDoc> invoiceDocs = invoiceDocDAO.findPoKey(newEventForm.getInvoiceKey()).getResultList();

		List<InvoiceUpload> invoiceUploads = invoiceUploadDAO.findPoKey(newEventForm.getInvoiceKey()).getResultList();

		if (sellerSameBankEvents != null && sellerSameBankEvents.size() > 0) {

			SellerSameBankEvent sellerSameBankEvent = sellerSameBankEvents.get(0);

			sellerSameBankEventForm = eventsService.populateSellerSameBankFormBasedOnEvents(sellerSameBankEvent,
					invoice);

			/* Feedback module */
			sellerSameBankEventForm = eventsService.generateSellerSameBankFeedback(sellerSameBankEventForm,
					sellerSameBankEvent, invoice);

			model.put("sellerSameBankEvent", sellerSameBankEvent);

			model.put("sellerSameBankEventForm", sellerSameBankEventForm);

			mav = "comparisonBankInvoiceDataForSame";
		} else {
			List<SellerDiffBankEvent> sellerDiffBankEventList = sellerDiffBankEventDAO
					.findInvoiceKey(newEventForm.getInvoiceKey()).getResultList();
			if (sellerDiffBankEventList != null && sellerDiffBankEventList.size() > 0) {
				SellerDiffBankEvent sellerDiffBankEvent = sellerDiffBankEventList.get(0);

				sellerDiffBankEventForm = eventsService.populateSellerDiffBankFormBasedOnEvents(sellerDiffBankEvent,
						invoice);
				/* Feedback module */
				sellerDiffBankEventForm = eventsService.generateSellerDiffBankFeedback(sellerDiffBankEventForm,
						sellerDiffBankEvent, invoice);

				model.put("sellerDiffBankEvent", sellerDiffBankEvent);

				model.put("sellerDiffBankEventForm", sellerDiffBankEventForm);

				mav = "comparisonBankInvoiceDataForDiff";

			}
		}
		model.put("invoiceUploads", invoiceUploads);
		model.put("invoiceDocs", invoiceDocs);
		model.put("invoice", invoice);

		return new ModelAndView(mav, "model", model);

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

		return new ModelAndView("generateMasterPlanChart", "model", model);
	}

	@RequestMapping(value = "/masterPlanStatusDetails", method = RequestMethod.GET)
	public ModelAndView appMasterPlanDetails(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<MasterPlan> masterList = masterPlanDAO.getApprovedMasterKey().getResultList();

		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);
			return new ModelAndView("masterPlanStatusDetails", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/bankEmpStatusTable", method = RequestMethod.GET)
	public ModelAndView statusData(@RequestParam Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		MasterPlan master = masterPlanDAO.getByMasterPlanId(id);

		masterPlanForm.setId(master.getId());
		masterPlanForm.setMasterKey(master.getMasterKey());
		masterPlanForm.setMasterPlanDate(master.getMasterPlanDate());
		masterPlanForm.setAppDate(master.getAppDate());

		if (master.getStatus() != null && master.getStatus().equals("Approved")) {
			masterPlanForm.setInfo("is approved by Approval Manager");
			model.put("master", master);
			model.put("masterPlanForm", masterPlanForm);
		} else if (master.getStatus() != null && master.getStatus().equals("Rejected")) {
			masterPlanForm.setInfo("Rejected by Approved Manager");
			model.put("master", master);
			model.put("masterPlanForm", masterPlanForm);
		} else {
			masterPlanForm.setInfo("still Pending");
			model.put("master", master);
			model.put("masterPlanForm", masterPlanForm);
		}

		List<PurchaseOrder> po = purchaseOrderDAO.getPoByMasterKeyList(master.getMasterKey()).getResultList();
		List<PurchaseOrderForm> poList = new ArrayList<PurchaseOrderForm>();
		for (PurchaseOrder value : po) {
			if (value.getPoKey() != null) {

				List<LimitBurst> limit = limitBurstDAO.getLimitBurstByPOKey(value.getPoKey());

				PurchaseOrderForm purchaseOrderForm = new PurchaseOrderForm();

				purchaseOrderForm.setPoKey(value.getPoKey());
				purchaseOrderForm.setCustomerName(value.getCustomerName());
				purchaseOrderForm.setSupplierName(value.getSupplierName());
				purchaseOrderForm.setStatus(value.getStatus());
				purchaseOrderForm.setAmount(value.getAmount());
				purchaseOrderForm.setPurchaseDate(value.getPurchaseDate());
				purchaseOrderForm.setAmount(value.getAmount());
				if (limit != null && limit.size() > 0) {
					purchaseOrderForm.setLimitAmt(limit.get(0).getFinalAmt());
					purchaseOrderForm.setLimitDate(limit.get(0).getLimitDate());
					purchaseOrderForm.setLimitStatus(limit.get(0).getaStatus());
				}
				poList.add(purchaseOrderForm);

			}

			model.put("user", user);
			model.put("po", po);

		}
		model.put("poList", poList);

		List<Invoice> invoice = invoiceDAO.getInvoiceByMasterKeyList(master.getMasterKey()).getResultList();
		{
			model.put("user", user);
			model.put("invoice", invoice);
		}
		model.put("master", master);
		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("bankEmpStatusTable", "model", model);

	}

	@RequestMapping(value = "/bankExistingStockInvoiceList", method = RequestMethod.GET)
	public ModelAndView existingStockHeadInvoiceFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<InvoiceStock> poList = invoiceStockDAO.getList().getResultList();

		model.put("user", user);
		if (poList != null && poList.size() > 0) {
			model.put("poList", poList);

			return new ModelAndView("bankExistingStockInvoiceList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/bankExistingStockPoList", method = RequestMethod.GET)
	public ModelAndView bankExistingStockPoList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PoStock> poList = poStockDAO.getList().getResultList();

		model.put("user", user);
		if (poList != null && poList.size() > 0) {
			model.put("poList", poList);

			return new ModelAndView("bankExistingStockPoList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/bankHelp", method = RequestMethod.GET)
	public ModelAndView adminHelp(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("bankHelp", "model", model);

	}

	@RequestMapping(value = "/poInsFullBankList", method = RequestMethod.GET)
	public ModelAndView poInsFullList(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);

		List<DisputeReports> dispute = disputeReportsDAO.getList().getResultList();

		if (dispute != null && dispute.size() > 0) {
			model.put("dispute", dispute);
			return new ModelAndView("poInsFullBankList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/addOrModifyReportBankView", method = RequestMethod.GET)
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

		return new ModelAndView("addOrModifyReportBankView", "model", model);
	}

	@RequestMapping(value = "/addOrModifyReportBankCompare", method = RequestMethod.GET)
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

		return new ModelAndView("addOrModifyReportBankCompare", "model", model);
	}

	@RequestMapping(value = "/addOrModifyReportInvoiceBankFullList", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportVendorFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<InvoiceReports> disputeList = invoiceReportsDAO.getList().getResultList();

		if (disputeList != null && disputeList.size() > 0) {
			model.put("disputeList", disputeList);
			return new ModelAndView("addOrModifyReportInvoiceBankFullList", "model", model);
		} else {
			return new ModelAndView("noDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/addOrModifyReportInvoiceBankView", method = RequestMethod.GET)
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

		return new ModelAndView("addOrModifyReportInvoiceBankView", "model", model);
	}

	@RequestMapping(value = "/addOrModifyReportInvoiceBankCompare", method = RequestMethod.GET)
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

		return new ModelAndView("addOrModifyReportInvoiceBankCompare", "model", model);
	}

	/**
	 * Method to display PO events graph
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/eventsGraph", method = RequestMethod.GET)
	public ModelAndView eventsGraph(@RequestParam String masterKey, ModelMap model, RedirectAttributes attributes) {

		List<BuyerSameBankEvent> buyerSameBankEventList = buyerSameBankeventDAO.findAllEvents(masterKey)
				.getResultList();
		List<BuyerDiffBankEvent> buyerDiffBankEventList = buyerDiffBankEventDAO.findAllEvents(masterKey)
				.getResultList();

		if (buyerSameBankEventList == null && buyerDiffBankEventList == null) {
			attributes.addFlashAttribute("success", "Master Key Not Found");

			return new ModelAndView("redirect:masterKeyApprovedList");
		}

		List<ColumnGraphForm> columnGraphList = new ArrayList<ColumnGraphForm>();
		List<String> categoryList = new ArrayList<>();
		// Populate Graphs for BuyerSameBankEvent
		if (buyerSameBankEventList != null && buyerSameBankEventList.size() > 0) {

			float[] sanctionedData = new float[buyerSameBankEventList.size()];
			float[] utilizedData = new float[buyerSameBankEventList.size()];
			float[] balanceData = new float[buyerSameBankEventList.size()];
			for (int i = 0; i < buyerSameBankEventList.size(); i++) {
				categoryList.add(buyerSameBankEventList.get(i).getSupplier());
				sanctionedData[i] = buyerSameBankEventList.get(i).getSanctionedAmount();
				utilizedData[i] = buyerSameBankEventList.get(i).getUtilizedAmount();
				balanceData[i] = buyerSameBankEventList.get(i).getAvailableCost();
			}
			columnGraphList.add(ColumnGraphService.generateObject(Constants.SANCTIONEDAMT, sanctionedData));
			columnGraphList.add(ColumnGraphService.generateObject(Constants.UTILIZEDAMT, utilizedData));
			columnGraphList.add(ColumnGraphService.generateObject(Constants.AVAILABLEAMT, balanceData));
		}

		// Populate Graphs for BuyerDiffBankEvent
		if (buyerDiffBankEventList != null && buyerDiffBankEventList.size() > 0) {
			List<String> diffCategoryList = new ArrayList<String>();
			List<ColumnGraphForm> diffColumnGraphList = new ArrayList<ColumnGraphForm>();
			float[] sanctionedData = new float[buyerDiffBankEventList.size()];
			float[] utilizedData = new float[buyerDiffBankEventList.size()];
			float[] balanceData = new float[buyerDiffBankEventList.size()];
			for (int i = 0; i < buyerDiffBankEventList.size(); i++) {
				diffCategoryList.add(buyerDiffBankEventList.get(i).getSupplier());
				sanctionedData[i] = buyerDiffBankEventList.get(i).getSanctionedAmount();
				utilizedData[i] = buyerDiffBankEventList.get(i).getUtilizedAmount();
				balanceData[i] = buyerDiffBankEventList.get(i).getAvailableCost();
			}
			diffColumnGraphList.add(ColumnGraphService.generateObject(Constants.SANCTIONEDAMT, sanctionedData));
			diffColumnGraphList.add(ColumnGraphService.generateObject(Constants.UTILIZEDAMT, utilizedData));
			diffColumnGraphList.add(ColumnGraphService.generateObject(Constants.AVAILABLEAMT, balanceData));
			columnGraphList.addAll(diffColumnGraphList);
			categoryList.addAll(diffCategoryList);
		}

		String[] category = categoryList.toArray(new String[categoryList.size()]);
		Gson gson = new Gson();
		String values = gson.toJson(columnGraphList);
		String categories = gson.toJson(category);

		model.put("values", values);
		model.put("categories", categories);

		return new ModelAndView("eventsGraph", "model", model);

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

		return new ModelAndView("generalLedgerGraphicalView", "model", model);
	}

	@RequestMapping(value = "/wareHouseMngBankFullList", method = RequestMethod.GET)
	public ModelAndView wareHouseMngList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouseMng> mngList = wareHouseMngDAO.getList().getResultList();

		model.put("user", user);
		model.put("mngList", mngList);

		return new ModelAndView("wareHouseMngBankFullList", "model", model);

	}

	@RequestMapping(value = "/clientAppMngFullList", method = RequestMethod.GET)
	public ModelAndView clientAppMngFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<ClientAppMng> clientList = clientAppMngDAO.getList().getResultList();

		model.put("user", user);
		if(clientList != null && clientList.size() > 0) {
			model.put("clientList", clientList);

			return new ModelAndView("clientAppMngFullListEmp", "model", model);
		} else {
			return new ModelAndView("noDataFoundCAdmin", "model", model);
		}

	}
}
