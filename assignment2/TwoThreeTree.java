//Sijia Yin 30049836; Chunyu Li 30056553; Boxiao Li 30069613
package cpsc331.assignment2;

import java.util.NoSuchElementException;
import cpsc331.collections.ElementFoundException;
import java.util.*;
//These codes for cpsc331 assignment2. 
//Author: Sijia Yin 30049836; Chunyu Li 30056553; Boxiao Li 30069613

/**
 *
 * Provides a 2-3 Tree Storing Values from an Ordered Type E.
 *
 */

// 2-3 Tree Invariant: A rooted tree T is represented, so that the
// following 2-3 Tree Properties are satisfied:
//
// a) Each leaf in T stores an element of type E, and the elements
//    stored at the leaves are distinct.
// b) Each internal node in T has either (exactly) two or three
//    children - which are either leaves or internal nodes of T.
// c) If an internal node x of T has exactly two children - a first
//    child and a second child, then every element of E stored at a
//    leaf in the subtree whose root is the first child is less than
//    every element of E stored at a leaf in the subtree whose root
//    is the second child.
// d) If an internal node x of T has exactly three children - a first
//    child, second child and third child, then every element of E
//    stored at a leaf in the subtree whose root is the first child
//    is less than every element of E stored at a leaf in the subtree
//    whose root is the second child, and every element of E stored at
//    a leaf in the subtree whose root is the second child is less than
//    every element of E stored at a leaf in the subtree whose root
//    is the third child.
// e) If an internal node x has exactly two children then the largest
//    elements stored in each of the subtrees whose roots are its
//    children are also stored at x (and are called firstMax
//    and secondMax).
// f) If an internal node x has exactly three children then the largest
//    elements stored in each of the subtrees whose roots are its
//    children are also stored at x (and are called firstMax, secondMax
//    and thirdMax).
// g) Every leaf in T is at the same level, that is, has the same
//    distance from the root of T.
// h) Each node in T is the root of a 2-3 tree as well. That is, the
//    subtree of T with root x also satisfies properties (a)-(g).

public class TwoThreeTree<E extends Comparable<E>>
{

    // Provides a node in this 2-3 Tree

    class TwoThreeNode
    {

	// Data Fields

	int numberChildren; // Number of children of this node; an
			    // integer between 0 and 4

	E element; // Element stored at this node; null
		   // if this is not a leaf

	TwoThreeNode firstChild; // First child
	E firstMax;		 // Largest element stored in first subtree
				 // Both are null if this node is a leaf

	TwoThreeNode secondChild; // Second child
	E secondMax;		  // Largest element stored in second subtree
				  // Both are null if this node has at most
				  // one child

	TwoThreeNode thirdChild; // Third child
	E thirdMax;		 // Largest element stored in third subtree
				 // Both are null if this node has at most
				 // two children

	TwoThreeNode fourthChild; // Fourth child
	E fourthMax;		  // Largest element stored in fourth subtree
				  // Both are null if this node has at most
				  // three children

	TwoThreeNode parent; // Parent; null if this node is the root
			     // of this tree


	// Constructor; constructs a TwoTreeNode with no children or parent,
	// storing null

	TwoThreeNode()
	{

	    numberChildren = 0;

	    element = null;

	    firstChild = null;
	    firstMax = null;

	    secondChild = null;
	    secondMax = null;

	    thirdChild = null;
	    thirdMax = null;

	    fourthChild = null;
	    fourthMax = null;

	    parent = null;
	}


	void printNode()
	{

	    System.out.println("\nSTART PRINT NODE: ----");
	    System.out.println("pointer " + this);
	    System.out.println("parent " + parent);
	    System.out.println("number of children: " + numberChildren);
	    System.out.println("element: " + element);
	    System.out.println("firstmax: " + firstMax);
	    System.out.println("firstchild: " + firstChild);
	    System.out.println("secondmax: " + secondMax);
	    System.out.println("secondchild: " + secondChild);
	    System.out.println("thirdmax: " + thirdMax);
	    System.out.println("thirdchild: " + thirdChild);
	    System.out.println("fourthmax: " + fourthMax);
	    System.out.println("fourtgchild: " + fourthChild);
	    System.out.println("END PRINT NODE: ----");
	}

	// Returns the number of children of this node

	int numberChildren()
	{

	    return numberChildren;
	}

	// Returns the element stored at this node if it is a leaf; returns
	// null otherwise.

	E element()
	{

	    return element;
	}

	// Returns the first child of this node if it is not a leaf; returns
	// null otherwise

	TwoThreeNode firstChild()
	{

	    return firstChild;
	}

	// Returns the largest value stored at the first subtree of this
	// node if it is not a leaf; returns null otherwise

	E firstMax()
	{

	    return firstMax;
	}

	// Returns the second child of this node if it has at least two
	// children; returns null otherwise

	TwoThreeNode secondChild()
	{

	    return secondChild;
	}

	// Returns the largest value stored at the first subtree of this
	// node if it has at least two children; returns null otherwise

	E secondMax()
	{

	    return secondMax;
	}

	// Returns the third child of this node if it has at least three
	// children; returns null otherwise

	TwoThreeNode thirdChild()
	{

	    return thirdChild;
	}

	// Returns the largest value stored at the third subtree of this
	// node if it has at least four children; returns null otherwise

	E thirdMax()
	{

	    return thirdMax;
	}

	// Returns the fourth child of this node if it has four children;
	// returns null otherwise

	TwoThreeNode fourthChild()
	{

	    return fourthChild;
	}

	// Returns the largest value stored at the fourth subtree of this
	// node if it has four chilren; returns null otherwise

	E fourthMax()
	{

	    return fourthMax;
	}

	// Returns the parent of this node

	TwoThreeNode parent()
	{

	    return parent;
	}
    }


    // Data Fields

    private TwoThreeNode root;

    /**
     *
     * Constructs an empty 2-3 Tree.
     *
     */

    // Precondition: None
    // Postcondition: An empty 2-3 Tree (satisfying the above
    //                2-3 Tree Invariant) has been created.

    public TwoThreeTree()
    {

	root = null;
    }

    // *****************************************************************
    //
    //   Searching in a 2-3 Tree
    //
    // *****************************************************************
    public void printTree()
    {
	printTreeR(root);
    }

    public void printTreeR(TwoThreeNode n)
    {
	Queue<TwoThreeNode> q = new LinkedList<>();

	q.add(root);

	while (!q.isEmpty()) {
	    TwoThreeNode tmp = q.remove();
	    if (tmp != null)
		tmp.printNode();
	    else {
		System.out.println("\nnull node");
		continue;
	    }

	    q.add(tmp.firstChild);
	    q.add(tmp.secondChild);
	    q.add(tmp.thirdChild);
	    q.add(tmp.fourthChild);
	}
    }

    /**
     *
     * Returns a TwoThreeNode with a given key<br>
     *
     * @param key the element to be searched for
     * @return the TwoThreeNode in this 2-3 tree storing the input key
     * @throws NoSuchElementException if the key is not in this tree
     *
     */

    // Precondition::
    // a) This 2-3 Tree satisfies the above 2-3 Tree Properties.
    // b) A non-null key with type E is given as input.
    //
    // Postcondition:
    // a) If the key is stored in this 2-3 tree then the node storing it is
    //    returned as output. A NoSuchElementException is thrown, otherwise.
    // b) This 2-3 Tree has not been changed, so it still satisfies
    //    the 2-3 Tree Properties.

    public TwoThreeNode search(E key) throws NoSuchElementException
    {
	if (root == null) {
	    throw new NoSuchElementException();
	} else {
	    return get(key, root);
	}
    }

    //
    // Searches for a given key in the subtree with a given node as root
    //
    // Precondition:
    // a) This 2-3 tree satisfies the above 2-3 Tree Properties.
    // b) key is a non-null input with type E.
    // c) x is a non-null TwoThreeNode in this 2-3 Tree, that is
    //    also given as input.
    //
    // Postcondition:
    // a) If the key is stored in the subtree with root x, then the node
    //    storing the key is returned as output. A NoSuchElementException
    //    is thrown otherwise.
    // b) This 2-3 Tree has not been changed, so it still satisfies
    //    the 2-3 Tree Properties.

    private TwoThreeNode get(E key, TwoThreeNode x)
	throws NoSuchElementException
    {
	if (x.element() != null) { // if x is a leaf
	    if (key.compareTo(x.element())
		== 0) { // if key is equal to element stored at x
		return x;
	    } else {
		throw new NoSuchElementException();
	    }
	} else if (x.numberChildren() == 2) { // if x has 2 children
	    if (key.compareTo(x.firstMax()) <= 0) {
		return get(key, x.firstChild());
	    } else {
		return get(key, x.secondChild());
	    }
	} else {
	    if (key.compareTo(x.firstMax()) <= 0) {
		return get(key, x.firstChild());
	    } else if (key.compareTo(x.secondMax()) <= 0) {
		return get(key, x.secondChild());
	    } else {
		return get(key, x.thirdChild());
	    }
	}
    }

    // ********************************************************************
    //
    //   Insertions in a 2-3 Tree
    //
    // ********************************************************************

    // The following "Modified Tree" properties are satisfied at the
    // beginning or end of private methods called by public ones provided
    // by this 2-3 tree.
    //
    // a) This tree, T, satisfies 2-3 Tree properties (a), (c), (d), (e),
    //    (f), and (g) - but not, necessarily, 2-3 Tree properties (b)
    //    or (h).
    // b) Every internal node of T has (exactly) either one, two, three
    //    or four children - which are each either leaves or internal nodes
    //    of T. There is at most one internal node of T that has (exactly)
    //    either one or four children - all other internal nodes of T have
    //    either exactly two or three children.
    // c) If an internal node x of T has exactly one child, then this is
    //    called a first child, which is the root of a first subtree of the
    //    subtree with root x. In this case, the largest element of E stored
    //    in a leaf of the first subtree is stored at x, as the value of
    //    x.firstMax.
    // d) If an internal node x of T has four children - a first child, second
    //    child, third child and fourth child, which are the roots of the
    //    first subtree, second subtree, third subtree and fourth subtree of
    //    the subtree of T with root x, respectively - then each of the values
    //    stored at leaves of the first subtree is less than each of the values
    //    stored at leaves of the second subtree, each of the values stored at
    //    leaves of the second subtree is less than each of the values stored
    //    at leaves of the third subtree, and each of the values stored at
    //    leaves of the third subtree is less than values stored at leaves of
    //    the fourth subtree.
    // e) If an internal node x of T has exactly four children then the
    //    largest values stored at the leaves of the first, second, third and
    //    fourth subtrees of the subtree with root x are stored at x - and are
    //    called firstMax, secondMax, thirdMax and fourthMax, respectively.
    // f) Each node x of T is the root of a rooted tree satisfying these
    //    properties as well. That is, the subtree with root x also satisfies
    //    the above properties (a)-(e), for eery node x in T.

    /**
     *
     * Inserts an input key into this 2-3 Tree
     *
     * @param key the key to be inserted into this 2-3 Tree
     * @throws ElementFoundException if the input key is already stored
     *         in this tree
     *
     */

    // Precondition:
    // a) This 2-3 Tree, T, satisfies the above 2-3 Tree Properties.
    // b) key is a non-null input of type E.
    //
    // Postcondition:
    // a) If the input key is not already included in the subset of E
    //    represented by T then it is added to this subset (with this subset
    //    being otherwise unchanged). An ElementFoundException is thrown and
    //    the set is not changed, if the key already belongs to this subset.
    // b) T satisfies the 2-3 Tree oroperties given above.


    public void insert(E key) throws ElementFoundException
    {
	if (root == null) {
	    addFirstElement(key);
	} else if (root.element() != null) { // if root is a leaf
	    addSecondElement(key);
	} else {
	    insertIntoSubtree(key, root);
	    if (root.numberChildren() == 4) { // if root has 4 children
		fixRoot();
	    }
	}
    }

    // Inserts an input key into this 2-3 Treee when it is Empty
    //
    // Precondition:
    // a) This 2-3 Tree, T, satisfies the above 2-3 tree properties - and
    //    is empty.
    // b) key is a non-null input with type E.
    //
    // Postcondition:
    // a) The input key has been added to the subset of E represented by T,
    //    which is otherwise unchanged.
    // b) T satisfies the 2-3 Tree properties given above.

    private void addFirstElement(E key)
    {
	root = new TwoThreeNode();
	root.element = key;
    }

    // Inserts an input key into this 2-3 tree when the root of this tree
    // is a leaf
    //
    // Precondition:
    // a) This 2-3 Tree, T, satisfies the 2-3 Tree properties - and the
    //    root of this tree is a leaf, so that T represents a subset of E
    //    with size one.
    // b) key is a non-null input with type E.
    //
    // Postcondition:
    // a) Id the input key does not belong to the subset of E represented
    //    by T then they key is added to this subset - which is otherwise
    //    unchanged. If the key does already belong to this subset and an
    //    ElementFoundException is thrown and T is not changed.
    // b) T satisfies the 2-3 Tree properties given above.

    private void addSecondElement(E key) throws ElementFoundException
    {
	TwoThreeNode temp = new TwoThreeNode();
	TwoThreeNode keyNode = new TwoThreeNode();
	keyNode.element = key;

	temp.numberChildren = 2; // temp have 2 children
	keyNode.parent = temp;   // setting keyNode's parent as temp

	if (root.element().compareTo(key)
	    == 0) { // if key already contained in the tree
	    throw new ElementFoundException(
		"element found inside addSecondElement");
	} else if (root.element().compareTo(key) < 0) { // if root < newKey
	    temp.firstMax = root.element();
	    temp.firstChild = root; // first child is root
	    temp.secondMax = key;
	    temp.secondChild = keyNode; // second child is keynode
	} else {			// if root > newKey
	    temp.firstMax = key;
	    temp.firstChild = keyNode; // first child is keynode
	    temp.secondMax = root.element();
	    temp.secondChild = root; // second child is root
	}
	root.parent = temp; // setting root's parent as temp
	root = temp;	// make root point to temp
    }

    // Inserts a given key into the subtree of T with a given node x
    // as root if x is an internal node; throws an exception to aid the
    // inclusion of the input key if x is a leaf
    //
    // Precondition:
    // a) This 2-3 tree, T, satisfies the 2-3 Tree properties given above.
    // b) key is a non-null input with type E.
    // c) x is a non-null node in T.
    // d) Either key is not stored at any leaf in T or it is stored in a
    //    leaf of the subtree of T with root x.
    //
    // Postcondition:
    // a) If the input key already belongs to the subset of E stored at
    //    the leaves in the subtree of T with root x, then an
    //    ElementFoundException is thrown and T is not changed.
    // b) If x is a leaf that stores an element of E that is not equal
    //    to the input key then a NoSuchElementException is thrown and
    //    T is not changed.
    // c) If x is an internal node and the input key does not (initially)
    //    belong to the subset of E stored at the leaves in the subtree
    //    of T with root x, then
    //    - the input key is added to the subset of E stored at the leaves
    //      of T - which is otherwise unchanged;
    //    - either T satisfies the 2-3 Tree properties, given above, or
    //      T satisfies the "Modified Tree" properties, given above, and
    //      x is now an internal node with four children.

    private void insertIntoSubtree(E key, TwoThreeNode x)
	throws ElementFoundException, NoSuchElementException
    {
	if (x.element() != null) { // if x is a leaf
	    E e = x.element();
	    if (e.compareTo(key) == 0) {
		throw new ElementFoundException(
		    "element found in insertIntoSubtree");
	    } else {
		throw new NoSuchElementException();
	    }
	} else {
	    try {
		if (x.numberChildren() == 2) { // if x has two children
		    if (key.compareTo(x.firstMax()) <= 0) {
			insertIntoSubtree(key, x.firstChild);
		    } else {
			insertIntoSubtree(key, x.secondChild);
		    }
		} else {
		    if (key.compareTo(x.firstMax()) <= 0) {
			insertIntoSubtree(key, x.firstChild);
		    } else if (key.compareTo(x.secondMax()) <= 0) {
			insertIntoSubtree(key, x.secondChild);
		    } else {
			insertIntoSubtree(key, x.thirdChild);
		    }
		}
		raiseSurplus(x);
	    } catch (NoSuchElementException ex) {
		addLeaf(key, x);
	    }
	}
    }

    // Brings a node with four children closer to the root, if one exists
    // in this modified tree
    //
    // Precondition:
    // a) This tree, T, satisfies the "Modified Tree" properties given above.
    // b) x is an internal node of T whose children are also internal nodes
    //    in T.
    // c) Either T is a 2-3 tree (that is, it satisfies the above "2-3 Tree"
    //    properties), or one of the children of x has four children.
    //
    // Postcondition:
    // a) The subset of E stored at the leaves of T has not been changed.
    // b) Either T is a 2-3 tree (that is, it satisfies the above "2-3 Tree"
    //    properties), or T satisfies the "Modified Tree" properties and x
    //    has four children.

    private void raiseSurplus(TwoThreeNode x)
    {
	ArrayList<TwoThreeNode> children = new ArrayList<TwoThreeNode>() {
	    {
		add(x.firstChild());
		add(x.secondChild());
		add(x.thirdChild());
		add(x.fourthChild());
	    }
	}; // stores the children of x
	TwoThreeNode fourChildrenNode = new TwoThreeNode();
	boolean hasFourChildren = false;

	int i = 0, indexOfFourChild = 0;
	while (i < children.size() && children.get(i) != null) {
	    if (children.get(i).numberChildren() == 4) {
		hasFourChildren = true; // hasFourChildren is true
		fourChildrenNode =
		    children.get(i); // fourChildrenNode have same pointer value
				     // as children.get(i)A
		indexOfFourChild = i;
		break;
	    }
	    ++i;
	}

	if (hasFourChildren) { // if a child of x has 4 children
	    TwoThreeNode tempNodeLeft = new TwoThreeNode();
	    TwoThreeNode tempNodeRight = new TwoThreeNode();
	    tempNodeLeft.numberChildren = 2;
	    tempNodeLeft.parent = x; // tempNodeLeft's parent is x
	    tempNodeRight.numberChildren = 2;
	    tempNodeRight.parent = x; // tempNodeRight's parent is x


	    tempNodeLeft.firstChild = fourChildrenNode.firstChild();
	    tempNodeLeft.firstMax = fourChildrenNode.firstMax();
	    fourChildrenNode.firstChild().parent = tempNodeLeft;

	    tempNodeLeft.secondChild = fourChildrenNode.secondChild();
	    tempNodeLeft.secondMax = fourChildrenNode.secondMax();
	    fourChildrenNode.secondChild().parent = tempNodeLeft;


	    tempNodeRight.firstChild = fourChildrenNode.thirdChild();
	    tempNodeRight.firstMax = fourChildrenNode.thirdMax();
	    fourChildrenNode.thirdChild().parent = tempNodeRight;

	    tempNodeRight.secondChild = fourChildrenNode.fourthChild();
	    tempNodeRight.secondMax = fourChildrenNode.fourthMax();
	    fourChildrenNode.fourthChild().parent = tempNodeRight;


	    x.numberChildren++;
	    // modify the array
	    // children.remove(indexOfFourChild);
	    children.set(indexOfFourChild, tempNodeLeft);
	    children.add(indexOfFourChild + 1, tempNodeRight);

	    x.firstChild = children.get(0);
	    x.firstMax = getMaxFromNode(children.get(0));
	    x.secondChild = children.get(1);
	    x.secondMax = getMaxFromNode(children.get(1));
	    x.thirdChild = children.get(2);
	    x.thirdMax = getMaxFromNode(children.get(2));
	    x.fourthChild = children.get(3);
	    x.fourthMax = getMaxFromNode(children.get(3));
	} else {
	    x.firstChild = children.get(0);
	    x.firstMax = getMaxFromNode(children.get(0));
	    x.secondChild = children.get(1);
	    x.secondMax = getMaxFromNode(children.get(1));
	    x.thirdChild = children.get(2);
	    x.thirdMax = getMaxFromNode(children.get(2));
	    x.fourthChild = children.get(3);
	    x.fourthMax = getMaxFromNode(children.get(3));
	}
    }

    private E getMaxFromNode(TwoThreeNode node)
    {
	if (node == null) {
	    return null;
	}
	if (node.element() != null) {
	    return node.element();
	}
	if (node.numberChildren == 1) {
	    return node.firstMax();
	} else if (node.numberChildren == 2) {
	    return node.secondMax();
	} else if (node.numberChildren == 3) {
	    return node.thirdMax();
	} else { // node has 4 children
	    return node.fourthMax();
	}
    }

    // Adds a leaf storing a given value as a child of a given
    // internal node
    //
    // Precondition:
    // a) This tree, T,is a 2-3 tree (that is,it satisfies the 2-3 Tree
    //    properties given above).
    // b) x is an input internal node in T whose children are leaves.
    // c) key is a non-null input element of E that is not in the set of
    //    elements of E stored at leaves of T.
    // d) It is possible to produce a tree satisfying the "Modified Tree"
    //    properties, given above, by adding a leaf storing the input key
    //    as a child of x.
    //
    // Postcondition:
    // a) The input key has been added to the set of elements stored at the
    //    leaves of T, which is otherwise unchanged.
    // b) Either T is a 2-3 Tree or T satisfies the above "Modified Tree"
    //    properties and x has four children.

    private void addLeaf(E key, TwoThreeNode x)
    {
	TwoThreeNode keyNode = new TwoThreeNode();
	keyNode.parent = x;
	keyNode.element = key;

	ArrayList<TwoThreeNode> children = new ArrayList<TwoThreeNode>() {
	    {
		add(x.firstChild());
		add(x.secondChild());
		add(x.thirdChild());
		add(x.fourthChild());
	    }
	}; // stores the children of x

	boolean foundLarge = false;
	int i = 0, indexOfKeyInsertion = 0;
	while (children.get(i) != null
	       && (children.get(i).element().compareTo(key) <= 0)) {
	    ++i;
	}

	indexOfKeyInsertion = i;

	children.add(indexOfKeyInsertion, keyNode); // insert

	x.numberChildren++;

	x.firstChild = children.get(0);
	x.firstMax = getMaxFromNode(children.get(0));
	x.secondChild = children.get(1);
	x.secondMax = getMaxFromNode(children.get(1));
	x.thirdChild = children.get(2);
	x.thirdMax = getMaxFromNode(children.get(2));

	x.fourthChild = children.get(3);
	x.fourthMax = getMaxFromNode(children.get(3));
    }


    // Completes the restoration of a 2-3 tree after the
    // "insertIntoSubtree" method has applied and the root has four
    // children
    //
    // Precondition:
    // a) T is a rooted tree, satisfying the above "Modified Tree"
    //    properties, whose root is an internal node with four children.
    //
    // Postcondition:
    // a) The subset of E represented by T has not been changed.
    // b) T now satisfies the "2-3 Tree" properties given above.

    private void fixRoot()
    {
	TwoThreeNode fstChildOfRoot = new TwoThreeNode();
	TwoThreeNode sndChildOfRoot = new TwoThreeNode();
	TwoThreeNode newRoot = new TwoThreeNode();
	fstChildOfRoot.parent = newRoot;
	fstChildOfRoot.numberChildren = 2;
	sndChildOfRoot.parent = newRoot;
	sndChildOfRoot.numberChildren = 2;
	newRoot.numberChildren = 2;

	fstChildOfRoot.firstChild = root.firstChild();
	fstChildOfRoot.firstMax = root.firstMax();
	root.firstChild().parent = fstChildOfRoot;

	fstChildOfRoot.secondChild = root.secondChild();
	fstChildOfRoot.secondMax = root.secondMax();
	root.secondChild().parent = fstChildOfRoot;

	sndChildOfRoot.firstChild = root.thirdChild();
	sndChildOfRoot.firstMax = root.thirdMax();
	root.thirdChild().parent = sndChildOfRoot;

	sndChildOfRoot.secondChild = root.fourthChild();
	sndChildOfRoot.secondMax = root.fourthMax();
	root.fourthChild().parent = sndChildOfRoot;

	newRoot.firstChild = fstChildOfRoot;
	newRoot.firstMax = fstChildOfRoot.secondMax();
	newRoot.secondChild = sndChildOfRoot;
	newRoot.secondMax = sndChildOfRoot.secondMax();

	root = newRoot;
    }

    // *****************************************************************
    //
    //   Deletions from a 2-3 Tree
    //
    // *****************************************************************

    /**
     *
     * Removes an input key from this 2-3 Tree
     *
     * @param key the key to be removed from this 2-3 Tree
     * @throws NoSuchElementException if the input key is not already
     *         stored in this tree
     *
     */

    // Precondition:
    // a) This 2-3 Tree, T, satisfies the above 2-3 Tree Properties.
    // b) key is a non-null input of type E.
    //
    // Postcondition:
    // a) If the input key is included in the subset of E represented
    //    by T then it is removed from this subset (with this subset
    //    being otherwise unchanged). A NoSuchElementException  is thrown
    //    and the set is not changed, if the key already belongs to
    //    this subset.
    // b) T satisfies the 2-3 Tree oroperties given above.

    // took ideas from this website:
    // https://www2.cs.duke.edu/courses/cps100e/spring99/lects/sect1623treeH.pdf
    public void delete(E key)throws NoSuchElementException
    {
	TwoThreeNode leafPos = search(key);
	// if key not found search method should throw no usch element exception
	deleteNode(leafPos, leafPos.parent());
    }

    public void fixMax(TwoThreeNode p)
    {
	// initial input will be the deleted node's parent
	// should fix root too
	while (p != null) {
	    p.firstMax = getMaxFromNode(p.firstChild);
	    p.secondMax = getMaxFromNode(p.secondChild);
	    p.thirdMax = getMaxFromNode(p.thirdChild);
	    p = p.parent;
	}
    }

    // will start at the leaf's parent and delete
    public void deleteNode(TwoThreeNode nodeToDelete, TwoThreeNode p)
    {
	if (root.element() != null) { // if root is a leaf
	    if (root.element().compareTo(nodeToDelete.element())
		== 0) {      // if root = leaf
		root = null; // delete root
	    } else {
		throw new NoSuchElementException();
	    }
	} else if (hasThreeChild(p)) { // if p have 3 child
	    // first time should have 3 leaf child
	    ArrayList<TwoThreeNode> children = new ArrayList<TwoThreeNode>() {
		{
		    add(p.firstChild());
		    add(p.secondChild());
		    add(p.thirdChild());
		}
	    }; // stores the children of p

	    int i = 0;
	    // while the child is not the nodeToDelete
	    while (i < children.size() && children.get(i) != nodeToDelete) {
		++i;
	    }

	    // when loop finish it should have found the key to delete
	    children.remove(i);
	    // p now have 2 children, and the algorithm will terminate
	    p.firstChild = children.get(0);
	    p.firstMax = getMaxFromNode(children.get(0));
	    p.secondChild = children.get(1);
	    p.secondMax = getMaxFromNode(children.get(1));
	    p.thirdChild = null;
	    p.thirdMax = null;

	    nodeToDelete.parent = null;
	    nodeToDelete = null;

	    p.numberChildren = 2;
	    // fix max of p
	    fixMax(p);

	} else { // p have 2 child
	    // first time should have 3 leaf child

	    // if p is root
	    if (p == root) { // can compare pointers here..
		TwoThreeNode newRoot = new TwoThreeNode();
		if (p.firstChild() == nodeToDelete) { // if remove first child
		    newRoot = p.secondChild();
		} else {
		    newRoot = p.firstChild(); // if remove second child
		}
		// new root with the other child as root
		newRoot.parent = null;

		root = newRoot;
	    } else {
		// note that not all these siblings can exist, but one of them
		// must exist
		TwoThreeNode leftSiblingOfP = new TwoThreeNode();
		TwoThreeNode rightSiblingOfP = new TwoThreeNode();
		leftSiblingOfP = leftSibling(p);
		rightSiblingOfP = rightSibling(p);

		if (leftSiblingOfP != null
		    && leftSiblingOfP.numberChildren()
			   == 3) { // if left sibling opf p exist and it have 3
				   // children
		    // just move left sib child to p
		    if (p.firstChild()
			== nodeToDelete) { // if want delete first child
					   // do nothing
		    } else {		   // want delete second child
			p.secondChild = p.firstChild();
			p.secondMax =
			    p.firstMax; // second max = first max, since first
					// max will be replaced (get smaller)
		    }
		    // want shift 3rd child of left sib into 1st child
		    leftSiblingOfP.thirdChild().parent = p;
		    leftSiblingOfP.numberChildren = 2;
		    // put left sib into p
		    p.firstChild = leftSiblingOfP.thirdChild();
		    p.firstMax = leftSiblingOfP.thirdMax();
		    // delete from left sib
		    leftSiblingOfP.thirdChild = null;
		    leftSiblingOfP.thirdMax = null;
		    // fix max of p
		    fixMax(p);
		} else if (rightSiblingOfP != null
			   && rightSiblingOfP.numberChildren()
				  == 3) { // if right sibling opf p exist and it
					  // have 3 children
		    // just move right sib child to p
		    if (p.secondChild()
			== nodeToDelete) { // if want delete second child
					   // do nothing
		    } else {		   // want delete first child
			// make first child as second child
			p.firstChild = p.secondChild();
			p.firstMax = p.secondMax; // since second max will be
						  // replaced (get bigger)
		    }
		    // move 1st child of right sib into second child of p
		    rightSiblingOfP.firstChild().parent = p;
		    rightSiblingOfP.numberChildren = 2;
		    // put right sib into p
		    p.secondChild = rightSiblingOfP.firstChild();
		    p.secondMax = rightSiblingOfP.firstMax();

		    // move 2nd to 1st, 3rd to 2nd, and make 3rd null......
		    rightSiblingOfP.firstChild = rightSiblingOfP.secondChild();
		    rightSiblingOfP.firstMax = rightSiblingOfP.secondMax();

		    rightSiblingOfP.secondChild = rightSiblingOfP.thirdChild();
		    rightSiblingOfP.secondMax = rightSiblingOfP.thirdMax();

		    rightSiblingOfP.thirdChild = null;
		    rightSiblingOfP.thirdMax = null;
		    // fix max of p
		    fixMax(p);
		} else {
		    // hard case, left and/or right sibling have 2 children
		    if (leftSiblingOfP != null) { // if left sib is not null
			// move the remaining child in p to left
			TwoThreeNode temp = new TwoThreeNode();
			if (p.firstChild()
			    == nodeToDelete) { // if want delete first child
			    temp = p.secondChild();

			} else { // want delete second child
			    temp = p.firstChild();
			}
			p.secondChild = null; // delete second child
			p.secondMax = null;

			p.firstChild = null; // delete first child
			p.firstMax = null;

			temp.parent = leftSiblingOfP; // change temp's parent
			leftSiblingOfP.numberChildren = 3;
			p.numberChildren = 0;
			// transfer max
			leftSiblingOfP.thirdChild = temp;
			leftSiblingOfP.thirdMax = getMaxFromNode(temp);
			// mark p to be the deleted node, and recursivly delete

			deleteNode(p, p.parent());
			// fix max of p
			fixMax(p);
		    }

		    else { // right sib is not null

			TwoThreeNode temp = new TwoThreeNode();
			if (p.firstChild()
			    == nodeToDelete) { // if want delete first child
			    temp = p.secondChild();

			} else { // want delete second child
			    temp = p.firstChild();
			}
			p.secondChild = null;
			p.secondMax = null;

			p.firstChild = null;
			p.firstMax = null;

			temp.parent = rightSiblingOfP;
			rightSiblingOfP.numberChildren = 3;
			p.numberChildren = 0;
			// transfer max
			rightSiblingOfP.thirdChild =
			    rightSiblingOfP.secondChild;
			rightSiblingOfP.thirdMax = rightSiblingOfP.secondMax;
			rightSiblingOfP.secondChild =
			    rightSiblingOfP.firstChild;
			rightSiblingOfP.secondMax = rightSiblingOfP.firstMax;

			rightSiblingOfP.firstChild = temp;
			rightSiblingOfP.firstMax = getMaxFromNode(temp);
			// mark p to be the deleted node, and recursivly delete

			deleteNode(p, p.parent());

			// fix max of p
			fixMax(p);
		    }
		}
	    }
	}
    }

    boolean hasThreeChild(TwoThreeNode x)
    {
	// if 3 child are not null, and the first child is a leaf
	return x.firstChild() != null && x.secondChild() != null
	    && x.thirdChild() != null;
    }

    TwoThreeNode leftSibling(TwoThreeNode x)
    {
	// can compare pointers here..
	if (x.parent().secondChild()
	    == x) { // if x is second child of its parent
	    return x.parent().firstChild();
	} else if (x.parent().thirdChild() == x) { // if x is third child
	    return x.parent().secondChild();
	} else { // if x is first child
	    return null;
	}
    }

    TwoThreeNode rightSibling(TwoThreeNode x)
    {
	// can compare pointers here..
	if (x.parent().secondChild()
	    == x) { // if x is second child of its parent
	    return x.parent().thirdChild();
	} else if (x.parent().firstChild() == x) { // if x is first child
	    return x.parent().secondChild();
	} else { // if x is third child
	    return null;
	}
    }
    // *****************************************************************
    //
    //   Additional Code for Testing
    //
    // *****************************************************************

    // Returns a reference to the root of this 2-3 Tree

    TwoThreeNode root()
    {

	return root;
    }
}
