public class NBody{
    public static double readRadius(String file_name){
        In in = new In(file_name);
        in.readInt();
        return in.readDouble();
    }
    public static Planet[] readPlanets(String file_name){
        In in = new In(file_name);
        int number = in.readInt();
        in.readDouble();
        Planet[] array = new Planet[number];
        int index = 0;
        while(number > index){
            Planet new_planet = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
            array[index] = new_planet;
            index++;
        }
        return array;
    }
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        int num = 0;

        for(Planet planet : planets){
            planet.draw();
            num++;
        }
        
        double time = 0;
        while(time != T){
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            int index = 0;
            for(Planet planet : planets){
                xForces[index] = planet.calcNetForceExertedByX(planets);
                yForces[index] = planet.calcNetForceExertedByY(planets);
                index++;
            }
            int i = 0;
            for(Planet planet : planets) {
                planet.update(dt, xForces[i], yForces[i]);
                i++;
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(Planet planet: planets) {
                planet.draw();
            }
            StdDraw.show(10);
            time += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}