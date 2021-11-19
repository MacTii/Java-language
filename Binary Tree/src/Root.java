public class Root {
	private Root left;
	private Root right;
	private Person person;
	// Tree tree = new Tree();

	Root(Person person) {
		this.person = person;
		this.right = null;
		this.left = null;
	}

	public Root getLeft() {
		return left;
	}

	public void setLeft(Root left) {
		this.left = left;
	}

	public Root getRight() {
		return right;
	}

	public void setRight(Root right) {
		this.right = right;
	}

	public Person getKey() {
		return person;
	}

	public void setKey(Person person) {
		this.person = person;
	}

	public void insertNode(Root newNode) {
		if (newNode.person.compareTo(person) < 0) {
			if (left == null)
				left = newNode;
			else
				left.insertNode(newNode);
		} else {
			if (right == null)
				right = newNode;
			else
				right.insertNode(newNode);
		}
	}
}
