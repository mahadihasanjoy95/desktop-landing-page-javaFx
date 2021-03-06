package sample.utils;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Window;
import org.apache.commons.validator.routines.EmailValidator;
import sample.view.responsive.ScreenCal;

import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

public class Common {

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static void checkInternet(Window owner) {
        try {
            URL url = new URL("https://www.google.com/");
            URLConnection connection = url.openConnection();
            connection.connect();

        } catch (Exception e) {
            Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                    "Internet Not Connected");
            return;
        }
    }

    public static boolean emailValidator(String email) {


        EmailValidator validator = EmailValidator.getInstance();

        if (!validator.isValid(email)) {
            return false;
        }
        return true;
    }

    /**
     * This method set profile picture
     */
    public static void setProfilePic(Circle cir, String photoString) {
        Image image = new Image("/imgs/user_default.png");
        if (!photoString.equals("")) {
            try {
                image = new Image(photoString);
            } catch (Exception ex) {
                image = new Image("/imgs/user_default.png");
            }
        }
        if (Objects.nonNull(image)) {
            cir.setFill(new ImagePattern(image));
            cir.setCache(true);
        }
        cir.setCache(true);
    }

    public static void progressIndicator(StackPane stackPane) {

        ProgressIndicator pi = new ProgressIndicator();
        stackPane.getChildren().add(pi);
        pi.setMaxSize(ScreenCal.getScreenResulation().getWidth() / 21, ScreenCal.getScreenResulation().getWidth() / 21);
        stackPane.setAlignment(Pos.CENTER);
    }

    public static void eyeSlashImage(Button button) {
        ImageView eyeSlash = new ImageView(new Image(Constants.ImageUrl.EYE_SLASH));
        eyeSlash.setFitHeight(18.5);
        eyeSlash.setFitWidth(25);
        button.setGraphic(eyeSlash);
    }

    public static void eyeOpenImage(Button button) {
        ImageView eyeOpen = new ImageView(new Image(Constants.ImageUrl.EYE_OPEN));
        eyeOpen.setFitHeight(18.5);
        eyeOpen.setFitWidth(25);
        button.setGraphic(eyeOpen);
    }

    public static void setCountryList(ComboBox comboBox) {

        comboBox.getItems().add("Afghanistan");
        comboBox.getItems().add("Albania");
        comboBox.getItems().add("Algeria");
        comboBox.getItems().add("Andorra");
        comboBox.getItems().add("Angola");
        comboBox.getItems().add("Antigua and Barbuda");
        comboBox.getItems().add("Argentina");
        comboBox.getItems().add("Armenia");
        comboBox.getItems().add("Australia");
        comboBox.getItems().add("Austria");
        comboBox.getItems().add("Azerbaijan");
        comboBox.getItems().add("Bahamas");
        comboBox.getItems().add("Bahrain");
        comboBox.getItems().add("Bangladesh");
        comboBox.getItems().add("Barbados");
        comboBox.getItems().add("Belarus");
        comboBox.getItems().add("Belgium");
        comboBox.getItems().add("Belize");
        comboBox.getItems().add("Benin");
        comboBox.getItems().add("Bhutan");
        comboBox.getItems().add("Bolivia");
        comboBox.getItems().add("Bosnia and Herzegovina");
        comboBox.getItems().add("Botswana");
        comboBox.getItems().add("Brazil");
        comboBox.getItems().add("Brunei");
        comboBox.getItems().add("Bulgaria");
        comboBox.getItems().add("Burkina Faso");
        comboBox.getItems().add("Burundi");
        comboBox.getItems().add("Cabo Verde");
        comboBox.getItems().add("Cambodia");
        comboBox.getItems().add("Cameroon");
        comboBox.getItems().add("Canada");
        comboBox.getItems().add("Central African Republic (CAR)");
        comboBox.getItems().add("Chad");
        comboBox.getItems().add("Chile");
        comboBox.getItems().add("China");
        comboBox.getItems().add("Colombia");
        comboBox.getItems().add("Comoros");
        comboBox.getItems().add("Democratic Republic of the Congo");
        comboBox.getItems().add("Republic of the Congo");
        comboBox.getItems().add("Costa Rica");
        comboBox.getItems().add("Cote d'Ivoire");
        comboBox.getItems().add("Croatia");
        comboBox.getItems().add("Cuba");
        comboBox.getItems().add("Cyprus");
        comboBox.getItems().add("Czech Republic");
        comboBox.getItems().add("Denmark");
        comboBox.getItems().add("Djibouti");
        comboBox.getItems().add("Dominica");
        comboBox.getItems().add("Dominican Republic");
        comboBox.getItems().add("Ecuador");
        comboBox.getItems().add("Egypt");
        comboBox.getItems().add("El Salvador");
        comboBox.getItems().add("Equatorial Guinea");
        comboBox.getItems().add("Eritrea");
        comboBox.getItems().add("Estonia");
        comboBox.getItems().add("Ethiopia");
        comboBox.getItems().add("Fiji");
        comboBox.getItems().add("Finland");
        comboBox.getItems().add("France");
        comboBox.getItems().add("Gabon");
        comboBox.getItems().add("Gambia");
        comboBox.getItems().add("Georgia");
        comboBox.getItems().add("Germany");
        comboBox.getItems().add("Ghana");
        comboBox.getItems().add("Greece");
        comboBox.getItems().add("Grenada");
        comboBox.getItems().add("Guatemala");
        comboBox.getItems().add("Guinea");
        comboBox.getItems().add("Guinea-Bissau");
        comboBox.getItems().add("Guyana");
        comboBox.getItems().add("Haiti");
        comboBox.getItems().add("Honduras");
        comboBox.getItems().add("Hungary");
        comboBox.getItems().add("Iceland");
        comboBox.getItems().add("India");
        comboBox.getItems().add("Indonesia");
        comboBox.getItems().add("Iran");
        comboBox.getItems().add("Iraq");
        comboBox.getItems().add("Ireland");
        comboBox.getItems().add("Israel");
        comboBox.getItems().add("Italy");
        comboBox.getItems().add("Jamaica");
        comboBox.getItems().add("Japan");
        comboBox.getItems().add("Jordan");
        comboBox.getItems().add("Kazakhstan");
        comboBox.getItems().add("Kenya");
        comboBox.getItems().add("Kiribati");
        comboBox.getItems().add("Kosovo");
        comboBox.getItems().add("Kuwait");
        comboBox.getItems().add("Kyrgyzstan");
        comboBox.getItems().add("Laos");
        comboBox.getItems().add("Latvia");
        comboBox.getItems().add("Lebanon");
        comboBox.getItems().add("Lesotho");
        comboBox.getItems().add("Liberia");
        comboBox.getItems().add("Libya");
        comboBox.getItems().add("Liechtenstein");
        comboBox.getItems().add("Lithuania");
        comboBox.getItems().add("Luxembourg");
        comboBox.getItems().add("Macedonia (FYROM)");
        comboBox.getItems().add("Madagascar");
        comboBox.getItems().add("Malawi");
        comboBox.getItems().add("Malaysia");
        comboBox.getItems().add("Maldives");
        comboBox.getItems().add("Mali");
        comboBox.getItems().add("Malta");
        comboBox.getItems().add("Marshall Islands");
        comboBox.getItems().add("Mauritania");
        comboBox.getItems().add("Mauritius");
        comboBox.getItems().add("Mexico");
        comboBox.getItems().add("Micronesia");
        comboBox.getItems().add("Moldova");
        comboBox.getItems().add("Monaco");
        comboBox.getItems().add("Mongolia");
        comboBox.getItems().add("Montenegro");
        comboBox.getItems().add("Morocco");
        comboBox.getItems().add("Mozambique");
        comboBox.getItems().add("Myanmar (Burma)");
        comboBox.getItems().add("Namibia");
        comboBox.getItems().add("Nauru");
        comboBox.getItems().add("Nepal");
        comboBox.getItems().add("Netherlands");
        comboBox.getItems().add("New Zealand");
        comboBox.getItems().add("Nicaragua");
        comboBox.getItems().add("Niger");
        comboBox.getItems().add("Nigeria");
        comboBox.getItems().add("North Korea");
        comboBox.getItems().add("Norway");
        comboBox.getItems().add("Oman");
        comboBox.getItems().add("Pakistan");
        comboBox.getItems().add("Palau");
        comboBox.getItems().add("Palestine");
        comboBox.getItems().add("Panama");
        comboBox.getItems().add("Papua New Guinea");
        comboBox.getItems().add("Paraguay");
        comboBox.getItems().add("Peru");
        comboBox.getItems().add("Philippines");
        comboBox.getItems().add("Poland");
        comboBox.getItems().add("Portugal");
        comboBox.getItems().add("Qatar");
        comboBox.getItems().add("Romania");
        comboBox.getItems().add("Russia");
        comboBox.getItems().add("Rwanda");
        comboBox.getItems().add("Saint Kitts and Nevis");
        comboBox.getItems().add("Saint Lucia");
        comboBox.getItems().add("Saint Vincent and the Grenadines");
        comboBox.getItems().add("Samoa");
        comboBox.getItems().add("San Marino");
        comboBox.getItems().add("Sao Tome and Principe");
        comboBox.getItems().add("Saudi Arabia");
        comboBox.getItems().add("Senegal");
        comboBox.getItems().add("Serbia");
        comboBox.getItems().add("Seychelles");
        comboBox.getItems().add("Sierra Leone");
        comboBox.getItems().add("Singapore");
        comboBox.getItems().add("Slovakia");
        comboBox.getItems().add("Slovenia");
        comboBox.getItems().add("Solomon Islands");
        comboBox.getItems().add("Somalia");
        comboBox.getItems().add("South Africa");
        comboBox.getItems().add("South Korea");
        comboBox.getItems().add("South Sudan");
        comboBox.getItems().add("Spain");
        comboBox.getItems().add("Sri Lanka");
        comboBox.getItems().add("Sudan");
        comboBox.getItems().add("Suriname");
        comboBox.getItems().add("Swaziland");
        comboBox.getItems().add("Sweden");
        comboBox.getItems().add("Switzerland");
        comboBox.getItems().add("Syria");
        comboBox.getItems().add("Taiwan");
        comboBox.getItems().add("Tajikistan");
        comboBox.getItems().add("Tanzania");
        comboBox.getItems().add("Thailand");
        comboBox.getItems().add("Timor-Leste");
        comboBox.getItems().add("Togo");
        comboBox.getItems().add("Tonga");
        comboBox.getItems().add("Trinidad and Tobago");
        comboBox.getItems().add("Tunisia");
        comboBox.getItems().add("Turkey");
        comboBox.getItems().add("Turkmenistan");
        comboBox.getItems().add("Tuvalu");
        comboBox.getItems().add("Uganda");
        comboBox.getItems().add("Ukraine");
        comboBox.getItems().add("United Arab Emirates (UAE)");
        comboBox.getItems().add("United Kingdom (UK)");
        comboBox.getItems().add("United States of America (USA)");
        comboBox.getItems().add("Uruguay");
        comboBox.getItems().add("Uzbekistan");
        comboBox.getItems().add("Vanuatu");
        comboBox.getItems().add("Vatican City (Holy See)");
        comboBox.getItems().add("Venezuela");
        comboBox.getItems().add("Vietnam");
        comboBox.getItems().add("Yemen");
        comboBox.getItems().add("Zambia");
        comboBox.getItems().add("Zimbabwe");
        comboBox.setOpacity(1);
    }
}
