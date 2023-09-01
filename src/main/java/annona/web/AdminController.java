package annona.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annona.dao.*;
import annona.domain.*;
import annona.form.*;
import annona.services.DateService;
import annona.utility.KeyGenerator;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

import com.google.gson.Gson;

import annona.services.ColumnGraphService;
import annona.services.ImageService;
import annona.utility.Constants;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	EndUserDAO endUserDAOImpl;

	@Autowired
	TransactionDAO transcationDAOImpl;

	@Autowired
	EndUserForm endUserForm;

	@Autowired
	RegulationsForm regulationsForm;

	@Autowired
	BankDetailsForm bankDetailsForm;

	@Autowired
	BankDetailsDAO bankDetailsDAO;

	@Autowired
	RegulationsDAO regulationsDAO;

	@Autowired
	SwapAccountDAO swapAccountDAO;

	@Autowired
	PurchaseOrderDAO purchaseOrderDAO;

	@Autowired
	LimitBurstDAO limitBurstDAO;

	@Autowired
	MasterPlanDAO masterPlanDAO;

	@Autowired
	PoUploadDAO poUploadDAO;
	
	@Autowired
	RepaymentDAO repaymentDAO;
	
	@Autowired
	InvoiceDAO invoiceDAO;

	@Autowired
	private JavaMailSender mailSender;

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

	private EndUser getCurrentLoggedUserDetails() {

		EndUser endUser = endUserDAOImpl.findByUsername(
				getCurrentLoggedUserName()).getSingleResult();

		return endUser;

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

	@RequestMapping(value = "/adminPage", method = RequestMethod.GET)
	public ModelAndView showadminDashBoard(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();
		
       List<Repayment> repay = repaymentDAO.getList().getResultList();
		
		
		List<ColumnGraphForm> columnGraphList = new ArrayList<ColumnGraphForm>();			
		List<String> categoryList = new ArrayList<>();
		//Populate Graphs for BuyerSameBankEvent
		if( repay != null && repay.size() > 0 ) {
			
			float[] sanctionedData = new float[repay.size()];
			float[] utilizedData = new float[repay.size()];
			float[] balanceData = new float[repay.size()];
			for(int i = 0; i < repay.size(); i++) {
				categoryList.add(repay.get(i).getCustomer());
				if(repay.get(i).getBuyingCostSanc() == null) {
					repay.get(i).setBuyingCostSanc(0f);
				}
				if(repay.get(i).getAmountPaid() == null) {
					repay.get(i).setAmountPaid(0f);
				}
				if(repay.get(i).getFunalAmt() == null) {
					repay.get(i).setFunalAmt(0f);
				}
				sanctionedData[i] = repay.get(i).getBuyingCostSanc();
				utilizedData[i] = repay.get(i).getAmountPaid();
				balanceData[i] = repay.get(i).getFunalAmt();
			}
			columnGraphList.add(ColumnGraphService.generateObject("Sanctioned Amount", sanctionedData));
			columnGraphList.add(ColumnGraphService.generateObject("Amount Paid", utilizedData));
			columnGraphList.add(ColumnGraphService.generateObject("Total Amount", balanceData));				
		}
		
		
		String [] category = categoryList.toArray(new String[categoryList.size()]);
		Gson gson = new Gson();
		String values = gson.toJson(columnGraphList);
		String categories = gson.toJson(category);
				
		model.put("values", values);
		model.put("categories", categories);

		model.put("user", user);

		return new ModelAndView("admin", "model", model);
	}

	@RequestMapping(value = "/editAdminProfile", method = RequestMethod.GET)
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

		return new ModelAndView("editAdminProfile", "model", model);

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
		return "redirect:editAdminProfile?id="+endUserForm.getId();
	}

	@RequestMapping(value = "/confirmEditAdminProfile", method = RequestMethod.POST)
	public ModelAndView confirmEditAdminProfile(ModelMap model,
			@ModelAttribute EndUserForm endUserForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("confirmEditAdminProfile", "model", model);

	}

	@RequestMapping(value = "/updateAdminDetails", method = RequestMethod.POST)
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

		return new ModelAndView("updateAdminSuccess", "model", model);

	}

	@RequestMapping(value = "/editAdminPWD", method = RequestMethod.GET)
	public ModelAndView editAdminPWD(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser userProfile = endUserDAOImpl.findId(id);

		endUserForm.setId(userProfile.getId());

		endUserForm.setTransactionId(userProfile.getTransactionId());

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("editAdminPWD", "model", model);

	}

	@RequestMapping(value = "/updateEditAdminPWD", method = RequestMethod.POST)
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

		return new ModelAndView("updateAdminSuccess", "model", model);

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

		return "redirect:adminPage";
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

		return "redirect:adminPage";
	}

	@RequestMapping(value = "/regulationPage", method = RequestMethod.GET)
	public ModelAndView regulations(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("regulationsForm", regulationsForm);

		Collection<Regulation> regulations = regulationsDAO.getList();

		model.put("regulations", regulations);

		return new ModelAndView("regulationPage", "model", model);
	}

	@RequestMapping(value = "/selectRegulation", method = RequestMethod.GET)
	public ModelAndView selectRegulation(ModelMap model,
			@RequestParam("id") Long id, RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		Regulation regulation = regulationsDAO.findId(id);

		regulationsForm.setId(regulation.getId());
		regulationsForm.setBannedGoods(regulation.getBannedGoods());
		regulationsForm.setCountryName(regulation.getCountryName());
		regulationsForm.setExportedGoods(regulation.getExportedGoods());
		regulationsForm.setRegulation(regulation.getRegulation());
		regulationsForm.setTransactionId(regulation.getTransactionId());

		model.put("user", user);

		model.put("regulationsForm", regulationsForm);

		return new ModelAndView("selectRegulation", "model", model);

	}

	@RequestMapping(value = "/bannedRegulationUpdate", method = RequestMethod.POST)
	public ModelAndView bannedRegulationUpdate(ModelMap model,
			@ModelAttribute RegulationsForm regulationsForm,
			BindingResult result, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		Regulation regulation = regulationsDAO.findId(regulationsForm.getId());

		regulation.setId(regulationsForm.getId());
		regulation.setExportedGoods(regulationsForm.getExportedGoods());
		regulation.setCountryName(regulationsForm.getCountryName());
		regulation.setRegulation(regulationsForm.getRegulation());
		regulation.setTransactionId(regulationsForm.getTransactionId());
		regulation.setBannedGoods("");

		regulationsDAO.update(regulation);

		model.put("regulationsForm", regulationsForm);

		return new ModelAndView("notBannedRegulationSuccess", "model", model);

	}

	@RequestMapping(value = "/notBannedRegulationUpdate", method = RequestMethod.POST)
	public ModelAndView notBannedRegulationUpdate(ModelMap model,
			@ModelAttribute RegulationsForm regulationsForm,
			BindingResult result, RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		Regulation regulation = regulationsDAO.findId(regulationsForm.getId());

		regulation.setId(regulationsForm.getId());
		regulation.setBannedGoods(regulationsForm.getBannedGoods());
		regulation.setCountryName(regulationsForm.getCountryName());
		regulation.setRegulation(regulationsForm.getRegulation());
		regulation.setTransactionId(regulationsForm.getTransactionId());
		regulation.setExportedGoods("");

		regulationsDAO.update(regulation);

		model.put("regulationsForm", regulationsForm);

		return new ModelAndView("notBannedRegulationSuccess", "model", model);

	}

	@RequestMapping(value = "/updateReg", method = RequestMethod.POST)
	public ModelAndView regulationsUpdate2(ModelMap model,
			@ModelAttribute RegulationsForm regulationsForm,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		if (regulationsForm.getRegulation().equals("Banned")) {

			model.put("regulationsForm", regulationsForm);

			mav = new ModelAndView("bannedRegulationUpdate", "model", model);
		} else {

			model.put("regulationsForm", regulationsForm);

			mav = new ModelAndView("notBannedRegulationUpdate", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/regulationUpdatePage", method = RequestMethod.POST)
	public ModelAndView regulationsUpdate1(ModelMap model,
			@ModelAttribute RegulationsForm regulationsForm,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		ModelAndView mav = new ModelAndView();

		List<Regulation> regulation = regulationsDAO.findByCountry(
				regulationsForm.getCountryName()).getResultList();

		if (regulation.size() == 0) {
			if (regulationsForm.getRegulation().equals("Banned")) {

				model.put("regulationsForm", regulationsForm);

				mav = new ModelAndView("bannedRegulation", "model", model);
			} else {

				model.put("regulationsForm", regulationsForm);

				mav = new ModelAndView("notBannedRegulation", "model", model);
			}
		} else {
			attributes.addFlashAttribute("success",
					"Regulations already done for this country");

			mav = new ModelAndView("redirect:regulationPage");

		}

		return mav;

	}

	@RequestMapping(value = "/bannedRegulationDetails", method = RequestMethod.POST)
	public ModelAndView regulationsUpdate(ModelMap model,
			@ModelAttribute RegulationsForm regulationsForm,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();


		regulationsForm.setTransactionId(KeyGenerator.generateTransactionKey());

		model.put("user", user);

		Regulation regulation = new Regulation();

		Transaction transaction = new Transaction();

		regulation.setCountryName(regulationsForm.getCountryName());

		regulation.setTransactionId(regulationsForm.getTransactionId());

		regulation.setRegulation(regulationsForm.getRegulation());

		regulation.setExportedGoods(regulationsForm.getExportedGoods());

		transaction.setTransactionId(regulationsForm.getTransactionId());

		transaction.setTransactionType("Regulation");

		transaction
				.setTransactionStatus("Regulation status saved successfully");

		regulationsDAO.createUser(regulation);

		transcationDAOImpl.insertTransaction(transaction);

		model.put("regulationsForm", regulationsForm);

		return new ModelAndView("bannedRegulationSuccess", "model", model);

	}

	@RequestMapping(value = "/notBannedRegulationDetails", method = RequestMethod.POST)
	public ModelAndView notBannedUpdate(ModelMap model,
			@ModelAttribute RegulationsForm regulationsForm,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();

		regulationsForm.setTransactionId(KeyGenerator.generateTransactionKey());

		model.put("user", user);

		Regulation regulation = new Regulation();

		Transaction transaction = new Transaction();

		regulation.setCountryName(regulationsForm.getCountryName());

		regulation.setTransactionId(regulationsForm.getTransactionId());

		regulation.setRegulation(regulationsForm.getRegulation());

		regulation.setBannedGoods(regulationsForm.getBannedGoods());

		transaction.setTransactionId(regulationsForm.getTransactionId());

		transaction.setTransactionType("Regulation");

		transaction
				.setTransactionStatus("Regulation status saved successfully");

		regulationsDAO.createUser(regulation);

		transcationDAOImpl.insertTransaction(transaction);

		attributes.addFlashAttribute("success", "Saved Successfully");

		model.put("regulationsForm", regulationsForm);

		return new ModelAndView("bannedRegulationSuccess", "model", model);

	}

	@RequestMapping(value = "/createRole", method = RequestMethod.GET)
	public ModelAndView createRole(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("endUserForm", endUserForm);
		Collection<EndUser> endUsers = endUserDAOImpl.getList();

		model.put("endUsers", endUsers);
		return new ModelAndView("createRole", "model", model);
	}

	@RequestMapping(value = "/updateRole", method = RequestMethod.POST)
	public ModelAndView updateRole(@ModelAttribute EndUserForm endUserForm,
			RedirectAttributes attributes, ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);



		EndUser savedUser = endUserDAOImpl.findId(endUserForm.getId());
		if(!(savedUser.getUserName().equals(endUserForm.getUserName()))) {
			List<EndUser> endUser = endUserDAOImpl.findByUsername(
					endUserForm.getUserName()).getResultList();

			if (endUser.size() == 0) {

				if (endUserForm != null
						&& savedUser.getCurrentRole().equals("ROLE_APPROVALMNG")) {

					model.put("endUserForm", endUserForm);

					return new ModelAndView("rolepageForward", "model", model);
				} else {

					model.put("endUserForm", endUserForm);

					return new ModelAndView("bankEmployeeForward", "model", model);
				}

			} else {
				attributes.addFlashAttribute("error", "Role already Exists");

				return new ModelAndView("redirect:createRole");

			}
		}else {
			if (endUserForm != null
					&& savedUser.getCurrentRole().equals("ROLE_APPROVALMNG")) {

				model.put("endUserForm", endUserForm);

				return new ModelAndView("rolepageForward", "model", model);
			} else {

				model.put("endUserForm", endUserForm);

				return new ModelAndView("bankEmployeeForward", "model", model);
			}
		}

	}

	@RequestMapping(value = "/selectRole", method = RequestMethod.GET)
	public ModelAndView selectRole(ModelMap model, @RequestParam("id") Long id,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser endUser = endUserDAOImpl.findId(id);

		endUserForm.setId(endUser.getId());

		endUserForm.setRole(endUser.getRole());
		endUserForm.setContactNo(endUser.getContactNo());
		endUserForm.setEmail(endUser.getEmail());
		endUserForm.setUserName(endUser.getUserName());
		endUserForm.setTransactionId(endUser.getTransactionId());

		model.put("user", user);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("selectRole", "model", model);

	}

	@RequestMapping(value = "/updateRole1", method = RequestMethod.POST)
	public ModelAndView roleupdate(ModelMap model,
			@ModelAttribute EndUserForm endUserForm,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		ModelAndView mav = new ModelAndView();

		if (endUserForm != null && endUserForm.getRole().equals(3)) {

			model.put("endUserForm", endUserForm);

			mav = new ModelAndView("rolepageForwardUpdate", "model", model);
		} else {

			model.put("endUserForm", endUserForm);

			mav = new ModelAndView("bankEmployeeForwardUpdate", "model", model);
		}

		return mav;

	}

	@RequestMapping(value = "/aprrovalManagerUpdate", method = RequestMethod.POST)
	public ModelAndView approvalRoleUpdate(ModelMap model,
			@ModelAttribute EndUserForm endUserForm, BindingResult result,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		EndUser endUser = endUserDAOImpl.findId(endUserForm.getId());

		endUser.setContactNo(endUserForm.getContactNo());
		endUser.setEmail(endUserForm.getEmail());
		endUser.setUserName(endUserForm.getUserName());
		endUser.setNotificationStatus("Pending");
		endUser.setStatus("Pending");
		endUser.setTransactionId(endUserForm.getTransactionId());

		endUserDAOImpl.update(endUser);

		model.put("endUserForm", endUserForm);

		return new ModelAndView("approvalRoleSuccess", "model", model);

	}

	@RequestMapping(value = "/roleApprovalList", method = RequestMethod.POST)
	public ModelAndView roleApprovalListUpdate(ModelMap model,
			@ModelAttribute EndUserForm endUserForm,
			RedirectAttributes attributes) throws ParseException {
		EndUser user = getCurrentLoggedUserDetails();
		
		 Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(endUserForm.getAccExpiryDateStr() );  

			

		endUserForm.setTransactionId(KeyGenerator.generateTransactionKey());
		model.put("user", user);
		Transaction transaction = new Transaction();
		EndUser endUser ;
		if(endUserForm.getId()!=null){
			endUser=endUserDAOImpl.findId(endUserForm.getId());
			endUser.setEmail(endUserForm.getEmail());
			endUser.setContactNo(endUserForm.getContactNo());
			endUser.setUserName(endUserForm.getUserName());
			endUserDAOImpl.update(endUser);
		}else{
			endUser= new EndUser();
			endUser.setEmail(endUserForm.getEmail());
			endUser.setContactNo(endUserForm.getContactNo());
			endUser.setRole(endUserForm.getRole());
			endUser.setUserName(endUserForm.getUserName());
			endUser.setDisplayName(endUserForm.getDisplayName());
			endUser.setCurrentRole(endUserForm.getCurrentRole());
			endUser.setPrefferedLanguage("en");
			endUser.setTheme("themeBlue");
			endUser.setNotificationStatus("Pending");
			endUser.setStatus("Approved");
			endUser.setPassword(endUserForm.getPassword());
			endUser.setApprovallimit(endUserForm.getApprovallimit());
			endUser.setTransactionId(endUserForm.getTransactionId());
			transaction.setTransactionId(endUserForm.getTransactionId());
			transaction.setTransactionType("Role");
			transaction.setTransactionStatus("Role status saved successfully");
			endUser.setPasswordFlag(0);
			endUser.setAccExpiryDate(endUserForm.getAccExpiryDate());
			endUserDAOImpl.createUser(endUser);
		}

		transcationDAOImpl.insertTransaction(transaction);

		attributes.addFlashAttribute("success", "Saved Successfully");

		model.put("endUserForm", endUserForm);

		return new ModelAndView("roleApprovalListSuccess", "model", model);

	}

	@RequestMapping(value = "/bankDetails", method = RequestMethod.GET)
	public ModelAndView createBankDetails(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		Collection<BankDetails> bankDetails = bankDetailsDAO.getList();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("bankDetails", bankDetails);

		model.put("user", user);

		model.put("bankDetailsForm", bankDetailsForm);

		return new ModelAndView("bankDetails", "model", model);
	}

	@RequestMapping(value = "/confirmBankDetails", method = RequestMethod.POST)
	public ModelAndView insertBankDetails(ModelMap model,
			@ModelAttribute BankDetailsForm bankDetailsForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("bankDetailsForm", bankDetailsForm);

		return new ModelAndView("confirmBankDetails", "model", model);

	}

	@RequestMapping(value = "/insertBankDetails", method = RequestMethod.POST)
	public ModelAndView notBannedUpdate(ModelMap model,
			@ModelAttribute BankDetailsForm bankDetailsForm) {
		EndUser user = getCurrentLoggedUserDetails();

		bankDetailsForm.setTransactionId(KeyGenerator.generateTransactionKey());

		model.put("user", user);

		BankDetails bankDetails = new BankDetails();

		Transaction transaction = new Transaction();

		bankDetails.setBankName(bankDetailsForm.getBankName());

		bankDetails.setLocation(bankDetailsForm.getLocation());

		bankDetails.setBranch(bankDetailsForm.getBranch());

		bankDetails.setContactNo(bankDetailsForm.getContactNo());

		bankDetails.setEmail(bankDetailsForm.getEmail());
		
		bankDetails.setBranchCode(bankDetailsForm.getBranchCode());

		bankDetails.setTransactionId(bankDetailsForm.getTransactionId());

		transaction.setTransactionId(bankDetailsForm.getTransactionId());

		transaction.setTransactionType("Bank Details");

		transaction.setTransactionStatus("Bank Details saved successfully");

		bankDetailsDAO.insertBankDetails(bankDetails);

		transcationDAOImpl.insertTransaction(transaction);

		model.put("bankDetailsForm", bankDetailsForm);

		return new ModelAndView("bankDetailsSuccess", "model", model);

	}

	@RequestMapping(value = "/selectBankDetails", method = RequestMethod.GET)
	public ModelAndView selectBankDetails(ModelMap model,
			@RequestParam("id") Long id) {

		EndUser user = getCurrentLoggedUserDetails();

		BankDetails bankDetails = bankDetailsDAO.findId(id);

		bankDetailsForm.setId(bankDetails.getId());
		bankDetailsForm.setBankName(bankDetails.getBankName());
		bankDetailsForm.setLocation(bankDetails.getLocation());
		bankDetailsForm.setBranch(bankDetails.getBranch());
		bankDetailsForm.setBranchCode(bankDetails.getBranchCode());
		bankDetailsForm.setContactNo(bankDetails.getContactNo());
		bankDetailsForm.setEmail(bankDetails.getEmail());
		bankDetailsForm.setTransactionId(bankDetails.getTransactionId());

		model.put("user", user);

		model.put("bankDetailsForm", bankDetailsForm);

		return new ModelAndView("selectBankDetails", "model", model);

	}

	@RequestMapping(value = "/updateBDetails", method = RequestMethod.POST)
	public ModelAndView updateBDetails(ModelMap model,
			@ModelAttribute BankDetailsForm bankDetailsForm) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		model.put("bankDetailsForm", bankDetailsForm);

		return new ModelAndView("confirmUpdateBankDetails", "model", model);

	}

	@RequestMapping(value = "/updateBankDetails", method = RequestMethod.POST)
	public ModelAndView updateBankDetails(ModelMap model,
			@ModelAttribute BankDetailsForm bankDetailsForm,
			BindingResult result) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		BankDetails bankDetails = bankDetailsDAO
				.findId(bankDetailsForm.getId());

		bankDetails.setId(bankDetailsForm.getId());

		bankDetails.setBankName(bankDetailsForm.getBankName());

		bankDetails.setLocation(bankDetailsForm.getLocation());

		bankDetails.setBranch(bankDetailsForm.getBranch());

		bankDetails.setContactNo(bankDetailsForm.getContactNo());
		
		bankDetails.setBranchCode(bankDetailsForm.getBranchCode());

		bankDetails.setEmail(bankDetailsForm.getEmail());

		bankDetails.setTransactionId(bankDetailsForm.getTransactionId());

		bankDetailsDAO.update(bankDetails);

		model.put("bankDetailsForm", bankDetailsForm);

		return new ModelAndView("updateBankDetailsSuccess", "model", model);

	}

	@RequestMapping(value = "/bankEmpList", method = RequestMethod.GET)
	public ModelAndView bankEmpList(ModelMap model) {

		List<EndUser> userList = endUserDAOImpl.getByRoleList().getResultList();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("userList", userList);

		return new ModelAndView("bankEmpList", "model", model);
	}

	@RequestMapping(value = "/selectBank", method = RequestMethod.GET)
	public ModelAndView selectBank(ModelMap model, @RequestParam("id") Long id) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		EndUser endUser = endUserDAOImpl.findId(id);
		endUserForm.setId(endUser.getId());
		endUserForm.setUserName(endUser.getUserName());
		endUserForm.setContactNo(endUser.getContactNo());
		endUserForm.setCurrentRole(endUser.getCurrentRole());
		endUserForm.setEmail(endUser.getEmail());

		endUserForm.setStatus(endUser.getStatus());

		model.put("endUserForm", endUserForm);

		model.put("endUser", endUser);

		return new ModelAndView("selectBankEmp", "model", model);

	}

	@RequestMapping(value = "/selectUpdateBankEmp", method = RequestMethod.POST)
	public ModelAndView mailBank(ModelMap model,
			@ModelAttribute EndUserForm endUserForm, BindingResult result,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		EndUser endUuser = endUserDAOImpl.findId(endUserForm.getId());

		String email = endUuser.getEmail();
		String username = endUuser.getUserName();

		String password = endUuser.getPassword();
		String conatctNo = endUuser.getContactNo();
		String currentRole = endUuser.getCurrentRole();
		endUuser.setNotificationStatus("Notification Sent");
		endUserDAOImpl.mergeUser(endUuser);
		try {
			String stat = endUuser.getStatus();
			if (stat.equals("Approved")) {
				String tex = "Bank Details Notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello "
						+ username
						+ ",\n\n An official bank employee account has been created for you. "
						+ "\n" + "\n" + "\n\n Your Login Credentials are:"
						+ "\n\n\nUser Name: " + username + "\n\n\n Password: "
						+ password + "\n\n\nConatct Number: " + conatctNo
						+ "\n\n\nCurrent Role: " + currentRole
						+ "\n\n\nRegards,\nBank");
				System.out.println("" + email + username);
				mailSender.send(emails);
				SimpleMailMessage emails1 = new SimpleMailMessage();
				emails1.setTo(email);
				emails1.setSubject(tex);
				emails1.setText("Hello "
						+ username
						+ "\n\n An official bank employee account has been created for you.\n"
						+ "\n\nRegards,\nBank");

			}

			else if (stat.equals("Rejected")) {
				String tex = "Bank Details Notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello "
						+ username
						+ ",\n\n\n Your Bank Employee Account has been Rejected "

						+ "\n\n\nRegards,\nBank");
				mailSender.send(emails);
			} else {
				String tex = "Bank Details Notification";
				SimpleMailMessage emails = new SimpleMailMessage();
				emails.setTo(email);
				emails.setSubject(tex);
				emails.setText("Hello "
						+ username
						+ ",\n\n\n Your bank employee account  approval is pending.\n\n\nRegards,\nBank");
				mailSender.send(emails);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage() + e);
		}

		System.out.println("MAIL SENT");

		attributes.addFlashAttribute("success",
				"Mail has been sent successfully.");

		return new ModelAndView("redirect:bankEmpList");
	}

	@RequestMapping(value = "/appMngList", method = RequestMethod.GET)
	public ModelAndView appMngList(ModelMap model) {

		List<EndUser> userList = endUserDAOImpl.getByApprovalMng()
				.getResultList();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("userList", userList);

		return new ModelAndView("appMngList", "model", model);
	}

	@RequestMapping(value = "/selectApproveManager", method = RequestMethod.GET)
	public ModelAndView selectApproveManager(ModelMap model,
			@RequestParam("id") Long id) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		EndUser endUser = endUserDAOImpl.findId(id);
		endUserForm.setId(endUser.getId());
		endUserForm.setUserName(endUser.getUserName());
		endUserForm.setContactNo(endUser.getContactNo());
		endUserForm.setCurrentRole(endUser.getCurrentRole());
		endUserForm.setEmail(endUser.getEmail());

		model.put("endUserForm", endUserForm);

		model.put("endUser", endUser);

		return new ModelAndView("selectApproveManager", "model", model);

	}

	@RequestMapping(value = "/selectUpdateApproveManager", method = RequestMethod.POST)
	public ModelAndView mailApprovalManagerk(ModelMap model,
			@ModelAttribute EndUserForm endUserForm, BindingResult result,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);

		EndUser endUser = endUserDAOImpl.findId(endUserForm.getId());
		try {
			String email = endUser.getEmail();
			String username = endUser.getUserName();
			String password = endUser.getPassword();

			String conatctNo = endUser.getContactNo();
			String currentRole = endUser.getCurrentRole();
			String tex = "Approval Manager Details Notification";

			endUser.setNotificationStatus("Notification Sent");
			endUserDAOImpl.mergeUser(endUser);

			SimpleMailMessage emails = new SimpleMailMessage();
			emails.setTo(email);
			emails.setSubject(tex);
			emails.setText("Hello " + username
					+ ",\n\n An official account has been created for You. "
					+ "\n" + "\n" + "\n\n Your Login credentials are: "
					+ "\n\nUser Name:" + username + "\n\nPassword: " + password
					+ "\n\nConatct Number:" + conatctNo + "\n\nCurrent Role: "
					+ "\n\n\nCurrent Role: " + currentRole
					+ "\n\n\nRegards,\nBank");
			System.out.println("" + email + username);
			mailSender.send(emails);
			SimpleMailMessage emails1 = new SimpleMailMessage();
			emails1.setTo(email);
			emails1.setSubject(tex);
			emails1.setText("Hello " + username
					+ "\n\n An Official account has been created for You.\n"
					+ "\n\nRegards,\nBank");

			endUser.setNotificationStatus("Sent");
			endUserDAOImpl.update(endUser);

		} catch (Exception e) {
			System.out.println(e.getMessage() + e);
		}

		attributes.addFlashAttribute("success",
				"Mail has been sent successfully.");

		return new ModelAndView("redirect:appMngList");
	}

	@RequestMapping(value = "/bankemployeeList", method = RequestMethod.GET)
	public ModelAndView bankEmpApprovList(ModelMap model,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		
		List<EndUser> userList = endUserDAOImpl.getByRoleList().getResultList();
       if(userList != null && userList.size()  > 0)	
       {
		model.put("userList", userList);

		return new ModelAndView("bankemployeeList", "model", model);
       }
       else
       {
    	   return new ModelAndView("noDataFoundAdmin", "model", model);
       }
	}

	@RequestMapping(value = "/approvalManagerList", method = RequestMethod.GET)
	public ModelAndView approvalManagerList(ModelMap model) {

		List<EndUser> userList = endUserDAOImpl.getByApprovalMng()
				.getResultList();

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		 if(userList != null && userList.size()  > 0)	
	       {
		model.put("userList", userList);

		return new ModelAndView("approvalManagerList", "model", model);
	       }
		
		 else
	       {
	    	   return new ModelAndView("noDataFoundAdmin", "model", model);
	       }
	}

	/**
	 * Method to display List of (BankEmp,ApprovalMng) to Block/Unblock or Renew
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getUsersForBlockUnblockRenew", method = RequestMethod.GET)
	public ModelAndView getUsersForBlockUnblockRenew(ModelMap model,@ModelAttribute EndUserForm endUserForm) {
		
		List<EndUser> userList = endUserDAOImpl.getUsersForBlockUnblockRenew();
		 if(userList != null && userList.size()  > 0) {
			 model.put("userList", userList);
			 model.put("endUserForm", endUserForm);
			 return new ModelAndView("getUsersForBlockUnblockRenew", "model", model);
	     } else {
	    	 return new ModelAndView("noDataFoundAdmin", "model", model);
	       }

	}

	/**
	 * Method to renew account by extending the account expiry date
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getAdminRenew", method = RequestMethod.GET)
	public ModelAndView getRenewAccount(@RequestParam Long id, ModelMap model,
			@ModelAttribute EndUserForm endUserForm) {

		EndUser user = getCurrentLoggedUserDetails();
		EndUser renewUser = endUserDAOImpl.findId(id);
		endUserForm.setId(id);
		endUserForm.setAccExpiryDate(renewUser.getAccExpiryDate());

		model.put("user", user);
		model.put("endUserForm", endUserForm);
		return new ModelAndView("getAdminRenew", "model", model);

	}

	/**
	 * Method to renew account
	 * 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/renewAccount", method = RequestMethod.POST)
	public String renewAccount(@ModelAttribute EndUserForm endUserForm,
			ModelMap model) throws ParseException {

		EndUser user = endUserDAOImpl.findId(endUserForm.getId());
		
		 Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(endUserForm.getAccExpiryDateStr() );  

		user.setAccExpiryDate(date1);
		user.setAccRenewStatus(Constants.RENEWED);
		user.setReason(endUserForm.getReason());
		endUserDAOImpl.update(user);

		return "redirect:getUsersForBlockUnblockRenew";

	}

	/**
	 * Method to update EndUser with Block/Unblock status
	 * 
	 * @param endUserForm
	 * @param model
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/blockUnblockAccount", method = RequestMethod.POST)
	public ModelAndView blockUnblockAccount(ModelMap model,
			RedirectAttributes attributes,@ModelAttribute EndUserForm endUserForm) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		EndUser endUser = endUserDAOImpl.findId(endUserForm.getId());
		endUser.setAccessStatus(endUserForm.getAccessStatus());
		endUser.setReason(endUserForm.getReason());
		endUserForm.setTransactionId(endUser.getTransactionId());
		endUserDAOImpl.update(endUser);

		Transaction trans = new Transaction();

		trans.setTransactionId(endUserForm.getTransactionId());
		trans.setTransactionStatus("Account Renew/Block");
		trans.setTransactionType("Account update");

		attributes.addFlashAttribute("success", "Updated successfully");
		model.put("endUserForm", endUserForm);
		
		return new ModelAndView("adminBlockRenewTransaction", "model", model);
	}

	@RequestMapping(value = "/adminBlockRenewTransaction", method = RequestMethod.GET)
	public ModelAndView clientAppMngTransaction(ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);
		model.put("endUserForm", endUserForm);

		return new ModelAndView("adminBlockRenewTransaction", "model", model);

	}

	/**
	 * Method to display all BankEmp,App mng for Swapping/Reverting account
	 *
	 */
	@RequestMapping(value = "/usersForSwapRevert", method = RequestMethod.GET)
	public ModelAndView usersForSwapRevert(ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		List<EndUser> userList = endUserDAOImpl.getUsersForBlockUnblockRenew();
		List<SwapAccount> swapAccountList = swapAccountDAO.getAllSwapAccountsByStatus(Constants.APPROVED);

		if(userList != null && userList.size() > 0)
		{
		model.put("userList", userList);
		
		model.put("swapAccountList", swapAccountList);

		return new ModelAndView("usersForSwapRevert", "model", model);
		}
		else
		{
			return new ModelAndView("noDataFoundAdmin", "model", model);
		}
	}

	/**
	 * Method to display swap account details
	 */
	@RequestMapping(value = "/adminSwapAccount", method = RequestMethod.GET)
	public ModelAndView adminSwapAccount(@RequestParam Long id,
			@ModelAttribute SwapAccountForm swapAccountForm, ModelMap model) {

		EndUser user = getCurrentLoggedUserDetails();

		EndUser swapUser = endUserDAOImpl.findId(id);

		swapAccountForm.setOldUser(swapUser.getUserName());
		swapAccountForm.setOldEmail(swapUser.getCurrentRole());
		swapAccountForm.setOldContactNo(swapUser.getContactNo());
		swapAccountForm.setEndUserId(swapUser.getId());

		model.put("user", user);
		model.put("swapAccountForm", swapAccountForm);

		return new ModelAndView("adminSwapAccount", "model", model);
	}

	/**
	 * Method to display revert account details
	 */
	@RequestMapping(value = "/adminRevertAccount", method = RequestMethod.GET)
	public ModelAndView adminRevertAccount(@RequestParam Long id,
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

		return new ModelAndView("adminRevertAccount", "model", model);
	}

	/**
	 * Method to swap account and save
	 */
	@RequestMapping(value = "/saveSwapAccount", method = RequestMethod.POST)
	public String saveSwapAccount(ModelMap model,
			@ModelAttribute SwapAccountForm swapAccountForm,
			RedirectAttributes attributes) {

		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		// Existing user details
		EndUser endUser = endUserDAOImpl.findId(swapAccountForm.getEndUserId());

		SwapAccount swapAccount = new SwapAccount();

		swapAccount.setOldUser(endUser.getUserName());
		swapAccount.setOldPassword(endUser.getPassword());
		swapAccount.setOldEmail(endUser.getEmail());
		swapAccount.setOldContactNo(endUser.getContactNo());

		swapAccount.setNewUser(swapAccountForm.getNewUser());
		swapAccount.setNewPassword(swapAccountForm.getNewContactNo());
		swapAccount.setNewEmail(swapAccountForm.getNewEmail());
		swapAccount.setNewContactNo(swapAccountForm.getNewContactNo());

		swapAccount.setStatus(Constants.APPROVED);
		swapAccount.setEndUserId(endUser.getId());

		endUser.setUserName(swapAccount.getNewUser());
		endUser.setPassword(swapAccount.getNewPassword());
		endUser.setEmail(swapAccount.getNewEmail());
		endUser.setContactNo(swapAccount.getNewContactNo());
		endUser.setPasswordFlag(0);

		swapAccountDAO.createSwapAccount(swapAccount);
		endUserDAOImpl.update(endUser);

		attributes.addFlashAttribute("success",
				"Account swap approval request sent");

		return "redirect:usersForSwapRevert";
	}

	/**
	 * Method to swap account and save
	 */
	@RequestMapping(value = "/updateRevertAccount", method = RequestMethod.POST)
	public String updateRevertAccount(ModelMap model,
			@ModelAttribute SwapAccountForm swapAccountForm,
			RedirectAttributes attributes) {
		EndUser user = getCurrentLoggedUserDetails();
		model.put("user", user);
		// Existing user details
		EndUser endUser = endUserDAOImpl.findId(swapAccountForm.getEndUserId());

		SwapAccount swapAccount = swapAccountDAO
				.findByEndUserId(swapAccountForm.getEndUserId());

		swapAccount.setNewUser(swapAccount.getOldUser());
		swapAccount.setNewPassword(swapAccount.getOldContactNo());
		swapAccount.setNewEmail(swapAccount.getOldEmail());
		swapAccount.setNewContactNo(swapAccount.getOldContactNo());

		swapAccount.setOldUser(endUser.getUserName());
		swapAccount.setOldEmail(endUser.getEmail());
		swapAccount.setOldContactNo(endUser.getContactNo());
		swapAccount.setOldPassword("");

		swapAccount.setStatus(Constants.APPROVED);
		swapAccount.setEndUserId(endUser.getId());

		endUser.setUserName(swapAccount.getNewUser());
		endUser.setPassword(swapAccount.getNewPassword());
		endUser.setEmail(swapAccount.getNewEmail());
		endUser.setContactNo(swapAccount.getNewContactNo());
		endUser.setPasswordFlag(0);

		swapAccountDAO.updateSwapAccount(swapAccount);
		endUserDAOImpl.update(endUser);

		attributes.addFlashAttribute("success",
				"Account Revert approval request sent");

		return "redirect:usersForSwapRevert";
	}

	/**
	 * Method to generate Master Plan Chart
	 */
	@RequestMapping(value = "/generateMasterPlanChart", method = RequestMethod.GET)
	public ModelAndView generateMasterPlanChart(
			@RequestParam(required = false) Long id, ModelMap model) {
		EndUser user = getCurrentLoggedUserDetails();

		List<MasterPlan> masterPlanList = masterPlanDAO.getApprovedMasterKey()
				.getResultList();

		Map<MasterPlan,TreeChartForm> planChart = new LinkedHashMap<>();
		Map<PurchaseOrder, TreeChartForm> limitBurstNDocumentList = new LinkedHashMap<>();		
		TreeChartForm form = new TreeChartForm();
		if (id != null) {
			MasterPlan plan = masterPlanDAO.getByMasterPlanId(id);
			List<PurchaseOrder> poList = purchaseOrderDAO.getPoByMasterKeyList(
					plan.getMasterKey()).getResultList();
			List<Invoice> invoiceList = invoiceDAO.getInvoiceByMasterKeyList(plan.getMasterKey()).getResultList();
			if (poList != null && poList.size() > 0) {
				for (PurchaseOrder po : poList) {
					List<LimitBurst> limitBurstList = limitBurstDAO
							.getLimitBurstByPOKey(po.getPoKey());
					List<PoUpload> documentList = poUploadDAO
							.getPoUploadByPOKey(po.getPoKey());

					TreeChartForm treeChartForm = new TreeChartForm();
					treeChartForm.setLimitBurstList(limitBurstList);
					treeChartForm.setPoUploadList(documentList);

					limitBurstNDocumentList.put(po, treeChartForm);					
				}
				form.setPoList(limitBurstNDocumentList);
			}
			
			if(invoiceList != null && invoiceList.size() > 0) {
				form.setInvoiceList(invoiceList);
			}

			planChart.put(plan, form);
		}


		model.put("user", user);
		model.put("masterPlanList", masterPlanList);
		model.put("planChart", planChart);

		return new ModelAndView("generateMasterPlanChartAdmin", "model", model);
	}

	@RequestMapping(value = "/adminHelp", method = RequestMethod.GET)
	public ModelAndView adminHelp(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		EndUser user = getCurrentLoggedUserDetails();

		model.put("user", user);

		return new ModelAndView("adminHelp", "model", model);

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

		return new ModelAndView("loginDateAdmin", "model", model);

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

		return new ModelAndView("loginDateAdmin", "model", model);

	}

}
