// package com.project.englishweb.Controller;

// import com.project.englishweb.DTO.TopicDTO;
// import com.project.englishweb.Service.TopicService;
// import com.project.englishweb.Entity.Topic;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// // @CrossOrigin(origins = "http://localhost:3000")
// @Controller
// @RequestMapping("/topictable")
// @RequiredArgsConstructor
// @Slf4j
// public class TopicController {

//     private final TopicService topicService;

//     // Lấy tất cả Topics
//     @GetMapping
//     public ResponseEntity<List<TopicDTO>> getAllTopics() {
//         try {
//             List<TopicDTO> topics = topicService.getAllTopics();
//             return ResponseEntity.ok(topics);
//         } catch (Exception e) {
//             log.error("Error retrieving all topics: {}", e.getMessage());
//             return ResponseEntity.internalServerError().build();
//         }
//     }

//     // Lấy Topic theo ID
//     @GetMapping("/{id}")
//     public ResponseEntity<TopicDTO> getTopicById(@PathVariable Long id) {
//         try {
//             TopicDTO topic = topicService.getTopicById(id);
//             return topic != null ? ResponseEntity.ok(topic) : ResponseEntity.notFound().build();
//         } catch (RuntimeException e) {
//             log.error("Error retrieving topic with ID {}: {}", id, e.getMessage());
//             return ResponseEntity.notFound().build();
//         }
//     }

//     // Thêm mới một Topic
//     @PostMapping
//     public ResponseEntity<TopicDTO> createTopic(@RequestBody TopicDTO topicDTO) {
//         try {
//             TopicDTO createdTopic = topicService.createTopic(topicDTO);
//             return ResponseEntity.status(201).body(createdTopic); // Return 201 Created status
//         } catch (Exception e) {
//             log.error("Error creating topic: {}", e.getMessage());
//             return ResponseEntity.internalServerError().build();
//         }
//     }

//     // Cập nhật Topic theo ID
//     @PutMapping("/{id}")
//     public ResponseEntity<TopicDTO> updateTopic(@PathVariable Long id, @RequestBody TopicDTO topicDTO) {
//         try {
//             TopicDTO updatedTopic = topicService.updateTopic(id, topicDTO);
//             return updatedTopic != null ? ResponseEntity.ok(updatedTopic) : ResponseEntity.notFound().build();
//         } catch (RuntimeException e) {
//             log.error("Error updating topic with ID {}: {}", id, e.getMessage());
//             return ResponseEntity.notFound().build();
//         }
//     }

//     // Xóa Topic theo ID
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
//         try {
//             topicService.deleteTopic(id);
//             return ResponseEntity.noContent().build(); // Return 204 No Content status
//         } catch (RuntimeException e) {
//             log.error("Error deleting topic with ID {}: {}", id, e.getMessage());
//             return ResponseEntity.notFound().build();
//         }
//     }

//     // // This is for rendering an HTML page from a specific URL for front-end purposes
//     // @GetMapping("/topictable")
//     // public String showTopicTable() {
//     //     return "topictable"; // This will look for `src/main/resources/templates/topictable.html`
//     // }
// }







// package com.project.englishweb.Controller;

// import com.project.englishweb.DTO.TopicDTO;
// import com.project.englishweb.Entity.Topic;
// import com.project.englishweb.Service.TopicService;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;

// @Controller
// @RequestMapping("/topictable")
// @RequiredArgsConstructor
// @Slf4j
// public class TopicController {

//     private final TopicService topicService;

//     // Giao diện quản lý topic
//     @GetMapping
//     public String topicManagement(@RequestParam(defaultValue = "0") int page,
//                                   @RequestParam(required = false) Long editId,
//                                   Model model) {
//         Pageable pageable = PageRequest.of(page, 10);
//         Page<Topic> topicsPage = topicService.getAllTopicsPaged(pageable);

//         model.addAttribute("topics", topicsPage);
//         model.addAttribute("currentPage", page);
//         model.addAttribute("totalPages", topicsPage.getTotalPages());
//         model.addAttribute("activePage", "topics");
//         model.addAttribute("topicDTO", new TopicDTO());
//         model.addAttribute("editTopicDTO", new TopicDTO());
//         model.addAttribute("editTopicId", editId);

//         if (editId != null) {
//             Topic topicToEdit = topicService.getTopicEntityById(editId);
//             if (topicToEdit != null) {
//                 TopicDTO editDTO = new TopicDTO();
//                 editDTO.setTitle(topicToEdit.getTitle());
//                 editDTO.setLevel(topicToEdit.getLevel());
//                 editDTO.setDetail(topicToEdit.getDetail());
//                 editDTO.setTopicId(topicToEdit.getTopicId());
//                 model.addAttribute("editTopicDTO", editDTO);
//             }
//         }

//         return "topictable/management"; // template path: src/main/resources/templates/topictable/management.html
//     }

//     // Xử lý thêm mới
//     @PostMapping
//     public String createTopic(@ModelAttribute TopicDTO topicDTO) {
//         topicService.createTopic(topicDTO);
//         return "redirect:/topictable";
//     }

//     // Xử lý cập nhật
//     @PostMapping("/update/{id}")
//     public String updateTopic(@PathVariable Long id, @ModelAttribute TopicDTO topicDTO) {
//         topicService.updateTopic(id, topicDTO);
//         return "redirect:/topictable";
//     }

//     // Xử lý xoá
//     @GetMapping("/delete/{id}")
//     public String deleteTopic(@PathVariable Long id) {
//         topicService.deleteTopic(id);
//         return "redirect:/topictable";
//     }
// }
