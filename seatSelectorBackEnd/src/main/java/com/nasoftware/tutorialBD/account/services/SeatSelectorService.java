package com.nasoftware.tutorialBD.account.services;
import com.nasoftware.tutorialBD.account.daos.SeatUnit;
import com.nasoftware.tutorialBD.account.exceptions.AccountNotFoundException;
import com.nasoftware.tutorialBD.account.exceptions.OperationTimeNotValidException;
import com.nasoftware.tutorialBD.account.exceptions.SeatAlreadyTokenException;
import com.nasoftware.tutorialBD.account.mapper.SeatsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatSelectorService {
    private int[] tokenPass = {3, 5, 2, 8, 7};
    private boolean tableDirty = true;
    @Autowired
    private SeatsMapper seatsMapper;
    List<SeatUnit> cacheTable = new ArrayList<SeatUnit>();

    public int checkUserName(String name) throws AccountNotFoundException {
        SeatUnit seatUnit = seatsMapper.queryName(name);
        if(seatUnit == null) {
            throw new AccountNotFoundException();
        }
        return seatUnit.getId();
    }

    public void setSeatById(int id, int seat) throws SeatAlreadyTokenException, OperationTimeNotValidException {
        //可用时间检查
        SeatUnit seatUnit = seatsMapper.queryId(id);
        if(seatUnit.getStart_time() == 0) {
            //永远都被允许的值
        }else if(seatUnit.getStart_time() == -1) {
            //永远都不被允许的值
            throw new OperationTimeNotValidException();
        }else {
            //判断可用时间
            long now = System.currentTimeMillis()/1000L;
            if(seatUnit.getStart_time() > now || seatUnit.getEnd_time() < now) {
                throw  new OperationTimeNotValidException();
            }
        }

        if(seat == -1) {
            seatsMapper.updateSeatById(id, seat);
            tableDirty = true;
            return;
        }
        seatUnit = seatsMapper.queryBySeatId(seat);
        if(seatUnit != null) {
            throw new SeatAlreadyTokenException();
        }
        //入座
        seatsMapper.updateSeatById(id, seat);
        //记录有脏数据未同步
        tableDirty = true;
    }

    public List<SeatUnit> getAllUserState()
    {
        if(tableDirty) {
            cacheTable = seatsMapper.queryAll();
            tableDirty = false;
        }
        return cacheTable;
    }
}
