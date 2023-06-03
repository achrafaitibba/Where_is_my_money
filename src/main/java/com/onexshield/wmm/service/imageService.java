package com.onexshield.wmm.service;

import com.onexshield.wmm.mappers.accountMapper;
import com.onexshield.wmm.model.account;
import com.onexshield.wmm.model.profileImage;
import com.onexshield.wmm.repository.IAccountRepository;
import com.onexshield.wmm.repository.IImageRepository;
import com.onexshield.wmm.request.imageDeletionRequest;
import com.onexshield.wmm.request.profileImageDownloadRequest;
import com.onexshield.wmm.response.profileImageUploadResponse;
import com.onexshield.wmm.util.imageUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class imageService {

    private final IImageRepository imageRepository;
    private final IAccountRepository accountRepository;

    public profileImageUploadResponse uploadImage(Long accountId, MultipartFile image) throws Exception {
        profileImage toSave = imageRepository.save(profileImage.builder()
                .imageName(image.getOriginalFilename())
                .type(image.getContentType())
                .image(imageUtils.compressImage(image.getBytes()))
                .build());
        account accountToUpdate = accountRepository.findByAccountId(accountId);
        profileImage oldImage = accountToUpdate.getProfileImage();
        if(oldImage != null){
            imageRepository.deleteById(oldImage.getImageId());
        }
        accountToUpdate.setProfileImage(toSave);
        accountRepository.save(accountToUpdate);
        return profileImageUploadResponse.builder()
                .imageId(toSave.getImageId())
                .build();
    }

    public byte[] downloadImage(profileImageDownloadRequest request) {
        Optional<profileImage> image = imageRepository.findByImageIdAndAccountId(request.getImageId(), request.getAccountId());
        return imageUtils.decompressImage(image.get().getImage());

    }

    public void deleteByAccountId(imageDeletionRequest request ) {
        Optional<account> account = Optional.ofNullable(accountRepository.findByAccountId(request.getAccountId()));
        if(account.isPresent()){
            account.get().setProfileImage(null);
            imageRepository.deleteById(request.getImageId());
        }
    }
}
