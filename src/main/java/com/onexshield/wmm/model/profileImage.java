package com.onexshield.wmm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class profileImage {
    @Id
    @GeneratedValue
    @Column(name = "image_id")
    private UUID imageId;
    private String imageName;
    private String type;
    @Lob
    @Column(length = 1000)
    private byte[] image;
}
