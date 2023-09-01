package annona.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annona.form.*;
import annona.services.DateService;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import annona.dao.BuyerPODAO;
import annona.dao.DisputeReportsDAO;
import annona.dao.EndUserDAO;
import annona.dao.InvoiceDAO;
import annona.dao.InvoiceReportsDAO;
import annona.dao.PurchaseOrderDAO;
import annona.dao.TransactionDAO;
import annona.dao.WareHouseDAO;
import annona.domain.BuyerPO;
import annona.domain.CustomerHead;
import annona.domain.Dispute;
import annona.domain.DisputeReports;
import annona.domain.EndUser;
import annona.domain.Invoice;
import annona.domain.InvoiceReports;
import annona.domain.MasterPlan;
import annona.domain.PurchaseOrder;
import annona.domain.SalesOrder;
import annona.domain.SalesOrderDAO;
import annona.domain.Transaction;
import annona.services.ImageService;
import annona.utility.Constants;
import annona.utility.KeyGenerator;

@Controller
@RequestMapping("/buyer")
public class BuyerController {

	@Autowired
	EndUserDAO endUserDAOImpl;

	@Autowired
	TransactionDAO transcationDAOImpl;

	@Autowired
	BuyerPOForm buyerPOForm;

	@Autowired
	WareHouseForm wareHouseForm;

	@Autowired
	InvoiceDAO invoiceDAO;

	@Autowired
	WareHouseDAO wareHouseDAO;
	
	@Autowired
	SalesOrderDAO salesOrderDAO;
	
	@Autowired
	SalesOrderForm salesOrderForm;

	@Autowired
	EndUserForm endUserForm;

	@Autowired
	BuyerPODAO buyerPODAO;
	
	@Autowired
	PurchaseOrderDAO purchaseOrderDAO;
	
	@Autowired
	DisputeReportsDAO disputeReportsDAO;
	
	@Autowired
	InvoiceReportsDAO invoiceReportsDAO;

	@Autowired
	DisputeReportsForm disputeReportsForm;

	@Autowired
	InvoiceForm invoiceForm;

	@Autowired
	PurchaseOrderForm purchaseOrderForm;
	

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

		return "redirect:buyers";
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

		return "redirect:buyers";
	}

	@RequestMapping(value = "/buyers", method = RequestMethod.GET)
	public ModelAndView showVendorDashBoard(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("buyersPage", "model", model);

	}

	@RequestMapping(value = "/editBuyerProfile", method = RequestMethod.GET)
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

		return new ModelAndView("editBuyerProfile", "model", model);

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
		return "redirect:editBuyerProfile?id="+endUserForm.getId();
	}

	@RequestMapping(value = "/confirmEditBuyerProfile", method = RequestMethod.POST)
	public ModelAndView confirmEditAdminProfile(ModelMap model,
			@ModelAttribute EndUserForm endUserForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("confirmEditBuyerProfile", "model", model);

	}

	@RequestMapping(value = "/updateBuyerDetails", method = RequestMethod.POST)
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

		return new ModelAndView("updateBuyerSuccess", "model", model);

	}

	@RequestMapping(value = "/editBuyerPWD", method = RequestMethod.GET)
	public ModelAndView editAdminPWD(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser userProfile = endUserDAOImpl.findId(id);

		endUserForm.setId(userProfile.getId());

		endUserForm.setTransactionId(userProfile.getTransactionId());

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("editBuyerPWD", "model", model);

	}

	@RequestMapping(value = "/updateEditBuyerPWD", method = RequestMethod.POST)
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

		return new ModelAndView("updateBuyerSuccess", "model", model);

	}
	
	@RequestMapping(value = "/purchaseOrder", method = RequestMethod.GET)
	public ModelAndView businessPlan(ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		List<SalesOrder> salesOrder = salesOrderDAO.getSalesOrderByCustomer(user.getUserName()).getResultList();

		if (salesOrder != null) {
			model.put("salesOrder", salesOrder);
			return new ModelAndView("getListForPurchaseOrder", "model", model);
		} else {
			return new ModelAndView("noDataFoundBuyer", "model", model);
		}

	}
	
	@RequestMapping(value = "/creatPurchaseOrder", method = RequestMethod.GET)
	public ModelAndView createBusinessPlan(@RequestParam("id") Long id, ModelMap model) {

		EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		SalesOrder salesOrder = salesOrderDAO. getBySalesOrderId(id);

		salesOrderForm.setId(salesOrder.getId());
		salesOrderForm.setBuyerName(salesOrder.getBuyerName());
		salesOrderForm.setCustomerHeadName(salesOrder.getCustomerHeadName());
		salesOrderForm.setBuyerEmail(salesOrder.getBuyerEmail());
		salesOrderForm.setCustomerHeadEmail(salesOrder.getCustomerHeadEmail());
		salesOrderForm.setStartDate(salesOrder.getStartDate());
		salesOrderForm.setGoods(salesOrder.getGoods());
		salesOrderForm.setGoodsCategory(salesOrder.getGoodsCategory());
		salesOrderForm.setGoodsDetails(salesOrder.getGoodsDetails());
		salesOrderForm.setQuantity(salesOrder.getQuantity());
		salesOrderForm.setWeight(salesOrder.getWeight());
		salesOrderForm.setMasterKey(salesOrder.getMasterKey());
		salesOrderForm.setTenure(salesOrder.getTenure());
		salesOrderForm.setAmount(salesOrder.getAmount());
		
		model.put("user", user);
		model.put("salesOrderForm", salesOrderForm);

		return new ModelAndView("buyerPo", "model", model);
	}



	/*
	 * @RequestMapping(value = "/buyerPo", method = RequestMethod.GET) public
	 * ModelAndView buyerPo(ModelMap model) {
	 * 
	 * EndUser user = endUserDAOImpl
	 * .findByUsername(getCurrentLoggedUserName()).getSingleResult();
	 * 
	 * buyerPOForm.setName(user.getUserName());
	 * buyerPOForm.setCustomerHeadName(user.getCustomerHeadName());
	 * 
	 * model.put("buyerPOForm", buyerPOForm); model.put("user", user);
	 * 
	 * return new ModelAndView("buyerPo", "model", model); }
	 */

	@RequestMapping(value = "/buyerPoConfirm", method = RequestMethod.POST)
	public ModelAndView buyerPoConfirm(ModelMap model,
			@ModelAttribute SalesOrderForm salesOrderForm,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		buyerPOForm.setSalesOrderId(salesOrderForm.getId());
		buyerPOForm.setName(salesOrderForm.getBuyerName());
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

	@RequestMapping(value = "/buyerPoPost", method = RequestMethod.POST)
	public ModelAndView buyerPoPost(ModelMap model,
			@ModelAttribute BuyerPOForm buyerPOForm,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		
		buyerPOForm.setPoKey(KeyGenerator.generateTransactionKey());
		buyerPOForm.setTransactionId(KeyGenerator.generateTransactionKey());
		
		BuyerPO buyerPO = new BuyerPO();

		buyerPO.setName(buyerPOForm.getName());
		buyerPO.setCustomerHeadName(buyerPOForm.getCustomerHeadName());
		buyerPO.setEmail(buyerPOForm.getEmail());
		buyerPO.setCustomerHeadEmail(buyerPOForm.getCustomerHeadEmail());
		buyerPO.setPurchaseDate(buyerPOForm.getPurchaseDate());
		buyerPO.setGoods(buyerPOForm.getGoods());
		buyerPO.setGoodsCategory(buyerPOForm.getGoodsCategory());
		buyerPO.setGoodsDetails(buyerPOForm.getGoodsDetails());
		buyerPO.setQuantity(buyerPOForm.getQuantity());
		buyerPO.setInvoiceRemaining(buyerPOForm.getQuantity());
		buyerPO.setWeight(buyerPOForm.getWeight());
		buyerPO.setPoKey(buyerPOForm.getPoKey());
		buyerPO.setTenure(buyerPOForm.getTenure());
		buyerPO.setAmount(buyerPOForm.getAmount());
		buyerPO.setCustomerPrefix(buyerPOForm.getCustomerPrefix());
		buyerPO.setTransactionId(buyerPOForm.getTransactionId());
		buyerPO.setSalesOrderId(buyerPOForm.getSalesOrderId());

		buyerPODAO.createUser(buyerPO);

		Transaction trans = new Transaction();

		trans.setTransactionId(buyerPOForm.getTransactionId());
		trans.setTransactionStatus("Buyer PO");
		trans.setTransactionType("Saved Successfully");

		transcationDAOImpl.insertTransaction(trans);

		model.put("user", user);
		model.put("buyerPOForm", buyerPOForm);

		return new ModelAndView("buyerPoTransaction", "model", model);

	}

	@RequestMapping(value = "/buyerPoTransaction", method = RequestMethod.GET)
	public ModelAndView buyerPoTransaction(ModelMap model) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();

		model.put("buyerPOForm", buyerPOForm);
		model.put("user", user);

		return new ModelAndView("buyerPoTransaction", "model", model);
	}

	@RequestMapping(value = "/buyerPoList", method = RequestMethod.GET)
	public ModelAndView buyerPoList(ModelMap model) {

		EndUser user = endUserDAOImpl
				.findByUsername(getCurrentLoggedUserName()).getSingleResult();
		model.put("user", user);
		List<BuyerPO> buyerList = buyerPODAO.getBuyerPOByName(
				user.getUserName()).getResultList();
		if (buyerList != null && buyerList.size() > 0) {
			model.put("buyerList", buyerList);
			
			return new ModelAndView("buyerPoList", "model", model);
		}
		
		else
		{
			return new ModelAndView("noDataFoundBuyer", "model", model);
		}
	}

	@RequestMapping(value = "/custWareHouseBuyerList", method = RequestMethod.GET)
	public ModelAndView custWareHouseInvoiceList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getInvoiceListBuyerForGoods(
				user.getUserName()).getResultList();

		if (invoiceList != null && invoiceList.size() > 0) {
			
			model.put("invoiceList", invoiceList);
			return new ModelAndView("custWareHouseBuyerList", "model", model);
		}
		
		else
		{
			return new ModelAndView("noDataFoundBuyer", "model", model);
		}

	}

	@RequestMapping(value = "/custWareHouseBuyerREceived", method = RequestMethod.GET)
	public ModelAndView custWareHouseInvoiceSend(@RequestParam Long id,
			ModelMap model, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Invoice invoice = invoiceDAO.getByInvoiceId(id);

		invoice.setGoodsStatus("Received");

		Date receive = DateService.loginDate;
		invoice.setReceiveDate(receive);

		invoiceDAO.updateInvoice(invoice);

		model.put("user", user);

		attributes.addFlashAttribute("success", "Updated Successfully");
		return new ModelAndView("redirect:custWareHouseBuyerList");

	}

	@RequestMapping(value = "/buyerInvoiceList", method = RequestMethod.GET)
	public ModelAndView buyerInvoiceList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getInvoiceListBuyer(
				user.getUserName()).getResultList();

		if (invoiceList != null && invoiceList.size() > 0) {
			
			model.put("invoiceList", invoiceList);
			return new ModelAndView("buyerInvoiceList", "model", model);
		}
		else
		{
			return new ModelAndView("noDataFoundBuyer", "model", model);
		}

	}
	
	@RequestMapping(value = "/buyerHelp", method = RequestMethod.GET)
	public ModelAndView adminHelp(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("buyerHelp", "model", model);

	}
	
	@RequestMapping(value = "/invoiceDisputeReports", method = RequestMethod.GET)
	public ModelAndView invoiceDisputeReports(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getInvoiceListForDisReports(user.getUserName()).getResultList();

		if (invoiceList != null && invoiceList.size() > 0) {
			
			model.put("user", user);
			model.put("invoiceList", invoiceList);
			return new ModelAndView("invoiceDisputeReports", "model", model);
		}
		else
		{
			return new ModelAndView("noDataFoundBuyer", "model", model);
		}

	}
	
	@RequestMapping(value = "/addOrModifyBuyerReport", method = RequestMethod.GET)
	public ModelAndView addOrModifyReport(@RequestParam Long id, ModelMap model,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Invoice invoice = invoiceDAO.getByInvoiceId(id);

		disputeReportsForm.setCustomerName(invoice.getCustomerName());
		disputeReportsForm.setInvoicekey(invoice.getPoKey());
		disputeReportsForm.setBuyerName(invoice.getBuyerName());
		disputeReportsForm.setGoods(invoice.getGoods());
		disputeReportsForm.setInsDetails(invoice.getInsuranceDetails());
		disputeReportsForm.setCost(invoice.getAmount());
		disputeReportsForm.setGoodsDetails(invoice.getGoodsDetails());
		disputeReportsForm.setTransactionId(invoice.getTransactionId());
		disputeReportsForm.setInStartDate(invoice.getStartDate());
		disputeReportsForm.setInsEndDate(invoice.getEndDate());

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		return new ModelAndView("addOrModifyBuyerReport", "model", model);
	}

	@RequestMapping(value = "/addOrModifyBuyerReportConfirm", method = RequestMethod.POST)
	public ModelAndView addOrModifyReportConfirm(ModelMap model,
			@ModelAttribute DisputeReportsForm disputeReportsForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();

		disputeReportsForm.setId(disputeReportsForm.getId());
		disputeReportsForm.setCustomerName(disputeReportsForm.getCustomerName());
		disputeReportsForm.setInvoicekey(disputeReportsForm.getInvoicekey());
		disputeReportsForm.setBuyerName(disputeReportsForm.getBuyerName());
		disputeReportsForm.setGoods(disputeReportsForm.getGoods());
		disputeReportsForm.setInsDetails(disputeReportsForm.getInsDetails());
		disputeReportsForm.setCost(disputeReportsForm.getCost());
		disputeReportsForm.setTransactionId(disputeReportsForm.getTransactionId());
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
		disputeReportsForm.setInStartDate(disputeReportsForm.getInStartDate());
		disputeReportsForm.setInsEndDate(disputeReportsForm.getInsEndDate());

		model.put("disputeReportsForm", disputeReportsForm);
		model.put("user", user);

		return new ModelAndView("addOrModifyBuyerReportConfirm", "model", model);

	}

	@RequestMapping(value = "/addOrModifyBuyerReportPost", method = RequestMethod.POST)
	public ModelAndView addOrModifyReportPost(ModelMap model,
			@ModelAttribute DisputeReportsForm disputeReportsForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();
		List<InvoiceReports> dis = invoiceReportsDAO.getInvoiceReportsList(disputeReportsForm.getInvoicekey()).getResultList();
		if(dis!=null && dis.size()>0)
			
		{
			InvoiceReports disp = invoiceReportsDAO.getInvoiceReportsList(disputeReportsForm.getDisputekey()).getSingleResult();
		
			disp.setCustomerName(disputeReportsForm.getCustomerName());
			disp.setInvoicekey(disputeReportsForm.getInvoicekey());
			disp.setBuyerName(disputeReportsForm.getBuyerName());
			disp.setGoods(disputeReportsForm.getGoods());
			disp.setInsDetails(disputeReportsForm.getInsDetails());
			disp.setCost(disputeReportsForm.getCost());
			disp.setTransactionId(disputeReportsForm.getTransactionId());
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
			disp.setCustAccept("Pending");

			invoiceReportsDAO.updateInvoiceReports(disp);
		
		}else
		{
			InvoiceReports dispute = new InvoiceReports();

		dispute.setId(disputeReportsForm.getId());
		dispute.setStartDate(disputeReportsForm.getInStartDate());
		dispute.setEndDate(disputeReportsForm.getInsEndDate());
		dispute.setCustomerName(disputeReportsForm.getCustomerName());
		dispute.setInvoicekey(disputeReportsForm.getInvoicekey());
		dispute.setBuyerName(disputeReportsForm.getBuyerName());
		dispute.setGoods(disputeReportsForm.getGoods());
		dispute.setInsDetails(disputeReportsForm.getInsDetails());
		dispute.setCost(disputeReportsForm.getCost());
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
		dispute.setCustAccept("Pending");

		invoiceReportsDAO.createInvoiceReports(dispute);
		}

		Transaction trans = new Transaction();
		trans.setTransactionId(disputeReportsForm.getTransactionId());
		trans.setTransactionStatus("Dispute");
		trans.setTransactionType("Submitted Successfully");

		model.put("disputeReportsForm", disputeReportsForm);
		model.put("user", user);

		return new ModelAndView("addOrModifyBuyerReportTrans", "model", model);

	}

	@RequestMapping(value = "/addOrModifyBuyerReportTrans", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		return new ModelAndView("addOrModifyBuyerReportTrans", "model", model);

	}
	
	@RequestMapping(value = "/addOrModifyBuyerReportList", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<InvoiceReports> disputeList = invoiceReportsDAO.getInvoiceReportsOnCustList(
				user.getUserName()).getResultList();

		if (disputeList != null && disputeList.size() > 0) {
			model.put("disputeList", disputeList);
			return new ModelAndView("addOrModifyBuyerReportList", "model", model);
		}
		else
		{
			return new ModelAndView("noDataFoundBuyer", "model", model);
		}	

	}
	@RequestMapping(value = "/addOrModifyBuyerReportView", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportView(@RequestParam Long id, ModelMap model,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		InvoiceReports dispute = invoiceReportsDAO.getByInvoiceReportsId(id);

		disputeReportsForm.setId(dispute.getId());
		disputeReportsForm.setCustomerName(dispute.getCustomerName());
		disputeReportsForm.setInvoicekey(dispute.getInvoicekey());
		disputeReportsForm.setBuyerName(dispute.getBuyerName());
		disputeReportsForm.setGoods(dispute.getGoods());
		disputeReportsForm.setInsDetails(dispute.getInsDetails());
		disputeReportsForm.setCost(dispute.getCost());
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

		return new ModelAndView("addOrModifyBuyerReportView", "model", model);
	}
	@RequestMapping(value = "/addOrModifyBuyerReportCompare", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportCompare(@RequestParam Long id, ModelMap model,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		InvoiceReports dispute = invoiceReportsDAO.getByInvoiceReportsId(id);

		disputeReportsForm.setId(dispute.getId());
		disputeReportsForm.setCustomerName(dispute.getCustomerName());
		disputeReportsForm.setInvoicekey(dispute.getInvoicekey());
		disputeReportsForm.setBuyerName(dispute.getBuyerName());
		disputeReportsForm.setGoods(dispute.getGoods());
		disputeReportsForm.setInsDetails(dispute.getInsDetails());
		disputeReportsForm.setCost(dispute.getCost());
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

		return new ModelAndView("addOrModifyBuyerReportCompare", "model", model);
	}
	
	@RequestMapping(value = "/addOrModifyBuyerReportAddArbitration", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportAddArbitration(@RequestParam Long id, ModelMap model,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		InvoiceReports dispute = invoiceReportsDAO.getByInvoiceReportsId(id);

		disputeReportsForm.setTransactionId(dispute.getTransactionId());
		disputeReportsForm.setInvoicekey(dispute.getInvoicekey());

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		return new ModelAndView("addOrModifyBuyerReportAddArbitration", "model", model);
	}
	
	@RequestMapping(value = "/addOrModifyBuyerReportAddArbitrationConfirm", method = RequestMethod.POST)
	public ModelAndView addOrModifyReportAddArbitrationConfirm(ModelMap model,
			@ModelAttribute DisputeReportsForm disputeReportsForm, BindingResult result) {

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
		disputeReportsForm.setInvoicekey(disputeReportsForm.getInvoicekey());

		model.put("disputeReportsForm", disputeReportsForm);
		model.put("user", user);

		return new ModelAndView("addOrModifyBuyerReportAddArbitrationConfirm", "model", model);

	}

	@RequestMapping(value = "/addOrModifyBuyerReportAddArbitrationPost", method = RequestMethod.POST)
	public ModelAndView addOrModifyReportAddArbitrationPost(ModelMap model,
			@ModelAttribute DisputeReportsForm disputeReportsForm, BindingResult result) {

		EndUser user = getCurrentLoggedUserDetails();
		List<InvoiceReports> dis = invoiceReportsDAO.getInvoiceReportsList(disputeReportsForm.getInvoicekey()).getResultList();
		if(dis!=null && dis.size()>0)
			
		{
			InvoiceReports disp = invoiceReportsDAO.getInvoiceReportsList(disputeReportsForm.getInvoicekey()).getSingleResult();
		
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

			invoiceReportsDAO.updateInvoiceReports(disp);
		
		}

		Transaction trans = new Transaction();
		trans.setTransactionId(disputeReportsForm.getTransactionId());
		trans.setTransactionStatus("Dispute");
		trans.setTransactionType("Submitted Successfully");

		model.put("disputeReportsForm", disputeReportsForm);
		model.put("user", user);

		return new ModelAndView("addOrModifyBuyerReportAddArbitrationTrans", "model", model);

	}

	@RequestMapping(value = "/addOrModifyBuyerReportAddArbitrationTrans", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportAddArbitrationTrans(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		return new ModelAndView("addOrModifyBuyerReportAddArbitrationTrans", "model", model);

	}
	
	@RequestMapping(value = "/addOrModifyBuyerReportAccept", method = RequestMethod.GET)
	public ModelAndView addOrModifyReportAccept(@RequestParam Long id, ModelMap model,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		InvoiceReports dispute = invoiceReportsDAO.getByInvoiceReportsId(id);

		dispute.setAccept("Accepted");
		
		
		invoiceReportsDAO.updateInvoiceReports(dispute);

		model.put("user", user);
		model.put("disputeReportsForm", disputeReportsForm);

		attributes.addFlashAttribute("success", "Accepted Successfully");
		
		return new ModelAndView("redirect:addOrModifyBuyerReportList");
	}


	@RequestMapping(value = "/invoicePaymentBuyerList", method = RequestMethod.GET)
	public ModelAndView poPaymentHeadList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getInvoiceListForBuyer(
				user.getUserName()).getResultList();

		if (invoiceList != null && invoiceList.size() > 0) {
			model.put("invoiceList", invoiceList);
			return new ModelAndView("invoicePaymentBuyerList", "model", model);
		}

		else {
			return new ModelAndView("noDataFoundBuyer", "model", model);
		}
	}

	@RequestMapping(value = "/invoicePaymentBuyer", method = RequestMethod.GET)
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

		return new ModelAndView("invoicePaymentBuyer", "model", model);
	}

	@RequestMapping(value = "/invoicePaymentBuyerConfirm", method = RequestMethod.POST)
	public ModelAndView invoicePaymentBuyerConfirm(ModelMap model,
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

		return new ModelAndView("invoicePaymentBuyerConfirm", "model", model);

	}

	@RequestMapping(value = "/invoicePaymentBuyerSave", method = RequestMethod.POST)
	public ModelAndView invoicePaymentBuyerSave(ModelMap model,
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

		return new ModelAndView("invoicePaymentBuyerTrans", "model", model);

	}

	@RequestMapping(value = "/requestMoneyInvoiceBuyerFullList", method = RequestMethod.GET)
	public ModelAndView requestMoneyInvoiceHeadFullList(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<Invoice> invoiceList = invoiceDAO.getInvoiceListByBuyerName(
				user.getUserName()).getResultList();

		if (invoiceList != null && invoiceList.size() > 0) {
			model.put("invoiceList", invoiceList);
			return new ModelAndView("requestMoneyInvoiceBuyerFullList",
					"model", model);
		} else {
			return new ModelAndView("noDataFoundBuyer", "model", model);
		}

	}
	

}
