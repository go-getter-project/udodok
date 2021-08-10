package udodog.goGetterServer.service.sharingboard;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.entity.SharingBoard;
import udodog.goGetterServer.model.entity.SharingBoardLike;
import udodog.goGetterServer.repository.SharingBoardLikeRepository;
import udodog.goGetterServer.repository.SharingBoardRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SharingBoardLikeService {

    private final SharingBoardLikeRepository sharingBoardLikeRepository;
    private final SharingBoardRepository sharingBoardRepository;

    public DefaultRes likeFeature(Long boardId, Long userId) {
        Optional<SharingBoard> optionalSharingBoard = sharingBoardRepository.findById(boardId);
        Optional<SharingBoardLike> sharingBoardLike = sharingBoardLikeRepository.findByUserIdAndSharingBoardId(userId,boardId);

        if (optionalSharingBoard.isEmpty()){
            return DefaultRes.response(HttpStatus.OK.value(),"글이 존재하지 않음");
        }

        SharingBoard sharingBoard = optionalSharingBoard.get();

        if(sharingBoardLike.isPresent()){
            sharingBoardLikeRepository.delete(sharingBoardLike.get());
            sharingBoard.minusLikeCnt();

            sharingBoardRepository.save(sharingBoard);

            return DefaultRes.response(HttpStatus.OK.value(),"좋아요 취소");
        }
        else{
            SharingBoardLike like = SharingBoardLike.builder()
                    .userId(userId)
                    .sharingBoardId(boardId)
                    .build();

            sharingBoardLikeRepository.save(like);
            sharingBoard.plusLikeCnt();

            sharingBoardRepository.save(sharingBoard);
            return DefaultRes.response(HttpStatus.OK.value(),"좋아요 등록");
        }

    }
}
