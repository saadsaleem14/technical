package com.example.technicalTest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/license")
public class licenseController {

    private String privatekey = "codingIsFunSometimes";

    @PostMapping("/generate")
    public ResponseEntity<String> generateLicenseKey(@RequestBody licenseClass request) {
        String input = request.getFullName() + privatekey; //as software name or software package is not being accepted by validate we wont use it in Key generation.

        //check for private key
        if (request.getSecret() == null || !request.getSecret().equals(privatekey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        //generate license using sha256Hex -> 64 chars -> 256bits
        String licenseKey = DigestUtils.sha256Hex(input);
        return ResponseEntity.ok(licenseKey);
    }

    @GetMapping("/validate")
    public ResponseEntity<Void> validateLicenseKey(@RequestParam String fullName, @RequestParam String licenseKey) {
        String input = fullName + privatekey;
        String generatedKey = DigestUtils.sha256Hex(input); //generate the key based on inputs and match it with provided licneseKey

        if (generatedKey.equals(licenseKey)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
