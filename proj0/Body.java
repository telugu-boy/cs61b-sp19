import java.lang.Math;

public class Body {
    public static final double G = 6.67e-11;
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    public Body(double xxPos, double yyPos, double xxVel,
                double yyVel, double mass, String imgFileName){
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }
    public Body(Body b) {
        this(b.xxPos,b.yyPos,b.xxVel,b.yyVel, b.mass,b.imgFileName);
    }
    double calcDistance(Body other){
        return Math.sqrt(Math.pow(this.xxPos - other.xxPos, 2)+Math.pow(this.yyPos - other.yyPos, 2));
    }
    double calcForceExertedBy(Body other){
        double distance = calcDistance(other);
        double r2 = Math.pow(distance, 2);
        double F = G*this.mass*other.mass/r2;
        return F;
    }
    double calcForceExertedByX(Body other){
        //F * dx / r
        return calcForceExertedBy(other)*(other.xxPos - this.xxPos)/calcDistance(other);
    }
    double calcForceExertedByY(Body other){
        //F * dy / r
        return calcForceExertedBy(other)*(other.yyPos - this.yyPos)/calcDistance(other);
    }
    double calcNetForceExertedByX(Body[] others){
        double netx = 0;
        for(Body other : others){
            if(!this.equals(other)){
                netx += calcForceExertedByX(other);
            }
        }
        return netx;
    }
    double calcNetForceExertedByY(Body[] others){
        double nety = 0;
        for(Body other : others){
            if(!this.equals(other)){
                nety += calcForceExertedByY(other);
            }
        }
        return nety;
    }
    void update(double dt, double xf, double yf){
        double anetx = xf/this.mass;
        double anety = yf/this.mass;
        this.xxVel += dt * anetx;
        this.yyVel += dt * anety;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }
    void draw(){
        StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
    }
}