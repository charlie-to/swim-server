package com.example.swimServer.interfaces.api.record;

import com.example.swimServer.domain.service.RaceRecordService;
import com.example.swimServer.domain.model.entity.record.RaceRecord;
import com.example.swimServer.infrastructure.persistance.maria.swimmer.SwimmerRepository;
import com.example.swimServer.interfaces.dto.RecordDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/record")
@Validated
public class RecordController {

    @Autowired
    RaceRecordService raceRecordService;

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = "application/json")
    public List<RaceRecord> record() {
        return raceRecordService.getAllRecords();
    }

    @PostMapping(consumes = "application/json",produces = "application/json")
    public String PostNewRecord(@RequestBody RecordDto sent_record) {
        try{
            raceRecordService.addRecord(sent_record);
            return "{\"message\":\"Record added\"}";
        } catch (IllegalArgumentException e){
            throw e;
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"Invalid record\"}");
    }
}
