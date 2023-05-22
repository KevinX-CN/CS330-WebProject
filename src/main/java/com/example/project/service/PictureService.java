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
    pList.add(new Picture(1, "png", "Tea and Health:The secret of Pingwu Fuzhuan brick tea", "['ethnic groups dwelling', 'health- promoting functions', 'systematic study focuses', 'eurotium cristatum pw-1', 'aspergillus niger pw-2', 'fuzhuan brick tea', 'tea famously consumed', 'popular pu-erh tea', 'fermented loose tea', 'autumn green tea']","['Fuzhuan brick tea (FBT),a type of unique brick-shaped dark tea,is generally produced from old,coarse,rough leaves of Camellia sinensis var.sinensis.It is an important beverage for the ethnic groups dwelling in the border regions of southwestern China.The health- promoting functions of FBT,such as its anti-obesity, hypolipidemic,and anti-proliferation effects,have attracted many tea lovers throughout China,Korea,and Japan.Nowadays,FBT is considered another tea famously consumed in China after the upsurge of the popular Pu-erh tea.To date,detailed and clear information on the relationship between microbiota and the quality of Pingwu FBT has not been available, and no systematic study focuses on the impact of Pingwu FBT on health.In this seminar,we introduce the secret of Pingwu Fuzhuan brick tea and the report contents contain the follows:(1)Introduction of FBT', '(3)Insight into effects of isolated Eurotium cristatum PW-1 (MF800948)from Pingwu FBT on the fermentation process and quality characteristics of FBT', '(5) Revealing the effect of Eurotium cristatum PW-1(MF800948)fermentation on quality of autumn green tea','Fuzhuan brick tea (FBT),a type of unique brick-shaped dark tea,is generally produced from old,coarse,rough leaves of Camellia sinensis var.sinensis.It is an important beverage for the ethnic groups dwelling in the border regions of southwestern China.The health- promoting functions of FBT,such as its anti-obesity, hypolipidemic,and anti-proliferation effects,have attracted many tea lovers throughout China,Korea,and Japan.Nowadays,FBT is considered another tea famously consumed in China after the upsurge of the popular Pu-erh tea.To date,detailed and clear information on the relationship between microbiota and the quality of Pingwu FBT has not been available, and no systematic study focuses on the impact of Pingwu FBT on health.In this seminar,we introduce the secret of Pingwu Fuzhuan brick tea and the report contents contain the follows:(1)Introduction of FBT']"));
    pList.add(new Picture(2, "png", "用于人机交互的柔性机电转换器件", "['intelligent living environment', 'future smart society', 'develop flexible electronics', 'flexible transducers', 'fully interconnected', 'grand challenge', 'critically essential', 'interactive sensing', 'actuating systems', 'human-machine interfaces']","['A fully interconnected and intelligent living env', 'A fully interconnected and intelligent living environment has been a grand challenge for future smart society and it is critically essential to develop the interactive sensing and actuating systems to bridge the human-machine interfaces.In this talk,Dr.Junwen Zhong will introduce various sensors,actuators,and robots that are fabricated based on piezoelectret and piezoelectric effects.Specifically,the electrical properties of the materials and the mechanical structures of the devices are designed and optimized,in order to improve the output and durability of such flexible transducers.These flexible transducers have been successfully demonstrated with potential applications in personal mobile-health, haptics feedback,and soft robotics,etc.These works not only offer new valuable insights but also will open up new perspectives to develop flexible electronics and human-machine interactivity with high properties', 'A fully interconnected and intelligent living environment has been a grand challenge for future smart society and it is critically essential to develop the interactive sensing and actuating systems to bridge the human-machine interfaces.In this talk,Dr.Junwen Zhong will introduce various sensors,actuators,and robots that are fabricated based on piezoelectret and piezoelectric effects.Specifically,the electrical properties of the materials and the mechanical structures of the devices are designed and optimized,in order to improve the output and durability of such flexible transducers.These flexible transducers have been successfully demonstrated with potential applications in personal mobile-health, haptics feedback,and soft robotics,etc.These works not only offer new valuable insights but also will open up new perspectives to develop flexible electronics and human-machine interactivity with high properties']"));
    pList.add(new Picture(3, "png", "植物适应温度变化分子机理", "['maintain agricultural productivities', 'e3 ligase xbat', 'well-conserved response call', 'nuclear genes involved', 'membrane-associated transcription factors', 'heat stress upregulates', 'heat stress conditions', 'growth-promoting factor pif4', 'frequent extreme temperature', 'elevated ambient temperature']","[' Climate change poses major challenges to agricult', 'Climate change poses major challenges to agriculture.One consequence of climate change is that plants have to cope with greater ambient temperature fluctuations and more frequent extreme temperature changes.Research on the fundamental mechanisms through which plants respond and adapt to temperature changes will be vital to maintain agricultural productivities.Currently we are using thermomorphogenesis as a model system to understand how plants coordinate their growth and development with elevated ambient temperature.EARLY FLOWERING3 (ELF3), a recently proposed thermosensor,negatively regulates protein activity of the growth-promoting factor PIF4,and such an inhibitory effect is subjected to attenuation at warm temperatures.Recently we found that B-box family protein BBX18 recruits the E3 ligase XBAT to ubiquitinate and degrade ELF3 at warm ambient temperatures,which releases the inhibitory effect of ELF3 on PIF4 activity during plant thermomorphogenesis.Heat stress denatures proteins in the ER and elicits a well-conserved response call the Unfolded Protein Response (UPR)for transcriptional upregulation of nuclear genes involved in protein folding, vesicle trafficking,and protein degradation.We have identified several membrane-associated transcription factors that serve as sensors/transducers of the ER stress in plants.Two of them,bZIP28 and bZIP60,are activated via distinct mechanisms and are important for maintaining fertility during the reproductive stage in Arabidopsis.Heat stress upregulates the expression of OsNTL3,which encodes a membrane-associated NAC transcription factor in rice,and such regulation depends on OsbZIP74,the orthologue of Arabidopsis bZIP60.OsNTL3 mediates the communication between the plasma membrane and the nucleus under heat stress conditions', 'Climate change poses major challenges to agriculture.One consequence of climate change is that plants have to cope with greater ambient temperature fluctuations and more frequent extreme temperature changes.Research on the fundamental mechanisms through which plants respond and adapt to temperature changes will be vital to maintain agricultural productivities.Currently we are using thermomorphogenesis as a model system to understand how plants coordinate their growth and development with elevated ambient temperature.EARLY FLOWERING3 (ELF3), a recently proposed thermosensor,negatively regulates protein activity of the growth-promoting factor PIF4,and such an inhibitory effect is subjected to attenuation at warm temperatures.Recently we found that B-box family protein BBX18 recruits the E3 ligase XBAT to ubiquitinate and degrade ELF3 at warm ambient temperatures,which releases the inhibitory effect of ELF3 on PIF4 activity during plant thermomorphogenesis.Heat stress denatures proteins in the ER and elicits a well-conserved response call the Unfolded Protein Response (UPR)for transcriptional upregulation of nuclear genes involved in protein folding, vesicle trafficking,and protein degradation.We have identified several membrane-associated transcription factors that serve as sensors/transducers of the ER stress in plants.Two of them,bZIP28 and bZIP60,are activated via distinct mechanisms and are important for maintaining fertility during the reproductive stage in Arabidopsis.Heat stress upregulates the expression of OsNTL3,which encodes a membrane-associated NAC transcription factor in rice,and such regulation depends on OsbZIP74,the orthologue of Arabidopsis bZIP60.OsNTL3 mediates the communication between the plasma membrane and the nucleus under heat stress conditions']"));
    pList.add(new Picture(4, "png", "Massive Random Access for 5G and Beyond:An Optimization Perspective", "['wireless networks defined', 'internationa telecommunication union', 'conventional human-type communication', 'massive access aims', 'present optimization formulations', 'active devices based', 'massive access', 'optimization perspective', 'realizing efficient', 'reliable communications']","[' Massive access.also known as s massive connectivi', 'Massive access.also known as s massive connectivity massive machine-type communication (mMTC).is one of the three main use cases s of the fifth-generation(5G and beyond 5G (B5G) wireless networks defined by the internationa Telecommunication Union.Different from conventional human-type communication,massive access aims at realizing efficient and reliable communications for a massive number ot nternet of Things (loT)devices.The main challenge of mMTC is that the BS can efficiently and reliably detect the active devices based on the superposition of their unique signatures from a large pool of uplink devices,among which only a small fraction is active n this talk,we shall present some recent results of massive access from an optimization perspective.In particular,we shall present optimization formulations and algorithms as well s some phase transition analysis results', 'Massive access.also known as s massive connectivity massive machine-type communication (mMTC).is one of the three main use cases s of the fifth-generation(5G and beyond 5G (B5G) wireless networks defined by the internationa Telecommunication Union.Different from conventional human-type communication,massive access aims at realizing efficient and reliable communications for a massive number ot nternet of Things (loT)devices.The main challenge of mMTC is that the BS can efficiently and reliably detect the active devices based on the superposition of their unique signatures from a large pool of uplink devices,among which only a small fraction is active n this talk,we shall present some recent results of massive access from an optimization perspective.In particular,we shall present optimization formulations and algorithms as well s some phase transition analysis results']"));

    this.pictureRepository.saveAll(pList);
  }

  public void addPicture(Picture p) {
    this.pictureRepository.saveAll(List.of(p));
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
