// Author: Nikolas Leslie
// Date created: 9/27/22
// Last modified: 9/27/22
// Generates a lottery number from personal information
// Error count: 159

/*
_, _, _, _, _, _  Powerball: _
Numbers between 1-69, no duplacates

Powerball number: 69/hourly wage
First number: favorite month * 13 % 69 + 1
Second number: date of birth squared % 69 + 1
Third number: graduation year / 2 % 69 + 1
Fourth number: phrase length * 4 % 69 + 1
Fith number: absolute value of the sin of an angle * 68 + 1
Sixth number: key on keyboard ascii + 3 / 4 * 5 %69 + 1

need default month/day/year/wage
*/

import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

public class Lotto{
	
	static final int DEFAULT_MONTH = 1;
	static final int DEFAULT_DAY = 1;
	static final int DEFAULT_YEAR = 2000;
	static final int DEFAULT_WAGE = 12;
	
	public static void main(String[] args){
		
		//Asign variables
		int powerballNumber = 1;
		String[] positions = new String[]{"favorite month", "day of birth",
		                                  "graduation year", "phrase", "angle",
										  "key"};
		int[] numbers = new int[]{0,0,0,0,0,0};
		String warningMessage = "";
		
		//Create input fields
		JTextField wageField = new JTextField(5);
		JTextField monthField = new JTextField(5);
		JTextField birthdayField = new JTextField(5);
		JTextField graduationField = new JTextField(5);
		JTextField phraseField = new JTextField(5);
		JTextField angleField = new JTextField(5);
		JTextField keyField = new JTextField(5);
		
		//Create the panel that input will be gathered on
		JPanel questionPanel = new JPanel();
		questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.PAGE_AXIS));
		questionPanel.add(new JLabel("Please enter the requested values below"));
		questionPanel.add(Box.createVerticalStrut(15));
		questionPanel.add(new JLabel("Hourly wage: "));
		questionPanel.add(wageField);
		questionPanel.add(Box.createVerticalStrut(15));
		questionPanel.add(new JLabel("Favorite month: "));
		questionPanel.add(monthField);
		questionPanel.add(Box.createHorizontalStrut(30));
		questionPanel.add(Box.createVerticalStrut(15));
		questionPanel.add(new JLabel("Day of birth: "));
		questionPanel.add(birthdayField);
		questionPanel.add(Box.createVerticalStrut(15));
		questionPanel.add(new JLabel("Year you graduated high school: "));
		questionPanel.add(graduationField);
		questionPanel.add(Box.createVerticalStrut(15));
		questionPanel.add(new JLabel("A phrase or word you like: "));
		questionPanel.add(phraseField);
		questionPanel.add(Box.createVerticalStrut(15));
		questionPanel.add(new JLabel("An angle: "));
		questionPanel.add(angleField);
		questionPanel.add(Box.createVerticalStrut(15));
		questionPanel.add(new JLabel("A random keyboard key: "));
		questionPanel.add(keyField);
		questionPanel.add(Box.createVerticalStrut(15));
		
		//Gather input	
		boolean inputTest = true;
		while (inputTest){
			inputTest = false;
			int result = JOptionPane.showConfirmDialog(null, questionPanel, 
			                          "Lotto", JOptionPane.OK_CANCEL_OPTION, 
					                   JOptionPane.QUESTION_MESSAGE);
			
			//Emergency closure
			if (result != JOptionPane.OK_OPTION){
				System.exit(0);
			}
			
			//Test entered wage
			try{
				if (Integer.parseInt(wageField.getText()) < 0){
					powerballNumber = 69 / DEFAULT_WAGE;
				}else{
					powerballNumber = 69 / Integer.parseInt(wageField.getText());
				}
			}catch (Exception e){
				inputTest = true;
				warningMessage += "Your wage is not a number\n";
			}
			
			//Test entered month
			try{
				if (Integer.parseInt(monthField.getText()) < 1 ||
				        Integer.parseInt(monthField.getText()) > 12){
					numbers[0] = ((DEFAULT_MONTH * 13) % 69) + 1;
				}else{
					numbers[0] = (((Integer.parseInt(monthField.getText())
					                * 13) % 69) + 1);
				}
			}catch (Exception e){
				inputTest = true;
				warningMessage += "Your favorite month is not a number\n";
			}
			
			//Test entered day
			try{
				if (Integer.parseInt(birthdayField.getText()) < 1 || 
				        Integer.parseInt(birthdayField.getText()) > 31){
					numbers[1] = (int)(Math.pow(DEFAULT_DAY, 2) % 69) + 1;
				}else{
					numbers[1] = (int)(Math.pow(
					                   Integer.parseInt(birthdayField.getText())
									   , 2) % 69) + 1;
				}
			}catch (Exception e){
				inputTest = true;
				warningMessage += "Your day of birth is not a number\n";
			}
			
			//Test entered year
			try{
				if (Integer.parseInt(graduationField.getText()) < 1){
					numbers[2] = ((DEFAULT_YEAR / 2) % 69) + 1;
				}else{
					numbers[2] = ((Integer.parseInt(graduationField.getText())
 					               / 2) % 69) + 1;
				}
			}catch (Exception e){
				inputTest = true;
				warningMessage += "Your graduation year is not a number\n";
			}
			
			//Test entered phrase
			try{
				if (phraseField.getText() != null){
					numbers[3] = (((phraseField.getText()).length() 
					                * 4) % 69) + 1;
				}else{
					numbers[3] = 1;
				}
			}catch (Exception e){
				inputTest = true;
				warningMessage += "There was a problem processing your "+
				                  "phrase\n";
			}
			
			//Test entered angle
			try{
				numbers[4] = (int)((Math.abs(Math.sin(
				                    Integer.parseInt(angleField.getText())
									* Math.PI / 180)) * 68) + 1);
			}catch (Exception e){
				inputTest = true;
				warningMessage += "There was a problem processing"+
				                  " your phrase\n";
			}
			
			//Test entered key
			try{
				if ((keyField.getText()).length() == 1){
					numbers[5] = (int)((((((keyField.getText()).charAt(0) + 3)
                        					/ 4) * 5) % 69) + 1);
				}else{
					inputTest = true;
					warningMessage += "It seems you entered more"+
					                  " than a single key";
				}
			}catch (Exception e){
				inputTest = true;
				warningMessage += "There was a problem"+
				                  " processing your keystroke";
			}
			
			//Check for repeats
			for (int i = 0; i < numbers.length; i++){
				for (int j = 0; j < numbers.length; j++){
					if (numbers[i] == numbers[j] && numbers[i] != 0 && i != j){
						inputTest = true;
						warningMessage += ("Your " + positions[i] + " and " + 
						                   positions[j] + " gave the same"+
										   " value, please change one");
					}
				}
			}
			
			if (inputTest){
				JOptionPane.showMessageDialog(null, "There are a few problems "
				                      +"with your input:\n\n" + warningMessage,
							           "Lotto", JOptionPane.ERROR_MESSAGE);
				numbers = new int[]{0, 0, 0, 0, 0, 0};
				warningMessage = "";
			}
		}
		
		//Create and present formatted result
		String finalMessage = "Powerball number: " + 
		                       powerballNumber + "\nNumbers: ";
		for(int k = 0; k < numbers.length; k++){
			finalMessage += numbers[k];
			if (k < (numbers.length - 1)){
				finalMessage += ", ";
			}
		}
		JOptionPane.showMessageDialog(null, finalMessage, "Lotto",
		                              JOptionPane.INFORMATION_MESSAGE);
	}
}
