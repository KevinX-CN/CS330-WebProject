package com.example.project.controller;

import com.example.project.model.Picture;
import com.example.project.processer.Processer;
import com.example.project.service.PictureService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ManageController {

  private final PictureService pictureService;

  public ManageController(PictureService pictureService) {
    this.pictureService = pictureService;
  }

  private int generateId() {
    return this.pictureService.getMaxId() + 1;
  }

  @PostMapping("/upload")
  @CrossOrigin
  public Picture uploadFile(@RequestParam("file") MultipartFile file)
    throws IOException, TesseractException, InterruptedException, IOException, TesseractException {
    if (file != null) {
      int id = generateId();
      String type = file.getOriginalFilename()
        .substring(file.getOriginalFilename().length() - 3,
          file.getOriginalFilename().length());
      String name = file.getOriginalFilename()
        .substring(0, file.getOriginalFilename().length() - 4);
      Picture picture = new Picture(id, type, name);
      File mkdir = new File("posters");
      if (!mkdir.exists()) {
        mkdir.mkdirs();
      }
      FileOutputStream os = new FileOutputStream(picture.getFileName());
      InputStream in = file.getInputStream();
      int bit;
      while ((bit = in.read()) != -1) {
        os.write(bit);
      }
      os.flush();
      in.close();
      os.close();
      String text=(Processer.OCR2(picture.getFileName()));
      picture.setTopic(Processer.generateTopic(text));
      picture.setSummary(Processer.generateSummary(text));
      this.pictureService.addPicture(picture);
      return picture;
    } else {
      return null;
    }
  }
}
