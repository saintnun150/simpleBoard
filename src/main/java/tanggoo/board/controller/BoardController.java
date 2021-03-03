package tanggoo.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tanggoo.board.dto.BoardDto;
import tanggoo.board.service.BoardService;

import java.util.List;

@Controller
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String list(Model model) {
        List<BoardDto> boardList = boardService.getBoardList();
        model.addAttribute("postList", boardList);
        return "board/list";
    }

    @GetMapping("/post")
    public String post() {
        return "board/post";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.saveArticle(boardDto);
        return "redirect:/";
    }

    @GetMapping("/article/{id}")
    public String getArticle(@PathVariable("id") Long id, Model model) {
        BoardDto article = boardService.getArticle(id);
        model.addAttribute("article", article);
        return "board/articleView";
    }

    @GetMapping("/article/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        BoardDto article = boardService.getArticle(id);
        model.addAttribute("article", article);
        return "board/edit";
    }

    @PutMapping("/article/edit/{id}")
    public String update(BoardDto boardDto) {
        boardService.saveArticle(boardDto);
        return "redirect:/";
    }

    @DeleteMapping("/article/{id}")
    public String deleteArticle(@PathVariable("id") Long id) {
        boardService.deleteArticle(id);
        return "redirect:/";
    }
}
