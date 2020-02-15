import java.util.HashSet;
import java.util.Set;

public class LetterNode {

	private char letter;
	private Boolean isWord = false;
	private Set<LetterNode> branches;
	
	public LetterNode(char letter) {
		this.letter = letter;
		this.branches = new HashSet<LetterNode>();
	}
	
	public void addBranch(char letter) {
		branches.add(new LetterNode(letter));
	}
	
	public LetterNode getNextNode(char letter) {
		for (LetterNode node : branches) {
			if (node.getLetter() == letter) {
				return node;
			}
		}
		return null;
	}
	
	public char getLetter() {
		return letter;
	}
	
	public Boolean isWord() {
		return isWord;
	}
	
	public void markAsWord() {
		isWord = true;
	}
	
	public Set<LetterNode> getBranches() {
		return branches;
	}
	
	public Boolean branchExists(char letter) {
		if (branches.contains(new LetterNode(letter))) {
			return true;
		}
		
		return false;
	}
		
	@Override
	public int hashCode() {
		return letter;
	}
	
	@Override
	public boolean equals(Object node) {
		LetterNode letterNode = (LetterNode) node;
		return letterNode.letter == this.letter;
	}
}