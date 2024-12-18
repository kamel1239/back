package org.project.infrastructure.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TokenEntity {

    @Id
    private String id;
    private String tokenValue;
    private Date expiryDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
