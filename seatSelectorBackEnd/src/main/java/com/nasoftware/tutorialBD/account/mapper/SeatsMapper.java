package com.nasoftware.tutorialBD.account.mapper;

import com.nasoftware.tutorialBD.account.daos.SeatUnit;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SeatsMapper {
    @Select("SELECT * FROM crusers " +
            "WHERE name = #{name}")
    SeatUnit queryName(@Param(value = "name") String name);

    @Select("SELECT * FROM crusers " +
            "WHERE seat = #{seat}")
    SeatUnit queryBySeatId(@Param(value = "seat") int seat);

    @Select("UPDATE crusers SET seat = #{seat} " +
            "WHERE id = #{id}")
    void updateSeatById(@Param(value = "id") int id, @Param(value = "seat") int seat);

    @Select("SELECT * FROM crusers " +
            "WHERE id = #{id}")
    SeatUnit queryId(@Param(value = "id") int id);

    @Select("SELECT * FROM crusers")
    List<SeatUnit> queryAll();
}
