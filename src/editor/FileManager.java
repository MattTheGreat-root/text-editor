package editor;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class FileManager {
    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        Scanner scn;
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try{
                scn = new Scanner(file);
                textArea.setText("");
                while(scn.hasNextLine()) {
                    textArea.append(scn.nextLine() + "\n");
                }
                textEditor.currentFile = file;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile == null){
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(textArea.getText());
                    textEditor.currentFile = file;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else{
            try (FileWriter writer = new FileWriter(textEditor.currentFile)) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        textArea.setText("");
        textEditor.currentFile = null;
    }
}
