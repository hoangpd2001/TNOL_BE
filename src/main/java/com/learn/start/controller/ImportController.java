//package com.learn.start.controller;
//import com.learn.start.config.ExcelProcessor;
//import com.learn.start.dto.request.CauHoiDTO_Req;
//import com.learn.start.dto.response.CauHoiDTO_Res;
//import com.learn.start.service.CauHoiService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.beans.factory.annotation.Autowired;
//import java.nio.file.*;
//import java.io.IOException;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@CrossOrigin(origins = "http://localhost:3000") // hoặc thay * bằng http://localhost:3000 nếu dùng React
//@RestController
//@RequestMapping("/cauhoi2")
//public class ImportController {
//
//    @Autowired
//    private CauHoiService cauHoiService;
//
//    @Autowired
//    private ExcelProcessor excelProcessor;
//
//    @PostMapping("/import-file")
//    public ResponseEntity<Map<String, Object>> importFile(
//            @RequestParam("teacherId") Integer teacherId,
//            @RequestParam("selectedChapterId") Integer selectedChapterId,
//            @RequestParam("files") MultipartFile[] files) {
//        Map<String, Object> response = new HashMap<>();
//        System.out.println("Tổng file: " + files.length);
//        for (MultipartFile file : files) {
//            System.out.println("Tên file: " + file.getOriginalFilename());
//        }
//
//        List<MultipartFile> excelFiles = Arrays.stream(files)
//                .filter(file -> file.getOriginalFilename().endsWith(".xlsx") || file.getOriginalFilename().endsWith(".xls"))
//                .collect(Collectors.toList());
//        List<MultipartFile> questionImages = Arrays.stream(files)
//                .filter(file -> file.getOriginalFilename().startsWith("question_"))
//                .collect(Collectors.toList());
//        List<MultipartFile> answerImages = Arrays.stream(files)
//                .filter(file -> file.getOriginalFilename().startsWith("answer_"))
//                .collect(Collectors.toList());
//
//        // Kiểm tra có ít nhất 1 file Excel
//        if (excelFiles.isEmpty()) {
//            response.put("success", false);
//            response.put("message", "Không tìm thấy file Excel!");
//            return ResponseEntity.status(400).body(response);
//        }
//
//        // Lưu file Excel và xử lý câu hỏi
//        MultipartFile excelFile = excelFiles.get(0);
//        try {
//            // Lưu file Excel và đọc dữ liệu từ đó
//            Path excelPath = Paths.get("uploads/excel_files", excelFile.getOriginalFilename());
//            Files.copy(excelFile.getInputStream(), excelPath, StandardCopyOption.REPLACE_EXISTING);
//
//            // Đọc và xử lý câu hỏi từ file Excel
//            List<CauHoiDTO_Req> questions = excelProcessor.processExcelFile(excelPath, teacherId, selectedChapterId);
//
//            // Xử lý ảnh
//            for (int i = 0; i < questions.size(); i++) {
//                final int index = i; // Đảm bảo biến 'index' là final
//                final CauHoiDTO_Req question = questions.get(i); // Đảm bảo 'question' là final
//                String questionImageName = "question_" + (index + 1) + ".jpg"; // Tên ảnh theo thứ tự câu hỏi
//                String answerImageName = "answer_" + (index + 1) + ".jpg"; // Tên ảnh theo thứ tự câu hỏi
//
//                questionImages.stream()
//                        .filter(image -> image.getOriginalFilename().contains("question_" + (index + 1)))
//                        .findFirst()
//                        .ifPresent(image -> {
//                            try {
//                                Path questionImagePath = Paths.get("uploads/question_images", questionImageName);
//                                image.transferTo(questionImagePath.toFile());
//                                question.setHinhAnh(questionImageName);
//                            } catch (IOException e) {
//                                // Handle exception if necessary
//                            }
//                        });
//            }
//
//
//            // Lưu câu hỏi vào database
//            List<CauHoiDTO_Res> result = cauHoiService.importQuestions(questions);
//            response.put("success", true);
//            response.put("message", "Import thành công!");
//            return ResponseEntity.ok(response);
//
//        } catch (IOException e) {
//            response.put("success", false);
//            response.put("message", "Lỗi khi xử lý file!");
//            return ResponseEntity.status(500).body(response);
//        }
//    }
//
//}
