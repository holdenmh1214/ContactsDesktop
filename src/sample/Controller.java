package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    ObservableList<Contact> items = FXCollections.observableArrayList();

    @FXML
    TextField nameField;

    @FXML
    TextField phoneField;

    @FXML
    TextField emailField;

    @FXML
    ListView listView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(items);

    }

    public void addContact(){

        if (!nameField.getText().equals("") && !phoneField.getText().equals("") && !emailField.getText().equals(""))
        {
            Contact contact = new Contact(nameField.getText(), phoneField.getText(), emailField.getText());
            items.add(contact);
            nameField.setText("");
            phoneField.setText("");
            emailField.setText("");
            saveContacts(items);
        }
    }

    public void removeContact(){

        Contact oldContact =  (Contact) listView.getSelectionModel().getSelectedItem();
        items.remove(oldContact);
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");


        saveContacts(items);
    }


    static void saveContacts(ObservableList<Contact> items) {
        File f = new File("Contacts.json");
        JsonSerializer serializer = new JsonSerializer();
        String contentToSave = serializer.serialize(items);

        try {
            FileWriter fw = new FileWriter(f);
            fw.write(contentToSave);
            fw.close();
        } catch (Exception e) {

        }
    }
}
