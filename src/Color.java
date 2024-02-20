public class Color {
    private static final int[] sandColor={colorPoint(227, 197, 154), colorPoint(190,159,116),
            colorPoint(227,213,170)};
    public static int getSandColor(){
        return sandColor[(int)(Math.random()*sandColor.length)];
    }
    public static int colorPoint(int r, int g, int b){
        return (clamp(r, 0, 255))<<16 | (clamp(g, 0, 255))<<8 | (clamp(b, 0, 255));
    }
    public static int clamp(int val, int min, int max) {return Math.max(min, Math.min(max, val));}
}
