import processing.core.PApplet;

public class Sketch extends PApplet {
  // Related arrays for the (x, y) coordinate of the snowflakes
  float [] snowX = new float[15]; 
  float [] snowY = new float[15]; 
  int snowDiameter = 20; 

  float circleX = 50;
  float circleY = 50;
  int playerLives = 3;

  // Declare blue player variables
  boolean wPressed = false;
  boolean aPressed = false;
  boolean sPressed = false;
  boolean dPressed = false;

  // Declare snow speed variables
  boolean upPressed = false;
  boolean downPressed = false;

  boolean[] ballHideStatus;

  public void settings() {
    size(400, 400);
    ballHideStatus = new boolean[snowX.length];
  }

  public void setup() {
    background(0);

    ballHideStatus = new boolean [snowX.length];

    // Generate random x- and y- values for snowflakes
    for (int i = 0; i < snowX.length; i++) {
      snowX[i] = random(width);
      snowY[i] = random(height);
    }
  }

  public void draw() {
    background (0);

    // Draw snow
    snow();

    playerLives();

    if (playerLives > 0) {
      bluePlayerCirle();
    }

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

    mouseClicked();
  }
  
  // All other defined methods are written below:
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
      if(!ballHideStatus[i])
      circle (snowX[i], snowY[i], snowDiameter);

      // If the down arrow is pressed, the snow falls faster
      // If the up arrow is pressed, the snow falls slower
      // If no arrow is pressed, the snow falls
      if (downPressed) {
        snowY[i] += 5;
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
        ballHideStatus[i]=false;
      }
    }
  }

  public void playerLives() {
    for (int i = 0; i < playerLives; i++) {
      float x = width - 50 - i * 50;
      float y = 20;
      fill (255);
      rect (x, y, 30, 30);
    }

    if (playerLives <= 0) {
      background (255);
      textSize (50);
      fill (0);
      textAlign (CENTER, CENTER);
      text ("Game Over", width/2, height/2);
    }
  }

  public void bluePlayerCirle() {
    // Blue player circle
    fill (214, 245, 255); // blue
    ellipse(circleX, circleY, 50, 50);
  }

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

  public void mouseClicked() {
    // Make snowflakes disappear when they are clicked
    float clickRadius = 10;
    for (int i = 0; i < snowX.length; i++) {
      float distance = dist(snowX[i], snowY[i], mouseX, mouseY);
      if (distance < clickRadius&&mousePressed) {
        ballHideStatus[i] = true;
      }
    }
  }
}