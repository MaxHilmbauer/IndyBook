package mh.indybook.gui.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import mh.indybook.indy.model.IndyEvent;
import mh.indybook.indy.model.IndyEventDetailed;
import mh.indybook.indy.model.IndySubject;
import mh.indybook.indy.model.IndyTeacher;
import mh.indybook.indy.Indy;
import javafx.fxml.FXML;
import mh.indybook.profile.controller.ProfileController;
import mh.indybook.profile.model.Profile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookingController {


    @FXML
    private Tab bookingTab;
    @FXML
    private ListView<IndyEvent> eventList;
    @FXML
    private ComboBox<IndyTeacher> teacherBox;
    @FXML
    private ComboBox<IndySubject> subjectBox;
    @FXML
    private Pane editorPane;
    @FXML
    private Tab profileTab;
    @FXML
    private ListView<String> subjectActivityList;
    @FXML
    private ListView<String> prioritiesList;
    @FXML
    private Button openProfileBtn;
    @FXML
    private Tab settingsTab;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private ToggleButton showPasswordBtn;
    private Indy indy;
    private IndyEventDetailed selectedIndyEvent;



    @FXML
    public void initialize() {
        indy = Indy.getInstance();
        ObservableList<IndyEvent> observableEvents = FXCollections.observableArrayList(indy.getNextIndyEvents(200).stream().toList());
        eventList.setCellFactory(param -> new ListCell<IndyEvent>(){
            @Override
            public void updateItem(IndyEvent event, boolean empty) {
                super.updateItem(event, empty);
                if (empty || event == null) {
                    setText(null);
                } else {
                    setText("Indy " + event.getDate());
                }
            }
        });
       eventList.setItems(observableEvents);
       teacherBox.setCellFactory(new Callback<ListView<IndyTeacher>, ListCell<IndyTeacher>>() {
                   @Override
                   public ListCell<IndyTeacher> call(ListView<IndyTeacher> param) {
                       return new ListCell<IndyTeacher>() {
                           @Override
                           protected void updateItem(IndyTeacher teacher, boolean empty) {
                               super.updateItem(teacher, empty);
                               if (teacher != null && !empty) {
                                   setText(teacher.getId() + " - " + teacher.getLastname() + " - " + teacher.getRoom() + " (" + teacher.getStudentAmount() + "/" + teacher.getStudentLimit() + ")");
                                   setAccessibleText(teacher.getId() + " - " + teacher.getLastname() + " - " + teacher.getRoom() + " (" + teacher.getStudentAmount() + "/" + teacher.getStudentLimit() + ")");
                               }
                           }
                       };
                   }
               });
       teacherBox.setConverter(new StringConverter<IndyTeacher>() {
           @Override
           public String toString(IndyTeacher teacher) {
               return teacher.getId() + " - " + teacher.getLastname() + " - " + teacher.getRoom() + " (" + teacher.getStudentAmount() + "/" + teacher.getStudentLimit() + ")";
           }

           @Override
           public IndyTeacher fromString(String s) {
               return null;
           }
       });

       subjectBox.setCellFactory(new Callback<ListView<IndySubject>, ListCell<IndySubject>>() {
           @Override
           public ListCell<IndySubject> call(ListView<IndySubject> param) {
               return new ListCell<IndySubject>() {
                   @Override
                   protected void updateItem(IndySubject subject, boolean empty) {
                       super.updateItem(subject, empty);

                       if (subject != null && !empty) {
                           setText(subject.getName());
                       }
                   }
               };
           }
       });
       subjectBox.setConverter(new StringConverter<IndySubject>() {
           @Override
           public String toString(IndySubject subject) {
               return subject.getName();
           }

           @Override
           public IndySubject fromString(String s) {
               return null;
           }
       });

       subjectActivityList.setCellFactory(param -> new ListCell<String>(){
           @Override
           public void updateItem(String subjectActivityString, boolean empty) {
               super.updateItem(subjectActivityString, empty);
               if (empty || subjectActivityString == null) {
                   setText(null);
               } else {
                   setText(subjectActivityString);
               }
           }
       });

       prioritiesList.setCellFactory(param -> new ListCell<String>() {
           @Override
           public void updateItem(String priorityString, boolean empty) {
               super.updateItem(priorityString, empty);
               if (empty || priorityString == null) {
                   setText(null);
               } else {
                   setText(priorityString);
               }
           }
       });

    }

    @FXML
    public void openProfile(ActionEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        ProfileController profileController = new ProfileController();
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        Profile profile = profileController.readProfile(selectedFile);
        subjectActivityList.setItems(FXCollections.observableArrayList(profile.getSubjectActivity().stream().toList()));
        HashMap<Integer, String> priorities = profile.getPriorities();
        ArrayList<String> prioritiesString = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : priorities.entrySet()) {
            String entryString = entry.getKey() + " - " + entry.getValue();
            prioritiesString.add(entryString);
        }
        prioritiesList.setItems(FXCollections.observableArrayList(prioritiesString.stream().toList()));
    }

    @FXML
    public void seeEventdetails() {
        editorPane.setDisable(false);
        IndyEvent indyEvent = getSelectedIndyEvent();
        if (indyEvent != null) {
            selectedIndyEvent = indyEvent.getDetails();
            teacherBox.setItems(FXCollections.observableArrayList(selectedIndyEvent.getTeachers_hour1().stream().toList()));
            teacherBox.getSelectionModel().select(0);
            subjectBox.setItems(FXCollections.observableArrayList(selectedIndyEvent.getSubjects().stream().toList()));
            subjectBox.getSelectionModel().select(0);
        }
    }

    @FXML
    public void hour3RadioBtnPress() {
        teacherBox.setItems(FXCollections.observableArrayList(selectedIndyEvent.getTeachers_hour1().stream().toList()));
        teacherBox.getSelectionModel().select(0);
        subjectBox.setItems(FXCollections.observableArrayList(selectedIndyEvent.getSubjects().stream().toList()));
        subjectBox.getSelectionModel().select(0);
    }

    @FXML
    public void hour4RadioBtnPress() {
        teacherBox.setItems(FXCollections.observableArrayList(selectedIndyEvent.getTeachers_hour2().stream().toList()));
        teacherBox.getSelectionModel().select(0);
        subjectBox.setItems(FXCollections.observableArrayList(selectedIndyEvent.getSubjects().stream().toList()));
        subjectBox.getSelectionModel().select(0);
    }

    @FXML
    public void openedSettingsTab() {
        usernameLabel.setText(indy.getCredentials().getUsername());
    }

    @FXML
    public void showPassword() {
        if (showPasswordBtn.isSelected()) {
            passwordLabel.setText(indy.getCredentials().getPassword());
            showPasswordBtn.setText("Hide Password");
        } else {
            passwordLabel.setText("************");
            showPasswordBtn.setText("Show Password");
        }
    }

    private IndyEvent getSelectedIndyEvent() {
        return eventList.getSelectionModel().getSelectedItem();
    }



}
