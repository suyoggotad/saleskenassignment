package com.studentreportingsystem.salesken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private Search elasticSearchQuery;

    @PostMapping("/createOrUpdateDocument")
    public ResponseEntity<Object> createOrUpdateDocument(@RequestBody Students student) throws IOException {
        String response = elasticSearchQuery.createOrUpdateDocument(student);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getDocument")
    public ResponseEntity<Object> getDocumentById(@RequestParam String studentId) throws IOException {
                Students student =  elasticSearchQuery.getDocumentById(studentId);
        return new ResponseEntity<>(student, HttpStatus.OKstudentId

    @DeleteMapping("/deleteDocument")
    public ResponseEntity<Object> deleteDocumentById(@RequestParam String studentId) throws IOException {
        String response =  elasticSearchQuery.deleteDocumentById(studentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/searchDocument")
    public ResponseEntity<Object> searchAllDocument() throws IOException {
        List<Students> students = elasticSearchQuery.searchAllDocuments();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }}
