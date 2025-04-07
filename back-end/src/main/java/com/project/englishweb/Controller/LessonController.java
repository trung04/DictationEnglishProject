package com.project.englishweb.Controller;

import com.project.englishweb.DTO.LessonDTO;
import com.project.englishweb.Entity.Lesson;
import com.project.englishweb.Service.LessonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lessons")
@RequiredArgsConstructor
@Slf4j
public class LessonController {

    private final LessonService lessonService;

    @GetMapping
    public String lessonManagement(@RequestParam(required = false) String title,
                                   @RequestParam(required = false) String level,
                                   @RequestParam(required = false) Long topicId,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(required = false) Long editId,
                                   Model model) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Lesson> lessonsPage;

        log.info("Quản lý bài học: Tìm kiếm (Controller): title={}, level={}, topicId={}, page={}, editId={}", title, level, topicId, page, editId);
        if (title != null || level != null || topicId != null) {
            lessonsPage = lessonService.searchLessons(title, level, topicId, pageable);
            model.addAttribute("searchTitle", title);
            model.addAttribute("searchLevel", level);
            model.addAttribute("searchTopicId", topicId);
        } else {
            lessonsPage = lessonService.getAllLessons(pageable);
        }

        model.addAttribute("lessons", lessonsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", lessonsPage.getTotalPages());
        model.addAttribute("activePage", "lessons");
        model.addAttribute("lessonDTO", new LessonDTO()); // DTO trống cho form tạo mới
        model.addAttribute("editLessonDTO", new LessonDTO()); // DTO trống cho form chỉnh sửa
        model.addAttribute("editLessonId", editId);

        if (editId != null) {
            Lesson lessonToEdit = lessonService.getLessonById(editId);
            if (lessonToEdit != null) {
                LessonDTO editDTO = new LessonDTO();
                editDTO.setTitle(lessonToEdit.getTitle());
                editDTO.setLevel(lessonToEdit.getLevel());
                editDTO.setURL(lessonToEdit.getURL());
                editDTO.setTopicId(lessonToEdit.getTopic().getTopicId());
                model.addAttribute("editLessonDTO", editDTO);
            }
        }

        return "lessons/management";
    }

    @PostMapping
    public String createLesson(@ModelAttribute LessonDTO lessonDTO) {
        lessonService.createLesson(lessonDTO);
        return "redirect:/lessons";
    }

    @PostMapping("/update/{id}")
    public String updateLesson(@PathVariable Long id, @ModelAttribute LessonDTO lessonDTO) {
        lessonService.updateLesson(id, lessonDTO);
        return "redirect:/lessons";
    }

    @GetMapping("/delete/{id}")
    public String deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return "redirect:/lessons";
    }
}
