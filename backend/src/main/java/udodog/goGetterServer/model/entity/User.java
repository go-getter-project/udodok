package udodog.goGetterServer.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import udodog.goGetterServer.model.enumclass.UserGrade;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private String nickName;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserGrade grade;

    private String token;

    @CreatedDate
    private LocalDate createdAt;

    private String profileUrl;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = UserConnection.class)
    @JoinColumn(name = "user_connection_id")
    private UserConnection userConnection;

    @Builder
    public User(String email, String password, String name, String nickName, String phoneNumber,
                UserGrade grade, UserConnection userConnection) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.grade = grade;
        this.userConnection = userConnection;
    }

    public void setRefreshToken(String refreshToken){
        this.token = refreshToken;
    }

}
