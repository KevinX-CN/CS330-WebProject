package com.example.project.controller;

import com.example.project.model.Picture;
import com.example.project.service.PictureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

  private final PictureService pictureService;

  public ViewController(PictureService pictureService) {
    this.pictureService = pictureService;
  }

  @GetMapping("/view/{page}/{size}")
  public Page<Picture> getView(@PathVariable("page") Integer page,
    @PathVariable("size") Integer size) {
    Pageable p = PageRequest.of(page - 1, size);
    return pictureService.getPictures(p);
  }
}
