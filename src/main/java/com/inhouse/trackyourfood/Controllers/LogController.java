package com.inhouse.trackyourfood.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inhouse.trackyourfood.Entities.Log;
import com.inhouse.trackyourfood.Repositories.LogRepository;
import com.inhouse.trackyourfood.Types.LogInterval;
import com.inhouse.trackyourfood.Types.LogKey;
import com.inhouse.trackyourfood.Types.LogRequest;

import jakarta.validation.Valid;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "/log")
public class LogController {

    @Autowired
    LogRepository logRepository;

    @PostMapping("/addLog")
    public long addNewLog(@Valid @RequestBody Log log) {
        return logRepository.save(log).getId();
    }

    @GetMapping("/oneLog")
    public Log getMethodName(@Valid @RequestBody LogKey param) {
        return logRepository.findByIdAndUserId(param.getId(), param.getUserId());
    }

    @GetMapping("/all/{userId}")
    public List<Log> getAllByUser(@PathVariable long userId) {
        return logRepository.findByUserId(userId);
    }

    @GetMapping("/fromGiven")
    public List<Log> getLogFromGiven(@RequestBody LogRequest request) {
        LocalDate startDate = LocalDate.parse(request.getTimestamp().toString());
        LocalDate endDate = null;

        switch (request.getInterval()) {
            case DAILY:
                endDate = startDate.plusDays(request.getDuration());
                break;
            case MONTHLY:
                endDate = startDate.plusMonths(request.getDuration());
                break;
            case SINGLE:
                endDate = startDate;
                break;
            case WEEKLY:
                endDate = startDate.plusWeeks(request.getDuration());
                break;
            case YEARLY:
                endDate = startDate.plusYears(request.getDuration());
                break;
            default:
                return null;
        }
        return logRepository.findByTimestampBetween(Timestamp.valueOf(startDate.toString()),
                Timestamp.valueOf(endDate.toString()));
    }

    @GetMapping("/interval")
    public List<Log> getLog(@Valid @RequestBody LogRequest request) {
        LogInterval reqInterval = request.getInterval();

        switch (reqInterval) {
            case DAILY:
                break;
            case MONTHLY:
                break;
            case QUATERLY:
                break;
            case SINGLE:
                break;
            case WEEKLY:
                break;
            case YEARLY:
                break;

            default:
                break;
        }

        return null;
    }

}
