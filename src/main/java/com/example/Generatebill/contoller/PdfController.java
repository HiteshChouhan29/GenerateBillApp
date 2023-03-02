package com.example.Generatebill.contoller;


import com.example.Generatebill.entity.Form;
import com.example.Generatebill.services.PdfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;
    Logger logger = LoggerFactory.getLogger("myLogger");

    @PostMapping("/createpdf")
    public ResponseEntity<InputStreamResource> createPdf(@RequestBody Form form) {
        System.out.println("form......"+ form);
        ByteArrayInputStream pdf = pdfService.createPdf(form);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content", "inline;file=hgj.pdf");
        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));

    }
}





