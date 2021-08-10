package udodog.goGetterServer.repository.querydsl.sharingboard;

import udodog.goGetterServer.model.entity.SharingBoard;
import java.util.List;

public interface SharingBoardExtension {

    List<SharingBoard> getWeeklyBest();
}
