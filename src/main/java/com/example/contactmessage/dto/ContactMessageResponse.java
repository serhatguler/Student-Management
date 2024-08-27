package com.example.contactmessage.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)

public class ContactMessageResponse { //serviste responseDTO olan class



    private String name;

    private String email;

    private String subject;

    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd HH:mm" ,timezone = "US")
    private LocalDateTime dateTime;

}
