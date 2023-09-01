package annona.trade.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annona.services.DateService;
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

import annona.dao.CustomerDAO;
import annona.dao.EndUserDAO;
import annona.domain.CustomerHead;
import annona.domain.EndUser;
import annona.form.EndUserForm;
import annona.services.UploadService;
import annona.trade.dao.BuyerTradeDAO;
import annona.trade.dao.SupplierTradeDAO;
import annona.trade.dao.TBuyingCostDAO;
import annona.trade.dao.TCollateralDAO;
import annona.trade.dao.TDraftsMasterPlanDAO;
import annona.trade.dao.TFullAmountDAO;
import annona.trade.dao.THalfYearlyDAO;
import annona.trade.dao.TMasterPlanDAO;
import annona.trade.dao.TQuarterlyDAO;
import annona.trade.dao.TRepaymentDAO;
import annona.trade.dao.TTransactionDAO;
import annona.trade.dao.TUploadDAO;
import annona.trade.domain.BuyerTrade;
import annona.trade.domain.SupplierTrade;
import annona.trade.domain.TBuyingCost;
import annona.trade.domain.TCollateral;
import annona.trade.domain.TDraftsMasterPlan;
import annona.trade.domain.TFullAmount;
import annona.trade.domain.THalfYearly;
import annona.trade.domain.TMasterPlan;
import annona.trade.domain.TQuarterly;
import annona.trade.domain.TRepayment;
import annona.trade.domain.TTransaction;
import annona.trade.domain.TUploadedFile;
import annona.trade.form.BuyerTradeForm;
import annona.trade.form.SupplierTradeForm;
import annona.trade.form.TCollateralForm;
import annona.trade.form.TFullAmountForm;
import annona.trade.form.THalfYearlyForm;
import annona.trade.form.TMasterPlanForm;
import annona.trade.form.TQuarterlyForm;
import annona.trade.form.TRepaymentForm;
import annona.trade.form.TUploadedFileForm;
import annona.trade.validator.TFileValidator;
import annona.web.AdminController;

@Controller
@RequestMapping("/tUsers")
public class TradeCustomerController  implements ServletContextAware{

	@Autowired
	TFileValidator tfileValidator;
	@Autowired
	JavaMailSender mailSender;
	@Autowired
	EndUserDAO endUserDAOImpl;
	@Autowired
	EndUserForm endUserForm;
	
	@Autowired
	TUploadedFileForm tuploadedFileForm;
	
	@Autowired
	TUploadDAO tuploadDaoImpl;
	@Autowired
	TTransactionDAO transcationDAOImpl;
	@Autowired
	BuyerTradeForm buyerTradeForm;
	@Autowired
	SupplierTradeForm supplierTradeForm;
	
	@Autowired
	TMasterPlanDAO tmasterPlanDAO;
	
	@Autowired
	TCollateralDAO tcollateralDAO;
	
	@Autowired
	TCollateralForm tcollateralForm;
	
	@Autowired
	CustomerDAO customerDAO;
	
	@Autowired
	TMasterPlanForm tMasterPlanForm;
	
	@Autowired
	BuyerTradeDAO buyerTradeDAO;
	
	@Autowired
	TBuyingCostDAO tbuyingCostDAO;
	
	@Autowired
	SupplierTradeDAO supplierTradeDAO;
	
	@Autowired
	UploadService uploadService;
	
	@Autowired
	TDraftsMasterPlanDAO tdraftsMasterPlanDAO;
	
	@Autowired
	TQuarterlyDAO tquarterlyDAO;

	@Autowired
	TQuarterlyForm tquarterlyForm;

	@Autowired
	TFullAmountDAO tfullAmountDAO; 

	@Autowired
	TFullAmountForm tfullAmountForm;

	@Autowired
	THalfYearlyForm thalfYearlyForm;

	@Autowired
	THalfYearlyDAO thalfYearlyDAO;
	
	@Autowired
	TRepaymentForm trepaymentForm;

	@Autowired
	TRepaymentDAO trepaymenyDAO;
	
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

	private EndUser getCurrentLoggedUserDetails() {

		EndUser endUser = endUserDAOImpl.findByUsername(
				getCurrentLoggedUserName()).getSingleResult();

		return endUser;

	}
	@ModelAttribute("requestCurrentUser")
	public EndUser getUserDetails() {
		EndUser enUser = getCurrentLoggedUserDetails();
		String url = "data:image/JPG;base64,"
				+ Base64.encodeBase64String(enUser.getImage());
		enUser.setImageName(url);		
		
		return enUser;
	}

	

	@RequestMapping(value = "/tUser", method = RequestMethod.GET)
	public ModelAndView showUserDashBoard(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("tUserPage", "model", model);

	}
	
	
	@RequestMapping(value = "/themeChange", method = RequestMethod.GET)
	public String getThemeChange(HttpServletRequest request,
			HttpServletResponse response) {

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

		return "redirect:tUser";
	}

	@RequestMapping(value = "/getLocaleLang", method = RequestMethod.GET)
	public String getLocaleLang(HttpServletRequest request,
			HttpServletResponse response) {

		log.info("Received request for locale change");
		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		LocaleResolver localeResolver = new CookieLocaleResolver();
		Locale locale = new Locale(request.getParameter("lang"));
		localeResolver.setLocale(request, response, locale);
		user.setPrefferedLanguage(request.getParameter("lang"));

		endUserDAOImpl.mergeUser(user);

		return "redirect:tUser";
	}
	
	@RequestMapping(value = "/editTradeCustomerProfile", method = RequestMethod.GET)
	public ModelAndView editAdminProfile(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser userProfile = endUserDAOImpl.findId(id);
		String url = "data:image/JPG;base64,"
				+ Base64.encodeBase64String(userProfile.getImage());
		userProfile.setImageName(url);
		
		endUserForm.setImageName(url);
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

		return new ModelAndView("editTradeCustomerProfile", "model", model);

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
		return "redirect:editTradeCustomerProfile?id="+endUserForm.getId();
	}


	@RequestMapping(value = "/confirmEditTradeCustomerProfile", method = RequestMethod.POST)
	public ModelAndView confirmEditAdminProfile(ModelMap model,
			@ModelAttribute EndUserForm endUserForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("confirmEditTradeCustomerProfile", "model", model);

	}

	@RequestMapping(value = "/updateTradeCustomerDetails", method = RequestMethod.POST)
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

		return new ModelAndView("updateCustomerTradeSuccess", "model", model);

	}

	@RequestMapping(value = "/editCustomerTradePWD", method = RequestMethod.GET)
	public ModelAndView editAdminPWD(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser userProfile = endUserDAOImpl.findId(id);

		endUserForm.setId(userProfile.getId());

		endUserForm.setTransactionId(userProfile.getTransactionId());

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("editCustomerTradePWD", "model", model);

	}

	@RequestMapping(value = "/updateEditCustomerTradePWD", method = RequestMethod.POST)
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

		return new ModelAndView("updateCustomerTradeSuccess", "model", model);

	}
	
	@RequestMapping(value = "/tradeShowMail", method = RequestMethod.GET)
	public ModelAndView doSendEmail1(@ModelAttribute ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		return new ModelAndView("tradeQueryMail", "model", model);
	}

	@RequestMapping(value = "/tradeMailSender", method = RequestMethod.POST)
	public ModelAndView doSendEmail(@ModelAttribute ModelMap model,
			HttpServletRequest request) {

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

		// forwards to the view named "Result"
		return new ModelAndView("tradeResult", "model", model);
	}

	@RequestMapping(value = "/tradeShowCurrency", method = RequestMethod.GET)
	public ModelAndView showCurrency(ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		return new ModelAndView("tradeCurrency", "model", model);
	}

	@RequestMapping(value = "/tradeShowcurrencyConversion", method = RequestMethod.GET)
	public ModelAndView showcurrencyConversion(@ModelAttribute ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		return new ModelAndView("tradeShowcurrencyConversion", "model", model);
	}

	
	@RequestMapping(value = "/tfileUploadForm", method = RequestMethod.GET)
	 public ModelAndView getTradingfileUpload( RedirectAttributes attribute,ModelMap model) {  
		
		EndUser user = getCurrentLoggedUserDetails();
		
		 model.put("user", user);
		  model.put("tuploadedFileForm", tuploadedFileForm);
	  
		return new ModelAndView("tuploadForm");  
	 }  
	 @RequestMapping("/fileUpload1")  
	 public ModelAndView fileUploadedForTarding( ModelMap model, RedirectAttributes attribute , @ModelAttribute TUploadedFileForm tuploadedFileForm,BindingResult result) throws RuntimeException, IOException { 

		 
		 EndUser user = getCurrentLoggedUserDetails();

			 TUploadedFile upload = new TUploadedFile();
		
			 
				List<MultipartFile> files = tuploadedFileForm.getFiles();
				Set<byte[]> filesList = new HashSet<byte[]>();
				Set<String> fileNameList = new HashSet<String>();
				for (MultipartFile multipartFile : files) {		
					filesList.add(multipartFile.getBytes());
					fileNameList.add(multipartFile.getOriginalFilename());
				}
				  upload.setFiles(filesList);
				  upload.setFileNames(fileNameList);
				  upload.setCustomerName(user.getUserName());
				  upload.setDocument(tuploadedFileForm.getDocument());
				  upload.setCustomerHeadKey(tuploadedFileForm.getCustomerHeadKey());
				  upload.setCustomerHeadName(tuploadedFileForm.getCustomerHeadName());
				  upload.setReason(tuploadedFileForm.getReason());
				  upload.setTransactionId(tuploadedFileForm.getTransactionId());
				  upload.setStatus("Pending");
				  Date date = DateService.loginDate;
				  upload.setUploadDate(date);
				
				  tuploadDaoImpl.createUser(upload);
			     TTransaction trans = new TTransaction();

			trans.setTransactionId(tuploadedFileForm.getTransactionId());
			trans.setTransactionStatus("Upload Document");
			trans.setTransactionType("Uploaded Successfully");

			transcationDAOImpl.insertTransaction(trans);
	  
			model.put("tuploadedFileForm", tuploadedFileForm);
	         model.put("user", user);
	  
	  attribute.addFlashAttribute("success","Saved Successfully. ");
	  return new ModelAndView("showFile1", "model", model);  
	 } 
	 
	//Add New Buyer 
		@RequestMapping(value = "/addnewBuyerTrade", method = RequestMethod.GET)
		public ModelAndView newBuyer(ModelMap model) {

			EndUser user = endUserDAOImpl
					.findByUsername(getCurrentLoggedUserName()).getSingleResult();

			buyerTradeForm = new BuyerTradeForm();
			 buyerTradeForm.setEndUser(user); 
			buyerTradeForm.setName(user.getUserName());
			 

			List<BuyerTrade> newbuyeList = buyerTradeDAO.getByPending().getResultList();

			model.put("user", user);
			model.put("newbuyeList", newbuyeList);
			model.put("buyerTradeForm", buyerTradeForm);

			return new ModelAndView("addnewBuyerTrade", "model", model);
		}

	

		@RequestMapping(value = "/updatenewBuyerTradePage", method = RequestMethod.POST)
		public ModelAndView updatenewBuyerPage(
				@ModelAttribute BuyerTradeForm buyerTradeForm,
				RedirectAttributes attributes, ModelMap model) {
			
			  List<BuyerTrade> buyer = buyerTradeDAO.findByName(buyerTradeForm.getBuyerName()).getResultList();
				
				List<EndUser> endUser = endUserDAOImpl.findByUsername(
						buyerTradeForm.getBuyerName()).getResultList();
				if (endUser.size() == 0 || buyer.size()==0) {

				EndUser user = getCurrentLoggedUserDetails();

			
			model.put("user", user);
			model.put("buyerTradeForm", buyerTradeForm);

		return new ModelAndView("updatenewBuyerTradePage", "model", model);
				}
				else {
					attributes.addFlashAttribute("success", "Customer Name Already Exists");

					return new ModelAndView("redirect:addnewBuyerTrade");

				}

		}

		@RequestMapping(value = "/newbuyerConfirmTardeList", method = RequestMethod.POST)
		public ModelAndView approvalConfirm(
				@ModelAttribute BuyerTradeForm buyerTradeForm, ModelMap model,
				RedirectAttributes attribute) {

			BuyerTrade newBuyer = new BuyerTrade();

			TTransaction transaction = new TTransaction();
			newBuyer.setId(buyerTradeForm.getId());
			newBuyer.setBuyerName(buyerTradeForm.getBuyerName());
			newBuyer.setIfsc(buyerTradeForm.getIfsc());
			newBuyer.setAddress(buyerTradeForm.getAddress());
			newBuyer.setAltcontactNum(buyerTradeForm.getAltcontactNum());
			newBuyer.setAltEmail(buyerTradeForm.getAltEmail());
			newBuyer.setApproveDate(buyerTradeForm.getApproveDate());
			newBuyer.setBank(buyerTradeForm.getBank());
			newBuyer.setBankEmail(buyerTradeForm.getBankEmail());
			newBuyer.setBranch(buyerTradeForm.getBranch());
			newBuyer.setCity(buyerTradeForm.getCity());
			newBuyer.setComment(buyerTradeForm.getComment());
			newBuyer.setCompanyName(buyerTradeForm.getCompanyName());
			newBuyer.setContactNum(buyerTradeForm.getContactNum());
			newBuyer.setCountry(buyerTradeForm.getCountry());
            newBuyer.setEmail(buyerTradeForm.getEmail());
            newBuyer.setName(buyerTradeForm.getName());
			newBuyer.setPinCode(buyerTradeForm.getPinCode());
			newBuyer.setState(buyerTradeForm.getState());
			newBuyer.setcStatus("Pending");
			newBuyer.setUniqueKey(buyerTradeForm.getUniqueKey());
			newBuyer.setCurrencydeal(buyerTradeForm.getCurrencydeal());
			newBuyer.setTransactionId(buyerTradeForm.getTransactionId());
			transaction.setTransactionId(buyerTradeForm.getTransactionId());
			transaction.setTransactionType("Role");
			transaction.setTransactionStatus("Buyer saved successfully");
			buyerTradeDAO.createUser(newBuyer);
			transcationDAOImpl.insertTransaction(transaction);

			attribute.addFlashAttribute("success", "Saved Successfully");

			model.put("buyerTradeForm", buyerTradeForm);

			return new ModelAndView("newbuyerConfirmTardeList", "model", model);

		}

	
		@RequestMapping(value = "/selectBuyerForTraderUpdate", method = RequestMethod.GET)
		public ModelAndView selectBuyerUpdate(ModelMap model,
				@RequestParam("id") Long id, RedirectAttributes attributes) {

			EndUser user = getCurrentLoggedUserDetails();

			BuyerTrade newBuyer = buyerTradeDAO.findId(id);

			buyerTradeForm.setId(newBuyer.getId());
			buyerTradeForm.setContactNum(newBuyer.getContactNum());
			buyerTradeForm.setAltcontactNum(newBuyer.getAltcontactNum());
			buyerTradeForm.setAltEmail(newBuyer.getAltEmail());
			buyerTradeForm.setAddress(newBuyer.getAddress());
			buyerTradeForm.setBranch(newBuyer.getBranch());
			buyerTradeForm.setPinCode(newBuyer.getPinCode());
			buyerTradeForm.setName(newBuyer.getName());
			buyerTradeForm.setBuyerName(newBuyer.getBuyerName());
			buyerTradeForm.setIfsc(newBuyer.getIfsc());
			buyerTradeForm.setBank(newBuyer.getBank());
			buyerTradeForm.setBankEmail(newBuyer.getBankEmail());
			buyerTradeForm.setBranch(newBuyer.getBranch());
			buyerTradeForm.setCountry(newBuyer.getCountry());
			buyerTradeForm.setState(newBuyer.getState());
			buyerTradeForm.setCity(newBuyer.getCity());
			buyerTradeForm.setEmail(newBuyer.getEmail());
	       buyerTradeForm.setCompanyName(newBuyer.getCompanyName());
	       buyerTradeForm.setCurrencydeal(newBuyer.getCurrencydeal());
	       buyerTradeForm.setTransactionId(newBuyer.getTransactionId());
            

			model.put("user", user);

			model.put("buyerTradeForm", buyerTradeForm);

			return new ModelAndView("selectBuyerForTraderUpdate", "model", model);

		}

		@RequestMapping(value = "/selectBuyerForTraderUpdate2", method = RequestMethod.POST)
		public ModelAndView selectBuyerUpdate2(ModelMap model,
				@ModelAttribute BuyerTradeForm buyerTradeForm) {

			EndUser user = getCurrentLoggedUserDetails();

			model.put("user", user);

			model.put("buyerTradeForm", buyerTradeForm);

			return new ModelAndView("selectBuyerForTraderUpdate2", "model", model);

		}

		@RequestMapping(value = "/selectBuyerForTraderUpdate3", method = RequestMethod.POST)
		public ModelAndView selectBuyerUpdate3(
				@ModelAttribute BuyerTradeForm buyerTradeForm, ModelMap model,
				RedirectAttributes attribute) {

			BuyerTrade newBuyer = buyerTradeDAO.findId(buyerTradeForm.getId());
			TTransaction transaction = new TTransaction();
			newBuyer.setBuyerName(buyerTradeForm.getBuyerName());
			newBuyer.setIfsc(buyerTradeForm.getIfsc());
			newBuyer.setAddress(buyerTradeForm.getAddress());
			newBuyer.setAltcontactNum(buyerTradeForm.getAltcontactNum());
			newBuyer.setAltEmail(buyerTradeForm.getAltEmail());
			newBuyer.setApproveDate(buyerTradeForm.getApproveDate());
			newBuyer.setBank(buyerTradeForm.getBank());
			newBuyer.setBankEmail(buyerTradeForm.getBankEmail());
			newBuyer.setBranch(buyerTradeForm.getBranch());
			newBuyer.setCity(buyerTradeForm.getCity());
			newBuyer.setComment(buyerTradeForm.getComment());
			newBuyer.setCompanyName(buyerTradeForm.getCompanyName());
			newBuyer.setContactNum(buyerTradeForm.getContactNum());
			newBuyer.setCountry(buyerTradeForm.getCountry());
            newBuyer.setEmail(buyerTradeForm.getEmail());
			newBuyer.setId(buyerTradeForm.getId());
			newBuyer.setName(buyerTradeForm.getName());
			newBuyer.setPinCode(buyerTradeForm.getPinCode());
			newBuyer.setState(buyerTradeForm.getState());
			newBuyer.setCurrencydeal(buyerTradeForm.getCurrencydeal());
			newBuyer.setcStatus("Pending");

			transaction.setTransactionType("Buyer Details");
			transaction
					.setTransactionStatus("Buyer Details Update successfully");
			transaction.setTransactionId(buyerTradeForm.getTransactionId());
			model.put("buyerTradeForm", buyerTradeForm);

			buyerTradeDAO.update(newBuyer);
			transcationDAOImpl.insertTransaction(transaction);
			return new ModelAndView("selectBuyerForTraderUpdate3", "model", model);
		}	
	
	
	 

		// Supplier
		@RequestMapping(value = "/newSupplierPageTrade", method = RequestMethod.GET)
		public ModelAndView newSupplier(ModelMap model) {

			EndUser user = endUserDAOImpl
					.findByUsername(getCurrentLoggedUserName()).getSingleResult();

			List<SupplierTrade> supplierList = supplierTradeDAO.getByPending()
					.getResultList();
			supplierTradeForm = new SupplierTradeForm();
		    supplierTradeForm.setEndUser(user); 
			supplierTradeForm.setName(user.getUserName());
			model.put("user", user);
			model.put("supplierList", supplierList);

			model.put("supplierTradeForm", supplierTradeForm);

			return new ModelAndView("newSupplierPageTrade", "model", model);
		}

		

		@RequestMapping(value = "/SupplierPageForwardTrade", method = RequestMethod.POST)
		public ModelAndView selectsupplierUpdat(
				@ModelAttribute SupplierTradeForm supplierTradeForm,
				RedirectAttributes attributes, ModelMap model) {
			
	       List<SupplierTrade> supplier = supplierTradeDAO.findByName(supplierTradeForm.getSupplierName()).getResultList();
			
			List<EndUser> endUser = endUserDAOImpl.findByUsername(
					supplierTradeForm.getSupplierName()).getResultList();
			if (endUser.size() == 0 || supplier.size()==0) {

			EndUser user = getCurrentLoggedUserDetails();

			model.put("user", user);

			model.put("supplierTradeForm", supplierTradeForm);

			return new ModelAndView("SupplierPageForwardTrade", "model", model);
			}
			else {
				attributes.addFlashAttribute("success", "Customer Name Already Exists");

				return new ModelAndView("redirect:newSupplierPageTrade");

			}

		}

		@RequestMapping(value = "/supplierApprovalTradeList", method = RequestMethod.POST)
		public ModelAndView approvalConfirm(
				@ModelAttribute SupplierTradeForm supplierTradeForm, ModelMap model,
				RedirectAttributes attribute) {

			SupplierTrade supplier = new SupplierTrade();

			TTransaction transaction = new TTransaction();

			
			supplier.setAddress(supplierTradeForm.getAddress());
			supplier.setAltContactNum(supplierTradeForm.getAltContactNum());
			supplier.setAltEmail(supplierTradeForm.getAltEmail());
			supplier.setApproveDate(supplierTradeForm.getApproveDate());
			supplier.setBank(supplierTradeForm.getBank());
			supplier.setIfsc(supplierTradeForm.getIfsc());
			supplier.setBankEmail(supplierTradeForm.getBankEmail());
			supplier.setBranch(supplierTradeForm.getBranch());
			supplier.setCity(supplierTradeForm.getCity());
			supplier.setComment(supplierTradeForm.getComment());
			supplier.setCompanyName(supplierTradeForm.getCompanyName());
			supplier.setContactNum(supplierTradeForm.getContactNum());
			supplier.setCountry(supplierTradeForm.getCountry());
			supplier.setSupplierName(supplierTradeForm.getSupplierName());
		    supplier.setEmail(supplierTradeForm.getEmail());
			supplier.setName(supplierTradeForm.getName());
			supplier.setPinCode(supplierTradeForm.getPinCode());
			supplier.setState(supplierTradeForm.getState());
			supplier.setCurrencydeal(supplierTradeForm.getCurrencydeal());
			supplier.setcStatus("Pending");
			supplier.setTransactionId(supplierTradeForm.getTransactionId());
			transaction.setTransactionId(supplierTradeForm.getTransactionId());
			transaction.setTransactionType("Supplier Details");
			transaction.setTransactionStatus("Supplier Details saved successfully");
			supplierTradeDAO.createUser(supplier);
			transcationDAOImpl.insertTransaction(transaction);

			attribute.addFlashAttribute("success", "Saved Successfully");

			model.put("supplierTradeForm", supplierTradeForm);

			return new ModelAndView("supplierApprovalTradeListSuccess", "model", model);

		}

		@RequestMapping(value = "/selectsupplierTradeUpdate", method = RequestMethod.GET)
		public ModelAndView selectsupplierUpdat(ModelMap model,
				@RequestParam("id") Long id, RedirectAttributes attributes) {

			EndUser user = getCurrentLoggedUserDetails();

			SupplierTrade supplier = supplierTradeDAO.findId(id);

			supplierTradeForm.setId(supplier.getId());
			supplierTradeForm.setName(supplier.getName());
			supplierTradeForm.setPinCode(supplier.getPinCode());
			supplierTradeForm.setCountry(supplier.getCountry());
			supplierTradeForm.setSupplierName(supplier.getSupplierName());
			supplierTradeForm.setCompanyName(supplier.getCompanyName());
			supplierTradeForm.setEmail(supplier.getEmail());
			supplierTradeForm.setCurrencydeal(supplier.getCurrencydeal());
			supplierTradeForm.setBranch(supplier.getBranch());
			supplierTradeForm.setAltContactNum(supplier.getAltContactNum());
			supplierTradeForm.setAltEmail(supplier.getAltEmail());
			supplierTradeForm.setBank(supplier.getBank());
			supplierTradeForm.setIfsc(supplier.getIfsc());
			supplierTradeForm.setBankEmail(supplier.getBankEmail());
			supplierTradeForm.setCity(supplier.getCity());
			supplierTradeForm.setState(supplier.getState());
			supplierTradeForm.setContactNum(supplier.getContactNum());
			supplierTradeForm.setAddress(supplier.getAddress());
			supplierTradeForm.setTransactionId(supplier.getTransactionId());

			model.put("user", user);

			model.put("supplierTradeForm", supplierTradeForm);

			return new ModelAndView("selectsupplierTradeUpdate", "model", model);

		}

		@RequestMapping(value = "/selectSupplierTradeUpdate2", method = RequestMethod.POST)
		public ModelAndView selectSupplierUpdate2(ModelMap model,
				@ModelAttribute SupplierTradeForm supplierTradeForm,
				RedirectAttributes attributes) {

			EndUser user = getCurrentLoggedUserDetails();
			SupplierTrade supplier = supplierTradeDAO.findId(supplierTradeForm.getId());

			model.put("user", user);
			model.put("supplier", supplier);

			model.put("supplierTradeForm", supplierTradeForm);

			return new ModelAndView("selectSupplierTradeUpdate2", "model", model);

		}

		@RequestMapping(value = "/selectSupplierTradeUpdate3", method = RequestMethod.POST)
		public ModelAndView selectSupplierUpdate3(
				@ModelAttribute SupplierTradeForm supplierTradeForm, ModelMap model,
				RedirectAttributes attribute) {
			EndUser user = getCurrentLoggedUserDetails();
			SupplierTrade supplier = supplierTradeDAO.findId(supplierTradeForm.getId());
			TTransaction transaction = new TTransaction();
			supplier.setAddress(supplierTradeForm.getAddress());
			supplier.setSupplierName(supplierTradeForm.getSupplierName());
			supplier.setAltContactNum(supplierTradeForm.getAltContactNum());
			supplier.setAltEmail(supplierTradeForm.getAltEmail());
			supplier.setApproveDate(supplierTradeForm.getApproveDate());
			supplier.setBank(supplierTradeForm.getBank());
			supplier.setIfsc(supplierTradeForm.getIfsc());
			supplier.setBankEmail(supplierTradeForm.getBankEmail());
			supplier.setBranch(supplierTradeForm.getBranch());
			supplier.setCity(supplierTradeForm.getCity());
			supplier.setComment(supplierTradeForm.getComment());
			supplier.setCompanyName(supplierTradeForm.getCompanyName());
			supplier.setContactNum(supplierTradeForm.getContactNum());
			supplier.setCountry(supplierTradeForm.getCountry());
			supplier.setCustomerPrefix(supplierTradeForm.getCustomerPrefix());
		    supplier.setEmail(supplierTradeForm.getEmail());
			supplier.setId(supplierTradeForm.getId());
			supplier.setName(supplierTradeForm.getName());
			supplier.setPinCode(supplierTradeForm.getPinCode());
			supplier.setState(supplierTradeForm.getState());
			supplier.setCurrencydeal(supplierTradeForm.getCurrencydeal());
			supplier.setcStatus("Pending");
			supplier.setTransactionId(supplierTradeForm.getTransactionId());
			transaction.setTransactionId(supplierTradeForm.getTransactionId());
			transaction.setTransactionType("Supplier Details");
			transaction.setTransactionStatus("Supplier Details Status saved successfully");
			transaction.setTransactionId(supplierTradeForm.getTransactionId());
			model.put("supplierTradeForm", supplierTradeForm);
			model.put("user", user);
			supplierTradeDAO.update(supplier);
			transcationDAOImpl.insertTransaction(transaction);
			return new ModelAndView("selectSupplierTradeUpdate3", "model", model);
		}
		
	//Buyer List
		@RequestMapping(value = "/buyerTradeUserList", method = RequestMethod.GET)
		public ModelAndView getAllBuyerTradeList(ModelMap model,
				HttpServletRequest request, HttpServletResponse response) {
			
			EndUser user = endUserDAOImpl
					.findByUsername(getCurrentLoggedUserName()).getSingleResult();
			List<BuyerTrade> buyerList = buyerTradeDAO.findByName(user.getUserName()).getResultList();

			if (buyerList != null && buyerList.size() > 0) {

				model.put("user", user);
		
				model.put("buyerList", buyerList);

			}
         return new ModelAndView("buyerTradeUserList", "model", model);

		}	
		//Supplier List
		@RequestMapping(value = "/supplierTradeUserList", method = RequestMethod.GET)
		public ModelAndView getAllCustomerListTrade(ModelMap model,HttpServletRequest request, HttpServletResponse response) {

			EndUser user = endUserDAOImpl
					.findByUsername(getCurrentLoggedUserName()).getSingleResult();
			List<SupplierTrade> supplierList = supplierTradeDAO.findByName(user.getUserName()).getResultList();

			if (supplierList != null && supplierList.size() > 0) {

				model.put("user", user);
		
				model.put("supplierList", supplierList);

			}
			
			
 
    return new ModelAndView("supplierTradeUserList", "model", model);

		}
		
		/*MasterPlan Flow*/
		@RequestMapping(value = "/tmasterPlan", method = RequestMethod.GET)
		public ModelAndView masterPlan(ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();
			
			List<CustomerHead> custprefix = customerDAO.getCustomerDetailsList(user.getUserName()).getResultList();
			{
			tMasterPlanForm.setCustomerPrefix(custprefix.get(0).getCustomerPrefix());
			}

			model.put("user", user);
			model.put("tMasterPlanForm", tMasterPlanForm);
			return new ModelAndView("tmasterPlan", "model", model);
		}

		@RequestMapping(value = "/tmasterPlanCurrency", method = RequestMethod.POST)
		public ModelAndView masterPlanPost(ModelMap model,
				@ModelAttribute TMasterPlanForm tMasterPlanForm,
				RedirectAttributes attributes) {

			EndUser user = getCurrentLoggedUserDetails();
			 
			tMasterPlanForm.setCustomerPrefix(tMasterPlanForm.getCustomerPrefix());
			tMasterPlanForm.setCustomer(user.getUserName());
			tMasterPlanForm.setCurrencySymbol(tMasterPlanForm.getCurrencySymbol());

			model.put("user", user);
			model.put("tMasterPlanForm", tMasterPlanForm);

			return new ModelAndView("tmasterPlan1", "model", model);

		}

		@RequestMapping(value = "/tmasterPlan1", method = RequestMethod.GET)
		public ModelAndView masterPlanConfirm(ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();
	    
			model.put("user", user);
			model.put("tMasterPlanForm", tMasterPlanForm);
			return new ModelAndView("tmasterPlan1", "model", model);
		}

		@RequestMapping(value = "/tmasterPlanInfoConfirm", method = RequestMethod.POST)
		public ModelAndView masterPlanInfoConfirm(ModelMap model,
				@ModelAttribute TMasterPlanForm tMasterPlanForm,
				RedirectAttributes attributes) {

			EndUser user = getCurrentLoggedUserDetails();

			tMasterPlanForm.setCustomer(tMasterPlanForm.getCustomer());
			tMasterPlanForm.setCurrencySymbol(tMasterPlanForm.getCurrencySymbol());
			tMasterPlanForm.setCategory(tMasterPlanForm.getCategory());
			tMasterPlanForm.setProduct(tMasterPlanForm.getProduct());
			tMasterPlanForm.setDescription(tMasterPlanForm.getDescription());
			tMasterPlanForm.setLicence(tMasterPlanForm.getLicence());
			tMasterPlanForm.setWeight(tMasterPlanForm.getWeight());
			tMasterPlanForm.setQuantity(tMasterPlanForm.getQuantity());
			tMasterPlanForm.setBuyingCost(tMasterPlanForm.getBuyingCost());
			tMasterPlanForm.setTenure(tMasterPlanForm.getTenure());
			tMasterPlanForm.setTransactionId(tMasterPlanForm.getTransactionId());
			tMasterPlanForm.setMasterKey(tMasterPlanForm.getMasterKey());
			tMasterPlanForm.setCurrencySymbol(tMasterPlanForm.getCurrencySymbol());
			tMasterPlanForm.setCurrencySymbol2(tMasterPlanForm.getCurrencySymbol2());
			model.put("user", user);
			model.put("tMasterPlanForm", tMasterPlanForm);
			attributes.addFlashAttribute("successFull",
					" cost Does not Match Buyer Cost. ");
			return new ModelAndView("tmasterPlanInfoConfirm", "model", model);

		}
		@RequestMapping(value = "/tmasterPlanInfoPost", method = RequestMethod.POST)
		 public ModelAndView masterPlanInfoPost(ModelMap model,
		   @ModelAttribute TMasterPlanForm tMasterPlanForm,
		   BindingResult result, RedirectAttributes attributes) {

		  EndUser user = getCurrentLoggedUserDetails();

		  String allCountrys = tMasterPlanForm.getCountry();
		  String[] countrys = allCountrys.split(",");

		  String allMaterials = tMasterPlanForm.getMaterial();
		  String[] materials = allMaterials.split(",");

		  String allSuppliers = tMasterPlanForm.getSupplierName();
		  String[] suppliers = allSuppliers.split(",");

		  String allQuantity = tMasterPlanForm.getQuantityStr();
		  String[] quantity = allQuantity.split(",");

		  String allCost = tMasterPlanForm.getCostStr();
		  String[] cost = allCost.split(",");

		  List<TMasterPlanForm> masterplanList = new ArrayList<TMasterPlanForm>();
		  for (int count = 0; count < materials.length; count++) {

		   TMasterPlanForm masterForm = new TMasterPlanForm();

		   masterForm.setCountry(countrys[count]);
		   masterForm.setSupplierName(suppliers[count]);
		   masterForm.setMaterial(materials[count]);
		   masterForm.setBuyerQuantity(Integer.parseInt(quantity[count]));
		   masterForm.setCost(Float.parseFloat(cost[count]));

		   masterplanList.add(masterForm);
		  }
		  for (TMasterPlanForm value : masterplanList) {

		   TBuyingCost buying = new TBuyingCost();

		   buying.setCountry(value.getCountry());
		   buying.setRegulation(value.getRegulation());
		   buying.setMaterial(value.getMaterial());
		   buying.setSupplierName(value.getSupplierName());
		   buying.setQuantity(value.getBuyerQuantity());
		   buying.setCost(value.getCost());
		   buying.setTransactionId(tMasterPlanForm.getTransactionId());
		   buying.setCustomer(tMasterPlanForm.getCustomer());
		   buying.setMasterKey(tMasterPlanForm.getMasterKey());
		   buying.setTransactionId(tMasterPlanForm.getTransactionId());

		   tbuyingCostDAO.createMasterPlan(buying);
		  }

		 

		   TMasterPlan master = new TMasterPlan();



		  master.setCustomer(tMasterPlanForm.getCustomer());
		  master.setCurrencySymbol(tMasterPlanForm.getCurrencySymbol());
		  master.setCategory(tMasterPlanForm.getCategory());
		  master.setProduct(tMasterPlanForm.getProduct());
		  master.setDescription(tMasterPlanForm.getDescription());
		  master.setLicence(tMasterPlanForm.getLicence());
		  master.setWeight(tMasterPlanForm.getWeight());
		  master.setQuantity(tMasterPlanForm.getQuantity());
		  master.setBuyingCost(tMasterPlanForm.getBuyingCost());
		  master.setTenure(tMasterPlanForm.getTenure());
		  master.setMasterKey(tMasterPlanForm.getMasterKey());
		  master.setTransactionId(tMasterPlanForm.getTransactionId());
		  master.setCustomerEmail(user.getEmail());
		master.setCurrencySymbol(tMasterPlanForm.getCurrencySymbol());
		  master.setCustomerPrefix(tMasterPlanForm.getCustomerPrefix());
		  master.setaStatus("pending");
		  master.setAccept("None");
		  master.setApprovalSent("No");
		  master.setFlag(1);

		  Date date = DateService.loginDate;
		  master.setMasterPlanDate(date);

		  tmasterPlanDAO.createMasterPlan(master);

		  TTransaction trans = new TTransaction();

		  trans.setTransactionId(tMasterPlanForm.getTransactionId());
		  trans.setTransactionStatus("Master Plan Saved");
		  trans.setTransactionType("Master Plan");

		  transcationDAOImpl.insertTransaction(trans);
		  // }

		  model.put("user", user);
		  model.put("tMasterPlanForm", tMasterPlanForm);

		  return new ModelAndView("tmasterPlanTransaction", "model", model);

		 }

		@RequestMapping(value = "/tdraftsMasterPlan", method = RequestMethod.POST)
		public ModelAndView draftsMasterPlan(ModelMap model,
				@ModelAttribute TMasterPlanForm tMasterPlanForm,
				RedirectAttributes attributes) {

			EndUser user = getCurrentLoggedUserDetails();

			TDraftsMasterPlan drafts = new TDraftsMasterPlan();

			drafts.setCustomer(tMasterPlanForm.getCustomer());
			drafts.setCurrencySymbol(tMasterPlanForm.getCurrencySymbol());
			drafts.setCategory(tMasterPlanForm.getCategory());
			drafts.setProduct(tMasterPlanForm.getProduct());
			drafts.setDescription(tMasterPlanForm.getDescription());
			drafts.setLicence(tMasterPlanForm.getLicence());
			drafts.setWeight(tMasterPlanForm.getWeight());
			drafts.setQuantity(tMasterPlanForm.getQuantity());
			drafts.setBuyingCost(tMasterPlanForm.getBuyingCost());
			drafts.setTenure(tMasterPlanForm.getTenure());
			drafts.setWorkingCapital(tMasterPlanForm.getWorkingCapital());
			drafts.setWcTenure(tMasterPlanForm.getWcTenure());
			drafts.setSellingCost(tMasterPlanForm.getSellingCost());
			drafts.setSellingTenure(tMasterPlanForm.getSellingTenure());
			drafts.setTransactionId(tMasterPlanForm.getTransactionId());
			Date date = DateService.loginDate;
			drafts.setDraftsDate(date);
			drafts.setFlag(0);
			tdraftsMasterPlanDAO.createDrafts(drafts);

			model.put("user", user);
			model.put("tMasterPlanForm", tMasterPlanForm);
			attributes.addFlashAttribute("successFull", " Saved to Drafts. ");

			return new ModelAndView("redirect:tmasterPlan");

		}

		@RequestMapping(value = "/tmasterPlanTransaction", method = RequestMethod.GET)
		public ModelAndView masterPlanTransaction(ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();

			model.put("user", user);
			model.put("tMasterPlanForm", tMasterPlanForm);

			return new ModelAndView("tmasterPlanTransaction", "model", model);

		}
		@RequestMapping(value = "/tmasterPlanDraftsList", method = RequestMethod.GET)
		public ModelAndView masterPlanDraftsList(ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();

			List<TDraftsMasterPlan> draftsList = tdraftsMasterPlanDAO
					.getByCustomerAndFlag(user.getUserName()).getResultList();

			if (draftsList != null && draftsList.size() > 0) {

				model.put("user", user);
				model.put("draftsList", draftsList);
			}
			return new ModelAndView("tmasterPlanDraftsList", "model", model);

		}

		@RequestMapping(value = "/tdraftsMasterPlan1", method = RequestMethod.GET)
		public ModelAndView draftsMasterPlanConfirm(@RequestParam("id") Long id,
				ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();

			TDraftsMasterPlan drafts = tdraftsMasterPlanDAO.getByMasterPlanId(id);

			tMasterPlanForm.setId(drafts.getId());
			tMasterPlanForm.setCustomer(drafts.getCustomer());
			tMasterPlanForm.setCurrencySymbol(drafts.getCurrencySymbol());
			tMasterPlanForm.setBuyingCost(drafts.getBuyingCost());
			tMasterPlanForm.setCategory(drafts.getCategory());
			tMasterPlanForm.setProduct(drafts.getProduct());
			tMasterPlanForm.setDescription(drafts.getDescription());
			tMasterPlanForm.setLicence(drafts.getLicence());
			tMasterPlanForm.setWeight(drafts.getWeight());
			tMasterPlanForm.setQuantity(drafts.getQuantity());
			tMasterPlanForm.setTenure(drafts.getTenure());
			

			model.put("user", user);
			model.put("tMasterPlanForm", tMasterPlanForm);
			return new ModelAndView("tdraftsMasterPlan1", "model", model);
		}

		@RequestMapping(value = "/tdraftsMasterPlanInfoConfirm", method = RequestMethod.POST)
		public ModelAndView draftsMasterPlanInfoConfirm(ModelMap model,
				@ModelAttribute TMasterPlanForm tMasterPlanForm,
				RedirectAttributes attributes) {

			EndUser user = getCurrentLoggedUserDetails();

			tMasterPlanForm.setId(tMasterPlanForm.getId());
			tMasterPlanForm.setCustomer(tMasterPlanForm.getCustomer());
			tMasterPlanForm.setCurrencySymbol(tMasterPlanForm.getCurrencySymbol());
			tMasterPlanForm.setCategory(tMasterPlanForm.getCategory());
			tMasterPlanForm.setProduct(tMasterPlanForm.getProduct());
			tMasterPlanForm.setDescription(tMasterPlanForm.getDescription());
			tMasterPlanForm.setLicence(tMasterPlanForm.getLicence());
			tMasterPlanForm.setWeight(tMasterPlanForm.getWeight());
			tMasterPlanForm.setQuantity(tMasterPlanForm.getQuantity());
			tMasterPlanForm.setBuyingCost(tMasterPlanForm.getBuyingCost());
			tMasterPlanForm.setTenure(tMasterPlanForm.getTenure());
			tMasterPlanForm.setTransactionId(tMasterPlanForm.getTransactionId());
			tMasterPlanForm.setMasterKey(tMasterPlanForm.getMasterKey());

			model.put("user", user);
			model.put("tMasterPlanForm", tMasterPlanForm);
			attributes.addFlashAttribute("successFull",
					" cost Does not Match Buyer Cost. ");
			return new ModelAndView("tdraftsMasterPlanInfoConfirm", "model", model);

		}

		@RequestMapping(value = "/tdraftsMasterPlanInfoPost", method = RequestMethod.POST)
		public ModelAndView DraftsMasterPlanInfoPost(ModelMap model,
				@ModelAttribute TMasterPlanForm tMasterPlanForm,
				BindingResult result, RedirectAttributes attributes) {

			EndUser user = getCurrentLoggedUserDetails();

			Float buyingCost = tMasterPlanForm.getBuyingCost();
			Float totalBreakDownCost = 0.0f;

			String allCountrys = tMasterPlanForm.getCountry();
			String[] countrys = allCountrys.split(",");

			String allMaterials = tMasterPlanForm.getMaterial();
			String[] materials = allMaterials.split(",");

			String allQuantity = tMasterPlanForm.getQuantityStr();
			String[] quantity = allQuantity.split(",");

			String allCost = tMasterPlanForm.getCostStr();
			String[] cost = allCost.split(",");

			List<TMasterPlanForm> masterplanList = new ArrayList<TMasterPlanForm>();
			for (int count = 0; count < materials.length; count++) {

				TMasterPlanForm masterForm = new TMasterPlanForm();

				masterForm.setCustomer(tMasterPlanForm.getCustomer());
				masterForm.setMasterKey(tMasterPlanForm.getMasterKey());
				masterForm.setTransactionId(tMasterPlanForm.getTransactionId());
				masterForm.setCountry(countrys[count]);
				masterForm.setMaterial(materials[count]);
				masterForm.setBuyerQuantity(Integer.parseInt(quantity[count]));
				masterForm.setCost(Float.parseFloat(cost[count]));

				totalBreakDownCost = totalBreakDownCost + masterForm.getCost();

				masterplanList.add(masterForm);
			}
			if (totalBreakDownCost.compareTo(buyingCost) != 0) {

				model.put("user", user);
				model.put("tMasterPlanForm", tMasterPlanForm);

				attributes.addFlashAttribute("successFull",
						" cost Does not Match Buyer Cost. ");

				return new ModelAndView("tdraftsMasterPlanInfoConfirm", "model",
						model);
			} else {

				for (TMasterPlanForm value : masterplanList) {

					TBuyingCost buying = new TBuyingCost();
					buying.setCustomer(value.getCustomer());
					buying.setCountry(value.getCountry());
					buying.setRegulation(value.getRegulation());
					buying.setMaterial(value.getMaterial());
					buying.setQuantity(value.getBuyerQuantity());
					buying.setCost(value.getCost());
					buying.setMasterKey(value.getMasterKey());
					buying.setTransactionId(tMasterPlanForm.getTransactionId());

					tbuyingCostDAO.createMasterPlan(buying);
				}

				TDraftsMasterPlan drafts = tdraftsMasterPlanDAO
						.getByMasterPlanId(tMasterPlanForm.getId());


				tdraftsMasterPlanDAO.deleteRow(drafts.getId());

				TMasterPlan master = new TMasterPlan();

				master.setCustomer(tMasterPlanForm.getCustomer());
				master.setCurrencySymbol(tMasterPlanForm.getCurrencySymbol());
				master.setCategory(tMasterPlanForm.getCategory());
				master.setProduct(tMasterPlanForm.getProduct());
				master.setDescription(tMasterPlanForm.getDescription());
				master.setLicence(tMasterPlanForm.getLicence());
				master.setWeight(tMasterPlanForm.getWeight());
				master.setQuantity(tMasterPlanForm.getQuantity());
				master.setBuyingCost(tMasterPlanForm.getBuyingCost());
				master.setTenure(tMasterPlanForm.getTenure());
				master.setMasterKey(tMasterPlanForm.getMasterKey());
				master.setTransactionId(tMasterPlanForm.getTransactionId());
				master.setCustomerEmail(user.getEmail());
				master.setaStatus("pending");
				master.setAccept("None");
				master.setApprovalSent("No");
				master.setFlag(1);

				Date date = DateService.loginDate;
				master.setMasterPlanDate(date);

				tmasterPlanDAO.createMasterPlan(master);

				TTransaction trans = new TTransaction();

				trans.setTransactionId(tMasterPlanForm.getTransactionId());
				trans.setTransactionStatus("Master Plan Saved");
				trans.setTransactionType("Master Plan");

				transcationDAOImpl.insertTransaction(trans);
			}

			model.put("user", user);
			model.put("tMasterPlanForm", tMasterPlanForm);

			return new ModelAndView("tmasterPlanTransaction", "model", model);

		}

		@RequestMapping(value = "/tmasterPlanDetails", method = RequestMethod.GET)
		public ModelAndView masterPlanDetails(ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();

			List<TMasterPlan> masterList = tmasterPlanDAO.getMasterPlanFullList(
					user.getUserName()).getResultList();

			{
				model.put("masterList", masterList);
				model.put("user", user);
			}
			return new ModelAndView("tmasterPlanDetails", "model", model);

		}
		
		@RequestMapping(value = "/tmasterPlanPending", method = RequestMethod.GET)
		public ModelAndView masterPlanPending(ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();

			List<TMasterPlan> masterPendingList = tmasterPlanDAO.getByStatusAndCust(
					user.getUserName()).getResultList();

			if (masterPendingList != null && masterPendingList.size() > 0) {

				model.put("user", user);
				model.put("masterPendingList", masterPendingList);
			}
			return new ModelAndView("tmasterPlanPending", "model", model);

		}

		@RequestMapping(value = "/tcollateral", method = RequestMethod.GET)
		public ModelAndView collateral(@RequestParam("id") Long id, ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();

			TMasterPlan plan = tmasterPlanDAO.getByMasterPlanId(id);

			tcollateralForm.setMasterKey(plan.getMasterKey());
			tcollateralForm.setUserName(plan.getCustomer());
			tcollateralForm.setTransactionId(plan.getTransactionId());
			tcollateralForm.setId(plan.getId());

			model.put("user", user);
			model.put("tcollateralForm", tcollateralForm);
			return new ModelAndView("tcollateral", "model", model);

		}

		@RequestMapping(value = "/tcollateralConfirm", method = RequestMethod.POST)
		public ModelAndView collateralConfirm(ModelMap model,
				@ModelAttribute TCollateralForm tcollateralForm,
				BindingResult result, RedirectAttributes attributes) {

			EndUser user = getCurrentLoggedUserDetails();

			TCollateral collateral = new TCollateral();

			tcollateralForm.setId(tcollateralForm.getId());
			tcollateralForm.setUserName(tcollateralForm.getUserName());
			tcollateralForm.setMasterKey(tcollateralForm.getMasterKey());
			tcollateralForm.setTransactionId(tcollateralForm.getTransactionId());
			tcollateralForm.setProperty(tcollateralForm.getProperty());
			tcollateralForm.setPropertyAddress(tcollateralForm.getPropertyAddress());
			tcollateralForm.setPropertyArea(tcollateralForm.getPropertyArea());
			tcollateralForm.setPropertyType(tcollateralForm.getPropertyType());
			tcollateralForm.setpCostPerArea(tcollateralForm.getpCostPerArea());
			tcollateralForm.setPropertyPurchaseValue(tcollateralForm
					.getPropertyPurchaseValue());
			tcollateralForm.setPropertyCurrValue(tcollateralForm
					.getPropertyCurrValue());
			tcollateralForm.setpExisCharge(tcollateralForm.getpExisCharge());
			tcollateralForm.setVehicle(tcollateralForm.getVehicle());
			tcollateralForm.setvType(tcollateralForm.getvType());
			tcollateralForm.setvPurchaseValue(tcollateralForm.getvPurchaseValue());
			tcollateralForm.setVcurrValue(tcollateralForm.getVcurrValue());
			tcollateralForm.setDetails(tcollateralForm.getDetails());
			tcollateralForm.setvInsurence(tcollateralForm.getvInsurence());
			tcollateralForm.setvInsuranceStart(tcollateralForm.getvInsuranceStart());
			tcollateralForm.setvInsuranceEnd(tcollateralForm.getvInsuranceEnd());
			tcollateralForm.setvExistingCharge(tcollateralForm.getvExistingCharge());
			tcollateralForm.setCash(tcollateralForm.getCash());
			tcollateralForm.setAmount(tcollateralForm.getAmount());
			tcollateralForm.setFinInstitution(tcollateralForm.getFinInstitution());
			tcollateralForm.setBranch(tcollateralForm.getBranch());
			tcollateralForm.setLocation(tcollateralForm.getLocation());
			tcollateralForm.setfDStartDate(tcollateralForm.getfDStartDate());
			tcollateralForm.setfDEndDate(tcollateralForm.getfDEndDate());
			tcollateralForm.setfDInterestRate(tcollateralForm.getfDInterestRate());
			tcollateralForm.setcExistimgCharge(tcollateralForm.getcExistimgCharge());
			tcollateralForm.setInsurancePolicy(tcollateralForm.getInsurancePolicy());
			tcollateralForm.setBenficiaryName(tcollateralForm.getBenficiaryName());
			tcollateralForm.setNominee(tcollateralForm.getNominee());
			tcollateralForm.setPolicyStartDate(tcollateralForm.getPolicyStartDate());
			tcollateralForm.setPolicyEndDate(tcollateralForm.getPolicyEndDate());
			tcollateralForm
					.setPolicyExisCharge(tcollateralForm.getPolicyExisCharge());
			tcollateralForm.setShares(tcollateralForm.getShares());
			tcollateralForm.setCompanyShares(tcollateralForm.getCompanyShares());
			tcollateralForm.setSharesExisPrice(tcollateralForm.getSharesExisPrice());
			tcollateralForm.setDetails(tcollateralForm.getDetails());
			tcollateralForm.setTurnOver(tcollateralForm.getTurnOver());
			tcollateralForm.setProfit(tcollateralForm.getProfit());
			tcollateralForm.setLineOfActivity(tcollateralForm.getLineOfActivity());
			tcollateralForm
					.setSharesExisCharge(tcollateralForm.getSharesExisCharge());
			tcollateralForm.setMachinery(tcollateralForm.getMachinery());
			tcollateralForm.setMachineryLocation(tcollateralForm
					.getMachineryLocation());
			tcollateralForm.setMachineryPurchaseValue(tcollateralForm
					.getMachineryPurchaseValue());
			tcollateralForm.setMachineryMarketValue(tcollateralForm
					.getMachineryMarketValue());
			tcollateralForm
					.setMachineryDetails(tcollateralForm.getMachineryDetails());
			tcollateralForm.setRegistrationNum(tcollateralForm.getRegistrationNum());
			tcollateralForm.setMachineryExisCharge(tcollateralForm
					.getMachineryExisCharge());

			model.put("user", user);
			model.put("tcollateralForm", tcollateralForm);

			return new ModelAndView("tcollateralConfirm", "model", model);

		}

		@RequestMapping(value = "/tcollateralPost", method = RequestMethod.POST)
		public ModelAndView collateralPost(ModelMap model,
				@ModelAttribute TCollateralForm tcollateralForm,
				BindingResult result, RedirectAttributes attributes) {

			EndUser user = getCurrentLoggedUserDetails();

			TCollateral collateral = new TCollateral();

			collateral.setUserName(tcollateralForm.getUserName());
			collateral.setMasterKey(tcollateralForm.getMasterKey());
			collateral.setTransactionId(tcollateralForm.getTransactionId());
			collateral.setProperty(tcollateralForm.getProperty());
			collateral.setPropertyAddress(tcollateralForm.getPropertyAddress());
			collateral.setPropertyArea(tcollateralForm.getPropertyArea());
			collateral.setPropertyType(tcollateralForm.getPropertyType());
			collateral.setpCostPerArea(tcollateralForm.getpCostPerArea());
			collateral.setPropertyPurchaseValue(tcollateralForm
					.getPropertyPurchaseValue());
			collateral.setPropertyCurrValue(tcollateralForm.getPropertyCurrValue());
			collateral.setpExisCharge(tcollateralForm.getpExisCharge());
			collateral.setVehicle(tcollateralForm.getVehicle());
			collateral.setvType(tcollateralForm.getvType());
			collateral.setvPurchaseValue(tcollateralForm.getvPurchaseValue());
			collateral.setVcurrValue(tcollateralForm.getVcurrValue());
			collateral.setDetails(tcollateralForm.getDetails());
			collateral.setvInsurence(tcollateralForm.getvInsurence());
			collateral.setvInsuranceStart(tcollateralForm.getvInsuranceStart());
			collateral.setvInsuranceEnd(tcollateralForm.getvInsuranceEnd());
			collateral.setvExistingCharge(tcollateralForm.getvExistingCharge());
			collateral.setCash(tcollateralForm.getCash());
			collateral.setAmount(tcollateralForm.getAmount());
			collateral.setFinInstitution(tcollateralForm.getFinInstitution());
			collateral.setBranch(tcollateralForm.getBranch());
			collateral.setLocation(tcollateralForm.getLocation());
			collateral.setfDStartDate(tcollateralForm.getfDStartDate());
			collateral.setfDEndDate(tcollateralForm.getfDEndDate());
			collateral.setfDInterestRate(tcollateralForm.getfDInterestRate());
			collateral.setcExistimgCharge(tcollateralForm.getcExistimgCharge());
			collateral.setInsurancePolicy(tcollateralForm.getInsurancePolicy());
			collateral.setBenficiaryName(tcollateralForm.getBenficiaryName());
			collateral.setNominee(tcollateralForm.getNominee());
			collateral.setPolicyStartDate(tcollateralForm.getPolicyStartDate());
			collateral.setPolicyEndDate(tcollateralForm.getPolicyEndDate());
			collateral.setPolicyExisCharge(tcollateralForm.getPolicyExisCharge());
			collateral.setShares(tcollateralForm.getShares());
			collateral.setCompanyShares(tcollateralForm.getCompanyShares());
			collateral.setSharesExisPrice(tcollateralForm.getSharesExisPrice());
			collateral.setDetails(tcollateralForm.getDetails());
			collateral.setTurnOver(tcollateralForm.getTurnOver());
			collateral.setProfit(tcollateralForm.getProfit());
			collateral.setLineOfActivity(tcollateralForm.getLineOfActivity());
			collateral.setSharesExisCharge(tcollateralForm.getSharesExisCharge());
			collateral.setMachinery(tcollateralForm.getMachinery());
			collateral.setMachineryLocation(tcollateralForm.getMachineryLocation());
			collateral.setMachineryPurchaseValue(tcollateralForm
					.getMachineryPurchaseValue());
			collateral.setMachineryMarketValue(tcollateralForm
					.getMachineryMarketValue());
			collateral.setMachineryDetails(tcollateralForm.getMachineryDetails());
			collateral.setRegistrationNum(tcollateralForm.getRegistrationNum());
			collateral.setMachineryExisCharge(tcollateralForm
					.getMachineryExisCharge());

			tcollateralDAO.createUser(collateral);

			TTransaction trans = new TTransaction();
			trans.setTransactionId(tcollateralForm.getTransactionId());
			trans.setTransactionStatus("Collateral");
			trans.setTransactionType("Submitted Successfully");

			transcationDAOImpl.insertTransaction(trans);

			model.put("user", user);
			model.put("tcollateralForm", tcollateralForm);

			return new ModelAndView("tcollateralTransaction", "model", model);

		}

		@RequestMapping(value = "/tcollateralTransaction", method = RequestMethod.GET)
		public ModelAndView collateralTransaction(ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();

			model.put("user", user);
			model.put("tcollateralForm", tcollateralForm);

			return new ModelAndView("tcollateralTransaction", "model", model);

		}

	   @RequestMapping(value = "/tmasterPlanFullDetails", method = RequestMethod.GET)
	    public ModelAndView masterPlanFullDetails(@RequestParam("id") Long id,
	                  ModelMap model) {

	           EndUser user = getCurrentLoggedUserDetails();

	           TMasterPlan masterList = tmasterPlanDAO.getByMasterPlanId(id);

	           if (masterList != null && masterList.getMasterKey() != null) {
	                  List<TCollateral> collateralList = tcollateralDAO
	                               .getCollateralBYMAsterKey(masterList.getMasterKey())
	                               .getResultList();
	                  if (collateralList != null && collateralList.size() > 0) {
	                        model.put("collateralList", collateralList);
	                  }

	                  List<TBuyingCost> buyingList = tbuyingCostDAO
	                               .getBuyingCostBYMAsterKey(masterList.getMasterKey())
	                               .getResultList();

	                  if (buyingList != null && buyingList.size() > 0)

	                  {
	                        model.put("buyingList", buyingList);
	                  }

	                
	                  model.put("masterList", masterList);
	           }
	           model.put("user", user);

	           return new ModelAndView("tmasterPlanFullDetails", "model", model);

	    }
	   
	   @RequestMapping(value = "/tmasterPlanAccept", method = RequestMethod.GET)
	    public ModelAndView masterPlanAccept(ModelMap model) {

	           EndUser user = getCurrentLoggedUserDetails();

	           List<TMasterPlan> masterList = tmasterPlanDAO.getMasterPlanForAccept(
	                        user.getUserName()).getResultList();

	           if (masterList != null && masterList.size() > 0) {
	                  model.put("masterList", masterList);
	                  model.put("user", user);
	           }
	           return new ModelAndView("tmasterPlanAccept", "model", model);

	    }

	    @RequestMapping(value = "/tmasterPlanAcceptOrReject", method = RequestMethod.GET)
	    public ModelAndView masterPlanCreditAssign(@RequestParam("id") Long id,
	                  ModelMap model, RedirectAttributes attributes) {

	           EndUser user = getCurrentLoggedUserDetails();

	           TMasterPlan master = tmasterPlanDAO.getByMasterPlanId(id);

	           tMasterPlanForm.setId(master.getId());
	           tMasterPlanForm.setMasterKey(master.getMasterKey());
	           tMasterPlanForm.setCustomer(master.getCustomer());
	           tMasterPlanForm.setTransactionId(master.getTransactionId());
	           tMasterPlanForm.setBuyingCostSanc(master.getBuyingCostSanc());

	           model.put("user", user);
	           model.put("tMasterPlanForm", tMasterPlanForm);
	           return new ModelAndView("tmasterPlanAcceptOrReject", "model", model);

	    }

	    @RequestMapping(value = "/tmasterPlanAcceptOrRejectConfirm", method = RequestMethod.POST)
	    public ModelAndView masterPlanCreditAssignConfirm(ModelMap model,
	                  @ModelAttribute TMasterPlanForm tMasterPlanForm,
	                  RedirectAttributes attributes) {

	           EndUser user = getCurrentLoggedUserDetails();

	           tMasterPlanForm.setId(tMasterPlanForm.getId());
	           tMasterPlanForm.setMasterKey(tMasterPlanForm.getMasterKey());
	           tMasterPlanForm.setCustomer(tMasterPlanForm.getCustomer());
	           tMasterPlanForm.setTransactionId(tMasterPlanForm.getTransactionId());
	           tMasterPlanForm.setAccept(tMasterPlanForm.getAccept());
	           tMasterPlanForm.setBuyingCostSanc(tMasterPlanForm.getBuyingCostSanc());

	           model.put("user", user);
	           model.put("tMasterPlanForm", tMasterPlanForm);

	           return new ModelAndView("tmasterPlanAcceptOrRejectConfirm", "model",
	                        model);

	    }

	    @RequestMapping(value = "/tmasterPlanAcceptOrRejectPost", method = RequestMethod.POST)
	    public ModelAndView masterPlanCreditAssignPost(ModelMap model,
	                  @ModelAttribute TMasterPlanForm tMasterPlanForm,
	                  RedirectAttributes attributes) {

	           EndUser user = getCurrentLoggedUserDetails();
	           tMasterPlanForm.setTransactionId(tMasterPlanForm.getTransactionId());

	           TMasterPlan master = tmasterPlanDAO.getByMasterPlanId(tMasterPlanForm
	                        .getId());

	           master.setAccept(tMasterPlanForm.getAccept());
	           master.setBalance(tMasterPlanForm.getBuyingCostSanc());
	           master.setDistributedAmt(0.0f);
	           master.setUtilizedBusnsAmt(0.0f);
	           master.setAmountPaid(0.0f);
	           tmasterPlanDAO.updatePlan(master);

	           TTransaction trans = new TTransaction();
	           trans.setTransactionId(tMasterPlanForm.getTransactionId());
	           trans.setTransactionStatus("Acceptance");
	           trans.setTransactionType("Submitted Successfully");

	           transcationDAOImpl.insertTransaction(trans);

	           model.put("user", user);
	           model.put("tMasterPlanForm", tMasterPlanForm);

	           return new ModelAndView("tmasterPlanAcceptOrRejectTransaction", "model",
	                        model);

	    }

	    @RequestMapping(value = "/tmasterPlanAcceptOrRejectTransaction", method = RequestMethod.GET)
	    public ModelAndView creditAssignmentTransaction(ModelMap model) {

	           EndUser user = getCurrentLoggedUserDetails();

	           model.put("user", user);
	           model.put("tMasterPlanForm", tMasterPlanForm);

	           return new ModelAndView("tmasterPlanAcceptOrRejectTransaction", "model",
	                        model);

	    }
	    
	  /*  Repayment*/
	    
	    @RequestMapping(value = "/tmasterPlanRePayment", method = RequestMethod.GET)
		public ModelAndView masterPlanRePayment(ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();

			List<TMasterPlan> masterList = tmasterPlanDAO.getMasterPlanForFunds(
					user.getUserName()).getResultList();

			if (masterList != null && masterList.size() > 0) {
				model.put("masterList", masterList);
				model.put("user", user);
			}
			return new ModelAndView("tmasterPlanRePayment", "model", model);

		}

		@RequestMapping(value = "/tmasterPlanRePaymentDisplay", method = RequestMethod.GET)
		public ModelAndView masterPlanRePaymentDisplay(@RequestParam Long id,
				ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();

			TMasterPlan master = tmasterPlanDAO.getByMasterPlanId(id);

			trepaymentForm.setMasterKey(master.getMasterKey());
			trepaymentForm.setBuyingCostSanc(master.getBuyingCostSanc());
			trepaymentForm.setWcSancAmount(master.getWcSancAmount());
			trepaymentForm.setCustomer(master.getCustomer());
			trepaymentForm.setCustomerEmail(master.getCustomerEmail());
			trepaymentForm.setBuyingCostDate(master.getBuyingCostDate());
			trepaymentForm.setTenure(master.getTenure());
			trepaymentForm.setRateOfInt1(master.getRateOfInt1());
			trepaymentForm.setAmountPaid(master.getAmountPaid());
			trepaymentForm.setFunalAmt(master.getFunalAmt());
			model.put("trepaymentForm", trepaymentForm);
			model.put("user", user);

			return new ModelAndView("tmasterPlanRePaymentDisplay", "model", model);

		}

		@RequestMapping(value = "/tmasterPlanRePaymentDisplayConfirm", method = RequestMethod.POST)
		public ModelAndView masterPlanRePaymentDisplayConfirm(ModelMap model,
				@ModelAttribute TRepaymentForm trepaymentForm, BindingResult result) {

			EndUser user = getCurrentLoggedUserDetails();

			trepaymentForm.setMasterKey(trepaymentForm.getMasterKey());
			trepaymentForm.setBuyingCostSanc(trepaymentForm.getBuyingCostSanc());
			trepaymentForm.setWcSancAmount(trepaymentForm.getWcSancAmount());
			trepaymentForm.setCustomer(trepaymentForm.getCustomer());
			trepaymentForm.setCustomerEmail(trepaymentForm.getCustomerEmail());
			trepaymentForm.setBuyingCostDate(trepaymentForm.getBuyingCostDate());
			trepaymentForm.setTenure(trepaymentForm.getTenure());
			trepaymentForm.setRateOfInt1(trepaymentForm.getRateOfInt1());
			trepaymentForm.setPayOption(trepaymentForm.getPayOption());
			trepaymentForm.setAmtType(trepaymentForm.getAmtType());
			trepaymentForm.setAmountPaid(trepaymentForm.getAmountPaid());

			model.put("trepaymentForm", trepaymentForm);
			model.put("user", user);

			return new ModelAndView("tmasterPlanRePaymentDisplayConfirm", "model",
					model);

		}

		@RequestMapping(value = "/tmasterPlanRePaymentSave", method = RequestMethod.POST)
		public ModelAndView masterPlanRePaymentSave(ModelMap model,
				@ModelAttribute TRepaymentForm trepaymentForm, BindingResult result) {

			EndUser user = getCurrentLoggedUserDetails();

			TRepayment repay = new TRepayment();

			repay.setMasterKey(trepaymentForm.getMasterKey());
			repay.setBuyingCostSanc(trepaymentForm.getBuyingCostSanc());
			repay.setWcSancAmount(trepaymentForm.getWcSancAmount());
			repay.setCustomer(trepaymentForm.getCustomer());
			repay.setCustomerEmail(trepaymentForm.getCustomerEmail());
			repay.setBuyingCostDate(trepaymentForm.getBuyingCostDate());
			repay.setTenure(trepaymentForm.getTenure());
			repay.setRateOfInt1(trepaymentForm.getRateOfInt1());
			repay.setPayOption(trepaymentForm.getPayOption());
			repay.setAmtType(trepaymentForm.getAmtType());
			repay.setTransactionId(trepaymentForm.getTransactionId());
			repay.setAmountPaid(trepaymentForm.getAmountPaid());
			repay.setcStatus("Pending");
			repay.setMoneyStatus("Pending");

			trepaymenyDAO.createRepay(repay);

			TTransaction trans = new TTransaction();

			trans.setTransactionId(trepaymentForm.getTransactionId());
			trans.setTransactionStatus("Repayment Request");
			trans.setTransactionType("Sent Successfully");

			transcationDAOImpl.insertTransaction(trans);

			model.put("trepaymentForm", trepaymentForm);
			model.put("user", user);

			return new ModelAndView("tmasterPlanRePaymentTransaction", "model",
					model);

		}

		@RequestMapping(value = "/tmasterPlanRePaymentTransaction", method = RequestMethod.GET)
		public ModelAndView clientAdminTransaction(ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();

			model.put("user", user);
			model.put("trepaymentForm", trepaymentForm);

			return new ModelAndView("tmasterPlanRePaymentTransaction", "model",
					model);

		}

		@RequestMapping(value = "/tmasterPlanRePaymentList", method = RequestMethod.GET)
		public ModelAndView masterPlanRePaymentList(ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();

			List<TRepayment> repayList = trepaymenyDAO.getMasterPlanForRepayment(
					user.getUserName()).getResultList();

			if (repayList != null && repayList.size() > 0) {
				model.put("repayList", repayList);
				model.put("user", user);
			}
			return new ModelAndView("tmasterPlanRePaymentList", "model", model);

		}

		@RequestMapping(value = "/tmasterPlanRePaymentAcceptList", method = RequestMethod.GET)
		public ModelAndView masterPlanRePaymentAcceptList(ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();

			List<TRepayment> repayList = trepaymenyDAO
					.getMasterPlanForRepaymentAndAppStatus(user.getUserName())
					.getResultList();

			if (repayList != null && repayList.size() > 0) {
				model.put("repayList", repayList);
				model.put("user", user);
			}
			return new ModelAndView("tmasterPlanRePaymentAcceptList", "model", model);

		}

		@RequestMapping(value = "/tmasterPlanRePaymentAccept", method = RequestMethod.GET)
		public ModelAndView masterPlanRePaymentAppMngLAppr(@RequestParam Long id,
				ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();

			TRepayment master = trepaymenyDAO.getByRepaymentId(id);

			trepaymentForm.setId(master.getId());
			trepaymentForm.setMasterKey(master.getMasterKey());
			trepaymentForm.setCustomer(master.getCustomer());
			trepaymentForm.setCustomerEmail(master.getCustomerEmail());
			trepaymentForm.setPayOption(master.getPayOption());
			trepaymentForm.setAmtType(master.getAmtType());
			trepaymentForm.setTransactionId(master.getTransactionId());

			model.put("trepaymentForm", trepaymentForm);
			model.put("user", user);

			return new ModelAndView("tmasterPlanRePaymentAccept", "model", model);

		}

		@RequestMapping(value = "/tmasterPlanRePaymentAcceptConfirm", method = RequestMethod.POST)
		public ModelAndView masterPlanRePaymentAcceptConfirm(ModelMap model,
				@ModelAttribute TRepaymentForm trepaymentForm, BindingResult result) {

			EndUser user = getCurrentLoggedUserDetails();

			trepaymentForm.setId(trepaymentForm.getId());
			trepaymentForm.setMasterKey(trepaymentForm.getMasterKey());
			trepaymentForm.setCustomer(trepaymentForm.getCustomer());
			trepaymentForm.setCustomerEmail(trepaymentForm.getCustomerEmail());
			trepaymentForm.setTenure(trepaymentForm.getTenure());
			trepaymentForm.setPayOption(trepaymentForm.getPayOption());
			trepaymentForm.setAmtType(trepaymentForm.getAmtType());
			trepaymentForm.setTransactionId(trepaymentForm.getTransactionId());
			trepaymentForm.setAccept(trepaymentForm.getAccept());

			model.put("trepaymentForm", trepaymentForm);
			model.put("user", user);

			return new ModelAndView("tmasterPlanRePaymentAcceptConfirm", "model",
					model);

		}

		@RequestMapping(value = "/tmasterPlanRePaymentAcceptSave", method = RequestMethod.POST)
		public ModelAndView masterPlanRePaymentAcceptSave(ModelMap model,
				@ModelAttribute TRepaymentForm trepaymentForm, BindingResult result) {

			EndUser user = getCurrentLoggedUserDetails();
			TRepayment repay = trepaymenyDAO.getByRepaymentId(trepaymentForm.getId());

			repay.setAccept(trepaymentForm.getAccept());
			repay.setPayConfirm("Pending");
			trepaymenyDAO.updateRepayment(repay);

			TTransaction trans = new TTransaction();

			trans.setTransactionId(trepaymentForm.getTransactionId());
			trans.setTransactionStatus("Repayment Accept/Reject");
			trans.setTransactionType("Sent Successfully");
			transcationDAOImpl.insertTransaction(trans);

			model.put("trepaymentForm", trepaymentForm);
			model.put("user", user);

			return new ModelAndView("tmasterPlanRePaymentAcceptTrans", "model",
					model);

		}

		@RequestMapping(value = "/tmasterPlanRePaymentAcceptTrans", method = RequestMethod.GET)
		public ModelAndView masterPlanRePaymentTransaction(ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();

			model.put("user", user);
			model.put("trepaymentForm", trepaymentForm);

			return new ModelAndView("tmasterPlanRePaymentAcceptTrans", "model",
					model);

		}

		@RequestMapping(value = "/tmasterPlanRePaymentCustQuarterly", method = RequestMethod.GET)
		public ModelAndView masterPlanRePaymentAppQuarterly(@RequestParam Long id,
				ModelMap model, RedirectAttributes attributes) {

			EndUser user = getCurrentLoggedUserDetails();

			TRepayment master = trepaymenyDAO.getByRepaymentId(id);
			if (master != null) {
				List<TQuarterly> full = tquarterlyDAO.getByTransIdList(
						master.getTransactionId()).getResultList();

				if (full != null && full.size() > 0) {
					model.put("full", full);
					model.put("user", user);
				} else {
					attributes.addFlashAttribute("success",
							"Check The Type of Payment Type");

					return new ModelAndView(
							"redirect:tmasterPlanRePaymentAcceptList");

				}
			}
			return new ModelAndView("tmasterPlanRePaymentCustQuarterly", "model",
					model);
		}

		@RequestMapping(value = "/tmasterPlanRePaymentCustFullList", method = RequestMethod.GET)
		public ModelAndView masterPlanRePaymentAppFullList(@RequestParam Long id,
				ModelMap model, RedirectAttributes attributes) {

			EndUser user = getCurrentLoggedUserDetails();

			TRepayment master = trepaymenyDAO.getByRepaymentId(id);
			if (master != null) {
				List<TFullAmount> full = tfullAmountDAO.getByTransIdList(
						master.getTransactionId()).getResultList();
				if (full != null && full.size() > 0) {
					model.put("full", full);
					model.put("user", user);
				} else {
					attributes.addFlashAttribute("success",
							"Check The Type of Payment Type");

					return new ModelAndView(
							"redirect:tmasterPlanRePaymentAcceptList");

				}
			}
			return new ModelAndView("tmasterPlanRePaymentCustFullList", "model",
					model);
		}

		@RequestMapping(value = "/tmasterPlanRePaymentCustHalfYearly", method = RequestMethod.GET)
		public ModelAndView masterPlanRePaymentAppHalfYearly(@RequestParam Long id,
				ModelMap model, RedirectAttributes attributes) {

			EndUser user = getCurrentLoggedUserDetails();

			TRepayment master = trepaymenyDAO.getByRepaymentId(id);
			if (master != null) {
				List<THalfYearly> full = thalfYearlyDAO.getByTransIdList(
						master.getTransactionId()).getResultList();

				if (full != null && full.size() > 0) {
					model.put("full", full);
					model.put("user", user);
				} else {
					attributes.addFlashAttribute("success",
							"Check The Type of Payment Type");

					return new ModelAndView(
							"redirect:tmasterPlanRePaymentAcceptList");

				}
			}
			return new ModelAndView("tmasterPlanRePaymentCustHalfYearly", "model",
					model);
		}
		
		@RequestMapping(value = "/tCustHelp", method = RequestMethod.GET)
		public ModelAndView adminHelp(ModelMap model,
				HttpServletRequest request, HttpServletResponse response) {

			EndUser user = getCurrentLoggedUserDetails();

			model.put("user", user);

			return new ModelAndView("tCustHelp", "model", model);

		}

		public ServletContext getServletContext() {
			return servletContext;
		}

		public void setServletContext(ServletContext servletContext) {
			this.servletContext = servletContext;
		}
}
