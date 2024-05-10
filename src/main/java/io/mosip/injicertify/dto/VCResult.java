package io.mosip.injicertify.dto;

import lombok.Data;

@Data
public class VCResult<T> {

    /**
     * Format of credential
     * Eg: ldp_vc
     */
    private String format;

    /**
     *
     */
    private T credential;
}
