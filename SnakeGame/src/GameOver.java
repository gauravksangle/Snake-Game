import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameOver {

	Font f1=new Font("DialogInput", Font.BOLD, 20);
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawString("GAME OVER", 25, 25);
		g.setColor(Color.RED);
		g.setFont(f1);
		
		
	}
	
}
