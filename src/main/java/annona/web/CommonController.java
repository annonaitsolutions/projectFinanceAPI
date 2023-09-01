package annona.web;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import annona.dao.LoginDateDao;
import annona.domain.LoginDate;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.theme.CookieThemeResolver;

import annona.dao.EndUserDAO;
import annona.domain.EndUser;
import annona.services.DateService;
import annona.utility.Constants;

@Controller
@RequestMapping("/main")
public class CommonController {

	@Autowired
	EndUserDAO endUserDAO;

	@Autowired
	DateService dateService;

	@Autowired
	LoginDateDao loginDateDao;

	@Value("${useSavedLoginDate}")
	boolean useSavedLoginDate;

	private CookieLocaleResolver localeResolver;
	private CookieThemeResolver themeResolver;

	static Logger log = Logger.getLogger(AdminController.class.getName());

	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String getDefaultPage(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {


		EndUser endUser = endUserDAO.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getSingleResult();


		if(useSavedLoginDate) {
			LoginDate date = loginDateDao.getLoginDate();
//			if (date != null) DateService.loginDate = date.getLoginDate();
//			else DateService.loginDate = new Date();
			DateService.loginDate=(date==null?(new Date()):date.getLoginDate());
		}else {
			DateService.loginDate = new Date();
		}

//		date = DateService.loginDate;
		HttpSession session = request.getSession();
		if(!request.isUserInRole("ROLE_ADMIN")) {
			if (null != endUser.getAccessStatus() && endUser.getAccessStatus().equals(Constants.BLOCKED)) {

				return "redirect:/auth/login?error=2";

			} else if ((null != endUser.getAccExpiryDate() && (DateService.loginDate.equals(endUser.getAccExpiryDate()) || endUser.getAccExpiryDate().compareTo(DateService.loginDate) < 0)) || (null != endUser.getAccRenewStatus() && (endUser.getAccRenewStatus().equals(Constants.PENDING) || endUser.getAccRenewStatus().equals(Constants.REJECTED)))) {

				return "redirect:/auth/login?error=3";

			}
		}
		if (endUser.getTheme() != null) {
			themeResolver = new CookieThemeResolver();
			themeResolver.setThemeName(request, response, endUser.getTheme());
		}

		if (endUser.getPrefferedLanguage() != null) {

			log.info("Changing to default language " + endUser.getPrefferedLanguage());
			localeResolver = new CookieLocaleResolver();
			String defaultLocale = endUser.getPrefferedLanguage();
			Locale currentLanguage = LocaleUtils.toLocale(defaultLocale);
			localeResolver.setLocale(request, response, currentLanguage);
		}
		if ((endUser.getPasswordFlag() == 0) || (endUser.getPwdExpiryDate() != null && endUser.getPwdExpiryDate().compareTo(Calendar.getInstance().getTime()) < 0)) {

			attributes.addFlashAttribute("error","Password expired!!Please change the password");
			return "redirect:/auth/loginChangePassword?id=" + endUser.getId();

		} else {
			session.setAttribute("played", false);
			if (request.isUserInRole("ROLE_ADMIN")) {

				return "redirect:/admin/adminPage";
			} else if (request.isUserInRole("ROLE_BANKEMP")) {
//				return "redirect:/bnkEmp/bankEmpCommon";
				return "redirect:/bnkEmp/bankEmp";
			} else if (request.isUserInRole("ROLE_APPROVALMNG")) {
//				return "redirect:/appMng/apprMngCommon";
				return "redirect:/appMng/apprMng";
			} else if (request.isUserInRole("ROLE_USERBRANCH")) {
				return "redirect:/userBranch/userBranch";
			} else if (request.isUserInRole("ROLE_USERSUBSIDIARY")) {
				return "redirect:/userSubsidiary/userSubsidiary";
			} else if (request.isUserInRole("ROLE_VENDOR")) {
				return "redirect:/vendor/vendors";
			} else if (request.isUserInRole("ROLE_BUYER")) {
				return "redirect:/buyer/buyers";
			} else if (request.isUserInRole("ROLE_CUSTOMERADMIN")) {
//				return "redirect:/customerAdmin/custAdminCommon";
				return "redirect:/customerAdmin/custAdminPage";
			} else if (request.isUserInRole("ROLE_CUSTOMERAPPMNG")) {
//				return "redirect:/clientAppMng/clientApprMngCommon";
				return "redirect:/clientAppMng/clientApprMng";
			} else if (request.isUserInRole("ROLE_WAREHOUSEMNG")) {
				return "redirect:/wareHouseMng/wareHouseMngPage";
			}  else {
//				return "redirect:/users/commonUser";
				return "redirect:/users/user";
			}
		}
	}

	@RequestMapping(value = "/loginDate", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody Date loginDate() {

		return DateService.loginDate;
	}

}
