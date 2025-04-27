package com.project.englishweb.Service;

import com.project.englishweb.Entity.Level;
import com.project.englishweb.Repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LevelService {

    private final LevelRepository levelRepository;

    public List<Level> getAllLevels() {
        return levelRepository.findAll();
    }
    public Level getLevelById(Long levelId) {
        return levelRepository.findById(levelId)
                .orElseThrow(() -> new NoSuchElementException("Level not found with id: " + levelId));
    }
}
