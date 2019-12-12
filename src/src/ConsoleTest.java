public class ConsoleTest {
    public static final String testingString = "4261357";

    public static void main(String args[]){
        CharacterSearchTree tree = new CharacterSearchTree(testingString.toCharArray());
        tree.showPreOrder();
        System.out.println(tree.height());
        System.out.println(tree.countCharacters());
        //tree.add('z', 5, "testcode");
        System.out.println(tree.equalStructure(new CharacterSearchTree("5372460".toCharArray())));
    }
}
