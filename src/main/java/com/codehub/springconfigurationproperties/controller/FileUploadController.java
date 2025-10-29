package com.codehub.springconfigurationproperties.controller;

import com.codehub.springconfigurationproperties.model.Invoice;
import com.codehub.springconfigurationproperties.service.PDFService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequestMapping("/api/files")
@RestController
@RequiredArgsConstructor
public class FileUploadController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final PDFService pdfService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            File dir = new File(uploadDir);
            if (!dir.exists())
                dir.mkdirs();
            Path filePath = Paths.get(uploadDir, file.getOriginalFilename());
            Files.write(filePath, file.getBytes());
            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }

    @PostMapping("/generate-invoice")
    public ResponseEntity<Resource> generateInvoice(@RequestBody Invoice invoice) {
        try {
            String filePath = pdfService.generateInvoicePdf(invoice);
            File file = new File(filePath);
            Resource resource = new FileSystemResource(file);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice_" + invoice.getInvoiceNumber() + ".pdf");

            return ResponseEntity.ok()
                    .headers(httpHeaders)
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
