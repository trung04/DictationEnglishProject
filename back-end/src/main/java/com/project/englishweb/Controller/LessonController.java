package com.project.englishweb.Controller;

import com.project.englishweb.DTO.LessonDTO;
import com.project.englishweb.Entity.Lesson;
import com.project.englishweb.Entity.Level;
import com.project.englishweb.Entity.Topic;
import com.project.englishweb.Service.LessonService;
import com.project.englishweb.Service.LevelService;
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
    private final LevelService levelService;

    @GetMapping
    public String lessonManagement(@RequestParam(required = false) String title,
                                   @RequestParam(required = false) String levelName,  // Tìm kiếm theo level name
                                   @RequestParam(required = false) String topicTitle,  // Tìm kiếm theo topic title
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(required = false) Long editId,
                                   @RequestParam(required = false) Long levelId,
                                   Model model) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Lesson> lessonsPage;

        if (levelId != null) {
            model.addAttribute("levels", levelService.getLevelById(levelId));
        } else {
            model.addAttribute("levels", levelService.getAllLevels());  // Nếu không có levelId, lấy tất cả Level
        }

        if (title != null || levelName != null || topicTitle != null) {
            lessonsPage = lessonService.searchLessons(title, levelName, topicTitle, pageable);  // Gọi search theo topicTitle
            model.addAttribute("searchTitle", title);
            model.addAttribute("searchLevelName", levelName);  // Truyền levelName cho tìm kiếm
            model.addAttribute("searchTopicTitle", topicTitle);  // Truyền topicTitle cho tìm kiếm
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
                editDTO.setLevelName(lessonToEdit.getLevel().getName());
                editDTO.setLevelId(lessonToEdit.getLevel().getLevelId());  
                editDTO.setURL(lessonToEdit.getURL());
                editDTO.setTopicId(lessonToEdit.getTopic().getTopicId());
                editDTO.setTranscript(lessonToEdit.getTranscript());  
                editDTO.setQuestionCount(lessonToEdit.getQuestionCount());
                model.addAttribute("editLessonDTO", editDTO);
            }
        }

        return "lessons/management";
    }

    @PostMapping
    public String createLesson(@ModelAttribute LessonDTO lessonDTO) {
        Level level = levelService.getLevelById(lessonDTO.getLevelId());  // Lấy level bằng levelId
        if (level != null) {
            lessonDTO.setLevelName(level.getName());  // Cập nhật tên level
        }

        // Tạo bài học mới
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
