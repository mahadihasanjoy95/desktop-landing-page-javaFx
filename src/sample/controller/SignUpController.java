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
import javafx.scene.control.*;
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

public class SignUpController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtEmailAddress;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private ComboBox countryList;

    @FXML
    private ComboBox stateList;

    @FXML
    private ComboBox cityList;

    @FXML
    private Button login;

    private JFXButton register = new JFXButton();

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

        register.setButtonType(JFXButton.ButtonType.RAISED);
        register.getStyleClass().addAll("sub-button");
        register.setGraphic(imageView1);
        register.setLayoutY(580);
        register.setLayoutX(600);

        countryList.getItems().add("Bangladesh");
        countryList.getItems().add("India");
        countryList.getItems().add("Pakistan");
        countryList.getItems().add("Bhutan");

        cityList.getItems().add("Dhaka");
        cityList.getItems().add("Barishal");
        cityList.getItems().add("Khulna");
        cityList.getItems().add("Chittagong");


        stateList.getItems().add("Mirpur");

        pane.getChildren().add(register);


        register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Window owner = pane.getScene().getWindow();
                System.out.println(txtUserName.getText());
                System.out.println(txtEmailAddress.getText());
                System.out.println(txtPassword.getText());
                System.out.println(txtPhoneNumber.getText());
                if (txtUserName.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                            "Please enter User Name");
                    return;
                }

                if (txtEmailAddress.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                            "Please enter your email id");
                    return;
                }
                if (txtPhoneNumber.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                            "Please enter your Phone Number");
                    return;
                }

                if (txtPassword.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                            "Please enter a password");
                    return;
                }

                String userName = txtUserName.getText();
                String emailAddress = txtEmailAddress.getText();
                String password = txtPassword.getText();
                String phoneNumber = txtPhoneNumber.getText();
                String countryName = (String) countryList.getValue();


                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userName", userName);
                jsonObject.put("emailAddress", emailAddress);
                jsonObject.put("phoneNumber", phoneNumber);
                jsonObject.put("password", password);

                try {

                    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                    HttpPost signUpRequest = new HttpPost(StaticValues.SIGN_UP_URL);
                    StringEntity input = new StringEntity(jsonObject.toString());
                    input.setContentType("application/json");
                    signUpRequest.setEntity(input);
                    HttpResponse response;
                    try {
                        response = httpClient.execute(signUpRequest);
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
                            root1 = FXMLLoader.load(getClass().getResource("/fxml/log_in_form.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene2 = new Scene(root1);
                        scene2.getStylesheets().add("/styles/login_signin.css");
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

        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root1 = null;
                try {
                    root1 = FXMLLoader.load(getClass().getResource("/fxml/log_in_form.fxml"));
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
