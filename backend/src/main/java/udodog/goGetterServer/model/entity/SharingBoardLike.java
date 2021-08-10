package udodog.goGetterServer.model.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter @NoArgsConstructor
public class SharingBoardLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long sharingBoardId;

    @Builder
    public SharingBoardLike(Long userId, Long sharingBoardId) {
        this.userId = userId;
        this.sharingBoardId = sharingBoardId;
    }
}
