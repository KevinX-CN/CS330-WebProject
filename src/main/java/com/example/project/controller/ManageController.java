package com.example.project.controller;

import com.example.project.model.Picture;
import com.example.project.processer.Processer;
import com.example.project.service.PictureService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ManageController {

  private final PictureService pictureService;

  public ManageController(PictureService pictureService) {
    this.pictureService = pictureService;
  }

  private int generateId() {
    return this.pictureService.getMaxId() + 1;
  }

  @PostMapping("/upload")
  public int upFile(@RequestParam("file") MultipartFile file)
    throws IOException, TesseractException, InterruptedException {
    if (file != null) {
      int id = generateId();
      String type = file.getOriginalFilename()
        .substring(file.getOriginalFilename().length() - 4,
          file.getOriginalFilename().length());
      String name = file.getOriginalFilename()
        .substring(0, file.getOriginalFilename().length() - 4);
      Picture picture = new Picture(id, type, name);
      File mkdir = new File("posters");
      if (!mkdir.exists()) {
        mkdir.mkdirs();
      }
      FileOutputStream os = new FileOutputStream(
        mkdir.getPath() + "\\" + picture.getFileName());
      InputStream in = file.getInputStream();
      int bit;
      while ((bit = in.read()) != -1) {
        os.write(bit);
      }
      os.flush();
      in.close();
      os.close();
      picture.setText(Processer.OCR(picture.getFileName()));
      picture.setTopic(Processer.generateTopic(picture.getFileName()));
      picture.setTopic(Processer.generateSummary(picture.getFileName()));
      this.pictureService.addPicture(picture);
      return 200;
    } else {
      return 401;
    }
  }
}
