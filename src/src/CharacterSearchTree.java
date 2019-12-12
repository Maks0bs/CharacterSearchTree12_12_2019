public class CharacterSearchTree {
    private HuffmanTriple content;
    private CharacterSearchTree leftChild, rightChild;

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
