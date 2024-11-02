package hospitalmanagementsystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static javafx.geometry.Pos.CENTER;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public class MAIN extends Application {
    public Registration registration = new Registration();
    public BedAllocation bedAllocation = new BedAllocation();
    public InitialScreening screening = new InitialScreening();
    public AppointmentBooking appointmentBooking = new AppointmentBooking();
    public EmergencyHandler emergencyHandler = new EmergencyHandler();
    public Info info = new Info(); // Create an instance of Info

    private Stage stage;
    private Scene welcomeScene;
    private Scene mainScene;
    private Scene secondScene;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        createWelcomePage();
        createMainPage();
        createSecondPage();

        stage.setScene(welcomeScene); // Start with the welcome scene
        stage.setTitle("Hospital Management System");
        stage.show();
    }

    private void createWelcomePage() {
        VBox vbox = new VBox(10);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);

        Label welcomeLabel = new Label("Welcome to the Hospital");
       // Increase title font size

        Button loginButton = new Button("Login");
        loginButton.setId("login-button");

        // Set styles for welcome page
        welcomeLabel.getStyleClass().add("welcome-label");
        

        // Prompt for email and password on login button click
        loginButton.setOnAction(e -> 
        {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Login");
            dialog.setHeaderText("Enter email and password:");

            // Create fields for email and password
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setAlignment(javafx.geometry.Pos.CENTER);

            TextField emailField = new TextField();
            emailField.setId("email-field");
            PasswordField passwordField = new PasswordField();
            passwordField.setId("password-field");

            grid.add(new Label("Email:"), 0, 0);    grid.add(emailField, 1, 0);
            grid.add(new Label("Password:"), 0, 1); grid.add(passwordField, 1, 1);

            dialog.getDialogPane().setContent(grid);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            // Handle dialog result
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Proceed to main scene if credentials are entered
                stage.setScene(mainScene);
            }
        });

        vbox.getChildren().addAll(welcomeLabel, loginButton);
        welcomeScene = new Scene(vbox, 300, 250);
        welcomeScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }
//// ///// ////
private void createMainPage() {
    VBox vbox = new VBox(10);
    vbox.setAlignment(CENTER); // Center alignment

    // HBox for the main page card layout
    HBox hbox = new HBox(30);
    hbox.setAlignment(CENTER);

    // Create card-style buttons for the main page
    hbox.getChildren().addAll(
        createCard("Register Patient", "Registration.jpg", e -> registration.registerPatient()),
        createCard("Emergency", "emergency.jpg", e -> handleEmergency()),
        createCard("Next", "next.jpg", e -> stage.setScene(secondScene)) // "Next" as a card
    );

    vbox.getChildren().addAll(hbox);

    // "Exit" as a standard button
    Button exitButton = new Button("Exit");
    
    exitButton.getStyleClass().add("button-exit");
    exitButton.setPrefSize(250, 50);
    exitButton.setOnAction(e -> {
        showAlert("Exit", "Exiting system. Thank you!");
        stage.close();
    });

    vbox.getChildren().add(exitButton); // Add exit button below the cards
    mainScene = new Scene(vbox, 300, 250);
    mainScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
}

/////////////////////////
private void createSecondPage() {
    VBox vbox = new VBox(30);
    vbox.setAlignment(CENTER); // Center alignment
    vbox.getStyleClass().add("vbox-navigation");

    // HBox for row layout
    HBox hbox1 = new HBox(30);
    hbox1.setAlignment(CENTER);
    HBox hbox2 = new HBox(30);
    hbox2.setAlignment(CENTER);

    // Create buttons and their corresponding card layout
    hbox1.getChildren().addAll(
        createCard("Allocate Bed", "bed.jpg", e -> bedAllocation.displayMenu()),
        createCard("Initial Screening", "screening.jpg", e -> performScreening()),
        createCard("Book Appointment", "appointment.jpg", e -> bookAppointment())
    );

    hbox2.getChildren().addAll(
        createCard("Display Data", "data.jpg", e -> HardCodedData.displayInfo()),
        createCard("FAQ Section", "faq.jpg", e -> info.displayFAQ()),
        createCard("Previous Page", "prev.jpg", e -> stage.setScene(mainScene))
    );

    // Add HBoxes to the main VBox
    vbox.getChildren().addAll(hbox1, hbox2);

    // Add buttons for navigation
    Button exitButton = new Button("Exit");
    exitButton.setId("exit-button");
    exitButton.getStyleClass().add("button-exit");
    exitButton.setPrefSize(250, 50); // Increase size
    exitButton.setOnAction(e -> {
        showAlert("Exit", "Exiting system. Thank you!");
        stage.close();
    });

    vbox.getChildren().add(exitButton);
    secondScene = new Scene(vbox, 300, 250);
    secondScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
}

/////////////////////
///////////////////////
////////////////////////

private VBox createCard(String buttonText, String imagePath, EventHandler<ActionEvent> eventHandler) {
    VBox card = new VBox();
    card.setAlignment(CENTER);
    card.setSpacing(15);
    

  card.getStyleClass().add("card");
    // Image
    ImageView imageView;
    try {
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        imageView = new ImageView(image);
         
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        
       Rectangle clip = new Rectangle(200, 200);
clip.setArcWidth(15); 
clip.setArcHeight(15);
imageView.setClip(clip);

    }
    catch (NullPointerException e) {
        System.err.println("Image not found: " + imagePath);
        imageView = new ImageView(); // Fallback to empty ImageView if not found
    }

    // Button
    Button button = new Button(buttonText);
    button.getStyleClass().add("button");
    button.setOnAction(eventHandler);

    // Add the image and button to the VBox
    card.getChildren().addAll(imageView, button); // Add both ImageView and Button to the card

    return card;
}

/////////////////
    public void performScreening() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Patient ID Input");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter Patient ID:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                int id = Integer.parseInt(result.get());
                Patient patient = BedAllocation.getPatientById(id);

                if (patient != null) {
                    screening.performScreening(patient);
                } else {
                    showAlert("Error", "No patient found with the given ID.");
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "Invalid ID format. Please enter a numeric Patient ID.");
            }
        }
    }

    public void bookAppointment() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Appointment Type");
        dialog.setHeaderText(null);
        dialog.setContentText("Appointment type (Normal/Specialist):");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(type -> appointmentBooking.bookAppointment(type));
    }

    public void handleEmergency() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Emergency Severity Level");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter severity level (1 for Low, 2 for Medium, 3 for High):");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                int severityLevel = Integer.parseInt(result.get());
                emergencyHandler.handleEmergency(severityLevel);
            } catch (NumberFormatException e) {
                showAlert("Error", "Invalid input. Please enter a numeric severity level.");
            }
        }
    }

    public void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    

    public static void main(String[] args) {
        launch(args);
    }
}
