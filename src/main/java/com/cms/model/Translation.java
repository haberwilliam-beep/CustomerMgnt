package com.cms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Translation {
    private Long id;
    private String key;
    private String lang;
    private String value;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
