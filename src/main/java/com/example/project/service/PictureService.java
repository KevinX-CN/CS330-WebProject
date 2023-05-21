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

  public List<Picture> getPicturesByPage(int page) {
    return this.pictureRepository.findAll().stream()
      .filter((p) -> ((p.getId() - 1) / 10) + 1 == page)
      .toList();
  }

  public Picture getPictureById(int id) {
    return this.pictureRepository.findAll().stream().filter((p) -> p.getId() == id).findFirst()
      .orElseThrow(() -> new IllegalStateException("Picture not exists"));
  }

  public void addPictures() {
    List<Picture> pList = new ArrayList<>();
    pList.add(new Picture(1, "jpg", "Mary1", "['wireless networks defined', 'international telecommunication union', 'conventional human-type communication', 'massive machine-type communication', 'massive access aims', 'present optimization formulations', 'active devices based', 'massive access', 'optimization perspective', 'massive connectivity']", "[' Massive access,also known as massive connectivity', 'Massive access,also known as massive connectivity or massive machine-type communication (mMTC),is one of the three main use cases of the fifth-generation (5G) and beyond 5G (B5G)wireless networks defined by the International Telecommunication Union.Different from conventional human-type communication,massive access aims at realizing efficient and reliable communications for a massive number of Internet of Things (loT)devices.The main challenge of mMTC is that the BS can efficiently and reliably detect the active devices based on the superposition of their unique signatures from a large pool of uplink devices,among which only a small fraction is active. In this talk,we shall present some recent results of massive access from an optimization perspective.In particular,we shall present optimization formulations and algorithms as well as some phase transition analysis results', 'Massive access is one of the main use cases of 5G wireless networks defined by the International Telecommunication Union.', 'Massive access,also known as massive connectivity or massive machine-type communication (mMTC),is one of the three main use cases of the fifth-generation (5G) and beyond 5G (B5G)wireless networks defined by the International Telecommunication Union.Different from conventional human-type communication,massive access aims at realizing efficient and reliable communications for a massive number of Internet of Things (loT)devices.The main challenge of mMTC is that the BS can efficiently and reliably detect the active devices based on the superposition of their unique signatures from a large pool of uplink devices,among which only a small fraction is active. In this talk,we shall present some recent results of massive access from an optimization perspective.In particular,we shall present optimization formulations and algorithms as well as some phase transition analysis results']"));
    pList.add(new Picture(2, "jpg", "alex2", "['基督徒', '年轻人', '村民', '传统', '老一辈', '鬼神', '勾兑酒', '山寨', '生活', '心思']", "['一方面，山寨食品价格低廉、质量低劣，却给年轻人品尝、接触、和想象都市生活的愿望和可能性。', '山寨食品和勾兑酒在景颇社区中有矛盾含义，对中老年人和一二十岁的年轻人各自意味着两个相反的世界，也透露出献鬼村民和基督徒的不同生活方式', '这两种生活伦理导致基督徒和献鬼村民对山寨食品和勾兑酒的不同态度：对基督徒，酒污染人的单纯，让人兴奋，诱发不该有的心思和行为', '而酒连接人神，承载祭祀传统的老一辈杜绝山寨食品却必须喝酒，种植经济林以来人们不再酿酒，市场勾兑酒给老一辈带来严重的健康问题', '在景颇社区,献鬼村民和基督徒的不同生活方式。', '山寨食品和勾兑酒在景颇社区中有矛盾含义，对中老年人和一二十岁的年轻人各自意味着两个相反的世界，也透露出献鬼村民和基督徒的不同生活方式']"));
    pList.add(new Picture(3, "jpg", "dean3", "hahaha3", "asdfsa3"));
    pList.add(new Picture(4, "jpg", "Mary4", "hahaha4", "asdfsa4"));
    pList.add(new Picture(5, "jpg", "alex5", "hahaha5", "asdfsa5"));
    pList.add(new Picture(6, "jpg", "dean6", "hahaha6", "asdfsa6"));
    pList.add(new Picture(7, "jpg", "Mary7", "hahaha7", "asdfsa7"));
    pList.add(new Picture(8, "jpg", "alex8", "hahaha8", "asdfsa8"));
    pList.add(new Picture(9, "jpg", "dean9", "hahaha9", "asdfsa9"));
    //pList.add(new Picture(10, "jpg", "Mary10", "hahaha10", "asdfsa10"));
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
