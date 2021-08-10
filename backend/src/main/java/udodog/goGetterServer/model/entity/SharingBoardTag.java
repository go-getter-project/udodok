package udodog.goGetterServer.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class SharingBoardTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sharingBoardId;

    private String Content;

    public SharingBoardTag(Long sharingBoardId, String content) {
        this.sharingBoardId = sharingBoardId;
        Content = content;
    }
}
