/*
 * THE REAL DEAL
 * snake version john.0
 */

import java.awt.Color; // this lets us create new colors

public class RealSnake {
    //for use in multiple
    private static int bodies = 1;
    private static double[][] snake = new double[bodies][2];
    private static double headX = 0.5;
    private static double newheadX;
    private static double headY = 0.5;
    private static double newheadY;
    private static String keyboard = "wasd";
    private static boolean food = false;
    private static int size = 1;
    private static boolean alive = true;

    //for use in main
    private static boolean cake = false;
    private static int newdir = 5;
    private static int sPEED = 50;
        
    //for use in movement function
    private static boolean tulip = true;
    private static int keypressed = 5;
        
    //for use in food
    private static double foodX = 0.5;
    private static double foodY = 0.75;
        
    //for use in follow
    private static double oldX;
    private static double oldY;
    
    public static void main(String[] args) {
        
        //Set up standard canvas
        StdDraw.setCanvasSize(455, 455);
        StdDraw.setXscale(0.045, 0.955);
        StdDraw.setYscale(0.045, 0.955);
        //FOR REFERENCE: you have 910 scale in 455 pixels
        
        //draw snake head
        StdDraw.filledCircle(headX, headY, 0.01);              
        
        
        //#runit
        while (true) {
            //check if alive  
            
            if (alive == false) {
                System.out.println("mandrake");
                /*StdDraw.clear(Color.BLACK);
                StdDraw.setPenColor(Color.WHITE);
                StdDraw.filledRectangle(0.5, 0.5, 0.2, 0.15);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.text(0.5, 0.6, "GAME OVER");
                StdDraw.text(0.5, 0.4, "Score: " + size);
                StdDraw.show(300000000);
                */
            }
            
            //this will happen if movement has already started
            else if (cake == true) {
                newdir = snakeMovement(newdir);
            }
            else if (StdDraw.hasNextKeyTyped()) {
               char key = StdDraw.nextKeyTyped();
               
               // determine whether key is on keyboard                
                for (int i = 0; i < keyboard.length(); i++) {
                    if (key == keyboard.charAt(i))
                        cake = true;
                }
                if (cake == false) continue;
                
                else {
                    int index = keyboard.indexOf(key);
                    newdir = snakeMovement(index);
                }
           }
        }
    }
    
    public static int snakeMovement(int direction) {
        
        //loop these, stop when a CORRECT key is pressed
        //(you have to check within this function)
        if (direction == 0) {
            //System.out.println("up");
            while (tulip == true) {
                StdDraw.clear(Color.WHITE);
                
                //redraw food
                StdDraw.setPenColor(Color.RED);
                StdDraw.filledSquare(foodX, foodY, 0.01);
                StdDraw.setPenColor(Color.BLACK);
                
                //redraw score
                StdDraw.text(0.87, 0.07, "Score: " + size);
                
                newheadY = headY += 0.02;
                StdDraw.filledCircle(headX, headY, 0.01);
                
                //check for crashing into self
                for (int j = 1; j < bodies; j++) {
                    if (headX == snake[j][0] && headY == snake[j][1]) {
                        youLose();
                    }
                }
                
                follow(headX, headY);
                StdDraw.show(sPEED);
                
                checkFood(headX, headY);
                weGood();
                //if (alive = false) return 5;
                
                if (StdDraw.hasNextKeyTyped()) {
                    char newkey = StdDraw.nextKeyTyped();
                
                    // determine whether key is on keyboard                
                    for (int i = 0; i < keyboard.length(); i++) {
                        if (newkey == keyboard.charAt(i))
                            tulip = false;
                    }
                    if (tulip == false) keypressed = keyboard.indexOf(newkey);
                    if (keypressed == direction) tulip = true;
                }
            }
        }
        if (direction == 1) {
            //System.out.println("left");
            while (tulip == true) {
                StdDraw.clear(Color.WHITE);
                
                
                //redraw food
                StdDraw.setPenColor(Color.RED);
                StdDraw.filledSquare(foodX, foodY, 0.01);
                StdDraw.setPenColor(Color.BLACK);
                
                //redraw score
                StdDraw.text(0.87, 0.07, "Score: " + size);
                
                newheadX = headX -= 0.02;
                StdDraw.filledCircle(headX, headY, 0.01);
                
                //check for crashing into self
                for (int j = 0; j < bodies; j++) {
                    if (headX == snake[j][0] && headY == snake[j][1]) {
                        youLose();
                    }
                }
                
                follow(headX, headY);
                StdDraw.show(sPEED);
                
                checkFood(headX, headY);
                weGood();
                //if (alive = false) return 5;
                
                if (StdDraw.hasNextKeyTyped()) {
                    char newkey = StdDraw.nextKeyTyped();
                
                    // determine whether key is on keyboard                
                    for (int i = 0; i < keyboard.length(); i++) {
                        if (newkey == keyboard.charAt(i))
                            tulip = false;
                    }
                    if (tulip == false) keypressed = keyboard.indexOf(newkey);
                    if (keypressed == direction) tulip = true;
                }
            }
        }
        if (direction == 2) {
            //System.out.println("down");
            while (tulip == true) {
                StdDraw.clear(Color.WHITE);
                
                //redraw food
                StdDraw.setPenColor(Color.RED);
                StdDraw.filledSquare(foodX, foodY, 0.01);
                StdDraw.setPenColor(Color.BLACK);
                
                //redraw score
                StdDraw.text(0.87, 0.07, "Score: " + size);
                
                newheadY = headY -= 0.02;
                StdDraw.filledCircle(headX, headY, 0.01);
                
                //check for crashing into self
                for (int j = 0; j < bodies; j++) {
                    if (headX == snake[j][0] && headY == snake[j][1]) {
                        youLose();
                    }
                }
                
                follow(headX, headY);
                StdDraw.show(sPEED);
                
                checkFood(headX, headY);
                weGood();
                //if (alive = false) return 5;
                
                if (StdDraw.hasNextKeyTyped()) {
                    char newkey = StdDraw.nextKeyTyped();
                
                    // determine whether key is on keyboard                
                    for (int i = 0; i < keyboard.length(); i++) {
                        if (newkey == keyboard.charAt(i))
                            tulip = false;
                    }
                    if (tulip == false) keypressed = keyboard.indexOf(newkey);
                    if (keypressed == direction) tulip = true;
                }
            }
        }
        if (direction == 3) { //direction should be 3
            //System.out.println("right");
            while (tulip == true) {
                StdDraw.clear(Color.WHITE);
                
                //redraw food
                StdDraw.setPenColor(Color.RED);
                StdDraw.filledSquare(foodX, foodY, 0.01);
                StdDraw.setPenColor(Color.BLACK);
                
                //redraw score
                StdDraw.text(0.87, 0.07, "Score: " + size);
                
                newheadX = headX += 0.02;
                StdDraw.filledCircle(newheadX, headY, 0.01);
                
                //check for crashing into self
                for (int j = 0; j < bodies; j++) {
                    if (headX == snake[j][0] && headY == snake[j][1]) {
                        youLose();
                    }
                }
                
                follow(headX, headY);
                StdDraw.show(sPEED);
                
                checkFood(headX, headY);
                weGood();
                //if (alive = false) return 5;
                
                if (StdDraw.hasNextKeyTyped()) {
                    char newkey = StdDraw.nextKeyTyped();
                
                    // determine whether key is on keyboard                
                    for (int i = 0; i < keyboard.length(); i++) {
                        if (newkey == keyboard.charAt(i))
                            tulip = false;
                    }
                    if (tulip == false) keypressed = keyboard.indexOf(newkey);
                    if (keypressed == direction) tulip = true;
                }
            }
        }
        tulip = true;
        return keypressed;
    }
        
    
    public static boolean food() {
        System.out.println("reached");
        StdDraw.setPenColor(Color.RED);
        foodX = (Math.round(Math.random()*50.0))/50.0;
        foodY = (Math.round(Math.random()*50.0))/50.0;
        StdDraw.filledSquare(foodX, foodY, 0.01);
        System.out.println(foodX + ", " + foodY);
        
        if (foodX == 0.0 || foodX == 1.0 || 
            foodY == 0.0 || foodY == 1.0) food();
        
        food = true;
        return food;
    }
    
    
    public static int getBig() {
        food = false;
        double[][] newsnake = new double[bodies + 1][2];
        for (int i = 0; i < bodies; i++) {
            for (int j = 0; j < 2; j++) {
                newsnake[i][j] = snake[i][j];
                
            }
        }
        bodies += 1;
        newsnake[bodies-1][0] = 0.0;
        newsnake[bodies-1][1] = 0.0;
        snake = newsnake;
        
        return bodies;
    }
    
    public static void follow(double x, double y) {
        for (int k = 0; k < bodies; k++) {
            oldX = snake[k][0];
            snake[k][0] = x;
            x = oldX;
        }
        for (int m = 0; m < bodies; m++) {
            oldY = snake[m][1];
            snake[m][1] = y;
            y = oldY;
        }       
        for (int n = 0; n < bodies; n++) {
            StdDraw.filledCircle(snake[n][0], snake[n][1], 0.01);
        }
    }
    
    public static void checkFood(double checkX, double checkY) {
        if (checkX < (foodX + 0.01) && checkX > (foodX - 0.01)) {
            if (checkY < (foodY + 0.01) && checkY > (foodY - 0.01)) {
                food = food();
                size = getBig();
            }
        }
    }
    
    public static void weGood() {
        if (headX < 0.0) {
            System.out.println("source 1");
            youLose();
        }
        if (headX > 1.0) {
            System.out.println("source 2");
            youLose();
        }
        if (headY < 0.0) {
            System.out.println("source 3");
            youLose();
        }
        if (headY > 1.0) {
            System.out.println("source 4");
            youLose();
        }
    }
    
    public static void youLose() {
        System.out.println("potato");
        alive = false;
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.filledRectangle(0.5, 0.5, 0.2, 0.15);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(0.5, 0.6, "GAME OVER");
        StdDraw.text(0.5, 0.4, "Score: " + size);
        StdDraw.show(300000000);
    }
}

