package cwd.shiinfo.tablayoutwithjsondata;

/**
 * Created by Kavitha on 13/10/2020.
 */
public class Hero {
   private String imageurl;

    public String getImageurl() {
        return imageurl;
    }

    public String getName() {
        return name;
    }

    public Hero(String imageurl, String name) {
        this.imageurl = imageurl;
        this.name = name;
    }

    private String name;
}
