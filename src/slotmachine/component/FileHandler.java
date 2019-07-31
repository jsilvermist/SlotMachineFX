package slotmachine.component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileHandler {

  private final Properties prop = new Properties();
  private File file;

  public FileHandler() {
    file = new File("config.properties");
    initFile();
  }

  public FileHandler(String pathname) {
    file = new File(pathname);
    File parentFile = file.getParentFile();
    if (parentFile != null) {
      parentFile.mkdirs();
    }
    initFile();
  }

  private void initFile() {
    try {
      if (file.exists()) {
        prop.load(new FileInputStream(file));
      } else {
        file.createNewFile();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void storeProps() {
    try {
      prop.store(new FileOutputStream(file), null);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getProp(String key, String defaultString) {
    String sValue = prop.getProperty(key, defaultString);
    return sValue;
  }

  public void setProp(String key, String value) {
    prop.setProperty(key, value);
    storeProps();
  }

  public int getProp(String key, int defaultValue) {
    String defaultString = String.valueOf(defaultValue);
    String value = prop.getProperty(key, defaultString);
    return Integer.valueOf(value);
  }

  public void setProp(String key, int value) {
    String sValue = String.valueOf(value);
    prop.setProperty(key, sValue);
    storeProps();
  }

  public long getProp(String key, long defaultValue) {
    String defaultString = String.valueOf(defaultValue);
    String value = prop.getProperty(key, defaultString);
    return Long.valueOf(value);
  }

  public void setProp(String key, long value) {
    String sValue = String.valueOf(value);
    prop.setProperty(key, sValue);
    storeProps();
  }

  public float getProp(String key, float defaultValue) {
    String defaultString = String.valueOf(defaultValue);
    String value = prop.getProperty(key, defaultString);
    return Float.valueOf(value);
  }

  public void setProp(String key, float value) {
    String sValue = String.valueOf(value);
    prop.setProperty(key, sValue);
    storeProps();
  }

  public double getProp(String key, double defaultValue) {
    String defaultString = String.valueOf(defaultValue);
    String value = prop.getProperty(key, defaultString);
    return Double.valueOf(value);
  }

  public void setProp(String key, double value) {
    String sValue = String.valueOf(value);
    prop.setProperty(key, sValue);
    storeProps();
  }

}
