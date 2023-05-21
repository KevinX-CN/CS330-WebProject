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

  public static String generateTopic(String text) throws IOException, InterruptedException {
    //获取执行进程
    Runtime runtime = Runtime.getRuntime();
    System.out.println(System.getProperty("user.dir"));
    Process process = runtime.exec("python src/main/python/topic/Keywords.py");
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
    //获取执行进程
    Runtime runtime = Runtime.getRuntime();
    Process process = runtime.exec("python summary.py");

    //读取输入流
    InputStream inputStream = process.getInputStream();
    //将字节流转成字符流
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    //字符缓冲区
    String s="";
    char[] c = new char[1024];
    int len = -1;
    while ((len = inputStreamReader.read(c)) != -1) {
      s = new String(c, 0, len);
      System.out.print(s);
    }
    inputStream.close();
    inputStreamReader.close();
    process.waitFor();
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
    String text=OCR("46.png").replace("\n", "").replace("'","");
    System.out.println(generateTopic(text.replace("\n", "")));
  }
}

