package annona.trade.web;

import annona.dao.*;
import annona.domain.ClientAdmin;
import annona.domain.Company;
import annona.domain.CustomerBankDetails;
import annona.domain.EndUser;
import annona.form.ClientAdminForm;
import annona.form.CompanyForm;
import annona.form.EndUserForm;
import annona.services.DateService;
import annona.trade.dao.*;
import annona.trade.domain.*;
import annona.trade.form.*;
import annona.web.ApprovalMngController;
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
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/tAppMng")
public class TradeApprovalMngController {

	@Autowired
	EndUserDAO endUserDAOImpl;
	@Autowired
	EndUserForm endUserForm;

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	NotificationDAO notificationDAO;

	@Autowired
	TTransactionDAO transcationDAOImpl;

	@Autowired
	BuyerTradeForm buyerTradeForm;
	@Autowired
	SupplierTradeForm supplierTradeForm;

	@Autowired
	BuyerTradeDAO buyerTradeDAO;

	@Autowired
	SupplierTradeDAO supplierTradeDAO;

	@Autowired
	TMasterPlanDAO tmasterPlanDAO;

	@Autowired
	TCollateralDAO tcollateralDAO;

	@Autowired
	TMasterPlanForm tMasterPlanForm;

	@Autowired
	TBuyingCostDAO tbuyingCostDAO;

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

	@Autowired
	ClientAdminDAO clientAdminDAO;

	@Autowired
	ClientAdminForm clientAdminForm;

	@Autowired
	TradeNotificationDAO tradeNotificationDAO;

	@Autowired
	CompanyDAO companyDAO;

	@Autowired
	CompanyForm companyForm;

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
		String url = "data:image/JPG;base64," + Base64.encodeBase64String(enUser.getImage());
		enUser.setImageName(url);

		return enUser;
	}

	private EndUser getCurrentLoggedUserDetails() {

		EndUser endUser = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		return endUser;

	}

	@RequestMapping(value = "/themeChange", method = RequestMethod.GET)
	public String getThemeChange(HttpServletRequest request, HttpServletResponse response) {

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

		return "redirect:tApprMng";
	}

	@RequestMapping(value = "/getLocaleLang", method = RequestMethod.GET)
	public String getLocaleLang(HttpServletRequest request, HttpServletResponse response) {

		log.info("Received request for locale change");
		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		LocaleResolver localeResolver = new CookieLocaleResolver();
		Locale locale = new Locale(request.getParameter("lang"));
		localeResolver.setLocale(request, response, locale);
		user.setPrefferedLanguage(request.getParameter("lang"));

		endUserDAOImpl.mergeUser(user);

		return "redirect:tApprMng";
	}

	@RequestMapping(value = "/tApprMng", method = RequestMethod.GET)
	public ModelAndView showApprMngDashBoard(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("tApprMngPage", "model", model);

	}

	@RequestMapping(value = "/editTradeAppmngProfile", method = RequestMethod.GET)
	public ModelAndView editAdminProfile(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser userProfile = endUserDAOImpl.findId(id);
		String url = "data:image/JPG;base64," + Base64.encodeBase64String(userProfile.getImage());
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

		return new ModelAndView("editTradeAppmngProfile", "model", model);

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
		return "redirect:editTradeAppmngProfile?id=" + endUserForm.getId();
	}

	@RequestMapping(value = "/confirmEditTradeAppMngProfile", method = RequestMethod.POST)
	public ModelAndView confirmEditAdminProfile(ModelMap model, @ModelAttribute EndUserForm endUserForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("confirmEditTradeAppMngProfile", "model", model);

	}

	@RequestMapping(value = "/updateTradeAppMngDetails", method = RequestMethod.POST)
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

		return new ModelAndView("updateAppMngTradeSuccess", "model", model);

	}

	@RequestMapping(value = "/editAppMngTradePWD", method = RequestMethod.GET)
	public ModelAndView editAdminPWD(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser userProfile = endUserDAOImpl.findId(id);

		endUserForm.setId(userProfile.getId());

		endUserForm.setTransactionId(userProfile.getTransactionId());

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("editAppMngTradePWD", "model", model);

	}

	@RequestMapping(value = "/updateEditAppMngTradePWD", method = RequestMethod.POST)
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

		return new ModelAndView("updateAppMngTradeSuccess", "model", model);

	}

	// Buyer List
	@RequestMapping(value = "/buyerCustomerTradeApprovalList", method = RequestMethod.GET)
	public ModelAndView getAllCustomerTradeApprovalList(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		Collection<BuyerTrade> buyerList = buyerTradeDAO.getByBuyer().getResultList();

		model.put("user", user);
		model.put("buyerList", buyerList);
		return new ModelAndView("buyerCustomerTradeApprovalList", "model", model);

	}

	@RequestMapping(value = "/buyerTradePageShowApproval", method = RequestMethod.GET)
	public ModelAndView buyerPageShow(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		BuyerTrade buyer = buyerTradeDAO.findId(id);

		buyerTradeForm.setId(buyer.getId());
		buyerTradeForm.setBuyerName(buyer.getBuyerName());
		buyerTradeForm.setName(buyer.getName());
		buyerTradeForm.setbName(buyer.getbName());
		buyerTradeForm.setBank(buyer.getBank());
		buyerTradeForm.setBranch(buyer.getBranch());
		buyerTradeForm.setBankEmail(buyer.getBankEmail());
		buyerTradeForm.setUniqueKey(buyer.getUniqueKey());
		buyerTradeForm.setCompanyName(buyer.getCompanyName());
		buyerTradeForm.setContactNum(buyer.getContactNum());
		buyerTradeForm.setEmail(buyer.getEmail());
		buyerTradeForm.setcStatus(buyer.getcStatus());
		buyerTradeForm.setStatus(buyer.getStatus());

		model.put("user", user);

		model.put("user", user);

		model.put("buyerTradeForm", buyerTradeForm);

		return new ModelAndView("buyerTradePageShowApproval", "model", model);
	}

	@RequestMapping(value = "/buyerTradePageShowConfirmApproval", method = RequestMethod.POST)
	public ModelAndView approveBankEmpConfrim(@ModelAttribute BuyerTradeForm buyerTradeForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("buyerTradeForm", buyerTradeForm);

		return new ModelAndView("buyerTradePageShowConfirmApproval", "model", model);
	}

	@RequestMapping(value = "/updateTradebuyerPageShowConfrim", method = RequestMethod.POST)
	public ModelAndView updatebuyerPageShowConfrim(@ModelAttribute BuyerTradeForm buyerTradeForm, ModelMap model,
			RedirectAttributes attributes) {

		BuyerTrade buyer = buyerTradeDAO.findId(buyerTradeForm.getId());
		buyer.setId(buyerTradeForm.getId());
		buyer.setBuyerName(buyerTradeForm.getBuyerName());
		buyer.setName(buyerTradeForm.getName());
		buyer.setbName(buyerTradeForm.getbName());
		buyer.setContactNum(buyerTradeForm.getContactNum());
		buyer.setUniqueKey(buyerTradeForm.getUniqueKey());
		buyer.setEmail(buyerTradeForm.getEmail());
		buyer.setcStatus(buyerTradeForm.getcStatus());
		buyer.setStatus(buyerTradeForm.getStatus());
		buyer.setComment(buyerTradeForm.getComment());
		buyer.setTransactionId(buyerTradeForm.getTransactionId());
		TTransaction transaction = new TTransaction();
		transaction.setTransactionId(buyerTradeForm.getTransactionId());
		transaction.setTransactionType("Buyer Details Update Status");
		transaction.setTransactionStatus("Buyer  Deatils  status saved successfully");

		transcationDAOImpl.insertTransaction(transaction);
		buyerTradeDAO.update(buyer);

		String bankName = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0).getBankName();
		try {
			String stat = buyerTradeForm.getStatus();

			String buyername = buyerTradeForm.getBuyerName();
			String email = buyerTradeForm.getEmail();
			String companyname = buyerTradeForm.getCompanyName();
			String contactNum = buyerTradeForm.getContactNum();
			String comment = buyerTradeForm.getComment();
			if (stat.equals("Approved")) {

				String tex = "Buyer Trading Details Notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + buyername
						+ ",\n\n An official Buyer Trading has been registered against you by " + "\n" + "\n"

						+ "\n\nCompanyname:" + companyname + "\n\nContactNum:" + contactNum + "\nStatus:" + stat
						+ "\n\ncomment:" + comment +

						"\n\n\nRegards,\n"+bankName);
				System.out.println("" + email + buyername);
				mailSender.send(emails);
				SimpleMailMessage emails1 = new SimpleMailMessage();
				emails1.setTo(email);
				emails1.setSubject(tex);
				emails1.setText(
						"Hello " + buyername + "\n\n official Buyer Trading has been registered approved by the bank.\n"
								+ "\n\nRegards,\n"+bankName);
			} else if (stat.equals("Rejected")) {
				String tex = "Buyer Trading Rejection notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + buyername + ",\n\n\n Your Buyer Trading Account has been Rejected: " + stat
						+ "\nPlease apply for Buyer Trading Account. \n\n\nRegards,\n"+bankName);
				mailSender.send(emails);
			} else {
				String tex = "Buyer Pending notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + buyername + ",\n\n\n Your Buyer Trading Account "
						+ " is pending.\n\n\nRegards,\n"+bankName);
				mailSender.send(emails);
			}

			attributes.addFlashAttribute("success", "Mail has been sent successfully.");

			model.put("buyerTradeForm", buyerTradeForm);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("buyerApprovalSuccess", "model", model);
	}
	// Supplier List

	@RequestMapping(value = "/supplierCustomerTradeApprovalList", method = RequestMethod.GET)
	public ModelAndView getAllBuyerList(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		Collection<SupplierTrade> supplierList = supplierTradeDAO.getBySupplier().getResultList();

		model.put("user", user);
		model.put("supplierList", supplierList);
		return new ModelAndView("supplierCustomerTradeApprovalList", "model", model);

	}

	@RequestMapping(value = "/supplierTradePageShowConfirmApproval", method = RequestMethod.GET)
	public ModelAndView supplierPageShow(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		SupplierTrade supplier = supplierTradeDAO.findId(id);

		supplierTradeForm.setId(supplier.getId());
		supplierTradeForm.setContactNum(supplier.getContactNum());
		supplierTradeForm.setBank(supplier.getBank());
		supplierTradeForm.setBranch(supplier.getBranch());
		supplierTradeForm.setBankEmail(supplier.getBankEmail());
		supplierTradeForm.setCompanyName(supplier.getCompanyName());
		supplierTradeForm.setEmail(supplier.getEmail());
		supplierTradeForm.setSupplierName(supplier.getSupplierName());
		supplierTradeForm.setbName(supplier.getbName());
		supplierTradeForm.setName(supplier.getName());
		supplierTradeForm.setUniquekey(supplier.getUniquekey());
		supplierTradeForm.setComment(supplier.getComment());
		supplierTradeForm.setStatus(supplier.getStatus());
		supplierTradeForm.setTransactionId(supplier.getTransactionId());

		model.put("user", user);
		model.put("supplierTradeForm", supplierTradeForm);

		return new ModelAndView("supplierTradePageShowConfirmApproval", "model", model);
	}

	@RequestMapping(value = "/supplierTradePageShowApprovalConfirm", method = RequestMethod.POST)
	public ModelAndView approvesupplierPageShowConfirm(@ModelAttribute SupplierTradeForm supplierTradeForm,
			ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		supplierTradeForm.setStatus(supplierTradeForm.getStatus());

		model.put("user", user);
		model.put("supplierTradeForm", supplierTradeForm);

		return new ModelAndView("supplierTradePageShowApprovalConfirm", "model", model);
	}

	@RequestMapping(value = "/updatesupplierPageShowTradeConfrim", method = RequestMethod.POST)
	public ModelAndView updatesupplierPageShowConfrim(@ModelAttribute SupplierTradeForm supplierTradeForm,
			ModelMap model, RedirectAttributes attributes) {

		SupplierTrade supplier = supplierTradeDAO.findId(supplierTradeForm.getId());
		TTransaction transaction = new TTransaction();

		supplier.setStatus(supplierTradeForm.getStatus());
		supplier.setTransactionId(supplierTradeForm.getTransactionId());

		transaction.setTransactionId(supplierTradeForm.getTransactionId());
		transaction.setTransactionType("Supplier Deatils Update Status");
		transaction.setTransactionStatus("Supplier Deatils  status saved successfully");
		transcationDAOImpl.insertTransaction(transaction);

		supplierTradeDAO.update(supplier);

		model.put("supplierTradeForm", supplierTradeForm);
		String stat = supplierTradeForm.getStatus();
		String suppliername = supplierTradeForm.getSupplierName();
		String email = supplierTradeForm.getEmail();
		String companyname = supplierTradeForm.getCompanyName();
		String contactNum = supplierTradeForm.getContactNum();
		String comment = supplierTradeForm.getComment();

		String bankName = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0).getBankName();
		try {
			if (stat.equals("Approved")) {

				String tex = "Supplier Trading Details Notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + suppliername
						+ ",\n\n An official SupplierTrading has been registered against you by " + "\n" + "\n"

						+ "\n\nCompanyname:" + companyname + "\n\nContactNum:" + contactNum + "\nStatus:" + stat
						+ "\n\ncomment:" + comment +

						"\n\n\nRegards,\n"+bankName);
				System.out.println("" + email + suppliername);
				mailSender.send(emails);
				SimpleMailMessage emails1 = new SimpleMailMessage();
				emails1.setTo(email);
				emails1.setSubject(tex);
				emails1.setText("Hello " + suppliername
						+ "\n\n official Buyer Trading has been registered approved by the bank.\n"
						+ "\n\nRegards,\n"+bankName);
			} else if (stat.equals("Rejected")) {
				String tex = "Supplier Trading Rejection notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + suppliername + ",\n\n\n Your Supplier Trading Account has been Rejected: "
						+ stat + "\nPlease apply for Supplier Trading Account. \n\n\nRegards,\n"+bankName);
				mailSender.send(emails);
			} else {
				String tex = "Buyer Trading Pending notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello " + suppliername + ",\n\n\n Your Supplier Trading Account "
						+ " is pending.\n\n\nRegards,\n"+bankName);
				mailSender.send(emails);
			}

			attributes.addFlashAttribute("success", "Mail has been sent successfully.");

		} catch (Exception e) {
			System.out.print(e.getMessage() + e);
		}
		return new ModelAndView("supplierTradeSuccessApproval", "model", model);
	}

	@RequestMapping(value = "/buyerCustomerTradeListPage", method = RequestMethod.GET)
	public ModelAndView getAllBuyerCustomerTradeList(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		Collection<BuyerTrade> buyerList = buyerTradeDAO.getList();

		model.put("user", user);
		model.put("buyerList", buyerList);
		return new ModelAndView("buyerCustomerTradeListPage", "model", model);

	}

	@RequestMapping(value = "/supplierCustomerTradeListPage", method = RequestMethod.GET)
	public ModelAndView getAllSupplierCustomerTradeList(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		Collection<SupplierTrade> supplierList = supplierTradeDAO.getList();

		model.put("user", user);
		model.put("supplierList", supplierList);
		return new ModelAndView("supplierCustomerTradeListPage", "model", model);

	}

	@RequestMapping(value = "/tappMasterPlanDetails", method = RequestMethod.GET)
	public ModelAndView appMasterPlanDetails(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<TMasterPlan> masterList = tmasterPlanDAO.getMasterPlanByMng().getResultList();

		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);

		}
		model.put("user", user);
		return new ModelAndView("tappMasterPlanDetails", "model", model);

	}

	@RequestMapping(value = "/tappMasterPlanFullDetails", method = RequestMethod.GET)
	public ModelAndView bankMasterPlanFullDetails(@RequestParam("id") Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		TMasterPlan masterList = tmasterPlanDAO.getByMasterPlanId(id);

		if (masterList != null && masterList.getMasterKey() != null) {
			List<TCollateral> collateralList = tcollateralDAO.getCollateralBYMAsterKey(masterList.getMasterKey())
					.getResultList();
			if (collateralList != null && collateralList.size() > 0) {
				model.put("collateralList", collateralList);
			}

			List<TBuyingCost> buyingList = tbuyingCostDAO.getBuyingCostBYMAsterKey(masterList.getMasterKey())
					.getResultList();

			if (buyingList != null && buyingList.size() > 0)

			{
				model.put("buyingList", buyingList);
			}

			model.put("masterList", masterList);
		}
		model.put("user", user);

		return new ModelAndView("tappMasterPlanFullDetails", "model", model);

	}

	@RequestMapping(value = "/tappRiskAssignment", method = RequestMethod.GET)
	public ModelAndView riskAssignment(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("tappRiskAssignment", "model", model);

	}

	@RequestMapping(value = "/tappMasterPlanCreditAssign", method = RequestMethod.GET)
	public ModelAndView masterPlanCreditAssign(@RequestParam("id") Long id, ModelMap model,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		TMasterPlan master = tmasterPlanDAO.getByMasterPlanId(id);

		tMasterPlanForm.setId(master.getId());
		tMasterPlanForm.setBuyingCost(master.getBuyingCost());
		tMasterPlanForm.setTenure(master.getTenure());
		tMasterPlanForm.setBuyingCostSanc(master.getBuyingCostSanc());

		model.put("user", user);
		model.put("tMasterPlanForm", tMasterPlanForm);

		return new ModelAndView("tappMasterPlanCreditAssign", "model", model);

	}

	@RequestMapping(value = "/tappMasterPlanApproveStatus", method = RequestMethod.GET)
	public ModelAndView bankMasterPlanApproveStatus(@RequestParam("id") Long id, ModelMap model) {

		TMasterPlan master = tmasterPlanDAO.getByMasterPlanId(id);

		tMasterPlanForm.setId(master.getId());
		tMasterPlanForm.setCustomer(master.getCustomer());
		tMasterPlanForm.setMasterKey(master.getMasterKey());
		tMasterPlanForm.setStatus(master.getStatus());
		tMasterPlanForm.setTransactionId(master.getTransactionId());
		tMasterPlanForm.setCustomerEmail(master.getCustomerEmail());

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("tMasterPlanForm", tMasterPlanForm);

		return new ModelAndView("tappMasterPlanApproveStatus", "model", model);

	}

	@RequestMapping(value = "/tappMasterPlanApproveStatusConfirm", method = RequestMethod.POST)
	public ModelAndView bankMasterPlanApproveStatusConfirm(ModelMap model,
			@ModelAttribute TMasterPlanForm tMasterPlanForm, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		tMasterPlanForm.setTransactionId(tMasterPlanForm.getTransactionId());
		tMasterPlanForm.setCustomer(tMasterPlanForm.getCustomer());
		tMasterPlanForm.setManagerStatus(tMasterPlanForm.getManagerStatus());
		tMasterPlanForm.setManagerComment(tMasterPlanForm.getManagerComment());
		tMasterPlanForm.setCustomerEmail(tMasterPlanForm.getCustomerEmail());

		model.put("user", user);
		model.put("tMasterPlanForm", tMasterPlanForm);

		return new ModelAndView("tappMasterPlanApproveStatusConfirm", "model", model);

	}

	@RequestMapping(value = "/tappMasterPlanApproveStatusPost", method = RequestMethod.POST)
	public ModelAndView draftsMasterPlanInfoConfirm(ModelMap model, @ModelAttribute TMasterPlanForm tMasterPlanForm,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		tMasterPlanForm.setTransactionId(tMasterPlanForm.getTransactionId());

		TMasterPlan master = tmasterPlanDAO.getByMasterPlanId(tMasterPlanForm.getId());

		master.setManagerStatus(tMasterPlanForm.getManagerStatus());
		master.setManagerComment(tMasterPlanForm.getManagerComment());

		Date masterDate = DateService.loginDate;
		master.setBuyingCostDate(masterDate);

		if (master.getManagerStatus().equals("Rejected")) {
			master.setBuyingCostSanc(null);
			master.setWcSancAmount(null);

		}
		tMasterPlanForm.setCustomer(tMasterPlanForm.getCustomer());
		tMasterPlanForm.setBuyingCostSanc(master.getBuyingCostSanc());
		tMasterPlanForm.setWcTotalAmount(master.getWcTotalAmount());
		tMasterPlanForm.setAmt(master.getAmountPaid());
		tMasterPlanForm.setWcSancAmount(master.getWcSancAmount());
		tMasterPlanForm.setWorkingCapital(master.getWorkingCapital());

		tmasterPlanDAO.updatePlan(master);

		TTransaction trans = new TTransaction();
		trans.setTransactionId(tMasterPlanForm.getTransactionId());
		trans.setTransactionStatus("Master Plan Approval/Reject By Manager");
		trans.setTransactionType("Submitted Successfully");

		transcationDAOImpl.insertTransaction(trans);

		/*
		 * Notification not = new Notification();
		 * 
		 * not.setCustomerName(tMasterPlanForm.getCustomer()); Date notDate = new
		 * Date(); not.setNotificationDate(notDate);
		 * not.setNotificationType("Master Plan Approval");
		 * not.setNotificationDescription(tMasterPlanForm.getManagerStatus());
		 * not.setNotificationAcc("Trade");
		 * 
		 * notificationDAO.insertNotification(not);
		 * 
		 * 
		 * 
		 */
		TradeNotification not = new TradeNotification();
		not.setCustomerName(tMasterPlanForm.getCustomer());
		Date notDate = DateService.loginDate;
		not.setNotificationDate(notDate);
		not.setNotificationType("Master Plan Approval");
		not.setNotificationDescription(tMasterPlanForm.getManagerStatus());
		not.setNotificationAcc("Trade");

		tradeNotificationDAO.insertNotification(not);

		String email = tMasterPlanForm.getCustomerEmail();
		String customer = tMasterPlanForm.getCustomer();

		String masterKey = tMasterPlanForm.getMasterKey();
		Float workingcapitalcost = tMasterPlanForm.getWorkingCapital();
		Float workingSancAmount = tMasterPlanForm.getWcSancAmount();
		Float amtsanctioned = tMasterPlanForm.getWcSancAmount();
		Float buyingCostSanc = tMasterPlanForm.getBuyingCostSanc();
		String stat = tMasterPlanForm.getManagerStatus();

		String bankName = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0).getBankName();
		if (stat.equals("Approved")) {

			String tex = "Master Plan Details Notification";
			SimpleMailMessage emails = new SimpleMailMessage();
			emails.setTo(email);
			emails.setSubject(tex);
			emails.setText("Hello " + customer + ",\n\n Your MasterPlan has been approved " + "\n" + "\n"

					+ "\n\nMaterKey:" + masterKey + "\n\nAmount Sanctioned Cost:" + amtsanctioned
					+ "\n\nBuying Sanctioned Cost:" + buyingCostSanc +

					"\n\nWorking Capital Cost:" + workingcapitalcost + "\n\nWorking Capital Sanctioned Cost:"
					+ workingSancAmount + "\n\nCheck your Profile for full Details" + "\n\n\nRegards,\n"+bankName);
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
		model.put("tMasterPlanForm", tMasterPlanForm);

		return new ModelAndView("tappMasterPlanAppTransaction", "model", model);

	}

	@RequestMapping(value = "/tappMasterPlanAppTransaction", method = RequestMethod.GET)
	public ModelAndView collateralTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("tMasterPlanForm", tMasterPlanForm);

		return new ModelAndView("tappMasterPlanAppTransaction", "model", model);

	}

	@RequestMapping(value = "/tappMasterPlanFullList", method = RequestMethod.GET)
	public ModelAndView masterPlanFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<TMasterPlan> masterList = tmasterPlanDAO.getAllMasterPlans().getResultList();

		model.put("user", user);
		model.put("masterList", masterList);

		return new ModelAndView("tappMasterPlanFullList", "model", model);

	}

	/* Re-Payment */

	@RequestMapping(value = "/tmasterPlanRePaymentAppMngList", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentBank(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<TRepayment> repayList = trepaymenyDAO.getRepayByAppMngStatus().getResultList();

		if (repayList != null && repayList.size() > 0) {
			model.put("repayList", repayList);
			model.put("user", user);
		}
		return new ModelAndView("tmasterPlanRePaymentAppMngList", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentAppMngAppr", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentAppMngLAppr(@RequestParam Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		TRepayment master = trepaymenyDAO.getByRepaymentId(id);

		trepaymentForm.setId(master.getId());
		trepaymentForm.setMasterKey(master.getMasterKey());
		trepaymentForm.setCustomer(master.getCustomer());
		trepaymentForm.setCustomerEmail(master.getCustomerEmail());
		trepaymentForm.setTenure(master.getTenure());
		trepaymentForm.setPayOption(master.getPayOption());
		trepaymentForm.setAmtType(master.getAmtType());
		trepaymentForm.setTransactionId(master.getTransactionId());

		model.put("trepaymentForm", trepaymentForm);
		model.put("user", user);

		return new ModelAndView("tmasterPlanRePaymentAppMngAppr", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentAppMngApprConfirm", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentDisplayConfirm(ModelMap model, @ModelAttribute TRepaymentForm trepaymentForm,
			BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		trepaymentForm.setId(trepaymentForm.getId());
		trepaymentForm.setMasterKey(trepaymentForm.getMasterKey());
		trepaymentForm.setCustomer(trepaymentForm.getCustomer());
		trepaymentForm.setCustomerEmail(trepaymentForm.getCustomerEmail());
		trepaymentForm.setTenure(trepaymentForm.getTenure());
		trepaymentForm.setPayOption(trepaymentForm.getPayOption());
		trepaymentForm.setAmtType(trepaymentForm.getAmtType());
		trepaymentForm.setTransactionId(trepaymentForm.getTransactionId());
		trepaymentForm.setStatus(trepaymentForm.getStatus());
		trepaymentForm.setComment(trepaymentForm.getComment());

		model.put("trepaymentForm", trepaymentForm);
		model.put("user", user);

		return new ModelAndView("tmasterPlanRePaymentAppMngApprConfirm", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentAppMngApprSave", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentAppMngApprSave(ModelMap model, @ModelAttribute TRepaymentForm trepaymentForm,
			BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		TRepayment repay = trepaymenyDAO.getByRepaymentId(trepaymentForm.getId());
		repay.setStatus(trepaymentForm.getStatus());
		repay.setComment(trepaymentForm.getComment());
		repay.setAccept("Pending");
		if (trepaymentForm.getStatus().equals("Rejected")) {
			repay.setbStatus("Pending");
		}
		trepaymenyDAO.updateRepayment(repay);

		List<TQuarterly> full = tquarterlyDAO.getByTransIdList(trepaymentForm.getTransactionId()).getResultList();
		if (full != null && full.size() > 0) {
			full.get(0).setStatus(trepaymentForm.getStatus());
			tquarterlyDAO.updateQuarterly(full.get(0));
		}
		List<TFullAmount> full1 = tfullAmountDAO.getByTransIdList(trepaymentForm.getTransactionId()).getResultList();
		if (full1 != null && full1.size() > 0) {
			full1.get(0).setStatus(trepaymentForm.getStatus());
			tfullAmountDAO.updateFullAmount(full1.get(0));
		}
		List<THalfYearly> full2 = thalfYearlyDAO.getByTransIdList(trepaymentForm.getTransactionId()).getResultList();
		if (full2 != null && full2.size() > 0) {
			full2.get(0).setStatus(trepaymentForm.getStatus());
			thalfYearlyDAO.updateHalfYearly(full2.get(0));
		}

		TTransaction trans = new TTransaction();

		trans.setTransactionId(trepaymentForm.getTransactionId());
		trans.setTransactionStatus("Repayment Approve/Reject");
		trans.setTransactionType("Sent Successfully");
		transcationDAOImpl.insertTransaction(trans);

		model.put("trepaymentForm", trepaymentForm);
		model.put("user", user);

		return new ModelAndView("tmasterPlanRePaymentAppMngApprTrans", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentAppMngApprTrans", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("trepaymentForm", trepaymentForm);

		return new ModelAndView("tmasterPlanRePaymentAppMngApprTrans", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentAppQuarterly", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentAppQuarterly(@RequestParam Long id, ModelMap model,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		TRepayment master = trepaymenyDAO.getByRepaymentId(id);
		if (master != null) {
			List<TQuarterly> full = tquarterlyDAO.getByTransIdList(master.getTransactionId()).getResultList();

			if (full != null && full.size() > 0) {
				model.put("full", full);
				model.put("user", user);
			} else {
				attributes.addFlashAttribute("success", "Check The Type of Payment Type");

				return new ModelAndView("redirect:tmasterPlanRePaymentAppMngList");

			}
		}
		return new ModelAndView("tmasterPlanRePaymentAppQuarterly", "model", model);
	}

	@RequestMapping(value = "/tmasterPlanRePaymentAppFullList", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentAppFullList(@RequestParam Long id, ModelMap model,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		TRepayment master = trepaymenyDAO.getByRepaymentId(id);
		if (master != null) {
			List<TFullAmount> full = tfullAmountDAO.getByTransIdList(master.getTransactionId()).getResultList();
			if (full != null && full.size() > 0) {
				model.put("full", full);
				model.put("user", user);
			} else {
				attributes.addFlashAttribute("success", "Check The Type of Payment Type");

				return new ModelAndView("redirect:tmasterPlanRePaymentAppMngList");

			}
		}
		return new ModelAndView("tmasterPlanRePaymentAppFullList", "model", model);
	}

	@RequestMapping(value = "/tmasterPlanRePaymentAppHalfYearly", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentAppHalfYearly(@RequestParam Long id, ModelMap model,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		TRepayment master = trepaymenyDAO.getByRepaymentId(id);
		if (master != null) {
			List<THalfYearly> full = thalfYearlyDAO.getByTransIdList(master.getTransactionId()).getResultList();

			if (full != null && full.size() > 0) {
				model.put("full", full);
				model.put("user", user);
			} else {
				attributes.addFlashAttribute("success", "Check The Type of Payment Type");

				return new ModelAndView("redirect:tmasterPlanRePaymentAppMngList");

			}
		}
		return new ModelAndView("tmasterPlanRePaymentAppHalfYearly", "model", model);
	}

	@RequestMapping(value = "/tmasterPlanRePaymentAppMngFullList", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<TRepayment> repayList = trepaymenyDAO.getRepayFullList().getResultList();

		if (repayList != null && repayList.size() > 0) {
			model.put("repayList", repayList);
			model.put("user", user);
		}
		return new ModelAndView("tmasterPlanRePaymentAppMngFullList", "model", model);

	}

	@RequestMapping(value = "/tradeAppMngTransactionList", method = RequestMethod.GET)
	public ModelAndView getALLTransactionList(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		Collection<TTransaction> transactionPageList = transcationDAOImpl.getList();

		model.put("user", user);
		model.put("transactionPageList", transactionPageList);
		return new ModelAndView("tradeAppMngTransactionList", "model", model);

	}

	@RequestMapping(value = "/tAppMngHelp", method = RequestMethod.GET)
	public ModelAndView adminHelp(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("tAppMngHelp", "model", model);

	}

	@RequestMapping(value = "/custAdminTradeApprovalList", method = RequestMethod.GET)
	public ModelAndView custAdminApprovalList(ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		List<ClientAdminForm> customerList = clientAdminDAO
				.getClientAdminByIdAndAStatusCompAndIsForTrading(Boolean.TRUE);

		if (customerList != null && customerList.size() > 0) {

			model.put("customerList", customerList);
			model.put("clientAdminForm", clientAdminForm);
			return new ModelAndView("custAdminTradeApprovalList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/approveClientAdminTrade", method = RequestMethod.GET)
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
		return new ModelAndView("approveClientAdminTrade", "model", model);
	}

	@RequestMapping(value = "/approveClientAdminTradeConfirm", method = RequestMethod.POST)
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

		return new ModelAndView("approveClientAdminTradeConfirm", "model", model);
	}

	@RequestMapping(value = "/approveClientAdminTradeSave", method = RequestMethod.POST)
	public ModelAndView approveClientAdminSave(@ModelAttribute ClientAdminForm clientAdminForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		ClientAdmin customer = clientAdminDAO.getByClientAdminId(clientAdminForm.getId());
		Company company = companyDAO.getByCompanyName(clientAdminForm.getCompanyName());

		customer.setName(clientAdminForm.getName());
		// customer.setCompanyName(clientAdminForm.getCompanyName());
		customer.setCompanyId(company.getId());
		customer.setCustomerPrefix(clientAdminForm.getCustomerPrefix());
		// customer.setAddress(clientAdminForm.getAddress());
		customer.setStatus(clientAdminForm.getStatus());
		customer.setComment(clientAdminForm.getComment());
		Date date = DateService.loginDate;
		customer.setApproveeDate(date);
		customer.setCustomerHeadKey(clientAdminForm.getCustomerHeadKey());
		customer.setPassword(customer.getContactNum());

		clientAdminDAO.updateUser(customer);

		String status = clientAdminForm.getStatus();

//		Will implement this code after confirmation with Arpita
//		if (status != null && status.equalsIgnoreCase("Approved")) {
//			EndUser endUser = new EndUser();
//
//			endUser.setUserName(clientAdminForm.getName());
//			endUser.setPassword(clientAdminForm.getContactNum());
//			endUser.setContactNo(clientAdminForm.getContactNum());
//			endUser.setCurrentRole("ROLE_CUSTOMERADMIN");
//			endUser.setCompanyId(company.getId());
//			endUser.setRole(9);
//			endUser.setPrefferedLanguage("en");
//			endUser.setTheme("themeBlue");
//			endUser.setEmail(clientAdminForm.getEmail());
//			endUser.setDisplayName(clientAdminForm.getName());
//			endUser.setStatus(clientAdminForm.getStatus());
//			endUser.setTransactionId(clientAdminForm.getTransactionId());
//			endUser.setPasswordFlag(0);
//			endUser.setAccExpiryDate(clientAdminForm.getAccExpiryDate());
//			endUser.setIsForTrading(customer.getIsForTrading());
//			endUserDAOImpl.createUser(endUser);
//
//		}

		TTransaction trans = new TTransaction();
		trans.setTransactionId(clientAdminForm.getTransactionId());
		trans.setTransactionStatus("Customer Admin Approval");
		trans.setTransactionType("Approved Successfully");

		transcationDAOImpl.insertTransaction(trans);
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

			}

			else if (stat.equals("Rejected")) {
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
		return new ModelAndView("customerAdminTradeAppTransaction", "model", model);
	}

	@RequestMapping(value = "/tradeCompanyList", method = RequestMethod.GET)
	public ModelAndView companyList(ModelMap model, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Company> companyList = companyDAO.getCompanyByStatusAndIsForTrading("Pending", Boolean.TRUE);
		if (companyList != null && companyList.size() > 0) {
			model.put("companyList", companyList);
			model.put("companyForm", companyForm);
			return new ModelAndView("tradeCompanyList", "model", model);
		} else {
			return new ModelAndView("noDataFound1", "model", model);
		}

	}

	@RequestMapping(value = "/approveTradeCompany", method = RequestMethod.GET)
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
		return new ModelAndView("approveTradeCompany", "model", model);
	}

	@RequestMapping(value = "/approveTradeCompanyConfirm", method = RequestMethod.POST)
	public ModelAndView approveCompanyConfirm(@ModelAttribute CompanyForm companyForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("companyForm", companyForm);

		return new ModelAndView("approveTradeCompanyConfirm", "model", model);
	}

	@RequestMapping(value = "/approveTradeCompanySave", method = RequestMethod.POST)
	public ModelAndView approveClientAppMngSave(@ModelAttribute CompanyForm companyForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		Company company = companyDAO.getByCompanyId(companyForm.getCompanyId());
		company.setStatus(companyForm.getStatus());
		company.setComment(companyForm.getComment());

		companyDAO.updateCompany(company);

		TTransaction trans = new TTransaction();
		trans.setTransactionId(companyForm.getTransactionId());
		trans.setTransactionStatus(companyForm.getStatus() + " Successfully");
		trans.setTransactionType("Company Approval");

		transcationDAOImpl.insertTransaction(trans);
		model.put("transaction", trans);
		model.put("companyForm", companyForm);
		model.put("user", user);

		return new ModelAndView("approveTradeCompanySave", "model", model);

	}

}
