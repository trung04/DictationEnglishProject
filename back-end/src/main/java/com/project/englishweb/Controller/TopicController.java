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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            topicsPage = topicService.getAllTopics(pageable);
        }

        model.addAttribute("topics", topicsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", topicsPage.getTotalPages());
        model.addAttribute("activePage", "topics");
        model.addAttribute("topicDTO", new TopicDTO());
        model.addAttribute("editTopicDTO", new TopicDTO());
        model.addAttribute("editTopicId", editId);

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
            }
        }

        try {
            // code lấy dữ liệu
        } catch (Exception e) {
            log.error("Error in topicManagement: ", e);
            throw e; // hoặc trả về view lỗi tùy ý
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
}
