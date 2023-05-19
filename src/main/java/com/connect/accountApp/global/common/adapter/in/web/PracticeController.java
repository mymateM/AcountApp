package com.connect.accountApp.global.common.adapter.in.web;

import com.connect.accountApp.global.common.adapter.in.web.response.PracticeResponse;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PracticeController {

  @GetMapping("/api/practice1")
  public ResponseEntity getPracticeResponse1() {

    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(1));
  }

  @GetMapping("/api/practice2")
  public ResponseEntity getPracticeResponse2() {

    PracticeResponse practiceResponse = new PracticeResponse(1);
    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(practiceResponse));
  }


  @GetMapping("/api/practice3/{id}")
  public ResponseEntity getPracticeResponse2(@PathVariable Integer id) {

    PracticeResponse practiceResponse = new PracticeResponse(id);
    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(practiceResponse));
  }



}
