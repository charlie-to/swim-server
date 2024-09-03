package com.example.swimServer.interfaces.api.record;

import com.example.swimServer.domain.model.entity.record.RaceRecord;
import com.example.swimServer.interfaces.dto.RecordDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record")
@Validated
public class RecordController {

    @GetMapping(produces = "application/json")
    public String record() {
        return "{\"message\":\"Hello, World!\"}";
    }

    @PostMapping(consumes = "application/json",produces = "application/json")
    public String PostNewRecord(@RequestBody RecordDto sent_record) {
        try{
            RaceRecord record = new RaceRecord(sent_record);
            return "{\"message\":\"Record added\",\"swimmerId\":" + record.getSwimmerId() + ",\"swimStyle\":\"" + record.getRaceType().getSwimStyle() + "\",\"distance\":" + record.getRaceType().getDistance() + ",\"time_s\":" + record.getTime_s() + "}";
        } catch (IllegalArgumentException e){
            throw e;
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"Invalid record\"}");
    }
}
