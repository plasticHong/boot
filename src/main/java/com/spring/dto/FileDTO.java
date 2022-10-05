package com.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDTO {

    private String uid;
    private String fileName;
    private String contentType;


    public FileDTO() {
    }

    public FileDTO(String uid, String fileName, String contentType) {
        this.uid = uid;
        this.fileName = fileName;
        this.contentType = contentType;
    }


}
