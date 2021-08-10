package udodog.goGetterServer.model.dto.response.sharingboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class WriterInfo {

    private String nickName;

    private String profileUrl;

    // 글 작성자 id(pk)
    private Long writerId;

}
