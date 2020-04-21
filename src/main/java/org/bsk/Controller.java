package org.bsk;

import ciphers.z1.*;
import ciphers.z2.*;
import javafx.scene.control.Control;
import org.bsk.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.stage.FileChooser;

public class Controller {

    private File selectedFile;
    private String destinationFileDir;
    private String destinationFileName;
    private String destinationFilePath;


    @FXML private TextField plainTextRailFence;
    @FXML private TextField keyRailFence;
    @FXML private TextField encryptedRailFence;
    @FXML private TextField decryptedRailFence;

    @FXML private TextField plainTextTranspositionA;
    @FXML private TextField keyTranspositionA;
    @FXML private TextField encryptedTranspositionA;
    @FXML private TextField decryptedTranspositionA;

    @FXML private TextField plainTextTranspositionB;
    @FXML private TextField keyTranspositionB;
    @FXML private TextField encryptedTranspositionB;
    @FXML private TextField decryptedTranspositionB;

    @FXML private TextField plainTextTranspositionC;
    @FXML private TextField keyTranspositionC;
    @FXML private TextField encryptedTranspositionC;
    @FXML private TextField decryptedTranspositionC;

    @FXML private TextField plainTextCaesar;
    @FXML private TextField keyCaesar;
    @FXML private TextField encryptedCaesar;
    @FXML private TextField decryptedCaesar;

    @FXML private TextField plainTextVigenere;
    @FXML private TextField keyVigenere;
    @FXML private TextField encryptedVigenere;
    @FXML private TextField decryptedVigenere;

    @FXML private TextField lfsrTekst;
    @FXML private TextArea lfsrWynik;

    @FXML private TextField sscWielomian;
    @FXML private TextField sscZiarno;
    @FXML private TextField sscDoZaszyfrowania;
    @FXML private TextArea sscWynik;

    @FXML private Pane pane;
    @FXML private Label selectedFileName;
    @FXML private Label selectedFilePath;

    @FXML
    private void encryptRailFence() throws Exception {

        String plainText = plainTextRailFence.getText();
        String key = keyRailFence.getText();

        if (selectedFile == null) {

            String cipherText = RailFence.encrypt(plainText, key);
            encryptedRailFence.setText(cipherText);

            String decryptedText = RailFence.decrypt(cipherText, key);
            decryptedRailFence.setText(decryptedText);
        } else {

            key = keyRailFence.getText();
            readFile("RailFence", selectedFile, key);
        }
    }

    @FXML
    private void encryptTranspositionA() throws Exception {
        String plainText = plainTextTranspositionA.getText();
        String key = keyTranspositionA.getText();

        if (selectedFile == null) {

            String cipherText = TranspositionA.encrypt(plainText, key);
            encryptedTranspositionA.setText(cipherText);

            String decryptedText = TranspositionA.decrypt(cipherText, key);
            decryptedTranspositionA.setText(decryptedText);
        } else {

            key = keyTranspositionA.getText();
            readFile("TranspositionA", selectedFile, key);
        }
    }

    @FXML
    private void encryptTranspositionB() throws Exception {
        String plainText = plainTextTranspositionB.getText();
        String key = keyTranspositionB.getText();

        if (selectedFile == null) {

            String cipherText = TranspositionB.encrypt(plainText, key);
            encryptedTranspositionB.setText(cipherText);

            String decryptedText = TranspositionB.decrypt(cipherText, key);
            decryptedTranspositionB.setText(decryptedText);
        } else {

            key = keyTranspositionB.getText();
            readFile("TranspositionB", selectedFile, key);
        }
    }

    @FXML
    private void encryptTranspositionC() throws Exception {
        String plainText = plainTextTranspositionC.getText();
        String key = keyTranspositionC.getText();

        if (selectedFile == null) {

            String cipherText = TranspositionC.encrypt(plainText, key);
            encryptedTranspositionC.setText(cipherText);

            String decryptedText = TranspositionC.decrypt(cipherText, key);
            decryptedTranspositionC.setText(decryptedText);
        } else {

            key = keyTranspositionC.getText();
            readFile("TranspositionC", selectedFile, key);
        }
    }

    @FXML
    private void encryptCaesar() throws Exception {

        String plainText = plainTextCaesar.getText();
        String key = keyCaesar.getText();

        if (selectedFile == null) {

            String cipherText = Caesar.encrypt(plainText, key);
            encryptedCaesar.setText(cipherText);

            String decryptedText = Caesar.decrypt(cipherText, key);
            decryptedCaesar.setText(decryptedText);
        } else {

            key = keyCaesar.getText();
            readFile("Caesar", selectedFile, key);
        }

    }

    @FXML
    private void encryptVigenere() throws Exception {
        String plainText = plainTextVigenere.getText();
        String key = keyVigenere.getText();

        if (selectedFile == null) {

            String cipherText = Vigenere.encrypt(plainText, key);
            encryptedVigenere.setText(cipherText);

            String decryptedText = Vigenere.decrypt(cipherText, key);
            decryptedVigenere.setText(decryptedText);

        } else {

            key = keyVigenere.getText();
            readFile("Vigenere", selectedFile, key);
        }
    }

    @FXML
    private void singleFileChooser(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        fileChooser.setTitle("Wybierz plik do zaszyfrowania");
        Stage stage = (Stage)pane.getScene().getWindow();

        File selectedFile = fileChooser.showOpenDialog(null);

        if(selectedFile != null) {
            selectedFileName.setText("" + selectedFile.getName());
            selectedFilePath.setText("" + selectedFile.getAbsolutePath());
            this.selectedFile = selectedFile;

            plainTextRailFence.setEditable(false);
            plainTextRailFence.setDisable(true);
            plainTextRailFence.setText("Usuń plik, aby edytować");

            plainTextTranspositionA.setEditable(false);
            plainTextTranspositionA.setDisable(true);
            plainTextTranspositionA.setText("Usuń plik, aby edytować");

            plainTextTranspositionB.setEditable(false);
            plainTextTranspositionB.setDisable(true);
            plainTextTranspositionB.setText("Usuń plik, aby edytować");

            plainTextCaesar.setEditable(false);
            plainTextCaesar.setDisable(true);
            plainTextCaesar.setText("Usuń plik, aby edytować");

            plainTextTranspositionC.setEditable(false);
            plainTextTranspositionC.setDisable(true);
            plainTextTranspositionC.setText("Usuń plik, aby edytować");

            plainTextVigenere.setEditable(false);
            plainTextVigenere.setDisable(true);
            plainTextVigenere.setText("Usuń plik, aby edytować");
        }
    }

    @FXML
    private void removeSelectedFile(ActionEvent event) {

        this.selectedFile = null;

        selectedFileName.setText("");
        selectedFilePath.setText("");

        plainTextRailFence.setEditable(true);
        plainTextRailFence.setDisable(false);
        plainTextRailFence.setText("");
    }

    @FXML
    private void readFile(String className, File selectedFile, String key) throws IOException {

        String packageName = "";
        String classPath;
        String cipherText = "";

        destinationFileDir = selectedFile.getParent();
        destinationFileName = selectedFileName.getText().replaceFirst("[.][^.]+$", "") + className + ".txt";
        destinationFilePath = destinationFileDir + "\\" + destinationFileName;
        System.out.println(destinationFilePath);
        BufferedReader br = null;
        BufferedWriter bw = null;
        String line;
        try {
            br = new BufferedReader(new FileReader(selectedFilePath.getText()));
            bw = new BufferedWriter(new FileWriter(destinationFilePath));
        } catch(FileNotFoundException fnfex) {
            System.out.println(fnfex.getMessage() + "The file was not found");
            System.exit(0);
        }
        try {
            while((line = br.readLine()) != null) {
                try {
                    if ( className == "RailFence" || className == "TranspositionA" || className == "TranspositionB") {
                        packageName = "z1";
                    } else if ( className == "Caesar" || className == "TranspositionC" || className == "Vigenere") {
                        packageName = "z2";
                    }
                    classPath = "ciphers" + "." + packageName  + "." +className;
                    Class<?> myClass = Class.forName(classPath);
                    Method method = myClass.getDeclaredMethod("encrypt", String.class, String.class);
                    Object result = method.invoke(null, line, key);

                    cipherText = (String)result;
                } catch(Exception e) {
                    System.out.println( "Exception : " + e.getMessage() );
                }
                bw.write(cipherText+"\n");
            }
            bw.close();
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage() + "Error reading file");
        }
    }
}
