package com.example.project.service;

import com.example.project.model.Picture;
import com.example.project.repository.PictureRepository;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PictureService {

  private final PictureRepository pictureRepository;

  @Autowired
  public PictureService(PictureRepository pictureRepository) {
    this.pictureRepository = pictureRepository;
  }

  public Page<Picture> getPictures(Pageable p) {
    return this.pictureRepository.findAll(p);
  }

  public int getMaxId() {
    return this.pictureRepository.findAll().stream().map(Picture::getId).max(
      Comparator.comparing(x -> x)).orElse(0);
  }

  public Picture getPictureById(int id) {
    return this.pictureRepository.findAll().stream().filter((p) -> p.getId() == id).findFirst()
      .orElseThrow(() -> new IllegalStateException("Picture not exists"));
  }

  public void addPictures() {
    Picture maria = new Picture(1, "jpg", "Mary");
    Picture alex = new Picture(2, "jpg", "alex");
    Picture dean = new Picture(3, "jpg", "dean");
    pictureRepository.saveAll(List.of(maria, alex, dean));
  }

  public void addPicture(Picture p) {
    pictureRepository.save(p);
  }

  @Transactional
  public void updatePictureById(UUID id, String topic, String summary) {
    Picture p = this.pictureRepository.findById(id).
      orElseThrow(() -> new IllegalStateException("Picture not exists"));
    if (topic != null && topic.length() > 0 && !topic.equals(p.getTopic())) {
      p.setTopic(topic);
    }
    if (summary != null && summary.length() > 0 && !summary.equals(p.getSummary())) {
      p.setSummary(summary);
    }
  }
}