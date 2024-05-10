package io.mosip.injicertify.service;

import io.mosip.injicertify.dto.vci.CredentialRequest;
import io.mosip.injicertify.dto.vci.CredentialResponse;

import java.util.Map;

public interface VCIssuanceService {

    /**
     *
     * @param credentialRequest
     * @return
     */
    <T> CredentialResponse<T> getCredential(CredentialRequest credentialRequest);

    Map<String, Object> getCredentialIssuerMetadata(String version);
}
