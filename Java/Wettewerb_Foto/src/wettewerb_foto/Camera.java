package wettewerb_foto;

import java.util.ArrayList;

public class Camera {

    private int id;
    private String name;
    private ArrayList<Image> images = new ArrayList();
    public Camera(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int countImages() {
        return getImages().size();
    }

    public String writeLine() {
        String s = "";
        short i = 0;
        for (Image image : images) {
            s = s + this.name + "  " + this.id + i + ";";
            s = s + image.getAbstand() + ";";
            s = s + image.getStreuung() + ";";
            s = s + System.getProperty("line.separator");
            i++;
        }
        s = s.replace(".", ",");
        return s;
    }

    public void AddImage(String path) {
        Image tmp = new Image();


        tmp.Load(path);
        System.out.println(this.name + this.id + " loaded a new Image");
        getImages().add(tmp);

    }
    @Override
    public String toString()
    {
        return name + " " + id;
    }
    public ArrayList<Image> getImages() {
        return images;
    }
}
