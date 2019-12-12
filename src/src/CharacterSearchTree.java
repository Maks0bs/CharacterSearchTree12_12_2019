public class CharacterSearchTree {
    private HuffmanTriple content;
    private CharacterSearchTree leftChild, rightChild;

    public static class HelperClass{
        public static int max(int a, int b){
            if (a > b){
                return a;
            }
            else{
                return b;
            }
        }

        public static int min(int a, int b){
            if (a < b){
                return a;
            }
            else{
                return b;
            }
        }

        public static char min(char a, char b){
            if (a < b){
                return a;
            }
            else{
                return b;
            }
        }
    }

    public CharacterSearchTree() {
        content = null;
        leftChild = null;
        rightChild = null;
    }

    public HuffmanTriple getContent() {
        if ( !isEmpty() ) {
            return content;
        } else {
            throw new IllegalStateException();
        }
    }

    public CharacterSearchTree(char str[]){ //AUFGABE 1
        for (int i = 0; i < str.length; i++){
            this.add(str[i]);
        }
    }

    //TODO fix bug in task 2: not clear if you have to set code for all entries of equal characters or create a seperate node for the same character, but with a different code
    public void add(char t, int q, String c){ //AUFGABE 2
        if ( isEmpty() ) {
            content = new HuffmanTriple(t, q);
            content.setCode(c);
            leftChild = new CharacterSearchTree();
            rightChild = new CharacterSearchTree();
        }
        else {
            if ( content.getToken() > t ) {
                leftChild.add(t, q, c);
            }
            else if ( content.getToken() < t ) {
                rightChild.add(t, q, c);
            }
            else {
                for (int i = 0; i < q; i++){
                    content.incrementQuantity();
                }

                content.setCode(c);
            }
        }
    }

    public void showPreOrder(){// AUFGABE 3
        if (isEmpty()){
            System.out.println("*");
        }
        else{
            System.out.println(content.toString());
            leftChild.showPreOrder();
            rightChild.showPreOrder();
        }
    }

    public int height(){//AUFGABE 4
        if (isEmpty()){
            return 0;
        }
        else{
            return 1 + HelperClass.max(leftChild.height(), rightChild.height());
        }
    }

    public int countCharacters(){ //AUFGABE 5
        if (isEmpty()){
            return 0;
        }
        else{
            return this.content.getQuantity() + leftChild.countCharacters() + rightChild.countCharacters();
        }
    }

    public int longestCode(){ //AUFGABE 6
        if (isEmpty()){
            return 0;
        }
        else{
            String curCode = this.content.getCode();
            String leftCode = this.leftChild.content.getCode();
            String rightCode = this.rightChild.content.getCode();
            int maxChildrenCodeLength = HelperClass.max(leftCode.length(), rightCode.length());
            return HelperClass.max(maxChildrenCodeLength, curCode.length());
        }
    }

    //Don't know how to solve without toArray yet
    public HuffmanTriple minimum(){ //AUFGABE 7
        if (isEmpty()){
            return null;
        }
        else{
            HuffmanTriple nodes[] = this.toArray();
            char minChar = nodes[0].getToken();
            HuffmanTriple result = nodes[0];


            for (int i = 0; i < nodes.length; i++){
                if (nodes[i].getToken() < minChar){
                    minChar = nodes[i].getToken();
                    result = nodes[i];
                }
            }

            return result;
        }
    }

    public boolean hasOnlyCompleteNode(){ //AUFGABE 8 (not sure, but works correctly
        if (isEmpty()){
            return true;
        }
        else{
            int childCount = 0;
            if (!this.leftChild.isEmpty()){
                childCount++;
            }
            if (!this.rightChild.isEmpty()){
                childCount++;
            }

            if (childCount == 1 || !this.leftChild.hasOnlyCompleteNode() || !this.rightChild.hasOnlyCompleteNode()){
                return false;
            }
            else{
                return true;
            }
        }
    }

    public boolean containsCharacter(char t){ //AUFGABE 9
        if (isEmpty()){
            return false;
        }
        else{
            if (this.content.getToken() == t ||
                this.leftChild.containsCharacter(t) || this.rightChild.containsCharacter(t)){

                return true;
            }
            else{
                return false;
            }
        }
    }

    public boolean equalStructure(CharacterSearchTree cst){ //AUFGABE 10
        if (this.isEmpty() && cst.isEmpty()){
            return true;
        }
        else if ((this.isEmpty() && !cst.isEmpty()) || (!this.isEmpty() && cst.isEmpty())){
            return false;
        }
        boolean equalLeft = this.leftChild.equalStructure(cst.leftChild);
        boolean equalRight = this.rightChild.equalStructure(cst.rightChild);
        return !cst.isEmpty() && !this.isEmpty() && equalLeft && equalRight;
    }


    /*  not sure how to do this without changing the structure - only the case,
        described in the example is possible to rotate in such a way,
        otherwise the right child of the root's left child has to move to the right subtree
        see https://en.wikipedia.org/wiki/Tree_rotation
     */
    public CharacterSearchTree rotateNodeToRight(){ //AUFGABE 11
        CharacterSearchTree rightChildOfLeft =


    }



    public boolean isEmpty() {
        return content == null;
    }

    public boolean isLeaf() {
        return !isEmpty() && leftChild.isEmpty() && rightChild.isEmpty();
    }

    public void add( char t ) {
        if ( isEmpty() ) {
            content = new HuffmanTriple( t );
            leftChild = new CharacterSearchTree();
            rightChild = new CharacterSearchTree();
        }
        else {
            if ( content.getToken() > t ) {
                leftChild.add( t );
            }
            else if ( content.getToken() < t ) {
                rightChild.add( t );
            }
            else {
                content.incrementQuantity();
            }
        }
    }

    public void iterativeAdd( char t ) { //Why do we need this method if we have add?
        CharacterSearchTree current = this;
        while ( !current.isEmpty() && current.content.getToken() != t ) {
            if ( current.content.getToken() > t ) {
                current = current.leftChild;
            }
            else {
                current = current.rightChild;
            }
        }
        if ( current.isEmpty() ) {
            current.content = new HuffmanTriple( t );
            current.leftChild = new CharacterSearchTree();
            current.rightChild = new CharacterSearchTree();
        }
        else {
            current.content.incrementQuantity();
        }
    }

    public String getCode( char t ) {
        if ( !isEmpty() )
        {
            if ( content.getToken() > t )
            {
                return leftChild.getCode( t );
            }
            else if ( content.getToken() < t )
            {
                return rightChild.getCode( t );
            }
            else
            {
                return content.getCode();
            }
        }
        else
        {
            throw new IllegalStateException();
        }
    }

    public int size() {
        if ( isEmpty() )
        {
            return 0;
        }
        else
        {
            return 1 + leftChild.size() + rightChild.size();
        }
    }

    public void show() {
        if ( !isEmpty() )
        {
            leftChild.show();
            System.out.println( content.toString() );
            rightChild.show();
        }
    }

    public HuffmanTriple[] toArray() {
        if ( !isEmpty() )
        {
            HuffmanTriple[] collector = new HuffmanTriple[size()];
            toArray( collector, 0 );
            return collector;
        }
        return new HuffmanTriple[0];
    }

    private int toArray( HuffmanTriple[] collector, int index ) {
        if ( !isEmpty() )
        {
            index = leftChild.toArray( collector, index );
            collector[index] = content;
            index = rightChild.toArray( collector, index + 1 );
        }
        return index;
    }
}
