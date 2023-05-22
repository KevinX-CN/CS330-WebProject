package com.example.project.controller;

import com.example.project.model.Picture;
import com.example.project.service.PictureService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewController {

  private final PictureService pictureService;

  public ViewController(PictureService pictureService) {
    this.pictureService = pictureService;
  }

  @GetMapping(value = "/view")
  @CrossOrigin
  public List<Picture> getView(@RequestParam(value = "page") Integer page) {
    System.out.println("Viewing");
    return pictureService.getPicturesByPage(page);
  }
}
