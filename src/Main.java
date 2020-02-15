/*
 * Darey Lee
 * Assignment 2
 * CS 4050
 * 
 * ========== REFERENCES ==========
 * Java Swing Tutorial - javatpoint.com
 * Java Swing | JTextArea - geeksforgeeks.org
 * Value Change Listener to JTextField - stackoverflow.com
 * How Can Recognize Backspace In JTextField - dreamincode.net
 */

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Main {
	
	private static FileReader fileReader = new FileReader();
	private static List<String> words = new ArrayList<String>();
	private static WordTree wordTree;
	
	private static JFrame frame;
	private static JTextField textField;
	private static JTextArea textArea;
	private static JLabel isWordLabel;
	
	public static void main(String[] args) {
		words = fileReader.getWords();
		wordTree = new WordTree(words);
		
		initializeGUI();
	}
	
	private static void initializeGUI() {
		frame = new JFrame("ASSIGNMENT 2 - TEXT PREDICTION");
		textField = new JTextField();
		textArea = new JTextArea();
		isWordLabel = new JLabel("IS A WORD");
		Font font = new Font("SansSerif", Font.PLAIN, 50);

		textField.setBounds(300, 25, 400, 75);
		textField.setFont(font);
		
		isWordLabel.setBounds(700, 25, 300, 75);
		isWordLabel.setFont(font);
		isWordLabel.setForeground(Color.BLUE);
		isWordLabel.setVisible(false);
		
		textArea.setBounds(300, 150, 400, 750);
		textArea.setFont(font);
		textArea.setEditable(false);
		
		initializeKeyListener();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(textField);
		frame.add(textArea);
		frame.add(isWordLabel);
		frame.setSize(1000, 1000);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	private static void initializeKeyListener() {	
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {		
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				predictText();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				predictText();
			}
			
		});
	}
	
	private static void predictText() {
		textArea.setText(null);
		isWordLabel.setVisible(false);
		
		if (!textField.getText().isEmpty()) {
		
			wordTree.findAllPossibleWords(textField.getText());
		
			if (wordTree.spelledWord()) {
				isWordLabel.setVisible(true);
			}
		
			for (String word : wordTree.getPossibleWords()) {
				textArea.setText(textArea.getText() + word + "\n");
			}
		}
	}
}
