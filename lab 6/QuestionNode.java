// Programmer: Marcel Rodriguez
// Date: 8/05/2023
// Class: CS &145
// Lab 6: 20 Questions
// Purpose: Define the structure of a node in the 20 Questions game binary tree.

public class QuestionNode {
    String data; //text data of node (question/answer)
    QuestionNode yes; //link to "yes" branch 
    QuestionNode no; //link to "no" branch 
    boolean isAnswer; //flag for answer node
    
    //Constructor for creating answer node
    public QuestionNode(String data) {
        this(data, null, null);
    }
    //Constructor for creating a q/a node.
    public QuestionNode(String data, QuestionNode yes, QuestionNode no) {
        this.data = data;
        this.yes = yes;
        this.no = no;
        this.isAnswer = true; // Initialize to answer node by default
    }
    //Check if node is an answer node
    public boolean isAnswer() {
        return isAnswer;
    }
    //Set the answer status of the node
    public void setAnswer(boolean isAnswer) {
        this.isAnswer = isAnswer;
    }
}


