/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FaceprocessingController implements Initializable {

    /**
     * Initializes the controller class.
     */
    void run()
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        CascadeClassifier faceDetector = new CascadeClassifier("haarcascade_frontalface_alt.xml");
         VideoCapture camera = new VideoCapture(0);
         
        Mat image = new Mat();
        camera.read(image);
 
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);
 
        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
 
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
        }
 
        String filename = "ouput.png";
        System.out.println(String.format("Writing %s", filename));
        Imgcodecs .imwrite(filename, image);
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        run();    }    
    
}
