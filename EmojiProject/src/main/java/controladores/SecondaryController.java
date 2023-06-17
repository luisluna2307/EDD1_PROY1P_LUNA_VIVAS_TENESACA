package controladores;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.User;
import proyecto.Eye;
import proyecto.Face;
import proyecto.Mouth;
import tdas.CDoublyLinkedList;
import tdas.LinkedList;
import tdas.List;

public class SecondaryController {

    @FXML
    private HBox hBoxImagesContainers;
    @FXML
    private AnchorPane topContainer;
    @FXML
    private Button nextButton;
    @FXML
    private Button previusButton;
    @FXML
    private AnchorPane emojiContainer;
    private CDoublyLinkedList<Face> faces;
    private List<ImageView> imageViews;
    private int currentIndex;
    
    public void initialize(){
        currentIndex =0;
        faces = Face.loadFaces(App.pathFaces);
        User.loadUsers();
        Eye.loadEyes(App.pathEyes);
        Face.loadFaces(App.pathFaces);
        Mouth.loadMouths(App.pathMouths);
        imageViews = new LinkedList<>();
        for (int i = 0; i <= 5; i++) {
            ImageView imageView = new ImageView();
            imageViews.addLast(imageView);
            hBoxImagesContainers.getChildren().add(imageView);
        }
        cargarPrincipales();
        
    }
    
    private void cargarPrincipales() {
        faces = Face.loadFaces(App.pathFaces);
        //DoublyNodeList<>
        for (int i = 0; i <= 5; i++) {
            
            String currentPath = faces.get(i).getPath()+".png";
            try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath)) {
                Image img = new Image(input);
                ImageView imgview = imageViews.get(i);
                imgview.setImage(img);
                imgview.setFitHeight(50);
                imgview.setFitWidth(50);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }
        hBoxImagesContainers.setSpacing(60);
        hBoxImagesContainers.setAlignment(Pos.CENTER);
    }
    @FXML
    private void next(ActionEvent event) {
        
        
        //for(int j=)
        for(int i= 0;i<imageViews.size();i++){
            ImageView imageView = imageViews.get(i);
            currentIndex = (currentIndex+1) % faces.size();
            String currentPath = faces.get(currentIndex).getPath()+".png";
            try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath)) {
                Image img = new Image(input);
               // ImageView imgview = imageViews.get(i);
                imageView.setImage(img);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }
        System.out.println(faces.size());
        
    }

    @FXML
    private void previous(ActionEvent event) {
    }
}