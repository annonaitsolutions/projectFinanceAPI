package annona.web;

import annona.dao.*;
import annona.domain.*;
import annona.form.*;
import annona.services.DateService;
import annona.services.UploadService;
import annona.utility.Constants;
import annona.utility.KeyGenerator;

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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Controller
@RequestMapping("/userSubsidiary")
public class customerSubsidiaryController implements ServletContextAware {


    // Used to display log messages
    protected Logger log = Logger.getLogger(AdminController.class.getName());
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    EndUserDAO endUserDAOImpl;
    @Autowired
    EndUserForm endUserForm;
    @Autowired
    UploadedFileForm uploadedFileForm;
    @Autowired
    UploadDAO uploadDaoImpl;
    @Autowired

    InvoiceInventoryDAO invoiceInventoryDAO;
    @Autowired
    SupplierDAO supplierDAO;
    @Autowired
    NewBuyerDAO newBuyerDAO;
    @Autowired
    SupplierForm supplierForm;
    @Autowired
    FundsDistributeDAO fundsDistributeDAO;
    @Autowired
    PurchaseOrderForm purchaseOrderForm;
    @Autowired
    RequestMoneyForm requestMoneyForm;
    @Autowired
    RequestMoneyDAO requestMoneyDAO;
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
    FundsStatementDAO fundsStatementDAO;
    @Autowired
    TransactionDAO transcationDAOImpl;
    @Autowired
    InventoryForm inventoryForm;
    @Autowired
    InventoryDAO invenrotyDAO;
    @Autowired
    SupplierDAO supplierDAOImpl;
    @Autowired
    MasterPlanDAO masterPlanDAO;
    @Autowired
    BuyerPODAO buyerPODAO;
    @Autowired
    LetterOfCreditForm letterOfCreditForm;
    @Autowired
    LetterOfCreditDAO letterOfCreditDAO;
    @Autowired
    CorrespondentDAO correspondentDAO;
    @Autowired
    UploadService uploadService;
    @Autowired
    DisputeDAO disputeDAO;
    @Autowired
    DisputeForm disputeForm;
    @Autowired
    CustomerBankDetailsDAO customerBankDetailsDAO;
    CookieThemeResolver themeResolver = new CookieThemeResolver();
    private ServletContext servletContext;

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

        return "redirect:userSubsidiary";
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

        return "redirect:userSubsidiary";
    }


    @RequestMapping(value = "/userSubsidiary", method = RequestMethod.GET)
    public ModelAndView showUserDashBoard(ModelMap model,
                                          HttpServletRequest request, HttpServletResponse response) {

        EndUser user = getCurrentLoggedUserDetails();

        model.put("user", user);

        return new ModelAndView("userSubsidiaryPage", "model", model);

    }

    @RequestMapping(value = "/editCustomerSubsidiaryProfile", method = RequestMethod.GET)
    public ModelAndView editAdminProfile(ModelMap model,
                                         @RequestParam("id") Long id, RedirectAttributes attributes) {

        EndUser user = getCurrentLoggedUserDetails();

        EndUser userProfile = endUserDAOImpl.findId(id);

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

        return new ModelAndView("editCustomerSubsidiaryProfile", "model", model);

    }

    @RequestMapping(value = "/confirmEditCustomerSubsidiaryProfile", method = RequestMethod.POST)
    public ModelAndView confirmEditAdminProfile(ModelMap model,
                                                @ModelAttribute EndUserForm endUserForm) {

        EndUser user = getCurrentLoggedUserDetails();

        model.put("user", user);

        model.put("endUserForm", endUserForm);

        return new ModelAndView("confirmEditCustomerSubsidiaryProfile", "model", model);

    }

    @RequestMapping(value = "/updateCustomerSubsidiaryDetails", method = RequestMethod.POST)
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

        return new ModelAndView("updateCustomerSubsidiarySuccess", "model", model);

    }

    @RequestMapping(value = "/editCustomerSubsidiaryPWD", method = RequestMethod.GET)
    public ModelAndView editAdminPWD(ModelMap model,
                                     @RequestParam("id") Long id, RedirectAttributes attributes) {

        EndUser user = getCurrentLoggedUserDetails();

        EndUser userProfile = endUserDAOImpl.findId(id);

        endUserForm.setId(userProfile.getId());

        endUserForm.setTransactionId(userProfile.getTransactionId());

        model.put("user", user);

        model.put("endUserForm", endUserForm);

        return new ModelAndView("editCustomerSubsidiaryPWD", "model", model);

    }

    @RequestMapping(value = "/updateEditCustomerSubsidiaryPWD", method = RequestMethod.POST)
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

        return new ModelAndView("updateCustomerSubsidiarySuccess", "model", model);

    }


    //File Upload

    @RequestMapping(value = "/fileUploadForm", method = RequestMethod.GET)
    public ModelAndView getUploadForm(RedirectAttributes attribute, ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        uploadedFileForm.setCustomerHeadName(user.getCustomerHeadName());
        uploadedFileForm.setCustomerHeadKey(user.getCustomerHeadKey());

        model.put("user", user);
        model.put("uploadedFileForm", uploadedFileForm);

        return new ModelAndView("uploadFormCustomerSubsidiary");
    }

    @RequestMapping("/fileUpload")
    public ModelAndView fileUploaded(ModelMap model, RedirectAttributes attribute, @ModelAttribute UploadedFileForm uploadedFileForm, BindingResult result) throws RuntimeException, IOException {


        EndUser user = getCurrentLoggedUserDetails();
        
        uploadedFileForm.setTransactionId(KeyGenerator.generateTransactionKey());

        UploadedFile upload = new UploadedFile();
        List<MultipartFile> files = uploadedFileForm.getFiles();
        for (MultipartFile multipartFile : files) {
            String fileName = multipartFile.getOriginalFilename();


            if (fileName != null && !fileName.equals("")) {
                log.info("These are the File" + fileName);
                uploadService.saveImage(servletContext.getRealPath("/") + "/img" + "/" + fileName, multipartFile);

            }

            upload.setFileName(servletContext.getRealPath("/") + "/img" + "/" + fileName);
            uploadDaoImpl.update(upload);
        }


        upload.setCustomerName(user.getUserName());
        upload.setDocument(uploadedFileForm.getDocument());
        upload.setCustomerHeadKey(uploadedFileForm.getCustomerHeadKey());
        upload.setCustomerHeadName(uploadedFileForm.getCustomerHeadName());
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
        model.put("user", user);

        attribute.addFlashAttribute("success", "Saved Successfully. ");
        return new ModelAndView("showFileCustomerSubsidiary", "message", files);
    }

    @RequestMapping(value = "/selectcustomerSubsidiarydocumentApprovalList", method = RequestMethod.GET)
    public ModelAndView selectdocumentApprovalList(ModelMap model) {

        EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
        List<UploadedFile> uploadDocumentList = uploadDaoImpl.findByName(user.getUserName()).getResultList();

        if (uploadDocumentList != null && uploadDocumentList.size() > 0) {

            model.put("user", user);
            model.put("uploadedFileForm", uploadedFileForm);
            model.put("uploadDocumentList", uploadDocumentList);

        }

        return new ModelAndView("customerSubsidiarydocumentApprovalList", "model", model);

    }

    //list of Buyer
    @RequestMapping(value = "/customerSubsidiaryBuyerList", method = RequestMethod.GET)
    public ModelAndView customerSubsidiaryBuyerList(ModelMap model) {

        EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

        if (user.getCustomerHeadName() != null) {
            List<NewBuyer> buyerList = newBuyerDAO.findByCustomerHeadName(user.getCustomerHeadName()).getResultList();

            if (buyerList != null && buyerList.size() > 0) {

                model.put("user", user);
                model.put("buyerList", buyerList);

            }
        }

        return new ModelAndView("customerSubsidiaryBuyerList", "model", model);

    }

    @RequestMapping(value = "/customerSubsidiarySupplierList", method = RequestMethod.GET)
    public ModelAndView customerSubsidiarySupplierList(ModelMap model) {

        EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

        if (user.getCustomerHeadName() != null) {
            List<Supplier> supplierList = supplierDAO.findByCustomerHeadName(user.getCustomerHeadName()).getResultList();

            if (supplierList != null && supplierList.size() > 0) {

                model.put("user", user);
                model.put("supplierList", supplierList);

            }
        }

        return new ModelAndView("customerSubsidiarySupplierList", "model", model);

    }


    @RequestMapping(value = "/subsBusinessPlan", method = RequestMethod.GET)
    public ModelAndView businessPlan(ModelMap model) {


        EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

        List<FundsDistribute> plan = fundsDistributeDAO.getFundsListByCustomerName(user.getUserName()).getResultList();

        if (plan != null) {
            model.put("plan", plan);
            model.put("user", user);
        }
        return new ModelAndView("subsBusinessPlan", "model", model);
    }

    @RequestMapping(value = "/createSubsBusinessPlan", method = RequestMethod.GET)
    public ModelAndView createBusinessPlan(@RequestParam("id") Long id, ModelMap model) {


        EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

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
            List<Supplier> suppliers = supplierDAO.findByCustomerHeadNameAndStatus(user.getCustomerHeadName()).getResultList();
            if (suppliers != null && suppliers.size() > 0) {
                model.put("suppliers", suppliers);
            }
            List<NewBuyer> buyers = newBuyerDAO.findByCustomerHeadNameAndStatus(user.getCustomerHeadName()).getResultList();
            if (buyers != null && buyers.size() > 0) {
                model.put("buyers", buyers);
            }
        }
        model.put("fundsDistributeForm", fundsDistributeForm);
        model.put("user", user);
        model.put("purchaseOrderForm", purchaseOrderForm);

        return new ModelAndView("createSubsBusinessPlan", "model", model);
    }

    @RequestMapping(value = "/subsBusinessPlanForPo", method = RequestMethod.GET)
    public ModelAndView branchBusinessPlanForPo(@RequestParam("id") Long id, ModelMap model) {


        EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();


        purchaseOrderForm.setCustomerHeadName(purchaseOrderForm.getCustomerHeadName());
        purchaseOrderForm.setCustomerName(purchaseOrderForm.getCustomerName());
        purchaseOrderForm.setMasterKey(purchaseOrderForm.getMasterKey());
        purchaseOrderForm.setUtilizedBusnsAmt(purchaseOrderForm.getUtilizedBusnsAmt());
        purchaseOrderForm.setDistributedAmount(purchaseOrderForm.getDistributedAmount());
        purchaseOrderForm.setBalance(purchaseOrderForm.getBalance());
        purchaseOrderForm.setCustomerHeadEmail(purchaseOrderForm.getCustomerHeadEmail());
        purchaseOrderForm.setCustomerBranchEmail(purchaseOrderForm.getCustomerBranchEmail());

        Supplier supplier = supplierDAO.findId(id);

        supplierForm.setId(supplier.getId());
        supplierForm.setSupplierName(supplier.getSupplierName());
        supplierForm.setEmail(supplier.getEmail());
        supplierForm.setBank(supplier.getBank());
        supplierForm.setBankEmail(supplier.getBankEmail());
        supplierForm.setCountry(supplier.getCountry());
        supplierForm.setState(supplier.getState());
        supplierForm.setCity(supplier.getCity());


        model.put("supplierForm", supplierForm);
        model.put("fundsDistributeForm", fundsDistributeForm);
        model.put("purchaseOrderForm", purchaseOrderForm);
        model.put("user", user);

        return new ModelAndView("subsBusinessPlanForPo", "model", model);
    }

    @RequestMapping(value = "/subsBusinessPlanForPoConfirm", method = RequestMethod.POST)
    public ModelAndView branchBusinessPlanForPoConfirm(ModelMap model,
                                                       @ModelAttribute PurchaseOrderForm purchaseOrderForm,
                                                       RedirectAttributes attributes) {

        EndUser user = getCurrentLoggedUserDetails();

        if (purchaseOrderForm.getAmount().compareTo(purchaseOrderForm.getBalance()) > 0) {
            Long Id = purchaseOrderForm.getId();
            attributes.addFlashAttribute("success", "Amount Should Not Exceed Balance");
            return new ModelAndView("redirect:subsBusinessPlanForPo?id=" + Id + "");
        }

        purchaseOrderForm.setCustomerHeadEmail(purchaseOrderForm.getCustomerHeadEmail());
        purchaseOrderForm.setCustomerBranchEmail(purchaseOrderForm.getCustomerBranchEmail());
        purchaseOrderForm.setSupplierEmail(purchaseOrderForm.getSupplierEmail());
        purchaseOrderForm.setSupplierBank(purchaseOrderForm.getSupplierBank());
        purchaseOrderForm.setSupplierBankEmail(purchaseOrderForm.getSupplierBankEmail());
        purchaseOrderForm.setSuppliercountry(purchaseOrderForm.getSuppliercountry());
        purchaseOrderForm.setSupplierState(purchaseOrderForm.getSupplierState());
        purchaseOrderForm.setSupplierCity(purchaseOrderForm.getSupplierCity());
        purchaseOrderForm.setMasterKey(purchaseOrderForm.getMasterKey());
        purchaseOrderForm.setCustomerName(purchaseOrderForm.getCustomerName());
        purchaseOrderForm.setCustomerHeadName(purchaseOrderForm.getCustomerHeadName());
        purchaseOrderForm.setSupplierName(purchaseOrderForm.getSupplierName());
        purchaseOrderForm.setPurchaseDate(purchaseOrderForm.getPurchaseDate());
        purchaseOrderForm.setGoodsCategory(purchaseOrderForm.getGoodsCategory());
        purchaseOrderForm.setGoods(purchaseOrderForm.getGoods());
        purchaseOrderForm.setGoodsDetails(purchaseOrderForm.getGoodsDetails());
        purchaseOrderForm.setQuantity(purchaseOrderForm.getQuantity());
        purchaseOrderForm.setWeight(purchaseOrderForm.getWeight());
        purchaseOrderForm.setLicence(purchaseOrderForm.getLicence());
        purchaseOrderForm.setAmount(purchaseOrderForm.getAmount());
        purchaseOrderForm.setUtilizedBusnsAmt(purchaseOrderForm.getUtilizedBusnsAmt());
        purchaseOrderForm.setBalance(purchaseOrderForm.getBalance());
        purchaseOrderForm.setTenure(purchaseOrderForm.getTenure());


        model.put("user", user);
        model.put("purchaseOrderForm", purchaseOrderForm);

        return new ModelAndView("subsBusinessPlanForPoConfirm", "model", model);

    }

    @RequestMapping(value = "/subsBusinessPlanForPoPost", method = RequestMethod.POST)
    public ModelAndView branchBusinessPlanForPoPost(ModelMap model,
                                                    @ModelAttribute PurchaseOrderForm purchaseOrderForm,
                                                    RedirectAttributes attributes) {

        EndUser user = getCurrentLoggedUserDetails();
        
        purchaseOrderForm.setPoKey(KeyGenerator.generateKey(purchaseOrderForm.getCustomerPrefix(),Constants.PURCHASEORDER));
		purchaseOrderForm.setTransactionId(KeyGenerator.generateTransactionKey());

        PurchaseOrder purchaseOrder = new PurchaseOrder();

        Float utilizedAmt = purchaseOrderForm.getUtilizedBusnsAmt();
        Float bal = purchaseOrderForm.getBalance();

        Float totalUtilized = utilizedAmt + purchaseOrderForm.getAmount();
        Float totalBal = bal - purchaseOrderForm.getAmount();

        purchaseOrder.setCustomerHeadEmail(purchaseOrderForm.getCustomerHeadEmail());
        purchaseOrder.setCustomerBranchEmail(purchaseOrderForm.getCustomerBranchEmail());
        purchaseOrder.setMasterKey(purchaseOrderForm.getMasterKey());
        purchaseOrder.setSupplierBank(purchaseOrderForm.getSupplierBank());
        purchaseOrder.setCustomerName(purchaseOrderForm.getCustomerName());
        purchaseOrder.setCustomerHeadName(purchaseOrderForm.getCustomerHeadName());
        purchaseOrder.setSupplierName(purchaseOrderForm.getSupplierName());
        purchaseOrder.setSupplierEmail(purchaseOrderForm.getSupplierEmail());
        purchaseOrder.setPurchaseDate(purchaseOrderForm.getPurchaseDate());
        purchaseOrder.setGoodsCategory(purchaseOrderForm.getGoodsCategory());
        purchaseOrder.setGoods(purchaseOrderForm.getGoods());
        purchaseOrder.setGoodsDetails(purchaseOrderForm.getGoodsDetails());
        purchaseOrder.setQuantity(purchaseOrderForm.getQuantity());
        purchaseOrder.setWeight(purchaseOrderForm.getWeight());
        purchaseOrder.setLicence(purchaseOrderForm.getLicence());
        purchaseOrder.setAmount(purchaseOrderForm.getAmount());
        purchaseOrder.setTransactionId(purchaseOrderForm.getTransactionId());
        purchaseOrder.setPoKey(purchaseOrderForm.getPoKey());
        purchaseOrder.setTenure(purchaseOrderForm.getTenure());
        purchaseOrder.setvStatus("Pending");
        purchaseOrder.setFlag(1);

        purchaseOrderDAO.insertPo(purchaseOrder);

        FundsDistribute funds = fundsDistributeDAO.getFundsListByKeyAndName(purchaseOrderForm.getMasterKey(), purchaseOrderForm.getCustomerName()).getSingleResult();

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

        return new ModelAndView("subsBusinessPlanForPoTransaction", "model", model);

    }

    @RequestMapping(value = "/subsBusinessPlanForPoTransaction", method = RequestMethod.GET)
    public ModelAndView businessPlanForPoTransaction(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        model.put("user", user);
        model.put("purchaseOrderForm", purchaseOrderForm);

        return new ModelAndView("subsBusinessPlanForPoTransaction", "model", model);

    }

    @RequestMapping(value = "/subsBusinessPlanForInvoice", method = RequestMethod.GET)
    public ModelAndView businessPlanForInvoice(@RequestParam("id") Long id, ModelMap model) {


        EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();


        invoiceForm.setCustomerHeadName(invoiceForm.getCustomerHeadName());
        invoiceForm.setCustomerName(invoiceForm.getCustomerName());
        invoiceForm.setMasterKey(invoiceForm.getMasterKey());
        invoiceForm.setCustomerHeadEmail(invoiceForm.getCustomerHeadEmail());
        invoiceForm.setCustomerBranchEmail(invoiceForm.getCustomerBranchEmail());


        NewBuyer buyer = newBuyerDAO.findId(id);

        newBuyerForm.setId(buyer.getId());
        newBuyerForm.setBuyerName(buyer.getBuyerName());
        newBuyerForm.setEmail(buyer.getEmail());
        newBuyerForm.setBank(buyer.getBank());
        newBuyerForm.setBankEmail(buyer.getBankEmail());
        newBuyerForm.setCountry(buyer.getCountry());
        newBuyerForm.setState(buyer.getState());
        newBuyerForm.setCity(buyer.getCity());


        model.put("fundsDistributeForm", fundsDistributeForm);
        model.put("newBuyerForm", newBuyerForm);
        model.put("invoiceForm", invoiceForm);
        model.put("user", user);

        return new ModelAndView("subsBusinessPlanForInvoice", "model", model);
    }

    @RequestMapping(value = "/subsBusinessPlanForInvoiceConfirm", method = RequestMethod.POST)
    public ModelAndView businessPlanForInvoiceConfirm(ModelMap model,
                                                      @ModelAttribute InvoiceForm invoiceForm,
                                                      RedirectAttributes attributes) {

        EndUser user = getCurrentLoggedUserDetails();


        invoiceForm.setCustomerHeadEmail(invoiceForm.getCustomerHeadEmail());
        invoiceForm.setCustomerBranchEmail(invoiceForm.getCustomerBranchEmail());
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


        model.put("user", user);
        model.put("invoiceForm", invoiceForm);

        return new ModelAndView("subsBusinessPlanForInvoiceConfirm", "model", model);

    }

    @RequestMapping(value = "/subsBusinessPlanForInvoicePost", method = RequestMethod.POST)
    public ModelAndView branchBusinessPlanForInvoicePost(ModelMap model, @ModelAttribute InvoiceForm invoiceForm, 
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
        invoice.setGoods(invoiceForm.getGoods());
        invoice.setGoodsDetails(invoiceForm.getGoodsDetails());
        invoice.setQuantity(invoiceForm.getQuantity());
        invoice.setWeight(invoiceForm.getWeight());
        invoice.setLicence(invoiceForm.getLicence());
        invoice.setAmount(invoiceForm.getAmount());
        invoice.setTransactionId(invoiceForm.getTransactionId());
        invoice.setPoKey(invoiceForm.getPoKey());
        invoice.setStatus("Pending");
        invoice.setPoInfo(invoiceForm.getPoInfo());
        invoice.setPoDate(invoiceForm.getPoDate());

        invoiceDAO.insertInvoice(invoice);


        Transaction trans = new Transaction();

        trans.setTransactionId(invoiceForm.getTransactionId());
        trans.setTransactionType("Invoice");
        trans.setTransactionStatus("Submitted successfully");
        transcationDAOImpl.insertTransaction(trans);

        model.put("user", user);
        model.put("invoiceForm", invoiceForm);

        return new ModelAndView("subsBusinessPlanForInvoiceTransaction", "model", model);

    }

    @RequestMapping(value = "/subsBusinessPlanForInvoiceTransaction", method = RequestMethod.GET)
    public ModelAndView businessPlanForInvoiceTransaction(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        model.put("user", user);
        model.put("invoiceForm", invoiceForm);

        return new ModelAndView("subsBusinessPlanForInvoiceTransaction", "model", model);

    }

    @RequestMapping(value = "/listSubsPoOnMasterKeyAndName", method = RequestMethod.GET)
    public ModelAndView listofPoOnMasterKeyAndName(@RequestParam("id") Long id, ModelMap model) {

        EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

        FundsDistribute funds = fundsDistributeDAO.getByFundsId(id);

        List<PurchaseOrder> purchase = purchaseOrderDAO.getPoListByMasterKeyAndName(funds.getMasterKey(), user.getUserName()).getResultList();

        if (purchase != null && purchase.size() > 0) {
            model.put("purchase", purchase);
        }

        model.put("user", user);
        return new ModelAndView("listSubsPoOnMasterKeyAndName", "model", model);
    }

    @RequestMapping(value = "/listSubsInvoiceOnMasterKeyAndName", method = RequestMethod.GET)
    public ModelAndView listofInvoiceOnMasterKey(@RequestParam("id") Long id, ModelMap model) {

        EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

        FundsDistribute funds = fundsDistributeDAO.getByFundsId(id);

        List<Invoice> invoice = invoiceDAO.getInvoiceListByMasterKeyAndName(funds.getMasterKey(), user.getUserName()).getResultList();

        if (invoice != null && invoice.size() > 0) {
            model.put("invoice", invoice);
        }

        model.put("user", user);
        return new ModelAndView("listSubsInvoiceOnMasterKeyAndName", "model", model);
    }

    @RequestMapping(value = "/customerSubsInvoiceList", method = RequestMethod.GET)
    public ModelAndView BankFullInvoiceList(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        List<Invoice> invoiceList = invoiceDAO.getInvoiceListByBranchName(user.getUserName()).getResultList();

        model.put("user", user);
        model.put("invoiceList", invoiceList);

        return new ModelAndView("customerSubsInvoiceList", "model", model);

    }

    @RequestMapping(value = "/customerSubsPoList", method = RequestMethod.GET)
    public ModelAndView fullPoList(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        List<PurchaseOrder> poList = purchaseOrderDAO.getPoListByBranchName(user.getUserName()).getResultList();

        model.put("user", user);
        model.put("poList", poList);

        return new ModelAndView("customerSubsPoList", "model", model);

    }
    //Purchase Order Inventory

    @RequestMapping(value = "/InventoryPoListForSub", method = RequestMethod.GET)
    public ModelAndView InventoryPoList(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        List<PurchaseOrder> poList = purchaseOrderDAO.getPoBycustomerNameAndStatus(user.getUserName()).getResultList();

        model.put("user", user);
        model.put("poList", poList);

        return new ModelAndView("InventoryPoListForSub", "model", model);

    }


    @RequestMapping(value = "/inventoryPoListForSubShow", method = RequestMethod.GET)
    public ModelAndView inventoryShow(@RequestParam Long id, ModelMap model,
                                      RedirectAttributes attributes) {
        EndUser user = getCurrentLoggedUserDetails();

        PurchaseOrder purchaseorder = purchaseOrderDAO.getByPurchaseId(id);

        inventoryForm.setId(purchaseorder.getId());
        inventoryForm.setCustomerName(purchaseorder.getCustomerName());
        inventoryForm.setPoKey(purchaseorder.getPoKey());
        inventoryForm.setAmount(purchaseorder.getAmount());
        inventoryForm.setQuantity(purchaseorder.getQuantity());
        inventoryForm.setMasterKey(purchaseorder.getMasterKey());
        inventoryForm.setSupplierName(purchaseorder.getSupplierName());
        inventoryForm.setCustomerHeadName(purchaseorder.getCustomerHeadName());

        model.put("user", user);
        model.put("inventoryForm", inventoryForm);

        return new ModelAndView("inventoryPoListForSubShow", "model", model);
    }

    @RequestMapping(value = "/inventoryPoListForSubShowConfirm", method = RequestMethod.POST)
    public ModelAndView approveBankEmpConfrim(
            @ModelAttribute InventoryForm inventoryForm, ModelMap model,
            RedirectAttributes attributes) {
        EndUser user = getCurrentLoggedUserDetails();

        inventoryForm.setMasterKey(inventoryForm.getMasterKey());

        model.put("user", user);
        model.put("inventoryForm", inventoryForm);

        return new ModelAndView("inventoryPoListForSubShowConfirm", "model", model);
    }

    @RequestMapping(value = "/updateinventoryPoListForSubShowConfirm", method = RequestMethod.POST)
    public ModelAndView updateinventoryShowConfirm(
            @ModelAttribute InventoryForm inventoryForm, ModelMap model,
            RedirectAttributes attributes) {

        List<Inventory> inventory1 = invenrotyDAO.getInventoryByKeyList(inventoryForm.getMasterKey(), inventoryForm.getCustomerName()).getResultList();

        if (inventory1 != null && inventory1.size() > 0) {
            inventory1.get(0).setDamaged(inventoryForm.getDamaged());
            inventory1.get(0).setTotal(inventoryForm.getTotal());
            inventory1.get(0).setUsedQuantity(inventoryForm.getUsedQuantity());
            invenrotyDAO.updateInventory(inventory1.get(0));
        } else {
            Inventory inventroy = new Inventory();

            inventroy.setId(inventoryForm.getId());
            inventroy.setCustomerName(inventoryForm.getCustomerName());
            inventroy.setCustomerHeadName(inventoryForm.getCustomerHeadName());
            inventroy.setPoKey(inventoryForm.getPoKey());
            inventroy.setAmount(inventoryForm.getAmount());
            inventroy.setDamaged(inventoryForm.getDamaged());
            inventroy.setTotal(inventoryForm.getTotal());
            inventroy.setUsedQuantity(inventoryForm.getUsedQuantity());
            inventroy.setQuantity(inventoryForm.getQuantity());
            inventroy.setMasterKey(inventoryForm.getMasterKey());
            inventroy.setSupplierName(inventoryForm.getSupplierName());
            inventroy.setTransactionId(inventoryForm.getTransactionId());
            inventroy.setFlag(0);
            invenrotyDAO.insertInventory(inventroy);


        }


        return new ModelAndView("redirect:InventoryPoList");
    }

    @RequestMapping(value = "/inventoryPoListForSub", method = RequestMethod.GET)
    public ModelAndView InventoryList(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        List<Inventory> inventoryList = invenrotyDAO.getInventoryBycustomerName(user.getUserName()).getResultList();

        model.put("user", user);
        model.put("inventoryList", inventoryList);

        return new ModelAndView("inventoryPoListForSubApproved", "model", model);

    }


    //Inventory for Invoice Customer subsidairy


    @RequestMapping(value = "/inventoryInvoiceSubsidiaryList", method = RequestMethod.GET)
    public ModelAndView InventoryInvoiceList(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        List<Invoice> invoiceList = invoiceDAO.getInoviceListBycustomerNameAndStatus(user.getUserName()).getResultList();

        model.put("user", user);
        model.put("invoiceList", invoiceList);

        return new ModelAndView("inventoryInvoiceSubsidiaryList", "model", model);

    }


    @RequestMapping(value = "/inventoryInvoiceSubsidiaryShow", method = RequestMethod.GET)
    public ModelAndView inventoryInvoiceShow(@RequestParam Long id, ModelMap model,
                                             RedirectAttributes attributes) {
        EndUser user = getCurrentLoggedUserDetails();


        Invoice invoice = invoiceDAO.getByInvoiceId(id);

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

        return new ModelAndView("inventoryInvoiceSubsidiaryShow", "model", model);
    }

    @RequestMapping(value = "/inventoryInvoiceSubsidiaryShowConfirm", method = RequestMethod.POST)
    public ModelAndView inventoryInvoiceSubsidiaryShowConfirm(
            @ModelAttribute InventoryForm inventoryForm, ModelMap model,
            RedirectAttributes attributes) {
        EndUser user = getCurrentLoggedUserDetails();

        inventoryForm.setMasterKey(inventoryForm.getMasterKey());

        model.put("user", user);
        model.put("inventoryForm", inventoryForm);

        return new ModelAndView("inventoryInvoiceSubsidiaryShowConfirm", "model", model);
    }

    @RequestMapping(value = "/updateinventoryInvoiceSubsidiaryShowConfirm", method = RequestMethod.POST)
    public ModelAndView updateinventoryInvoiceSubsidiaryShowConfirm(
            @ModelAttribute InvoiceInventoryForm invoiceInventoryForm, ModelMap model,
            RedirectAttributes attributes) {

        List<InvoiceInventory> inventory1 = invoiceInventoryDAO.getInventoryByKeyList(invoiceInventoryForm.getMasterKey(), invoiceInventoryForm.getCustomerName()).getResultList();

        if (inventory1 != null && inventory1.size() > 0) {
            inventory1.get(0).setDamaged(invoiceInventoryForm.getDamaged());
            inventory1.get(0).setTotal(invoiceInventoryForm.getTotal());
            inventory1.get(0).setUsedQuantity(invoiceInventoryForm.getUsedQuantity());
            invoiceInventoryDAO.updateInvoiceInventory(inventory1.get(0));
        } else {
            InvoiceInventory invoiceInventory = new InvoiceInventory();

            invoiceInventory.setId(invoiceInventoryForm.getId());
            invoiceInventory.setCustomerName(invoiceInventoryForm.getCustomerName());
            invoiceInventory.setCustomerHeadName(invoiceInventoryForm.getCustomerHeadName());
            invoiceInventory.setPoKey(invoiceInventoryForm.getPoKey());
            invoiceInventory.setAmount(invoiceInventoryForm.getAmount());
            invoiceInventory.setDamaged(invoiceInventoryForm.getDamaged());
            invoiceInventory.setTotal(invoiceInventoryForm.getTotal());
            invoiceInventory.setUsedQuantity(invoiceInventoryForm.getUsedQuantity());
            invoiceInventory.setQuantity(invoiceInventoryForm.getQuantity());
            invoiceInventory.setMasterKey(invoiceInventoryForm.getMasterKey());
            invoiceInventory.setBuyerName(invoiceInventoryForm.getBuyerName());
            invoiceInventory.setInventoryType("Invoice");
            invoiceInventory.setTransactionId(invoiceInventoryForm.getTransactionId());
            invoiceInventory.setFlag(0);
            invoiceInventoryDAO.insertInvoiceInventory(invoiceInventory);


        }


        return new ModelAndView("redirect:inventoryInvoiceSubsidiaryList");
    }


    @RequestMapping(value = "/inventoryInvoiceListForSubsidiaryApproved", method = RequestMethod.GET)
    public ModelAndView inventoryInvoiceListCustApproved(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        List<InvoiceInventory> inventoryInvoiceList = invoiceInventoryDAO.getInvoiceInventoryBycustomerName(user.getUserName()).getResultList();

        model.put("user", user);
        model.put("inventoryInvoiceList", inventoryInvoiceList);

        return new ModelAndView("inventoryInvoiceListForSubsidiaryApproved", "model", model);

    }


    //Add New Buyer
    @RequestMapping(value = "/addBuyerCusSubsidiary", method = RequestMethod.GET)
    public ModelAndView newBuyer(ModelMap model) {

        EndUser user = endUserDAOImpl
                .findByUsername(getCurrentLoggedUserName()).getSingleResult();

        newBuyerForm.setEndUser(user);
        newBuyerForm.setName(user.getUserName());
        newBuyerForm.setbName(user.getCustomerHeadName());
        List<NewBuyer> newbuyeList = newBuyerDAO.getByPending()
                .getResultList();

        model.put("user", user);
        model.put("newbuyeList", newbuyeList);
        model.put("newBuyerForm", newBuyerForm);

        return new ModelAndView("addBuyerCusSubsidiary", "model", model);
    }


    @RequestMapping(value = "/updatenewBuyerSubsPage", method = RequestMethod.POST)
    public ModelAndView updatenewBuyerPage(
            @ModelAttribute NewBuyerForm newBuyerForm,
            RedirectAttributes attributes, ModelMap model) {

        List<NewBuyer> buyer = newBuyerDAO.findByName(newBuyerForm.getBuyerName()).getResultList();

        List<EndUser> endUser = endUserDAOImpl.findByUsername(
                newBuyerForm.getBuyerName()).getResultList();
        if (endUser.size() == 0 || buyer.size() == 0) {

            EndUser user = getCurrentLoggedUserDetails();


            model.put("user", user);
            model.put("newBuyerForm", newBuyerForm);

            return new ModelAndView("updatenewBuyerSubsPage", "model", model);
        } else {
            attributes.addFlashAttribute("success", "Customer Name Already Exists");

            return new ModelAndView("redirect:newBuyerPage");

        }

    }

    @RequestMapping(value = "/newbuyerConfirmSubList", method = RequestMethod.POST)
    public ModelAndView approvalConfirm(
            @ModelAttribute NewBuyerForm newBuyerForm, ModelMap model,
            RedirectAttributes attribute) {

        NewBuyer newBuyer = new NewBuyer();
        
        newBuyerForm.setTransactionId(KeyGenerator.generateTransactionKey());

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
        newBuyer.setContactNum(newBuyerForm.getContactNum());
        newBuyer.setCountry(newBuyerForm.getCountry());
        newBuyer.setEmail(newBuyerForm.getEmail());
        newBuyer.setName(newBuyerForm.getName());
        newBuyer.setbName(newBuyerForm.getbName());
        newBuyer.setPinCode(newBuyerForm.getPinCode());
        newBuyer.setState(newBuyerForm.getState());
        newBuyer.setcStatus("Pending");

        newBuyer.setUniqueKey(newBuyerForm.getUniqueKey());
        newBuyer.setCurrencydeal(newBuyerForm.getCurrencydeal());
        newBuyer.setTransactionId(newBuyerForm.getTransactionId());
        transaction.setTransactionId(newBuyerForm.getTransactionId());
        transaction.setTransactionType("Buyer Details");
        transaction.setTransactionStatus("Buyer saved successfully");
        newBuyerDAO.createUser(newBuyer);
        transcationDAOImpl.insertTransaction(transaction);

        attribute.addFlashAttribute("success", "Saved Successfully");

        model.put("newBuyerForm", newBuyerForm);

        return new ModelAndView("buyerSubListSuccess", "model", model);

    }


    @RequestMapping(value = "/selectBuyerForCustomerSubUpdate", method = RequestMethod.GET)
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

        return new ModelAndView("selectBuyerForCustomerSubUpdate", "model", model);

    }

    @RequestMapping(value = "/selectBuyerForCustomerSubUpdate2", method = RequestMethod.POST)
    public ModelAndView selectBuyerUpdate2(ModelMap model,
                                           @ModelAttribute NewBuyerForm newBuyerForm) {

        EndUser user = getCurrentLoggedUserDetails();

        model.put("user", user);

        model.put("newBuyerForm", newBuyerForm);

        return new ModelAndView("selectBuyerForCustomerSubUpdate2", "model", model);

    }

    @RequestMapping(value = "/selectBuyerForCustomerSubUpdate3", method = RequestMethod.POST)
    public ModelAndView selectBuyerUpdate3(
            @ModelAttribute NewBuyerForm newBuyerForm, ModelMap model,
            RedirectAttributes attribute) {

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
        newBuyer.setbName(newBuyerForm.getbName());
        newBuyer.setDate(newBuyerForm.getDate());
        newBuyer.setEmail(newBuyerForm.getEmail());
        newBuyer.setId(newBuyerForm.getId());
        newBuyer.setName(newBuyerForm.getName());
        newBuyer.setPinCode(newBuyerForm.getPinCode());
        newBuyer.setState(newBuyerForm.getState());
        newBuyer.setCurrencydeal(newBuyerForm.getCurrencydeal());
        newBuyer.setcStatus("Pending");
        newBuyer.setcStatus(newBuyerForm.getcStatus());
        transaction.setTransactionType("Buyer Details");
        transaction
                .setTransactionStatus("Buyer Details status saved successfully");
        transaction.setTransactionId(newBuyerForm.getTransactionId());
        model.put("newBuyerForm", newBuyerForm);

        newBuyerDAO.update(newBuyer);
        transcationDAOImpl.insertTransaction(transaction);
        return new ModelAndView("selectBuyerForCustomerSubUpdate3", "model", model);
    }


    //Add Supplier

    @RequestMapping(value = "/supplierForCustomerSub", method = RequestMethod.GET)
    public ModelAndView newSupplier(ModelMap model) {

        EndUser user = endUserDAOImpl
                .findByUsername(getCurrentLoggedUserName()).getSingleResult();

        List<Supplier> supplierList = supplierDAOImpl.getByPending()
                .getResultList();
        supplierForm.setEndUser(user);
        supplierForm.setName(user.getUserName());
        supplierForm.setbName(user.getCustomerHeadName());
        model.put("user", user);
        model.put("supplierList", supplierList);

        model.put("supplierForm", supplierForm);

        return new ModelAndView("supplierForCustomerSub", "model", model);
    }


    @RequestMapping(value = "/supplierPageForwardCustomerSub", method = RequestMethod.POST)
    public ModelAndView selectsupplierUpdat(
            @ModelAttribute SupplierForm supplierForm,
            RedirectAttributes attributes, ModelMap model) {

        List<Supplier> supplier = supplierDAOImpl.findByName(supplierForm.getSupplierName()).getResultList();

        List<EndUser> endUser = endUserDAOImpl.findByUsername(
                supplierForm.getSupplierName()).getResultList();
        if (endUser.size() == 0 || supplier.size() == 0) {

            EndUser user = getCurrentLoggedUserDetails();

            model.put("user", user);

            model.put("supplierForm", supplierForm);

            return new ModelAndView("supplierPageForwardCustomerSub", "model", model);
        } else {
            attributes.addFlashAttribute("success", "Customer Name Already Exists");

            return new ModelAndView("redirect:newSupplierPage");

        }

    }

    @RequestMapping(value = "/supplierPageCustomerSubConfirmList", method = RequestMethod.POST)
    public ModelAndView approvalConfirm(
            @ModelAttribute SupplierForm supplierForm, ModelMap model,
            RedirectAttributes attribute) {

        Supplier supplier = new Supplier();
        
        supplierForm.setTransactionId(KeyGenerator.generateTransactionKey());

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
        supplier.setbName(supplierForm.getbName());

        supplier.setTransactionId(supplierForm.getTransactionId());
        transaction.setTransactionId(supplierForm.getTransactionId());
        transaction.setTransactionType("Supplier Details");
        transaction.setTransactionStatus("Supplier Details saved successfully");
        supplierDAOImpl.createUser(supplier);
        transcationDAOImpl.insertTransaction(transaction);

        attribute.addFlashAttribute("success", "Saved Successfully");

        model.put("supplierForm", supplierForm);

        return new ModelAndView("supplierPageCustomerSubConfirmList", "model", model);

    }

    @RequestMapping(value = "/selectsupplierUpdateForCustomerSub", method = RequestMethod.GET)
    public ModelAndView selectsupplierUpdat(ModelMap model,
                                            @RequestParam("id") Long id, RedirectAttributes attributes) {

        EndUser user = getCurrentLoggedUserDetails();

        Supplier supplier = supplierDAOImpl.findId(id);

        supplierForm.setId(supplier.getId());
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
        supplierForm.setTransactionId(supplier.getTransactionId());

        model.put("user", user);

        model.put("supplierForm", supplierForm);

        return new ModelAndView("selectsupplierUpdateForCustomerSub", "model", model);

    }

    @RequestMapping(value = "/selectsupplierUpdateForCustomerSub2", method = RequestMethod.POST)
    public ModelAndView selectSupplierUpdate2(ModelMap model,
                                              @ModelAttribute SupplierForm supplierForm,
                                              RedirectAttributes attributes) {

        EndUser user = getCurrentLoggedUserDetails();
        Supplier supplier = supplierDAOImpl.findId(supplierForm.getId());

        model.put("user", user);
        model.put("supplier", supplier);

        model.put("supplierForm", supplierForm);

        return new ModelAndView("selectsupplierUpdateForCustomerSub2", "model", model);

    }

    @RequestMapping(value = "/selectsupplierUpdateForCustomerSub3", method = RequestMethod.POST)
    public ModelAndView selectSupplierUpdate3(
            @ModelAttribute SupplierForm supplierForm, ModelMap model,
            RedirectAttributes attribute) {
        EndUser user = getCurrentLoggedUserDetails();
        Supplier supplier = supplierDAOImpl.findId(supplierForm.getId());
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
        supplierDAOImpl.update(supplier);
        transcationDAOImpl.insertTransaction(transaction);
        return new ModelAndView("selectsupplierUpdateForCustomerSub3", "model", model);
    }

    @RequestMapping(value = "/SubsFundsRequest", method = RequestMethod.GET)
    public ModelAndView branchFundsRequest(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        List<MasterPlan> masterList = masterPlanDAO.getMasterPlanForFunds(user.getCustomerHeadName()).getResultList();

        {
            model.put("masterList", masterList);
            model.put("user", user);
        }
        return new ModelAndView("SubsFundsRequest", "model", model);

    }

    @RequestMapping(value = "/subsRequestMoney", method = RequestMethod.GET)
    public ModelAndView branchRequestMoney(@RequestParam("id") Long id, ModelMap model) {


        EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

        MasterPlan plan = masterPlanDAO.getByMasterPlanId(id);

        requestMoneyForm.setId(plan.getId());
        requestMoneyForm.setRequestedBy(user.getUserName());
        requestMoneyForm.setRequestedFrom(plan.getCustomer());
        requestMoneyForm.setMasterKey(plan.getMasterKey());
        requestMoneyForm.setAvailAmount(plan.getBalance());


        model.put("user", user);
        model.put("requestMoneyForm", requestMoneyForm);

        return new ModelAndView("subsRequestMoney", "model", model);
    }

    @RequestMapping(value = "/subsRequestMoneyConfirm", method = RequestMethod.POST)
    public ModelAndView subsRequestMoneyConfirm(
            @ModelAttribute RequestMoneyForm requestMoneyForm, ModelMap model,
            RedirectAttributes attributes) {

        EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

        if (requestMoneyForm.getAmount().compareTo(requestMoneyForm.getAvailAmount()) > 0) {
            Long Id = requestMoneyForm.getId();
            attributes.addFlashAttribute("success", "Amount Should Not Exceed Available Amount");
            return new ModelAndView("redirect:subsRequestMoney?id=" + Id + "");
        }

        requestMoneyForm.setRequestedBy(requestMoneyForm.getRequestedBy());
        requestMoneyForm.setRequestedFrom(requestMoneyForm.getRequestedFrom());
        requestMoneyForm.setMasterKey(requestMoneyForm.getMasterKey());
        requestMoneyForm.setAmount(requestMoneyForm.getAmount());

        model.put("user", user);
        model.put("requestMoneyForm", requestMoneyForm);

        return new ModelAndView("subsRequestMoneyConfirm", "model", model);
    }

    @RequestMapping(value = "/subsRequestMoneyPost", method = RequestMethod.POST)
    public ModelAndView requestMoneyPost(
            @ModelAttribute RequestMoneyForm requestMoneyForm, ModelMap model,
            RedirectAttributes attributes) {

        EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();
        
        requestMoneyForm.setTransactionId(KeyGenerator.generateTransactionKey());

        RequestMoney money = new RequestMoney();

        money.setRequestedBy(requestMoneyForm.getRequestedBy());
        money.setRequestedFrom(requestMoneyForm.getRequestedFrom());
        money.setMasterKey(requestMoneyForm.getMasterKey());
        money.setAmount(requestMoneyForm.getAmount());
        money.setTransactionId(requestMoneyForm.getTransactionId());

        Date reqDate = DateService.loginDate;
        money.setRequestDate(reqDate);

        requestMoneyDAO.insertRequest(money);

        Transaction trans = new Transaction();

        trans.setTransactionId(requestMoneyForm.getTransactionId());
        trans.setTransactionType("Request For Money");
        trans.setTransactionStatus("Submitted successfully");
        transcationDAOImpl.insertTransaction(trans);

        model.put("user", user);
        model.put("requestMoneyForm", requestMoneyForm);

        return new ModelAndView("subsRequestMoneyTransaction", "model", model);
    }

    @RequestMapping(value = "/subsRequestMoneyTransaction", method = RequestMethod.GET)
    public ModelAndView requestMoneyTransaction(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        model.put("user", user);
        model.put("requestMoneyForm", requestMoneyForm);

        return new ModelAndView("subsRequestMoneyTransaction", "model", model);

    }

    @RequestMapping(value = "/viewSubsRequestSent", method = RequestMethod.GET)
    public ModelAndView viewRequestSent(ModelMap model) {


        EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();


        List<RequestMoney> requestList = requestMoneyDAO.getRequestSentList(user.getUserName()).getResultList();

        if (requestList != null && requestList.size() > 0) {
            model.put("user", user);
            model.put("requestList", requestList);
        }
        return new ModelAndView("viewSubsRequestSent", "model", model);
    }

    @RequestMapping(value = "/viewSubsRequestRecieved", method = RequestMethod.GET)
    public ModelAndView viewRequestRecieved(ModelMap model) {


        EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();


        List<RequestMoney> requestList = requestMoneyDAO.getRequestRecievedList(user.getUserName()).getResultList();

        if (requestList != null && requestList.size() > 0) {
            model.put("user", user);
            model.put("requestList", requestList);
        }
        return new ModelAndView("viewSubsRequestRecieved", "model", model);
    }


    @RequestMapping(value = "/subsFundsTransfer", method = RequestMethod.GET)
    public ModelAndView branchFundsTransfer(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        List<FundsDistribute> masterList = fundsDistributeDAO.getFundsListByCustomerName(user.getUserName()).getResultList();

        {
            model.put("masterList", masterList);
            model.put("user", user);
        }
        return new ModelAndView("subsFundsTransfer", "model", model);

    }

    @RequestMapping(value = "/subsFundsTransfer1", method = RequestMethod.GET)
    public ModelAndView branchFundsTransfer1(ModelMap model, @RequestParam("id") Long id) {

        EndUser user = getCurrentLoggedUserDetails();


        FundsDistribute fundsList = fundsDistributeDAO.getByFundsId(id);
        if (fundsList != null) {
            fundsDistributeForm.setId(fundsList.getId());
            fundsDistributeForm.setCustomerName(fundsList.getCustomerName());
            fundsDistributeForm.setCustomerHeadName(fundsList.getCustomerHeadName());
            fundsDistributeForm.setCustHeadEmail(fundsList.getCustHeadEmail());
            fundsDistributeForm.setCustHeadMngEmail(fundsList.getCustHeadMngEmail());
            fundsDistributeForm.setEmail(fundsList.getEmail());
            fundsDistributeForm.setManagerEmail(fundsList.getManagerEmail());
            fundsDistributeForm.setBusBalance(fundsList.getBusBalance());
            fundsDistributeForm.setUtilizedAmount(fundsList.getUtilizedAmount());
            fundsDistributeForm.setDistributedAmount(fundsList.getDistributedAmount());
            fundsDistributeForm.setMasterKey(fundsList.getMasterKey());

            MasterPlan plan = masterPlanDAO.getMasterPlanByMasterKey(fundsDistributeForm.getMasterKey()).getSingleResult();
            if (plan != null) {

                fundsDistributeForm.setBalance(plan.getBalance());
            }
            model.put("fundsDistributeForm", fundsDistributeForm);
        }
        model.put("user", user);

        return new ModelAndView("subsFundsTransfer1", "model", model);

    }


    @RequestMapping(value = "/subsFundsTransfer1Confirm", method = RequestMethod.POST)
    public ModelAndView branchFundsTransfer1Confirm(
            @ModelAttribute FundsDistributeForm fundsDistributeForm, ModelMap model,
            RedirectAttributes attributes) {

        EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

        if (fundsDistributeForm.getAmount().compareTo(fundsDistributeForm.getBusBalance()) > 0) {
            Long Id = fundsDistributeForm.getId();
            attributes.addFlashAttribute("success", "Amount Should Not Exceed Blance");
            return new ModelAndView("redirect:subsFundsTransfer1?id=" + Id + "");
        }


        fundsDistributeForm.setCustomerName(fundsDistributeForm.getCustomerName());
        fundsDistributeForm.setCustomerHeadName(fundsDistributeForm.getCustomerHeadName());
        fundsDistributeForm.setCustHeadEmail(fundsDistributeForm.getCustHeadEmail());
        fundsDistributeForm.setCustHeadMngEmail(fundsDistributeForm.getCustHeadMngEmail());
        fundsDistributeForm.setEmail(fundsDistributeForm.getEmail());
        fundsDistributeForm.setManagerEmail(fundsDistributeForm.getManagerEmail());
        fundsDistributeForm.setBusBalance(fundsDistributeForm.getBusBalance());
        fundsDistributeForm.setUtilizedAmount(fundsDistributeForm.getUtilizedAmount());
        fundsDistributeForm.setDistributedAmount(fundsDistributeForm.getDistributedAmount());
        fundsDistributeForm.setAmount(fundsDistributeForm.getAmount());
        fundsDistributeForm.setMasterKey(fundsDistributeForm.getMasterKey());
        fundsDistributeForm.setBalance(fundsDistributeForm.getBalance());

        model.put("user", user);
        model.put("fundsDistributeForm", fundsDistributeForm);

        return new ModelAndView("subsFundsTransfer1Confirm", "model", model);
    }


    @RequestMapping(value = "/subsFundsTransferPost", method = RequestMethod.POST)
    public ModelAndView masterPlanFundsDistributePost(ModelMap model,
                                                      @ModelAttribute FundsDistributeForm fundsDistributeForm,
                                                      RedirectAttributes attributes) {

        EndUser user = getCurrentLoggedUserDetails();
        
        fundsDistributeForm.setTransactionId(KeyGenerator.generateTransactionKey());
        
        FundsStatement statement = new FundsStatement();


        String bankName = ((ArrayList<CustomerBankDetails>) customerBankDetailsDAO.getList()).get(0).getBankName();


        fundsDistributeForm.setCustomerName(fundsDistributeForm.getCustomerName());
        fundsDistributeForm.setCustomerHeadName(fundsDistributeForm.getCustomerHeadName());
        fundsDistributeForm.setCustHeadEmail(fundsDistributeForm.getCustHeadEmail());
        fundsDistributeForm.setCustHeadMngEmail(fundsDistributeForm.getCustHeadMngEmail());
        fundsDistributeForm.setEmail(fundsDistributeForm.getEmail());
        fundsDistributeForm.setManagerEmail(fundsDistributeForm.getManagerEmail());
        fundsDistributeForm.setBusBalance(fundsDistributeForm.getBusBalance());
        fundsDistributeForm.setUtilizedAmount(fundsDistributeForm.getUtilizedAmount());
        fundsDistributeForm.setDistributedAmount(fundsDistributeForm.getDistributedAmount());
        fundsDistributeForm.setAmount(fundsDistributeForm.getAmount());
        fundsDistributeForm.setMasterKey(fundsDistributeForm.getMasterKey());
        fundsDistributeForm.setBalance(fundsDistributeForm.getBalance());

        Float amt = fundsDistributeForm.getAmount();
        Float bal = fundsDistributeForm.getBusBalance();
        Float bal1 = fundsDistributeForm.getBalance();

        Float totalbal = bal - amt;
        Float totalbal1 = bal1 + amt;
        FundsDistribute funds = fundsDistributeDAO.getFundsList(fundsDistributeForm.getMasterKey(), fundsDistributeForm.getCustomerName()).getSingleResult();
        if (funds != null) {
            funds.setBusBalance(totalbal);
            funds.setTransactionId(fundsDistributeForm.getTransactionId());
            fundsDistributeDAO.updateFunds(funds);
        }
        MasterPlan planList = masterPlanDAO.getMasterPlanByMasterKey(fundsDistributeForm.getMasterKey()).getSingleResult();
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
        emails.setText("Hello " + customerName +
                ",\n\n Funds Transferred Successfully "
                + "\n" + "\n"

                + "\n\nMaterKey:" + masterKey +
                "\n\nAvailabe Amount:" + availabeamount +

                "\n\nAmount:" + amount +

                "\n\n\nRegards,\n" + bankName);
        System.out.println("" + email + customerName);
        mailSender.send(emails);
        SimpleMailMessage emails1 = new SimpleMailMessage();
        emails1.setTo(email);


        String tex1 = "Fund Transfer Details Notification";
        SimpleMailMessage emails2 = new SimpleMailMessage();
        emails2.setTo(custheademail);
        emails2.setSubject(tex1);
        emails2.setText("Hello " + customerName +
                ",\n\n Funds Transferred Successfully "
                + "\n" + "\n"

                + "\n\nMaterKey:" + masterKey +
                "\n\nAvailabe Amount:" + availabeamount +

                "\n\nAmount:" + amount +

                "\n\n\nRegards,\n" + bankName);
        System.out.println("" + custheademail + customerName);
        mailSender.send(emails2);
        SimpleMailMessage email2 = new SimpleMailMessage();
        email2.setTo(custheademail);


        String tex2 = "Fund Transfer Details Notification";
        SimpleMailMessage emails3 = new SimpleMailMessage();
        emails3.setTo(managermail);
        emails3.setSubject(tex2);
        emails3.setText("Hello " + customerName +
                ",\n\n Funds Transferred Successfully "
                + "\n" + "\n"

                + "\n\nMaterKey:" + masterKey +
                "\n\nAvailabe Amount:" + availabeamount +

                "\n\nAmount:" + amount +

                "\n\n\nRegards,\n" + bankName);
        System.out.println("" + managermail + customerName);
        mailSender.send(emails3);
        SimpleMailMessage email3 = new SimpleMailMessage();
        email3.setTo(managermail);

        String tex3 = "Fund Transfer Details Notification";
        SimpleMailMessage emails4 = new SimpleMailMessage();
        emails4.setTo(custheadmngEmail);
        emails4.setSubject(tex3);
        emails4.setText("Hello " + customerName +
                ",\n\n Funds Transferred Successfully "
                + "\n" + "\n"

                + "\n\nMaterKey:" + masterKey +
                "\n\nAvailabe Amount:" + availabeamount +

                "\n\nAmount:" + amount +

                "\n\n\nRegards,\n" + bankName);
        System.out.println("" + custheadmngEmail + customerName);
        mailSender.send(emails4);
        SimpleMailMessage email4 = new SimpleMailMessage();
        email4.setTo(custheadmngEmail);


        model.put("user", user);
        model.put("fundsDistributeForm", fundsDistributeForm);

        return new ModelAndView("subsFundsTransferTransaction", "model", model);

    }

    @RequestMapping(value = "/subsFundsTransferTransaction", method = RequestMethod.GET)
    public ModelAndView FundsDistributeTransaction(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        model.put("user", user);
        model.put("fundsDistributeForm", fundsDistributeForm);

        return new ModelAndView("subsFundsTransferTransaction", "model", model);

    }

    @RequestMapping(value = "/fullSubsFundsDistributeStatement", method = RequestMethod.GET)
    public ModelAndView fullFundsStatement(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        List<FundsStatement> statement = fundsStatementDAO.getStatementList(user.getUserName()).getResultList();

        if (statement != null && statement.size() > 0) {
            model.put("statement", statement);
            model.put("user", user);
        }
        return new ModelAndView("fullSubsFundsDistributeStatement", "model", model);

    }

    @RequestMapping(value = "/subsBuyerPoList", method = RequestMethod.GET)
    public ModelAndView buyerPoList(ModelMap model) {


        EndUser user = endUserDAOImpl.findByUsername(getCurrentLoggedUserName()).getSingleResult();

        List<BuyerPO> buyerList = buyerPODAO.getBuyerPOByHeadName(user.getUserName()).getResultList();
        if (buyerList != null && buyerList.size() > 0) {
            model.put("buyerList", buyerList);
            model.put("user", user);
        }
        return new ModelAndView("subsBuyerPoList", "model", model);
    }

    @RequestMapping(value = "/subsShowMail", method = RequestMethod.GET)
    public ModelAndView doSendEmail1(@ModelAttribute ModelMap model) {
        EndUser user = getCurrentLoggedUserDetails();
        model.put("user", user);
        return new ModelAndView("subsQueryMail", "model", model);
    }

    @RequestMapping(value = "/subsMailSender", method = RequestMethod.POST)
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
        return new ModelAndView("subsResult", "model", model);
    }

    @RequestMapping(value = "/subsShowCurrency", method = RequestMethod.GET)
    public ModelAndView showCurrency(ModelMap model) {
        EndUser user = getCurrentLoggedUserDetails();
        model.put("user", user);
        return new ModelAndView("subsCurrency", "model", model);
    }

    @RequestMapping(value = "/subsShowcurrencyConversion", method = RequestMethod.GET)
    public ModelAndView showcurrencyConversion(@ModelAttribute ModelMap model) {
        EndUser user = getCurrentLoggedUserDetails();
        model.put("user", user);
        return new ModelAndView("subsShowcurrencyConversion", "model", model);
    }


    @RequestMapping(value = "/poPaymentSubsList", method = RequestMethod.GET)
    public ModelAndView poPaymentHeadList(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        List<PurchaseOrder> purchaseList = purchaseOrderDAO.getPoListForPayment(user.getUserName()).getResultList();

        if (purchaseList != null && purchaseList.size() > 0) {
            model.put("purchaseList", purchaseList);
            model.put("user", user);
        }
        return new ModelAndView("poPaymentSubsList", "model", model);

    }

    @RequestMapping(value = "/poPaymentSubs", method = RequestMethod.GET)
    public ModelAndView poPaymentHead(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

        EndUser user = getCurrentLoggedUserDetails();

        PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(id);

        purchaseOrderForm.setId(purchase.getId());
        purchaseOrderForm.setSupplierName(purchase.getSupplierName());
        purchaseOrderForm.setCustomerHeadName(purchase.getCustomerHeadName());
        purchaseOrderForm.setCustomerName(purchase.getCustomerName());
        purchaseOrderForm.setPoKey(purchase.getPoKey());
        purchaseOrderForm.setSupplierEmail(purchase.getSupplierEmail());
        purchaseOrderForm.setCustomerHeadEmail(purchase.getCustomerHeadEmail());
        purchaseOrderForm.setCustomerBranchEmail(purchase.getCustomerBranchEmail());
        purchaseOrderForm.setAmount(purchase.getAmount());
        purchaseOrderForm.setTransactionId(purchase.getTransactionId());

        model.put("user", user);
        model.put("purchaseOrderForm", purchaseOrderForm);

        return new ModelAndView("poPaymentSubs", "model", model);
    }

    @RequestMapping(value = "/poPaymentSubsConfirm", method = RequestMethod.POST)
    public ModelAndView poPaymentHeadConfirm(ModelMap model, @ModelAttribute PurchaseOrderForm purchaseOrderForm, BindingResult result) {

        EndUser user = getCurrentLoggedUserDetails();

        if (purchaseOrderForm.getTypeOfTrans().equals("LC") || purchaseOrderForm.getTypeOfTrans().equals("BG")) {
            model.put("purchaseOrderForm", purchaseOrderForm);
            return new ModelAndView("poPaymentSubsLcPage", "model", model);
        } else {
            purchaseOrderForm.setId(purchaseOrderForm.getId());
            purchaseOrderForm.setSupplierName(purchaseOrderForm.getSupplierName());
            purchaseOrderForm.setCustomerHeadName(purchaseOrderForm.getCustomerHeadName());
            purchaseOrderForm.setCustomerName(purchaseOrderForm.getCustomerName());
            purchaseOrderForm.setPoKey(purchaseOrderForm.getPoKey());
            purchaseOrderForm.setSupplierEmail(purchaseOrderForm.getSupplierEmail());
            purchaseOrderForm.setCustomerHeadEmail(purchaseOrderForm.getCustomerHeadEmail());
            purchaseOrderForm.setCustomerBranchEmail(purchaseOrderForm.getCustomerBranchEmail());
            purchaseOrderForm.setAmount(purchaseOrderForm.getAmount());
            purchaseOrderForm.setTypeOfTrans(purchaseOrderForm.getTypeOfTrans());
            purchaseOrderForm.setChequenum(purchaseOrderForm.getChequenum());
            purchaseOrderForm.setTransactionId(purchaseOrderForm.getTransactionId());
            model.put("purchaseOrderForm", purchaseOrderForm);
            model.put("user", user);
        }
        return new ModelAndView("poPaymentSubsConfirm", "model", model);

    }

    @RequestMapping(value = "/poPaymentSubsSave", method = RequestMethod.POST)
    public ModelAndView poPaymentHeadSave(ModelMap model, @ModelAttribute PurchaseOrderForm purchaseOrderForm, BindingResult result) {

        EndUser user = getCurrentLoggedUserDetails();

        PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(purchaseOrderForm.getId());

        purchase.setTypeOfTrans(purchaseOrderForm.getTypeOfTrans());
        purchase.setChequenum(purchaseOrderForm.getTransStatus());
        purchase.setTransStatus("Approved");
        if (purchase.getTypeOfTrans().equals("transfer")) {
            purchase.setTransResult("Cleared");
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

        return new ModelAndView("poPaymentSubsTrans", "model", model);

    }

    @RequestMapping(value = "/poPaymentSubsTrans", method = RequestMethod.GET)
    public ModelAndView masterPlanRePaymentHalfTransaction(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        model.put("user", user);
        model.put("purchaseOrderForm", purchaseOrderForm);

        return new ModelAndView("poPaymentSubsTrans", "model", model);

    }


    @RequestMapping(value = "/poPaymentSubsLcPage", method = RequestMethod.GET)
    public ModelAndView poPaymentHeadLcPage(ModelMap model, RedirectAttributes attributes) {

        EndUser user = getCurrentLoggedUserDetails();


        letterOfCreditForm.setId(purchaseOrderForm.getId());
        letterOfCreditForm.setSupplierName(purchaseOrderForm.getSupplierName());
        letterOfCreditForm.setCustomerHeadName(purchaseOrderForm.getCustomerHeadName());
        letterOfCreditForm.setCustomerName(purchaseOrderForm.getCustomerName());
        letterOfCreditForm.setPoKey(purchaseOrderForm.getPoKey());
        letterOfCreditForm.setSupplierEmail(purchaseOrderForm.getSupplierEmail());
        letterOfCreditForm.setCustomerHeadEmail(purchaseOrderForm.getCustomerHeadEmail());
        letterOfCreditForm.setCustomerBranchEmail(purchaseOrderForm.getCustomerBranchEmail());
        letterOfCreditForm.setAmount(purchaseOrderForm.getAmount());
        /*	 letterOfCreditForm.setTypeOfTrans(purchaseOrderForm.getTypeOfTrans());*/
        letterOfCreditForm.setTransactionId(purchaseOrderForm.getTransactionId());

        model.put("user", user);
        model.put("letterOfCreditForm", letterOfCreditForm);
        model.put("purchaseOrderForm", purchaseOrderForm);

        return new ModelAndView("poPaymentSubsLcPage", "model", model);
    }

    @RequestMapping(value = "/poPaymentSubsLcPageConfirm", method = RequestMethod.POST)
    public ModelAndView poPaymentHeadLcPageConfirm(ModelMap model, @ModelAttribute LetterOfCreditForm letterOfCreditForm, BindingResult result) {

        EndUser user = getCurrentLoggedUserDetails();

        letterOfCreditForm.setId(letterOfCreditForm.getId());
        letterOfCreditForm.setSupplierName(letterOfCreditForm.getSupplierName());
        letterOfCreditForm.setCustomerHeadName(letterOfCreditForm.getCustomerHeadName());
        letterOfCreditForm.setCustomerName(letterOfCreditForm.getCustomerName());
        letterOfCreditForm.setPoKey(letterOfCreditForm.getPoKey());
        letterOfCreditForm.setSupplierEmail(letterOfCreditForm.getSupplierEmail());
        letterOfCreditForm.setCustomerHeadEmail(letterOfCreditForm.getCustomerHeadEmail());
        letterOfCreditForm.setCustomerBranchEmail(letterOfCreditForm.getCustomerBranchEmail());
        letterOfCreditForm.setAmount(letterOfCreditForm.getAmount());
        letterOfCreditForm.setTransactionId(letterOfCreditForm.getTransactionId());
        letterOfCreditForm.setTypeOfLc(letterOfCreditForm.getTypeOfLc());
        letterOfCreditForm.setBankType(letterOfCreditForm.getBankType());
        letterOfCreditForm.setBankName(letterOfCreditForm.getBankName());
        letterOfCreditForm.setBankAddress(letterOfCreditForm.getBankAddress());
        letterOfCreditForm.setBankBranch(letterOfCreditForm.getBankBranch());
        letterOfCreditForm.setSwiftCode(letterOfCreditForm.getSwiftCode());
        letterOfCreditForm.setAccNo(letterOfCreditForm.getAccNo());
        letterOfCreditForm.setContactNum(letterOfCreditForm.getContactNum());
        letterOfCreditForm.setBankNameCorr(letterOfCreditForm.getBankNameCorr());
        letterOfCreditForm.setBankBranchCorr(letterOfCreditForm.getBankBranchCorr());
        letterOfCreditForm.setBankLocCorr(letterOfCreditForm.getBankLocCorr());
        letterOfCreditForm.setSwiftCodeCorr(letterOfCreditForm.getSwiftCodeCorr());
        letterOfCreditForm.setAccNum(letterOfCreditForm.getAccNum());
        /* letterOfCreditForm.setTypeOfTrans(letterOfCreditForm.getTypeOfTrans());*/


        model.put("letterOfCreditForm", letterOfCreditForm);
        model.put("user", user);

        return new ModelAndView("poPaymentSubsLcPageConfirm", "model", model);

    }


    @RequestMapping(value = "/poPaymentSubsLcPageSave", method = RequestMethod.POST)
    public ModelAndView poPaymentHeadLcPageSave(ModelMap model, @ModelAttribute LetterOfCreditForm letterOfCreditForm, BindingResult result) {

        EndUser user = getCurrentLoggedUserDetails();

        LetterOfCredit credit = new LetterOfCredit();

        credit.setSupplierName(letterOfCreditForm.getSupplierName());
        credit.setCustomerHeadName(letterOfCreditForm.getCustomerHeadName());
        credit.setCustomerName(letterOfCreditForm.getCustomerName());
        credit.setPoKey(letterOfCreditForm.getPoKey());
        credit.setSupplierEmail(letterOfCreditForm.getSupplierEmail());
        credit.setCustomerHeadEmail(letterOfCreditForm.getCustomerHeadEmail());
        credit.setCustomerBranchEmail(letterOfCreditForm.getCustomerBranchEmail());
        credit.setAmount(letterOfCreditForm.getAmount());
        credit.setTransactionId(letterOfCreditForm.getTransactionId());
        credit.setTypeOfLc(letterOfCreditForm.getTypeOfLc());
        credit.setBankType(letterOfCreditForm.getBankType());
        credit.setBankName(letterOfCreditForm.getBankName());
        credit.setBankAddress(letterOfCreditForm.getBankAddress());
        credit.setBankBranch(letterOfCreditForm.getBankBranch());
        credit.setSwiftCode(letterOfCreditForm.getSwiftCode());
        credit.setAccNo(letterOfCreditForm.getAccNo());
        credit.setContactNum(letterOfCreditForm.getContactNum());
        /*		credit.setTypeOfTrans(letterOfCreditForm.getTypeOfTrans());*/
        credit.setTransResult("Pending");
        credit.setTransStatus("Approved");
        /*			credit.setTypeOfTrans(letterOfCreditForm.getTypeOfTrans());*/

        letterOfCreditDAO.createLetterOfCredit(credit);

        PurchaseOrder purchase = purchaseOrderDAO.getPoListByPoKey(letterOfCreditForm.getPoKey()).getSingleResult();

        purchase.setTransResult("Pending");
        purchase.setTransStatus("Approved");
        /*	purchase.setTypeOfTrans(letterOfCreditForm.getTypeOfTrans());*/

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
            letterCredit.setTransactionId(letterOfCreditForm.getTransactionId());
            letterCredit.setBankName(value.getBankNameCorr());
            letterCredit.setBankBranch(value.getBankBranchCorr());
            letterCredit.setBankLoc(value.getBankLocCorr());
            letterCredit.setSwiftCode(value.getSwiftCodeCorr());
            letterCredit.setAccNum(value.getAccNum());


            correspondentDAO.createCorrespondent(letterCredit);
        }


        model.put("letterOfCreditForm", letterOfCreditForm);
        model.put("user", user);

        return new ModelAndView("poPaymentSubsLcTrans", "model", model);

    }

    @RequestMapping(value = "/poPaymentSubsLcTrans", method = RequestMethod.GET)
    public ModelAndView poPaymentHeadLcTrans(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        model.put("user", user);
        model.put("letterOfCreditForm", letterOfCreditForm);

        return new ModelAndView("poPaymentSubsLcTrans", "model", model);

    }

    @RequestMapping(value = "/poPaymentSubsFullList", method = RequestMethod.GET)
    public ModelAndView poPaymentBankList(ModelMap model, RedirectAttributes attributes) {

        EndUser user = getCurrentLoggedUserDetails();

        List<PurchaseOrder> purchaseList = purchaseOrderDAO.getPoListByBranchName(user.getUserName()).getResultList();
        if (purchaseList != null && purchaseList.size() > 0) {
            model.put("user", user);
            model.put("purchaseList", purchaseList);
        }
        return new ModelAndView("poPaymentSubsFullList");

    }

    @RequestMapping(value = "/poPaymentSubsClear", method = RequestMethod.GET)
    public ModelAndView poPaymentCustClear(@RequestParam Long id, ModelMap model, RedirectAttributes attributes) {

        EndUser user = getCurrentLoggedUserDetails();

        PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(id);

        List<LetterOfCredit> letter = letterOfCreditDAO.getLCListByPoKey(purchase.getPoKey()).getResultList();
        if (letter != null && letter.size() > 0) {


            letterOfCreditForm.setSupplierName(letter.get(0).getSupplierName());
            letterOfCreditForm.setCustomerHeadName(letter.get(0).getCustomerHeadName());
            letterOfCreditForm.setCustomerName(letter.get(0).getCustomerName());
            letterOfCreditForm.setPoKey(letter.get(0).getPoKey());
            letterOfCreditForm.setSupplierEmail(letter.get(0).getSupplierEmail());
            letterOfCreditForm.setCustomerHeadEmail(letter.get(0).getCustomerHeadEmail());
            letterOfCreditForm.setCustomerBranchEmail(letter.get(0).getCustomerBranchEmail());
            letterOfCreditForm.setAmount(letter.get(0).getAmount());
            letterOfCreditForm.setTransactionId(letter.get(0).getTransactionId());
            letterOfCreditForm.setTypeOfLc(letter.get(0).getTypeOfLc());
            letterOfCreditForm.setBankType(letter.get(0).getBankType());
            letterOfCreditForm.setBankName(letter.get(0).getBankName());
            letterOfCreditForm.setBankAddress(letter.get(0).getBankAddress());
            letterOfCreditForm.setBankBranch(letter.get(0).getBankBranch());
            letterOfCreditForm.setSwiftCode(letter.get(0).getSwiftCode());
            letterOfCreditForm.setAccNo(letter.get(0).getAccNo());
            letterOfCreditForm.setContactNum(letter.get(0).getContactNum());

            List<Correspondent> corrList = correspondentDAO.getCorrespondenttByPoKey(purchase.getPoKey()).getResultList();

            if (corrList != null && corrList.size() > 0) {
                model.put("corrList", corrList);
            }

            model.put("user", user);
            model.put("letterOfCreditForm", letterOfCreditForm);
        } else {
            attributes.addFlashAttribute("success", "Please Check Tpe Of Transfer ");
            return new ModelAndView("redirect:poPaymentSubsFullList");
        }
        return new ModelAndView("poPaymentSubsClear", "model", model);

    }


    @RequestMapping(value = "/customerSubsidiaryPODcumentList	", method = RequestMethod.GET)
    public ModelAndView POdocumentListCustomer(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        List<PurchaseOrder> poList = purchaseOrderDAO.getPoListBycustomerNameAndStatus(user.getUserName()).getResultList();

        if (poList != null && poList.size() > 0) {
            purchaseOrderForm.setId(poList.get(0).getId());
            model.put("user", user);
            model.put("poList", poList);
            model.put("purchaseOrderForm", purchaseOrderForm);
        }
        return new ModelAndView("customerSubsidiaryPODcumentList", "model", model);

    }


    @RequestMapping(value = "/closePoSubsList", method = RequestMethod.GET)
    public ModelAndView closePoList(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        List<PurchaseOrder> purchaseList = purchaseOrderDAO.getPoPaymentCleared(user.getUserName()).getResultList();

        if (purchaseList != null && purchaseList.size() > 0) {
            model.put("purchaseList", purchaseList);
            model.put("user", user);
        }
        return new ModelAndView("closePoSubsList", "model", model);

    }

    @RequestMapping(value = "/closePoSubs", method = RequestMethod.GET)
    public String closePo(@RequestParam Long id, ModelMap model,
                          RedirectAttributes attributes) {

        EndUser user = getCurrentLoggedUserDetails();

        PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(id);

        if (purchase != null) {

            purchase.setStatus("Closed");
            purchase.setvStatus("Closed");
            purchase.setTransStatus("Closed");
            purchase.setTransResult("Closed");
            purchaseOrderDAO.updatePo(purchase);
        }
        attributes.addFlashAttribute("success", "Closed Successfully");
        model.put("user", user);

        return "redirect:closePoSubsList";
    }


    @RequestMapping(value = "/cancelPoSubsList", method = RequestMethod.GET)
    public ModelAndView cancelPoList(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        List<PurchaseOrder> purchaseList = purchaseOrderDAO.getPoListForPayment(user.getUserName()).getResultList();

        if (purchaseList != null && purchaseList.size() > 0) {
            model.put("purchaseList", purchaseList);
            model.put("user", user);
        }
        return new ModelAndView("cancelPoSubsList", "model", model);

    }

    @RequestMapping(value = "/cancelPoSubs", method = RequestMethod.GET)
    public String cancelPo(@RequestParam Long id, ModelMap model,
                           RedirectAttributes attributes) {

        EndUser user = getCurrentLoggedUserDetails();

        PurchaseOrder purchase = purchaseOrderDAO.getByPurchaseId(id);

        if (purchase != null) {

            purchase.setStatus("Cancelled");
            purchase.setvStatus("Cancelled");
            purchase.setTransStatus("Cancelled");
            purchase.setTransResult("Cancelled");
            purchaseOrderDAO.updatePo(purchase);

        }
        purchaseOrderForm.setStatus(purchase.getStatus());
        purchaseOrderForm.setFlag(purchase.getFlag());
        purchaseOrderForm.setMasterKey(purchase.getMasterKey());
        purchaseOrderForm.setCustomerName(purchase.getCustomerName());
        purchaseOrderForm.setAmount(purchase.getAmount());

        if (purchaseOrderForm.getFlag().equals(0)) {
            if (purchaseOrderForm.getStatus().equals("Cancelled")) {
                MasterPlan plan = masterPlanDAO.getMasterPlanByMasterKey(purchaseOrderForm.getMasterKey()).getSingleResult();
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
            if (purchaseOrderForm.getStatus().equals("Cancelled")) {
                FundsDistribute funds = fundsDistributeDAO.getFundsListByKeyAndName(purchaseOrderForm.getMasterKey(), purchaseOrderForm.getCustomerName()).getSingleResult();
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
        attributes.addFlashAttribute("success", "Cancelled Successfully");
        model.put("user", user);

        return "redirect:cancelPoSubsList";
    }

    @RequestMapping(value = "/closeinvoiceSubsList", method = RequestMethod.GET)
    public ModelAndView closeinvoiceList(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        List<Invoice> invoiceList = invoiceDAO.getInvoiceListForClosing(user.getUserName()).getResultList();

        if (invoiceList != null && invoiceList.size() > 0) {
            model.put("invoiceList", invoiceList);
            model.put("user", user);
        }
        return new ModelAndView("closeinvoiceSubsList", "model", model);

    }

    @RequestMapping(value = "/closeInvoiceSubs", method = RequestMethod.GET)
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

        return "redirect:closeinvoiceSubsList";
    }

    @RequestMapping(value = "/disputeSubsList", method = RequestMethod.GET)
    public ModelAndView disputeHeadList(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        List<PurchaseOrder> purchaseList = purchaseOrderDAO.getPoBycustomerNameAndStatus(user.getUserName()).getResultList();

        if (purchaseList != null && purchaseList.size() > 0) {
            model.put("purchaseList", purchaseList);
            model.put("user", user);
        }
        return new ModelAndView("disputeSubsList", "model", model);

    }

    @RequestMapping(value = "/disputeSubs", method = RequestMethod.GET)
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
        disputeForm.setTotalCost(purchase.getAmount());
        disputeForm.setTransactionId(purchase.getTransactionId());


        attributes.addFlashAttribute("success", "Closed Successfully");
        model.put("user", user);
        model.put("disputeForm", disputeForm);

        return new ModelAndView("disputeSubs", "model", model);
    }


    @RequestMapping(value = "/disputeSubsConfirm", method = RequestMethod.POST)
    public ModelAndView disputeHeadConfirm(ModelMap model, @ModelAttribute DisputeForm disputeForm, BindingResult result) {

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
        disputeForm.setGoods(disputeForm.getGoods());
        disputeForm.setGoodsInfo(disputeForm.getGoodsInfo());
        disputeForm.setTotalCost(disputeForm.getTotalCost());
        disputeForm.setQuantity(disputeForm.getQuantity());
        disputeForm.setAnswer(disputeForm.getAnswer());
        disputeForm.setGoodsDefect(disputeForm.getGoodsDefect());
        disputeForm.setNoOfDefect(disputeForm.getNoOfDefect());
        disputeForm.setAnswer1(disputeForm.getAnswer1());
        disputeForm.setAnswer2(disputeForm.getAnswer2());
        disputeForm.setTransactionId(disputeForm.getTransactionId());

        model.put("disputeForm", disputeForm);
        model.put("user", user);

        return new ModelAndView("disputeSubsConfirm", "model", model);

    }

    @RequestMapping(value = "/disputeSubsPost", method = RequestMethod.POST)
    public ModelAndView disputeHeadPost(ModelMap model, @ModelAttribute DisputeForm disputeForm, BindingResult result) {

        EndUser user = getCurrentLoggedUserDetails();

        Dispute dispute = new Dispute();

        dispute.setCustomerName(disputeForm.getCustomerName());
        dispute.setCustomerEmail(disputeForm.getCustomerEmail());
        dispute.setMasterKey(disputeForm.getMasterKey());
        dispute.setPoKey(disputeForm.getPokey());
        dispute.setSupplierName(disputeForm.getSupplierName());
        dispute.setSupplierEmail(disputeForm.getSupplierEmail());
        dispute.setGoods(disputeForm.getGoods());
        dispute.setTotalCost(disputeForm.getTotalCost());
        dispute.setGoods(disputeForm.getGoods());
        dispute.setGoodsInfo(disputeForm.getGoodsInfo());
        dispute.setTotalCost(disputeForm.getTotalCost());
        dispute.setQuantity(disputeForm.getQuantity());
        dispute.setAnswer(disputeForm.getAnswer());
        dispute.setGoodsDefect(disputeForm.getGoodsDefect());
        dispute.setNoOfDefect(disputeForm.getNoOfDefect());
        dispute.setAnswer1(disputeForm.getAnswer1());
        dispute.setAnswer2(disputeForm.getAnswer2());
        dispute.setDisputeKey(disputeForm.getDisputeKey());

        disputeDAO.createDispute(dispute);

        Transaction trans = new Transaction();
        trans.setTransactionId(disputeForm.getTransactionId());
        trans.setTransactionStatus("Dispute");
        trans.setTransactionType("Submitted Successfully");

        model.put("disputeForm", disputeForm);
        model.put("user", user);

        return new ModelAndView("disputeSubsTrans", "model", model);

    }

    @RequestMapping(value = "/disputeSubsTrans", method = RequestMethod.GET)
    public ModelAndView disputeHeadTrans(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        model.put("user", user);
        model.put("disputeForm", disputeForm);

        return new ModelAndView("disputeSubsTrans", "model", model);

    }

    @RequestMapping(value = "/disputeSubsUpdateList", method = RequestMethod.GET)
    public ModelAndView disputeHeadUpdateList(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        List<Dispute> disputeList = disputeDAO.getDisputeList(user.getUserName()).getResultList();

        if (disputeList != null && disputeList.size() > 0) {
            model.put("disputeList", disputeList);
            model.put("user", user);
        }
        return new ModelAndView("disputeSubsUpdateList", "model", model);

    }

    @RequestMapping(value = "/disputeSubsUpdate", method = RequestMethod.GET)
    public ModelAndView disputeHeadUpdate(@RequestParam Long id, ModelMap model,
                                          RedirectAttributes attributes) {

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

        return new ModelAndView("disputeSubsUpdate", "model", model);
    }

    @RequestMapping(value = "/disputeSubsUpdateConfirm", method = RequestMethod.POST)
    public ModelAndView disputeHeadUpdateConfirm(ModelMap model, @ModelAttribute DisputeForm disputeForm, BindingResult result) {

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

        return new ModelAndView("disputeSubsUpdateConfirm", "model", model);

    }

    @RequestMapping(value = "/disputeSubsUpdatePost", method = RequestMethod.POST)
    public ModelAndView disputeHeadUpdatePost(ModelMap model, @ModelAttribute DisputeForm disputeForm, BindingResult result) {

        EndUser user = getCurrentLoggedUserDetails();

        Dispute dispute = disputeDAO.getByDisputeId(disputeForm.getId());

        disputeForm.setSupplierEmail(disputeForm.getSupplierEmail());
        ;
        disputeForm.setTransactionId(disputeForm.getTransactionId());
        dispute.setDisputeStatus(disputeForm.getDisputeStatus());
        dispute.setComment(disputeForm.getComment());

        disputeDAO.updateDispute(dispute);

        Transaction trans = new Transaction();
        trans.setTransactionId(disputeForm.getTransactionId());
        trans.setTransactionStatus("Dispute");
        trans.setTransactionType("Submitted Successfully");

        model.put("disputeForm", disputeForm);
        model.put("user", user);

        return new ModelAndView("disputeSubsUpdateTrans", "model", model);

    }

    @RequestMapping(value = "/disputeSubsUpdateTrans", method = RequestMethod.GET)
    public ModelAndView disputeHeadUpdateTrans(ModelMap model) {

        EndUser user = getCurrentLoggedUserDetails();

        model.put("user", user);
        model.put("disputeForm", disputeForm);

        return new ModelAndView("disputeSubsUpdateTrans", "model", model);

    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

}
