package Photomosaic;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //Demo is run when user clicks Ok button in UserInput form
        UserInput userInput = new UserInput();
        userInput.setupAndGetUserInput();
    }
}
