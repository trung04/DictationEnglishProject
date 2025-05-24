package com.project.englishweb.Controller;

import com.project.englishweb.DTO.TopicDTO;
import com.project.englishweb.Entity.Topic;
import com.project.englishweb.Entity.Level;
import com.project.englishweb.Service.TopicService;
import com.project.englishweb.Service.LevelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/topics")
@RequiredArgsConstructor
@Slf4j
public class TopicController {

    private final TopicService topicService;
    private final LevelService levelService;

    @GetMapping
    public String topicManagement(@RequestParam(required = false) String title,
                                @RequestParam(required = false) String levelName,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(required = false) Long editId,
                                @RequestParam(required = false) Long levelId,
                                Model model) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Topic> topicsPage;

        if (levelId != null) {
            model.addAttribute("levels", levelService.getLevelById(levelId));
        } else {
            model.addAttribute("levels", levelService.getAllLevels());
        }

        if (title != null || levelName != null) {
            topicsPage = topicService.searchTopics(title, levelName, pageable);
            model.addAttribute("searchTitle", title);
            model.addAttribute("searchLevelName", levelName);
        } else {
            topicsPage = topicService.findByParentIsNotNull(pageable);
        }

        model.addAttribute("parentTopics", topicService.findByParentIsNull());
        model.addAttribute("topics", topicsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", topicsPage.getTotalPages());
        model.addAttribute("activePage", "topics");
        model.addAttribute("topicDTO", new TopicDTO());

        if (editId != null) {
            Topic topicToEdit = topicService.getTopicById(editId);
            if (topicToEdit != null) {
                TopicDTO editDTO = new TopicDTO();
                editDTO.setTitle(topicToEdit.getTitle());
                editDTO.setDetail(topicToEdit.getDetail());
                editDTO.setLevelId(topicToEdit.getLevel().getLevelId());
                if (topicToEdit.getParent() != null) {
                    editDTO.setParentId(topicToEdit.getParent().getTopicId());
                }
                model.addAttribute("editTopicDTO", editDTO);
                model.addAttribute("editTopicId", editId);
            }
        }

        return "topics/management";
    }

    @PostMapping
    public String createTopic(@ModelAttribute TopicDTO topicDTO) {
        topicService.createTopic(topicDTO);
        return "redirect:/topics";
    }

    @PostMapping("/update/{id}")
    public String updateTopic(@PathVariable Long id, @ModelAttribute TopicDTO topicDTO) {
        topicService.updateTopic(id, topicDTO);
        return "redirect:/topics";
    }

    @GetMapping("/delete/{id}")
    public String deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return "redirect:/topics";
    }

    @PostMapping("/add-parent")
    public String createParentTopic(@RequestParam String title,
                                   @RequestParam(required = false) String detail,
                                   @RequestParam("image") MultipartFile image) {
        try {
            String fileName = java.util.UUID.randomUUID() + "_" + image.getOriginalFilename();
            java.nio.file.Path uploadPath = java.nio.file.Paths.get("uploads/parent-topics");
            if (!java.nio.file.Files.exists(uploadPath)) java.nio.file.Files.createDirectories(uploadPath);
            java.nio.file.Path filePath = uploadPath.resolve(fileName);
            java.nio.file.Files.copy(image.getInputStream(), filePath);

            Level defaultLevel = levelService.getAllLevels().stream().findFirst().orElse(null);
            TopicDTO parentTopicDTO = new TopicDTO();
            parentTopicDTO.setTitle(title);
            parentTopicDTO.setDetail(detail != null ? detail : "");
            parentTopicDTO.setLevelId(defaultLevel != null ? defaultLevel.getLevelId() : null);
            parentTopicDTO.setParentId(null);
            parentTopicDTO.setParentImagePath("/uploads/parent-topics/" + fileName);

            topicService.createTopic(parentTopicDTO);

            return "redirect:/topics";
        } catch (Exception e) {
            log.error("Error in createParentTopic: ", e);
            return "redirect:/topics?error=" + e.getMessage();
        }
    }
}
