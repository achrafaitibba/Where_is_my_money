package com.onexshield.wmm.controller;

import com.onexshield.wmm.request.profileImageDownloadRequest;
import com.onexshield.wmm.service.imageService;
import com.onexshield.wmm.response.profileImageUploadResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
@Tag(name = "Image")
public class imageController {
    //todo /remove image by account Id or image id ?

    private final imageService imageService;


    @PostMapping("/add/{accountId}")
    public ResponseEntity<profileImageUploadResponse> uploadProfileImage(@RequestParam("image") MultipartFile image,
                                                                         @PathVariable Long accountId
                                                                         ) throws Exception{
        return ResponseEntity.ok().body(imageService.uploadImage(accountId, image));
    }

    @GetMapping("/")
    public ResponseEntity<?> getImageByAccountId(@RequestBody profileImageDownloadRequest request){
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("image/png"))
                .body(imageService.downloadImage(request));
    }

}
