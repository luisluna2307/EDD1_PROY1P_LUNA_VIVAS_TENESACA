package controladores;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import modelo.User;
import proyecto.Eye;
import proyecto.Face;
import proyecto.Mouth;
import tdas.CDoublyLinkedList;
import tdas.LinkedList;
import tdas.List;

public class PrincipalPaneController {

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
    private CDoublyLinkedList<Mouth> mouths;
    private CDoublyLinkedList<Eye> eyes;
    private List<ImageView> imageViews;
    private List<ImageView> imageViewsFace;
    private List<ImageView> imageViewsEye;
    private List<ImageView> imageViewsMouth;
    private LinkedList<Face> currentPathFaces;
    private LinkedList<Mouth> currentPathMouths;
    private LinkedList<Eye> currentPathEyes;
    private int currentIndex;
    private  boolean isChangingFace;
    private boolean isChangingEye;
    private boolean isChangingMouth;
    @FXML
    private ImageView imgViewEmoji;
    @FXML
    private ImageView imgview1;
    @FXML
    private ImageView imgview2;
    @FXML
    private ImageView imgview3;
    @FXML
    private ImageView imgview4;
    @FXML
    private ImageView imgview5;
    @FXML
    private ImageView imgview6;
    @FXML
    private ImageView imgViewEyes;
    @FXML
    private ImageView imgViewMouth;
    @FXML
    private Button btnFace;
    @FXML
    private Button btnEye;
    @FXML
    private Button btnMouth;
    
    public void initialize(){
        currentIndex =0;
        faces = Face.loadFaces(App.pathFaces);
        eyes = Eye.loadEyes(App.pathEyes);
        imageViewsFace = new LinkedList<>();
        currentPathFaces = new LinkedList<>();
        currentPathEyes = new LinkedList<>();
        currentPathMouths= new LinkedList<>();
        mouths =  Mouth.loadMouths(App.pathMouths);
        imageViewsEye = new LinkedList<>();
        imageViewsMouth = new LinkedList<>();
        
        User.loadUsers();
        Eye.loadEyes(App.pathEyes);
        Face.loadFaces(App.pathFaces);
        Mouth.loadMouths(App.pathMouths);
        imageViews = new LinkedList<>();
        for (int i = 0; i <= 5; i++) {
            currentPathFaces.addLast(faces.get(i));
        }
        for (int i = 0; i <= 5; i++) {
            currentPathEyes.addLast(eyes.get(i));
        }
        for (int i = 0; i <= 5; i++) {
            currentPathMouths.addLast(mouths.get(i));
        }
        imageViews.addLast(imgview1);
        imageViews.addLast(imgview2);
        imageViews.addLast(imgview3);
        imageViews.addLast(imgview4);
        imageViews.addLast(imgview5);
        imageViews.addLast(imgview6);
        
        
        initialComponents();
        
    }
    

    
    private void initialComponents() {
        faces = Face.loadFaces(App.pathFaces);
        isChangingFace = true;
        //DoublyNodeList<>
        for (int i = 0; i <= 5; i++) {
            
            String currentPath = faces.get(i).getPath()+".png";
            try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath)) {
                Image img = new Image(input);
                ImageView imgview = imageViews.get(i);
                imageViewsFace.addLast(imgview);
                imgview.setImage(img);
                imgview.setFitHeight(50);
                imgview.setFitWidth(50);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }
        hBoxImagesContainers.setSpacing(60);
        hBoxImagesContainers.setAlignment(Pos.CENTER);
        String currentPath = faces.get(0).getPath()+".png";
        try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath)) {
            Image img = new Image(input);
            imgViewEmoji.setImage(img);
            imgViewEmoji.setFitHeight(300);
            imgViewEmoji.setFitWidth(300);
        } catch (IOException ex) {
            System.out.println("Error imagen 1");
        }
    }
    @FXML
    private void next(ActionEvent event) {
        if(isChangingFace){
            for(int i= imageViews.size()-1;i>=0;i--){
                Face actualFace = currentPathFaces.get(i);
                System.out.println(actualFace.getPath());
                Face foundFace = actualFace;
                for (int j = 0; j < faces.size(); j++) {
                    if(faces.get(j).getPath().equals(actualFace.getPath())){
                        foundFace = faces.get(j);
                        break;
                    }
                }
                currentPathFaces.set(i, faces.getNode(faces.getIndex(foundFace)).getNext().getContent());
                ImageView imageView = imageViews.get(i);
                String currentPath = currentPathFaces.get(i).getPath()+".png";
                imageViewsFace.set(i, imageView);
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
            String currentPath1 = currentPathFaces.get(5).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath1)) {
                Image img = new Image(input);
                imgViewEmoji.setImage(img);
                imgViewEmoji.setFitHeight(300);
                imgViewEmoji.setFitWidth(300);
                imgview6.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview6.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }else if(isChangingEye){
            for(int i= imageViews.size()-1;i>=0;i--){
                Eye actualEye = currentPathEyes.get(i);
                System.out.println(actualEye.getPath());
                Eye foundEye = actualEye;
                for (int j = 0; j < eyes.size(); j++) {
                    if(eyes.get(j).getPath().equals(actualEye.getPath())){
                        foundEye = eyes.get(j);
                        break;
                    }
                }
                currentPathEyes.set(i, eyes.getNode(eyes.getIndex(foundEye)).getNext().getContent());
                ImageView imageView = imageViews.get(i);
                String currentPath = currentPathFaces.get(i).getPath()+".png";
                imageViewsFace.set(i, imageView);
                try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath)) {
                    Image img = new Image(input);
                   // ImageView imgview = imageViews.get(i);
                    imageView.setImage(img);
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                } catch (IOException ex) {
                    System.out.println("Error: image not found");
                }
            }
            String currentPath1 = currentPathEyes.get(5).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesEyes + currentPath1)) {
                Image img = new Image(input);
                imgViewEmoji.setImage(img);
                imgViewEmoji.setFitHeight(300);
                imgViewEmoji.setFitWidth(300);
                imgview6.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview6.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }else if(isChangingMouth){
            for(int i= imageViews.size()-1;i>=0;i--){
                Mouth actualMouth = currentPathMouths.get(i);
                Mouth foundMouth = actualMouth;
                for (int j = 0; j < mouths.size(); j++) {
                    if(mouths.get(j).getPath().equals(actualMouth.getPath())){
                        foundMouth = mouths.get(j);
                        break;
                    }
                }
                currentPathMouths.set(i, mouths.getNode(mouths.getIndex(foundMouth)).getNext().getContent());
                ImageView imageView = imageViews.get(i);
                String currentPath = currentPathMouths.get(i).getPath()+".png";
                imageViewsMouth.set(i, imageView);
                try ( FileInputStream input = new FileInputStream(App.fileImagesMouths + currentPath)) {
                    Image img = new Image(input);
                   // ImageView imgview = imageViews.get(i);
                    imageView.setImage(img);
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                } catch (IOException ex) {
                    System.out.println("Error: image not found");
                }
            }
            String currentPath1 = currentPathMouths.get(5).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesMouths + currentPath1)) {
                Image img = new Image(input);
                imgViewEmoji.setImage(img);
                imgViewEmoji.setFitHeight(300);
                imgViewEmoji.setFitWidth(300);
                imgview6.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview6.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }
        
    }

    @FXML
    private void previous(ActionEvent event) {
        if(isChangingFace){
            for(int i= 0;i<imageViews.size();i++){
                Face actualFace = currentPathFaces.get(i);
                System.out.println(actualFace.getPath());
                Face foundFace = actualFace;
                for (int j = 0; j < faces.size(); j++) {
                    if(faces.get(j).getPath().equals(actualFace.getPath())){
                        foundFace = faces.get(j);
                        break;
                    }
                }
                currentPathFaces.set(i, faces.getNode(faces.getIndex(foundFace)).getPrevious().getContent());
                System.out.println(faces.getIndex(actualFace));
                System.out.println(currentPathFaces.get(i).getPath());
                ImageView imageView = imageViews.get(i);
                String currentPath = currentPathFaces.get(i).getPath()+".png";
                imageViewsFace.set(i, imageView);
                try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath)) {
                    Image img = new Image(input);
                   // ImageView imgview = imageViews.get(i);
                    imageView.setImage(img);
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                } catch (IOException ex) {
                    System.out.println("Error imagen 1");
                }
               // System.out.println(currentPaths.get(i).toString());
                System.out.println(currentPathFaces.toString());
            }
            String currentPath1 = currentPathFaces.get(0).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath1)) {
                Image img = new Image(input);
                imgViewEmoji.setImage(img);
                imgViewEmoji.setFitHeight(300);
                imgViewEmoji.setFitWidth(300);
                imgview1.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview1.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }

            System.out.println(currentPathFaces.toString());
            System.out.println(faces.size());
        }else if(isChangingEye){
            for(int i= 0;i<imageViews.size();i++){
                Eye actualEye = currentPathEyes.get(i);
                Eye foundEye = actualEye;
                for (int j = 0; j < eyes.size(); j++) {
                    if(eyes.get(j).getPath().equals(actualEye.getPath())){
                        foundEye = eyes.get(j);
                        break;
                    }
                }
                currentPathEyes.set(i, eyes.getNode(eyes.getIndex(foundEye)).getPrevious().getContent());
                ImageView imageView = imageViews.get(i);
                String currentPath = currentPathEyes.get(i).getPath()+".png";
                imageViewsEye.set(i, imageView);
                try ( FileInputStream input = new FileInputStream(App.fileImagesEyes + currentPath)) {
                    Image img = new Image(input);
                   // ImageView imgview = imageViews.get(i);
                    imageView.setImage(img);
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                } catch (IOException ex) {
                    System.out.println("Error imagen 1");
                }
               // System.out.println(currentPaths.get(i).toString());
                System.out.println(currentPathEyes.toString());
            }
            String currentPath1 = currentPathEyes.get(0).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesEyes + currentPath1)) {
                Image img = new Image(input);
                imgViewEmoji.setImage(img);
                imgViewEmoji.setFitHeight(300);
                imgViewEmoji.setFitWidth(300);
                imgview1.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview1.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }            
        }else if(isChangingMouth){
            for(int i= 0;i<imageViews.size();i++){
                Mouth actualMouth = currentPathMouths.get(i);
                Mouth foundMouth = actualMouth;
                for (int j = 0; j < mouths.size(); j++) {
                    if(mouths.get(j).getPath().equals(actualMouth.getPath())){
                        foundMouth = mouths.get(j);
                        break;
                    }
                }
                currentPathMouths.set(i, mouths.getNode(mouths.getIndex(foundMouth)).getPrevious().getContent());
                ImageView imageView = imageViews.get(i);
                String currentPath = currentPathMouths.get(i).getPath()+".png";
                imageViewsMouth.set(i, imageView);
                try ( FileInputStream input = new FileInputStream(App.fileImagesMouths + currentPath)) {
                    Image img = new Image(input);
                   // ImageView imgview = imageViews.get(i);
                    imageView.setImage(img);
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                } catch (IOException ex) {
                    System.out.println("Error imagen 1");
                }
               // System.out.println(currentPaths.get(i).toString());
                System.out.println(currentPathMouths.toString());
            }
            String currentPath1 = currentPathMouths.get(0).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesMouths + currentPath1)) {
                Image img = new Image(input);
                imgViewEmoji.setImage(img);
                imgViewEmoji.setFitHeight(150);
                imgViewEmoji.setFitWidth(150);
                imgview1.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview1.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }    
        }
        
    }

    @FXML
    private void imgViewClick1(MouseEvent event) {
        if(isChangingFace){
            String currentPath1 = currentPathFaces.get(0).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath1)) {
                Image img = new Image(input);
                imgViewEmoji.setImage(img);
                imgViewEmoji.setFitHeight(300);
                imgViewEmoji.setFitWidth(300);
                imgview1.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview1.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }else if(isChangingEye){
            String currentPath1 = currentPathEyes.get(0).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesEyes + currentPath1)) {
                Image img = new Image(input);
                imgViewEyes.setImage(img);
                imgViewEyes.setFitHeight(300);
                imgViewEyes.setFitWidth(300);
                imgview1.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview1.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }else if(isChangingMouth){
            String currentPath1 = currentPathMouths.get(0).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesMouths + currentPath1)) {
                Image img = new Image(input);
                imgViewMouth.setImage(img);
                imgViewMouth.setFitHeight(150);
                imgViewMouth.setFitWidth(150);
                imgview1.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview1.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }

        
            
    }

    @FXML
    private void imgViewClick2(MouseEvent event) {
       if(isChangingFace){
            String currentPath1 = currentPathFaces.get(1).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath1)) {
                Image img = new Image(input);
                imgViewEmoji.setImage(img);
                imgViewEmoji.setFitHeight(300);
                imgViewEmoji.setFitWidth(300);
                imgview2.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview2.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }else if(isChangingEye){
            String currentPath1 = currentPathEyes.get(1).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesEyes + currentPath1)) {
                Image img = new Image(input);
                imgViewEyes.setImage(img);
                imgViewEyes.setFitHeight(300);
                imgViewEyes.setFitWidth(300);
                imgview2.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview2.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }else if(isChangingMouth){
            String currentPath1 = currentPathMouths.get(1).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesMouths + currentPath1)) {
                Image img = new Image(input);
                imgViewMouth.setImage(img);
                imgViewMouth.setFitHeight(150);
                imgViewMouth.setFitWidth(150);
                imgview2.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview2.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }
    }

    @FXML
    private void imgViewClick3(MouseEvent event) {
        if(isChangingFace){
            String currentPath1 = currentPathFaces.get(2).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath1)) {
                Image img = new Image(input);
                imgViewEmoji.setImage(img);
                imgViewEmoji.setFitHeight(300);
                imgViewEmoji.setFitWidth(300);
                imgview3.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview3.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }else if(isChangingEye){
            String currentPath1 = currentPathEyes.get(2).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesEyes + currentPath1)) {
                Image img = new Image(input);
                imgViewEyes.setImage(img);
                imgViewEyes.setFitHeight(300);
                imgViewEyes.setFitWidth(300);
                imgview3.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview3.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }else if(isChangingMouth){
            String currentPath1 = currentPathMouths.get(2).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesMouths + currentPath1)) {
                Image img = new Image(input);
                imgViewMouth.setImage(img);
                imgViewMouth.setFitHeight(150);
                imgViewMouth.setFitWidth(150);
                imgview3.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview3.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }
    }

    @FXML
    private void imgViewClick4(MouseEvent event) {
        if(isChangingFace){
            String currentPath1 = currentPathFaces.get(3).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath1)) {
                Image img = new Image(input);
                imgViewEmoji.setImage(img);
                imgViewEmoji.setFitHeight(300);
                imgViewEmoji.setFitWidth(300);
                imgview4.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview4.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }else if(isChangingEye){
            String currentPath1 = currentPathEyes.get(3).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesEyes + currentPath1)) {
                Image img = new Image(input);
                imgViewEyes.setImage(img);
                imgViewEyes.setFitHeight(300);
                imgViewEyes.setFitWidth(300);
                imgview4.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview4.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }else if(isChangingMouth){
            String currentPath1 = currentPathMouths.get(3).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesMouths + currentPath1)) {
                Image img = new Image(input);
                imgViewMouth.setImage(img);
                imgViewMouth.setFitHeight(150);
                imgViewMouth.setFitWidth(150);
                imgview4.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview4.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }
    }

    @FXML
    private void imgViewClick5(MouseEvent event) {
        if(isChangingFace){
            String currentPath1 = currentPathFaces.get(4).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath1)) {
                Image img = new Image(input);
                imgViewEmoji.setImage(img);
                imgViewEmoji.setFitHeight(300);
                imgViewEmoji.setFitWidth(300);
                imgview5.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview5.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }else if(isChangingEye){
            String currentPath1 = currentPathEyes.get(4).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesEyes + currentPath1)) {
                Image img = new Image(input);
                imgViewEyes.setImage(img);
                imgViewEyes.setFitHeight(300);
                imgViewEyes.setFitWidth(300);
                imgview5.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview5.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }else if(isChangingMouth){
            String currentPath1 = currentPathMouths.get(4).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesMouths + currentPath1)) {
                Image img = new Image(input);
                imgViewMouth.setImage(img);
                imgViewMouth.setFitHeight(150);
                imgViewMouth.setFitWidth(150);
                imgview5.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview5.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }
        
    }

    @FXML
    private void imgViewClick6(MouseEvent event) {
       if(isChangingFace){
            String currentPath1 = currentPathFaces.get(5).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath1)) {
                Image img = new Image(input);
                imgViewEmoji.setImage(img);
                imgViewEmoji.setFitHeight(300);
                imgViewEmoji.setFitWidth(300);
                imgview6.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview6.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }else if(isChangingEye){
            String currentPath1 = currentPathEyes.get(5).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesEyes + currentPath1)) {
                Image img = new Image(input);
                imgViewEyes.setImage(img);
                imgViewEyes.setFitHeight(100);
                imgViewEyes.setFitWidth(100);
                imgview6.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview6.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }else if(isChangingMouth){
            String currentPath1 = currentPathMouths.get(5).getPath()+".png";
            DropShadow borderEffect = new DropShadow();
            borderEffect.setColor(Color.BLACK);
            borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
            borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
            try ( FileInputStream input = new FileInputStream(App.fileImagesMouths + currentPath1)) {
                Image img = new Image(input);
                imgViewMouth.setImage(img);
                imgViewMouth.setFitHeight(150);
                imgViewMouth.setFitWidth(150);
                imgview6.setEffect(borderEffect);

                KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
                // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
                // Crear un Timeline con el KeyFrame y configurar el evento de finalización
                Timeline timeline = new Timeline(keyFrame);
                timeline.setOnFinished(e -> imgview6.setEffect(null)); // Eliminar el efecto después de 2 segundos
                timeline.play();
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }
    }

    @FXML
    private void btnFaceClick(ActionEvent event) {
        isChangingFace = true;
        isChangingEye = false;
        isChangingMouth = false;
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
        String currentPath = faces.get(0).getPath()+".png";
        try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath)) {
            Image img = new Image(input);
            imgViewEmoji.setImage(img);
            imgViewEmoji.setFitHeight(300);
            imgViewEmoji.setFitWidth(300);
        } catch (IOException ex) {
            System.out.println("Error imagen 1");
        }
    }

    @FXML
    private void btnEyeClick(ActionEvent event) {
        isChangingFace=false;
        isChangingEye = true;
        isChangingMouth = false;
        for (int i = 0; i <= 5; i++) {
            
            String currentPath = eyes.get(i).getPath()+".png";
            try ( FileInputStream input = new FileInputStream(App.fileImagesEyes + currentPath)) {
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
        String currentPath = eyes.get(0).getPath()+".png";
        try ( FileInputStream input = new FileInputStream(App.fileImagesMouths + currentPath)) {
            Image img = new Image(input);
            imgViewEyes.setImage(img);
            imgViewEyes.setFitHeight(300);
            imgViewEyes.setFitWidth(300);
        } catch (IOException ex) {
            System.out.println("Error imagen 1");
        }
    }

    @FXML
    private void btnMouthClick(ActionEvent event) {
        isChangingFace=false;
        isChangingEye = false;
        isChangingMouth = true;
        for (int i = 0; i <= 5; i++) {
            
            String currentPath = mouths.get(i).getPath()+".png";
            try ( FileInputStream input = new FileInputStream(App.fileImagesMouths + currentPath)) {
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
        String currentPath = mouths.get(0).getPath()+".png";
        try ( FileInputStream input = new FileInputStream(App.fileImagesMouths + currentPath)) {
            Image img = new Image(input);
            imgViewMouth.setImage(img);
            imgViewMouth.setFitHeight(300);
            imgViewMouth.setFitWidth(300);
        } catch (IOException ex) {
            System.out.println("Error imagen 1");
        }
    
    }

    
}