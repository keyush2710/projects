import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Huffman {
    public static void main(String[] args) throws FileNotFoundException
    {
        int count=0;
        // taking the name of the file from which the data has to read
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the file name to read from");
        String fileName = sc.nextLine();
        File file = new File(fileName);
        Scanner input = new Scanner(file);
        // declaring queues
        Queue<BinaryTree<Pair>> S = new Queue<BinaryTree<Pair>>();
        Queue<BinaryTree<Pair>> T = new Queue<BinaryTree<Pair>>();

        while (input.hasNextLine()) {
            char c = input.next().charAt(0);
            Pair p = new Pair(c, input.nextDouble());
            //creating a binary tree
            BinaryTree<Pair> bt = new BinaryTree<Pair>();
            bt.makeRoot(p);
            //adding binary tree to the queue
            S.add(bt);
        }

        // the loop will run untill queue S is empty
        while (!S.isEmpty()) {
            BinaryTree<Pair> P = new BinaryTree<Pair>();
            BinaryTree<Pair> A = new BinaryTree<Pair>();
            BinaryTree<Pair> B = new BinaryTree<Pair>();
            //checking whether the queue T is empty or not
            //if empty the adding two elements from queue S to binary tree A and B as they are the lowest in probability
            if (T.isEmpty()) {
                A = S.get(0);
                B = S.get(1);
                // removing elements from the queue
                S.remove();
                S.remove();
            }
            else {
                /*checking whether the probability of element in queue S is less than element in queue T
                  and accordingly adding element to binary tree A and B */
                if (S.get(0).getData().getProb() < T.get(0).getData().getProb()) {
                    A = S.get(0);
                    S.remove();
                } else {
                    A = T.get(0);
                    T.remove();
                }
                if(T.isEmpty())
                {
                    B=S.get(0);
                    S.remove();
                }
                else{
                    if (S.get(0).getData().getProb() < T.get(0).getData().getProb()) {
                        B = S.get(0);
                        S.remove();
                    }
                    else {
                        B = T.get(0);
                        T.remove();
                    }
                }

            }
            //  making a root to the Binary tree
                P.makeRoot(new Pair((char)count, A.getData().getProb() + B.getData().getProb()));
                P.attachLeft(A);
                P.attachRight(B);
                T.add(P);
                count++;
            }
            while (T.getSize()>1)
            {
                BinaryTree<Pair> P = new BinaryTree<Pair>();
                P.makeRoot(new Pair((char)count, T.get(0).getData().getProb()+T.get(1).getData().getProb()));
                P.attachLeft(T.get(0));
                P.attachRight(T.get(1));
                T.remove();
                T.remove();
                T.add(P);
                count++;
            }
            //huffman array contains encoded value of each element in the binary tree
        String[] huffman = findEncoding(T.get(0));

        System.out.println();
        System.out.println("Building the Huffman tree .....");
        System.out.println("Huffman coding completed.");
        System.out.println();
        System.out.println("Enter a line of text (uppercase letters only): ");
        String line =sc.nextLine();
        System.out.println();
        System.out.println( encode(huffman,line));
        System.out.println();
        System.out.println(decode(huffman, encode(huffman,line)));
    }

    //static methods for getting all codes findEncoding
    private static String[] findEncoding(BinaryTree<Pair> bt){
        String[] result = new String[26];
        findEncoding(bt, result, "");
        return result;
    }
    private static void findEncoding(BinaryTree<Pair> bt, String[] a, String prefix){
        // test is node/tree is a leaf
        if (bt.getLeft()==null && bt.getRight()==null){
            a[bt.getData().getValue() - 65] = prefix;
        }
        // recursive calls
        else{
            findEncoding(bt.getLeft(), a, prefix+"0");
            findEncoding(bt.getRight(), a, prefix+"1");
        }
    }

    /**
     * this method is used to encode the line that user have input
     * @param huffman array containing the encoded values of every element
     * @param line that user input
     * @return the encoded line that user have input
     */
    public static String encode(String[] huffman, String line){
        String encoded = "";
        for (int i = 0; i < line.length(); i++){
            char ch = line.charAt(i);
            if (ch==' '){
                encoded += ch;
            }
            else {
                encoded += huffman[ch - 65];
            }
        }
        return encoded;
    }

    /**
     * this class is used to decode the encoded line using map to store value with specific key
     * @param huffman array containing the encoded values of every element
     * @param line
     * @return
     */
    public static String decode(String[] huffman, String line){
        String decoded = "";
        String s = "";
        Map<String, Character> map = new HashMap<>();
        for (int i = 0; i < huffman.length; i++){
            map.put(huffman[i], (char)(i+65));
        }

        for (int i = 0; i < line.length(); i++){
            char ch = line.charAt(i);
            s+= ch;
            if (ch == ' '){
                decoded += ch;
                s = "";
            }
            else {
                if (map.get(s) != null){
                    decoded+= map.get(s);
                    s = "";
                }
            }
        }
        return decoded;
    }

}
