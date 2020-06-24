package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtEmailAddress;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button signUp;

    private JFXButton login = new JFXButton();

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Image logo1 = null;
        try {
            logo1 = new Image(new FileInputStream("src/resources/imgs/white-arrow-clipart.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView1 = new ImageView(logo1);
        imageView1.setFitWidth(40);
        imageView1.setFitHeight(40);

        login.setButtonType(JFXButton.ButtonType.RAISED);
        login.getStyleClass().addAll("sub-button");
        login.setGraphic(imageView1);
        login.setLayoutY(520);
        login.setLayoutX(600);

        pane.getChildren().add(login);


        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Window owner = pane.getScene().getWindow();
                System.out.println(txtEmailAddress.getText());

                if (txtEmailAddress.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                            "Please enter your email id");
                    return;
                }


                if (txtPassword.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                            "Please enter a password");
                    return;
                }

                String emailAddress = txtEmailAddress.getText();
                String password = txtPassword.getText();


                JSONObject jsonObject = new JSONObject();
                jsonObject.put("emailAddress", emailAddress);
                jsonObject.put("password", password);

                try {

                    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                    HttpPost logInRequest = new HttpPost(StaticValues.SIGN_IN_URL);
                    StringEntity input = new StringEntity(jsonObject.toString());
                    input.setContentType("application/json");
                    logInRequest.setEntity(input);
                    HttpResponse response;
                    try {
                        response = httpClient.execute(logInRequest);
                    } catch (Exception ex) {
                        showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                                "Server Connection Failed!!");
                        return;
                    }

                    if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 201) {
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader((response.getEntity().getContent())));

                        String output;
                        System.out.println("Output from Server .... \n");
                        while ((output = br.readLine()) != null) {
                            System.out.println(output);
                        }

                        httpClient.close();


                        Parent root1 = null;
                        try {
                            root1 = FXMLLoader.load(getClass().getResource("/fxml/landing_page.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene2 = new Scene(root1);
                        scene2.getStylesheets().add("/styles/landing_page.css");
                        Node node = (Node) event.getSource();
                        final Stage stage = (Stage) node.getScene().getWindow();
                        stage.setScene(scene2);
                        stage.show();
                    } else {
                        try {
                            HttpEntity entity = response.getEntity();
                            String content = EntityUtils.toString(entity);
                            JSONObject exceptionObject = new JSONObject(content);
                            if (Objects.nonNull(exceptionObject.get("message"))) {
                                showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                                        exceptionObject.get("message").toString());
                                return;
                            }
                            if (Objects.nonNull(exceptionObject.get("errors"))) {
                                showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                                        exceptionObject.get("errors").toString());
                                return;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                } catch (MalformedURLException e) {

                    e.printStackTrace();

                } catch (IOException e) {

                    e.printStackTrace();

                }
            }
        });

        signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root1 = null;
                try {
                    root1 = FXMLLoader.load(getClass().getResource("/fxml/sign_up_form.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene2 = new Scene(root1);
                scene2.getStylesheets().add("/styles/landing_page.css");
                Node node = (Node) event.getSource();
                final Stage stage = (Stage) node.getScene().getWindow();
                stage.setScene(scene2);
                stage.show();
            }
        });


    }
}
