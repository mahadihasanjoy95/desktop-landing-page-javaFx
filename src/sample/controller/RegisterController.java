package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Objects;


public class RegisterController {

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtEmailAddress;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button submitButton;

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    public void register(ActionEvent event) throws SQLException, IOException {
        Window owner = submitButton.getScene().getWindow();

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
            HttpResponse response = httpClient.execute(signUpRequest);

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
                    StaticValues.w = 5;
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
}
