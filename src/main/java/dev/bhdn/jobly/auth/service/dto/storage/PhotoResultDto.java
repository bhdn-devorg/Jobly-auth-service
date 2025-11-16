package dev.bhdn.jobly.auth.service.dto.storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoResultDto {
    private String logoPath;
    private String logoLink;
}
