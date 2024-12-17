/*
    2024
*/
package org.project.domain.user.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserModel implements UserDetails {

    private long id;
    private long version;
    @NonNull
    private final String username;
    @NonNull
    private String password;
    @Nullable
    @Setter
    private String currentSessionId;
    @NonNull
    private List<TokenInfoModel> tokenIdList;
    @NonNull
    private final String role;

    public static UserModelBuilder builder() {
        return new UserModelBuilder();
    }

    public void addToken(TokenInfoModel token) {
        this.tokenIdList.add(token);
    }

    public void removeTokenById(String id) {
        this.tokenIdList.removeIf(token -> token.id().equals(id));
    }

    public void removeTokens() {
        this.tokenIdList.clear();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));
    }

    public void removeExpiredTokens() {
        this.tokenIdList.removeIf(token -> token.expirationDate().before(new Date()));
    }

    // Builder
    public static class UserModelBuilder {

        private long id;
        private long version;
        private String username;
        private String password;
        private List<TokenInfoModel> tokenIdList;
        private String role;

        UserModelBuilder() {
        }

        public UserModelBuilder id(long id) {
            this.id = id;
            return this;
        }

        public UserModelBuilder version(long version) {
            this.version = version;
            return this;
        }

        public UserModelBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserModelBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserModelBuilder tokenIdList(@NonNull List<TokenInfoModel> tokenIdList) {
            this.tokenIdList = new ArrayList<>(tokenIdList);
            return this;
        }

        public UserModelBuilder role(String role) {
            this.role = role;
            return this;
        }


        public UserModel build() {
            if (this.tokenIdList == null) {
                this.tokenIdList = new ArrayList<>();
            }
            return new UserModel(this.id, this.version, this.username, this.password, null,
                this.tokenIdList, this.role);
        }

    }

}
