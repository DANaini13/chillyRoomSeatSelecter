package com.nasoftware.tutorialBD.account.mapper;
import com.nasoftware.tutorialBD.account.daos.UserAccount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface UserAccountMapper {

    @Insert("INSERT INTO accounts " +
            "(account, password_hash) " +
            "VALUES " +
            "(#{account}, #{password_hash})")
    void createAccount(@Param(value = "account") String account,
                             @Param(value = "password_hash") String password_hash);

    @Select("SELECT * FROM accounts " +
            "WHERE account=#{account}")
    UserAccount queryUserAccountInfoByAccount(@Param(value = "account") String account);

    @Select("UPDATE accounts SET password_hash = #{new_pass}" +
            "WHERE account = #{account}")
    void updatePassword(@Param(value = "new_pass") String new_pass,
                        @Param(value = "account") String account);

}
