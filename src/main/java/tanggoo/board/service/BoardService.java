package tanggoo.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tanggoo.board.domain.Board;
import tanggoo.board.dto.BoardDto;
import tanggoo.board.repository.BoardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Long saveArticle(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<BoardDto> getBoardList() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (Board board : boardList) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .author(board.getAuthor())
                    .content(board.getContent())
                    .createdDate(board.getCreateDate())
                    .modifiedDate(board.getUpdateDate())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    @Transactional(readOnly = true)
    public BoardDto getArticle(Long id) {
        Board board = boardRepository.findById(id).get();

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .author(board.getAuthor())
                .content(board.getContent())
                .createdDate(board.getCreateDate())
                .modifiedDate(board.getUpdateDate())
                .build();
        return boardDto;
    }

    @Transactional
    public void deleteArticle(Long id) {
        boardRepository.deleteById(id);
    }
}
