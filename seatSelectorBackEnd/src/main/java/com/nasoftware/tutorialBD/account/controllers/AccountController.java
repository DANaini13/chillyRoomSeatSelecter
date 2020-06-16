package com.nasoftware.tutorialBD.account.controllers;
import com.nasoftware.tutorialBD.account.daos.UserAccount;
import com.nasoftware.tutorialBD.account.errors.LoginResult;
import com.nasoftware.tutorialBD.account.errors.ErrorInfo;
import com.nasoftware.tutorialBD.account.errors.SetUserNameResult;
import com.nasoftware.tutorialBD.account.exceptions.AccountAlreadyExistException;
import com.nasoftware.tutorialBD.account.exceptions.AccountNotFoundException;
import com.nasoftware.tutorialBD.account.exceptions.PasswordNotMatchException;
import com.nasoftware.tutorialBD.account.services.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@Api(tags="账号相关", value="")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "登录", notes = "账号密码登录")
    @PostMapping("/login")
    LoginResult login(@RequestParam String account, @RequestParam String password) {
        try {
            String token = accountService.login(account, password);
            ErrorInfo errorInfo = new ErrorInfo(ErrorInfo.success, ErrorInfo.success_msg);
            return new LoginResult(token, errorInfo);
        }catch (AccountNotFoundException e) {
            ErrorInfo errorInfo = new ErrorInfo(ErrorInfo.account_not_found, e.getLocalizedErrorMessage());
            return new LoginResult("", errorInfo);
        }catch (PasswordNotMatchException e) {
            ErrorInfo errorInfo = new ErrorInfo(ErrorInfo.pass_not_match, e.getLocalizedErrorMessage());
            return new LoginResult("", errorInfo);
        }
    }

    @ApiOperation(value = "注册", notes = "账号密码注册")
    @PostMapping("/register")
    ErrorInfo register(@RequestParam String account, @RequestParam String password) {
        try {
            accountService.registerAccount(account, password);
            return new ErrorInfo(ErrorInfo.success, ErrorInfo.success_msg);
        } catch (AccountAlreadyExistException e) {
            return new ErrorInfo(ErrorInfo.account_already_exist, e.getLocalizedErrorMessage());
        }
    }

    @ApiOperation(value = "修改密码", notes = "使用账号密码修改密码")
    @PostMapping("/modify_pass")
    ErrorInfo modify_pass(@RequestParam String account, @RequestParam String password, @RequestParam String new_pass) {
        try {
            accountService.modifyPassword(account, password, new_pass);
            return new ErrorInfo(ErrorInfo.success, ErrorInfo.success_msg);
        } catch (AccountNotFoundException e) {
            return new ErrorInfo(ErrorInfo.account_not_found, e.getLocalizedErrorMessage());
        } catch (PasswordNotMatchException e) {
            return new ErrorInfo(ErrorInfo.pass_not_match, e.getLocalizedErrorMessage());
        }
    }
}
