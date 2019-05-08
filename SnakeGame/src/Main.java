import javax.swing.JFrame;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
		JFrame frame=new JFrame();
		GamePanel gamepanel=new GamePanel();
		frame.add(gamepanel);
		
		
		frame.pack();
		frame.setVisible(true);
		frame.setTitle("Snake Game");
		frame.setLocationRelativeTo(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		
		new Main();
		
	}
}
