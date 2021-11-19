import java.util.Comparator;

public class Tree {
	private Root root;
	private Comparator<Person> comparator;

	Tree() {
		root = null;
	}

	Tree(Comparator<Person> comparator) {
		this.comparator = comparator;
	}

	public Comparator<Person> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<Person> comparator) {
		this.comparator = comparator;
	}

	public void insert(Person person) {
		Root newNode = new Root(person);
		if (root == null)
			root = newNode;
		else
			root.insertNode(newNode);
	}

	// public void insertComparator(Person person) {
	// Root newNode = new Root(person);
	// if (root == null)
	// root = newNode;
	// else
	// insertNodeComp(newNode, person);
	// }

	// public void insertNodeComp(Root newNode, Person person) {
	// if (comparator.compare(newNode.getKey(), person) < 0) {
	// if (newNode.getLeft() == null)
	// newNode.setLeft(newNode);
	// else
	// newNode.getLeft().insertNode(newNode);
	// } else {
	// if (newNode.getRight() == null)
	// newNode.setRight(newNode);
	// else
	// newNode.getRight().insertNode(newNode);
	// }
	// }

	public void insertComparator(Person person) { // comparator
		root = insertNodeComparator(root, person);
	}

	private Root insertNodeComparator(Root node, Person person) { // comparator
		if (node == null) {
			return new Root(person);
		}

		if (comparator.compare(node.getKey(), person) > 0) {
			node.setLeft(insertRecursive(node.getLeft(), person));
		} else if (comparator.compare(node.getKey(), person) < 0) {
			node.setRight(insertRecursive(node.getRight(), person));
		}
		return node;
	}

	private Root insertRecursive(Root node, Person person) { // comparator
		if (node == null) {
			return new Root(person);
		}
		if (node.getKey().compareTo(person) > 0) {
			node.setLeft(insertRecursive(node.getLeft(), person));
		} else if (node.getKey().compareTo(person) < 0) {
			node.setRight(insertRecursive(node.getRight(), person));
		}
		return node;
	}

	public void preorder() {
		preorder(root);
	}

	public void showInfo(Root node) {
		System.out.println("---------------------");
		System.out.println("Imie: " + node.getKey().getName());
		System.out.println("Nazwisko: " + node.getKey().getSurname());
		System.out.println("Rok urodzenia: " + node.getKey().getYear());
		System.out.println("Waga: " + node.getKey().getWage());
	}

	public void preorder(Root node) {
		if (node == null) {
			return;
		}
		showInfo(node);
		// System.out.println(node.getKey().getName() + " " + node.getKey().getSurname() + " " + node.getKey().getYear()
		// 		+ " " + node.getKey().getWage());
		preorder(node.getLeft());
		preorder(node.getRight());

	}

	public void inorder() { // sorting
		inorder(root);
	}

	public void inorder(Root node) { // sorting
		if (node != null) {
			inorder(node.getLeft());
			showInfo(node);
			// System.out.println(node.getKey().getName() + " " + node.getKey().getSurname() + " "
			// 		+ node.getKey().getYear() + " " + node.getKey().getWage());
			inorder(node.getRight());
		}
	}

	public void deleteKey(Person person) {
		root = deleteNode(this.root, person);
	}

	// public void deleteKeyComparator(Person person) {
	// root = deleteNodeComparator(this.root, person);
	// }

	private Root deleteNode(Root node, Person person) {
		// tree is empty
		if (node == null)
			return node;

		// traverse the tree
		if (person.compareTo(node.getKey()) < 0) // traverse left subtree
			node.setLeft(deleteNode(node.getLeft(), person));
		else if (person.compareTo(node.getKey()) > 0) // traverse right subtree
			node.setRight(deleteNode(node.getRight(), person));
		else {
			// node contains only one child
			if (node.getLeft() == null)
				return node.getRight();
			else if (node.getRight() == null)
				return node.getLeft();

			// node has two children;
			// get inorder successor (min value in the right subtree)
			Person minVal = minValue(node.getRight());
			node.setKey(minVal);

			// Delete the inorder successor
			node.setRight(deleteNode(node.getRight(), minVal));
		}
		return node;
	}

	// private Root deleteNodeComparator(Root node, Person person) {
	// // tree is empty
	// if (node == null)
	// return node;

	// // traverse the tree
	// if (comparator.compare(node.getKey(), person) < 0) // traverse left subtree
	// node.setLeft(deleteNode(node.getLeft(), person));
	// else if (comparator.compare(node.getKey(), person) > 0) // traverse right
	// subtree
	// node.setRight(deleteNode(node.getRight(), person));
	// else {
	// // node contains only one child
	// if (node.getLeft() == null)
	// return node.getRight();
	// else if (node.getRight() == null)
	// return node.getLeft();

	// // node has two children;
	// // get inorder successor (min value in the right subtree)
	// Person minVal = minValue(node.getRight());
	// node.setKey(minVal);

	// // Delete the inorder successor
	// node.setRight(deleteNode(node.getRight(), minVal));
	// }
	// return node;
	// }

	private Person minValue(Root node) {
		if (node.getLeft() != null) {
			return minValue(node.getLeft());
		}
		return node.getKey();
	}

	public Root getRoot() {
		return root;
	}

	public void setRoot(Root root) {
		this.root = root;
	}

	public static void main(String[] args) {
		Tree tree = new Tree();
		Person personx = new Person("Maciek", "Zet", 1978, 77.7);
		Tree treec = new Tree(personx);

		Person person1 = new Person("Tomek", "Oplucinski", 1993, 50.4);
		Person person2 = new Person("Marek", "Ogorski", 1992, 70.5);
		Person person3 = new Person("Ola", "Eksinska", 1990, 60.7);
		Person person4 = new Person("Maja", "Zolska", 1970, 50.7);
		Person person5 = new Person("Filip", "Kapuscinski", 1995, 66.7);
		Person person6 = new Person("Ola", "Eksinska", 1990, 60.7);
		// Person person7 = new Person("Marek", "Zakulski", 1943, 60.1);
		// Person person8 = new Person("Zofil", "Zalewski", 1953, 100.1);
		// Person person9 = new Person("Mariusz", "Korecki", 1941, 64.8);
		// Person person10 = new Person("Eustachy", "Dupski", 1945, 99.4);
		Person person11 = new Person("Mateusz", "Alinski", 2002, 94.3);
		// Person person12 = new Person("Wojciech", "Wojciechowski", 1988, 77.7);
		// Person person13 = new Person("Mariusz", "Kowalski", 1966, 87.4);
		// Person person14 = new Person("Janusz", "Lewacki", 1954, 76.3);
		// Person person15 = new Person("Dominik", "Bracki", 1999, 87.4);
		// Person person16 = new Person("Kamil", "Kowalski", 1956, 65.5);
		// Person person17 = new Person("Daniel", "Downacki", 1945, 77.5);
		// Person person18 = new Person("Mariusz", "Olowski", 1894, 111.3);
		// Person person19 = new Person("Mateusz", "Golfacki", 1894, 87.6);
		// Person person20 = new Person("Andrzej", "Typecki", 1965, 65.7);
		// Person person21 = new Person("Aleksander", "Polonski", 1994, 86.7);
		// Person person22 = new Person("Jacek", "Jukewski", 1956, 67.4);
		// Person person23 = new Person("Dariusz", "Dusowski", 1967, 77.9);
		// Person person24 = new Person("Marek", "Pasacinski", 1965, 57.4);
		// Person person25 = new Person("Filip", "Bracki", 1965, 98.7);
		// Person person26 = new Person("Patrycja", "Znawska", 1993, 76.4);
		// Person person27 = new Person("Daria", "Cywinska", 2000, 66.7);
		// Person person28 = new Person("Kamila", "Srebranska", 1959, 66.4);
		// Person person29 = new Person("Julia", "Patryszewska", 1978, 73.4);
		// Person person30 = new Person("Filipna", "Focuszewska", 1974, 88.3);

		tree.insert(person1);
		tree.insert(person2);
		tree.insert(person3);
		tree.insert(person4);
		tree.insert(person5);

		treec.insertComparator(person1);
		treec.insertComparator(person2);
		treec.insertComparator(person3);
		treec.insertComparator(person4);
		treec.insertComparator(person5);
		treec.insertComparator(person6);
		treec.insertComparator(person11);

		// treec.deleteKeyComparator(person4);
		// treec.deleteKeyComparator(person6);
		// treec.deleteKeyComparator(person1);

		// tree.deleteKey(person4);
		// tree.deleteKey(person1);
		// tree.deleteKey(person2);

		System.out.println("Comparable: ");
		tree.preorder();
		System.out.println("Comparator: ");
		treec.preorder();

		// System.out.println("Posortowane: ");
		// tree.inorder();
		// treec.inorder();
	}
}