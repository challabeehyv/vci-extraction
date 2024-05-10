package io.mosip.injicertify;

import io.mosip.injicertify.dto.AuditDTO;
import io.mosip.injicertify.plugin.AuditPlugin;
import io.mosip.injicertify.util.Action;
import io.mosip.injicertify.util.ActionStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;


@ConditionalOnProperty(value = "mosip.esignet.integration.audit-plugin", havingValue = "TestAuditPlugin")
@Component
@Slf4j
public class TestAuditPluginImpl implements AuditPlugin {

    @Override
    public void logAudit(Action action, ActionStatus status, AuditDTO audit, Throwable t) {
        //do nothing
    }

    @Override
    public void logAudit(String username, Action action, ActionStatus status, AuditDTO audit, Throwable t) {
        //do nothing
    }
}
