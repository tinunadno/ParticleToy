
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
public class Screen{
	private JFrame frame;
	private int xSize;
	private int ySize;
	private int mouseButtonDown;
	private BufferedImage img;
	private boolean closeOnExit;
	public Screen(int xSize, int ySize, boolean closeOnExit){
		this.xSize=xSize;
		this.ySize=ySize;
		this.closeOnExit=closeOnExit;
		frame=new JFrame();
		if(closeOnExit)
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		img = new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_RGB);
		frame.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1)mouseButtonDown=1;
				if(e.getButton()==MouseEvent.BUTTON2)mouseButtonDown=2;
				if(e.getButton()==MouseEvent.BUTTON3)mouseButtonDown=0;
			}
			@Override
			public void mouseReleased(MouseEvent e) {mouseButtonDown=-1;}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
	}
	public void setCloseOnExit(boolean val){
		if(val) frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		else frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void refresh(){
		img = new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_RGB);
	}
	public void setPixel(int x, int y, int r, int g, int b){
		img.setRGB(x, y, Color.colorPoint(r,g,b));
	}
	public void setPixel(int x, int y, int color){
		img.setRGB(x, y, color);
	}
	public Point getMousePosition(){
		Point p = MouseInfo.getPointerInfo().getLocation();
		p = new Point(p.x - frame.getLocation().x, p.y - frame.getLocation().y);
		return p;
	}
	public int isMouseDown(){return mouseButtonDown;}
	public void show(){
		frame.getContentPane().removeAll();
		frame.repaint();
		frame.getContentPane().add(new JLabel(new ImageIcon(img)));
		frame.pack();
		frame.repaint();
		frame.setVisible(true);
	}
}