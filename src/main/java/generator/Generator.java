package generator;

import com.google.gson.Gson;
import dao.Database;
import dao.PersonDAO;
import model.Event;
import model.Person;
import service.FillService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;

public class Generator {
  public Random generator;
  public MNames mNames;
  public FNames fNames;
  public SNames sNames;
  public LocationData locationData;
  private int DEFAULT_GENERATIONS = 4;
  private int CURRENT_YEAR = 2022;

  public Generator() {
    generator = new Random();
    try {
      Gson gson=new Gson();
      Reader reader = new FileReader("json/locations.json");
      locationData = (LocationData)gson.fromJson(reader, LocationData.class);
      reader = new FileReader("json/mnames.json");
      mNames = (MNames)gson.fromJson(reader, MNames.class);
      reader = new FileReader("json/fnames.json");
      fNames = (FNames)gson.fromJson(reader, FNames.class);
      reader = new FileReader("json/snames.json");
      sNames = (SNames)gson.fromJson(reader, SNames.class);
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public class Location {
    String latitude;
    String longitude;
    String city;
    String country;

    public Float getLatitude() {
      return Float.parseFloat(latitude);
    }

    public Float getLongitude() {
      return Float.parseFloat(longitude);
    }

    public String getCity() {
      return city;
    }

    public String getCountry() {
      return country;
    }
  }
  public class LocationData {
    Location[] data;
  }
  public class MNames {
    String[] data;
  }
  public class FNames {
    String[] data;
  }
  public class SNames {
    String[] data;
  }
  public String getRandomFName() {
    int randIndex = generator.nextInt(fNames.data.length - 1);
    return fNames.data[randIndex];
  }
  public String getRandomMName() {
    int randIndex = generator.nextInt(mNames.data.length - 1);
    return mNames.data[randIndex];
  }
  public String getRandomSName() {
    int randIndex = generator.nextInt(sNames.data.length - 1);
    return sNames.data[randIndex];
  }
  public Location getRandomLocation() {
    int randIndex = generator.nextInt(locationData.data.length - 1);
    return locationData.data[randIndex];
  }


}
