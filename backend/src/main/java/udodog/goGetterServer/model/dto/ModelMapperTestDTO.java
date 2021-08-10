package udodog.goGetterServer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.entity.User;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelMapperTestDTO {

    //SharingBoard Entity를 ModelMapperTestDTO로 매핑

    private User user;

    private String title;

    private LocalDate createdAt;
}
