package com.example.project.repository;

import com.example.project.model.Picture;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, UUID> {
    Picture getPictureById(UUID id);
}