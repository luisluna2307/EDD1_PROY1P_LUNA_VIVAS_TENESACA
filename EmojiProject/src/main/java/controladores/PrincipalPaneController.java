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
    private LinkedList<Face> currentPathsFaces;
    private LinkedList<Mouth> currentPathMouths;
    private LinkedList<Eye> currentPathEyes;
    private int currentIndex;
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
    private HBox hBoxImagesContainers1;
    @FXML
    private ImageView imgviewEyes1;
    @FXML
    private ImageView imgviewEyes4;
    @FXML
    private ImageView imgviewEyes5;
    @FXML
    private ImageView imgviewEyes6;
    @FXML
    private ImageView imgviewEyes2;
    @FXML
    private ImageView imgviewEyes3;
    
    public void initialize(){
        currentIndex =0;
        faces = Face.loadFaces(App.pathFaces);
        currentPathsFaces = new LinkedList<>();
        mouths =  Mouth.loadMouths(App.pathMouths);
        
        User.loadUsers();
        Eye.loadEyes(App.pathEyes);
        Face.loadFaces(App.pathFaces);
        Mouth.loadMouths(App.pathMouths);
        imageViews = new LinkedList<>();
        for (int i = 0; i <= 5; i++) {
            currentPathsFaces.addLast(faces.get(i));
        }
        imageViews.addLast(imgview1);
        imageViews.addLast(imgview2);
        imageViews.addLast(imgview3);
        imageViews.addLast(imgview4);
        imageViews.addLast(imgview5);
        imageViews.addLast(imgview6);
        
        
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
        for(int i= imageViews.size()-1;i>=0;i--){
            Face actualFace = currentPathsFaces.get(i);
            System.out.println(actualFace.getPath());
            Face foundFace = actualFace;
            for (int j = 0; j < faces.size(); j++) {
                if(faces.get(j).getPath().equals(actualFace.getPath())){
                    foundFace = faces.get(j);
                    break;
                }
            }
            currentPathsFaces.set(i, faces.getNode(faces.getIndex(foundFace)).getNext().getContent());
            System.out.println(faces.getIndex(actualFace));
            System.out.println(currentPathsFaces.get(i).getPath());
            ImageView imageView = imageViews.get(i);
            String currentPath = currentPathsFaces.get(i).getPath()+".png";
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
           
            System.out.println(currentPathsFaces.toString());
        }
        
        System.out.println(currentPathsFaces.toString());
        System.out.println(faces.size());
        String currentPath1 = currentPathsFaces.get(5).getPath()+".png";
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
        
    }

    @FXML
    private void previous(ActionEvent event) {
        for(int i= 0;i<imageViews.size();i++){
            Face actualFace = currentPathsFaces.get(i);
            System.out.println(actualFace.getPath());
            Face foundFace = actualFace;
            for (int j = 0; j < faces.size(); j++) {
                if(faces.get(j).getPath().equals(actualFace.getPath())){
                    foundFace = faces.get(j);
                    break;
                }
            }
            currentPathsFaces.set(i, faces.getNode(faces.getIndex(foundFace)).getPrevious().getContent());
            System.out.println(faces.getIndex(actualFace));
            System.out.println(currentPathsFaces.get(i).getPath());
            ImageView imageView = imageViews.get(i);
            String currentPath = currentPathsFaces.get(i).getPath()+".png";
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
            System.out.println(currentPathsFaces.toString());
        }
        String currentPath1 = currentPathsFaces.get(0).getPath()+".png";
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
        
        System.out.println(currentPathsFaces.toString());
        System.out.println(faces.size());
    }

    @FXML
    private void imgViewClick1(MouseEvent event) {
        String currentPath1 = currentPathsFaces.get(0).getPath()+".png";
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
        
        
            
    }

    @FXML
    private void imgViewClick2(MouseEvent event) {
        String currentPath2 = currentPathsFaces.get(1).getPath()+".png";
        DropShadow borderEffect = new DropShadow();
        borderEffect.setColor(Color.BLACK);
        borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
        borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
        try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath2)) {
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
    }

    @FXML
    private void imgViewClick3(MouseEvent event) {
        String currenPath3 = currentPathsFaces.get(2).getPath()+".png";
        DropShadow borderEffect = new DropShadow();
        borderEffect.setColor(Color.BLACK);
        borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
        borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
        try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currenPath3)) {
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
    }

    @FXML
    private void imgViewClick4(MouseEvent event) {
        String currenPath4 = currentPathsFaces.get(3).getPath()+".png";
        DropShadow borderEffect = new DropShadow();
        borderEffect.setColor(Color.BLACK);
        borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
        borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
        try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currenPath4)) {
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
    }

    @FXML
    private void imgViewClick5(MouseEvent event) {
        String currenPath5 = currentPathsFaces.get(4).getPath()+".png";
        DropShadow borderEffect = new DropShadow();
        borderEffect.setColor(Color.BLACK);
        borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
        borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
        try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currenPath5)) {
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
        
    }

    @FXML
    private void imgViewClick6(MouseEvent event) {
        String currenPath6 = currentPathsFaces.get(5).getPath()+".png";
        DropShadow borderEffect = new DropShadow();
        borderEffect.setColor(Color.BLACK);
        borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
        borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
         try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currenPath6)) {
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

    private void faceBtnClick(MouseEvent event) {
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

    private void mouthbtnClick(MouseEvent event) {
        mouths = Mouth.loadMouths(App.pathMouths);
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
    private void imgViewEyesClick1(MouseEvent event) {
    }

    @FXML
    private void imgViewEyesClick2(MouseEvent event) {
    }

    @FXML
    private void imgViewEyesClick3(MouseEvent event) {
    }

    @FXML
    private void imgViewEyesClick4(MouseEvent event) {
    }

    @FXML
    private void imgViewEyesClick5(MouseEvent event) {
    }

    @FXML
    private void imgViewEyesClick6(MouseEvent event) {
    }
}