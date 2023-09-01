package annona.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annona.dao.*;
import annona.domain.*;
import annona.form.*;
import annona.services.DateService;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.theme.CookieThemeResolver;

import annona.services.ImageService;
import annona.utility.Constants;
import annona.utility.KeyGenerator;

@Controller
@RequestMapping("/customerAdmin")
public class CustomerAdminController {

	@Autowired
	EndUserDAO endUserDAOImpl;

	@Autowired
	TransactionDAO transcationDAOImpl;

	@Autowired
	InvoiceDAO invoiceDAO;

	@Autowired
	MasterPlanDAO masterPlanDAO;
	
	@Autowired
	BankDetailsForm bankDetailsForm;

	@Autowired
	BankDetailsDAO bankDetailsDAO;

	@Autowired
	CollateralDAO collateralDAO;

	@Autowired
	BuyingCostDAO buyingCostDAO;

	@Autowired
	NewBuyerDAO newBuyerDAOImpl;

	@Autowired
	SellerBuyingCostDAO sellerBuyingCostDAO;

	@Autowired
	PurchaseOrderDAO purchaseOrderDAO;

	@Autowired
	CustomerHeadForm customerHeadForm;

	@Autowired
	CustomerBranchForm customerBranchForm;
	
	@Autowired
	CustomerBankDetailsForm customerBankDetails;
	
	@Autowired
	CustomerBankDetailsDAO customerBankDetailsDAO;
	
	@Autowired
	CustomerBankDetailsForm customerBankDetailsForm;

	@Autowired
	CustomerSubsidiaryForm customerSubsidiaryForm;

	@Autowired
	CustomerDAO customerDAO;
	
	@Autowired
	CompanyDAO companyDAO;
	
	@Autowired
	ClientAdminDAO clientAdminDAO;

	@Autowired
	SupplierDAO supplierDAO;

	@Autowired
	EndUserForm endUserForm;

	@Autowired
	TransactionDAO transactionDAO;

	@Autowired
	ClientAppMngForm clientAppMngForm;

	@Autowired
	ClientAppMngDAO clientAppMngDAO;

	@Autowired
	SwapAccountDAO swapAccountDAO;

	@Autowired
	WareHouseDAO wareHouseDAO;

	@Autowired
	InventoryDAO inventoryDAO;
	
	@Autowired
	PoStockDAO poStockDAO;
	
	@Autowired
	InvoiceStockDAO invoiceStockDAO;
	
	@Autowired
	InvoiceInventoryDAO invoiceInventoryDAO;
	
	@Autowired
	DisputeReportsDAO disputeReportsDAO;
	
	@Autowired
	InvoiceReportsDAO invoiceReportsDAO;
	
	@Autowired
	DisputeReportsForm disputeReportsForm;
	
	@Autowired
	WareHouseMngDAO wareHouseMngDAO;

	@Autowired
	LoginDateDao loginDateDao;

	@Autowired
	DateService dateService;

	@Autowired
	LoginDateForm loginDateForm;

	CookieThemeResolver themeResolver = new CookieThemeResolver();

	static Logger log = Logger.getLogger(AdminController.class.getName());

	private String getCurrentLoggedUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();

	}

	@ModelAttribute("requestCurrentUser")
	public EndUser getUserDetails() {
		EndUser enUser = getCurrentLoggedUserDetails();
		if(enUser.getImageName() != null) {
			String type= ImageService.getImageType(enUser.getImageName());

			String url = "data:image/"+type+";base64,"
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
	
	@RequestMapping(value = "/custBankDetails", method = RequestMethod.GET)
	public ModelAndView createBankDetails(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		Collection<CustomerBankDetails> customerBankDetails = customerBankDetailsDAO.getList();
		CustomerBankDetailsForm customerBankDetailsForm = new CustomerBankDetailsForm();
		EndUser user = getCurrentLoggedUserDetails();

		model.put("customerBankDetails", customerBankDetails);

		model.put("user", user);

		model.put("customerBankDetailsForm", customerBankDetailsForm);

		return new ModelAndView("custBankDetails", "model", model);
	}
	
	@RequestMapping(value = "/confirmCustBankDetails", method = RequestMethod.POST)
	public ModelAndView insertBankDetails(ModelMap model,
			@ModelAttribute CustomerBankDetailsForm customerBankDetailsForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("customerBankDetailsForm", customerBankDetailsForm);

		return new ModelAndView("confirmCustBankDetails", "model", model);

	}
	
	@RequestMapping(value = "/insertCustBankDetails", method = RequestMethod.POST)
	public ModelAndView notBannedUpdate(ModelMap model,
			@ModelAttribute CustomerBankDetailsForm customerBankDetailsForm) {
		EndUser user = getCurrentLoggedUserDetails();
		
		customerBankDetailsForm.setTransactionId(KeyGenerator.generateTransactionKey());
		
		model.put("user", user);

		CustomerBankDetails customerBankDetails = new CustomerBankDetails();

		Transaction transaction = new Transaction();

		customerBankDetails.setBankName(customerBankDetailsForm.getBankName());

		customerBankDetails.setLocation(customerBankDetailsForm.getLocation());

		customerBankDetails.setBranch(customerBankDetailsForm.getBranch());

		customerBankDetails.setContactNo(customerBankDetailsForm.getContactNo());

		customerBankDetails.setEmail(customerBankDetailsForm.getEmail());
		
		customerBankDetails.setBranchCode(customerBankDetailsForm.getBranchCode());

		customerBankDetails.setTransactionId(customerBankDetailsForm.getTransactionId());

		transaction.setTransactionId(customerBankDetailsForm.getTransactionId());

		transaction.setTransactionType("Customer Bank Details");

		transaction.setTransactionStatus("Customer Bank Details saved successfully");

		customerBankDetailsDAO.insertCustBankDetails(customerBankDetails);

		transcationDAOImpl.insertTransaction(transaction);

		model.put("customerBankDetailsForm", customerBankDetailsForm);

		return new ModelAndView("custBankDetailsSuccess", "model", model);

	}
	
	@RequestMapping(value = "/selectCustBankDetails", method = RequestMethod.GET)
	public ModelAndView selectBankDetails(ModelMap model,
			@RequestParam("id") Long id) {

		EndUser user = getCurrentLoggedUserDetails();

		CustomerBankDetails customerBankDetails = customerBankDetailsDAO.findId(id);

		customerBankDetailsForm.setId(customerBankDetails.getId());
		customerBankDetailsForm.setBankName(customerBankDetails.getBankName());
		customerBankDetailsForm.setLocation(customerBankDetails.getLocation());
		customerBankDetailsForm.setBranch(customerBankDetails.getBranch());
		customerBankDetailsForm.setBranchCode(customerBankDetails.getBranchCode());
		customerBankDetailsForm.setContactNo(customerBankDetails.getContactNo());
		customerBankDetailsForm.setEmail(customerBankDetails.getEmail());
		customerBankDetailsForm.setTransactionId(customerBankDetails.getTransactionId());

		model.put("user", user);

		model.put("customerBankDetailsForm", customerBankDetailsForm);

		return new ModelAndView("selectCustBankDetails", "model", model);

	}
	
	@RequestMapping(value = "/updateCustBDetails", method = RequestMethod.POST)
	public ModelAndView updateBDetails(ModelMap model,
			@ModelAttribute CustomerBankDetailsForm customerBankDetailsForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("customerBankDetailsForm", customerBankDetailsForm);

		return new ModelAndView("confirmUpdateCustBankDetails", "model", model);

	}
	
	@RequestMapping(value = "/updateCustBankDetails", method = RequestMethod.POST)
	public ModelAndView updateBankDetails(ModelMap model,
			@ModelAttribute CustomerBankDetailsForm customerBankDetailsForm,
			BindingResult result) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		CustomerBankDetails customerBankDetails = customerBankDetailsDAO
				.findId(customerBankDetailsForm.getId());

		customerBankDetails.setId(customerBankDetailsForm.getId());

		customerBankDetails.setBankName(customerBankDetailsForm.getBankName());

		customerBankDetails.setLocation(customerBankDetailsForm.getLocation());

		customerBankDetails.setBranch(customerBankDetailsForm.getBranch());

		customerBankDetails.setContactNo(customerBankDetailsForm.getContactNo());
		
		customerBankDetails.setBranchCode(customerBankDetailsForm.getBranchCode());

		customerBankDetails.setEmail(customerBankDetailsForm.getEmail());

		customerBankDetails.setTransactionId(customerBankDetailsForm.getTransactionId());

		customerBankDetailsDAO.update(customerBankDetails);

		model.put("customerBankDetailsForm", customerBankDetailsForm);

		return new ModelAndView("updateCustBankDetailsSuccess", "model", model);

	}

	@RequestMapping(value = "/custAdminCommon", method = RequestMethod.GET)
	public ModelAndView showApprMngCommonDashBoard(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("custAdminCommonPage", "model", model);

	}

	@RequestMapping(value = "/custAdminPage", method = RequestMethod.GET)
	public ModelAndView showadminDashBoard(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("custAdmin", "model", model);
	}

	@RequestMapping(value = "/themeChange", method = RequestMethod.GET)
	public String getThemeChange(ModelMap model,HttpServletRequest request,
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

		return "redirect:custAdminPage";
	}

	@RequestMapping(value = "/getLocaleLang", method = RequestMethod.GET)
	public String getLocaleLang(ModelMap model,HttpServletRequest request,
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
		return "redirect:custAdminPage";
	}

	@RequestMapping(value = "/editCustAminProfile", method = RequestMethod.GET)
	public ModelAndView editAdminProfile(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser userProfile = endUserDAOImpl.findId(id);
		if(userProfile.getImageName() != null) {
			String type= ImageService.getImageType(userProfile.getImageName());

			String url = "data:image/"+type+";base64,"
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

		return new ModelAndView("editCustAminProfile", "model", model);

	}
	
	
	@RequestMapping(value = "/updateImageForProfile", method = RequestMethod.POST)
	public String updateImageForProfile(ModelMap model,
			@ModelAttribute EndUserForm endUserForm) {
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
		return "redirect:editCustAminProfile?id="+endUserForm.getId();
	}

	@RequestMapping(value = "/confirmEditCustAdminProfile", method = RequestMethod.POST)
	public ModelAndView confirmEditAdminProfile(ModelMap model,
			@ModelAttribute EndUserForm endUserForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("confirmEditCustAdminProfile", "model", model);

	}

	@RequestMapping(value = "/updateCustAdminDetails", method = RequestMethod.POST)
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

		return new ModelAndView("updateCustAdminSuccess", "model", model);

	}

	@RequestMapping(value = "/editCustAdminPWD", method = RequestMethod.GET)
	public ModelAndView editAdminPWD(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser userProfile = endUserDAOImpl.findId(id);

		endUserForm.setId(userProfile.getId());

		endUserForm.setTransactionId(userProfile.getTransactionId());

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("editCustAdminPWD", "model", model);

	}

	@RequestMapping(value = "/updateEditCustAdminPWD", method = RequestMethod.POST)
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

		return new ModelAndView("updateCustAdminSuccess", "model", model);

	}

	@RequestMapping(value = "/createClientAppMng", method = RequestMethod.GET)
	public ModelAndView createClientAdmin(ModelMap model) {
		 EndUser user = getCurrentLoggedUserDetails();
		
		List<ClientAppMngForm> appMngList = clientAppMngDAO.getCustAppMngByIdAndAStatusComp(user.getCompanyId());
		
		  List<Company> companyList = companyDAO.getCompanyByStatuss("Approved");
		
		
		 if (companyList != null && companyList.size() > 0) {
			   model.addAttribute("companyList", companyList);
		  }

		model.put("user", user);
		model.put("clientAppMngForm", clientAppMngForm);
		model.put("appMngList", appMngList);

		return new ModelAndView("createClientAppMng", "model", model);

	}

	@RequestMapping(value = "/selectClientAppMng", method = RequestMethod.GET)
	public ModelAndView selectClientAdmin(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		ClientAppMng customer = clientAppMngDAO.getByClientAppMngId(id);
		
		Company company = companyDAO.getByCompanyId(customer.getCompanyId());

		clientAppMngForm.setId(customer.getId());
		clientAppMngForm.setName(customer.getName());
		clientAppMngForm.setCompanyName(company.getCompanyName());
		clientAppMngForm.setCountry(customer.getCountry());
		clientAppMngForm.setState(customer.getState());
		clientAppMngForm.setCity(customer.getCity());
		clientAppMngForm.setAddress(customer.getAddress());
		clientAppMngForm.setPincode(customer.getPincode());
		clientAppMngForm.setContactNum(customer.getContactNum());
		clientAppMngForm.setAltContactNum(customer.getAltContactNum());
		clientAppMngForm.setEmail(customer.getEmail());
		clientAppMngForm.setAltEmail(customer.getAltEmail());
		clientAppMngForm.setCompanyPrefix(customer.getCompanyPrefix());
		clientAppMngForm.setPosition(customer.getPosition());
		clientAppMngForm.setGender(customer.getGender());
		clientAppMngForm.setDateOfBirth(customer.getDateOfBirth());
		clientAppMngForm.setCustomerPrefix(customer.getCustomerPrefix());

		model.put("user", user);

		model.put("clientAppMngForm", clientAppMngForm);

		return new ModelAndView("selectClientAppMng", "model", model);

	}

	@RequestMapping(value = "/updateClientAppMngConfirm", method = RequestMethod.POST)
	public ModelAndView updateClientAppMngConfirm(
			@ModelAttribute ClientAppMngForm clientAppMngForm, ModelMap model,
			RedirectAttributes attribute) {
		EndUser user = getCurrentLoggedUserDetails();
		
		Company company = companyDAO.getByCompanyName(clientAppMngForm.getCompanyName());

		clientAppMngForm.setId(clientAppMngForm.getId());
		clientAppMngForm.setName(clientAppMngForm.getName());
		clientAppMngForm.setCompanyName(company.getCompanyName());
		clientAppMngForm.setCountry(clientAppMngForm.getCountry());
		clientAppMngForm.setState(clientAppMngForm.getState());
		clientAppMngForm.setCity(clientAppMngForm.getCity());
		clientAppMngForm.setAddress(clientAppMngForm.getAddress());
		clientAppMngForm.setPincode(clientAppMngForm.getPincode());
		clientAppMngForm.setContactNum(clientAppMngForm.getContactNum());
		clientAppMngForm.setAltContactNum(clientAppMngForm.getAltContactNum());
		clientAppMngForm.setEmail(clientAppMngForm.getEmail());
		clientAppMngForm.setAltEmail(clientAppMngForm.getAltEmail());
		clientAppMngForm.setCompanyPrefix(clientAppMngForm.getCompanyPrefix());
		clientAppMngForm.setPosition(clientAppMngForm.getPosition());
		clientAppMngForm.setGender(clientAppMngForm.getGender());
		clientAppMngForm.setDateOfBirth(clientAppMngForm.getDateOfBirth());
		clientAppMngForm
				.setCustomerPrefix(clientAppMngForm.getCustomerPrefix());
		clientAppMngForm.setManager(clientAppMngForm.getManager());
		clientAppMngForm.setManagerEmail(clientAppMngForm.getManagerEmail());

		model.put("clientAppMngForm", clientAppMngForm);
		model.put("user", user);

		return new ModelAndView("updateClientAppMngConfirm", "model", model);

	}

	@RequestMapping(value = "/updateClientAppMng", method = RequestMethod.POST)
	public ModelAndView updateClientAppMng(ModelMap model,
			@ModelAttribute ClientAppMngForm clientAppMngForm,
			RedirectAttributes attributes) {

		ClientAppMng customer = clientAppMngDAO
				.getByClientAppMngId(clientAppMngForm.getId());
		
		Company company=companyDAO.getByCompanyName(clientAppMngForm.getCompanyName());

		customer.setName(clientAppMngForm.getName());
		//customer.setCompanyName(clientAppMngForm.getCompanyName());
		customer.setCompanyId(company.getId());
		customer.setCountry(clientAppMngForm.getCountry());
		customer.setState(clientAppMngForm.getState());
		customer.setCity(clientAppMngForm.getCity());
		customer.setAddress(clientAppMngForm.getAddress());
		customer.setPincode(clientAppMngForm.getPincode());
		customer.setContactNum(clientAppMngForm.getContactNum());
		customer.setAltContactNum(clientAppMngForm.getAltContactNum());
		customer.setEmail(clientAppMngForm.getEmail());
		customer.setAltEmail(clientAppMngForm.getAltEmail());
		customer.setCompanyPrefix(clientAppMngForm.getCompanyPrefix());
		customer.setPosition(clientAppMngForm.getPosition());
		customer.setGender(clientAppMngForm.getGender());
		customer.setDateOfBirth(clientAppMngForm.getDateOfBirth());
		customer.setCustomerPrefix(clientAppMngForm.getCustomerPrefix());
		customer.setManager(clientAppMngForm.getManager());
		customer.setManagerEmail(clientAppMngForm.getManagerEmail());
		customer.setStatus("Pending");

		clientAppMngDAO.updateUser(customer);

		return new ModelAndView("redirect:createClientAppMng");

	}

	@RequestMapping(value = "/clientAppMngConfirm", method = RequestMethod.POST)
	public ModelAndView clientAppMngConfirm(
			@ModelAttribute ClientAppMngForm clientAppMngForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		List<ClientAppMng> head = clientAppMngDAO.getClientAppMngList(
				clientAppMngForm.getName()).getResultList();

		List<EndUser> endUser = endUserDAOImpl.findByUsername(
				clientAppMngForm.getName()).getResultList();
		Company company = companyDAO.getByCompanyId(clientAppMngForm.getCompanyId());
		
		if (endUser.size() != 0 || head.size() != 0) {
			attributes.addFlashAttribute("success",
					"Customer Name Already Exists");

			return new ModelAndView("redirect:createClientAppMng");
		} else {

			clientAppMngForm.setName(clientAppMngForm.getName());
			clientAppMngForm.setCompanyName(company.getCompanyName());
			clientAppMngForm.setCountry(clientAppMngForm.getCountry());
			clientAppMngForm.setState(clientAppMngForm.getState());
			clientAppMngForm.setCity(clientAppMngForm.getCity());
			clientAppMngForm.setAddress(clientAppMngForm.getAddress());
			clientAppMngForm.setPincode(clientAppMngForm.getPincode());
			clientAppMngForm.setContactNum(clientAppMngForm.getContactNum());
			clientAppMngForm.setAltContactNum(clientAppMngForm
					.getAltContactNum());
			clientAppMngForm.setEmail(clientAppMngForm.getEmail());
			clientAppMngForm.setAltEmail(clientAppMngForm.getAltEmail());
			clientAppMngForm.setCompanyPrefix(clientAppMngForm
					.getCompanyPrefix());
			clientAppMngForm.setPosition(clientAppMngForm.getPosition());
			clientAppMngForm.setGender(clientAppMngForm.getGender());
			clientAppMngForm.setDateOfBirth(clientAppMngForm.getDateOfBirth());
			clientAppMngForm.setCustomerPrefix(clientAppMngForm
					.getCustomerPrefix());
			clientAppMngForm.setManager(clientAppMngForm.getManager());
			clientAppMngForm
					.setManagerEmail(clientAppMngForm.getManagerEmail());
			clientAppMngForm.setAccExpiryDate(clientAppMngForm
					.getAccExpiryDate());

			model.put("clientAppMngForm", clientAppMngForm);
			model.put("user", user);

			return new ModelAndView("clientAppMngConfirm", "model", model);
		}
	}

	@RequestMapping(value = "/clientAppMngSave", method = RequestMethod.POST)
	public ModelAndView clientAppMngSave(
			@ModelAttribute ClientAppMngForm clientAppMngForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		
		clientAppMngForm.setTransactionId(KeyGenerator.generateTransactionKey());
		
		Company company = companyDAO.getByCompanyName(clientAppMngForm.getCompanyName());

		ClientAppMng customer = new ClientAppMng();
		customer.setName(clientAppMngForm.getName());
		//customer.setCompanyName(clientAppMngForm.getCompanyName());
		customer.setCompanyId(company.getId());
		customer.setCountry(clientAppMngForm.getCountry());
		customer.setState(clientAppMngForm.getState());
		customer.setCity(clientAppMngForm.getCity());
		customer.setAddress(clientAppMngForm.getAddress());
		customer.setPincode(clientAppMngForm.getPincode());
		customer.setContactNum(clientAppMngForm.getContactNum());
		customer.setAltContactNum(clientAppMngForm.getAltContactNum());
		customer.setEmail(clientAppMngForm.getEmail());
		customer.setAltEmail(clientAppMngForm.getAltEmail());
		customer.setCompanyPrefix(clientAppMngForm.getCompanyPrefix());
		customer.setPosition(clientAppMngForm.getPosition());
		customer.setGender(clientAppMngForm.getGender());
		customer.setDateOfBirth(clientAppMngForm.getDateOfBirth());
		customer.setManager(clientAppMngForm.getManager());
		customer.setManagerEmail(clientAppMngForm.getManagerEmail());
		customer.setNotificationStatus("Pending");
		customer.setStatus("Pending");
		customer.setHeadName(user.getUserName());
		customer.setCustomerPrefix(clientAppMngForm.getCustomerPrefix());
		customer.setTransactionId(clientAppMngForm.getTransactionId());
		customer.setAccExpiryDate(clientAppMngForm.getAccExpiryDate());
		customer.setIsForTrading(Boolean.FALSE);
		clientAppMngDAO.insertCustomer(customer);
		Transaction trans = new Transaction();

		trans.setTransactionId(clientAppMngForm.getTransactionId());
		trans.setTransactionStatus("Client App Mng Saved");
		trans.setTransactionType("Customer App MNg");
		;
		transactionDAO.insertTransaction(trans);

		model.put("clientAppMngForm", clientAppMngForm);
		model.put("user", user);

		return new ModelAndView("clientAppMngTransaction", "model", model);

	}

	@RequestMapping(value = "/clientAppMngTransaction", method = RequestMethod.GET)
	public ModelAndView clientAppMngTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("clientAppMngForm", clientAppMngForm);

		return new ModelAndView("clientAppMngTransaction", "model", model);

	}

	@RequestMapping(value = "/clientAppMngFullList", method = RequestMethod.GET)
	public ModelAndView clientAppMngFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<ClientAppMngForm> clientList = clientAppMngDAO.getCustAppMngByStatusCompIDAndIsForTrading(user.getCompanyId() ,Boolean.FALSE);

		model.put("user", user);
		if(clientList != null && clientList.size() > 0)
		{
		model.put("clientList", clientList);

		return new ModelAndView("clientAppMngFullList", "model", model);
		}
		else
		{
			return new ModelAndView("noDataFoundCAdmin", "model", model);
		}

	}

	@RequestMapping(value = "/createCustomerHead", method = RequestMethod.GET)
	public ModelAndView createCustomerHead(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		List<CustomerHeadForm> customerList = customerDAO.getCustByIdAndAStatusComp(user.getCompanyId());
		
		  List<Company> companyList = companyDAO.getCompanyByStatuss("Approved");
		
			if (companyList != null && companyList.size() > 0) {
                   model.addAttribute("companyList", companyList);
				}

		model.put("user", user);
		model.put("customerHeadForm", customerHeadForm);
		model.put("customerList", customerList);

		return new ModelAndView("createCustomerHead", "model", model);

	}
	
	@RequestMapping(value = "/bankCustomerHeadList", method = RequestMethod.GET)
	public ModelAndView CustomerHeadList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		List<CustomerHeadForm> customerList = customerDAO.getCustByStatusCompIDAndIsForTrading(user.getCompanyId(), Boolean.FALSE);
		model.put("user", user);
		if(customerList != null && customerList.size() > 0)
		{
	    model.put("customerList", customerList);

		return new ModelAndView("bankCustomerHeadList", "model", model);
		}
		else
		{
			return new ModelAndView("noDataFoundCAdmin", "model", model);
		}

	}
	
	
	
	

	@RequestMapping(value = "/selectCustomerHead", method = RequestMethod.GET)
	public ModelAndView selectCustomerHead(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		CustomerHead customer = customerDAO.findCustomers(id);
		
		Company company = companyDAO.getByCompanyId(customer.getCompanyId());

		customerHeadForm.setId(customer.getId());
		customerHeadForm.setName(customer.getName());
		customerHeadForm.setCompanyName(company.getCompanyName());
		customerHeadForm.setCountry(customer.getCountry());
		customerHeadForm.setState(customer.getState());
		customerHeadForm.setCity(customer.getCity());
		customerHeadForm.setAddress(company.getAddress());
		customerHeadForm.setPincode(customer.getPincode());
		customerHeadForm.setContactNum(customer.getContactNum());
		customerHeadForm.setAltContactNum(customer.getAltContactNum());
		customerHeadForm.setEmail(customer.getEmail());
		customerHeadForm.setAltEmail(customer.getAltEmail());
		customerHeadForm.setCompanyPrefix(customer.getCompanyPrefix());
		customerHeadForm.setPosition(customer.getPosition());
		customerHeadForm.setGender(customer.getGender());
		customerHeadForm.setDateOfBirth(customer.getDateOfBirth());
		customerHeadForm.setCustomerPrefix(customer.getCustomerPrefix());
		customerHeadForm.setManager(customer.getManager());
		customerHeadForm.setManagerEmail(customer.getManagerEmail());

		model.put("user", user);

		model.put("customerHeadForm", customerHeadForm);

		return new ModelAndView("selectCustomerHead", "model", model);

	}

	@RequestMapping(value = "/updateCustomerHeadConfirm", method = RequestMethod.POST)
	public ModelAndView updateCustomerHeadConfirm(
			@ModelAttribute CustomerHeadForm customerHeadForm, ModelMap model,
			RedirectAttributes attribute) {
		EndUser user = getCurrentLoggedUserDetails();

		customerHeadForm.setId(customerHeadForm.getId());
		customerHeadForm.setName(customerHeadForm.getName());
		customerHeadForm.setCompanyName(customerHeadForm.getCompanyName());
		customerHeadForm.setCountry(customerHeadForm.getCountry());
		customerHeadForm.setState(customerHeadForm.getState());
		customerHeadForm.setCity(customerHeadForm.getCity());
		customerHeadForm.setAddress(customerHeadForm.getAddress());
		customerHeadForm.setPincode(customerHeadForm.getPincode());
		customerHeadForm.setContactNum(customerHeadForm.getContactNum());
		customerHeadForm.setAltContactNum(customerHeadForm.getAltContactNum());
		customerHeadForm.setEmail(customerHeadForm.getEmail());
		customerHeadForm.setAltEmail(customerHeadForm.getAltEmail());
		customerHeadForm.setCompanyPrefix(customerHeadForm.getCompanyPrefix());
		customerHeadForm.setPosition(customerHeadForm.getPosition());
		customerHeadForm.setGender(customerHeadForm.getGender());
		customerHeadForm.setDateOfBirth(customerHeadForm.getDateOfBirth());
		customerHeadForm
				.setCustomerPrefix(customerHeadForm.getCustomerPrefix());
		customerHeadForm.setManager(customerHeadForm.getManager());
		customerHeadForm.setManagerEmail(customerHeadForm.getManagerEmail());

		model.put("customerHeadForm", customerHeadForm);
		model.put("user", user);

		return new ModelAndView("updateCustomerHeadConfirm", "model", model);

	}

	@RequestMapping(value = "/updateCustomerHead", method = RequestMethod.POST)
	public ModelAndView roleupdate(ModelMap model,
			@ModelAttribute CustomerHeadForm customerHeadForm,
			RedirectAttributes attributes) {

		CustomerHead customer = customerDAO.findCustomers(customerHeadForm
				.getId());
		
		Company company = companyDAO.getByCompanyName(customerHeadForm.getCompanyName());

		customer.setName(customerHeadForm.getName());
		//customer.setCompanyName(customerHeadForm.getCompanyName());
		customer.setCompanyId(company.getId());
		customer.setCountry(customerHeadForm.getCountry());
		customer.setState(customerHeadForm.getState());
		customer.setCity(customerHeadForm.getCity());
		customer.setAddress(customerHeadForm.getAddress());
		customer.setPincode(customerHeadForm.getPincode());
		customer.setContactNum(customerHeadForm.getContactNum());
		customer.setAltContactNum(customerHeadForm.getAltContactNum());
		customer.setEmail(customerHeadForm.getEmail());
		customer.setAltEmail(customerHeadForm.getAltEmail());
		customer.setCompanyPrefix(customerHeadForm.getCompanyPrefix());
		customer.setPosition(customerHeadForm.getPosition());
		customer.setGender(customerHeadForm.getGender());
		customer.setDateOfBirth(customerHeadForm.getDateOfBirth());
		customer.setCustomerPrefix(customerHeadForm.getCustomerPrefix());
		customer.setManager(customerHeadForm.getManager());
		customer.setManagerEmail(customerHeadForm.getManagerEmail());
		customer.setaStatus("Pending");

		customerDAO.updateUser(customer);

		return new ModelAndView("redirect:createCustomerHead");

	}

	@RequestMapping(value = "/customerHeadConfirm", method = RequestMethod.POST)
	public ModelAndView customerConfirm(
			@ModelAttribute CustomerHeadForm customerHeadForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		List<CustomerHead> head = customerDAO.getCustomerDetailsList(
				customerHeadForm.getName()).getResultList();

		Company company = companyDAO.getByCompanyId(customerHeadForm .getCompanyId());
		
		List<EndUser> endUser = endUserDAOImpl.findByUsername(
				customerHeadForm.getName()).getResultList();
		if (endUser.size() == 0 || head.size() == 0) {

			customerHeadForm.setName(customerHeadForm.getName());
			customerHeadForm.setCompanyName(company.getCompanyName());
			customerHeadForm.setCountry(customerHeadForm.getCountry());
			customerHeadForm.setState(customerHeadForm.getState());
			customerHeadForm.setCity(customerHeadForm.getCity());
			customerHeadForm.setAddress(company.getAddress());
			customerHeadForm.setPincode(customerHeadForm.getPincode());
			customerHeadForm.setContactNum(customerHeadForm.getContactNum());
			customerHeadForm.setAltContactNum(customerHeadForm
					.getAltContactNum());
			customerHeadForm.setEmail(customerHeadForm.getEmail());
			customerHeadForm.setAltEmail(customerHeadForm.getAltEmail());
			customerHeadForm.setCompanyPrefix(customerHeadForm
					.getCompanyPrefix());
			customerHeadForm.setPosition(customerHeadForm.getPosition());
			customerHeadForm.setGender(customerHeadForm.getGender());
			customerHeadForm.setDateOfBirth(customerHeadForm.getDateOfBirth());
			customerHeadForm.setCustomerPrefix(customerHeadForm
					.getCustomerPrefix());
			customerHeadForm.setManager(customerHeadForm.getManager());
			customerHeadForm
					.setManagerEmail(customerHeadForm.getManagerEmail());
			customerHeadForm.setAccExpiryDate(customerHeadForm
					.getAccExpiryDate());

			model.put("customerHeadForm", customerHeadForm);
			model.put("user", user);

			return new ModelAndView("customerHeadConfirm", "model", model);
		} else {
			attributes.addFlashAttribute("success",
					"Customer Name Already Exists");

			return new ModelAndView("redirect:createCustomerHead");

		}
	}

	@RequestMapping(value = "/customerHeadSave", method = RequestMethod.POST)
	public ModelAndView customerSave(
			@ModelAttribute CustomerHeadForm customerHeadForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		
		customerHeadForm.setTransactionId(KeyGenerator.generateTransactionKey());
		
		Company company = companyDAO.getByCompanyName(customerHeadForm.getCompanyName());

		CustomerHead customer = new CustomerHead();
		customer.setName(customerHeadForm.getName());
		customer.setHeadName(user.getUserName());
		customer.setCompanyId(company.getId());
		customer.setCountry(customerHeadForm.getCountry());
		customer.setState(customerHeadForm.getState());
		customer.setCity(customerHeadForm.getCity());
		customer.setAddress(customerHeadForm.getAddress());
		customer.setPincode(customerHeadForm.getPincode());
		customer.setContactNum(customerHeadForm.getContactNum());
		customer.setAltContactNum(customerHeadForm.getAltContactNum());
		customer.setEmail(customerHeadForm.getEmail());
		customer.setAltEmail(customerHeadForm.getAltEmail());
		customer.setCompanyPrefix(customerHeadForm.getCompanyPrefix());
		customer.setPosition(customerHeadForm.getPosition());
		customer.setGender(customerHeadForm.getGender());
		customer.setDateOfBirth(customerHeadForm.getDateOfBirth());
		customer.setManager(customerHeadForm.getManager());
		customer.setManagerEmail(customerHeadForm.getManagerEmail());
		customer.setNotificationStatus("Pending");
		customer.setaStatus("Pending");
		customer.setCustomerPrefix(customerHeadForm.getCustomerPrefix());
		customer.setTransactionId(customerHeadForm.getTransactionId());
		customer.setAccExpiryDate(customerHeadForm.getAccExpiryDate());
		customer.setIsForTrading(Boolean.FALSE);
		customerDAO.insertCustomer(customer);
		Transaction trans = new Transaction();

		trans.setTransactionId(customerHeadForm.getTransactionId());
		trans.setTransactionStatus("Customer Head Saved");
		trans.setTransactionType("Customer Head");
		;
		transactionDAO.insertTransaction(trans);

		model.put("customerHeadForm", customerHeadForm);
		model.put("user", user);

		return new ModelAndView("customerHeadTransaction", "model", model);

	}

	@RequestMapping(value = "/customerHeadTransaction", method = RequestMethod.GET)
	public ModelAndView customerHeadTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("customerHeadForm", customerHeadForm);

		return new ModelAndView("customerHeadTransaction", "model", model);

	}

	@RequestMapping(value = "/createCustomerBranch", method = RequestMethod.GET)
	public ModelAndView createCustomerBranchAndSubsidory(ModelMap model) {

		List<CustomerHead> customerList = customerDAO.getApproved()
				.getResultList();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("customerBranchForm", customerBranchForm);
		model.put("customerList", customerList);

		return new ModelAndView("createCustomerBranch", "model", model);

	}

	@RequestMapping(value = "/createCustomerBranches", method = RequestMethod.GET)
	public ModelAndView createCustomerBranch(ModelMap model,
			@RequestParam("id") Long id) {

		CustomerHead customer = customerDAO.getByCustomerId(id);

		customerBranchForm.setId(customer.getId());
		customerBranchForm.setCustomerHeadKey(customer.getCustomerHeadKey());
		customerBranchForm.setCustomerPrefix(customer.getCustomerPrefix());
		customerBranchForm.setCustomerHeadName(customer.getName());

		EndUser user = getCurrentLoggedUserDetails();
		if (customerBranchForm.getCustomerHeadKey() != null) {
			List<CustomerBranch> customerList = customerDAO
					.getByCustomerKeyAndStatus(
							customerBranchForm.getCustomerHeadKey())
					.getResultList();
			model.put("customerList", customerList);
		}
		model.put("user", user);
		model.put("customerBranchForm", customerBranchForm);

		return new ModelAndView("createCustomerBranches", "model", model);

	}

	@RequestMapping(value = "/customerBranchesConfirm", method = RequestMethod.POST)
	public ModelAndView customerBranchConfirm(
			@ModelAttribute CustomerBranchForm customerBranchForm,
			ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		List<CustomerBranch> branch = customerDAO.getBranchCustomerDetailsList(
				customerBranchForm.getName()).getResultList();

		List<EndUser> endUser = endUserDAOImpl.findByUsername(
				customerBranchForm.getName()).getResultList();
		if (endUser.size() == 0 || branch.size() == 0) {

			customerBranchForm.setId(customerBranchForm.getId());
			customerBranchForm.setCustomerHeadKey(customerBranchForm
					.getCustomerHeadKey());
			customerBranchForm.setCustomerPrefix(customerBranchForm
					.getCustomerPrefix());
			customerBranchForm.setCustomerHeadName(customerBranchForm
					.getCustomerHeadName());
			customerBranchForm.setName(customerBranchForm.getName());
			customerBranchForm.setCompanyName(customerBranchForm
					.getCompanyName());
			customerBranchForm.setCountry(customerBranchForm.getCountry());
			customerBranchForm.setState(customerBranchForm.getState());
			customerBranchForm.setCity(customerBranchForm.getCity());
			customerBranchForm.setAddress(customerBranchForm.getAddress());
			customerBranchForm.setPincode(customerBranchForm.getPincode());
			customerBranchForm
					.setContactNum(customerBranchForm.getContactNum());
			customerBranchForm.setAltContactNum(customerBranchForm
					.getAltContactNum());
			customerBranchForm.setEmail(customerBranchForm.getEmail());
			customerBranchForm.setAltEmail(customerBranchForm.getAltEmail());
			customerBranchForm.setCompanyHeadPrefix(customerBranchForm
					.getCompanyHeadPrefix());
			customerBranchForm.setRoleType(customerBranchForm.getRoleType());
			customerBranchForm.setCustomerBranchPrefix(customerBranchForm
					.getCustomerBranchPrefix());
			customerBranchForm.setPosition(customerBranchForm.getPosition());
			customerBranchForm.setGender(customerBranchForm.getGender());
			customerBranchForm.setDateOfBirth(customerBranchForm
					.getDateOfBirth());
			customerBranchForm.setManager(customerBranchForm.getManager());
			customerBranchForm.setManagerEmail(customerBranchForm
					.getManagerEmail());
			customerBranchForm.setAccExpiryDate(customerBranchForm
					.getAccExpiryDate());

			model.put("customerBranchForm", customerBranchForm);
			model.put("user", user);

			return new ModelAndView("customerBranchesConfirm", "model", model);
		} else {

			model.put("customerBranchForm", customerBranchForm);
			Long Id = customerBranchForm.getId();
			attributes.addFlashAttribute("success",
					"Customer Name Already Exists");

			return new ModelAndView("redirect:createCustomerBranches?id=" + Id
					+ "");

		}
	}

	@RequestMapping(value = "/customerBranchesSave", method = RequestMethod.POST)
	public ModelAndView customerBranchesSave(
			@ModelAttribute CustomerBranchForm customerBranchForm,
			ModelMap model, RedirectAttributes attribute) {
		EndUser user = getCurrentLoggedUserDetails();
		
		customerBranchForm.setTransactionId(KeyGenerator.generateTransactionKey());

		CustomerBranch customer = new CustomerBranch();

		customer.setId(customerBranchForm.getId());
		customer.setCustomerHeadKey(customerBranchForm.getCustomerHeadKey());
		customer.setCustomerPrefix(customerBranchForm.getCustomerPrefix());
		customer.setCustomerHeadName(customerBranchForm.getCustomerHeadName());
		customer.setName(customerBranchForm.getName());
		customer.setCompanyName(customerBranchForm.getCompanyName());
		customer.setCountry(customerBranchForm.getCountry());
		customer.setState(customerBranchForm.getState());
		customer.setCity(customerBranchForm.getCity());
		customer.setRoleType(customerBranchForm.getRoleType());
		customer.setAddress(customerBranchForm.getAddress());
		customer.setPincode(customerBranchForm.getPincode());
		customer.setContactNum(customerBranchForm.getContactNum());
		customer.setAltContactNum(customerBranchForm.getAltContactNum());
		customer.setEmail(customerBranchForm.getEmail());
		customer.setAltEmail(customerBranchForm.getAltEmail());
		customer.setCompanyHeadPrefix(customerBranchForm.getCompanyHeadPrefix());
		customer.setPosition(customerBranchForm.getPosition());
		customer.setGender(customerBranchForm.getGender());
		customer.setDateOfBirth(customerBranchForm.getDateOfBirth());

		customer.setNotificationStatus("Pending");
		customer.setHeadName(user.getUserName());
		customer.setaStatus("Pending");
		customer.setCustomerBranchPrefix(customerBranchForm
				.getCustomerBranchPrefix());
		customer.setTransactionId(customerBranchForm.getTransactionId());
		customer.setManager(customerBranchForm.getManager());
		customer.setManagerEmail(customerBranchForm.getManagerEmail());
		customer.setAccExpiryDate(customerBranchForm.getAccExpiryDate());
		customerDAO.insertCustomerBranch(customer);
		Transaction trans = new Transaction();

		trans.setTransactionId(customerBranchForm.getTransactionId());
		trans.setTransactionStatus("Customer Branch Saved");
		trans.setTransactionType("Customer Branch/Subsidiary");
		;
		transactionDAO.insertTransaction(trans);

		model.put("customerBranchForm", customerBranchForm);
		model.put("user", user);

		return new ModelAndView("customerBranchesTransaction", "model", model);

	}

	@RequestMapping(value = "/customerBranchesTransaction", method = RequestMethod.GET)
	public ModelAndView customerBranchTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("customerBranchForm", customerBranchForm);

		return new ModelAndView("customerBranchesTransaction", "model", model);

	}

	@RequestMapping(value = "/selectCustomerBranch", method = RequestMethod.GET)
	public ModelAndView selectCustomerBranch(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		CustomerBranch customer = customerDAO.getByCustomerBranchId(id);

		customerBranchForm.setId(customer.getId());
		customerBranchForm.setName(customer.getName());
		customerBranchForm.setCompanyName(customer.getCompanyName());
		customerBranchForm.setCountry(customer.getCountry());
		customerBranchForm.setState(customer.getState());
		customerBranchForm.setCity(customer.getCity());
		customerBranchForm.setAddress(customer.getAddress());
		customerBranchForm.setPincode(customer.getPincode());
		customerBranchForm.setContactNum(customer.getContactNum());
		customerBranchForm.setAltContactNum(customer.getAltContactNum());
		customerBranchForm.setEmail(customer.getEmail());
		customerBranchForm.setAltEmail(customer.getAltEmail());
		customerBranchForm.setCustomerPrefix(customer.getCustomerPrefix());
		customerBranchForm.setPosition(customer.getPosition());
		customerBranchForm.setGender(customer.getGender());
		customerBranchForm.setDateOfBirth(customer.getDateOfBirth());
		customerBranchForm.setCustomerPrefix(customer.getCustomerPrefix());
		customerBranchForm.setCustomerBranchPrefix(customer
				.getCustomerBranchPrefix());
		customerBranchForm.setManager(customer.getManager());
		customerBranchForm.setManagerEmail(customer.getManagerEmail());

		model.put("user", user);

		model.put("customerBranchForm", customerBranchForm);

		return new ModelAndView("selectCustomerBranch", "model", model);

	}

	@RequestMapping(value = "/updateCustomerBranchConfirm", method = RequestMethod.POST)
	public ModelAndView updateCustomerBranchConfirm(
			@ModelAttribute CustomerBranchForm customerBranchForm,
			ModelMap model, RedirectAttributes attribute) {
		EndUser user = getCurrentLoggedUserDetails();

		customerBranchForm.setId(customerBranchForm.getId());
		customerBranchForm.setName(customerBranchForm.getName());
		customerBranchForm.setCountry(customerBranchForm.getCountry());
		customerBranchForm.setState(customerBranchForm.getState());
		customerBranchForm.setCity(customerBranchForm.getCity());
		customerBranchForm.setAddress(customerBranchForm.getAddress());
		customerBranchForm.setPincode(customerBranchForm.getPincode());
		customerBranchForm.setContactNum(customerBranchForm.getContactNum());
		customerBranchForm.setAltContactNum(customerBranchForm
				.getAltContactNum());
		customerBranchForm.setEmail(customerBranchForm.getEmail());
		customerBranchForm.setAltEmail(customerBranchForm.getAltEmail());
		customerBranchForm.setCustomerPrefix(customerBranchForm
				.getCustomerPrefix());
		customerBranchForm.setPosition(customerBranchForm.getPosition());
		customerBranchForm.setGender(customerBranchForm.getGender());
		customerBranchForm.setDateOfBirth(customerBranchForm.getDateOfBirth());
		customerBranchForm.setCustomerPrefix(customerBranchForm
				.getCustomerPrefix());
		customerBranchForm.setCustomerBranchPrefix(customerBranchForm
				.getCustomerBranchPrefix());
		customerBranchForm.setManager(customerBranchForm.getManager());
		customerBranchForm
				.setManagerEmail(customerBranchForm.getManagerEmail());

		model.put("customerBranchForm", customerBranchForm);
		model.put("user", user);

		return new ModelAndView("updateCustomerBranchConfirm", "model", model);

	}

	@RequestMapping(value = "/updateCustomerBranch", method = RequestMethod.POST)
	public ModelAndView updateCustomerBranch(ModelMap model,
			@ModelAttribute CustomerBranchForm customerBranchForm,
			RedirectAttributes attributes) {

		CustomerBranch customer = customerDAO
				.getByCustomerBranchId(customerBranchForm.getId());

		customer.setName(customerBranchForm.getName());
		customer.setCountry(customerBranchForm.getCountry());
		customer.setState(customerBranchForm.getState());
		customer.setCity(customerBranchForm.getCity());
		customer.setAddress(customerBranchForm.getAddress());
		customer.setPincode(customerBranchForm.getPincode());
		customer.setContactNum(customerBranchForm.getContactNum());
		customer.setAltContactNum(customerBranchForm.getAltContactNum());
		customer.setEmail(customerBranchForm.getEmail());
		customer.setAltEmail(customerBranchForm.getAltEmail());
		customer.setCustomerPrefix(customerBranchForm.getCustomerPrefix());
		customer.setPosition(customerBranchForm.getPosition());
		customer.setGender(customerBranchForm.getGender());
		customer.setDateOfBirth(customerBranchForm.getDateOfBirth());
		customer.setCustomerBranchPrefix(customerBranchForm
				.getCustomerBranchPrefix());
		customer.setManager(customerBranchForm.getManager());
		customer.setManagerEmail(customerBranchForm.getManagerEmail());
		customer.setaStatus("Pending");

		customerDAO.updateBranch(customer);

		return new ModelAndView("redirect:createCustomerBranch");

	}

	@RequestMapping(value = "/bankCustomerBranchList", method = RequestMethod.GET)
	public ModelAndView customerBranchList(ModelMap model) {

		Collection<CustomerBranch> customerList = customerDAO
				.findAllCustomerBranch();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		if(customerList != null && customerList.size() > 0)
		{
		model.put("customerList", customerList);

		return new ModelAndView("bankCustomerBranchList", "model", model);
		}
		else
		{
			return new ModelAndView("noDataFoundCAdmin", "model", model);
		}

	}

	@RequestMapping(value = "/createCustomerSubsidiary", method = RequestMethod.GET)
	public ModelAndView createCustomerSubsidiary(ModelMap model,
			@RequestParam("id") Long id) {

		CustomerHead customer = customerDAO.getByCustomerId(id);

		customerSubsidiaryForm.setId(customer.getId());
		customerSubsidiaryForm
				.setCustomerHeadKey(customer.getCustomerHeadKey());
		customerSubsidiaryForm.setCustomerPrefix(customer.getCustomerPrefix());
		customerSubsidiaryForm.setCustomerHeadName(customer.getName());

		EndUser user = getCurrentLoggedUserDetails();
		List<CustomerSubsidiary> customerList = customerDAO
				.getByCustomerHeadKeyAndStatus(
						customerSubsidiaryForm.getCustomerHeadKey())
				.getResultList();
		model.put("user", user);
		model.put("customerSubsidiaryForm", customerSubsidiaryForm);
		model.put("customerList", customerList);

		return new ModelAndView("createCustomerSubsidiary", "model", model);

	}

	@RequestMapping(value = "/customerSubsidiaryConfirm", method = RequestMethod.POST)
	public ModelAndView customerSubsidiaryConfirm(
			@ModelAttribute CustomerSubsidiaryForm customerSubsidiaryForm,
			ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		List<CustomerSubsidiary> sub = customerDAO.getSubsCustomerDetailsList(
				customerSubsidiaryForm.getName()).getResultList();

		List<EndUser> endUser = endUserDAOImpl.findByUsername(
				customerSubsidiaryForm.getName()).getResultList();
		if (endUser.size() == 0 || sub.size() == 0) {

			customerSubsidiaryForm.setId(customerSubsidiaryForm.getId());
			customerSubsidiaryForm.setCustomerHeadKey(customerSubsidiaryForm
					.getCustomerHeadKey());
			customerSubsidiaryForm.setCustomerPrefix(customerSubsidiaryForm
					.getCustomerPrefix());
			customerSubsidiaryForm.setCustomerHeadName(customerSubsidiaryForm
					.getCustomerHeadName());
			customerSubsidiaryForm.setName(customerSubsidiaryForm.getName());
			customerSubsidiaryForm.setCompanyName(customerSubsidiaryForm
					.getCompanyName());
			customerSubsidiaryForm.setCountry(customerSubsidiaryForm
					.getCountry());
			customerSubsidiaryForm.setState(customerSubsidiaryForm.getState());
			customerSubsidiaryForm.setCity(customerSubsidiaryForm.getCity());
			customerSubsidiaryForm.setAddress(customerSubsidiaryForm
					.getAddress());
			customerSubsidiaryForm.setPincode(customerSubsidiaryForm
					.getPincode());
			customerSubsidiaryForm.setContactNum(customerSubsidiaryForm
					.getContactNum());
			customerSubsidiaryForm.setAltContactNum(customerSubsidiaryForm
					.getAltContactNum());
			customerSubsidiaryForm.setEmail(customerSubsidiaryForm.getEmail());
			customerSubsidiaryForm.setAltEmail(customerSubsidiaryForm
					.getAltEmail());
			customerSubsidiaryForm.setCompanyHeadPrefix(customerSubsidiaryForm
					.getCompanyHeadPrefix());
			customerSubsidiaryForm.setCompanyHeadPrefix(customerSubsidiaryForm
					.getCompanyHeadPrefix());
			customerSubsidiaryForm.setPosition(customerSubsidiaryForm
					.getPosition());
			customerSubsidiaryForm
					.setGender(customerSubsidiaryForm.getGender());
			customerSubsidiaryForm.setDateOfBirth(customerSubsidiaryForm
					.getDateOfBirth());
			customerSubsidiaryForm.setManager(customerSubsidiaryForm
					.getManager());
			customerSubsidiaryForm.setManagerEmail(customerSubsidiaryForm
					.getManagerEmail());

			model.put("customerSubsidiaryForm", customerSubsidiaryForm);
			model.put("user", user);

			return new ModelAndView("customerSubsidiaryConfirm", "model", model);
		} else {

			model.put("customerSubsidiaryForm", customerSubsidiaryForm);
			Long Id = customerSubsidiaryForm.getId();
			attributes.addFlashAttribute("success",
					"Customer Name Already Exists");

			return new ModelAndView("redirect:createCustomerSubsidiary?id="
					+ Id + "");

		}
	}

	@RequestMapping(value = "/customerSubsidiarySave", method = RequestMethod.POST)
	public ModelAndView customerSubsidiarySave(
			@ModelAttribute CustomerSubsidiaryForm customerSubsidiaryForm,
			ModelMap model, RedirectAttributes attribute) {
		EndUser user = getCurrentLoggedUserDetails();
		
		customerSubsidiaryForm.setTransactionId(KeyGenerator.generateTransactionKey());

		CustomerSubsidiary customer = new CustomerSubsidiary();

		customer.setId(customerSubsidiaryForm.getId());
		customer.setCustomerHeadKey(customerSubsidiaryForm.getCustomerHeadKey());
		customer.setCustomerPrefix(customerSubsidiaryForm.getCustomerPrefix());
		customer.setCustomerHeadName(customerSubsidiaryForm
				.getCustomerHeadName());
		customer.setName(customerSubsidiaryForm.getName());
		customer.setCompanyName(customerSubsidiaryForm.getCompanyName());
		customer.setCountry(customerSubsidiaryForm.getCountry());
		customer.setState(customerSubsidiaryForm.getState());
		customer.setCity(customerSubsidiaryForm.getCity());
		customer.setAddress(customerSubsidiaryForm.getAddress());
		customer.setPincode(customerSubsidiaryForm.getPincode());
		customer.setContactNum(customerSubsidiaryForm.getContactNum());
		customer.setAltContactNum(customerSubsidiaryForm.getAltContactNum());
		customer.setEmail(customerSubsidiaryForm.getEmail());
		customer.setAltEmail(customerSubsidiaryForm.getAltEmail());
		customer.setCompanyHeadPrefix(customerSubsidiaryForm
				.getCompanyHeadPrefix());
		customer.setPosition(customerSubsidiaryForm.getPosition());
		customer.setGender(customerSubsidiaryForm.getGender());
		customer.setDateOfBirth(customerSubsidiaryForm.getDateOfBirth());
		customer.setNotificationStatus("Pending");
		customer.setaStatus("Pending");
		customer.setHeadName(user.getUserName());
		customer.setCustomerPrefix(customerSubsidiaryForm.getCustomerPrefix());
		customer.setTransactionId(customerSubsidiaryForm.getTransactionId());
		customer.setManager(customerSubsidiaryForm.getManager());
		customer.setManagerEmail(customerSubsidiaryForm.getManagerEmail());
		customerDAO.insertCustomerSubsidiary(customer);
		Transaction trans = new Transaction();

		trans.setTransactionId(customerBranchForm.getTransactionId());
		trans.setTransactionStatus("Customer Branch Saved");
		trans.setTransactionType("Customer Branch");

		transactionDAO.insertTransaction(trans);

		model.put("customerSubsidiaryForm", customerSubsidiaryForm);
		model.put("user", user);

		return new ModelAndView("customerSubsidiaryTransaction", "model", model);

	}

	@RequestMapping(value = "/customerSubsidiaryTransaction", method = RequestMethod.GET)
	public ModelAndView customerSubsidiaryTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("customerSubsidiaryForm", customerSubsidiaryForm);

		return new ModelAndView("customerSubsidiaryTransaction", "model", model);

	}

	@RequestMapping(value = "/selectCustomerSubsidiary", method = RequestMethod.GET)
	public ModelAndView selectCustomerSubsidiary(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		CustomerSubsidiary customer = customerDAO.getByCustomerSubsidiaryId(id);

		customerSubsidiaryForm.setId(customer.getId());
		customerSubsidiaryForm.setName(customer.getName());

		customerSubsidiaryForm.setCountry(customer.getCountry());
		customerSubsidiaryForm.setState(customer.getState());
		customerSubsidiaryForm.setCity(customer.getCity());
		customerSubsidiaryForm.setAddress(customer.getAddress());
		customerSubsidiaryForm.setPincode(customer.getPincode());
		customerSubsidiaryForm.setContactNum(customer.getContactNum());
		customerSubsidiaryForm.setAltContactNum(customer.getAltContactNum());
		customerSubsidiaryForm.setEmail(customer.getEmail());
		customerSubsidiaryForm.setAltEmail(customer.getAltEmail());
		customerSubsidiaryForm.setCustomerPrefix(customer.getCustomerPrefix());
		customerSubsidiaryForm.setPosition(customer.getPosition());
		customerSubsidiaryForm.setGender(customer.getGender());
		customerSubsidiaryForm.setDateOfBirth(customer.getDateOfBirth());
		customerSubsidiaryForm.setCompanyHeadPrefix(customer
				.getCompanyHeadPrefix());
		customerSubsidiaryForm.setManager(customer.getManager());
		customerSubsidiaryForm.setManagerEmail(customer.getManagerEmail());

		model.put("user", user);

		model.put("customerSubsidiaryForm", customerSubsidiaryForm);

		return new ModelAndView("selectCustomerSubsidiary", "model", model);

	}

	@RequestMapping(value = "/updateCustomerSubsidiaryConfirm", method = RequestMethod.POST)
	public ModelAndView updateCustomerSubsidiaryConfirm(
			@ModelAttribute CustomerSubsidiaryForm customerSubsidiaryForm,
			ModelMap model, RedirectAttributes attribute) {
		EndUser user = getCurrentLoggedUserDetails();

		customerSubsidiaryForm.setId(customerSubsidiaryForm.getId());
		customerSubsidiaryForm.setName(customerSubsidiaryForm.getName());
		customerSubsidiaryForm.setCountry(customerSubsidiaryForm.getCountry());
		customerSubsidiaryForm.setState(customerSubsidiaryForm.getState());
		customerSubsidiaryForm.setCity(customerSubsidiaryForm.getCity());
		customerSubsidiaryForm.setAddress(customerSubsidiaryForm.getAddress());
		customerSubsidiaryForm.setPincode(customerSubsidiaryForm.getPincode());
		customerSubsidiaryForm.setContactNum(customerSubsidiaryForm
				.getContactNum());
		customerSubsidiaryForm.setAltContactNum(customerSubsidiaryForm
				.getAltContactNum());
		customerSubsidiaryForm.setEmail(customerSubsidiaryForm.getEmail());
		customerSubsidiaryForm
				.setAltEmail(customerSubsidiaryForm.getAltEmail());
		customerSubsidiaryForm.setCustomerPrefix(customerSubsidiaryForm
				.getCustomerPrefix());
		customerSubsidiaryForm.setCompanyHeadPrefix(customerSubsidiaryForm
				.getCompanyHeadPrefix());
		customerSubsidiaryForm
				.setPosition(customerSubsidiaryForm.getPosition());
		customerSubsidiaryForm.setGender(customerSubsidiaryForm.getGender());
		customerSubsidiaryForm.setDateOfBirth(customerSubsidiaryForm
				.getDateOfBirth());
		customerSubsidiaryForm.setManager(customerSubsidiaryForm.getManager());
		customerSubsidiaryForm.setManagerEmail(customerSubsidiaryForm
				.getManagerEmail());

		model.put("customerSubsidiaryForm", customerSubsidiaryForm);
		model.put("user", user);

		return new ModelAndView("updateCustomerSubsidiaryConfirm", "model",
				model);

	}

	@RequestMapping(value = "/updateCustomerSubsidiary", method = RequestMethod.POST)
	public ModelAndView updateCustomerSubsidiary(ModelMap model,
			@ModelAttribute CustomerSubsidiaryForm customerSubsidiaryForm,
			RedirectAttributes attributes) {

		CustomerSubsidiary customer = customerDAO
				.getByCustomerSubsidiaryId(customerSubsidiaryForm.getId());

		customer.setName(customerSubsidiaryForm.getName());
		customer.setCountry(customerSubsidiaryForm.getCountry());
		customer.setState(customerSubsidiaryForm.getState());
		customer.setCity(customerSubsidiaryForm.getCity());
		customer.setAddress(customerSubsidiaryForm.getAddress());
		customer.setPincode(customerSubsidiaryForm.getPincode());
		customer.setContactNum(customerSubsidiaryForm.getContactNum());
		customer.setAltContactNum(customerSubsidiaryForm.getAltContactNum());
		customer.setEmail(customerSubsidiaryForm.getEmail());
		customer.setAltEmail(customerSubsidiaryForm.getAltEmail());
		customer.setCustomerPrefix(customerSubsidiaryForm.getCustomerPrefix());
		customer.setPosition(customerSubsidiaryForm.getPosition());
		customer.setGender(customerSubsidiaryForm.getGender());
		customer.setDateOfBirth(customerSubsidiaryForm.getDateOfBirth());
		customer.setCompanyHeadPrefix(customerSubsidiaryForm
				.getCompanyHeadPrefix());
		customer.setManager(customerSubsidiaryForm.getManager());
		customer.setManagerEmail(customerSubsidiaryForm.getManagerEmail());
		customer.setaStatus("Pending");

		customerDAO.updateSubsidiary(customer);

		return new ModelAndView("redirect:createCustomerBranch");

	}

	@RequestMapping(value = "/bankCustomerSubsidiaryList", method = RequestMethod.GET)
	public ModelAndView customerSubsidiaryList(ModelMap model) {

		Collection<CustomerSubsidiary> customerList = customerDAO
				.findAllCustomerSubsidiary();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		if(customerList != null && customerList.size() > 0)
		{
		model.put("customerList", customerList);

		return new ModelAndView("bankCustomerSubsidiaryList", "model", model);
		}
		else
		{
			return new ModelAndView("noDataFoundCAdmin", "model", model);
		}
	}

	@RequestMapping(value = "/snapShotCustAdmin", method = RequestMethod.GET)
	public ModelAndView snapShotBank(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<MasterPlan> masterList = masterPlanDAO.getAllMasterPlans()
				.getResultList();

		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);
			
			return new ModelAndView("snapShotCustAdmin", "model", model);
		}
		else
		{
			return new ModelAndView("noDataFoundCAdmin", "model", model);
		}

	}

	@RequestMapping(value = "/snapShotCustAdminView", method = RequestMethod.GET)
	public ModelAndView snapShotBankView(@RequestParam("id") Long id,
			ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		MasterPlan masterList = masterPlanDAO.getByMasterPlanId(id);

		if (masterList != null && masterList.getMasterKey() != null) {
			List<Collateral> collateralList = collateralDAO
					.getCollateralBYMAsterKey(masterList.getMasterKey())
					.getResultList();
			if (collateralList != null && collateralList.size() > 0) {
				model.put("collateralList", collateralList);
			}

			List<BuyingCost> buyingList = buyingCostDAO
					.getBuyingCostBYMAsterKey(masterList.getMasterKey())
					.getResultList();

			if (buyingList != null && buyingList.size() > 0)

			{
				model.put("buyingList", buyingList);
			}

			List<SellerBuyingCost> sellerList = sellerBuyingCostDAO
					.getSellerBuyingCostList(masterList.getMasterKey())
					.getResultList();
			if (sellerList != null && sellerList.size() > 0)

			{
				model.put("sellerList", sellerList);
			}

			List<Invoice> invoiceList = invoiceDAO.getInvoiceByMasterKeyList(
					masterList.getMasterKey()).getResultList();

			if (invoiceList != null && invoiceList.size() > 0) {
				model.put("invoiceList", invoiceList);
			}
			List<PurchaseOrder> purchaseList = purchaseOrderDAO
					.getPoByMasterKeyList(masterList.getMasterKey())
					.getResultList();

			if (purchaseList != null && purchaseList.size() > 0) {
				model.put("purchaseList", purchaseList);

			}

			model.put("masterList", masterList);
		}
		model.put("user", user);

		return new ModelAndView("snapShotCustAdminView", "model", model);

	}

	/**
	 * Method to display all Users for Swapping/Reverting account
	 *
	 */
	@RequestMapping(value = "/getUsersForSwapRevert", method = RequestMethod.GET)
	public ModelAndView getUsersForSwapRevert(ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		List<EndUser> userList = endUserDAOImpl.getUsersForBlockUnblock();
		List<SwapAccount> swapAccountList = swapAccountDAO.getAllSwapAccounts();

		model.put("userList", userList);
		model.put("user", user);
		if(swapAccountList != null && swapAccountList.size() > 0)
		{
		model.put("swapAccountList", swapAccountList);

		return new ModelAndView("getUsersForSwapRevert", "model", model);
		}
		else
		{
			return new ModelAndView("noDataFoundCAdmin", "model", model);
		}
	}

	/**
	 * Method to display swap account details
	 */
	@RequestMapping(value = "/swapAccount", method = RequestMethod.GET)
	public ModelAndView swapAccount(@RequestParam Long id,
			@ModelAttribute SwapAccountForm swapAccountForm, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser swapUser = endUserDAOImpl.findId(id);

		swapAccountForm.setOldUser(swapUser.getUserName());
		swapAccountForm.setOldEmail(swapUser.getCurrentRole());
		swapAccountForm.setOldContactNo(swapUser.getContactNo());
		swapAccountForm.setEndUserId(swapUser.getId());

		model.put("user", user);
		model.put("swapAccountForm", swapAccountForm);

		return new ModelAndView("swapAccount", "model", model);
	}

	/**
	 * Method to swap account and save
	 */
	@RequestMapping(value = "/saveSwapAccount", method = RequestMethod.POST)
	public String saveSwapAccount(
			@ModelAttribute SwapAccountForm swapAccountForm,
			RedirectAttributes attributes) {

		// Existing user details
		EndUser user = endUserDAOImpl.findId(swapAccountForm.getEndUserId());

		SwapAccount swapAccount = new SwapAccount();

		swapAccount.setOldUser(user.getUserName());
		swapAccount.setOldPassword(user.getPassword());
		swapAccount.setOldEmail(user.getEmail());
		swapAccount.setOldContactNo(user.getContactNo());

		swapAccount.setNewUser(swapAccountForm.getNewUser());
		swapAccount.setNewPassword(swapAccountForm.getNewContactNo());
		swapAccount.setNewEmail(swapAccountForm.getNewEmail());
		swapAccount.setNewContactNo(swapAccountForm.getNewContactNo());

		swapAccount.setStatus(Constants.PENDING);
		swapAccount.setEndUserId(user.getId());
		swapAccountDAO.createSwapAccount(swapAccount);
		attributes.addFlashAttribute("success",
				"Account swap approval request sent");

		return "redirect:getUsersForSwapRevert";
	}

	/**
	 * Method to display revert account details
	 */
	@RequestMapping(value = "/revertAccount", method = RequestMethod.GET)
	public ModelAndView revertAccount(@RequestParam Long id,
			@ModelAttribute SwapAccountForm swapAccountForm, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		SwapAccount revertUser = swapAccountDAO.findByEndUserId(id);

		swapAccountForm.setOldUser(revertUser.getOldUser());
		swapAccountForm.setOldEmail(revertUser.getOldEmail());
		swapAccountForm.setOldContactNo(revertUser.getOldContactNo());

		swapAccountForm.setNewUser(revertUser.getNewUser());
		swapAccountForm.setNewEmail(revertUser.getNewEmail());
		swapAccountForm.setNewContactNo(revertUser.getNewContactNo());
		swapAccountForm.setEndUserId(id);

		model.put("user", user);
		model.put("swapAccountForm", swapAccountForm);

		return new ModelAndView("revertAccount", "model", model);
	}

	/**
	 * Method to swap account and save
	 */
	@RequestMapping(value = "/updateRevertAccount", method = RequestMethod.POST)
	public String updateRevertAccount(ModelMap model,
			@ModelAttribute SwapAccountForm swapAccountForm,
			RedirectAttributes attributes) {

		// Existing user details
		EndUser user = endUserDAOImpl.findId(swapAccountForm.getEndUserId());
        model.put("user", user);
		SwapAccount swapAccount = swapAccountDAO
				.findByEndUserId(swapAccountForm.getEndUserId());

		swapAccount.setNewUser(swapAccount.getOldUser());
		swapAccount.setNewPassword(swapAccount.getOldContactNo());
		swapAccount.setNewEmail(swapAccount.getOldEmail());
		swapAccount.setNewContactNo(swapAccount.getOldContactNo());

		swapAccount.setOldUser(user.getUserName());
		swapAccount.setOldEmail(user.getEmail());
		swapAccount.setOldContactNo(user.getContactNo());
		swapAccount.setOldPassword("");

		swapAccount.setStatus(Constants.PENDING);
		swapAccount.setEndUserId(user.getId());
		swapAccountDAO.updateSwapAccount(swapAccount);

		attributes.addFlashAttribute("success",
				"Account Revert approval request sent");

		return "redirect:getUsersForSwapRevert";
	}

	@RequestMapping(value = "/clientAdminBuyerPageList", method = RequestMethod.GET)
	public ModelAndView getAllBuyerList(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		Collection<NewBuyer> buyerList = newBuyerDAOImpl.getbuyerListByCompanyId(user.getCompanyId());

		model.put("user", user);
		if(buyerList != null && buyerList.size() >0)
		{
		model.put("buyerList", buyerList);
		return new ModelAndView("clientAdminBuyerPageList", "model", model);
		}
		else
		{
			return new ModelAndView("noDataFoundCAdmin", "model", model);
		}

	}

	@RequestMapping(value = "/clientAdminSupplierPageList", method = RequestMethod.GET)
	public ModelAndView getAllSellerList(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		/*supplierDAO.getList();*/

		Collection<Supplier> SupplierList = supplierDAO.getSupplierListByCompanyId(user.getCompanyId());
		model.put("user", user);
		if(SupplierList != null && SupplierList.size() > 0)
		{
		model.put("SupplierList", SupplierList);

		return new ModelAndView("clientAdminSupplierPageList", "model", model);
		}
		else
		{
			return new ModelAndView("noDataFoundCAdmin", "model", model);
		}

	}
	
	
	
	/**
	 * Method to generate Warehouse Chart
	 */
	@RequestMapping(value = "/generateWarehouseChart", method = RequestMethod.GET)
	public ModelAndView generateWarehouseChart(
			@ModelAttribute WareHouseForm wareHouseForm, ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouse> warehouseList = wareHouseDAO.getList().getResultList();

		model.put("user", user);
		model.put("warehouseList", warehouseList);
		

		return new ModelAndView("generateWarehouseChartClientAdm", "model", model);
	}

	/**
	 * Method to generate Warehouse Chart
	 */
	@RequestMapping(value = "/generateWarehouseChartById", method = RequestMethod.POST)
	public String generateWarehouseChartById(
			@ModelAttribute WareHouseForm wareHouseForm, ModelMap model, RedirectAttributes attributes) {
				

		Map<WareHouse, List<Inventory>> warehouseChart = new LinkedHashMap<>();

		if (wareHouseForm.getIds() != null) {
			for(long id : wareHouseForm.getIds()) {
				WareHouse wareHouse = wareHouseDAO.getByWareHouseId(id);
				List<Inventory> inventoryList = inventoryDAO.
						                        getInventoryListByWareHouse
						                        (wareHouse.getWareHouseName());
			warehouseChart.put(wareHouse, inventoryList);
			}
		}
		attributes.addFlashAttribute("warehouseChart", warehouseChart);
		
		return "redirect:generateWarehouseChart";
	}

	
	@RequestMapping(value = "/adminExistingStockInvoiceList", method = RequestMethod.GET)
	public ModelAndView existingStockHeadInvoiceFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		Company company = companyDAO.getByCompanyId(user.getCompanyId());
		String Comp = company.getCompanyName();

		List<InvoiceStock> poList = invoiceStockDAO.getList(
				).getResultList();

		model.put("user", user);
		
		if(Comp != null )
		{
		model.put("Company", Comp);
		}
		
		if(poList != null && poList.size() >0)
		{
		model.put("poList", poList);

		return new ModelAndView("adminExistingStockInvoiceList", "model", model);
		}
		else
		{
			return new ModelAndView("noDataFoundCAdmin", "model", model);
		}

	}

	@RequestMapping(value = "/adminExistingStockPoList", method = RequestMethod.GET)
	public ModelAndView bankExistingStockPoList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		Company comp = companyDAO.getByCompanyId(user.getCompanyId());
		String company = comp.getCompanyName();

		List<PoStock> poList = poStockDAO.getList(
				).getResultList();

		model.put("user", user);
		if(company != null)
		{
		model.put("company", company);
		}
		
		if(poList != null && poList.size() >0)
		{
		model.put("poList", poList);

		return new ModelAndView("adminExistingStockPoList", "model", model);
		}
		else
		{
			return new ModelAndView("noDataFoundCAdmin", "model", model);
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

		return new ModelAndView("existingStockPOInventoryChartCltAdm", "model", model);
	}


	/**
	 * Method to generate Warehouse Chart
	 */
	@RequestMapping(value = "/existingStockPOInventoryChartById", method = RequestMethod.POST)
	public String existingStockPOInventoryChartById(
			@ModelAttribute WareHouseForm wareHouseForm, ModelMap model, RedirectAttributes attributes) {
			

		Map<WareHouse,List<PoStock>> warehouseChart = new LinkedHashMap<>();

		if (wareHouseForm.getIds() != null) {
			for(long id : wareHouseForm.getIds()) {
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
	public ModelAndView existingStockInvoiceInventoryChart(@ModelAttribute WareHouseForm wareHouseForm, ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouse> warehouseList = wareHouseDAO.getList().getResultList();

		model.put("user", user);
		model.put("warehouseList", warehouseList);
		
		return new ModelAndView("existingStockInvoiceInventoryChartCltAdm", "model", model);
	}
	
	/**
	 * Method to generate Warehouse Chart
	 */
	@RequestMapping(value = "/existingStockInvoiceInventoryChartById", method = RequestMethod.POST)
	public String existingStockInvoiceInventoryChartById(
			@ModelAttribute WareHouseForm wareHouseForm, ModelMap model, RedirectAttributes attributes) {
		
		Map<WareHouse,List<InvoiceStock>> warehouseChart = new LinkedHashMap<>();	

		if (wareHouseForm.getIds() != null) {
			for(long id : wareHouseForm.getIds()) {
				WareHouse wareHouse = wareHouseDAO.getByWareHouseId(id);
				List<InvoiceStock> invoiceList = invoiceStockDAO.
						                    getInvoiceStockByWarehouse(wareHouse.getWareHouseName());

				warehouseChart.put(wareHouse, invoiceList);				
			
			}
		}
		attributes.addFlashAttribute("warehouseChart", warehouseChart);
		
		return "redirect:existingStockInvoiceInventoryChart";
	}

	

	/**
	 * Method to generate Invoice inventory chart
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/generateInvoiceInventoryChartBranch", method = RequestMethod.GET)
	public ModelAndView generateInvoiceInventoryChartBranch(@ModelAttribute WareHouseForm wareHouseForm , ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouse> warehouseList = wareHouseDAO.getList().getResultList();

		model.put("user", user);
		model.put("warehouseList", warehouseList);		

		return new ModelAndView("generateInvoiceInventoryChartCltAdm", "model", model);
	}
	
	/**
	 * Method to generate Invoice inventory Chart
	 */
	@RequestMapping(value = "/generateInvoiceInventoryChartBranchById", method = RequestMethod.POST)
	public String generateInvoiceInventoryChartBranchById(
			@ModelAttribute WareHouseForm wareHouseForm, ModelMap model, RedirectAttributes attributes) {			

		Map<WareHouse,List<InvoiceInventory>> warehouseChart = new LinkedHashMap<>();

		if (wareHouseForm.getIds() != null) {
			for(long id : wareHouseForm.getIds()) {
				WareHouse wareHouse = wareHouseDAO.getByWareHouseId(id);
				List<InvoiceInventory> inventoryList = invoiceInventoryDAO.getInvoiceInventoryByWarehouse
													   (wareHouse.getWareHouseName());

				warehouseChart.put(wareHouse, inventoryList);
			}
		}
		attributes.addFlashAttribute("warehouseChart", warehouseChart);
		
		return "redirect:generateInvoiceInventoryChartBranch";
	}
	
	@RequestMapping(value = "/custAdminHelp", method = RequestMethod.GET)
	public ModelAndView adminHelp(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("custAdminHelp", "model", model);

	}
	
	@RequestMapping(value="/poInsFullCAdminList", method = RequestMethod.GET)
	public ModelAndView poInsFullList(ModelMap model) {
	 
		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		 model.put("user", user);
				
		List<DisputeReports> dispute = disputeReportsDAO.getList().getResultList();
		
		if(dispute!=null && dispute.size()>0)
		{
			 model.put("dispute", dispute);
			 return new ModelAndView("poInsFullCAdminList","model",model);
		}

		else
		{
			return new ModelAndView("noDataFoundCAdmin", "model", model);
		}
	   
	 }
	@RequestMapping(value = "/addOrModifyReportCAdminView", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportView(@RequestParam Long id, ModelMap model,
			RedirectAttributes attributes) {

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

		return new ModelAndView("addOrModifyReportCAdminView", "model", model);
	}
	@RequestMapping(value = "/addOrModifyReportCAdminCompare", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportCompare(@RequestParam Long id, ModelMap model,
			RedirectAttributes attributes) {

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
		
		Float total= dispute.getDefectQty() - dispute.getSuppDefectQty();
		Float total1= dispute.getRepairCost() - dispute.getSuppRepairCost();
		

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);
		model.put("total", total);
		model.put("total1", total1);

		return new ModelAndView("addOrModifyReportCAdminCompare", "model", model);
	}
	
	@RequestMapping(value = "/addOrModifyReportInvoiceCAdminFullList", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportVendorFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<InvoiceReports> disputeList = invoiceReportsDAO.getList()
				.getResultList();

		if (disputeList != null && disputeList.size() > 0) {
			model.put("disputeList", disputeList);
			return new ModelAndView("addOrModifyReportInvoiceCAdminFullList", "model",
					model);
		} else {
			return new ModelAndView("noDataFoundCAdmin", "model", model);
		}

	}

	@RequestMapping(value = "/addOrModifyReportInvoiceCAdminView", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportInvoiceView(@RequestParam Long id,
			ModelMap model, RedirectAttributes attributes) {

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
		disputeReportsForm.setSuppDefectCostForGoods(dispute
				.getSuppDefectCostForGoods());
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

		return new ModelAndView("addOrModifyReportInvoiceCAdminView", "model", model);
	}

	@RequestMapping(value = "/addOrModifyReportInvoiceCAdminCompare", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportInvoiceCompare(@RequestParam Long id,
			ModelMap model, RedirectAttributes attributes) {

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
		disputeReportsForm.setDefectCostForGoods(dispute
				.getDefectCostForGoods());
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
		disputeReportsForm.setSuppDefectCostForGoods(dispute
				.getSuppDefectCostForGoods());
		disputeReportsForm.setSuppDamageStatus(dispute.getSuppDamageStatus());
		disputeReportsForm.setSuppReplacement(dispute.getSuppReplacement());
		disputeReportsForm.setSuppRepairCost(dispute.getSuppRepairCost());
		disputeReportsForm.setAccept(dispute.getAccept());
		
		Float total= dispute.getDefectQty() - dispute.getSuppDefectQty();
		Float total1= dispute.getRepairCost() - dispute.getSuppRepairCost();

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);
		model.put("total", total);
		model.put("total1", total1);

		return new ModelAndView("addOrModifyReportInvoiceCAdminCompare", "model",
				model);
	}
	
	@RequestMapping(value = "/wareHouseMngCAdminFullList", method = RequestMethod.GET)
	public ModelAndView wareHouseMngList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		
		List<WareHouseMng> mngList = wareHouseMngDAO.getList().getResultList();

		model.put("user", user);
		model.put("mngList", mngList);

		return new ModelAndView("wareHouseMngCAdminFullList", "model", model);

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

		return new ModelAndView("loginDateCustAdmin", "model", model);

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

		return new ModelAndView("loginDateCustAdmin", "model", model);

	}

}
