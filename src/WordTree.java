import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordTree {

	private LetterNode rootNode;
	private LetterNode latestInputNode;
	private Set<String> setOfPossibleWords;
	private final int wordCapacity = 10;
	private int wordCount = 0;

	public WordTree(List<String> words) {
		initializeNodes();
		generateTree(words);
		setOfPossibleWords = new HashSet<String>();
	}

	public Set<String> getPossibleWords() {
		return setOfPossibleWords;
	}
	
	public void findAllPossibleWords(String inputLetters) {
		setOfPossibleWords.clear();
		wordCount = 0;
		resetLatestInputNode();
		Boolean possibleWordsFound = true;
		
		for (char letter : inputLetters.toCharArray()) {
			if (latestInputNode.branchExists(letter)) {
				latestInputNode = latestInputNode.getNextNode(letter);
			} else {
				setOfPossibleWords.clear();
				possibleWordsFound = false;
				resetLatestInputNode();
				break;
			}
		}
		
		if (possibleWordsFound) {
			traverseTree(latestInputNode, inputLetters);
		}
	}
	
	public Boolean spelledWord() {
		return latestInputNode.isWord();
	}
	
	private void initializeNodes() {
		rootNode = new LetterNode('\0');
		resetLatestInputNode();
	}
	
	private void resetLatestInputNode() {
		latestInputNode = rootNode;
	}

	private void generateTree(List<String> words) {
		for (String word : words) {
			generateBranches(word);
		}
	}

	private void generateBranches(String word) {
		LetterNode currentNode = rootNode;

		for (char letter : word.toCharArray()) {
			currentNode.addBranch(letter);
			currentNode = currentNode.getNextNode(letter);
		}

		currentNode.markAsWord();
	}

	private void traverseTree(LetterNode traversalNode, String leadingLetters) {
		if (!traversalNode.getBranches().isEmpty()) {
			for (LetterNode node : traversalNode.getBranches()) {
				String leadingLettersWithNextNodeLetter = leadingLetters + node.getLetter();
				
				if (node.isWord()) {
					addWord(leadingLettersWithNextNodeLetter);
				}
				traverseTree(node, leadingLettersWithNextNodeLetter);
			}
		}
	}

	private void addWord(String word) {
		if (wordCount <= wordCapacity) {
			setOfPossibleWords.add(word);
			wordCount++;
		}
	}
}
