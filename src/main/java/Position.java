public class Position {
    private int x;
    private int y;
    public Position(int x_,int y_){
        x = x_;
        y = y_;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x_){
        x = x_;
    }
    public void setY(int y_){
        y = y_;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Position p = (Position) o;
        return x == p.getX() && y == p.getY();
    }
}
