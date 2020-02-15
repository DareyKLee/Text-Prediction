import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

	private List<String> wordsArrayList = new ArrayList<String>();
	
	public FileReader() {
		
	}
	
	public List<String> getWords() {
		try {
			File file = new File("words.txt");
			Scanner reader = new Scanner(file);
			
			while (reader.hasNextLine()) {
				wordsArrayList.add(reader.nextLine());
			}
			
			reader.close();
			
		} catch (IOException e) {
			System.out.println("Could not read file words.txt");
		}
		
		return wordsArrayList;
	}
}
