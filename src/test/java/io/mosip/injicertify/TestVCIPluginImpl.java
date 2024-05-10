package io.mosip.injicertify;

import foundation.identity.jsonld.JsonLDObject;
import io.mosip.injicertify.dto.VCRequestDto;
import io.mosip.injicertify.dto.VCResult;
import io.mosip.injicertify.plugin.VCIssuancePlugin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConditionalOnProperty(value = "mosip.esignet.integration.vci-plugin", havingValue = "TestVCIPluginImpl")
@Component
@Slf4j
public class TestVCIPluginImpl implements VCIssuancePlugin {

    @Override
    public VCResult<JsonLDObject> getVerifiableCredentialWithLinkedDataProof(VCRequestDto vcRequestDto, String holderId,
                                                                             Map<String, Object> identityDetails) {
        return new VCResult<>();
    }

    @Override
    public VCResult<String> getVerifiableCredential(VCRequestDto vcRequestDto, String holderId, Map<String, Object> identityDetails) {
        return new VCResult<>();
    }
}
