import processing.core.PApplet;

public class Sketch extends PApplet {
  float circleX = 50;
  float circleY = 50;

  // Related arrays for the (x, y) coordinate of the snowflakes
  float [] snowX = new float[30]; // 42
  float [] snowY = new float[30]; // 42
  int snowDiameter = 10;

  public void settings() {
    size(400, 400);
  }

  public void setup() {
    background(0);

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

    bluePlayerCirle();

    playerLives();
  }
  
  // All other defined methods are written below:
  public void snow() {
    fill (255);
    for (int i = 0; i < snowX.length; i++) {
      circle (snowX[i], snowY[i], snowDiameter);

      snowY[i]+= 4;

      // Reset snowflakes
      if (snowY[i] > height) {
        snowY[i] = 0;
      }

      // If the down arrow is pressed, the snow falls faster
      // If the up arrow is pressed, the snow falls slower
      if (keyPressed) {
        if (keyCode == DOWN) {
          snowY[i] += 6;
        }
        else if (keyCode == UP) {
          snowY[i] -= 0.5;
        }
      }
    }
  }

  public void bluePlayerCirle() {
    fill (214, 245, 255); // blue
    ellipse(circleX, circleY, 50, 50);
    if (keyPressed) {
      if (key == 'w') {
        circleY -= 2; 
      }
      else if (key == 'a') {
        circleX -= 2;
      }
      else if (key == 's') {
        circleY += 2;
      }
      else if (key == 'd') {
        circleX += 2;
      }
    }
  }

  public void playerLives() {
    square (width - 50, height - 380, 30);
    square (width - 100, height - 380, 30);
    square (width - 150, height - 380, 30);
  }
}