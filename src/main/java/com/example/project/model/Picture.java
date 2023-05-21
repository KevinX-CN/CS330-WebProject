package com.example.project.model;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Base64.Encoder;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Picture {

  @Id
  @GeneratedValue
  private int id;
  private String type;
  private String title;
  private String text;
  private String topic;
  private String summary;

  public Picture() {

  }

  public Picture(int id, String type, String title) {
    this.id = id;
    this.type = type;
    this.title = title;
  }

  public Picture(int id, String type, String title, String topic, String summary) {
    this.id = id;
    this.type = type;
    this.title = title;
    this.topic = topic;
    this.summary = summary;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getTopic() {
    return this.topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getSummary() {
    return this.summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getFileName() {
    return "posters/" + this.id + "." + this.type;
  }

  public String getFile() throws IOException {
    InputStream fileInput = Files.newInputStream(Paths.get(this.getFileName()));
    byte[] data = new byte[fileInput.available()];
    fileInput.read(data);
    fileInput.close();
    Encoder base64Encoder = Base64.getEncoder();
    return base64Encoder.encodeToString(data);
  }
}
