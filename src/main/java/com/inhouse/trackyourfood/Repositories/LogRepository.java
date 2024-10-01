package com.inhouse.trackyourfood.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inhouse.trackyourfood.Entities.Log;
import java.util.List;
import java.sql.Timestamp;

public interface LogRepository extends JpaRepository<Log, Long> {

    List<Log> findByUserId(long userId);

    Log findByIdAndUserId(long id, long userId);

    List<Log> findByTimestampBetween(Timestamp start, Timestamp end);

    List<Log> findByUserIdAndTimestampBetween(long userId, Timestamp start, Timestamp end);
}
