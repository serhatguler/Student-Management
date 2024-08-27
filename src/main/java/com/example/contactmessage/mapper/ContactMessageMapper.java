package com.example.contactmessage.mapper;

import com.example.contactmessage.dto.ContactMessageRequest;
import com.example.contactmessage.dto.ContactMessageResponse;
import com.example.contactmessage.entity.ContactMessage;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ContactMessageMapper {//DTO yu pojo ya cevir ya da tam tersi


            //1-//DTO yu pojo ya cevir
    public ContactMessage requestToContactMessage(ContactMessageRequest contactMessageRequest){

        return ContactMessage.builder()
                .name(contactMessageRequest.getName()) //pojo olacagi icin degerleri set etmemiz gerekli
                .subject(contactMessageRequest.getSubject()) //builder olmasaysi kendimiz set edecektik.
                .message(contactMessageRequest.getMessage())
                .email(contactMessageRequest.getEmail())
                .dateTime(LocalDateTime.now())
                .build();
    }


    public ContactMessageResponse contactMessageToResponse(ContactMessage contactMessage){

        return ContactMessageResponse.builder()
                .name(contactMessage.getName())
                .subject(contactMessage.getSubject())
                .email(contactMessage.getEmail())
                .message(contactMessage.getMessage())
                .dateTime(contactMessage.getDateTime())
                .build();

    }




}
