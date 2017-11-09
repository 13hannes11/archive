package wettbewerb_csv_foto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Wettbewerb_CSV_Foto {

    static Camera uCam;
    static ArrayList<Camera> cam = new ArrayList();

    public static void main(String[] args) throws IOException {
        LoadCameras();
        LoadImages();
        createCSV("C:\\Users\\Hannes\\Desktop\\", "output.csv");
    }

    public static void LoadCameras() {
        uCam = new Camera(0, "Unbekannt");
        for (int i = 1; i <= 5; i++) {
            cam.add(new Camera(i, "Kamera"));
        }
        System.out.println("Cameras added!");
    }

    public static void LoadImages() {
        uCam.AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kanne.ppm");
        System.out.println("Unknown Image Loaded");

        cam.get(0).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera1\\K1Bild1.ppm");
        cam.get(0).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera1\\K1Bild2.ppm");
        cam.get(0).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera1\\K1Bild3.ppm");
        cam.get(0).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera1\\K1Bild4.ppm");
        cam.get(0).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera1\\K1Bild5.ppm");
        System.out.println("Images Camera 1 Loaded");

        cam.get(1).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera2\\K2Bild1.ppm");
        cam.get(1).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera2\\K2Bild2.ppm");
        cam.get(1).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera2\\K2Bild3.ppm");
        cam.get(1).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera2\\K2Bild4.ppm");
        cam.get(1).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera2\\K2Bild5.ppm");
        System.out.println("Images Camera 2 Loaded");

        cam.get(2).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera3\\K3Bild1.ppm");
        cam.get(2).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera3\\K3Bild2.ppm");
        cam.get(2).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera3\\K3Bild3.ppm");
        cam.get(2).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera3\\K3Bild4.ppm");
        cam.get(2).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera3\\K3Bild5.ppm");
        System.out.println("Images Camera 3 Loaded");

        cam.get(3).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera4\\K4Bild1.ppm");
        cam.get(3).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera4\\K4Bild2.ppm");
        cam.get(3).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera4\\K4Bild3.ppm");
        cam.get(3).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera4\\K4Bild4.ppm");
        System.out.println("Images Camera 4 Loaded");

        cam.get(4).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera5\\K5Bild1.ppm");
        cam.get(4).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera5\\K5Bild2.ppm");
        cam.get(4).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera5\\K5Bild3.ppm");
        cam.get(4).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera5\\K5Bild4.ppm");
        cam.get(4).AddImage("C:\\Users\\Hannes\\Documents\\Fotos\\Kamera5\\K5Bild5.ppm");
        System.out.println("Images Camera 5 Loaded");
    }

    public static void createCSV(String path, String filename) throws IOException {
        File file = new File(path, filename);
        if (!file.isFile() && !file.createNewFile()) {
            throw new IOException("Error creating new file: " + file.getAbsolutePath());
        }
        try {
            FileWriter writer = new FileWriter(path + filename);

            writer.append("Foto;Durchschnittlicher Abstand; Streuung" + System.getProperty("line.separator"));

            for (int i = 0; i < cam.size(); i++) {
                    writeLine(writer, cam.get(i));     
            }
            
            writeLine(writer, uCam);


            System.out.println("Written Data to File");

            //generate whatever data you want

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static FileWriter writeLine(FileWriter writer, Camera camera) throws IOException {
        writer.append(camera.writeLine());
        return writer;
    }
}
