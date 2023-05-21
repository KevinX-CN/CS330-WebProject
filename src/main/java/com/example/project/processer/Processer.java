package com.example.project.processer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Processer {

  public static String OCR(String picturePath) throws TesseractException {
    ITesseract tesseract = new Tesseract();
    tesseract.setDatapath("src\\main\\resources\\tessdata");
    tesseract.setLanguage("eng+chi_sim");
    File imgDir = new File(picturePath);
    return tesseract.doOCR(imgDir);
  }

  public static String OCR2(String picturePath)
    throws TesseractException, IOException, InterruptedException {
    Runtime runtime = Runtime.getRuntime();
    Process process = runtime.exec("python src/main/python/topic/K_OCR.py \""+picturePath+"\"");
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    process.waitFor();
    String s=reader.readLine();
    reader.close();
    int exitValue = process.exitValue();
    if (exitValue == 0) {
      System.out.println("进程正常结束");
    } else {
      System.out.println("进程异常结束");
    }
    return s;
  }

  public static String generateTopic(String text) throws IOException, InterruptedException {
    Runtime runtime = Runtime.getRuntime();
    Process process = runtime.exec("python src/main/python/topic/Keywords.py \""+text+"\"");
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    process.waitFor();
    String s=reader.readLine();
    reader.close();
    int exitValue = process.exitValue();
    if (exitValue == 0) {
      System.out.println("进程正常结束");
    } else {
      System.out.println("进程异常结束");
    }
    return s;
  }

  public static String generateSummary(String text)
    throws IOException, InterruptedException {
    Runtime runtime = Runtime.getRuntime();
    Process process = runtime.exec("python src/main/python/summary1/main.py \""+text+"\"");
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    process.waitFor();
    String s=reader.readLine();
    reader.close();
    int exitValue = process.exitValue();
    if (exitValue == 0) {
      System.out.println("进程正常结束");
    } else {
      System.out.println("进程异常结束");
    }
    return s;
  }

  public static String generateSummary2(String text)
    throws IOException, InterruptedException {
    Runtime runtime = Runtime.getRuntime();
    Process process = runtime.exec("python src/main/python/summary2/Untitled2.py \""+text+"\"");
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    process.waitFor();
    String s=reader.readLine();
    reader.close();
    int exitValue = process.exitValue();
    if (exitValue == 0) {
      System.out.println("进程正常结束");
    } else {
      System.out.println("进程异常结束");
    }
    return s;
  }

  public static void main(String[] args)
    throws TesseractException, IOException, InterruptedException {
    String text=OCR2("46.png");
    System.out.println(text);
    //System.out.println(generateSummary(text));
  }
}

