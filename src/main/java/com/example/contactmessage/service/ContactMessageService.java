package com.example.contactmessage.service;

import com.example.contactmessage.dto.ContactMessageRequest;
import com.example.contactmessage.dto.ContactMessageResponse;
import com.example.contactmessage.dto.ContactMessageUpdateRequest;
import com.example.contactmessage.entity.ContactMessage;
import com.example.contactmessage.exception.ResourceNotFoundException;
import com.example.contactmessage.mapper.ContactMessageMapper;
import com.example.contactmessage.message.Messages;
import com.example.contactmessage.repository.ContactMessageRepository;
import com.example.payload.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor //private FINAL ContactMessageRepository contactMessageRepository;
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;

    private final ContactMessageMapper contactMessageMapper;


    public ResponseMessage<ContactMessageResponse> save(ContactMessageRequest contactMessageRequest) {
        //ContactMessageRequest(DTO) classini pojoya cevirmem lazim.
        ContactMessage contactMessage = contactMessageMapper.requestToContactMessage(contactMessageRequest);//id siz data
        ContactMessage savedData = contactMessageRepository.save(contactMessage); //id li data


        return ResponseMessage.<ContactMessageResponse>builder()
                .message("Contact Message created successfully")
                .httpStatus(HttpStatus.CREATED)
                .object(contactMessageMapper.contactMessageToResponse(savedData))
                .build();

    }


    //getAll()
    public Page<ContactMessageResponse> getAll(int page, int size, String sort, String type) {

        Pageable pageable = PageRequest.of(page,size, Sort.by(sort).ascending());
        if (Objects.equals(type,"desc")){
            pageable = PageRequest.of(page,size, Sort.by(sort).descending());
        }
        return contactMessageRepository.findAll(pageable).map(contactMessageMapper::contactMessageToResponse);
    }


    ///searchByEmail
    public Page<ContactMessageResponse> searchByEmail(String email, int page, int size, String sort, String type) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(sort).ascending());
        if (Objects.equals(type,"desc")){
            pageable = PageRequest.of(page,size, Sort.by(sort).descending());
        }

        return contactMessageRepository.findByEmailEquals(email,pageable).map(contactMessageMapper::contactMessageToResponse);

    }


    //searchBySubject
    public Page<ContactMessageResponse> searchBySubject(String subject, int page, int size, String sort, String type) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(sort).ascending());
        if (Objects.equals(type,"desc")){
            pageable = PageRequest.of(page,size, Sort.by(sort).descending());
        }
        return contactMessageRepository.findBySubjectEquals(subject,pageable).map(contactMessageMapper::contactMessageToResponse);

    }

    //getById
    public ContactMessage getContactMessageById(Long contactMessageId) {

        return contactMessageRepository.findById(contactMessageId).orElseThrow(()->
                new ResourceNotFoundException(Messages.NOT_FOUND_MESSAGE));


    }

    //*************************************deleteByIdParam**********************
    public String deleteById(Long contactMessageId) {

        // 1st way-contactMessageRepository.delete(getContactMessageById(contactMessageId));
        getContactMessageById(contactMessageId);//check edip DB de olup olmadigina bakar
        contactMessageRepository.deleteById(contactMessageId);
        return Messages.CONTACT_MESSAGE_DELETE;
    }

    //*************************************updateById**********************

    public ResponseMessage<ContactMessageResponse> updateById(Long id, ContactMessageUpdateRequest contactMessageUpdateRequest) {
        ContactMessage contactMessage = getContactMessageById(id);
        if (contactMessageUpdateRequest.getMessage()!=null){
            contactMessage.setMessage(contactMessageUpdateRequest.getMessage());
        }
        if (contactMessageUpdateRequest.getSubject()!=null){
            contactMessage.setSubject(contactMessageUpdateRequest.getSubject());
        }
        if (contactMessageUpdateRequest.getName()!= null){
            contactMessage.setName(contactMessageUpdateRequest.getName());
        }
        if (contactMessageUpdateRequest.getEmail()!=null){
            contactMessage.setEmail(contactMessageUpdateRequest.getEmail());
        }

        contactMessage.setDateTime(LocalDateTime.now());
        contactMessageRepository.save(contactMessage);
        return ResponseMessage.<ContactMessageResponse>builder()
                .message("Contact Message updated Successfully")
                .httpStatus(HttpStatus.CREATED)
                .object(contactMessageMapper.contactMessageToResponse(contactMessage))
                .build();
    }
}
