package annona.trade.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.theme.CookieThemeResolver;

import annona.dao.EndUserDAO;
import annona.domain.EndUser;
import annona.form.EndUserForm;
import annona.trade.dao.BuyerTradeDAO;
import annona.trade.dao.SupplierTradeDAO;
import annona.trade.dao.TBuyingCostDAO;
import annona.trade.dao.TCollateralDAO;
import annona.trade.dao.TMasterPlanDAO;
import annona.trade.dao.TRepaymentDAO;
import annona.trade.dao.TTransactionDAO;
import annona.trade.dao.TradeNotificationDAO;
import annona.trade.domain.BuyerTrade;
import annona.trade.domain.SupplierTrade;
import annona.trade.domain.TBuyingCost;
import annona.trade.domain.TCollateral;
import annona.trade.domain.TMasterPlan;
import annona.trade.domain.TRepayment;
import annona.trade.domain.TTransaction;
import annona.trade.form.BuyerTradeForm;
import annona.trade.form.SupplierTradeForm;
import annona.trade.form.TMasterPlanForm;
import annona.trade.form.TRepaymentForm;
import annona.web.ApprovalMngController;

@Controller
@RequestMapping("/tClientAppMng")
public class TradeCustomerAppMngController {

	
	@Autowired
	EndUserDAO endUserDAOImpl;
	@Autowired
	EndUserForm endUserForm;
	@Autowired
	TTransactionDAO transcationDAOImpl;
	
	@Autowired
	TCollateralDAO tcollateralDAO;
	
	@Autowired
	TMasterPlanForm tMasterPlanForm;
	
	@Autowired
	TBuyingCostDAO tbuyingCostDAO;
	
	@Autowired
	BuyerTradeForm buyerTradeForm;
	@Autowired
	SupplierTradeForm supplierTradeForm;
	
	@Autowired
	TMasterPlanDAO tmasterPlanDAO;
	
	@Autowired
	BuyerTradeDAO buyerTradeDAO;
	
	@Autowired
	SupplierTradeDAO supplierTradeDAO;
	
	@Autowired
	TRepaymentForm trepaymentForm;

	@Autowired
	TRepaymentDAO trepaymenyDAO;
	
	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	TradeNotificationDAO tradeNotificationDAO;

	CookieThemeResolver themeResolver = new CookieThemeResolver();

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	protected Logger log = Logger.getLogger(ApprovalMngController.class
			.getName());

	private String getCurrentLoggedUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();

	}
	
	@ModelAttribute("requestCurrentUser")
	public EndUser getUserDetails() {
		EndUser enUser = getCurrentLoggedUserDetails();
		String url = "data:image/JPG;base64,"
				+ Base64.encodeBase64String(enUser.getImage());
		enUser.setImageName(url);		
		
		return enUser;
	}

	private EndUser getCurrentLoggedUserDetails() {

		EndUser endUser = endUserDAOImpl.findByUsername(
				getCurrentLoggedUserName()).getSingleResult();

		return endUser;

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

		return "redirect:tClientApprMng";
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

		return "redirect:tClientApprMng";
	}


	@RequestMapping(value = "/tClientApprMng", method = RequestMethod.GET)
	public ModelAndView showApprMngDashBoard(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("tClientApprMngPage", "model", model);

	}
	
	
	@RequestMapping(value = "/editTCustAppProfile", method = RequestMethod.GET)
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

		return new ModelAndView("editTCustAppProfile", "model", model);

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
		return "redirect:editTCustAppProfile?id="+endUserForm.getId();
	}

	@RequestMapping(value = "/confirmEditTCustAppProfile", method = RequestMethod.POST)
	public ModelAndView confirmEditAdminProfile(ModelMap model,
			@ModelAttribute EndUserForm endUserForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("confirmEditTCustAppProfile", "model", model);

	}

	@RequestMapping(value = "/updateTCustAppDetails", method = RequestMethod.POST)
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

		return new ModelAndView("updateTCustAppSuccess", "model", model);

	}

	@RequestMapping(value = "/editTCustAppPWD", method = RequestMethod.GET)
	public ModelAndView editAdminPWD(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser userProfile = endUserDAOImpl.findId(id);

		endUserForm.setId(userProfile.getId());

		endUserForm.setTransactionId(userProfile.getTransactionId());

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("editTCustAppPWD", "model", model);

	}

	@RequestMapping(value = "/updateEditTCustAppPWD", method = RequestMethod.POST)
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

		return new ModelAndView("updateTCustAppSuccess", "model", model);

	}
	//Buyer List

		@RequestMapping(value = "/buyerCustomerTradeList", method = RequestMethod.GET)
		public ModelAndView buyerCustomerHeadBranchList(ModelMap model) {

			EndUser user = endUserDAOImpl
					.findByUsername(getCurrentLoggedUserName()).getSingleResult();

			buyerTradeForm.setEndUser(user);
			buyerTradeForm.setName(user.getCustomerHeadName());
			buyerTradeForm.setbName(user.getUserName());

			List<BuyerTrade> newbuyerList = buyerTradeDAO.getForcApproval().getResultList();

			model.put("user", user);
			model.put("newbuyerList", newbuyerList);
			model.put("buyerTradeForm", buyerTradeForm);

			return new ModelAndView("buyerCustomerTradeList", "model", model);
		}
		
		@RequestMapping(value = "/buyerPageShowApproval", method = RequestMethod.GET)
		public ModelAndView buyerPageShow(@RequestParam Long id, ModelMap model,
				RedirectAttributes attributes) {
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

			model.put("user", user);

			model.put("user", user);

			model.put("buyerTradeForm", buyerTradeForm);

			return new ModelAndView("buyerPageShowApproval", "model", model);
		}

		@RequestMapping(value = "/buyerPageShowConfirmApproval", method = RequestMethod.POST)
		public ModelAndView approveBankEmpConfrim(
				@ModelAttribute BuyerTradeForm buyerTradeForm, ModelMap model,
				RedirectAttributes attributes) {
			EndUser user = getCurrentLoggedUserDetails();

			model.put("user", user);
			model.put("buyerTradeForm", buyerTradeForm);

			return new ModelAndView("buyerPageShowConfirmApproval", "model", model);
		}

		@RequestMapping(value = "/updatebuyerPageShowConfrim", method = RequestMethod.POST)
		public ModelAndView updatebuyerPageShowConfrim(
				@ModelAttribute BuyerTradeForm buyerTradeForm, ModelMap model,
				RedirectAttributes attributes) {

			BuyerTrade buyer = buyerTradeDAO.findId(buyerTradeForm.getId());
			TTransaction transaction = new TTransaction();
			buyer.setId(buyerTradeForm.getId());
			buyer.setBuyerName(buyerTradeForm.getBuyerName());
			buyer.setName(buyerTradeForm.getName());
			buyer.setbName(buyerTradeForm.getbName());
			buyer.setContactNum(buyerTradeForm.getContactNum());
			buyer.setUniqueKey(buyerTradeForm.getUniqueKey());
			buyer.setEmail(buyerTradeForm.getEmail());
		    buyer.setcStatus(buyerTradeForm.getcStatus());
			buyer.setComment(buyerTradeForm.getComment());
			buyer.setTransactionId(buyerTradeForm.getTransactionId());
			transaction.setTransactionId(buyerTradeForm.getTransactionId());
			transaction.setTransactionType("Buyer Details Update Status");
			transaction.setTransactionStatus("Buyer  Deatils  status saved successfully");
			if(buyerTradeForm.getcStatus().equals("Approved"))
			{
				buyer.setStatus("Pending");
			}
			
			transcationDAOImpl.insertTransaction(transaction);
			buyerTradeDAO.update(buyer);
			

			model.put("buyerTradeForm", buyerTradeForm);

			return new ModelAndView("approvalbuyerPageSuccessApproval", "model", model);
		}
		//Supplier List
		
		@RequestMapping(value = "/supplierCustomerTradeList", method = RequestMethod.GET)
		public ModelAndView supplierCustomerHeadBranchList(ModelMap model) {

			EndUser user = endUserDAOImpl
					.findByUsername(getCurrentLoggedUserName()).getSingleResult();

			supplierTradeForm.setEndUser(user);
			supplierTradeForm.setName(user.getCustomerHeadName());
			supplierTradeForm.setbName(user.getUserName());

			List<SupplierTrade> supplierList = supplierTradeDAO.getForcApproval().getResultList();

			model.put("user", user);
			model.put("supplierList", supplierList);
			model.put("supplierTradeForm", supplierTradeForm);

			return new ModelAndView("supplierCustomerTradeList", "model", model);
		}
		
		

		@RequestMapping(value = "/supplierPageShowConfirmApproval", method = RequestMethod.GET)
		public ModelAndView supplierPageShow(@RequestParam Long id, ModelMap model,
				RedirectAttributes attributes) {
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
			supplierTradeForm.setcStatus(supplier.getcStatus());
			model.put("user", user);
			model.put("supplierTradeForm", supplierTradeForm);

			return new ModelAndView("supplierPageShowConfirmApproval", "model", model);
		}

		
		@RequestMapping(value = "/supplierPageShowApprovalConfirm", method = RequestMethod.POST)
		public ModelAndView approvesupplierPageShowConfirm(
				@ModelAttribute SupplierTradeForm supplierTradeForm, ModelMap model,
				RedirectAttributes attributes) {
			EndUser user = getCurrentLoggedUserDetails();

			model.put("user", user);
			model.put("supplierTradeForm", supplierTradeForm);

			return new ModelAndView("supplierPageShowApprovalConfirm", "model", model);
		}

		@RequestMapping(value = "/updatesupplierPageShowConfrim", method = RequestMethod.POST)
		public ModelAndView updatesupplierPageShowConfrim(
				@ModelAttribute SupplierTradeForm supplierTradeForm, ModelMap model,
				RedirectAttributes attributes) {

			SupplierTrade supplier = supplierTradeDAO.findId(supplierTradeForm.getId());
			TTransaction transaction = new TTransaction();
			supplier.setId(supplierTradeForm.getId());
			supplier.setName(supplierTradeForm.getName());
			supplier.setbName(supplierTradeForm.getbName());
			supplier.setIfsc(supplierTradeForm.getIfsc());
			supplier.setUniquekey(supplierTradeForm.getUniquekey());
			supplier.setSupplierName(supplierTradeForm.getSupplierName());
			supplier.setCompanyName(supplierTradeForm.getCompanyName());
			supplier.setContactNum(supplierTradeForm.getContactNum());
			supplier.setEmail(supplierTradeForm.getEmail());
			supplier.setcStatus(supplierTradeForm.getcStatus());
			supplier.setTransactionId(supplierTradeForm.getTransactionId());
			transaction.setTransactionId(supplierTradeForm.getTransactionId());
			transaction.setTransactionType("Supplier Deatils Update Status");
			transaction
					.setTransactionStatus("Supplier Deatils  status saved successfully");
			transcationDAOImpl.insertTransaction(transaction);
			
			if(supplierTradeForm.getcStatus().equals("Approved"))
			{
				supplier.setStatus("Pending");
			}
			
			
			supplierTradeDAO.update(supplier);
			
		    model.put("supplierTradeForm", supplierTradeForm);

			return new ModelAndView("approvalsupplierPageSuccessApproval", "model", model);
		}
		
		@RequestMapping(value = "/tclientMasterPlanPendingDetails", method = RequestMethod.GET)
		public ModelAndView bankMasterPlanPendingDetails(ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();

			List<TMasterPlan> masterList = tmasterPlanDAO.getMasterPlanByClientPenStatus()
					.getResultList();

			if (masterList != null && masterList.size() > 0) {
				model.put("masterList", masterList);
				model.put("user", user);
			}
			return new ModelAndView("tclientMasterPlanPendingDetails", "model", model);

		}

		@RequestMapping(value = "/tclientMasterPlanFullDetails", method = RequestMethod.GET)
		public ModelAndView bankMasterPlanFullDetails(@RequestParam("id") Long id,
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

			return new ModelAndView("tclientMasterPlanFullDetails", "model", model);

		}
		
		@RequestMapping(value = "/tclientMasterPlanApproveStatus", method = RequestMethod.GET)
		public ModelAndView bankMasterPlanApproveStatus(
				@RequestParam("id") Long id, ModelMap model) {

			TMasterPlan master = tmasterPlanDAO.getByMasterPlanId(id);

			tMasterPlanForm.setId(master.getId());
			tMasterPlanForm.setCustomer(master.getCustomer());
			tMasterPlanForm.setMasterKey(master.getMasterKey());
			tMasterPlanForm.setTransactionId(master.getTransactionId());

			EndUser user = getCurrentLoggedUserDetails();

			model.put("user", user);
			model.put("tMasterPlanForm", tMasterPlanForm);

			return new ModelAndView("tclientMasterPlanApproveStatus", "model", model);

		}

		@RequestMapping(value = "/tclientMasterPlanApproveStatusConfirm", method = RequestMethod.POST)
		public ModelAndView bankMasterPlanApproveStatusConfirm(ModelMap model,
				@ModelAttribute TMasterPlanForm tMasterPlanForm,
				RedirectAttributes attributes) {

			EndUser user = getCurrentLoggedUserDetails();
			tMasterPlanForm.setTransactionId(tMasterPlanForm.getTransactionId());
			tMasterPlanForm.setCustomer(tMasterPlanForm.getCustomer());
			tMasterPlanForm.setaStatus(tMasterPlanForm.getaStatus());
			tMasterPlanForm.setaComment(tMasterPlanForm.getaComment());

			model.put("user", user);
			model.put("tMasterPlanForm", tMasterPlanForm);

			return new ModelAndView("tclientMasterPlanApproveStatusConfirm", "model",
					model);

		}

		@RequestMapping(value = "/tclientMasterPlanApproveStatusPost", method = RequestMethod.POST)
		public ModelAndView draftsMasterPlanInfoConfirm(ModelMap model,
				@ModelAttribute TMasterPlanForm tMasterPlanForm,
				RedirectAttributes attributes) {

			EndUser user = getCurrentLoggedUserDetails();
			tMasterPlanForm.setTransactionId(tMasterPlanForm.getTransactionId());

			TMasterPlan master = tmasterPlanDAO.getByMasterPlanId(tMasterPlanForm
					.getId());

			master.setaStatus(tMasterPlanForm.getaStatus());
			master.setaComment(tMasterPlanForm.getaComment());
			
			String status = tMasterPlanForm.getaStatus();
			
			if(status.equals("Approved"))
			{
				master.setStatus("pending");	
			}

			tmasterPlanDAO.updatePlan(master);

			TTransaction trans = new TTransaction();
			trans.setTransactionId(tMasterPlanForm.getTransactionId());
			trans.setTransactionStatus("Master Plan Approval/Reject By Customer App Mng");
			trans.setTransactionType("Submitted Successfully");

			transcationDAOImpl.insertTransaction(trans);

			model.put("user", user);
			model.put("tMasterPlanForm", tMasterPlanForm);

			return new ModelAndView("tmasterPlanClientAppTransaction", "model", model);

		}

		@RequestMapping(value = "/tmasterPlanClientAppTransaction", method = RequestMethod.GET)
		public ModelAndView collateralTransaction(ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();

			model.put("user", user);
			model.put("tMasterPlanForm", tMasterPlanForm);

			return new ModelAndView("tmasterPlanClientAppTransaction", "model", model);

		}
		
		@RequestMapping(value = "/tmasterPlanClientFullList", method = RequestMethod.GET)
		public ModelAndView masterPlanFullList(ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();

			List<TMasterPlan> masterList = tmasterPlanDAO.getAllMasterPlans()
					.getResultList();

			model.put("user", user);
			model.put("masterList", masterList);

			return new ModelAndView("tmasterPlanClientFullList", "model", model);

		}
		@RequestMapping(value = "/tmasterPlanRePaymentAppList", method = RequestMethod.GET)
		public ModelAndView masterPlanRePaymentList( ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();

			List<TRepayment> repayList = trepaymenyDAO.getRepayByIdAndStatus().getResultList();
			
			if(repayList!=null && repayList.size()>0)
			{
		    model.put("repayList", repayList);
		    model.put("user", user);
			 } 
			return new ModelAndView("tmasterPlanRePaymentAppList", "model", model);

		}
		
		@RequestMapping(value = "/tmasterPlanRePaymentAppApprove", method = RequestMethod.GET)
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
			  
			return new ModelAndView("tmasterPlanRePaymentAppApprove", "model", model);

		}
		
		@RequestMapping(value = "/tmasterPlanRePaymentAppApproveConfirm", method = RequestMethod.POST)
		public ModelAndView masterPlanRePaymentAppApproveConfirm( ModelMap model,@ModelAttribute TRepaymentForm trepaymentForm, BindingResult result) {

			EndUser user = getCurrentLoggedUserDetails();
	        
			trepaymentForm.setId(trepaymentForm.getId());
			trepaymentForm.setMasterKey(trepaymentForm.getMasterKey());
			trepaymentForm.setBuyingCostSanc(trepaymentForm.getBuyingCostSanc());
			trepaymentForm.setWcSancAmount(trepaymentForm.getWcSancAmount());
			trepaymentForm.setCustomer(trepaymentForm.getCustomer());
			trepaymentForm.setCustomerEmail(trepaymentForm.getCustomerEmail());
			trepaymentForm.setBuyingCostDate(trepaymentForm.getBuyingCostDate());
			trepaymentForm.setTenure(trepaymentForm.getTenure());
			trepaymentForm.setRateOfInt1(trepaymentForm.getRateOfInt1());
			trepaymentForm.setPayOption(trepaymentForm.getPayOption());
			trepaymentForm.setcStatus(trepaymentForm.getcStatus());
			trepaymentForm.setcComment(trepaymentForm.getcComment());
			trepaymentForm.setTransactionId(trepaymentForm.getTransactionId());
			
		    model.put("trepaymentForm", trepaymentForm);
		    model.put("user", user);
			 
			return new ModelAndView("tmasterPlanRePaymentAppApproveConfirm", "model", model);

		}
		
		@RequestMapping(value = "/tmasterPlanRePaymentAppApproveSave", method = RequestMethod.POST)
		public ModelAndView masterPlanRePaymentAppApproveSave( ModelMap model,@ModelAttribute TRepaymentForm trepaymentForm, BindingResult result) {

			EndUser user = getCurrentLoggedUserDetails();
			
			TRepayment repay = trepaymenyDAO.getByRepaymentId(trepaymentForm.getId());
	        
			repay.setcStatus(trepaymentForm.getcStatus());
			repay.setcComment(trepaymentForm.getcComment());
			repay.setbStatus("Pending");
			trepaymentForm.setTransactionId(trepaymentForm.getTransactionId());
			trepaymenyDAO.updateRepayment(repay);
			
		    model.put("trepaymentForm", trepaymentForm);
		    model.put("user", user);
			 
			return new ModelAndView("tmasterPlanRePaymentAppTransaction", "model", model);

		}
		
		@RequestMapping(value = "/tmasterPlanRePaymentAppTransaction", method = RequestMethod.GET)
		public ModelAndView clientAdminTransaction(ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();

			model.put("user", user);
			model.put("trepaymentForm", trepaymentForm);

			return new ModelAndView("tmasterPlanRePaymentAppTransaction", "model", model);

		}
		
		@RequestMapping(value = "/tmasterPlanRePaymentFullList", method = RequestMethod.GET)
		public ModelAndView masterPlanRePaymentFullList( ModelMap model) {

			EndUser user = getCurrentLoggedUserDetails();

			List<TRepayment> repayList = trepaymenyDAO.getList().getResultList();
			
			if(repayList!=null && repayList.size()>0)
			{
		    model.put("repayList", repayList);
		    model.put("user", user);
			 } 
			return new ModelAndView("tmasterPlanRePaymentFullList", "model", model);

		}
		
		@RequestMapping(value = "/tradeAppMngShowMail", method = RequestMethod.GET)
		public ModelAndView doSendEmail1(@ModelAttribute ModelMap model) {
			EndUser user = getCurrentLoggedUserDetails();
			model.put("user", user);
			return new ModelAndView("tradeAppMngQueryMail", "model", model);
		}

		@RequestMapping(value = "/tradeAppMngMailSender", method = RequestMethod.POST)
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
			return new ModelAndView("tradeAppMngResult", "model", model);
		}

		@RequestMapping(value = "/tradeAppMngShowCurrency", method = RequestMethod.GET)
		public ModelAndView showCurrency(ModelMap model) {
			EndUser user = getCurrentLoggedUserDetails();
			model.put("user", user);
			return new ModelAndView("tradeAppMngCurrency", "model", model);
		}

		@RequestMapping(value = "/tradeAppMngShowcurrencyConversion", method = RequestMethod.GET)
		public ModelAndView showcurrencyConversion(@ModelAttribute ModelMap model) {
			EndUser user = getCurrentLoggedUserDetails();
			model.put("user", user);
			return new ModelAndView("tradeAppMngShowcurrencyConversion", "model", model);
		}
		

		

		@RequestMapping(value = "/tAppHelp", method = RequestMethod.GET)
		public ModelAndView adminHelp(ModelMap model,
				HttpServletRequest request, HttpServletResponse response) {

			EndUser user = getCurrentLoggedUserDetails();

			model.put("user", user);

			return new ModelAndView("tAppHelp", "model", model);


		}


}
