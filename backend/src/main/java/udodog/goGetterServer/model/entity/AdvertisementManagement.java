package udodog.goGetterServer.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;


@Getter
@NoArgsConstructor          // 기본 생성자
@Entity
public class AdvertisementManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    private String image;
    
    private String url;
    
    private String content;
    
    @Builder
    public AdvertisementManagement(String name, String image, String url, String content) {
        this.name = name;
        this.image = image;
        this.url = url;
        this.content = content;
    } // 생성자 끝

} // Class End
