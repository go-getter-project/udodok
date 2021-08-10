package udodog.goGetterServer.model.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import udodog.goGetterServer.model.enumclass.oauth.SocialLoginType;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private SocialLoginType provider;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "access_token")
    private String accessToken;

    @Builder
    private UserConnection(String email, SocialLoginType provider, String providerId, String displayName,
                           String imageUrl, String accessToken) {
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
        this.displayName = displayName;
        this.imageUrl = imageUrl;
        this.accessToken = accessToken;
    }
}
