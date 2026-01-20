module ec.edu.espoch.singlelinkedlist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    exports ec.edu.espoch.singlelinkedlist;
    exports ec.edu.espoch.singlelinkedlist.controller;
    exports ec.edu.espoch.singlelinkedlist.model;
    exports ec.edu.espoch.singlelinkedlist.view;
    opens ec.edu.espoch.singlelinkedlist.controller to javafx.fxml;
}
