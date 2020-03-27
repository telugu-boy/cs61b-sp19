public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);
        int amtbodies = in.readInt();
        double radius = in.readDouble();
        return radius;
    }
    public static Body[] readBodies(String filename){
        In in = new In(filename);
        int amtbodies = in.readInt();
        double radius = in.readDouble();
        Body[] bodies = new Body[amtbodies];
        for(int i = 0; i < amtbodies; i++){
            bodies[i] = new Body(in.readDouble(),in.readDouble(),in.readDouble(),
                    in.readDouble(),in.readDouble(),in.readString());
        }
        return bodies;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);
        StdDraw.setScale(-radius,radius);
        StdDraw.picture(0,0,"images/starfield.jpg");
        for(Body body : bodies) {
            body.draw();
        }
        StdDraw.enableDoubleBuffering();
        for(int t = 0; t <= T; t+=dt){
            double[] xforces = new double[bodies.length];
            double[] yforces = new double[bodies.length];
            for(int i = 0; i < bodies.length; i++){
                xforces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yforces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for(int i = 0; i < bodies.length; i++){
                bodies[i].update(dt, xforces[i], yforces[i]);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(Body body : bodies) {
                body.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            System.out.println(t);
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }
}