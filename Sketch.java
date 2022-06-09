import processing.core.PApplet;

/**
 * Description: This program define arrays to animate a collection of objects.
 * @author: Stephanie Tam
 */

public class Sketch extends PApplet {

  // Global variables
  int intCircleYSpeed = 1;
  int intPlayerX = 200;
  int intPlayerY = 300;
  int intPlayerLives = 3;

  boolean blnPressDown = false;
  boolean blnPressUp = false;
  boolean blnCharW = false;
  boolean blnCharA = false;
  boolean blnCharS = false;
  boolean blnCharD = false;

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  
  public void settings() {
	// put your size call here
    size(400, 400);
  }

  // Blank array used throughout the program
  float[] circleY = new float[25];
  float[] circleX= new float[25];
  boolean[] ballHideStatus = new boolean[25];

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */

  public void setup() {

    // Array to store snowball random X values
    for(int index = 0; index < circleX.length; index++){
      circleX[index] = random(width);
    }

    // Array to store snowball random Y values
    for(int index = 0; index < circleY.length; index++){
      circleY[index] = random(height - 150);
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */

  /**
   * Draws snowball
   * 
   * @param blnPressDown boolean of down arrow key pressed
   * @param blnPressUp boolean of up arrow key pressed
   * @param intCircleYSpeed y speed of circle
   * @param circleX x-coordinate of the ellipse
   * @param circleY y-coordinate of the ellipse
   * @param intPlayerX x-coordinate of user
   * @param intPlayerY y-coordinate of user
   * @param blnCharW boolean of W key pressed
   * @param blnCharA boolean of A key pressed
   * @param blnCharS boolean of S key pressed
   * @param blnCharD boolean of D key pressed
   */

  public void draw() {
    background(130, 180, 240);

    // Snowball speed based on user inputted arrow keys
    if (blnPressDown && intCircleYSpeed < 20){
      intCircleYSpeed ++;
    }
    else if (blnPressUp && intCircleYSpeed > 1){
      intCircleYSpeed --;
    }
    
    // Creates/show new snowballs on screen with circleX and circleY arrays on the condition of ballHideStatus[]
    for (int index = 0; index < circleY.length; index++) {
      fill(255);
      if(!ballHideStatus[index]){
        ellipse(circleX[index], circleY[index], 25, 25);
        circleY[index] += intCircleYSpeed;
        
        if (circleY[index] > height) {
          circleY[index] = 0;
        }
      }
    }

    // ballHideStatus[] tracks whether the user clicks on a snowball, and makes the snowball disappear
    for(int index = 0; index < circleY.length; index++){
      if(dist(mouseX, mouseY, (circleX[index]), circleY[index]) <= 12.5){
        if(mousePressed){
          ballHideStatus[index] = true;
        }
      }
    }

    // Player movement and WASD keys user input
    fill(0, 0, 255);
    ellipse(intPlayerX, intPlayerY, 30, 30);
    if (blnCharW){
      intPlayerY -= 5;
    }
    else if (blnCharA){
      intPlayerX -= 5;
    }
    else if (blnCharS){
      intPlayerY += 5;
    }
    else if (blnCharD){
      intPlayerX += 5;
    }

    // Player edge collision detection
    if (intPlayerX > width - 15){
      intPlayerX = width - 15;
    }
    else if (intPlayerX < 15){
      intPlayerX = 15;
    }
    else if (intPlayerY < 15){
      intPlayerY = 15;
    }
    else if (intPlayerY > height - 15){
      intPlayerY = height - 15;
    }

    // Deduct player lives when the collide with a snowball
    for(int index = 0; index < circleY.length; index++){
      if(dist(intPlayerX, intPlayerY, (circleX[index]), circleY[index]) <= 30){
        circleY[index] = 0;
        intPlayerLives --;
      }
    }

    // Blank screen when user loses all their lives 
    for(int index = 0; index < intPlayerLives; index++){
      rect(355 - (index * 30), 20, 20, 20);
    }
    if (intPlayerLives <= 0){
      fill(255);
      rect(0, 0, width, height);
    }
  }
  
  /**
   * Assigns a value used in the draw method that indicates whether a key is pressed
   */

  public void keyPressed() {
    if (keyCode == DOWN) {
      blnPressDown = true;
    }
    else if (keyCode == UP) {
      blnPressUp = true;
    }
    else if (key == 'w') {
      blnCharW = true;
    }
    else if (key == 'a') {
      blnCharA = true;
    }
    else if (key == 's') {
      blnCharS = true;
    }
    else if (key == 'd') {
      blnCharD = true;
    }
  }

  /**
   * Assigns a value used in the draw method that indicates whether a key was released
   */
  public void keyReleased() {
    if (keyCode == UP) {
      blnPressUp = false;
    }
    else if (keyCode == DOWN){
      blnPressDown = false;
    }
    else if (key == 'w') {
      blnCharW = false;
    }
    else if (key == 'a') {
      blnCharA = false;
    }
    else if (key == 's') {
      blnCharS = false;
    }
    else if (key == 'd') {
      blnCharD = false;
    }
  }


}