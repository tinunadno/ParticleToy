public class ParticleSystem{
	private int xSize;
	private int ySize;
	private int[][] particleType;
	private int[][] particleColor;
	private static final int radius=3;
	public ParticleSystem(int xSize, int ySize){
		this.xSize=xSize;
		this.ySize=ySize;
		particleType=new int[xSize][ySize];
		particleColor=new int[xSize][ySize];
	}
	public boolean isEmpty(int x, int y){return (particleType[x][y]!=0);}
	public void setCell(int x, int y, int val){
		particleType[x][y]=val;
		if(val==1)
			particleColor[x][y]=Color.getSandColor();
		if(val==2)
			particleColor[x][y]=Color.colorPoint(100, 100, 100);
	}
	public void setCellCircle(int x, int y, int val){
		for(int i=(-1)*radius;i<radius+1;i++)
			for(int j=(-1)*radius;j<radius+1;j++)
				if(Math.abs(i*j)<radius) {
					if (x + j > 0 && x + j < xSize && y + i > 0 && y + i < ySize)
						this.setCell(x + j, y + i, val);
				}

	}
	public void swap(int ax, int ay, int bx, int by){
		int temp=this.getParticleType(ax, ay);
		this.setParticleType(ax, ay, this.getParticleType(bx, by));
		this.setParticleType(bx, by, temp);
		temp=this.getParticleColor(ax, ay);
		this.setParticleColor(ax, ay, this.getParticleColor(bx, by));
		this.setParticleColor(bx, by, temp);
	}
	public void update(){
		for(int i=ySize-1;i>0;i--){
			for(int j=0;j<xSize;j++){
				if(this.getParticleType(j, i-1)!=0 && this.getParticleType(j, i-1)!=2) {
					if ( this.getParticleType(j, i) == 0) swap(j, i - 1, j, i);
					if (this.getParticleType(j-1, i) == 0) swap(j, i - 1, j - 1, i);
					if ( this.getParticleType(j+1, i) == 0) swap(j, i - 1, j + 1, i);
				}
			}
		}
	}

	public int getParticleType(int x, int y){return particleType[(xSize+x)%xSize][(ySize+y)%ySize];}
	public int getParticleColor(int x, int y){return particleColor[(xSize+x)%xSize][(ySize+y)%ySize];}
	public void setParticleType(int x, int y, int val){particleType[(xSize+x)%xSize][(ySize+y)%ySize]=val;}
	public void setParticleColor(int x, int y, int val){particleColor[(xSize+x)%xSize][(ySize+y)%ySize]=val;}
	public int getXSize(){return xSize;}
	public int getYSize(){return ySize;}
}