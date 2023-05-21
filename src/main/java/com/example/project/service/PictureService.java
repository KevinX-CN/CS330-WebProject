package com.example.project.service;

import com.example.project.model.Picture;
import com.example.project.repository.PictureRepository;
import java.util.ArrayList;
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
    List<Picture> pList=new ArrayList<>();
    pList.add(new Picture(1, "jpg", "Mary1","hahaha1","asdfsa1"));
    pList.add(new Picture(2, "jpg", "alex2","hahaha2","asdfsa2"));
    pList.add(new Picture(3, "jpg", "dean3","hahaha3","asdfsa3"));
    pList.add(new Picture(4, "jpg", "Mary4","hahaha4","asdfsa4"));
    pList.add(new Picture(5, "jpg", "alex5","hahaha5","asdfsa5"));
    pList.add(new Picture(6, "jpg", "dean6","hahaha6","asdfsa6"));
    pList.add(new Picture(7, "jpg", "Mary7","hahaha7","asdfsa7"));
    pList.add(new Picture(8, "jpg", "alex8","hahaha8","asdfsa8"));
    pList.add(new Picture(9, "jpg", "dean9","hahaha9","asdfsa9"));
    pList.add(new Picture(10, "jpg", "Mary10","hahaha10","asdfsa10"));
    pictureRepository.saveAll(pList);
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
