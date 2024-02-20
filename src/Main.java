import java.awt.*;
import java.awt.event.MouseEvent;

public class Main {
	private static final int X_SIZE=800, Y_SIZE=600;
	private static final double SCALE=0.25;
	private static final Screen scrn=new Screen(X_SIZE, Y_SIZE, true);
	public static void main(String[] args){
		ParticleSystem partSys=new ParticleSystem((int)(X_SIZE*SCALE), (int)(Y_SIZE*SCALE));
		while(true){
			scrn.refresh();
			partSys.update();
			displayParticleSystem(partSys);
			scrn.show();
			try{Thread.sleep(1);}
			catch(Exception e){}
			if(scrn.isMouseDown()!=-1) {
				Point p = scrn.getMousePosition();
				p.setLocation(p.getX() * SCALE - 2, p.getY() * SCALE - 9);
				if (p.getX() > 0 && p.getX() < partSys.getXSize() && p.getY() > 0 && p.getY() < partSys.getYSize()) {
					partSys.setCellCircle((int) p.getX(), (int) p.getY(), scrn.isMouseDown());
				}
			}
			
		}
	}
	private static void displayParticleSystem(ParticleSystem partSys){
		for(int i=0;i<Y_SIZE;i++){
			for(int j=0;j<X_SIZE;j++){
				int x=(int)(j*SCALE);
				int y=(int)(i*SCALE);
				if(partSys.isEmpty(x, y))scrn.setPixel(j, i, partSys.getParticleColor(x, y));
				else scrn.setPixel(j, i,255,255,255);
			}
		}
	}
}