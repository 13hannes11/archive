package wettbewerb_csv_foto;

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


        Pixel[] Pixel = new Pixel[0];
        Pixel = tmp.Load(path).toArray(Pixel);
        System.out.println(this.name + this.id + " loaded a new Image");
        getImages().add(tmp);

    }

    private Image avgImage() {
        if (getImages().size() == 0) {
            return null;
        }
        Image avg = new Image();
        return avg;
    }
    /**
     * @return the images
     */
    public ArrayList<Image> getImages() {
        return images;
    }
}
