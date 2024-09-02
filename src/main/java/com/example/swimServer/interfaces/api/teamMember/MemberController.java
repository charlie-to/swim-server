package com.example.swimServer.interfaces.api.teamMember;

import com.example.swimServer.domain.model.entity.swimmer.Swimmer;
import com.example.swimServer.interfaces.dto.SwimmerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.swimServer.infrastructure.persistance.maria.swimmer.SwimmerRepository;

import java.util.List;

@RestController
@RequestMapping("/member")
@Validated
public class MemberController {
        @Autowired
        private SwimmerRepository swimmerRepository;

        @GetMapping(produces = "application/json")
        public String member() {
            return "{\"message\":\"Hello, World!\"}";
        }

        @PostMapping(path = "/swimmer/add", consumes = "application/json", produces = "application/json")
        public @ResponseBody String addNewSwimmer(@RequestBody SwimmerDto swimmer) {
            Swimmer newSwimmer = new Swimmer();
            newSwimmer.setFamilyName(swimmer.familyName);
            newSwimmer.setGivenName(swimmer.givenName);
            newSwimmer.setAge(swimmer.age);
            swimmerRepository.save(newSwimmer);
            return "{\"message\":\"Swimmer added\"}";
        }

        @GetMapping(path = "/swimmer/all", produces = "application/json")
        public @ResponseBody String getAllSwimmers()
        {
            List<Swimmer> swimmers = (List<Swimmer>) swimmerRepository.findAll();
            StringBuilder result = new StringBuilder("{[");
            for (Swimmer swimmer : swimmers) {
               result.append(swimmer.toJson()).append(",");
            }
            result.append("]}");
            return result.toString();
        }
}
