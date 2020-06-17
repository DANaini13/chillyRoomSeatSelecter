package com.nasoftware.tutorialBD.account.controllers;
import com.nasoftware.tutorialBD.account.daos.SeatUnit;
import com.nasoftware.tutorialBD.account.errors.ErrorInfo;
import com.nasoftware.tutorialBD.account.errors.LoginResult;
import com.nasoftware.tutorialBD.account.errors.SetUserNameResult;
import com.nasoftware.tutorialBD.account.exceptions.AccountNotFoundException;
import com.nasoftware.tutorialBD.account.exceptions.OperationTimeNotValidException;
import com.nasoftware.tutorialBD.account.exceptions.PasswordNotMatchException;
import com.nasoftware.tutorialBD.account.exceptions.SeatAlreadyTokenException;
import com.nasoftware.tutorialBD.account.services.SeatSelectorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seats")
@Api(tags="选座位", value="")
public class SeatSelectorController {

    @Autowired
    private SeatSelectorService seatSelectorService = null;

    @ApiOperation(value = "校验用户名", notes = "使用姓名")
    @PostMapping("/checkName")
    SetUserNameResult checkName(@RequestParam String name) {
        try {
            int id = seatSelectorService.checkUserName(name);
            ErrorInfo errorInfo = new ErrorInfo(ErrorInfo.success, ErrorInfo.success_msg);
            return new SetUserNameResult(errorInfo, id);
        } catch (AccountNotFoundException e) {
            ErrorInfo errorInfo = new ErrorInfo(ErrorInfo.account_not_found, e.getLocalizedErrorMessage());
            return new SetUserNameResult(errorInfo, -1);
        }
    }

    @ApiOperation(value = "设置座位", notes = "使用token设置座位")
    @PostMapping("/setSeat")
    ErrorInfo setSeat(@RequestParam int userId, @RequestParam int seatId) {
        try {
            seatSelectorService.setSeatById(userId, seatId);
            return new ErrorInfo(ErrorInfo.success, ErrorInfo.success_msg);
        } catch (SeatAlreadyTokenException e) {
            return new ErrorInfo(ErrorInfo.seat_already_token, e.getLocalizedErrorMessage());
        } catch (OperationTimeNotValidException e) {
            return new ErrorInfo(ErrorInfo.operation_not_in_valid_period, e.getLocalizedErrorMessage());
        }
    }

    @ApiOperation(value = "查询所有的用户状态", notes = "无参数")
    @PostMapping("/queryUsers")
    List<SeatUnit> queryAllUsers() {
        return seatSelectorService.getAllUserState();
    }

    @ApiOperation(value = "获取服务器时间", notes = "无参数")
    @PostMapping("/timestamp")
    long getTimeStamp()
    {
        return System.currentTimeMillis()/1000L;
    }

}
