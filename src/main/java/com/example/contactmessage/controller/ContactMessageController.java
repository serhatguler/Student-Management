package com.example.contactmessage.controller;

import com.example.contactmessage.dto.ContactMessageRequest;
import com.example.contactmessage.dto.ContactMessageResponse;
import com.example.contactmessage.dto.ContactMessageUpdateRequest;
import com.example.contactmessage.entity.ContactMessage;
import com.example.contactmessage.service.ContactMessageService;
import com.example.payload.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/contactMessages")
@RequiredArgsConstructor
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    //)))***************************888NOTE: SAVE()******************88888888
    //JSon dosya request ile geirese controllerda DTo ile maplemek gerek
    @PostMapping("/save")//http://localhost:8080/contactMessages/save + POST  + JSON(@REquestBOdy) ve valid et
    public ResponseMessage<ContactMessageResponse> save(@Valid @RequestBody ContactMessageRequest contactMessageRequest){

        return contactMessageService.save(contactMessageRequest);
    }


    //********************************getAll()********************************8
    @GetMapping("/getAll")//http://localhost:8080/getAll?page=0&size=10
    public Page<ContactMessageResponse> getAll(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "10") int size,
            @RequestParam(value = "sort",defaultValue = "dateTime") String sort,
            @RequestParam(value = "type",defaultValue = "desc") String type
    ){
        return contactMessageService.getAll(page,size,sort,type);

    }

    //*************************************searchByEmail**********************
    @GetMapping("/searchByEmail")//http://localhost:8080/contactMessages/searchByEmail?email=aaa@bbb.com
    public Page<ContactMessageResponse> searchByemail(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "10") int size,
            @RequestParam(value = "sort",defaultValue = "dateTime") String sort,
            @RequestParam(value = "type",defaultValue = "desc") String type
    ){
        return contactMessageService.searchByEmail(email,page,size,sort,type);
    }

    //*************************************searchBySubject**********************
    @GetMapping("/searchSubject")//http://localhost:8080/contactMessages/searchSubject?subject=deneme
    public Page<ContactMessageResponse> searchSubject(
            @RequestParam(value = "subject") String subject,
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "10") int size,
            @RequestParam(value = "sort",defaultValue = "dateTime") String sort,
            @RequestParam(value = "type",defaultValue = "desc") String type
    ){
        return contactMessageService.searchBySubject(subject,page,size,sort,type);

    }

    //*************************************deleteByIdParam**********************
    @DeleteMapping("/deleteByIdParam") //http://localhost:8080/contactMessages/deleteByIdParam?contactMessageId=1
    public ResponseEntity<String> deleteById(@RequestParam(value = "contactMessageId") Long contactMessageId){
        return ResponseEntity.ok(contactMessageService.deleteById(contactMessageId));
    }

    //*************************************deleteByIdWithPath**********************
    @DeleteMapping("/deleteById/{contactMessageId}")//http://localhost:8080/contactMessages/deleteById/1
    public ResponseEntity<String> deleteByIdWithPath(@PathVariable Long contactMessageId){
        return ResponseEntity.ok(contactMessageService.deleteById(contactMessageId));
    }


    //*************************************getByIdParam**********************
    @GetMapping("/getByIdParam")//http://localhost:8080/contactMessages/getByIdParam?contactMessageId=1 + GET
    public ResponseEntity<ContactMessage> getById(@RequestParam(value = "contactMessageId") Long contactMessageId){
        return ResponseEntity.ok(contactMessageService.getContactMessageById(contactMessageId));

    }


    //*************************************getById**********************
    @GetMapping("/getById/{contactMessageId}")//http://localhost:8080/contactMessages/getById/1 + GET
    public ResponseEntity<ContactMessage> getByIdPath(@PathVariable Long contactMessageId){
        return ResponseEntity.ok(contactMessageService.getContactMessageById(contactMessageId));
    }

    //*************************************updateById**********************
    @PutMapping("/updateById/{contactMessageId}")//http://localhost:8080/contactMessages/updateById/1 + PUT +JSON
    public  ResponseEntity<ResponseMessage<ContactMessageResponse>> updateById(@PathVariable Long contactMessageId,
                                                                       @RequestBody @NotNull ContactMessageUpdateRequest contactMessageUpdateRequest){
        return ResponseEntity.ok(contactMessageService.updateById(contactMessageId,contactMessageUpdateRequest));

    }





}
