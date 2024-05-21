import processing.core.PApplet;

/**
 * Program Sketch.java is a sketch that simulates snowfales falling. 
 * When the down arrow is pressed on the keyboard, the snow falls faster. 
 * When the up arrow is pressed on the keyboard, the snow falls slower.
 * Users have three lives and lose a life everytime their blue player circle controlled by their WASD keys collides with a snowflake.
 * The game ends when all lives are lost and the screen clears to white.
 * A user's mouse can be used to click on snowflakes and make them disappear if they are clicked on. 
 * @author: E. Yap
 */
public class Sketch extends PApplet {
  // Related arrays for the (x, y) coordinate of the snowflakes
  float [] snowX = new float[15]; 
  float [] snowY = new float[15]; 
  int snowDiameter = 20; 

  // Related variables for the (x, y) coordinates of the blue player circle
  float circleX = 50;
  float circleY = 50;

  // Player has 3 lives
  int playerLives = 3;

  // Declare blue player WASD variables
  boolean wPressed = false;
  boolean aPressed = false;
  boolean sPressed = false;
  boolean dPressed = false;

  // Declare snow speed variables
  boolean upPressed = false;
  boolean downPressed = false;

  boolean[] ballHideStatus;

  /**
   * Settings
   * Window size
   * @author: E. Yap
   */
  public void settings() {
    size(400, 400);
    ballHideStatus = new boolean[snowX.length];
  }

  /**
   * Initial set up values
   * Background colour
   * @author: E. Yap
   */
  public void setup() {
    background(0); // black

    ballHideStatus = new boolean [snowX.length];

    // Generate random x- and y- values for snowflakes
    for (int i = 0; i < snowX.length; i++) {
      snowX[i] = random(width);
      snowY[i] = random(height);
    }
  }

  /**
   * Everything drawn to the screen
   * @author: E. Yap
   */
  public void draw() {
    // Background colour
    background (0); // black

    // Draw snow
    snow();

    // Draw rectangles indicating player lives and game over screen
    playerLives();

    // If player still has lives, blue player circle still appears
    if (playerLives > 0) {
      bluePlayerCirle();
    }

    // Move blue player circle if WASD is pressed
    if (wPressed) {
      circleY--;
    }
    if (sPressed) {
      circleY++;
    }
    if (aPressed) {
      circleX--;
    }
    if (dPressed) {
      circleX++;
    }

    // Make snowflakes dissapear when they are clicked
    mouseClicked();
  }
  
  // All other defined methods are written below:
  /**
   * If the ball and snowflake collide, player loses one life and snowflake disappears.
   * If the down arrow is pressed, the snow falls faster
   * If the up arrow is pressed, the snow falls slower
   * If no arrow is pressed, the snow falls
   */
  public void snow() {
    for (int i = 0; i < snowX.length; i++) {
      if (!ballHideStatus[i]) { 
        ellipse(snowX[i], snowY[i], 20, 20); 

        if (dist(snowX[i], snowY[i], circleX, circleY) < 35) {
          playerLives--;
          snowY[i] = 0;
          snowX[i] = random(width);
          ballHideStatus[i] = true;
        }
      }
    }

    fill (255);
    for (int i = 0; i < snowX.length; i++) {
      if (!ballHideStatus[i])
      circle (snowX[i], snowY[i], snowDiameter);

      // If the down arrow is pressed, the snow falls faster
      // If the up arrow is pressed, the snow falls slower
      // If no arrow is pressed, the snow falls
      if (downPressed) {
        snowY[i] += 4;
      }
      else if (upPressed) {
        snowY[i] += 0.5;
      }
      else {
        snowY[i] += 2;
      }

      // Reset snowflakes
      if (snowY[i] > height) {
        snowY[i] = 0;
        ballHideStatus[i] = false;
      }
    }
  }

  /**
   * Draws three rectangles that indicate player lives
   * If the player has no more lives, the screen clear to white 
   * @author: E. Yap
   */
  public void playerLives() {
    for (int i = 0; i < playerLives; i++) {
      float x = width - 50 - i * 50;
      float y = 20;
      fill (255);
      rect (x, y, 30, 30);
    }

    // The game ends when all lives are lost and the screen clear to white
    if (playerLives <= 0) {
      background (255); // white
    }
  }

  /**
   * Draws a blue player circle
   * @author: E. Yap
   */
  public void bluePlayerCirle() {
    // Blue player circle
    fill (214, 245, 255); // blue
    ellipse(circleX, circleY, 50, 50);
  }

  /**
   * Constrains the blue player circle to the window.
   * If the up arrow is pressed, the snow falls faster.
   * If the down arrow is pressed, the snow falls slower.
   * If the 'w' key is pressed the blue player circle moves up.
   * If the 'a' key is pressed the blue player circle moves left.
   * If the 's' key is pressed the blue player circle moves down.
   * If the 'd' key is pressed the blue player circle moves right.
   * @author: E. Yap
   */
  public void keyPressed() {
    circleX = constrain (circleX, 0, width);
    circleY = constrain (circleY, 0, height);

    if (keyCode == UP) {
      upPressed = true;
    }
    else if (keyCode == DOWN) {
      downPressed = true;
    }

    if (key == 'w' || key == 'W') {
      wPressed = true;
    }
    if (key == 'a' || key == 'A') {
      aPressed = true;
    }
    if (key == 's' || key == 'S') {
      sPressed = true;
    }
    if (key == 'd' || key == 'D') {
      dPressed = true;
    }
  }

  /**
   * Release for up and down arrows for the speed that the snow falls at.
   * Release for the WASD keys for the direction of the blue player circle. 
   * @author: E. Yap
   */
  public void keyReleased() {
    if (keyCode == UP) {
      upPressed = false;
    }
    else if (keyCode == DOWN) {
      downPressed = false;
    }

    if (key == 'w' || key == 'W') {
      wPressed = false;
    }
    else if (key == 'a' || key == 'A') {
      aPressed = false;
    }
    else if (key == 's' || key == 'S') {
      sPressed = false;
    }
    else if (key == 'd' || key == 'D') {
      dPressed = false;
    }
  }

  /**
   * Snowflakes disappear when they are clicked
   * @author: E. Yap
   */
  public void mouseClicked() {
    // Make snowflakes disappear when they are clicked
    float clickRadius = 10;
    for (int i = 0; i < snowX.length; i++) {
      float distance = dist(snowX[i], snowY[i], mouseX, mouseY);
      if (distance < clickRadius && mousePressed) {
        ballHideStatus[i] = true;
      }
    }
  }
}