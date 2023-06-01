package com.example.bundle.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bundles")
public class Bundle {

    @Id
    @Column(name = "bundle_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bundleId;

    @Column(name = "bundle_name")
    private String bundleName;

    @Column(name = "bundle_duration")
    private String bundleDuration;

    @Column(name = "bundle_price")
    private Integer bundlePrice;

    @Column(name = "bundle_data")
    private Long bundleData;

    @Column(name = "auto_renewal")
    private boolean autoRenewal;
}
