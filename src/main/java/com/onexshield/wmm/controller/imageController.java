package com.onexshield.wmm.controller;

import com.onexshield.wmm.request.imageDeletionRequest;
import com.onexshield.wmm.request.profileImageDownloadRequest;
import com.onexshield.wmm.service.imageService;
import com.onexshield.wmm.response.profileImageUploadResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    private final imageService imageService;

    @Operation(
            summary = "Adding/Updating profile image",
            description = "To add a profile image to an account you should send a request that contains the account Id and image Id. <br>",
            responses = {
                    @ApiResponse(
                            description = "Success, or image doesn't exist",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Forbidden or token expired",
                            responseCode = "403"
                    )
            }
    )
    @PostMapping("/add/{accountId}")
    public ResponseEntity<profileImageUploadResponse> uploadProfileImage(@RequestParam("image") MultipartFile image,
                                                                         @PathVariable Long accountId
                                                                         ) throws Exception{
        return ResponseEntity.ok().body(imageService.uploadImage(accountId, image));
    }

    @Operation(
            summary = "Getting profile image",
            description = "To get a profile image of an account you should send a request that contains the account Id and image Id. <br>",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Forbidden or token expired",
                            responseCode = "403"
                    )
            }
    )
    @PostMapping(value = "/")
    public ResponseEntity<?> getImageByAccountId(@RequestBody profileImageDownloadRequest request){

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("image/png"))
                .body(imageService.downloadImage(request));
    }


    @Operation(
            summary = "Deleting profile image",
            description = "To delete a profile image of an account you should send a request that contains the account Id and image Id. <br>",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Forbidden or token expired",
                            responseCode = "403"
                    )
            }
    )
    @DeleteMapping("/delete")
    public void deleteImageByAccountId(@RequestBody imageDeletionRequest request){
         imageService.deleteByAccountId(request);
    }

}
