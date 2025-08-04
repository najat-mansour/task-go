package com.taskgo.controllers;

import com.taskgo.constants.URLs;
import com.taskgo.dtos.statistics.UserStatisticsResponseDTO;
import com.taskgo.exceptions.NoUsersFoundException;
import com.taskgo.services.StatisticsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URLs.STATISTICS_PREFIX)
@RequiredArgsConstructor
@Tag(name = "8. Statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping(URLs.STATISTICS_GET_BY_USER_ID)
    public ResponseEntity<UserStatisticsResponseDTO> getUserStatistics(@PathVariable String userId) throws NoUsersFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(statisticsService.getUserStatistics(userId));
    }
}
