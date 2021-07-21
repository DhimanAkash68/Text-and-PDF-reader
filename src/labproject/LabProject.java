package labproject;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.AudioPlayer;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;
import java.io.File;
import java.util.Scanner;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFileFormat.Type;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class LabProject extends Application {

    Scene scene, scene1, scene2, scene3, scene4, scene5;
    static File selectedFile, selectedFile0;
    String Filename, name, fname;
    static AudioPlayer audioPlayer;
    static MediaPlayer mediaplayer;
    Duration duration;
    ProgressBar progressbar;

    @Override
    public void start(Stage stage) {
        FileChooser fileChooser = new FileChooser();

        Voice helloVoice = VoiceManager.getInstance().getVoice("kevin16");
        if (helloVoice != null) {
            helloVoice.allocate();
        }

        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
        }
        Font font = Font.font("Brush Script MT", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 15);
        TextField textField2 = new TextField();
        textField2.setLayoutX(120);
        textField2.setLayoutY(50);
        textField2.setPromptText("save as .wav file");

        TextField textField3 = new TextField();
        textField3.setLayoutX(80);
        textField3.setLayoutY(50);
        textField3.setPromptText("First Page No");

        TextField textField31 = new TextField();
        textField31.setLayoutX(80);
        textField31.setLayoutY(85);
        textField31.setPromptText("Last Page No");

        TextField textField32 = new TextField();
        textField32.setLayoutX(80);
        textField32.setLayoutY(120);
        textField32.setPromptText("Save with name ");

        Label label2 = new Label("Save with name ");
        label2.setLayoutX(33);
        label2.setLayoutY(50);

        Button btn = new Button();
        btn.setText("Go");
        btn.setMaxHeight(40);
        btn.setMaxWidth(100);
        btn.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        btn.setTextFill(Color.BLUEVIOLET);
        btn.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        btn.setStyle("-fx-font-size:20");
        btn.setFont(font);
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene1);
            }
        });

        Button btn1 = new Button();
        btn1.setText("Pdf file");

        btn1.setMinHeight(40);
        btn1.setMinWidth(200);

        btn1.setLayoutX(60);
        btn1.setLayoutY(40);
        btn1.setFont(Font.font("Brush Script MT", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 15));
        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene3);
            }
        });

        Button btn12 = new Button();
        btn12.setText("Text File");
        btn12.setMinHeight(40);
        btn12.setMinWidth(200);
        btn12.setLayoutX(60);
        btn12.setLayoutY(100);
        btn12.setFont(Font.font("Brush Script MT", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 15));
        btn12.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene2);
            }
        });

        Button btn13 = new Button();
        btn13.setText("Back");
        btn13.setLayoutX(120);
        btn13.setLayoutY(150);

        btn13.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene);
            }
        });

        Button btn2 = new Button();
        btn2.setText("Record");
        btn2.setLayoutX(120);
        btn2.setLayoutY(100);
        btn2.setFont(Font.font("Brush Script MT", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 15));
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    selectedFile = fileChooser.showOpenDialog(stage);

                    File file = selectedFile;

                    Filename = "C:\\Users\\CZ\\Desktop\\";
                    name = Filename + textField2.getText();

                    fname = name + ".wav";
                    audioPlayer = new SingleFileAudioPlayer(name, Type.WAVE);

                    voice.setAudioPlayer(audioPlayer);

                    Scanner scan = new Scanner(file);

                    while (scan.hasNextLine()) {
                        voice.speak(scan.nextLine());

                    }

                    audioPlayer.close();

                } catch (Exception ex) {
                    System.err.println(ex);
                }
                mediaplayer = new MediaPlayer(new Media(new File(fname).toURI().toString()));

            }
        });

        Button btn21 = new Button();
        btn21.setText("Play");
        btn21.setLayoutX(120);
        btn21.setLayoutY(140);
        btn21.setFont(Font.font("Brush Script MT", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 15));
        btn21.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                stage.setScene(scene5);

            }
        });

        Button btn22 = new Button();
        btn22.setText("Back");
        btn22.setLayoutX(120);
        btn22.setLayoutY(180);

        btn22.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene1);
            }
        });

        Button btn3 = new Button();
        btn3.setText("Select File");
        btn3.setMinHeight(40);
        btn3.setMinWidth(200);

        btn3.setLayoutX(80);
        btn3.setLayoutY(150);
        btn3.setFont(Font.font("Brush Script MT", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 25));
        btn3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

            }
        });

        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    selectedFile = fileChooser.showOpenDialog(stage);
                    int startpage = Integer.parseInt(textField3.getText());
                    int endpage = Integer.parseInt(textField31.getText());
                    File file = selectedFile;

                    Filename = "C:\\Users\\CZ\\Desktop\\";
                    name = Filename + textField32.getText();

                    fname = name + ".wav";
                    audioPlayer = new SingleFileAudioPlayer(name, Type.WAVE);

                    helloVoice.setAudioPlayer(audioPlayer);

                    PDDocument document = PDDocument.load(file);

                    int count = document.getNumberOfPages();

                    PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                    stripper.setSortByPosition(true);
                    PDFTextStripper Tstripper = new PDFTextStripper();

                    Tstripper.setStartPage(startpage);
                    Tstripper.setEndPage(endpage);

                    String str = Tstripper.getText(document);

                    Scanner scan = new Scanner(str);

                    while (scan.hasNextLine()) {
                        helloVoice.speak(scan.nextLine());

                    }

                    audioPlayer.close();
                    stage.setScene(scene4);

                } catch (Exception ex) {
                    System.err.println(ex);
                }
                mediaplayer = new MediaPlayer(new Media(new File(fname).toURI().toString()));

            }
        });

        Button btn31 = new Button();
        btn31.setText("Back");
        btn31.setLayoutX(80);
        btn31.setLayoutY(210);
        btn31.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene1);
            }
        });

        Button PlayButton4 = new Button("PLAY/RESUME");
        PlayButton4.setLayoutX(120);
        PlayButton4.setLayoutY(25);
        PlayButton4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //  mediaplayer = new MediaPlayer(new Media(new File("C:/Users/CZ/Desktop/audio.wav").toURI().toString()));
                mediaplayer.play();

            }

        });
        Button PauseButton4 = new Button("PAUSE");
        PauseButton4.setLayoutX(120);
        PauseButton4.setLayoutY(60);
        PauseButton4.setOnAction(event -> {
            mediaplayer.pause();
        });

        Button ResetButton4 = new Button("RESET");
        ResetButton4.setLayoutX(120);
        ResetButton4.setLayoutY(90);

        ResetButton4.setOnAction(event -> {
            mediaplayer.seek(Duration.ZERO);
        });

        Button btn4 = new Button("BACK");
        btn4.setLayoutX(120);
        btn4.setLayoutY(240);
        btn4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene3);
            }
        });

        Slider vol4 = new Slider();
        vol4.setLayoutX(120);
        vol4.setLayoutY(120);
        vol4.setShowTickLabels(true);
        vol4.setShowTickMarks(true);
        vol4.setValue(100);
        vol4.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable ov) {

                if (vol4.isValueChanging()) {
                    mediaplayer.setVolume(vol4.getValue() / 100.0);
                }
            }

        });
        Slider timeslider4 = new Slider();
        timeslider4.setLayoutX(120);
        timeslider4.setLayoutY(160);
        timeslider4.setShowTickLabels(true);
        timeslider4.setShowTickMarks(true);

        timeslider4.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable ov) {
                duration = mediaplayer.getMedia().getDuration();
                if (timeslider4.isValueChanging()) {

                    mediaplayer.seek(duration.multiply(timeslider4.getValue() / 100));
                }
            }
        });

        Button PlayButton5 = new Button("PLAY/RESUME");
        PlayButton5.setLayoutX(120);
        PlayButton5.setLayoutY(25);
        PlayButton5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                mediaplayer.play();

            }

        });
        Button PauseButton5 = new Button("PAUSE");
        PauseButton5.setLayoutX(120);
        PauseButton5.setLayoutY(60);
        PauseButton5.setOnAction(event -> {
            mediaplayer.pause();
        });

        Button ResetButton5 = new Button("RESET");
        ResetButton5.setLayoutX(120);
        ResetButton5.setLayoutY(90);

        ResetButton5.setOnAction(event -> {
            mediaplayer.seek(Duration.ZERO);
        });

        Button btn5 = new Button("BACK");
        btn5.setLayoutX(120);
        btn5.setLayoutY(240);
        btn5.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene2);
            }
        });

        Slider vol5 = new Slider();
        vol5.setLayoutX(120);
        vol5.setLayoutY(125);
        vol5.setShowTickLabels(true);
        vol5.setShowTickMarks(true);
        vol5.setValue(100);
        vol5.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable ov) {

                if (vol5.isValueChanging()) {
                    mediaplayer.setVolume(vol5.getValue() / 100.0);
                }
            }

        });

        Slider timeslider5 = new Slider();
        timeslider5.setLayoutX(120);
        timeslider5.setLayoutY(160);
        timeslider5.setShowTickLabels(true);
        timeslider5.setShowTickMarks(true);

        timeslider5.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable ov) {
                duration = mediaplayer.getMedia().getDuration();

                if (timeslider5.isValueChanging()) {

                
                   
                  mediaplayer.seek(duration.multiply(timeslider5.getValue() / 100));
                   
                }
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        Image image = new Image("file:purple.png");
        BackgroundImage size = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        root.setBackground(new Background(size));

        Pane root1 = new Pane();
        root1.getChildren().addAll(btn1, btn12, btn13);
        root1.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        Pane root2 = new Pane(textField2);
        root2.getChildren().addAll(btn2, btn21, btn22, label2);
        Image image2 = new Image("file:seabeach.jpg");
        BackgroundImage size2 = new BackgroundImage(image2, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        root2.setBackground(new Background(size2));

        Pane root3 = new Pane(textField3, textField31, textField32);
        root3.getChildren().addAll(btn3, btn31);
        Image image3 = new Image("file:nature.jpg");
        BackgroundImage size3 = new BackgroundImage(image3, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        root3.setBackground(new Background(size3));

        Pane root4 = new Pane();
        root4.getChildren().addAll(PlayButton4, PauseButton4, ResetButton4, btn4, vol4, timeslider4);
        Image image4 = new Image("file:crystal.jpg");
        BackgroundImage size4 = new BackgroundImage(image4, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        root4.setBackground(new Background(size4));

        Pane root5 = new Pane();
        root5.getChildren().addAll(PlayButton5, PauseButton5, ResetButton5, btn5, vol5, timeslider5);
        Image image5 = new Image("file:nicepic.jpg");
        BackgroundImage size5 = new BackgroundImage(image5, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        root5.setBackground(new Background(size5));

        scene = new Scene(root, 300, 300);
        scene1 = new Scene(root1, 300, 300);
        scene2 = new Scene(root2, 300, 300);
        scene3 = new Scene(root3, 300, 300);
        scene4 = new Scene(root4, 300, 300);
        scene5 = new Scene(root5, 300, 300);
        stage.setTitle("Reader Software");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
