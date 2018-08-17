/*
Author: Danny Lu
Project: CIS 422 Project 2: Music Maker

Functions: getFilePath()
reference the Module Interface Specification to learn more about
how to use each function.

This file mainly handles the UI for the user to locate files and look
through directories.
*/
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;

import javax.swing.JFileChooser;

public class saveLoad{
    public static String save() throws IOException{

    	JFileChooser fileChooser = new JFileChooser();
		File selectedFile = null;
	    int returnValue = fileChooser.showOpenDialog(null);
	    if (returnValue == JFileChooser.APPROVE_OPTION) {
	        selectedFile = fileChooser.getSelectedFile();
	    }
	    String fileName = selectedFile.getName();

		File file = new File(fileName);
		try {
			Files.copy(selectedFile.toPath(), file.toPath());
		} catch (FileAlreadyExistsException e1) {
			System.out.println("song already exist");
		}


        return fileName;
    }
    public static String load() throws IOException{
    	JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        fileChooser.setCurrentDirectory(new java.io.File("."));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        String fileName = fileChooser.getSelectedFile().getAbsolutePath();
        return fileName;
    }
}
