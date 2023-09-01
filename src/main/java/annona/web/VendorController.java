package annona.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
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

import annona.dao.CorrespondentDAO;
import annona.dao.DisputeDAO;
import annona.dao.DisputeReportsDAO;
import annona.dao.EndUserDAO;
import annona.dao.FundsDistributeDAO;
import annona.dao.InvoiceDAO;
import annona.dao.LetterOfCreditDAO;
import annona.dao.MasterPlanDAO;
import annona.dao.NewBuyerDAO;
import annona.dao.PurchaseOrderDAO;
import annona.dao.TransactionDAO;
import annona.dao.VendorUploadDAO;
import annona.dao.WareHouseDAO;
import annona.domain.Correspondent;
import annona.domain.Dispute;
import annona.domain.DisputeReports;
import annona.domain.EndUser;
import annona.domain.FundsDistribute;
import annona.domain.Invoice;
import annona.domain.LetterOfCredit;
import annona.domain.MasterPlan;
import annona.domain.PurchaseOrder;
import annona.domain.Transaction;
import annona.domain.UploadedFile;
import annona.domain.VendorFileValidator;
import annona.domain.VendorUploadedFile;
import annona.form.DisputeReportsForm;
import annona.form.EndUserForm;
import annona.form.InvoiceForm;
import annona.form.LetterOfCreditForm;
import annona.form.NewBuyerForm;
import annona.form.PurchaseOrderForm;
import annona.form.VendorUploadedFileForm;
import annona.form.WareHouseForm;
import annona.services.ImageService;
import annona.services.UploadService;
import annona.utility.KeyGenerator;

@Controller
@RequestMapping("/vendor")
public class VendorController implements ServletContextAware {

	@Autowired
	EndUserDAO endUserDAOImpl;

	@Autowired
	PurchaseOrderDAO purchaseOrderDAO;

	@Autowired
	WareHouseForm wareHouseForm;

	@Autowired
	WareHouseDAO wareHouseDAO;

	@Autowired
	InvoiceDAO invoiceDAO;

	@Autowired
	InvoiceForm invoiceForm;

	@Autowired
	PurchaseOrderForm purchaseOrderForm;

	@Autowired
	LetterOfCreditForm letterOfCreditForm;

	@Autowired
	LetterOfCreditDAO letterOfCreditDAO;

	@Autowired
	CorrespondentDAO correspondentDAO;

	@Autowired
	MasterPlanDAO masterPlanDAO;

	@Autowired
	FundsDistributeDAO fundsDistributeDAO;

	@Autowired
	EndUserForm endUserForm;

	@Autowired
	TransactionDAO transcationDAO;

	@Autowired
	DisputeDAO disputeDAO;

	@Autowired
	NewBuyerForm newBuyerForm;

	@Autowired
	NewBuyerDAO newBuyerDAOImpl;

	@Autowired
	TransactionDAO transcationDAOImpl;

	@Autowired
	VendorFileValidator vendorFileValidator;

	@Autowired
	VendorUploadDAO vendorUploadDaoImpl;

	@Autowired
	UploadService uploadService;

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	DisputeReportsDAO disputeReportsDAO;

	@Autowired
	DisputeReportsForm disputeReportsForm;

	@Autowired
	DateService dateService;

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
		return "redirect:vendors";
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

		return "redirect:vendors";
	}

	@RequestMapping(value = "/vendors", method = RequestMethod.GET)
	public ModelAndView showVendorDashBoard(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("vendorsPage", "model", model);

	}

	@RequestMapping(value = "/editVendorProfile", method = RequestMethod.GET)
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

		return new ModelAndView("editVendorProfile", "model", model);

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
		return "redirect:editVendorProfile?id=" + endUserForm.getId();
	}

	@RequestMapping(value = "/confirmEditVendorProfile", method = RequestMethod.POST)
	public ModelAndView confirmEditAdminProfile(ModelMap model,
			@ModelAttribute EndUserForm endUserForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("confirmEditVendorProfile", "model", model);

	}

	@RequestMapping(value = "/updateVendorDetails", method = RequestMethod.POST)
	public ModelAndView updateAdminDetails(ModelMap model,
			@ModelAttribute EndUserForm endUserForm, BindingResult result,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		
		endUserForm.setTransactionId(KeyGenerator.generateTransactionKey());

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

		return new ModelAndView("updateVendorSuccess", "model", model);

	}

	@RequestMapping(value = "/editVendorPWD", method = RequestMethod.GET)
	public ModelAndView editAdminPWD(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser userProfile = endUserDAOImpl.findId(id);

		endUserForm.setId(userProfile.getId());

		endUserForm.setTransactionId(userProfile.getTransactionId());

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("editVendorPWD", "model", model);

	}

	@RequestMapping(value = "/updateEditVendorPWD", method = RequestMethod.POST)
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

		return new ModelAndView("updateVendorSuccess", "model", model);

	}

	@RequestMapping(value = "/vPoListForRenewel", method = RequestMethod.GET)
	public ModelAndView vPoListForRenewel(ModelMap model) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		List<PurchaseOrder> purchase = purchaseOrderDAO
				.getPurchaseOrderForRenewel(user.getUserName()).getResultList();

		if (purchase != null && purchase.size() > 0) {
			model.put("purchase", purchase);
			return new ModelAndView("vPoListForRenewel", "model", model);
		} else {
			return new ModelAndView("noDataFoundVendor", "model", model);
		}
	}

	@RequestMapping(value = "/vPoForRenewel", method = RequestMethod.GET)
	public ModelAndView vPoForRenewel(@RequestParam("id") Long id,
			ModelMap model) {

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
		purchaseOrderForm.setCustomerBranchEmail(purchase
				.getCustomerBranchEmail());
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

		return new ModelAndView("vPoForRenewel", "model", model);

	}

	@RequestMapping(value = "/vPoForRenewelConfirm", method = RequestMethod.POST)
	public ModelAndView vPoForRenewelConfirm(ModelMap model,
			@ModelAttribute PurchaseOrderForm purchaseOrderForm,
			BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		purchaseOrderForm.setCustomerName(purchaseOrderForm.getCustomerName());
		purchaseOrderForm.setCustomerHeadName(purchaseOrderForm
				.getCustomerHeadName());
		purchaseOrderForm.setMasterKey(purchaseOrderForm.getMasterKey());
		purchaseOrderForm.setGoods(purchaseOrderForm.getGoods());
		purchaseOrderForm.setQuantity(purchaseOrderForm.getQuantity());
		purchaseOrderForm.setSupplierName(purchaseOrderForm.getSupplierName());

		purchaseOrderForm
				.setTransactionId(purchaseOrderForm.getTransactionId());
		purchaseOrderForm.setAmount(purchaseOrderForm.getAmount());
		purchaseOrderForm.setFlag(purchaseOrderForm.getFlag());
		purchaseOrderForm.setCustomerHeadEmail(purchaseOrderForm
				.getCustomerHeadEmail());
		purchaseOrderForm.setCustomerBranchEmail(purchaseOrderForm
				.getCustomerBranchEmail());
		purchaseOrderForm
				.setSupplierEmail(purchaseOrderForm.getSupplierEmail());
		purchaseOrderForm.setPoKey(purchaseOrderForm.getPoKey());
		purchaseOrderForm.setInsuranceAmount(purchaseOrderForm
				.getInsuranceAmount());
		purchaseOrderForm.setInsuranceDetails(purchaseOrderForm
				.getInsuranceDetails());
		purchaseOrderForm
				.setInsuranceType(purchaseOrderForm.getInsuranceType());
		purchaseOrderForm.setStartDate(purchaseOrderForm.getStartDate());
		purchaseOrderForm.setEndDate(purchaseOrderForm.getEndDate());

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("vPoForRenewelConfirm", "model", model);

	}

	@RequestMapping(value = "/vPoForRenewelPost", method = RequestMethod.POST)
	public ModelAndView vPoForRenewelPost(ModelMap model,
			@ModelAttribute PurchaseOrderForm purchaseOrderForm,
			BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchase = purchaseOrderDAO
				.getByPurchaseId(purchaseOrderForm.getId());

		purchase.setInsuranceAmount(purchaseOrderForm.getInsuranceAmount());
		purchase.setInsuranceDetails(purchaseOrderForm.getInsuranceDetails());
		purchase.setInsuranceType(purchaseOrderForm.getInsuranceType());
		purchase.setStartDate(purchaseOrderForm.getStartDate());
		purchase.setEndDate(purchaseOrderForm.getEndDate());
		purchaseOrderDAO.updatePo(purchase);

		Transaction trans = new Transaction();
		trans.setTransactionId(purchaseOrderForm.getTransactionId());
		trans.setTransactionStatus("Purchase Order Insurance Renewal By Vendor");
		trans.setTransactionType("Submitted Successfully");

		transcationDAO.insertTransaction(trans);

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("vPoForRenewelTransaction", "model", model);
	}

	@RequestMapping(value = "/vPoForRenewelTransaction", method = RequestMethod.GET)
	public ModelAndView vPoForRenewelTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("vPoForRenewelTransaction", "model", model);

	}

	@RequestMapping(value = "/vPoListForApproval", method = RequestMethod.GET)
	public ModelAndView poListForApproval(ModelMap model) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		List<PurchaseOrder> purchase = purchaseOrderDAO
				.getPurchaseOrderByvStatus(user.getUserName()).getResultList();

		if (purchase != null && purchase.size() > 0) {
			model.put("purchase", purchase);
			return new ModelAndView("vPoListForApproval", "model", model);
		} else {
			return new ModelAndView("noDataFoundVendor", "model", model);
		}

	}

	@RequestMapping(value = "/vPoForApproval", method = RequestMethod.GET)
	public ModelAndView poForApproval(@RequestParam("id") Long id,
			ModelMap model) {

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
		purchaseOrderForm.setCustomerBranchEmail(purchase
				.getCustomerBranchEmail());
		purchaseOrderForm.setSupplierEmail(purchase.getSupplierEmail());
		purchaseOrderForm.setStartDate(purchase.getStartDate());
		purchaseOrderForm.setEndDate(purchase.getEndDate());
		purchaseOrderForm.setPoKey(purchase.getPoKey());
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("vPoForApproval", "model", model);

	}

	@RequestMapping(value = "/vPoForApprovalConfirm", method = RequestMethod.POST)
	public ModelAndView poForApprovalConfirm(ModelMap model,
			@ModelAttribute PurchaseOrderForm purchaseOrderForm,
			BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		purchaseOrderForm.setCustomerName(purchaseOrderForm.getCustomerName());
		purchaseOrderForm.setCustomerHeadName(purchaseOrderForm
				.getCustomerHeadName());
		purchaseOrderForm.setMasterKey(purchaseOrderForm.getMasterKey());
		purchaseOrderForm.setStatus(purchaseOrderForm.getStatus());
		purchaseOrderForm.setGoods(purchaseOrderForm.getGoods());
		purchaseOrderForm.setQuantity(purchaseOrderForm.getQuantity());
		purchaseOrderForm.setSupplierName(purchaseOrderForm.getSupplierName());
		purchaseOrderForm.setComment(purchaseOrderForm.getComment());
		purchaseOrderForm
				.setTransactionId(purchaseOrderForm.getTransactionId());
		purchaseOrderForm.setAmount(purchaseOrderForm.getAmount());
		purchaseOrderForm.setFlag(purchaseOrderForm.getFlag());
		purchaseOrderForm.setCustomerHeadEmail(purchaseOrderForm
				.getCustomerHeadEmail());
		purchaseOrderForm.setCustomerBranchEmail(purchaseOrderForm
				.getCustomerBranchEmail());
		purchaseOrderForm
				.setSupplierEmail(purchaseOrderForm.getSupplierEmail());
		purchaseOrderForm.setPoKey(purchaseOrderForm.getPoKey());
		purchaseOrderForm.setInsuranceAmount(purchaseOrderForm
				.getInsuranceAmount());
		purchaseOrderForm.setInsuranceDetails(purchaseOrderForm
				.getInsuranceDetails());
		purchaseOrderForm
				.setInsuranceType(purchaseOrderForm.getInsuranceType());
		purchaseOrderForm.setStartDate(purchaseOrderForm.getStartDate());
		purchaseOrderForm.setEndDate(purchaseOrderForm.getEndDate());

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("vPoForApprovalConfirm", "model", model);

	}

	@RequestMapping(value = "/vPoForApprovalPost", method = RequestMethod.POST)
	public ModelAndView poForApprovalPost(ModelMap model,
			@ModelAttribute PurchaseOrderForm purchaseOrderForm,
			BindingResult result, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		purchaseOrderForm
				.setTransactionId(purchaseOrderForm.getTransactionId());
		purchaseOrderForm.setAmount(purchaseOrderForm.getAmount());
		purchaseOrderForm.setMasterKey(purchaseOrderForm.getMasterKey());
		purchaseOrderForm.setCustomerName(purchaseOrderForm.getCustomerName());
		purchaseOrderForm.setCustomerHeadEmail(purchaseOrderForm
				.getCustomerHeadEmail());
		purchaseOrderForm.setCustomerBranchEmail(purchaseOrderForm
				.getCustomerBranchEmail());
		purchaseOrderForm
				.setSupplierEmail(purchaseOrderForm.getSupplierEmail());
		purchaseOrderForm.setPoKey(purchaseOrderForm.getPoKey());

		if (purchaseOrderForm.getvStatus().equals("Approved")) {
			PurchaseOrder purchase = purchaseOrderDAO
					.getByPurchaseId(purchaseOrderForm.getId());

			purchase.setvStatus(purchaseOrderForm.getvStatus());
			purchase.setGoodsStatus("Send");
			purchase.setStatus("Approved");
//			Date vDate = DateService.loginDate;
//			Date vDate = dateService.getCurrentSavedLoginDate();
			purchase.setVendorAppDate(DateService.loginDate);
			purchase.setvComment(purchaseOrderForm.getvComment());
			purchase.setInsuranceAmount(purchaseOrderForm.getInsuranceAmount());
			purchase.setInsuranceDetails(purchaseOrderForm
					.getInsuranceDetails());
			purchase.setInsuranceType(purchaseOrderForm.getInsuranceType());
			purchase.setStartDate(purchaseOrderForm.getStartDate());
			purchase.setEndDate(purchaseOrderForm.getEndDate());
			purchaseOrderDAO.updatePo(purchase);
		} else {
			PurchaseOrder purchase = purchaseOrderDAO
					.getByPurchaseId(purchaseOrderForm.getId());
			purchase.setvStatus(purchaseOrderForm.getvStatus());
			purchase.setvComment(purchaseOrderForm.getvComment());
			purchaseOrderDAO.updatePo(purchase);
		}

		if (purchaseOrderForm.getFlag().equals(0)) {
			if (purchaseOrderForm.getvStatus().equals("Rejected")) {
				MasterPlan plan = masterPlanDAO.getMasterPlanByMasterKey(
						purchaseOrderForm.getMasterKey()).getSingleResult();
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
			if (purchaseOrderForm.getvStatus().equals("Rejected")) {
				FundsDistribute funds = fundsDistributeDAO
						.getFundsListByKeyAndName(
								purchaseOrderForm.getMasterKey(),
								purchaseOrderForm.getCustomerName())
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

		String stat = purchaseOrderForm.getvStatus();
		String email = purchaseOrderForm.getCustomerHeadEmail();
		String customerheadname = purchaseOrderForm.getCustomerHeadName();
		String vendor = purchaseOrderForm.getSupplierName();
		String pokey = purchaseOrderForm.getPoKey();
		String masterkey = purchaseOrderForm.getMasterKey();
		try {
			if (stat.equals("Approved")) {
				String tex = "Purchase Order  Details Notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + customerheadname + "\n\nVendor:"
						+ vendor + "has accepted the purchase order request."
						+ "\n\n Your Pokey Detail is as follows." + "\n"
						+ "\n\nMaterKey:" + masterkey + "\n\nPoKey:" + pokey
						+ "\n\n\nRegards,\n Bank");
				System.out.println("" + email + customerheadname);
				mailSender.send(emails);
				SimpleMailMessage emails1 = new SimpleMailMessage();
				emails1.setTo(email);

			} else if (stat.equals("Rejected")) {
				String tex = "Purchase Order  Details Notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + customerheadname + "\n\nVendor:"
						+ vendor
						+ ",\n\n  has rejected the purchase order request. " +

						",\n\n Your Pokey Detail is as follows. " + "\n" + "\n"
						+ "\n\nPoKey:" + pokey + "\n\n\nRegards,\nBank");
				mailSender.send(emails);
			}

			model.put("user", user);
			model.put("purchaseOrderForm", purchaseOrderForm);

		} catch (Exception e) {
			System.out.println(e.getMessage() + e);

		}
		return new ModelAndView("vPoForApprovalTransaction", "model", model);
	}

	@RequestMapping(value = "/vPoForApprovalTransaction", method = RequestMethod.GET)
	public ModelAndView poForApprovalTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("vPoForApprovalTransaction", "model", model);

	}

	@RequestMapping(value = "/vendorsPoList", method = RequestMethod.GET)
	public ModelAndView fullPoList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PurchaseOrder> poList = purchaseOrderDAO.getPoListBySupplierName(
				user.getUserName()).getResultList();

		model.put("user", user);
		model.put("poList", poList);

		return new ModelAndView("vendorsPoList", "model", model);

	}

	@RequestMapping(value = "/vendorPoApprovalList", method = RequestMethod.GET)
	public ModelAndView VendorPoList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PurchaseOrder> poList = purchaseOrderDAO.getPoListByDocument(
				user.getUserName()).getResultList();

		model.put("user", user);
		model.put("poList", poList);

		return new ModelAndView("vendorPoApprovalList", "model", model);

	}

	@RequestMapping(value = "/vendorfileUploadForm", method = RequestMethod.GET)
	public ModelAndView vendorPOFileupload(@RequestParam Long id,
			ModelMap model,
			@ModelAttribute VendorUploadedFileForm vendorUploadedFileForm,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		PurchaseOrder purchaseOrder = purchaseOrderDAO.getByPurchaseId(id);

		vendorUploadedFileForm.setPoKey(purchaseOrder.getPoKey());

		model.put("user", user);

		model.put("vendorUploadedFileForm", vendorUploadedFileForm);

		return new ModelAndView("vendorfileUploadForm", "model", model);
	}

	@RequestMapping("/fileUpload1")
	public ModelAndView fileUploadedForTarding(ModelMap model,
			RedirectAttributes attribute,
			@ModelAttribute VendorUploadedFileForm vendorUploadedFileForm,
			BindingResult result) throws RuntimeException, IOException {

		EndUser user = getCurrentLoggedUserDetails();
		
		vendorUploadedFileForm.setTransactionId(KeyGenerator.generateTransactionKey());

		VendorUploadedFile vendorFileUpload = new VendorUploadedFile();
		/*
		 * List<MultipartFile> files = vendorUploadedFileForm.getFiles(); for
		 * (MultipartFile multipartFile : files) { String fileName =
		 * multipartFile.getOriginalFilename();
		 * 
		 * if (fileName != null && !fileName.equals("")) {
		 * log.info("These are the File" + fileName);
		 * uploadService.saveImage(servletContext.getRealPath("/") + "/img" +
		 * "/" + fileName, multipartFile);
		 * 
		 * }
		 */
		/*
		 * PurchaseOrder purchaseorder = purchaseOrderDAO.getPoListByPoKey(
		 * vendorUploadedFileForm.getPoKey()).getSingleResult();
		 * purchaseorder.setFileName(servletContext.getRealPath("/") + "/img" +
		 * "/" + fileName);
		 * vendorFileUpload.setFileName(servletContext.getRealPath("/") + "/img"
		 * + "/" + fileName);
		 */

		List<MultipartFile> files = vendorUploadedFileForm.getFiles();
		Set<byte[]> filesList = new HashSet<byte[]>();
		Set<String> fileNameList = new HashSet<String>();
		for (MultipartFile multipartFile : files) {
			filesList.add(multipartFile.getBytes());
			fileNameList.add(multipartFile.getOriginalFilename());
		}
		vendorFileUpload.setFiles(filesList);
		vendorFileUpload.setFileNames(fileNameList);

		PurchaseOrder purchaseorder = purchaseOrderDAO.getPoListByPoKey(
				vendorUploadedFileForm.getPoKey()).getSingleResult();
		vendorFileUpload.setCustomerName(vendorUploadedFileForm
				.getCustomerName());
		vendorFileUpload.setDocument(vendorUploadedFileForm.getDocument());
		vendorFileUpload.setPoKey(vendorUploadedFileForm.getPoKey());
		vendorFileUpload.setMasterKey(vendorUploadedFileForm.getMasterKey());
		purchaseorder.setDocument(vendorFileUpload.getDocument());
		purchaseorder.setForWard("No");
		purchaseorder.setFileNames(fileNameList);
		purchaseOrderDAO.updatePo(purchaseorder);
		vendorFileUpload.setReason(vendorUploadedFileForm.getReason());
		vendorFileUpload.setPoKey(vendorUploadedFileForm.getPoKey());
		vendorFileUpload.setMasterKey(vendorUploadedFileForm.getMasterKey());
		vendorFileUpload.setTransactionId(vendorUploadedFileForm
				.getTransactionId());

		vendorFileUpload.setStatus("Pending");

		Date date = DateService.loginDate;
		vendorFileUpload.setUploadDate(date);
		vendorUploadDaoImpl.createUser(vendorFileUpload);

		Transaction trans = new Transaction();

		trans.setTransactionId(vendorUploadedFileForm.getTransactionId());
		trans.setTransactionStatus("Upload Document");
		trans.setTransactionType("Uploaded Successfully");

		transcationDAOImpl.insertTransaction(trans);

		model.put("user", user);

		attribute.addFlashAttribute("success", "Saved Successfully. ");
		return new ModelAndView("vendorUploadFileSuccess", "model", model);

	}

	@RequestMapping(value = "/vendorUploadedDocumentList", method = RequestMethod.GET)
	public ModelAndView VendorUploadedDocumentList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		Collection<VendorUploadedFile> poList = vendorUploadDaoImpl.findByName(user.getUserName()).getResultList();

		model.put("user", user);
		model.put("poList", poList);

		return new ModelAndView("vendorUploadedDocumentList", "model", model);

	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@RequestMapping(value = "/poPaymentVendorFullList", method = RequestMethod.GET)
	public ModelAndView poPaymentBankList(ModelMap model,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		List<PurchaseOrder> purchaseList = purchaseOrderDAO
				.getPoListBySupplierNameAndClearence(user.getUserName())
				.getResultList();
		if (purchaseList != null && purchaseList.size() > 0) {
			model.put("user", user);
			model.put("purchaseList", purchaseList);
		}
		return new ModelAndView("poPaymentVendorFullList");

	}

	@RequestMapping(value = "/poPaymentVendorClear", method = RequestMethod.GET)
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

			model.put("user", user);
			model.put("letterOfCreditForm", letterOfCreditForm);
		} else {
			model.put("user", user);
			attributes.addFlashAttribute("success",
					"Please Check Tpe Of Transfer ");
			return new ModelAndView("redirect:poPaymentVendorFullList");
		}
		return new ModelAndView("poPaymentVendorClear", "model", model);

	}

	@RequestMapping(value = "/invoicePaymentVendorList", method = RequestMethod.GET)
	public ModelAndView poPaymentHeadList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getInvoiceListForBuyer(
				user.getUserName()).getResultList();

		if (invoiceList != null && invoiceList.size() > 0) {
			model.put("invoiceList", invoiceList);
			return new ModelAndView("invoicePaymentVendorList", "model", model);
		}

		else {
			return new ModelAndView("noDataFoundVendor", "model", model);
		}

	}

	@RequestMapping(value = "/invoicePaymentVendor", method = RequestMethod.GET)
	public ModelAndView poPaymentHead(@RequestParam Long id, ModelMap model,
			RedirectAttributes attributes) {

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
		invoiceForm.setCustomerHeadEmail(invoice.getCustomerHeadEmail());
		invoiceForm.setCustomerBranchEmail(invoice.getCustomerBranchEmail());
		invoiceForm.setBuyerEmail(invoice.getBuyerEmail());

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("invoicePaymentVendor", "model", model);
	}

	@RequestMapping(value = "/invoicePaymentVendorConfirm", method = RequestMethod.POST)
	public ModelAndView invoicePaymentVendorConfirm(ModelMap model,
			@ModelAttribute InvoiceForm invoiceForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		invoiceForm.setId(invoiceForm.getId());
		invoiceForm.setCustomerName(invoiceForm.getCustomerName());
		invoiceForm.setBuyerName(invoiceForm.getBuyerName());
		invoiceForm.setPoKey(invoiceForm.getPoKey());
		invoiceForm.setTenure(invoiceForm.getTenure());
		invoiceForm.setAmount(invoiceForm.getAmount());
		invoiceForm.setFunalAmt(invoiceForm.getFunalAmt());
		invoiceForm.setTransactionId(invoiceForm.getTransactionId());
		invoiceForm.setCustomerHeadEmail(invoiceForm.getCustomerHeadEmail());
		invoiceForm
				.setCustomerBranchEmail(invoiceForm.getCustomerBranchEmail());
		invoiceForm.setBuyerEmail(invoiceForm.getBuyerEmail());
		invoiceForm.setTypeOfTransaction(invoiceForm.getTypeOfTransaction());
		invoiceForm.setChequeNum(invoiceForm.getChequeNum());
		invoiceForm.setTransactionId(invoiceForm.getTransactionId());

		model.put("purchaseOrderForm", invoiceForm);
		model.put("user", user);

		return new ModelAndView("invoicePaymentVendorConfirm", "model", model);

	}

	@RequestMapping(value = "/invoicePaymentVendorSave", method = RequestMethod.POST)
	public ModelAndView invoicePaymentVendorSave(ModelMap model,
			@ModelAttribute InvoiceForm invoiceForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		Invoice invoice = invoiceDAO.getByInvoiceId(invoiceForm.getId());

		invoice.setTypeOfTransaction(invoiceForm.getTypeOfTransaction());
		invoice.setChequeNum(invoiceForm.getChequeNum());
		invoice.setTransStatus("PayDone");
		if (invoice.getTypeOfTransaction().equals("transfer")) {
			invoice.setTransResult("PayReceived");
			invoice.setRequestMoney("PayReceived");
		} else {
			invoice.setTransResult("PayClearence");
			invoice.setRequestMoney("PayClearence");
		}

		invoiceDAO.updateInvoice(invoice);

		Transaction trans = new Transaction();

		trans.setTransactionId(invoiceForm.getTransactionId());
		trans.setTransactionStatus("Invoice Payment");
		trans.setTransactionType("Sent Successfully");

		transcationDAOImpl.insertTransaction(trans);

		model.put("purchaseOrderForm", purchaseOrderForm);
		model.put("user", user);

		return new ModelAndView("invoicePaymentVendorTrans", "model", model);

	}

	@RequestMapping(value = "/invoicePaymentVendorTrans", method = RequestMethod.GET)
	public ModelAndView invoicePaymentVendorTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("invoiceForm", invoiceForm);

		return new ModelAndView("invoicePaymentVendorTrans", "model", model);

	}

	@RequestMapping(value = "/requestMoneyInvoiceVendorFullList", method = RequestMethod.GET)
	public ModelAndView requestMoneyInvoiceHeadFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getInvoiceListByBuyerName(
				user.getUserName()).getResultList();

		if (invoiceList != null && invoiceList.size() > 0) {
			model.put("invoiceList", invoiceList);
			return new ModelAndView("requestMoneyInvoiceVendorFullList",
					"model", model);
		} else {
			return new ModelAndView("noDataFoundVendor", "model", model);
		}

	}

	@RequestMapping(value = "/disputeSuppList", method = RequestMethod.GET)
	public ModelAndView disputeBankList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		List<Dispute> disputeList = disputeDAO.getDisputeSupplierList(
				user.getUserName()).getResultList();
		if (disputeList != null && disputeList.size() > 0) {
			model.put("disputeList", disputeList);

			return new ModelAndView("disputeSuppList", "model", model);
		} else {
			return new ModelAndView("noDataFoundVendor", "model", model);
		}

	}

	@RequestMapping(value = "/custWareHouseVendorList", method = RequestMethod.GET)
	public ModelAndView apprCustAppMngList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<PurchaseOrder> purchaseList = purchaseOrderDAO
				.getPoListBySupplierNameAndGoodsStatus(user.getUserName())
				.getResultList();

		if (purchaseList != null && purchaseList.size() > 0) {

			model.put("purchaseList", purchaseList);
			return new ModelAndView("custWareHouseVendorList", "model", model);
		} else {
			return new ModelAndView("noDataFoundVendor", "model", model);
		}

	}

	@RequestMapping(value = "/custWareHouseVendorSend", method = RequestMethod.GET)
	public ModelAndView invoiceVendorPayCleared(@RequestParam Long id,
			ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		
		PurchaseOrderForm  purchaseOrderForm  = new PurchaseOrderForm ();

		PurchaseOrder po = purchaseOrderDAO.getByPurchaseId(id);

		po.setGoodsStatus("Sent");
		
		purchaseOrderForm.setCustomerHeadName(po.getCustomerHeadName());
		purchaseOrderForm.setSupplierName(po.getSupplierName());
		purchaseOrderForm.setAmount(po.getAmount());
		purchaseOrderForm.setMasterKey(po.getMasterKey());
		purchaseOrderForm.setGoods(po.getGoods());
		purchaseOrderForm.setQuantity(po.getQuantity());

//		Date sent = DateService.loginDate;
//		Date sent = dateService.getCurrentSavedLoginDate();
		po.setSentDate(DateService.loginDate);
		purchaseOrderDAO.updatePo(po);

		model.put("user", user);
		model.put("purchaseOrderForm", purchaseOrderForm);

		return new ModelAndView("venderWareHouseTrans");

	}

	@RequestMapping(value = "/vendorHelp", method = RequestMethod.GET)
	public ModelAndView adminHelp(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("vendorHelp", "model", model);

	}

	@RequestMapping(value = "/addOrModifyReportVendorList", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<DisputeReports> disputeList = disputeReportsDAO
				.getDisputeReportsOnCustAndAcceptList(user.getUserName())
				.getResultList();

		if (disputeList != null && disputeList.size() > 0) {
			model.put("disputeList", disputeList);
			return new ModelAndView("addOrModifyReportVendorList", "model",
					model);
		} else {
			return new ModelAndView("noDataFoundVendor", "model", model);
		}

	}

	@RequestMapping(value = "/addOrModifyReportAccept", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportAccept(@RequestParam Long id,
			ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		DisputeReports dispute = disputeReportsDAO.getByDisputeReportsId(id);

		dispute.setAccept("Accepted");
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

		disputeReportsDAO.updateDisputeReports(dispute);

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		attributes.addFlashAttribute("success", "Accepted Successfully");

		return new ModelAndView("redirect:addOrModifyReportVendorList");
	}

	@RequestMapping(value = "/addOrModifyReportVendor", method = RequestMethod.GET)
	public ModelAndView addOrModifyReport(@RequestParam Long id,
			ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		DisputeReports dispute = disputeReportsDAO.getByDisputeReportsId(id);

		disputeReportsForm.setCustomerName(dispute.getCustomerName());
		disputeReportsForm.setPokey(dispute.getPokey());
		disputeReportsForm.setSupplierName(dispute.getSupplierName());
		disputeReportsForm.setGoods(dispute.getGoods());
		disputeReportsForm.setInsDetails(dispute.getInsDetails());
		disputeReportsForm.setCost(dispute.getCost());
		disputeReportsForm.setGoodsDetails(dispute.getGoodsDetails());
		disputeReportsForm.setTransactionId(dispute.getTransactionId());
		disputeReportsForm.setDisputekey(dispute.getDisputekey());
        disputeReportsForm.setInStartDate(dispute.getInStartDate());
		disputeReportsForm.setInsEndDate(dispute.getInsEndDate());

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		return new ModelAndView("addOrModifyReportVendor", "model", model);
	}

	@RequestMapping(value = "/addOrModifyReportVendorConfirm", method = RequestMethod.POST)
	public ModelAndView addOrModifyReportConfirm(ModelMap model,
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

		model.put("disputeReportsForm", disputeReportsForm);
		model.put("user", user);

		return new ModelAndView("addOrModifyReportVendorConfirm", "model",
				model);

	}

	@RequestMapping(value = "/addOrModifyReportVendorPost", method = RequestMethod.POST)
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
			disp.setAccept("NotAccepted");

			disputeReportsDAO.updateDisputeReports(disp);

		}

		Transaction trans = new Transaction();
		trans.setTransactionId(disputeReportsForm.getTransactionId());
		trans.setTransactionStatus("Dispute");
		trans.setTransactionType("Submitted Successfully");

		model.put("disputeReportsForm", disputeReportsForm);
		model.put("user", user);

		return new ModelAndView("addOrModifyReportVendorTrans", "model", model);

	}

	@RequestMapping(value = "/addOrModifyReportVendorTrans", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		return new ModelAndView("addOrModifyReportVendorTrans", "model", model);

	}

	@RequestMapping(value = "/addOrModifyReportVendorFullList", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportVendorFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<DisputeReports> disputeList = disputeReportsDAO
				.getDisputeReportsVendorList(user.getUserName())
				.getResultList();

		if (disputeList != null && disputeList.size() > 0) {
			model.put("disputeList", disputeList);
			return new ModelAndView("addOrModifyReportVendorFullList", "model",
					model);
		} else {
			return new ModelAndView("noDataFoundVendor", "model", model);
		}

	}

	@RequestMapping(value = "/addOrModifyReportVendorView", method = RequestMethod.GET)
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

		return new ModelAndView("addOrModifyReportVendorView", "model", model);
	}

	@RequestMapping(value = "/addOrModifyReportVendorCompare", method = RequestMethod.GET)
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
		
		Float total= dispute.getDefectQty() - dispute.getSuppDefectQty();
		Float total1= dispute.getRepairCost() - dispute.getSuppRepairCost();

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);
		model.put("total", total);
		model.put("total1", total1);

		return new ModelAndView("addOrModifyReportVendorCompare", "model",
				model);
	}

}
