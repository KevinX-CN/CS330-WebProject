package com.example.project.model;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Base64.Encoder;
import javafx.util.Pair;
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
  private Pair<String, String> result;

  public Picture() {

  }

  public Picture(int id, String type, String title) {
    this.id = id;
    this.type = type;
    this.title = title;
    this.result=new Pair<>("","");
  }

  public Picture(int id, String type, String title, String topic, String summary) {
    this.id = id;
    this.type = type;
    this.title = title;
    result = new Pair<>(topic, summary);
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

  public String getTopic() {
    return this.result.getKey();
  }

  public void setTopic(String topic) {
    this.result = new Pair<>(topic, this.result.getValue());
  }

  public String getSummary() {
    return this.result.getValue();
  }

  public void setSummary(String summary) {
    this.result = new Pair<>(this.result.getKey(), summary);
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
