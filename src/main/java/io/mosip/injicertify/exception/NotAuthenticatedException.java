/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package io.mosip.injicertify.exception;

import io.mosip.injicertify.constants.ErrorConstants;

public class NotAuthenticatedException extends EsignetException {

    public NotAuthenticatedException() {
        super(ErrorConstants.INVALID_AUTH_TOKEN);
    }

    public NotAuthenticatedException(String errorCode) {
        super(errorCode);
    }
}
