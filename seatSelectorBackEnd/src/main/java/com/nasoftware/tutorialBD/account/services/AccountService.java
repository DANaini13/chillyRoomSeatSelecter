package com.nasoftware.tutorialBD.account.services;
import com.nasoftware.tutorialBD.account.daos.UserAccount;
import com.nasoftware.tutorialBD.account.exceptions.AccountAlreadyExistException;
import com.nasoftware.tutorialBD.account.exceptions.AccountNotFoundException;
import com.nasoftware.tutorialBD.account.exceptions.PasswordNotMatchException;
import com.nasoftware.tutorialBD.account.mapper.UserAccountMapper;
import com.nasoftware.tutorialBD.account.utils.EncryptionTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AccountService {
    private int[] tokenPass = {3, 5, 2, 8, 7};
    private int[] passPass = {4, 4, 5, 3, 2};
    @Autowired
    private UserAccountMapper userAccountMapper;

    public String login(String account, String password) throws AccountNotFoundException, PasswordNotMatchException {
        String pass_hash = EncryptionTools.digest(password, passPass);
        UserAccount userAccount = userAccountMapper.queryUserAccountInfoByAccount(account);
        if(userAccount == null) {
            throw new AccountNotFoundException();
        }
        if(!userAccount.getPassword_hash().equals(pass_hash)) {
            throw new PasswordNotMatchException();
        }
        //生成token
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS");
        String date = df.format(new Date());
        return EncryptionTools.digest(date + account + password, tokenPass);
    }

    public void registerAccount(String account, String password) throws AccountAlreadyExistException {

        UserAccount userAccount = userAccountMapper.queryUserAccountInfoByAccount(account);
        if(userAccount != null) {
            throw new AccountAlreadyExistException();
        }
        String pass_hash = EncryptionTools.digest(password, passPass);
        userAccountMapper.createAccount(account, pass_hash);
    }

    public void modifyPassword(String account, String password, String new_pass) throws AccountNotFoundException, PasswordNotMatchException {
        String old_pass_hash = EncryptionTools.digest(password, passPass);
        UserAccount userAccount = userAccountMapper.queryUserAccountInfoByAccount(account);
        if(userAccount == null) {
            throw new AccountNotFoundException();
        }
        if(!userAccount.getPassword_hash().equals(old_pass_hash)) {
            throw new PasswordNotMatchException();
        }
        String new_pass_hash = EncryptionTools.digest(new_pass, passPass);
        userAccountMapper.updatePassword(account, new_pass_hash);
    }
}
