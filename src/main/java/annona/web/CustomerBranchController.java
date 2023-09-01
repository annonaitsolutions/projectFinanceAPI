package annona.web;

import annona.dao.*;
import annona.domain.*;
import annona.form.*;
import annona.services.DateService;
import annona.services.EventsService;
import annona.services.ImageService;
import annona.services.UploadService;
import annona.utility.Constants;
import annona.utility.KeyGenerator;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
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
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.theme.CookieThemeResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/userBranch")
public class CustomerBranchController implements ServletContextAware {

	@Autowired
	EndUserDAO endUserDAOImpl;

	@Autowired
	LetterOfCreditForm letterOfCreditForm;

	@Autowired
	CustomerHeadForm customerHeadForm;
	@Autowired
	CustomerBranchForm customerBranchForm;

	@Autowired
	InoviceUploadForm inoviceUploadForm;

	@Autowired
	InvoiceUploadDAO invoiceUploadDAO;

	@Autowired
	PoUploadDAO poUploadDAO;

	@Autowired
	PoUploadForm poUploadForm;

	@Autowired
	LetterOfCreditDAO letterOfCreditDAO;

	@Autowired
	WareHouseForm wareHouseForm;

	@Autowired
	WareHouseDAO wareHouseDAO;

	@Autowired
	CorrespondentDAO correspondentDAO;

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	EndUserForm endUserForm;

	@Autowired
	InvoiceInventoryDAO invoiceInventoryDAO;

	@Autowired
	UploadedFileForm uploadedFileForm;

	@Autowired
	RequestMoneyForm requestMoneyForm;

	@Autowired
	RequestMoneyDAO requestMoneyDAO;

	@Autowired
	UploadDAO uploadDaoImpl;

	@Autowired
	SupplierDAO supplierDAO;

	@Autowired
	FundsStatementDAO fundsStatementDAO;

	@Autowired
	NewBuyerDAO newBuyerDAO;

	@Autowired
	MasterPlanDAO masterPlanDAO;

	@Autowired
	FundsDistributeDAO fundsDistributeDAO;

	@Autowired
	MasterPlanForm masterPlanForm;

	@Autowired
	SupplierForm supplierForm;

	@Autowired
	PurchaseOrderForm purchaseOrderForm;

	@Autowired
	InvoiceForm invoiceForm;

	@Autowired
	NewBuyerForm newBuyerForm;

	@Autowired
	PurchaseOrderDAO purchaseOrderDAO;

	@Autowired
	InvoiceDAO invoiceDAO;

	@Autowired
	FundsDistributeForm fundsDistributeForm;

	@Autowired
	TransactionDAO transcationDAOImpl;

	@Autowired
	InventoryForm inventoryForm;

	@Autowired
	InventoryDAO invenrotyDAO;

	@Autowired
	BuyerPODAO buyerPODAO;

	@Autowired
	UploadService uploadService;

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
	BuyerSameBankEventDAO buyerSameBankeventDAO;

	@Autowired
	BuyerDiffBankEventDAO buyerDiffBankEventDAO;

	@Autowired
	SellerSameBankEventDAO sellerSameBankEventDAO;
	@Autowired
	SellerDiffBankEventDAO sellerDiffBankEventDAO;
	@Autowired
	DisputeDAO disputeDAO;

	@Autowired
	DisputeForm disputeForm;

	@Autowired
	VendorUploadDAO vendorUploadDAO;

	@Autowired
	UploadDAO uploadDAO;

	@Autowired
	PurchaseDocDAO purchaseDocDAO;

	@Autowired
	PoStockDAO poStockDAO;

	@Autowired
	PoStockForm poStockForm;

	@Autowired
	InvoiceDocDAO invoiceDocDAO;

	@Autowired
	InvoiceStockDAO invoiceStockDAO;

	@Autowired
	InvoiceStockForm invoiceStockForm;

	@Autowired
	EventsService eventsService;

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
	WareHouseMngDAO wareHouseMngDAO;
	
	@Autowired
	WareHouseMngForm wareHouseMngForm;

	@Autowired
	CustomerBankDetailsDAO customerBankDetailsDAO;

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

		return "redirect:userBranch";
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
		model.put("user", user);
		endUserDAOImpl.mergeUser(user);

		return "redirect:userBranch";
	}

	@RequestMapping(value = "/userBranch", method = RequestMethod.GET)
	public ModelAndView showUserDashBoard(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("userBranchPage", "model", model);

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

	@RequestMapping(value = "/editCustomerBranchProfile", method = RequestMethod.GET)
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

		return new ModelAndView("editCustomerBranchProfile", "model", model);

	}

	@RequestMapping(value = "/updateImageForProfile", method = RequestMethod.POST)
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
		return "redirect:editCustomerBranchProfile?id=" + endUserForm.getId();
	}

	@RequestMapping(value = "/confirmEditCustomerBranchProfile", method = RequestMethod.POST)
	public ModelAndView confirmEditAdminProfile(ModelMap model,
			@ModelAttribute EndUserForm endUserForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("confirmEditCustomerBranchProfile", "model",
				model);

	}

	@RequestMapping(value = "/updateCustomerBranchDetails", method = RequestMethod.POST)
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

		return new ModelAndView("updateCustomerBranchSuccess", "model", model);

	}

	@RequestMapping(value = "/editCustomerBranchPWD", method = RequestMethod.GET)
	public ModelAndView editAdminPWD(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser userProfile = endUserDAOImpl.findId(id);

		endUserForm.setId(userProfile.getId());

		endUserForm.setTransactionId(userProfile.getTransactionId());

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("editCustomerBranchPWD", "model", model);

	}

	@RequestMapping(value = "/updateEditCustomerBranchPWD", method = RequestMethod.POST)
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

		return new ModelAndView("updateCustomerBranchSuccess", "model", model);

	}

	// File Upload

	@RequestMapping(value = "/fileUploadForm", method = RequestMethod.GET)
	public ModelAndView getUploadForm(RedirectAttributes attribute,
			ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		uploadedFileForm.setCustomerHeadName(user.getCustomerHeadName());
		uploadedFileForm.setCustomerHeadKey(user.getCustomerHeadKey());

		model.put("user", user);
		model.put("uploadedFileForm", uploadedFileForm);

		return new ModelAndView("uploadFormCustomerBranch");
	}

	@RequestMapping("/fileUpload")
	public ModelAndView fileUploaded(ModelMap model,
			RedirectAttributes attribute,
			@ModelAttribute UploadedFileForm uploadedFileForm,
			BindingResult result) throws RuntimeException, IOException {

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
		upload.setUploadComment(uploadedFileForm.getUploadComment());
		upload.setReason(uploadedFileForm.getReason());
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
		return new ModelAndView("showFileCustomerBranch", "model", model);
	}

	@RequestMapping(value = "/selectcustomerBranchdocumentApprovalList", method = RequestMethod.GET)
	public ModelAndView selectdocumentApprovalList(ModelMap model) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		List<UploadedFile> uploadDocumentList = uploadDaoImpl.findByName(
				user.getUserName()).getResultList();

		if (uploadDocumentList != null && uploadDocumentList.size() > 0) {

			model.put("uploadedFileForm", uploadedFileForm);
			model.put("uploadDocumentList", uploadDocumentList);
			return new ModelAndView("customerBranchdocumentApprovalList",
					"model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	// list of Buyer
	@RequestMapping(value = "/customerBranchBuyerList", method = RequestMethod.GET)
	public ModelAndView customerSubsidiaryBuyerList(ModelMap model) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);

		List<NewBuyer> buyerList = newBuyerDAO.findByCustomerHeadName(
				user.getUserName()).getResultList();

		if (buyerList != null && buyerList.size() > 0) {

			model.put("buyerList", buyerList);
			return new ModelAndView("customerBranchBuyerList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	// list of Supplier
	@RequestMapping(value = "/customerBranchSupplierList", method = RequestMethod.GET)
	public ModelAndView customerSubsidiarySupplierList(ModelMap model) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);

		List<Supplier> supplierList = supplierDAO.findByCustomerHeadName(
				user.getUserName()).getResultList();

		if (supplierList != null && supplierList.size() > 0) {

			model.put("supplierList", supplierList);
			return new ModelAndView("customerBranchSupplierList", "model",
					model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/branchBusinessPlan", method = RequestMethod.GET)
	public ModelAndView businessPlan(ModelMap model) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		List<FundsDistribute> plan = fundsDistributeDAO
				.getFundsListByCustomerName(user.getUserName()).getResultList();

		if (plan != null) {
			model.put("plan", plan);

			return new ModelAndView("branchBusinessPlan", "model", model);
		}

		else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}
	}

	@RequestMapping(value = "/createBranchBusinessPlan", method = RequestMethod.GET)
	public ModelAndView createBusinessPlan(@RequestParam("id") Long id,
			ModelMap model) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		FundsDistribute funds = fundsDistributeDAO.getByFundsId(id);

		fundsDistributeForm.setId(funds.getId());
		fundsDistributeForm.setCustomerHeadName(funds.getCustomerHeadName());
		fundsDistributeForm.setCustomerName(funds.getCustomerName());
		fundsDistributeForm.setMasterKey(funds.getMasterKey());
		fundsDistributeForm.setUtilizedAmount(funds.getUtilizedAmount());
		fundsDistributeForm.setDistributedAmount(funds.getDistributedAmount());
		fundsDistributeForm.setBusBalance(funds.getBusBalance());
		fundsDistributeForm.setCustHeadEmail(funds.getCustHeadEmail());
		fundsDistributeForm.setEmail(funds.getEmail());

		if (user.getCustomerHeadName() != null) {
			List<Supplier> suppliers = supplierDAO
					.findByCustomerHeadNameAndStatus(user.getUserName())
					.getResultList();
			if (suppliers != null && suppliers.size() > 0) {
				model.put("suppliers", suppliers);
			}
			List<NewBuyer> buyers = newBuyerDAO
					.findByCustomerHeadNameAndStatus(user.getUserName())
					.getResultList();
			if (buyers != null && buyers.size() > 0) {
				model.put("buyers", buyers);
			}
		}
		model.put("fundsDistributeForm", fundsDistributeForm);
		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("createBranchBusinessPlan", "model", model);
	}

	@RequestMapping(value = "/branchBusinessPlanForPo", method = RequestMethod.GET)
	public ModelAndView branchBusinessPlanForPo(@RequestParam("id") Long id,
			ModelMap model) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		purchaseOrderForm.setCustomerHeadName(purchaseOrderForm
				.getCustomerHeadName());
		purchaseOrderForm.setCustomerName(purchaseOrderForm.getCustomerName());
		purchaseOrderForm.setMasterKey(purchaseOrderForm.getMasterKey());
		purchaseOrderForm.setUtilizedBusnsAmt(purchaseOrderForm
				.getUtilizedBusnsAmt());
		purchaseOrderForm.setDistributedAmount(purchaseOrderForm
				.getDistributedAmount());
		purchaseOrderForm.setBalance(purchaseOrderForm.getBalance());
		purchaseOrderForm.setCustomerHeadEmail(purchaseOrderForm
				.getCustomerHeadEmail());
		purchaseOrderForm.setCustomerBranchEmail(purchaseOrderForm
				.getCustomerBranchEmail());

		Supplier supplier = supplierDAO.findId(id);

		supplierForm.setId(supplier.getId());
		supplierForm.setSupplierName(supplier.getSupplierName());
		supplierForm.setEmail(supplier.getEmail());
		supplierForm.setBank(supplier.getBank());
		supplierForm.setBankEmail(supplier.getBankEmail());
		supplierForm.setCountry(supplier.getCountry());
		supplierForm.setState(supplier.getState());
		supplierForm.setCity(supplier.getCity());

		List<WareHouse> whList = wareHouseDAO.getWareHouseandStatusList(
				user.getUserName()).getResultList();
		if (whList != null) {
			purchaseOrderForm.setWareHouseList(whList);

			model.put("purchaseOrderForm", purchaseOrderForm);
			model.put("user", user);
		}

		model.put("supplierForm", supplierForm);
		model.put("fundsDistributeForm", fundsDistributeForm);
		model.put("purchaseOrderForm", purchaseOrderForm);
		model.put("user", user);

		return new ModelAndView("branchBusinessPlanForPo", "model", model);
	}

	@RequestMapping(value = "/branchBusinessPlanForPoConfirm", method = RequestMethod.POST)
	public ModelAndView branchBusinessPlanForPoConfirm(ModelMap model,
			@ModelAttribute PurchaseOrderForm purchaseOrderForm,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		if (purchaseOrderForm.getAmount().compareTo(
				purchaseOrderForm.getBalance()) > 0) {
			Long Id = purchaseOrderForm.getId();
			attributes.addFlashAttribute("success",
					"Amount Should Not Exceed Balance");
			return new ModelAndView("redirect:branchBusinessPlanForPo?id=" + Id
					+ "");
		}
		
		List<RestrictedLicense> license = restrictedLicenseDAO.getRestrictedGoodsList(user.getUserName(), purchaseOrderForm.getGoodsCategory(), purchaseOrderForm.getPurchaseDate()
				, purchaseOrderForm.getWeight()).getResultList();
		if (license!=null && license.size()>0) {
			Long Id = purchaseOrderForm.getId();
			attributes.addFlashAttribute("success",
					"The goods are Restricted. License is not Uploaded or Expired");
			return new ModelAndView("redirect:branchBusinessPlanForPo?id=" + Id + "");
		}

		purchaseOrderForm.setCustomerHeadEmail(purchaseOrderForm
				.getCustomerHeadEmail());
		purchaseOrderForm.setCustomerBranchEmail(purchaseOrderForm
				.getCustomerBranchEmail());
		purchaseOrderForm
				.setSupplierEmail(purchaseOrderForm.getSupplierEmail());
		purchaseOrderForm.setSupplierBank(purchaseOrderForm.getSupplierBank());
		purchaseOrderForm.setSupplierBankEmail(purchaseOrderForm
				.getSupplierBankEmail());
		purchaseOrderForm.setSuppliercountry(purchaseOrderForm
				.getSuppliercountry());
		purchaseOrderForm
				.setSupplierState(purchaseOrderForm.getSupplierState());
		purchaseOrderForm.setSupplierCity(purchaseOrderForm.getSupplierCity());
		purchaseOrderForm
				.setWareHouseName(purchaseOrderForm.getWareHouseName());
		purchaseOrderForm.setMasterKey(purchaseOrderForm.getMasterKey());
		purchaseOrderForm.setCustomerName(purchaseOrderForm.getCustomerName());
		purchaseOrderForm.setCustomerHeadName(purchaseOrderForm
				.getCustomerHeadName());
		purchaseOrderForm.setSupplierName(purchaseOrderForm.getSupplierName());
		purchaseOrderForm.setPurchaseDate(purchaseOrderForm.getPurchaseDate());
		purchaseOrderForm
				.setGoodsCategory(purchaseOrderForm.getGoodsCategory());
		purchaseOrderForm.setGoods(purchaseOrderForm.getGoods());
		purchaseOrderForm.setGoodsDetails(purchaseOrderForm.getGoodsDetails());
		purchaseOrderForm.setQuantity(purchaseOrderForm.getQuantity());
		purchaseOrderForm.setWeight(purchaseOrderForm.getWeight());
		purchaseOrderForm.setLicence(purchaseOrderForm.getLicence());
		purchaseOrderForm.setAmount(purchaseOrderForm.getAmount());
		purchaseOrderForm.setUtilizedBusnsAmt(purchaseOrderForm
				.getUtilizedBusnsAmt());
		purchaseOrderForm.setBalance(purchaseOrderForm.getBalance());
		purchaseOrderForm.setTenure(purchaseOrderForm.getTenure());

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("branchBusinessPlanForPoConfirm", "model",
				model);

	}

	@RequestMapping(value = "/branchBusinessPlanForPoPost", method = RequestMethod.POST)
	public ModelAndView branchBusinessPlanForPoPost(ModelMap model,
			@ModelAttribute PurchaseOrderForm purchaseOrderForm,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();


		purchaseOrderForm.setTransactionId(KeyGenerator.generateTransactionKey());
		purchaseOrderForm.setPoKey(KeyGenerator.generateTransactionKey());

		PurchaseOrder purchaseOrder = new PurchaseOrder();

		Float utilizedAmt = purchaseOrderForm.getUtilizedBusnsAmt();
		Float bal = purchaseOrderForm.getBalance();

		Float totalUtilized = utilizedAmt + purchaseOrderForm.getAmount();
		Float totalBal = bal - purchaseOrderForm.getAmount();

		purchaseOrder.setCustomerHeadEmail(purchaseOrderForm
				.getCustomerHeadEmail());
		purchaseOrder.setCustomerBranchEmail(purchaseOrderForm
				.getCustomerBranchEmail());
		purchaseOrder.setMasterKey(purchaseOrderForm.getMasterKey());
		purchaseOrder.setSupplierBank(purchaseOrderForm.getSupplierBank());
		purchaseOrder.setCustomerName(purchaseOrderForm.getCustomerName());
		purchaseOrder.setCustomerHeadName(purchaseOrderForm
				.getCustomerHeadName());
		purchaseOrder.setSupplierName(purchaseOrderForm.getSupplierName());
		purchaseOrder.setSupplierEmail(purchaseOrderForm.getSupplierEmail());
		purchaseOrder.setPurchaseDate(purchaseOrderForm.getPurchaseDate());
		purchaseOrder.setWareHouseName(purchaseOrderForm.getWareHouseName());
		WareHouse wareMng = wareHouseDAO.getWareHouseNameList(purchaseOrderForm.getWareHouseName()).getSingleResult();
		purchaseOrder.setWhMngName(wareMng.getPersonInCharge());
		purchaseOrder.setGoodsCategory(purchaseOrderForm.getGoodsCategory());
		purchaseOrder.setGoods(purchaseOrderForm.getGoods());
		purchaseOrder.setGoodsDetails(purchaseOrderForm.getGoodsDetails());
		purchaseOrder.setQuantity(purchaseOrderForm.getQuantity());
		purchaseOrder.setWeight(purchaseOrderForm.getWeight());
		purchaseOrder.setLicence(purchaseOrderForm.getLicence());
		purchaseOrder.setAmount(purchaseOrderForm.getAmount());
		purchaseOrder.setPayBalance(purchaseOrderForm.getAmount());
		purchaseOrder.setPayAdvance(0.0f);
		purchaseOrder.setTransactionId(purchaseOrderForm.getTransactionId());
		purchaseOrder.setPoKey(purchaseOrderForm.getPoKey());
		purchaseOrder.setTenure(purchaseOrderForm.getTenure());
		purchaseOrder.setvStatus("Pending");
		purchaseOrder.setFlag(1);

		purchaseOrderDAO.insertPo(purchaseOrder);

		FundsDistribute funds = fundsDistributeDAO.getFundsListByKeyAndName(
				purchaseOrderForm.getMasterKey(),
				purchaseOrderForm.getCustomerName()).getSingleResult();

		funds.setBusBalance(totalBal);
		funds.setUtilizedAmount(totalUtilized);

		fundsDistributeDAO.updateFunds(funds);

		Transaction trans = new Transaction();

		trans.setTransactionId(purchaseOrderForm.getTransactionId());
		trans.setTransactionType("Purchase Order");
		trans.setTransactionStatus("Submitted successfully");
		transcationDAOImpl.insertTransaction(trans);

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("branchBusinessPlanForPoTransaction", "model",
				model);

	}

	@RequestMapping(value = "/branchBusinessPlanForPoTransaction", method = RequestMethod.GET)
	public ModelAndView businessPlanForPoTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("branchBusinessPlanForPoTransaction", "model",
				model);

	}

	@RequestMapping(value = "/branchBusinessPlanForInvoice", method = RequestMethod.GET)
	public ModelAndView businessPlanForInvoice(@RequestParam("id") Long id,
			ModelMap model) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		invoiceForm.setCustomerHeadName(invoiceForm.getCustomerHeadName());
		invoiceForm.setCustomerName(invoiceForm.getCustomerName());
		invoiceForm.setMasterKey(invoiceForm.getMasterKey());
		invoiceForm.setCustomerHeadEmail(invoiceForm.getCustomerHeadEmail());
		invoiceForm
				.setCustomerBranchEmail(invoiceForm.getCustomerBranchEmail());
		masterPlanForm.setServiceTax(masterPlanForm.getServiceTax());
		masterPlanForm.setDutytax(masterPlanForm.getDutytax());
		masterPlanForm.setVattax(masterPlanForm.getVattax());
		NewBuyer buyer = newBuyerDAO.findId(id);

		newBuyerForm.setId(buyer.getId());
		newBuyerForm.setBuyerName(buyer.getBuyerName());
		newBuyerForm.setEmail(buyer.getEmail());
		newBuyerForm.setBank(buyer.getBank());
		newBuyerForm.setBankEmail(buyer.getBankEmail());
		newBuyerForm.setCountry(buyer.getCountry());
		newBuyerForm.setState(buyer.getState());
		List<WareHouse> whList = wareHouseDAO.getWareHouseandStatusList(
				user.getUserName()).getResultList();
		if (whList != null) {
			invoiceForm.setWareHouseList(whList);

			model.put("purchaseOrderForm", purchaseOrderForm);
			model.put("user", user);
		}

		model.put("fundsDistributeForm", fundsDistributeForm);
		model.put("newBuyerForm", newBuyerForm);
		model.put("invoiceForm", invoiceForm);
		model.put("user", user);

		return new ModelAndView("branchBusinessPlanForInvoice", "model", model);
	}

	@RequestMapping(value = "/branchBusinessPlanForInvoiceConfirm", method = RequestMethod.POST)
	public ModelAndView businessPlanForInvoiceConfirm(ModelMap model,
			@ModelAttribute InvoiceForm invoiceForm,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		invoiceForm.setCustomerHeadEmail(invoiceForm.getCustomerHeadEmail());
		invoiceForm
				.setCustomerBranchEmail(invoiceForm.getCustomerBranchEmail());
		invoiceForm.setBuyerEmail(invoiceForm.getBuyerEmail());
		invoiceForm.setBuyerBank(invoiceForm.getBuyerBank());
		invoiceForm.setBuyerBankEmail(invoiceForm.getBuyerBankEmail());
		invoiceForm.setBuyerCountry(invoiceForm.getBuyerCountry());
		invoiceForm.setBuyerState(invoiceForm.getBuyerState());
		invoiceForm.setBuyerCity(invoiceForm.getBuyerCity());
		invoiceForm.setMasterKey(invoiceForm.getMasterKey());
		invoiceForm.setCustomerName(invoiceForm.getCustomerName());
		invoiceForm.setCustomerHeadName(invoiceForm.getCustomerHeadName());
		invoiceForm.setBuyerName(invoiceForm.getBuyerName());
		invoiceForm.setWareHouseName(invoiceForm.getWareHouseName());
		invoiceForm.setPoDate(invoiceForm.getPoDate());
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

		return new ModelAndView("branchBusinessPlanForInvoiceConfirm", "model",
				model);

	}

	@RequestMapping(value = "/branchBusinessPlanForInvoicePost", method = RequestMethod.POST)
	public ModelAndView branchBusinessPlanForInvoicePost(ModelMap model,
			@ModelAttribute InvoiceForm invoiceForm,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		invoiceForm.setTransactionId(KeyGenerator.generateTransactionKey());

		Invoice invoice = new Invoice();

		invoice.setCustomerHeadEmail(invoiceForm.getCustomerHeadEmail());
		invoice.setCustomerBranchEmail(invoiceForm.getCustomerBranchEmail());
		invoice.setMasterKey(invoiceForm.getMasterKey());
		invoice.setCustomerName(invoiceForm.getCustomerName());
		invoice.setBuyerBank(invoiceForm.getBuyerBank());
		invoice.setCustomerHeadName(invoiceForm.getCustomerHeadName());
		invoice.setBuyerName(invoiceForm.getBuyerName());
		invoice.setBuyerEmail(invoiceForm.getBuyerEmail());
		invoice.setPoDate(invoiceForm.getPoDate());
		invoice.setGoodsCategory(invoiceForm.getGoodsCategory());
		invoice.setWareHouseName(invoiceForm.getWareHouseName());
		WareHouse wareMng = wareHouseDAO.getWareHouseNameList(invoiceForm.getWareHouseName()).getSingleResult();
		invoice.setWhMngName(wareMng.getPersonInCharge());
		invoice.setGoods(invoiceForm.getGoods());
		invoice.setGoodsDetails(invoiceForm.getGoodsDetails());
		invoice.setQuantity(invoiceForm.getQuantity());
		invoice.setWeight(invoiceForm.getWeight());
		invoice.setLicence(invoiceForm.getLicence());
		invoice.setAmount(invoiceForm.getAmount());
		invoice.setTransactionId(invoiceForm.getTransactionId());
		invoice.setTenure(invoiceForm.getTenure());
		invoice.setPoKey(invoiceForm.getPoKey());
		invoice.setStatus("Pending");
		invoice.setGoodsStatus("Send");
		invoice.setPoInfo(invoiceForm.getPoInfo());
		invoice.setPoDate(invoiceForm.getPoDate());
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

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("branchBusinessPlanForInvoiceTransaction",
				"model", model);

	}

	@RequestMapping(value = "/branchBusinessPlanForInvoiceTransaction", method = RequestMethod.GET)
	public ModelAndView businessPlanForInvoiceTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("branchBusinessPlanForInvoiceTransaction",
				"model", model);

	}

	@RequestMapping(value = "/listofPoOnMasterKeyAndName", method = RequestMethod.GET)
	public ModelAndView listofPoOnMasterKeyAndName(@RequestParam("id") Long id,
			ModelMap model) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		FundsDistribute funds = fundsDistributeDAO.getByFundsId(id);

		List<PurchaseOrder> purchase = purchaseOrderDAO
				.getPoListByMasterKeyAndName(funds.getMasterKey(),
						user.getUserName()).getResultList();

		if (purchase != null && purchase.size() > 0) {
			model.put("purchase", purchase);
			return new ModelAndView("listofPoOnMasterKeyAndName", "model",
					model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/listofInvoiceOnMasterKeyAndName", method = RequestMethod.GET)
	public ModelAndView listofInvoiceOnMasterKey(@RequestParam("id") Long id,
			ModelMap model) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		FundsDistribute funds = fundsDistributeDAO.getByFundsId(id);

		List<Invoice> invoice = invoiceDAO.getInvoiceListByMasterKeyAndName(
				funds.getMasterKey(), user.getUserName()).getResultList();

		if (invoice != null && invoice.size() > 0) {
			model.put("invoice", invoice);
			return new ModelAndView("listofInvoiceOnMasterKeyAndName", "model",
					model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/customerBranchInvoiceList", method = RequestMethod.GET)
	public ModelAndView BankFullInvoiceList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<Invoice> invoiceList = invoiceDAO.getInvoiceListByBranchName(
				user.getUserName()).getResultList();

		model.put("user", user);
		if (invoiceList != null && invoiceList.size() > 0) {
			model.put("invoiceList", invoiceList);

			return new ModelAndView("customerBranchInvoiceList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/customerBranchPoList", method = RequestMethod.GET)
	public ModelAndView fullPoList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PurchaseOrder> poList = purchaseOrderDAO.getPoListByBranchName(
				user.getUserName()).getResultList();

		model.put("user", user);
		if (poList != null && poList.size() > 0) {
			model.put("poList", poList);

			return new ModelAndView("customerBranchPoList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	// Purchase order Inventory for Customer Branch
	@RequestMapping(value = "/inventoryPoListForBranch", method = RequestMethod.GET)
	public ModelAndView InventoryPoList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PurchaseOrder> poList = purchaseOrderDAO
				.getPoBycustomerNameAndStatus(user.getUserName())
				.getResultList();

		model.put("user", user);
		if (poList != null && poList.size() > 0) {
			model.put("poList", poList);

			return new ModelAndView("inventoryPoListForBranch", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/inventoryPoListForBranchShow", method = RequestMethod.GET)
	public ModelAndView inventoryPoListForBranchShow(@RequestParam Long id,
			ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchaseorder = purchaseOrderDAO.getByPurchaseId(id);

		inventoryForm.setPoKey(purchaseorder.getPoKey());
		inventoryForm.setCustomerName(purchaseorder.getCustomerName());
		inventoryForm.setGoods(purchaseorder.getGoods());
		List<Inventory> inventory1 = invenrotyDAO.getInventoryByKeyList(
				inventoryForm.getPoKey(), inventoryForm.getCustomerName())
				.getResultList();

		if (inventory1 != null && inventory1.size() > 0) {

			attributes.addFlashAttribute("success", "Please Click On Update");
			return new ModelAndView("redirect:inventoryPoListForBranch");

		} else {

			inventoryForm.setId(purchaseorder.getId());
			List<WareHouse> whList = wareHouseDAO.getWareHouseandStatusList(
					user.getUserName()).getResultList();
			if (whList != null) {
				inventoryForm.setWareHouseList(whList);

				model.put("inventoryForm", inventoryForm);
				model.put("user", user);
			}

			inventoryForm.setPoKey(purchaseorder.getPoKey());
			inventoryForm.setAmount(purchaseorder.getAmount());
			inventoryForm.setQuantity(purchaseorder.getQuantity());

			inventoryForm.setSupplierName(purchaseorder.getSupplierName());
			inventoryForm.setCustomerHeadName(purchaseorder
					.getCustomerHeadName());

			model.put("user", user);
			model.put("inventoryForm", inventoryForm);

			return new ModelAndView("inventoryPoListForBranchShow", "model",
					model);
		}
	}

	@RequestMapping(value = "/inventoryPoListForBranchShowConfirm", method = RequestMethod.POST)
	public ModelAndView approveBankEmpConfrim(
			@ModelAttribute InventoryForm inventoryForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		inventoryForm.setMasterKey(inventoryForm.getMasterKey());
		if (inventoryForm.getTotal() < 0) {
			attributes.addFlashAttribute("success",
					"Total Should Not be Less than Zero");
			return new ModelAndView("redirect:inventoryPoListForBranch");
		} else {

			model.put("user", user);
			model.put("inventoryForm", inventoryForm);

			return new ModelAndView("inventoryPoListForBranchShowConfirm",
					"model", model);
		}
	}

	@RequestMapping(value = "/updateinventoryPoListForBranchShowConfirm", method = RequestMethod.POST)
	public ModelAndView updateinventoryShowConfirm(
			@ModelAttribute InventoryForm inventoryForm, ModelMap model,
			RedirectAttributes attributes) {

		Inventory inventroy = new Inventory();

		inventroy.setId(inventoryForm.getId());
		inventroy.setCustomerName(inventoryForm.getCustomerName());
		inventroy.setCustomerHeadName(inventoryForm.getCustomerHeadName());
		inventroy.setPoKey(inventoryForm.getPoKey());
		inventroy.setAmount(inventoryForm.getAmount());
		inventroy.setDamaged(inventoryForm.getDamaged());
		inventroy.setWareHouseName(inventoryForm.getWareHouseName());
		inventroy.setTotal(inventoryForm.getTotal());
		inventroy.setAvail(inventoryForm.getTotal());
		inventroy.setUsedQuantity(inventoryForm.getUsedQuantity());
		inventroy.setQuantity(inventoryForm.getQuantity());
		inventroy.setMasterKey(inventoryForm.getMasterKey());
		inventroy.setSupplierName(inventoryForm.getSupplierName());
		inventroy.setTransactionId(inventoryForm.getTransactionId());
		inventroy.setFlag(0);
		inventroy.setGoods(inventoryForm.getGoods());
		invenrotyDAO.insertInventory(inventroy);

		return new ModelAndView("redirect:inventoryPoListForBranch");
	}

	@RequestMapping(value = "/inventoryUpdateShowBranch", method = RequestMethod.GET)
	public ModelAndView inventoryUpdateShow(@RequestParam Long id,
			ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchaseorder = purchaseOrderDAO.getByPurchaseId(id);
		inventoryForm.setPoKey(purchaseorder.getPoKey());
		inventoryForm.setCustomerName(purchaseorder.getCustomerName());

		List<Inventory> inventory1 = invenrotyDAO.getInventoryByKeyList(
				inventoryForm.getPoKey(), inventoryForm.getCustomerName())
				.getResultList();

		if (inventory1 != null && inventory1.size() > 0) {

			Inventory inventory = invenrotyDAO.getInventoryByKeyList(
					purchaseorder.getPoKey(), purchaseorder.getCustomerName())
					.getSingleResult();

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

			return new ModelAndView("inventoryUpdateShowBranch", "model", model);
		} else {
			attributes.addFlashAttribute("success", "Please Click On Select");
			return new ModelAndView("redirect:inventoryPoListForBranch");

		}
	}

	@RequestMapping(value = "/inventoryUpdateShowConfirmBranch", method = RequestMethod.POST)
	public ModelAndView inventoryUpdateShowConfirm(
			@ModelAttribute InventoryForm inventoryForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		inventoryForm.setMasterKey(inventoryForm.getMasterKey());
		if (inventoryForm.getTotal() < 0) {
			attributes.addFlashAttribute("success",
					"Total Should Not be Less than Zero");
			return new ModelAndView("redirect:inventoryPoListForBranch");
		} else {

			model.put("user", user);
			model.put("inventoryForm", inventoryForm);

			return new ModelAndView("inventoryUpdateShowConfirmBranch",
					"model", model);
		}
	}

	@RequestMapping(value = "/inventoryupdateShowBranchSave", method = RequestMethod.POST)
	public ModelAndView inventoryupdateShowSave(
			@ModelAttribute InventoryForm inventoryForm, ModelMap model,
			RedirectAttributes attributes) {

		Inventory inventroy = invenrotyDAO.getByInventoryId(inventoryForm
				.getId());

		inventroy.setDamaged(inventoryForm.getDamaged());
		inventroy.setReturned(inventoryForm.getReturned());
		inventroy.setAvail(inventoryForm.getTotal());
		inventroy.setTotal(inventoryForm.getTotal());
		inventroy.setUsedQuantity(inventoryForm.getUsedQuantity());

		invenrotyDAO.updateInventory(inventroy);

		return new ModelAndView("redirect:inventoryPoListForBranch");
	}

	@RequestMapping(value = "/inventoryPoListForBranchApproved", method = RequestMethod.GET)
	public ModelAndView InventoryList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<Inventory> inventoryList = invenrotyDAO
				.getInventoryBycustomerName(user.getUserName()).getResultList();

		model.put("user", user);
		if (inventoryList != null && inventoryList.size() > 0) {
			model.put("inventoryList", inventoryList);

			return new ModelAndView("inventoryPoListForBranchApproved",
					"model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	// Inventory for Invoice Customer Branch

	@RequestMapping(value = "/inventoryInvoiceBranchList", method = RequestMethod.GET)
	public ModelAndView InventoryInvoiceList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<Invoice> invoiceList = invoiceDAO
				.getInoviceListBycustomerNameAndStatus(user.getUserName())
				.getResultList();

		model.put("user", user);
		if (invoiceList != null && invoiceList.size() > 0) {
			model.put("invoiceList", invoiceList);

			return new ModelAndView("inventoryInvoiceBranchList", "model",
					model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/inventoryInvoiceBranchShow", method = RequestMethod.GET)
	public ModelAndView inventoryInvoiceShow(@RequestParam Long id,
			ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		Invoice invoice = invoiceDAO.getByInvoiceId(id);

		inventoryForm.setPoKey(invoice.getPoKey());
		inventoryForm.setCustomerName(invoice.getCustomerName());
		List<InvoiceInventory> inventory1 = invoiceInventoryDAO
				.getInventoryByKeyList(inventoryForm.getPoKey(),
						inventoryForm.getCustomerName()).getResultList();

		if (inventory1 != null && inventory1.size() > 0) {

			attributes.addFlashAttribute("success", "Please Click On Update");
			return new ModelAndView("redirect:inventoryInvoiceBranchList");

		} else {

			List<WareHouse> whList = wareHouseDAO.getWareHouseandStatusList(
					user.getUserName()).getResultList();
			if (whList != null) {
				inventoryForm.setWareHouseList(whList);

				model.put("inventoryForm", inventoryForm);
				model.put("user", user);
			}

			inventoryForm.setId(invoice.getId());
			inventoryForm.setCustomerName(invoice.getCustomerName());
			inventoryForm.setPoKey(invoice.getPoKey());
			inventoryForm.setAmount(invoice.getAmount());
			inventoryForm.setQuantity(invoice.getQuantity());
			inventoryForm.setMasterKey(invoice.getMasterKey());
			inventoryForm.setBuyerName(invoice.getBuyerName());
			inventoryForm.setCustomerHeadName(invoice.getCustomerName());

			model.put("user", user);
			model.put("inventoryForm", inventoryForm);

			return new ModelAndView("inventoryInvoiceBranchShow", "model",
					model);
		}
	}

	@RequestMapping(value = "/inventoryInvoiceBranchShowConfirm", method = RequestMethod.POST)
	public ModelAndView inventoryInvoiceShowConfirm(
			@ModelAttribute InventoryForm inventoryForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		inventoryForm.setMasterKey(inventoryForm.getMasterKey());

		model.put("user", user);
		model.put("inventoryForm", inventoryForm);

		return new ModelAndView("inventoryInvoiceBranchShowConfirm", "model",
				model);
	}

	@RequestMapping(value = "/updateinventoryInvoiceBranchShowConfirm", method = RequestMethod.POST)
	public ModelAndView updateinventoryInvoiceShowConfirm(
			@ModelAttribute InventoryForm inventoryForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		InvoiceInventory invoiceInventory = new InvoiceInventory();

		invoiceInventory.setId(inventoryForm.getId());
		invoiceInventory.setCustomerName(inventoryForm.getCustomerName());
		invoiceInventory.setCustomerHeadName(inventoryForm
				.getCustomerHeadName());
		invoiceInventory.setPoKey(inventoryForm.getPoKey());
		invoiceInventory.setAmount(inventoryForm.getAmount());
		invoiceInventory.setDamaged(inventoryForm.getDamaged());
		invoiceInventory.setTotal(inventoryForm.getTotal());
		invoiceInventory.setUsedQuantity(inventoryForm.getUsedQuantity());
		invoiceInventory.setQuantity(inventoryForm.getQuantity());
		invoiceInventory.setMasterKey(inventoryForm.getMasterKey());
		invoiceInventory.setBuyerName(inventoryForm.getBuyerName());
		invoiceInventory.setWareHouseName(inventoryForm.getWareHouseName());
		invoiceInventory.setAvail(inventoryForm.getTotal());
		invoiceInventory.setInventoryType("Invoice");
		invoiceInventory.setTransactionId(inventoryForm.getTransactionId());
		invoiceInventory.setFlag(0);
		invoiceInventory.setGoods(inventoryForm.getGoods());
		invoiceInventoryDAO.insertInvoiceInventory(invoiceInventory);

		return new ModelAndView("redirect:inventoryInvoiceBranchList");
	}

	@RequestMapping(value = "/invoiceInventoryBranchUpdateShow", method = RequestMethod.GET)
	public ModelAndView invoiceInventoryUpdateShow(@RequestParam Long id,
			ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		Invoice purchaseorder = invoiceDAO.getByInvoiceId(id);
		inventoryForm.setPoKey(purchaseorder.getPoKey());
		inventoryForm.setCustomerName(purchaseorder.getCustomerName());

		List<InvoiceInventory> inventory1 = invoiceInventoryDAO
				.getInventoryByKeyList(inventoryForm.getPoKey(),
						inventoryForm.getCustomerName()).getResultList();

		if (inventory1 != null && inventory1.size() > 0) {

			InvoiceInventory inventory = invoiceInventoryDAO
					.getInventoryByKeyList(purchaseorder.getPoKey(),
							purchaseorder.getCustomerName()).getSingleResult();

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

			return new ModelAndView("invoiceInventoryBranchUpdateShow",
					"model", model);
		} else {
			attributes.addFlashAttribute("success", "Please Click On Select");
			return new ModelAndView("redirect:inventoryInvoiceBranchList");

		}
	}

	@RequestMapping(value = "/invoiceInventoryUpdateBracnhShowConfirm", method = RequestMethod.POST)
	public ModelAndView invoiceInventoryUpdateShowConfirm(
			@ModelAttribute InventoryForm inventoryForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		inventoryForm.setMasterKey(inventoryForm.getMasterKey());
		if (inventoryForm.getTotal() < 0) {
			attributes.addFlashAttribute("success",
					"Total Should Not be Less than Zero");
			return new ModelAndView("redirect:inventoryInvoiceBranchList");
		} else {

			model.put("inventoryForm", inventoryForm);

			return new ModelAndView("invoiceInventoryUpdateBracnhShowConfirm",
					"model", model);
		}
	}

	@RequestMapping(value = "/invoiceInventoryupdateBranchShowSave", method = RequestMethod.POST)
	public ModelAndView invoiceInventoryupdateShowSave(
			@ModelAttribute InventoryForm inventoryForm, ModelMap model,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		InvoiceInventory inventroy = invoiceInventoryDAO
				.getByInvoiceInventoryId(inventoryForm.getId());

		inventroy.setDamaged(inventoryForm.getDamaged());
		inventroy.setAvail(inventoryForm.getTotal());
		inventroy.setTotal(inventoryForm.getTotal());
		inventroy.setUsedQuantity(inventoryForm.getUsedQuantity());

		invoiceInventoryDAO.updateInvoiceInventory(inventroy);

		return new ModelAndView("redirect:inventoryInvoiceBranchList");
	}

	@RequestMapping(value = "/inventoryInvoiceListForBranchApproved", method = RequestMethod.GET)
	public ModelAndView inventoryInvoiceListCustApproved(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<InvoiceInventory> inventoryInvoiceList = invoiceInventoryDAO
				.getInvoiceInventoryBycustomerName(user.getUserName())
				.getResultList();
		model.put("user", user);
		if (inventoryInvoiceList != null && inventoryInvoiceList.size() > 0) {
			model.put("inventoryInvoiceList", inventoryInvoiceList);

			return new ModelAndView("inventoryInvoiceListForBranchApproved",
					"model", model);
		}

		else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	// Add New Buyer
	@RequestMapping(value = "/addnewBuyerCustomerBranch", method = RequestMethod.GET)
	public ModelAndView newBuyer(ModelMap model) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		newBuyerForm.setEndUser(user);
		newBuyerForm.setName(user.getUserName());

		List<NewBuyer> newbuyeList = newBuyerDAO.getByCustAppPending(user.getUserName()).getResultList();

		model.put("user", user);
		model.put("newbuyeList", newbuyeList);
		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("addnewBuyerCustomerBranch", "model", model);
	}

	@RequestMapping(value = "/updatenewBuyerBranchPage", method = RequestMethod.POST)
	public ModelAndView updatenewBuyerPage(
			@ModelAttribute NewBuyerForm newBuyerForm,
			RedirectAttributes attributes, ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<EndUser> endUser = endUserDAOImpl.findByUsername(
				newBuyerForm.getBuyerName()).getResultList();
		if (endUser.size() == 0) {

			model.put("newBuyerForm", newBuyerForm);

			return new ModelAndView("updatenewBuyerBranchPage", "model", model);
		} else {
			attributes
					.addFlashAttribute("success", "Buyer Name Already Exists");

			return new ModelAndView("redirect:addnewBuyerCustomerBranch");

		}

	}

	@RequestMapping(value = "/newbuyerConfirmBranchList", method = RequestMethod.POST)
	public ModelAndView approvalConfirm(
			@ModelAttribute NewBuyerForm newBuyerForm, ModelMap model,
			RedirectAttributes attribute) {

		newBuyerForm.setTransactionId(KeyGenerator.generateTransactionKey());

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		NewBuyer newBuyer = new NewBuyer();

		Transaction transaction = new Transaction();
		newBuyer.setId(newBuyerForm.getId());
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
		newBuyer.setAccExpiryDate(newBuyerForm.getAccExpiryDate());
		newBuyer.setIfsc(newBuyerForm.getIfsc());
		newBuyer.setContactNum(newBuyerForm.getContactNum());
		newBuyer.setCountry(newBuyerForm.getCountry());
		newBuyer.setEmail(newBuyerForm.getEmail());
		newBuyer.setName(newBuyerForm.getName());
		newBuyer.setPinCode(newBuyerForm.getPinCode());
		newBuyer.setState(newBuyerForm.getState());
		newBuyer.setcStatus("Pending");
		newBuyer.setUniqueKey(newBuyerForm.getUniqueKey());
		newBuyer.setCurrencydeal(newBuyerForm.getCurrencydeal());
		newBuyer.setTransactionId(newBuyerForm.getTransactionId());
		newBuyer.setRate(newBuyerForm.getRate());
		transaction.setTransactionId(newBuyerForm.getTransactionId());
		transaction.setTransactionType("Role");
		transaction.setTransactionStatus("Buyer saved successfully");
		newBuyerDAO.createUser(newBuyer);
		transcationDAOImpl.insertTransaction(transaction);

		attribute.addFlashAttribute("success", "Saved Successfully");

		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("newbuyerConfirmBranchList", "model", model);

	}

	@RequestMapping(value = "/selectBuyerForCustomerBranchUpdate", method = RequestMethod.GET)
	public ModelAndView selectBuyerUpdate(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		NewBuyer newBuyer = newBuyerDAO.findId(id);

		newBuyerForm.setId(newBuyer.getId());
		newBuyerForm.setContactNum(newBuyer.getContactNum());
		newBuyerForm.setAddress(newBuyer.getAddress());
		newBuyerForm.setBranch(newBuyer.getBranch());

		newBuyerForm.setTransactionId(newBuyer.getTransactionId());

		model.put("user", user);

		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("selectBuyerForCustomerBranchUpdate", "model",
				model);

	}

	@RequestMapping(value = "/selectBuyerForCustomerBranchUpdate2", method = RequestMethod.POST)
	public ModelAndView selectBuyerUpdate2(ModelMap model,
			@ModelAttribute NewBuyerForm newBuyerForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("newBuyerForm", newBuyerForm);

		return new ModelAndView("selectBuyerForCustomerBranchUpdate2", "model",
				model);

	}

	@RequestMapping(value = "/selectBuyerForCustomerBranchUpdate3", method = RequestMethod.POST)
	public ModelAndView selectBuyerUpdate3(
			@ModelAttribute NewBuyerForm newBuyerForm, ModelMap model,
			RedirectAttributes attribute) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		NewBuyer newBuyer = newBuyerDAO.findId(newBuyerForm.getId());
		Transaction transaction = new Transaction();
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
		newBuyer.setContactNum(newBuyerForm.getContactNum());
		newBuyer.setCountry(newBuyerForm.getCountry());

		newBuyer.setDate(newBuyerForm.getDate());
		newBuyer.setEmail(newBuyerForm.getEmail());
		newBuyer.setId(newBuyerForm.getId());
		newBuyer.setName(newBuyerForm.getName());
		newBuyer.setPinCode(newBuyerForm.getPinCode());
		newBuyer.setState(newBuyerForm.getState());
		newBuyer.setCurrencydeal(newBuyerForm.getCurrencydeal());
		newBuyer.setcStatus("Pending");

		transaction.setTransactionType("Buyer Details");
		transaction
				.setTransactionStatus("Buyer Details status saved successfully");
		transaction.setTransactionId(newBuyerForm.getTransactionId());
		model.put("newBuyerForm", newBuyerForm);

		newBuyerDAO.update(newBuyer);
		transcationDAOImpl.insertTransaction(transaction);
		return new ModelAndView("selectBuyerForCustomerBranchUpdate3", "model",
				model);
	}

	// Add Supplier

	@RequestMapping(value = "/supplierForCustomerBranch", method = RequestMethod.GET)
	public ModelAndView newSupplier(ModelMap model) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		List<Supplier> supplierList = supplierDAO.getByCustPending(user.getUserName())
				.getResultList();
		supplierForm.setEndUser(user);
		supplierForm.setName(user.getUserName());
		model.put("user", user);
		model.put("supplierList", supplierList);

		model.put("supplierForm", supplierForm);

		return new ModelAndView("supplierForCustomerBranch", "model", model);
	}

	@RequestMapping(value = "/supplierPageForwardCustomerBranch", method = RequestMethod.POST)
	public ModelAndView selectsupplierUpdat(
			@ModelAttribute SupplierForm supplierForm,
			RedirectAttributes attributes, ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<EndUser> endUser = endUserDAOImpl.findByUsername(
				supplierForm.getSupplierName()).getResultList();
		if (endUser.size() == 0) {

			model.put("user", user);

			model.put("supplierForm", supplierForm);

			return new ModelAndView("supplierPageForwardCustomerBranch",
					"model", model);
		} else {
			model.put("user", user);
			attributes.addFlashAttribute("success",
					"Supplier Name Already Exists");

			return new ModelAndView("redirect:supplierForCustomerBranch");

		}

	}

	@RequestMapping(value = "/supplierPageCustomerBranchConfirmList", method = RequestMethod.POST)
	public ModelAndView approvalConfirm(
			@ModelAttribute SupplierForm supplierForm, ModelMap model,
			RedirectAttributes attribute) {
		EndUser user = getCurrentLoggedUserDetails();


		supplierForm.setTransactionId(KeyGenerator.generateTransactionKey());

		model.put("user", user);

		Supplier supplier = new Supplier();

		Transaction transaction = new Transaction();

		supplier.setAddress(supplierForm.getAddress());
		supplier.setAltContactNum(supplierForm.getAltContactNum());
		supplier.setAltEmail(supplierForm.getAltEmail());
		supplier.setApproveDate(supplierForm.getApproveDate());
		supplier.setBank(supplierForm.getBank());
		supplier.setBankEmail(supplierForm.getBankEmail());
		supplier.setBranch(supplierForm.getBranch());
		supplier.setCity(supplierForm.getCity());
		supplier.setComment(supplierForm.getComment());
		supplier.setCompanyName(supplierForm.getCompanyName());
		supplier.setContactNum(supplierForm.getContactNum());
		supplier.setCountry(supplierForm.getCountry());
		supplier.setSupplierName(supplierForm.getSupplierName());
		supplier.setDate(supplierForm.getDate());
		supplier.setEmail(supplierForm.getEmail());
		supplier.setName(supplierForm.getName());
		supplier.setPinCode(supplierForm.getPinCode());
		supplier.setState(supplierForm.getState());
		supplier.setCurrencydeal(supplierForm.getCurrencydeal());
		supplier.setcStatus("Pending");
		supplier.setTransactionId(supplierForm.getTransactionId());
		supplier.setRate(supplierForm.getRate());
		transaction.setTransactionId(supplierForm.getTransactionId());
		transaction.setTransactionType("Supplier Details");
		transaction.setTransactionStatus("Supplier Details saved successfully");
		supplierDAO.createUser(supplier);
		transcationDAOImpl.insertTransaction(transaction);

		attribute.addFlashAttribute("success", "Saved Successfully");

		model.put("supplierForm", supplierForm);

		return new ModelAndView("supplierPageCustomerBranchConfirmList",
				"model", model);

	}

	@RequestMapping(value = "/selectsupplierUpdateForCustomerBranch", method = RequestMethod.GET)
	public ModelAndView selectsupplierUpdat(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Supplier supplier = supplierDAO.findId(id);

		supplierForm.setId(supplier.getId());
		supplierForm.setSupplierName(supplier.getSupplierName());
		supplierForm.setPinCode(supplier.getPinCode());
		supplierForm.setCountry(supplier.getCountry());
		supplierForm.setCity(supplier.getCity());
		supplierForm.setEmail(supplier.getEmail());
		supplierForm.setCompanyName(supplier.getCompanyName());
		supplierForm.setName(supplier.getName());
		supplierForm.setContactNum(supplier.getContactNum());
		supplierForm.setAddress(supplier.getAddress());
		supplierForm.setBranch(supplier.getBranch());
		supplierForm.setAltContactNum(supplier.getAltContactNum());
		supplierForm.setAltEmail(supplier.getAltEmail());
		supplierForm.setBank(supplier.getBank());
		supplierForm.setBankEmail(supplier.getBankEmail());
		supplierForm.setCity(supplier.getCity());
		supplierForm.setState(supplier.getState());
		supplierForm.setCurrencydeal(supplier.getCurrencydeal());
		supplierForm.setTransactionId(supplier.getTransactionId());

		model.put("user", user);

		model.put("supplierForm", supplierForm);

		return new ModelAndView("selectsupplierUpdateForCustomerBranch",
				"model", model);

	}

	@RequestMapping(value = "/selectsupplierUpdateForCustomerBranch2", method = RequestMethod.POST)
	public ModelAndView selectSupplierUpdate2(ModelMap model,
			@ModelAttribute SupplierForm supplierForm,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		Supplier supplier = supplierDAO.findId(supplierForm.getId());

		model.put("user", user);
		model.put("supplier", supplier);

		model.put("supplierForm", supplierForm);

		return new ModelAndView("selectsupplierUpdateForCustomerBranch2",
				"model", model);

	}

	@RequestMapping(value = "/selectsupplierUpdateForCustomerBranch3", method = RequestMethod.POST)
	public ModelAndView selectSupplierUpdate3(
			@ModelAttribute SupplierForm supplierForm, ModelMap model,
			RedirectAttributes attribute) {
		EndUser user = getCurrentLoggedUserDetails();
		Supplier supplier = supplierDAO.findId(supplierForm.getId());
		Transaction transaction = new Transaction();
		supplier.setAddress(supplierForm.getAddress());
		supplier.setSupplierName(supplierForm.getSupplierName());
		supplier.setAltContactNum(supplierForm.getAltContactNum());
		supplier.setAltEmail(supplierForm.getAltEmail());
		supplier.setApproveDate(supplierForm.getApproveDate());
		supplier.setBank(supplierForm.getBank());
		supplier.setBankEmail(supplierForm.getBankEmail());
		supplier.setBranch(supplierForm.getBranch());
		supplier.setCity(supplierForm.getCity());
		supplier.setComment(supplierForm.getComment());
		supplier.setCompanyName(supplierForm.getCompanyName());
		supplier.setContactNum(supplierForm.getContactNum());
		supplier.setCountry(supplierForm.getCountry());
		supplier.setCustomerPrefix(supplierForm.getCustomerPrefix());
		supplier.setDate(supplierForm.getDate());
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
		transaction
				.setTransactionStatus("Supplier Details Status saved successfully");
		transaction.setTransactionId(supplierForm.getTransactionId());
		model.put("supplierForm", supplierForm);
		model.put("user", user);
		supplierDAO.update(supplier);
		transcationDAOImpl.insertTransaction(transaction);
		return new ModelAndView("selectsupplierUpdateForCustomerBranch3",
				"model", model);
	}

	@RequestMapping(value = "/branchFundsRequest", method = RequestMethod.GET)
	public ModelAndView branchFundsRequest(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<MasterPlan> masterList = masterPlanDAO.getMasterPlanForFunds(
				user.getCustomerHeadName()).getResultList();
		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);

			return new ModelAndView("branchFundsRequest", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/branchRequestMoney", method = RequestMethod.GET)
	public ModelAndView branchRequestMoney(@RequestParam("id") Long id,
			ModelMap model) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		MasterPlan plan = masterPlanDAO.getByMasterPlanId(id);

		requestMoneyForm.setId(plan.getId());
		requestMoneyForm.setRequestedBy(user.getUserName());
		requestMoneyForm.setRequestedFrom(plan.getCustomer());
		requestMoneyForm.setMasterKey(plan.getMasterKey());
		requestMoneyForm.setAvailAmount(plan.getBalance());

		requestMoneyForm.setCustomerEmail(plan.getCustomerEmail());

		model.put("user", user);
		model.put("requestMoneyForm", requestMoneyForm);

		return new ModelAndView("branchRequestMoney", "model", model);
	}

	@RequestMapping(value = "/branchRequestMoneyConfirm", method = RequestMethod.POST)
	public ModelAndView branchRequestMoneyConfirm(
			@ModelAttribute RequestMoneyForm requestMoneyForm, ModelMap model,
			RedirectAttributes attributes) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		if (requestMoneyForm.getAmount().compareTo(
				requestMoneyForm.getAvailAmount()) > 0) {
			Long Id = requestMoneyForm.getId();
			attributes.addFlashAttribute("success",
					"Amount Should Not Exceed Available Amount");
			return new ModelAndView("redirect:branchRequestMoney?id=" + Id + "");
		}

		requestMoneyForm.setRequestedBy(requestMoneyForm.getRequestedBy());
		requestMoneyForm.setRequestedFrom(requestMoneyForm.getRequestedFrom());
		requestMoneyForm.setMasterKey(requestMoneyForm.getMasterKey());
		requestMoneyForm.setAmount(requestMoneyForm.getAmount());

		requestMoneyForm.setCustomerEmail(requestMoneyForm.getCustomerEmail());

		model.put("user", user);
		model.put("requestMoneyForm", requestMoneyForm);

		return new ModelAndView("branchRequestMoneyConfirm", "model", model);
	}

	@RequestMapping(value = "/branchRequestMoneyPost", method = RequestMethod.POST)
	public ModelAndView requestMoneyPost(
			@ModelAttribute RequestMoneyForm requestMoneyForm, ModelMap model,
			RedirectAttributes attributes) {


		requestMoneyForm.setTransactionId(KeyGenerator.generateTransactionKey());

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		RequestMoney money = new RequestMoney();

		money.setRequestedBy(requestMoneyForm.getRequestedBy());
		money.setRequestedFrom(requestMoneyForm.getRequestedFrom());
		money.setMasterKey(requestMoneyForm.getMasterKey());
		money.setAmount(requestMoneyForm.getAmount());

		money.setCustomerEmail(requestMoneyForm.getCustomerEmail());
		money.setTransactionId(requestMoneyForm.getTransactionId());

		Date reqDate = DateService.loginDate;
		money.setRequestDate(reqDate);

		requestMoneyDAO.insertRequest(money);

		Transaction trans = new Transaction();

		trans.setTransactionId(requestMoneyForm.getTransactionId());
		trans.setTransactionType("Request For Money");
		trans.setTransactionStatus("Submitted successfully");
		transcationDAOImpl.insertTransaction(trans);

		String bankName = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0).getBankName();
		try {

			String customerName = requestMoneyForm.getRequestedFrom();

			String customerheadmail = requestMoneyForm.getCustomerEmail();

			String branchname = requestMoneyForm.getRequestedBy();
			Float amountrequest = requestMoneyForm.getAmount();

			String tex = "Requested Amount Details Notification";
			SimpleMailMessage emails = new SimpleMailMessage();
			emails.setTo(customerheadmail);
			emails.setSubject(tex);
			emails.setText("Hello " + customerName

			+ "\n\n:" + branchname

			+ " has a money request " + "\n"

			+ "\n\nAmount Requested:" + amountrequest +

			"\n\n\nRegards,\n"+bankName);
			System.out.println("" + customerheadmail + customerName);
			mailSender.send(emails);
			SimpleMailMessage emails1 = new SimpleMailMessage();
			emails1.setTo(customerheadmail);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		model.put("user", user);
		model.put("requestMoneyForm", requestMoneyForm);

		return new ModelAndView("branchRequestMoneyTransaction", "model", model);
	}

	@RequestMapping(value = "/branchRequestMoneyTransaction", method = RequestMethod.GET)
	public ModelAndView requestMoneyTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("requestMoneyForm", requestMoneyForm);

		return new ModelAndView("branchRequestMoneyTransaction", "model", model);

	}

	@RequestMapping(value = "/viewBranchRequestSent", method = RequestMethod.GET)
	public ModelAndView viewRequestSent(ModelMap model) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);

		List<RequestMoney> requestList = requestMoneyDAO.getRequestSentList(
				user.getUserName()).getResultList();

		if (requestList != null && requestList.size() > 0) {

			model.put("requestList", requestList);
			return new ModelAndView("viewBranchRequestSent", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/viewBranchRequestRecieved", method = RequestMethod.GET)
	public ModelAndView viewRequestRecieved(ModelMap model) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);

		List<RequestMoney> requestList = requestMoneyDAO
				.getRequestRecievedList(user.getUserName()).getResultList();

		if (requestList != null && requestList.size() > 0) {

			model.put("requestList", requestList);
			return new ModelAndView("viewBranchRequestRecieved", "model", model);
		}

		else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}
	}

	@RequestMapping(value = "/branchFundsTransfer", method = RequestMethod.GET)
	public ModelAndView branchFundsTransfer(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<FundsDistribute> masterList = fundsDistributeDAO
				.getFundsListByCustomerName(user.getUserName()).getResultList();

		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);

			return new ModelAndView("branchFundsTransfer", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/branchFundsTransfer1", method = RequestMethod.GET)
	public ModelAndView branchFundsTransfer1(ModelMap model,
			@RequestParam("id") Long id) {

		EndUser user = getCurrentLoggedUserDetails();

		FundsDistribute fundsList = fundsDistributeDAO.getByFundsId(id);
		if (fundsList != null) {
			fundsDistributeForm.setId(fundsList.getId());
			fundsDistributeForm.setCustomerName(fundsList.getCustomerName());
			fundsDistributeForm.setCustomerHeadName(fundsList
					.getCustomerHeadName());
			fundsDistributeForm.setCustHeadEmail(fundsList.getCustHeadEmail());
			fundsDistributeForm.setCustHeadMngEmail(fundsList
					.getCustHeadMngEmail());
			fundsDistributeForm.setEmail(fundsList.getEmail());
			fundsDistributeForm.setManagerEmail(fundsList.getManagerEmail());
			fundsDistributeForm.setBusBalance(fundsList.getBusBalance());
			fundsDistributeForm
					.setUtilizedAmount(fundsList.getUtilizedAmount());
			fundsDistributeForm.setDistributedAmount(fundsList
					.getDistributedAmount());
			fundsDistributeForm.setMasterKey(fundsList.getMasterKey());

			MasterPlan plan = masterPlanDAO.getMasterPlanByMasterKey(
					fundsDistributeForm.getMasterKey()).getSingleResult();
			if (plan != null) {

				fundsDistributeForm.setBalance(plan.getBalance());
			}
			model.put("fundsDistributeForm", fundsDistributeForm);
		}
		model.put("user", user);

		return new ModelAndView("branchFundsTransfer1", "model", model);

	}

	@RequestMapping(value = "/branchFundsTransfer1Confirm", method = RequestMethod.POST)
	public ModelAndView branchFundsTransfer1Confirm(
			@ModelAttribute FundsDistributeForm fundsDistributeForm,
			ModelMap model, RedirectAttributes attributes) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		if (fundsDistributeForm.getAmount().compareTo(
				fundsDistributeForm.getBusBalance()) > 0) {
			Long Id = fundsDistributeForm.getId();
			attributes.addFlashAttribute("success",
					"Amount Should Not Exceed Blance");
			return new ModelAndView("redirect:branchFundsTransfer1?id=" + Id
					+ "");
		}

		fundsDistributeForm.setCustomerName(fundsDistributeForm
				.getCustomerName());
		fundsDistributeForm.setCustomerHeadName(fundsDistributeForm
				.getCustomerHeadName());
		fundsDistributeForm.setCustHeadEmail(fundsDistributeForm
				.getCustHeadEmail());
		fundsDistributeForm.setCustHeadMngEmail(fundsDistributeForm
				.getCustHeadMngEmail());
		fundsDistributeForm.setEmail(fundsDistributeForm.getEmail());
		fundsDistributeForm.setManagerEmail(fundsDistributeForm
				.getManagerEmail());
		fundsDistributeForm.setBusBalance(fundsDistributeForm.getBusBalance());
		fundsDistributeForm.setUtilizedAmount(fundsDistributeForm
				.getUtilizedAmount());
		fundsDistributeForm.setDistributedAmount(fundsDistributeForm
				.getDistributedAmount());
		fundsDistributeForm.setAmount(fundsDistributeForm.getAmount());
		fundsDistributeForm.setMasterKey(fundsDistributeForm.getMasterKey());
		fundsDistributeForm.setBalance(fundsDistributeForm.getBalance());

		model.put("user", user);
		model.put("fundsDistributeForm", fundsDistributeForm);

		return new ModelAndView("branchFundsTransfer1Confirm", "model", model);
	}

	@RequestMapping(value = "/branchFundsTransferPost", method = RequestMethod.POST)
	public ModelAndView masterPlanFundsDistributePost(ModelMap model,
			@ModelAttribute FundsDistributeForm fundsDistributeForm,
			RedirectAttributes attributes) {

		fundsDistributeForm.setTransactionId(KeyGenerator.generateTransactionKey());

		EndUser user = getCurrentLoggedUserDetails();
		FundsStatement statement = new FundsStatement();

		fundsDistributeForm.setCustomerName(fundsDistributeForm
				.getCustomerName());
		fundsDistributeForm.setCustomerHeadName(fundsDistributeForm
				.getCustomerHeadName());
		fundsDistributeForm.setCustHeadEmail(fundsDistributeForm
				.getCustHeadEmail());
		fundsDistributeForm.setCustHeadMngEmail(fundsDistributeForm
				.getCustHeadMngEmail());
		fundsDistributeForm.setEmail(fundsDistributeForm.getEmail());
		fundsDistributeForm.setManagerEmail(fundsDistributeForm
				.getManagerEmail());
		fundsDistributeForm.setBusBalance(fundsDistributeForm.getBusBalance());
		fundsDistributeForm.setUtilizedAmount(fundsDistributeForm
				.getUtilizedAmount());
		fundsDistributeForm.setDistributedAmount(fundsDistributeForm
				.getDistributedAmount());
		fundsDistributeForm.setAmount(fundsDistributeForm.getAmount());
		fundsDistributeForm.setMasterKey(fundsDistributeForm.getMasterKey());
		fundsDistributeForm.setBalance(fundsDistributeForm.getBalance());

		Float amt = fundsDistributeForm.getAmount();
		Float bal = fundsDistributeForm.getBusBalance();
		Float bal1 = fundsDistributeForm.getBalance();

		Float totalbal = bal - amt;
		Float totalbal1 = bal1 + amt;
		FundsDistribute funds = fundsDistributeDAO.getFundsList(
				fundsDistributeForm.getMasterKey(),
				fundsDistributeForm.getCustomerName()).getSingleResult();
		if (funds != null) {
			funds.setBusBalance(totalbal);
			funds.setTransactionId(fundsDistributeForm.getTransactionId());
			fundsDistributeDAO.updateFunds(funds);
		}
		MasterPlan planList = masterPlanDAO.getMasterPlanByMasterKey(
				fundsDistributeForm.getMasterKey()).getSingleResult();
		if (planList != null) {
			planList.setBalance(totalbal1);
			masterPlanDAO.updatePlan(planList);
		}

		statement.setCustomerHeadName(fundsDistributeForm.getCustomerName());
		statement.setCustomerName(fundsDistributeForm.getCustomerHeadName());
		statement.setAmount(fundsDistributeForm.getAmount());
		statement.setBalance(totalbal);
		statement.setTransactionID(fundsDistributeForm.getTransactionId());
		Date fundsDate = DateService.loginDate;
		statement.setFundsDate(fundsDate);

		fundsStatementDAO.insertStatement(statement);

		Transaction trans = new Transaction();

		trans.setTransactionId(fundsDistributeForm.getTransactionId());
		trans.setTransactionType("Funds Distribute");
		trans.setTransactionStatus("Distributed successfully");
		transcationDAOImpl.insertTransaction(trans);

		model.put("fundsDistributeForm", fundsDistributeForm);

		String bankName = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0).getBankName();

		try {
			String managermail = fundsDistributeForm.getManagerEmail();
			String email = fundsDistributeForm.getEmail();
			String custheademail = fundsDistributeForm.getCustHeadEmail();
			String custheadmngEmail = fundsDistributeForm.getCustHeadMngEmail();
			String customerName = fundsDistributeForm.getCustomerName();

			String masterKey = fundsDistributeForm.getMasterKey();
			Float availabeamount = fundsDistributeForm.getBusBalance();
			Float amount = fundsDistributeForm.getAmount();

			String tex = "Fund Transfer Details Notification";
			SimpleMailMessage emails = new SimpleMailMessage();
			emails.setTo(email);
			emails.setSubject(tex);
			emails.setText("Hello " + customerName
					+ ",\n\n  Funds Transferred Successfully  " + "\n" + "\n"

					+ "\n\nMaterKey:" + masterKey + "\n\nAvailabe Amount:"
					+ availabeamount +

					"\n\nAmount:" + amount +

					"\n\n\nRegards,\n"+bankName);
			System.out.println("" + email + customerName);
			mailSender.send(emails);
			SimpleMailMessage emails1 = new SimpleMailMessage();
			emails1.setTo(email);

			String tex1 = "Fund Transfer Details Notification";
			SimpleMailMessage emails2 = new SimpleMailMessage();
			emails2.setTo(custheademail);
			emails2.setSubject(tex1);
			emails2.setText("Hello " + customerName
					+ ",\n\n Funds Transferred Successfully  " + "\n" + "\n"

					+ "\n\nMaterKey:" + masterKey + "\n\nAvailabe Amount:"
					+ availabeamount +

					"\n\nAmount:" + amount +

					"\n\n\nRegards,\n"+bankName);
			System.out.println("" + custheademail + customerName);
			mailSender.send(emails2);
			SimpleMailMessage email2 = new SimpleMailMessage();
			email2.setTo(custheademail);

			String tex2 = "Fund Transfer Details Notification";
			SimpleMailMessage emails3 = new SimpleMailMessage();
			emails3.setTo(managermail);
			emails3.setSubject(tex2);
			emails3.setText("Hello " + customerName
					+ ",\n\n Funds Transferred Successfully  " + "\n" + "\n"

					+ "\n\nMaterKey:" + masterKey + "\n\nAvailabe Amount:"
					+ availabeamount +

					"\n\nAmount:" + amount +

					"\n\n\nRegards,\n"+bankName);
			System.out.println("" + managermail + customerName);
			mailSender.send(emails3);
			SimpleMailMessage email3 = new SimpleMailMessage();
			email3.setTo(managermail);

			String tex3 = "Fund Transfer Details Notification";
			SimpleMailMessage emails4 = new SimpleMailMessage();
			emails4.setTo(custheadmngEmail);
			emails4.setSubject(tex3);
			emails4.setText("Hello " + customerName
					+ ",\n\n Funds Transferred Successfully  " + "\n" + "\n"

					+ "\n\nMaterKey:" + masterKey + "\n\nAvailabe Amount:"
					+ availabeamount +

					"\n\nAmount:" + amount +

					"\n\n\nRegards,\n"+bankName);
			System.out.println("" + custheadmngEmail + customerName);
			mailSender.send(emails4);
			SimpleMailMessage email4 = new SimpleMailMessage();
			email4.setTo(custheadmngEmail);

			model.put("fundsDistributeForm", fundsDistributeForm);

		} catch (Exception e) {
			System.out.println(e.getMessage() + e);
		}
		model.put("user", user);
		return new ModelAndView("branchFundsTransferTransaction", "model",
				model);
	}

	@RequestMapping(value = "/branchFundsTransferTransaction", method = RequestMethod.GET)
	public ModelAndView FundsDistributeTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("fundsDistributeForm", fundsDistributeForm);

		return new ModelAndView("branchFundsTransferTransaction", "model",
				model);

	}

	@RequestMapping(value = "/fullBranchFundsDistributeStatement", method = RequestMethod.GET)
	public ModelAndView fullFundsStatement(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<FundsStatement> statement = fundsStatementDAO.getStatementList(
				user.getUserName()).getResultList();

		if (statement != null && statement.size() > 0) {
			model.put("statement", statement);

			return new ModelAndView("fullBranchFundsDistributeStatement",
					"model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/branchBuyerPoList", method = RequestMethod.GET)
	public ModelAndView buyerPoList(ModelMap model) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		List<BuyerPO> buyerList = buyerPODAO.getBuyerPOByHeadName(
				user.getUserName()).getResultList();
		if (buyerList != null && buyerList.size() > 0) {
			model.put("buyerList", buyerList);

			return new ModelAndView("branchBuyerPoList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}
	}

	@RequestMapping(value = "/branchShowMail", method = RequestMethod.GET)
	public ModelAndView doSendEmail1(@ModelAttribute ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		return new ModelAndView("branchQueryMail", "model", model);
	}

	@RequestMapping(value = "/branchMailSender", method = RequestMethod.POST)
	public ModelAndView doSendEmail(@ModelAttribute ModelMap model,
			HttpServletRequest request) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
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
		return new ModelAndView("branchResult", "model", model);
	}

	@RequestMapping(value = "/branchShowCurrency", method = RequestMethod.GET)
	public ModelAndView showCurrency(ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		return new ModelAndView("branchCurrency", "model", model);
	}

	@RequestMapping(value = "/branchShowcurrencyConversion", method = RequestMethod.GET)
	public ModelAndView showcurrencyConversion(@ModelAttribute ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		return new ModelAndView("branchShowcurrencyConversion", "model", model);
	}

	@RequestMapping(value = "/poPaymentBranchList", method = RequestMethod.GET)
	public ModelAndView poPaymentHeadList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> purchaseList = purchaseOrderDAO
				.getPoListForPayment(user.getUserName()).getResultList();

		if (purchaseList != null && purchaseList.size() > 0) {
			model.put("purchaseList", purchaseList);

			return new ModelAndView("poPaymentBranchList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/poPaymentBranch", method = RequestMethod.GET)
	public ModelAndView poPaymentHead(@RequestParam Long id, ModelMap model,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(id);

		purchaseOrderForm.setId(purchase.getId());
		purchaseOrderForm.setSupplierName(purchase.getSupplierName());
		purchaseOrderForm.setCustomerHeadName(purchase.getCustomerHeadName());
		purchaseOrderForm.setCustomerName(purchase.getCustomerName());
		purchaseOrderForm.setPoKey(purchase.getPoKey());
		purchaseOrderForm.setSupplierEmail(purchase.getSupplierEmail());
		purchaseOrderForm.setCustomerHeadEmail(purchase.getCustomerHeadEmail());
		purchaseOrderForm.setCustomerBranchEmail(purchase
				.getCustomerBranchEmail());
		purchaseOrderForm.setAmount(purchase.getAmount());
		purchaseOrderForm.setTransactionId(purchase.getTransactionId());
		purchaseOrderForm.setPayBalance(purchase.getPayBalance());

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("poPaymentBranch", "model", model);
	}

	@RequestMapping(value = "/poPaymentBranchConfirm", method = RequestMethod.POST)
	public ModelAndView poPaymentHeadConfirm(ModelMap model,
			@ModelAttribute PurchaseOrderForm purchaseOrderForm,
			BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		if (purchaseOrderForm.getTypeOfTrans().equals("LC")
				|| purchaseOrderForm.getTypeOfTrans().equals("BG")) {
			model.put("purchaseOrderForm", purchaseOrderForm);
			return new ModelAndView("poPaymentBranchLcPage", "model", model);
		} else {
			purchaseOrderForm.setId(purchaseOrderForm.getId());
			purchaseOrderForm.setSupplierName(purchaseOrderForm
					.getSupplierName());
			purchaseOrderForm.setCustomerHeadName(purchaseOrderForm
					.getCustomerHeadName());
			purchaseOrderForm.setCustomerName(purchaseOrderForm
					.getCustomerName());
			purchaseOrderForm.setPoKey(purchaseOrderForm.getPoKey());
			purchaseOrderForm.setSupplierEmail(purchaseOrderForm
					.getSupplierEmail());
			purchaseOrderForm.setCustomerHeadEmail(purchaseOrderForm
					.getCustomerHeadEmail());
			purchaseOrderForm.setCustomerBranchEmail(purchaseOrderForm
					.getCustomerBranchEmail());
			purchaseOrderForm.setAmount(purchaseOrderForm.getAmount());
			purchaseOrderForm
					.setTypeOfTrans(purchaseOrderForm.getTypeOfTrans());
			purchaseOrderForm.setChequenum(purchaseOrderForm.getChequenum());
			purchaseOrderForm.setSignAuth(purchaseOrderForm.getSignAuth());
			purchaseOrderForm.setAmtLimit(purchaseOrderForm.getAmtLimit());
			purchaseOrderForm.setTransactionId(purchaseOrderForm
					.getTransactionId());
			purchaseOrderForm.setPayBalance(purchaseOrderForm.getPayBalance());
			model.put("purchaseOrderForm", purchaseOrderForm);
			model.put("user", user);
		}
		return new ModelAndView("poPaymentBranchConfirm", "model", model);

	}

	@RequestMapping(value = "/poPaymentBranchSave", method = RequestMethod.POST)
	public ModelAndView poPaymentHeadSave(ModelMap model,
			@ModelAttribute PurchaseOrderForm purchaseOrderForm,
			BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchase = purchaseOrderDAO
				.getByPurchaseId(purchaseOrderForm.getId());

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

		model.put("purchaseOrderForm", purchaseOrderForm);
		model.put("user", user);

		return new ModelAndView("poPaymentBranchTrans", "model", model);

	}

	@RequestMapping(value = "/poPaymentBranchAdvance", method = RequestMethod.GET)
	public ModelAndView poPaymentBranchAdvance(@RequestParam Long id,
			ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(id);

		purchaseOrderForm.setId(purchase.getId());
		purchaseOrderForm.setSupplierName(purchase.getSupplierName());
		purchaseOrderForm.setCustomerHeadName(purchase.getCustomerHeadName());
		purchaseOrderForm.setCustomerName(purchase.getCustomerName());
		purchaseOrderForm.setPoKey(purchase.getPoKey());
		purchaseOrderForm.setSupplierEmail(purchase.getSupplierEmail());
		purchaseOrderForm.setCustomerHeadEmail(purchase.getCustomerHeadEmail());
		purchaseOrderForm.setCustomerBranchEmail(purchase
				.getCustomerBranchEmail());
		purchaseOrderForm.setPayAdvance(purchase.getPayAdvance());
		purchaseOrderForm.setPayBalance(purchase.getPayBalance());
		purchaseOrderForm.setAmount(purchase.getAmount());
		purchaseOrderForm.setTransactionId(purchase.getTransactionId());

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("poPaymentBranchAdvance", "model", model);
	}

	@RequestMapping(value = "/poPaymentBranchAdvanceConfirm", method = RequestMethod.POST)
	public ModelAndView poPaymentBranchAdvanceConfirm(ModelMap model,
			@ModelAttribute PurchaseOrderForm purchaseOrderForm,
			RedirectAttributes attributes, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();
		Float advance = purchaseOrderForm.getPayAdvance();
		Float bal = purchaseOrderForm.getPayBalance();
		Float amt = purchaseOrderForm.getPayNow();

		Float adv = advance + amt;
		Float balance = bal - amt;
		if (balance <= 0) {
			attributes.addFlashAttribute("success",
					"Payment is more than the total amount");
			model.put("purchaseOrderForm", purchaseOrderForm);
			return new ModelAndView("redirect:poPaymentBranchList");
		} else {
			purchaseOrderForm.setId(purchaseOrderForm.getId());
			purchaseOrderForm.setSupplierName(purchaseOrderForm
					.getSupplierName());
			purchaseOrderForm.setCustomerHeadName(purchaseOrderForm
					.getCustomerHeadName());
			purchaseOrderForm.setCustomerName(purchaseOrderForm
					.getCustomerName());
			purchaseOrderForm.setPoKey(purchaseOrderForm.getPoKey());
			purchaseOrderForm.setSupplierEmail(purchaseOrderForm
					.getSupplierEmail());
			purchaseOrderForm.setCustomerHeadEmail(purchaseOrderForm
					.getCustomerHeadEmail());
			purchaseOrderForm.setCustomerBranchEmail(purchaseOrderForm
					.getCustomerBranchEmail());
			purchaseOrderForm.setAmount(purchaseOrderForm.getAmount());
			purchaseOrderForm
					.setTypeOfTrans(purchaseOrderForm.getTypeOfTrans());
			purchaseOrderForm.setChequenum(purchaseOrderForm.getChequenum());
			purchaseOrderForm.setSignAuth(purchaseOrderForm.getSignAuth());
			purchaseOrderForm.setAmtLimit(purchaseOrderForm.getAmtLimit());
			purchaseOrderForm.setPayAdvance(adv);
			purchaseOrderForm.setPayBalance(balance);
			purchaseOrderForm.setTransactionId(purchaseOrderForm
					.getTransactionId());
			model.put("purchaseOrderForm", purchaseOrderForm);
			model.put("user", user);
		}
		return new ModelAndView("poPaymentBranchAdvanceConfirm", "model", model);

	}

	@RequestMapping(value = "/poPaymentBranchAdvanceSave", method = RequestMethod.POST)
	public ModelAndView poPaymentBranchAdvanceSave(ModelMap model,
			@ModelAttribute PurchaseOrderForm purchaseOrderForm,
			BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchase = purchaseOrderDAO
				.getByPurchaseId(purchaseOrderForm.getId());
		purchase.setChequenum(purchaseOrderForm.getChequenum());
		purchase.setSignAuth(purchaseOrderForm.getSignAuth());
		purchase.setAmtLimit(purchaseOrderForm.getAmtLimit());
		purchase.setTypeOfTrans(purchaseOrderForm.getTypeOfTrans());
		purchase.setPayAdvance(purchaseOrderForm.getPayAdvance());
		purchase.setPayBalance(purchaseOrderForm.getPayBalance());
		purchase.setTransResult("Multiple");

		purchaseOrderDAO.updatePo(purchase);
		Transaction trans = new Transaction();

		trans.setTransactionId(purchaseOrderForm.getTransactionId());
		trans.setTransactionStatus("Advance Payment For PO");
		trans.setTransactionType("Sent Successfully");

		transcationDAOImpl.insertTransaction(trans);

		model.put("purchaseOrderForm", purchaseOrderForm);
		model.put("user", user);

		return new ModelAndView("poPaymentBranchTrans", "model", model);

	}

	@RequestMapping(value = "/poPaymentBranchTrans", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentHalfTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("poPaymentBranchTrans", "model", model);

	}

	/**
	 * Method to make multiple PO payment
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/poMultiplePayment", method = RequestMethod.GET)
	public ModelAndView poMultiplePayment(ModelMap model,
			@ModelAttribute PurchaseOrderForm purchaseOrderForm) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> purchaseList = new ArrayList<PurchaseOrder>();
		Float totalAmt = 0f;
		if (purchaseOrderForm.getPurchaseList() == null) {
			purchaseList = purchaseOrderDAO.getPoListForPayment(
					user.getUserName()).getResultList();
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
			return new ModelAndView("poMultiplePaymentBranch", "model", model);
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
	public String poMultiplePaymentReview(ModelMap model,
			@ModelAttribute PurchaseOrderForm purchaseOrderForm,
			RedirectAttributes attributes) {
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
	public String poMultiplePaymentUpdate(ModelMap model,
			@ModelAttribute PurchaseOrderForm purchaseOrderForm,
			RedirectAttributes attributes) {

		if (purchaseOrderForm.getPurchaseList() != null
				&& purchaseOrderForm.getPurchaseList().size() > 0) {
			for (PurchaseOrder po : purchaseOrderForm.getPurchaseList()) {
				if (po.getId() != null) {
					PurchaseOrder purchase = purchaseOrderDAO
							.getByPurchaseId(po.getId());

					purchase.setTransResult(Constants.PENDING);
					purchase.setTransStatus(Constants.APPROVED);
					purchase.setTypeOfTrans(Constants.MULTITRANS);
					purchase.setPoPayDate(DateService.loginDate);
					purchase.setTransactionId(purchaseOrderForm
							.getTransactionId());

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
		return "redirect:poPaymentBranchTrans";
	}

	@RequestMapping(value = "/poPaymentBranchLcPage", method = RequestMethod.GET)
	public ModelAndView poPaymentHeadLcPage(ModelMap model,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		letterOfCreditForm.setId(purchaseOrderForm.getId());
		letterOfCreditForm.setSupplierName(purchaseOrderForm.getSupplierName());
		letterOfCreditForm.setCustomerHeadName(purchaseOrderForm
				.getCustomerHeadName());
		letterOfCreditForm.setCustomerName(purchaseOrderForm.getCustomerName());
		letterOfCreditForm.setPoKey(purchaseOrderForm.getPoKey());
		letterOfCreditForm.setSupplierEmail(purchaseOrderForm
				.getSupplierEmail());
		letterOfCreditForm.setCustomerHeadEmail(purchaseOrderForm
				.getCustomerHeadEmail());
		letterOfCreditForm.setCustomerBranchEmail(purchaseOrderForm
				.getCustomerBranchEmail());
		letterOfCreditForm.setAmount(purchaseOrderForm.getAmount());
		letterOfCreditForm.setTypeOfTrans(purchaseOrderForm.getTypeOfTrans());
		letterOfCreditForm.setTransactionId(purchaseOrderForm
				.getTransactionId());
		letterOfCreditForm.setPayBalance(purchaseOrderForm.getPayBalance());

		model.put("user", user);
		model.put("letterOfCreditForm", letterOfCreditForm);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("poPaymentBranchLcPage", "model", model);
	}

	@RequestMapping(value = "/poPaymentBranchLcPageConfirm", method = RequestMethod.POST)
	public ModelAndView poPaymentHeadLcPageConfirm(ModelMap model,
			@ModelAttribute LetterOfCreditForm letterOfCreditForm,
			BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		letterOfCreditForm.setId(letterOfCreditForm.getId());
		letterOfCreditForm
				.setSupplierName(letterOfCreditForm.getSupplierName());
		letterOfCreditForm.setCustomerHeadName(letterOfCreditForm
				.getCustomerHeadName());
		letterOfCreditForm
				.setCustomerName(letterOfCreditForm.getCustomerName());
		letterOfCreditForm.setPoKey(letterOfCreditForm.getPoKey());
		letterOfCreditForm.setSupplierEmail(letterOfCreditForm
				.getSupplierEmail());
		letterOfCreditForm.setCustomerHeadEmail(letterOfCreditForm
				.getCustomerHeadEmail());
		letterOfCreditForm.setCustomerBranchEmail(letterOfCreditForm
				.getCustomerBranchEmail());
		letterOfCreditForm.setContactPerson(letterOfCreditForm.getContactPerson());
		letterOfCreditForm.setAmount(letterOfCreditForm.getAmount());
		letterOfCreditForm.setTransactionId(letterOfCreditForm
				.getTransactionId());
		letterOfCreditForm.setTypeOfLc(letterOfCreditForm.getTypeOfLc());
		letterOfCreditForm.setBankType(letterOfCreditForm.getBankType());
		letterOfCreditForm.setBankName(letterOfCreditForm.getBankName());
		letterOfCreditForm.setBankAddress(letterOfCreditForm.getBankAddress());
		letterOfCreditForm.setBankBranch(letterOfCreditForm.getBankBranch());
		letterOfCreditForm.setSwiftCode(letterOfCreditForm.getSwiftCode());
		letterOfCreditForm.setAccNo(letterOfCreditForm.getAccNo());
		letterOfCreditForm.setContactNum(letterOfCreditForm.getContactNum());
		letterOfCreditForm
				.setBankNameCorr(letterOfCreditForm.getBankNameCorr());
		letterOfCreditForm.setBankBranchCorr(letterOfCreditForm
				.getBankBranchCorr());
		letterOfCreditForm.setBankLocCorr(letterOfCreditForm.getBankLocCorr());
		letterOfCreditForm.setSwiftCodeCorr(letterOfCreditForm
				.getSwiftCodeCorr());
		letterOfCreditForm.setAccNum(letterOfCreditForm.getAccNum());
		letterOfCreditForm.setTypeOfTrans(letterOfCreditForm.getTypeOfTrans());
		letterOfCreditForm.setPayBalance(letterOfCreditForm.getPayBalance());

		model.put("letterOfCreditForm", letterOfCreditForm);
		model.put("user", user);

		return new ModelAndView("poPaymentBranchLcPageConfirm", "model", model);

	}

	@RequestMapping(value = "/poPaymentBranchLcPageSave", method = RequestMethod.POST)
	public ModelAndView poPaymentHeadLcPageSave(ModelMap model,
			@ModelAttribute LetterOfCreditForm letterOfCreditForm,
			BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		LetterOfCredit credit = new LetterOfCredit();

		credit.setSupplierName(letterOfCreditForm.getSupplierName());
		credit.setCustomerHeadName(letterOfCreditForm.getCustomerHeadName());
		credit.setCustomerName(letterOfCreditForm.getCustomerName());
		credit.setPoKey(letterOfCreditForm.getPoKey());
		credit.setSupplierEmail(letterOfCreditForm.getSupplierEmail());
		credit.setCustomerHeadEmail(letterOfCreditForm.getCustomerHeadEmail());
		credit.setContactPerson(letterOfCreditForm.getContactPerson());
		credit.setCustomerBranchEmail(letterOfCreditForm
				.getCustomerBranchEmail());
		credit.setAmount(letterOfCreditForm.getPayBalance());
		credit.setTransactionId(letterOfCreditForm.getTransactionId());
		credit.setTypeOfLc(letterOfCreditForm.getTypeOfLc());
		credit.setBankType(letterOfCreditForm.getBankType());
		credit.setBankName(letterOfCreditForm.getBankName());
		credit.setBankAddress(letterOfCreditForm.getBankAddress());
		credit.setBankBranch(letterOfCreditForm.getBankBranch());
		credit.setSwiftCode(letterOfCreditForm.getSwiftCode());
		credit.setAccNo(letterOfCreditForm.getAccNo());
		credit.setContactNum(letterOfCreditForm.getContactNum());
		credit.setTypeOfTrans(letterOfCreditForm.getTypeOfTrans());
		credit.setTransResult("Pending");
		credit.setTransStatus("Approved");
		credit.setTypeOfTrans(letterOfCreditForm.getTypeOfTrans());

		letterOfCreditDAO.createLetterOfCredit(credit);

		PurchaseOrder purchase = purchaseOrderDAO.getPoListByPoKey(
				letterOfCreditForm.getPoKey()).getSingleResult();

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
			letterCredit
					.setTransactionId(letterOfCreditForm.getTransactionId());
			letterCredit.setBankName(value.getBankNameCorr());
			letterCredit.setBankBranch(value.getBankBranchCorr());
			letterCredit.setBankLoc(value.getBankLocCorr());
			letterCredit.setSwiftCode(value.getSwiftCodeCorr());
			letterCredit.setAccNum(value.getAccNum());

			correspondentDAO.createCorrespondent(letterCredit);
		}

		model.put("letterOfCreditForm", letterOfCreditForm);
		model.put("user", user);

		return new ModelAndView("poPaymentBranchLcTrans", "model", model);

	}

	@RequestMapping(value = "/poPaymentBranchLcTrans", method = RequestMethod.GET)
	public ModelAndView poPaymentHeadLcTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("letterOfCreditForm", letterOfCreditForm);

		return new ModelAndView("poPaymentBranchLcTrans", "model", model);

	}

	@RequestMapping(value = "/poPaymentBranchFullList", method = RequestMethod.GET)
	public ModelAndView poPaymentBankList(ModelMap model,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> purchaseList = purchaseOrderDAO
				.getPoListByBranchName(user.getUserName()).getResultList();
		if (purchaseList != null && purchaseList.size() > 0) {

			model.put("purchaseList", purchaseList);
			return new ModelAndView("poPaymentBranchFullList");
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/poPaymentBranchClear", method = RequestMethod.GET)
	public ModelAndView poPaymentCustClear(@RequestParam Long id,
			ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(id);

		List<LetterOfCredit> letter = letterOfCreditDAO.getLCListByPoKey(
				purchase.getPoKey()).getResultList();
		if (letter != null && letter.size() > 0) {

			letterOfCreditForm.setSupplierName(letter.get(0).getSupplierName());
			letterOfCreditForm.setCustomerHeadName(letter.get(0)
					.getCustomerHeadName());
			letterOfCreditForm.setCustomerName(letter.get(0).getCustomerName());
			letterOfCreditForm.setPoKey(letter.get(0).getPoKey());
			letterOfCreditForm.setSupplierEmail(letter.get(0)
					.getSupplierEmail());
			letterOfCreditForm.setCustomerHeadEmail(letter.get(0)
					.getCustomerHeadEmail());
			letterOfCreditForm.setCustomerBranchEmail(letter.get(0)
					.getCustomerBranchEmail());
			letterOfCreditForm.setAmount(letter.get(0).getAmount());
			letterOfCreditForm.setTransactionId(letter.get(0)
					.getTransactionId());
			letterOfCreditForm.setTypeOfLc(letter.get(0).getTypeOfLc());
			letterOfCreditForm.setBankType(letter.get(0).getBankType());
			letterOfCreditForm.setBankName(letter.get(0).getBankName());
			letterOfCreditForm.setBankAddress(letter.get(0).getBankAddress());
			letterOfCreditForm.setBankBranch(letter.get(0).getBankBranch());
			letterOfCreditForm.setSwiftCode(letter.get(0).getSwiftCode());
			letterOfCreditForm.setAccNo(letter.get(0).getAccNo());
			letterOfCreditForm.setContactNum(letter.get(0).getContactNum());

			List<Correspondent> corrList = correspondentDAO
					.getCorrespondenttByPoKey(purchase.getPoKey())
					.getResultList();

			if (corrList != null && corrList.size() > 0) {
				model.put("corrList", corrList);
			}

			model.put("letterOfCreditForm", letterOfCreditForm);
		} else {
			attributes.addFlashAttribute("success",
					"Please Check Tpe Of Transfer ");
			return new ModelAndView("redirect:poPaymentBranchFullList");
		}
		model.put("user", user);
		return new ModelAndView("poPaymentBranchClear", "model", model);

	}

	@RequestMapping(value = "/customerBranchPODocumentList", method = RequestMethod.GET)
	public ModelAndView POdocumentListCustomer(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> poList = purchaseOrderDAO
				.getPoListBycustomerNameAndStatus(user.getUserName())
				.getResultList();

		if (poList != null && poList.size() > 0) {
			purchaseOrderForm.setId(poList.get(0).getId());

			model.put("poList", poList);
			model.put("purchaseOrderForm", purchaseOrderForm);
			return new ModelAndView("customerBranchPODocumentList", "model",
					model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/closePoBranchList", method = RequestMethod.GET)
	public ModelAndView closePoList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> purchaseList = purchaseOrderDAO
				.getPoPaymentCleared(user.getUserName()).getResultList();

		if (purchaseList != null && purchaseList.size() > 0) {
			model.put("purchaseList", purchaseList);

			model.put("supplierForm", supplierForm);
			return new ModelAndView("closePoBranchList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/closePoBranch", method = RequestMethod.GET)
	public String closePo(@RequestParam Long id, ModelMap model,
			RedirectAttributes attributes) {

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

		return "redirect:closePoBranchList";
	}

	@RequestMapping(value = "/cancelPoBranchList", method = RequestMethod.GET)
	public ModelAndView cancelPoList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> purchaseList = purchaseOrderDAO
				.getPoListForPayment(user.getUserName()).getResultList();

		if (purchaseList != null && purchaseList.size() > 0) {
			model.put("purchaseList", purchaseList);

			model.put("supplierForm", supplierForm);
			return new ModelAndView("cancelPoBranchList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/cancelPoBranch", method = RequestMethod.GET)
	public String cancelPo(@RequestParam Long id, ModelMap model,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(id);

		if (purchase != null) {

			purchase.setStatus("Cancel");

			purchaseOrderDAO.updatePo(purchase);

		}

		attributes.addFlashAttribute("success", "Cancelled Successfully");
		model.put("user", user);

		return "redirect:cancelPoBranchList";
	}

	@RequestMapping(value = "/closeinvoiceBranchList", method = RequestMethod.GET)
	public ModelAndView closeinvoiceList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getInvoiceListForClosing(
				user.getUserName()).getResultList();

		if (invoiceList != null && invoiceList.size() > 0) {
			model.put("invoiceList", invoiceList);

			model.put("newBuyerForm", newBuyerForm);
			return new ModelAndView("closeinvoiceBranchList", "model", model);
		}

		else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/closeInvoiceBranch", method = RequestMethod.GET)
	public String closeInvoice(@RequestParam Long id, ModelMap model,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Invoice invoice = invoiceDAO.getByInvoiceId(id);

		if (invoice != null) {

			invoice.setStatus("Closed");
			invoice.setTransStatus("Closed");
			invoice.setTransResult("Closed");
			invoice.setRequestMoney("Closed");

			invoiceDAO.updateInvoice(invoice);
		}
		attributes.addFlashAttribute("success", "Closed Successfully");
		model.put("user", user);

		return "redirect:closeinvoiceBranchList";
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@RequestMapping(value = "/disputeBranchList", method = RequestMethod.GET)
	public ModelAndView disputeHeadList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> purchaseList = purchaseOrderDAO
				.getPoBycustomerNameAndStatus(user.getUserName())
				.getResultList();

		if (purchaseList != null && purchaseList.size() > 0) {
			model.put("purchaseList", purchaseList);

			return new ModelAndView("disputeBranchList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/disputeBranch", method = RequestMethod.GET)
	public ModelAndView disputeHead(@RequestParam Long id, ModelMap model,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(id);

		disputeForm.setCustomerName(purchase.getCustomerName());
		disputeForm.setCustomerEmail(purchase.getCustomerHeadEmail());
		disputeForm.setMasterKey(purchase.getMasterKey());
		disputeForm.setPokey(purchase.getPoKey());
		disputeForm.setSupplierName(purchase.getSupplierName());
		disputeForm.setSupplierEmail(purchase.getSupplierEmail());
		disputeForm.setGoods(purchase.getGoods());
		disputeForm.setWeight(purchase.getWeight());
		disputeForm.setTotalCost(purchase.getAmount());
		disputeForm.setTransactionId(purchase.getTransactionId());
		disputeForm.setWareHousrMng(purchase.getWhMngName());

		attributes.addFlashAttribute("success", "Closed Successfully");
		model.put("user", user);
		model.put("disputeForm", disputeForm);

		return new ModelAndView("disputeBranch", "model", model);
	}

	@RequestMapping(value = "/disputeBranchConfirm", method = RequestMethod.POST)
	public ModelAndView disputeHeadConfirm(ModelMap model,
			@ModelAttribute DisputeForm disputeForm, BindingResult result) {

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
		disputeForm.setFir(disputeForm.getFir());
		disputeForm.setFirDate(disputeForm.getFirDate());
		disputeForm.setLocation(disputeForm.getLocation());
		disputeForm.setGoodsSummarry(disputeForm.getGoodsSummarry());
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

		return new ModelAndView("disputeBranchConfirm", "model", model);

	}

	@RequestMapping(value = "/disputeBranchPost", method = RequestMethod.POST)
	public ModelAndView disputeHeadPost(ModelMap model,
			@ModelAttribute DisputeForm disputeForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		disputeForm.setTransactionId(KeyGenerator.generateTransactionKey());

		Dispute dispute = new Dispute();

		dispute.setCustomerName(disputeForm.getCustomerName());
		dispute.setCustomerEmail(disputeForm.getCustomerEmail());
		dispute.setMasterKey(disputeForm.getMasterKey());
		dispute.setPoKey(disputeForm.getPokey());
		dispute.setSupplierName(disputeForm.getSupplierName());
		dispute.setSupplierEmail(disputeForm.getSupplierEmail());
		dispute.setGoods(disputeForm.getGoods());
		dispute.setTotalCost(disputeForm.getTotalCost());
		dispute.setMissingGoods(disputeForm.getMissingGoods());
		dispute.setMissingGoodsCost(disputeForm.getMissingGoodsCost());
		dispute.setMissingGoodsQty(disputeForm.getMissingGoodsQty());
		dispute.setFullyDamagedGoods(disputeForm.getFullyDamagedGoods());
		dispute.setFullyDamagedCost(disputeForm.getFullyDamagedCost());
		dispute.setFullyDamagedQty(disputeForm.getFullyDamagedQty());
		dispute.setPartiallyDamagedGoods(disputeForm.getPartiallyDamagedGoods());
		dispute.setPartiallyDamagedCost(disputeForm.getPartiallyDamagedCost());
		dispute.setPartiallyDamagedQty(disputeForm.getPartiallyDamagedQty());
		dispute.setFir(disputeForm.getFir());
		dispute.setFirDate(disputeForm.getFirDate());
		dispute.setLocation(disputeForm.getLocation());
		dispute.setGoods(disputeForm.getGoods());
		dispute.setGoodsInfo(disputeForm.getGoodsInfo());
		dispute.setTotalCost(disputeForm.getTotalCost());
		dispute.setWeight(disputeForm.getWeight());
		dispute.setAnswer(disputeForm.getAnswer());
		dispute.setGoodsDefect(disputeForm.getGoodsDefect());
		dispute.setNoOfDefect(disputeForm.getNoOfDefect());
		dispute.setAnswer1(disputeForm.getAnswer1());
		dispute.setAnswer2(disputeForm.getAnswer2());
		dispute.setDisputeKey(disputeForm.getDisputeKey());
		dispute.setWareHousrMng(disputeForm.getWareHousrMng());
		dispute.setGoodsSummarry(disputeForm.getGoodsSummarry());
		dispute.setTransactionId(disputeForm.getTransactionId());
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

		return new ModelAndView("disputeBranchTrans", "model", model);

	}

	@RequestMapping(value = "/disputeBranchTrans", method = RequestMethod.GET)
	public ModelAndView disputeHeadTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("disputeForm", disputeForm);

		return new ModelAndView("disputeBranchTrans", "model", model);

	}

	@RequestMapping(value = "/addOrModifyReportBranch", method = RequestMethod.GET)
	public ModelAndView addOrModifyReport(@RequestParam Long id,
			ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Dispute dispute = disputeDAO.getByDisputeId(id);

		PurchaseOrder purchase = purchaseOrderDAO.getPoData(dispute.getPoKey())
				.getSingleResult();

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

		return new ModelAndView("addOrModifyReportBranch", "model", model);
	}

	@RequestMapping(value = "/addOrModifyReportBranchConfirm", method = RequestMethod.POST)
	public ModelAndView addOrModifyReportConfirm(ModelMap model,
			@ModelAttribute DisputeReportsForm disputeReportsForm,
			BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		disputeReportsForm.setId(disputeReportsForm.getId());
		disputeReportsForm
				.setCustomerName(disputeReportsForm.getCustomerName());
		disputeReportsForm.setPokey(disputeReportsForm.getPokey());
		disputeReportsForm
				.setSupplierName(disputeReportsForm.getSupplierName());
		disputeReportsForm.setGoods(disputeReportsForm.getGoods());
		disputeReportsForm.setInsDetails(disputeReportsForm.getInsDetails());
		disputeReportsForm.setCost(disputeReportsForm.getCost());
		disputeReportsForm.setDisputekey(disputeReportsForm.getDisputekey());
		disputeReportsForm
				.setGoodsDetails(disputeReportsForm.getGoodsDetails());
		disputeReportsForm.setTransactionId(disputeReportsForm
				.getTransactionId());
		disputeReportsForm.setShipper(disputeReportsForm.getShipper());
		disputeReportsForm.setTransportType(disputeReportsForm
				.getModeOfTransport());
		disputeReportsForm.setModeOfTransport(disputeReportsForm
				.getModeOfTransport());
		disputeReportsForm.setTermsCond(disputeReportsForm.getTermsCond());
		disputeReportsForm.setInclusion(disputeReportsForm.getInclusion());
		disputeReportsForm.setExclusion(disputeReportsForm.getExclusion());
		disputeReportsForm
				.setSurveyorName(disputeReportsForm.getSurveyorName());
		disputeReportsForm.setSurveyorCom(disputeReportsForm.getSurveyorCom());
		disputeReportsForm.setSurveyorAdd(disputeReportsForm.getSurveyorAdd());
		disputeReportsForm.setSurveyorEmail(disputeReportsForm
				.getSurveyorEmail());
		disputeReportsForm.setSurveyorPhone(disputeReportsForm
				.getSurveyorPhone());
		disputeReportsForm.setDefectType(disputeReportsForm.getDefectType());
		disputeReportsForm.setDefectQty(disputeReportsForm.getDefectQty());
		disputeReportsForm.setDefectCostForGoods(disputeReportsForm
				.getDefectCostForGoods());
		disputeReportsForm
				.setDamageStatus(disputeReportsForm.getDamageStatus());
		disputeReportsForm.setReplacement(disputeReportsForm.getReplacement());
		disputeReportsForm.setRepairCost(disputeReportsForm.getRepairCost());

		model.put("disputeReportsForm", disputeReportsForm);
		model.put("user", user);

		return new ModelAndView("addOrModifyReportBranchConfirm", "model",
				model);

	}

	@RequestMapping(value = "/addOrModifyReportBranchPost", method = RequestMethod.POST)
	public ModelAndView addOrModifyReportPost(ModelMap model,
			@ModelAttribute DisputeReportsForm disputeReportsForm,
			BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();
		List<DisputeReports> dis = disputeReportsDAO.getDisputeReportsList(
				disputeReportsForm.getDisputekey()).getResultList();
		if (dis != null && dis.size() > 0)

		{
			DisputeReports disp = disputeReportsDAO.getDisputeReportsList(
					disputeReportsForm.getDisputekey()).getSingleResult();

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
			disp.setDefectCostForGoods(disputeReportsForm
					.getDefectCostForGoods());
			disp.setDamageStatus(disputeReportsForm.getDamageStatus());
			disp.setReplacement(disputeReportsForm.getReplacement());
			disp.setRepairCost(disputeReportsForm.getRepairCost());
			disp.setAccept("Pending");
			disp.setStatus("Pending");

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
			dispute.setDefectCostForGoods(disputeReportsForm
					.getDefectCostForGoods());
			dispute.setDamageStatus(disputeReportsForm.getDamageStatus());
			dispute.setReplacement(disputeReportsForm.getReplacement());
			dispute.setRepairCost(disputeReportsForm.getRepairCost());
			dispute.setAccept("Pending");
			dispute.setStatus("Pending");
			dispute.setSuppRepairCost(0.0f);
			dispute.setSuppDefectQty(0.0f);

			disputeReportsDAO.createDisputeReports(dispute);
		}

		Transaction trans = new Transaction();
		trans.setTransactionId(disputeReportsForm.getTransactionId());
		trans.setTransactionStatus("Dispute");
		trans.setTransactionType("Submitted Successfully");

		model.put("disputeReportsForm", disputeReportsForm);
		model.put("user", user);

		return new ModelAndView("addOrModifyReportBranchTrans", "model", model);

	}

	@RequestMapping(value = "/addOrModifyReportBranchTrans", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		return new ModelAndView("addOrModifyReportBranchTrans", "model", model);

	}

	@RequestMapping(value = "/addOrModifyReportBranchList", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<DisputeReports> disputeList = disputeReportsDAO
				.getDisputeReportsOnCustList(user.getUserName())
				.getResultList();

		if (disputeList != null && disputeList.size() > 0) {
			model.put("disputeList", disputeList);
			return new ModelAndView("addOrModifyReportBranchList", "model",
					model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/addOrModifyReportBranchView", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportView(@RequestParam Long id,
			ModelMap model, RedirectAttributes attributes) {

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
		disputeReportsForm.setDefectCostForGoods(dispute
				.getDefectCostForGoods());
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

		return new ModelAndView("addOrModifyReportBranchView", "model", model);
	}

	@RequestMapping(value = "/addOrModifyReportBranchCompare", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportCompare(@RequestParam Long id,
			ModelMap model, RedirectAttributes attributes) {

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

		Float total = dispute.getDefectQty() - dispute.getSuppDefectQty();
		Float total1 = dispute.getRepairCost() - dispute.getSuppRepairCost();

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);
		model.put("total", total);
		model.put("total1", total1);

		return new ModelAndView("addOrModifyReportBranchCompare", "model",
				model);
	}

	@RequestMapping(value = "/disputeBranchUpdateList", method = RequestMethod.GET)
	public ModelAndView disputeHeadUpdateList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Dispute> disputeList = disputeDAO.getDisputeList(
				user.getUserName()).getResultList();

		if (disputeList != null && disputeList.size() > 0) {
			model.put("disputeList", disputeList);

			return new ModelAndView("disputeBranchUpdateList", "model", model);
		}

		else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/disputeBranchUpdate", method = RequestMethod.GET)
	public ModelAndView disputeHeadUpdate(@RequestParam Long id,
			ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Dispute dispute = disputeDAO.getByDisputeId(id);

		disputeForm.setId(dispute.getId());
		disputeForm.setCustomerName(dispute.getCustomerName());
		disputeForm.setMasterKey(dispute.getDisputeKey());
		disputeForm.setPokey(dispute.getPoKey());
		disputeForm.setSupplierName(dispute.getSupplierName());
		disputeForm.setSupplierEmail(dispute.getSupplierEmail());
		disputeForm.setTransactionId(dispute.getTransactionId());

		attributes.addFlashAttribute("success", "Closed Successfully");
		model.put("user", user);
		model.put("disputeForm", disputeForm);

		return new ModelAndView("disputeBranchUpdate", "model", model);
	}

	@RequestMapping(value = "/disputeBranchUpdateConfirm", method = RequestMethod.POST)
	public ModelAndView disputeHeadUpdateConfirm(ModelMap model,
			@ModelAttribute DisputeForm disputeForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		disputeForm.setId(disputeForm.getId());
		disputeForm.setCustomerName(disputeForm.getCustomerName());
		disputeForm.setMasterKey(disputeForm.getDisputeKey());
		disputeForm.setPokey(disputeForm.getPokey());
		disputeForm.setSupplierName(disputeForm.getSupplierName());
		disputeForm.setSupplierEmail(disputeForm.getSupplierEmail());
		disputeForm.setTransactionId(disputeForm.getTransactionId());
		disputeForm.setDisputeStatus(disputeForm.getDisputeStatus());
		disputeForm.setComment(disputeForm.getComment());

		model.put("disputeForm", disputeForm);
		model.put("user", user);

		return new ModelAndView("disputeBranchUpdateConfirm", "model", model);

	}

	@RequestMapping(value = "/disputeBranchUpdatePost", method = RequestMethod.POST)
	public ModelAndView disputeHeadUpdatePost(ModelMap model,
			@ModelAttribute DisputeForm disputeForm, BindingResult result) {

		disputeForm.setTransactionId(KeyGenerator.generateTransactionKey());

		EndUser user = getCurrentLoggedUserDetails();

		Dispute dispute = disputeDAO.getByDisputeId(disputeForm.getId());

		disputeForm.setSupplierEmail(disputeForm.getSupplierEmail());
		;
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
		transcationDAOImpl.insertTransaction(trans);
		model.put("disputeForm", disputeForm);
		model.put("user", user);

		return new ModelAndView("disputeBranchUpdateTrans", "model", model);

	}

	@RequestMapping(value = "/disputeBranchUpdateTrans", method = RequestMethod.GET)
	public ModelAndView disputeHeadUpdateTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("disputeForm", disputeForm);

		return new ModelAndView("disputeBranchUpdateTrans", "model", model);

	}

	/* Same bank events for buyer side */

	@RequestMapping(value = "/newEvent", method = RequestMethod.GET)
	public ModelAndView createNewEvent(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView();

		EndUser user = getCurrentLoggedUserDetails();

		List<PurchaseOrder> purchaseOrderList = purchaseOrderDAO
				.getPoForEvents(user.getUserName()).getResultList();

		newEventForm.setPurchaseOrderList(purchaseOrderList);

		if (purchaseOrderList.size() == 0) {

			mav = new ModelAndView("noDataFoundInCusBranch", "model", model);
		} else {

			model.put("user", user);

			model.put("newEventForm", newEventForm);

			mav = new ModelAndView("branchNewEvent", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/eventForPo", method = RequestMethod.POST)
	public ModelAndView supplierSameBank(ModelMap model,
			@ModelAttribute NewEventForm newEventForm,
			HttpServletRequest request, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		List<BuyerSameBankEvent> buyerSameBankEvents = buyerSameBankeventDAO
				.findPoKey(newEventForm.getPoKey()).getResultList();

		List<BuyerDiffBankEvent> buyerDiffBankEvents = buyerDiffBankEventDAO
				.findPoKey(newEventForm.getPoKey()).getResultList();

		PurchaseOrder purchaseOrder = purchaseOrderDAO.getPoData(
				newEventForm.getPoKey()).getSingleResult();

		CustomerBankDetails customerBankDetail = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0);

		model.put("customerBankName",customerBankDetail.getBankName());

		if (buyerSameBankEvents != null && buyerSameBankEvents.size() > 0
				|| buyerDiffBankEvents != null
				&& buyerDiffBankEvents.size() > 0) {
			attributes.addFlashAttribute("success",
					"Purchase Key is already existed in events table");

			mav = new ModelAndView("redirect:newEvent");

		}

		else if (purchaseOrder.getSupplierBank().equals(customerBankDetail.getBankName())) {
			MasterPlan masterPlan = masterPlanDAO.getMasterPlanByMasterKey(purchaseOrder.getMasterKey()).getSingleResult();
			float availableCost = masterPlan.getBuyingCostSanc() - masterPlan.getUtilizedBusnsAmt();
			buyerSameBankEventForm.setAvailableCost(availableCost);
			model.put("purchaseOrder", purchaseOrder);
			model.put("user", user);
			model.put("masterPlan", masterPlan);
			model.put("buyerSameBankEventForm", buyerSameBankEventForm);

			mav = new ModelAndView("branchBuyerEventForm", "model", model);

		} else {
			MasterPlan masterPlan = masterPlanDAO.getMasterPlanByMasterKey(purchaseOrder.getMasterKey()).getSingleResult();
			float availableCost = masterPlan.getBuyingCostSanc() - masterPlan.getUtilizedBusnsAmt();
			buyerDiffBankEventForm.setAvailableCost(availableCost);
			model.put("purchaseOrder", purchaseOrder);
			model.put("user", user);
			model.put("masterPlan", masterPlan);
			model.put("buyerDiffBankEventForm", buyerDiffBankEventForm);

			mav = new ModelAndView("branchBuyerDiffEventForm", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/confirmBuyerSameBankEvent", method = RequestMethod.POST)
	public ModelAndView confirmBuyerSameBankEvent(ModelMap model,
			@ModelAttribute BuyerSameBankEventForm buyerSameBankEventForm,
			HttpServletRequest request, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("buyerSameBankEventForm", buyerSameBankEventForm);

		return new ModelAndView("branchConfirmBuyerEventForm", "model", model);

	}

	@RequestMapping(value = "/insertBSameEvents", method = RequestMethod.POST)
	public ModelAndView insertSameBankEvents(
			ModelMap model,
			@ModelAttribute(value = "buyerSameBankEventForm") BuyerSameBankEventForm buyerSameBankEventForm,
			BindingResult result, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		buyerSameBankEventForm.setTransactionId(KeyGenerator.generateTransactionKey());

		model.put("user", user);

		BuyerSameBankEvent buyerSameBankEvent = new BuyerSameBankEvent();

		Transaction transaction = new Transaction();

		buyerSameBankEvent.setCustomerName(buyerSameBankEventForm
				.getCustomerName());
		buyerSameBankEvent.setSupplier(buyerSameBankEventForm.getSupplier());
		buyerSameBankEvent.setSupplierBank(buyerSameBankEventForm
				.getSupplierBank());
		buyerSameBankEvent.setMasterKey(buyerSameBankEventForm.getMasterKey());
		buyerSameBankEvent.setTransactionId(buyerSameBankEventForm
				.getTransactionId());
		buyerSameBankEvent.setGoods(buyerSameBankEventForm.getGoods());
		buyerSameBankEvent.setSanctionedAmount(buyerSameBankEventForm
				.getSanctionedAmount());
		buyerSameBankEvent.setUtilizedAmount(buyerSameBankEventForm
				.getUtilizedAmount());
		buyerSameBankEvent.setAvailableCost(buyerSameBankEventForm
				.getAvailableCost());
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

		transcationDAOImpl.insertTransaction(transaction);

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

			purchaseDoc.setTransactionId(buyerSameBankEventForm
					.getTransactionId());

			purchaseDocDAO.createDoc(purchaseDoc);
		}

		model.put("buyerSameBankEventForm", buyerSameBankEventForm);

		return new ModelAndView("branchSavedSuccess", "model", model);
	}

	@RequestMapping(value = "/viewAllEvents", method = RequestMethod.GET)
	public ModelAndView showAllBEvents(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<BuyerSameBankEvent> buyerSameBankEvents = buyerSameBankeventDAO
				.findUpdateEvents(user.getUserName()).getResultList();

		List<BuyerSameBankEvent> buyerSameBankEvents1 = buyerSameBankeventDAO
				.findRejStatus(user.getUserName()).getResultList();

		List<BuyerSameBankEvent> buyerSameBankEvents2 = buyerSameBankeventDAO
				.findStatus(user.getUserName()).getResultList();

		ModelAndView mav = new ModelAndView();

		if (buyerSameBankEvents != null && buyerSameBankEvents.size() > 0) {
			model.put("buyerSameBankEvents", buyerSameBankEvents);

			mav = new ModelAndView("branchUpdateSameBankEvents", "model", model);
		}

		else if (buyerSameBankEvents1 != null
				&& buyerSameBankEvents1.size() > 0) {

			model.put("buyerSameBankEvents", buyerSameBankEvents1);

			mav = new ModelAndView("branchUpdateSameBankEvents", "model", model);

		} else if (buyerSameBankEvents2 != null
				&& buyerSameBankEvents2.size() > 0) {

			model.put("buyerSameBankEvents", buyerSameBankEvents2);

			mav = new ModelAndView("branchUpdateSameBankEvents", "model", model);

		}

		else {

			mav = new ModelAndView("noDataFoundInCusBranch", "model", model);

		}

		return mav;

	}

	@RequestMapping(value = "/selectUpdateSameBankEvents", method = RequestMethod.GET)
	public ModelAndView selectUpdateSameBankEvents(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		BuyerSameBankEvent buyerSameBankEvent = buyerSameBankeventDAO
				.findEvent(id);

		buyerSameBankEventForm.setId(buyerSameBankEvent.getId());
		buyerSameBankEventForm.setCustomerName(buyerSameBankEvent
				.getCustomerName());
		buyerSameBankEventForm.setSupplier(buyerSameBankEvent.getSupplier());
		buyerSameBankEventForm.setSupplierBank(buyerSameBankEvent
				.getSupplierBank());
		buyerSameBankEventForm.setMasterKey(buyerSameBankEvent.getMasterKey());
		buyerSameBankEventForm.setPoKey(buyerSameBankEvent.getPoKey());
		buyerSameBankEventForm.setGoods(buyerSameBankEvent.getGoods());
		buyerSameBankEventForm.setSanctionedAmount(buyerSameBankEvent
				.getSanctionedAmount());
		buyerSameBankEventForm.setUtilizedAmount(buyerSameBankEvent
				.getUtilizedAmount());
		buyerSameBankEventForm.setAvailableCost(buyerSameBankEvent
				.getAvailableCost());
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

		List<PurchaseDoc> purchaseDocs = purchaseDocDAO.findPoKey(
				buyerSameBankEvent.getPoKey()).getResultList();
		List<PurchaseDocForm> purchaseDocForms = new ArrayList<PurchaseDocForm>();

		for (PurchaseDoc value : purchaseDocs) {

			PurchaseDocForm purchaseDocForm = new PurchaseDocForm();
			purchaseDocForm.setPoKey(value.getPoKey());
			purchaseDocForm.setDocName(value.getDocName());

	   
       purchaseDocForm.setDescription(value.getDescription());
	  
       purchaseDocForms.add(purchaseDocForm);
	  }
	  
	 model.put("purchaseDocForms", purchaseDocForms);
	 
	 model.put("buyerSameBankEvent", buyerSameBankEvent);

			

		return new ModelAndView("branchSelectBuyerUpdateEvents", "model", model);

	}

	@RequestMapping(value = "/confirmSelectUpdateSameBankEvents", method = RequestMethod.POST)
	public ModelAndView confirmSelectUpdateSameBankEvents(ModelMap model,
			@ModelAttribute BuyerSameBankEventForm buyerSameBankEventForm,
			HttpServletRequest request, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<PurchaseDoc> purchaseDocs = purchaseDocDAO.findPoKey(
				buyerSameBankEventForm.getPoKey()).getResultList();
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

		return new ModelAndView("branchConfirmSelectUpdateSameBankEvents",
				"model", model);

	}

	@RequestMapping(value = "/selectUpdateSameBankEvents", method = RequestMethod.POST)
	public ModelAndView selectUpdateSameBankEvents(ModelMap model,
			@ModelAttribute BuyerSameBankEventForm buyerSameBankEventForm,
			BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();


		buyerSameBankEventForm.setTransactionId(KeyGenerator.generateTransactionKey());

		model.put("user", user);

		Transaction transaction = new Transaction();

		BuyerSameBankEvent buyerSameBankEvent = buyerSameBankeventDAO
				.findEvent(buyerSameBankEventForm.getId());

		buyerSameBankEvent.setId(buyerSameBankEventForm.getId());
		buyerSameBankEvent.setCustomerName(buyerSameBankEventForm
				.getCustomerName());
		buyerSameBankEvent.setSupplier(buyerSameBankEventForm.getSupplier());
		buyerSameBankEvent.setSupplierBank(buyerSameBankEventForm
				.getSupplierBank());
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

		buyerSameBankEvent.setTransactionId(buyerSameBankEventForm
				.getTransactionId());

		transaction.setTransactionId(buyerSameBankEventForm.getTransactionId());

		transaction.setTransactionType("Buyer Same Bank Events");

		transaction.setTransactionStatus("updated successfully");

		transcationDAOImpl.insertTransaction(transaction);

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

				purchaseDoc.setTransactionId(buyerSameBankEventForm
						.getTransactionId());

				purchaseDocDAO.createDoc(purchaseDoc);
			}
		}

		model.put("buyerSameBankEventForm", buyerSameBankEventForm);

		return new ModelAndView("branchUpdateSuccess", "model", model);

	}

	@RequestMapping(value = "/viewAllEvents3", method = RequestMethod.GET)
	public ModelAndView checkStatusEvents(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		List<BuyerSameBankEvent> buyerSameBankEvents = buyerSameBankeventDAO
				.findCheckStatus(user.getUserName()).getResultList();

		if (buyerSameBankEvents != null && buyerSameBankEvents.size() > 0) {

			model.put("buyerSameBankEvents", buyerSameBankEvents);

			mav = new ModelAndView("branchCheckStatusEvents", "model", model);

		} else {
			mav = new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/selectEventsForCheckStatus", method = RequestMethod.GET)
	public ModelAndView selectEventsForCheckStatus(ModelMap model,
			@RequestParam("id") Long id, HttpServletRequest request,
			RedirectAttributes attributes) throws ParseException {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		BuyerSameBankEvent buyerSameBankEvent = buyerSameBankeventDAO
				.findEvent(id);

		List<PurchaseDoc> purchaseDocs = purchaseDocDAO.findPoKey(
				buyerSameBankEvent.getPoKey()).getResultList();
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
				long tenure10 = DateService.getDaysBetweenTwoDates(date9,
						date10);

				model.put("tenure10", tenure10);
			}
		} else {
			long tenure10 = 0;
			model.put("tenure10", tenure10);
		}
		if (date11 != null) {
			if (date10 != null) {
				long tenure11 = DateService.getDaysBetweenTwoDates(date10,
						date11);

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
			bDate1 = DateService.getBuyerSameEventDate(buyerSameBankEvent,
					i - 1);
			bDate2 = DateService.getBuyerSameEventDate(buyerSameBankEvent, i);
			if (bDate1 != null && bDate2 != null) {
				int noOfDays = DateService.getDaysBetweenTwoDates(bDate1,
						bDate2);

				arrayList.add(noOfDays);
			}
		}
		Collections.sort(arrayList);

		double median = 0.0;

		if (arrayList.size() % 2 == 0) {
			median = Math
					.round((arrayList.get((arrayList.size() / 2)) + arrayList
							.get((arrayList.size() / 2 - 1))) / 2);
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

		buyerSameBankEventForm.setCustomerName(buyerSameBankEvent
				.getCustomerName());
		buyerSameBankEventForm.setSupplier(buyerSameBankEvent.getSupplier());
		buyerSameBankEventForm.setSupplierBank(buyerSameBankEvent
				.getSupplierBank());
		buyerSameBankEventForm.setMasterKey(buyerSameBankEvent.getMasterKey());
		buyerSameBankEventForm.setGoods(buyerSameBankEvent.getGoods());
		buyerSameBankEventForm.setSanctionedAmount(buyerSameBankEvent
				.getSanctionedAmount());
		buyerSameBankEventForm.setUtilizedAmount(buyerSameBankEvent
				.getUtilizedAmount());
		buyerSameBankEventForm.setAvailableCost(buyerSameBankEvent
				.getAvailableCost());
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

		return new ModelAndView("branchSelectEventsForCheckStatus", "model",
				model);

	}

	/* Buyer Different Bank Events */

	@RequestMapping(value = "/confirmBuyerDiffBankEvent", method = RequestMethod.POST)
	public ModelAndView confirmBuyerDiffBankEvent(ModelMap model,
			@ModelAttribute BuyerDiffBankEventForm buyerDiffBankEventForm,
			HttpServletRequest request, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("buyerDiffBankEventForm", buyerDiffBankEventForm);

		return new ModelAndView("branchConfirmBuyerDiffEventForm", "model",
				model);

	}

	@RequestMapping(value = "/insertBDiffEvents", method = RequestMethod.POST)
	public ModelAndView insertDiffBankEvents(ModelMap model,
			@ModelAttribute BuyerDiffBankEventForm buyerDiffBankEventForm,
			BindingResult result, RedirectAttributes attributes) {

		BuyerDiffBankEvent buyerDiffBankEvent = new BuyerDiffBankEvent();


		buyerDiffBankEventForm.setTransactionId(KeyGenerator.generateTransactionKey());

		Transaction transaction = new Transaction();

		buyerDiffBankEvent.setCustomerName(buyerDiffBankEventForm
				.getCustomerName());
		buyerDiffBankEvent.setSupplier(buyerDiffBankEventForm.getSupplier());
		buyerDiffBankEvent.setSupplierBank(buyerDiffBankEventForm
				.getSupplierBank());
		buyerDiffBankEvent.setMasterKey(buyerDiffBankEventForm.getMasterKey());
		buyerDiffBankEvent.setGoods(buyerDiffBankEventForm.getGoods());
		buyerDiffBankEvent.setSanctionedAmount(buyerDiffBankEventForm
				.getSanctionedAmount());
		buyerDiffBankEvent.setUtilizedAmount(buyerDiffBankEventForm
				.getUtilizedAmount());
		buyerDiffBankEvent.setAvailableCost(buyerDiffBankEventForm
				.getAvailableCost());
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
		buyerDiffBankEvent.setTransactionId(buyerDiffBankEventForm
				.getTransactionId());
		buyerDiffBankEvent.setPoKey(buyerDiffBankEventForm.getPoKey());

		buyerDiffBankEvent.setStatus("NA");

		transaction.setTransactionId(buyerDiffBankEventForm.getTransactionId());

		transaction.setTransactionType("Buyer Different Bank Events");

		transaction.setTransactionStatus("Details saved successfully");

		transcationDAOImpl.insertTransaction(transaction);

		buyerDiffBankEventDAO.insertDiffBankEvents(buyerDiffBankEvent);

		String allDocs = buyerDiffBankEventForm.getDocName();
		String[] docs = allDocs.split(",");

		String allDescription = buyerDiffBankEventForm.getDescription();
		String[] description = allDescription.split(",");

	buyerDiffBankEventForm.setId(buyerDiffBankEvent.getId());
	buyerDiffBankEventForm.setCustomerName(buyerDiffBankEvent
			.getCustomerName());
	buyerDiffBankEventForm.setSupplier(buyerDiffBankEvent.getSupplier());
	buyerDiffBankEventForm.setSupplierBank(buyerDiffBankEvent
			.getSupplierBank());
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
	
	model.put("buyerDiffBankEvent", buyerDiffBankEvent);

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

			purchaseDoc.setTransactionId(buyerDiffBankEventForm
					.getTransactionId());

			purchaseDocDAO.createDoc(purchaseDoc);
		}

		model.put("buyerDiffBankEventForm", buyerDiffBankEventForm);

		return new ModelAndView("branchSavedDiffSuccess", "model", model);

	}

	@RequestMapping(value = "/buyerDiffViewAllEvents", method = RequestMethod.GET)
	public ModelAndView showBuyerDiffViewAllEvents(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<BuyerDiffBankEvent> buyerDiffBankEvents = buyerDiffBankEventDAO
				.findUpdateEvents(user.getUserName()).getResultList();

		List<BuyerDiffBankEvent> buyerDiffBankEvents1 = buyerDiffBankEventDAO
				.findRejStatus(user.getUserName()).getResultList();

		List<BuyerDiffBankEvent> buyerDiffBankEvents2 = buyerDiffBankEventDAO
				.findStatus(user.getUserName()).getResultList();

		ModelAndView mav = new ModelAndView();

		if (buyerDiffBankEvents != null && buyerDiffBankEvents.size() > 0) {
			model.put("buyerDiffBankEvents", buyerDiffBankEvents);

			mav = new ModelAndView("branchUpdateDiffBankEvents", "model", model);
		}

		else if (buyerDiffBankEvents1 != null
				&& buyerDiffBankEvents1.size() > 0) {

			model.put("buyerDiffBankEvents", buyerDiffBankEvents1);

			mav = new ModelAndView("branchUpdateDiffBankEvents", "model", model);

		} else if (buyerDiffBankEvents2 != null
				&& buyerDiffBankEvents2.size() > 0) {

			model.put("buyerDiffBankEvents", buyerDiffBankEvents2);

			mav = new ModelAndView("branchUpdateDiffBankEvents", "model", model);

		}

		else {

			mav = new ModelAndView("noDataFoundInCusBranch", "model", model);

		}

		return mav;

	}

	@RequestMapping(value = "/selectUpdateDiffBankEvents", method = RequestMethod.GET)
	public ModelAndView selectUpdateSameDiffEvents(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		BuyerDiffBankEvent buyerDiffBankEvent = buyerDiffBankEventDAO
				.findEvent(id);

		List<PurchaseDoc> purchaseDocs = purchaseDocDAO.findPoKey(
				buyerDiffBankEvent.getPoKey()).getResultList();
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
		buyerDiffBankEventForm.setCustomerName(buyerDiffBankEvent
				.getCustomerName());
		buyerDiffBankEventForm.setSupplier(buyerDiffBankEvent.getSupplier());
		buyerDiffBankEventForm.setSupplierBank(buyerDiffBankEvent
				.getSupplierBank());
		buyerDiffBankEventForm.setMasterKey(buyerDiffBankEvent.getMasterKey());
		buyerDiffBankEventForm.setPoKey(buyerDiffBankEvent.getPoKey());
		buyerDiffBankEventForm.setGoods(buyerDiffBankEvent.getGoods());
		buyerDiffBankEventForm.setSanctionedAmount(buyerDiffBankEvent
				.getSanctionedAmount());
		buyerDiffBankEventForm.setUtilizedAmount(buyerDiffBankEvent
				.getUtilizedAmount());
		buyerDiffBankEventForm.setAvailableCost(buyerDiffBankEvent
				.getAvailableCost());
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

		return new ModelAndView("branchSelectBuyerDiffUpdateEvents", "model",
				model);

	}

	@RequestMapping(value = "/confirmSelectUpdateDiffBankEvents", method = RequestMethod.POST)
	public ModelAndView confirmSelectUpdateDiffBankEvents(ModelMap model,
			BuyerDiffBankEventForm buyerDiffBankEventForm,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		List<PurchaseDoc> purchaseDocs = purchaseDocDAO.findPoKey(
				buyerDiffBankEventForm.getPoKey()).getResultList();
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

		return new ModelAndView("branchConfirmSelectBuyerDiffUpdateEvents",
				"model", model);
	}

	@RequestMapping(value = "/selectUpdateDiffBankEvents", method = RequestMethod.POST)
	public ModelAndView selectUpdateSameDiffEvents(ModelMap model,
			@ModelAttribute BuyerDiffBankEventForm buyerDiffBankEventForm,
			BindingResult result, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		buyerDiffBankEventForm.setTransactionId(KeyGenerator.generateTransactionKey());

		model.put("user", user);

		BuyerDiffBankEvent buyerDiffBankEvent = buyerDiffBankEventDAO
				.findEvent(buyerDiffBankEventForm.getId());

		Transaction transaction = new Transaction();

		buyerDiffBankEvent.setId(buyerDiffBankEventForm.getId());
		buyerDiffBankEvent.setCustomerName(buyerDiffBankEventForm
				.getCustomerName());
		buyerDiffBankEvent.setSupplier(buyerDiffBankEventForm.getSupplier());
		buyerDiffBankEvent.setSupplierBank(buyerDiffBankEventForm
				.getSupplierBank());
		buyerDiffBankEvent.setMasterKey(buyerDiffBankEventForm.getMasterKey());
		buyerDiffBankEvent.setGoods(buyerDiffBankEventForm.getGoods());

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

		buyerDiffBankEvent.setTransactionId(buyerDiffBankEventForm
				.getTransactionId());

		transaction.setTransactionId(buyerDiffBankEventForm.getTransactionId());

		transaction.setTransactionType("Buyer Different Bank Events");

		transaction.setTransactionStatus("updated successfully");

		transcationDAOImpl.insertTransaction(transaction);

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

				purchaseDoc.setTransactionId(buyerDiffBankEventForm
						.getTransactionId());

				purchaseDocDAO.createDoc(purchaseDoc);
			}
		}

		model.put("buyerDiffBankEventForm", buyerDiffBankEventForm);

		return new ModelAndView("branchUpdateDiffSuccess", "model", model);

	}

	@RequestMapping(value = "/buyerDiffViewAllEvents3", method = RequestMethod.GET)
	public ModelAndView checkStatusDiffEvents(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<BuyerDiffBankEvent> buyerDiffBankEvents = buyerDiffBankEventDAO
				.findCheckStatus(user.getUserName()).getResultList();

		ModelAndView mav = new ModelAndView();

		if (buyerDiffBankEvents != null && buyerDiffBankEvents.size() > 0) {

			model.put("buyerDiffBankEvents", buyerDiffBankEvents);

			mav = new ModelAndView("branchCheckStatusDiffEvents", "model",
					model);

		} else {
			mav = new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/selectDiffEventsForCheckStatus", method = RequestMethod.GET)
	public ModelAndView selectDiffEventsForCheckStatus(ModelMap model,
			@RequestParam("id") Long id, HttpServletRequest request,
			RedirectAttributes attributes) throws ParseException {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		BuyerDiffBankEvent buyerDiffBankEvent = buyerDiffBankEventDAO
				.findEvent(id);

		List<PurchaseDoc> purchaseDocs = purchaseDocDAO.findPoKey(
				buyerDiffBankEvent.getPoKey()).getResultList();
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
				long tenure10 = DateService.getDaysBetweenTwoDates(date9,
						date10);

				model.put("tenure10", tenure10);
			}
		} else {
			long tenure10 = 0;
			model.put("tenure10", tenure10);
		}
		if (date11 != null) {
			if (date10 != null) {
				long tenure11 = DateService.getDaysBetweenTwoDates(date10,
						date11);

				model.put("tenure11", tenure11);
			}
		} else {
			long tenure11 = 0;
			model.put("tenure11", tenure11);
		}
		if (date12 != null) {
			if (date11 != null) {
				long tenure12 = DateService.getDaysBetweenTwoDates(date11,
						date12);

				model.put("tenure12", tenure12);
			}
		} else {
			long tenure12 = 0;
			model.put("tenure12", tenure12);
		}

		if (date13 != null) {
			if (date12 != null) {
				long tenure13 = DateService.getDaysBetweenTwoDates(date12,
						date13);
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
			bDate1 = DateService.getBuyerDiffEventDate(buyerDiffBankEvent,
					i - 1);
			bDate2 = DateService.getBuyerDiffEventDate(buyerDiffBankEvent, i);
			if (bDate1 != null && bDate2 != null) {
				int noOfDays = DateService.getDaysBetweenTwoDates(bDate1,
						bDate2);

				arrayList.add(noOfDays);
			}
		}
		Collections.sort(arrayList);

		double median = 0.0;

		if (arrayList.size() % 2 == 0) {
			median = Math
					.round((arrayList.get((arrayList.size() / 2)) + arrayList
							.get((arrayList.size() / 2 - 1))) / 2);
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

		buyerDiffBankEventForm.setCustomerName(buyerDiffBankEvent
				.getCustomerName());
		buyerDiffBankEventForm.setSupplier(buyerDiffBankEvent.getSupplier());
		buyerDiffBankEventForm.setSupplierBank(buyerDiffBankEvent
				.getSupplierBank());
		buyerDiffBankEventForm.setMasterKey(buyerDiffBankEvent.getMasterKey());
		buyerDiffBankEventForm.setGoods(buyerDiffBankEvent.getGoods());
		buyerDiffBankEventForm.setSanctionedAmount(buyerDiffBankEvent
				.getSanctionedAmount());
		buyerDiffBankEventForm.setUtilizedAmount(buyerDiffBankEvent
				.getUtilizedAmount());
		buyerDiffBankEventForm.setAvailableCost(buyerDiffBankEvent
				.getAvailableCost());
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

		mav = new ModelAndView("branchSelectDiffEventsForCheckStatus", "model",
				model);

		return mav;
	}

	/* Comparison For PO events */

	@RequestMapping(value = "/comparisonBranchPO", method = RequestMethod.GET)
	public ModelAndView comparisionCustomerBranch(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		List<BuyerSameBankEvent> buyerSameBankEvents = buyerSameBankeventDAO
				.findCheckStatus(user.getUserName()).getResultList();
		List<BuyerDiffBankEvent> buyerDiffBankEvents = buyerDiffBankEventDAO
				.findCheckStatus(user.getUserName()).getResultList();

		newEventForm.setBuyerSameBankEvents(buyerSameBankEvents);
		newEventForm.setBuyerDiffBankEvents(buyerDiffBankEvents);

		if (buyerSameBankEvents.size() == 0 && buyerDiffBankEvents.size() == 0) {

			mav = new ModelAndView("noDataFoundInCusBranch", "model", model);
		} else {

			model.put("user", user);

			model.put("newEventForm", newEventForm);

			mav = new ModelAndView("comparisonBranchPO", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/comparisonData", method = RequestMethod.POST)
	public ModelAndView comparisionCustomerBranch(ModelMap model,
			@ModelAttribute NewEventForm newEventForm,
			RedirectAttributes attributes) throws ParseException {

		String mav = "";

		List<ComparisonForm> comparisonList = new ArrayList<ComparisonForm>();

		PurchaseOrder purchaseOrder = purchaseOrderDAO.getPoListByPoKey(
				newEventForm.getPoKey()).getSingleResult();

		List<BuyerSameBankEvent> buyerSameBankEvents = buyerSameBankeventDAO
				.findPoKey(newEventForm.getPoKey()).getResultList();

		List<PurchaseDoc> purchaseDocForms = purchaseDocDAO.findPoKey(
				newEventForm.getPoKey()).getResultList();

		List<PoUpload> poUploadForms = poUploadDAO
				.getPoUploadByPOKey(newEventForm.getPoKey());

		if (buyerSameBankEvents != null && buyerSameBankEvents.size() > 0) {

			BuyerSameBankEvent buyerSameBankEvent = buyerSameBankEvents.get(0);

			buyerSameBankEventForm = eventsService
					.populateBuyerSameBankFormBasedOnEvents(buyerSameBankEvent,
							purchaseOrder);

			/* If there are any Disputes */

			List<Dispute> disputesList = disputeDAO.getPoKey(
					newEventForm.getPoKey()).getResultList();

			if (disputesList != null && disputesList.size() > 0) {
				comparisonList = eventsService.populateFormBasedOnDisputes(
						disputesList, buyerSameBankEvent);
			}

			/* FeedBack Module */

			buyerSameBankEventForm = eventsService.generateFeedback(
					buyerSameBankEventForm, buyerSameBankEvent, purchaseOrder);

			model.put("buyerSameBankEvent", buyerSameBankEvent);

			model.put("buyerSameBankEventForm", buyerSameBankEventForm);

			mav = "comparisonBranchDataForSame";

		} else {
			List<BuyerDiffBankEvent> buyerDiffBankEventList = buyerDiffBankEventDAO
					.findPoKey(newEventForm.getPoKey()).getResultList();
			if (buyerDiffBankEventList != null
					&& buyerDiffBankEventList.size() > 0) {
				BuyerDiffBankEvent buyerDiffBankEvent = buyerDiffBankEventList
						.get(0);

				buyerDiffBankEventForm = eventsService
						.populateBuyerDiffBankFormBasedOnEvents(
								buyerDiffBankEvent, purchaseOrder);

				/* If there are any Disputes */

				List<Dispute> disputesList = disputeDAO.getPoKey(
						newEventForm.getPoKey()).getResultList();

				if (disputesList != null && disputesList.size() > 0) {
					comparisonList = eventsService
							.populateDiffBankFormBasedOnDisputes(disputesList,
									buyerDiffBankEvent);
				}

				/* FeedBack Module */

				buyerDiffBankEventForm = eventsService
						.generateDiffBankFeedback(buyerDiffBankEventForm,
								buyerDiffBankEvent, purchaseOrder);

				model.put("buyerDiffBankEvent", buyerDiffBankEvent);

				model.put("buyerDiffBankEventForm", buyerDiffBankEventForm);

			}
			mav = "comparisonBranchDataForDiff";
		}

		model.put("poUploadForms", poUploadForms);

		model.put("purchaseDocForms", purchaseDocForms);

		model.put("purchaseOrders", purchaseOrder);

		model.put("comparisonList", comparisonList);

		return new ModelAndView(mav, "model", model);

	}

	/* warehouse */

	@RequestMapping(value = "/createBranchWareHouse", method = RequestMethod.GET)
	public ModelAndView createClientAdmin(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouse> whList = wareHouseDAO.getByPending(user.getUserName())
				.getResultList();

		wareHouseForm.setCustomerName(user.getUserName());
		List<WareHouseMng> wahList = wareHouseMngDAO.getWareHouseMngFullList(user.getUserName()).getResultList();
		if(wahList!=null && wahList.size()>0){
			wareHouseForm.setWhList(wahList);
		}

		model.put("user", user);
		model.put("wareHouseForm", wareHouseForm);
		model.put("whList", whList);

		return new ModelAndView("createBranchWareHouse", "model", model);

	}

	/* Same bank events for Seller side */

	@RequestMapping(value = "/selectBranchWareHouse", method = RequestMethod.GET)
	public ModelAndView selectClientAdmin(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

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

		return new ModelAndView("selectBranchWareHouse", "model", model);
	}

	@RequestMapping(value = "/sellerNewEvent", method = RequestMethod.GET)
	public ModelAndView createSellerNewEvent(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<Invoice> invoiceList = invoiceDAO.getInvoiceForEvents(
				user.getUserName()).getResultList();

		newEventForm.setInvoiceList(invoiceList);

		if (invoiceList.size() == 0) {

			mav = new ModelAndView("noDataFoundInCusBranch", "model", model);
		} else {

			model.put("newEventForm", newEventForm);

			mav = new ModelAndView("branchNewSellerSameEvent", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/updateBranchWareHouseConfirm", method = RequestMethod.POST)
	public ModelAndView updateCustWareHouseConfirm(
			@ModelAttribute WareHouseForm wareHouseForm, ModelMap model,
			RedirectAttributes attribute) {
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

		return new ModelAndView("updateBranchWareHouseConfirm", "model", model);

	}

	@RequestMapping(value = "/updateBranchWareHouse", method = RequestMethod.POST)
	public ModelAndView updateCustWareHouse(ModelMap model,
			@ModelAttribute WareHouseForm wareHouseForm,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		WareHouse wareHouse = wareHouseDAO.getByWareHouseId(wareHouseForm
				.getId());

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

		return new ModelAndView("redirect:createBranchWareHouse");

	}

	@RequestMapping(value = "/branchWareHouseConfirm", method = RequestMethod.POST)
	public ModelAndView clientAppMngConfirm(
			@ModelAttribute WareHouseForm wareHouseForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouse> head = wareHouseDAO.getWareHouseList(
				wareHouseForm.getWareHouseName()).getResultList();

		if (head.size() != 0) {
			attributes.addFlashAttribute("success", "WareHouse Already Exists");

			return new ModelAndView("redirect:createBranchWareHouse");
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

			return new ModelAndView("branchWareHouseConfirm", "model", model);
		}
	}

	@RequestMapping(value = "/branchWareHouseSave", method = RequestMethod.POST)
	public ModelAndView custWareHouseSave(
			@ModelAttribute WareHouseForm wareHouseForm, ModelMap model,
			RedirectAttributes attributes) {

		wareHouseForm.setTransactionId(KeyGenerator.generateTransactionKey());

		EndUser user = getCurrentLoggedUserDetails();

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
		transcationDAOImpl.insertTransaction(trans);

		model.put("wareHouseForm", wareHouseForm);
		model.put("user", user);

		return new ModelAndView("branchWareHouseTransaction", "model", model);

	}

	@RequestMapping(value = "/branchWareHouseTransaction", method = RequestMethod.GET)
	public ModelAndView custWareHouseTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("wareHouseForm", wareHouseForm);

		return new ModelAndView("branchWareHouseTransaction", "model", model);

	}

	@RequestMapping(value = "/branchWareHouseFullList", method = RequestMethod.GET)
	public ModelAndView custWareHouseFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouse> whList = wareHouseDAO.getWareHouseFullList(
				user.getUserName()).getResultList();

		model.put("user", user);
		if (whList != null && whList.size() > 0) {
			model.put("whList", whList);

			return new ModelAndView("branchWareHouseFullList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/branchWareHouseReceiveList", method = RequestMethod.GET)
	public ModelAndView apprCustAppMngList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> purchaseList = purchaseOrderDAO
				.getPoListGoodsReceive(user.getUserName()).getResultList();

		if (purchaseList != null && purchaseList.size() > 0) {

			model.put("purchaseList", purchaseList);
			return new ModelAndView("branchWareHouseReceiveList", "model",
					model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/branchWareHouseReceive", method = RequestMethod.GET)
	public ModelAndView invoiceVendorPayCleared(@RequestParam Long id,
			ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder po = purchaseOrderDAO.getByPurchaseId(id);

		po.setGoodsStatus("Received");

		Date receive = DateService.loginDate;
		po.setReceiveDate(receive);
		purchaseOrderDAO.updatePo(po);

		model.put("user", user);

		attributes.addFlashAttribute("success", "Updated Successfully");
		return new ModelAndView("redirect:branchWareHouseReceiveList");

	}

	@RequestMapping(value = "/branchWareHouseInvoiceList", method = RequestMethod.GET)
	public ModelAndView custWareHouseInvoiceList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getInvoiceListForGoods(
				user.getUserName()).getResultList();

		if (invoiceList != null && invoiceList.size() > 0) {

			model.put("invoiceList", invoiceList);
			return new ModelAndView("branchWareHouseInvoiceList", "model",
					model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/branchWareHouseInvoiceSend", method = RequestMethod.GET)
	public ModelAndView custWareHouseInvoiceSend(@RequestParam Long id,
			ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Invoice invoice = invoiceDAO.getByInvoiceId(id);

		invoice.setGoodsStatus("Sent");

		Date sent = DateService.loginDate;
		invoice.setSentDate(sent);

		invoiceDAO.updateInvoice(invoice);

		model.put("user", user);

		attributes.addFlashAttribute("success", "Updated Successfully");
		return new ModelAndView("redirect:branchWareHouseInvoiceList");

	}

	@RequestMapping(value = "/eventsForInvoice", method = RequestMethod.POST)
	public ModelAndView eventsForInvoice(ModelMap model,
			@ModelAttribute NewEventForm newEventForm,
			HttpServletRequest request, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		List<SellerSameBankEvent> sellerSameBankEvents = sellerSameBankEventDAO
				.findInvoiceKey(newEventForm.getInvoiceKey()).getResultList();

		List<SellerDiffBankEvent> sellerDiffBankEvents = sellerDiffBankEventDAO
				.findInvoiceKey(newEventForm.getInvoiceKey()).getResultList();

		Invoice invoice = invoiceDAO.getInvoiceData(
				newEventForm.getInvoiceKey()).getSingleResult();

		CustomerBankDetails customerBankDetail = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0);

		if (sellerSameBankEvents != null && sellerSameBankEvents.size() > 0
				|| sellerDiffBankEvents != null
				&& sellerDiffBankEvents.size() > 0) {
			attributes.addFlashAttribute("success",
					"Invoice Key is already existed in events table");

			mav = new ModelAndView("redirect:sellerNewEvent");

		}

		else if (invoice.getBuyerBank().equals(customerBankDetail.getBankName())) {
			model.put("invoice", invoice);

			model.put("sellerSameBankEventForm", sellerSameBankEventForm);

			mav = new ModelAndView("branchSellerSameBankEvent", "model", model);

		} else {
			model.put("invoice", invoice);

			model.put("sellerDiffBankEventForm", sellerDiffBankEventForm);

			mav = new ModelAndView("branchSellerDiffBankEvent", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/confirmSellerSameBankEvent", method = RequestMethod.POST)
	public ModelAndView confirmSellerSameBankEvent(ModelMap model,
			@ModelAttribute SellerSameBankEventForm sellerSameBankEventForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("sellerSameBankEventForm", sellerSameBankEventForm);

		return new ModelAndView("branchConfirmSellerSameBankEvent", "model",
				model);

	}

	@RequestMapping(value = "/insertSellerSameEvents", method = RequestMethod.POST)
	public ModelAndView insertSameBankEvents(ModelMap model,
			@ModelAttribute SellerSameBankEventForm sellerSameBankEventForm,
			BindingResult result, RedirectAttributes attributes) {


		sellerSameBankEventForm.setTransactionId(KeyGenerator.generateTransactionKey());

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		SellerSameBankEvent sellerSameBankEvent = new SellerSameBankEvent();

		Transaction transaction = new Transaction();

		sellerSameBankEvent.setCustomerName(sellerSameBankEventForm
				.getCustomerName());
		sellerSameBankEvent.setBuyer(sellerSameBankEventForm.getBuyer());
		sellerSameBankEvent
				.setBuyerBank(sellerSameBankEventForm.getBuyerBank());
		sellerSameBankEvent
				.setMasterKey(sellerSameBankEventForm.getMasterKey());
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
		sellerSameBankEvent.setTransactionId(sellerSameBankEventForm
				.getTransactionId());
		sellerSameBankEvent.setInvoiceKey(sellerSameBankEventForm
				.getInvoiceKey());

		sellerSameBankEvent.setStatus("NA");

		transaction
				.setTransactionId(sellerSameBankEventForm.getTransactionId());

		transaction.setTransactionType("Seller Same Bank Events");

		transaction.setTransactionStatus("Details saved successfully");

		transcationDAOImpl.insertTransaction(transaction);

		sellerSameBankEventDAO.insertSameBankEvents(sellerSameBankEvent);

		String allDocs = sellerSameBankEventForm.getDocName();
		String[] docs = allDocs.split(",");

		String allDescription = sellerSameBankEventForm.getDescription();
		String[] description = allDescription.split(",");

		List<InvoiceDocForm> invoiceDocForms = new ArrayList<InvoiceDocForm>();
		for (int count = 0; count < docs.length; count++) {

			for (InvoiceDocForm value : invoiceDocForms) {
		   InvoiceDocForm invoiceDocForm = new InvoiceDocForm();
		   invoiceDocForm.setInvoiceKey(value.getInvoiceKey());
		   invoiceDocForm.setDocName(value.getDocName());
		   
		   invoiceDocForm.setDescription(value.getDescription());
		  
		   invoiceDocForms.add(invoiceDocForm);
		  }
		  
		}
		 model.put("invoiceDocForms", invoiceDocForms);
	
	
	sellerSameBankEventForm.setId(sellerSameBankEvent.getId());
	sellerSameBankEventForm.setCustomerName(sellerSameBankEvent
			.getCustomerName());
	sellerSameBankEventForm.setBuyer(sellerSameBankEvent.getBuyer());
	sellerSameBankEventForm
			.setBuyerBank(sellerSameBankEvent.getBuyerBank());
	sellerSameBankEventForm
			.setMasterKey(sellerSameBankEvent.getMasterKey());
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
	
	model.put("sellerSameBankEvent", sellerSameBankEvent);

		for (InvoiceDocForm value : invoiceDocForms) {

			InvoiceDoc invoiceDoc = new InvoiceDoc();

			invoiceDoc.setInvoiceKey(value.getInvoiceKey());

			invoiceDoc.setDocName(value.getDocName());

			invoiceDoc.setDescription(value.getDescription());

			invoiceDoc.setTransactionId(sellerSameBankEventForm
					.getTransactionId());

			invoiceDocDAO.createDoc(invoiceDoc);
		}

		model.put("sellerSameBankEventForm", sellerSameBankEventForm);

		return new ModelAndView("branchSendSellerSameSuccess", "model", model);

	}

	@RequestMapping(value = "/sellerSameViewAllEvents", method = RequestMethod.GET)
	public ModelAndView showSellerSameViewAllEvents(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<SellerSameBankEvent> sellerSameBankEvents = sellerSameBankEventDAO
				.findUpdateEvents(user.getUserName()).getResultList();

		List<SellerSameBankEvent> sellerSameBankEvents1 = sellerSameBankEventDAO
				.findRejStatus(user.getUserName()).getResultList();

		List<SellerSameBankEvent> sellerSameBankEvents2 = sellerSameBankEventDAO
				.findStatus(user.getUserName()).getResultList();

		ModelAndView mav = new ModelAndView();

		if (sellerSameBankEvents != null && sellerSameBankEvents.size() > 0) {
			model.put("sellerSameBankEvents", sellerSameBankEvents);

			mav = new ModelAndView("branchUpdateSellerSameBankEvents", "model",
					model);
		}

		else if (sellerSameBankEvents1 != null
				&& sellerSameBankEvents1.size() > 0) {

			model.put("sellerSameBankEvents", sellerSameBankEvents1);

			mav = new ModelAndView("branchUpdateSellerSameBankEvents", "model",
					model);

		} else if (sellerSameBankEvents2 != null
				&& sellerSameBankEvents2.size() > 0) {

			model.put("sellerSameBankEvents", sellerSameBankEvents2);

			mav = new ModelAndView("branchUpdateSellerSameBankEvents", "model",
					model);

		}

		else {

			mav = new ModelAndView("noDataFoundInCusBranch", "model", model);

		}

		return mav;

	}

	@RequestMapping(value = "/selectUpdateSellerSameBankEvents", method = RequestMethod.GET)
	public ModelAndView selectUpdateSellerSameBankEvents(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		SellerSameBankEvent sellerSameBankEvent = sellerSameBankEventDAO
				.findEvent(id);
		
		 List<InvoiceDoc> invoiceDocs = invoiceDocDAO.findPoKey(sellerSameBankEvent.getInvoiceKey()).getResultList();
			List<InvoiceDocForm> invoiceDocForms = new ArrayList<InvoiceDocForm>();
			 
			 for (InvoiceDoc value : invoiceDocs){

			   InvoiceDocForm invoiceDocForm = new InvoiceDocForm();
			   invoiceDocForm.setInvoiceKey(value.getInvoiceKey());
			   invoiceDocForm.setDocName(value.getDocName());
			   
			   invoiceDocForm.setDescription(value.getDescription());
			  
			   invoiceDocForms.add(invoiceDocForm);
			  }
			  
			 model.put("invoiceDocForms", invoiceDocForms);
		
		
		sellerSameBankEventForm.setId(sellerSameBankEvent.getId());
		sellerSameBankEventForm.setCustomerName(sellerSameBankEvent
				.getCustomerName());
		sellerSameBankEventForm.setBuyer(sellerSameBankEvent.getBuyer());
		sellerSameBankEventForm
				.setBuyerBank(sellerSameBankEvent.getBuyerBank());
		sellerSameBankEventForm
				.setMasterKey(sellerSameBankEvent.getMasterKey());
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
		
		model.put("sellerSameBankEvent", sellerSameBankEvent);

		model.put("sellerSameBankEventForm", sellerSameBankEventForm);

		return new ModelAndView("branchSelectSellerSameUpdateEvents", "model", model);

	}

	@RequestMapping(value = "/confirmSelectUpdateSellerSameBankEvents", method = RequestMethod.POST)
	public ModelAndView confirmSelectUpdateSellerSameBankEvents(ModelMap model,
			@ModelAttribute SellerSameBankEventForm sellerSameBankEventForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		
		 List<InvoiceDoc> invoiceDocs = invoiceDocDAO.findPoKey(sellerSameBankEventForm.getInvoiceKey()).getResultList();
			List<InvoiceDocForm> invoiceDocForms = new ArrayList<InvoiceDocForm>();
			 
			 for (InvoiceDoc value : invoiceDocs){

			   InvoiceDocForm invoiceDocForm = new InvoiceDocForm();
			   invoiceDocForm.setInvoiceKey(value.getInvoiceKey());
			   invoiceDocForm.setDocName(value.getDocName());
			   
			   invoiceDocForm.setDescription(value.getDescription());
			  
			   invoiceDocForms.add(invoiceDocForm);
			  }
			  
			 model.put("invoiceDocForms", invoiceDocForms);

		model.put("sellerSameBankEventForm", sellerSameBankEventForm);

		return new ModelAndView("branchConfirmSelectSellerSameUpdateEvents", "model",
				model);

	}

	@RequestMapping(value = "/selectUpdateSellerSameBankEvents", method = RequestMethod.POST)
	public ModelAndView selectUpdateSellerSameBankEvents(ModelMap model,
			@ModelAttribute SellerSameBankEventForm sellerSameBankEventForm,
			BindingResult result, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		sellerSameBankEventForm.setTransactionId(KeyGenerator.generateTransactionKey());
		 model.put("user", user);

		SellerSameBankEvent sellerSameBankEvent = sellerSameBankEventDAO
				.findEvent(sellerSameBankEventForm.getId());

		Transaction transaction = new Transaction();

		sellerSameBankEvent.setId(sellerSameBankEventForm.getId());
		sellerSameBankEvent.setCustomerName(sellerSameBankEventForm
				.getCustomerName());
		sellerSameBankEvent.setBuyer(sellerSameBankEventForm.getBuyer());
		sellerSameBankEvent
				.setBuyerBank(sellerSameBankEventForm.getBuyerBank());
		sellerSameBankEvent
				.setMasterKey(sellerSameBankEventForm.getMasterKey());
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

		sellerSameBankEvent.setTransactionId(sellerSameBankEventForm
				.getTransactionId());

		transaction
				.setTransactionId(sellerSameBankEventForm.getTransactionId());

		transaction.setTransactionType("Seller Same Bank Events");

		transaction.setTransactionStatus("updated successfully");

		transcationDAOImpl.insertTransaction(transaction);

		sellerSameBankEventDAO.updateSellerSameBankEvent(sellerSameBankEvent);
		
		
		if(sellerSameBankEventForm.getDocName().equals(""))
		{}else
		{
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

		return new ModelAndView("branchUpdateSellerSameSuccess", "model", model);

	}
	@RequestMapping(value="/sellerSameViewAllEvents3", method = RequestMethod.GET)
	public ModelAndView checkStatusSellerSameEvents(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		
	    EndUser user = getCurrentLoggedUserDetails();
		
		model.put("user", user);
		
		ModelAndView mav = new ModelAndView();
		
		List<SellerSameBankEvent> sellerSameBankEvents = sellerSameBankEventDAO
				.findCheckStatus(user.getUserName()).getResultList();

		

		if (sellerSameBankEvents!=null && sellerSameBankEvents.size()>0) {
			
			model.put("sellerSameBankEvents", sellerSameBankEvents);

			mav = new ModelAndView("branchCheckStatusSellerSameEvents", "model", model);
			
		}
		else {
			mav = new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

		return mav;
		
	}

	@RequestMapping(value = "/selectSellerSameEventsForCheckStatus", method = RequestMethod.GET)
	public ModelAndView selectSellerSameEventsForCheckStatus(ModelMap model,
			@RequestParam("id") Long id, HttpServletRequest request,
			RedirectAttributes attributes) throws ParseException {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		SellerSameBankEvent sellerSameBankEvent = sellerSameBankEventDAO
				.findEvent(id);

		List<InvoiceDoc> invoiceDocs = invoiceDocDAO.findPoKey(
				sellerSameBankEvent.getInvoiceKey()).getResultList();
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
				long tenure10 = DateService.getDaysBetweenTwoDates(date9,
						date10);

				model.put("tenure10", tenure10);
			}
		} else {
			long tenure10 = 0;
			model.put("tenure10", tenure10);
		}
		if (date11 != null) {
			if (date10 != null) {
				long tenure11 = DateService.getDaysBetweenTwoDates(date10,
						date11);

				model.put("tenure11", tenure11);
			}
		} else {
			long tenure11 = 0;
			model.put("tenure11", tenure11);
		}
		if (date12 != null) {
			if (date11 != null) {
				long tenure12 = DateService.getDaysBetweenTwoDates(date11,
						date12);

				model.put("tenure12", tenure12);
			}
		} else {
			long tenure12 = 0;
			model.put("tenure12", tenure12);
		}

		if (date13 != null) {
			if (date12 != null) {
				long tenure13 = DateService.getDaysBetweenTwoDates(date12,
						date13);
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
			bDate1 = DateService.getSellerSameEventDate(sellerSameBankEvent,
					i - 1);
			bDate2 = DateService.getSellerSameEventDate(sellerSameBankEvent, i);
			if (bDate1 != null && bDate2 != null) {
				int noOfDays = DateService.getDaysBetweenTwoDates(bDate1,
						bDate2);

				arrayList.add(noOfDays);
			}
		}
		Collections.sort(arrayList);

		double median = 0.0;

		if (arrayList.size() % 2 == 0) {
			median = Math
					.round((arrayList.get((arrayList.size() / 2)) + arrayList
							.get((arrayList.size() / 2 - 1))) / 2);
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

		sellerSameBankEventForm.setCustomerName(sellerSameBankEvent
				.getCustomerName());
		sellerSameBankEventForm.setBuyer(sellerSameBankEvent.getBuyer());
		sellerSameBankEventForm
				.setBuyerBank(sellerSameBankEvent.getBuyerBank());
		sellerSameBankEventForm
				.setMasterKey(sellerSameBankEvent.getMasterKey());
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

		return new ModelAndView("branchSelectSellerSameEventsForCheckStatus",
				"model", model);

	}

	/* Different bank events for Seller side */

	@RequestMapping(value = "/confirmSellerDiffBankEvent", method = RequestMethod.POST)
	public ModelAndView confirmSellerDiffBankEvent(ModelMap model,
			@ModelAttribute SellerDiffBankEventForm sellerDiffBankEventForm,
			HttpServletRequest request, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("sellerDiffBankEventForm", sellerDiffBankEventForm);

		return new ModelAndView("branchConfirmSellerDiffBankEvent", "model",
				model);

	}

	@RequestMapping(value = "/insertSellerDiffEvents", method = RequestMethod.POST)
	public ModelAndView insertDiffBankEvents(ModelMap model,
			@ModelAttribute SellerDiffBankEventForm sellerDiffBankEventForm,
			BindingResult result, RedirectAttributes attributes) {

		SellerDiffBankEvent sellerDiffBankEvent = new SellerDiffBankEvent();

		sellerDiffBankEventForm.setTransactionId(KeyGenerator.generateTransactionKey());

		Transaction transaction = new Transaction();

		sellerDiffBankEvent.setCustomerName(sellerDiffBankEventForm
				.getCustomerName());
		sellerDiffBankEvent.setBuyer(sellerDiffBankEventForm.getBuyer());
		sellerDiffBankEvent
				.setBuyerBank(sellerDiffBankEventForm.getBuyerBank());
		sellerDiffBankEvent
				.setMasterKey(sellerDiffBankEventForm.getMasterKey());
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

		sellerDiffBankEvent.setTransactionId(sellerDiffBankEventForm
				.getTransactionId());

		sellerDiffBankEvent.setInvoiceKey(sellerDiffBankEventForm
				.getInvoiceKey());

		sellerDiffBankEventDAO.insertDiffBankEvents(sellerDiffBankEvent);
		transaction
				.setTransactionId(sellerDiffBankEventForm.getTransactionId());

		transaction.setTransactionType("Seller Different Bank Events");

		transaction.setTransactionStatus("Details saved successfully");

		transcationDAOImpl.insertTransaction(transaction);
		
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

		return new ModelAndView("branchSendSellerDiffSuccess", "model", model);

	}


	@RequestMapping(value="/sellerDiffViewAllEvents", method = RequestMethod.GET)
	public ModelAndView showSellerDiffViewAllEvents(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		
	    EndUser user = getCurrentLoggedUserDetails();
		
		model.put("user", user);
		
		List<SellerDiffBankEvent> sellerDiffBankEvents = sellerDiffBankEventDAO.findUpdateEvents(user.getUserName()).getResultList();
		
		List<SellerDiffBankEvent> sellerDiffBankEvents1 = sellerDiffBankEventDAO.findRejStatus(user.getUserName()).getResultList();
		
		List<SellerDiffBankEvent> sellerDiffBankEvents2 = sellerDiffBankEventDAO.findStatus(user.getUserName()).getResultList();

		ModelAndView mav = new ModelAndView();
		
		if(sellerDiffBankEvents !=null && sellerDiffBankEvents.size()>0 )
		{
			model.put("sellerDiffBankEvents", sellerDiffBankEvents);

			mav = new ModelAndView("branchUpdateSellerDiffBankEvents", "model", model);
		}

		else if (sellerDiffBankEvents1 !=null && sellerDiffBankEvents1.size()>0  ) {
			
			model.put("sellerDiffBankEvents", sellerDiffBankEvents1);

			mav = new ModelAndView("branchUpdateSellerDiffBankEvents", "model", model);
			
		}
	    else if (sellerDiffBankEvents2 !=null && sellerDiffBankEvents2.size()>0  ) {
			
			model.put("sellerDiffBankEvents", sellerDiffBankEvents2);

			mav = new ModelAndView("branchUpdateSellerDiffBankEvents", "model", model);
			
		}  
		
		else {

			mav = new ModelAndView("noDataFoundInCusBranch", "model", model);
			
		}
		
		return mav;
		
	}

	@RequestMapping(value = "/selectUpdateSellerDiffBankEvents", method = RequestMethod.GET)
	public ModelAndView selectUpdateSellerDiffBankEvents(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		SellerDiffBankEvent sellerDiffBankEvent = sellerDiffBankEventDAO
				.findEvent(id);

		List<InvoiceDoc> invoiceDocs = invoiceDocDAO.findPoKey(
				sellerDiffBankEvent.getInvoiceKey()).getResultList();
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
		sellerDiffBankEventForm.setCustomerName(sellerDiffBankEvent
				.getCustomerName());
		sellerDiffBankEventForm.setBuyer(sellerDiffBankEvent.getBuyer());
		sellerDiffBankEventForm
				.setBuyerBank(sellerDiffBankEvent.getBuyerBank());
		sellerDiffBankEventForm
				.setMasterKey(sellerDiffBankEvent.getMasterKey());

		sellerDiffBankEventForm.setInvoiceKey(sellerDiffBankEvent
				.getInvoiceKey());
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

		return new ModelAndView("branchSelectSellerDiffUpdateEvents", "model",
				model);

	}

	@RequestMapping(value = "/confirmSelectUpdateSellerDiffBankEvents", method = RequestMethod.POST)
	public ModelAndView confirmSelectUpdateSellerDiffBankEvents(ModelMap model,
			@ModelAttribute SellerDiffBankEventForm sellerDiffBankEventForm,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		List<InvoiceDoc> invoiceDocs = invoiceDocDAO.findPoKey(
				sellerDiffBankEventForm.getInvoiceKey()).getResultList();
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

		return new ModelAndView(
				"branchConfirmSelectUpdateSellerDiffBankEvents", "model", model);

	}

	@RequestMapping(value = "/selectUpdateSellerDiffBankEvents", method = RequestMethod.POST)
	public ModelAndView selectUpdateSellerDiffBankEvents(ModelMap model,
			@ModelAttribute SellerDiffBankEventForm sellerDiffBankEventForm,
			BindingResult result, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		sellerDiffBankEventForm.setTransactionId(KeyGenerator.generateTransactionKey());

		model.put("user", user);

		SellerDiffBankEvent sellerDiffBankEvent = sellerDiffBankEventDAO
				.findEvent(sellerDiffBankEventForm.getId());

		Transaction transaction = new Transaction();

		sellerDiffBankEvent.setId(sellerDiffBankEventForm.getId());
		sellerDiffBankEvent.setCustomerName(sellerDiffBankEventForm
				.getCustomerName());
		sellerDiffBankEvent.setBuyer(sellerDiffBankEventForm.getBuyer());
		sellerDiffBankEvent
				.setBuyerBank(sellerDiffBankEventForm.getBuyerBank());
		sellerDiffBankEvent
				.setMasterKey(sellerDiffBankEventForm.getMasterKey());
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

		sellerDiffBankEvent.setTransactionId(sellerDiffBankEventForm
				.getTransactionId());

		transaction
				.setTransactionId(sellerDiffBankEventForm.getTransactionId());

		transaction.setTransactionType("Seller Different Bank Events");

		transaction.setTransactionStatus("updated successfully");

		transcationDAOImpl.insertTransaction(transaction);

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
				invoiceDocForm.setInvoiceKey(sellerDiffBankEventForm
						.getInvoiceKey());
				invoiceDocForm.setDocName(docs[count]);

				invoiceDocForm.setDescription(description[count]);

				invoiceDocForms.add(invoiceDocForm);
			}
			for (InvoiceDocForm value : invoiceDocForms) {

				InvoiceDoc invoiceDoc = new InvoiceDoc();

				invoiceDoc.setInvoiceKey(value.getInvoiceKey());

				invoiceDoc.setDocName(value.getDocName());

				invoiceDoc.setDescription(value.getDescription());

				invoiceDoc.setTransactionId(sellerDiffBankEventForm
						.getTransactionId());

				invoiceDocDAO.createDoc(invoiceDoc);
			}
		}

		model.put("sellerDiffBankEventForm", sellerDiffBankEventForm);

		return new ModelAndView("branchUpdateSellerDiffSuccess", "model", model);
	}

	@RequestMapping(value = "/sellerDiffViewAllEvents3", method = RequestMethod.GET)
	public ModelAndView checkStatusSellerDiffEvents(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		List<SellerDiffBankEvent> sellerDiffBankEvents = sellerDiffBankEventDAO
				.findCheckStatus(user.getUserName()).getResultList();

		if (sellerDiffBankEvents != null && sellerDiffBankEvents.size() > 0) {

			model.put("sellerDiffBankEvents", sellerDiffBankEvents);

			mav = new ModelAndView("branchCheckStatusSellerDiffEvents",
					"model", model);

		} else {
			mav = new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/selectSellerDiffEventsForCheckStatus", method = RequestMethod.GET)
	public ModelAndView selectSellerDiffEventsForCheckStatus(ModelMap model,
			@RequestParam("id") Long id, HttpServletRequest request,
			RedirectAttributes attributes) throws ParseException {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		SellerDiffBankEvent sellerDiffBankEvent = sellerDiffBankEventDAO
				.findEvent(id);

		List<InvoiceDoc> invoiceDocs = invoiceDocDAO.findPoKey(
				sellerDiffBankEvent.getInvoiceKey()).getResultList();
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
				long tenure10 = DateService.getDaysBetweenTwoDates(date9,
						date10);

				model.put("tenure10", tenure10);
			}
		} else {
			long tenure10 = 0;
			model.put("tenure10", tenure10);
		}
		if (date11 != null) {
			if (date10 != null) {
				long tenure11 = DateService.getDaysBetweenTwoDates(date10,
						date11);

				model.put("tenure11", tenure11);
			}
		} else {
			long tenure11 = 0;
			model.put("tenure11", tenure11);
		}
		if (date12 != null) {
			if (date11 != null) {
				long tenure12 = DateService.getDaysBetweenTwoDates(date11,
						date12);

				model.put("tenure12", tenure12);
			}
		} else {
			long tenure12 = 0;
			model.put("tenure12", tenure12);
		}

		if (date13 != null) {
			if (date12 != null) {
				long tenure13 = DateService.getDaysBetweenTwoDates(date12,
						date13);
				model.put("tenure13", tenure13);
			}
		} else {
			long tenure13 = 0;
			model.put("tenure13", tenure13);
		}

		if (date14 != null) {
			if (date13 != null) {
				long tenure14 = DateService.getDaysBetweenTwoDates(date13,
						date14);
				model.put("tenure14", tenure14);
			}
		} else {
			long tenure14 = 0;
			model.put("tenure14", tenure14);
		}
		if (date15 != null) {
			if (date14 != null) {
				long tenure15 = DateService.getDaysBetweenTwoDates(date14,
						date15);
				model.put("tenure15", tenure15);
			}
		} else {
			long tenure15 = 0;
			model.put("tenure15", tenure15);
		}
		if (date16 != null) {
			if (date15 != null) {
				long tenure16 = DateService.getDaysBetweenTwoDates(date15,
						date16);
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
			bDate1 = DateService.getSellerDiffEventDate(sellerDiffBankEvent,
					i - 1);
			bDate2 = DateService.getSellerDiffEventDate(sellerDiffBankEvent, i);
			if (bDate1 != null && bDate2 != null) {
				int noOfDays = DateService.getDaysBetweenTwoDates(bDate1,
						bDate2);
				arrayList.add(noOfDays);
			}
		}
		Collections.sort(arrayList);

		double median = 0.0;

		if (arrayList.size() % 2 == 0) {
			median = Math
					.round((arrayList.get((arrayList.size() / 2)) + arrayList
							.get((arrayList.size() / 2 - 1))) / 2);
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

		sellerDiffBankEventForm.setCustomerName(sellerDiffBankEvent
				.getCustomerName());
		sellerDiffBankEventForm.setBuyer(sellerDiffBankEvent.getBuyer());
		sellerDiffBankEventForm
				.setBuyerBank(sellerDiffBankEvent.getBuyerBank());
		sellerDiffBankEventForm
				.setMasterKey(sellerDiffBankEvent.getMasterKey());
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

		mav = new ModelAndView("branchSelectSellerDiffEventsForCheckStatus",
				"model", model);

		return mav;

	}

	/* Comparison For Invoice events */

	@RequestMapping(value = "/comparisonBranchInvoice", method = RequestMethod.GET)
	public ModelAndView comparisionInvoiceCustomer(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		List<SellerSameBankEvent> sellerSameBankEvents = sellerSameBankEventDAO
				.findCheckStatus(user.getUserName()).getResultList();
		List<SellerDiffBankEvent> sellerDiffBankEvents = sellerDiffBankEventDAO
				.findCheckStatus(user.getUserName()).getResultList();

		newEventForm.setSellerSameBankEvents(sellerSameBankEvents);
		newEventForm.setSellerDiffBankEvents(sellerDiffBankEvents);

		if (sellerSameBankEvents.size() == 0
				&& sellerDiffBankEvents.size() == 0) {

			mav = new ModelAndView("noDataFoundInCusBranch", "model", model);
		} else {

			model.put("user", user);

			model.put("newEventForm", newEventForm);

			mav = new ModelAndView("comparisonBranchInvoice", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/comparisonInvoiceData", method = RequestMethod.POST)
	public ModelAndView comparisionInvoiceCustomer(ModelMap model,
			@ModelAttribute NewEventForm newEventForm,
			RedirectAttributes attributes) throws ParseException {

		String mav = "";

		Invoice invoice = invoiceDAO.getInvoiceData(
				newEventForm.getInvoiceKey()).getSingleResult();

		List<SellerSameBankEvent> sellerSameBankEvents = sellerSameBankEventDAO
				.findInvoiceKey(newEventForm.getInvoiceKey()).getResultList();

		List<InvoiceDoc> invoiceDocs = invoiceDocDAO.findPoKey(
				newEventForm.getInvoiceKey()).getResultList();

		List<InvoiceUpload> invoiceUploads = invoiceUploadDAO.findPoKey(
				newEventForm.getInvoiceKey()).getResultList();

		if (sellerSameBankEvents != null && sellerSameBankEvents.size() > 0) {

			SellerSameBankEvent sellerSameBankEvent = sellerSameBankEvents
					.get(0);

			sellerSameBankEventForm = eventsService
					.populateSellerSameBankFormBasedOnEvents(
							sellerSameBankEvent, invoice);

			/* Feedback module */
			sellerSameBankEventForm = eventsService
					.generateSellerSameBankFeedback(sellerSameBankEventForm,
							sellerSameBankEvent, invoice);

			model.put("sellerSameBankEvent", sellerSameBankEvent);

			model.put("sellerSameBankEventForm", sellerSameBankEventForm);

			mav = "comparisonBranchInvoiceDataForSame";
		} else {
			List<SellerDiffBankEvent> sellerDiffBankEventList = sellerDiffBankEventDAO
					.findInvoiceKey(newEventForm.getInvoiceKey())
					.getResultList();
			if (sellerDiffBankEventList != null
					&& sellerDiffBankEventList.size() > 0) {
				SellerDiffBankEvent sellerDiffBankEvent = sellerDiffBankEventList
						.get(0);

				sellerDiffBankEventForm = eventsService
						.populateSellerDiffBankFormBasedOnEvents(
								sellerDiffBankEvent, invoice);
				/* Feedback module */
				sellerDiffBankEventForm = eventsService
						.generateSellerDiffBankFeedback(
								sellerDiffBankEventForm, sellerDiffBankEvent,
								invoice);

				model.put("sellerDiffBankEvent", sellerDiffBankEvent);

				model.put("sellerDiffBankEventForm", sellerDiffBankEventForm);

				mav = "comparisonBranchInvoiceDataForDiff";

			}
		}
		model.put("invoiceUploads", invoiceUploads);
		model.put("invoiceDocs", invoiceDocs);
		model.put("invoice", invoice);

		return new ModelAndView(mav, "model", model);

	}

	/**
	 * Method to change rate of supplier and cancel PO/close PO based on Status
	 * 
	 * @param supplierForm
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/updateRateForSupplier", method = RequestMethod.POST)
	public String updateRateForSupplier(ModelMap model,
			@ModelAttribute SupplierForm supplierForm,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		Supplier supplier = supplierDAO.findBySupplierName(supplierForm
				.getSupplierName());

		supplier.setRate((supplier.getRate() + supplierForm.getRate()) / 2);

		supplierDAO.update(supplier);

		if (supplierForm.getStatus().equals(Constants.CLOSE)) {

			return "redirect:closePoBranch?id=" + supplierForm.getId();

		} else if (supplierForm.getStatus().equals(Constants.CANCEL)) {
			PurchaseOrder purchase = purchaseOrderDAO
					.getByPurchaseId(supplierForm.getId());

			purchase.setcComment(supplierForm.getComment());

			purchaseOrderDAO.updatePo(purchase);

			return "redirect:cancelPoBranch?id=" + supplierForm.getId();
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
	public String updateRateForBuyer(ModelMap model,
			@ModelAttribute NewBuyerForm newBuyerForm) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		NewBuyer buyer = newBuyerDAO.findByBuyerName(newBuyerForm
				.getBuyerName());

		buyer.setRate((buyer.getRate() + newBuyerForm.getRate()) / 2);

		newBuyerDAO.update(buyer);

		return "redirect:closeInvoiceBranch?id=" + newBuyerForm.getId();

	}

	// Po Approved List for Branch
	@RequestMapping(value = "/poApprovedListBranch", method = RequestMethod.GET)
	public ModelAndView PoApprovedList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> poList = purchaseOrderDAO.getPoListBycustomerName(
				user.getUserName()).getResultList();
		if (poList != null && poList.size() > 0) {
			purchaseOrderForm.setPoKey(poList.get(0).getPoKey());
			model.put("poList", poList);
			model.put("purchaseOrderForm", purchaseOrderForm);

			return new ModelAndView("poApprovedListBranch", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	// PO Upload
	@RequestMapping(value = "/fileUploadFormPoBranch", method = RequestMethod.GET)
	public ModelAndView getfileUploadFormPO(@RequestParam("id") Long id,RedirectAttributes attribute,
			ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		PoUpload poUpload = poUploadDAO.findId(id);
		poUploadForm.setPoKey(poUpload.getPoKey());
		model.put("user", user);
		model.put("poUploadForm", poUploadForm);

		return new ModelAndView("fileUploadFormPoBranch");

	}

	@RequestMapping("/fileUploadPoBrnach")
	public ModelAndView fileUploadedPO(ModelMap model,
			RedirectAttributes attribute,
			@ModelAttribute PoUploadForm poUploadForm, BindingResult result)
			throws RuntimeException, IOException {

		EndUser user = getCurrentLoggedUserDetails();

		poUploadForm.setTransactionId(KeyGenerator.generateTransactionKey());

		PoUpload poupload = new PoUpload();
		/*
		 * List<MultipartFile> files = poUploadForm.getFiles(); for
		 * (MultipartFile multipartFile : files) { String fileName =
		 * multipartFile.getOriginalFilename();
		 * 
		 * if (fileName != null && !fileName.equals("")) {
		 * log.info("These are the File" + fileName);
		 * uploadService.saveImage(servletContext.getRealPath("/") + "/img" +
		 * "/" + fileName, multipartFile);
		 * 
		 * }
		 * 
		 * poupload.setFileName(servletContext.getRealPath("/") + "/img" + "/" +
		 * fileName); }
		 */
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

		model.put("user", user);
		model.put("poUploadForm", poUploadForm);

		attribute.addFlashAttribute("success", "Saved Successfully. ");
		return new ModelAndView("showFileForPoBranch", "message", files);
	}

	@RequestMapping(value = "/poDocumnetListBranch", method = RequestMethod.GET)
	public ModelAndView PoDocumnetList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PoUpload> docList = poUploadDAO.findByName(user.getUserName())
				.getResultList();

		model.put("user", user);
		if (docList != null && docList.size() > 0) {
			model.put("docList", docList);

			return new ModelAndView("poDocumnetListBranch", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	// Invoice Approved List
	@RequestMapping(value = "/invoiceApprovedListBranch", method = RequestMethod.GET)
	public ModelAndView InvoiceApprovedList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getPoListBycustomerName(
				user.getUserName()).getResultList();
		if (invoiceList != null && invoiceList.size() > 0) {
			invoiceForm.setPoKey(invoiceList.get(0).getPoKey());
			model.put("invoiceList", invoiceList);
			model.put("invoiceForm", invoiceForm);

			return new ModelAndView("invoiceApprovedListBranch", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	// Invoice Upload
	@RequestMapping(value = "/fileUploadFormInvoiceBranch", method = RequestMethod.GET)
	public ModelAndView getfileUploadFormInvoice(@RequestParam("id") Long id,RedirectAttributes attribute,
			ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		InvoiceUpload invoiceUpload = invoiceUploadDAO.findId(id);
		inoviceUploadForm.setPoKey(invoiceUpload.getPoKey());
		model.put("user", user);
		model.put("inoviceUploadForm", inoviceUploadForm);

		return new ModelAndView("fileUploadFormInvoiceBranch");

	}

	@RequestMapping("/fileUploadInoviceBranch")
	public ModelAndView fileUploadedInvoice(ModelMap model,
			RedirectAttributes attribute,
			@ModelAttribute InoviceUploadForm inoviceUploadForm)
			throws RuntimeException, IOException {


		inoviceUploadForm.setTransactionId(KeyGenerator.generateTransactionKey());

		EndUser user = getCurrentLoggedUserDetails();

		InvoiceUpload invoiceupload = new InvoiceUpload();
		/*
		 * List<MultipartFile> files = inoviceUploadForm.getFiles(); for
		 * (MultipartFile multipartFile : files) { String fileName =
		 * multipartFile.getOriginalFilename();
		 * 
		 * if (fileName != null && !fileName.equals("")) {
		 * log.info("These are the File" + fileName);
		 * uploadService.saveImage(servletContext.getRealPath("/") + "/img" +
		 * "/" + fileName, multipartFile);
		 * 
		 * }
		 * 
		 * invoiceupload.setFileName(servletContext.getRealPath("/") + "/img" +
		 * "/" + fileName); }
		 */
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
		invoiceupload
				.setCustomerHeadKey(inoviceUploadForm.getCustomerHeadKey());
		invoiceupload.setCustomerHeadName(inoviceUploadForm
				.getCustomerHeadName());
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

		model.put("user", user);
		model.put("inoviceUploadForm", inoviceUploadForm);

		attribute.addFlashAttribute("success", "Saved Successfully. ");
		return new ModelAndView("showFileForInvoiceBranch", "message", files);
	}

	@RequestMapping(value = "/invoiceDocumnetListBranch", method = RequestMethod.GET)
	public ModelAndView InvoiceDocumnetList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<InvoiceUpload> docList = invoiceUploadDAO.findByName(
				user.getUserName()).getResultList();

		model.put("user", user);
		if (docList != null && docList.size() > 0) {
			model.put("docList", docList);

			return new ModelAndView("invoiceDocumnetListBranch", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	/**
	 * Method to generate Warehouse Chart
	 */
	@RequestMapping(value = "/generateWarehouseChart", method = RequestMethod.GET)
	public ModelAndView generateWarehouseChart(
			@ModelAttribute WareHouseForm wareHouseForm, ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouse> warehouseList = wareHouseDAO.getWareHouseandStatusList(
				user.getUserName()).getResultList();

		model.put("user", user);
		model.put("warehouseList", warehouseList);

		return new ModelAndView("generateWarehouseChartBranch", "model", model);
	}

	/**
	 * Method to generate Warehouse Chart
	 */
	@RequestMapping(value = "/generateWarehouseChartById", method = RequestMethod.POST)
	public String generateWarehouseChartById(
			@ModelAttribute WareHouseForm wareHouseForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		Map<WareHouse, List<Inventory>> warehouseChart = new LinkedHashMap<>();

		if (wareHouseForm.getIds() != null) {
			for (long id : wareHouseForm.getIds()) {
				WareHouse wareHouse = wareHouseDAO.getByWareHouseId(id);
				List<Inventory> inventoryList = invenrotyDAO
						.getInventoryListByWareHouseNCustomerName(
								wareHouse.getWareHouseName(),
								user.getUserName());

				warehouseChart.put(wareHouse, inventoryList);
			}
		}
		attributes.addFlashAttribute("warehouseChart", warehouseChart);

		return "redirect:generateWarehouseChart";
	}

	@RequestMapping(value = "/downloadBranchFile", method = RequestMethod.GET)
	public ModelAndView printWelcome(ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.addAttribute("message", "Sample code!");
		return new ModelAndView("downloadBranchFile", "model", model);

	}

	@RequestMapping(value = "/downloadFilesBranch", method = RequestMethod.GET)
	public @ResponseBody void downloadFiles(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		ServletContext context = getServletContext();

		File downloadFile = new File(
				"D:/CustBranchSampleFile/Declaration cum Affidavit to Bank.docx");
		FileInputStream inputStream = null;
		OutputStream outStream = null;

		try {
			inputStream = new FileInputStream(downloadFile);

			response.setContentType(context
					.getMimeType("D:/CustBranchSampleFile/Declaration cum Affidavit to Bank.docx"));
			response.setContentLength((int) downloadFile.length());

			// response header
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					downloadFile.getName());
			response.setHeader(headerKey, headerValue);

			// Write response
			outStream = response.getOutputStream();
			IOUtils.copy(inputStream, outStream);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != inputStream)
					inputStream.close();
				if (null != inputStream)
					outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	@RequestMapping(value = "/reportsListBranch", method = RequestMethod.GET)
	public ModelAndView reportsListBank(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("purchaseOrderForm", purchaseOrderForm);
		model.put("user", user);

		return new ModelAndView("reportsListBranch", "model", model);

	}

	@RequestMapping(value = "/reportsPOBranchPost", method = RequestMethod.POST)
	public ModelAndView reportsPOBankPost(
			@ModelAttribute PurchaseOrderForm purchaseOrderForm,
			ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PurchaseOrder> purchaseList = purchaseOrderDAO
				.getPOByHeadCategoryAndDate(user.getUserName(),
						purchaseOrderForm.getGoodsCategory(),
						purchaseOrderForm.getFromDate(),
						purchaseOrderForm.getToDate());

		if (purchaseList != null && purchaseList.size() > 0) {
			purchaseOrderForm.setPurchaseList(purchaseList);

			int range = DateService.getDaysBetweenTwoDates(
					purchaseOrderForm.getFromDate(),
					purchaseOrderForm.getToDate());
			double mean = Math.round(range / purchaseList.size());
			String mode = purchaseOrderDAO.getModeByQuantity(
					user.getUserName(), purchaseOrderForm.getGoodsCategory(),
					purchaseOrderForm.getFromDate(),
					purchaseOrderForm.getToDate());
			double median = 0.0;
			if (purchaseList.size() % 2 == 0) {
				median = Math
						.round((Double.parseDouble(purchaseList.get(
								purchaseList.size() / 2).getQuantity()) + Double
								.parseDouble(purchaseList.get(
										purchaseList.size() / 2 - 1)
										.getQuantity())) / 2);
			} else {
				median = Math.round(Double.parseDouble(purchaseList.get(
						purchaseList.size() / 2).getQuantity()));
			}

			double variance = Math.round(((range - mean) * (range - mean))
					/ purchaseList.size());

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

		return new ModelAndView("reportsListBranchView", "model", model);

	}

	@RequestMapping(value = "/reportsListBranchView", method = RequestMethod.GET)
	public ModelAndView reportsListBankView(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("purchaseOrderForm", purchaseOrderForm);
		model.put("user", user);

		return new ModelAndView("reportsListBranchView", "model", model);

	}

	@RequestMapping(value = "/reportsListBranchBying", method = RequestMethod.GET)
	public ModelAndView reportsListBankBying(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("invoiceForm", invoiceForm);
		model.put("user", user);

		return new ModelAndView("reportsListBranchBying", "model", model);

	}

	@RequestMapping(value = "/reportsBranchBuyingPost", method = RequestMethod.POST)
	public ModelAndView reportsPOBankPost(
			@ModelAttribute InvoiceForm invoiceForm, ModelMap model,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		List<Invoice> invoiceList = invoiceDAO.getInvoiceReports(
				user.getUserName(), invoiceForm.getGoodsCategory(),
				invoiceForm.getFromDate(), invoiceForm.getToDate());

		if (invoiceList != null && invoiceList.size() > 0) {
			invoiceForm.setInvoiceList(invoiceList);

			int range = DateService.getDaysBetweenTwoDates(
					invoiceForm.getFromDate(), invoiceForm.getToDate());
			double mean = Math.round(range / invoiceList.size());
			String mode = invoiceDAO.getModeByQuantity(user.getUserName(),
					invoiceForm.getGoodsCategory(), invoiceForm.getFromDate(),
					invoiceForm.getToDate());
			double median = 0.0;
			if (invoiceList.size() % 2 == 0) {
				median = Math
						.round((Double.parseDouble(invoiceList.get(
								invoiceList.size() / 2).getQuantity()) + Double
								.parseDouble(invoiceList.get(
										invoiceList.size() / 2 - 1)
										.getQuantity())) / 2);
			} else {
				median = Math.round(Double.parseDouble(invoiceList.get(
						invoiceList.size() / 2).getQuantity()));
			}

			double variance = Math.round(((range - mean) * (range - mean))
					/ invoiceList.size());

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
		return new ModelAndView("reportsListBranchBuyingView", "model", model);

	}

	@RequestMapping(value = "/reportsListBranchBuyingView", method = RequestMethod.GET)
	public ModelAndView reportsListBankBuyingView(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("invoiceForm", invoiceForm);
		model.put("user", user);

		return new ModelAndView("reportsListBranchBuyingView", "model", model);

	}

	/* Existing Stock */

	@RequestMapping(value = "/existingStockPo", method = RequestMethod.GET)
	public ModelAndView createCusthWareHouse(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PoStock> poStockList = poStockDAO.getPoStockList(
				user.getUserName()).getResultList();

		poStockForm.setCustomerName(user.getUserName());
		poStockForm.setCustomerHeadName(user.getCustomerHeadName());

		List<WareHouse> whList = wareHouseDAO.getWareHouseandStatusList(
				user.getUserName()).getResultList();
		if (whList != null) {
			poStockForm.setWareHouseList(whList);

			model.put("poStockForm", poStockForm);

		}

		model.put("user", user);
		model.put("poStockForm", poStockForm);
		model.put("poStockList", poStockList);

		return new ModelAndView("existingStockPo", "model", model);

	}

	@RequestMapping(value = "/selectExistingStockPo", method = RequestMethod.GET)
	public ModelAndView selectExistingStockPo(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

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

		List<WareHouse> whList = wareHouseDAO.getWareHouseandStatusList(
				user.getUserName()).getResultList();
		if (whList != null) {
			poStockForm.setWareHouseList(whList);

			model.put("poStockForm", poStockForm);
			model.put("user", user);
		}

		model.put("user", user);

		model.put("poStockForm", poStockForm);

		return new ModelAndView("selectExistingStockPo", "model", model);

	}

	@RequestMapping(value = "/updateExistingStockPoConfirm", method = RequestMethod.POST)
	public ModelAndView updatExistingStockPoConfirm(
			@ModelAttribute PoStockForm poStockForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		if (poStockForm.getOverAllQuantity() < 0) {
			model.put("user", user);
			attributes.addFlashAttribute("success",
					"Total Should Not be Less than Zero");
			return new ModelAndView("redirect:existingStockPo");
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

		return new ModelAndView("updateExistingStockPoConfirm", "model", model);

	}

	@RequestMapping(value = "/updateExistingStockPo", method = RequestMethod.POST)
	public ModelAndView updateExistingStockPo(ModelMap model,
			@ModelAttribute PoStockForm poStockForm,
			RedirectAttributes attributes) {
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

		return new ModelAndView("redirect:existingStockPo");

	}

	@RequestMapping(value = "/existingStockHeadPoShow", method = RequestMethod.POST)
	public ModelAndView existingStockHeadPoShow(
			@ModelAttribute PoStockForm poStockForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		if (poStockForm.getOverAllQuantity() < 0) {
			model.put("user", user);
			attributes.addFlashAttribute("success",
					"Total Should Not be Less than Zero");
			return new ModelAndView("redirect:existingStockPo");
		} else {

			model.put("user", user);
			model.put("poStockForm", poStockForm);

			return new ModelAndView("existingStockPoConfirm", "model", model);
		}
	}

	@RequestMapping(value = "/existingStockPoConfirm", method = RequestMethod.POST)
	public ModelAndView existingStockPoConfirm(
			@ModelAttribute PoStockForm poStockForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		List<PoStock> po = poStockDAO.getPoStockGoodsName(
				poStockForm.getGoodsName(), user.getUserName()).getResultList();

		if (po.size() != 0) {
			model.put("user", user);
			attributes.addFlashAttribute("success", "Goods Already Exists");

			return new ModelAndView("redirect:existingStockPo");
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

			return new ModelAndView("existingStockPoConfirm", "model", model);
		}
	}

	@RequestMapping(value = "/existingStockPoSave", method = RequestMethod.POST)
	public ModelAndView existingStockPoSave(
			@ModelAttribute PoStockForm poStockForm, ModelMap model,
			RedirectAttributes attributes) {

		poStockForm.setTransactionId(KeyGenerator.generateTransactionKey());
		EndUser user = getCurrentLoggedUserDetails();

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
		;

		poStockDAO.insertPoStock(po);

		Transaction trans = new Transaction();

		trans.setTransactionId(poStockForm.getTransactionId());
		trans.setTransactionStatus("Po Stock  Saved");
		trans.setTransactionType("Po Stock");
		;
		transcationDAOImpl.insertTransaction(trans);

		model.put("poStockForm", poStockForm);
		model.put("user", user);

		return new ModelAndView("existingStockPoTransaction", "model", model);

	}

	@RequestMapping(value = "/existingStockPoTransaction", method = RequestMethod.GET)
	public ModelAndView existingStockPoTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("poStockForm", poStockForm);

		return new ModelAndView("existingStockPoTransaction", "model", model);

	}

	@RequestMapping(value = "/existingStockPoFullList", method = RequestMethod.GET)
	public ModelAndView existingStockPoFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PoStock> poList = poStockDAO.getPoStockList(user.getUserName())
				.getResultList();

		Collection<Inventory> inventoryList = invenrotyDAO.getList();

		List<PoStockForm> stockList = new ArrayList<PoStockForm>();
		if(poList != null && poList.size() > 0) {
			for (PoStock po : poList )	{
				if(inventoryList != null && inventoryList.size() > 0) {
				for(Inventory inventory :  inventoryList) {
					if(po.getGoodsName().equals(inventory.getGoods())) {
						PoStockForm poStockForm = new PoStockForm();
						poStockForm.setId(po.getId());
						poStockForm.setQuantity(inventory.getTotal());
						poStockForm.setGoodsName(po.getGoodsName());
						poStockForm.setCustomerName(po.getCustomerName());
						poStockForm.setOverAllQuantity(po.getOverAllQuantity());
						stockList.add(poStockForm);
					}else{
						PoStockForm poStockForm = new PoStockForm();
						poStockForm.setId(po.getId());
						poStockForm.setQuantity(inventory.getTotal());
						poStockForm.setGoodsName(po.getGoodsName());
						poStockForm.setCustomerName(po.getCustomerName());
						poStockForm.setOverAllQuantity(po.getOverAllQuantity());
						stockList.add(poStockForm);
					}
				}
				}else {
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

			return new ModelAndView("existingStockPoFullList", "model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/existingStockInvoice", method = RequestMethod.GET)
	public ModelAndView existingStockHeadInvoice(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<InvoiceStock> poStockList = invoiceStockDAO.getInvoiceStockList(
				user.getUserName()).getResultList();

		invoiceStockForm.setCustomerName(user.getUserName());
		invoiceStockForm.setCustomerHeadName(user.getCustomerHeadName());

		List<WareHouse> whList = wareHouseDAO.getWareHouseandStatusList(
				user.getUserName()).getResultList();
		if (whList != null) {
			invoiceStockForm.setWareHouseList(whList);

			model.put("poStockForm", poStockForm);

		}

		model.put("user", user);
		model.put("invoiceStockForm", invoiceStockForm);
		model.put("poStockList", poStockList);

		return new ModelAndView("existingStockInvoice", "model", model);

	}

	@RequestMapping(value = "/selectExistingStockInvoice", method = RequestMethod.GET)
	public ModelAndView selectExistingStockHeadInvoice(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

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

		List<WareHouse> whList = wareHouseDAO.getWareHouseandStatusList(
				user.getUserName()).getResultList();
		if (whList != null) {
			invoiceStockForm.setWareHouseList(whList);

			model.put("invoiceStockForm", invoiceStockForm);
			model.put("user", user);
		}

		model.put("user", user);

		model.put("invoiceStockForm", invoiceStockForm);

		return new ModelAndView("selectExistingStockInvoice", "model", model);

	}

	@RequestMapping(value = "/updateExistingStockInvoiceConfirm", method = RequestMethod.POST)
	public ModelAndView updateExistingStockHeadInvoiceConfirm(
			@ModelAttribute InvoiceStockForm invoiceStockForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		if (invoiceStockForm.getOverAllQuantity() < 0) {
			model.put("user", user);
			attributes.addFlashAttribute("success",
					"Total Should Not be Less than Zero");
			return new ModelAndView("redirect:existingStockInvoice");
		} else {
			invoiceStockForm.setId(invoiceStockForm.getId());
			invoiceStockForm
					.setCustomerName(invoiceStockForm.getCustomerName());
			invoiceStockForm.setCustomerHeadName(invoiceStockForm
					.getCustomerHeadName());
			invoiceStockForm.setOverAllQuantity(invoiceStockForm
					.getOverAllQuantity());
			invoiceStockForm.setWareHouseName(invoiceStockForm
					.getWareHouseName());
			invoiceStockForm.setQuantity(invoiceStockForm.getQuantity());
			invoiceStockForm
					.setUsedQuantity(invoiceStockForm.getUsedQuantity());
			invoiceStockForm.setDamaged(invoiceStockForm.getDamaged());
			invoiceStockForm.setGoodsName(invoiceStockForm.getGoodsName());

		}
		model.put("user", user);

		model.put("invoiceStockForm", invoiceStockForm);

		return new ModelAndView("updateExistingStockInvoiceConfirm", "model",
				model);

	}

	@RequestMapping(value = "/updateExistingStockInvoice", method = RequestMethod.POST)
	public ModelAndView updateExistingStockHeadInvoice(ModelMap model,
			@ModelAttribute InvoiceStockForm invoiceStockForm,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		InvoiceStock po = invoiceStockDAO.getByInvoiceStockId(invoiceStockForm
				.getId());

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

		return new ModelAndView("redirect:existingStockInvoice");

	}

	@RequestMapping(value = "/existingStockHeadInvoiceShow", method = RequestMethod.POST)
	public ModelAndView existingStockHeadInvoiceShow(
			@ModelAttribute InvoiceStockForm invoiceStockForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		if (invoiceStockForm.getOverAllQuantity() < 0) {
			model.put("user", user);
			attributes.addFlashAttribute("success",
					"Total Should Not be Less than Zero");
			return new ModelAndView("redirect:existingStockInvoice");
		} else {

			model.put("user", user);
			model.put("invoiceStockForm", invoiceStockForm);

			return new ModelAndView("existingStockInvoiceConfirm", "model",
					model);
		}
	}

	@RequestMapping(value = "/existingStockInvoiceConfirm", method = RequestMethod.POST)
	public ModelAndView existingStockHeadInvoiceConfirm(
			@ModelAttribute InvoiceStockForm invoiceStockForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		List<InvoiceStock> po = invoiceStockDAO.getInvoiceStockGoodsName(
				invoiceStockForm.getGoodsName(), user.getUserName())
				.getResultList();

		if (po.size() != 0) {
			model.put("user", user);
			attributes.addFlashAttribute("success", "Goods Already Exists");

			return new ModelAndView("redirect:existingStockInvoice");
		} else {

			invoiceStockForm
					.setCustomerName(invoiceStockForm.getCustomerName());
			invoiceStockForm.setCustomerHeadName(invoiceStockForm
					.getCustomerHeadName());
			invoiceStockForm.setWareHouseName(invoiceStockForm
					.getWareHouseName());
			invoiceStockForm.setOverAllQuantity(invoiceStockForm
					.getOverAllQuantity());
			invoiceStockForm.setQuantity(invoiceStockForm.getQuantity());
			invoiceStockForm
					.setUsedQuantity(invoiceStockForm.getUsedQuantity());
			invoiceStockForm.setDamaged(invoiceStockForm.getDamaged());
			invoiceStockForm.setGoodsName(invoiceStockForm.getGoodsName());
			invoiceStockForm.setTransactionId(invoiceStockForm
					.getTransactionId());

			model.put("invoiceStockForm", invoiceStockForm);
			model.put("user", user);

			return new ModelAndView("existingStockInvoiceConfirm", "model",
					model);
		}
	}

	@RequestMapping(value = "/existingStockInvoiceSave", method = RequestMethod.POST)
	public ModelAndView existingStockHeadInvoiceSave(
			@ModelAttribute InvoiceStockForm invoiceStockForm, ModelMap model,
			RedirectAttributes attributes) {

		invoiceStockForm.setTransactionId(KeyGenerator.generateTransactionKey());

		EndUser user = getCurrentLoggedUserDetails();

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

		return new ModelAndView("existingStockInvoiceTransaction", "model",
				model);

	}

	@RequestMapping(value = "/existingStockInvoiceTransaction", method = RequestMethod.GET)
	public ModelAndView existingStockHeadInvoiceTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("invoiceStockForm", invoiceStockForm);

		return new ModelAndView("existingStockHeadInvoiceTransaction", "model",
				model);

	}

	@RequestMapping(value = "/existingStockInvoiceFullList", method = RequestMethod.GET)
	public ModelAndView existingStockHeadInvoiceFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<InvoiceStock> poList = invoiceStockDAO.getInvoiceStockList(
				user.getUserName()).getResultList();

		model.put("user", user);

		Collection<InvoiceInventory> inventoryList = invoiceInventoryDAO
				.getList();

		List<InvoiceStockForm> stockList = new ArrayList<InvoiceStockForm>();
		if(poList != null && poList.size() > 0 ) {
		for (InvoiceStock po : poList )	{
			if(inventoryList != null && inventoryList.size() > 0 ) {
			for(InvoiceInventory inventory :  inventoryList) {
				if(po.getGoodsName().equals(inventory.getGoods())) {
					InvoiceStockForm poStockForm = new InvoiceStockForm();
					poStockForm.setId(po.getId());
					poStockForm.setQuantity(inventory.getTotal());
					poStockForm.setGoodsName(po.getGoodsName());
					poStockForm.setCustomerName(po.getCustomerName());
					poStockForm.setOverAllQuantity(po.getOverAllQuantity());
					stockList.add(poStockForm);
				}else{
					InvoiceStockForm poStockForm = new InvoiceStockForm();
					poStockForm.setId(po.getId());
					poStockForm.setQuantity(inventory.getTotal());
					poStockForm.setGoodsName(po.getGoodsName());
					poStockForm.setCustomerName(po.getCustomerName());
					poStockForm.setOverAllQuantity(po.getOverAllQuantity());
					stockList.add(poStockForm);
				}
			}
			}else {
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

			return new ModelAndView("existingStockInvoiceFullList", "model",
					model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	/**
	 * Method to generate Existing stock PO Chart
	 */
	@RequestMapping(value = "/existingStockPOInventoryChart", method = RequestMethod.GET)
	public ModelAndView existingStockPOInventoryChart(
			@ModelAttribute WareHouseForm wareHouseForm, ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouse> warehouseList = wareHouseDAO.getWareHouseandStatusList(
				user.getUserName()).getResultList();

		model.put("user", user);
		model.put("warehouseList", warehouseList);

		return new ModelAndView("existingStockPOInventoryChart", "model", model);
	}

	/**
	 * Method to generate Warehouse Chart
	 */
	@RequestMapping(value = "/existingStockPOInventoryChartById", method = RequestMethod.POST)
	public String existingStockPOInventoryChartById(
			@ModelAttribute WareHouseForm wareHouseForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		Map<WareHouse, List<PoStock>> warehouseChart = new LinkedHashMap<>();

		if (wareHouseForm.getIds() != null) {
			for (long id : wareHouseForm.getIds()) {
				WareHouse wareHouse = wareHouseDAO.getByWareHouseId(id);
				List<PoStock> poList = poStockDAO
						.getPoStockByCustomerNWarehouse(user.getUserName(),
								wareHouse.getWareHouseName());

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
	public ModelAndView existingStockInvoiceInventoryChart(
			@ModelAttribute WareHouseForm wareHouseForm, ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouse> warehouseList = wareHouseDAO.getWareHouseandStatusList(
				user.getUserName()).getResultList();

		model.put("user", user);
		model.put("warehouseList", warehouseList);

		return new ModelAndView("existingStockInvoiceInventoryChart", "model",
				model);
	}

	/**
	 * Method to generate Warehouse Chart
	 */
	@RequestMapping(value = "/existingStockInvoiceInventoryChartById", method = RequestMethod.POST)
	public String existingStockInvoiceInventoryChartById(
			@ModelAttribute WareHouseForm wareHouseForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		Map<WareHouse, List<InvoiceStock>> warehouseChart = new LinkedHashMap<>();

		if (wareHouseForm.getIds() != null) {
			for (long id : wareHouseForm.getIds()) {
				WareHouse wareHouse = wareHouseDAO.getByWareHouseId(id);
				List<InvoiceStock> invoiceList = invoiceStockDAO
						.getInvoiceStockByCustomerNWarehouse(
								user.getUserName(),
								wareHouse.getWareHouseName());

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
	public ModelAndView generateInvoiceInventoryChartBranch(
			@ModelAttribute WareHouseForm wareHouseForm, ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouse> warehouseList = wareHouseDAO.getWareHouseandStatusList(
				user.getUserName()).getResultList();

		model.put("user", user);
		model.put("warehouseList", warehouseList);

		return new ModelAndView("generateInvoiceInventoryChartBranch", "model",
				model);
	}

	/**
	 * Method to generate Invoice inventory Chart
	 */
	@RequestMapping(value = "/generateInvoiceInventoryChartBranchById", method = RequestMethod.POST)
	public String generateInvoiceInventoryChartBranchById(
			@ModelAttribute WareHouseForm wareHouseForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		Map<WareHouse, List<InvoiceInventory>> warehouseChart = new LinkedHashMap<>();

		if (wareHouseForm.getIds() != null) {
			for (long id : wareHouseForm.getIds()) {
				WareHouse wareHouse = wareHouseDAO.getByWareHouseId(id);
				List<InvoiceInventory> inventoryList = invoiceInventoryDAO
						.getInvoiceInventoryByCustomerNWarehouse(
								user.getUserName(),
								wareHouse.getWareHouseName());

				warehouseChart.put(wareHouse, inventoryList);
			}
		}
		attributes.addFlashAttribute("warehouseChart", warehouseChart);

		return "redirect:generateInvoiceInventoryChartBranch";
	}

	@RequestMapping(value = "/branchHelp", method = RequestMethod.GET)
	public ModelAndView adminHelp(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("branchHelp", "model", model);

	}

	@RequestMapping(value = "/addOrModifyReportCustBranchList", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportCustList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<InvoiceReports> disputeList = invoiceReportsDAO
				.getInvoiceReportsOnCustAndAcceptList(user.getUserName())
				.getResultList();

		if (disputeList != null && disputeList.size() > 0) {
			model.put("disputeList", disputeList);
			return new ModelAndView("addOrModifyReportCustBranchList", "model",
					model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/addOrModifyReportInvoiceBranchAccept", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportInvoiceAccept(@RequestParam Long id,
			ModelMap model, RedirectAttributes attributes) {

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

		return new ModelAndView("redirect:addOrModifyReportCustBranchList");
	}

	@RequestMapping(value = "/addOrModifyReportInvoiceBranch", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportInvoice(@RequestParam Long id,
			ModelMap model, RedirectAttributes attributes) {

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

		return new ModelAndView("addOrModifyReportInvoiceBranch", "model",
				model);
	}

	@RequestMapping(value = "/addOrModifyReportInvoiceBranchConfirm", method = RequestMethod.POST)
	public ModelAndView addOrModifyReportInvoiceConfirm(ModelMap model,
			@ModelAttribute DisputeReportsForm disputeReportsForm,
			BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		disputeReportsForm
				.setSurveyorName(disputeReportsForm.getSurveyorName());
		disputeReportsForm.setSurveyorCom(disputeReportsForm.getSurveyorCom());
		disputeReportsForm.setSurveyorAdd(disputeReportsForm.getSurveyorAdd());
		disputeReportsForm.setSurveyorEmail(disputeReportsForm
				.getSurveyorEmail());
		disputeReportsForm.setSurveyorPhone(disputeReportsForm
				.getSurveyorPhone());
		disputeReportsForm.setDefectType(disputeReportsForm.getDefectType());
		disputeReportsForm.setDefectQty(disputeReportsForm.getDefectQty());
		disputeReportsForm.setDefectCostForGoods(disputeReportsForm
				.getDefectCostForGoods());
		disputeReportsForm
				.setDamageStatus(disputeReportsForm.getDamageStatus());
		disputeReportsForm.setReplacement(disputeReportsForm.getReplacement());
		disputeReportsForm.setRepairCost(disputeReportsForm.getRepairCost());
		disputeReportsForm.setInvoicekey(disputeReportsForm.getInvoicekey());
		model.put("disputeReportsForm", disputeReportsForm);
		model.put("user", user);

		return new ModelAndView("addOrModifyReportInvoiceBranchConfirm",
				"model", model);

	}

	@RequestMapping(value = "/addOrModifyReportInvoiceBranchPost", method = RequestMethod.POST)
	public ModelAndView addOrModifyReportInvoicePost(ModelMap model,
			@ModelAttribute DisputeReportsForm disputeReportsForm,
			BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();
		List<InvoiceReports> dis = invoiceReportsDAO.getInvoiceReportsList(
				disputeReportsForm.getInvoicekey()).getResultList();
		if (dis != null && dis.size() > 0)

		{
			InvoiceReports disp = invoiceReportsDAO.getInvoiceReportsList(
					disputeReportsForm.getInvoicekey()).getSingleResult();

			disp.setSuppSurveyorName(disputeReportsForm.getSuppSurveyorName());
			disp.setSuppSurveyorCom(disputeReportsForm.getSuppSurveyorCom());
			disp.setSuppSurveyorAdd(disputeReportsForm.getSuppSurveyorAdd());
			disp.setSuppSurveyorEmail(disputeReportsForm.getSuppSurveyorEmail());
			disp.setSuppSurveyorPhone(disputeReportsForm.getSuppSurveyorPhone());
			disp.setSuppDefectType(disputeReportsForm.getSuppDefectType());
			disp.setSuppDefectQty(disputeReportsForm.getSuppDefectQty());
			disp.setSuppDefectCostForGoods(disputeReportsForm
					.getSuppDefectCostForGoods());
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

		return new ModelAndView("addOrModifyReportInvoiceBranchTrans", "model",
				model);

	}

	@RequestMapping(value = "/addOrModifyReportInvoiceBranchTrans", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportInvoiceTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		return new ModelAndView("addOrModifyReportInvoiceBranchTrans", "model",
				model);

	}

	@RequestMapping(value = "/addOrModifyReportInvoiceBranchFullList", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportVendorFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<InvoiceReports> disputeList = invoiceReportsDAO
				.getInvoiceReportsVendorList(user.getUserName())
				.getResultList();

		if (disputeList != null && disputeList.size() > 0) {
			model.put("disputeList", disputeList);
			return new ModelAndView("addOrModifyReportInvoiceBranchFullList",
					"model", model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}

	}

	@RequestMapping(value = "/addOrModifyReportInvoiceBranchView", method = RequestMethod.GET)
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

		return new ModelAndView("addOrModifyReportInvoiceBranchView", "model",
				model);
	}

	@RequestMapping(value = "/addOrModifyReportInvoiceBranchCompare", method = RequestMethod.GET)
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

		Float total = dispute.getDefectQty() - dispute.getSuppDefectQty();
		Float total1 = dispute.getRepairCost() - dispute.getSuppRepairCost();

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);
		model.put("total", total);
		model.put("total1", total1);

		return new ModelAndView("addOrModifyReportInvoiceBranchCompare",
				"model", model);
	}

	@RequestMapping(value = "/invoiceBranchListForRenewel", method = RequestMethod.GET)
	public ModelAndView InvoiceListForRenewel(ModelMap model) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		List<Invoice> purchase = invoiceDAO
				.getInoviceListBycustomerNameAndStatus(user.getUserName())
				.getResultList();

		if (purchase != null && purchase.size() > 0) {
			model.put("purchase", purchase);
			return new ModelAndView("invoiceBranchListForRenewel", "model",
					model);
		} else {
			return new ModelAndView("noDataFoundInCusBranch", "model", model);
		}
	}

	@RequestMapping(value = "/invoiceBranchForRenewel", method = RequestMethod.GET)
	public ModelAndView InvoiceForRenewel(@RequestParam("id") Long id,
			ModelMap model) {

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

		return new ModelAndView("invoiceBranchForRenewel", "model", model);

	}

	@RequestMapping(value = "/invoiceBranchForRenewelConfirm", method = RequestMethod.POST)
	public ModelAndView InvoiceForRenewelConfirm(ModelMap model,
			@ModelAttribute InvoiceForm invoiceForm, BindingResult result,
			RedirectAttributes attributes) {

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
		invoiceForm
				.setCustomerBranchEmail(invoiceForm.getCustomerBranchEmail());
		invoiceForm.setBuyerEmail(invoiceForm.getBuyerEmail());
		invoiceForm.setPoKey(invoiceForm.getPoKey());
		invoiceForm.setInsuranceAmount(invoiceForm.getInsuranceAmount());
		invoiceForm.setInsuranceDetails(invoiceForm.getInsuranceDetails());
		invoiceForm.setInsuranceType(invoiceForm.getInsuranceType());
		invoiceForm.setSentDate(invoiceForm.getStartDate());
		invoiceForm.setEndDate(invoiceForm.getEndDate());

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("invoiceBranchForRenewelConfirm", "model",
				model);

	}

	@RequestMapping(value = "/invoiceBranchForRenewelPost", method = RequestMethod.POST)
	public ModelAndView invoiceForRenewelPost(ModelMap model,
			@ModelAttribute InvoiceForm invoiceForm, BindingResult result,
			RedirectAttributes attributes) {

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

		return new ModelAndView("invoiceBranchForRenewelTransaction", "model",
				model);
	}

	@RequestMapping(value = "/invoiceBranchForRenewelTransaction", method = RequestMethod.GET)
	public ModelAndView invoiceForRenewelTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("invoiceBranchForRenewelTransaction", "model",
				model);

	}
	
	@RequestMapping(value = "/licenseOnRestrictedBranch", method = RequestMethod.GET)
	public ModelAndView licenseOnRestricted(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("restrictedLicenseForm", restrictedLicenseForm);

		return new ModelAndView("licenseOnRestrictedBranch", "model", model);

	}
	
	@RequestMapping(value = "/licenseOnRestrictedBranchConfirm", method = RequestMethod.POST)
	public ModelAndView licenseOnRestrictedConfirm(ModelMap model,
			@ModelAttribute RestrictedLicenseForm restrictedLicenseForm,
			BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		restrictedLicenseForm.setCustomer(user.getUserName());
		restrictedLicenseForm.setLicenseDetails(restrictedLicenseForm.getLicenseDetails());
		restrictedLicenseForm.setStartDate(restrictedLicenseForm.getStartDate());
		restrictedLicenseForm.setEndDate(restrictedLicenseForm.getEndDate());
		restrictedLicenseForm.setGoodsName(restrictedLicenseForm.getGoodsName());
		restrictedLicenseForm.setQty(restrictedLicenseForm.getQty());

		model.put("user", user);
		model.put("restrictedLicenseForm", restrictedLicenseForm);

		return new ModelAndView("licenseOnRestrictedBranchConfirm", "model", model);
	}
	
	@RequestMapping(value = "/licenseOnRestrictedBranchPost", method = RequestMethod.POST)
	public ModelAndView licenseOnRestrictedPost(ModelMap model,
			@ModelAttribute RestrictedLicenseForm restrictedLicenseForm,
			BindingResult result, RedirectAttributes attributes) {

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

		return new ModelAndView("licenseOnRestrictedBranchTrans", "model", model);
	}
	@RequestMapping(value = "/licenseOnRestrictedBranchTrans", method = RequestMethod.GET)
	public ModelAndView licenseOnRestrictedTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("restrictedLicenseForm", restrictedLicenseForm);

		return new ModelAndView("licenseOnRestrictedBranchTrans", "model", model);

	}
	
	@RequestMapping(value = "/licenseOnRestrictedBranchList", method = RequestMethod.GET)
	public ModelAndView licenseOnRestrictedHeadList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		
		List<RestrictedLicense> license = restrictedLicenseDAO.getRestrictedLicenseOnCustList(user.getUserName()).getResultList();

		model.put("user", user);
		model.put("license", license);

		return new ModelAndView("licenseOnRestrictedBranchList", "model", model);

	}
	
	/**
	 * Display warehouse image
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wareHouseImageDisplay", method = RequestMethod.GET)
	public ModelAndView wareHouseImageDisplay(@ModelAttribute WareHouseForm wareHouseForm,
											  @ModelAttribute InventoryForm inventoryForm,
											  ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		List<WareHouse> warehouseList = wareHouseDAO.getWareHouseandStatusList(user.getUserName()).getResultList();		
		
		model.put("user", user);
		model.put("warehouseList", warehouseList);

		return new ModelAndView("wareHouseImageDisplayBranch", "model", model);

	}
	
	/**
	 * Display warehouse image
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wareHouseImageDisplayById", method = {RequestMethod.POST,RequestMethod.GET})
	public String wareHouseImageDisplayById(@ModelAttribute WareHouseForm wareHouseForm, ModelMap model,
			                                RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();	
		if (wareHouseForm.getId() != null) {			
			WareHouse wareHouse = wareHouseDAO.getByWareHouseId(wareHouseForm.getId());
			
			wareHouseForm.setWareHouseName(wareHouse.getWareHouseName());
			wareHouseForm.setAddress(wareHouse.getAddress());
			wareHouseForm.setCapacity(wareHouse.getCapacity());
			wareHouseForm.setCountry(wareHouse.getCountry());
			wareHouseForm.setState(wareHouse.getState());
			wareHouseForm.setSize(wareHouse.getSize());
			
			List<Inventory> existingInventoryList = invenrotyDAO.getInventoryListByWareHouseNCustomerName
					                        (wareHouse.getWareHouseName(), user.getUserName());
			List<Inventory> inventoryList = new ArrayList<Inventory>();
			for(Inventory inventory : existingInventoryList) {
				if(inventory.getImageName() != null) {
					String type= ImageService.getImageType(inventory.getImageName());

					String url = "data:image/"+type+";base64,"
							+ Base64.encodeBase64String(inventory.getImage());
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
	
	/**
	 * Method to display all goods for current user to upload image
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/uploadImageForGoods", method = RequestMethod.GET)
	public ModelAndView uploadImageForGoods(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<Inventory> inventoryList = invenrotyDAO
				.getInventoryBycustomerName(user.getUserName()).getResultList();
		for (Inventory inventory : inventoryList) {
			if(inventory.getImageName() != null) {
			String type= ImageService.getImageType(inventory.getImageName());
			
			String url = "data:image/"+type+";base64,"			
					+ Base64.encodeBase64String(inventory.getImage());
			inventory.setImageName(url);
			}
		}

		model.put("user", user);
		model.put("inventoryList", inventoryList);
		model.put("inventoryForm", inventoryForm);
		return new ModelAndView("uploadImageForGoodsBranch", "model", model);

	}

	/**
	 * Method to save uploaded image for goods
	 * 
	 * @param inventoryForm
	 * @return
	 */
	@RequestMapping(value = "/updateImageForGoods", method = RequestMethod.POST)
	public String updateImageForGoods(ModelMap model,
									  @ModelAttribute InventoryForm inventoryForm,
									  RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		try {
			Inventory inventory = invenrotyDAO.getByInventoryId(inventoryForm
					.getId());
			inventory.setImage(inventoryForm.getFile().getBytes());
			inventory.setImageName(inventoryForm.getFile().getOriginalFilename());
			invenrotyDAO.updateInventory(inventory);

		} catch (Exception e) {
			e.getMessage();
		}
		if(inventoryForm.getFlag() == 0) {
			wareHouseForm.setId(inventoryForm.getWarehouseId());
			
			attributes.addFlashAttribute("wareHouseForm",wareHouseForm);			
			return "redirect:wareHouseImageDisplayById";
		}
		return "redirect:uploadImageForGoods";
	}
	
	@RequestMapping(value = "/createBranchWareHouseMng", method = RequestMethod.GET)
	public ModelAndView createWareHouseMng(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		
		
		List<WareHouseMng> mngList = wareHouseMngDAO.getByPending(user.getUserName())
				.getResultList();

		model.put("user", user);
		if(mngList != null && mngList.size() > 0)
		{
		
		model.put("mngList", mngList);

		
		}
		model.put("wareHouseMngForm", wareHouseMngForm);
		return new ModelAndView("createBranchWareHouseMng", "model", model);

	}

	@RequestMapping(value = "/selectBranchWareHouseMng", method = RequestMethod.GET)
	public ModelAndView selectWareHouseMng(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

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

		return new ModelAndView("selectBranchWareHouseMng", "model", model);

	}

	@RequestMapping(value = "/updateBranchWareHouseMngConfirm", method = RequestMethod.POST)
	public ModelAndView updateClientAdminConfirm(
			@ModelAttribute WareHouseMngForm wareHouseMngForm, ModelMap model,
			RedirectAttributes attribute) {
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

		return new ModelAndView("updateBranchWareHouseMngConfirm", "model", model);

	}

	@RequestMapping(value = "/updateBranchWareHouseMng", method = RequestMethod.POST)
	public ModelAndView updateClientAdmin(ModelMap model,
			@ModelAttribute WareHouseMngForm wareHouseMngForm,
			RedirectAttributes attributes) {

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

		return new ModelAndView("redirect:createBranchWareHouseMng");

	}

	@RequestMapping(value = "/branchWareHouseMngConfirm", method = RequestMethod.POST)
	public ModelAndView wareHouseMngConfirm(
			@ModelAttribute WareHouseMngForm wareHouseMngForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		List<WareHouseMng> head = wareHouseMngDAO.getWareHouseMngList(
				wareHouseMngForm.getMngName()).getResultList();

		List<EndUser> endUser = endUserDAOImpl.findByUsername(
				wareHouseMngForm.getMngName()).getResultList();
		if (endUser.size() != 0 || head.size() != 0) {
			model.put("user", user);
			attributes.addFlashAttribute("success",
					"WareHouse Manager Already Exists");

			return new ModelAndView("redirect:createBranchWareHouseMng");
		} else {

			wareHouseMngForm.setMngName(wareHouseMngForm.getMngName());
			wareHouseMngForm.setCompanyName(wareHouseMngForm.getCompanyName());
			wareHouseMngForm.setCountry(wareHouseMngForm.getCountry());
			wareHouseMngForm.setState(wareHouseMngForm.getState());
			wareHouseMngForm.setCity(wareHouseMngForm.getCity());
			wareHouseMngForm.setAddress(wareHouseMngForm.getAddress());
			wareHouseMngForm.setPincode(wareHouseMngForm.getPincode());
			wareHouseMngForm.setContactNum(wareHouseMngForm.getContactNum());
			wareHouseMngForm
					.setAltContactNum(wareHouseMngForm.getAltContactNum());
			wareHouseMngForm.setEmail(wareHouseMngForm.getEmail());
			wareHouseMngForm.setAltEmail(wareHouseMngForm.getAltEmail());
			wareHouseMngForm
					.setCompanyPrefix(wareHouseMngForm.getCompanyPrefix());
			wareHouseMngForm.setPosition(wareHouseMngForm.getPosition());
			wareHouseMngForm.setGender(wareHouseMngForm.getGender());
			wareHouseMngForm.setDateOfBirth(wareHouseMngForm.getDateOfBirth());
			wareHouseMngForm.setCustomerPrefix(wareHouseMngForm
					.getCustomerPrefix());
			wareHouseMngForm.setManager(wareHouseMngForm.getManager());
			wareHouseMngForm.setManagerEmail(wareHouseMngForm.getManagerEmail());
			wareHouseMngForm
					.setAccExpiryDate(wareHouseMngForm.getAccExpiryDate());

			model.put("wareHouseMngForm", wareHouseMngForm);
			model.put("user", user);

			return new ModelAndView("branchWareHouseMngConfirm", "model", model);
		}
	}

	@RequestMapping(value = "/branchWareHouseMngSave", method = RequestMethod.POST)
	public ModelAndView wareHouseMngSave(
			@ModelAttribute WareHouseMngForm wareHouseMngForm, ModelMap model,
			RedirectAttributes attributes) {
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
		customer.setCustomerPrefix(wareHouseMngForm.getCustomerPrefix());
		customer.setTransactionId(wareHouseMngForm.getTransactionId());
		customer.setAccExpiryDate(wareHouseMngForm.getAccExpiryDate());
		wareHouseMngDAO.insertWareHouseMng(customer);
		Transaction trans = new Transaction();

		trans.setTransactionId(wareHouseMngForm.getTransactionId());
		trans.setTransactionStatus("WareHouse Manager Saved");
		trans.setTransactionType("WareHouse Manager");

		transcationDAOImpl.insertTransaction(trans);

		model.put("wareHouseMngForm", wareHouseMngForm);
		model.put("user", user);

		return new ModelAndView("branchWareHouseMngTransaction", "model", model);

	}

	@RequestMapping(value = "/branchWareHouseMngTransaction", method = RequestMethod.GET)
	public ModelAndView wareHouseMngTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("wareHouseMngForm", wareHouseMngForm);

		return new ModelAndView("branchWareHouseMngTransaction", "model", model);

	}
	
	@RequestMapping(value = "/branchWareHouseMngList", method = RequestMethod.GET)
	public ModelAndView wareHouseMngList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		
		List<WareHouseMng> mngList = wareHouseMngDAO.getWareHouseMngFullList(
				user.getUserName()).getResultList();

		model.put("user", user);
		model.put("mngList", mngList);

		return new ModelAndView("branchWareHouseMngList", "model", model);

	}

}
