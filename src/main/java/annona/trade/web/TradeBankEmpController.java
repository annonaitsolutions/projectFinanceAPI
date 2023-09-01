package annona.trade.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import annona.dao.ClientAdminDAO;
import annona.dao.CompanyDAO;
import annona.dao.EndUserDAO;
import annona.dao.TransactionDAOImpl;
import annona.domain.ClientAdmin;
import annona.domain.Company;
import annona.domain.EndUser;
import annona.domain.Transaction;
import annona.form.ClientAdminForm;
import annona.form.CompanyForm;
import annona.form.EndUserForm;
import annona.trade.dao.BuyerTradeDAO;
import annona.trade.dao.SupplierTradeDAO;
import annona.trade.dao.TBuyingCostDAO;
import annona.trade.dao.TCollateralDAO;
import annona.trade.dao.TFullAmountDAO;
import annona.trade.dao.THalfYearlyDAO;
import annona.trade.dao.TMasterPlanDAO;
import annona.trade.dao.TQuarterlyDAO;
import annona.trade.dao.TRepaymentDAO;
import annona.trade.dao.TTransactionDAO;
import annona.trade.domain.BuyerTrade;
import annona.trade.domain.SupplierTrade;
import annona.trade.domain.TBuyingCost;
import annona.trade.domain.TCollateral;
import annona.trade.domain.TFullAmount;
import annona.trade.domain.THalfYearly;
import annona.trade.domain.TMasterPlan;
import annona.trade.domain.TQuarterly;
import annona.trade.domain.TRepayment;
import annona.trade.domain.TTransaction;
import annona.trade.form.BuyerTradeForm;
import annona.trade.form.SupplierTradeForm;
import annona.trade.form.TFullAmountForm;
import annona.trade.form.THalfYearlyForm;
import annona.trade.form.TMasterPlanForm;
import annona.trade.form.TQuarterlyForm;
import annona.trade.form.TRepaymentForm;
import annona.web.BankEmpController;

@Controller
@RequestMapping("/tBnkEmp")
public class TradeBankEmpController {

	@Autowired
	EndUserDAO endUserDAOImpl;

	@Autowired
	EndUserForm endUserForm;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	TMasterPlanDAO tmasterPlanDAO;

	@Autowired
	TCollateralDAO tcollateralDAO;

	@Autowired
	TMasterPlanForm tMasterPlanForm;

	@Autowired
	TBuyingCostDAO tbuyingCostDAO;

	@Autowired
	private JavaMailSender mailSender2;
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
	CompanyDAO companyDAO;

	@Autowired
	CompanyForm companyForm;

	CookieThemeResolver themeResolver = new CookieThemeResolver();

	protected Logger log = Logger.getLogger(BankEmpController.class.getName());

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

	/* Bank employee home page display */

	@RequestMapping(value = "/tBankEmp", method = RequestMethod.GET)
	public ModelAndView showBankEmpDashBoard(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("tBankEmpPage", "model", model);

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

		return "redirect:tBankEmp";
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

		return "redirect:tBankEmp";
	}

	@RequestMapping(value = "/editTradeBankEmpProfile", method = RequestMethod.GET)
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

		return new ModelAndView("editTradeBankEmpProfile", "model", model);

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
		return "redirect:editTradeBankEmpProfile?id=" + endUserForm.getId();
	}

	@RequestMapping(value = "/confirmEditTradeBankEmpProfile", method = RequestMethod.POST)
	public ModelAndView confirmEditAdminProfile(ModelMap model, @ModelAttribute EndUserForm endUserForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("confirmEditTradeBankEmpProfile", "model", model);

	}

	@RequestMapping(value = "/updateTradeBankEmpDetails", method = RequestMethod.POST)
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

		return new ModelAndView("updateBankEmpTradeSuccess", "model", model);

	}

	@RequestMapping(value = "/editBankEmpTradePWD", method = RequestMethod.GET)
	public ModelAndView editAdminPWD(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser userProfile = endUserDAOImpl.findId(id);

		endUserForm.setId(userProfile.getId());

		endUserForm.setTransactionId(userProfile.getTransactionId());

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("editBankEmpTradePWD", "model", model);

	}

	@RequestMapping(value = "/updateEditBankEmpTradePWD", method = RequestMethod.POST)
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

		return new ModelAndView("updateBankEmpTradeSuccess", "model", model);

	}

	@RequestMapping(value = "/buyerTradeBankList", method = RequestMethod.GET)
	public ModelAndView getAllBuyerCustomerTradeList(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		Collection<BuyerTrade> buyerList = buyerTradeDAO.getList();

		model.put("user", user);
		model.put("buyerList", buyerList);
		return new ModelAndView("buyerTradeBankList", "model", model);

	}

	@RequestMapping(value = "/supplierTradeBankList", method = RequestMethod.GET)
	public ModelAndView getAllSupplierCustomerTradeList(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		Collection<SupplierTrade> supplierList = supplierTradeDAO.getList();

		model.put("user", user);
		model.put("supplierList", supplierList);
		return new ModelAndView("supplierTradeBankList", "model", model);

	}

	@RequestMapping(value = "/tbankMasterPlanPendingDetails", method = RequestMethod.GET)
	public ModelAndView bankMasterPlanPendingDetails(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<TMasterPlan> masterList = tmasterPlanDAO.getMasterPlanByPenStatus().getResultList();

		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);
			model.put("user", user);
		}
		return new ModelAndView("tbankMasterPlanPendingDetails", "model", model);

	}

	@RequestMapping(value = "/tbankMasterPlanFullDetails", method = RequestMethod.GET)
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

		return new ModelAndView("tbankMasterPlanFullDetails", "model", model);

	}

	@RequestMapping(value = "/triskAssignment", method = RequestMethod.GET)
	public ModelAndView riskAssignment(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("triskAssignment", "model", model);

	}

	@RequestMapping(value = "/tbankMasterPlanApproveStatus", method = RequestMethod.GET)
	public ModelAndView bankMasterPlanApproveStatus(@RequestParam("id") Long id, ModelMap model) {

		TMasterPlan master = tmasterPlanDAO.getByMasterPlanId(id);

		tMasterPlanForm.setId(master.getId());
		tMasterPlanForm.setCustomer(master.getCustomer());
		tMasterPlanForm.setMasterKey(master.getMasterKey());
		tMasterPlanForm.setTransactionId(master.getTransactionId());

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("tMasterPlanForm", tMasterPlanForm);

		return new ModelAndView("tbankMasterPlanApproveStatus", "model", model);

	}

	@RequestMapping(value = "/tbankMasterPlanApproveStatusConfirm", method = RequestMethod.POST)
	public ModelAndView bankMasterPlanApproveStatusConfirm(ModelMap model,
			@ModelAttribute TMasterPlanForm tMasterPlanForm, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		tMasterPlanForm.setTransactionId(tMasterPlanForm.getTransactionId());
		tMasterPlanForm.setCustomer(tMasterPlanForm.getCustomer());
		tMasterPlanForm.setStatus(tMasterPlanForm.getStatus());
		tMasterPlanForm.setComment(tMasterPlanForm.getComment());

		model.put("user", user);
		model.put("tMasterPlanForm", tMasterPlanForm);

		return new ModelAndView("tbankMasterPlanApproveStatusConfirm", "model", model);

	}

	@RequestMapping(value = "/tbankMasterPlanApproveStatusPost", method = RequestMethod.POST)
	public ModelAndView draftsMasterPlanInfoConfirm(ModelMap model, @ModelAttribute TMasterPlanForm tMasterPlanForm,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		tMasterPlanForm.setTransactionId(tMasterPlanForm.getTransactionId());

		TMasterPlan master = tmasterPlanDAO.getByMasterPlanId(tMasterPlanForm.getId());

		master.setStatus(tMasterPlanForm.getStatus());
		master.setComment(tMasterPlanForm.getComment());

		tmasterPlanDAO.updatePlan(master);

		TTransaction trans = new TTransaction();
		trans.setTransactionId(tMasterPlanForm.getTransactionId());
		trans.setTransactionStatus("Master Plan Approval/Reject By Bank");
		trans.setTransactionType("Submitted Successfully");

		transcationDAOImpl.insertTransaction(trans);

		model.put("user", user);
		model.put("tMasterPlanForm", tMasterPlanForm);

		return new ModelAndView("tmasterPlanAppTransaction", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanAppTransaction", method = RequestMethod.GET)
	public ModelAndView collateralTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("tMasterPlanForm", tMasterPlanForm);

		return new ModelAndView("tmasterPlanAppTransaction", "model", model);

	}

	@RequestMapping(value = "/tbankMasterPlanDetails", method = RequestMethod.GET)
	public ModelAndView bankMasterPlanDetails(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<TMasterPlan> masterList = tmasterPlanDAO.getMasterPlanByAppStatus().getResultList();

		if (masterList != null && masterList.size() > 0) {
			model.put("masterList", masterList);
			model.put("user", user);
		}
		return new ModelAndView("tbankMasterPlanDetails", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanCreditAssign", method = RequestMethod.GET)
	public ModelAndView masterPlanCreditAssign(@RequestParam("id") Long id, ModelMap model,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		TMasterPlan master = tmasterPlanDAO.getByMasterPlanId(id);

		tMasterPlanForm.setId(master.getId());
		tMasterPlanForm.setBuyingCost(master.getBuyingCost());
		tMasterPlanForm.setTenure(master.getTenure());
		tMasterPlanForm.setTransactionId(master.getTransactionId());

		if (master.getBuyingCostSanc() != null) {
			attributes.addFlashAttribute("success", "Credit Assignment is already Done.");
			return new ModelAndView("redirect:tbankMasterPlanDetails");
		} else {

			model.put("user", user);
			model.put("tMasterPlanForm", tMasterPlanForm);
			return new ModelAndView("tmasterPlanCreditAssign", "model", model);
		}
	}

	@RequestMapping(value = "/tmasterPlanCreditAssignConfirm", method = RequestMethod.POST)
	public ModelAndView masterPlanCreditAssignConfirm(ModelMap model, @ModelAttribute TMasterPlanForm tMasterPlanForm,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		tMasterPlanForm.setId(tMasterPlanForm.getId());
		tMasterPlanForm.setTransactionId(tMasterPlanForm.getTransactionId());
		tMasterPlanForm.setTenure(tMasterPlanForm.getTenure());
		tMasterPlanForm.setBuyingCost(tMasterPlanForm.getBuyingCost());
		tMasterPlanForm.setBuyingCostSanc(tMasterPlanForm.getBuyingCostSanc());
		tMasterPlanForm.setInterestType(tMasterPlanForm.getInterestType());
		tMasterPlanForm.setRateOfInt(tMasterPlanForm.getRateOfInt());
		tMasterPlanForm.setNoOfDays(tMasterPlanForm.getNoOfDays());
		tMasterPlanForm.setPlrRate(tMasterPlanForm.getPlrRate());
		tMasterPlanForm.setBasicPoints(tMasterPlanForm.getBasicPoints());
		tMasterPlanForm.setBasicAmt(tMasterPlanForm.getBasicAmt());
		tMasterPlanForm.setCalPlrRate(tMasterPlanForm.getCalPlrRate());
		tMasterPlanForm.setTotal1(tMasterPlanForm.getTotal1());
		tMasterPlanForm.setRateOfInt1(tMasterPlanForm.getRateOfInt1());
		tMasterPlanForm.setFlatCharges(tMasterPlanForm.getFlatCharges());
		tMasterPlanForm.setPercentAmt(tMasterPlanForm.getPercentAmt());
		tMasterPlanForm.setPercentage(tMasterPlanForm.getPercentage());
		tMasterPlanForm.setProcFee(tMasterPlanForm.getProcFee());
		tMasterPlanForm.setDocFee(tMasterPlanForm.getDocFee());
		tMasterPlanForm.setLateFee(tMasterPlanForm.getLateFee());
		tMasterPlanForm.setTaxName(tMasterPlanForm.getTaxName());
		tMasterPlanForm.setTaxPercentage(tMasterPlanForm.getTaxPercentage());
		tMasterPlanForm.setTaxAmt(tMasterPlanForm.getTaxAmt());
		tMasterPlanForm.setTaxName1(tMasterPlanForm.getTaxName1());
		tMasterPlanForm.setTaxPercentage1(tMasterPlanForm.getTaxPercentage1());
		tMasterPlanForm.setTaxAmt1(tMasterPlanForm.getTaxAmt1());
		tMasterPlanForm.setTaxPercentage2(tMasterPlanForm.getTaxPercentage2());
		tMasterPlanForm.setTaxAmt2(tMasterPlanForm.getTaxAmt2());
		tMasterPlanForm.setFunalAmt(tMasterPlanForm.getFunalAmt());

		model.put("user", user);
		model.put("tMasterPlanForm", tMasterPlanForm);

		return new ModelAndView("tmasterPlanCreditAssignConfirm", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanCreditAssignPost", method = RequestMethod.POST)
	public ModelAndView masterPlanCreditAssignPost(ModelMap model, @ModelAttribute TMasterPlanForm tMasterPlanForm,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		tMasterPlanForm.setTransactionId(tMasterPlanForm.getTransactionId());

		TMasterPlan master = tmasterPlanDAO.getByMasterPlanId(tMasterPlanForm.getId());

		master.setBuyingCostSanc(tMasterPlanForm.getBuyingCostSanc());
		master.setInterestType(tMasterPlanForm.getInterestType());
		master.setRateOfInt(tMasterPlanForm.getRateOfInt());
		master.setNoOfDays(tMasterPlanForm.getNoOfDays());
		master.setPlrRate(tMasterPlanForm.getPlrRate());
		master.setBasicPoints(tMasterPlanForm.getBasicPoints());
		master.setBasicAmt(tMasterPlanForm.getBasicAmt());
		master.setCalPlrRate(tMasterPlanForm.getCalPlrRate());
		master.setTotal1(tMasterPlanForm.getTotal1());
		master.setRateOfInt1(tMasterPlanForm.getRateOfInt1());
		master.setFlatCharges(tMasterPlanForm.getFlatCharges());
		master.setPercentAmt(tMasterPlanForm.getPercentAmt());
		master.setPercentage(tMasterPlanForm.getPercentage());
		master.setProcFee(tMasterPlanForm.getProcFee());
		master.setDocFee(tMasterPlanForm.getDocFee());
		master.setLateFee(tMasterPlanForm.getLateFee());
		master.setTaxName(tMasterPlanForm.getTaxName());
		master.setTaxPercentage(tMasterPlanForm.getTaxPercentage());
		master.setTaxAmt(tMasterPlanForm.getTaxAmt());
		master.setTaxName1(tMasterPlanForm.getTaxName1());
		master.setTaxPercentage1(tMasterPlanForm.getTaxPercentage1());
		master.setTaxAmt1(tMasterPlanForm.getTaxAmt1());
		master.setTaxPercentage2(tMasterPlanForm.getTaxPercentage2());
		master.setTaxAmt2(tMasterPlanForm.getTaxAmt2());
		master.setFunalAmt(tMasterPlanForm.getFunalAmt());

		tmasterPlanDAO.updatePlan(master);

		TTransaction trans = new TTransaction();
		trans.setTransactionId(tMasterPlanForm.getTransactionId());
		trans.setTransactionStatus("Credit Assignment");
		trans.setTransactionType("Submitted Successfully");

		transcationDAOImpl.insertTransaction(trans);

		model.put("user", user);
		model.put("tMasterPlanForm", tMasterPlanForm);

		return new ModelAndView("tcreditAssignmentTransaction", "model", model);

	}

	@RequestMapping(value = "/tcreditAssignmentTransaction", method = RequestMethod.GET)
	public ModelAndView creditAssignmentTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("tMasterPlanForm", tMasterPlanForm);

		return new ModelAndView("tcreditAssignmentTransaction", "model", model);

	}

	@RequestMapping(value = "/tbankMasterPlanSend", method = RequestMethod.GET)
	public String bankMasterPlanSend(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		TMasterPlan masterPlan = tmasterPlanDAO.getByMasterPlanId(id);
		if (masterPlan.getBuyingCostSanc() != null) {
			if (masterPlan != null) {
				masterPlan.setApprovalSent("Yes");
				masterPlan.setManagerStatus("Pending");
				tmasterPlanDAO.updatePlan(masterPlan);
			}
			attributes.addFlashAttribute("success", "Sent for Approval");
			model.put("user", user);
		} else {
			attributes.addFlashAttribute("success", "Credit  Assignment is Mandatory");
		}
		return "redirect:tbankMasterPlanDetails";
	}

	@RequestMapping(value = "/tmasterPlanFullList", method = RequestMethod.GET)
	public ModelAndView masterPlanFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<TMasterPlan> masterList = tmasterPlanDAO.getAllMasterPlans().getResultList();

		model.put("user", user);
		model.put("masterList", masterList);

		return new ModelAndView("tmasterPlanFullList", "model", model);

	}

	/* Re-Payment */

	@RequestMapping(value = "/tmasterPlanRePaymentBank", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentBank(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<TRepayment> repayList = trepaymenyDAO.getRepayByIdAndStatusBank().getResultList();

		if (repayList != null && repayList.size() > 0) {
			model.put("repayList", repayList);
			model.put("user", user);
		}
		return new ModelAndView("tmasterPlanRePaymentBank", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentBankSet", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentAppApprove(@RequestParam Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		TRepayment master = trepaymenyDAO.getByRepaymentId(id);

		trepaymentForm.setId(master.getId());
		trepaymentForm.setMasterKey(master.getMasterKey());
		trepaymentForm.setBuyingCostSanc(master.getBuyingCostSanc());
		trepaymentForm.setWcSancAmount(master.getWcSancAmount());
		trepaymentForm.setCustomer(master.getCustomer());
		trepaymentForm.setCustomerEmail(master.getCustomerEmail());
		trepaymentForm.setBuyingCostDate(master.getBuyingCostDate());
		trepaymentForm.setTenure(master.getTenure());
		trepaymentForm.setRateOfInt1(master.getRateOfInt1());
		trepaymentForm.setAmtType(master.getAmtType());
		trepaymentForm.setPayOption(master.getPayOption());
		trepaymentForm.setTransactionId(master.getTransactionId());

		model.put("trepaymentForm", trepaymentForm);
		model.put("user", user);

		return new ModelAndView("tmasterPlanRePaymentBankSet", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentFull", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentSet(@RequestParam Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		TRepayment master = trepaymenyDAO.getByRepaymentId(id);

		tquarterlyForm.setId(master.getId());
		tquarterlyForm.setMasterKey(master.getMasterKey());
		tquarterlyForm.setCustomer(master.getCustomer());
		tquarterlyForm.setCustomerEmail(master.getCustomerEmail());
		tquarterlyForm.setTenure(master.getTenure());
		tquarterlyForm.setPayOption(master.getPayOption());
		tquarterlyForm.setAmtType(master.getAmtType());
		tquarterlyForm.setTransactionId(master.getTransactionId());

		model.put("tquarterlyForm", tquarterlyForm);
		model.put("user", user);

		return new ModelAndView("tmasterPlanRePaymentFull", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentDisplayBankConfirm", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentDisplayConfirm(ModelMap model, @ModelAttribute TQuarterlyForm tquarterlyForm,
			BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		tquarterlyForm.setId(tquarterlyForm.getId());
		tquarterlyForm.setMasterKey(tquarterlyForm.getMasterKey());
		tquarterlyForm.setCustomer(tquarterlyForm.getCustomer());
		tquarterlyForm.setCustomerEmail(tquarterlyForm.getCustomerEmail());
		tquarterlyForm.setTenure(tquarterlyForm.getTenure());
		tquarterlyForm.setPayOption(tquarterlyForm.getPayOption());
		tquarterlyForm.setAmtType(tquarterlyForm.getAmtType());
		tquarterlyForm.setTransactionId(tquarterlyForm.getTransactionId());
		tquarterlyForm.setAmount(tquarterlyForm.getAmount());
		tquarterlyForm.setIntRate(tquarterlyForm.getIntRate());
		tquarterlyForm.setPayDate(tquarterlyForm.getPayDate());
		tquarterlyForm.setTotalAmount(tquarterlyForm.getTotalAmount());
		tquarterlyForm.setLoanDate(tquarterlyForm.getLoanDate());
		tquarterlyForm.setAmount1(tquarterlyForm.getAmount1());
		tquarterlyForm.setIntRate1(tquarterlyForm.getIntRate1());
		tquarterlyForm.setPayDate1(tquarterlyForm.getPayDate1());
		tquarterlyForm.setTotalAmount1(tquarterlyForm.getTotalAmount1());
		tquarterlyForm.setLoanDate1(tquarterlyForm.getLoanDate1());
		tquarterlyForm.setAmount2(tquarterlyForm.getAmount2());
		tquarterlyForm.setIntRate2(tquarterlyForm.getIntRate2());
		tquarterlyForm.setPayDat2(tquarterlyForm.getPayDat2());
		tquarterlyForm.setTotalAmount2(tquarterlyForm.getTotalAmount2());
		tquarterlyForm.setLoanDate2(tquarterlyForm.getLoanDate2());
		tquarterlyForm.setAmount3(tquarterlyForm.getAmount3());
		tquarterlyForm.setIntRate3(tquarterlyForm.getIntRate3());
		tquarterlyForm.setPayDate3(tquarterlyForm.getPayDate3());
		tquarterlyForm.setTotalAmount3(tquarterlyForm.getTotalAmount3());
		tquarterlyForm.setLoanDate3(tquarterlyForm.getLoanDate3());

		model.put("tquarterlyForm", tquarterlyForm);
		model.put("user", user);

		return new ModelAndView("tmasterPlanRePaymentDisplayBankConfirm", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentSave", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentSave(ModelMap model, @ModelAttribute TQuarterlyForm tquarterlyForm,
			BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		TRepayment master = trepaymenyDAO.getByRepaymentId(tquarterlyForm.getId());
		master.setStatus("Pending");
		master.setbStatus("Approved");

		trepaymenyDAO.updateRepayment(master);

		TQuarterly quarter = new TQuarterly();
		List<TQuarterly> full = tquarterlyDAO.getByTransIdList(tquarterlyForm.getTransactionId()).getResultList();
		if (full != null && full.size() > 0) {

			full.get(0).setMasterKey(tquarterlyForm.getMasterKey());
			full.get(0).setCustomer(tquarterlyForm.getCustomer());
			full.get(0).setCustomerEmail(tquarterlyForm.getCustomerEmail());
			full.get(0).setTenure(tquarterlyForm.getTenure());
			full.get(0).setPayOption(tquarterlyForm.getPayOption());
			full.get(0).setTransactionId(tquarterlyForm.getTransactionId());
			full.get(0).setAmount(tquarterlyForm.getAmount());
			full.get(0).setIntRate(tquarterlyForm.getIntRate());
			full.get(0).setPayDate(tquarterlyForm.getPayDate());
			full.get(0).setTotalAmount(tquarterlyForm.getTotalAmount());
			full.get(0).setLoanDate(tquarterlyForm.getLoanDate());
			full.get(0).setTransactionId(tquarterlyForm.getTransactionId());
			full.get(0).setAmount1(tquarterlyForm.getAmount1());
			full.get(0).setIntRate1(tquarterlyForm.getIntRate1());
			full.get(0).setPayDate1(tquarterlyForm.getPayDate1());
			full.get(0).setTotalAmount1(tquarterlyForm.getTotalAmount1());
			full.get(0).setLoanDate1(tquarterlyForm.getLoanDate1());
			full.get(0).setAmount2(tquarterlyForm.getAmount2());
			full.get(0).setIntRate2(tquarterlyForm.getIntRate2());
			full.get(0).setPayDat2(tquarterlyForm.getPayDat2());
			full.get(0).setTotalAmount2(tquarterlyForm.getTotalAmount2());
			full.get(0).setLoanDate2(tquarterlyForm.getLoanDate2());
			full.get(0).setAmount3(tquarterlyForm.getAmount3());
			full.get(0).setIntRate3(tquarterlyForm.getIntRate3());
			full.get(0).setPayDate3(tquarterlyForm.getPayDate3());
			full.get(0).setTotalAmount3(tquarterlyForm.getTotalAmount3());
			full.get(0).setLoanDate3(tquarterlyForm.getLoanDate3());
			full.get(0).setStatus("Pending");
			tquarterlyDAO.updateQuarterly(full.get(0));
		} else {

			quarter.setMasterKey(tquarterlyForm.getMasterKey());
			quarter.setCustomer(tquarterlyForm.getCustomer());
			quarter.setCustomerEmail(tquarterlyForm.getCustomerEmail());
			quarter.setTenure(tquarterlyForm.getTenure());
			quarter.setPayOption(tquarterlyForm.getPayOption());
			quarter.setTransactionId(tquarterlyForm.getTransactionId());
			quarter.setAmount(tquarterlyForm.getAmount());
			quarter.setIntRate(tquarterlyForm.getIntRate());
			quarter.setPayDate(tquarterlyForm.getPayDate());
			quarter.setTotalAmount(tquarterlyForm.getTotalAmount());
			quarter.setLoanDate(tquarterlyForm.getLoanDate());
			quarter.setTransactionId(tquarterlyForm.getTransactionId());
			quarter.setAmount1(tquarterlyForm.getAmount1());
			quarter.setIntRate1(tquarterlyForm.getIntRate1());
			quarter.setPayDate1(tquarterlyForm.getPayDate1());
			quarter.setTotalAmount1(tquarterlyForm.getTotalAmount1());
			quarter.setLoanDate1(tquarterlyForm.getLoanDate1());
			quarter.setAmount2(tquarterlyForm.getAmount2());
			quarter.setIntRate2(tquarterlyForm.getIntRate2());
			quarter.setPayDat2(tquarterlyForm.getPayDat2());
			quarter.setTotalAmount2(tquarterlyForm.getTotalAmount2());
			quarter.setLoanDate2(tquarterlyForm.getLoanDate2());
			quarter.setAmount3(tquarterlyForm.getAmount3());
			quarter.setIntRate3(tquarterlyForm.getIntRate3());
			quarter.setPayDate3(tquarterlyForm.getPayDate3());
			quarter.setTotalAmount3(tquarterlyForm.getTotalAmount3());
			quarter.setLoanDate3(tquarterlyForm.getLoanDate3());
			quarter.setStatus("Pending");

			tquarterlyDAO.updateQuarterly(quarter);
		}
		TTransaction trans = new TTransaction();

		trans.setTransactionId(tquarterlyForm.getTransactionId());
		trans.setTransactionStatus("Repayment Rates Quarterly");
		trans.setTransactionType("Sent Successfully");

		transcationDAOImpl.insertTransaction(trans);

		model.put("tquarterlyForm", tquarterlyForm);
		model.put("user", user);

		return new ModelAndView("tmasterPlanRePaymentBankTransaction", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentBankTransaction", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("tquarterlyForm", tquarterlyForm);

		return new ModelAndView("tmasterPlanRePaymentBankTransaction", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentQuater", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentQuater(@RequestParam Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		TRepayment master = trepaymenyDAO.getByRepaymentId(id);

		tfullAmountForm.setId(master.getId());
		tfullAmountForm.setMasterKey(master.getMasterKey());
		tfullAmountForm.setCustomer(master.getCustomer());
		tfullAmountForm.setCustomerEmail(master.getCustomerEmail());
		tfullAmountForm.setTenure(master.getTenure());
		tfullAmountForm.setPayOption(master.getPayOption());
		tfullAmountForm.setAmtType(master.getAmtType());
		tfullAmountForm.setTransactionId(master.getTransactionId());

		model.put("tfullAmountForm", tfullAmountForm);
		model.put("user", user);

		return new ModelAndView("tmasterPlanRePaymentQuater", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentDisplayFullConfirm", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentDisplayFullConfirm(ModelMap model,
			@ModelAttribute TFullAmountForm tfullAmountForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		tfullAmountForm.setId(tfullAmountForm.getId());
		tfullAmountForm.setMasterKey(tfullAmountForm.getMasterKey());
		tfullAmountForm.setCustomer(tfullAmountForm.getCustomer());
		tfullAmountForm.setCustomerEmail(tfullAmountForm.getCustomerEmail());
		tfullAmountForm.setTenure(tfullAmountForm.getTenure());
		tfullAmountForm.setPayOption(tfullAmountForm.getPayOption());
		tfullAmountForm.setAmtType(tfullAmountForm.getAmtType());
		tfullAmountForm.setTransactionId(tfullAmountForm.getTransactionId());
		tfullAmountForm.setAmount(tfullAmountForm.getAmount());
		tfullAmountForm.setIntRate(tfullAmountForm.getIntRate());
		tfullAmountForm.setPayDate(tfullAmountForm.getPayDate());
		tfullAmountForm.setTotalAmount(tfullAmountForm.getTotalAmount());
		tfullAmountForm.setLoanDate(tfullAmountForm.getLoanDate());

		model.put("tfullAmountForm", tfullAmountForm);
		model.put("user", user);

		return new ModelAndView("tmasterPlanRePaymentDisplayFullConfirm", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentQuarterSave", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentQuarterSave(ModelMap model, @ModelAttribute TFullAmountForm tfullAmountForm,
			BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		TRepayment master = trepaymenyDAO.getByRepaymentId(tfullAmountForm.getId());
		master.setStatus("Pending");
		master.setbStatus("Approved");

		trepaymenyDAO.updateRepayment(master);

		TFullAmount quarter = new TFullAmount();
		List<TFullAmount> full1 = tfullAmountDAO.getByTransIdList(tfullAmountForm.getTransactionId()).getResultList();
		if (full1 != null && full1.size() > 0) {
			full1.get(0).setStatus(tfullAmountForm.getStatus());
			full1.get(0).setMasterKey(tfullAmountForm.getMasterKey());
			full1.get(0).setCustomer(tfullAmountForm.getCustomer());
			full1.get(0).setCustomerEmail(tfullAmountForm.getCustomerEmail());
			full1.get(0).setTenure(tfullAmountForm.getTenure());
			full1.get(0).setPayOption(tfullAmountForm.getPayOption());
			full1.get(0).setTransactionId(tfullAmountForm.getTransactionId());
			full1.get(0).setAmount(tfullAmountForm.getAmount());
			full1.get(0).setIntRate(tfullAmountForm.getIntRate());
			full1.get(0).setPayDate(tfullAmountForm.getPayDate());
			full1.get(0).setTotalAmount(tfullAmountForm.getTotalAmount());
			full1.get(0).setLoanDate(tfullAmountForm.getLoanDate());
			full1.get(0).setTransactionId(tfullAmountForm.getTransactionId());
			full1.get(0).setStatus("Pending");
			tfullAmountDAO.updateFullAmount(full1.get(0));
		} else {
			quarter.setMasterKey(tfullAmountForm.getMasterKey());
			quarter.setCustomer(tfullAmountForm.getCustomer());
			quarter.setCustomerEmail(tfullAmountForm.getCustomerEmail());
			quarter.setTenure(tfullAmountForm.getTenure());
			quarter.setPayOption(tfullAmountForm.getPayOption());
			quarter.setTransactionId(tfullAmountForm.getTransactionId());
			quarter.setAmount(tfullAmountForm.getAmount());
			quarter.setIntRate(tfullAmountForm.getIntRate());
			quarter.setPayDate(tfullAmountForm.getPayDate());
			quarter.setTotalAmount(tfullAmountForm.getTotalAmount());
			quarter.setLoanDate(tfullAmountForm.getLoanDate());
			quarter.setTransactionId(tfullAmountForm.getTransactionId());
			quarter.setStatus("Pending");

			tfullAmountDAO.updateFullAmount(quarter);
		}
		TTransaction trans = new TTransaction();

		trans.setTransactionId(tfullAmountForm.getTransactionId());
		trans.setTransactionStatus("Repayment Rates Set");
		trans.setTransactionType("Sent Successfully");

		transcationDAOImpl.insertTransaction(trans);

		model.put("tfullAmountForm", tfullAmountForm);
		model.put("user", user);

		return new ModelAndView("tmasterPlanRePaymentQuarterTransaction", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentQuarterTransaction", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentQuarterTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("tfullAmountForm", tfullAmountForm);

		return new ModelAndView("tmasterPlanRePaymentQuarterTransaction", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentHalf", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentHalf(@RequestParam Long id, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		TRepayment master = trepaymenyDAO.getByRepaymentId(id);

		thalfYearlyForm.setId(master.getId());
		thalfYearlyForm.setMasterKey(master.getMasterKey());
		thalfYearlyForm.setCustomer(master.getCustomer());
		thalfYearlyForm.setCustomerEmail(master.getCustomerEmail());
		thalfYearlyForm.setTenure(master.getTenure());
		thalfYearlyForm.setPayOption(master.getPayOption());
		thalfYearlyForm.setAmtType(master.getAmtType());
		thalfYearlyForm.setTransactionId(master.getTransactionId());

		model.put("thalfYearlyForm", thalfYearlyForm);
		model.put("user", user);

		return new ModelAndView("tmasterPlanRePaymentHalf", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentDisplayHalfConfirm", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentDisplayHalfConfirm(ModelMap model,
			@ModelAttribute THalfYearlyForm thalfYearlyForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		thalfYearlyForm.setId(thalfYearlyForm.getId());
		thalfYearlyForm.setMasterKey(thalfYearlyForm.getMasterKey());
		thalfYearlyForm.setCustomer(thalfYearlyForm.getCustomer());
		thalfYearlyForm.setCustomerEmail(thalfYearlyForm.getCustomerEmail());
		thalfYearlyForm.setTenure(thalfYearlyForm.getTenure());
		thalfYearlyForm.setPayOption(thalfYearlyForm.getPayOption());
		thalfYearlyForm.setAmtType(thalfYearlyForm.getAmtType());
		thalfYearlyForm.setTransactionId(thalfYearlyForm.getTransactionId());
		thalfYearlyForm.setAmount(thalfYearlyForm.getAmount());
		thalfYearlyForm.setIntRate(thalfYearlyForm.getIntRate());
		thalfYearlyForm.setPayDate(thalfYearlyForm.getPayDate());
		thalfYearlyForm.setTotalAmount(thalfYearlyForm.getTotalAmount());
		thalfYearlyForm.setLoanDate(thalfYearlyForm.getLoanDate());

		model.put("thalfYearlyForm", thalfYearlyForm);
		model.put("user", user);

		return new ModelAndView("tmasterPlanRePaymentDisplayHalfConfirm", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentHalfSave", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentHalfSave(ModelMap model, @ModelAttribute THalfYearlyForm thalfYearlyForm,
			BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		TRepayment master = trepaymenyDAO.getByRepaymentId(thalfYearlyForm.getId());
		master.setStatus("Pending");
		master.setbStatus("Approved");

		trepaymenyDAO.updateRepayment(master);

		THalfYearly quarter = new THalfYearly();
		List<THalfYearly> full2 = thalfYearlyDAO.getByTransIdList(trepaymentForm.getTransactionId()).getResultList();
		if (full2 != null && full2.size() > 0) {
			full2.get(0).setStatus(thalfYearlyForm.getStatus());
			full2.get(0).setMasterKey(thalfYearlyForm.getMasterKey());
			full2.get(0).setCustomer(thalfYearlyForm.getCustomer());
			full2.get(0).setCustomerEmail(thalfYearlyForm.getCustomerEmail());
			full2.get(0).setTenure(thalfYearlyForm.getTenure());
			full2.get(0).setPayOption(thalfYearlyForm.getPayOption());
			full2.get(0).setTransactionId(thalfYearlyForm.getTransactionId());
			full2.get(0).setAmount(thalfYearlyForm.getAmount());
			full2.get(0).setIntRate(thalfYearlyForm.getIntRate());
			full2.get(0).setPayDate(thalfYearlyForm.getPayDate());
			full2.get(0).setTotalAmount(thalfYearlyForm.getTotalAmount());
			full2.get(0).setLoanDate(thalfYearlyForm.getLoanDate());
			full2.get(0).setTransactionId(thalfYearlyForm.getTransactionId());
			full2.get(0).setStatus("Pending");
			thalfYearlyDAO.updateHalfYearly(full2.get(0));
		} else {
			quarter.setMasterKey(thalfYearlyForm.getMasterKey());
			quarter.setCustomer(thalfYearlyForm.getCustomer());
			quarter.setCustomerEmail(thalfYearlyForm.getCustomerEmail());
			quarter.setTenure(thalfYearlyForm.getTenure());
			quarter.setPayOption(thalfYearlyForm.getPayOption());
			quarter.setTransactionId(thalfYearlyForm.getTransactionId());
			quarter.setAmount(thalfYearlyForm.getAmount());
			quarter.setIntRate(thalfYearlyForm.getIntRate());
			quarter.setPayDate(thalfYearlyForm.getPayDate());
			quarter.setTotalAmount(thalfYearlyForm.getTotalAmount());
			quarter.setLoanDate(thalfYearlyForm.getLoanDate());
			quarter.setTransactionId(thalfYearlyForm.getTransactionId());
			quarter.setStatus("Pending");

			thalfYearlyDAO.updateHalfYearly(quarter);
		}
		TTransaction trans = new TTransaction();

		trans.setTransactionId(thalfYearlyForm.getTransactionId());
		trans.setTransactionStatus("Repayment Rates Set");
		trans.setTransactionType("Sent Successfully");

		transcationDAOImpl.insertTransaction(trans);

		model.put("thalfYearlyForm", thalfYearlyForm);
		model.put("user", user);

		return new ModelAndView("tmasterPlanRePaymentHalfTransaction", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentHalfTransaction", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentHalfTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("thalfYearlyForm", thalfYearlyForm);

		return new ModelAndView("tmasterPlanRePaymentHalfTransaction", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentReject", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentReject(ModelMap model, @RequestParam Long id,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		TRepayment repay = trepaymenyDAO.getByRepaymentId(id);

		repay.setbStatus("Rejected");
		trepaymenyDAO.updateRepayment(repay);

		attributes.addFlashAttribute("success", "Rejected Successfully");

		model.put("user", user);
		model.put("thalfYearlyForm", thalfYearlyForm);

		return new ModelAndView("redirect:tmasterPlanRePaymentBank");

	}

	@RequestMapping(value = "/tmasterPlanRePaymentMadeList", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentMadeList(ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		List<TRepayment> repay = trepaymenyDAO.getRepayByAccept().getResultList();
		if (repay != null && repay.size() > 0) {
			model.put("user", user);
			model.put("repay", repay);
		}
		return new ModelAndView("tmasterPlanRePaymentMadeList", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentMadeStatus", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentMadeStatus(@RequestParam("id") Long id, ModelMap model) {

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

		return new ModelAndView("tmasterPlanRePaymentMadeStatus", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentMadeStatusConfirm", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentMadeStatusConfirm(ModelMap model,
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
		trepaymentForm.setAmountPaid(trepaymentForm.getAmountPaid());

		model.put("trepaymentForm", trepaymentForm);
		model.put("user", user);

		return new ModelAndView("tmasterPlanRePaymentMadeStatusConfirm", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentMadeStatusPost", method = RequestMethod.POST)
	public ModelAndView masterPlanRePaymentMadeStatusPost(ModelMap model, @ModelAttribute TRepaymentForm trepaymentForm,
			BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		TRepayment repay = trepaymenyDAO.getByRepaymentId(trepaymentForm.getId());
		repay.setAmountPaid(trepaymentForm.getAmountPaid());
		repay.setPayConfirm("Paid");
		repay.setMoneyStatus("Approved");
		trepaymenyDAO.updateRepayment(repay);

		TMasterPlan plan = tmasterPlanDAO.getMasterPlanByMasterKey(trepaymentForm.getMasterKey()).getSingleResult();
		if (plan != null) {
			Float amt = plan.getAmountPaid();
			Float total = amt + trepaymentForm.getAmountPaid();

			plan.setAmountPaid(total);
			tmasterPlanDAO.updatePlan(plan);
		}

		TTransaction trans = new TTransaction();

		trans.setTransactionId(trepaymentForm.getTransactionId());
		trans.setTransactionStatus("Repayment Made");
		trans.setTransactionType("Sent Successfully");
		transcationDAOImpl.insertTransaction(trans);

		model.put("trepaymentForm", trepaymentForm);
		model.put("user", user);

		return new ModelAndView("tmasterPlanRePaymentMadeStatusTrans", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentMadeStatusTrans", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentMadeStatusTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("trepaymentForm", trepaymentForm);

		return new ModelAndView("tmasterPlanRePaymentMadeStatusTrans", "model", model);

	}

	@RequestMapping(value = "/tmasterPlanRePaymentBankQuarterly", method = RequestMethod.GET)
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

				return new ModelAndView("redirect:tmasterPlanRePaymentMadeList");

			}
		}
		return new ModelAndView("tmasterPlanRePaymentBankQuarterly", "model", model);
	}

	@RequestMapping(value = "/tmasterPlanRePaymentBankFullList", method = RequestMethod.GET)
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

				return new ModelAndView("redirect:tmasterPlanRePaymentMadeList");

			}
		}
		return new ModelAndView("tmasterPlanRePaymentBankFullList", "model", model);
	}

	@RequestMapping(value = "/tmasterPlanRePaymentBankHalfYearly", method = RequestMethod.GET)
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

				return new ModelAndView("redirect:tmasterPlanRePaymentMadeList");

			}
		}
		return new ModelAndView("tmasterPlanRePaymentBankHalfYearly", "model", model);
	}

	@RequestMapping(value = "/tmasterPlanRePaymentBankList", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<TRepayment> repayList = trepaymenyDAO.getRepayFullList().getResultList();

		if (repayList != null && repayList.size() > 0) {
			model.put("repayList", repayList);
			model.put("user", user);
		}
		return new ModelAndView("tmasterPlanRePaymentBankList", "model", model);

	}

	@RequestMapping(value = "/tradeBankTransactionList", method = RequestMethod.GET)
	public ModelAndView getALLTransactionList(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		Collection<TTransaction> transactionPageList = transcationDAOImpl.getList();

		model.put("user", user);
		model.put("transactionPageList", transactionPageList);
		return new ModelAndView("tradeBankTransactionList", "model", model);

	}

	@RequestMapping(value = "/tBankHelp", method = RequestMethod.GET)
	public ModelAndView adminHelp(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("tBankHelp", "model", model);

	}

	@RequestMapping(value = "/tCreateClientAdmin", method = RequestMethod.GET)
	public ModelAndView createClientAdmin(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		// List<ClientAdminForm> customerList1 =
		// clientAdminDAO.getCustByIdAndStatusComp(user.getId()).getResultList();

		List<ClientAdmin> customerList = clientAdminDAO.getClientAdminByStatusAndTradeFlag(Boolean.TRUE)
				.getResultList();

		List<ClientAdmin> adm = new ArrayList<ClientAdmin>();

		List<Company> companyList = companyDAO.getCompanyByStatusAndIsForTrading("Approved", Boolean.TRUE);

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
		return new ModelAndView("tCreateClientAdmin", "model", model);

	}

	@RequestMapping(value = "/tClientAdminConfirm", method = RequestMethod.POST)
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

			return new ModelAndView("tClientAdminConfirm", "model", model);
		}
	}

	@RequestMapping(value = "/tClientAdminSave", method = RequestMethod.POST)
	public ModelAndView clientAdminSave(@ModelAttribute ClientAdminForm clientAdminForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

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
		customer.setIsForTrading(Boolean.TRUE);
		clientAdminDAO.insertCustomer(customer);

		TTransaction trans = new TTransaction();

		trans.setTransactionId(clientAdminForm.getTransactionId());
		trans.setTransactionStatus("Client Admin Saved");
		trans.setTransactionType("Customer Head");

		transcationDAOImpl.insertTransaction(trans);

		model.put("clientAdminForm", clientAdminForm);
		model.put("user", user);

		return new ModelAndView("tClientAdminTransaction", "model", model);

	}

//	@RequestMapping(value = "/clientAdminTransaction", method = RequestMethod.GET)
//	public ModelAndView clientAdminTransaction(ModelMap model) {
//
//		EndUser user = getCurrentLoggedUserDetails();
//
//		model.put("user", user);
//		model.put("clientAdminForm", clientAdminForm);
//
//		return new ModelAndView("clientAdminTransaction", "model", model);
//
//	}

	@RequestMapping(value = "/tSelectClientAdmin", method = RequestMethod.GET)
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

		return new ModelAndView("tSelectClientAdmin", "model", model);

	}

	@RequestMapping(value = "/tUpdateClientAdminConfirm", method = RequestMethod.POST)
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

		return new ModelAndView("tUpdateClientAdminConfirm", "model", model);

	}

	@RequestMapping(value = "/tUpdateClientAdmin", method = RequestMethod.POST)
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

		return new ModelAndView("redirect:tCreateClientAdmin");

	}

	@RequestMapping(value = "/tCreateCompany", method = RequestMethod.GET)
	public ModelAndView createCompany(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		companyForm = new CompanyForm();
		model.put("companyForm", companyForm);
		return new ModelAndView("tCreateCompany", "model", model);
	}

	@RequestMapping(value = "/tCreateCompanyConfirm", method = RequestMethod.POST)
	public ModelAndView createCompanyConfirm(@ModelAttribute CompanyForm companyForm, ModelMap model,
			RedirectAttributes attribute) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("companyForm", companyForm);
		model.put("user", user);
		return new ModelAndView("tCreateCompanyConfirm", "model", model);
	}

	@RequestMapping(value = "/tCreateCompanySave", method = RequestMethod.POST)
	public ModelAndView createCompanySave(@ModelAttribute CompanyForm companyForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		Company company = new Company();
		company.setCompanyName(companyForm.getCompanyName());
		company.setCompanyPrefix(companyForm.getCompanyPrefix());
		company.setAddress(companyForm.getAddress());
		company.setCity(companyForm.getCity());
		company.setCountry(companyForm.getCountry());
		company.setPincode(companyForm.getPincode());
		company.setState(companyForm.getState());
		company.setStatus("Pending");
		company.setIsForTrading(Boolean.TRUE);

		companyDAO.insertCompany(company);

		TTransaction trans = new TTransaction();
		trans.setTransactionId(companyForm.getTransactionId());
		trans.setTransactionStatus("Company Created");
		trans.setTransactionType("Company");

		transcationDAOImpl.insertTransaction(trans);

		model.put("companyForm", companyForm);
		model.put("transaction", trans);
		model.put("user", user);

		return new ModelAndView("tCompanyTransaction", "model", model);

	}

}
