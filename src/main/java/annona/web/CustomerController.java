package annona.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.NoResultException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annona.dao.*;
import annona.domain.*;
import annona.form.*;
import annona.utility.KeyGenerator;
import com.google.gson.JsonObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.theme.CookieThemeResolver;

import annona.services.DateService;
import annona.services.EventsService;
import annona.services.ImageService;
import annona.services.UploadService;
import annona.trade.dao.TradeNotificationDAO;
import annona.trade.domain.TradeNotification;
import annona.utility.Constants;

@Controller
@RequestMapping("/users")
public class CustomerController implements ServletContextAware {

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	EndUserDAO endUserDAOImpl;

	@Autowired
	EndUserForm endUserForm;

	@Autowired
	DisputeForm disputeForm;

	@Autowired
	InoviceUploadForm inoviceUploadForm;

	@Autowired
	InvoiceUploadDAO invoiceUploadDAO;

	@Autowired
	SellerBuyingCostDAO sellerBuyingCostDAO;

	@Autowired
	DraftSellerBuyingCostDAO draftSellerBuyingCostDAO;

	@Autowired
	BuyerPODAO buyerPODAO;

	@Autowired
	QuarterlyDAO quarterlyDAO;

	@Autowired
	QuarterlyForm quarterlyForm;

	@Autowired
	FullAmountDAO fullAmountDAO;

	@Autowired
	PoUploadForm poUploadForm;

	@Autowired
	FullAmountForm fullAmountForm;

	@Autowired
	HalfYearlyForm halfYearlyForm;

	@Autowired
	HalfYearlyDAO halfYearlyDAO;

	@Autowired
	FundsDistributeForm fundsDistributeForm;

	@Autowired
	FundsDistributeDAO fundsDistributeDAO;

	@Autowired
	CollateralDAO collateralDAO;

	@Autowired
	RequestMoneyDAO requestMoneyDAO;

	@Autowired
	CollateralForm collateralForm;

	@Autowired
	TransactionDAO transcationDAOImpl;

	@Autowired
	NewBuyerDAO newBuyerDAOImpl;

	@Autowired
	DraftsMasterPlanDAO draftsMasterPlanDAO;

	@Autowired
	NewBuyerForm newBuyerForm;

	@Autowired
	CustomerDAO customerDAO;

	@Autowired
	RegulationsDAO regulationsDAO;

	@Autowired
	MasterPlanForm masterPlanForm;

	@Autowired
	WareHouseForm wareHouseForm;

	@Autowired
	WareHouseDAO wareHouseDAO;

	@Autowired
	MasterPlanDAO masterPlanDAO;

	@Autowired
	CorrespondentDAO correspondentDAO;

	@Autowired
	BuyingCostDAO buyingCostDAO;

	@Autowired
	DraftBuyingCostDAO draftBuyingCostDAO;

	@Autowired
	PoUploadDAO poUploadDAO;

	@Autowired
	FundsStatementDAO fundsStatementDAO;

	@Autowired
	SupplierDAO supplierDAOImpl;

	@Autowired
	SupplierForm supplierForm;

	@Autowired
	InvoiceForm invoiceForm;

	@Autowired
	SalesOrderForm salesOrderForm;

	@Autowired
	SalesOrderDAO salesOrderDAO;


	@Autowired
	WorkingCapitalDAO workingCapitalDAO;

	@Autowired
	DraftWorkingCapitalDAO draftWorkingCapitalDAO;

	@Autowired
	RequestMoneyForm requestMoneyForm;

	@Autowired
	InvoiceDAO invoiceDAO;


	@Autowired
	InventoryDAO invenrotyDAO;

	@Autowired
	InvoiceInventoryForm invoiceInventoryForm;

	@Autowired
	InvoiceInventoryDAO invoiceInventoryDAO;


	@Autowired
	LetterOfCreditForm letterOfCreditForm;

	@Autowired
	LetterOfCreditDAO letterOfCreditDAO;

	@Autowired
	PurchaseOrderForm purchaseOrderForm;

	@Autowired
	RepaymentForm repaymentForm;

	@Autowired
	RepaymentDAO repaymenyDAO;

	@Autowired
	InventoryForm inventoryForm;

	@Autowired
	PurchaseOrderDAO purchaseOrderDAO;

	@Autowired
	PaymentDAO paymentDAO;

	@Autowired
	BuyerSameBankEventForm buyerSameBankEventForm;

	@Autowired
	BuyerDiffBankEventForm buyerDiffBankEventForm;

	@Autowired
	SellerSameBankEventForm sellerSameBankEventForm;

	@Autowired
	SellerDiffBankEventForm sellerDiffBankEventForm;

	@Autowired
	NewEventForm newEventForm;

	@Autowired
	SupplierDAO supplierDAO;

	@Autowired
	TransactionDAO transactionDAO;

	@Autowired
	BuyerSameBankEventDAO buyerSameBankeventDAO;

	@Autowired
	BuyerDiffBankEventDAO buyerDiffBankEventDAO;

	@Autowired
	SellerSameBankEventDAO sellerSameBankEventDAO;

	@Autowired
	SellerDiffBankEventDAO sellerDiffBankEventDAO;

	@Autowired
	UploadedFileForm uploadedFileForm;

	@Autowired
	UploadDAO uploadDaoImpl;

	@Autowired
	DisputeDAO disputeDAO;

	@Autowired
	LimitBurstDAO limitBurstDAO;

	@Autowired
	LimitBurstForm limitBurstForm;

	@Autowired
	UploadService uploadService;

	@Autowired
	VendorUploadDAO vendorUploadDAO;

	@Autowired
	UploadDAO uploadDAO;

	@Autowired
	PurchaseDocDAO purchaseDocDAO;

	@Autowired
	InvoiceDocDAO invoiceDocDAO;

	@Autowired
	PoStockDAO poStockDAO;

	@Autowired
	PoStockForm poStockForm;

	@Autowired
	InvoiceStockDAO invoiceStockDAO;

	@Autowired
	InvoiceStockForm invoiceStockForm;

	@Autowired
	TradeNotificationDAO tradeNotificationDAO;

	@Autowired
	NotificationDAO notificationDAO;

	@Autowired
	DisputeReportsDAO disputeReportsDAO;

	@Autowired
	InvoiceReportsDAO invoiceReportsDAO;

	@Autowired
	DisputeReportsForm disputeReportsForm;

	@Autowired
	RestrictedLicenseForm restrictedLicenseForm;

	@Autowired
	RestrictedLicenseDAO restrictedLicenseDAO;

	@Autowired
	EventsService eventsService;

	@Autowired
	WareHouseMngForm wareHouseMngForm;

	@Autowired
	WareHouseMngDAO wareHouseMngDAO;

	@Autowired
	CompanyDAO companyDAO;

	@Autowired
	LoginDateForm loginDateForm;

	@Autowired
	LoginDateDao loginDateDao;

	@Autowired
	DateService dateService;

	@Autowired
	CustomerBankDetailsDAO customerBankDetailsDAO;

	@Autowired
	BuyerPOForm buyerPOForm;

	@Autowired
	InventoryForProductionDAO inventoryForProductionDAO;

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

	private String getCurrentLoggedUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();

	}

	private EndUser getCurrentLoggedUserDetails() {

		EndUser endUser = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		return endUser;

	}

	@RequestMapping(value = "/commonUser", method = RequestMethod.GET)
	public ModelAndView showCommonUserDashBoard(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		TradeNotification tradenot = new TradeNotification();

		List<TradeNotification> tradenotificationList = tradeNotificationDAO.findByCustomerName(user.getUserName()).getResultList();

		if (tradenotificationList != null && tradenotificationList.size() > 0) {

			model.put("tradenot", tradenot);

			model.put("tradenotificationList", tradenotificationList);

		}

		Notification not = new Notification();
		Collection<Notification> notificationList = notificationDAO.findByCustomerName(user.getUserName()).getResultList();

		if (notificationList != null && notificationList.size() > 0) {

			model.put("not", not);

			model.put("notificationList", notificationList);

		}
		model.put("user", user);
		model.put("user", user);

		return new ModelAndView("userCommonPage", "model", model);

	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView showUserDashBoard(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		if (user.getImageName() != null) {
			String type = ImageService.getImageType(user.getImageName());

			String url = "data:image/" + type + ";base64," + Base64.encodeBase64String(user.getImage());
			user.setImageName(url);

		}

		model.put("user", user);

		return new ModelAndView("userPage", "model", model);

	}

	@RequestMapping(value = "/editCustomerProfile", method = RequestMethod.GET)
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

		return new ModelAndView("editCustomerProfile", "model", model);

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
		return "redirect:editCustomerProfile?id=" + endUserForm.getId();
	}

	@RequestMapping(value = "/confirmEditCustomerProfile", method = RequestMethod.POST)
	public ModelAndView confirmEditAdminProfile(ModelMap model, @ModelAttribute EndUserForm endUserForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("confirmEditCustomerProfile", "model", model);

	}

	@RequestMapping(value = "/updateCustomerDetails", method = RequestMethod.POST)
	public ModelAndView updateAdminDetails(ModelMap model, @ModelAttribute EndUserForm endUserForm, BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

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

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("updateCustomerSuccess", "model", model);

	}

	@RequestMapping(value = "/editCustomerPWD", method = RequestMethod.GET)
	public ModelAndView editAdminPWD(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser userProfile = endUserDAOImpl.findId(id);

		endUserForm.setId(userProfile.getId());

		endUserForm.setTransactionId(userProfile.getTransactionId());

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("editCustomerPWD", "model", model);

	}

	@RequestMapping(value = "/updateEditCustomerPWD", method = RequestMethod.POST)
	public ModelAndView updateEditAdminPWD(ModelMap model, @ModelAttribute EndUserForm endUserForm, BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		EndUser endUser = endUserDAOImpl.findId(endUserForm.getId());

		endUser.setId(endUserForm.getId());

		endUser.setPassword(endUserForm.getNewPassword());

		endUser.setTransactionId(endUserForm.getTransactionId());

		endUserDAOImpl.update(endUser);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("updateCustomerSuccess", "model", model);

	}

	@RequestMapping(value = "/themeChange", method = RequestMethod.GET)
	public String getThemeChange(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		log.info("Received request for theme change");

		EndUser endUser = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		if (endUser.getTheme() == null) themeResolver.setThemeName(request, response, "themeBlue");
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
		} else themeResolver.setThemeName(request, response, endUser.getTheme());

		model.put("user", user);

		return "redirect:user";
	}

	@RequestMapping(value = "/getLocaleLang", method = RequestMethod.GET)
	public String getLocaleLang(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		log.info("Received request for locale change");
		EndUser endUser = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		LocaleResolver localeResolver = new CookieLocaleResolver();
		Locale locale = new Locale(request.getParameter("lang"));
		localeResolver.setLocale(request, response, locale);
		endUser.setPrefferedLanguage(request.getParameter("lang"));

		endUserDAOImpl.mergeUser(endUser);

		model.put("user", user);

		return "redirect:user";
	}

	@RequestMapping(value = "/newBuyerPage", method = RequestMethod.GET)
	public ModelAndView newBuyer(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		newBuyerForm = new NewBuyerForm();
		newBuyerForm.setEndUser(user);
		newBuyerForm.setName(user.getUserName());


		if (user.getCompanyId() != null) {
			Company company = companyDAO.getByCompanyId(user.getCompanyId());
			newBuyerForm.setCompanyId(user.getCompanyId());
			newBuyerForm.setCompanyName(company.getCompanyName());
		}

		List<NewBuyer> newbuyeList = newBuyerDAOImpl.getByCustAppPending(user.getUserName()).getResultList();

		// model.put("user", user);
		model.put("newbuyeList", newbuyeList);
		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("newBuyerPage", "model", model);

	}

	@RequestMapping(value = "/selectbuyerList", method = RequestMethod.GET)
	public ModelAndView selectbuyerList(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		List<NewBuyer> newBuyerList = newBuyerDAOImpl.findByCustomerHeadName(user.getUserName()).getResultList();

		if (newBuyerList != null && newBuyerList.size() > 0) {

			model.put("newBuyerForm", newBuyerForm);
			model.put("newBuyerList", newBuyerList);
			return new ModelAndView("selectbuyerList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/updatenewBuyerPage", method = RequestMethod.POST)
	public ModelAndView updatenewBuyerPage(@ModelAttribute NewBuyerForm newBuyerForm, RedirectAttributes attributes, ModelMap model) {


		EndUser user = getCurrentLoggedUserDetails();
		NewBuyer Duplicate=newBuyerDAOImpl.findBuyerByNameAndCompId(newBuyerForm.getBuyerName() , user.getCompanyId() );

		if (Duplicate == null) {
			//EndUser user = getCurrentLoggedUserDetails()

			model.put("user", user);
			model.put("newBuyerForm", newBuyerForm);

			return new ModelAndView("newBuyerPageForward", "model", model);
		} else {
			//EndUser user = getCurrentLoggedUserDetails();

			attributes.addFlashAttribute("success", "Buyer Name Already Exists");

			model.put("user", user);

			return new ModelAndView("redirect:newBuyerPage");

		}

	}

	@RequestMapping(value = "/newbuyerApprovalList", method = RequestMethod.POST)
	public ModelAndView approvalConfirm(@ModelAttribute NewBuyerForm newBuyerForm, ModelMap model, RedirectAttributes attribute) {

		EndUser user = getCurrentLoggedUserDetails();

		newBuyerForm.setTransactionId(KeyGenerator.generateTransactionKey());

		NewBuyer newBuyer = new NewBuyer();

		Transaction transaction = new Transaction();
		newBuyer.setId(newBuyerForm.getId());
		newBuyer.setIfsc(newBuyerForm.getIfsc());
		newBuyer.setBuyerName(newBuyerForm.getBuyerName());
		newBuyer.setAddress(newBuyerForm.getAddress());
		newBuyer.setAltcontactNum(newBuyerForm.getAltcontactNum());
		newBuyer.setAltEmail(newBuyerForm.getAltEmail());
		newBuyer.setApproveDate(newBuyerForm.getApproveDate());
		newBuyer.setBank(newBuyerForm.getBank());
		newBuyer.setBankEmail(newBuyerForm.getBankEmail());
		newBuyer.setBranch(newBuyerForm.getBranch());
		newBuyer.setCity(newBuyerForm.getCity());
		newBuyer.setComment(newBuyerForm.getComment());
		newBuyer.setCompanyName(newBuyerForm.getCompanyName());
		newBuyer.setCompanyId((Long) newBuyerForm.getCompanyId());
		newBuyer.setContactNum(newBuyerForm.getContactNum());
		newBuyer.setCountry(newBuyerForm.getCountry());
		newBuyer.setRate(newBuyerForm.getRate());

		newBuyer.setEmail(newBuyerForm.getEmail());

		newBuyer.setName(newBuyerForm.getName());
		newBuyer.setPinCode(newBuyerForm.getPinCode());
		newBuyer.setState(newBuyerForm.getState());
		newBuyer.setcStatus("Pending");
		newBuyer.setStatus("Pending");
		newBuyer.setUniqueKey(newBuyerForm.getUniqueKey());
		newBuyer.setCurrencydeal(newBuyerForm.getCurrencydeal());
		newBuyer.setTransactionId(newBuyerForm.getTransactionId());
		newBuyer.setAccExpiryDate(newBuyerForm.getAccExpiryDate());
		transaction.setTransactionId(newBuyerForm.getTransactionId());
		transaction.setTransactionType("Role");
		transaction.setTransactionStatus("Buyer saved successfully");
		newBuyerDAOImpl.createUser(newBuyer);
		transcationDAOImpl.insertTransaction(transaction);

		attribute.addFlashAttribute("success", "Saved Successfully");

		model.put("user", user);

		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("buyerlistSuccess", "model", model);

	}

	@RequestMapping(value = "/selectBuyerUpdate", method = RequestMethod.GET)
	public ModelAndView selectBuyerUpdate(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		NewBuyer newBuyer = newBuyerDAOImpl.findId(id);
		Company company = companyDAO.getByCompanyId(newBuyer.getCompanyId());

		newBuyerForm.setId(newBuyer.getId());
		newBuyerForm.setContactNum(newBuyer.getContactNum());
		newBuyerForm.setAltcontactNum(newBuyer.getAltcontactNum());
		newBuyerForm.setAltEmail(newBuyer.getAltEmail());
		newBuyerForm.setIfsc(newBuyer.getIfsc());
		newBuyerForm.setAddress(newBuyer.getAddress());
		newBuyerForm.setBranch(newBuyer.getBranch());
		newBuyerForm.setPinCode(newBuyer.getPinCode());
		newBuyerForm.setName(newBuyer.getName());
		newBuyerForm.setBuyerName(newBuyer.getBuyerName());
		newBuyerForm.setBank(newBuyer.getBank());
		newBuyerForm.setBankEmail(newBuyer.getBankEmail());
		newBuyerForm.setBranch(newBuyer.getBranch());
		newBuyerForm.setCountry(newBuyer.getCountry());
		newBuyerForm.setState(newBuyer.getState());
		newBuyerForm.setCity(newBuyer.getCity());
		newBuyerForm.setEmail(newBuyer.getEmail());
		newBuyerForm.setCompanyName(company.getCompanyName());
		newBuyerForm.setCurrencydeal(newBuyer.getCurrencydeal());
		newBuyerForm.setTransactionId(newBuyer.getTransactionId());

		model.put("user", user);

		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("selectBuyerUpdate", "model", model);

	}

	@RequestMapping(value = "/selectBuyerUpdate2", method = RequestMethod.POST)
	public ModelAndView selectBuyerUpdate2(ModelMap model, @ModelAttribute NewBuyerForm newBuyerForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("selectBuyerUpdate2", "model", model);

	}

	@RequestMapping(value = "/selectBuyerUpdate3", method = RequestMethod.POST)
	public ModelAndView selectBuyerUpdate3(@ModelAttribute NewBuyerForm newBuyerForm, ModelMap model, RedirectAttributes attribute) {

		EndUser user = getCurrentLoggedUserDetails();

		NewBuyer newBuyer = newBuyerDAOImpl.findId(newBuyerForm.getId());
		Transaction transaction = new Transaction();
		newBuyer.setId(newBuyerForm.getId());
		newBuyer.setName(newBuyerForm.getName());
		newBuyer.setBuyerName(newBuyerForm.getBuyerName());
		newBuyer.setIfsc(newBuyerForm.getIfsc());
		newBuyer.setAddress(newBuyerForm.getAddress());
		newBuyer.setAltcontactNum(newBuyerForm.getAltcontactNum());
		newBuyer.setAltEmail(newBuyerForm.getAltEmail());
		newBuyer.setBank(newBuyerForm.getBank());
		newBuyer.setBankEmail(newBuyerForm.getBankEmail());
		newBuyer.setBranch(newBuyerForm.getBranch());
		newBuyer.setCity(newBuyerForm.getCity());
		newBuyer.setComment(newBuyerForm.getComment());
//		newBuyer.setCompanyName(newBuyerForm.getCompanyName());
		newBuyer.setCompanyId(newBuyerForm.getCompanyId());
		newBuyer.setContactNum(newBuyerForm.getContactNum());
		newBuyer.setCountry(newBuyerForm.getCountry());
		newBuyer.setEmail(newBuyerForm.getEmail());
		newBuyer.setPinCode(newBuyerForm.getPinCode());
		newBuyer.setState(newBuyerForm.getState());
		newBuyer.setCurrencydeal(newBuyerForm.getCurrencydeal());
		newBuyer.setTransactionId(newBuyerForm.getTransactionId());
		newBuyer.setcStatus("Pending");

		transaction.setTransactionType("Buyer Details");
		transaction.setTransactionStatus("Buyer Details Updtaed  successfully");
		transaction.setTransactionId(newBuyerForm.getTransactionId());
		model.put("newBuyerForm", newBuyerForm);

		newBuyerDAOImpl.update(newBuyer);
		transcationDAOImpl.insertTransaction(transaction);

		model.put("user", user);

		return new ModelAndView("selectBuyerUpdate3", "model", model);
	}

	// Supplier
	@RequestMapping(value = "/newSupplierPage", method = RequestMethod.GET)
	public ModelAndView newSupplier(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		List<Supplier> supplierList = supplierDAOImpl.getByCustPending(user.getUserName()).getResultList();
		supplierForm = new SupplierForm();
		supplierForm.setEndUser(user);
		supplierForm.setName(user.getUserName());

		if (user.getCompanyId() != null) {
			Company company = companyDAO.getByCompanyId(user.getCompanyId());
			supplierForm.setCompanyId(user.getCompanyId());
			supplierForm.setCompanyName(company.getCompanyName());
		}

		model.put("user", user);
		model.put("supplierList", supplierList);
		model.put("supplierForm", supplierForm);

		return new ModelAndView("newSupplierPage", "model", model);
	}

	@RequestMapping(value = "/selectsupplierList", method = RequestMethod.GET)
	public ModelAndView selectsupplierList(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		List<Supplier> supplierList = supplierDAOImpl.findByName(user.getUserName()).getResultList();

		if (supplierList != null && supplierList.size() > 0) {

			model.put("supplierForm", supplierForm);
			model.put("supplierList", supplierList);
			return new ModelAndView("selectsupplierList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/SupplierPageForward", method = RequestMethod.POST)
	public ModelAndView selectsupplierUpdat(@ModelAttribute SupplierForm supplierForm, RedirectAttributes attributes, ModelMap model) {

		//List<EndUser> endUser = endUserDAOImpl.findByUsername(supplierForm.getSupplierName()).getResultList();
		EndUser user = getCurrentLoggedUserDetails();
		Supplier duplicate = supplierDAOImpl.findSupplierByNameAndCompId(supplierForm.getSupplierName() , user.getCompanyId() );
		if (duplicate == null) {
			model.put("user", user);

			model.put("supplierForm", supplierForm);

			return new ModelAndView("SupplierPageForward", "model", model);
		} else {
			model.put("user", user);

			attributes.addFlashAttribute("success", "Supplier Name Already Exists");

			return new ModelAndView("redirect:newSupplierPage");

		}

	}

	@RequestMapping(value = "/supplierApprovalList", method = RequestMethod.POST)
	public ModelAndView approvalConfirm(@ModelAttribute SupplierForm supplierForm, ModelMap model, RedirectAttributes attribute) {

		EndUser user = getCurrentLoggedUserDetails();


		supplierForm.setTransactionId(KeyGenerator.generateTransactionKey());


		Supplier supplier = new Supplier();

		Transaction transaction = new Transaction();

		supplier.setAddress(supplierForm.getAddress());
		supplier.setAltContactNum(supplierForm.getAltContactNum());
		supplier.setAltEmail(supplierForm.getAltEmail());
		supplier.setApproveDate(supplierForm.getApproveDate());
		supplier.setBank(supplierForm.getBank());
		supplier.setBankEmail(supplierForm.getBankEmail());
		supplier.setIfsc(supplierForm.getIfsc());
		supplier.setBranch(supplierForm.getBranch());
		supplier.setCity(supplierForm.getCity());
		supplier.setComment(supplierForm.getComment());
		supplier.setCompanyId(supplierForm.getCompanyId());
		supplier.setCompanyName(supplierForm.getCompanyName());
		supplier.setContactNum(supplierForm.getContactNum());
		supplier.setCountry(supplierForm.getCountry());
		supplier.setSupplierName(supplierForm.getSupplierName());
		supplier.setEmail(supplierForm.getEmail());
		supplier.setName(supplierForm.getName());
		supplier.setPinCode(supplierForm.getPinCode());
		supplier.setState(supplierForm.getState());
		supplier.setCurrencydeal(supplierForm.getCurrencydeal());
		supplier.setcStatus("Pending");
		supplier.setStatus("Pending");
		supplier.setTransactionId(supplierForm.getTransactionId());
		supplier.setAccExpiryDate(supplierForm.getAccExpiryDate());
		supplier.setRate(supplierForm.getRate());
		transaction.setTransactionId(supplierForm.getTransactionId());
		transaction.setTransactionType("Supplier Details");
		transaction.setTransactionStatus("Supplier Details saved successfully");
		supplierDAOImpl.createUser(supplier);
		transcationDAOImpl.insertTransaction(transaction);

		attribute.addFlashAttribute("success", "Saved Successfully");

		model.put("user", user);

		model.put("supplierForm", supplierForm);

		return new ModelAndView("supplierListSuccess", "model", model);

	}

	@RequestMapping(value = "/selectsupplierUpdate", method = RequestMethod.GET)
	public ModelAndView selectsupplierUpdat(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Supplier supplier = supplierDAOImpl.findId(id);

		supplierForm.setId(supplier.getId());
		supplierForm.setName(supplier.getName());
		supplierForm.setPinCode(supplier.getPinCode());
		supplierForm.setCountry(supplier.getCountry());
		supplierForm.setSupplierName(supplier.getSupplierName());
		supplierForm.setCompanyName(supplier.getCompanyName());
		supplierForm.setIfsc(supplier.getIfsc());
		supplierForm.setEmail(supplier.getEmail());
		supplierForm.setCurrencydeal(supplier.getCurrencydeal());
		supplierForm.setBranch(supplier.getBranch());
		supplierForm.setAltContactNum(supplier.getAltContactNum());
		supplierForm.setAltEmail(supplier.getAltEmail());
		supplierForm.setBank(supplier.getBank());
		supplierForm.setBankEmail(supplier.getBankEmail());
		supplierForm.setCity(supplier.getCity());
		supplierForm.setState(supplier.getState());
		supplierForm.setContactNum(supplier.getContactNum());
		supplierForm.setAddress(supplier.getAddress());
		supplierForm.setTransactionId(supplier.getTransactionId());

		model.put("user", user);

		model.put("supplierForm", supplierForm);

		return new ModelAndView("selectsupplierUpdate", "model", model);

	}

	@RequestMapping(value = "/selectSupplierUpdate2", method = RequestMethod.POST)
	public ModelAndView selectSupplierUpdate2(ModelMap model, @ModelAttribute SupplierForm supplierForm, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		Supplier supplier = supplierDAOImpl.findId(supplierForm.getId());

		model.put("user", user);
		model.put("supplier", supplier);

		model.put("supplierForm", supplierForm);

		return new ModelAndView("selectSupplierUpdate2", "model", model);

	}

	@RequestMapping(value = "/selectSupplierUpdate3", method = RequestMethod.POST)
	public ModelAndView selectSupplierUpdate3(@ModelAttribute SupplierForm supplierForm, ModelMap model, RedirectAttributes attribute) {

		EndUser user = getCurrentLoggedUserDetails();

		Supplier supplier = supplierDAOImpl.findId(supplierForm.getId());
		Transaction transaction = new Transaction();
		supplier.setAddress(supplierForm.getAddress());
		supplier.setSupplierName(supplierForm.getSupplierName());
		supplier.setAltContactNum(supplierForm.getAltContactNum());
		supplier.setAltEmail(supplierForm.getAltEmail());
		supplier.setApproveDate(supplierForm.getApproveDate());
		supplier.setBank(supplierForm.getBank());
		supplier.setBankEmail(supplierForm.getBankEmail());
		supplier.setBranch(supplierForm.getBranch());
		supplier.setIfsc(supplierForm.getIfsc());
		supplier.setCity(supplierForm.getCity());
		supplier.setComment(supplierForm.getComment());
		supplier.setCompanyName(supplierForm.getCompanyName());
		supplier.setContactNum(supplierForm.getContactNum());
		supplier.setCountry(supplierForm.getCountry());
		supplier.setCustomerPrefix(supplierForm.getCustomerPrefix());
		supplier.setEmail(supplierForm.getEmail());
		supplier.setId(supplierForm.getId());
		supplier.setName(supplierForm.getName());
		supplier.setPinCode(supplierForm.getPinCode());
		supplier.setState(supplierForm.getState());
		supplier.setCurrencydeal(supplierForm.getCurrencydeal());
		supplier.setcStatus("Pending");
		supplier.setTransactionId(supplierForm.getTransactionId());
		transaction.setTransactionId(supplierForm.getTransactionId());
		transaction.setTransactionType("Supplier Details");
		transaction.setTransactionStatus("Supplier Details Status saved successfully");
		transaction.setTransactionId(supplierForm.getTransactionId());

		model.put("supplierForm", supplierForm);
		model.put("user", user);

		supplierDAOImpl.update(supplier);

		transcationDAOImpl.insertTransaction(transaction);

		return new ModelAndView("selectSupplierUpdate3", "model", model);
	}

	@RequestMapping(value = "/showMail", method = RequestMethod.GET)
	public ModelAndView doSendEmail1(@ModelAttribute ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("queryMail", "model", model);
	}

	@RequestMapping(value = "/mailSender", method = RequestMethod.POST)
	public ModelAndView doSendEmail(@ModelAttribute ModelMap model, HttpServletRequest request) {
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
		return new ModelAndView("result", "model", model);
	}

	@RequestMapping(value = "/showCurrency", method = RequestMethod.GET)
	public ModelAndView showCurrency(ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("currency", "model", model);
	}

	@RequestMapping(value = "/showcurrencyConversion", method = RequestMethod.GET)
	public ModelAndView showcurrencyConversion(@ModelAttribute ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("showcurrencyConversion", "model", model);
	}

//	@RequestMapping(value = "/masterPlan", method = RequestMethod.GET)
//	public ModelAndView masterPlan(ModelMap model) {
//
//		EndUser user = getCurrentLoggedUserDetails();
//
//		List<CustomerHead> custprefix = customerDAO.getCustomerDetailsList(user.getUserName()).getResultList();
//		if (custprefix != null && custprefix.size() > 0) {
//			masterPlanForm.setCustomerPrefix(custprefix.get(0).getCustomerPrefix());
//		}
//
//		model.put("user", user);
//		model.put("masterPlanForm", masterPlanForm);
//		return new ModelAndView("masterPlan", "model", model);
//	}

	@RequestMapping(value = "/masterPlanCurrency", method = RequestMethod.GET)
	public ModelAndView masterPlanPost(ModelMap model, RedirectAttributes attributes) {

		masterPlanForm = new MasterPlanForm();

		EndUser user = getCurrentLoggedUserDetails();

		masterPlanForm.setCustomerPrefix(masterPlanForm.getCustomerPrefix());
		masterPlanForm.setCustomer(user.getUserName());
		masterPlanForm.setCurrencySymbol("rs");
		List<CustomerHead> custprefix = customerDAO.getCustomerDetailsList(user.getUserName()).getResultList();
		if (custprefix != null && custprefix.size() > 0) {
			masterPlanForm.setCustomerPrefix(custprefix.get(0).getCustomerPrefix());
		}

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("masterPlan1", "model", model);

	}

	@RequestMapping(value = "/masterPlan1", method = RequestMethod.GET)
	public ModelAndView masterPlanConfirm(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);
		return new ModelAndView("masterPlan1", "model", model);
	}

	@RequestMapping(value = "/masterPlanInfoConfirm", method = RequestMethod.POST)
	public ModelAndView masterPlanInfoConfirm(ModelMap model, @ModelAttribute MasterPlanForm masterPlanForm, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		masterPlanForm.setMasterKey(KeyGenerator.generateKey(masterPlanForm.getCustomerPrefix(),Constants.MASTERPLAN));
		masterPlanForm.setTransactionId(KeyGenerator.generateTransactionKey());


		List<Supplier> supplierList = supplierDAO.getSupplierListByCompanyId(user.getCompanyId());
		List<NewBuyer> buyerList = newBuyerDAOImpl.getbuyerListByCompanyId(user.getCompanyId());

		masterPlanForm.setCustomer(masterPlanForm.getCustomer());
		masterPlanForm.setCurrencySymbol(masterPlanForm.getCurrencySymbol());
		masterPlanForm.setCategory(masterPlanForm.getCategory());
		masterPlanForm.setProduct(masterPlanForm.getProduct());
		masterPlanForm.setDescription(masterPlanForm.getDescription());
		masterPlanForm.setLicence(masterPlanForm.getLicence());
		masterPlanForm.setWeight(masterPlanForm.getWeight());
		masterPlanForm.setQuantity(masterPlanForm.getQuantity());
		masterPlanForm.setBuyingCost(masterPlanForm.getBuyingCost());
//		masterPlanForm.setTenure(masterPlanForm.getTenure());
		masterPlanForm.setWorkingCapital(masterPlanForm.getWorkingCapital());
//		masterPlanForm.setWcTenure(masterPlanForm.getWcTenure());
		masterPlanForm.setSellingCost(masterPlanForm.getSellingCost());
//		masterPlanForm.setSellingTenure(masterPlanForm.getSellingTenure());
		masterPlanForm.setTransactionId(masterPlanForm.getTransactionId());
		masterPlanForm.setMasterKey(masterPlanForm.getMasterKey());
		masterPlanForm.setFinalPro(masterPlanForm.getProduct());

		if (supplierList != null && supplierList.size() > 0) {
			model.addAttribute("supplierList", supplierList);
			Map<String, List<String>> materials = new HashMap();
//			JsonObject materials = new JsonObject();
			for (Supplier s : supplierList) {
				materials.put(s.getSupplierName(),buyingCostDAO.getMaterials(s.getSupplierName()));
			}
			model.addAttribute("materials", new JSONObject(materials));
		}

		if (buyerList != null && buyerList.size() > 0) {
			model.addAttribute("buyerList", buyerList);
		}

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);
		attributes.addFlashAttribute("successFull", " cost Does not Match Buyer Cost. ");
		return new ModelAndView("masterPlanInfoConfirm", "model", model);

	}

	@RequestMapping(value = "/masterPlanInfoPost", method = RequestMethod.POST)
	public ModelAndView masterPlanInfoPost(ModelMap model, @ModelAttribute MasterPlanForm masterPlanForm, BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		masterPlanForm.setMasterKey(KeyGenerator.generateKey(masterPlanForm.getCustomerPrefix(),Constants.MASTERPLAN));
		masterPlanForm.setTransactionId(KeyGenerator.generateTransactionKey());

		String allCountrys = masterPlanForm.getCountry();
		String[] countrys = allCountrys.split(",");

		String allMaterials = masterPlanForm.getMaterial();
		String[] materials = allMaterials.split(",");

		String allSuppliers = masterPlanForm.getSupplierName();
		String[] suppliers = allSuppliers.split(",");

		String allQuantity = masterPlanForm.getQuantityStr();
		String[] quantity = allQuantity.split(",");

		String allCost = masterPlanForm.getCostStr();
		String[] cost = allCost.split(",");

		List<MasterPlanForm> masterplanList = new ArrayList<MasterPlanForm>();
		if( allMaterials!=null && !allMaterials.equals("") ) {
			for (int count = 0; count < materials.length; count++) {

				MasterPlanForm masterForm = new MasterPlanForm();

				masterForm.setCustomer(masterPlanForm.getCustomer());
				masterForm.setMasterKey(masterPlanForm.getMasterKey());
				masterForm.setTransactionId(masterPlanForm.getTransactionId());
				masterForm.setCountry(countrys[count]);
				masterForm.setSupplierName(suppliers[count]);
				masterForm.setMaterial(materials[count]);
				masterForm.setBuyerQuantity(Integer.parseInt(quantity[count]));
				masterForm.setCost(Float.parseFloat(cost[count]));

				masterplanList.add(masterForm);
			}
		}
		for (MasterPlanForm value : masterplanList) {

			BuyingCost buying = new BuyingCost();
			buying.setCustomer(value.getCustomer());
			buying.setCountry(value.getCountry());
			buying.setRegulation(value.getRegulation());
			buying.setMaterial(value.getMaterial());
			buying.setSupplierName(value.getSupplierName());
			buying.setQuantity(value.getBuyerQuantity());
			buying.setCost(value.getCost());
			buying.setMasterKey(value.getMasterKey());
			buying.setTransactionId(masterPlanForm.getTransactionId());

			buyingCostDAO.createMasterPlan(buying);
		}

		if (masterPlanForm.getBuyerName() != "") {
			String allBuyers = masterPlanForm.getBuyerName();
			if(allBuyers==null){
				allBuyers=" ";
			}
			String[] buyers = allBuyers.split(",");

			String allFinalPro = masterPlanForm.getFinalPro();
			String[] products = allFinalPro==null?null:allFinalPro.split(",");

			String allQtys = masterPlanForm.getQtyStr();
			String[] qtys = allQtys==null?null:allQtys.split(",");

			String allAmt = masterPlanForm.getAmtStr();
			String[] amt = allAmt==null?null:allAmt.split(",");

			String allCostperUnit = masterPlanForm.getCostperUnitStr();
			String[] costperunit = allCostperUnit==null?null:allCostperUnit.split(",");

			List<MasterPlanForm> masterplanList1 = new ArrayList<MasterPlanForm>();
			if(allBuyers!=null&&!allBuyers.trim().equals("")&&!allBuyers.trim().equals(" ")) {
				for (int count = 0; count < buyers.length; count++) {

					MasterPlanForm masterForm1 = new MasterPlanForm();

					masterForm1.setBuyerName(buyers[count]);
					masterForm1.setFinalPro(products[count]);
					masterForm1.setQty(Float.parseFloat(qtys[count]));
					masterForm1.setAmt(Float.parseFloat(amt[count]));
					masterForm1.setCostperUnit(Float.parseFloat(costperunit[count]));
					masterplanList1.add(masterForm1);
				}
			}
			for (MasterPlanForm value : masterplanList1) {

				SellerBuyingCost seller = new SellerBuyingCost();
				seller.setCustomerName(masterPlanForm.getCustomer());
				seller.setMasterKey(masterPlanForm.getMasterKey());
				seller.setTransactionId(masterPlanForm.getTransactionId());
				seller.setBuyerName(value.getBuyerName());
				seller.setFinalPro(value.getFinalPro());
				seller.setQuantity(value.getQty());
				seller.setAmt(value.getAmt());
				seller.setCostperUnit(value.getCostperUnit());
				sellerBuyingCostDAO.createSellerBuyingCost(seller);
			}
		}

		if (masterPlanForm.getWcReason() != "") {
			String allReason = masterPlanForm.getWcReason();
			String[] reasons = allReason==null?null:allReason.split(",");

			String allAmnt = masterPlanForm.getWcAmt();
			String[] amnt = allAmnt==null?null:allAmnt.split(",");

			List<MasterPlanForm> masterplanList2 = new ArrayList<MasterPlanForm>();
			if(reasons!=null) {
				for (int count = 0; count < reasons.length; count++) {

					MasterPlanForm masterForm = new MasterPlanForm();

					masterForm.setWcReason(reasons[count]);
					masterForm.setWcAmnt(Float.parseFloat(amnt[count]));

					masterplanList2.add(masterForm);
				}
			}
			for (MasterPlanForm value : masterplanList2) {

				WorkingCapital wc = new WorkingCapital();
				wc.setCustomer(masterPlanForm.getCustomer());
				wc.setMasterKey(masterPlanForm.getMasterKey());
				wc.setTransactionId(masterPlanForm.getTransactionId());
				wc.setAmount(value.getWcAmnt());
				wc.setReason(value.getWcReason());

				workingCapitalDAO.createMasterPlan(wc);
			}
		}
		MasterPlan master = new MasterPlan();

		master.setCustomer(masterPlanForm.getCustomer());
		master.setCompanyId(user.getCompanyId());
		master.setCurrencySymbol(masterPlanForm.getCurrencySymbol());
		master.setCategory(masterPlanForm.getCategory());
		master.setProduct(masterPlanForm.getProduct());
		master.setDescription(masterPlanForm.getDescription());
		master.setLicence(masterPlanForm.getLicence());
		master.setWeight(masterPlanForm.getWeight());
		master.setQuantity(masterPlanForm.getQuantity());
		master.setBuyingCost(masterPlanForm.getBuyingCost());
//		master.setTenure(masterPlanForm.getTenure());
		master.setTenure(1); //this will be default for all.
		master.setWorkingCapital(masterPlanForm.getWorkingCapital());
//		master.setWcTenure(masterPlanForm.getWcTenure());
		master.setWcTenure(1);
		master.setSellingCost(masterPlanForm.getSellingCost());
//		master.setSellingTenure(masterPlanForm.getSellingTenure());
		master.setSellingTenure(1);
		master.setMasterKey(masterPlanForm.getMasterKey());
		master.setTransactionId(masterPlanForm.getTransactionId());
		master.setCustomerEmail(user.getEmail());
		master.setCustomerPrefix(masterPlanForm.getCustomerPrefix());
		master.setaStatus("pending");
		master.setAccept("None");
		master.setApprovalSent("No");
		master.setFlag(1);

		Date date = DateService.loginDate;
//		master.setMasterPlanDate(date);
		master.setMasterPlanDate(dateService.getCurrentSavedLoginDate());
		masterPlanDAO.createMasterPlan(master);

		Transaction trans = new Transaction();

		trans.setTransactionId(masterPlanForm.getTransactionId());
		trans.setTransactionStatus("Master Plan Saved");
		trans.setTransactionType("Master Plan");

		transcationDAOImpl.insertTransaction(trans);
		// }

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("masterPlanTransaction", "model", model);

	}

	@RequestMapping(value = "/masterPlanInfoDraft", method = RequestMethod.POST)
	public ModelAndView masterPlanInfoDraft(ModelMap model, @ModelAttribute MasterPlanForm masterPlanForm, BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		String allCountrys = masterPlanForm.getCountry();
		String[] countrys = allCountrys.split(",");

		String allMaterials = masterPlanForm.getMaterial();
		String[] materials = allMaterials.split(",");

		String allSuppliers = masterPlanForm.getSupplierName();
		String[] suppliers = allSuppliers.split(",");

		String allQuantity = masterPlanForm.getQuantityStr();
		String[] quantity = allQuantity.split(",");

		String allCost = masterPlanForm.getCostStr();
		String[] cost = allCost.split(",");

		List<MasterPlanForm> masterplanList = new ArrayList<MasterPlanForm>();
		for (int count = 0; count < materials.length; count++) {

			MasterPlanForm masterForm = new MasterPlanForm();

			masterForm.setCustomer(masterPlanForm.getCustomer());
			masterForm.setMasterKey(masterPlanForm.getMasterKey());
			masterForm.setTransactionId(masterPlanForm.getTransactionId());
			masterForm.setCountry(countrys[count]);
			masterForm.setSupplierName(suppliers[count]);
			masterForm.setMaterial(materials[count]);
			masterForm.setBuyerQuantity(Integer.parseInt(quantity[count]));
			masterForm.setCost(Float.parseFloat(cost[count]));

			masterplanList.add(masterForm);
		}
		for (MasterPlanForm value : masterplanList) {

			DraftBuyingCost buying = new DraftBuyingCost();
			buying.setCustomer(value.getCustomer());
			buying.setCountry(value.getCountry());
			buying.setRegulation(value.getRegulation());
			buying.setMaterial(value.getMaterial());
			buying.setSupplierName(value.getSupplierName());
			buying.setQuantity(value.getBuyerQuantity());
			buying.setCost(value.getCost());
			buying.setMasterKey(value.getMasterKey());
			buying.setTransactionId(masterPlanForm.getTransactionId());

			draftBuyingCostDAO.createDraftMasterPlan(buying);
		}

		if (masterPlanForm.getBuyerName() != "") {
			String allBuyers = masterPlanForm.getBuyerName();
			String[] buyers = allBuyers.split(",");

			String allFinalPro = masterPlanForm.getFinalPro();
			String[] products = allFinalPro.split(",");

			String allQtys = masterPlanForm.getQtyStr();
			String[] qtys = allQtys.split(",");

			String allAmt = masterPlanForm.getAmtStr();
			String[] amt = allAmt.split(",");

			String allCostperUnit = masterPlanForm.getCostperUnitStr();
			String[] costperunit = allCostperUnit.split(",");

			List<MasterPlanForm> masterplanList1 = new ArrayList<MasterPlanForm>();
			for (int count = 0; count < buyers.length; count++) {

				MasterPlanForm masterForm1 = new MasterPlanForm();

				masterForm1.setBuyerName(buyers[count]);
				masterForm1.setFinalPro(products[count]);
				masterForm1.setQty(Float.parseFloat(qtys[count]));
				masterForm1.setAmt(Float.parseFloat(amt[count]));
				masterForm1.setCostperUnit(Float.parseFloat(costperunit[count]));
				masterplanList1.add(masterForm1);
			}
			for (MasterPlanForm value : masterplanList1) {

				DraftSellerBuyingCost seller = new DraftSellerBuyingCost();
				seller.setCustomerName(masterPlanForm.getCustomer());
				seller.setMasterKey(masterPlanForm.getMasterKey());
				seller.setTransactionId(masterPlanForm.getTransactionId());
				seller.setBuyerName(value.getBuyerName());
				seller.setFinalPro(value.getFinalPro());
				seller.setQuantity(value.getQty());
				seller.setAmt(value.getAmt());
				seller.setCostperUnit(value.getCostperUnit());
				draftSellerBuyingCostDAO.createDraftSellerBuyingCost(seller);
			}
		}

		if (masterPlanForm.getWcReason() != "") {
			String allReason = masterPlanForm.getWcReason();
			String[] reasons = allReason.split(",");

			String allAmnt = masterPlanForm.getWcAmt();
			String[] amnt = allAmnt.split(",");

			List<MasterPlanForm> masterplanList2 = new ArrayList<MasterPlanForm>();
			for (int count = 0; count < reasons.length; count++) {

				MasterPlanForm masterForm = new MasterPlanForm();

				masterForm.setWcReason(reasons[count]);
				masterForm.setWcAmnt(Float.parseFloat(amnt[count]));

				masterplanList2.add(masterForm);
			}
			for (MasterPlanForm value : masterplanList2) {

				DraftWorkingCapital wc = new DraftWorkingCapital();
				wc.setCustomer(masterPlanForm.getCustomer());
				wc.setMasterKey(masterPlanForm.getMasterKey());
				wc.setTransactionId(masterPlanForm.getTransactionId());
				wc.setAmount(value.getWcAmnt());
				wc.setReason(value.getWcReason());

				draftWorkingCapitalDAO.createDraftMasterPlan(wc);
			}
		}
//		MasterPlan master = new MasterPlan();
//
//		master.setCustomer(masterPlanForm.getCustomer());
//		master.setCurrencySymbol(masterPlanForm.getCurrencySymbol());
//		master.setCategory(masterPlanForm.getCategory());
//		master.setProduct(masterPlanForm.getProduct());
//		master.setDescription(masterPlanForm.getDescription());
//		master.setLicence(masterPlanForm.getLicence());
//		master.setWeight(masterPlanForm.getWeight());
//		master.setQuantity(masterPlanForm.getQuantity());
//		master.setBuyingCost(masterPlanForm.getBuyingCost());
//		master.setTenure(masterPlanForm.getTenure());
//		master.setWorkingCapital(masterPlanForm.getWorkingCapital());
//		master.setWcTenure(masterPlanForm.getWcTenure());
//		master.setSellingCost(masterPlanForm.getSellingCost());
//		master.setSellingTenure(masterPlanForm.getSellingTenure());
//		master.setMasterKey(masterPlanForm.getMasterKey());
//		master.setTransactionId(masterPlanForm.getTransactionId());
//		master.setCustomerEmail(user.getEmail());
//		master.setCustomerPrefix(masterPlanForm.getCustomerPrefix());
//		master.setaStatus("pending");
//		master.setAccept("None");
//		master.setApprovalSent("No");
//		master.setFlag(1);
//
//		Date date = DateService.loginDate;
//		master.setMasterPlanDate(date);
//
//		masterPlanDAO.createMasterPlan(master);

		DraftsMasterPlan drafts = new DraftsMasterPlan();

		drafts.setCustomer(masterPlanForm.getCustomer());
		drafts.setCurrencySymbol(masterPlanForm.getCurrencySymbol());
		drafts.setCategory(masterPlanForm.getCategory());
		drafts.setProduct(masterPlanForm.getProduct());
		drafts.setDescription(masterPlanForm.getDescription());
		drafts.setLicence(masterPlanForm.getLicence());
		drafts.setWeight(masterPlanForm.getWeight());
		drafts.setQuantity(masterPlanForm.getQuantity());
		drafts.setBuyingCost(masterPlanForm.getBuyingCost());
		drafts.setTenure(masterPlanForm.getTenure());
		drafts.setWorkingCapital(masterPlanForm.getWorkingCapital());
		drafts.setWcTenure(masterPlanForm.getWcTenure());
		drafts.setSellingCost(masterPlanForm.getSellingCost());
		drafts.setSellingTenure(masterPlanForm.getSellingTenure());
		drafts.setTransactionId(masterPlanForm.getTransactionId());
		drafts.setMasterKey(masterPlanForm.getMasterKey());
		Date date = DateService.loginDate;
		drafts.setDraftsDate(date);
		drafts.setFlag(0);
		draftsMasterPlanDAO.createDrafts(drafts);

//		Transaction trans = new Transaction();
//
//		trans.setTransactionId(masterPlanForm.getTransactionId());
//		trans.setTransactionStatus("Master Plan Saved");
//		trans.setTransactionType("Master Plan");
//
//		transcationDAOImpl.insertTransaction(trans);
		// }

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);
		attributes.addFlashAttribute("successFull", " Saved to Drafts. ");

		return new ModelAndView("redirect:masterPlanCurrency");

	}

	@RequestMapping(value = "/draftsMasterPlan", method = RequestMethod.POST)
	public ModelAndView draftsMasterPlan(ModelMap model, @ModelAttribute MasterPlanForm masterPlanForm, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		DraftsMasterPlan drafts = new DraftsMasterPlan();

		drafts.setCustomer(masterPlanForm.getCustomer());
		drafts.setCurrencySymbol(masterPlanForm.getCurrencySymbol());
		drafts.setCategory(masterPlanForm.getCategory());
		drafts.setProduct(masterPlanForm.getProduct());
		drafts.setDescription(masterPlanForm.getDescription());
		drafts.setLicence(masterPlanForm.getLicence());
		drafts.setWeight(masterPlanForm.getWeight());
		drafts.setQuantity(masterPlanForm.getQuantity());
		drafts.setBuyingCost(masterPlanForm.getBuyingCost());
		drafts.setTenure(masterPlanForm.getTenure());
		drafts.setWorkingCapital(masterPlanForm.getWorkingCapital());
		drafts.setWcTenure(masterPlanForm.getWcTenure());
		drafts.setSellingCost(masterPlanForm.getSellingCost());
		drafts.setSellingTenure(masterPlanForm.getSellingTenure());
		drafts.setTransactionId(masterPlanForm.getTransactionId());
		Date date = DateService.loginDate;
		drafts.setDraftsDate(date);
		drafts.setFlag(0);
		draftsMasterPlanDAO.createDrafts(drafts);

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);
		attributes.addFlashAttribute("successFull", " Saved to Drafts. ");

		return new ModelAndView("redirect:masterPlan");

	}

	@RequestMapping(value = "/masterPlanTransaction", method = RequestMethod.GET)
	public ModelAndView masterPlanTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("masterPlanTransaction", "model", model);

	}

	@RequestMapping(value = "/masterPlanPending", method = RequestMethod.GET)
	public ModelAndView masterPlanPending(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<MasterPlan> masterPendingList = masterPlanDAO.getByStatusAndCust(user.getUserName()).getResultList();

		if (masterPendingList != null && masterPendingList.size() > 0) {

			model.put("masterPendingList", masterPendingList);
			return new ModelAndView("masterPlanPending", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/collateral", method = RequestMethod.GET)
	public ModelAndView collateral(@RequestParam("id") Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		MasterPlan plan = masterPlanDAO.getByMasterPlanId(id);

		collateralForm.setMasterKey(plan.getMasterKey());
		collateralForm.setUserName(plan.getCustomer());
		collateralForm.setTransactionId(plan.getTransactionId());
		collateralForm.setId(plan.getId());

		model.put("user", user);
		model.put("collateralForm", collateralForm);
		return new ModelAndView("collateral", "model", model);

	}

	@RequestMapping(value = "/collateralConfirm", method = RequestMethod.POST)
	public ModelAndView collateralConfirm(ModelMap model, @ModelAttribute CollateralForm collateralForm, BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Collateral collateral = new Collateral();

		collateralForm.setId(collateralForm.getId());
		collateralForm.setUserName(collateralForm.getUserName());
		collateralForm.setMasterKey(collateralForm.getMasterKey());
		collateralForm.setTransactionId(collateralForm.getTransactionId());
		collateralForm.setProperty(collateralForm.getProperty());
		collateralForm.setPropertyAddress(collateralForm.getPropertyAddress());
		collateralForm.setPropertyArea(collateralForm.getPropertyArea());
		collateralForm.setPropertyType(collateralForm.getPropertyType());
		collateralForm.setpCostPerArea(collateralForm.getpCostPerArea());
		collateralForm.setPropertyPurchaseValue(collateralForm.getPropertyPurchaseValue());
		collateralForm.setPropertyCurrValue(collateralForm.getPropertyCurrValue());
		collateralForm.setpExisCharge(collateral.getpExisCharge());
		collateralForm.setVehicle(collateralForm.getVehicle());
		collateralForm.setvType(collateralForm.getvType());
		collateralForm.setvPurchaseValue(collateralForm.getvPurchaseValue());
		collateralForm.setVcurrValue(collateralForm.getVcurrValue());
		collateralForm.setDetails(collateralForm.getDetails());
		collateralForm.setvInsurence(collateralForm.getvInsurence());
		collateralForm.setvInsuranceStart(collateralForm.getvInsuranceStart());
		collateralForm.setvInsuranceEnd(collateralForm.getvInsuranceEnd());
		collateralForm.setvExistingCharge(collateralForm.getvExistingCharge());
		collateralForm.setCash(collateralForm.getCash());
		collateralForm.setAmount(collateralForm.getAmount());
		collateralForm.setFinInstitution(collateralForm.getFinInstitution());
		collateralForm.setBranch(collateralForm.getBranch());
		collateralForm.setLocation(collateralForm.getLocation());
		collateralForm.setfDStartDate(collateralForm.getfDStartDate());
		collateralForm.setfDEndDate(collateralForm.getfDEndDate());
		collateralForm.setfDInterestRate(collateralForm.getfDInterestRate());
		collateralForm.setcExistimgCharge(collateralForm.getcExistimgCharge());
		collateralForm.setInsurancePolicy(collateralForm.getInsurancePolicy());
		collateralForm.setBenficiaryName(collateralForm.getBenficiaryName());
		collateralForm.setNominee(collateralForm.getNominee());
		collateralForm.setPolicyStartDate(collateralForm.getPolicyStartDate());
		collateralForm.setPolicyEndDate(collateralForm.getPolicyEndDate());
		collateralForm.setPolicyExisCharge(collateralForm.getPolicyExisCharge());
		collateralForm.setShares(collateralForm.getShares());
		collateralForm.setCompanyShares(collateralForm.getCompanyShares());
		collateralForm.setSharesExisPrice(collateralForm.getSharesExisPrice());
		collateralForm.setDetails(collateralForm.getDetails());
		collateralForm.setTurnOver(collateralForm.getTurnOver());
		collateralForm.setProfit(collateralForm.getProfit());
		collateralForm.setLineOfActivity(collateralForm.getLineOfActivity());
		collateralForm.setSharesExisCharge(collateralForm.getSharesExisCharge());
		collateralForm.setMachinery(collateralForm.getMachinery());
		collateralForm.setMachineryLocation(collateralForm.getMachineryLocation());
		collateralForm.setMachineryPurchaseValue(collateralForm.getMachineryPurchaseValue());
		collateralForm.setMachineryMarketValue(collateralForm.getMachineryMarketValue());
		collateralForm.setMachineryDetails(collateralForm.getMachineryDetails());
		collateralForm.setRegistrationNum(collateralForm.getRegistrationNum());
		collateralForm.setMachineryExisCharge(collateralForm.getMachineryExisCharge());

		model.put("user", user);
		model.put("collateralForm", collateralForm);

		return new ModelAndView("collateralConfirm", "model", model);

	}

	@RequestMapping(value = "/collateralPost", method = RequestMethod.POST)
	public ModelAndView collateralPost(ModelMap model, @ModelAttribute CollateralForm collateralForm, BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Collateral collateral = new Collateral();

		collateral.setUserName(collateralForm.getUserName());
		collateral.setMasterKey(collateralForm.getMasterKey());
		collateral.setTransactionId(collateralForm.getTransactionId());
		collateral.setProperty(collateralForm.getProperty());
		collateral.setPropertyAddress(collateralForm.getPropertyAddress());
		collateral.setPropertyArea(collateralForm.getPropertyArea());
		collateral.setPropertyType(collateralForm.getPropertyType());
		collateral.setpCostPerArea(collateralForm.getpCostPerArea());
		collateral.setPropertyPurchaseValue(collateralForm.getPropertyPurchaseValue());
		collateral.setPropertyCurrValue(collateralForm.getPropertyCurrValue());
		collateral.setpExisCharge(collateral.getpExisCharge());
		collateral.setVehicle(collateralForm.getVehicle());
		collateral.setvType(collateralForm.getvType());
		collateral.setvPurchaseValue(collateralForm.getvPurchaseValue());
		collateral.setVcurrValue(collateralForm.getVcurrValue());
		collateral.setDetails(collateralForm.getDetails());
		collateral.setvInsurence(collateralForm.getvInsurence());
		collateral.setvInsuranceStart(collateralForm.getvInsuranceStart());
		collateral.setvInsuranceEnd(collateralForm.getvInsuranceEnd());
		collateral.setvExistingCharge(collateralForm.getvExistingCharge());
		collateral.setCash(collateralForm.getCash());
		collateral.setAmount(collateralForm.getAmount());
		collateral.setFinInstitution(collateralForm.getFinInstitution());
		collateral.setBranch(collateralForm.getBranch());
		collateral.setLocation(collateralForm.getLocation());
		collateral.setfDStartDate(collateralForm.getfDStartDate());
		collateral.setfDEndDate(collateralForm.getfDEndDate());
		collateral.setfDInterestRate(collateralForm.getfDInterestRate());
		collateral.setcExistimgCharge(collateralForm.getcExistimgCharge());
		collateral.setInsurancePolicy(collateralForm.getInsurancePolicy());
		collateral.setBenficiaryName(collateralForm.getBenficiaryName());
		collateral.setNominee(collateralForm.getNominee());
		collateral.setPolicyStartDate(collateralForm.getPolicyStartDate());
		collateral.setPolicyEndDate(collateralForm.getPolicyEndDate());
		collateral.setPolicyExisCharge(collateralForm.getPolicyExisCharge());
		collateral.setShares(collateralForm.getShares());
		collateral.setCompanyShares(collateralForm.getCompanyShares());
		collateral.setSharesExisPrice(collateralForm.getSharesExisPrice());
		collateral.setDetails(collateralForm.getDetails());
		collateral.setTurnOver(collateralForm.getTurnOver());
		collateral.setProfit(collateralForm.getProfit());
		collateral.setLineOfActivity(collateralForm.getLineOfActivity());
		collateral.setSharesExisCharge(collateralForm.getSharesExisCharge());
		collateral.setMachinery(collateralForm.getMachinery());
		collateral.setMachineryLocation(collateralForm.getMachineryLocation());
		collateral.setMachineryPurchaseValue(collateralForm.getMachineryPurchaseValue());
		collateral.setMachineryMarketValue(collateralForm.getMachineryMarketValue());
		collateral.setMachineryDetails(collateralForm.getMachineryDetails());
		collateral.setRegistrationNum(collateralForm.getRegistrationNum());
		collateral.setMachineryExisCharge(collateralForm.getMachineryExisCharge());

		collateralDAO.createUser(collateral);

		Transaction trans = new Transaction();
		trans.setTransactionId(masterPlanForm.getTransactionId());
		trans.setTransactionStatus("Collateral");
		trans.setTransactionType("Submitted Successfully");

		transcationDAOImpl.insertTransaction(trans);

		model.put("user", user);
		model.put("collateralForm", collateralForm);

		return new ModelAndView("collateralTransaction", "model", model);

	}

	@RequestMapping(value = "/collateralTransaction", method = RequestMethod.GET)
	public ModelAndView collateralTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("collateralTransaction", "model", model);

	}

	@RequestMapping(value = "/fileUploadForm", method = RequestMethod.GET)
	public ModelAndView getUploadForm(RedirectAttributes attribute, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("uploadedFileForm", uploadedFileForm);

		return new ModelAndView("uploadForm");
	}

	@RequestMapping("/fileUpload")
	public ModelAndView fileUploaded(ModelMap model, RedirectAttributes attribute, @ModelAttribute UploadedFileForm uploadedFileForm, BindingResult result) throws RuntimeException, IOException {

		EndUser user = getCurrentLoggedUserDetails();
		uploadedFileForm.setTransactionId(KeyGenerator.generateTransactionKey());

		UploadedFile upload = new UploadedFile();

		List<MultipartFile> files = uploadedFileForm.getFiles();
		Set<byte[]> filesList = new HashSet<byte[]>();
		Set<String> fileNameList = new HashSet<String>();
		for (MultipartFile multipartFile : files) {
			filesList.add(multipartFile.getBytes());
			fileNameList.add(multipartFile.getOriginalFilename());
		}
		upload.setFiles(filesList);
		upload.setFileNames(fileNameList);
		upload.setCustomerName(user.getUserName());
		upload.setDocument(uploadedFileForm.getDocument());
		upload.setCustomerHeadKey(uploadedFileForm.getCustomerHeadKey());
		upload.setCustomerHeadName(uploadedFileForm.getCustomerHeadName());
		upload.setReason(uploadedFileForm.getReason());
		upload.setUploadComment(uploadedFileForm.getUploadComment());
		upload.setTransactionId(uploadedFileForm.getTransactionId());
		upload.setStatus("Pending");
		Date date = DateService.loginDate;
		upload.setUploadDate(date);

		uploadDaoImpl.createUser(upload);

		Transaction trans = new Transaction();

		trans.setTransactionId(uploadedFileForm.getTransactionId());
		trans.setTransactionStatus("Upload Document");
		trans.setTransactionType("Uploaded Successfully");

		transcationDAOImpl.insertTransaction(trans);
		model.put("uploadedFileForm", uploadedFileForm);
		model.put("user", user);

		attribute.addFlashAttribute("success", "Saved Successfully. ");
		return new ModelAndView("showFile", "model", model);
	}

	// Po Approved List
	@RequestMapping(value = "/poApprovedList", method = RequestMethod.GET)
	public ModelAndView PoApprovedList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> poList = purchaseOrderDAO.getPoListBycustomerName(user.getUserName()).getResultList();
		if (poList != null && poList.size() > 0) {
			purchaseOrderForm.setPoKey(poList.get(0).getPoKey());
			model.put("poList", poList);
			model.put("purchaseOrderForm", purchaseOrderForm);

			return new ModelAndView("poApprovedList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	// PO Upload
	@RequestMapping(value = "/fileUploadFormPO", method = RequestMethod.GET)
	public ModelAndView getfileUploadFormPO(@RequestParam("id") Long id, RedirectAttributes attribute, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		//PoUpload poUpload = poUploadDAO.findId(id);
		//poUploadForm.setPoKey(poUpload.getPoKey());
		model.put("poUploadForm", poUploadForm);
		model.put("user", user);
		return new ModelAndView("fileUploadFormPO");

	}

	@RequestMapping("/fileUploadPO")
	public ModelAndView fileUploadedPO(ModelMap model, RedirectAttributes attribute, @ModelAttribute PoUploadForm poUploadForm, BindingResult result) throws RuntimeException, IOException {

		EndUser user = getCurrentLoggedUserDetails();


		poUploadForm.setTransactionId(KeyGenerator.generateTransactionKey());

		PoUpload poupload = new PoUpload();

		List<MultipartFile> files = poUploadForm.getFiles();
		Set<byte[]> filesList = new HashSet<byte[]>();
		Set<String> fileNameList = new HashSet<String>();
		for (MultipartFile multipartFile : files) {
			filesList.add(multipartFile.getBytes());
			fileNameList.add(multipartFile.getOriginalFilename());
		}
		poupload.setFiles(filesList);
		poupload.setFileNames(fileNameList);

		poupload.setCustomerName(user.getUserName());
		poupload.setPoKey(poUploadForm.getPoKey());
		poupload.setDocument(poUploadForm.getDocument());
		poupload.setCustomerHeadKey(poUploadForm.getCustomerHeadKey());
		poupload.setCustomerHeadName(poUploadForm.getCustomerHeadName());
		poupload.setReason(poUploadForm.getReason());
		poupload.setTransactionId(poUploadForm.getTransactionId());
		poupload.setStatus("Pending");
		Date date = DateService.loginDate;
		poupload.setUploadDate(date);

		poUploadDAO.createUser(poupload);
		Transaction trans = new Transaction();

		trans.setTransactionId(poUploadForm.getTransactionId());
		trans.setTransactionStatus("PO Upload Document");
		trans.setTransactionType("PO Uploaded Successfully");

		transcationDAOImpl.insertTransaction(trans);
		model.put("poUploadForm", poUploadForm);
		model.put("user", user);

		attribute.addFlashAttribute("success", "Saved Successfully. ");
		return new ModelAndView("showFileForPO", "model", model);
	}

	@RequestMapping(value = "/poDocumnetList", method = RequestMethod.GET)
	public ModelAndView PoDocumnetList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PoUpload> docList = poUploadDAO.findByName(user.getUserName()).getResultList();

		model.put("user", user);
		if (docList != null && docList.size() > 0) {
			model.put("docList", docList);

			return new ModelAndView("poDocumnetList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	// Invoice Approved List
	@RequestMapping(value = "/invoiceApprovedList", method = RequestMethod.GET)
	public ModelAndView InvoiceApprovedList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getPoListBycustomerName(user.getUserName()).getResultList();
		if (invoiceList != null && invoiceList.size() > 0) {
			inoviceUploadForm.setPoKey(invoiceList.get(0).getPoKey());
			model.put("invoiceList", invoiceList);
			model.put("inoviceUploadForm", inoviceUploadForm);

			return new ModelAndView("invoiceApprovedList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	// Invoice Upload
	@RequestMapping(value = "/fileUploadFormInvoice", method = RequestMethod.GET)
	public ModelAndView getfileUploadFormInvoice(@RequestParam("id") Long id, RedirectAttributes attribute, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		InvoiceUpload invoiceUpload = invoiceUploadDAO.findId(id);
		/* inoviceUploadForm.setPoKey(invoiceUpload.getPoKey()); */
		inoviceUploadForm.setPoKey(KeyGenerator.generateTransactionKey());

		model.put("user", user);
		model.put("inoviceUploadForm", inoviceUploadForm);

		return new ModelAndView("fileUploadFormInvoice");

	}

	@RequestMapping("/fileUploadInovice")
	public ModelAndView fileUploadedInvoice(ModelMap model, RedirectAttributes attribute, @ModelAttribute InoviceUploadForm inoviceUploadForm) throws RuntimeException, IOException {

		EndUser user = getCurrentLoggedUserDetails();


		inoviceUploadForm.setTransactionId(KeyGenerator.generateTransactionKey());

		InvoiceUpload invoiceupload = new InvoiceUpload();

		List<MultipartFile> files = inoviceUploadForm.getFiles();
		Set<byte[]> filesList = new HashSet<byte[]>();
		Set<String> fileNameList = new HashSet<String>();
		for (MultipartFile multipartFile : files) {
			filesList.add(multipartFile.getBytes());
			fileNameList.add(multipartFile.getOriginalFilename());
		}
		invoiceupload.setFiles(filesList);
		invoiceupload.setFileNames(fileNameList);

		invoiceupload.setCustomerName(user.getUserName());
		invoiceupload.setPoKey(inoviceUploadForm.getPoKey());
		invoiceupload.setDocument(inoviceUploadForm.getDocument());
		invoiceupload.setCustomerHeadKey(inoviceUploadForm.getCustomerHeadKey());
		invoiceupload.setCustomerHeadName(inoviceUploadForm.getCustomerHeadName());
		invoiceupload.setReason(inoviceUploadForm.getReason());
		invoiceupload.setTransactionId(inoviceUploadForm.getTransactionId());
		invoiceupload.setStatus("Pending");
		Date date = DateService.loginDate;
		invoiceupload.setUploadDate(date);

		invoiceUploadDAO.createUser(invoiceupload);
		Transaction trans = new Transaction();

		trans.setTransactionId(inoviceUploadForm.getTransactionId());
		trans.setTransactionStatus("PO Upload Document");
		trans.setTransactionType("PO Uploaded Successfully");

		transcationDAOImpl.insertTransaction(trans);
		model.put("inoviceUploadForm", inoviceUploadForm);
		model.put("user", user);

		attribute.addFlashAttribute("success", "Saved Successfully. ");
		return new ModelAndView("showFileForInvoice", "model", model);
	}

	@RequestMapping(value = "/invoiceDocumnetList", method = RequestMethod.GET)
	public ModelAndView InvoiceDocumnetList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<InvoiceUpload> docList = invoiceUploadDAO.findByName(user.getUserName()).getResultList();

		model.put("user", user);
		if (docList != null && docList.size() > 0) {
			model.put("docList", docList);

			return new ModelAndView("invoiceDocumnetList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	// selective Buyer Document List
	@RequestMapping(value = "/selectdocumentApprovalList", method = RequestMethod.GET)
	public ModelAndView selectdocumentApprovalList(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		List<UploadedFile> uploadDocumentList = uploadDaoImpl.findByName(user.getUserName()).getResultList();

		if (uploadDocumentList != null && uploadDocumentList.size() > 0) {

			model.put("uploadDocumentList", uploadDocumentList);
			return new ModelAndView("selectdocumentApprovalList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/masterPlanDraftsList", method = RequestMethod.GET)
	public ModelAndView masterPlanDraftsList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		Company comp = companyDAO.getByCompanyId(user.getCompanyId());
		String company = comp.getCompanyName();
		model.put("user", user);
		model.put("company", company);
		List<DraftsMasterPlan> draftsList = draftsMasterPlanDAO.getByCustomerAndFlag(user.getUserName()).getResultList();

		if (draftsList != null && draftsList.size() > 0) {

			model.put("draftsList", draftsList);
			return new ModelAndView("masterPlanDraftsList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/draftsMasterPlan1", method = RequestMethod.GET)
	public ModelAndView draftsMasterPlanConfirm(@RequestParam("id") Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		DraftsMasterPlan drafts = draftsMasterPlanDAO.getByMasterPlanId(id);

		masterPlanForm.setId(drafts.getId());
		masterPlanForm.setCustomer(drafts.getCustomer());
		masterPlanForm.setCurrencySymbol(drafts.getCurrencySymbol());
		masterPlanForm.setBuyingCost(drafts.getBuyingCost());
		masterPlanForm.setCategory(drafts.getCategory());
		masterPlanForm.setProduct(drafts.getProduct());
		masterPlanForm.setDescription(drafts.getDescription());
		masterPlanForm.setLicence(drafts.getLicence());
		masterPlanForm.setWeight(drafts.getWeight());
		masterPlanForm.setQuantity(drafts.getQuantity());
		masterPlanForm.setTenure(drafts.getTenure());
		masterPlanForm.setWorkingCapital(drafts.getWorkingCapital());
		masterPlanForm.setWcTenure(drafts.getWcTenure());
		masterPlanForm.setSellingCost(drafts.getSellingCost());
		masterPlanForm.setSellingTenure(drafts.getSellingTenure());
		masterPlanForm.setMasterKey(drafts.getMasterKey());

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);
		return new ModelAndView("draftsMasterPlan1", "model", model);
	}

	@RequestMapping(value = "/draftsMasterPlanInfoConfirm", method = RequestMethod.POST)
	public ModelAndView draftsMasterPlanInfoConfirm(ModelMap model, @ModelAttribute MasterPlanForm masterPlanForm, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		//masterPlanForm.setMasterKey(KeyGenerator.generateKey(masterPlanForm.getCustomerPrefix(),Constants.MASTERPLAN));
		masterPlanForm.setTransactionId(KeyGenerator.generateTransactionKey());

		masterPlanForm.setId(masterPlanForm.getId());
		masterPlanForm.setCustomer(masterPlanForm.getCustomer());
		masterPlanForm.setCurrencySymbol(masterPlanForm.getCurrencySymbol());
		masterPlanForm.setCategory(masterPlanForm.getCategory());
		masterPlanForm.setProduct(masterPlanForm.getProduct());
		masterPlanForm.setDescription(masterPlanForm.getDescription());
		masterPlanForm.setLicence(masterPlanForm.getLicence());
		masterPlanForm.setWeight(masterPlanForm.getWeight());
		masterPlanForm.setQuantity(masterPlanForm.getQuantity());
		masterPlanForm.setBuyingCost(masterPlanForm.getBuyingCost());
		masterPlanForm.setTenure(masterPlanForm.getTenure());
		masterPlanForm.setWorkingCapital(masterPlanForm.getWorkingCapital());
		masterPlanForm.setWcTenure(masterPlanForm.getWcTenure());
		masterPlanForm.setSellingCost(masterPlanForm.getSellingCost());
		masterPlanForm.setSellingTenure(masterPlanForm.getSellingTenure());
		masterPlanForm.setTransactionId(masterPlanForm.getTransactionId());
		masterPlanForm.setMasterKey(masterPlanForm.getMasterKey());

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		List<DraftBuyingCost> draftBuyingCostList = draftBuyingCostDAO.getDraftBuyingCostBYMAsterKey(masterPlanForm.getMasterKey()).getResultList();

		List<DraftSellerBuyingCost> draftSellerBuyingCostList = draftSellerBuyingCostDAO.getDraftSellerBuyingCostByMasterKey(masterPlanForm.getMasterKey()).getResultList();

		List<DraftWorkingCapital> draftWorkingCapitalList = draftWorkingCapitalDAO.getDWCBYMAsterKey(masterPlanForm.getMasterKey()).getResultList();

		model.put("draftBuyingCostList", draftBuyingCostList);
		model.put("draftSellerBuyingCostList", draftSellerBuyingCostList);
		model.put("draftWorkingCapitalList", draftWorkingCapitalList);

		attributes.addFlashAttribute("successFull", " cost Does not Match Buyer Cost. ");
		return new ModelAndView("draftsMasterPlanInfoConfirm", "model", model);

	}

	@RequestMapping(value = "/draftsMasterPlanInfoPost", method = RequestMethod.POST)
	public ModelAndView DraftsMasterPlanInfoPost(ModelMap model, @ModelAttribute MasterPlanForm masterPlanForm, BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		//masterPlanForm.setMasterKey(KeyGenerator.generateKey(masterPlanForm.getCustomerPrefix(),Constants.MASTERPLAN));
		masterPlanForm.setTransactionId(KeyGenerator.generateTransactionKey());

		Float buyingCost = masterPlanForm.getBuyingCost();
		Float totalBreakDownCost = 0.0f;

		String allCountrys = masterPlanForm.getCountry();
		String[] countrys = allCountrys.split(",");

		String allMaterials = masterPlanForm.getMaterial();
		String[] materials = allMaterials.split(",");

		String allQuantity = masterPlanForm.getQuantityStr();
		String[] quantity = allQuantity.split(",");

		String allCost = masterPlanForm.getCostStr();
		String[] cost = allCost.split(",");

		List<MasterPlanForm> masterplanList = new ArrayList<MasterPlanForm>();
		for (int count = 0; count < materials.length; count++) {

			MasterPlanForm masterForm = new MasterPlanForm();

			masterForm.setCustomer(masterPlanForm.getCustomer());
			masterForm.setMasterKey(masterPlanForm.getMasterKey());
			masterForm.setTransactionId(masterPlanForm.getTransactionId());
			masterForm.setCountry(countrys[count]);
			masterForm.setMaterial(materials[count]);
			masterForm.setBuyerQuantity(Integer.parseInt(quantity[count]));
			masterForm.setCost(Float.parseFloat(cost[count]));

			totalBreakDownCost = totalBreakDownCost + masterForm.getCost();

			masterplanList.add(masterForm);
		}
		if (totalBreakDownCost.compareTo(buyingCost) != 0) {

			model.put("user", user);
			model.put("masterPlanForm", masterPlanForm);

			attributes.addFlashAttribute("successFull", " cost Does not Match Buyer Cost. ");

			return new ModelAndView("draftsMasterPlanInfoConfirm", "model", model);
		} else {

			for (MasterPlanForm value : masterplanList) {

				BuyingCost buying = new BuyingCost();
				buying.setCustomer(value.getCustomer());
				buying.setCountry(value.getCountry());
				buying.setRegulation(value.getRegulation());
				buying.setMaterial(value.getMaterial());
				buying.setQuantity(value.getBuyerQuantity());
				buying.setCost(value.getCost());
				buying.setMasterKey(value.getMasterKey());
				buying.setTransactionId(masterPlanForm.getTransactionId());

				buyingCostDAO.createMasterPlan(buying);
			}
			if (masterPlanForm.getBuyerName() != "") {
				String allBuyers = masterPlanForm.getBuyerName();
				String[] buyers = allBuyers.split(",");

				String allFinalPro = masterPlanForm.getFinalPro();
				String[] products = allFinalPro.split(",");

				String allQtys = masterPlanForm.getQtyStr();
				String[] qtys = allQtys.split(",");

				String allAmt = masterPlanForm.getAmtStr();
				String[] amt = allAmt.split(",");

				String allCostperUnit = masterPlanForm.getCostperUnitStr();
				String[] costperunit = allCostperUnit.split(",");

				List<MasterPlanForm> masterplanList1 = new ArrayList<MasterPlanForm>();
				for (int count = 0; count < buyers.length; count++) {

					MasterPlanForm masterForm1 = new MasterPlanForm();

					masterForm1.setBuyerName(buyers[count]);
					masterForm1.setFinalPro(products[count]);
					masterForm1.setQty(Float.parseFloat(qtys[count]));
					masterForm1.setAmt(Float.parseFloat(amt[count]));
					masterForm1.setCostperUnit(Float.parseFloat(costperunit[count]));
					masterplanList1.add(masterForm1);
				}
				for (MasterPlanForm value : masterplanList1) {

					SellerBuyingCost seller = new SellerBuyingCost();
					seller.setCustomerName(masterPlanForm.getCustomer());
					seller.setMasterKey(masterPlanForm.getMasterKey());
					seller.setTransactionId(masterPlanForm.getTransactionId());
					seller.setBuyerName(value.getBuyerName());
					seller.setFinalPro(value.getFinalPro());
					seller.setQuantity(value.getQty());
					seller.setAmt(value.getAmt());
					seller.setCostperUnit(value.getCostperUnit());
					sellerBuyingCostDAO.createSellerBuyingCost(seller);
				}
			}
			if (masterPlanForm.getWcReason() != "") {
				String allReason = masterPlanForm.getWcReason();
				String[] reasons = allReason.split(",");

				String allAmnt = masterPlanForm.getWcAmt();
				String[] amnt = allAmnt.split(",");

				List<MasterPlanForm> masterplanList2 = new ArrayList<MasterPlanForm>();
				for (int count = 0; count < reasons.length; count++) {

					MasterPlanForm masterForm = new MasterPlanForm();

					masterForm.setWcReason(reasons[count]);
					masterForm.setWcAmnt(Float.parseFloat(amnt[count]));

					masterplanList2.add(masterForm);
				}
				for (MasterPlanForm value : masterplanList2) {

					WorkingCapital wc = new WorkingCapital();
					wc.setCustomer(masterPlanForm.getCustomer());
					wc.setMasterKey(masterPlanForm.getMasterKey());
					wc.setTransactionId(masterPlanForm.getTransactionId());
					wc.setAmount(value.getWcAmnt());
					wc.setReason(value.getWcReason());

					workingCapitalDAO.createMasterPlan(wc);
				}
			}
			DraftsMasterPlan drafts = draftsMasterPlanDAO.getByMasterPlanId(masterPlanForm.getId());

			draftsMasterPlanDAO.deleteRow(drafts.getId());

			MasterPlan master = new MasterPlan();

			master.setCustomer(masterPlanForm.getCustomer());
			master.setCurrencySymbol(masterPlanForm.getCurrencySymbol());
			master.setCategory(masterPlanForm.getCategory());
			master.setProduct(masterPlanForm.getProduct());
			master.setDescription(masterPlanForm.getDescription());
			master.setLicence(masterPlanForm.getLicence());
			master.setWeight(masterPlanForm.getWeight());
			master.setQuantity(masterPlanForm.getQuantity());
			master.setBuyingCost(masterPlanForm.getBuyingCost());
			master.setTenure(masterPlanForm.getTenure());
			master.setWorkingCapital(masterPlanForm.getWorkingCapital());
			master.setWcTenure(masterPlanForm.getWcTenure());
			master.setSellingCost(masterPlanForm.getSellingCost());
			master.setSellingTenure(masterPlanForm.getSellingTenure());
			master.setMasterKey(masterPlanForm.getMasterKey());
			master.setTransactionId(masterPlanForm.getTransactionId());
			master.setCustomerEmail(user.getEmail());
			master.setaStatus("pending");
			master.setAccept("None");
			master.setApprovalSent("No");
			master.setFlag(1);

			Date date = DateService.loginDate;
			master.setMasterPlanDate(date);

			masterPlanDAO.createMasterPlan(master);

			Transaction trans = new Transaction();

			trans.setTransactionId(masterPlanForm.getTransactionId());
			trans.setTransactionStatus("Master Plan Saved");
			trans.setTransactionType("Master Plan");

			transcationDAOImpl.insertTransaction(trans);
		}

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("masterPlanTransaction", "model", model);

	}

	@RequestMapping(value = "/masterPlanDetails", method = RequestMethod.GET)
	public ModelAndView masterPlanDetails(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		Company comp = companyDAO.getByCompanyId(user.getCompanyId());
		String company = comp.getCompanyName();

		model.put("company", company);
		model.put("user", user);
		List<MasterPlan> masterList = masterPlanDAO.getMasterPlanFullList(user.getUserName()).getResultList();
		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);
			return new ModelAndView("masterPlanDetails", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/masterPlanFullDetails", method = RequestMethod.GET)
	public ModelAndView masterPlanFullDetails(@RequestParam("id") Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		MasterPlan masterList = masterPlanDAO.getByMasterPlanId(id);

		if (masterList != null && masterList.getMasterKey() != null) {
			List<Collateral> collateralList = collateralDAO.getCollateralBYMAsterKey(masterList.getMasterKey()).getResultList();
			if (collateralList != null && collateralList.size() > 0) {
				model.put("collateralList", collateralList);
			}

			List<BuyingCost> buyingList = buyingCostDAO.getBuyingCostBYMAsterKey(masterList.getMasterKey()).getResultList();

			if (buyingList != null && buyingList.size() > 0) {
				model.put("buyingList", buyingList);
			}

			List<SellerBuyingCost> sellerList = sellerBuyingCostDAO.getSellerBuyingCostList(masterList.getMasterKey()).getResultList();
			if (sellerList != null && sellerList.size() > 0) {
				model.put("sellerList", sellerList);
			}
			model.put("masterList", masterList);

			List<WorkingCapital> wclist = workingCapitalDAO.getWCBYMAsterKey(masterList.getMasterKey()).getResultList();
			if (wclist != null && wclist.size() > 0) {
				model.put("wclist", wclist);
			}
			model.put("wclist", wclist);

		}

		model.put("user", user);

		return new ModelAndView("masterPlanFullDetails", "model", model);

	}

	@RequestMapping(value = "/masterPlanAccept", method = RequestMethod.GET)
	public ModelAndView masterPlanAccept(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<MasterPlan> masterList = masterPlanDAO.getMasterPlanForAccept(user.getUserName()).getResultList();

		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);
			return new ModelAndView("masterPlanAccept", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/masterPlanAcceptOrReject", method = RequestMethod.GET)
	public ModelAndView masterPlanCreditAssign(@RequestParam("id") Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		MasterPlan master = masterPlanDAO.getByMasterPlanId(id);

		masterPlanForm.setId(master.getId());
		masterPlanForm.setMasterKey(master.getMasterKey());
		masterPlanForm.setCustomer(master.getCustomer());
		masterPlanForm.setTransactionId(master.getTransactionId());
		masterPlanForm.setBuyingCostSanc(master.getBuyingCostSanc());

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);
		return new ModelAndView("masterPlanAcceptOrReject", "model", model);

	}

	@RequestMapping(value = "/masterPlanAcceptOrRejectConfirm", method = RequestMethod.POST)
	public ModelAndView masterPlanCreditAssignConfirm(ModelMap model, @ModelAttribute MasterPlanForm masterPlanForm, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		masterPlanForm.setId(masterPlanForm.getId());
		masterPlanForm.setMasterKey(masterPlanForm.getMasterKey());
		masterPlanForm.setCustomer(masterPlanForm.getCustomer());
		masterPlanForm.setTransactionId(masterPlanForm.getTransactionId());
		masterPlanForm.setAccept(masterPlanForm.getAccept());
		masterPlanForm.setBuyingCostSanc(masterPlanForm.getBuyingCostSanc());

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("masterPlanAcceptOrRejectConfirm", "model", model);

	}

	@RequestMapping(value = "/masterPlanAcceptOrRejectPost", method = RequestMethod.POST)
	public ModelAndView masterPlanCreditAssignPost(ModelMap model, @ModelAttribute MasterPlanForm masterPlanForm, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		masterPlanForm.setTransactionId(masterPlanForm.getTransactionId());

		MasterPlan master = masterPlanDAO.getByMasterPlanId(masterPlanForm.getId());

		master.setAccept(masterPlanForm.getAccept());
		master.setBalance(masterPlanForm.getBuyingCostSanc());
		master.setDistributedAmt(0.0f);
		master.setUtilizedBusnsAmt(0.0f);
		master.setAmountPaid(0.0f);
		masterPlanDAO.updatePlan(master);

		Transaction trans = new Transaction();
		trans.setTransactionId(masterPlanForm.getTransactionId());
		trans.setTransactionStatus("Acceptance");
		trans.setTransactionType("Submitted Successfully");

		transcationDAOImpl.insertTransaction(trans);

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("masterPlanAcceptOrRejectTransaction", "model", model);

	}

	@RequestMapping(value = "/masterPlanAcceptOrRejectTransaction", method = RequestMethod.GET)
	public ModelAndView creditAssignmentTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("masterPlanForm", masterPlanForm);

		return new ModelAndView("masterPlanAcceptOrRejectTransaction", "model", model);

	}

	@RequestMapping(value = "/masterPlanFunds", method = RequestMethod.GET)
	public ModelAndView masterPlanFunds(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<MasterPlan> masterList = masterPlanDAO.getMasterPlanForFunds(user.getUserName()).getResultList();

		{
			model.put("masterList", masterList);
			return new ModelAndView("masterPlanFunds", "model", model);
		}

	}

	@RequestMapping(value = "/masterPlanFundsDistribute", method = RequestMethod.GET)
	public ModelAndView masterPlanFundsDistribute(@RequestParam("id") Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		MasterPlan master = masterPlanDAO.getByMasterPlanId(id);

		fundsDistributeForm.setId(master.getId());
		fundsDistributeForm.setMasterKey(master.getMasterKey());
		fundsDistributeForm.setCustomerHeadName(master.getCustomer());
		fundsDistributeForm.setCustHeadEmail(master.getCustomerEmail());
		fundsDistributeForm.setBuyingCostSanc(master.getBuyingCostSanc());
		fundsDistributeForm.setBalance(master.getBalance());
		fundsDistributeForm.setDistributedAmount(master.getDistributedAmt());

		if (master.getCustomer() != null) {
			List<CustomerBranch> customerBranchList = customerDAO.getByCustomerHeadAndStatus(master.getCustomer()).getResultList();
			if (customerBranchList != null && customerBranchList.size() > 0) {
				fundsDistributeForm.setCustomerBranchList(customerBranchList);

			}
		}

		if (master.getCustomer() != null) {
			List<CustomerSubsidiary> customerSubList = customerDAO.getByCustomerHeadAndStatusFoeSub(master.getCustomer()).getResultList();
			if (customerSubList != null && customerSubList.size() > 0) {
				fundsDistributeForm.setCustomerSubsList(customerSubList);
			}
		}

		if (master.getCustomer() != null) {
			List<FundsDistribute> fundsList = fundsDistributeDAO.getFundsList(master.getMasterKey()).getResultList();
			if (fundsList != null && fundsList.size() > 0) {
				model.put("fundsList", fundsList);
			}
		}

		model.put("user", user);
		model.put("fundsDistributeForm", fundsDistributeForm);
		return new ModelAndView("masterPlanFundsDistribute", "model", model);

	}

	@RequestMapping(value = "/masterPlanFundsDistributeConfirm", method = RequestMethod.POST)
	public ModelAndView masterPlanFundsDistributeConfirm(ModelMap model, @ModelAttribute FundsDistributeForm fundsDistributeForm, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		if (fundsDistributeForm.getAmount().compareTo(fundsDistributeForm.getBalance()) > 0) {
			Long Id = fundsDistributeForm.getId();
			attributes.addFlashAttribute("success", "Amount Should Not Exceed Balance");
			return new ModelAndView("redirect:masterPlanFundsDistribute?id=" + Id + "");
		}

		fundsDistributeForm.setId(fundsDistributeForm.getId());
		fundsDistributeForm.setMasterKey(fundsDistributeForm.getMasterKey());
		fundsDistributeForm.setCustomerHeadName(fundsDistributeForm.getCustomerHeadName());
		fundsDistributeForm.setCustomerName(fundsDistributeForm.getCustomerName());
		fundsDistributeForm.setBuyingCostSanc(fundsDistributeForm.getBuyingCostSanc());
		fundsDistributeForm.setBalance(fundsDistributeForm.getBalance());
		fundsDistributeForm.setFlag(fundsDistributeForm.getFlag());
		fundsDistributeForm.setDistributedAmount(fundsDistributeForm.getDistributedAmount());
		fundsDistributeForm.setCustHeadEmail(fundsDistributeForm.getCustHeadEmail());

		model.put("user", user);
		model.put("fundsDistributeForm", fundsDistributeForm);

		return new ModelAndView("masterPlanFundsDistributeConfirm", "model", model);

	}

	@RequestMapping(value = "/masterPlanFundsDistributePost", method = RequestMethod.POST)
	public ModelAndView masterPlanFundsDistributePost(ModelMap model, @ModelAttribute FundsDistributeForm fundsDistributeForm, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		FundsStatement statement = new FundsStatement();

		fundsDistributeForm.setTransactionId(KeyGenerator.generateTransactionKey());

		FundsDistribute funds = new FundsDistribute();
		FundsDistributeForm form = new FundsDistributeForm();

		String bankName = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0).getBankName();

		List<FundsDistribute> fund = fundsDistributeDAO.getCustomerList(fundsDistributeForm.getCustomerName(), fundsDistributeForm.getMasterKey()).getResultList();
		if (fund != null && fund.size() > 0) {

			form.setFlag(fund.get(0).getFlag());
			form.setCustomerName(fund.get(0).getCustomerName());
			form.setMasterKey(fund.get(0).getMasterKey());
		}

		if (form.getFlag() == null && form.getCustomerName() == null && form.getMasterKey() == null) {

			Float damt = 0.0f;
			Float IndAmt = damt + fundsDistributeForm.getAmount();
			Float calcu = fundsDistributeForm.getBalance();
			Float distr = fundsDistributeForm.getDistributedAmount();
			Float overallDist = distr + fundsDistributeForm.getAmount();
			Float amt = fundsDistributeForm.getAmount();
			Float finalAmt = calcu - amt;

			String name = fundsDistributeForm.getCustomerName();
			if (name != null) {
				EndUser enduser = endUserDAOImpl.findByUsername(name).getSingleResult();

				funds.setCurrentRole(enduser.getCurrentRole());
			}

			funds.setMasterKey(fundsDistributeForm.getMasterKey());
			funds.setCustomerHeadName(fundsDistributeForm.getCustomerHeadName());
			funds.setCustomerName(fundsDistributeForm.getCustomerName());
			funds.setBuyingCostSanc(fundsDistributeForm.getBuyingCostSanc());
			funds.setTransactionId(fundsDistributeForm.getTransactionId());
			funds.setCustHeadEmail(fundsDistributeForm.getCustHeadEmail());
			fundsDistributeForm.setMasterKey(fundsDistributeForm.getMasterKey());
			fundsDistributeForm.setBalance(finalAmt);
			fundsDistributeForm.setDistributedAmount(IndAmt);
			funds.setDistributedAmount(IndAmt);
			funds.setBusBalance(IndAmt);
			funds.setBalance(finalAmt);
			funds.setFlag(1);
			funds.setUtilizedAmount(0.0f);
			List<CustomerBranch> branch = customerDAO.getByCustomerNameForEmail(fundsDistributeForm.getCustomerName()).getResultList();
			if (branch != null && branch.size() > 0) {
				funds.setEmail(branch.get(0).getEmail());
				funds.setManagerEmail(branch.get(0).getManagerEmail());
				fundsDistributeForm.setEmail(branch.get(0).getEmail());
				fundsDistributeForm.setManagerEmail(branch.get(0).getManagerEmail());
				model.put("fundsDistributeForm", fundsDistributeForm);
			}
			List<CustomerSubsidiary> sub = customerDAO.getBySubCustomerNameForEmail(fundsDistributeForm.getCustomerName()).getResultList();
			if (sub != null && sub.size() > 0) {
				funds.setEmail(sub.get(0).getEmail());
				funds.setManagerEmail(sub.get(0).getManagerEmail());
				fundsDistributeForm.setEmail(sub.get(0).getEmail());
				fundsDistributeForm.setManagerEmail(sub.get(0).getManagerEmail());
				model.put("fundsDistributeForm", fundsDistributeForm);
			}
			List<CustomerHead> head = customerDAO.getByHeadCustomerNameForEmail(fundsDistributeForm.getCustomerHeadName()).getResultList();
			if (head != null && head.size() > 0) {
				funds.setCustHeadEmail(head.get(0).getEmail());
				funds.setCustHeadMngEmail(head.get(0).getManagerEmail());
				fundsDistributeForm.setCustHeadEmail(head.get(0).getEmail());
				fundsDistributeForm.setCustHeadMngEmail(head.get(0).getManagerEmail());
				model.put("fundsDistributeForm", fundsDistributeForm);
			}
			fundsDistributeDAO.insertFunds(funds);

			statement.setCustomerHeadName(fundsDistributeForm.getCustomerHeadName());
			statement.setCustomerName(fundsDistributeForm.getCustomerName());
			statement.setAmount(fundsDistributeForm.getAmount());
			statement.setBalance(finalAmt);
			statement.setTransactionID(fundsDistributeForm.getTransactionId());
			Date fundsDate = DateService.loginDate;
			statement.setFundsDate(fundsDate);
			fundsStatementDAO.insertStatement(statement);

			MasterPlan master = masterPlanDAO.getByMasterPlanId(fundsDistributeForm.getId());
			master.setId(fundsDistributeForm.getId());
			master.setBalance(finalAmt);
			master.setDistributedAmt(overallDist);

			masterPlanDAO.updatePlan(master);
		} else {

			FundsDistribute fundsMerge = fundsDistributeDAO.getFundsList(fundsDistributeForm.getMasterKey(), fundsDistributeForm.getCustomerName()).getSingleResult();

			Float damt = fundsMerge.getDistributedAmount();
			Float IndAmt = damt + fundsDistributeForm.getAmount();
			Float calcu = fundsDistributeForm.getBalance();
			Float distr = fundsDistributeForm.getDistributedAmount();
			Float amt = fundsDistributeForm.getAmount();
			Float overallDist = distr + fundsDistributeForm.getAmount();
			Float finalAmt = calcu - amt;

			fundsMerge.setBalance(finalAmt);
			fundsMerge.setDistributedAmount(IndAmt);
			fundsDistributeForm.setEmail(fundsMerge.getEmail());
			fundsDistributeForm.setManagerEmail(fundsMerge.getManagerEmail());
			fundsDistributeForm.setCustHeadEmail(fundsMerge.getCustHeadEmail());
			fundsDistributeForm.setCustHeadMngEmail(fundsMerge.getCustHeadMngEmail());
			fundsDistributeForm.setMasterKey(fundsDistributeForm.getMasterKey());
			fundsDistributeForm.setBalance(finalAmt);
			funds.setBusBalance(IndAmt);
			fundsDistributeForm.setDistributedAmount(IndAmt);
			fundsDistributeDAO.updateFunds(fundsMerge);

			statement.setCustomerHeadName(fundsDistributeForm.getCustomerHeadName());
			statement.setCustomerName(fundsDistributeForm.getCustomerName());
			statement.setAmount(fundsDistributeForm.getAmount());
			statement.setBalance(finalAmt);
			statement.setTransactionID(fundsDistributeForm.getTransactionId());
			Date fundsDate = DateService.loginDate;
			statement.setFundsDate(fundsDate);
			fundsStatementDAO.insertStatement(statement);

			MasterPlan master = masterPlanDAO.getByMasterPlanId(fundsDistributeForm.getId());
			master.setId(fundsDistributeForm.getId());
			master.setBalance(finalAmt);
			master.setDistributedAmt(overallDist);
			masterPlanDAO.updatePlan(master);
		}

		Transaction trans = new Transaction();

		trans.setTransactionId(fundsDistributeForm.getTransactionId());
		trans.setTransactionType("Funds Distribute");
		trans.setTransactionStatus("Distributed successfully");
		transcationDAOImpl.insertTransaction(trans);

		model.put("fundsDistributeForm", fundsDistributeForm);
		try {
			String managermail = fundsDistributeForm.getManagerEmail();
			String email = fundsDistributeForm.getEmail();
			String custheademail = fundsDistributeForm.getCustHeadEmail();
			String custheadmngEmail = fundsDistributeForm.getCustHeadMngEmail();
			String customerName = fundsDistributeForm.getCustomerName();

			String masterKey = fundsDistributeForm.getMasterKey();
			Float balance = fundsDistributeForm.getBalance();
			Float amt = fundsDistributeForm.getAmount();

			String tex = "Fund Transfer Details Notification";
			SimpleMailMessage emails = new SimpleMailMessage();
			emails.setTo(email);
			emails.setSubject(tex);
			emails.setText("Hello " + customerName + ",\n\n  Funds Transferred Successfully  " + "\n" + "\n"

					+ "\n\nMaterKey:" + masterKey + "\n\nDistributed Amount :" + amt + "\n\nBalance:" + balance +

					"\n\n\nRegards,\n"+bankName);
			System.out.println("" + email + customerName);
			mailSender.send(emails);
			SimpleMailMessage emails1 = new SimpleMailMessage();
			emails1.setTo(email);

			String tex1 = "Fund Transfer Details Notification";
			SimpleMailMessage emails2 = new SimpleMailMessage();
			emails2.setTo(custheademail);
			emails2.setSubject(tex1);
			emails2.setText("Hello " + customerName + ",\n\n Funds Transferred Successfully " + "\n" + "\n"

					+ "\n\nMaterKey:" + masterKey + "\n\nBalance Cost:" + balance + "\n\nDistributed Amount Cost:" + amt +

					"\n\n\nRegards,\n"+bankName);
			System.out.println("" + custheademail + customerName);
			mailSender.send(emails2);
			SimpleMailMessage email2 = new SimpleMailMessage();
			email2.setTo(custheademail);

			String tex2 = "Fund Transfer Details Notification";
			SimpleMailMessage emails3 = new SimpleMailMessage();
			emails3.setTo(managermail);
			emails3.setSubject(tex2);
			emails3.setText("Hello " + customerName + ",\n\n Funds Transferred Successfully " + "\n" + "\n"

					+ "\n\nMaterKey:" + masterKey + "\n\nBalance Cost:" + balance + "\n\nDistributed Amount Cost:" + amt +

					"\n\n\nRegards,\n"+bankName);
			System.out.println("" + managermail + customerName);
			mailSender.send(emails3);
			SimpleMailMessage email3 = new SimpleMailMessage();
			email3.setTo(managermail);

			String tex3 = "Fund Transfer Details Notification";
			SimpleMailMessage emails4 = new SimpleMailMessage();
			emails4.setTo(custheadmngEmail);
			emails4.setSubject(tex3);
			emails4.setText("Hello " + customerName + ",\n\n Funds Transferred Successfully " + "\n" + "\n"

					+ "\n\nMaterKey:" + masterKey + "\n\nBalance Cost:" + balance + "\n\nDistributed Amount Cost:" + amt +

					"\n\n\nRegards,\n"+bankName);
			System.out.println("" + custheadmngEmail + customerName);
			mailSender.send(emails4);
			SimpleMailMessage email4 = new SimpleMailMessage();
			email4.setTo(custheadmngEmail);

			model.put("user", user);
			model.put("fundsDistributeForm", fundsDistributeForm);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		model.put("user", user);
		return new ModelAndView("FundsDistributeTransaction", "model", model);

	}

	@RequestMapping(value = "/FundsDistributeTransaction", method = RequestMethod.GET)
	public ModelAndView FundsDistributeTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("fundsDistributeForm", fundsDistributeForm);

		return new ModelAndView("FundsDistributeTransaction", "model", model);

	}

	@RequestMapping(value = "/fullFundsDistributeStatement", method = RequestMethod.GET)
	public ModelAndView fullFundsStatement(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<FundsStatement> statement = fundsStatementDAO.getStatementList(user.getUserName()).getResultList();

		if (statement != null && statement.size() > 0) {
			model.put("statement", statement);
			return new ModelAndView("fullFundsDistributeStatement", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/salesPurchaseOrder", method = RequestMethod.GET)
	public ModelAndView businessPlan(@RequestParam("productType") String productType, ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		model.put("productType", productType);
		List<MasterPlan> plan = masterPlanDAO.getMasterPlanForFunds(user.getUserName()).getResultList();
		if(productType.equalsIgnoreCase("salesOrder")) model.put("salesOrder",true);
		else model.put("salesOrder",false);
		if (plan != null) {
			model.put("plan", plan);
			return new ModelAndView("salesPurchaseOrder", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/createSalesPurchaseOrder", method = RequestMethod.GET)
	public ModelAndView createBusinessPlan(@RequestParam("id") Long id, @RequestParam("productType") String productType,ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

		MasterPlan plan = masterPlanDAO.getByMasterPlanId(id);
		List<CustomerHead> custprefix = customerDAO.getCustomerDetailsList(user.getUserName()).getResultList();
		{
			masterPlanForm.setCustomerPrefix(custprefix.get(0).getCustomerPrefix());
		}

		masterPlanForm.setId(plan.getId());
		masterPlanForm.setBuyingCostSanc(plan.getBuyingCostSanc());
		masterPlanForm.setQuantity(plan.getQuantity());
		masterPlanForm.setUtilizedBusnsAmt(plan.getUtilizedBusnsAmt());
		masterPlanForm.setMasterKey(plan.getMasterKey());
		masterPlanForm.setBalance(plan.getBalance());
		masterPlanForm.setCustomer(plan.getCustomer());
		masterPlanForm.setCustomerEmail(plan.getCustomerEmail());
		masterPlanForm.setCustomerPrefix(masterPlanForm.getCustomerPrefix());
		masterPlanForm.setCategory(plan.getCategory());
		masterPlanForm.setProduct(plan.getProduct());
		masterPlanForm.setDescription(plan.getDescription());
		masterPlanForm.setMasterPlanDate(plan.getMasterPlanDate());
		masterPlanForm.setWeight(plan.getWeight());
		masterPlanForm.setLicence(plan.getLicence());


		if (productType.equalsIgnoreCase("PurchaseOrder")) {
			List<SupplierForm> suppliers = supplierDAOImpl.getSupplierForBusinessPlan(plan.getMasterKey());
			if (suppliers != null && suppliers.size() > 0) {
				model.put("suppliers", suppliers);
			}
		}
		if (productType.equalsIgnoreCase("SalesOrder")) {
			List<NewBuyerForm> buyers = newBuyerDAOImpl.getBuyerForBusinessPlan(plan.getMasterKey());
			if (buyers != null && buyers.size() > 0) {
				model.put("buyers", buyers);
			}

		}

		model.put("masterPlanForm", masterPlanForm);
		model.put("user", user);

		return new ModelAndView("createSalesPurchaseOrder", "model", model);
	}

	@RequestMapping(value = "/businessPlanForPo", method = RequestMethod.GET)
	public ModelAndView businessPlanForPo(@RequestParam("id") Long id, @RequestParam("supplierBuyerId") Long supplierBuyerId, ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		masterPlanForm.setBalance(masterPlanForm.getBalance());
		masterPlanForm.setMasterKey(masterPlanForm.getMasterKey());
		masterPlanForm.setCustomer(masterPlanForm.getCustomer());
		masterPlanForm.setUtilizedBusnsAmt(masterPlanForm.getUtilizedBusnsAmt());
		masterPlanForm.setCustomerEmail(masterPlanForm.getCustomerEmail());
		masterPlanForm.setCustomerPrefix(masterPlanForm.getCustomerPrefix());
		masterPlanForm.setProduct(masterPlanForm.getProduct());
		masterPlanForm.setDescription(masterPlanForm.getDescription());
		masterPlanForm.setMasterPlanDate(masterPlanForm.getMasterPlanDate());
		masterPlanForm.setLicence(masterPlanForm.getLicence());

		Supplier supplier = supplierDAOImpl.findId(supplierBuyerId);

		supplierForm.setId(supplier.getId());
		supplierForm.setSupplierName(supplier.getSupplierName());
		supplierForm.setEmail(supplier.getEmail());
		supplierForm.setBank(supplier.getBank());
		supplierForm.setEmail(supplier.getEmail());
		supplierForm.setCountry(supplier.getCountry());
		supplierForm.setState(supplier.getState());
		supplierForm.setCity(supplier.getCity());


//		BuyingCost  buyingCost = buyingCostDAO.getBuyingCostByCustomerName(supplier.getSupplierName(), masterPlanForm.getMasterKey());
		BuyingCost buyingCost=buyingCostDAO.getById(id).getSingleResult();

		purchaseOrderForm.setGoodsCategory(masterPlanForm.getCategory());
		purchaseOrderForm.setGoodsDetails(masterPlanForm.getDescription());
//		purchaseOrderForm.setPurchaseDate(DateService.loginDate);
		purchaseOrderForm.setPurchaseDate(dateService.getCurrentSavedLoginDate());
		purchaseOrderForm.setGoods(buyingCost.getMaterial());
		purchaseOrderForm.setCountry(supplier.getCountry());
		purchaseOrderForm.setrStatus(supplier.getState());
		purchaseOrderForm.setQuantity(buyingCost.getQuantity().toString());
		purchaseOrderForm.setWeight(masterPlanForm.getWeight());


		List<WareHouse> whList = wareHouseDAO.getWareHouseandStatusList(user.getUserName()).getResultList();
		if (whList != null) {
			purchaseOrderForm.setWareHouseList(whList);

			model.put("purchaseOrderForm", purchaseOrderForm);
			model.put("user", user);
		}

		model.put("masterPlanForm", masterPlanForm);
		model.put("supplierForm", supplierForm);
		model.put("purchaseOrderForm", purchaseOrderForm);
		model.put("user", user);

		return new ModelAndView("businessPlanForPo", "model", model);
	}

	@RequestMapping(value = "/businessPlanForPoConfirm", method = RequestMethod.POST)
	public ModelAndView businessPlanForPoConfirm(ModelMap model, @ModelAttribute PurchaseOrderForm purchaseOrderForm, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		if (purchaseOrderForm.getAmount().compareTo(masterPlanForm.getBalance()) > 0) {
			Long Id = purchaseOrderForm.getId();
			attributes.addFlashAttribute("success", "Amount Should Not Exceed Balance");
			return new ModelAndView("redirect:businessPlanForPo?id=" + Id + "");
		}

		List<RestrictedLicense> license = restrictedLicenseDAO.getRestrictedGoodsList(user.getUserName(), purchaseOrderForm.getGoodsCategory(), purchaseOrderForm.getPurchaseDate(), purchaseOrderForm.getWeight()).getResultList();
		if (license != null && license.size() > 0) {
			Long Id = purchaseOrderForm.getId();
			attributes.addFlashAttribute("success", "The goods are Restricted. License is not Uploaded or Expired");
			return new ModelAndView("redirect:businessPlanForPo?id=" + Id + "");
		}

		purchaseOrderForm.setSupplierEmail(purchaseOrderForm.getSupplierEmail());
		purchaseOrderForm.setCountry(purchaseOrderForm.getCountry());
		purchaseOrderForm.setState(purchaseOrderForm.getState());
		purchaseOrderForm.setSupplierBank(purchaseOrderForm.getSupplierBank());
		purchaseOrderForm.setSupplierBankEmail(purchaseOrderForm.getSupplierBankEmail());
		purchaseOrderForm.setSuppliercountry(purchaseOrderForm.getSuppliercountry());
		purchaseOrderForm.setSupplierState(purchaseOrderForm.getSupplierState());
		purchaseOrderForm.setSupplierCity(purchaseOrderForm.getSupplierCity());
		purchaseOrderForm.setMasterKey(purchaseOrderForm.getMasterKey());
		purchaseOrderForm.setWareHouseName(purchaseOrderForm.getWareHouseName());
		purchaseOrderForm.setCustomerName(purchaseOrderForm.getCustomerName());
		purchaseOrderForm.setCustomerHeadEmail(purchaseOrderForm.getCustomerHeadEmail());
		purchaseOrderForm.setSupplierName(purchaseOrderForm.getSupplierName());
		purchaseOrderForm.setPurchaseDate(purchaseOrderForm.getPurchaseDate());
		purchaseOrderForm.setGoodsCategory(purchaseOrderForm.getGoodsCategory());
		purchaseOrderForm.setGoods(purchaseOrderForm.getGoods());
		purchaseOrderForm.setGoodsDetails(purchaseOrderForm.getGoodsDetails());
		purchaseOrderForm.setQuantity(purchaseOrderForm.getQuantity());
		purchaseOrderForm.setWeight(purchaseOrderForm.getWeight());
		purchaseOrderForm.setLicence(purchaseOrderForm.getLicence());
		purchaseOrderForm.setAmount(purchaseOrderForm.getAmount());
		purchaseOrderForm.setUtilizedBusnsAmt(purchaseOrderForm.getUtilizedBusnsAmt());
		purchaseOrderForm.setBalance(purchaseOrderForm.getBalance());
		purchaseOrderForm.setTenure(purchaseOrderForm.getTenure());
		purchaseOrderForm.setCustomerPrefix(purchaseOrderForm.getCustomerPrefix());

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("businessPlanForPoConfirm", "model", model);

	}

	@RequestMapping(value = "/businessPlanForPoPost", method = RequestMethod.POST)
	public ModelAndView businessPlanForPoPost(ModelMap model, @ModelAttribute PurchaseOrderForm purchaseOrderForm, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchaseOrder = new PurchaseOrder();

		purchaseOrderForm.setPoKey(KeyGenerator.generateKey(purchaseOrderForm.getCustomerPrefix(),Constants.PURCHASEORDER));
		purchaseOrderForm.setTransactionId(KeyGenerator.generateTransactionKey());


		Float utilizedAmt = purchaseOrderForm.getUtilizedBusnsAmt();
		Float bal = purchaseOrderForm.getBalance();

		Float totalUtilized = utilizedAmt + purchaseOrderForm.getAmount();
		Float totalBal = bal - purchaseOrderForm.getAmount();

		purchaseOrder.setMasterKey(purchaseOrderForm.getMasterKey());
		purchaseOrder.setCustomerName(purchaseOrderForm.getCustomerName());
		purchaseOrder.setCountry(purchaseOrderForm.getCountry());
		purchaseOrder.setState(purchaseOrderForm.getState());
		purchaseOrder.setCustomerHeadName(purchaseOrderForm.getCustomerName());
		purchaseOrder.setCustomerHeadEmail(purchaseOrderForm.getCustomerHeadEmail());
		purchaseOrder.setCustomerPrefix(purchaseOrderForm.getCustomerPrefix());
		purchaseOrder.setSupplierBank(purchaseOrderForm.getSupplierBank());
		purchaseOrder.setSupplierName(purchaseOrderForm.getSupplierName());
		purchaseOrder.setSupplierEmail(purchaseOrderForm.getSupplierBankEmail());
		purchaseOrder.setPurchaseDate(purchaseOrderForm.getPurchaseDate());
		purchaseOrder.setGoodsCategory(purchaseOrderForm.getGoodsCategory());
		purchaseOrder.setGoods(purchaseOrderForm.getGoods());
		purchaseOrder.setGoodsDetails(purchaseOrderForm.getGoodsDetails());
		purchaseOrder.setWareHouseName(purchaseOrderForm.getWareHouseName());
		WareHouse wareMng = wareHouseDAO.getWareHouseNameList(purchaseOrderForm.getWareHouseName()).getSingleResult();
		purchaseOrder.setWhMngName(wareMng.getPersonInCharge());
		purchaseOrder.setQuantity(purchaseOrderForm.getQuantity());
		purchaseOrder.setWeight(purchaseOrderForm.getWeight());
		purchaseOrder.setLicence(purchaseOrderForm.getLicence());
		purchaseOrder.setAmount(purchaseOrderForm.getAmount());
		purchaseOrder.setPayBalance(purchaseOrderForm.getAmount());
		purchaseOrder.setPayAdvance(0.0f);
		purchaseOrder.setTransactionId(purchaseOrderForm.getTransactionId());
		purchaseOrder.setPoKey(purchaseOrderForm.getPoKey());
		purchaseOrder.setTenure(purchaseOrderForm.getTenure());

		purchaseOrder.setFlag(0);
		purchaseOrder.setvStatus("Pending");

		purchaseOrderDAO.insertPo(purchaseOrder);

		MasterPlan plan = masterPlanDAO.getMasterPlanByMasterKey(purchaseOrderForm.getMasterKey()).getSingleResult();

		plan.setBalance(totalBal);
		plan.setUtilizedBusnsAmt(totalUtilized);

		masterPlanDAO.updatePlan(plan);

		Transaction trans = new Transaction();

		trans.setTransactionId(purchaseOrderForm.getTransactionId());
		trans.setTransactionType("Purchase Order");
		trans.setTransactionStatus("Submitted successfully");
		transcationDAOImpl.insertTransaction(trans);

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("businessPlanForPoTransaction", "model", model);

	}

	@RequestMapping(value = "/businessPlanForPoTransaction", method = RequestMethod.GET)
	public ModelAndView businessPlanForPoTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("businessPlanForPoTransaction", "model", model);

	}

	@RequestMapping(value = "/businessPlanForInvoice", method = RequestMethod.GET)
	public ModelAndView businessPlanForInvoice(@RequestParam("id") Long id, ModelMap model) {
		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		masterPlanForm.setMasterKey(masterPlanForm.getMasterKey());
		masterPlanForm.setCustomer(masterPlanForm.getCustomer());
		masterPlanForm.setCustomerEmail(masterPlanForm.getCustomerEmail());
		masterPlanForm.setBuyingCostSanc(masterPlanForm.getBuyingCostSanc());
		masterPlanForm.setQuantity(masterPlanForm.getQuantity());
		masterPlanForm.setServiceTax(masterPlanForm.getServiceTax());
		masterPlanForm.setDutytax(masterPlanForm.getDutytax());
		masterPlanForm.setVattax(masterPlanForm.getVattax());
		NewBuyer buyer = newBuyerDAOImpl.findId(id);
		newBuyerForm.setId(buyer.getId());
		newBuyerForm.setBuyerName(buyer.getBuyerName());
		newBuyerForm.setEmail(buyer.getEmail());
		newBuyerForm.setBank(buyer.getBank());
		newBuyerForm.setBankEmail(buyer.getBankEmail());
		newBuyerForm.setCountry(buyer.getCountry());
		newBuyerForm.setState(buyer.getState());
		newBuyerForm.setCity(buyer.getCity());
		SellerBuyingCost  sellerbuyingcost = sellerBuyingCostDAO.getSellerBuyingCostBuyerName(buyer.getBuyerName(), masterPlanForm.getMasterKey() );
		purchaseOrderForm.setGoodsCategory(masterPlanForm.getCategory());
		purchaseOrderForm.setGoods(sellerbuyingcost.getFinalPro());
		purchaseOrderForm.setQuantity(sellerbuyingcost.getQuantity().toString());

		List<WareHouse> whList = wareHouseDAO.getWareHouseandStatusList(user.getUserName()).getResultList();
		if (whList != null) {
			invoiceForm.setWareHouseList(whList);

			model.put("purchaseOrderForm", purchaseOrderForm);
			model.put("user", user);
		}

		model.put("masterPlanForm", masterPlanForm);
		model.put("newBuyerForm", newBuyerForm);
		model.put("invoiceForm", invoiceForm);
		model.put("user", user);

		return new ModelAndView("businessPlanForInvoice", "model", model);
	}

	@RequestMapping(value = "/businessPlanForInvoiceConfirm", method = RequestMethod.POST)
	public ModelAndView businessPlanForInvoiceConfirm(ModelMap model, @ModelAttribute InvoiceForm invoiceForm, BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		invoiceForm.setBuyerEmail(invoiceForm.getBuyerEmail());
		invoiceForm.setCustomerHeadEmail(invoiceForm.getCustomerHeadEmail());
		invoiceForm.setBuyerBank(invoiceForm.getBuyerBank());
		invoiceForm.setBuyerBankEmail(invoiceForm.getBuyerBankEmail());
		invoiceForm.setBuyerCountry(invoiceForm.getBuyerCountry());
		invoiceForm.setBuyerState(invoiceForm.getBuyerState());
		invoiceForm.setBuyerCity(invoiceForm.getBuyerCity());
		invoiceForm.setMasterKey(invoiceForm.getMasterKey());
		invoiceForm.setCustomerName(invoiceForm.getCustomerName());
		invoiceForm.setBuyerName(invoiceForm.getBuyerName());
		invoiceForm.setPoDate(invoiceForm.getPoDate());
		invoiceForm.setWareHouseName(invoiceForm.getWareHouseName());
		invoiceForm.setGoodsCategory(invoiceForm.getGoodsCategory());
		invoiceForm.setGoods(invoiceForm.getGoods());
		invoiceForm.setGoodsDetails(invoiceForm.getGoodsDetails());
		invoiceForm.setQuantity(invoiceForm.getQuantity());
		invoiceForm.setWeight(invoiceForm.getWeight());
		invoiceForm.setLicence(invoiceForm.getLicence());
		invoiceForm.setAmount(invoiceForm.getAmount());
		invoiceForm.setPoInfo(invoiceForm.getPoInfo());
		invoiceForm.setPoKey(invoiceForm.getPoKey());
		invoiceForm.setTenure(invoiceForm.getTenure());
		invoiceForm.setServiceTax(invoiceForm.getServiceTax());
		invoiceForm.setDutytax(invoiceForm.getDutytax());
		invoiceForm.setVattax(invoiceForm.getVattax());
		invoiceForm.setAnswer(invoiceForm.getAnswer());
		invoiceForm.setInsuranceAmount(invoiceForm.getInsuranceAmount());
		invoiceForm.setInsuranceDetails(invoiceForm.getInsuranceDetails());
		invoiceForm.setInsuranceType(invoiceForm.getInsuranceType());
		invoiceForm.setStartDate(invoiceForm.getStartDate());
		invoiceForm.setEndDate(invoiceForm.getEndDate());
		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("businessPlanForInvoiceConfirm", "model", model);

	}

	@RequestMapping(value = "/businessPlanForInvoicePost", method = RequestMethod.POST)
	public ModelAndView businessPlanForInvoicePost(ModelMap model, @ModelAttribute InvoiceForm invoiceForm, BindingResult result, RedirectAttributes attributes) {

		Invoice invoice = new Invoice();

		invoiceForm.setTransactionId(KeyGenerator.generateTransactionKey());

		invoice.setMasterKey(invoiceForm.getMasterKey());
		invoice.setCustomerName(invoiceForm.getCustomerName());
		invoice.setCustomerHeadName(invoiceForm.getCustomerName());
		invoice.setBuyerBank(invoiceForm.getBuyerBank());
		invoice.setCustomerHeadEmail(invoiceForm.getCustomerHeadEmail());
		invoice.setBuyerName(invoiceForm.getBuyerName());
		invoice.setBuyerEmail(invoiceForm.getBuyerEmail());
		invoice.setPoDate(invoiceForm.getPoDate());
		invoice.setGoodsCategory(invoiceForm.getGoodsCategory());
		invoice.setGoods(invoiceForm.getGoods());
		invoice.setGoodsDetails(invoiceForm.getGoodsDetails());
		invoice.setQuantity(invoiceForm.getQuantity());
		invoice.setWeight(invoiceForm.getWeight());
		invoice.setLicence(invoiceForm.getLicence());
		invoice.setAmount(invoiceForm.getAmount());
		invoice.setTransactionId(invoiceForm.getTransactionId());
		invoice.setPoKey(invoiceForm.getPoKey());
		invoice.setWareHouseName(invoiceForm.getWareHouseName());
		WareHouse wareMng = wareHouseDAO.getWareHouseNameList(invoiceForm.getWareHouseName()).getSingleResult();
		invoice.setWhMngName(wareMng.getPersonInCharge());
		invoice.setStatus("Pending");
		invoice.setGoodsStatus("Send");
		invoice.setPoInfo(invoiceForm.getPoInfo());
		invoice.setPoDate(invoiceForm.getPoDate());
		invoice.setTenure(invoiceForm.getTenure());
		invoice.setServiceTax(invoiceForm.getServiceTax());
		invoice.setDutytax(invoiceForm.getDutytax());
		invoice.setVattax(invoiceForm.getVattax());
		invoice.setAnswer(invoiceForm.getAnswer());
		invoice.setInsuranceAmount(invoiceForm.getInsuranceAmount());
		invoice.setInsuranceDetails(invoiceForm.getInsuranceDetails());
		invoice.setInsuranceType(invoiceForm.getInsuranceType());
		invoice.setStartDate(invoiceForm.getStartDate());
		invoice.setEndDate(invoiceForm.getEndDate());
		invoiceDAO.insertInvoice(invoice);

		Transaction trans = new Transaction();

		trans.setTransactionId(invoiceForm.getTransactionId());
		trans.setTransactionType("Invoice");
		trans.setTransactionStatus("Submitted successfully");
		transcationDAOImpl.insertTransaction(trans);

		return new ModelAndView("businessPlanForInvoiceTransaction", "model", model);
	}

	@RequestMapping(value = "/businessPlanForInvoiceTransaction", method = RequestMethod.GET)
	public ModelAndView businessPlanForInvoiceTransaction(ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("businessPlanForInvoiceTransaction", "model", model);
	}

	@RequestMapping(value = "/businessPlanForSalesOrder", method = RequestMethod.GET)
	public ModelAndView businessPlanForSalesOrder(@RequestParam("id") Long id, ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		masterPlanForm.setMasterKey(masterPlanForm.getMasterKey());
		masterPlanForm.setMasterPlanDate(masterPlanForm.getMasterPlanDate());
		masterPlanForm.setCustomer(masterPlanForm.getCustomer());
		masterPlanForm.setCustomerEmail(masterPlanForm.getCustomerEmail());
		masterPlanForm.setBuyingCostSanc(masterPlanForm.getBuyingCostSanc());
		masterPlanForm.setQuantity(masterPlanForm.getQuantity());
		masterPlanForm.setServiceTax(masterPlanForm.getServiceTax());
		masterPlanForm.setDutytax(masterPlanForm.getDutytax());
		masterPlanForm.setVattax(masterPlanForm.getVattax());
		masterPlanForm.setProduct(masterPlanForm.getProduct());
		masterPlanForm.setDescription(masterPlanForm.getDescription());
		masterPlanForm.setWeight(masterPlanForm.getWeight());
		/* masterPlanForm.setMasterPlanDate(masterPlanForm.getMasterPlanDate()); */

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		model.put("masterPlanDate",formatter.format(masterPlanForm.getMasterPlanDate()));

		NewBuyer buyer = newBuyerDAOImpl.findId(id);

		newBuyerForm.setId(buyer.getId());
		newBuyerForm.setBuyerName(buyer.getBuyerName());
		newBuyerForm.setEmail(buyer.getEmail());
		newBuyerForm.setBank(buyer.getBank());
		newBuyerForm.setBankEmail(buyer.getBankEmail());
		newBuyerForm.setCountry(buyer.getCountry());
		newBuyerForm.setState(buyer.getState());
		newBuyerForm.setCity(buyer.getCity());

		SellerBuyingCost  sellerbuyingcost = sellerBuyingCostDAO.getSellerBuyingCostBuyerName(buyer.getBuyerName(), masterPlanForm.getMasterKey() );

		purchaseOrderForm.setGoodsCategory(masterPlanForm.getCategory());
		purchaseOrderForm.setGoods(sellerbuyingcost.getFinalPro());
		purchaseOrderForm.setQuantity(sellerbuyingcost.getQuantity().toString());

		/*
		 * List<WareHouse> whList =
		 * wareHouseDAO.getWareHouseandStatusList(user.getUserName()).getResultList();
		 * if (whList != null) { salesOrderForm.setWareHouseList(whList); }
		 */

		model.put("purchaseOrderForm", purchaseOrderForm);
		model.put("user", user);


		model.put("masterPlanForm", masterPlanForm);
		model.put("newBuyerForm", newBuyerForm);
		model.put("salesOrderForm", salesOrderForm);
		model.put("user", user);

		return new ModelAndView("businessPlanForSalesOrder", "model", model);
	}

	@RequestMapping(value = "/businessPlanForSalesOrderConfirm", method = RequestMethod.POST)
	public ModelAndView businessPlanForSalesOrderConfirm(ModelMap model, @ModelAttribute SalesOrderForm salesOrderForm, BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		salesOrderForm.setBuyerEmail(salesOrderForm.getBuyerEmail());
		salesOrderForm.setCustomerHeadEmail(salesOrderForm.getCustomerHeadEmail());
		salesOrderForm.setBuyerBank(salesOrderForm.getBuyerBank());
		salesOrderForm.setBuyerBankEmail(salesOrderForm.getBuyerBankEmail());
		salesOrderForm.setBuyerCountry(salesOrderForm.getBuyerCountry());
		salesOrderForm.setBuyerState(salesOrderForm.getBuyerState());
		salesOrderForm.setBuyerCity(salesOrderForm.getBuyerCity());
		salesOrderForm.setMasterKey(salesOrderForm.getMasterKey());
		salesOrderForm.setCustomerName(salesOrderForm.getCustomerName());
		salesOrderForm.setBuyerName(salesOrderForm.getBuyerName());
		salesOrderForm.setMasterPlanDate (salesOrderForm.getMasterPlanDate ());
		//salesOrderForm.setWareHouseName(salesOrderForm.getWareHouseName());
		salesOrderForm.setGoodsCategory(salesOrderForm.getGoodsCategory());
		salesOrderForm.setGoods(salesOrderForm.getGoods());
		salesOrderForm.setGoodsDetails(salesOrderForm.getGoodsDetails());
		salesOrderForm.setQuantity(salesOrderForm.getQuantity());
		salesOrderForm.setWeight(salesOrderForm.getWeight());
		salesOrderForm.setLicence(salesOrderForm.getLicence());
		salesOrderForm.setAmount(salesOrderForm.getAmount());
		salesOrderForm.setMasterPlanKey(salesOrderForm.getMasterPlanKey());
		salesOrderForm.setTenure(salesOrderForm.getTenure());
		salesOrderForm.setServiceTax(salesOrderForm.getServiceTax());
		salesOrderForm.setDutytax(salesOrderForm.getDutytax());
		salesOrderForm.setVattax(salesOrderForm.getVattax());
		salesOrderForm.setAnswer(salesOrderForm.getAnswer());
		salesOrderForm.setInsuranceAmount(salesOrderForm.getInsuranceAmount());
		salesOrderForm.setInsuranceDetails(salesOrderForm.getInsuranceDetails());
		salesOrderForm.setInsuranceType(salesOrderForm.getInsuranceType());
		salesOrderForm.setStartDate(salesOrderForm.getStartDate());
		salesOrderForm.setEndDate(salesOrderForm.getEndDate());
		model.put("user", user);
		model.put("salesOrderForm", salesOrderForm);

		return new ModelAndView("businessPlanForSalesOrderConfirm", "model", model);

	}

	@RequestMapping(value = "/businessPlanForSalesOrderPost", method = RequestMethod.POST)
	public ModelAndView businessPlanForSalesOrderPost(ModelMap model, @ModelAttribute SalesOrderForm salesOrderForm, BindingResult result, RedirectAttributes attributes) {


		salesOrderForm.setTransactionId(KeyGenerator.generateTransactionKey());

		SalesOrder  salesOrder  = new SalesOrder ();

		salesOrder.setMasterKey(salesOrderForm.getMasterKey());
		salesOrder.setCustomerName(salesOrderForm.getCustomerName());
		salesOrder.setCustomerHeadName(salesOrderForm.getCustomerName());
		salesOrder.setBuyerBank(salesOrderForm.getBuyerBank());
		salesOrder.setCustomerHeadEmail(salesOrderForm.getCustomerHeadEmail());
		salesOrder.setBuyerName(salesOrderForm.getBuyerName());
		salesOrder.setBuyerEmail(salesOrderForm.getBuyerEmail());
		salesOrder.setMasterPlanDate (salesOrderForm.getMasterPlanDate ());
		salesOrder.setGoodsCategory(salesOrderForm.getGoodsCategory());
		salesOrder.setGoods(salesOrderForm.getGoods());
		salesOrder.setGoodsDetails(salesOrderForm.getGoodsDetails());
		salesOrder.setQuantity(salesOrderForm.getQuantity());
		salesOrder.setWeight(salesOrderForm.getWeight());
		salesOrder.setLicence(salesOrderForm.getLicence());
		salesOrder.setAmount(salesOrderForm.getAmount());
		salesOrder.setTransactionId(salesOrderForm.getTransactionId());
		salesOrder.setMasterPlanKey(salesOrderForm.getMasterPlanKey());
		//salesOrder.setWareHouseName(salesOrderForm.getWareHouseName());
		//WareHouse wareMng = wareHouseDAO.getWareHouseNameList(salesOrderForm.getWareHouseName()).getSingleResult();
		//salesOrder.setWhMngName(wareMng.getPersonInCharge());
		salesOrder.setStatus("Pending");
		salesOrder.setGoodsStatus("Send");
		salesOrder.setTenure(salesOrderForm.getTenure());
		salesOrder.setServiceTax(salesOrderForm.getServiceTax());
		salesOrder.setDutytax(salesOrderForm.getDutytax());
		salesOrder.setVattax(salesOrderForm.getVattax());
		salesOrder.setAnswer(salesOrderForm.getAnswer());
		salesOrder.setInsuranceAmount(salesOrderForm.getInsuranceAmount());
		salesOrder.setInsuranceDetails(salesOrderForm.getInsuranceDetails());
		salesOrder.setInsuranceType(salesOrderForm.getInsuranceType());
		salesOrder.setStartDate(salesOrderForm.getStartDate());
		salesOrder.setEndDate(salesOrderForm.getEndDate());
		salesOrderDAO.insertSalesOrder (salesOrder);

		Transaction trans = new Transaction();

		trans.setTransactionId(salesOrder.getTransactionId());
		trans.setTransactionType("salesOrderForm");
		trans.setTransactionStatus("Submitted successfully");
		transcationDAOImpl.insertTransaction(trans);

		return new ModelAndView("businessPlanForSalesOrderTransaction", "model", model);

	}

	@RequestMapping(value = "/businessPlanForSalesOrderTransaction", method = RequestMethod.GET)
	public ModelAndView businessPlanForSalesOrderTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("salesOrderForm", salesOrderForm);

		return new ModelAndView("businessPlanForSalesOrderTransaction", "model", model);

	}

	@RequestMapping(value = "/listofPoOnMasterKey", method = RequestMethod.GET)
	public ModelAndView listofPoOnMasterKey(@RequestParam("id") Long id, ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		MasterPlan master = masterPlanDAO.getByMasterPlanId(id);

		List<PurchaseOrder> purchase = purchaseOrderDAO.getPoListByMasterKey(master.getMasterKey()).getResultList();

		if (purchase != null && purchase.size() > 0) {
			model.put("purchase", purchase);
			return new ModelAndView("listofPoOnMasterKey", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/listofInvoiceOnMasterKey", method = RequestMethod.GET)
	public ModelAndView listofInvoiceOnMasterKey(@RequestParam("id") Long id, ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		MasterPlan master = masterPlanDAO.getByMasterPlanId(id);

		List<Invoice> invoice = invoiceDAO.getInvoiceListByMasterKey(master.getMasterKey(), user.getUserName()).getResultList();

		if (invoice != null && invoice.size() > 0) {
			model.put("invoice", invoice);
			return new ModelAndView("listofInvoiceOnMasterKey", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}
	}

	@RequestMapping(value = "/customerHeadInvoiceList", method = RequestMethod.GET)
	public ModelAndView BankFullInvoiceList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<Invoice> invoiceList = invoiceDAO.getInvoiceListBycustomerName(user.getUserName()).getResultList();

		model.put("user", user);
		if (invoiceList != null && invoiceList.size() > 0) {
			model.put("invoiceList", invoiceList);

			return new ModelAndView("customerHeadInvoiceList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/customerHeadlPoList", method = RequestMethod.GET)
	public ModelAndView fullPoList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PurchaseOrder> poList = purchaseOrderDAO.getPoListBycustomerName(user.getUserName()).getResultList();

		model.put("user", user);
		if (poList != null && poList.size() > 0) {
			model.put("poList", poList);

			return new ModelAndView("customerHeadlPoList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/InventoryPoList", method = RequestMethod.GET)
	public ModelAndView InventoryPoList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PurchaseOrder> poList = purchaseOrderDAO.getPoListBycustomerNameAndStatus(user.getUserName()).getResultList();


		List<PurchaseOrderForm> poFormList=new ArrayList<>();
		for(PurchaseOrder po:poList){
			PurchaseOrderForm poForm=new PurchaseOrderForm();

			poForm.setId(po.getId());
			poForm.setCustomerName(po.getCustomerName());
			poForm.setSupplierName(po.getSupplierName());
			poForm.setMasterKey(po.getMasterKey());
			poForm.setPoKey(po.getPoKey());
			poForm.setStatus(po.getStatus());
			poForm.setPurchaseDate(po.getPurchaseDate());


			poForm.setAmount(po.getAmount());
			poForm.setGoods(po.getGoods());
			poForm.setQuantity(po.getQuantity());

			try {
				Inventory inv = invenrotyDAO.getInventoryByKeyList(po.getPoKey(), po.getCustomerName()).getSingleResult();
				poForm.setAvailableQuantity(inv.getAvail().toString());
			} catch (NoResultException nre) {
				poForm.setAvailableQuantity("0");
			}
			poFormList.add(poForm);

		}
		model.put("user", user);
		if (poList != null && poList.size() > 0) {
			model.put("poList", poFormList);

			return new ModelAndView("InventoryPoList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/inventoryShow", method = RequestMethod.GET)
	public ModelAndView inventoryShow(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchaseorder = purchaseOrderDAO.getByPurchaseId(id);

		inventoryForm.setPoKey(purchaseorder.getPoKey());
		inventoryForm.setCustomerName(purchaseorder.getCustomerName());
		inventoryForm.setGoods(purchaseorder.getGoods());
		List<Inventory> inventory1 = invenrotyDAO.getInventoryByKeyList(inventoryForm.getPoKey(), inventoryForm.getCustomerName()).getResultList();

		if (inventory1 != null && inventory1.size() > 0) {

			attributes.addFlashAttribute("success", "Please Click On Update");
			return new ModelAndView("redirect:InventoryPoList");

		} else {

			inventoryForm.setId(purchaseorder.getId());

			List<WareHouse> whList = wareHouseDAO.getWareHouseandStatusList(user.getUserName()).getResultList();
			if (whList != null) {
				inventoryForm.setWareHouseList(whList);

				model.put("inventoryForm", inventoryForm);
				model.put("user", user);
			}

			inventoryForm.setPoKey(purchaseorder.getPoKey());
			inventoryForm.setAmount(purchaseorder.getAmount());
			inventoryForm.setQuantity(purchaseorder.getQuantity());

			inventoryForm.setSupplierName(purchaseorder.getSupplierName());
			inventoryForm.setCustomerHeadName(purchaseorder.getCustomerName());

			model.put("user", user);
			model.put("inventoryForm", inventoryForm);

			return new ModelAndView("inventoryShow", "model", model);
		}
	}

	@RequestMapping(value = "/inventoryShowConfirm", method = RequestMethod.POST)
	public ModelAndView approveBankEmpConfrim(@ModelAttribute InventoryForm inventoryForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		inventoryForm.setMasterKey(inventoryForm.getMasterKey());
		if (inventoryForm.getTotal() < 0) {
			model.put("user", user);
			attributes.addFlashAttribute("success", "Total Should Not be Less than Zero");
			return new ModelAndView("redirect:InventoryPoList");
		} else {

			model.put("user", user);
			model.put("inventoryForm", inventoryForm);

			return new ModelAndView("inventoryShowConfirm", "model", model);
		}
	}

	@RequestMapping(value = "/updateinventoryShowConfirm", method = RequestMethod.POST)
	public ModelAndView updateinventoryShowConfirm(@ModelAttribute InventoryForm inventoryForm, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder po = purchaseOrderDAO.getPoListByPoKey(inventoryForm.getPoKey()).getSingleResult();

		po.setGoodsStatus("Received");

		Date receive = DateService.loginDate;
		po.setReceiveDate(receive);
		purchaseOrderDAO.updatePo(po);

		Transaction trans = new Transaction();
		trans.setTransactionId(KeyGenerator.generateTransactionKey());
		trans.setTransactionStatus("Collateral");
		trans.setTransactionType("Submitted Successfully");

		Inventory inventroy = new Inventory();

		inventroy.setId(inventoryForm.getId());
		inventroy.setCustomerName(inventoryForm.getCustomerName());
		inventroy.setCustomerHeadName(inventoryForm.getCustomerHeadName());
		inventroy.setPoKey(inventoryForm.getPoKey());
		inventroy.setAmount(inventoryForm.getAmount());
		inventroy.setDamaged(inventoryForm.getDamaged());
		inventroy.setReturned(inventoryForm.getReturned());
		inventroy.setTotal(inventoryForm.getTotal());
		inventroy.setAvail(inventoryForm.getTotal());
		inventroy.setUsedQuantity((float) 0); //While receving goods used will be zero
		inventroy.setQuantity(inventoryForm.getQuantity());
		inventroy.setMasterKey(inventoryForm.getMasterKey());
		inventroy.setSupplierName(inventoryForm.getSupplierName());
		inventroy.setInventoryType("Purchase Order");
		inventroy.setTransactionId(inventoryForm.getTransactionId());
		inventroy.setWareHouseName(inventoryForm.getWareHouseName());
		inventroy.setTransactionId(trans.getTransactionId());
		inventroy.setFlag(0);
		inventroy.setGoods(inventoryForm.getGoods());
		invenrotyDAO.insertInventory(inventroy);

		transcationDAOImpl.insertTransaction(trans);

		model.put("user", user);
		model.put("transactionId",trans.getTransactionId());

		return new ModelAndView("receiveTransaction", "model", model);
	}

	@RequestMapping(value = "/inventoryUpdateShow", method = RequestMethod.GET)
	public ModelAndView inventoryUpdateShow(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchaseorder = purchaseOrderDAO.getByPurchaseId(id);
		inventoryForm.setPoKey(purchaseorder.getPoKey());
		inventoryForm.setCustomerName(purchaseorder.getCustomerName());

		List<Inventory> inventory1 = invenrotyDAO.getInventoryByKeyList(inventoryForm.getPoKey(), inventoryForm.getCustomerName()).getResultList();

		if (inventory1 != null && inventory1.size() > 0) {

			Inventory inventory = invenrotyDAO.getInventoryByKeyList(purchaseorder.getPoKey(), purchaseorder.getCustomerName()).getSingleResult();

			inventoryForm.setId(inventory.getId());

			inventoryForm.setPoKey(inventory.getPoKey());
			inventoryForm.setAmount(inventory.getAmount());
			inventoryForm.setQuantity(inventory.getQuantity());
			inventoryForm.setMasterKey(inventory.getMasterKey());
			inventoryForm.setCustomerName(inventory.getCustomerName());
			inventoryForm.setSupplierName(inventory.getSupplierName());
			inventoryForm.setCustomerHeadName(inventory.getCustomerName());
			inventoryForm.setAvail(inventory.getAvail());

			model.put("user", user);
			model.put("inventoryForm", inventoryForm);

			return new ModelAndView("inventoryUpdateShow", "model", model);
		} else {
			model.put("user", user);
			attributes.addFlashAttribute("success", "This order is not received in INventor ");
			return new ModelAndView("redirect:InventoryPoList");

		}
	}

	@RequestMapping(value = "/inventoryUpdateShowConfirm", method = RequestMethod.POST)
	public ModelAndView inventoryUpdateShowConfirm(@ModelAttribute InventoryForm inventoryForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		inventoryForm.setMasterKey(inventoryForm.getMasterKey());
		if (inventoryForm.getTotal() < 0) {
			model.put("user", user);
			attributes.addFlashAttribute("success", "Total Should Not be Less than Zero");
			return new ModelAndView("redirect:InventoryPoList");
		} else {

			model.put("user", user);
			model.put("inventoryForm", inventoryForm);

			return new ModelAndView("inventoryUpdateShowConfirm", "model", model);
		}
	}

	@RequestMapping(value = "/inventoryupdateShowSave", method = RequestMethod.POST)
	public ModelAndView inventoryupdateShowSave(@ModelAttribute InventoryForm inventoryForm, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Inventory inventroy = invenrotyDAO.getByInventoryId(inventoryForm.getId());

//		inventroy.setDamaged(inventoryForm.getDamaged());
//		inventroy.setReturned(inventoryForm.getReturned());
		inventroy.setAvail(inventoryForm.getTotal());
		inventroy.setTotal(inventoryForm.getTotal());
		inventroy.setUsedQuantity(inventroy.getUsedQuantity()==null?0:inventroy.getUsedQuantity() + inventoryForm.getUsedQuantity());

		invenrotyDAO.updateInventory(inventroy);

		InventoryForProduction inventoryForProduction=new InventoryForProduction();
		inventoryForProduction.setInventoryId(inventoryForm.getId());
		inventoryForProduction.setGoods(inventroy.getGoods());
		inventoryForProduction.setUsedForProduction(inventoryForm.getUsedQuantity());
		inventoryForProduction.setDate(dateService.getCurrentSavedLoginDate());
		inventoryForProduction.setTransactionId(KeyGenerator.generateTransactionKey());
		inventoryForProductionDAO.insertInventoryForProduction(inventoryForProduction);


		model.put("user", user);
		model.put("transactionId",inventoryForProduction.getTransactionId());

		return new ModelAndView("updateInventoryTransaction", "model", model);

//		return new ModelAndView("redirect:InventoryPoList");
	}

	@RequestMapping(value = "/inventoryListCustApproved", method = RequestMethod.GET)
	public ModelAndView InventoryList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<Inventory> inventoryList = invenrotyDAO.getInventoryBycustomerName(user.getUserName()).getResultList();

		model.put("user", user);
		if (inventoryList != null && inventoryList.size() > 0) {
			model.put("inventoryList", inventoryList);

			return new ModelAndView("inventoryListCustApproved", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	// Inventory for Invoice

	@RequestMapping(value = "/inventoryInvoiceList", method = RequestMethod.GET)
	public ModelAndView InventoryInvoiceList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<InventoryForm> invoiceList = invoiceDAO.getInoviceListBycustomerName(user.getUserName());

		model.put("user", user);
		if (invoiceList != null && invoiceList.size() > 0) {
			model.put("invoiceList", invoiceList);

			return new ModelAndView("inventoryInvoiceList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/inventoryInvoiceShow", method = RequestMethod.GET)
	public ModelAndView inventoryInvoiceShow(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		Invoice invoice = invoiceDAO.getByInvoiceId(id);

		inventoryForm.setPoKey(invoice.getPoKey());
		inventoryForm.setCustomerName(invoice.getCustomerName());
		inventoryForm.setGoods(invoice.getGoods());
		List<InvoiceInventory> inventory1 = invoiceInventoryDAO.getInventoryByKeyList(inventoryForm.getPoKey(), inventoryForm.getCustomerName()).getResultList();

		if (inventory1 != null && inventory1.size() > 0) {
			model.put("user", user);
			attributes.addFlashAttribute("success", "Please Click On Update");
			return new ModelAndView("redirect:inventoryInvoiceList");

		} else {

			List<WareHouse> whList = wareHouseDAO.getWareHouseandStatusList(user.getUserName()).getResultList();
			if (whList != null) {
				inventoryForm.setWareHouseList(whList);

				model.put("inventoryForm", inventoryForm);
				model.put("user", user);
			}

			inventoryForm.setId(invoice.getId());

			inventoryForm.setPoKey(invoice.getPoKey());
			inventoryForm.setAmount(invoice.getAmount());
			inventoryForm.setQuantity(invoice.getQuantity());
			inventoryForm.setBuyerName(invoice.getBuyerName());
			inventoryForm.setCustomerHeadName(invoice.getCustomerName());

			model.put("user", user);
			model.put("inventoryForm", inventoryForm);

			return new ModelAndView("inventoryInvoiceShow", "model", model);
		}

	}

	@RequestMapping(value = "/inventoryInvoiceShowConfirm", method = RequestMethod.POST)
	public ModelAndView inventoryInvoiceShowConfirm(@ModelAttribute InventoryForm inventoryForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		inventoryForm.setMasterKey(inventoryForm.getMasterKey());

		model.put("user", user);
		model.put("inventoryForm", inventoryForm);

		return new ModelAndView("inventoryInvoiceShowConfirm", "model", model);
	}

	@RequestMapping(value = "/updateinventoryInvoiceShowConfirm", method = RequestMethod.POST)
	public ModelAndView updateinventoryInvoiceShowConfirm(@ModelAttribute InventoryForm inventoryForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		InvoiceInventory invoiceInventory = new InvoiceInventory();

		invoiceInventory.setId(inventoryForm.getId());
		invoiceInventory.setCustomerName(inventoryForm.getCustomerName());
		invoiceInventory.setCustomerHeadName(inventoryForm.getCustomerHeadName());
		invoiceInventory.setPoKey(inventoryForm.getPoKey());
		invoiceInventory.setAmount(inventoryForm.getAmount());
		invoiceInventory.setDamaged(inventoryForm.getDamaged());
		invoiceInventory.setTotal(inventoryForm.getTotal());
		invoiceInventory.setUsedQuantity(inventoryForm.getUsedQuantity());
		invoiceInventory.setQuantity(inventoryForm.getQuantity());
		invoiceInventory.setMasterKey(inventoryForm.getMasterKey());
		invoiceInventory.setBuyerName(inventoryForm.getBuyerName());
		invoiceInventory.setWareHouseName(inventoryForm.getWareHouseName());
		invoiceInventory.setInventoryType("Invoice");
		invoiceInventory.setTransactionId(inventoryForm.getTransactionId());
		invoiceInventory.setAvail(inventoryForm.getTotal());
		invoiceInventory.setFlag(0);
		invoiceInventory.setGoods(inventoryForm.getGoods());
		invoiceInventoryDAO.insertInvoiceInventory(invoiceInventory);

		model.put("user", user);

		return new ModelAndView("redirect:inventoryInvoiceList");
	}

	@RequestMapping(value = "/invoiceInventoryUpdateShow", method = RequestMethod.GET)
	public ModelAndView invoiceInventoryUpdateShow(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		Invoice purchaseorder = invoiceDAO.getByInvoiceId(id);
		inventoryForm.setPoKey(purchaseorder.getPoKey());
		inventoryForm.setCustomerName(purchaseorder.getCustomerName());

		List<InvoiceInventory> inventory1 = invoiceInventoryDAO.getInventoryByKeyList(inventoryForm.getPoKey(), inventoryForm.getCustomerName()).getResultList();

		if (inventory1 != null && inventory1.size() > 0) {

			InvoiceInventory inventory = invoiceInventoryDAO.getInventoryByKeyList(purchaseorder.getPoKey(), purchaseorder.getCustomerName()).getSingleResult();

			inventoryForm.setId(inventory.getId());

			inventoryForm.setPoKey(inventory.getPoKey());
			inventoryForm.setAmount(inventory.getAmount());
			inventoryForm.setQuantity(inventory.getQuantity());
			inventoryForm.setMasterKey(inventory.getMasterKey());
			inventoryForm.setCustomerName(inventory.getCustomerName());
			inventoryForm.setBuyerName(inventory.getBuyerName());
			inventoryForm.setCustomerHeadName(inventory.getCustomerName());
			inventoryForm.setAvail(inventory.getAvail());

			model.put("user", user);
			model.put("inventoryForm", inventoryForm);

			return new ModelAndView("invoiceInventoryUpdateShow", "model", model);
		} else {
			model.put("user", user);
			attributes.addFlashAttribute("success", "Please Click On Select");
			return new ModelAndView("redirect:inventoryInvoiceList");

		}
	}

	@RequestMapping(value = "/invoiceInventoryUpdateShowConfirm", method = RequestMethod.POST)
	public ModelAndView invoiceInventoryUpdateShowConfirm(@ModelAttribute InventoryForm inventoryForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		inventoryForm.setMasterKey(inventoryForm.getMasterKey());
		if (inventoryForm.getTotal() < 0) {
			model.put("user", user);
			attributes.addFlashAttribute("success", "Total Should Not be Less than Zero");
			return new ModelAndView("redirect:inventoryInvoiceList");
		} else {

			model.put("user", user);
			model.put("inventoryForm", inventoryForm);

			return new ModelAndView("invoiceInventoryUpdateShowConfirm", "model", model);
		}
	}

	@RequestMapping(value = "/invoiceInventoryupdateShowSave", method = RequestMethod.POST)
	public ModelAndView invoiceInventoryupdateShowSave(@ModelAttribute InventoryForm inventoryForm, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		InvoiceInventory inventroy = invoiceInventoryDAO.getByInvoiceInventoryId(inventoryForm.getId());

		inventroy.setDamaged(inventoryForm.getDamaged());
		inventroy.setAvail(inventoryForm.getTotal());
		inventroy.setTotal(inventoryForm.getTotal());
		inventroy.setUsedQuantity(inventoryForm.getUsedQuantity());

		invoiceInventoryDAO.updateInvoiceInventory(inventroy);

		model.put("user", user);

		return new ModelAndView("redirect:inventoryInvoiceList");
	}

	@RequestMapping(value = "/inventoryInvoiceListCustApproved", method = RequestMethod.GET)
	public ModelAndView inventoryInvoiceListCustApproved(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<InvoiceInventory> inventoryInvoiceList = invoiceInventoryDAO.getInvoiceInventoryBycustomerName(user.getUserName()).getResultList();

		model.put("user", user);
		if (inventoryInvoiceList != null && inventoryInvoiceList.size() > 0) {
			model.put("inventoryInvoiceList", inventoryInvoiceList);

			return new ModelAndView("inventoryInvoiceListCustApproved", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/distributionFullDetails", method = RequestMethod.GET)
	public ModelAndView distributionFullDetails(@RequestParam("id") Long id, ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		MasterPlan masterList = masterPlanDAO.getByMasterPlanId(id);

		if (masterList != null && masterList.getMasterKey() != null) {

			List<FundsDistribute> fundsList = fundsDistributeDAO.getFundsList(masterList.getMasterKey()).getResultList();
			if (fundsList != null && fundsList.size() > 0) {
				model.put("fundsList", fundsList);
				return new ModelAndView("distributionFullDetails", "model", model);
			} else {
				return new ModelAndView("noDataFoundInCus", "model", model);
			}
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/fundsRequest", method = RequestMethod.GET)
	public ModelAndView fundsRequest(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<MasterPlan> masterList = masterPlanDAO.getMasterPlanForFunds(user.getUserName()).getResultList();
		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);
			return new ModelAndView("fundsRequest", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/askFundsRequest", method = RequestMethod.GET)
	public ModelAndView askFundsRequest(@RequestParam("id") Long id, ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		MasterPlan masterList = masterPlanDAO.getByMasterPlanId(id);

		if (masterList != null && masterList.getMasterKey() != null) {

			List<FundsDistribute> fundsList = fundsDistributeDAO.getFundsList(masterList.getMasterKey()).getResultList();
			if (fundsList != null && fundsList.size() > 0) {
				model.put("fundsList", fundsList);
				return new ModelAndView("askFundsRequest", "model", model);
			} else {
				return new ModelAndView("noDataFoundInCus", "model", model);
			}
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/requestMoney", method = RequestMethod.GET)
	public ModelAndView requestMoney(@RequestParam("id") Long id, ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		FundsDistribute funds = fundsDistributeDAO.getByFundsId(id);

		requestMoneyForm.setId(funds.getId());
		requestMoneyForm.setRequestedBy(funds.getCustomerHeadName());
		requestMoneyForm.setRequestedFrom(funds.getCustomerName());
		requestMoneyForm.setMasterKey(funds.getMasterKey());
		requestMoneyForm.setAvailAmount(funds.getBusBalance());

		model.put("user", user);
		model.put("requestMoneyForm", requestMoneyForm);

		return new ModelAndView("requestMoney", "model", model);
	}

	@RequestMapping(value = "/requestMoneyConfirm", method = RequestMethod.POST)
	public ModelAndView requestMoneyConfirm(@ModelAttribute RequestMoneyForm requestMoneyForm, ModelMap model, RedirectAttributes attributes) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		if (requestMoneyForm.getAmount().compareTo(requestMoneyForm.getAvailAmount()) > 0) {
			Long Id = requestMoneyForm.getId();
			attributes.addFlashAttribute("success", "Amount Should Not Exceed Available Amount");
			return new ModelAndView("redirect:requestMoney?id=" + Id + "");
		}

		requestMoneyForm.setRequestedBy(requestMoneyForm.getRequestedBy());
		requestMoneyForm.setRequestedFrom(requestMoneyForm.getRequestedFrom());
		requestMoneyForm.setMasterKey(requestMoneyForm.getMasterKey());
		requestMoneyForm.setAmount(requestMoneyForm.getAmount());

		model.put("user", user);
		model.put("requestMoneyForm", requestMoneyForm);

		return new ModelAndView("requestMoneyConfirm", "model", model);
	}

	@RequestMapping(value = "/requestMoneyPost", method = RequestMethod.POST)
	public ModelAndView requestMoneyPost(@ModelAttribute RequestMoneyForm requestMoneyForm, ModelMap model, RedirectAttributes attributes) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		requestMoneyForm.setTransactionId(KeyGenerator.generateTransactionKey());

		RequestMoney money = new RequestMoney();

		money.setRequestedBy(requestMoneyForm.getRequestedBy());
		money.setRequestedFrom(requestMoneyForm.getRequestedFrom());
		money.setMasterKey(requestMoneyForm.getMasterKey());
		money.setAmount(requestMoneyForm.getAmount());
		money.setTransactionId(requestMoneyForm.getTransactionId());

		Date reqDate = DateService.loginDate;
		money.setRequestDate(reqDate);

		requestMoneyDAO.insertRequest(money);

		Transaction trans = new Transaction();

		trans.setTransactionId(requestMoneyForm.getTransactionId());
		trans.setTransactionType("Request For Money");
		trans.setTransactionStatus("Submitted successfully");
		transcationDAOImpl.insertTransaction(trans);

		model.put("user", user);
		model.put("requestMoneyForm", requestMoneyForm);

		return new ModelAndView("requestMoneyTransaction", "model", model);
	}

	@RequestMapping(value = "/requestMoneyTransaction", method = RequestMethod.GET)
	public ModelAndView requestMoneyTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("requestMoneyForm", requestMoneyForm);

		return new ModelAndView("requestMoneyTransaction", "model", model);

	}

	@RequestMapping(value = "/viewRequestSent", method = RequestMethod.GET)
	public ModelAndView viewRequestSent(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		List<RequestMoney> requestList = requestMoneyDAO.getRequestSentList(user.getUserName()).getResultList();

		if (requestList != null && requestList.size() > 0) {

			model.put("requestList", requestList);
			return new ModelAndView("viewRequestSent", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}
	}

	@RequestMapping(value = "/viewRequestRecieved", method = RequestMethod.GET)
	public ModelAndView viewRequestRecieved(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		List<RequestMoney> requestList = requestMoneyDAO.getRequestRecievedList(user.getUserName()).getResultList();

		if (requestList != null && requestList.size() > 0) {

			model.put("requestList", requestList);
			return new ModelAndView("viewRequestRecieved", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/headBuyerPoList", method = RequestMethod.GET)
	public ModelAndView buyerPoList(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		List<BuyerPO> buyerList = buyerPODAO.getBuyerPOByHeadName(user.getUserName()).getResultList();
		if (buyerList != null && buyerList.size() > 0) {
			model.put("buyerList", buyerList);
			return new ModelAndView("headBuyerPoList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}
	}

	@RequestMapping(value = "/buyerCustomerHeadBranchList", method = RequestMethod.GET)
	public ModelAndView buyerCustomerHeadBranchList(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		newBuyerForm.setEndUser(user);
		newBuyerForm.setName(user.getCustomerHeadName());
		newBuyerForm.setbName(user.getUserName());

		List<NewBuyer> newbuyerList = newBuyerDAOImpl.getForApproval().getResultList();

		model.put("user", user);
		if (newbuyerList != null && newbuyerList.size() > 0) {
			model.put("newbuyerList", newbuyerList);
			model.put("newBuyerForm", newBuyerForm);

			return new ModelAndView("buyerCustomerHeadBranchList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}
	}

	@RequestMapping(value = "/buyerPageShowApproval", method = RequestMethod.GET)
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

		return new ModelAndView("buyerPageShowApproval", "model", model);
	}

	@RequestMapping(value = "/buyerPageShowConfirmApproval", method = RequestMethod.POST)
	public ModelAndView approveBankEmpConfrim(@ModelAttribute NewBuyerForm newBuyerForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("buyerPageShowConfirmApproval", "model", model);
	}

	@RequestMapping(value = "/updatebuyerPageShowConfrim", method = RequestMethod.POST)
	public ModelAndView updatebuyerPageShowConfrim(@ModelAttribute NewBuyerForm newBuyerForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		newBuyerForm.setTransactionId(KeyGenerator.generateTransactionKey());
		newBuyerForm.setUniqueKey(KeyGenerator.generateTransactionKey());

		NewBuyer buyer = newBuyerDAOImpl.findId(newBuyerForm.getId());
		Transaction transaction = new Transaction();
		buyer.setId(newBuyerForm.getId());
		buyer.setBuyerName(newBuyerForm.getBuyerName());
		buyer.setName(newBuyerForm.getName());
		newBuyerForm.setbName(buyer.getbName());
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

		model.put("user", user);
		return new ModelAndView("approvalbuyerPageSuccessApproval", "model", model);
	}

	// Customer Supplier Head List

	@RequestMapping(value = "/supplierCustomerHeadBranchList", method = RequestMethod.GET)
	public ModelAndView supplierCustomerHeadBranchList(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		supplierForm.setEndUser(user);
		supplierForm.setName(user.getCustomerHeadName());
		supplierForm.setbName(user.getUserName());

		List<Supplier> supplierList = supplierDAOImpl.getForApproval().getResultList();

		model.put("user", user);
		if (supplierList != null && supplierList.size() > 0) {
			model.put("supplierList", supplierList);
			model.put("supplierForm", supplierForm);

			return new ModelAndView("supplierCustomerHeadBranchList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}
	}

	@RequestMapping(value = "/supplierPageShowConfirmApproval", method = RequestMethod.GET)
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

		return new ModelAndView("supplierPageShowConfirmApproval", "model", model);
	}

	@RequestMapping(value = "/supplierPageShowApprovalConfirm", method = RequestMethod.POST)
	public ModelAndView approvesupplierPageShowConfirm(@ModelAttribute SupplierForm supplierForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("supplierPageShowApprovalConfirm", "model", model);
	}

	@RequestMapping(value = "/updatesupplierPageShowConfrim", method = RequestMethod.POST)
	public ModelAndView updatesupplierPageShowConfrim(@ModelAttribute SupplierForm supplierForm, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();


		supplierForm.setTransactionId(KeyGenerator.generateTransactionKey());
		supplierForm.setUniquekey(KeyGenerator.generateTransactionKey());


		Supplier supplier = supplierDAOImpl.findId(supplierForm.getId());
		Transaction transaction = new Transaction();
		supplier.setId(supplierForm.getId());
		supplier.setName(supplierForm.getName());
		supplier.setbName(supplierForm.getbName());
		supplier.setUniquekey(supplierForm.getUniquekey());
		supplier.setSupplierName(supplierForm.getSupplierName());
		supplier.setCompanyName(supplierForm.getCompanyName());
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

		model.put("user", user);

		return new ModelAndView("approvalsupplierPageSuccessApproval", "model", model);
	}

	@RequestMapping(value = "/confirmSelectCustomerSellerSameBankEvents", method = RequestMethod.POST)
	public ModelAndView confirmSelectCustomerSellerSameBankEvents(ModelMap model, @ModelAttribute SellerSameBankEventForm sellerSameBankEventForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("sellerSameBankEventForm", sellerSameBankEventForm);

		return new ModelAndView("confirmSelectCustomerSellerSameBankEvents", "model", model);
	}

	@RequestMapping(value = "/masterPlanRePayment", method = RequestMethod.GET)
	public ModelAndView masterPlanRePayment(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<MasterPlan> masterList = masterPlanDAO.getMasterPlanForFunds(user.getUserName()).getResultList();

		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);
			return new ModelAndView("masterPlanRePayment", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/masterPlanRePaymentDisplay", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentDisplay(@RequestParam Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		MasterPlan master = masterPlanDAO.getByMasterPlanId(id);

		repaymentForm.setMasterKey(master.getMasterKey());
		repaymentForm.setBuyingCostSanc(master.getBuyingCostSanc());
		repaymentForm.setWcSancAmount(master.getWcSancAmount());
		repaymentForm.setCustomer(master.getCustomer());
		repaymentForm.setCustomerEmail(master.getCustomerEmail());
		repaymentForm.setBuyingCostDate(master.getBuyingCostDate());
		repaymentForm.setTenure(master.getTenure());
		repaymentForm.setRateOfInt1(master.getRateOfInt1());
		repaymentForm.setAmountPaid(master.getAmountPaid());
		repaymentForm.setFunalAmt(master.getFunalAmt());
		repaymentForm.setRateOfInt1(master.getRateOfInt1());
		repaymentForm.setWcTotalInterest(master.getWcTotalInterest());
		repaymentForm.setWcTenure(master.getWcTenure());
		model.put("repaymentForm", repaymentForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentDisplay", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentDisplayConfirm", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentDisplayConfirm(ModelMap model, @ModelAttribute RepaymentForm repaymentForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		repaymentForm.setMasterKey(repaymentForm.getMasterKey());
		repaymentForm.setBuyingCostSanc(repaymentForm.getBuyingCostSanc());
		repaymentForm.setWcSancAmount(repaymentForm.getWcSancAmount());
		repaymentForm.setCustomer(repaymentForm.getCustomer());
		repaymentForm.setCustomerEmail(repaymentForm.getCustomerEmail());
		repaymentForm.setBuyingCostDate(repaymentForm.getBuyingCostDate());
		repaymentForm.setTenure(repaymentForm.getTenure());
		repaymentForm.setRateOfInt1(repaymentForm.getRateOfInt1());
		repaymentForm.setPayOption(repaymentForm.getPayOption());
		repaymentForm.setAmtType(repaymentForm.getAmtType());
		repaymentForm.setAmountPaid(repaymentForm.getAmountPaid());
		repaymentForm.setFunalAmt(repaymentForm.getFunalAmt());
		repaymentForm.setWcTotalInterest(repaymentForm.getWcTotalInterest());
		repaymentForm.setWcTenure(repaymentForm.getWcTenure());
		model.put("repaymentForm", repaymentForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentDisplayConfirm", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentSave", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentSave(ModelMap model, @ModelAttribute RepaymentForm repaymentForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		MasterPlan master= masterPlanDAO.getMasterPlanByMasterKey(repaymentForm.getMasterKey()).getSingleResult();

		repaymentForm.setTransactionId(KeyGenerator.generateTransactionKey());


		/* repaymentForm.setTransactionId(KeyGenerator.generateTransactionKey()); */

		Repayment repay = new Repayment();

		repay.setMasterKey(repaymentForm.getMasterKey());
		repay.setBuyingCostSanc(repaymentForm.getBuyingCostSanc());
		repay.setWcSancAmount(repaymentForm.getWcSancAmount());
		repay.setCustomer(repaymentForm.getCustomer());
		repay.setCustomerEmail(repaymentForm.getCustomerEmail());
		repay.setBuyingCostDate(repaymentForm.getBuyingCostDate());
		repay.setTenure(repaymentForm.getTenure());
		repay.setFunalAmt(repaymentForm.getFunalAmt());
		repay.setRateOfInt1(repaymentForm.getRateOfInt1());
		repay.setPayOption(repaymentForm.getPayOption());
		repay.setAmtType(repaymentForm.getAmtType());
		repay.setTransactionId(repaymentForm.getTransactionId());
		repay.setAmountPaid(repaymentForm.getAmountPaid());
		repay.setWcTotalInterest(repaymentForm.getWcTotalInterest());
		repay.setWcTenure(repaymentForm.getWcTenure());
		repay.setWcDate(master.getWcDate());
		repay.setcStatus("Pending");
		repay.setMoneyStatus("Pending");

		repaymenyDAO.createRepay(repay);

		Transaction trans = new Transaction();

		trans.setTransactionId(repaymentForm.getTransactionId());
		trans.setTransactionStatus("Repayment Request");
		trans.setTransactionType("Sent Successfully");

		transcationDAOImpl.insertTransaction(trans);

		model.put("repaymentForm", repaymentForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentTransaction", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentTransaction", method = RequestMethod.GET)
	public ModelAndView clientAdminTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("repaymentForm", repaymentForm);

		return new ModelAndView("masterPlanRePaymentTransaction", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentList", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Repayment> repayList = repaymenyDAO.getMasterPlanForRepayment(user.getUserName()).getResultList();

		if (repayList != null && repayList.size() > 0) {
			model.put("repayList", repayList);
			return new ModelAndView("masterPlanRePaymentList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/masterPlanRePaymentAcceptList", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentAcceptList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Repayment> repayList = repaymenyDAO.getMasterPlanForRepaymentAndAppStatus(user.getUserName()).getResultList();

		if (repayList != null && repayList.size() > 0) {
			model.put("repayList", repayList);
			return new ModelAndView("masterPlanRePaymentAcceptList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/masterPlanRePaymentAccept", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentAppMngLAppr(@RequestParam Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		Repayment master = repaymenyDAO.getByRepaymentId(id);

		repaymentForm.setId(master.getId());
		repaymentForm.setMasterKey(master.getMasterKey());
		repaymentForm.setCustomer(master.getCustomer());
		repaymentForm.setCustomerEmail(master.getCustomerEmail());
		repaymentForm.setPayOption(master.getPayOption());
		repaymentForm.setAmtType(master.getAmtType());
		repaymentForm.setTransactionId(master.getTransactionId());

		model.put("repaymentForm", repaymentForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentAccept", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentAcceptConfirm", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentAcceptConfirm(ModelMap model, @ModelAttribute RepaymentForm repaymentForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		repaymentForm.setId(repaymentForm.getId());
		repaymentForm.setMasterKey(repaymentForm.getMasterKey());
		repaymentForm.setCustomer(repaymentForm.getCustomer());
		repaymentForm.setCustomerEmail(repaymentForm.getCustomerEmail());
		repaymentForm.setTenure(repaymentForm.getTenure());
		repaymentForm.setPayOption(repaymentForm.getPayOption());
		repaymentForm.setAmtType(repaymentForm.getAmtType());
		repaymentForm.setTransactionId(repaymentForm.getTransactionId());
		repaymentForm.setAccept(repaymentForm.getAccept());

		model.put("repaymentForm", repaymentForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentAcceptConfirm", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentAcceptSave", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentAcceptSave(ModelMap model, @ModelAttribute RepaymentForm repaymentForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();
		Repayment repay = repaymenyDAO.getByRepaymentId(repaymentForm.getId());

		repay.setAccept(repaymentForm.getAccept());
		repay.setPayConfirm("Pending");
		repaymenyDAO.updateRepayment(repay);

		Transaction trans = new Transaction();

		trans.setTransactionId(repaymentForm.getTransactionId());
		trans.setTransactionStatus("Repayment Accept/Reject");
		trans.setTransactionType("Sent Successfully");
		transcationDAOImpl.insertTransaction(trans);

		model.put("repaymentForm", repaymentForm);
		model.put("user", user);

		return new ModelAndView("masterPlanRePaymentAcceptTrans", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentAcceptTrans", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("repaymentForm", repaymentForm);

		return new ModelAndView("masterPlanRePaymentAcceptTrans", "model", model);

	}

	@RequestMapping(value = "/masterPlanRePaymentCustQuarterly", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentAppQuarterly(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

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

				return new ModelAndView("redirect:masterPlanRePaymentAcceptList");

			}
		}
		model.put("user", user);
		return new ModelAndView("masterPlanRePaymentCustQuarterly", "model", model);
	}

	@RequestMapping(value = "/masterPlanRePaymentCustFullList", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentAppFullList(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

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

				return new ModelAndView("redirect:masterPlanRePaymentAcceptList");

			}
		}
		model.put("user", user);
		return new ModelAndView("masterPlanRePaymentCustFullList", "model", model);
	}

	@RequestMapping(value = "/masterPlanRePaymentCustHalfYearly", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentAppHalfYearly(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

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

				return new ModelAndView("redirect:masterPlanRePaymentAcceptList");

			}
		}
		model.put("user", user);
		return new ModelAndView("masterPlanRePaymentCustHalfYearly", "model", model);
	}

	@RequestMapping(value = "/poPaymentHeadList", method = RequestMethod.GET)
	public ModelAndView poPaymentHeadList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> purchaseList = purchaseOrderDAO.getPoListForPayment(user.getUserName()).getResultList();

		if (purchaseList != null && purchaseList.size() > 0) {
			model.put("purchaseList", purchaseList);
			return new ModelAndView("poPaymentHeadList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/poPaymentHead", method = RequestMethod.GET)
	public ModelAndView poPaymentHead(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(id);

		purchaseOrderForm.setId(purchase.getId());
		purchaseOrderForm.setSupplierName(purchase.getSupplierName());
		purchaseOrderForm.setCustomerHeadName(purchase.getCustomerHeadName());
		purchaseOrderForm.setCustomerName(purchase.getCustomerName());
		purchaseOrderForm.setPoKey(purchase.getPoKey());
		purchaseOrderForm.setSupplierEmail(purchase.getSupplierEmail());
		purchaseOrderForm.setCustomerHeadEmail(purchase.getCustomerHeadEmail());
		purchaseOrderForm.setCustomerBranchEmail(purchase.getCustomerBranchEmail());
		purchaseOrderForm.setAmount(purchase.getAmount());
		purchaseOrderForm.setTransactionId(purchase.getTransactionId());
		purchaseOrderForm.setPayBalance(purchase.getPayBalance());

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("poPaymentHead", "model", model);
	}

	@RequestMapping(value = "/poPaymentHeadConfirm", method = RequestMethod.POST)
	public ModelAndView poPaymentHeadConfirm(ModelMap model, @ModelAttribute PurchaseOrderForm purchaseOrderForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		if (purchaseOrderForm.getTypeOfTrans().equals("LC") || purchaseOrderForm.getTypeOfTrans().equals("BG")) {
			model.put("purchaseOrderForm", purchaseOrderForm);
			return new ModelAndView("poPaymentHeadLcPage", "model", model);
		} else {
			purchaseOrderForm.setId(purchaseOrderForm.getId());
			purchaseOrderForm.setSupplierName(purchaseOrderForm.getSupplierName());
			purchaseOrderForm.setCustomerHeadName(purchaseOrderForm.getCustomerHeadName());
			purchaseOrderForm.setCustomerName(purchaseOrderForm.getCustomerName());
			purchaseOrderForm.setPoKey(purchaseOrderForm.getPoKey());
			purchaseOrderForm.setSupplierEmail(purchaseOrderForm.getSupplierEmail());
			purchaseOrderForm.setCustomerHeadEmail(purchaseOrderForm.getCustomerHeadEmail());
			purchaseOrderForm.setCustomerBranchEmail(purchaseOrderForm.getCustomerBranchEmail());
			purchaseOrderForm.setAmount(purchaseOrderForm.getAmount());
			purchaseOrderForm.setTypeOfTrans(purchaseOrderForm.getTypeOfTrans());
			purchaseOrderForm.setChequenum(purchaseOrderForm.getChequenum());
			purchaseOrderForm.setSignAuth(purchaseOrderForm.getSignAuth());
			purchaseOrderForm.setAmtLimit(purchaseOrderForm.getAmtLimit());
			purchaseOrderForm.setTransactionId(purchaseOrderForm.getTransactionId());
			purchaseOrderForm.setPayBalance(purchaseOrderForm.getPayBalance());
			model.put("purchaseOrderForm", purchaseOrderForm);

		}
		model.put("user", user);
		return new ModelAndView("poPaymentHeadConfirm", "model", model);

	}

	@RequestMapping(value = "/poPaymentHeadSave", method = RequestMethod.POST)
	public ModelAndView poPaymentHeadSave(ModelMap model, @ModelAttribute PurchaseOrderForm purchaseOrderForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(purchaseOrderForm.getId());

		purchase.setTypeOfTrans(purchaseOrderForm.getTypeOfTrans());
		purchase.setChequenum(purchaseOrderForm.getChequenum());
		purchase.setSignAuth(purchaseOrderForm.getSignAuth());
		purchase.setAmtLimit(purchaseOrderForm.getAmtLimit());
		purchase.setTransStatus("Approved");
		if (purchase.getTypeOfTrans().equals("transfer")) {
			purchase.setTransResult("Cleared");
			Date payDate = DateService.loginDate;
			purchase.setPoPayDate(payDate);
		} else {
			purchase.setTransResult("Pending");
		}

		purchaseOrderDAO.updatePo(purchase);
		Transaction trans = new Transaction();

		trans.setTransactionId(purchaseOrderForm.getTransactionId());
		trans.setTransactionStatus("Payment For PO");
		trans.setTransactionType("Sent Successfully");

		transcationDAOImpl.insertTransaction(trans);

		Payment payment = new Payment();
		payment.setCustomerName(purchaseOrderForm.getCustomerName());
		payment.setAmount(purchaseOrderForm.getPayBalance());
		payment.setMasterKey(purchase.getMasterKey());
		payment.setPoKey(purchase.getPoKey());
		payment.setTransactionId(purchaseOrderForm.getTransactionId());
		payment.setTypeOfTrans(purchaseOrderForm.getTypeOfTrans());
		payment.setPoID(purchaseOrderForm.getId());
		payment.setPaymentDate(DateService.loginDate);

		paymentDAO.insertPayment(payment);

		model.put("purchaseOrderForm", purchaseOrderForm);
		model.put("user", user);

		return new ModelAndView("poPaymentHeadTrans", "model", model);

	}

	@RequestMapping(value = "/poPaymentHeadAdvance", method = RequestMethod.GET)
	public ModelAndView poPaymentBranchAdvance(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(id);

		purchaseOrderForm.setId(purchase.getId());
		purchaseOrderForm.setSupplierName(purchase.getSupplierName());
		purchaseOrderForm.setCustomerHeadName(purchase.getCustomerHeadName());
		purchaseOrderForm.setCustomerName(purchase.getCustomerName());
		purchaseOrderForm.setPoKey(purchase.getPoKey());
		purchaseOrderForm.setSupplierEmail(purchase.getSupplierEmail());
		purchaseOrderForm.setCustomerHeadEmail(purchase.getCustomerHeadEmail());
		purchaseOrderForm.setCustomerBranchEmail(purchase.getCustomerBranchEmail());
		purchaseOrderForm.setPayAdvance(purchase.getPayAdvance());
		purchaseOrderForm.setPayBalance(purchase.getPayBalance());
		purchaseOrderForm.setAmount(purchase.getAmount());
		purchaseOrderForm.setTransactionId(purchase.getTransactionId());

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("poPaymentHeadAdvance", "model", model);
	}

	@RequestMapping(value = "/poPaymentHeadAdvanceConfirm", method = RequestMethod.POST)
	public ModelAndView poPaymentBranchAdvanceConfirm(ModelMap model, @ModelAttribute PurchaseOrderForm purchaseOrderForm, RedirectAttributes attributes, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();
		Float advance = purchaseOrderForm.getPayAdvance();
		Float bal = purchaseOrderForm.getPayBalance();
		Float amt = purchaseOrderForm.getPayNow();

		Float adv = advance + amt;
		Float balance = bal - amt;
		if (balance <= 0) {
			attributes.addFlashAttribute("success", "Payment is more than the total amount");
			model.put("purchaseOrderForm", purchaseOrderForm);
			return new ModelAndView("redirect:poPaymentHeadList");
		} else {
			purchaseOrderForm.setId(purchaseOrderForm.getId());
			purchaseOrderForm.setSupplierName(purchaseOrderForm.getSupplierName());
			purchaseOrderForm.setCustomerHeadName(purchaseOrderForm.getCustomerHeadName());
			purchaseOrderForm.setCustomerName(purchaseOrderForm.getCustomerName());
			purchaseOrderForm.setPoKey(purchaseOrderForm.getPoKey());
			purchaseOrderForm.setSupplierEmail(purchaseOrderForm.getSupplierEmail());
			purchaseOrderForm.setCustomerHeadEmail(purchaseOrderForm.getCustomerHeadEmail());
			purchaseOrderForm.setCustomerBranchEmail(purchaseOrderForm.getCustomerBranchEmail());
			purchaseOrderForm.setAmount(purchaseOrderForm.getAmount());
			purchaseOrderForm.setTypeOfTrans(purchaseOrderForm.getTypeOfTrans());
			purchaseOrderForm.setChequenum(purchaseOrderForm.getChequenum());
			purchaseOrderForm.setSignAuth(purchaseOrderForm.getSignAuth());
			purchaseOrderForm.setAmtLimit(purchaseOrderForm.getAmtLimit());
			purchaseOrderForm.setPayAdvance(adv);
			purchaseOrderForm.setPayBalance(balance);
			purchaseOrderForm.setTransactionId(purchaseOrderForm.getTransactionId());
			model.put("purchaseOrderForm", purchaseOrderForm);
			model.put("user", user);
		}
		return new ModelAndView("poPaymentHeadAdvanceConfirm", "model", model);

	}

	@RequestMapping(value = "/poPaymentHeadAdvanceSave", method = RequestMethod.POST)
	public ModelAndView poPaymentBranchAdvanceSave(ModelMap model, @ModelAttribute PurchaseOrderForm purchaseOrderForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(purchaseOrderForm.getId());

		purchase.setTypeOfTrans(purchaseOrderForm.getTypeOfTrans());
		purchase.setChequenum(purchaseOrderForm.getChequenum());
		purchase.setSignAuth(purchaseOrderForm.getSignAuth());
		purchase.setAmtLimit(purchaseOrderForm.getAmtLimit());
		purchase.setPayAdvance(purchaseOrderForm.getPayAdvance());
		purchase.setPayBalance(purchaseOrderForm.getPayBalance());
		purchase.setTransResult("Multiple");

		purchaseOrderDAO.updatePo(purchase);
		Transaction trans = new Transaction();

		trans.setTransactionId(purchaseOrderForm.getTransactionId());
		trans.setTransactionStatus("Advance Payment For PO");
		trans.setTransactionType("Sent Successfully");

		transcationDAOImpl.insertTransaction(trans);


		Payment payment = new Payment();
		payment.setCustomerName(purchaseOrderForm.getCustomerName());
		payment.setAmount(purchaseOrderForm.getPayAdvance());
		payment.setMasterKey(purchase.getMasterKey());
		payment.setPoKey(purchase.getPoKey());
		payment.setTransactionId(purchaseOrderForm.getTransactionId());
		payment.setTypeOfTrans(purchaseOrderForm.getTypeOfTrans());
		payment.setPoID(purchaseOrderForm.getId());
        payment.setPaymentDate(DateService.loginDate);

		paymentDAO.insertPayment(payment);

		model.put("purchaseOrderForm", purchaseOrderForm);
		model.put("user", user);

		return new ModelAndView("poPaymentHeadTrans", "model", model);

	}

	@RequestMapping(value = "/poPaymentHeadTrans", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentHalfTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("poPaymentHeadTrans", "model", model);

	}

	/**
	 * Method to make multiple PO payment
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/poMultiplePayment", method = RequestMethod.GET)
	public ModelAndView poMultiplePayment(ModelMap model, @ModelAttribute PurchaseOrderForm purchaseOrderForm) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> purchaseList = new ArrayList<PurchaseOrder>();
		Float totalAmt = 0f;
		if (purchaseOrderForm.getPurchaseList() == null) {
			purchaseList = purchaseOrderDAO.getPoListForPayment(user.getUserName()).getResultList();
		} else {
			for (PurchaseOrder po : purchaseOrderForm.getPurchaseList()) {
				if (po.getId() != null) {
					po = purchaseOrderDAO.getByPurchaseId(po.getId());
					if (po.getAmount() != null) {
						totalAmt = totalAmt + po.getAmount();
					}
					purchaseList.add(po);
				}
			}
		}
		if (purchaseList != null && purchaseList.size() > 0) {

			model.put("totalAmt", totalAmt);
			model.put("purchaseList", purchaseList);
			model.put("purchaseOrderForm", purchaseOrderForm);
			return new ModelAndView("poMultiplePayment", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}
	}

	/**
	 * Review the PO's selected for Payment
	 *
	 * @param model
	 * @param purchaseOrderForm
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/poMultiplePaymentReview", method = RequestMethod.POST)
	public String poMultiplePaymentReview(ModelMap model, @ModelAttribute PurchaseOrderForm purchaseOrderForm, RedirectAttributes attributes) {
		attributes.addFlashAttribute("purchaseOrderForm", purchaseOrderForm);

		return "redirect:poMultiplePayment";
	}

	/**
	 * Make selected PO's payment
	 *
	 * @param model
	 * @param purchaseOrderForm
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/poMultiplePaymentUpdate", method = RequestMethod.POST)
	public String poMultiplePaymentUpdate(ModelMap model, @ModelAttribute PurchaseOrderForm purchaseOrderForm, RedirectAttributes attributes) {

		purchaseOrderForm.setTransactionId(KeyGenerator.generateTransactionKey());

		if (purchaseOrderForm.getPurchaseList() != null && purchaseOrderForm.getPurchaseList().size() > 0) {
			for (PurchaseOrder po : purchaseOrderForm.getPurchaseList()) {
				if (po.getId() != null) {
					PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(po.getId());

					purchase.setTransResult(Constants.PENDING);
					purchase.setTransStatus(Constants.APPROVED);
					purchase.setTypeOfTrans(Constants.MULTITRANS);
					purchase.setPoPayDate(DateService.loginDate);
					purchase.setTransactionId(purchaseOrderForm.getTransactionId());

					purchaseOrderDAO.updatePo(purchase);
				}
			}
		}

		Transaction trans = new Transaction();

		trans.setTransactionId(purchaseOrderForm.getTransactionId());
		trans.setTransactionStatus("Multiple Payment For PO");
		trans.setTransactionType("Sent Successfully");

		transcationDAOImpl.insertTransaction(trans);

		model.put("purchaseOrderForm", purchaseOrderForm);
		return "redirect:poPaymentHeadTrans";
	}

	@RequestMapping(value = "/poPaymentHeadLcPage", method = RequestMethod.GET)
	public ModelAndView poPaymentHeadLcPage(ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		letterOfCreditForm.setId(purchaseOrderForm.getId());
		letterOfCreditForm.setSupplierName(purchaseOrderForm.getSupplierName());
		letterOfCreditForm.setCustomerHeadName(purchaseOrderForm.getCustomerHeadName());
		letterOfCreditForm.setCustomerName(purchaseOrderForm.getCustomerName());
		letterOfCreditForm.setPoKey(purchaseOrderForm.getPoKey());
		letterOfCreditForm.setSupplierEmail(purchaseOrderForm.getSupplierEmail());
		letterOfCreditForm.setCustomerHeadEmail(purchaseOrderForm.getCustomerHeadEmail());
		letterOfCreditForm.setCustomerBranchEmail(purchaseOrderForm.getCustomerBranchEmail());
		letterOfCreditForm.setAmount(purchaseOrderForm.getAmount());
		letterOfCreditForm.setTypeOfTrans(purchaseOrderForm.getTypeOfTrans());
		letterOfCreditForm.setTransactionId(purchaseOrderForm.getTransactionId());
		letterOfCreditForm.setPayBalance(purchaseOrderForm.getPayBalance());

		model.put("user", user);
		model.put("letterOfCreditForm", letterOfCreditForm);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("poPaymentHeadLcPage", "model", model);
	}

	@RequestMapping(value = "/poPaymentHeadLcPageConfirm", method = RequestMethod.POST)
	public ModelAndView poPaymentHeadLcPageConfirm(ModelMap model, @ModelAttribute LetterOfCreditForm letterOfCreditForm, BindingResult result) {

		letterOfCreditForm.setId(letterOfCreditForm.getId());
		letterOfCreditForm.setSupplierName(letterOfCreditForm.getSupplierName());
		letterOfCreditForm.setCustomerHeadName(letterOfCreditForm.getCustomerHeadName());
		letterOfCreditForm.setCustomerName(letterOfCreditForm.getCustomerName());
		letterOfCreditForm.setPoKey(letterOfCreditForm.getPoKey());
		letterOfCreditForm.setSupplierEmail(letterOfCreditForm.getSupplierEmail());
		letterOfCreditForm.setCustomerHeadEmail(letterOfCreditForm.getCustomerHeadEmail());
		letterOfCreditForm.setCustomerBranchEmail(letterOfCreditForm.getCustomerBranchEmail());
		letterOfCreditForm.setAmount(letterOfCreditForm.getAmount());
		letterOfCreditForm.setTransactionId(letterOfCreditForm.getTransactionId());
		letterOfCreditForm.setTypeOfLc(letterOfCreditForm.getTypeOfLc());
		letterOfCreditForm.setBankType(letterOfCreditForm.getBankType());
		letterOfCreditForm.setBankName(letterOfCreditForm.getBankName());
		letterOfCreditForm.setBankAddress(letterOfCreditForm.getBankAddress());
		letterOfCreditForm.setBankBranch(letterOfCreditForm.getBankBranch());
		letterOfCreditForm.setSwiftCode(letterOfCreditForm.getSwiftCode());
		letterOfCreditForm.setAccNo(letterOfCreditForm.getAccNo());
		letterOfCreditForm.setContactNum(letterOfCreditForm.getContactNum());
		letterOfCreditForm.setIfsc(letterOfCreditForm.getIfsc());
		letterOfCreditForm.setContactPerson(letterOfCreditForm.getContactPerson());
		letterOfCreditForm.setBankNameCorr(letterOfCreditForm.getBankNameCorr());
		letterOfCreditForm.setBankBranchCorr(letterOfCreditForm.getBankBranchCorr());
		letterOfCreditForm.setBankLocCorr(letterOfCreditForm.getBankLocCorr());
		letterOfCreditForm.setSwiftCodeCorr(letterOfCreditForm.getSwiftCodeCorr());
		letterOfCreditForm.setAccNum(letterOfCreditForm.getAccNum());
		letterOfCreditForm.setTypeOfTrans(letterOfCreditForm.getTypeOfTrans());
		letterOfCreditForm.setPayBalance(letterOfCreditForm.getPayBalance());

		model.put("letterOfCreditForm", letterOfCreditForm);

		return new ModelAndView("poPaymentHeadLcPageConfirm", "model", model);

	}

	@RequestMapping(value = "/poPaymentHeadLcPageSave", method = RequestMethod.POST)
	public ModelAndView poPaymentHeadLcPageSave(ModelMap model, @ModelAttribute LetterOfCreditForm letterOfCreditForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		LetterOfCredit credit = new LetterOfCredit();

		credit.setSupplierName(letterOfCreditForm.getSupplierName());
		credit.setCustomerHeadName(letterOfCreditForm.getCustomerHeadName());
		credit.setCustomerName(letterOfCreditForm.getCustomerName());
		credit.setPoKey(letterOfCreditForm.getPoKey());
		credit.setSupplierEmail(letterOfCreditForm.getSupplierEmail());
		credit.setCustomerHeadEmail(letterOfCreditForm.getCustomerHeadEmail());
		credit.setCustomerBranchEmail(letterOfCreditForm.getCustomerBranchEmail());
		credit.setAmount(letterOfCreditForm.getPayBalance());
		credit.setTransactionId(letterOfCreditForm.getTransactionId());
		credit.setTypeOfLc(letterOfCreditForm.getTypeOfLc());
		credit.setBankType(letterOfCreditForm.getBankType());
		credit.setBankName(letterOfCreditForm.getBankName());
		credit.setBankAddress(letterOfCreditForm.getBankAddress());
		credit.setBankBranch(letterOfCreditForm.getBankBranch());
		credit.setSwiftCode(letterOfCreditForm.getSwiftCode());
		credit.setAccNo(letterOfCreditForm.getAccNo());
		credit.setIfsc(letterOfCreditForm.getIfsc());
		credit.setContactPerson(letterOfCreditForm.getContactPerson());
		credit.setContactNum(letterOfCreditForm.getContactNum());
		credit.setTypeOfTrans(letterOfCreditForm.getTypeOfTrans());
		credit.setTransResult("Pending");
		credit.setTransStatus("Approved");
		credit.setTypeOfTrans(letterOfCreditForm.getTypeOfTrans());

		letterOfCreditDAO.createLetterOfCredit(credit);

		PurchaseOrder purchase = purchaseOrderDAO.getPoListByPoKey(letterOfCreditForm.getPoKey()).getSingleResult();

		purchase.setTransResult("Pending");
		purchase.setTransStatus("Approved");
		purchase.setTypeOfTrans(letterOfCreditForm.getTypeOfTrans());

		purchaseOrderDAO.updatePo(purchase);

		String allBanName = letterOfCreditForm.getBankNameCorr();
		String[] bankName = allBanName.split(",");

		String allBankBranch = letterOfCreditForm.getBankBranchCorr();
		String[] bankBranch = allBankBranch.split(",");

		String allBranchLoc = letterOfCreditForm.getBankLocCorr();
		String[] branchLoc = allBranchLoc.split(",");

		String allSwiftCode = letterOfCreditForm.getSwiftCodeCorr();
		String[] swiftCode = allSwiftCode.split(",");

		String allAcNo = letterOfCreditForm.getAccNum();
		String[] acNo = allAcNo.split(",");

		List<LetterOfCreditForm> creditList = new ArrayList<LetterOfCreditForm>();
		for (int count = 0; count < bankName.length; count++) {

			LetterOfCreditForm creditForm = new LetterOfCreditForm();

			creditForm.setBankNameCorr(bankName[count]);
			creditForm.setBankBranchCorr(bankBranch[count]);
			creditForm.setBankLocCorr(branchLoc[count]);
			creditForm.setSwiftCodeCorr(swiftCode[count]);
			creditForm.setAccNum(acNo[count]);

			creditList.add(creditForm);
		}
		for (LetterOfCreditForm value : creditList) {

			Correspondent letterCredit = new Correspondent();
			letterCredit.setCustomerName(letterOfCreditForm.getCustomerName());
			letterCredit.setSupplierName(letterOfCreditForm.getSupplierName());
			letterCredit.setPoKey(letterOfCreditForm.getPoKey());
			letterCredit.setTransactionId(letterOfCreditForm.getTransactionId());
			letterCredit.setBankName(value.getBankNameCorr());
			letterCredit.setBankBranch(value.getBankBranchCorr());
			letterCredit.setBankLoc(value.getBankLocCorr());
			letterCredit.setSwiftCode(value.getSwiftCodeCorr());
			letterCredit.setAccNum(value.getAccNum());

			correspondentDAO.createCorrespondent(letterCredit);
		}

		Payment payment = new Payment();
		payment.setCustomerName(letterOfCreditForm.getCustomerName());
		payment.setAmount(letterOfCreditForm.getPayBalance());
		payment.setMasterKey(purchase.getMasterKey());
		payment.setPoKey(purchase.getPoKey());
		payment.setTransactionId(letterOfCreditForm.getTransactionId());
		payment.setTypeOfTrans(letterOfCreditForm.getTypeOfTrans());
		payment.setPoID(letterOfCreditForm.getId());
		payment.setPaymentDate(DateService.loginDate);

		paymentDAO.insertPayment(payment);

		model.put("letterOfCreditForm", letterOfCreditForm);
		model.put("user", user);

		return new ModelAndView("poPaymentHeadLcTrans", "model", model);

	}

	@RequestMapping(value = "/poPaymentHeadLcTrans", method = RequestMethod.GET)
	public ModelAndView poPaymentHeadLcTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("letterOfCreditForm", letterOfCreditForm);

		return new ModelAndView("poPaymentHeadLcTrans", "model", model);

	}

	@RequestMapping(value = "/poPaymentHeadFullList", method = RequestMethod.GET)
	public ModelAndView poPaymentBankList(ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> purchaseList = purchaseOrderDAO.getPoListByBranchName(user.getUserName()).getResultList();
		if (purchaseList != null && purchaseList.size() > 0) {

			model.put("purchaseList", purchaseList);
			return new ModelAndView("poPaymentHeadFullList");
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/poPaymentCustClear", method = RequestMethod.GET)
	public ModelAndView poPaymentCustClear(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

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

			List<Correspondent> corrList = correspondentDAO.getCorrespondenttByPoKey(purchase.getPoKey()).getResultList();

			if (corrList != null && corrList.size() > 0) {
				model.put("corrList", corrList);
			}

			model.put("user", user);
			model.put("letterOfCreditForm", letterOfCreditForm);
		} else {
			model.put("user", user);
			attributes.addFlashAttribute("success", "Letter of Credit is not uploaded, Please Check Type Of Transfer");
			return new ModelAndView("redirect:poPaymentHeadFullList");
		}
		model.put("user", user);
		return new ModelAndView("poPaymentCustClear", "model", model);

	}

	@RequestMapping(value = "/requestMoneyOnInvoice", method = RequestMethod.GET)
	public ModelAndView requestMoneyOnInvoice(ModelMap model) {

		/* Dispute Logic */

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getInvoiceListForMnyRequest(user.getUserName()).getResultList();

		if (invoiceList != null && invoiceList.size() > 0) {

			model.put("invoiceList", invoiceList);
			return new ModelAndView("requestMoneyOnInvoice", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/invoiceMoneyReq", method = RequestMethod.GET)
	public ModelAndView invoiceMoneyReq(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Invoice invoice = invoiceDAO.getByInvoiceId(id);

		invoice.setRequestMoney("Yes");
		invoiceDAO.updateInvoice(invoice);

		model.put("user", user);

		attributes.addFlashAttribute("success", "Sent Successfully");
		return new ModelAndView("redirect:requestMoneyOnInvoice");

	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@RequestMapping(value = "/requestMoneyInvoiceHeadFullList", method = RequestMethod.GET)
	public ModelAndView requestMoneyInvoiceHeadFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getInvoiceListByBranchName(user.getUserName()).getResultList();

		if (invoiceList != null && invoiceList.size() > 0) {

			model.put("invoiceList", invoiceList);
			return new ModelAndView("requestMoneyInvoiceHeadFullList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/requestMoneyAccepted", method = RequestMethod.GET)
	public ModelAndView requestMoneyAccepted(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getInvoiceListForMnyRequestAndAccept(user.getUserName()).getResultList();

		if (invoiceList != null && invoiceList.size() > 0) {

			model.put("invoiceList", invoiceList);
			return new ModelAndView("requestMoneyAccepted", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/requestMoneyAcceptOrNo", method = RequestMethod.GET)
	public ModelAndView requestMoneyAcceptOrNo(@RequestParam Long id, ModelMap model) {

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

		return new ModelAndView("requestMoneyAcceptOrNo", "model", model);

	}

	@RequestMapping(value = "/requestMoneyAcceptOrNoConfirm", method = RequestMethod.POST)
	public ModelAndView requestedInvoiceSetRatePost(ModelMap model, @ModelAttribute InvoiceForm invoiceForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		invoiceForm.setId(invoiceForm.getId());
		invoiceForm.setCustomerName(invoiceForm.getCustomerName());
		invoiceForm.setBuyerName(invoiceForm.getBuyerName());
		invoiceForm.setPoKey(invoiceForm.getPoKey());
		invoiceForm.setTenure(invoiceForm.getTenure());
		invoiceForm.setAmount(invoiceForm.getAmount());
		invoiceForm.setFunalAmt(invoiceForm.getFunalAmt());
		invoiceForm.setTransactionId(invoiceForm.getTransactionId());
		invoiceForm.setRequestMoney(invoiceForm.getRequestMoney());

		model.put("invoiceForm", invoiceForm);
		model.put("user", user);

		return new ModelAndView("requestMoneyAcceptOrNoConfirm", "model", model);

	}

	@RequestMapping(value = "/requestMoneyAcceptOrNoPost", method = RequestMethod.POST)
	public ModelAndView requestedInvoiceAppMngStatusPost(ModelMap model, @ModelAttribute InvoiceForm invoiceForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		Invoice invoice = invoiceDAO.getByInvoiceId(invoiceForm.getId());

		invoiceForm.setTransactionId(invoiceForm.getTransactionId());
		invoice.setTransStatus(invoiceForm.getTransStatus());
		invoice.setRequestMoney(invoiceForm.getTransStatus());

		invoiceDAO.updateInvoice(invoice);

		Transaction trans = new Transaction();

		trans.setTransactionId(purchaseOrderForm.getTransactionId());
		trans.setTransactionStatus("Accept/Reject");
		trans.setTransactionType("Submitted Successfully");

		transcationDAOImpl.insertTransaction(trans);

		model.put("invoiceForm", invoiceForm);
		model.put("user", user);

		return new ModelAndView("requestMoneyAcceptOrNoTrans", "model", model);

	}

	@RequestMapping(value = "/requestMoneyAcceptOrNoTrans", method = RequestMethod.GET)
	public ModelAndView requestedInvoiceAppMngStatusTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("requestMoneyAcceptOrNoTrans", "model", model);

	}

	@RequestMapping(value = "/closePoList", method = RequestMethod.GET)
	public ModelAndView closePoList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> purchaseList = purchaseOrderDAO.getPoPaymentCleared(user.getUserName()).getResultList();

		if (purchaseList != null && purchaseList.size() > 0) {
			model.put("purchaseList", purchaseList);
			model.put("supplierForm", supplierForm);
			return new ModelAndView("closePoList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/closePo", method = RequestMethod.GET)
	public String closePo(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(id);

		if (purchase != null) {

			purchase.setStatus("Closed");
			purchase.setvStatus("Closed");
			purchase.setTransStatus("Closed");
			purchase.setTransResult("Closed");
			Date closeDate = DateService.loginDate;
			purchase.setClosePoDate(closeDate);
			purchaseOrderDAO.updatePo(purchase);
		}
		attributes.addFlashAttribute("success", "Closed Successfully");
		model.put("user", user);

		return "redirect:closePoList";
	}

	@RequestMapping(value = "/cancelPoList", method = RequestMethod.GET)
	public ModelAndView cancelPoList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> purchaseList = purchaseOrderDAO.getPoListForPayment(user.getUserName()).getResultList();

		if (purchaseList != null && purchaseList.size() > 0) {
			model.put("purchaseList", purchaseList);
			model.put("supplierForm", supplierForm);
			return new ModelAndView("cancelPoList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/cancelPo", method = RequestMethod.GET)
	public String cancelPo(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(id);

		if (purchase != null) {

			purchase.setStatus("Cancel");

			purchaseOrderDAO.updatePo(purchase);

		}

		attributes.addFlashAttribute("success", "Cancelled Successfully");
		model.put("user", user);

		return "redirect:cancelPoList";
	}

	@RequestMapping(value = "/closeinvoiceList", method = RequestMethod.GET)
	public ModelAndView closeinvoiceList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getInvoiceListForClosing(user.getUserName()).getResultList();

		if (invoiceList != null && invoiceList.size() > 0) {
			model.put("invoiceList", invoiceList);
			model.put("newBuyerForm", newBuyerForm);
			return new ModelAndView("closeinvoiceList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/closeInvoice", method = RequestMethod.GET)
	public String closeInvoice(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Invoice invoice = invoiceDAO.getByInvoiceId(id);

		if (invoice != null) {

			invoice.setStatus("Closed");
			invoice.setTransStatus("Closed");
			invoice.setTransResult("Closed");
			invoice.setRequestMoney("Closed");
			Date closeDate = DateService.loginDate;
			invoice.setCloseDate(closeDate);

			invoiceDAO.updateInvoice(invoice);
		}
		attributes.addFlashAttribute("success", "Closed Successfully");
		model.put("user", user);

		return "redirect:closeinvoiceList";
	}

	/* Same bank events for buyer side */

	@RequestMapping(value = "/newEvent", method = RequestMethod.GET)
	public ModelAndView createNewEvent(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView();

		EndUser user = getCurrentLoggedUserDetails();

		List<PurchaseOrder> purchaseOrderList = purchaseOrderDAO.getPoForEvents(user.getUserName()).getResultList();

		newEventForm.setPurchaseOrderList(purchaseOrderList);

		if (purchaseOrderList.size() == 0) {
			model.put("user", user);
			mav = new ModelAndView("noDataFoundInCus", "model", model);
		} else {

			model.put("user", user);

			model.put("newEventForm", newEventForm);

			mav = new ModelAndView("newEvent", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/eventForPo", method = RequestMethod.POST)
	public ModelAndView supplierSameBank(ModelMap model, @ModelAttribute NewEventForm newEventForm, HttpServletRequest request, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		List<BuyerSameBankEvent> buyerSameBankEvents = buyerSameBankeventDAO.findPoKey(newEventForm.getPoKey()).getResultList();

		List<BuyerDiffBankEvent> buyerDiffBankEvents = buyerDiffBankEventDAO.findPoKey(newEventForm.getPoKey()).getResultList();

		PurchaseOrder purchaseOrder = purchaseOrderDAO.getPoData(newEventForm.getPoKey()).getSingleResult();

		CustomerBankDetails customerBankDetail = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0);

		model.put("customerBankName",customerBankDetail.getBankName());

		if (buyerSameBankEvents != null && buyerSameBankEvents.size() > 0 || buyerDiffBankEvents != null && buyerDiffBankEvents.size() > 0) {
			attributes.addFlashAttribute("success", "Purchase Key is already existed in events table");
			model.put("user", user);
			mav = new ModelAndView("redirect:newEvent");

		} else if (purchaseOrder.getSupplierBank().equals(customerBankDetail.getBankName())) {
			MasterPlan masterPlan = masterPlanDAO.getMasterPlanByMasterKey(purchaseOrder.getMasterKey()).getSingleResult();
			float availableCost = masterPlan.getBuyingCostSanc() - masterPlan.getUtilizedBusnsAmt();
			buyerSameBankEventForm.setAvailableCost(availableCost);
			model.put("purchaseOrder", purchaseOrder);
			model.put("user", user);
			model.put("masterPlan", masterPlan);
			model.put("buyerSameBankEventForm", buyerSameBankEventForm);

			mav = new ModelAndView("buyerEventForm", "model", model);

		} else {
			MasterPlan masterPlan = masterPlanDAO.getMasterPlanByMasterKey(purchaseOrder.getMasterKey()).getSingleResult();
			float availableCost = masterPlan.getBuyingCostSanc() - masterPlan.getUtilizedBusnsAmt();
			buyerDiffBankEventForm.setAvailableCost(availableCost);
			model.put("purchaseOrder", purchaseOrder);
			model.put("user", user);
			model.put("masterPlan", masterPlan);
			model.put("buyerDiffBankEventForm", buyerDiffBankEventForm);

			mav = new ModelAndView("buyerDiffEventForm", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/confirmBuyerSameBankEvent", method = RequestMethod.POST)
	public ModelAndView confirmBuyerSameBankEvent(ModelMap model, @ModelAttribute BuyerSameBankEventForm buyerSameBankEventForm, HttpServletRequest request, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("buyerSameBankEventForm", buyerSameBankEventForm);

		return new ModelAndView("confirmBuyerEventForm", "model", model);

	}

	@RequestMapping(value = "/insertBSameEvents", method = RequestMethod.POST)
	public ModelAndView insertSameBankEvents(ModelMap model, @ModelAttribute(value = "buyerSameBankEventForm") BuyerSameBankEventForm buyerSameBankEventForm, BindingResult result, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		BuyerSameBankEvent buyerSameBankEvent = new BuyerSameBankEvent();
		buyerSameBankEventForm.setTransactionId(KeyGenerator.generateTransactionKey());

		Transaction transaction = new Transaction();

		buyerSameBankEvent.setCustomerName(buyerSameBankEventForm.getCustomerName());
		buyerSameBankEvent.setSupplier(buyerSameBankEventForm.getSupplier());
		buyerSameBankEvent.setSupplierBank(buyerSameBankEventForm.getSupplierBank());
		buyerSameBankEvent.setMasterKey(buyerSameBankEventForm.getMasterKey());
		buyerSameBankEvent.setTransactionId(buyerSameBankEventForm.getTransactionId());
		buyerSameBankEvent.setGoods(buyerSameBankEventForm.getGoods());
		buyerSameBankEvent.setSanctionedAmount(buyerSameBankEventForm.getSanctionedAmount());
		buyerSameBankEvent.setUtilizedAmount(buyerSameBankEventForm.getUtilizedAmount());
		buyerSameBankEvent.setAvailableCost(buyerSameBankEventForm.getAvailableCost());
		buyerSameBankEvent.setDate1(buyerSameBankEventForm.getDate1());
		buyerSameBankEvent.setEvent1(buyerSameBankEventForm.getEvent1());
		buyerSameBankEvent.setFirst(buyerSameBankEventForm.getFirst());
		buyerSameBankEvent.setDate2(buyerSameBankEventForm.getDate2());
		buyerSameBankEvent.setEvent2(buyerSameBankEventForm.getEvent2());
		buyerSameBankEvent.setSecond(buyerSameBankEventForm.getSecond());
		buyerSameBankEvent.setDate3(buyerSameBankEventForm.getDate3());
		buyerSameBankEvent.setEvent3(buyerSameBankEventForm.getEvent3());
		buyerSameBankEvent.setThird(buyerSameBankEventForm.getThird());
		buyerSameBankEvent.setDate4(buyerSameBankEventForm.getDate4());
		buyerSameBankEvent.setEvent4(buyerSameBankEventForm.getEvent4());
		buyerSameBankEvent.setFourth(buyerSameBankEventForm.getFourth());
		buyerSameBankEvent.setDate5(buyerSameBankEventForm.getDate5());
		buyerSameBankEvent.setEvent5(buyerSameBankEventForm.getEvent5());
		buyerSameBankEvent.setFifth(buyerSameBankEventForm.getFifth());
		buyerSameBankEvent.setDate6(buyerSameBankEventForm.getDate6());
		buyerSameBankEvent.setEvent6(buyerSameBankEventForm.getEvent6());
		buyerSameBankEvent.setSix(buyerSameBankEventForm.getSix());
		buyerSameBankEvent.setDate7(buyerSameBankEventForm.getDate7());
		buyerSameBankEvent.setEvent7(buyerSameBankEventForm.getEvent7());
		buyerSameBankEvent.setSeven(buyerSameBankEventForm.getSeven());
		buyerSameBankEvent.setDate8(buyerSameBankEventForm.getDate8());
		buyerSameBankEvent.setEvent8(buyerSameBankEventForm.getEvent8());
		buyerSameBankEvent.setEight(buyerSameBankEventForm.getEight());
		buyerSameBankEvent.setDate9(buyerSameBankEventForm.getDate9());
		buyerSameBankEvent.setEvent9(buyerSameBankEventForm.getEvent9());
		buyerSameBankEvent.setNine(buyerSameBankEventForm.getNine());
		buyerSameBankEvent.setDate10(buyerSameBankEventForm.getDate10());
		buyerSameBankEvent.setEvent10(buyerSameBankEventForm.getEvent10());
		buyerSameBankEvent.setTen(buyerSameBankEventForm.getTen());
		buyerSameBankEvent.setDate11(buyerSameBankEventForm.getDate11());
		buyerSameBankEvent.setEvent11(buyerSameBankEventForm.getEvent11());
		buyerSameBankEvent.setElven(buyerSameBankEventForm.getElven());

		buyerSameBankEvent.setStatus("NA");

		buyerSameBankEvent.setPoKey(buyerSameBankEventForm.getPoKey());

		transaction.setTransactionId(buyerSameBankEventForm.getTransactionId());

		transaction.setTransactionType("Buyer Same Bank Events");

		transaction.setTransactionStatus("Details saved successfully");

		transactionDAO.insertTransaction(transaction);

		buyerSameBankeventDAO.insertSameBankEvents(buyerSameBankEvent);

		String allDocs = buyerSameBankEventForm.getDocName();
		String[] docs = allDocs.split(",");

		String allDescription = buyerSameBankEventForm.getDescription();
		String[] description = allDescription.split(",");

		List<PurchaseDocForm> purchaseDocForms = new ArrayList<PurchaseDocForm>();
		for (int count = 0; count < docs.length; count++) {

			PurchaseDocForm purchaseDocForm = new PurchaseDocForm();
			purchaseDocForm.setPoKey(buyerSameBankEventForm.getPoKey());
			purchaseDocForm.setDocName(docs[count]);

			purchaseDocForm.setDescription(description[count]);

			purchaseDocForms.add(purchaseDocForm);
		}
		for (PurchaseDocForm value : purchaseDocForms) {

			PurchaseDoc purchaseDoc = new PurchaseDoc();

			purchaseDoc.setPoKey(value.getPoKey());

			purchaseDoc.setDocName(value.getDocName());

			purchaseDoc.setDescription(value.getDescription());

			purchaseDoc.setTransactionId(buyerSameBankEventForm.getTransactionId());

			purchaseDocDAO.createDoc(purchaseDoc);
		}

		model.put("buyerSameBankEventForm", buyerSameBankEventForm);

		return new ModelAndView("savedSuccess", "model", model);
	}

	@RequestMapping(value = "/viewAllEvents", method = RequestMethod.GET)
	public ModelAndView showAllBEvents(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<BuyerSameBankEvent> buyerSameBankEvents = buyerSameBankeventDAO.findUpdateEvents(user.getUserName()).getResultList();

		List<BuyerSameBankEvent> buyerSameBankEvents1 = buyerSameBankeventDAO.findRejStatus(user.getUserName()).getResultList();

		List<BuyerSameBankEvent> buyerSameBankEvents2 = buyerSameBankeventDAO.findStatus(user.getUserName()).getResultList();

		ModelAndView mav = new ModelAndView();

		if (buyerSameBankEvents != null && buyerSameBankEvents.size() > 0) {
			model.put("buyerSameBankEvents", buyerSameBankEvents);

			mav = new ModelAndView("updateSameBankEvents", "model", model);
		} else if (buyerSameBankEvents1 != null && buyerSameBankEvents1.size() > 0) {

			model.put("buyerSameBankEvents", buyerSameBankEvents1);

			mav = new ModelAndView("updateSameBankEvents", "model", model);

		} else if (buyerSameBankEvents2 != null && buyerSameBankEvents2.size() > 0) {

			model.put("buyerSameBankEvents", buyerSameBankEvents2);

			mav = new ModelAndView("updateSameBankEvents", "model", model);

		} else {

			mav = new ModelAndView("noDataFoundInCus", "model", model);

		}

		return mav;

	}

	@RequestMapping(value = "/selectUpdateSameBankEvents", method = RequestMethod.GET)
	public ModelAndView selectUpdateSameBankEvents(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		BuyerSameBankEvent buyerSameBankEvent = buyerSameBankeventDAO.findEvent(id);

		buyerSameBankEventForm.setId(buyerSameBankEvent.getId());
		buyerSameBankEventForm.setCustomerName(buyerSameBankEvent.getCustomerName());
		buyerSameBankEventForm.setSupplier(buyerSameBankEvent.getSupplier());
		buyerSameBankEventForm.setSupplierBank(buyerSameBankEvent.getSupplierBank());
		buyerSameBankEventForm.setMasterKey(buyerSameBankEvent.getMasterKey());
		buyerSameBankEventForm.setPoKey(buyerSameBankEvent.getPoKey());
		buyerSameBankEventForm.setSanctionedAmount(buyerSameBankEvent.getSanctionedAmount());
		buyerSameBankEventForm.setUtilizedAmount(buyerSameBankEvent.getUtilizedAmount());
		buyerSameBankEventForm.setAvailableCost(buyerSameBankEvent.getAvailableCost());
		buyerSameBankEventForm.setGoods(buyerSameBankEvent.getGoods());
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

		model.put("buyerSameBankEventForm", buyerSameBankEventForm);

		return new ModelAndView("selectBuyerUpdateEvents", "model", model);

	}

	@RequestMapping(value = "/confirmSelectUpdateSameBankEvents", method = RequestMethod.POST)
	public ModelAndView confirmSelectUpdateSameBankEvents(ModelMap model, @ModelAttribute BuyerSameBankEventForm buyerSameBankEventForm, HttpServletRequest request, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<PurchaseDoc> purchaseDocs = purchaseDocDAO.findPoKey(buyerSameBankEventForm.getPoKey()).getResultList();
		List<PurchaseDocForm> purchaseDocForms = new ArrayList<PurchaseDocForm>();

		for (PurchaseDoc value : purchaseDocs) {

			PurchaseDocForm purchaseDocForm = new PurchaseDocForm();
			purchaseDocForm.setPoKey(value.getPoKey());
			purchaseDocForm.setDocName(value.getDocName());

			purchaseDocForm.setDescription(value.getDescription());

			purchaseDocForms.add(purchaseDocForm);
		}

		model.put("purchaseDocForms", purchaseDocForms);
		model.put("buyerSameBankEventForm", buyerSameBankEventForm);

		return new ModelAndView("confirmSelectUpdateSameBankEvents", "model", model);

	}

	@RequestMapping(value = "/selectUpdateSameBankEvents", method = RequestMethod.POST)
	public ModelAndView selectUpdateSameBankEvents(ModelMap model, @ModelAttribute BuyerSameBankEventForm buyerSameBankEventForm, BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();


		buyerSameBankEventForm.setTransactionId(KeyGenerator.generateTransactionKey());

		model.put("user", user);

		Transaction transaction = new Transaction();

		BuyerSameBankEvent buyerSameBankEvent = buyerSameBankeventDAO.findEvent(buyerSameBankEventForm.getId());

		buyerSameBankEvent.setId(buyerSameBankEventForm.getId());
		buyerSameBankEvent.setCustomerName(buyerSameBankEventForm.getCustomerName());
		buyerSameBankEvent.setSupplier(buyerSameBankEventForm.getSupplier());
		buyerSameBankEvent.setSupplierBank(buyerSameBankEventForm.getSupplierBank());
		buyerSameBankEvent.setMasterKey(buyerSameBankEventForm.getMasterKey());
		buyerSameBankEvent.setGoods(buyerSameBankEventForm.getGoods());
		buyerSameBankEvent.setDate1(buyerSameBankEventForm.getDate1());
		buyerSameBankEvent.setEvent1(buyerSameBankEventForm.getEvent1());
		buyerSameBankEvent.setFirst(buyerSameBankEventForm.getFirst());
		buyerSameBankEvent.setDate2(buyerSameBankEventForm.getDate2());
		buyerSameBankEvent.setEvent2(buyerSameBankEventForm.getEvent2());
		buyerSameBankEvent.setSecond(buyerSameBankEventForm.getSecond());
		buyerSameBankEvent.setDate3(buyerSameBankEventForm.getDate3());
		buyerSameBankEvent.setEvent3(buyerSameBankEventForm.getEvent3());
		buyerSameBankEvent.setThird(buyerSameBankEventForm.getThird());
		buyerSameBankEvent.setDate4(buyerSameBankEventForm.getDate4());
		buyerSameBankEvent.setEvent4(buyerSameBankEventForm.getEvent4());
		buyerSameBankEvent.setFourth(buyerSameBankEventForm.getFourth());
		buyerSameBankEvent.setDate5(buyerSameBankEventForm.getDate5());
		buyerSameBankEvent.setEvent5(buyerSameBankEventForm.getEvent5());
		buyerSameBankEvent.setFifth(buyerSameBankEventForm.getFifth());
		buyerSameBankEvent.setDate6(buyerSameBankEventForm.getDate6());
		buyerSameBankEvent.setEvent6(buyerSameBankEventForm.getEvent6());
		buyerSameBankEvent.setSix(buyerSameBankEventForm.getSix());
		buyerSameBankEvent.setDate7(buyerSameBankEventForm.getDate7());
		buyerSameBankEvent.setEvent7(buyerSameBankEventForm.getEvent7());
		buyerSameBankEvent.setSeven(buyerSameBankEventForm.getSeven());
		buyerSameBankEvent.setDate8(buyerSameBankEventForm.getDate8());
		buyerSameBankEvent.setEvent8(buyerSameBankEventForm.getEvent8());
		buyerSameBankEvent.setEight(buyerSameBankEventForm.getEight());
		buyerSameBankEvent.setDate9(buyerSameBankEventForm.getDate9());
		buyerSameBankEvent.setEvent9(buyerSameBankEventForm.getEvent9());
		buyerSameBankEvent.setNine(buyerSameBankEventForm.getNine());
		buyerSameBankEvent.setDate10(buyerSameBankEventForm.getDate10());
		buyerSameBankEvent.setEvent10(buyerSameBankEventForm.getEvent10());
		buyerSameBankEvent.setTen(buyerSameBankEventForm.getTen());
		buyerSameBankEvent.setDate11(buyerSameBankEventForm.getDate11());
		buyerSameBankEvent.setEvent11(buyerSameBankEventForm.getEvent11());
		buyerSameBankEvent.setElven(buyerSameBankEventForm.getElven());

		buyerSameBankEvent.setStatus("NA");

		buyerSameBankEvent.setTransactionId(buyerSameBankEventForm.getTransactionId());

		transaction.setTransactionId(buyerSameBankEventForm.getTransactionId());

		transaction.setTransactionType("Buyer Same Bank Events");

		transaction.setTransactionStatus("updated successfully");

		transactionDAO.insertTransaction(transaction);

		buyerSameBankeventDAO.updateBuyerSameBankEvent(buyerSameBankEvent);

		if (buyerSameBankEventForm.getDocName().equals("")) {
		} else {
			String allDocs = buyerSameBankEventForm.getDocName();
			String[] docs = allDocs.split(",");

			String allDescription = buyerSameBankEventForm.getDescription();
			String[] description = allDescription.split(",");

			List<PurchaseDocForm> purchaseDocForms = new ArrayList<PurchaseDocForm>();
			for (int count = 0; count < docs.length; count++) {

				PurchaseDocForm purchaseDocForm = new PurchaseDocForm();
				purchaseDocForm.setPoKey(buyerSameBankEventForm.getPoKey());
				purchaseDocForm.setDocName(docs[count]);

				purchaseDocForm.setDescription(description[count]);

				purchaseDocForms.add(purchaseDocForm);
			}
			for (PurchaseDocForm value : purchaseDocForms) {

				PurchaseDoc purchaseDoc = new PurchaseDoc();

				purchaseDoc.setPoKey(value.getPoKey());

				purchaseDoc.setDocName(value.getDocName());

				purchaseDoc.setDescription(value.getDescription());

				purchaseDoc.setTransactionId(buyerSameBankEventForm.getTransactionId());

				purchaseDocDAO.createDoc(purchaseDoc);
			}
		}

		model.put("buyerSameBankEventForm", buyerSameBankEventForm);

		return new ModelAndView("updateSuccess", "model", model);

	}

	@RequestMapping(value = "/viewAllEvents3", method = RequestMethod.GET)
	public ModelAndView checkStatusEvents(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		List<BuyerSameBankEvent> buyerSameBankEvents = buyerSameBankeventDAO.findCheckStatus(user.getUserName()).getResultList();

		if (buyerSameBankEvents != null && buyerSameBankEvents.size() > 0) {

			model.put("buyerSameBankEvents", buyerSameBankEvents);

			mav = new ModelAndView("checkStatusEvents", "model", model);

		} else {
			mav = new ModelAndView("noDataFoundInCus", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/selectEventsForCheckStatus", method = RequestMethod.GET)
	public ModelAndView selectEventsForCheckStatus(ModelMap model, @RequestParam("id") Long id, HttpServletRequest request, RedirectAttributes attributes) throws ParseException {

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
			median = Math.round((arrayList.get((arrayList.size() / 2)) + arrayList.get((arrayList.size() / 2 - 1))) / 2);
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

		return new ModelAndView("selectEventsForCheckStatus", "model", model);

	}

	/* Buyer Different Bank Events */

	@RequestMapping(value = "/confirmBuyerDiffBankEvent", method = RequestMethod.POST)
	public ModelAndView confirmBuyerDiffBankEvent(ModelMap model, @ModelAttribute BuyerDiffBankEventForm buyerDiffBankEventForm, HttpServletRequest request, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("buyerDiffBankEventForm", buyerDiffBankEventForm);

		return new ModelAndView("confirmBuyerDiffEventForm", "model", model);

	}

	@RequestMapping(value = "/insertBDiffEvents", method = RequestMethod.POST)
	public ModelAndView insertDiffBankEvents(ModelMap model, @ModelAttribute BuyerDiffBankEventForm buyerDiffBankEventForm, BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		buyerDiffBankEventForm.setTransactionId(KeyGenerator.generateTransactionKey());

		model.put("user", user);

		BuyerDiffBankEvent buyerDiffBankEvent = new BuyerDiffBankEvent();

		Transaction transaction = new Transaction();

		buyerDiffBankEvent.setCustomerName(buyerDiffBankEventForm.getCustomerName());
		buyerDiffBankEvent.setSupplier(buyerDiffBankEventForm.getSupplier());
		buyerDiffBankEvent.setSupplierBank(buyerDiffBankEventForm.getSupplierBank());
		buyerDiffBankEvent.setMasterKey(buyerDiffBankEventForm.getMasterKey());
		buyerDiffBankEvent.setGoods(buyerDiffBankEventForm.getGoods());
		buyerDiffBankEvent.setSanctionedAmount(buyerDiffBankEventForm.getSanctionedAmount());
		buyerDiffBankEvent.setUtilizedAmount(buyerDiffBankEventForm.getUtilizedAmount());
		buyerDiffBankEvent.setAvailableCost(buyerDiffBankEventForm.getAvailableCost());
		buyerDiffBankEvent.setDate1(buyerDiffBankEventForm.getDate1());
		buyerDiffBankEvent.setEvent1(buyerDiffBankEventForm.getEvent1());
		buyerDiffBankEvent.setFirst(buyerDiffBankEventForm.getFirst());
		buyerDiffBankEvent.setDate2(buyerDiffBankEventForm.getDate2());
		buyerDiffBankEvent.setEvent2(buyerDiffBankEventForm.getEvent2());
		buyerDiffBankEvent.setSecond(buyerDiffBankEventForm.getSecond());
		buyerDiffBankEvent.setDate3(buyerDiffBankEventForm.getDate3());
		buyerDiffBankEvent.setEvent3(buyerDiffBankEventForm.getEvent3());
		buyerDiffBankEvent.setThird(buyerDiffBankEventForm.getThird());
		buyerDiffBankEvent.setDate4(buyerDiffBankEventForm.getDate4());
		buyerDiffBankEvent.setEvent4(buyerDiffBankEventForm.getEvent4());
		buyerDiffBankEvent.setFourth(buyerDiffBankEventForm.getFourth());
		buyerDiffBankEvent.setDate5(buyerDiffBankEventForm.getDate5());
		buyerDiffBankEvent.setEvent5(buyerDiffBankEventForm.getEvent5());
		buyerDiffBankEvent.setFifth(buyerDiffBankEventForm.getFifth());
		buyerDiffBankEvent.setDate6(buyerDiffBankEventForm.getDate6());
		buyerDiffBankEvent.setEvent6(buyerDiffBankEventForm.getEvent6());
		buyerDiffBankEvent.setSix(buyerDiffBankEventForm.getSix());
		buyerDiffBankEvent.setDate7(buyerDiffBankEventForm.getDate7());
		buyerDiffBankEvent.setEvent7(buyerDiffBankEventForm.getEvent7());
		buyerDiffBankEvent.setSeven(buyerDiffBankEventForm.getSeven());
		buyerDiffBankEvent.setDate8(buyerDiffBankEventForm.getDate8());
		buyerDiffBankEvent.setEvent8(buyerDiffBankEventForm.getEvent8());
		buyerDiffBankEvent.setEight(buyerDiffBankEventForm.getEight());
		buyerDiffBankEvent.setDate9(buyerDiffBankEventForm.getDate9());
		buyerDiffBankEvent.setEvent9(buyerDiffBankEventForm.getEvent9());
		buyerDiffBankEvent.setNine(buyerDiffBankEventForm.getNine());
		buyerDiffBankEvent.setDate10(buyerDiffBankEventForm.getDate10());
		buyerDiffBankEvent.setEvent10(buyerDiffBankEventForm.getEvent10());
		buyerDiffBankEvent.setTen(buyerDiffBankEventForm.getTen());
		buyerDiffBankEvent.setDate11(buyerDiffBankEventForm.getDate11());
		buyerDiffBankEvent.setEvent11(buyerDiffBankEventForm.getEvent11());
		buyerDiffBankEvent.setElven(buyerDiffBankEventForm.getElven());
		buyerDiffBankEvent.setDate12(buyerDiffBankEventForm.getDate12());
		buyerDiffBankEvent.setEvent12(buyerDiffBankEventForm.getEvent12());
		buyerDiffBankEvent.setTwelve(buyerDiffBankEventForm.getTwelve());
		buyerDiffBankEvent.setDate13(buyerDiffBankEventForm.getDate13());
		buyerDiffBankEvent.setEvent13(buyerDiffBankEventForm.getEvent13());
		buyerDiffBankEvent.setThirteen(buyerDiffBankEventForm.getThirteen());
		buyerDiffBankEvent.setTransactionId(buyerDiffBankEventForm.getTransactionId());
		buyerDiffBankEvent.setPoKey(buyerDiffBankEventForm.getPoKey());

		buyerDiffBankEvent.setStatus("NA");

		transaction.setTransactionId(buyerDiffBankEventForm.getTransactionId());

		transaction.setTransactionType("Buyer Different Bank Events");

		transaction.setTransactionStatus("Details saved successfully");

		transactionDAO.insertTransaction(transaction);

		buyerDiffBankEventDAO.insertDiffBankEvents(buyerDiffBankEvent);

		String allDocs = buyerDiffBankEventForm.getDocName();
		String[] docs = allDocs.split(",");

		String allDescription = buyerDiffBankEventForm.getDescription();
		String[] description = allDescription.split(",");

		List<PurchaseDocForm> purchaseDocForms = new ArrayList<PurchaseDocForm>();
		for (int count = 0; count < docs.length; count++) {

			PurchaseDocForm purchaseDocForm = new PurchaseDocForm();
			purchaseDocForm.setPoKey(buyerDiffBankEventForm.getPoKey());
			purchaseDocForm.setDocName(docs[count]);

			purchaseDocForm.setDescription(description[count]);

			purchaseDocForms.add(purchaseDocForm);
		}
		for (PurchaseDocForm value : purchaseDocForms) {

			PurchaseDoc purchaseDoc = new PurchaseDoc();

			purchaseDoc.setPoKey(value.getPoKey());

			purchaseDoc.setDocName(value.getDocName());

			purchaseDoc.setDescription(value.getDescription());

			purchaseDoc.setTransactionId(buyerDiffBankEventForm.getTransactionId());

			purchaseDocDAO.createDoc(purchaseDoc);
		}

		model.put("buyerDiffBankEventForm", buyerDiffBankEventForm);

		return new ModelAndView("savedDiffSuccess", "model", model);

	}

	@RequestMapping(value = "/buyerDiffViewAllEvents", method = RequestMethod.GET)
	public ModelAndView showBuyerDiffViewAllEvents(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<BuyerDiffBankEvent> buyerDiffBankEvents = buyerDiffBankEventDAO.findUpdateEvents(user.getUserName()).getResultList();

		List<BuyerDiffBankEvent> buyerDiffBankEvents1 = buyerDiffBankEventDAO.findRejStatus(user.getUserName()).getResultList();

		List<BuyerDiffBankEvent> buyerDiffBankEvents2 = buyerDiffBankEventDAO.findStatus(user.getUserName()).getResultList();

		ModelAndView mav = new ModelAndView();

		if (buyerDiffBankEvents != null && buyerDiffBankEvents.size() > 0) {
			model.put("buyerDiffBankEvents", buyerDiffBankEvents);

			mav = new ModelAndView("updateDiffBankEvents", "model", model);
		} else if (buyerDiffBankEvents1 != null && buyerDiffBankEvents1.size() > 0) {

			model.put("buyerDiffBankEvents", buyerDiffBankEvents1);

			mav = new ModelAndView("updateDiffBankEvents", "model", model);

		} else if (buyerDiffBankEvents2 != null && buyerDiffBankEvents2.size() > 0) {

			model.put("buyerDiffBankEvents", buyerDiffBankEvents2);

			mav = new ModelAndView("updateDiffBankEvents", "model", model);

		} else {

			mav = new ModelAndView("noDataFoundInCus", "model", model);

		}

		return mav;

	}

	@RequestMapping(value = "/selectUpdateDiffBankEvents", method = RequestMethod.GET)
	public ModelAndView selectUpdateSameDiffEvents(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

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

		buyerDiffBankEventForm.setId(buyerDiffBankEvent.getId());
		buyerDiffBankEventForm.setCustomerName(buyerDiffBankEvent.getCustomerName());
		buyerDiffBankEventForm.setSupplier(buyerDiffBankEvent.getSupplier());
		buyerDiffBankEventForm.setSupplierBank(buyerDiffBankEvent.getSupplierBank());
		buyerDiffBankEventForm.setMasterKey(buyerDiffBankEvent.getMasterKey());
		buyerDiffBankEventForm.setPoKey(buyerDiffBankEvent.getPoKey());
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

		return new ModelAndView("selectBuyerDiffUpdateEvents", "model", model);

	}

	@RequestMapping(value = "/confirmSelectUpdateDiffBankEvents", method = RequestMethod.POST)
	public ModelAndView confirmSelectUpdateDiffBankEvents(ModelMap model, BuyerDiffBankEventForm buyerDiffBankEventForm, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		List<PurchaseDoc> purchaseDocs = purchaseDocDAO.findPoKey(buyerDiffBankEventForm.getPoKey()).getResultList();
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

		model.put("buyerDiffBankEventForm", buyerDiffBankEventForm);

		return new ModelAndView("confirmSelectBuyerDiffUpdateEvents", "model", model);
	}

	@RequestMapping(value = "/selectUpdateDiffBankEvents", method = RequestMethod.POST)
	public ModelAndView selectUpdateSameDiffEvents(ModelMap model, @ModelAttribute BuyerDiffBankEventForm buyerDiffBankEventForm, BindingResult result, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		BuyerDiffBankEvent buyerDiffBankEvent = buyerDiffBankEventDAO.findEvent(buyerDiffBankEventForm.getId());

		buyerDiffBankEventForm.setTransactionId(KeyGenerator.generateTransactionKey());

		Transaction transaction = new Transaction();

		buyerDiffBankEvent.setId(buyerDiffBankEventForm.getId());
		buyerDiffBankEvent.setCustomerName(buyerDiffBankEventForm.getCustomerName());
		buyerDiffBankEvent.setSupplier(buyerDiffBankEventForm.getSupplier());
		buyerDiffBankEvent.setSupplierBank(buyerDiffBankEventForm.getSupplierBank());
		buyerDiffBankEvent.setMasterKey(buyerDiffBankEventForm.getMasterKey());
		buyerDiffBankEvent.setGoods(buyerDiffBankEventForm.getGoods());
		buyerDiffBankEvent.setSanctionedAmount(buyerDiffBankEventForm.getSanctionedAmount());
		buyerDiffBankEvent.setUtilizedAmount(buyerDiffBankEventForm.getUtilizedAmount());
		buyerDiffBankEvent.setAvailableCost(buyerDiffBankEventForm.getAvailableCost());
		buyerDiffBankEvent.setDate1(buyerDiffBankEventForm.getDate1());
		buyerDiffBankEvent.setEvent1(buyerDiffBankEventForm.getEvent1());
		buyerDiffBankEvent.setFirst(buyerDiffBankEventForm.getFirst());
		buyerDiffBankEvent.setDate2(buyerDiffBankEventForm.getDate2());
		buyerDiffBankEvent.setEvent2(buyerDiffBankEventForm.getEvent2());
		buyerDiffBankEvent.setSecond(buyerDiffBankEventForm.getSecond());
		buyerDiffBankEvent.setDate3(buyerDiffBankEventForm.getDate3());
		buyerDiffBankEvent.setEvent3(buyerDiffBankEventForm.getEvent3());
		buyerDiffBankEvent.setThird(buyerDiffBankEventForm.getThird());
		buyerDiffBankEvent.setDate4(buyerDiffBankEventForm.getDate4());
		buyerDiffBankEvent.setEvent4(buyerDiffBankEventForm.getEvent4());
		buyerDiffBankEvent.setFourth(buyerDiffBankEventForm.getFourth());
		buyerDiffBankEvent.setDate5(buyerDiffBankEventForm.getDate5());
		buyerDiffBankEvent.setEvent5(buyerDiffBankEventForm.getEvent5());
		buyerDiffBankEvent.setFifth(buyerDiffBankEventForm.getFifth());
		buyerDiffBankEvent.setDate6(buyerDiffBankEventForm.getDate6());
		buyerDiffBankEvent.setEvent6(buyerDiffBankEventForm.getEvent6());
		buyerDiffBankEvent.setSix(buyerDiffBankEventForm.getSix());
		buyerDiffBankEvent.setDate7(buyerDiffBankEventForm.getDate7());
		buyerDiffBankEvent.setEvent7(buyerDiffBankEventForm.getEvent7());
		buyerDiffBankEvent.setSeven(buyerDiffBankEventForm.getSeven());
		buyerDiffBankEvent.setDate8(buyerDiffBankEventForm.getDate8());
		buyerDiffBankEvent.setEvent8(buyerDiffBankEventForm.getEvent8());
		buyerDiffBankEvent.setEight(buyerDiffBankEventForm.getEight());
		buyerDiffBankEvent.setDate9(buyerDiffBankEventForm.getDate9());
		buyerDiffBankEvent.setEvent9(buyerDiffBankEventForm.getEvent9());
		buyerDiffBankEvent.setNine(buyerDiffBankEventForm.getNine());
		buyerDiffBankEvent.setDate10(buyerDiffBankEventForm.getDate10());
		buyerDiffBankEvent.setEvent10(buyerDiffBankEventForm.getEvent10());
		buyerDiffBankEvent.setTen(buyerDiffBankEventForm.getTen());
		buyerDiffBankEvent.setDate11(buyerDiffBankEventForm.getDate11());
		buyerDiffBankEvent.setEvent11(buyerDiffBankEventForm.getEvent11());
		buyerDiffBankEvent.setElven(buyerDiffBankEventForm.getElven());
		buyerDiffBankEvent.setDate12(buyerDiffBankEventForm.getDate12());
		buyerDiffBankEvent.setEvent12(buyerDiffBankEventForm.getEvent12());
		buyerDiffBankEvent.setTwelve(buyerDiffBankEventForm.getTwelve());
		buyerDiffBankEvent.setDate13(buyerDiffBankEventForm.getDate13());
		buyerDiffBankEvent.setEvent13(buyerDiffBankEventForm.getEvent13());
		buyerDiffBankEvent.setThirteen(buyerDiffBankEventForm.getThirteen());

		buyerDiffBankEvent.setStatus("NA");

		buyerDiffBankEvent.setTransactionId(buyerDiffBankEventForm.getTransactionId());

		transaction.setTransactionId(buyerDiffBankEventForm.getTransactionId());

		transaction.setTransactionType("Buyer Different Bank Events");

		transaction.setTransactionStatus("updated successfully");

		transactionDAO.insertTransaction(transaction);

		buyerDiffBankEventDAO.updateBuyerDiffBankEvent(buyerDiffBankEvent);

		if (buyerDiffBankEventForm.getDocName().equals("")) {
		} else {
			String allDocs = buyerDiffBankEventForm.getDocName();
			String[] docs = allDocs.split(",");

			String allDescription = buyerDiffBankEventForm.getDescription();
			String[] description = allDescription.split(",");

			List<PurchaseDocForm> purchaseDocForms = new ArrayList<PurchaseDocForm>();
			for (int count = 0; count < docs.length; count++) {

				PurchaseDocForm purchaseDocForm = new PurchaseDocForm();
				purchaseDocForm.setPoKey(buyerDiffBankEventForm.getPoKey());
				purchaseDocForm.setDocName(docs[count]);

				purchaseDocForm.setDescription(description[count]);

				purchaseDocForms.add(purchaseDocForm);
			}
			for (PurchaseDocForm value : purchaseDocForms) {

				PurchaseDoc purchaseDoc = new PurchaseDoc();

				purchaseDoc.setPoKey(value.getPoKey());

				purchaseDoc.setDocName(value.getDocName());

				purchaseDoc.setDescription(value.getDescription());

				purchaseDoc.setTransactionId(buyerDiffBankEventForm.getTransactionId());

				purchaseDocDAO.createDoc(purchaseDoc);
			}
		}

		model.put("buyerDiffBankEventForm", buyerDiffBankEventForm);

		return new ModelAndView("updateDiffSuccess", "model", model);

	}

	@RequestMapping(value = "/buyerDiffViewAllEvents3", method = RequestMethod.GET)
	public ModelAndView checkStatusDiffEvents(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<BuyerDiffBankEvent> buyerDiffBankEvents = buyerDiffBankEventDAO.findCheckStatus(user.getUserName()).getResultList();

		ModelAndView mav = new ModelAndView();

		if (buyerDiffBankEvents != null && buyerDiffBankEvents.size() > 0) {

			model.put("buyerDiffBankEvents", buyerDiffBankEvents);

			mav = new ModelAndView("checkStatusDiffEvents", "model", model);

		} else {
			mav = new ModelAndView("noDataFoundInCus", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/selectDiffEventsForCheckStatus", method = RequestMethod.GET)
	public ModelAndView selectDiffEventsForCheckStatus(ModelMap model, @RequestParam("id") Long id, HttpServletRequest request, RedirectAttributes attributes) throws ParseException {

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
			median = Math.round((arrayList.get((arrayList.size() / 2)) + arrayList.get((arrayList.size() / 2 - 1))) / 2);
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

		mav = new ModelAndView("selectDiffEventsForCheckStatus", "model", model);

		return mav;
	}

	/* Comparison For PO events */

	@RequestMapping(value = "/comparisonPO", method = RequestMethod.GET)
	public ModelAndView comparisionCustomer(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		List<BuyerSameBankEvent> buyerSameBankEvents = buyerSameBankeventDAO.findCheckStatus(user.getUserName()).getResultList();
		List<BuyerDiffBankEvent> buyerDiffBankEvents = buyerDiffBankEventDAO.findCheckStatus(user.getUserName()).getResultList();

		newEventForm.setBuyerSameBankEvents(buyerSameBankEvents);
		newEventForm.setBuyerDiffBankEvents(buyerDiffBankEvents);

		if (buyerSameBankEvents.size() == 0 && buyerDiffBankEvents.size() == 0) {

			mav = new ModelAndView("noDataFoundInCus", "model", model);
		} else {

			model.put("user", user);

			model.put("newEventForm", newEventForm);

			mav = new ModelAndView("comparisonPO", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/comparisonData", method = RequestMethod.POST)
	public ModelAndView comparisionCustomer(ModelMap model, @ModelAttribute NewEventForm newEventForm, RedirectAttributes attributes) throws ParseException {

		String mav = "";

		List<ComparisonForm> comparisonList = new ArrayList<ComparisonForm>();

		PurchaseOrder purchaseOrder = purchaseOrderDAO.getPoListByPoKey(newEventForm.getPoKey()).getSingleResult();

		List<BuyerSameBankEvent> buyerSameBankEvents = buyerSameBankeventDAO.findPoKey(newEventForm.getPoKey()).getResultList();

		List<PurchaseDoc> purchaseDocForms = purchaseDocDAO.findPoKey(newEventForm.getPoKey()).getResultList();

		List<PoUpload> poUploadForms = poUploadDAO.getPoUploadByPOKey(newEventForm.getPoKey());

		if (buyerSameBankEvents != null && buyerSameBankEvents.size() > 0) {

			BuyerSameBankEvent buyerSameBankEvent = buyerSameBankEvents.get(0);

			buyerSameBankEventForm = eventsService.populateBuyerSameBankFormBasedOnEvents(buyerSameBankEvent, purchaseOrder);

			/* If there are any Disputes */

			List<Dispute> disputesList = disputeDAO.getPoKey(newEventForm.getPoKey()).getResultList();

			if (disputesList != null && disputesList.size() > 0) {
				comparisonList = eventsService.populateFormBasedOnDisputes(disputesList, buyerSameBankEvent);
			}

			/* FeedBack Module */

			buyerSameBankEventForm = eventsService.generateFeedback(buyerSameBankEventForm, buyerSameBankEvent, purchaseOrder);

			model.put("buyerSameBankEvent", buyerSameBankEvent);

			model.put("buyerSameBankEventForm", buyerSameBankEventForm);

			mav = "comparisonDataForSame";

		} else {
			List<BuyerDiffBankEvent> buyerDiffBankEventList = buyerDiffBankEventDAO.findPoKey(newEventForm.getPoKey()).getResultList();
			if (buyerDiffBankEventList != null && buyerDiffBankEventList.size() > 0) {
				BuyerDiffBankEvent buyerDiffBankEvent = buyerDiffBankEventList.get(0);

				buyerDiffBankEventForm = eventsService.populateBuyerDiffBankFormBasedOnEvents(buyerDiffBankEvent, purchaseOrder);

				/* If there are any Disputes */

				List<Dispute> disputesList = disputeDAO.getPoKey(newEventForm.getPoKey()).getResultList();

				if (disputesList != null && disputesList.size() > 0) {
					comparisonList = eventsService.populateDiffBankFormBasedOnDisputes(disputesList, buyerDiffBankEvent);
				}

				/* FeedBack Module */

				buyerDiffBankEventForm = eventsService.generateDiffBankFeedback(buyerDiffBankEventForm, buyerDiffBankEvent, purchaseOrder);

				model.put("buyerDiffBankEvent", buyerDiffBankEvent);

				model.put("buyerDiffBankEventForm", buyerDiffBankEventForm);

			}
			mav = "comparisonDataForDiff";
		}

		model.put("poUploadForms", poUploadForms);

		model.put("purchaseDocForms", purchaseDocForms);

		model.put("purchaseOrders", purchaseOrder);

		model.put("comparisonList", comparisonList);

		return new ModelAndView(mav, "model", model);
	}

	/* Same bank events for Seller side */

	@RequestMapping(value = "/sellerNewEvent", method = RequestMethod.GET)
	public ModelAndView createSellerNewEvent(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<Invoice> invoiceList = invoiceDAO.getInvoiceForEvents(user.getUserName()).getResultList();

		newEventForm.setInvoiceList(invoiceList);

		if (invoiceList.size() == 0) {

			mav = new ModelAndView("noDataFoundInCus", "model", model);
		} else {

			model.put("newEventForm", newEventForm);

			mav = new ModelAndView("newSellerSameEvent", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/eventsForInvoice", method = RequestMethod.POST)
	public ModelAndView eventsForInvoice(ModelMap model, @ModelAttribute NewEventForm newEventForm, HttpServletRequest request, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		List<SellerSameBankEvent> sellerSameBankEvents = sellerSameBankEventDAO.findInvoiceKey(newEventForm.getInvoiceKey()).getResultList();

		List<SellerDiffBankEvent> sellerDiffBankEvents = sellerDiffBankEventDAO.findInvoiceKey(newEventForm.getInvoiceKey()).getResultList();

		Invoice invoice = invoiceDAO.getInvoiceData(newEventForm.getInvoiceKey()).getSingleResult();

		CustomerBankDetails customerBankDetail = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0);

		model.put("customerBankName",customerBankDetail.getBankName());


		if (sellerSameBankEvents != null && sellerSameBankEvents.size() > 0 || sellerDiffBankEvents != null && sellerDiffBankEvents.size() > 0) {
			attributes.addFlashAttribute("success", "Invoice Key is already existed in events table");

			mav = new ModelAndView("redirect:sellerNewEvent");

		} else if (invoice.getBuyerBank().equals(customerBankDetail.getBankName())) {
			model.put("invoice", invoice);

			model.put("sellerSameBankEventForm", sellerSameBankEventForm);

			mav = new ModelAndView("sellerSameBankEvent", "model", model);

		} else {
			model.put("invoice", invoice);

			model.put("sellerDiffBankEventForm", sellerDiffBankEventForm);

			mav = new ModelAndView("sellerDiffBankEvent", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/confirmSellerSameBankEvent", method = RequestMethod.POST)
	public ModelAndView confirmSellerSameBankEvent(ModelMap model, @ModelAttribute SellerSameBankEventForm sellerSameBankEventForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("sellerSameBankEventForm", sellerSameBankEventForm);

		return new ModelAndView("confirmSellerSameBankEvent", "model", model);

	}

	@RequestMapping(value = "/insertSellerSameEvents", method = RequestMethod.POST)
	public ModelAndView insertSameBankEvents(ModelMap model, @ModelAttribute SellerSameBankEventForm sellerSameBankEventForm, BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		sellerSameBankEventForm.setTransactionId(KeyGenerator.generateTransactionKey());

		SellerSameBankEvent sellerSameBankEvent = new SellerSameBankEvent();

		Transaction transaction = new Transaction();

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
		sellerSameBankEvent.setDate12(sellerSameBankEventForm.getDate12());
		sellerSameBankEvent.setEvent12(sellerSameBankEventForm.getEvent12());
		sellerSameBankEvent.setTwelve(sellerSameBankEventForm.getTwelve());
		sellerSameBankEvent.setDate13(sellerSameBankEventForm.getDate13());
		sellerSameBankEvent.setEvent13(sellerSameBankEventForm.getEvent13());
		sellerSameBankEvent.setThirteen(sellerSameBankEventForm.getThirteen());
		sellerSameBankEvent.setTransactionId(sellerSameBankEventForm.getTransactionId());
		sellerSameBankEvent.setInvoiceKey(sellerSameBankEventForm.getInvoiceKey());

		sellerSameBankEvent.setStatus("NA");

		transaction.setTransactionId(sellerSameBankEventForm.getTransactionId());

		transaction.setTransactionType("Seller Same Bank Events");

		transaction.setTransactionStatus("Details saved successfully");

		transactionDAO.insertTransaction(transaction);

		sellerSameBankEventDAO.insertSameBankEvents(sellerSameBankEvent);

		String allDocs = sellerSameBankEventForm.getDocName();
		String[] docs = allDocs.split(",");

		String allDescription = sellerSameBankEventForm.getDescription();
		String[] description = allDescription.split(",");

		List<InvoiceDocForm> invoiceDocForms = new ArrayList<InvoiceDocForm>();
		for (int count = 0; count < docs.length; count++) {

			InvoiceDocForm invoiceDocForm = new InvoiceDocForm();
			invoiceDocForm.setInvoiceKey(sellerSameBankEventForm.getInvoiceKey());
			invoiceDocForm.setDocName(docs[count]);

			invoiceDocForm.setDescription(description[count]);

			invoiceDocForms.add(invoiceDocForm);
		}
		for (InvoiceDocForm value : invoiceDocForms) {

			InvoiceDoc invoiceDoc = new InvoiceDoc();

			invoiceDoc.setInvoiceKey(value.getInvoiceKey());

			invoiceDoc.setDocName(value.getDocName());

			invoiceDoc.setDescription(value.getDescription());

			invoiceDoc.setTransactionId(sellerSameBankEventForm.getTransactionId());

			invoiceDocDAO.createDoc(invoiceDoc);
		}

		model.put("sellerSameBankEventForm", sellerSameBankEventForm);

		return new ModelAndView("sendSellerSameSuccess", "model", model);

	}

	@RequestMapping(value = "/sellerSameViewAllEvents", method = RequestMethod.GET)
	public ModelAndView showSellerSameViewAllEvents(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<SellerSameBankEvent> sellerSameBankEvents = sellerSameBankEventDAO.findUpdateEvents(user.getUserName()).getResultList();

		List<SellerSameBankEvent> sellerSameBankEvents1 = sellerSameBankEventDAO.findRejStatus(user.getUserName()).getResultList();

		List<SellerSameBankEvent> sellerSameBankEvents2 = sellerSameBankEventDAO.findStatus(user.getUserName()).getResultList();

		ModelAndView mav = new ModelAndView();

		if (sellerSameBankEvents != null && sellerSameBankEvents.size() > 0) {
			model.put("sellerSameBankEvents", sellerSameBankEvents);

			mav = new ModelAndView("updateSellerSameBankEvents", "model", model);
		} else if (sellerSameBankEvents1 != null && sellerSameBankEvents1.size() > 0) {

			model.put("sellerSameBankEvents", sellerSameBankEvents1);

			mav = new ModelAndView("updateSellerSameBankEvents", "model", model);

		} else if (sellerSameBankEvents2 != null && sellerSameBankEvents2.size() > 0) {

			model.put("sellerSameBankEvents", sellerSameBankEvents2);

			mav = new ModelAndView("updateSellerSameBankEvents", "model", model);

		} else {

			mav = new ModelAndView("noDataFoundInCus", "model", model);

		}

		return mav;

	}

	@RequestMapping(value = "/selectUpdateSellerSameBankEvents", method = RequestMethod.GET)
	public ModelAndView selectUpdateSellerSameBankEvents(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

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
		sellerSameBankEventForm.setDate12(sellerSameBankEvent.getDate12());
		sellerSameBankEventForm.setEvent12(sellerSameBankEvent.getEvent12());
		sellerSameBankEventForm.setTwelve(sellerSameBankEvent.getTwelve());
		sellerSameBankEventForm.setDate13(sellerSameBankEvent.getDate13());
		sellerSameBankEventForm.setEvent13(sellerSameBankEvent.getEvent13());
		sellerSameBankEventForm.setThirteen(sellerSameBankEvent.getThirteen());

		model.put("sellerSameBankEventForm", sellerSameBankEventForm);

		return new ModelAndView("selectSellerSameUpdateEvents", "model", model);

	}

	@RequestMapping(value = "/confirmSelectUpdateSellerSameBankEvents", method = RequestMethod.POST)
	public ModelAndView confirmSelectUpdateSellerSameBankEvents(ModelMap model, @ModelAttribute SellerSameBankEventForm sellerSameBankEventForm) {

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

		return new ModelAndView("confirmSelectSellerSameUpdateEvents", "model", model);

	}

	@RequestMapping(value = "/selectUpdateSellerSameBankEvents", method = RequestMethod.POST)
	public ModelAndView selectUpdateSellerSameBankEvents(ModelMap model, @ModelAttribute SellerSameBankEventForm sellerSameBankEventForm, BindingResult result, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		SellerSameBankEvent sellerSameBankEvent = sellerSameBankEventDAO.findEvent(sellerSameBankEventForm.getId());


		sellerSameBankEventForm.setTransactionId(KeyGenerator.generateTransactionKey());

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
		sellerSameBankEvent.setDate12(sellerSameBankEventForm.getDate12());
		sellerSameBankEvent.setEvent12(sellerSameBankEventForm.getEvent12());
		sellerSameBankEvent.setTwelve(sellerSameBankEventForm.getTwelve());
		sellerSameBankEvent.setDate13(sellerSameBankEventForm.getDate13());
		sellerSameBankEvent.setEvent13(sellerSameBankEventForm.getEvent13());
		sellerSameBankEvent.setThirteen(sellerSameBankEventForm.getThirteen());

		sellerSameBankEvent.setStatus("NA");

		sellerSameBankEvent.setTransactionId(sellerSameBankEventForm.getTransactionId());

		transaction.setTransactionId(sellerSameBankEventForm.getTransactionId());

		transaction.setTransactionType("Seller Same Bank Events");

		transaction.setTransactionStatus("updated successfully");

		transactionDAO.insertTransaction(transaction);

		sellerSameBankEventDAO.updateSellerSameBankEvent(sellerSameBankEvent);

		if (sellerSameBankEventForm.getDocName().equals("")) {
		} else {
			String allDocs = sellerSameBankEventForm.getDocName();
			String[] docs = allDocs.split(",");

			String allDescription = sellerSameBankEventForm.getDescription();
			String[] description = allDescription.split(",");

			List<InvoiceDocForm> invoiceDocForms = new ArrayList<InvoiceDocForm>();
			for (int count = 0; count < docs.length; count++) {

				InvoiceDocForm invoiceDocForm = new InvoiceDocForm();
				invoiceDocForm.setInvoiceKey(sellerSameBankEventForm.getInvoiceKey());
				invoiceDocForm.setDocName(docs[count]);

				invoiceDocForm.setDescription(description[count]);

				invoiceDocForms.add(invoiceDocForm);
			}
			for (InvoiceDocForm value : invoiceDocForms) {

				InvoiceDoc invoiceDoc = new InvoiceDoc();

				invoiceDoc.setInvoiceKey(value.getInvoiceKey());

				invoiceDoc.setDocName(value.getDocName());

				invoiceDoc.setDescription(value.getDescription());

				invoiceDoc.setTransactionId(sellerSameBankEventForm.getTransactionId());

				invoiceDocDAO.createDoc(invoiceDoc);
			}
		}

		model.put("sellerSameBankEventForm", sellerSameBankEventForm);

		return new ModelAndView("updateSellerSameSuccess", "model", model);

	}

	@RequestMapping(value = "/sellerSameViewAllEvents3", method = RequestMethod.GET)
	public ModelAndView checkStatusSellerSameEvents(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		List<SellerSameBankEvent> sellerSameBankEvents = sellerSameBankEventDAO.findCheckStatus(user.getUserName()).getResultList();

		if (sellerSameBankEvents != null && sellerSameBankEvents.size() > 0) {

			model.put("sellerSameBankEvents", sellerSameBankEvents);

			mav = new ModelAndView("checkStatusSellerSameEvents", "model", model);

		} else {
			mav = new ModelAndView("noDataFoundInCus", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/selectSellerSameEventsForCheckStatus", method = RequestMethod.GET)
	public ModelAndView selectSellerSameEventsForCheckStatus(ModelMap model, @RequestParam("id") Long id, HttpServletRequest request, RedirectAttributes attributes) throws ParseException {

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
			median = Math.round((arrayList.get((arrayList.size() / 2)) + arrayList.get((arrayList.size() / 2 - 1))) / 2);
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

		return new ModelAndView("selectSellerSameEventsForCheckStatus", "model", model);

	}

	/* Different bank events for Seller side */

	@RequestMapping(value = "/confirmSellerDiffBankEvent", method = RequestMethod.POST)
	public ModelAndView confirmSellerDiffBankEvent(ModelMap model, @ModelAttribute SellerDiffBankEventForm sellerDiffBankEventForm, HttpServletRequest request, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("sellerDiffBankEventForm", sellerDiffBankEventForm);

		return new ModelAndView("confirmSellerDiffBankEvent", "model", model);

	}

	@RequestMapping(value = "/insertSellerDiffEvents", method = RequestMethod.POST)
	public ModelAndView insertDiffBankEvents(ModelMap model, @ModelAttribute SellerDiffBankEventForm sellerDiffBankEventForm, BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();


		sellerDiffBankEventForm.setTransactionId(KeyGenerator.generateTransactionKey());

		model.put("user", user);

		SellerDiffBankEvent sellerDiffBankEvent = new SellerDiffBankEvent();

		Transaction transaction = new Transaction();

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
		sellerDiffBankEvent.setDate12(sellerDiffBankEventForm.getDate12());
		sellerDiffBankEvent.setEvent12(sellerDiffBankEventForm.getEvent12());
		sellerDiffBankEvent.setTwelve(sellerDiffBankEventForm.getTwelve());
		sellerDiffBankEvent.setDate13(sellerDiffBankEventForm.getDate13());
		sellerDiffBankEvent.setEvent13(sellerDiffBankEventForm.getEvent13());
		sellerDiffBankEvent.setThirteen(sellerDiffBankEventForm.getThirteen());
		sellerDiffBankEvent.setDate14(sellerDiffBankEventForm.getDate14());
		sellerDiffBankEvent.setEvent14(sellerDiffBankEventForm.getEvent14());
		sellerDiffBankEvent.setFourteen(sellerDiffBankEventForm.getFourteen());

		sellerDiffBankEvent.setDate15(sellerDiffBankEventForm.getDate15());
		sellerDiffBankEvent.setEvent15(sellerDiffBankEventForm.getEvent15());
		sellerDiffBankEvent.setFifteen(sellerDiffBankEventForm.getFifteen());

		sellerDiffBankEvent.setDate16(sellerDiffBankEventForm.getDate16());
		sellerDiffBankEvent.setEvent16(sellerDiffBankEventForm.getEvent16());
		sellerDiffBankEvent.setSixteen(sellerDiffBankEventForm.getSixteen());

		sellerDiffBankEvent.setStatus("NA");

		sellerDiffBankEvent.setTransactionId(sellerDiffBankEventForm.getTransactionId());

		sellerDiffBankEvent.setInvoiceKey(sellerDiffBankEventForm.getInvoiceKey());

		sellerDiffBankEventDAO.insertDiffBankEvents(sellerDiffBankEvent);
		transaction.setTransactionId(sellerDiffBankEventForm.getTransactionId());

		transaction.setTransactionType("Seller Different Bank Events");

		transaction.setTransactionStatus("Details saved successfully");

		transactionDAO.insertTransaction(transaction);

		String allDocs = sellerDiffBankEventForm.getDocName();
		String[] docs = allDocs.split(",");

		String allDescription = sellerDiffBankEventForm.getDescription();
		String[] description = allDescription.split(",");

		List<InvoiceDocForm> invoiceDocForms = new ArrayList<InvoiceDocForm>();
		for (int count = 0; count < docs.length; count++) {

			InvoiceDocForm invoiceDocForm = new InvoiceDocForm();
			invoiceDocForm.setInvoiceKey(sellerDiffBankEventForm.getInvoiceKey());
			invoiceDocForm.setDocName(docs[count]);

			invoiceDocForm.setDescription(description[count]);

			invoiceDocForms.add(invoiceDocForm);
		}
		for (InvoiceDocForm value : invoiceDocForms) {

			InvoiceDoc invoiceDoc = new InvoiceDoc();

			invoiceDoc.setInvoiceKey(value.getInvoiceKey());

			invoiceDoc.setDocName(value.getDocName());

			invoiceDoc.setDescription(value.getDescription());

			invoiceDoc.setTransactionId(sellerDiffBankEventForm.getTransactionId());

			invoiceDocDAO.createDoc(invoiceDoc);
		}

		model.put("sellerDiffBankEventForm", sellerDiffBankEventForm);

		return new ModelAndView("sendSellerDiffSuccess", "model", model);

	}

	@RequestMapping(value = "/sellerDiffViewAllEvents", method = RequestMethod.GET)
	public ModelAndView showSellerDiffViewAllEvents(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<SellerDiffBankEvent> sellerDiffBankEvents = sellerDiffBankEventDAO.findUpdateEvents(user.getUserName()).getResultList();

		List<SellerDiffBankEvent> sellerDiffBankEvents1 = sellerDiffBankEventDAO.findRejStatus(user.getUserName()).getResultList();

		List<SellerDiffBankEvent> sellerDiffBankEvents2 = sellerDiffBankEventDAO.findStatus(user.getUserName()).getResultList();

		ModelAndView mav = new ModelAndView();

		if (sellerDiffBankEvents != null && sellerDiffBankEvents.size() > 0) {
			model.put("sellerDiffBankEvents", sellerDiffBankEvents);

			mav = new ModelAndView("updateSellerDiffBankEvents", "model", model);
		} else if (sellerDiffBankEvents1 != null && sellerDiffBankEvents1.size() > 0) {

			model.put("sellerDiffBankEvents", sellerDiffBankEvents1);

			mav = new ModelAndView("updateSellerDiffBankEvents", "model", model);

		} else if (sellerDiffBankEvents2 != null && sellerDiffBankEvents2.size() > 0) {

			model.put("sellerDiffBankEvents", sellerDiffBankEvents2);

			mav = new ModelAndView("updateSellerDiffBankEvents", "model", model);

		} else {

			mav = new ModelAndView("noDataFoundInCus", "model", model);

		}

		return mav;

	}

	@RequestMapping(value = "/selectUpdateSellerDiffBankEvents", method = RequestMethod.GET)
	public ModelAndView selectUpdateSellerDiffBankEvents(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

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

		return new ModelAndView("selectSellerDiffUpdateEvents", "model", model);

	}

	@RequestMapping(value = "/confirmSelectUpdateSellerDiffBankEvents", method = RequestMethod.POST)
	public ModelAndView confirmSelectUpdateSellerDiffBankEvents(ModelMap model, @ModelAttribute SellerDiffBankEventForm sellerDiffBankEventForm, RedirectAttributes attributes) {

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

		return new ModelAndView("confirmSelectUpdateSellerDiffBankEvents", "model", model);

	}

	@RequestMapping(value = "/selectUpdateSellerDiffBankEvents", method = RequestMethod.POST)
	public ModelAndView selectUpdateSellerDiffBankEvents(ModelMap model, @ModelAttribute SellerDiffBankEventForm sellerDiffBankEventForm, BindingResult result, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		SellerDiffBankEvent sellerDiffBankEvent = sellerDiffBankEventDAO.findEvent(sellerDiffBankEventForm.getId());


		sellerDiffBankEventForm.setTransactionId(KeyGenerator.generateTransactionKey());

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
		sellerDiffBankEvent.setDate12(sellerDiffBankEventForm.getDate12());
		sellerDiffBankEvent.setEvent12(sellerDiffBankEventForm.getEvent12());
		sellerDiffBankEvent.setTwelve(sellerDiffBankEventForm.getTwelve());
		sellerDiffBankEvent.setDate13(sellerDiffBankEventForm.getDate13());
		sellerDiffBankEvent.setEvent13(sellerDiffBankEventForm.getEvent13());
		sellerDiffBankEvent.setThirteen(sellerDiffBankEventForm.getThirteen());
		sellerDiffBankEvent.setDate14(sellerDiffBankEventForm.getDate14());
		sellerDiffBankEvent.setEvent14(sellerDiffBankEventForm.getEvent14());
		sellerDiffBankEvent.setFourteen(sellerDiffBankEventForm.getFourteen());

		sellerDiffBankEvent.setDate15(sellerDiffBankEventForm.getDate15());
		sellerDiffBankEvent.setEvent15(sellerDiffBankEventForm.getEvent15());
		sellerDiffBankEvent.setFifteen(sellerDiffBankEventForm.getFifteen());

		sellerDiffBankEvent.setDate16(sellerDiffBankEventForm.getDate16());
		sellerDiffBankEvent.setEvent16(sellerDiffBankEventForm.getEvent16());
		sellerDiffBankEvent.setSixteen(sellerDiffBankEventForm.getSixteen());

		sellerDiffBankEvent.setStatus("NA");

		sellerDiffBankEvent.setTransactionId(sellerDiffBankEventForm.getTransactionId());

		transaction.setTransactionId(sellerDiffBankEventForm.getTransactionId());

		transaction.setTransactionType("Seller Different Bank Events");

		transaction.setTransactionStatus("updated successfully");

		transactionDAO.insertTransaction(transaction);

		sellerDiffBankEventDAO.updateSellerDiffBankEvent(sellerDiffBankEvent);

		if (sellerDiffBankEventForm.getDocName().equals("")) {
		} else {
			String allDocs = sellerDiffBankEventForm.getDocName();
			String[] docs = allDocs.split(",");

			String allDescription = sellerDiffBankEventForm.getDescription();
			String[] description = allDescription.split(",");

			List<InvoiceDocForm> invoiceDocForms = new ArrayList<InvoiceDocForm>();
			for (int count = 0; count < docs.length; count++) {

				InvoiceDocForm invoiceDocForm = new InvoiceDocForm();
				invoiceDocForm.setInvoiceKey(sellerDiffBankEventForm.getInvoiceKey());
				invoiceDocForm.setDocName(docs[count]);

				invoiceDocForm.setDescription(description[count]);

				invoiceDocForms.add(invoiceDocForm);
			}
			for (InvoiceDocForm value : invoiceDocForms) {

				InvoiceDoc invoiceDoc = new InvoiceDoc();

				invoiceDoc.setInvoiceKey(value.getInvoiceKey());

				invoiceDoc.setDocName(value.getDocName());

				invoiceDoc.setDescription(value.getDescription());

				invoiceDoc.setTransactionId(sellerDiffBankEventForm.getTransactionId());

				invoiceDocDAO.createDoc(invoiceDoc);
			}
		}

		model.put("sellerDiffBankEventForm", sellerDiffBankEventForm);

		return new ModelAndView("updateSellerDiffSuccess", "model", model);
	}

	@RequestMapping(value = "/sellerDiffViewAllEvents3", method = RequestMethod.GET)
	public ModelAndView checkStatusSellerDiffEvents(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		List<SellerDiffBankEvent> sellerDiffBankEvents = sellerDiffBankEventDAO.findCheckStatus(user.getUserName()).getResultList();

		if (sellerDiffBankEvents != null && sellerDiffBankEvents.size() > 0) {

			model.put("sellerDiffBankEvents", sellerDiffBankEvents);

			mav = new ModelAndView("checkStatusSellerDiffEvents", "model", model);

		} else {
			mav = new ModelAndView("noDataFoundInCus", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/selectSellerDiffEventsForCheckStatus", method = RequestMethod.GET)
	public ModelAndView selectSellerDiffEventsForCheckStatus(ModelMap model, @RequestParam("id") Long id, HttpServletRequest request, RedirectAttributes attributes) throws ParseException {

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
			median = Math.round((arrayList.get((arrayList.size() / 2)) + arrayList.get((arrayList.size() / 2 - 1))) / 2);
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

		mav = new ModelAndView("selectSellerDiffEventsForCheckStatus", "model", model);

		return mav;

	}

	/* Comparison For Invoice events */

	@RequestMapping(value = "/comparisonInvoice", method = RequestMethod.GET)
	public ModelAndView comparisionInvoiceCustomer(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		List<SellerSameBankEvent> sellerSameBankEvents = sellerSameBankEventDAO.findCheckStatus(user.getUserName()).getResultList();
		List<SellerDiffBankEvent> sellerDiffBankEvents = sellerDiffBankEventDAO.findCheckStatus(user.getUserName()).getResultList();

		newEventForm.setSellerSameBankEvents(sellerSameBankEvents);
		newEventForm.setSellerDiffBankEvents(sellerDiffBankEvents);

		if (sellerSameBankEvents.size() == 0 && sellerDiffBankEvents.size() == 0) {

			mav = new ModelAndView("noDataFoundInCus", "model", model);
		} else {

			model.put("user", user);

			model.put("newEventForm", newEventForm);

			mav = new ModelAndView("comparisonInvoice", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/comparisonInvoiceData", method = RequestMethod.POST)
	public ModelAndView comparisionInvoiceCustomer(ModelMap model, @ModelAttribute NewEventForm newEventForm, RedirectAttributes attributes) throws ParseException {

		String mav = "";

		Invoice invoice = invoiceDAO.getInvoiceData(newEventForm.getInvoiceKey()).getSingleResult();

		List<SellerSameBankEvent> sellerSameBankEvents = sellerSameBankEventDAO.findInvoiceKey(newEventForm.getInvoiceKey()).getResultList();

		List<InvoiceDoc> invoiceDocs = invoiceDocDAO.findPoKey(newEventForm.getInvoiceKey()).getResultList();

		List<InvoiceUpload> invoiceUploads = invoiceUploadDAO.findPoKey(newEventForm.getInvoiceKey()).getResultList();

		if (sellerSameBankEvents != null && sellerSameBankEvents.size() > 0) {

			SellerSameBankEvent sellerSameBankEvent = sellerSameBankEvents.get(0);

			sellerSameBankEventForm = eventsService.populateSellerSameBankFormBasedOnEvents(sellerSameBankEvent, invoice);

			/* Feedback module */
			sellerSameBankEventForm = eventsService.generateSellerSameBankFeedback(sellerSameBankEventForm, sellerSameBankEvent, invoice);

			model.put("sellerSameBankEvent", sellerSameBankEvent);

			model.put("sellerSameBankEventForm", sellerSameBankEventForm);

			mav = "comparisonInvoiceDataForSame";
		} else {
			List<SellerDiffBankEvent> sellerDiffBankEventList = sellerDiffBankEventDAO.findInvoiceKey(newEventForm.getInvoiceKey()).getResultList();
			if (sellerDiffBankEventList != null && sellerDiffBankEventList.size() > 0) {
				SellerDiffBankEvent sellerDiffBankEvent = sellerDiffBankEventList.get(0);

				sellerDiffBankEventForm = eventsService.populateSellerDiffBankFormBasedOnEvents(sellerDiffBankEvent, invoice);
				/* Feedback module */
				sellerDiffBankEventForm = eventsService.generateSellerDiffBankFeedback(sellerDiffBankEventForm, sellerDiffBankEvent, invoice);

				model.put("sellerDiffBankEvent", sellerDiffBankEvent);

				model.put("sellerDiffBankEventForm", sellerDiffBankEventForm);

				mav = "comparisonInvoiceDataForDiff";

			}
		}
		model.put("invoiceUploads", invoiceUploads);
		model.put("invoiceDocs", invoiceDocs);
		model.put("invoice", invoice);

		return new ModelAndView(mav, "model", model);
	}

	@RequestMapping(value = "/snapShotCustHead", method = RequestMethod.GET)
	public ModelAndView snapShotBank(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<MasterPlan> masterList = masterPlanDAO.getAllMasterPlans().getResultList();

		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);

		}
		model.put("user", user);
		return new ModelAndView("snapShotCustHead", "model", model);

	}

	@RequestMapping(value = "/snapShotCustHeadView", method = RequestMethod.GET)
	public ModelAndView snapShotBankView(@RequestParam("id") Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		MasterPlan masterList = masterPlanDAO.getByMasterPlanId(id);

		if (masterList != null && masterList.getMasterKey() != null) {
			List<Collateral> collateralList = collateralDAO.getCollateralBYMAsterKey(masterList.getMasterKey()).getResultList();
			if (collateralList != null && collateralList.size() > 0) {
				model.put("collateralList", collateralList);
			}

			List<BuyingCost> buyingList = buyingCostDAO.getBuyingCostBYMAsterKey(masterList.getMasterKey()).getResultList();

			if (buyingList != null && buyingList.size() > 0) {
				model.put("buyingList", buyingList);
			}

			List<WorkingCapital> wclist = workingCapitalDAO.getWCBYMAsterKey(masterList.getMasterKey()).getResultList();
			if (wclist != null && wclist.size() > 0) {
				model.put("wclist", wclist);
			}
			List<SellerBuyingCost> sellerList = sellerBuyingCostDAO.getSellerBuyingCostList(masterList.getMasterKey()).getResultList();
			if (sellerList != null && sellerList.size() > 0) {
				model.put("sellerList", sellerList);
			}

			List<Invoice> invoiceList = invoiceDAO.getInvoiceByMasterKeyList(masterList.getMasterKey()).getResultList();

			if (invoiceList != null && invoiceList.size() > 0) {
				model.put("invoiceList", invoiceList);
			}
			List<PurchaseOrder> purchaseList = purchaseOrderDAO.getPoByMasterKeyList(masterList.getMasterKey()).getResultList();

			if (purchaseList != null && purchaseList.size() > 0) {
				model.put("purchaseList", purchaseList);

			}

			model.put("masterList", masterList);
		}
		model.put("user", user);

		return new ModelAndView("snapShotCustHeadView", "model", model);

	}

	@RequestMapping(value = "/disputeHeadList", method = RequestMethod.GET)
	public ModelAndView disputeHeadList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> purchaseList = purchaseOrderDAO.getPoBycustomerNameAndStatus(user.getUserName()).getResultList();

		if (purchaseList != null && purchaseList.size() > 0) {
			model.put("purchaseList", purchaseList);
			return new ModelAndView("disputeHeadList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/disputeHead", method = RequestMethod.GET)
	public ModelAndView disputeHead(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(id);

		disputeForm.setCustomerName(purchase.getCustomerName());
		disputeForm.setCustomerEmail(purchase.getCustomerHeadEmail());
		disputeForm.setMasterKey(purchase.getMasterKey());
		disputeForm.setPokey(purchase.getPoKey());
		disputeForm.setSupplierName(purchase.getSupplierName());
		disputeForm.setSupplierEmail(purchase.getSupplierEmail());
		disputeForm.setGoods(purchase.getGoods());
		disputeForm.setTotalCost(purchase.getAmount());
		disputeForm.setWeight(purchase.getWeight());
		disputeForm.setTransactionId(purchase.getTransactionId());
		disputeForm.setWareHousrMng(purchase.getWhMngName());

		attributes.addFlashAttribute("success", "Closed Successfully");
		model.put("user", user);
		model.put("disputeForm", disputeForm);

		return new ModelAndView("disputeHead", "model", model);
	}

	@RequestMapping(value = "/disputeHeadConfirm", method = RequestMethod.POST)
	public ModelAndView disputeHeadConfirm(ModelMap model, @ModelAttribute DisputeForm disputeForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		disputeForm.setId(disputeForm.getId());
		disputeForm.setCustomerName(disputeForm.getCustomerName());
		disputeForm.setCustomerEmail(disputeForm.getCustomerEmail());
		disputeForm.setMasterKey(disputeForm.getMasterKey());
		disputeForm.setPokey(disputeForm.getPokey());
		disputeForm.setSupplierName(disputeForm.getSupplierName());
		disputeForm.setSupplierEmail(disputeForm.getSupplierEmail());
		disputeForm.setGoods(disputeForm.getGoods());
		disputeForm.setTotalCost(disputeForm.getTotalCost());
		disputeForm.setMissingGoods(disputeForm.getMissingGoods());
		disputeForm.setMissingGoodsCost(disputeForm.getMissingGoodsCost());
		disputeForm.setMissingGoodsQty(disputeForm.getMissingGoodsQty());
		disputeForm.setFullyDamagedGoods(disputeForm.getFullyDamagedGoods());
		disputeForm.setFullyDamagedCost(disputeForm.getFullyDamagedCost());
		disputeForm.setFullyDamagedQty(disputeForm.getFullyDamagedQty());
		disputeForm.setPartiallyDamagedGoods(disputeForm.getPartiallyDamagedGoods());
		disputeForm.setPartiallyDamagedCost(disputeForm.getPartiallyDamagedCost());
		disputeForm.setPartiallyDamagedQty(disputeForm.getPartiallyDamagedQty());
		disputeForm.setGoodsSummarry(disputeForm.getGoodsSummarry());
		disputeForm.setFir(disputeForm.getFir());
		disputeForm.setFirDate(disputeForm.getFirDate());
		disputeForm.setLocation(disputeForm.getLocation());
		disputeForm.setGoods(disputeForm.getGoods());
		disputeForm.setGoodsInfo(disputeForm.getGoodsInfo());
		disputeForm.setTotalCost(disputeForm.getTotalCost());
		disputeForm.setWeight(disputeForm.getWeight());
		disputeForm.setAnswer(disputeForm.getAnswer());
		disputeForm.setGoodsDefect(disputeForm.getGoodsDefect());
		disputeForm.setNoOfDefect(disputeForm.getNoOfDefect());
		disputeForm.setAnswer1(disputeForm.getAnswer1());
		disputeForm.setAnswer2(disputeForm.getAnswer2());
		disputeForm.setTransactionId(disputeForm.getTransactionId());
		disputeForm.setWareHousrMng(disputeForm.getWareHousrMng());

		model.put("disputeForm", disputeForm);
		model.put("user", user);

		return new ModelAndView("disputeHeadConfirm", "model", model);

	}

	@RequestMapping(value = "/disputeHeadPost", method = RequestMethod.POST)
	public ModelAndView disputeHeadPost(ModelMap model, @ModelAttribute DisputeForm disputeForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		Dispute dispute = new Dispute();

		dispute.setCustomerName(disputeForm.getCustomerName());
		dispute.setCustomerEmail(disputeForm.getCustomerEmail());
		dispute.setMasterKey(disputeForm.getMasterKey());
		dispute.setPoKey(disputeForm.getPokey());
		dispute.setSupplierName(disputeForm.getSupplierName());
		dispute.setSupplierEmail(disputeForm.getSupplierEmail());
		dispute.setGoods(disputeForm.getGoods());
		dispute.setTotalCost(disputeForm.getTotalCost());
		dispute.setGoods(disputeForm.getGoods());
		dispute.setGoodsInfo(disputeForm.getGoodsInfo());
		dispute.setTotalCost(disputeForm.getTotalCost());
		dispute.setWeight(disputeForm.getWeight());
		dispute.setMissingGoods(disputeForm.getMissingGoods());
		dispute.setMissingGoodsCost(disputeForm.getMissingGoodsCost());
		dispute.setMissingGoodsQty(disputeForm.getMissingGoodsQty());
		dispute.setFullyDamagedGoods(disputeForm.getFullyDamagedGoods());
		dispute.setFullyDamagedCost(disputeForm.getFullyDamagedCost());
		dispute.setFullyDamagedQty(disputeForm.getFullyDamagedQty());
		dispute.setPartiallyDamagedGoods(disputeForm.getPartiallyDamagedGoods());
		dispute.setPartiallyDamagedCost(disputeForm.getPartiallyDamagedCost());
		dispute.setPartiallyDamagedQty(disputeForm.getPartiallyDamagedQty());
		dispute.setGoodsSummarry(disputeForm.getGoodsSummarry());
		dispute.setFir(disputeForm.getFir());
		dispute.setFirDate(disputeForm.getFirDate());
		dispute.setLocation(disputeForm.getLocation());
		dispute.setAnswer(disputeForm.getAnswer());
		dispute.setGoodsDefect(disputeForm.getGoodsDefect());
		dispute.setNoOfDefect(disputeForm.getNoOfDefect());
		dispute.setAnswer1(disputeForm.getAnswer1());
		dispute.setAnswer2(disputeForm.getAnswer2());
		dispute.setDisputeKey(disputeForm.getDisputeKey());
		dispute.setTransactionId(disputeForm.getTransactionId());
		dispute.setWareHousrMng(disputeForm.getWareHousrMng());
		dispute.setStatus("Pending");

		Date DisputeDate = DateService.loginDate;
		dispute.setDate(DisputeDate);

		disputeDAO.createDispute(dispute);

		Transaction trans = new Transaction();
		trans.setTransactionId(disputeForm.getTransactionId());
		trans.setTransactionStatus("Dispute");
		trans.setTransactionType("Submitted Successfully");

		model.put("disputeForm", disputeForm);
		model.put("user", user);

		return new ModelAndView("disputeHeadTrans", "model", model);

	}

	@RequestMapping(value = "/disputeHeadTrans", method = RequestMethod.GET)
	public ModelAndView disputeHeadTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("disputeForm", disputeForm);

		return new ModelAndView("disputeHeadTrans", "model", model);

	}

	@RequestMapping(value = "/addOrModifyReport", method = RequestMethod.GET)
	public ModelAndView addOrModifyReport(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Dispute dispute = disputeDAO.getByDisputeId(id);

		PurchaseOrder purchase = purchaseOrderDAO.getPoData(dispute.getPoKey()).getSingleResult();

		disputeReportsForm.setCustomerName(dispute.getCustomerName());
		disputeReportsForm.setPokey(dispute.getPoKey());
		disputeReportsForm.setSupplierName(dispute.getSupplierName());
		disputeReportsForm.setGoods(dispute.getGoods());
		disputeReportsForm.setInsDetails(purchase.getInsuranceDetails());
		disputeReportsForm.setCost(purchase.getAmount());
		disputeReportsForm.setGoodsDetails(purchase.getGoodsDetails());
		disputeReportsForm.setTransactionId(purchase.getTransactionId());
		disputeReportsForm.setDisputekey(dispute.getDisputeKey());
		disputeReportsForm.setInStartDate(purchase.getStartDate());
		disputeReportsForm.setInsEndDate(purchase.getEndDate());

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		return new ModelAndView("addOrModifyReport", "model", model);
	}

	@RequestMapping(value = "/addOrModifyReportConfirm", method = RequestMethod.POST)
	public ModelAndView addOrModifyReportConfirm(ModelMap model, @ModelAttribute DisputeReportsForm disputeReportsForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		disputeReportsForm.setId(disputeReportsForm.getId());
		disputeReportsForm.setCustomerName(disputeReportsForm.getCustomerName());
		disputeReportsForm.setPokey(disputeReportsForm.getPokey());
		disputeReportsForm.setSupplierName(disputeReportsForm.getSupplierName());
		disputeReportsForm.setGoods(disputeReportsForm.getGoods());
		disputeReportsForm.setInsDetails(disputeReportsForm.getInsDetails());
		disputeReportsForm.setInStartDate(disputeReportsForm.getInStartDate());
		disputeReportsForm.setInsEndDate(disputeReportsForm.getInsEndDate());
		disputeReportsForm.setCost(disputeReportsForm.getCost());
		disputeReportsForm.setDisputekey(disputeReportsForm.getDisputekey());
		disputeReportsForm.setGoodsDetails(disputeReportsForm.getGoodsDetails());
		disputeReportsForm.setTransactionId(disputeReportsForm.getTransactionId());
		disputeReportsForm.setShipper(disputeReportsForm.getShipper());
		disputeReportsForm.setTransportType(disputeReportsForm.getModeOfTransport());
		disputeReportsForm.setModeOfTransport(disputeReportsForm.getModeOfTransport());
		disputeReportsForm.setTermsCond(disputeReportsForm.getTermsCond());
		disputeReportsForm.setInclusion(disputeReportsForm.getInclusion());
		disputeReportsForm.setExclusion(disputeReportsForm.getExclusion());
		disputeReportsForm.setSurveyorName(disputeReportsForm.getSurveyorName());
		disputeReportsForm.setSurveyorCom(disputeReportsForm.getSurveyorCom());
		disputeReportsForm.setSurveyorAdd(disputeReportsForm.getSurveyorAdd());
		disputeReportsForm.setSurveyorEmail(disputeReportsForm.getSurveyorEmail());
		disputeReportsForm.setSurveyorPhone(disputeReportsForm.getSurveyorPhone());
		disputeReportsForm.setDefectType(disputeReportsForm.getDefectType());
		disputeReportsForm.setDefectQty(disputeReportsForm.getDefectQty());
		disputeReportsForm.setDefectCostForGoods(disputeReportsForm.getDefectCostForGoods());
		disputeReportsForm.setDamageStatus(disputeReportsForm.getDamageStatus());
		disputeReportsForm.setReplacement(disputeReportsForm.getReplacement());
		disputeReportsForm.setRepairCost(disputeReportsForm.getRepairCost());

		model.put("disputeReportsForm", disputeReportsForm);
		model.put("user", user);

		return new ModelAndView("addOrModifyReportConfirm", "model", model);

	}

	@RequestMapping(value = "/addOrModifyReportPost", method = RequestMethod.POST)
	public ModelAndView addOrModifyReportPost(ModelMap model, @ModelAttribute DisputeReportsForm disputeReportsForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();
		List<DisputeReports> dis = disputeReportsDAO.getDisputeReportsList(disputeReportsForm.getDisputekey()).getResultList();
		if (dis != null && dis.size() > 0) {
			DisputeReports disp = disputeReportsDAO.getDisputeReportsList(disputeReportsForm.getDisputekey()).getSingleResult();

			disp.setCustomerName(disputeReportsForm.getCustomerName());
			disp.setPokey(disputeReportsForm.getPokey());
			disp.setSupplierName(disputeReportsForm.getSupplierName());
			disp.setGoods(disputeReportsForm.getGoods());
			disp.setInsDetails(disputeReportsForm.getInsDetails());
			disp.setCost(disputeReportsForm.getCost());
			disp.setDisputekey(disputeReportsForm.getDisputekey());
			disp.setGoodsDetails(disputeReportsForm.getGoodsDetails());
			disp.setTransactionId(disputeReportsForm.getTransactionId());
			disp.setShipper(disputeReportsForm.getShipper());
			disp.setTransportType(disputeReportsForm.getModeOfTransport());
			disp.setModeOfTransport(disputeReportsForm.getModeOfTransport());
			disp.setTermsCond(disputeReportsForm.getTermsCond());
			disp.setInclusion(disputeReportsForm.getInclusion());
			disp.setExclusion(disputeReportsForm.getExclusion());
			disp.setSurveyorName(disputeReportsForm.getSurveyorName());
			disp.setSurveyorCom(disputeReportsForm.getSurveyorCom());
			disp.setSurveyorAdd(disputeReportsForm.getSurveyorAdd());
			disp.setSurveyorEmail(disputeReportsForm.getSurveyorEmail());
			disp.setSurveyorPhone(disputeReportsForm.getSurveyorPhone());
			disp.setDefectType(disputeReportsForm.getDefectType());
			disp.setDefectQty(disputeReportsForm.getDefectQty());
			disp.setDefectCostForGoods(disputeReportsForm.getDefectCostForGoods());
			disp.setDamageStatus(disputeReportsForm.getDamageStatus());
			disp.setReplacement(disputeReportsForm.getReplacement());
			disp.setRepairCost(disputeReportsForm.getRepairCost());
			disp.setStatus("Pending");
			disp.setAccept("Pending");

			disputeReportsDAO.updateDisputeReports(disp);

		} else {
			DisputeReports dispute = new DisputeReports();

			dispute.setId(disputeReportsForm.getId());
			dispute.setCustomerName(disputeReportsForm.getCustomerName());
			dispute.setPokey(disputeReportsForm.getPokey());
			dispute.setSupplierName(disputeReportsForm.getSupplierName());
			dispute.setGoods(disputeReportsForm.getGoods());
			dispute.setInsDetails(disputeReportsForm.getInsDetails());
			dispute.setCost(disputeReportsForm.getCost());
			dispute.setDisputekey(disputeReportsForm.getDisputekey());
			dispute.setGoodsDetails(disputeReportsForm.getGoodsDetails());
			dispute.setTransactionId(disputeReportsForm.getTransactionId());
			dispute.setShipper(disputeReportsForm.getShipper());
			dispute.setTransportType(disputeReportsForm.getModeOfTransport());
			dispute.setModeOfTransport(disputeReportsForm.getModeOfTransport());
			dispute.setTermsCond(disputeReportsForm.getTermsCond());
			dispute.setInclusion(disputeReportsForm.getInclusion());
			dispute.setExclusion(disputeReportsForm.getExclusion());
			dispute.setSurveyorName(disputeReportsForm.getSurveyorName());
			dispute.setSurveyorCom(disputeReportsForm.getSurveyorCom());
			dispute.setSurveyorAdd(disputeReportsForm.getSurveyorAdd());
			dispute.setSurveyorEmail(disputeReportsForm.getSurveyorEmail());
			dispute.setSurveyorPhone(disputeReportsForm.getSurveyorPhone());
			dispute.setDefectType(disputeReportsForm.getDefectType());
			dispute.setDefectQty(disputeReportsForm.getDefectQty());
			dispute.setDefectCostForGoods(disputeReportsForm.getDefectCostForGoods());
			dispute.setDamageStatus(disputeReportsForm.getDamageStatus());
			dispute.setReplacement(disputeReportsForm.getReplacement());
			dispute.setRepairCost(disputeReportsForm.getRepairCost());
			dispute.setSuppRepairCost(0.0f);
			dispute.setSuppDefectQty(0.0f);
			dispute.setStatus("Pending");
			dispute.setAccept("Pending");

			disputeReportsDAO.createDisputeReports(dispute);
		}

		Transaction trans = new Transaction();
		trans.setTransactionId(disputeReportsForm.getTransactionId());
		trans.setTransactionStatus("Dispute");
		trans.setTransactionType("Submitted Successfully");

		model.put("disputeReportsForm", disputeReportsForm);
		model.put("user", user);

		return new ModelAndView("addOrModifyReportTrans", "model", model);

	}

	@RequestMapping(value = "/addOrModifyReportTrans", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		return new ModelAndView("addOrModifyReportTrans", "model", model);

	}

	@RequestMapping(value = "/addOrModifyReportList", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<DisputeReports> disputeList = disputeReportsDAO.getDisputeReportsOnCustList(user.getUserName()).getResultList();

		if (disputeList != null && disputeList.size() > 0) {
			model.put("disputeList", disputeList);
			return new ModelAndView("addOrModifyReportList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/addOrModifyReportView", method = RequestMethod.GET)
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

		return new ModelAndView("addOrModifyReportView", "model", model);
	}

	@RequestMapping(value = "/addOrModifyReportCompare", method = RequestMethod.GET)
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

		return new ModelAndView("addOrModifyReportCompare", "model", model);
	}

	@RequestMapping(value = "/addOrModifyReportAddArbitration", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportAddArbitration(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		DisputeReports dispute = disputeReportsDAO.getByDisputeReportsId(id);

		disputeReportsForm.setTransactionId(dispute.getTransactionId());
		disputeReportsForm.setDisputekey(dispute.getDisputekey());

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		return new ModelAndView("addOrModifyReportAddArbitration", "model", model);
	}

	@RequestMapping(value = "/addOrModifyReportAddArbitrationConfirm", method = RequestMethod.POST)
	public ModelAndView addOrModifyReportAddArbitrationConfirm(ModelMap model, @ModelAttribute DisputeReportsForm disputeReportsForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		disputeReportsForm.setArbNames(disputeReportsForm.getArbNames());
		disputeReportsForm.setLoc(disputeReportsForm.getLoc());
		disputeReportsForm.setJudgement(disputeReportsForm.getJudgement());
		disputeReportsForm.setJudgDate(disputeReportsForm.getJudgDate());
		disputeReportsForm.setCompliedDate(disputeReportsForm.getCompliedDate());
		disputeReportsForm.setArbCost(disputeReportsForm.getArbCost());
		disputeReportsForm.setArbQty(disputeReportsForm.getArbQty());
		disputeReportsForm.setArbStartDate(disputeReportsForm.getArbStartDate());
		disputeReportsForm.setArbEndDate(disputeReportsForm.getArbEndDate());
		disputeReportsForm.setDocSub(disputeReportsForm.getDocSub());
		disputeReportsForm.setMoneyPaid(disputeReportsForm.getMoneyPaid());
		disputeReportsForm.setPayDate(disputeReportsForm.getPayDate());
		disputeReportsForm.setGoodsReplaced(disputeReportsForm.getGoodsReplaced());
		disputeReportsForm.setRepacedDate(disputeReportsForm.getRepacedDate());
		disputeReportsForm.setTransactionId(disputeReportsForm.getTransactionId());
		disputeReportsForm.setDisputekey(disputeReportsForm.getDisputekey());

		model.put("disputeReportsForm", disputeReportsForm);
		model.put("user", user);

		return new ModelAndView("addOrModifyReportAddArbitrationConfirm", "model", model);

	}

	@RequestMapping(value = "/addOrModifyReportAddArbitrationPost", method = RequestMethod.POST)
	public ModelAndView addOrModifyReportAddArbitrationPost(ModelMap model, @ModelAttribute DisputeReportsForm disputeReportsForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();
		List<DisputeReports> dis = disputeReportsDAO.getDisputeReportsList(disputeReportsForm.getDisputekey()).getResultList();
		if (dis != null && dis.size() > 0) {
			DisputeReports disp = disputeReportsDAO.getDisputeReportsList(disputeReportsForm.getDisputekey()).getSingleResult();

			disp.setArbNames(disputeReportsForm.getArbNames());
			disp.setLoc(disputeReportsForm.getLoc());
			disp.setJudgement(disputeReportsForm.getJudgement());
			disp.setCompliedDate(disputeReportsForm.getCompliedDate());
			disp.setArbCost(disputeReportsForm.getArbCost());
			disp.setArbQty(disputeReportsForm.getArbQty());
			disp.setArbStartDate(disputeReportsForm.getArbStartDate());
			disp.setArbEndDate(disputeReportsForm.getArbEndDate());
			disp.setDocSub(disputeReportsForm.getDocSub());
			disp.setMoneyPaid(disputeReportsForm.getMoneyPaid());
			disp.setPayDate(disputeReportsForm.getPayDate());
			disp.setGoodsReplaced(disputeReportsForm.getGoodsReplaced());
			disp.setRepacedDate(disputeReportsForm.getRepacedDate());
			disp.setJudgDate(disputeReportsForm.getJudgDate());

			disputeReportsDAO.updateDisputeReports(disp);

		}

		Transaction trans = new Transaction();
		trans.setTransactionId(disputeReportsForm.getTransactionId());
		trans.setTransactionStatus("Dispute");
		trans.setTransactionType("Submitted Successfully");

		model.put("disputeReportsForm", disputeReportsForm);
		model.put("user", user);

		return new ModelAndView("addOrModifyReportTrans", "model", model);

	}

	@RequestMapping(value = "/addOrModifyReportAddArbitrationTrans", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportAddArbitrationTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		return new ModelAndView("addOrModifyReportAddArbitrationTrans", "model", model);

	}

	@RequestMapping(value = "/addOrModifyReportCustAccept", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportAccept(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		DisputeReports dispute = disputeReportsDAO.getByDisputeReportsId(id);

		dispute.setCustAccept("Accepted");

		disputeReportsDAO.updateDisputeReports(dispute);

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		attributes.addFlashAttribute("success", "Accepted Successfully");

		return new ModelAndView("redirect:addOrModifyReportList");
	}

	@RequestMapping(value = "/disputeHeadUpdateList", method = RequestMethod.GET)
	public ModelAndView disputeHeadUpdateList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Dispute> disputeList = disputeDAO.getDisputeList(user.getUserName()).getResultList();

		if (disputeList != null && disputeList.size() > 0) {
			model.put("disputeList", disputeList);
			return new ModelAndView("disputeHeadUpdateList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/disputeHeadUpdate", method = RequestMethod.GET)
	public ModelAndView disputeHeadUpdate(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Dispute dispute = disputeDAO.getByDisputeId(id);

		disputeForm.setId(dispute.getId());
		disputeForm.setCustomerName(dispute.getCustomerName());
		disputeForm.setMasterKey(dispute.getDisputeKey());
		disputeForm.setPokey(dispute.getPoKey());
		disputeForm.setCustomerEmail(dispute.getCustomerEmail());
		disputeForm.setSupplierName(dispute.getSupplierName());
		disputeForm.setSupplierEmail(dispute.getSupplierEmail());
		disputeForm.setTransactionId(dispute.getTransactionId());

		attributes.addFlashAttribute("success", "Closed Successfully");
		model.put("user", user);
		model.put("disputeForm", disputeForm);

		return new ModelAndView("disputeHeadUpdate", "model", model);
	}

	@RequestMapping(value = "/disputeHeadUpdateConfirm", method = RequestMethod.POST)
	public ModelAndView disputeHeadUpdateConfirm(ModelMap model, @ModelAttribute DisputeForm disputeForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		disputeForm.setId(disputeForm.getId());
		disputeForm.setCustomerName(disputeForm.getCustomerName());
		disputeForm.setMasterKey(disputeForm.getDisputeKey());
		disputeForm.setCustomerEmail(disputeForm.getCustomerEmail());
		disputeForm.setPokey(disputeForm.getPokey());
		disputeForm.setSupplierName(disputeForm.getSupplierName());
		disputeForm.setSupplierEmail(disputeForm.getSupplierEmail());
		disputeForm.setTransactionId(disputeForm.getTransactionId());
		disputeForm.setDisputeStatus(disputeForm.getDisputeStatus());
		disputeForm.setComment(disputeForm.getComment());

		model.put("disputeForm", disputeForm);
		model.put("user", user);

		return new ModelAndView("disputeHeadUpdateConfirm", "model", model);

	}

	@RequestMapping(value = "/disputeHeadUpdatePost", method = RequestMethod.POST)
	public ModelAndView disputeHeadUpdatePost(ModelMap model, @ModelAttribute DisputeForm disputeForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();


		disputeForm.setTransactionId(KeyGenerator.generateTransactionKey());

		Dispute dispute = disputeDAO.getByDisputeId(disputeForm.getId());

		disputeForm.setSupplierEmail(disputeForm.getSupplierEmail());

		disputeForm.setTransactionId(disputeForm.getTransactionId());
		dispute.setDisputeStatus(disputeForm.getDisputeStatus());
		dispute.setComment(disputeForm.getComment());

		Date statusDate = DateService.loginDate;
		dispute.setStatusDate(statusDate);

		disputeDAO.updateDispute(dispute);

		Transaction trans = new Transaction();
		trans.setTransactionId(disputeForm.getTransactionId());
		trans.setTransactionStatus("Dispute");
		trans.setTransactionType("Submitted Successfully");
		transactionDAO.insertTransaction(trans);

		model.put("disputeForm", disputeForm);
		model.put("user", user);
		return new ModelAndView("disputeHeadUpdateTrans", "model", model);

	}

	/*
	 * @RequestMapping(value = "/disputeHeadUpdateTrans", method =
	 * RequestMethod.GET) public ModelAndView disputeHeadUpdateTrans(ModelMap model)
	 * {
	 *
	 * EndUser user = getCurrentLoggedUserDetails();
	 *
	 * model.put("user", user); model.put("disputeForm", disputeForm);
	 *
	 * return new ModelAndView("disputeHeadUpdateTrans", "model", model);
	 *
	 * }
	 */

	@RequestMapping(value = "/downloadfile", method = RequestMethod.GET)
	public ModelAndView printWelcome(ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		model.addAttribute("message", "Sample code!");
		return new ModelAndView("downloadfile", "model", model);

	}

	@RequestMapping(value = "/downloadFiles", method = RequestMethod.GET)
	public @ResponseBody void downloadFiles(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		ServletContext context = getServletContext();

		File downloadFile = new File("D:/TESTAPP/Declaration cum Affidavit to Bank.docx");
		FileInputStream inputStream = null;
		OutputStream outStream = null;

		try {
			inputStream = new FileInputStream(downloadFile);

			response.setContentType(context.getMimeType("D:/TESTAPP/Declaration cum Affidavit to Bank.docx"));
			response.setContentLength((int) downloadFile.length());

			// response header
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			response.setHeader(headerKey, headerValue);

			// Write response
			outStream = response.getOutputStream();
			IOUtils.copy(inputStream, outStream);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != inputStream) inputStream.close();
				if (null != inputStream) outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/* warehouse */

	@RequestMapping(value = "/createCustWareHouse", method = RequestMethod.GET)
	public ModelAndView createCusthWareHouse(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouse> whList = wareHouseDAO.getByPending(user.getUserName()).getResultList();

		wareHouseForm.setCustomerName(user.getUserName());
		List<WareHouseMng> wahList = wareHouseMngDAO.getWareHouseMngFullList(user.getUserName()).getResultList();
		if (wahList != null && wahList.size() > 0) {
			wareHouseForm.setWhList(wahList);
		}

		model.put("user", user);
		model.put("wareHouseForm", wareHouseForm);
		model.put("whList", whList);

		return new ModelAndView("createCustWareHouse", "model", model);

	}

	@RequestMapping(value = "/selectCustWareHouse", method = RequestMethod.GET)
	public ModelAndView selectClientAdmin(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		WareHouse wareHouse = wareHouseDAO.getByWareHouseId(id);

		wareHouseForm.setId(wareHouse.getId());
		wareHouseForm.setCustomerName(wareHouse.getCustomerName());
		wareHouseForm.setPersonInCharge(wareHouse.getPersonInCharge());
		wareHouseForm.setCountry(wareHouse.getCountry());
		wareHouseForm.setState(wareHouse.getState());
		wareHouseForm.setPinCode(wareHouse.getPinCode());
		wareHouseForm.setAddress(wareHouse.getAddress());
		wareHouseForm.setWareHouseName(wareHouse.getWareHouseName());
		wareHouseForm.setCapacity(wareHouse.getCapacity());
		wareHouseForm.setSize(wareHouse.getSize());
		wareHouseForm.setTransactionId(wareHouse.getTransactionId());

		model.put("user", user);

		model.put("wareHouseForm", wareHouseForm);

		return new ModelAndView("selectCustWareHouse", "model", model);

	}

	@RequestMapping(value = "/updateCustWareHouseConfirm", method = RequestMethod.POST)
	public ModelAndView updateCustWareHouseConfirm(@ModelAttribute WareHouseForm wareHouseForm, ModelMap model, RedirectAttributes attribute) {
		EndUser user = getCurrentLoggedUserDetails();

		wareHouseForm.setId(wareHouseForm.getId());
		wareHouseForm.setCustomerName(wareHouseForm.getCustomerName());
		wareHouseForm.setPersonInCharge(wareHouseForm.getPersonInCharge());
		wareHouseForm.setCountry(wareHouseForm.getCountry());
		wareHouseForm.setState(wareHouseForm.getState());
		wareHouseForm.setPinCode(wareHouseForm.getPinCode());
		wareHouseForm.setAddress(wareHouseForm.getAddress());
		wareHouseForm.setWareHouseName(wareHouseForm.getWareHouseName());
		wareHouseForm.setCapacity(wareHouseForm.getCapacity());
		wareHouseForm.setSize(wareHouseForm.getSize());
		wareHouseForm.setTransactionId(wareHouseForm.getTransactionId());

		model.put("user", user);

		model.put("wareHouseForm", wareHouseForm);

		return new ModelAndView("updateCustWareHouseConfirm", "model", model);

	}

	@RequestMapping(value = "/updateCustWareHouse", method = RequestMethod.POST)
	public ModelAndView updateCustWareHouse(ModelMap model, @ModelAttribute WareHouseForm wareHouseForm, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		WareHouse wareHouse = wareHouseDAO.getByWareHouseId(wareHouseForm.getId());

		wareHouse.setCustomerName(wareHouseForm.getCustomerName());
		wareHouse.setPersonInCharge(wareHouseForm.getPersonInCharge());
		wareHouse.setCountry(wareHouseForm.getCountry());
		wareHouse.setState(wareHouseForm.getState());
		wareHouse.setPinCode(wareHouseForm.getPinCode());
		wareHouse.setAddress(wareHouseForm.getAddress());
		wareHouse.setWareHouseName(wareHouseForm.getWareHouseName());
		wareHouse.setCapacity(wareHouseForm.getCapacity());
		wareHouse.setSize(wareHouseForm.getSize());
		wareHouse.setTransactionId(wareHouseForm.getTransactionId());
		wareHouse.setStatus("Pending");

		wareHouseDAO.updateWareHouse(wareHouse);

		return new ModelAndView("redirect:createCustWareHouse");

	}

	@RequestMapping(value = "/custWareHouseConfirm", method = RequestMethod.POST)
	public ModelAndView clientAppMngConfirm(@ModelAttribute WareHouseForm wareHouseForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouse> head = wareHouseDAO.getWareHouseList(wareHouseForm.getWareHouseName()).getResultList();

		if (head.size() != 0) {
			model.put("user", user);
			attributes.addFlashAttribute("success", "WareHouse Already Exists");

			return new ModelAndView("redirect:createCustWareHouse");
		} else {

			wareHouseForm.setCustomerName(wareHouseForm.getCustomerName());
			wareHouseForm.setPersonInCharge(wareHouseForm.getPersonInCharge());
			wareHouseForm.setCountry(wareHouseForm.getCountry());
			wareHouseForm.setState(wareHouseForm.getState());
			wareHouseForm.setPinCode(wareHouseForm.getPinCode());
			wareHouseForm.setAddress(wareHouseForm.getAddress());
			wareHouseForm.setWareHouseName(wareHouseForm.getWareHouseName());
			wareHouseForm.setCapacity(wareHouseForm.getCapacity());
			wareHouseForm.setSize(wareHouseForm.getSize());
			wareHouseForm.setTransactionId(wareHouseForm.getTransactionId());

			model.put("wareHouseForm", wareHouseForm);
			model.put("user", user);

			return new ModelAndView("custWareHouseConfirm", "model", model);
		}
	}

	@RequestMapping(value = "/custWareHouseSave", method = RequestMethod.POST)
	public ModelAndView custWareHouseSave(@ModelAttribute WareHouseForm wareHouseForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		wareHouseForm.setTransactionId(KeyGenerator.generateTransactionKey());

		WareHouse wareHouse = new WareHouse();

		wareHouse.setCustomerName(wareHouseForm.getCustomerName());
		wareHouse.setPersonInCharge(wareHouseForm.getPersonInCharge());
		wareHouse.setCountry(wareHouseForm.getCountry());
		wareHouse.setState(wareHouseForm.getState());
		wareHouse.setPinCode(wareHouseForm.getPinCode());
		wareHouse.setAddress(wareHouseForm.getAddress());
		wareHouse.setWareHouseName(wareHouseForm.getWareHouseName());
		wareHouse.setCapacity(wareHouseForm.getCapacity());
		wareHouse.setSize(wareHouseForm.getSize());
		wareHouse.setTransactionId(wareHouseForm.getTransactionId());
		wareHouse.setStatus("Pending");

		wareHouseDAO.createMasterPlan(wareHouse);

		Transaction trans = new Transaction();

		trans.setTransactionId(wareHouseForm.getTransactionId());
		trans.setTransactionStatus("WareHouse Saved");
		trans.setTransactionType("WareHouse");
		;
		transactionDAO.insertTransaction(trans);

		model.put("wareHouseForm", wareHouseForm);
		model.put("user", user);

		return new ModelAndView("custWareHouseTransaction", "model", model);

	}

	@RequestMapping(value = "/custWareHouseTransaction", method = RequestMethod.GET)
	public ModelAndView custWareHouseTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("wareHouseForm", wareHouseForm);

		return new ModelAndView("custWareHouseTransaction", "model", model);

	}

	@RequestMapping(value = "/custWareHouseFullList", method = RequestMethod.GET)
	public ModelAndView custWareHouseFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouse> whList = wareHouseDAO.getWareHouseFullList(user.getUserName()).getResultList();

		model.put("user", user);
		if (whList != null && whList.size() > 0) {
			model.put("whList", whList);

			return new ModelAndView("custWareHouseFullList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/custWareHouseReceiveList", method = RequestMethod.GET)
	public ModelAndView apprCustAppMngList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		List<PurchaseOrder> purchaseList = purchaseOrderDAO.getPoListGoodsReceive(user.getUserName()).getResultList();

		if (purchaseList != null && purchaseList.size() > 0) {

			model.put("purchaseList", purchaseList);
			return new ModelAndView("custWareHouseReceiveList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}
	}

	@RequestMapping(value = "/custWareHouseReceive", method = RequestMethod.GET)
	public ModelAndView invoiceVendorPayCleared(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder po = purchaseOrderDAO.getByPurchaseId(id);

		po.setGoodsStatus("Received");
		String poKey = po.getPoKey();

		Date receive = DateService.loginDate;
		po.setReceiveDate(receive);
		purchaseOrderDAO.updatePo(po);

		model.put("user", user);
		model.put("poKey", poKey);

		/*
		 * attributes.addFlashAttribute("success",
		 * "Purchase Order with Id "+po.getId()+" has been received Successfully");
		 */
		return new ModelAndView("purchaseOrderTransaction");

	}

	/*
	 * @RequestMapping(value = "/custWareHouseInvoiceList", method =
	 * RequestMethod.GET) public ModelAndView custWareHouseInvoiceList(ModelMap
	 * model) {
	 *
	 * EndUser user = getCurrentLoggedUserDetails(); model.put("user", user);
	 * List<Invoice> invoiceList =
	 * invoiceDAO.getInvoiceListForGoods(user.getUserName()).getResultList();
	 *
	 * if (invoiceList != null && invoiceList.size() > 0) {
	 *
	 * model.put("invoiceList", invoiceList); return new
	 * ModelAndView("custWareHouseInvoiceList", "model", model); } else { return new
	 * ModelAndView("noDataFoundInCus", "model", model); } }
	 */

	/*
	 * @RequestMapping(value = "/custWareHouseInvoiceSend", method =
	 * RequestMethod.GET) public ModelAndView custWareHouseInvoiceSend(@RequestParam
	 * Long id, ModelMap model, RedirectAttributes attributes) {
	 *
	 * EndUser user = getCurrentLoggedUserDetails();
	 *
	 * Invoice invoice = invoiceDAO.getByInvoiceId(id);
	 *
	 * invoice.setGoodsStatus("Sent");
	 *
	 * Date sent = DateService.loginDate; invoice.setSentDate(sent);
	 *
	 * invoiceDAO.updateInvoice(invoice);
	 *
	 * model.put("user", user);
	 *
	 * attributes.addFlashAttribute("success",
	 * "Invoice Order with id "+invoice.getId()+" has been delivered to buyer");
	 * return new ModelAndView("redirect:custWareHouseInvoiceList");
	 *
	 * }
	 */

	@RequestMapping(value = "/masterPlanLimitBurstList", method = RequestMethod.GET)
	public ModelAndView masterPlanLimitBurstList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<MasterPlan> masterList = masterPlanDAO.getMasterPlanForFunds(user.getUserName()).getResultList();
		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);
			return new ModelAndView("masterPlanLimitBurstList", "model", model);

		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/poListForLimitBurst", method = RequestMethod.GET)
	public ModelAndView poListForLimitBurst(@RequestParam("id") Long id, ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		MasterPlan plan = masterPlanDAO.getByMasterPlanId(id);

		List<PurchaseOrder> poList = purchaseOrderDAO.getPoListByMasterKeyAndStatus(plan.getMasterKey()).getResultList();

		model.put("user", user);
		if (poList != null && poList.size() > 0) {
			model.put("poList", poList);

			return new ModelAndView("poListForLimitBurst", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}
	}

	@RequestMapping(value = "/poApplyLimit", method = RequestMethod.GET)
	public ModelAndView poApplyLimit(@RequestParam("id") Long id, ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		PurchaseOrder po = purchaseOrderDAO.getByPurchaseId(id);

		limitBurstForm.setCustomerHeadName(po.getCustomerHeadName());
		limitBurstForm.setCustomerEmail(po.getCustomerHeadEmail());
		limitBurstForm.setSupplierName(po.getSupplierName());
		limitBurstForm.setMasterKey(po.getMasterKey());
		limitBurstForm.setPoKey(po.getPoKey());
		limitBurstForm.setTransactionId(po.getTransactionId());

		model.put("user", user);
		model.put("limitBurstForm", limitBurstForm);

		return new ModelAndView("poApplyLimit", "model", model);
	}

	@RequestMapping(value = "/poApplyLimitConfirm", method = RequestMethod.POST)
	public ModelAndView poApplyLimit(@ModelAttribute LimitBurstForm limitBurstForm, ModelMap model, RedirectAttributes attributes) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		limitBurstForm.setCustomerHeadName(limitBurstForm.getCustomerHeadName());
		limitBurstForm.setCustomerEmail(limitBurstForm.getCustomerEmail());
		limitBurstForm.setSupplierName(limitBurstForm.getSupplierName());
		limitBurstForm.setMasterKey(limitBurstForm.getMasterKey());
		limitBurstForm.setPoKey(limitBurstForm.getPoKey());
		limitBurstForm.setTransactionId(limitBurstForm.getTransactionId());
		limitBurstForm.setReqAmt(limitBurstForm.getReqAmt());
		limitBurstForm.setTenure(limitBurstForm.getTenure());

		model.put("user", user);
		model.put("limitBurstForm", limitBurstForm);

		return new ModelAndView("poApplyLimitConfirm", "model", model);
	}

	@RequestMapping(value = "/poApplyLimitPost", method = RequestMethod.POST)
	public ModelAndView poApplyLimitPost(@ModelAttribute LimitBurstForm limitBurstForm, ModelMap model, RedirectAttributes attributes) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		LimitBurst limit = new LimitBurst();

		limit.setCustomerHeadName(limitBurstForm.getCustomerHeadName());
		limit.setCustomerEmail(limitBurstForm.getCustomerEmail());
		limit.setSupplierName(limitBurstForm.getSupplierName());
		limit.setMasterKey(limitBurstForm.getMasterKey());
		limit.setPoKey(limitBurstForm.getPoKey());
		limit.setTransactionId(limitBurstForm.getTransactionId());
		limit.setReqAmt(limitBurstForm.getReqAmt());
		limit.setTenure(limitBurstForm.getTenure());
		limit.setbStatus("Pending");

		limitBurstDAO.insertLimitBurst(limit);

		Transaction trans = new Transaction();
		trans.setTransactionId(limitBurstForm.getTransactionId());
		trans.setTransactionStatus("Limit Burst");
		trans.setTransactionType("Submitted Successfully");

		transcationDAOImpl.insertTransaction(trans);

		model.put("user", user);
		model.put("limitBurstForm", limitBurstForm);

		return new ModelAndView("poApplyLimitTransaction", "model", model);
	}

	@RequestMapping(value = "/poApplyLimitTransaction", method = RequestMethod.GET)
	public ModelAndView wcAssignmentTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("limitBurstForm", limitBurstForm);

		return new ModelAndView("poApplyLimitTransaction", "model", model);

	}

	@RequestMapping(value = "/limitBurstCustList", method = RequestMethod.GET)
	public ModelAndView limitBurstCustList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<LimitBurst> limit = limitBurstDAO.getByName(user.getUserName()).getResultList();

		model.put("user", user);
		if (limit != null && limit.size() > 0) {
			model.put("limit", limit);

			return new ModelAndView("limitBurstCustList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	/**
	 * Method to change rate of supplier and cancel PO/close PO based on Status
	 *
	 * @param supplierForm
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/updateRateForSupplier", method = RequestMethod.POST)
	public String updateRateForSupplier(ModelMap model, @ModelAttribute SupplierForm supplierForm) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		Supplier supplier = supplierDAOImpl.findBySupplierName(supplierForm.getSupplierName());

		supplier.setRate((supplier.getRate() + supplierForm.getRate()) / 2);

		supplierDAOImpl.update(supplier);

		if (supplierForm.getStatus().equals(Constants.CLOSE)) {

			return "redirect:closePo?id=" + supplierForm.getId();

		} else if (supplierForm.getStatus().equals(Constants.CANCEL)) {
			PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(supplierForm.getId());

			purchase.setcComment(supplierForm.getComment());

			purchaseOrderDAO.updatePo(purchase);

			return "redirect:cancelPo?id=" + supplierForm.getId();
		}

		return null;
	}

	/**
	 * Method to change rate of buyer and close Invoice
	 *
	 * @param supplierForm
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/updateRateForBuyer", method = RequestMethod.POST)
	public String updateRateForBuyer(ModelMap model, @ModelAttribute NewBuyerForm newBuyerForm) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		NewBuyer buyer = newBuyerDAOImpl.findByBuyerName(newBuyerForm.getBuyerName());

		buyer.setRate((buyer.getRate() + newBuyerForm.getRate()) / 2);

		newBuyerDAOImpl.update(buyer);

		return "redirect:closeInvoice?id=" + newBuyerForm.getId();

	}

	/**
	 * Method to generate Warehouse Chart
	 */
	@RequestMapping(value = "/generateWarehouseChart", method = RequestMethod.GET)
	public ModelAndView generateWarehouseChart(@ModelAttribute WareHouseForm wareHouseForm, ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouse> warehouseList = wareHouseDAO.getWareHouseandStatusList(user.getUserName()).getResultList();

		model.put("user", user);
		model.put("warehouseList", warehouseList);

		return new ModelAndView("generateWarehouseChart", "model", model);
	}

	/**
	 * Method to generate Warehouse Chart
	 */
	@RequestMapping(value = "/generateWarehouseChartById", method = RequestMethod.POST)
	public String generateWarehouseChartById(@ModelAttribute WareHouseForm wareHouseForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		Map<WareHouse, List<Inventory>> warehouseChart = new LinkedHashMap<>();

		if (wareHouseForm.getIds() != null) {
			for (long id : wareHouseForm.getIds()) {
				WareHouse wareHouse = wareHouseDAO.getByWareHouseId(id);
				List<Inventory> inventoryList = invenrotyDAO.getInventoryListByWareHouseNCustomerName(wareHouse.getWareHouseName(), user.getUserName());

				warehouseChart.put(wareHouse, inventoryList);
			}
		}
		attributes.addFlashAttribute("warehouseChart", warehouseChart);

		return "redirect:generateWarehouseChart";
	}

	/**
	 * Method to display all goods for current user to upload image
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/uploadImageForGoods", method = RequestMethod.GET)
	public ModelAndView uploadImageForGoods(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<Inventory> inventoryList = invenrotyDAO.getInventoryBycustomerName(user.getUserName()).getResultList();
		for (Inventory inventory : inventoryList) {
			if (inventory.getImageName() != null) {
				String type = ImageService.getImageType(inventory.getImageName());

				String url = "data:image/" + type + ";base64," + Base64.encodeBase64String(inventory.getImage());
				inventory.setImageName(url);
			}
		}

		model.put("user", user);
		model.put("inventoryList", inventoryList);
		model.put("inventoryForm", inventoryForm);
		return new ModelAndView("uploadImageForGoods", "model", model);

	}

	/**
	 * Method to save uploaded image for goods
	 *
	 * @param inventoryForm
	 * @return
	 */
	@RequestMapping(value = "/updateImageForGoods", method = RequestMethod.POST)
	public String updateImageForGoods(ModelMap model, @ModelAttribute InventoryForm inventoryForm, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		try {
			Inventory inventory = invenrotyDAO.getByInventoryId(inventoryForm.getId());
			inventory.setImage(inventoryForm.getFile().getBytes());
			inventory.setImageName(inventoryForm.getFile().getOriginalFilename());
			invenrotyDAO.updateInventory(inventory);

		} catch (Exception e) {
			e.getMessage();
		}
		if (inventoryForm.getFlag() == 0) {
			wareHouseForm.setId(inventoryForm.getWarehouseId());

			attributes.addFlashAttribute("wareHouseForm", wareHouseForm);
			return "redirect:wareHouseImageDisplayById";
		}
		return "redirect:uploadImageForGoods";
	}

	@RequestMapping(value = "/reportsListHead", method = RequestMethod.GET)
	public ModelAndView reportsListBank(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("purchaseOrderForm", purchaseOrderForm);
		model.put("user", user);

		return new ModelAndView("reportsListHead", "model", model);

	}

	@RequestMapping(value = "/reportsPOHeadPost", method = RequestMethod.POST)
	public ModelAndView reportsPOBankPost(@ModelAttribute PurchaseOrderForm purchaseOrderForm, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PurchaseOrder> purchaseList = purchaseOrderDAO.getPOByHeadCategoryAndDate(user.getUserName(), purchaseOrderForm.getGoodsCategory(), purchaseOrderForm.getFromDate(), purchaseOrderForm.getToDate());

		if (purchaseList != null && purchaseList.size() > 0) {
			purchaseOrderForm.setPurchaseList(purchaseList);

			int range = DateService.getDaysBetweenTwoDates(purchaseOrderForm.getFromDate(), purchaseOrderForm.getToDate());
			double mean = Math.round(range / purchaseList.size());
			String mode = purchaseOrderDAO.getModeByQuantity(user.getUserName(), purchaseOrderForm.getGoodsCategory(), purchaseOrderForm.getFromDate(), purchaseOrderForm.getToDate());
			double median = 0.0;
			if (purchaseList.size() % 2 == 0) {
				median = Math.round((Double.parseDouble(purchaseList.get(purchaseList.size() / 2).getQuantity()) + Double.parseDouble(purchaseList.get(purchaseList.size() / 2 - 1).getQuantity())) / 2);
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

		return new ModelAndView("reportsListHeadView", "model", model);

	}

	@RequestMapping(value = "/reportsListHeadView", method = RequestMethod.GET)
	public ModelAndView reportsListBankView(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("purchaseOrderForm", purchaseOrderForm);
		model.put("user", user);

		return new ModelAndView("reportsListHeadView", "model", model);

	}

	@RequestMapping(value = "/reportsListHeadBying", method = RequestMethod.GET)
	public ModelAndView reportsListBankBying(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("invoiceForm", invoiceForm);
		model.put("user", user);

		return new ModelAndView("reportsListHeadBying", "model", model);

	}

	@RequestMapping(value = "/reportsHeadBuyingPost", method = RequestMethod.POST)
	public ModelAndView reportsPOBankPost(@ModelAttribute InvoiceForm invoiceForm, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		List<Invoice> invoiceList = invoiceDAO.getInvoiceReports(user.getUserName(), invoiceForm.getGoodsCategory(), invoiceForm.getFromDate(), invoiceForm.getToDate());

		if (invoiceList != null && invoiceList.size() > 0) {
			invoiceForm.setInvoiceList(invoiceList);

			int range = DateService.getDaysBetweenTwoDates(invoiceForm.getFromDate(), invoiceForm.getToDate());
			double mean = Math.round(range / invoiceList.size());
			String mode = invoiceDAO.getModeByQuantity(user.getUserName(), invoiceForm.getGoodsCategory(), invoiceForm.getFromDate(), invoiceForm.getToDate());
			double median = 0.0;
			if (invoiceList.size() % 2 == 0) {
				median = Math.round((Double.parseDouble(invoiceList.get(invoiceList.size() / 2).getQuantity()) + Double.parseDouble(invoiceList.get(invoiceList.size() / 2 - 1).getQuantity())) / 2);
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
		return new ModelAndView("reportsListHeadBuyingView", "model", model);

	}

	@RequestMapping(value = "/reportsListHeadBuyingView", method = RequestMethod.GET)
	public ModelAndView reportsListBankBuyingView(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("invoiceForm", invoiceForm);
		model.put("user", user);

		return new ModelAndView("reportsListHeadBuyingView", "model", model);

	}

	/* Existing Stock */

	@RequestMapping(value = "/existingStockHeadPo", method = RequestMethod.GET)
	public ModelAndView existingStockPo(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PoStock> poStockList = poStockDAO.getPoStockList(user.getUserName()).getResultList();
		List<String> goodsList = purchaseOrderDAO.getPurchaseGoodsListByCustomer(user.getUserName());
		
		ArrayList<String> goods = new ArrayList<String>();
		 
		for (String element : goodsList) {
			if (!goods.contains(element)) {
				goods.add(element);
			}
		}
	
		if(goods!= null) {
			Collections.sort(goods);
			model.put("goods", goods);
		}

		poStockForm.setCustomerName(user.getUserName());
		poStockForm.setCustomerHeadName(user.getCustomerHeadName());

		List<WareHouse> whList = wareHouseDAO.getWareHouseandStatusList(user.getUserName()).getResultList();
		if (whList != null) {
			poStockForm.setWareHouseList(whList);

			model.put("poStockForm", poStockForm);

		}

		model.put("user", user);
		model.put("poStockForm", poStockForm);
		model.put("poStockList", poStockList);

		return new ModelAndView("existingStockHeadPo", "model", model);

	}

	@RequestMapping(value = "/selectExistingStockHeadPo", method = RequestMethod.GET)
	public ModelAndView selectExistingStockPo(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		PoStock po = poStockDAO.getByPoStockId(id);

		poStockForm.setId(po.getId());
		poStockForm.setCustomerName(po.getCustomerName());
		poStockForm.setCustomerHeadName(po.getCustomerHeadName());
		poStockForm.setOverAllQuantity(po.getOverAllQuantity());
		poStockForm.setQuantity(po.getQuantity());
		poStockForm.setUsedQuantity(po.getUsedQuantity());
		poStockForm.setDamaged(po.getDamaged());
		poStockForm.setGoodsName(po.getGoodsName());

		List<WareHouse> whList = wareHouseDAO.getWareHouseandStatusList(user.getUserName()).getResultList();
		if (whList != null) {
			poStockForm.setWareHouseList(whList);

			model.put("poStockForm", poStockForm);
			model.put("user", user);
		}

		model.put("user", user);

		model.put("poStockForm", poStockForm);

		return new ModelAndView("selectExistingStockHeadPo", "model", model);

	}

	@RequestMapping(value = "/updateExistingStockHeadPoConfirm", method = RequestMethod.POST)
	public ModelAndView updatExistingStockPoConfirm(@ModelAttribute PoStockForm poStockForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		if (poStockForm.getOverAllQuantity() < 0) {
			model.put("user", user);
			attributes.addFlashAttribute("success", "Total Should Not be Less than Zero");
			return new ModelAndView("redirect:existingStockHeadPo");
		} else {
			poStockForm.setId(poStockForm.getId());
			poStockForm.setCustomerName(poStockForm.getCustomerName());
			poStockForm.setCustomerHeadName(poStockForm.getCustomerHeadName());
			poStockForm.setOverAllQuantity(poStockForm.getOverAllQuantity());
			poStockForm.setWareHouseName(poStockForm.getWareHouseName());
			poStockForm.setQuantity(poStockForm.getQuantity());
			poStockForm.setUsedQuantity(poStockForm.getUsedQuantity());
			poStockForm.setDamaged(poStockForm.getDamaged());
			poStockForm.setGoodsName(poStockForm.getGoodsName());
		}

		model.put("user", user);

		model.put("poStockForm", poStockForm);

		return new ModelAndView("updateExistingStockHeadPoConfirm", "model", model);

	}

	@RequestMapping(value = "/updateExistingStockHeadPo", method = RequestMethod.POST)
	public ModelAndView updateExistingStockPo(ModelMap model, @ModelAttribute PoStockForm poStockForm, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		
		
		PoStock po = poStockDAO.getByPoStockId(poStockForm.getId());

		po.setId(poStockForm.getId());
		po.setCustomerName(poStockForm.getCustomerName());
		po.setCustomerHeadName(poStockForm.getCustomerHeadName());
		po.setOverAllQuantity(poStockForm.getOverAllQuantity());
		po.setWareHouseName(poStockForm.getWareHouseName());
		po.setQuantity(poStockForm.getQuantity());
		po.setUsedQuantity(poStockForm.getUsedQuantity());
		po.setDamaged(poStockForm.getDamaged());
		po.setGoodsName(poStockForm.getGoodsName());
		po.setTransactionId(poStockForm.getTransactionId());

		poStockDAO.updatePoStock(po);

		return new ModelAndView("redirect:existingStockHeadPo");

	}

	@RequestMapping(value = "/existingStockHeadPoShow", method = RequestMethod.POST)
	public ModelAndView existingStockHeadPoShow(@ModelAttribute PoStockForm poStockForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		if (poStockForm.getOverAllQuantity() < 0) {
			model.put("user", user);
			attributes.addFlashAttribute("success", "Total Should Not be Less than Zero");
			return new ModelAndView("redirect:existingStockHeadPo");
		} else {

			model.put("user", user);
			model.put("poStockForm", poStockForm);

			return new ModelAndView("existingStockHeadPoConfirm", "model", model);
		}
	}

	@RequestMapping(value = "/existingStockHeadPoConfirm", method = RequestMethod.POST)
	public ModelAndView existingStockPoConfirm(@ModelAttribute PoStockForm poStockForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		List<PoStock> po = poStockDAO.getPoStockGoodsName(poStockForm.getGoodsName(), user.getUserName()).getResultList();

		if (po.size() != 0) {
			model.put("user", user);
			attributes.addFlashAttribute("success", "Goods Already Exists");

			return new ModelAndView("redirect:existingStockHeadPo");
		} else {

			poStockForm.setCustomerName(poStockForm.getCustomerName());
			poStockForm.setCustomerHeadName(poStockForm.getCustomerHeadName());
			poStockForm.setWareHouseName(poStockForm.getWareHouseName());
			poStockForm.setOverAllQuantity(poStockForm.getOverAllQuantity());
			poStockForm.setQuantity(poStockForm.getQuantity());
			poStockForm.setUsedQuantity(poStockForm.getUsedQuantity());
			poStockForm.setDamaged(poStockForm.getDamaged());
			poStockForm.setGoodsName(poStockForm.getGoodsName());
			poStockForm.setTransactionId(poStockForm.getTransactionId());

			model.put("poStockForm", poStockForm);
			model.put("user", user);

			return new ModelAndView("existingStockHeadPoConfirm", "model", model);
		}
	}

	@RequestMapping(value = "/existingStockHeadPoSave", method = RequestMethod.POST)
	public ModelAndView existingStockPoSave(@ModelAttribute PoStockForm poStockForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		
      List<PoStock>  existingPO = poStockDAO.getPoStockGoodsName(poStockForm.getGoodsName(),user.getUserName()).getResultList();
		
		if (existingPO.size() != 0) {
			model.put("user", user);
			attributes.addFlashAttribute("success", "Goods Already Exists");

			return new ModelAndView("redirect:existingStockPo");
		} else {
			
		
		poStockForm.setTransactionId(KeyGenerator.generateTransactionKey());

		PoStock po = new PoStock();

		po.setCustomerName(poStockForm.getCustomerName());
		po.setCustomerHeadName(poStockForm.getCustomerHeadName());
		po.setWareHouseName(poStockForm.getWareHouseName());
		po.setOverAllQuantity(poStockForm.getOverAllQuantity());
		po.setQuantity(poStockForm.getQuantity());
		po.setUsedQuantity(poStockForm.getUsedQuantity());
		po.setDamaged(poStockForm.getDamaged());
		po.setGoodsName(poStockForm.getGoodsName());
		po.setTransactionId(poStockForm.getTransactionId());

		poStockDAO.insertPoStock(po);

		Transaction trans = new Transaction();

		trans.setTransactionId(poStockForm.getTransactionId());
		trans.setTransactionStatus("Po Stock  Saved");
		trans.setTransactionType("Po Stock");
		;
		transcationDAOImpl.insertTransaction(trans);

		model.put("poStockForm", poStockForm);
		model.put("user", user);

		return new ModelAndView("existingStockHeadPoTransaction", "model", model);
		}

	}

	@RequestMapping(value = "/existingStockHeadPoTransaction", method = RequestMethod.GET)
	public ModelAndView existingStockPoTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("poStockForm", poStockForm);

		return new ModelAndView("existingStockHeadPoTransaction", "model", model);

	}

	@RequestMapping(value = "/existingStockHeadPoFullList", method = RequestMethod.GET)
	public ModelAndView existingStockPoFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PoStock> poList = poStockDAO.getPoStockList(user.getUserName()).getResultList();

		Collection<Inventory> inventoryList = invenrotyDAO.getList();

		List<PoStockForm> stockList = new ArrayList<PoStockForm>();
		if (poList != null && poList.size() > 0) {
			for (PoStock po : poList) {
				if (inventoryList != null && inventoryList.size() > 0) {
					for (Inventory inventory : inventoryList) {
						if (po.getGoodsName().equals(inventory.getGoods())) {
							PoStockForm poStockForm = new PoStockForm();
							poStockForm.setId(po.getId());
							poStockForm.setQuantity(inventory.getTotal());
							poStockForm.setGoodsName(po.getGoodsName());
							poStockForm.setCustomerName(po.getCustomerName());
							poStockForm.setOverAllQuantity(po.getOverAllQuantity());
							stockList.add(poStockForm);
						} else {
							PoStockForm poStockForm = new PoStockForm();
							poStockForm.setId(po.getId());
							poStockForm.setQuantity(inventory.getTotal());
							poStockForm.setGoodsName(po.getGoodsName());
							poStockForm.setCustomerName(po.getCustomerName());
							poStockForm.setOverAllQuantity(po.getOverAllQuantity());
							stockList.add(poStockForm);
						}
					}
				} else {
					PoStockForm poStockForm = new PoStockForm();
					poStockForm.setId(po.getId());
					poStockForm.setGoodsName(po.getGoodsName());
					poStockForm.setCustomerName(po.getCustomerName());
					poStockForm.setOverAllQuantity(po.getOverAllQuantity());
					poStockForm.setQuantity(po.getOverAllQuantity());
					stockList.add(poStockForm);
				}
			}

		}

		model.put("user", user);
		if (stockList != null && stockList.size() > 0) {
			model.put("stockList", stockList);

			return new ModelAndView("existingStockHeadPoFullList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/existingStockHeadInvoice", method = RequestMethod.GET)
	public ModelAndView existingStockHeadInvoice(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<InvoiceStock> poStockList = invoiceStockDAO.getInvoiceStockList(user.getUserName()).getResultList();

		invoiceStockForm.setCustomerName(user.getUserName());
		invoiceStockForm.setCustomerHeadName(user.getCustomerHeadName());

		List<WareHouse> whList = wareHouseDAO.getWareHouseandStatusList(user.getUserName()).getResultList();
		if (whList != null) {
			invoiceStockForm.setWareHouseList(whList);

			model.put("poStockForm", poStockForm);

		}

		model.put("user", user);
		model.put("invoiceStockForm", invoiceStockForm);
		model.put("poStockList", poStockList);

		return new ModelAndView("existingStockHeadInvoice", "model", model);

	}

	@RequestMapping(value = "/selectExistingStockHeadInvoice", method = RequestMethod.GET)
	public ModelAndView selectExistingStockHeadInvoice(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		InvoiceStock invoice = invoiceStockDAO.getByInvoiceStockId(id);

		invoiceStockForm.setId(invoice.getId());
		invoiceStockForm.setCustomerName(invoice.getCustomerName());
		invoiceStockForm.setCustomerHeadName(invoice.getCustomerHeadName());
		invoiceStockForm.setOverAllQuantity(invoice.getOverAllQuantity());
		invoiceStockForm.setQuantity(invoice.getQuantity());
		invoiceStockForm.setUsedQuantity(invoice.getUsedQuantity());
		invoiceStockForm.setDamaged(invoice.getDamaged());
		invoiceStockForm.setGoodsName(invoice.getGoodsName());

		List<WareHouse> whList = wareHouseDAO.getWareHouseandStatusList(user.getUserName()).getResultList();
		if (whList != null) {
			invoiceStockForm.setWareHouseList(whList);

			model.put("invoiceStockForm", invoiceStockForm);
			model.put("user", user);
		}

		model.put("user", user);

		model.put("invoiceStockForm", invoiceStockForm);

		return new ModelAndView("selectExistingStockHeadInvoice", "model", model);

	}

	@RequestMapping(value = "/updateExistingStockHeadInvoiceConfirm", method = RequestMethod.POST)
	public ModelAndView updateExistingStockHeadInvoiceConfirm(@ModelAttribute InvoiceStockForm invoiceStockForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		if (invoiceStockForm.getOverAllQuantity() < 0) {
			model.put("user", user);
			attributes.addFlashAttribute("success", "Total Should Not be Less than Zero");
			return new ModelAndView("redirect:existingStockHeadInvoice");
		} else {

			invoiceStockForm.setId(invoiceStockForm.getId());
			invoiceStockForm.setCustomerName(invoiceStockForm.getCustomerName());
			invoiceStockForm.setCustomerHeadName(invoiceStockForm.getCustomerHeadName());
			invoiceStockForm.setOverAllQuantity(invoiceStockForm.getOverAllQuantity());
			invoiceStockForm.setWareHouseName(invoiceStockForm.getWareHouseName());
			invoiceStockForm.setQuantity(invoiceStockForm.getQuantity());
			invoiceStockForm.setUsedQuantity(invoiceStockForm.getUsedQuantity());
			invoiceStockForm.setDamaged(invoiceStockForm.getDamaged());
			invoiceStockForm.setGoodsName(invoiceStockForm.getGoodsName());
		}

		model.put("user", user);

		model.put("invoiceStockForm", invoiceStockForm);

		return new ModelAndView("updateExistingStockHeadInvoiceConfirm", "model", model);

	}

	@RequestMapping(value = "/updateExistingStockHeadInvoice", method = RequestMethod.POST)
	public ModelAndView updateExistingStockHeadInvoice(ModelMap model, @ModelAttribute InvoiceStockForm invoiceStockForm, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		InvoiceStock po = invoiceStockDAO.getByInvoiceStockId(invoiceStockForm.getId());

		po.setId(invoiceStockForm.getId());
		po.setCustomerName(invoiceStockForm.getCustomerName());
		po.setCustomerHeadName(invoiceStockForm.getCustomerHeadName());
		po.setOverAllQuantity(invoiceStockForm.getOverAllQuantity());
		po.setWareHouseName(invoiceStockForm.getWareHouseName());
		po.setQuantity(invoiceStockForm.getQuantity());
		po.setUsedQuantity(invoiceStockForm.getUsedQuantity());
		po.setDamaged(invoiceStockForm.getDamaged());
		po.setGoodsName(invoiceStockForm.getGoodsName());
		po.setTransactionId(invoiceStockForm.getTransactionId());

		invoiceStockDAO.updateInvoiceStock(po);

		return new ModelAndView("redirect:existingStockHeadInvoice");

	}

	@RequestMapping(value = "/existingStockHeadInvoiceShow", method = RequestMethod.POST)
	public ModelAndView existingStockHeadInvoiceShow(@ModelAttribute InvoiceStockForm invoiceStockForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		if (invoiceStockForm.getOverAllQuantity() < 0) {
			model.put("user", user);
			attributes.addFlashAttribute("success", "Total Should Not be Less than Zero");
			return new ModelAndView("redirect:existingStockHeadInvoice");
		} else {

			model.put("user", user);
			model.put("invoiceStockForm", invoiceStockForm);

			return new ModelAndView("existingStockHeadInvoiceConfirm", "model", model);
		}
	}

	@RequestMapping(value = "/existingStockHeadInvoiceConfirm", method = RequestMethod.POST)
	public ModelAndView existingStockHeadInvoiceConfirm(@ModelAttribute InvoiceStockForm invoiceStockForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		List<InvoiceStock> po = invoiceStockDAO.getInvoiceStockGoodsName(invoiceStockForm.getGoodsName(), user.getUserName()).getResultList();

		if (po.size() != 0) {
			model.put("user", user);
			attributes.addFlashAttribute("success", "Goods Already Exists");

			return new ModelAndView("redirect:existingStockHeadInvoice");
		} else {

			invoiceStockForm.setCustomerName(invoiceStockForm.getCustomerName());
			invoiceStockForm.setCustomerHeadName(invoiceStockForm.getCustomerHeadName());
			invoiceStockForm.setWareHouseName(invoiceStockForm.getWareHouseName());
			invoiceStockForm.setOverAllQuantity(invoiceStockForm.getOverAllQuantity());
			invoiceStockForm.setQuantity(invoiceStockForm.getQuantity());
			invoiceStockForm.setUsedQuantity(invoiceStockForm.getUsedQuantity());
			invoiceStockForm.setDamaged(invoiceStockForm.getDamaged());
			invoiceStockForm.setGoodsName(invoiceStockForm.getGoodsName());
			invoiceStockForm.setTransactionId(invoiceStockForm.getTransactionId());

			model.put("invoiceStockForm", invoiceStockForm);
			model.put("user", user);

			return new ModelAndView("existingStockHeadInvoiceConfirm", "model", model);
		}
	}

	@RequestMapping(value = "/existingStockHeadInvoiceSave", method = RequestMethod.POST)
	public ModelAndView existingStockHeadInvoiceSave(@ModelAttribute InvoiceStockForm invoiceStockForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();


		invoiceStockForm.setTransactionId(KeyGenerator.generateTransactionKey());

		InvoiceStock po = new InvoiceStock();

		po.setCustomerName(invoiceStockForm.getCustomerName());
		po.setCustomerHeadName(invoiceStockForm.getCustomerHeadName());
		po.setWareHouseName(invoiceStockForm.getWareHouseName());
		po.setOverAllQuantity(invoiceStockForm.getOverAllQuantity());
		po.setQuantity(invoiceStockForm.getQuantity());
		po.setUsedQuantity(invoiceStockForm.getUsedQuantity());
		po.setDamaged(invoiceStockForm.getDamaged());
		po.setGoodsName(invoiceStockForm.getGoodsName());
		po.setTransactionId(invoiceStockForm.getTransactionId());
		;

		invoiceStockDAO.insertInvoiceStock(po);

		Transaction trans = new Transaction();

		trans.setTransactionId(invoiceStockForm.getTransactionId());
		trans.setTransactionStatus("Invoice Stock  Saved");
		trans.setTransactionType("Invoice Stock");
		;
		transcationDAOImpl.insertTransaction(trans);

		model.put("invoiceStockForm", invoiceStockForm);
		model.put("user", user);

		return new ModelAndView("existingStockHeadInvoiceTransaction", "model", model);

	}

	@RequestMapping(value = "/existingStockHeadInvoiceTransaction", method = RequestMethod.GET)
	public ModelAndView existingStockHeadInvoiceTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("invoiceStockForm", invoiceStockForm);

		return new ModelAndView("existingStockHeadInvoiceTransaction", "model", model);

	}

	@RequestMapping(value = "/existingStockHeadInvoiceFullList", method = RequestMethod.GET)
	public ModelAndView existingStockHeadInvoiceFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<InvoiceStock> poList = invoiceStockDAO.getInvoiceStockList(user.getUserName()).getResultList();

		Collection<InvoiceInventory> inventoryList = invoiceInventoryDAO.getList();

		List<InvoiceStockForm> stockList = new ArrayList<InvoiceStockForm>();
		if (poList != null && poList.size() > 0) {
			for (InvoiceStock po : poList) {
				if (inventoryList != null && inventoryList.size() > 0) {
					for (InvoiceInventory inventory : inventoryList) {
						if (po.getGoodsName().equals(inventory.getGoods())) {
							InvoiceStockForm poStockForm = new InvoiceStockForm();
							poStockForm.setId(po.getId());
							poStockForm.setQuantity(inventory.getTotal());
							poStockForm.setGoodsName(po.getGoodsName());
							poStockForm.setCustomerName(po.getCustomerName());
							poStockForm.setOverAllQuantity(po.getOverAllQuantity());
							stockList.add(poStockForm);
						} else {
							InvoiceStockForm poStockForm = new InvoiceStockForm();
							poStockForm.setId(po.getId());
							poStockForm.setQuantity(inventory.getTotal());
							poStockForm.setGoodsName(po.getGoodsName());
							poStockForm.setCustomerName(po.getCustomerName());
							poStockForm.setOverAllQuantity(po.getOverAllQuantity());
							stockList.add(poStockForm);
						}
					}
				} else {
					InvoiceStockForm poStockForm = new InvoiceStockForm();
					poStockForm.setId(po.getId());
					poStockForm.setGoodsName(po.getGoodsName());
					poStockForm.setCustomerName(po.getCustomerName());
					poStockForm.setOverAllQuantity(po.getOverAllQuantity());
					poStockForm.setQuantity(po.getOverAllQuantity());
					stockList.add(poStockForm);
				}

			}
		}

		model.put("user", user);
		if (stockList != null && stockList.size() > 0) {
			model.put("stockList", stockList);

			return new ModelAndView("existingStockHeadInvoiceFullList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	/**
	 * Method to generate Existing stock PO Chart
	 */
	@RequestMapping(value = "/existingStockPOInventoryChart", method = RequestMethod.GET)
	public ModelAndView existingStockPOInventoryChart(@ModelAttribute WareHouseForm wareHouseForm, ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouse> warehouseList = wareHouseDAO.getWareHouseandStatusList(user.getUserName()).getResultList();

		model.put("user", user);
		model.put("warehouseList", warehouseList);

		return new ModelAndView("existingStockPOInventoryChartHead", "model", model);
	}

	/**
	 * Method to generate Warehouse Chart
	 */
	@RequestMapping(value = "/existingStockPOInventoryChartById", method = RequestMethod.POST)
	public String existingStockPOInventoryChartById(@ModelAttribute WareHouseForm wareHouseForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		Map<WareHouse, List<PoStock>> warehouseChart = new LinkedHashMap<>();

		if (wareHouseForm.getIds() != null) {
			for (long id : wareHouseForm.getIds()) {
				WareHouse wareHouse = wareHouseDAO.getByWareHouseId(id);
				List<PoStock> poList = poStockDAO.getPoStockByCustomerNWarehouse(user.getUserName(), wareHouse.getWareHouseName());

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
	public ModelAndView existingStockInvoiceInventoryChart(@ModelAttribute WareHouseForm wareHouseForm, ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouse> warehouseList = wareHouseDAO.getWareHouseandStatusList(user.getUserName()).getResultList();

		model.put("user", user);
		model.put("warehouseList", warehouseList);

		return new ModelAndView("existingStockInvoiceInventoryChartHead", "model", model);
	}

	/**
	 * Method to generate Existing stock Invoice Chart
	 */
	@RequestMapping(value = "/existingStockInvoiceInventoryChartById", method = RequestMethod.POST)
	public String existingStockInvoiceInventoryChartById(@ModelAttribute WareHouseForm wareHouseForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		Map<WareHouse, List<InvoiceStock>> warehouseChart = new LinkedHashMap<>();

		if (wareHouseForm.getIds() != null) {
			for (long id : wareHouseForm.getIds()) {
				WareHouse wareHouse = wareHouseDAO.getByWareHouseId(id);
				List<InvoiceStock> invoiceList = invoiceStockDAO.getInvoiceStockByCustomerNWarehouse(user.getUserName(), wareHouse.getWareHouseName());

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
	public ModelAndView generateInvoiceInventoryChartBranch(@ModelAttribute WareHouseForm wareHouseForm, ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouse> warehouseList = wareHouseDAO.getWareHouseandStatusList(user.getUserName()).getResultList();

		model.put("user", user);
		model.put("warehouseList", warehouseList);

		return new ModelAndView("generateInvoiceInventoryChart", "model", model);
	}

	/**
	 * Method to generate Invoice inventory Chart
	 */
	@RequestMapping(value = "/generateInvoiceInventoryChartBranchById", method = RequestMethod.POST)
	public String generateInvoiceInventoryChartBranchById(@ModelAttribute WareHouseForm wareHouseForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		Map<WareHouse, List<InvoiceInventory>> warehouseChart = new LinkedHashMap<>();

		if (wareHouseForm.getIds() != null) {
			for (long id : wareHouseForm.getIds()) {
				WareHouse wareHouse = wareHouseDAO.getByWareHouseId(id);
				List<InvoiceInventory> inventoryList = invoiceInventoryDAO.getInvoiceInventoryByCustomerNWarehouse(user.getUserName(), wareHouse.getWareHouseName());

				warehouseChart.put(wareHouse, inventoryList);
			}
		}
		attributes.addFlashAttribute("warehouseChart", warehouseChart);

		return "redirect:generateInvoiceInventoryChartBranch";
	}

	@RequestMapping(value = "/custHelp", method = RequestMethod.GET)
	public ModelAndView adminHelp(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("custHelp", "model", model);

	}

	/**
	 * Display warehouse image
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wareHouseImageDisplay", method = RequestMethod.GET)
	public ModelAndView wareHouseImageDisplay(@ModelAttribute WareHouseForm wareHouseForm, @ModelAttribute InventoryForm inventoryForm, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		List<WareHouse> warehouseList = wareHouseDAO.getWareHouseandStatusList(user.getUserName()).getResultList();

		model.put("user", user);
		model.put("warehouseList", warehouseList);

		return new ModelAndView("wareHouseImageDisplay", "model", model);

	}

	/**
	 * Display warehouse image
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wareHouseImageDisplayById", method = { RequestMethod.POST, RequestMethod.GET })
	public String wareHouseImageDisplayById(@ModelAttribute WareHouseForm wareHouseForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		if (wareHouseForm.getId() != null) {
			WareHouse wareHouse = wareHouseDAO.getByWareHouseId(wareHouseForm.getId());

			wareHouseForm.setWareHouseName(wareHouse.getWareHouseName());
			wareHouseForm.setAddress(wareHouse.getAddress());
			wareHouseForm.setCapacity(wareHouse.getCapacity());
			wareHouseForm.setCountry(wareHouse.getCountry());
			wareHouseForm.setState(wareHouse.getState());
			wareHouseForm.setSize(wareHouse.getSize());

			List<Inventory> existingInventoryList = invenrotyDAO.getInventoryListByWareHouseNCustomerName(wareHouse.getWareHouseName(), user.getUserName());
			List<Inventory> inventoryList = new ArrayList<Inventory>();
			for (Inventory inventory : existingInventoryList) {
				if (inventory.getImageName() != null) {
					String type = ImageService.getImageType(inventory.getImageName());

					String url = "data:image/" + type + ";base64," + Base64.encodeBase64String(inventory.getImage());
					inventory.setImageName(url);
				}
				inventoryList.add(inventory);
			}

			attributes.addFlashAttribute("inventoryList", inventoryList);

		}
		wareHouseForm.setStatus(Constants.UNBLOCK);

		attributes.addFlashAttribute("wareHouseForm", wareHouseForm);
		attributes.addFlashAttribute("inventoryForm", inventoryForm);

		return "redirect:wareHouseImageDisplay";
	}

	@RequestMapping(value = "/addOrModifyReportCustList", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportCustList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<InvoiceReports> disputeList = invoiceReportsDAO.getInvoiceReportsOnCustAndAcceptList(user.getUserName()).getResultList();

		if (disputeList != null && disputeList.size() > 0) {
			model.put("disputeList", disputeList);
			return new ModelAndView("addOrModifyReportCustList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/addOrModifyReportInvoiceAccept", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportInvoiceAccept(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		InvoiceReports dispute = invoiceReportsDAO.getByInvoiceReportsId(id);

		dispute.setCustAccept("Accepted");
		dispute.setSuppSurveyorName("Accepted");
		dispute.setSuppSurveyorCom("Accepted");
		dispute.setSuppSurveyorAdd("Accepted");
		dispute.setSuppSurveyorEmail("Accepted");
		dispute.setSuppSurveyorPhone("Accepted");
		dispute.setSuppDefectType("Accepted");
		dispute.setSuppDefectQty(dispute.getDefectQty());
		dispute.setSuppDefectCostForGoods(dispute.getDefectCostForGoods());
		dispute.setSuppDamageStatus("Accepted");
		dispute.setSuppReplacement("Accepted");
		dispute.setSuppRepairCost(dispute.getRepairCost());

		invoiceReportsDAO.updateInvoiceReports(dispute);

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		attributes.addFlashAttribute("success", "Accepted Successfully");

		return new ModelAndView("redirect:addOrModifyReportCustList");
	}

	@RequestMapping(value = "/addOrModifyReportInvoice", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportInvoice(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		InvoiceReports dispute = invoiceReportsDAO.getByInvoiceReportsId(id);

		disputeReportsForm.setCustomerName(dispute.getCustomerName());
		disputeReportsForm.setPokey(dispute.getPokey());
		disputeReportsForm.setBuyerName(dispute.getBuyerName());
		disputeReportsForm.setGoods(dispute.getGoods());
		disputeReportsForm.setInsDetails(dispute.getInsDetails());
		disputeReportsForm.setCost(dispute.getCost());
		disputeReportsForm.setGoodsDetails(dispute.getGoodsDetails());
		disputeReportsForm.setTransactionId(dispute.getTransactionId());
		disputeReportsForm.setInvoicekey(dispute.getInvoicekey());
		disputeReportsForm.setInStartDate(dispute.getStartDate());
		disputeReportsForm.setInsEndDate(dispute.getEndDate());

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		return new ModelAndView("addOrModifyReportInvoice", "model", model);
	}

	@RequestMapping(value = "/addOrModifyReportInvoiceConfirm", method = RequestMethod.POST)
	public ModelAndView addOrModifyReportInvoiceConfirm(ModelMap model, @ModelAttribute DisputeReportsForm disputeReportsForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		disputeReportsForm.setSurveyorName(disputeReportsForm.getSurveyorName());
		disputeReportsForm.setSurveyorCom(disputeReportsForm.getSurveyorCom());
		disputeReportsForm.setSurveyorAdd(disputeReportsForm.getSurveyorAdd());
		disputeReportsForm.setSurveyorEmail(disputeReportsForm.getSurveyorEmail());
		disputeReportsForm.setTransportType(disputeReportsForm.getTransportType());
		disputeReportsForm.setSurveyorPhone(disputeReportsForm.getSurveyorPhone());
		disputeReportsForm.setDefectType(disputeReportsForm.getDefectType());
		disputeReportsForm.setDefectQty(disputeReportsForm.getDefectQty());
		disputeReportsForm.setDefectCostForGoods(disputeReportsForm.getDefectCostForGoods());
		disputeReportsForm.setDamageStatus(disputeReportsForm.getDamageStatus());
		disputeReportsForm.setReplacement(disputeReportsForm.getReplacement());
		disputeReportsForm.setRepairCost(disputeReportsForm.getRepairCost());
		disputeReportsForm.setInvoicekey(disputeReportsForm.getInvoicekey());

		model.put("disputeReportsForm", disputeReportsForm);
		model.put("user", user);

		return new ModelAndView("addOrModifyReportInvoiceConfirm", "model", model);

	}

	@RequestMapping(value = "/addOrModifyReportInvoicePost", method = RequestMethod.POST)
	public ModelAndView addOrModifyReportInvoicePost(ModelMap model, @ModelAttribute DisputeReportsForm disputeReportsForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();
		List<InvoiceReports> dis = invoiceReportsDAO.getInvoiceReportsList(disputeReportsForm.getInvoicekey()).getResultList();
		if (dis != null && dis.size() > 0) {
			InvoiceReports disp = invoiceReportsDAO.getInvoiceReportsList(disputeReportsForm.getInvoicekey()).getSingleResult();

			disp.setSuppSurveyorName(disputeReportsForm.getSuppSurveyorName());
			disp.setSuppSurveyorCom(disputeReportsForm.getSuppSurveyorCom());
			disp.setSuppSurveyorAdd(disputeReportsForm.getSuppSurveyorAdd());
			disp.setSuppSurveyorEmail(disputeReportsForm.getSuppSurveyorEmail());
			disp.setSuppSurveyorPhone(disputeReportsForm.getSuppSurveyorPhone());
			disp.setSuppDefectType(disputeReportsForm.getSuppDefectType());
			disp.setSuppDefectQty(disputeReportsForm.getSuppDefectQty());
			disp.setSuppDefectCostForGoods(disputeReportsForm.getSuppDefectCostForGoods());
			disp.setSuppDamageStatus(disputeReportsForm.getSuppDamageStatus());
			disp.setSuppReplacement(disputeReportsForm.getSuppReplacement());
			disp.setSuppRepairCost(disputeReportsForm.getSuppRepairCost());
			disp.setCustAccept("NotAccepted");
			disp.setStatus("Pending");

			invoiceReportsDAO.updateInvoiceReports(disp);

		}

		Transaction trans = new Transaction();
		trans.setTransactionId(disputeReportsForm.getTransactionId());
		trans.setTransactionStatus("Dispute");
		trans.setTransactionType("Submitted Successfully");

		model.put("disputeReportsForm", disputeReportsForm);
		model.put("user", user);

		return new ModelAndView("addOrModifyReportInvoiceTrans", "model", model);

	}

	@RequestMapping(value = "/addOrModifyReportInvoiceTrans", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportInvoiceTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		return new ModelAndView("addOrModifyReportInvoiceTrans", "model", model);

	}

	@RequestMapping(value = "/addOrModifyReportInvoiceFullList", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportVendorFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<InvoiceReports> disputeList = invoiceReportsDAO.getInvoiceReportsVendorList(user.getUserName()).getResultList();

		if (disputeList != null && disputeList.size() > 0) {
			model.put("disputeList", disputeList);
			return new ModelAndView("addOrModifyReportInvoiceFullList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}

	}

	@RequestMapping(value = "/addOrModifyReportInvoiceView", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportInvoiceView(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

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

		return new ModelAndView("addOrModifyReportInvoiceView", "model", model);
	}

	@RequestMapping(value = "/addOrModifyReportInvoiceCompare", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportInvoiceCompare(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

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

		return new ModelAndView("addOrModifyReportInvoiceCompare", "model", model);
	}

	@RequestMapping(value = "/invoiceListForRenewel", method = RequestMethod.GET)
	public ModelAndView InvoiceListForRenewel(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		List<Invoice> purchase = invoiceDAO.getInoviceListBycustomerNameAndStatus(user.getUserName()).getResultList();

		if (purchase != null && purchase.size() > 0) {
			model.put("purchase", purchase);
			return new ModelAndView("invoiceListForRenewel", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}
	}

	@RequestMapping(value = "/invoiceForRenewel", method = RequestMethod.GET)
	public ModelAndView InvoiceForRenewel(@RequestParam("id") Long id, ModelMap model) {

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
		invoiceForm.setPoKey(invoice.getPoKey());
		invoiceForm.setInsuranceAmount(invoice.getInsuranceAmount());
		invoiceForm.setInsuranceDetails(invoice.getInsuranceDetails());
		invoiceForm.setInsuranceType(invoice.getInsuranceType());
		invoiceForm.setSentDate(invoice.getStartDate());
		invoiceForm.setEndDate(invoice.getEndDate());
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("invoiceForRenewel", "model", model);

	}

	@RequestMapping(value = "/invoiceForRenewelConfirm", method = RequestMethod.POST)
	public ModelAndView InvoiceForRenewelConfirm(ModelMap model, @ModelAttribute InvoiceForm invoiceForm, BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		invoiceForm.setCustomerName(invoiceForm.getCustomerName());
		invoiceForm.setCustomerHeadName(invoiceForm.getCustomerHeadName());
		invoiceForm.setMasterKey(invoiceForm.getMasterKey());
		invoiceForm.setGoods(invoiceForm.getGoods());
		invoiceForm.setQuantity(invoiceForm.getQuantity());
		invoiceForm.setBuyerName(invoiceForm.getBuyerName());

		invoiceForm.setTransactionId(invoiceForm.getTransactionId());
		invoiceForm.setAmount(invoiceForm.getAmount());
		invoiceForm.setCustomerHeadEmail(invoiceForm.getCustomerHeadEmail());
		invoiceForm.setCustomerBranchEmail(invoiceForm.getCustomerBranchEmail());
		invoiceForm.setBuyerEmail(invoiceForm.getBuyerEmail());
		invoiceForm.setPoKey(invoiceForm.getPoKey());
		invoiceForm.setInsuranceAmount(invoiceForm.getInsuranceAmount());
		invoiceForm.setInsuranceDetails(invoiceForm.getInsuranceDetails());
		invoiceForm.setInsuranceType(invoiceForm.getInsuranceType());
		invoiceForm.setSentDate(invoiceForm.getStartDate());
		invoiceForm.setEndDate(invoiceForm.getEndDate());

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("invoiceForRenewelConfirm", "model", model);

	}

	@RequestMapping(value = "/invoiceForRenewelPost", method = RequestMethod.POST)
	public ModelAndView invoiceForRenewelPost(ModelMap model, @ModelAttribute InvoiceForm invoiceForm, BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Invoice invoice = invoiceDAO.getByInvoiceId(invoiceForm.getId());

		invoice.setInsuranceAmount(invoiceForm.getInsuranceAmount());
		invoice.setInsuranceDetails(invoiceForm.getInsuranceDetails());
		invoice.setInsuranceType(invoiceForm.getInsuranceType());
		invoice.setSentDate(invoiceForm.getStartDate());
		invoice.setEndDate(invoiceForm.getEndDate());
		invoiceDAO.updateInvoice(invoice);

		Transaction trans = new Transaction();
		trans.setTransactionId(invoiceForm.getTransactionId());
		trans.setTransactionStatus("Invoice Insurance Renewal By Vendor");
		trans.setTransactionType("Submitted Successfully");

		transcationDAOImpl.insertTransaction(trans);

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("invoiceForRenewelTransaction", "model", model);
	}

	@RequestMapping(value = "/invoiceForRenewelTransaction", method = RequestMethod.GET)
	public ModelAndView invoiceForRenewelTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("invoiceForRenewelTransaction", "model", model);

	}

	/**
	 * Method to check if the category and country are banned or restricted
	 *
	 * @param country
	 * @param category
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkRegulations", method = RequestMethod.POST)
	public String checkRegulations(@RequestParam String country, @RequestParam String category) {
		String result = "";
		List<Regulation> regulationsList = regulationsDAO.getRegulationsByCountryNCategory(country, category);
		if (regulationsList != null && regulationsList.size() > 0) {
			if (regulationsList.get(0).getRegulation().equals(Constants.RESTRICTED)) {
				result = category + " is " + regulationsList.get(0).getRegulation() + " for " + country + ".Please upload support documents";
			} else {
				result = category + " is " + regulationsList.get(0).getRegulation() + " for " + country;
			}

		}

		return result;

	}

	@RequestMapping(value = "/licenseOnRestricted", method = RequestMethod.GET)
	public ModelAndView licenseOnRestricted(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("restrictedLicenseForm", restrictedLicenseForm);

		return new ModelAndView("licenseOnRestricted", "model", model);

	}

	@RequestMapping(value = "/licenseOnRestrictedConfirm", method = RequestMethod.POST)
	public ModelAndView licenseOnRestrictedConfirm(ModelMap model, @ModelAttribute RestrictedLicenseForm restrictedLicenseForm, BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		restrictedLicenseForm.setCustomer(user.getUserName());
		restrictedLicenseForm.setLicenseDetails(restrictedLicenseForm.getLicenseDetails());
		restrictedLicenseForm.setStartDate(restrictedLicenseForm.getStartDate());
		restrictedLicenseForm.setEndDate(restrictedLicenseForm.getEndDate());
		restrictedLicenseForm.setGoodsName(restrictedLicenseForm.getGoodsName());
		restrictedLicenseForm.setQty(restrictedLicenseForm.getQty());

		model.put("user", user);
		model.put("restrictedLicenseForm", restrictedLicenseForm);

		return new ModelAndView("licenseOnRestrictedConfirm", "model", model);
	}

	@RequestMapping(value = "/licenseOnRestrictedPost", method = RequestMethod.POST)
	public ModelAndView licenseOnRestrictedPost(ModelMap model, @ModelAttribute RestrictedLicenseForm restrictedLicenseForm, BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		RestrictedLicense license = new RestrictedLicense();

		license.setCustomer(user.getUserName());
		license.setLicenseDetails(restrictedLicenseForm.getLicenseDetails());
		license.setStartDate(restrictedLicenseForm.getStartDate());
		license.setEndDate(restrictedLicenseForm.getEndDate());
		license.setGoodsName(restrictedLicenseForm.getGoodsName());
		license.setQty(restrictedLicenseForm.getQty());
		license.setTotalQty(restrictedLicenseForm.getQty());
		license.setTransId(restrictedLicenseForm.getTransId());
		license.setStatus("Pending");

		restrictedLicenseDAO.createRestrictedLicense(license);

		model.put("user", user);
		model.put("restrictedLicenseForm", restrictedLicenseForm);

		return new ModelAndView("licenseOnRestrictedTrans", "model", model);
	}

	@RequestMapping(value = "/licenseOnRestrictedTrans", method = RequestMethod.GET)
	public ModelAndView licenseOnRestrictedTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("restrictedLicenseForm", restrictedLicenseForm);

		return new ModelAndView("licenseOnRestrictedTrans", "model", model);

	}

	@RequestMapping(value = "/licenseOnRestrictedHeadList", method = RequestMethod.GET)
	public ModelAndView licenseOnRestrictedHeadList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<RestrictedLicense> license = restrictedLicenseDAO.getRestrictedLicenseOnCustList(user.getUserName()).getResultList();

		model.put("user", user);
		model.put("license", license);

		return new ModelAndView("licenseOnRestrictedHeadList", "model", model);

	}

	@RequestMapping(value = "/createWareHouseMng", method = RequestMethod.GET)
	public ModelAndView createClientAdmin(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouseMng> mngList = wareHouseMngDAO.getByPending(user.getUserName()).getResultList();

		model.put("user", user);
		if (mngList != null && mngList.size() > 0) {

			model.put("mngList", mngList);

		}
		model.put("wareHouseMngForm", wareHouseMngForm);
		return new ModelAndView("createWareHouseMng", "model", model);

	}

	@RequestMapping(value = "/selectWareHouseMng", method = RequestMethod.GET)
	public ModelAndView selectWareHouseMng(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		WareHouseMng customer = wareHouseMngDAO.getByWareHouseMngId(id);

		wareHouseMngForm.setId(customer.getId());
		wareHouseMngForm.setMngName(customer.getMngName());
		wareHouseMngForm.setCompanyName(customer.getCompanyName());
		wareHouseMngForm.setCountry(customer.getCountry());
		wareHouseMngForm.setState(customer.getState());
		wareHouseMngForm.setCity(customer.getCity());
		wareHouseMngForm.setAddress(customer.getAddress());
		wareHouseMngForm.setPincode(customer.getPincode());
		wareHouseMngForm.setContactNum(customer.getContactNum());
		wareHouseMngForm.setAltContactNum(customer.getAltContactNum());
		wareHouseMngForm.setEmail(customer.getEmail());
		wareHouseMngForm.setAltEmail(customer.getAltEmail());
		wareHouseMngForm.setCompanyPrefix(customer.getCompanyPrefix());
		wareHouseMngForm.setPosition(customer.getPosition());
		wareHouseMngForm.setGender(customer.getGender());
		wareHouseMngForm.setDateOfBirth(customer.getDateOfBirth());
		wareHouseMngForm.setCustomerPrefix(customer.getCustomerPrefix());

		model.put("user", user);

		model.put("wareHouseMngForm", wareHouseMngForm);

		return new ModelAndView("selectWareHouseMng", "model", model);

	}

	@RequestMapping(value = "/updateWareHouseMngConfirm", method = RequestMethod.POST)
	public ModelAndView updateClientAdminConfirm(@ModelAttribute WareHouseMngForm wareHouseMngForm, ModelMap model, RedirectAttributes attribute) {
		EndUser user = getCurrentLoggedUserDetails();

		wareHouseMngForm.setId(wareHouseMngForm.getId());
		wareHouseMngForm.setMngName(wareHouseMngForm.getMngName());
		wareHouseMngForm.setCompanyName(wareHouseMngForm.getCompanyName());
		wareHouseMngForm.setCountry(wareHouseMngForm.getCountry());
		wareHouseMngForm.setState(wareHouseMngForm.getState());
		wareHouseMngForm.setCity(wareHouseMngForm.getCity());
		wareHouseMngForm.setAddress(wareHouseMngForm.getAddress());
		wareHouseMngForm.setPincode(wareHouseMngForm.getPincode());
		wareHouseMngForm.setContactNum(wareHouseMngForm.getContactNum());
		wareHouseMngForm.setAltContactNum(wareHouseMngForm.getAltContactNum());
		wareHouseMngForm.setEmail(wareHouseMngForm.getEmail());
		wareHouseMngForm.setAltEmail(wareHouseMngForm.getAltEmail());
		wareHouseMngForm.setCompanyPrefix(wareHouseMngForm.getCompanyPrefix());
		wareHouseMngForm.setPosition(wareHouseMngForm.getPosition());
		wareHouseMngForm.setGender(wareHouseMngForm.getGender());
		wareHouseMngForm.setDateOfBirth(wareHouseMngForm.getDateOfBirth());
		wareHouseMngForm.setCustomerPrefix(wareHouseMngForm.getCustomerPrefix());
		wareHouseMngForm.setManager(wareHouseMngForm.getManager());
		wareHouseMngForm.setManagerEmail(wareHouseMngForm.getManagerEmail());

		model.put("wareHouseMngForm", wareHouseMngForm);
		model.put("user", user);

		return new ModelAndView("updateWareHouseMngConfirm", "model", model);

	}

	@RequestMapping(value = "/updateWareHouseMng", method = RequestMethod.POST)
	public ModelAndView updateClientAdmin(ModelMap model, @ModelAttribute WareHouseMngForm wareHouseMngForm, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		WareHouseMng customer = wareHouseMngDAO.getByWareHouseMngId(wareHouseMngForm.getId());

		customer.setMngName(wareHouseMngForm.getMngName());
		customer.setCompanyName(wareHouseMngForm.getCompanyName());
		customer.setCountry(wareHouseMngForm.getCountry());
		customer.setState(wareHouseMngForm.getState());
		customer.setCity(wareHouseMngForm.getCity());
		customer.setAddress(wareHouseMngForm.getAddress());
		customer.setPincode(wareHouseMngForm.getPincode());
		customer.setContactNum(wareHouseMngForm.getContactNum());
		customer.setAltContactNum(wareHouseMngForm.getAltContactNum());
		customer.setEmail(wareHouseMngForm.getEmail());
		customer.setAltEmail(wareHouseMngForm.getAltEmail());
		customer.setCompanyPrefix(wareHouseMngForm.getCompanyPrefix());
		customer.setPosition(wareHouseMngForm.getPosition());
		customer.setGender(wareHouseMngForm.getGender());
		customer.setDateOfBirth(wareHouseMngForm.getDateOfBirth());
		customer.setCustomerPrefix(wareHouseMngForm.getCustomerPrefix());
		customer.setManager(wareHouseMngForm.getManager());
		customer.setManagerEmail(wareHouseMngForm.getManagerEmail());
		customer.setStatus("Pending");


		wareHouseMngDAO.updateUser(customer);

		return new ModelAndView("redirect:createWareHouseMng");

	}

	@RequestMapping(value = "/wareHouseMngConfirm", method = RequestMethod.POST)
	public ModelAndView wareHouseMngConfirm(@ModelAttribute WareHouseMngForm wareHouseMngForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouseMng> head = wareHouseMngDAO.getWareHouseMngList(wareHouseMngForm.getMngName()).getResultList();

		List<EndUser> endUser = endUserDAOImpl.findByUsername(wareHouseMngForm.getMngName()).getResultList();
		if (endUser.size() != 0 || head.size() != 0) {
			model.put("user", user);
			attributes.addFlashAttribute("success", "WareHouse Manager Already Exists");

			return new ModelAndView("redirect:createWareHouseMng");
		} else {

			wareHouseMngForm.setMngName(wareHouseMngForm.getMngName());
			wareHouseMngForm.setCompanyName(wareHouseMngForm.getCompanyName());
			wareHouseMngForm.setCountry(wareHouseMngForm.getCountry());
			wareHouseMngForm.setState(wareHouseMngForm.getState());
			wareHouseMngForm.setCity(wareHouseMngForm.getCity());
			wareHouseMngForm.setAddress(wareHouseMngForm.getAddress());
			wareHouseMngForm.setPincode(wareHouseMngForm.getPincode());
			wareHouseMngForm.setContactNum(wareHouseMngForm.getContactNum());
			wareHouseMngForm.setAltContactNum(wareHouseMngForm.getAltContactNum());
			wareHouseMngForm.setEmail(wareHouseMngForm.getEmail());
			wareHouseMngForm.setAltEmail(wareHouseMngForm.getAltEmail());
			wareHouseMngForm.setCompanyPrefix(wareHouseMngForm.getCompanyPrefix());
			wareHouseMngForm.setPosition(wareHouseMngForm.getPosition());
			wareHouseMngForm.setGender(wareHouseMngForm.getGender());
			wareHouseMngForm.setDateOfBirth(wareHouseMngForm.getDateOfBirth());
			wareHouseMngForm.setCustomerPrefix(wareHouseMngForm.getCustomerPrefix());
			wareHouseMngForm.setManager(wareHouseMngForm.getManager());
			wareHouseMngForm.setManagerEmail(wareHouseMngForm.getManagerEmail());
			wareHouseMngForm.setAccExpiryDate(wareHouseMngForm.getAccExpiryDate());

			model.put("wareHouseMngForm", wareHouseMngForm);
			model.put("user", user);

			return new ModelAndView("wareHouseMngConfirm", "model", model);
		}
	}

	@RequestMapping(value = "/wareHouseMngSave", method = RequestMethod.POST)
	public ModelAndView wareHouseMngSave(@ModelAttribute WareHouseMngForm wareHouseMngForm, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		wareHouseMngForm.setTransactionId(KeyGenerator.generateTransactionKey());

		WareHouseMng customer = new WareHouseMng();
		customer.setMngName(wareHouseMngForm.getMngName());
		customer.setCustomerName(user.getUserName());
		customer.setCompanyName(wareHouseMngForm.getCompanyName());
		customer.setCountry(wareHouseMngForm.getCountry());
		customer.setState(wareHouseMngForm.getState());
		customer.setCity(wareHouseMngForm.getCity());
		customer.setAddress(wareHouseMngForm.getAddress());
		customer.setPincode(wareHouseMngForm.getPincode());
		customer.setContactNum(wareHouseMngForm.getContactNum());
		customer.setAltContactNum(wareHouseMngForm.getAltContactNum());
		customer.setEmail(wareHouseMngForm.getEmail());
		customer.setAltEmail(wareHouseMngForm.getAltEmail());
		customer.setCompanyPrefix(wareHouseMngForm.getCompanyPrefix());
		customer.setPosition(wareHouseMngForm.getPosition());
		customer.setGender(wareHouseMngForm.getGender());
		customer.setDateOfBirth(wareHouseMngForm.getDateOfBirth());
		customer.setManager(wareHouseMngForm.getManager());
		customer.setManagerEmail(wareHouseMngForm.getManagerEmail());
		customer.setNotificationStatus("Pending");
		customer.setStatus("Pending");
		customer.setbStatus("Pending");
		customer.setCustomerPrefix(wareHouseMngForm.getCustomerPrefix());
		customer.setTransactionId(wareHouseMngForm.getTransactionId());
		customer.setAccExpiryDate(wareHouseMngForm.getAccExpiryDate());
		wareHouseMngDAO.insertWareHouseMng(customer);
		Transaction trans = new Transaction();

		trans.setTransactionId(wareHouseMngForm.getTransactionId());
		trans.setTransactionStatus("WareHouse Manager Saved");
		trans.setTransactionType("WareHouse Manager");

		transactionDAO.insertTransaction(trans);

		model.put("wareHouseMngForm", wareHouseMngForm);
		model.put("user", user);

		return new ModelAndView("wareHouseMngTransaction", "model", model);

	}

	@RequestMapping(value = "/wareHouseMngTransaction", method = RequestMethod.GET)
	public ModelAndView wareHouseMngTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("wareHouseMngForm", wareHouseMngForm);

		return new ModelAndView("wareHouseMngTransaction", "model", model);

	}

	@RequestMapping(value = "/wareHouseMngList", method = RequestMethod.GET)
	public ModelAndView wareHouseMngList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouseMng> mngList = wareHouseMngDAO.getWareHouseMngFullList(user.getUserName()).getResultList();

		model.put("user", user);
		model.put("mngList", mngList);

		return new ModelAndView("wareHouseMngList", "model", model);

	}

	@RequestMapping(value = "/getLoginDate", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody Date loginDate() {

		return DateService.loginDate;
	}

	@RequestMapping(value = "/loginDate", method = RequestMethod.GET)
	public ModelAndView loginDate(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		Date date=dateService.getCurrentSavedLoginDate();

		if(date!=null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = dateFormat.format(date);
			loginDateForm.setLoginDateString(dateString);
		}


//		List<WareHouseMng> mngList = wareHouseMngDAO.getWareHouseMngFullList(user.getUserName()).getResultList();
		model.put("loginDateForm",loginDateForm);
		model.put("user", user);
//		model.put("mngList", mngList);

		return new ModelAndView("loginDate", "model", model);

	}

	@RequestMapping(value = "/saveLoginDate", method = RequestMethod.POST)
	public ModelAndView saveLoginDate(@ModelAttribute LoginDateForm loginDateForm, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		LoginDate loginDate = loginDateDao.getLoginDate();

		SimpleDateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date date = dateFormat.parse(loginDateForm.getLoginDateString());

//			System.out.println(date);
			loginDateForm.setLoginDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (loginDate != null) {
			loginDate.setLoginDate(loginDateForm.getLoginDate());
			loginDateDao.updateLoginDate(loginDate);
		} else {
			LoginDate login = new LoginDate();
			login.setLoginDate(loginDateForm.getLoginDate());
			loginDateDao.insertLoginDate(login);
		}

		model.addAttribute("error", "Date Saved !!!");

		return new ModelAndView("loginDate", "model", model);

	}

	@RequestMapping(value = "/getMaterials", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getMaterials(@RequestParam(value="supplier") String supplier) {

		return buyingCostDAO.getMaterials(supplier);
	}

	@RequestMapping(value = "/generateInvoice", method = RequestMethod.GET)
	public ModelAndView generateInvoice(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		List<BuyerPO> buyerList = buyerPODAO.getBuyerPOByHeadNameAndRemainingInvoices(user.getUserName()).getResultList();
		if (buyerList != null && buyerList.size() > 0) {
			model.put("buyerList", buyerList);
			return new ModelAndView("headBuyerPoListForInvoice", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCus", "model", model);
		}
	}

	@RequestMapping(value = "/buyerPoConfirm", method = RequestMethod.POST)
	public ModelAndView buyerPoConfirm(@RequestParam("id") Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		BuyerPO buyerPO= buyerPODAO.getById(id).getSingleResult();

//		buyerPOForm.setName(buyerPO.getBuyerName());
		buyerPOForm.setCustomerHeadName(salesOrderForm.getCustomerHeadName());
		buyerPOForm.setEmail(salesOrderForm.getBuyerEmail());
		buyerPOForm.setCustomerHeadEmail(salesOrderForm.getCustomerHeadEmail());
		buyerPOForm.setPurchaseDate(salesOrderForm.getStartDate());
		buyerPOForm.setGoods(salesOrderForm.getGoods());
		buyerPOForm.setGoodsCategory(salesOrderForm.getGoodsCategory());
		buyerPOForm.setGoodsDetails(salesOrderForm.getGoodsDetails());
		buyerPOForm.setQuantity(salesOrderForm.getQuantity());
		buyerPOForm.setWeight(salesOrderForm.getWeight());
		buyerPOForm.setPoKey(salesOrderForm.getPoKey());
		buyerPOForm.setTenure(salesOrderForm.getTenure());
		buyerPOForm.setAmount(salesOrderForm.getAmount());


		model.put("user", user);
		model.put("buyerPOForm", buyerPOForm);

		return new ModelAndView("buyerPoConfirm", "model", model);

	}

	@RequestMapping(value = "/generateInvoiceFromPO", method = RequestMethod.GET)
	public ModelAndView generateInvoiceFromPO(@RequestParam("id") Long buyerPOId, ModelMap model) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		// Format the date using the SimpleDateFormat object
//		String formattedDate = dateFormat.format(currentDate);

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		BuyerPO buyerPO= buyerPODAO.getById(buyerPOId).getSingleResult();
		try {
//	            String formattedDateStr = outputFormat.format(inputFormat.parse(inputFormat.format(buyerPO.getPurchaseDate())));
//	             buyerPO.setPurchaseDate( outputFormat.parse(formattedDateStr));
			String formattedDate = dateFormat.format(buyerPO.getPurchaseDate());
			model.put("purchaseDate", formattedDate);

		} catch (Exception e) {
			e.printStackTrace();
		}
		SalesOrder salesOrder=salesOrderDAO.getBySalesOrderId(buyerPO.getSalesOrderId());

		masterPlanForm= new MasterPlanForm();
		masterPlanForm.setMasterKey(salesOrder.getMasterKey());
		masterPlanForm.setCustomer(salesOrder.getCustomerName());
		masterPlanForm.setQuantity(Float.parseFloat(salesOrder.getQuantity()));
		masterPlanForm.setServiceTax(salesOrder.getServiceTax());
		masterPlanForm.setDutytax(salesOrder.getDutytax());
		masterPlanForm.setVattax(salesOrder.getVattax());


		NewBuyer buyer = newBuyerDAOImpl.findByBuyerName(salesOrder.getBuyerName());
		newBuyerForm.setId(buyer.getId());
		newBuyerForm.setBuyerName(buyer.getBuyerName());
		newBuyerForm.setEmail(buyer.getEmail());
		newBuyerForm.setBank(buyer.getBank());
		newBuyerForm.setBankEmail(buyer.getBankEmail());
		newBuyerForm.setCountry(buyer.getCountry());
		newBuyerForm.setState(buyer.getState());
		newBuyerForm.setCity(buyer.getCity());
//		SellerBuyingCost  sellerbuyingcost = sellerBuyingCostDAO.getSellerBuyingCostBuyerName(buyer.getBuyerName(), masterPlanForm.getMasterKey() );
		purchaseOrderForm.setGoodsCategory(buyerPO.getGoodsCategory());
		purchaseOrderForm.setGoods(buyerPO.getGoods());
		purchaseOrderForm.setGoodsDetails(buyerPO.getGoodsDetails());
		purchaseOrderForm.setWeight(buyerPO.getWeight());
		purchaseOrderForm.setQuantity(buyerPO.getQuantity().toString());
		purchaseOrderForm.setPoKey(buyerPO.getPoKey().toString());
		purchaseOrderForm.setLicence(salesOrder.getLicence());
//		purchaseOrderForm.setPurchaseDate(buyerPO.getPurchaseDate());

		List<WareHouse> whList = wareHouseDAO.getWareHouseandStatusList(user.getUserName()).getResultList();
		if (whList != null) {
			invoiceForm.setWareHouseList(whList);

			model.put("purchaseOrderForm", purchaseOrderForm);
			model.put("user", user);
		}
		invoiceForm.setInvoiceRemaining(buyerPO.getInvoiceRemaining());
		invoiceForm.setQuantityForInvoice(buyerPO.getInvoiceRemaining());
		invoiceForm.setBuyerPoId(buyerPOId);
		model.put("masterPlanForm", masterPlanForm);
		model.put("newBuyerForm", newBuyerForm);
		model.put("invoiceForm", invoiceForm);
		model.put("user", user);

		return new ModelAndView("generateInvoiceFromPO", "model", model);
	}


	@RequestMapping(value = "/generateInvoiceFromPOConfirm", method = RequestMethod.POST)
	public ModelAndView generateInvoiceFromPOConfirm(ModelMap model, @ModelAttribute InvoiceForm invoiceForm, BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		invoiceForm.setBuyerEmail(invoiceForm.getBuyerEmail());
		invoiceForm.setCustomerHeadEmail(invoiceForm.getCustomerHeadEmail());
		invoiceForm.setBuyerBank(invoiceForm.getBuyerBank());
		invoiceForm.setBuyerBankEmail(invoiceForm.getBuyerBankEmail());
		invoiceForm.setBuyerCountry(invoiceForm.getBuyerCountry());
		invoiceForm.setBuyerState(invoiceForm.getBuyerState());
		invoiceForm.setBuyerCity(invoiceForm.getBuyerCity());
		invoiceForm.setMasterKey(invoiceForm.getMasterKey());
		invoiceForm.setCustomerName(invoiceForm.getCustomerName());
		invoiceForm.setBuyerName(invoiceForm.getBuyerName());
		invoiceForm.setPoDate(invoiceForm.getPoDate());
		invoiceForm.setWareHouseName(invoiceForm.getWareHouseName());
		invoiceForm.setGoodsCategory(invoiceForm.getGoodsCategory());
		invoiceForm.setGoods(invoiceForm.getGoods());
		invoiceForm.setGoodsDetails(invoiceForm.getGoodsDetails());
		invoiceForm.setQuantity(invoiceForm.getQuantity());
		invoiceForm.setWeight(invoiceForm.getWeight());
		invoiceForm.setLicence(invoiceForm.getLicence());
		invoiceForm.setAmount(invoiceForm.getAmount());
		invoiceForm.setPoInfo(invoiceForm.getPoInfo());
		invoiceForm.setPoKey(invoiceForm.getPoKey());
		invoiceForm.setTenure(invoiceForm.getTenure());
		invoiceForm.setServiceTax(invoiceForm.getServiceTax());
		invoiceForm.setDutytax(invoiceForm.getDutytax());
		invoiceForm.setVattax(invoiceForm.getVattax());
		invoiceForm.setAnswer(invoiceForm.getAnswer());
		invoiceForm.setInsuranceAmount(invoiceForm.getInsuranceAmount());
		invoiceForm.setInsuranceDetails(invoiceForm.getInsuranceDetails());
		invoiceForm.setInsuranceType(invoiceForm.getInsuranceType());
		invoiceForm.setStartDate(invoiceForm.getStartDate());
		invoiceForm.setEndDate(invoiceForm.getEndDate());
		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("generateInvoiceFromPOConfirm", "model", model);

	}

	@RequestMapping(value = "/generateInvoiceFromPOPost", method = RequestMethod.POST)
	public ModelAndView generateInvoiceFromPOPost(ModelMap model, @ModelAttribute InvoiceForm invoiceForm, BindingResult result, RedirectAttributes attributes) {

		Invoice invoice = new Invoice();


		invoiceForm.setTransactionId(KeyGenerator.generateTransactionKey());

		invoice.setMasterKey(invoiceForm.getMasterKey());
		invoice.setCustomerName(invoiceForm.getCustomerName());
		invoice.setCustomerHeadName(invoiceForm.getCustomerName());
		invoice.setBuyerBank(invoiceForm.getBuyerBank());
		invoice.setCustomerHeadEmail(invoiceForm.getCustomerHeadEmail());
		invoice.setBuyerName(invoiceForm.getBuyerName());
		invoice.setBuyerEmail(invoiceForm.getBuyerEmail());
		invoice.setPoDate(invoiceForm.getPoDate());
		invoice.setGoodsCategory(invoiceForm.getGoodsCategory());
		invoice.setGoods(invoiceForm.getGoods());
		invoice.setGoodsDetails(invoiceForm.getGoodsDetails());
		invoice.setQuantity(invoiceForm.getQuantityForInvoice());
		invoice.setWeight(invoiceForm.getWeight());
		invoice.setLicence(invoiceForm.getLicence());
		invoice.setAmount(invoiceForm.getAmount());
		invoice.setTransactionId(invoiceForm.getTransactionId());
		invoice.setPoKey(invoiceForm.getPoKey());
		invoice.setWareHouseName(invoiceForm.getWareHouseName());
		WareHouse wareMng = wareHouseDAO.getWareHouseNameList(invoiceForm.getWareHouseName()).getSingleResult();
		invoice.setWhMngName(wareMng.getPersonInCharge());
		invoice.setStatus("Pending");
		invoice.setGoodsStatus("Send");
		invoice.setPoInfo(invoiceForm.getPoInfo());
		invoice.setPoDate(invoiceForm.getPoDate());
		invoice.setTenure(invoiceForm.getTenure());
		invoice.setServiceTax(invoiceForm.getServiceTax());
		invoice.setDutytax(invoiceForm.getDutytax());
		invoice.setVattax(invoiceForm.getVattax());
		invoice.setAnswer(invoiceForm.getAnswer());
		invoice.setInsuranceAmount(invoiceForm.getInsuranceAmount());
		invoice.setInsuranceDetails(invoiceForm.getInsuranceDetails());
		invoice.setInsuranceType(invoiceForm.getInsuranceType());
		invoice.setStartDate(invoiceForm.getStartDate());
		invoice.setEndDate(invoiceForm.getEndDate());
		invoiceDAO.insertInvoice(invoice);

		BuyerPO buyerPO= buyerPODAO.getById(invoiceForm.getBuyerPoId()).getSingleResult();
		buyerPO.setInvoiceRemaining(String.valueOf(Float.parseFloat(buyerPO.getInvoiceRemaining())-Float.parseFloat(invoice.getQuantity())));
		buyerPODAO.updatePo(buyerPO);

		Transaction trans = new Transaction();

		trans.setTransactionId(invoiceForm.getTransactionId());
		trans.setTransactionType("Invoice");
		trans.setTransactionStatus("Submitted successfully");
		transcationDAOImpl.insertTransaction(trans);

		return new ModelAndView("businessPlanForInvoiceTransaction", "model", model);
	}

	@RequestMapping(value = "/listOfSalesOrder", method = RequestMethod.GET)
	public ModelAndView listOfSalesOrder(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		List<SalesOrderForm> salesOrder = salesOrderDAO.getSalesOrderByCustomerAndMasterKey(user.getUserName());


		if (salesOrder != null) {
			model.put("salesOrderForm", salesOrder);
			return new ModelAndView("listOfSalesOrder", "model", model);
		} else {
			return new ModelAndView("noDataFoundBuyer", "model", model);
		}

	}
//	@RequestMapping(value = "/businessPlanForInvoiceTransaction", method = RequestMethod.GET)
//	public ModelAndView businessPlanForInvoiceTransaction(ModelMap model) {
//		EndUser user = getCurrentLoggedUserDetails();
//
//		model.put("user", user);
//		model.put("invoiceForm", invoiceForm);
//
//		return new ModelAndView("businessPlanForInvoiceTransaction", "model", model);
//	}


}
