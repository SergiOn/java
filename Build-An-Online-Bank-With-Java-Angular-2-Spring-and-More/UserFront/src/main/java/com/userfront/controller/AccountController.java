package com.userfront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
	
//	private final UserService userService;
//	private final AccountService accountService;
//	private final TransactionService transactionService;
//
//    public AccountController(UserService userService, AccountService accountService, TransactionService transactionService) {
//        this.userService = userService;
//        this.accountService = accountService;
//        this.transactionService = transactionService;
//    }
//
//    @RequestMapping("/primaryAccount")
//	public String primaryAccount(Model model, Principal principal) {
//		List<PrimaryTransaction> primaryTransactionList = transactionService.findPrimaryTransactionList(principal.getName());
//
//		User user = userService.findByUsername(principal.getName());
//        PrimaryAccount primaryAccount = user.getPrimaryAccount();
//
//        model.addAttribute("primaryAccount", primaryAccount);
//        model.addAttribute("primaryTransactionList", primaryTransactionList);
//
//		return "primaryAccount";
//	}
//
//	@RequestMapping("/savingsAccount")
//    public String savingsAccount(Model model, Principal principal) {
//		List<SavingsTransaction> savingsTransactionList = transactionService.findSavingsTransactionList(principal.getName());
//        User user = userService.findByUsername(principal.getName());
//        SavingsAccount savingsAccount = user.getSavingsAccount();
//
//        model.addAttribute("savingsAccount", savingsAccount);
//        model.addAttribute("savingsTransactionList", savingsTransactionList);
//
//        return "savingsAccount";
//    }
//
//	@RequestMapping(value = "/deposit", method = RequestMethod.GET)
//    public String deposit(Model model) {
//        model.addAttribute("accountType", "");
//        model.addAttribute("amount", "");
//
//        return "deposit";
//    }
//
//    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
//    public String depositPOST(@ModelAttribute("amount") String amount, @ModelAttribute("accountType") String accountType, Principal principal) {
//        accountService.deposit(accountType, Double.parseDouble(amount), principal);
//
//        return "redirect:/userFront";
//    }
//
//    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
//    public String withdraw(Model model) {
//        model.addAttribute("accountType", "");
//        model.addAttribute("amount", "");
//
//        return "withdraw";
//    }
//
//    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
//    public String withdrawPOST(@ModelAttribute("amount") String amount, @ModelAttribute("accountType") String accountType, Principal principal) {
//        accountService.withdraw(accountType, Double.parseDouble(amount), principal);
//
//        return "redirect:/userFront";
//    }
}
