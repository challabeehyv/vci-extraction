/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package io.mosip.injicertify.dto.vci;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
public class ParsedAccessToken {

    private Map<String, Object> claims;
    private String accessTokenHash;
    private boolean isActive;
}
