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
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import modelo.User;
import proyecto.Accessories;
import proyecto.Eye;
import proyecto.Eyebrow;
import proyecto.Face;
import proyecto.Mouth;
import tdas.CDoublyLinkedList;
import tdas.LinkedList;
import tdas.List;

public class PrincipalPaneController {

    private User currentUser;
    @FXML
    private HBox hBoxImagesContainers;
    @FXML
    private AnchorPane topContainer;
    @FXML
    private Button nextButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button previusButton;
    @FXML
    private AnchorPane emojiContainer;
    private CDoublyLinkedList<Face> faces;
    private CDoublyLinkedList<Mouth> mouths;
    private CDoublyLinkedList<Eye> eyes;
    private CDoublyLinkedList<Eyebrow> eyebrows;
    private CDoublyLinkedList<Accessories> accessories;
    private List<ImageView> imageViews;
    private List<ImageView> imageViewsFace;
    private List<ImageView> imageViewsEye;
    private List<ImageView> imageViewsMouth;
    private List<ImageView> imageViewsEyebrows;
    private List<ImageView> imageViewsAccessories;
    private LinkedList<Face> currentPathFaces;
    private LinkedList<Mouth> currentPathMouths;
    private LinkedList<Eye> currentPathEyes;
    private LinkedList<Eyebrow> currentPathEyebrows;
    private LinkedList<Accessories> currentPathAccessories;
    private int currentIndex;
    private boolean isChangingFace;
    private boolean isChangingEye;
    private boolean isChangingMouth;
    private boolean isChangingAccessories;
    private boolean isChangingEyebrows;
    double initialXeye;
    double initialYeye;
    double initialXface;
    double initialYface;
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
    @FXML
    private Button btnDeleteFace;
    @FXML
    private Button btnDeleteMouth;
    @FXML
    private Button btnDeleteEyes;
    @FXML
    private Button btnEyebrows;
    @FXML
    private Button btnDeleteEyebrows;
    @FXML
    private Button btnAccessories;
    @FXML
    private Button btnDeleteAccessories;
    @FXML
    private ImageView imgViewEyebrows;
    @FXML
    private ImageView imgViewAccessories;
    @FXML
    private Slider sliderSize;
    @FXML
    private Slider sliderX;
    @FXML
    private Slider sliderY;
    @FXML
    private AnchorPane anchorPaneImgs;

    public void initialize() {
        currentIndex = 0;
        faces = Face.loadFaces(App.pathFaces);
        eyes = Eye.loadEyes(App.pathEyes);
        mouths = Mouth.loadMouths(App.pathMouths);
        eyebrows = Eyebrow.loadEyebrows(App.pathEyebrows);
        accessories = Accessories.loadAccessories(App.pathAccessories);

        imageViewsFace = new LinkedList<>();
        imageViewsEye = new LinkedList<>();
        imageViewsMouth = new LinkedList<>();
        imageViewsEyebrows = new LinkedList<>();
        imageViewsAccessories = new LinkedList<>();
        currentPathFaces = new LinkedList<>();
        currentPathEyes = new LinkedList<>();
        currentPathMouths = new LinkedList<>();
        currentPathEyebrows = new LinkedList();
        currentPathAccessories = new LinkedList<>();

        User.loadUsers();
        Eye.loadEyes(App.pathEyes);
        Face.loadFaces(App.pathFaces);
        Mouth.loadMouths(App.pathMouths);
        Eyebrow.loadEyebrows(App.pathEyebrows);
        Accessories.loadAccessories(App.pathAccessories);
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
        for (int i = 0; i <= 5; i++) {
            currentPathEyebrows.addLast(eyebrows.get(i));
        }
        for (int i = 0; i <= 5; i++) {
            currentPathAccessories.addLast(accessories.get(i));
        }
        imageViews.addLast(imgview1);
        imageViews.addLast(imgview2);
        imageViews.addLast(imgview3);
        imageViews.addLast(imgview4);
        imageViews.addLast(imgview5);
        imageViews.addLast(imgview6);
        initialXeye = imgViewEyes.getLayoutX();
        initialYeye = imgViewEyes.getLayoutY();
        initialXface = imgViewEmoji.getLayoutX();
        initialYface = imgViewEmoji.getLayoutY();

        initialComponents();

    }

    private void initialComponents() {
        faces = Face.loadFaces(App.pathFaces);
        isChangingFace = true;
        for (int i = 0; i <= 5; i++) {
            String currentPath = faces.get(i).getPath() + ".png";
            ImageView imgview = imageViews.get(i);
            try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath)) {
                imageViewsFace.addLast(imgview);
                setImagesViews(input, imgview, 50, 50);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }
        hBoxImagesContainers.setSpacing(60);
        hBoxImagesContainers.setAlignment(Pos.CENTER);
        String currentPath = faces.get(0).getPath() + ".png";
        try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath)) {
            setImagesViews(input, imgViewEmoji, 300, 300);
        } catch (IOException ex) {
            System.out.println("Error imagen 1");
        }
    }

    @FXML
    private void next(ActionEvent event) {
        if (isChangingFace) {
            for (int i = imageViews.size() - 1; i >= 0; i--) {
                Face actualFace = currentPathFaces.get(i);
                System.out.println(actualFace.getPath());
                Face foundFace = actualFace;
                for (int j = 0; j < faces.size(); j++) {
                    if (faces.get(j).getPath().equals(actualFace.getPath())) {
                        foundFace = faces.get(j);
                        break;
                    }
                }
                currentPathFaces.set(i, faces.getNode(faces.getIndex(foundFace)).getNext().getContent());
                ImageView imageView = imageViews.get(i);
                String currentPath = currentPathFaces.get(i).getPath() + ".png";
                imageViewsFace.set(i, imageView);
                try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath)) {
                    setImagesViews(input, imageView, 50, 50);
                } catch (IOException ex) {
                    System.out.println("Error imagen 1");
                }
            }
            String currentPath1 = currentPathFaces.get(5).getPath() + ".png";
            setDropShadow(imgview6);
            try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath1)) {
                setImagesViews(input, imgViewEmoji, 300, 300);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        } else if (isChangingEye) {
            for (int i = imageViews.size() - 1; i >= 0; i--) {
                Eye actualEye = currentPathEyes.get(i);
                System.out.println(actualEye.getPath());
                Eye foundEye = actualEye;
                for (int j = 0; j < eyes.size(); j++) {
                    if (eyes.get(j).getPath().equals(actualEye.getPath())) {
                        foundEye = eyes.get(j);
                        break;
                    }
                }
                currentPathEyes.set(i, eyes.getNode(eyes.getIndex(foundEye)).getNext().getContent());
                ImageView imageView = imageViews.get(i);
                String currentPath = currentPathEyes.get(i).getPath() + ".png";
                imageViewsEye.set(i, imageView);
                try ( FileInputStream input = new FileInputStream(App.fileImagesEyes + currentPath)) {
                    setImagesViews(input, imageView, 50, 50);
                } catch (IOException ex) {
                    System.out.println("Error: image not found");
                }
            }
            String currentPath1 = currentPathEyes.get(5).getPath() + ".png";
            setDropShadow(imgview6);
            try ( FileInputStream input = new FileInputStream(App.fileImagesEyes + currentPath1)) {
                setImagesViews(input, imgViewEyes, 150, 150);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        } else if (isChangingMouth) {
            for (int i = imageViews.size() - 1; i >= 0; i--) {
                Mouth actualMouth = currentPathMouths.get(i);
                Mouth foundMouth = actualMouth;
                for (int j = 0; j < mouths.size(); j++) {
                    if (mouths.get(j).getPath().equals(actualMouth.getPath())) {
                        foundMouth = mouths.get(j);
                        break;
                    }
                }
                currentPathMouths.set(i, mouths.getNode(mouths.getIndex(foundMouth)).getNext().getContent());
                ImageView imageView = imageViews.get(i);
                String currentPath = currentPathMouths.get(i).getPath() + ".png";
                imageViewsMouth.set(i, imageView);
                try ( FileInputStream input = new FileInputStream(App.fileImagesMouths + currentPath)) {
                    setImagesViews(input, imageView, 50, 50);
                } catch (IOException ex) {
                    System.out.println("Error: image not found");
                }
            }
            String currentPath1 = currentPathMouths.get(5).getPath() + ".png";
            setDropShadow(imgview6);
            try ( FileInputStream input = new FileInputStream(App.fileImagesMouths + currentPath1)) {
                setImagesViews(input, imgViewMouth, 150, 150);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        } else if (isChangingEyebrows) {
            for (int i = imageViews.size() - 1; i >= 0; i--) {
                Eyebrow actualEyebrow = currentPathEyebrows.get(i);
                Eyebrow foundEyebrow = actualEyebrow;
                for (int j = 0; j < eyebrows.size(); j++) {
                    if (eyebrows.get(j).getPath().equals(actualEyebrow.getPath())) {
                        foundEyebrow = eyebrows.get(j);
                        break;
                    }
                }
                currentPathEyebrows.set(i, eyebrows.getNode(eyebrows.getIndex(foundEyebrow)).getNext().getContent());
                ImageView imageView = imageViews.get(i);
                String currentPath = currentPathEyebrows.get(i).getPath() + ".png";
                imageViewsEyebrows.set(i, imageView);
                try ( FileInputStream input = new FileInputStream(App.fileImagesEyebrows + currentPath)) {
                    setImagesViews(input, imageView, 50, 50);
                } catch (IOException ex) {
                    System.out.println("Error: image not found");
                }
            }
            String currentPath1 = currentPathEyebrows.get(5).getPath() + ".png";
            setDropShadow(imgview6);
            try ( FileInputStream input = new FileInputStream(App.fileImagesEyebrows + currentPath1)) {
                setImagesViews(input, imgViewEyebrows, 150, 150);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        } else if (isChangingAccessories) {
            for (int i = imageViews.size() - 1; i >= 0; i--) {
                Accessories actualAccessories = currentPathAccessories.get(i);
                Accessories foundAccessorie = actualAccessories;
                for (int j = 0; j < accessories.size(); j++) {
                    if (accessories.get(j).getPath().equals(actualAccessories.getPath())) {
                        foundAccessorie = accessories.get(j);
                        break;
                    }
                }
                currentPathAccessories.set(i, accessories.getNode(accessories.getIndex(foundAccessorie)).getNext().getContent());
                ImageView imageView = imageViews.get(i);
                String currentPath = currentPathAccessories.get(i).getPath() + ".png";
                imageViewsAccessories.set(i, imageView);
                try ( FileInputStream input = new FileInputStream(App.fileImagesAccessories + currentPath)) {
                    setImagesViews(input, imageView, 50, 50);
                } catch (IOException ex) {
                    System.out.println("Error: image not found");
                }
            }
            String currentPath1 = currentPathAccessories.get(5).getPath() + ".png";
            setDropShadow(imgview6);
            try ( FileInputStream input = new FileInputStream(App.fileImagesAccessories + currentPath1)) {
                setImagesViews(input, imgViewAccessories, 150, 150);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }

    }

    @FXML
    private void previous(ActionEvent event) {
        if (isChangingFace) {
            for (int i = 0; i < imageViews.size(); i++) {
                Face actualFace = currentPathFaces.get(i);
                Face foundFace = actualFace;
                for (int j = 0; j < faces.size(); j++) {
                    if (faces.get(j).getPath().equals(actualFace.getPath())) {
                        foundFace = faces.get(j);
                        break;
                    }
                }
                currentPathFaces.set(i, faces.getNode(faces.getIndex(foundFace)).getPrevious().getContent());
                ImageView imageView = imageViews.get(i);
                String currentPath = currentPathFaces.get(i).getPath() + ".png";
                imageViewsFace.set(i, imageView);
                try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath)) {
                    setImagesViews(input, imageView, 50, 50);
                } catch (IOException ex) {
                    System.out.println("Error imagen 1");
                }
            }
            String currentPath1 = currentPathFaces.get(0).getPath() + ".png";
            setDropShadow(imgview1);
            try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath1)) {
                setImagesViews(input, imgViewEmoji, 300, 300);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }

            System.out.println(currentPathFaces.toString());
            System.out.println(faces.size());
        } else if (isChangingEye) {
            for (int i = 0; i < imageViews.size(); i++) {
                Eye actualEye = currentPathEyes.get(i);
                Eye foundEye = actualEye;
                for (int j = 0; j < eyes.size(); j++) {
                    if (eyes.get(j).getPath().equals(actualEye.getPath())) {
                        foundEye = eyes.get(j);
                        break;
                    }
                }
                currentPathEyes.set(i, eyes.getNode(eyes.getIndex(foundEye)).getPrevious().getContent());
                ImageView imageView = imageViews.get(i);
                String currentPath = currentPathEyes.get(i).getPath() + ".png";
                imageViewsEye.set(i, imageView);
                try ( FileInputStream input = new FileInputStream(App.fileImagesEyes + currentPath)) {
                    setImagesViews(input, imageView, 50, 50);
                } catch (IOException ex) {
                    System.out.println("Error imagen 1");
                }
            }
            String currentPath1 = currentPathEyes.get(0).getPath() + ".png";
            setDropShadow(imgview1);

            try ( FileInputStream input = new FileInputStream(App.fileImagesEyes + currentPath1)) {
                setImagesViews(input, imgViewEyes, 150, 150);

            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        } else if (isChangingMouth) {
            for (int i = 0; i < imageViews.size(); i++) {
                Mouth actualMouth = currentPathMouths.get(i);
                Mouth foundMouth = actualMouth;
                for (int j = 0; j < mouths.size(); j++) {
                    if (mouths.get(j).getPath().equals(actualMouth.getPath())) {
                        foundMouth = mouths.get(j);
                        break;
                    }
                }
                currentPathMouths.set(i, mouths.getNode(mouths.getIndex(foundMouth)).getPrevious().getContent());
                ImageView imageView = imageViews.get(i);
                String currentPath = currentPathMouths.get(i).getPath() + ".png";
                imageViewsMouth.set(i, imageView);
                try ( FileInputStream input = new FileInputStream(App.fileImagesMouths + currentPath)) {
                    setImagesViews(input, imageView, 50, 50);
                } catch (IOException ex) {
                    System.out.println("Error imagen 1");
                }
                // System.out.println(currentPaths.get(i).toString());
                System.out.println(currentPathMouths.toString());
            }
            String currentPath1 = currentPathMouths.get(0).getPath() + ".png";
            setDropShadow(imgview1);
            try ( FileInputStream input = new FileInputStream(App.fileImagesMouths + currentPath1)) {
                setImagesViews(input, imgViewMouth, 150, 150);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        } else if (isChangingAccessories) {
            for (int i = 0; i < imageViews.size(); i++) {
                Accessories actualAccessories = currentPathAccessories.get(i);
                Accessories foundAccessorie = actualAccessories;
                for (int j = 0; j < accessories.size(); j++) {
                    if (accessories.get(j).getPath().equals(actualAccessories.getPath())) {
                        foundAccessorie = accessories.get(j);
                        break;
                    }
                }
                currentPathAccessories.set(i, accessories.getNode(accessories.getIndex(foundAccessorie)).getPrevious().getContent());
                ImageView imageView = imageViews.get(i);
                String currentPath = currentPathAccessories.get(i).getPath() + ".png";
                imageViewsAccessories.set(i, imageView);
                try ( FileInputStream input = new FileInputStream(App.fileImagesAccessories + currentPath)) {
                    setImagesViews(input, imageView, 50, 50);
                } catch (IOException ex) {
                    System.out.println("Error imagen 1");
                }
                // System.out.println(currentPaths.get(i).toString());
                System.out.println(currentPathAccessories.toString());
            }
            String currentPath1 = currentPathAccessories.get(0).getPath() + ".png";
            setDropShadow(imgview1);
            try ( FileInputStream input = new FileInputStream(App.fileImagesAccessories + currentPath1)) {
                setImagesViews(input, imgViewAccessories, 150, 150);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        } else if (isChangingEyebrows) {
            for (int i = 0; i < imageViews.size(); i++) {
                Eyebrow actualEyebrows = currentPathEyebrows.get(i);
                Eyebrow foundEyebrows = actualEyebrows;
                for (int j = 0; j < eyebrows.size(); j++) {
                    if (eyebrows.get(j).getPath().equals(actualEyebrows.getPath())) {
                        foundEyebrows = eyebrows.get(j);
                        break;
                    }
                }
                currentPathEyebrows.set(i, eyebrows.getNode(eyebrows.getIndex(foundEyebrows)).getPrevious().getContent());
                ImageView imageView = imageViews.get(i);
                String currentPath = currentPathEyebrows.get(i).getPath() + ".png";
                imageViewsEyebrows.set(i, imageView);
                try ( FileInputStream input = new FileInputStream(App.fileImagesEyebrows + currentPath)) {
                    setImagesViews(input, imageView, 50, 50);
                } catch (IOException ex) {
                    System.out.println("Error imagen 1");
                }
                // System.out.println(currentPaths.get(i).toString());
                System.out.println(currentPathEyebrows.toString());
            }
            String currentPath1 = currentPathEyebrows.get(0).getPath() + ".png";
            setDropShadow(imgview1);
            try ( FileInputStream input = new FileInputStream(App.fileImagesEyebrows + currentPath1)) {
                setImagesViews(input, imgViewEyebrows, 150, 150);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }

    }

    @FXML
    private void imgViewClick1(MouseEvent event) {
        imagesViewsClick(0, imgview1);
    }

    @FXML
    private void imgViewClick2(MouseEvent event) {
        imagesViewsClick(1, imgview2);
    }

    @FXML
    private void imgViewClick3(MouseEvent event) {
        imagesViewsClick(2, imgview3);
    }

    @FXML
    private void imgViewClick4(MouseEvent event) {
        imagesViewsClick(3, imgview4);
    }

    @FXML
    private void imgViewClick5(MouseEvent event) {
        imagesViewsClick(4, imgview5);
    }

    @FXML
    private void imgViewClick6(MouseEvent event) {
        imagesViewsClick(5, imgview6);
    }

    @FXML
    private void btnFaceClick(ActionEvent event) {
        isChangingFace = true;
        isChangingEye = false;
        isChangingMouth = false;
        isChangingEyebrows = false;
        isChangingAccessories = false;
        for (int i = 0; i <= 5; i++) {
            ImageView imgview = imageViews.get(i);
            String currentPath = faces.get(i).getPath() + ".png";
            try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath)) {
                setImagesViews(input, imgview, 50, 50);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }
        String currentPath = faces.get(0).getPath() + ".png";
        try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath)) {
            setImagesViews(input, imgViewEmoji, 300, 300);
        } catch (IOException ex) {
            System.out.println("Error imagen 1");
        }
        resetSliders(imgViewEyes, imgViewEyebrows, imgViewMouth, imgViewAccessories);
        sliderSizeEvent(imgViewEmoji);
        setPositionImage(imgViewEmoji);
    }

    @FXML
    private void btnEyeClick(ActionEvent event) {
        isChangingFace = false;
        isChangingEye = true;
        isChangingMouth = false;
        isChangingEyebrows = false;
        isChangingAccessories = false;
        for (int i = 0; i <= 5; i++) {
            ImageView imgview = imageViews.get(i);
            String currentPath = eyes.get(i).getPath() + ".png";
            try ( FileInputStream input = new FileInputStream(App.fileImagesEyes + currentPath)) {
                setImagesViews(input, imgview, 50, 50);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }
        String currentPath = eyes.get(0).getPath() + ".png";
        try ( FileInputStream input = new FileInputStream(App.fileImagesEyes + currentPath)) {
            setImagesViews(input, imgViewEyes, 150, 150);
        } catch (IOException ex) {
            System.out.println("Error imagen 1");
        }
        resetSliders(imgViewEmoji, imgViewEyebrows, imgViewMouth, imgViewAccessories);
        sliderSizeEvent(imgViewEyes);
        setPositionImage(imgViewEyes);
    }

    @FXML
    private void btnMouthClick(ActionEvent event) {
        isChangingFace = false;
        isChangingEye = false;
        isChangingMouth = true;
        isChangingEyebrows = false;
        isChangingAccessories = false;
        for (int i = 0; i <= 5; i++) {
            ImageView imgview = imageViews.get(i);
            String currentPath = mouths.get(i).getPath() + ".png";
            try ( FileInputStream input = new FileInputStream(App.fileImagesMouths + currentPath)) {
                setImagesViews(input, imgview, 50, 50);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }
        String currentPath = mouths.get(0).getPath() + ".png";
        try ( FileInputStream input = new FileInputStream(App.fileImagesMouths + currentPath)) {
            setImagesViews(input, imgViewMouth, 150, 150);
        } catch (IOException ex) {
            System.out.println("Error imagen 1");
        }
        resetSliders(imgViewEyes, imgViewEyebrows, imgViewEmoji, imgViewAccessories);
        sliderSizeEvent(imgViewMouth);
        setPositionImage(imgViewMouth);

    }

    @FXML
    private void btnEyebrowsClick(ActionEvent event) {
        isChangingFace = false;
        isChangingEye = false;
        isChangingMouth = false;
        isChangingEyebrows = true;
        isChangingAccessories = false;
        for (int i = 0; i <= 5; i++) {
            ImageView imgview = imageViews.get(i);
            String currentPath = eyebrows.get(i).getPath() + ".png";
            try ( FileInputStream input = new FileInputStream(App.fileImagesEyebrows + currentPath)) {
                setImagesViews(input, imgview, 50, 50);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }
        String currentPath = eyebrows.get(0).getPath() + ".png";
        try ( FileInputStream input = new FileInputStream(App.fileImagesEyebrows + currentPath)) {
            setImagesViews(input, imgViewEyebrows, 150, 150);
        } catch (IOException ex) {
            System.out.println("Error imagen 1");
        }
        resetSliders(imgViewEyes, imgViewEmoji, imgViewMouth, imgViewAccessories);
        sliderSizeEvent(imgViewEyebrows);
        setPositionImage(imgViewEyebrows);
    }

    @FXML
    private void btnAccessoriesClick(ActionEvent event) {
        isChangingFace = false;
        isChangingEye = false;
        isChangingMouth = false;
        isChangingEyebrows = false;
        isChangingAccessories = true;
        for (int i = 0; i <= 5; i++) {
            ImageView imgview = imageViews.get(i);
            String currentPath = accessories.get(i).getPath() + ".png";
            try ( FileInputStream input = new FileInputStream(App.fileImagesAccessories + currentPath)) {
                setImagesViews(input, imgview, 50, 50);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }
        String currentPath = accessories.get(0).getPath() + ".png";
        try ( FileInputStream input = new FileInputStream(App.fileImagesAccessories + currentPath)) {
            setImagesViews(input, imgViewAccessories, 150, 150);
        } catch (IOException ex) {
            System.out.println("Error imagen 1");
        }
        resetSliders(imgViewEyes, imgViewEyebrows, imgViewMouth, imgViewEmoji);
        sliderSizeEvent(imgViewAccessories);
        setPositionImage(imgViewAccessories);
    }

    private void imagesViewsClick(int actualIndex, ImageView imgView) {
        if (isChangingFace) {
            String currentPath1 = currentPathFaces.get(actualIndex).getPath() + ".png";
            setDropShadow(imgView);
            try ( FileInputStream input = new FileInputStream(App.fileImagesFaces + currentPath1)) {
                setImagesViews(input, imgViewEmoji, 300, 300);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        } else if (isChangingEye) {
            String currentPath1 = currentPathEyes.get(actualIndex).getPath() + ".png";
            setDropShadow(imgView);
            try ( FileInputStream input = new FileInputStream(App.fileImagesEyes + currentPath1)) {
                setImagesViews(input, imgViewEyes, 150, 150);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        } else if (isChangingMouth) {
            String currentPath1 = currentPathMouths.get(actualIndex).getPath() + ".png";
            setDropShadow(imgView);
            try ( FileInputStream input = new FileInputStream(App.fileImagesMouths + currentPath1)) {
                setImagesViews(input, imgViewMouth, 150, 150);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        } else if (isChangingEyebrows) {
            String currentPath1 = currentPathEyebrows.get(actualIndex).getPath() + ".png";
            setDropShadow(imgView);
            try ( FileInputStream input = new FileInputStream(App.fileImagesEyebrows + currentPath1)) {
                setImagesViews(input, imgViewEyebrows, 150, 150);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        } else if (isChangingAccessories) {
            String currentPath1 = currentPathAccessories.get(actualIndex).getPath() + ".png";
            setDropShadow(imgView);
            try ( FileInputStream input = new FileInputStream(App.fileImagesAccessories + currentPath1)) {
                setImagesViews(input, imgViewAccessories, 150, 150);
            } catch (IOException ex) {
                System.out.println("Error imagen 1");
            }
        }
    }

    private void setDropShadow(ImageView imgview) {
        DropShadow borderEffect = new DropShadow();
        borderEffect.setColor(Color.BLACK);
        borderEffect.setRadius(20); // Ajustar el valor para hacer el borde más grueso
        borderEffect.setSpread(0.6); // Ajustar el valor para controlar la propagación del efecto
        imgview.setEffect(borderEffect);
        KeyValue keyValue = new KeyValue(borderEffect.radiusProperty(), 10);
        // Crear un KeyFrame para especificar la duración y el valor final del KeyValue
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.2), keyValue);
        // Crear un Timeline con el KeyFrame y configurar el evento de finalización
        Timeline timeline = new Timeline(keyFrame);
        timeline.setOnFinished(e -> imgview.setEffect(null)); // Eliminar el efecto después de 2 segundos
        timeline.play();
    }

    private void setImagesViews(FileInputStream input, ImageView imgView, int height, int width) {
        Image img = new Image(input);
        imgView.setImage(img);
        imgView.setFitHeight(height);
        imgView.setFitWidth(width);

    }

    @FXML
    private void btnDeleteFaceClick(ActionEvent event) {
        imgViewEmoji.setImage(null);
    }

    @FXML
    private void btnDeleteMouthClick(ActionEvent event) {
        imgViewMouth.setImage(null);
    }

    @FXML
    private void btnDeleteEyesClick(ActionEvent event) {
        imgViewEyes.setImage(null);
    }

    @FXML
    private void btnDeleteEyebrowsClick(ActionEvent event) {
        imgViewEyebrows.setImage(null);
    }

    @FXML
    private void btnDeleteAccessoriesClick(ActionEvent event) {
        imgViewAccessories.setImage(null);
    }

    @FXML
    private void loadProject(ActionEvent event) {

    }

    @FXML
    private void saveProject(ActionEvent event) {

    }

    @FXML
    private void SliderXEvent(DragEvent event) {

    }

    private void checkBounds(ImageView imageView) {
        double containerWidth = anchorPaneImgs.getWidth();
        double containerHeight = anchorPaneImgs.getHeight();
        double imageWidth = imageView.getBoundsInLocal().getWidth() * imageView.getScaleX();
        double imageHeight = imageView.getBoundsInLocal().getHeight() * imageView.getScaleY();
        double maxWidth = containerWidth - (imageWidth - imageView.getFitWidth());
        double maxHeight = containerHeight - (imageHeight - imageView.getFitHeight());

    }

    private void sliderSizeEvent(ImageView imageView) {
        sliderSize.setMin(0.5);
        sliderSize.setMax(1.0);
        sliderSize.setValue(5.0);
        sliderSize.valueProperty().addListener((observables, oldValue, newValue) -> {
            double zoom = newValue.doubleValue();
            imageView.setScaleX(zoom);
            imageView.setScaleY(zoom);
            checkBounds(imageView);
        });
    }

    private void setPositionImage(ImageView imageView) {

        imageView.translateXProperty().bind(sliderX.valueProperty());
        imageView.translateYProperty().bind(sliderY.valueProperty());
    }

    private void resetSliders(ImageView imgView1, ImageView imgView2, ImageView imgView3, ImageView imgView4) {
        imgView1.translateXProperty().unbind();
        imgView1.translateYProperty().unbind();
        imgView2.translateXProperty().unbind();
        imgView2.translateYProperty().unbind();
        imgView3.translateXProperty().unbind();
        imgView3.translateYProperty().unbind();
        imgView4.translateXProperty().unbind();
        imgView4.translateYProperty().unbind();
        sliderX.setValue(sliderX.getMin());
        sliderY.setValue(sliderY.getMin());
    }

    public void recoverUser(User u) {
        this.currentUser = u;
    }
}
