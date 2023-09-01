package annona.trade.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.theme.CookieThemeResolver;

import annona.dao.ClientAppMngDAO;
import annona.dao.CompanyDAO;
import annona.dao.CustomerDAO;
import annona.dao.EndUserDAO;
import annona.domain.ClientAppMng;
import annona.domain.Company;
import annona.domain.CustomerHead;
import annona.domain.EndUser;
import annona.domain.Transaction;
import annona.form.ClientAppMngForm;
import annona.form.CustomerHeadForm;
import annona.form.EndUserForm;
import annona.trade.dao.TBuyingCostDAO;
import annona.trade.dao.TCollateralDAO;
import annona.trade.dao.TFullAmountDAO;
import annona.trade.dao.THalfYearlyDAO;
import annona.trade.dao.TMasterPlanDAO;
import annona.trade.dao.TQuarterlyDAO;
import annona.trade.dao.TRepaymentDAO;
import annona.trade.dao.TTransactionDAO;
import annona.trade.domain.TBuyingCost;
import annona.trade.domain.TCollateral;
import annona.trade.domain.TFullAmount;
import annona.trade.domain.THalfYearly;
import annona.trade.domain.TMasterPlan;
import annona.trade.domain.TQuarterly;
import annona.trade.domain.TRepayment;
import annona.trade.domain.TTransaction;
import annona.web.AdminController;

@Controller
@RequestMapping("/customerTradeAdmin")
public class TradeCustomerAdmin {

	@Autowired
	EndUserDAO endUserDAOImpl;
	
	@Autowired
	CustomerDAO customerDAO;
	
	@Autowired
	ClientAppMngDAO clientAppMngDAO;
	
	@Autowired
	ClientAppMngForm clientAppMngForm;

	@Autowired
	TTransactionDAO transcationDAOImpl;

	@Autowired
	EndUserForm endUserForm;

	@Autowired
	TBuyingCostDAO tbuyingCostDAO;

	@Autowired
	TCollateralDAO tcollateralDAO;

	@Autowired
	TMasterPlanDAO tmasterPlanDAO;

	@Autowired
	TRepaymentDAO trepaymenyDAO;

	@Autowired
	THalfYearlyDAO thalfYearlyDAO;

	@Autowired
	TFullAmountDAO tfullAmountDAO;

	@Autowired
	TQuarterlyDAO tquarterlyDAO;

	@Autowired
	CompanyDAO companyDAO;
	
	@Autowired
	CustomerHeadForm customerHeadForm;


	@Autowired
	private JavaMailSender mailSender;

	CookieThemeResolver themeResolver = new CookieThemeResolver();

	static Logger log = Logger.getLogger(AdminController.class.getName());

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

	@RequestMapping(value = "/custTradeAdminPage", method = RequestMethod.GET)
	public ModelAndView showadminDashBoard(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("custTradeAdmin", "model", model);
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

		return "redirect:custTradeAdminPage";
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

		return "redirect:custTradeAdminPage";
	}

	@RequestMapping(value = "/editTadminProfile", method = RequestMethod.GET)
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

		return new ModelAndView("editTadminProfile", "model", model);

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
		return "redirect:editTadminProfile?id=" + endUserForm.getId();
	}

	@RequestMapping(value = "/confirmEditTadminProfile", method = RequestMethod.POST)
	public ModelAndView confirmEditAdminProfile(ModelMap model, @ModelAttribute EndUserForm endUserForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("confirmEditTadminProfile", "model", model);

	}

	@RequestMapping(value = "/updateTadminDetails", method = RequestMethod.POST)
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

		return new ModelAndView("updateTadminSuccess", "model", model);

	}

	@RequestMapping(value = "/editTadminPWD", method = RequestMethod.GET)
	public ModelAndView editAdminPWD(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser userProfile = endUserDAOImpl.findId(id);

		endUserForm.setId(userProfile.getId());

		endUserForm.setTransactionId(userProfile.getTransactionId());

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("editTadminPWD", "model", model);

	}

	@RequestMapping(value = "/updateEditTadminPWD", method = RequestMethod.POST)
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

		return new ModelAndView("updateTadminSuccess", "model", model);

	}

	@RequestMapping(value = "/tAdminMasterPlanFullDetails", method = RequestMethod.GET)
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

		return new ModelAndView("tAdminMasterPlanFullDetails", "model", model);

	}

	@RequestMapping(value = "/tAdminMasterPlanFullList", method = RequestMethod.GET)
	public ModelAndView masterPlanFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<TMasterPlan> masterList = tmasterPlanDAO.getAllMasterPlans().getResultList();

		model.put("user", user);
		model.put("masterList", masterList);

		return new ModelAndView("tAdminMasterPlanFullList", "model", model);

	}
	
	@RequestMapping(value = "/tBankCustomerHeadList", method = RequestMethod.GET)
	public ModelAndView CustomerHeadList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		List<CustomerHeadForm> customerList = customerDAO.getCustByStatusCompIDAndIsForTrading(user.getCompanyId(), Boolean.TRUE);
		model.put("user", user);
		if(customerList != null && customerList.size() > 0)
		{
	    model.put("customerList", customerList);

		return new ModelAndView("tBankCustomerHeadList", "model", model);
		}
		else
		{
			return new ModelAndView("tNoDataFound", "model", model);
		}

	}
	
	@RequestMapping(value = "/tClientAppMngFullList", method = RequestMethod.GET)
	public ModelAndView clientAppMngFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<ClientAppMngForm> clientList = clientAppMngDAO.getCustAppMngByStatusCompIDAndIsForTrading(user.getCompanyId(),Boolean.TRUE);
				

		model.put("user", user);
		if(clientList != null && clientList.size() > 0)
		{
		model.put("clientList", clientList);

		return new ModelAndView("tClientAppMngFullList", "model", model);
		}
		else
		{
			return new ModelAndView("tNoDataFound", "model", model);
		}

	}

	@RequestMapping(value = "/tmasterPlanRePaymentAdminQuarterly", method = RequestMethod.GET)
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

				return new ModelAndView("redirect:tmasterPlanRePaymentAdminList");

			}
		}
		return new ModelAndView("tmasterPlanRePaymentAdminQuarterly", "model", model);
	}

	@RequestMapping(value = "/tmasterPlanRePaymentAdminFullList", method = RequestMethod.GET)
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

				return new ModelAndView("redirect:tmasterPlanRePaymentAdminList");

			}
		}
		return new ModelAndView("tmasterPlanRePaymentAdminFullList", "model", model);
	}

	@RequestMapping(value = "/tmasterPlanRePaymentAdminHalfYearly", method = RequestMethod.GET)
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

				return new ModelAndView("redirect:tmasterPlanRePaymentAdminList");

			}
		}
		return new ModelAndView("tmasterPlanRePaymentAdminHalfYearly", "model", model);
	}

	@RequestMapping(value = "/tmasterPlanRePaymentAdminList", method = RequestMethod.GET)
	public ModelAndView masterPlanRePaymentList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		List<TRepayment> repayList = trepaymenyDAO.getRepayFullList().getResultList();

		if (repayList != null && repayList.size() > 0) {
			model.put("repayList", repayList);
			model.put("user", user);
		}
		return new ModelAndView("tmasterPlanRePaymentAdminList", "model", model);

	}

	@RequestMapping(value = "/tCustAdminHelp", method = RequestMethod.GET)
	public ModelAndView adminHelp(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("tCustAdminHelp", "model", model);

	}

	@RequestMapping(value = "/tCreateClientAppMng", method = RequestMethod.GET)
	public ModelAndView createClientAdmin(ModelMap model) {

		List<ClientAppMng> appMngList = clientAppMngDAO.getByPending().getResultList();

		EndUser user = getCurrentLoggedUserDetails();

		List<ClientAppMng> adm = new ArrayList<ClientAppMng>();

		List<Company> companyList = companyDAO.getCompanyByStatusAndIsForTrading("Approved", Boolean.TRUE);

		model.put("user", user);

		for (int a = 0; a < appMngList.size(); a++) {

			ClientAppMng appMng = appMngList.get(a);

			for (int i = 0; i < companyList.size(); i++) {

				Company comp = companyList.get(i);

				if (appMng.getCompanyId() == comp.getId() && appMng.getCompanyId() != null) {

					appMng.setAddress(comp.getAddress());
					appMng.setCompanyName(comp.getCompanyName());
				}

			}
			adm.add(appMng);

		}

		if (companyList != null && companyList.size() > 0) {

			model.addAttribute("companyList", companyList);
			// model.put("companyList", companyList);

		}

		model.put("user", user);
		model.put("clientAppMngForm", clientAppMngForm);
		model.put("appMngList", appMngList);

		return new ModelAndView("tCreateClientAppMng", "model", model);

	}

	@RequestMapping(value = "/tClientAppMngConfirm", method = RequestMethod.POST)
	public ModelAndView clientAppMngConfirm(@ModelAttribute ClientAppMngForm clientAppMngForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		List<ClientAppMng> head = clientAppMngDAO.getClientAppMngList(clientAppMngForm.getName()).getResultList();

		List<EndUser> endUser = endUserDAOImpl.findByUsername(clientAppMngForm.getName()).getResultList();
		Company company = companyDAO.getByCompanyId(clientAppMngForm.getCompanyId());

		if (endUser.size() != 0 || head.size() != 0) {
			attributes.addFlashAttribute("success", "Customer Name Already Exists");

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
			clientAppMngForm.setAltContactNum(clientAppMngForm.getAltContactNum());
			clientAppMngForm.setEmail(clientAppMngForm.getEmail());
			clientAppMngForm.setAltEmail(clientAppMngForm.getAltEmail());
			clientAppMngForm.setCompanyPrefix(clientAppMngForm.getCompanyPrefix());
			clientAppMngForm.setPosition(clientAppMngForm.getPosition());
			clientAppMngForm.setGender(clientAppMngForm.getGender());
			clientAppMngForm.setDateOfBirth(clientAppMngForm.getDateOfBirth());
			clientAppMngForm.setCustomerPrefix(clientAppMngForm.getCustomerPrefix());
			clientAppMngForm.setManager(clientAppMngForm.getManager());
			clientAppMngForm.setManagerEmail(clientAppMngForm.getManagerEmail());
			clientAppMngForm.setAccExpiryDate(clientAppMngForm.getAccExpiryDate());

			model.put("clientAppMngForm", clientAppMngForm);
			model.put("user", user);

			return new ModelAndView("tClientAppMngConfirm", "model", model);
		}
	}

	@RequestMapping(value = "/tClientAppMngSave", method = RequestMethod.POST)
	public ModelAndView clientAppMngSave(@ModelAttribute ClientAppMngForm clientAppMngForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		Company company = companyDAO.getByCompanyName(clientAppMngForm.getCompanyName());

		ClientAppMng customer = new ClientAppMng();
		customer.setName(clientAppMngForm.getName());
		// customer.setCompanyName(clientAppMngForm.getCompanyName());
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
		customer.setIsForTrading(Boolean.TRUE);
		clientAppMngDAO.insertCustomer(customer);
		TTransaction trans = new TTransaction();

		trans.setTransactionId(clientAppMngForm.getTransactionId());
		trans.setTransactionStatus("Client App Mng Saved");
		trans.setTransactionType("Customer App MNg");
		;
		transcationDAOImpl.insertTransaction(trans);

		model.put("clientAppMngForm", clientAppMngForm);
		model.put("user", user);

		return new ModelAndView("tClientAppMngTransaction", "model", model);

	}
	
	@RequestMapping(value = "/tSelectClientAppMng", method = RequestMethod.GET)
	public ModelAndView selectClientAdmin(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

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

		return new ModelAndView("tSelectClientAppMng", "model", model);

	}

	@RequestMapping(value = "/tUpdateClientAppMngConfirm", method = RequestMethod.POST)
	public ModelAndView updateClientAppMngConfirm(@ModelAttribute ClientAppMngForm clientAppMngForm, ModelMap model,
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
		clientAppMngForm.setCustomerPrefix(clientAppMngForm.getCustomerPrefix());
		clientAppMngForm.setManager(clientAppMngForm.getManager());
		clientAppMngForm.setManagerEmail(clientAppMngForm.getManagerEmail());

		model.put("clientAppMngForm", clientAppMngForm);
		model.put("user", user);

		return new ModelAndView("tUpdateClientAppMngConfirm", "model", model);

	}

	@RequestMapping(value = "/tUpdateClientAppMng", method = RequestMethod.POST)
	public ModelAndView updateClientAppMng(ModelMap model, @ModelAttribute ClientAppMngForm clientAppMngForm,
			RedirectAttributes attributes) {

		ClientAppMng customer = clientAppMngDAO.getByClientAppMngId(clientAppMngForm.getId());

		Company company = companyDAO.getByCompanyName(clientAppMngForm.getCompanyName());

		customer.setName(clientAppMngForm.getName());
		// customer.setCompanyName(clientAppMngForm.getCompanyName());
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

		return new ModelAndView("redirect:tCreateClientAppMng");

	}
	
	@RequestMapping(value = "/tCreateCustomerHead", method = RequestMethod.GET)
	public ModelAndView createCustomerHead(ModelMap model) {

		List<CustomerHead> customerList = customerDAO.getByPending().getResultList();

		EndUser user = getCurrentLoggedUserDetails();

		List<Company> companyList = companyDAO.getCompanyByStatusAndIsForTrading("Approved", Boolean.TRUE);
		List<CustomerHead> customerList1 = new ArrayList<CustomerHead>();

		for (int a = 0; a < customerList.size(); a++) {

			CustomerHead customerHead = customerList.get(a);

			for (int i = 0; i < companyList.size(); i++) {

				Company comp = companyList.get(i);

				if (customerHead.getCompanyId() == comp.getId() && customerHead.getCompanyId() != null) {

					customerHead.setAddress(comp.getAddress());
					customerHead.setCompanyName(comp.getCompanyName());
				}

			}
			customerList1.add(customerHead);
		}

		if (companyList != null && companyList.size() > 0) {

			model.addAttribute("companyList", companyList);

		}

		model.put("user", user);
		model.put("customerHeadForm", customerHeadForm);
		model.put("customerList", customerList);

		return new ModelAndView("tCreateCustomerHead", "model", model);

	}

	@RequestMapping(value = "/tSelectCustomerHead", method = RequestMethod.GET)
	public ModelAndView selectCustomerHead(ModelMap model, @RequestParam("id") Long id, RedirectAttributes attributes) {

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

		return new ModelAndView("tSelectCustomerHead", "model", model);

	}

	@RequestMapping(value = "/tUpdateCustomerHeadConfirm", method = RequestMethod.POST)
	public ModelAndView updateCustomerHeadConfirm(@ModelAttribute CustomerHeadForm customerHeadForm, ModelMap model,
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
		customerHeadForm.setCustomerPrefix(customerHeadForm.getCustomerPrefix());
		customerHeadForm.setManager(customerHeadForm.getManager());
		customerHeadForm.setManagerEmail(customerHeadForm.getManagerEmail());

		model.put("customerHeadForm", customerHeadForm);
		model.put("user", user);

		return new ModelAndView("tUpdateCustomerHeadConfirm", "model", model);

	}

	@RequestMapping(value = "/tUpdateCustomerHead", method = RequestMethod.POST)
	public ModelAndView roleupdate(ModelMap model, @ModelAttribute CustomerHeadForm customerHeadForm,
			RedirectAttributes attributes) {

		CustomerHead customer = customerDAO.findCustomers(customerHeadForm.getId());

		Company company = companyDAO.getByCompanyName(customerHeadForm.getCompanyName());

		customer.setName(customerHeadForm.getName());
		// customer.setCompanyName(customerHeadForm.getCompanyName());
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

		return new ModelAndView("redirect:tCreateCustomerHead");

	}

	@RequestMapping(value = "/tCustomerHeadConfirm", method = RequestMethod.POST)
	public ModelAndView customerConfirm(@ModelAttribute CustomerHeadForm customerHeadForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		List<CustomerHead> head = customerDAO.getCustomerDetailsList(customerHeadForm.getName()).getResultList();

		Company company = companyDAO.getByCompanyId(customerHeadForm.getCompanyId());

		List<EndUser> endUser = endUserDAOImpl.findByUsername(customerHeadForm.getName()).getResultList();
		if (endUser.size() == 0 || head.size() == 0) {

			customerHeadForm.setName(customerHeadForm.getName());
			customerHeadForm.setCompanyName(company.getCompanyName());
			customerHeadForm.setCountry(customerHeadForm.getCountry());
			customerHeadForm.setState(customerHeadForm.getState());
			customerHeadForm.setCity(customerHeadForm.getCity());
			customerHeadForm.setAddress(company.getAddress());
			customerHeadForm.setPincode(customerHeadForm.getPincode());
			customerHeadForm.setContactNum(customerHeadForm.getContactNum());
			customerHeadForm.setAltContactNum(customerHeadForm.getAltContactNum());
			customerHeadForm.setEmail(customerHeadForm.getEmail());
			customerHeadForm.setAltEmail(customerHeadForm.getAltEmail());
			customerHeadForm.setCompanyPrefix(customerHeadForm.getCompanyPrefix());
			customerHeadForm.setPosition(customerHeadForm.getPosition());
			customerHeadForm.setGender(customerHeadForm.getGender());
			customerHeadForm.setDateOfBirth(customerHeadForm.getDateOfBirth());
			customerHeadForm.setCustomerPrefix(customerHeadForm.getCustomerPrefix());
			customerHeadForm.setManager(customerHeadForm.getManager());
			customerHeadForm.setManagerEmail(customerHeadForm.getManagerEmail());
			customerHeadForm.setAccExpiryDate(customerHeadForm.getAccExpiryDate());

			model.put("customerHeadForm", customerHeadForm);
			model.put("user", user);

			return new ModelAndView("tCustomerHeadConfirm", "model", model);
		} else {
			attributes.addFlashAttribute("success", "Customer Name Already Exists");

			return new ModelAndView("redirect:tCreateCustomerHead");

		}
	}

	@RequestMapping(value = "/tCustomerHeadSave", method = RequestMethod.POST)
	public ModelAndView customerSave(@ModelAttribute CustomerHeadForm customerHeadForm, ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

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
		customer.setIsForTrading(Boolean.TRUE);
		customerDAO.insertCustomer(customer);
		TTransaction trans = new TTransaction();

		trans.setTransactionId(customerHeadForm.getTransactionId());
		trans.setTransactionStatus("Customer Head Saved");
		trans.setTransactionType("Customer Head");
		transcationDAOImpl.insertTransaction(trans);

		model.put("customerHeadForm", customerHeadForm);
		model.put("user", user);

		return new ModelAndView("tCustomerHeadTransaction", "model", model);

	}
}
