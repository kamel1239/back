/*
    2024
*/
package org.project.domain.user.model;

import java.util.Date;

public record TokenInfoModel(String id, String token, Date expirationDate) {

    public TokenInfoModel {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Id is required");
        }
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("Token is required");
        }
        if (expirationDate == null || expirationDate.before(new Date())) {
            throw new IllegalArgumentException("Expiration date is required");
        }
    }

    public boolean isExpired() {
        return expirationDate.before(new Date());
    }

}
